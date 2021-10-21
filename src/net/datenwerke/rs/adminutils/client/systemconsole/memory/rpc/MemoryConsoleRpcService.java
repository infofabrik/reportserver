package net.datenwerke.rs.adminutils.client.systemconsole.memory.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.adminutils.client.systemconsole.memory.dto.MemoryInfoDto;

@RemoteServiceRelativePath("memoryconsole")
public interface MemoryConsoleRpcService extends RemoteService {

   MemoryInfoDto loadMemoryInfo() throws ServerCallFailedException;

   void callGarbageCollector() throws ServerCallFailedException;

}
