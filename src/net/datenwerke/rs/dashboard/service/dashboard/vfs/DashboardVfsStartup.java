package net.datenwerke.rs.dashboard.service.dashboard.vfs;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.terminal.service.terminal.vfs.hooks.VirtualFileSystemManagerHook;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class DashboardVfsStartup {

	@Inject
	public DashboardVfsStartup(
		HookHandlerService hookHandler,
		Provider<DashboardVfs> vfsProvider
		){
		
		hookHandler.attachHooker(VirtualFileSystemManagerHook.class, vfsProvider);
	}
}
