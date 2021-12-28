package net.datenwerke.rs.adminutils.client.suuser.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;

@RemoteServiceRelativePath("datenwerke/suuser")
public interface SuUserRpcService extends RemoteService {

   public void su(String username) throws ServerCallFailedException;

   void su(Long id) throws ServerCallFailedException;
}
