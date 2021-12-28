package net.datenwerke.gf.service;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.lifecycle.hookers.CloseTaskAndConnectionsHooker;
import net.datenwerke.gf.service.lifecycle.hooks.ContextHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

public class DwGwtFrameworkStartup {

   @Inject
   public DwGwtFrameworkStartup(HookHandlerService hookHandler,

         Provider<CloseTaskAndConnectionsHooker> closeAsyncTasks) {

      hookHandler.attachHooker(ContextHook.class, closeAsyncTasks);

//		System.setSecurityManager(new SandboxingSecurityManager());
//		System.setSecurityManager(new PassAllSecurityManager());
   }
}
