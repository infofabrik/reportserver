package net.datenwerke.rs.adminutils.client.systemconsole;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.administration.AdministrationUIService;
import net.datenwerke.gf.client.administration.hooks.AdminModuleProviderHook;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.adminutils.client.systemconsole.security.SystemConsoleGenericTargetIdentifier;
import net.datenwerke.rs.adminutils.client.systemconsole.security.SystemConsoleSecurityTargetDomainHooker;
import net.datenwerke.rs.enterprise.client.EnterpriseUiService;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.ReadDto;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;
import net.datenwerke.security.client.security.hooks.GenericTargetProviderHook;

public class SystemConsoleUiStartup {

   @Inject
   public SystemConsoleUiStartup(
         final HookHandlerService hookHandler, 
         final WaitOnEventUIService waitOnEventService,
         final SecurityUIService securityService, 
         final Provider<SystemConsoleAdminModule> adminModuleProvider,
         final SystemConsoleSecurityTargetDomainHooker systemConsoleTargetDomain,
         final EnterpriseUiService enterpriseService
         ) {

      hookHandler.attachHooker(GenericTargetProviderHook.class,
            new GenericTargetProviderHook(systemConsoleTargetDomain.genericSecurityViewDomainHook_getTargetId()));
      hookHandler.attachHooker(GenericSecurityViewDomainHook.class, systemConsoleTargetDomain);

      /* test if user has rights to see system console view */
      waitOnEventService.callbackOnEvent(AdministrationUIService.REPORTSERVER_EVENT_HAS_ADMIN_RIGHTS, ticket -> {
         if (securityService.hasRight(SystemConsoleGenericTargetIdentifier.class, ReadDto.class)) {
            hookHandler.attachHooker(AdminModuleProviderHook.class, new AdminModuleProviderHook(adminModuleProvider),
                  HookHandlerService.PRIORITY_HIGH + 120);
         }
         waitOnEventService.signalProcessingDone(ticket);
      });
      
      

   }

}
