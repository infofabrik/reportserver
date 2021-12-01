package net.datenwerke.rs.authenticator.service.pam;

import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;

import javax.servlet.http.HttpServletRequest;

import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x500.style.IETFUtils;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.utils.properties.ApplicationPropertiesService;
import net.datenwerke.security.client.login.AuthToken;
import net.datenwerke.security.service.authenticator.AuthenticationResult;
import net.datenwerke.security.service.authenticator.ReportServerPAM;
import net.datenwerke.security.service.authenticator.exceptions.AuthenticatorRuntimeException;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.entities.User;

public class ClientCertificateMatchEmailPAM implements ReportServerPAM{
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	private static final String debugConfigKey = "rs.authenticator.pam.ClientCertificateMatchEmailPAM.debug";
	
	private final Provider<HttpServletRequest> requestProvider;
	private final UserManagerService userManagerService;
	private final ApplicationPropertiesService propsService;

	private final boolean debug;

	@Inject
	public ClientCertificateMatchEmailPAM(
			Provider<HttpServletRequest> requestProvider, 
			ApplicationPropertiesService propsService,
			UserManagerService userManagerService
	) {

		this.requestProvider = requestProvider;
		this.propsService = propsService;
		this.userManagerService = userManagerService;
		
		this.debug = propsService.getBoolean(debugConfigKey, false);
	}

	@Override
	public AuthenticationResult authenticate(AuthToken[] tokens) {
		HttpServletRequest request = requestProvider.get();

		if(null == request){
			throw new AuthenticatorRuntimeException("Not in a request scope"); //$NON-NLS-1$
		}

		X509Certificate[] certificates = (X509Certificate[]) request.getAttribute("javax.servlet.request.X509Certificate"); //$NON-NLS-1$

		if(debug) System.out.println("Trying certificate authentication");
		if(null != certificates && certificates.length > 0){
			if(debug) System.out.println("found certificate");
			X509Certificate crt = certificates[0];
			if(debug) System.out.println(crt);
			String mail = null;
			
			try{
				if(debug) System.out.println("trying subject alternative names");
				if (crt.getSubjectAlternativeNames() != null) {
					java.util.Collection altNames = crt.getSubjectAlternativeNames();
					java.util.Iterator i = altNames.iterator();
					
					while(i.hasNext()) {
						java.util.List item = (java.util.List)i.next();
						Integer type = (Integer)item.get(0);
						if (type.intValue() == 1) {
							mail = (String)item.get(1);
						}
					}
					if(debug) System.out.println("got email " + mail);
				}else{
					if(debug) System.out.println("no subject alternative names");
				}
			}catch(Exception e){
				logger.warn( "Error reading names from certificate", e);
			}

			
			if(null == mail){
				if(debug) System.out.println("trying subject");
				try {
					mail = IETFUtils.valueToString(new JcaX509CertificateHolder(crt).getSubject().getRDNs(BCStyle.E)[0].getFirst().getValue());
				} catch (Exception e1) {
					if(debug) System.out.println("no email in subject");
				}
			}

			if(null != mail){
				if(debug) System.out.println("performing certauth with email " + mail);
				User u = userManagerService.getUserByMail(mail);
				if(u != null){
					if(debug) System.out.println("user id: " + u.getId());
					return new AuthenticationResult(true, u);
				}else{
					if(debug) System.out.println("No user. aborting certauth");
				}
			}else{
				if(debug) System.out.println("no email. abort certauth");
			}

		}else{
			if(debug) System.out.println("no certificate. abort certauth");
		}

		
		return new AuthenticationResult(!isAuthoritative(), null);
	}

	@Override
	public String getClientModuleName() {
		return null;
	}

	protected boolean isAuthoritative() {
		return false;
	}
}
