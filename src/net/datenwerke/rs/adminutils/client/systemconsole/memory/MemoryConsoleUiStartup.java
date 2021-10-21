package net.datenwerke.rs.adminutils.client.systemconsole.memory;

import com.google.inject.Inject;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.adminutils.client.systemconsole.hooks.SystemConsoleViewDomainHook;
import net.datenwerke.rs.adminutils.client.systemconsole.memory.hooks.MemoryInfoSystemConsoleHooker;

public class MemoryConsoleUiStartup {

   @Inject
   public MemoryConsoleUiStartup(final HookHandlerService hookHandler,
         MemoryInfoSystemConsoleHooker generalTargetDomain) {

      hookHandler.attachHooker(SystemConsoleViewDomainHook.class, generalTargetDomain,
            HookHandlerService.PRIORITY_HIGH + 10);

   }
}
