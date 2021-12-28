package net.datenwerke.rs.globalconstants.client.globalconstants;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.administration.AdministrationUIService;
import net.datenwerke.gf.client.administration.hooks.AdminModuleProviderHook;
import net.datenwerke.gxtdto.client.waitonevent.SynchronousCallbackOnEventTrigger;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventTicket;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.globalconstants.client.globalconstants.security.GlobalConstantsGenericTargetIdentifier;
import net.datenwerke.rs.globalconstants.client.globalconstants.security.GlobalConstantsViewSecurityTargetDomainHooker;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.ReadDto;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;
import net.datenwerke.security.client.security.hooks.GenericTargetProviderHook;

/**
 * 
 *
 */
public class GlobalConstantsUIStartup {

   @Inject
   public GlobalConstantsUIStartup(final WaitOnEventUIService waitOnEventService, final HookHandlerService hookHandler,
         final GlobalConstantsDao constantsDao,

         final Provider<GlobalConstantsAdminModule> adminModuleProvider,
         GlobalConstantsViewSecurityTargetDomainHooker securityTargetDomain,

         final SecurityUIService securityService) {

      /* attach security target domains */
      hookHandler.attachHooker(GenericTargetProviderHook.class,
            new GenericTargetProviderHook(securityTargetDomain.genericSecurityViewDomainHook_getTargetId()));
      hookHandler.attachHooker(GenericSecurityViewDomainHook.class, securityTargetDomain);

      /* test if user has rights to see properties view */
      waitOnEventService.callbackOnEvent(AdministrationUIService.REPORTSERVER_EVENT_HAS_ADMIN_RIGHTS,
            new SynchronousCallbackOnEventTrigger() {
               public void execute(final WaitOnEventTicket ticket) {
                  if (securityService.hasRight(GlobalConstantsGenericTargetIdentifier.class, ReadDto.class))
                     hookHandler.attachHooker(AdminModuleProviderHook.class,
                           new AdminModuleProviderHook(adminModuleProvider), HookHandlerService.PRIORITY_HIGH + 100);

                  waitOnEventService.signalProcessingDone(ticket);
               }
            });
   }
}
