package net.datenwerke.rs.core.service.reportserver;

import javax.servlet.http.HttpServletRequest;

import net.datenwerke.rs.utils.config.ConfigService;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class ReportServerServiceImpl implements ReportServerService {


	public static final String HIBERNATE_PROPERTY_CONNECTION_DRIVER = "hibernate.connection.driver_class";
	
	private final ConfigService configService;
	private final Provider<ServerInfoContainer> serverInfoContainerProvider; 
	
	@Inject
	public ReportServerServiceImpl(
		ConfigService configService,
		Provider<ServerInfoContainer> serverInfoContainerProvider
		){
		
		this.configService = configService;
		this.serverInfoContainerProvider = serverInfoContainerProvider;
	}
	
	@Override
	public String getCharset() {
		return configService.getConfigFailsafe(CONFIG_FILE).getString("default.charset",DEFAULT_CHARSET);
	}

	void init(HttpServletRequest httpServletRequest) {
		ServerInfoContainer serverInfoContainer = serverInfoContainerProvider.get();
		if(null != serverInfoContainer)
			serverInfoContainer.init(httpServletRequest);
	}
	
	@Override
	public ServerInfoContainer getServerInfo() {
		return serverInfoContainerProvider.get();
	}

}
