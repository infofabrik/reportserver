package net.datenwerke.rs.passwordpolicy.client.activateuser.rpc;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.security.client.usermanager.dto.UserDto;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("security_activateuser")
public interface ActivateUserRpcService extends RemoteService {
	
	public void activateAccount(UserDto user, boolean force) throws ExpectedException;


}
