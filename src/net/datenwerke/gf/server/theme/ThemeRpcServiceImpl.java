package net.datenwerke.gf.server.theme;

import net.datenwerke.gf.client.theme.dto.ThemeUiConfig;
import net.datenwerke.gf.client.theme.rpc.ThemeRpcService;
import net.datenwerke.gf.service.theme.ThemeService;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.annotation.SecurityChecked;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class ThemeRpcServiceImpl  extends SecuredRemoteServiceServlet implements ThemeRpcService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private final Provider<ThemeService> themeServiceProvider;

	@Inject
	public ThemeRpcServiceImpl(
			Provider<ThemeService> themeServiceProvider
			){
		this.themeServiceProvider = themeServiceProvider;
	}

	@SecurityChecked(bypass=true)
	public ThemeUiConfig loadUiConfig() throws ServerCallFailedException {
		return themeServiceProvider.get().loadUiConfig();
	}



}
