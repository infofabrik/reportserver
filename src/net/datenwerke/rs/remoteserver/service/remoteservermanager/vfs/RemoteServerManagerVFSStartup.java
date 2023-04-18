package net.datenwerke.rs.remoteserver.service.remoteservermanager.vfs;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.terminal.service.terminal.vfs.hooks.VirtualFileSystemManagerHook;

public class RemoteServerManagerVFSStartup {

   @Inject
   public RemoteServerManagerVFSStartup(HookHandlerService hookHandler, Provider<RemoteServerManagerVFS> vfsProvider) {
      hookHandler.attachHooker(VirtualFileSystemManagerHook.class, vfsProvider);
   }

}
