package net.datenwerke.rs.remotersrestserver.client.remotersrestserver.rpc;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.remotersrestserver.client.remotersrestserver.dto.RemoteRsRestServerDto;

public interface RemoteRsRestServerRpcServiceAsync {

   Request test(RemoteRsRestServerDto remoteServerDto, AsyncCallback<Boolean> callback);

}
