package net.datenwerke.rs.core.client.urlview.rpc;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UrlViewRpcServiceAsync {

   void loadViewConfiguration(AsyncCallback<Map<String, Map<String, List<String[]>>>> callback);

}
