package net.datenwerke.rs.core.client.contexthelp.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.core.client.contexthelp.dto.ContextHelpInfo;

@RemoteServiceRelativePath("contexthelp")
public interface ContextHelpRpcService extends RemoteService {

   public String getContextHelp(ContextHelpInfo info) throws ServerCallFailedException;

}
