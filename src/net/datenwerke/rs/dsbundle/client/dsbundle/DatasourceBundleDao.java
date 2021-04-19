package net.datenwerke.rs.dsbundle.client.dsbundle;

import java.util.List;
import java.util.Map;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.dsbundle.client.dsbundle.rpc.DatasourceBundleRpcServiceAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class DatasourceBundleDao extends Dao {

	private final DatasourceBundleRpcServiceAsync rpcService;

	@Inject
	public DatasourceBundleDao(DatasourceBundleRpcServiceAsync rpcService) {
		this.rpcService = rpcService;
	}
	
	public void getAvailableBundleKeys(AsyncCallback<List<String>> callback){
		rpcService.getAvailableBundleKeys(transformAndKeepCallback(callback));
	}
	
	public void getBundleSelectorConfiguration(AsyncCallback<Map<String, String>> callback){
		rpcService.getBundleSelectorConfiguration(callback);
	}
	
	public void setSelectedBundle(String bundleKey, AsyncCallback<Void> callback){
		rpcService.setSelectedBundle(bundleKey, callback);
	}
}
