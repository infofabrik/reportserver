package net.datenwerke.rs.dsbundle.client.dsbundle.rpc;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DatasourceBundleRpcServiceAsync {

	void getAvailableBundleKeys(AsyncCallback<List<String>> callback);

	void getBundleSelectorConfiguration(AsyncCallback<Map<String, String>> callback);

	void setSelectedBundle(String bundleKey, AsyncCallback<Void> callback);

	

}
