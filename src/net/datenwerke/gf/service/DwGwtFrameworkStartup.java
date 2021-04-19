package net.datenwerke.gf.service;

import net.datenwerke.gf.service.lifecycle.hookers.CloseTaskAndConnectionsHooker;
import net.datenwerke.gf.service.lifecycle.hooks.ContextHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class DwGwtFrameworkStartup {

	@Inject
	public DwGwtFrameworkStartup(
		HookHandlerService hookHandler,
		
		Provider<CloseTaskAndConnectionsHooker> closeAsyncTasks
		){
		
		hookHandler.attachHooker(ContextHook.class, closeAsyncTasks);
		
//		System.setSecurityManager(new SandboxingSecurityManager());
//		System.setSecurityManager(new PassAllSecurityManager());
	}
}
