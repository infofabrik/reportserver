package net.datenwerke.gf.client.homepage.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface HomepageRpcServiceAsync {

   void getPageTitle(AsyncCallback<String> callback);

   void isStartupComplete(AsyncCallback<Boolean> callback);

   void getSessionRedirect(AsyncCallback<String> callback);

   void assertAllowsRedirect(String redir, AsyncCallback<Void> callback);

}
