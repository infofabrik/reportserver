package net.datenwerke.rs.core.client.urlview;

import java.util.List;
import java.util.Map;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.core.client.urlview.rpc.UrlViewRpcServiceAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class UrlViewDao extends Dao {

	private final UrlViewRpcServiceAsync rpcService;

	@Inject
	public UrlViewDao(UrlViewRpcServiceAsync rpcService) {
		this.rpcService = rpcService;
	}
	
	public void loadViewConfiguration(AsyncCallback<Map<String,Map<String, List<String[]>>>> callback){
		rpcService.loadViewConfiguration(transformAndKeepCallback(callback));
	}
	
}
