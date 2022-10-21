package net.datenwerke.rs.base.ext.service.datasourcemanager.vfs;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.terminal.service.terminal.vfs.hooks.VirtualFileSystemManagerHook;

public class DatasourceManagerVFSStartup {

   @Inject
   public DatasourceManagerVFSStartup(HookHandlerService hookHandler, Provider<DatasourceManagerVFS> vfsProvider) {

      hookHandler.attachHooker(VirtualFileSystemManagerHook.class, vfsProvider);
   }
}
