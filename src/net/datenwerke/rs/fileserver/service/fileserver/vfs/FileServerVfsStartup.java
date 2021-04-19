package net.datenwerke.rs.fileserver.service.fileserver.vfs;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.terminal.service.terminal.vfs.hooks.VirtualFileSystemManagerHook;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class FileServerVfsStartup {

	@Inject
	public FileServerVfsStartup(
		HookHandlerService hookHandler,
		Provider<FileServerVfs> vfsProvider
		){
		
		hookHandler.attachHooker(VirtualFileSystemManagerHook.class, vfsProvider);
	}
}
