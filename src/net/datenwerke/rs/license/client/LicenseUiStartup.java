package net.datenwerke.rs.license.client;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.administration.hooks.AdminModuleProviderHook;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.license.client.security.LicenseGenericTargetIdentifier;
import net.datenwerke.rs.license.client.security.LicenseSecurityTargetDomainHooker;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.ReadDto;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;
import net.datenwerke.security.client.security.hooks.GenericTargetProviderHook;

public class LicenseUiStartup {

   @Inject
   public LicenseUiStartup(final HookHandlerService hookHandler, final WaitOnEventUIService waitOnEventService,
         final SecurityUIService securityService, final Provider<LicenseAdminModule> adminModuleProvider,

         final LicenseSecurityTargetDomainHooker securityTargetDomain) {

      /* security */
      hookHandler.attachHooker(GenericTargetProviderHook.class,
            new GenericTargetProviderHook(securityTargetDomain.genericSecurityViewDomainHook_getTargetId()));
      hookHandler.attachHooker(GenericSecurityViewDomainHook.class, securityTargetDomain);

      /* attach */
      waitOnEventService.callbackOnEvent(SecurityUIService.REPORTSERVER_EVENT_GENERIC_RIGHTS_LOADED, ticket -> {
         if (securityService.hasRight(LicenseGenericTargetIdentifier.class, ReadDto.class))
            hookHandler.attachHooker(AdminModuleProviderHook.class, new AdminModuleProviderHook(adminModuleProvider),
                  HookHandlerService.PRIORITY_LOW + 1000);

         waitOnEventService.signalProcessingDone(ticket);
      });
   }
}
