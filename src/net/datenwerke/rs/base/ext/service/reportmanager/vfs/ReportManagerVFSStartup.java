package net.datenwerke.rs.base.ext.service.reportmanager.vfs;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.terminal.service.terminal.vfs.hooks.VirtualFileSystemManagerHook;

public class ReportManagerVFSStartup {

   @Inject
   public ReportManagerVFSStartup(HookHandlerService hookHandler, Provider<ReportManagerVFS> vfsProvider) {

      hookHandler.attachHooker(VirtualFileSystemManagerHook.class, vfsProvider);
   }
}
