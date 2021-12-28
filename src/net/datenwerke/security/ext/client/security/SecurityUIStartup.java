package net.datenwerke.security.ext.client.security;

import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.HashSet;

import com.google.inject.Inject;

import net.datenwerke.gf.client.administration.AdministrationUIService;
import net.datenwerke.gf.client.administration.hooks.AdminModuleProviderHook;
import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventTicket;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.security.client.security.GenericTargetIdentifier;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.GenericSecurityTargetContainer;
import net.datenwerke.security.client.security.dto.ReadDto;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;
import net.datenwerke.security.client.security.hooks.GenericTargetProviderHook;
import net.datenwerke.security.ext.client.security.ui.genericview.GenericSecurityViewAdminModule;
import net.datenwerke.security.ext.client.security.ui.genericview.targets.GenericSecurityTargetAdminViewGenericTargetIdentifier;
import net.datenwerke.security.ext.client.security.ui.genericview.targets.GenericSecurityTargetAdminViewSecurityTargetDomainHooker;

public class SecurityUIStartup {

   @Inject
   public SecurityUIStartup(final HookHandlerService hookHandler, final WaitOnEventUIService waitOnEventService,
         final SecurityDao securityDao, final GenericSecurityViewAdminModule genericSecurityViewAdminModule,
         final GenericSecurityTargetAdminViewSecurityTargetDomainHooker securityTargetDomain,

         final SecurityUIService securityService) {

      /* attach security target domains */
      hookHandler.attachHooker(GenericTargetProviderHook.class,
            new GenericTargetProviderHook(securityTargetDomain.genericSecurityViewDomainHook_getTargetId()));
      hookHandler.attachHooker(GenericSecurityViewDomainHook.class, securityTargetDomain);

      /* test if user has rights to see generic rights admin view view */
      waitOnEventService.callbackOnEvent(AdministrationUIService.REPORTSERVER_EVENT_HAS_ADMIN_RIGHTS, ticket -> {
         if (securityService.hasRight(GenericSecurityTargetAdminViewGenericTargetIdentifier.class, ReadDto.class))
            hookHandler.attachHooker(AdminModuleProviderHook.class,
                  new AdminModuleProviderHook(genericSecurityViewAdminModule), HookHandlerService.PRIORITY_HIGH + 70);

         waitOnEventService.signalProcessingDone(ticket);
      });

      /* load generic rights after login */
      waitOnEventService.callbackOnEvent(LoginService.REPORTSERVER_EVENT_AFTER_INITIAL_LOGIN,
            (final WaitOnEventTicket ticket) -> {
               final Collection<GenericTargetIdentifier> targetIdentifiers = new HashSet<>();

               targetIdentifiers.addAll(hookHandler.getHookers(GenericTargetProviderHook.class).stream()
                     .map(GenericTargetProviderHook::getObject).collect(toList()));

               securityDao.loadGenericRights(targetIdentifiers, new RsAsyncCallback<GenericSecurityTargetContainer>() {
                  @Override
                  public void onSuccess(GenericSecurityTargetContainer result) {
                     securityService.setGenericSecurityContainer(result);
                     waitOnEventService.triggerEvent(SecurityUIService.REPORTSERVER_EVENT_GENERIC_RIGHTS_LOADED);

                     waitOnEventService.signalProcessingDone(ticket);
                  }
               });
            });
   }
}
