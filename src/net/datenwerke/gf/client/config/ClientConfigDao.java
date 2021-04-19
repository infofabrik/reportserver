package net.datenwerke.gf.client.config;

import java.util.HashMap;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gf.client.config.rpc.ClientConfigRpcServiceAsync;
import net.datenwerke.gxtdto.client.dtomanager.Dao;

public class ClientConfigDao extends Dao {
	
	private final ClientConfigRpcServiceAsync rpcService;

	@Inject
	public ClientConfigDao(ClientConfigRpcServiceAsync rpcService) {
		this.rpcService = rpcService;
	}
	
	public void getClientConfig(String configfile, AsyncCallback<String> callback){
		rpcService.getConfigFile(configfile, callback);
	}
	
	public void getConfigProperties(String identifier, AsyncCallback<HashMap<String,String>> callback){
		rpcService.getConfigProperties(identifier, callback);
	}
}
