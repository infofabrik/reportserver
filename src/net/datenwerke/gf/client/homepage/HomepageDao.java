package net.datenwerke.gf.client.homepage;

import javax.inject.Inject;

import net.datenwerke.gf.client.homepage.rpc.HomepageRpcServiceAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class HomepageDao {
	
	private final HomepageRpcServiceAsync rpcService;
	
	@Inject
	public HomepageDao(HomepageRpcServiceAsync rpcService) {
		this.rpcService = rpcService; 
	}

	public void getPageTitle(AsyncCallback<String> callback){
		rpcService.getPageTitle(callback);
	}
	
	public void isStartupComplete(AsyncCallback<Boolean> callback){
		rpcService.isStartupComplete(callback);
	}
	
	public void getSessionRedirect(AsyncCallback<String> callback){
		rpcService.getSessionRedirect(callback);
	}
	
	public void assertAllowsRedirect(String redir, AsyncCallback<Void> callback) {
		rpcService.assertAllowsRedirect(redir, callback);
	}
}
