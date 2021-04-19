package net.datenwerke.security.client.security.lostpassword.rpc;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("security_lostpassword")
public interface LostPasswordRpcService extends RemoteService {
	public String requestNewPassword(String username) throws ServerCallFailedException;
}
