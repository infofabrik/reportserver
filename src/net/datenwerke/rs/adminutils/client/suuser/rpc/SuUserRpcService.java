package net.datenwerke.rs.adminutils.client.suuser.rpc;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("datenwerke/suuser")
public interface SuUserRpcService extends RemoteService {

	public void su(String username) throws ServerCallFailedException;

	void su(Long id) throws ServerCallFailedException;
}
