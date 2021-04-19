package net.datenwerke.rs.core.service;

import java.util.Set;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.authenticator.AuthenticatorModule;
import net.datenwerke.gf.service.lateinit.LateInitHook;
import net.datenwerke.rs.utils.properties.ApplicationPropertiesService;
import net.datenwerke.security.service.authenticator.ReportServerPAM;

public class EnvironmentAfterStartupInformation implements LateInitHook {

	private final Logger logger = LoggerFactory.getLogger(getClass().getName());
	
	private final ApplicationPropertiesService propertiesService;
	private final Provider<Set<ReportServerPAM>> pamProvider;
	private final Provider<ServletContext> servletContextProvider;


	@Inject
	public EnvironmentAfterStartupInformation(
		ApplicationPropertiesService propertiesService,
		Provider<Set<ReportServerPAM>> pamProvider,
		Provider<ServletContext> servletContextProvider
		) {
		this.propertiesService = propertiesService;
		this.pamProvider = pamProvider;
		this.servletContextProvider = servletContextProvider;
	}
	
	@Override
	public void initialize() {
		
		StringBuilder sb = new StringBuilder("\r\n\r\n");
		applicationServerConfig(sb);
		pamConfig(sb);
		
		logger.info(sb.toString());
	}

	private void applicationServerConfig(StringBuilder sb) {
		try{
			sb.append("Application Server: ") 
				.append(servletContextProvider.get().getServerInfo())
				.append("\r\n\r\n");
		} catch(Exception e){
			logger.warn("Could not log servlet context information", e);
		}
	}

	private void pamConfig(StringBuilder sb) {
		String authenticatorsString = propertiesService.getString(AuthenticatorModule.AUTHENTICATORS_PROPERTY_NAME);
		
		sb.append("### PAM Configuration ###\r\n");
		sb.append("Static PAM configuration: ").append(authenticatorsString).append("\r\n");
		
		/* log finalized pams */
		sb.append("Finalized PAM configuration: ");
		boolean first = true;
		for(ReportServerPAM p : pamProvider.get()){
			if(first)
				first = false;
			else
				sb.append(", ");
			sb.append(p.getClass());
		}
		sb.append("\r\n\r\n");
	}

}
