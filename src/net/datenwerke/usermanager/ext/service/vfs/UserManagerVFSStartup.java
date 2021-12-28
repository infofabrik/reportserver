package net.datenwerke.usermanager.ext.service.vfs;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.terminal.service.terminal.vfs.hooks.VirtualFileSystemManagerHook;

public class UserManagerVFSStartup {

	@Inject
	public UserManagerVFSStartup(
		HookHandlerService hookHandler,
		Provider<UserManagerVFS> vfsProvider
		){
		
		hookHandler.attachHooker(VirtualFileSystemManagerHook.class, vfsProvider);
	}
}
