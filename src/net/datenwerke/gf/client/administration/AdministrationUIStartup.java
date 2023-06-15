package net.datenwerke.gf.client.administration;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.administration.security.AdminGenericTargetIdentifier;
import net.datenwerke.gf.client.administration.security.AdminSecurityTargetDomainHooker;
import net.datenwerke.gf.client.administration.ui.AdministrationNavPanel;
import net.datenwerke.gf.client.homepage.hooks.ClientMainModuleProviderHook;
import net.datenwerke.gxtdto.client.eventbus.events.SubmoduleDisplayRequest;
import net.datenwerke.gxtdto.client.waitonevent.SynchronousCallbackOnEventTrigger;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventTicket;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.ReadDto;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;
import net.datenwerke.security.client.security.hooks.GenericTargetProviderHook;

/**
 * 
 *
 */
public class AdministrationUIStartup {

   @Inject
   public AdministrationUIStartup(final HookHandlerService hookHandler,

         final WaitOnEventUIService waitOnEventService, final Provider<AdministrationUIService> adminUIModuleProvider,

         AdminSecurityTargetDomainHooker securityTargetDomain,

         final SecurityUIService securityService,

         final EventBus eventBus, 
         final Provider<AdministrationNavPanel> navigationPanelProvider
   ) {

      /* attach security target domain */
      hookHandler.attachHooker(GenericTargetProviderHook.class,
            new GenericTargetProviderHook(new AdminGenericTargetIdentifier()));
      hookHandler.attachHooker(GenericSecurityViewDomainHook.class, securityTargetDomain);

      /* create hook, that is later either attached or detached */
      final ClientMainModuleProviderHook providerHook = new ClientMainModuleProviderHook(adminUIModuleProvider);

      /* request callback after login and check for rights */
      waitOnEventService.callbackOnEvent(SecurityUIService.REPORTSERVER_EVENT_GENERIC_RIGHTS_LOADED,
            new SynchronousCallbackOnEventTrigger() {

               public void execute(final WaitOnEventTicket ticket) {
                  if (securityService.hasRight(AdminGenericTargetIdentifier.class, ReadDto.class)) {
                     /* signal that user has admin rights */
                     waitOnEventService.triggerEvent(AdministrationUIService.REPORTSERVER_EVENT_HAS_ADMIN_RIGHTS,
                           () -> waitOnEventService.signalProcessingDone(ticket));

                     /* attach hooker */
                     hookHandler.attachHooker(ClientMainModuleProviderHook.class, providerHook,
                           HookHandlerService.PRIORITY_LOW);
                  } else {
                     hookHandler.detachHooker(ClientMainModuleProviderHook.class, providerHook);

                     waitOnEventService.signalProcessingDone(ticket);
                  }
               }
            });

      /* attach to eventbus */
      eventBus.addHandler(SubmoduleDisplayRequest.TYPE, event -> {
         if (AdministrationUIModule.ADMIN_PANEL_ID.equals(event.getParentId())) {
            AdministrationNavPanel panel = navigationPanelProvider.get();
            panel.handleDisplayRequest(event);
         }
      });

   }
}
