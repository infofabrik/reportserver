package net.datenwerke.rs.adminutils.client.suuser.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SuUserRpcServiceAsync {

   void su(String username, AsyncCallback<Void> callback);

   void su(Long id, AsyncCallback<Void> transformAndKeepCallback);

}
