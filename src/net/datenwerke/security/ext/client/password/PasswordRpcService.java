package net.datenwerke.security.ext.client.password;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;

@RemoteServiceRelativePath("security_password")
public interface PasswordRpcService extends RemoteService {

   public void changePassword(String oldPassword, String newPassword, boolean encrypted)
         throws ServerCallFailedException;

   public void changePassword(String username, String oldPassword, String newPassword, boolean encrypted)
         throws ServerCallFailedException;

}
