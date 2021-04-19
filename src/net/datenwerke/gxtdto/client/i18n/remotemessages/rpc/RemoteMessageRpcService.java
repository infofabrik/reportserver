package net.datenwerke.gxtdto.client.i18n.remotemessages.rpc;

import java.util.Collection;
import java.util.HashMap;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("remotemessage")
public interface RemoteMessageRpcService extends RemoteService {

	public HashMap<String, HashMap<String, String>> getMessages(String language);

	public Collection<String> getAvailableLanguages();
}
