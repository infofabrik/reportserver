package net.datenwerke.rs.adminutils.client.systemconsole.memory;

import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.rs.adminutils.client.systemconsole.memory.dto.MemoryInfoDto;
import net.datenwerke.rs.adminutils.client.systemconsole.memory.rpc.MemoryConsoleRpcServiceAsync;

public class MemoryConsoleDao extends Dao {

   private final MemoryConsoleRpcServiceAsync rpcService;

   @Inject
   public MemoryConsoleDao(MemoryConsoleRpcServiceAsync rpcService) {
      this.rpcService = rpcService;
   }

   public void loadMemoryInfo(RsAsyncCallback<MemoryInfoDto> callback) {
      rpcService.loadMemoryInfo(transformAndKeepCallback(callback));
   }

   public void callGarbageCollector(RsAsyncCallback<Void> callback) {
      rpcService.callGarbageCollector(transformAndKeepCallback(callback));
   }

}
