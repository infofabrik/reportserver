package net.datenwerke.rs.transport.service.transport.vfs;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.terminal.service.terminal.vfs.hooks.VirtualFileSystemManagerHook;

public class TransportManagerVFSStartup {

   @Inject
   public TransportManagerVFSStartup(
         HookHandlerService hookHandler, 
         Provider<TransportManagerVFS> vfsProvider
         ) {
      hookHandler.attachHooker(VirtualFileSystemManagerHook.class, vfsProvider);
   }

}
