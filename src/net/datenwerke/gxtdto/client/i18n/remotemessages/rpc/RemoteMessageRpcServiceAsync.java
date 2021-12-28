package net.datenwerke.gxtdto.client.i18n.remotemessages.rpc;

import java.util.Collection;
import java.util.HashMap;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RemoteMessageRpcServiceAsync {

   void getMessages(String language, AsyncCallback<HashMap<String, HashMap<String, String>>> callback);

   void getAvailableLanguages(AsyncCallback<Collection<String>> callback);

}
