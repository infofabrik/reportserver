package net.datenwerke.rs.keyutils.client.keyutils.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface KeyUtilsRpcServiceAsync {
   
   void generateDefaultKey(AsyncCallback<String> callback);
}
