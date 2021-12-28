package net.datenwerke.gf.client.config.rpc;

import java.util.HashMap;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ClientConfigRpcServiceAsync {

   void getConfigFile(String configfile, AsyncCallback<String> callback);

   void getConfigProperties(String identifier, AsyncCallback<HashMap<String, String>> callback);

}
