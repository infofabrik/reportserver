package net.datenwerke.rs.adminutils.client.systemconsole.memory.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.adminutils.client.systemconsole.memory.dto.MemoryInfoDto;

public interface MemoryConsoleRpcServiceAsync {

   void loadMemoryInfo(AsyncCallback<MemoryInfoDto> callback);

   void callGarbageCollector(AsyncCallback<Void> callback);

}
