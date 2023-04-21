package net.datenwerke.rs.remotersrestserver.client.remotersrestserver.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.remotersrestserver.client.remotersrestserver.dto.RemoteRsRestServerDto;

@RemoteServiceRelativePath("remote_rs_rest_server")
public interface RemoteRsRestServerRpcService extends RemoteService {

   boolean test(RemoteRsRestServerDto remoteServerDto) throws ServerCallFailedException;

}
