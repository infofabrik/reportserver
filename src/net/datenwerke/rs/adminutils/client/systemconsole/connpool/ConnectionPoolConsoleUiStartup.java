package net.datenwerke.rs.adminutils.client.systemconsole.connpool;

import com.google.inject.Inject;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.adminutils.client.systemconsole.connpool.hooks.ConnectionPoolInfoSystemConsoleHooker;
import net.datenwerke.rs.adminutils.client.systemconsole.hooks.SystemConsoleViewDomainHook;

public class ConnectionPoolConsoleUiStartup {
   @Inject
   public ConnectionPoolConsoleUiStartup(final HookHandlerService hookHandler,
         ConnectionPoolInfoSystemConsoleHooker generalTargetDomain) {

      hookHandler.attachHooker(SystemConsoleViewDomainHook.class, generalTargetDomain,
            HookHandlerService.PRIORITY_HIGH + 10);

   }

}
