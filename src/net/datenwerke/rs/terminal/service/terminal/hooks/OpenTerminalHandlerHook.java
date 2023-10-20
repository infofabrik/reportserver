package net.datenwerke.rs.terminal.service.terminal.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.terminal.service.terminal.vfs.hooks.TreeBasedVirtualFileSystem;
import net.datenwerke.security.service.treedb.entities.SecuredAbstractNode;

public interface OpenTerminalHandlerHook extends Hook {

   boolean consumes(String type);

   SecuredAbstractNode<?> getNode(Long nodeId);
   
   TreeBasedVirtualFileSystem<?> getVfs();
}
