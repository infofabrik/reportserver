package net.datenwerke.rs.core.client.contexthelp.rpc;


import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.core.client.contexthelp.dto.ContextHelpInfo;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("contexthelp")
public interface ContextHelpRpcService extends RemoteService {

	public String getContextHelp(ContextHelpInfo info) throws ServerCallFailedException;

	
}
