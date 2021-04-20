package net.datenwerke.rs.base.ext.service.datasinkmanager.vfs;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.terminal.service.terminal.vfs.hooks.VirtualFileSystemManagerHook;

public class DatasinkManagerVFSStartup {

   @Inject
   public DatasinkManagerVFSStartup(HookHandlerService hookHandler, Provider<DatasinkManagerVFS> vfsProvider) {
      hookHandler.attachHooker(VirtualFileSystemManagerHook.class, vfsProvider);
   }

}
