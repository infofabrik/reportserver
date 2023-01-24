package net.datenwerke.rs.adminutils.client.systemconsole.admin;

import com.google.inject.Inject;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.adminutils.client.systemconsole.hooks.SystemConsoleViewDomainHook;
import net.datenwerke.rs.adminutils.client.systemconsole.admin.hooks.AdminInfoSystemConsoleHooker;

public class AdminConsoleUiStartup {

   @Inject
   public AdminConsoleUiStartup(
         final HookHandlerService hookHandler,
         AdminInfoSystemConsoleHooker generalTargetDomain
         ) {

      hookHandler.attachHooker(SystemConsoleViewDomainHook.class, generalTargetDomain,
            HookHandlerService.PRIORITY_HIGH + 10);

   }
}
