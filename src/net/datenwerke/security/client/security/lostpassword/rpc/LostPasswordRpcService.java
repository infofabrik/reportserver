package net.datenwerke.security.client.security.lostpassword.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;

@RemoteServiceRelativePath("security_lostpassword")
public interface LostPasswordRpcService extends RemoteService {
   
   public String requestNewPassword(String username) throws ServerCallFailedException;
   
   public boolean isLostPasswordDisabled() throws ServerCallFailedException;
}
