package net.datenwerke.gf.client.theme.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gf.client.theme.dto.ThemeUiConfig;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;

@RemoteServiceRelativePath("themeservice")
public interface ThemeRpcService extends RemoteService {

	ThemeUiConfig loadUiConfig() throws ServerCallFailedException;
	
	
}
