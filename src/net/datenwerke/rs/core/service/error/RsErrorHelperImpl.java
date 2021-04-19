package net.datenwerke.rs.core.service.error;

import javax.inject.Provider;

import net.datenwerke.gf.service.localization.RemoteMessageService;
import net.datenwerke.rs.core.service.RsCoreModule;
import net.datenwerke.rs.core.service.reportserver.ServerInfoContainer;
import net.datenwerke.rs.utils.config.ConfigService;
import net.datenwerke.rs.utils.exception.ExceptionServices;
import net.datenwerke.rs.utils.juel.SimpleJuel;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;

import com.google.inject.Inject;

public class RsErrorHelperImpl implements RsErrorHelper {

	private final Provider<SimpleJuel> juelProvider;
	private final ExceptionServices exceptionServices;
	private final ConfigService configService;
	private final ServerInfoContainer serverInfo;
	private final RemoteMessageService remoteMessageService;
	
	@Inject
	public RsErrorHelperImpl(
		Provider<SimpleJuel> juel,
		ExceptionServices exceptionServices,
		ConfigService configService, 
		ServerInfoContainer serverInfo,
		RemoteMessageService remoteMessageService
		) {
		
		this.juelProvider = juel;
		this.exceptionServices = exceptionServices;
		this.configService = configService;
		this.serverInfo = serverInfo;
		this.remoteMessageService = remoteMessageService;
	}



	@Override
	public String getHtmlErrorPage(String headline, String msg, String stacktrace) {
		String template = null;
		try{
			template =  configService.getConfig(RsCoreModule.TEMPLATES_CONFIG_FILE).getString(RsCoreModule.ERROR_TEMPLATE_PROPERTY);
		}catch(Exception e){}
		template = null == template ? "Error template could not be loaded." : template;
		
		SimpleJuel juel = juelProvider.get();
		juel.addReplacement("headline", headline);
		juel.addReplacement("msg", msg);
		juel.addReplacement("stacktrace", stacktrace);
		juel.addReplacement("baseurl", serverInfo.getBaseURL());
		
		String currentLanguage = LocalizationServiceImpl.getLocale().getLanguage();
		juel.addReplacement("msgs", remoteMessageService.getMessages(currentLanguage));
		
		return juel.parse(template);
	}


	@Override
	public String getHtmlErrorPage(String headline,
			Exception e) {
		return getHtmlErrorPage(headline, e.getMessage(), exceptionServices.exceptionToString(e));
	}

	
}
