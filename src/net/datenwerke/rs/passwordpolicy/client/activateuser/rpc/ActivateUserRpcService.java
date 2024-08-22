package net.datenwerke.rs.passwordpolicy.client.activateuser.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.security.client.usermanager.dto.UserDto;

@RemoteServiceRelativePath("security_activateuser")
public interface ActivateUserRpcService extends RemoteService {

   public void activateAccount(UserDto user, boolean force) throws ServerCallFailedException;

}
