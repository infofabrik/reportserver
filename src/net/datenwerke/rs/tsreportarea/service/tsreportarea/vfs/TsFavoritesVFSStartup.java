package net.datenwerke.rs.tsreportarea.service.tsreportarea.vfs;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.terminal.service.terminal.vfs.hooks.VirtualFileSystemManagerHook;

public class TsFavoritesVFSStartup {

   @Inject
   public TsFavoritesVFSStartup(HookHandlerService hookHandler, Provider<TsFavoritesVFS> vfsProvider) {

      hookHandler.attachHooker(VirtualFileSystemManagerHook.class, vfsProvider);
   }
}
