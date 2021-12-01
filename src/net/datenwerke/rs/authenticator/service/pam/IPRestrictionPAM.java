package net.datenwerke.rs.authenticator.service.pam;

import javax.servlet.http.HttpServletRequest;

import net.datenwerke.rs.utils.misc.IPUtils;
import net.datenwerke.rs.utils.properties.ApplicationPropertiesService;
import net.datenwerke.security.client.login.AuthToken;
import net.datenwerke.security.service.authenticator.AuthenticationResult;
import net.datenwerke.security.service.authenticator.ReportServerPAM;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;


public class IPRestrictionPAM implements ReportServerPAM{
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	private final static String IP_ADDRESSES_PROPERTY_NAME = "rs.authenticator.iprestriction.addresses";
	
	private final Provider<HttpServletRequest> requestProvider;
	private final ApplicationPropertiesService propertiesService;
	private final IPUtils iputils;
	
	@Inject
	public IPRestrictionPAM(
			Provider<HttpServletRequest> requestProvider, 
			ApplicationPropertiesService propertiesService,
			IPUtils iputils) {
		this.requestProvider = requestProvider;
		this.propertiesService = propertiesService;
		this.iputils = iputils;
	}

	public AuthenticationResult authenticate(AuthToken[] tokens) {
		/* load ip addresses */
		String ipAddresses = propertiesService.getString(IP_ADDRESSES_PROPERTY_NAME);
		
		/* get request */
		HttpServletRequest request =requestProvider.get();
		String remoteAddress = request.getRemoteAddr();
		for(String ipString : ipAddresses.split(":")){
			ipString = ipString.trim();
			
			if(iputils.contains(ipString, remoteAddress))
				return new AuthenticationResult(true, null);
		}
		
		logger.info("Blocked access from: " + remoteAddress);
		return new AuthenticationResult(false, null);
	}
	
	
	public String getClientModuleName() {
		return null;
	}


}
