package net.datenwerke.rs.adminutils.server.systemconsole.memory;

import com.google.inject.Singleton;

import net.datenwerke.rs.adminutils.client.systemconsole.memory.dto.MemoryInfoDto;
import net.datenwerke.rs.adminutils.client.systemconsole.memory.rpc.MemoryConsoleRpcService;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;

@Singleton
public class MemoryConsoleDummyRpcServiceImpl extends SecuredRemoteServiceServlet implements MemoryConsoleRpcService {

   private static final long serialVersionUID = 1L;

   @Override
   public MemoryInfoDto loadMemoryInfo() {
      return null;
   }

   @Override
   public void callGarbageCollector() {
   }

}
