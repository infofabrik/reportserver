package net.datenwerke.rs.dsbundle.client.dsbundle.rpc;

import java.util.List;
import java.util.Map;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleDto;

public interface DatasourceBundleRpcServiceAsync {

   void getAvailableBundleKeys(AsyncCallback<List<String>> callback);

   void getBundleSelectorConfiguration(AsyncCallback<Map<String, String>> callback);

   void setSelectedBundle(String bundleKey, AsyncCallback<Void> callback);

   Request testConnection(DatabaseBundleDto databaseBundleDto, AsyncCallback<Boolean> callback);

}
