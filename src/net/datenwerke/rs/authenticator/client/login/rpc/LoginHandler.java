package net.datenwerke.rs.authenticator.client.login.rpc;

import java.util.Set;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.security.client.login.AuthToken;
import net.datenwerke.security.client.login.AuthenticateResultDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;

@RemoteServiceRelativePath("login")
public interface LoginHandler extends RemoteService {

   public Set<String> getRequiredClientModules() throws ServerCallFailedException;

   public AuthenticateResultDto authenticate(AuthToken[] tokens) throws ServerCallFailedException;

   public UserDto isAuthenticated() throws ServerCallFailedException;

   public String logoff() throws ServerCallFailedException;

   public int getSessionTimeout() throws ServerCallFailedException;

}
