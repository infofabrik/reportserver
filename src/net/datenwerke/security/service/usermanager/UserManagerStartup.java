package net.datenwerke.security.service.usermanager;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.terminal.service.terminal.hookers.UserOpenTerminalHooker;
import net.datenwerke.rs.terminal.service.terminal.hooks.OpenTerminalHandlerHook;
import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.security.service.eventlogger.jpa.RemoveEntityEvent;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.usermanager.entities.Group;
import net.datenwerke.security.service.usermanager.entities.OrganisationalUnit;
import net.datenwerke.security.service.usermanager.entities.User;
import net.datenwerke.security.service.usermanager.eventhandler.UserRemoveEventHandler;
import net.datenwerke.security.service.usermanager.hooks.NotificationEmailChangedPasswordHook;
import net.datenwerke.security.service.usermanager.hooks.PasswordManualSetHook;

public class UserManagerStartup {

   @Inject
   public UserManagerStartup(EventBus eventBus, SecurityService securityService,

         UserRemoveEventHandler userRemoveEventHandler, HookHandlerService hookHandlerService,
         NotificationEmailChangedPasswordHook notificationEmailHooker,
         final Provider<UserOpenTerminalHooker> userOpenTerminalHooker) {

      eventBus.attachObjectEventHandler(RemoveEntityEvent.class, User.class, userRemoveEventHandler);

      registerSecurityTargets(securityService);

      hookHandlerService.attachHooker(PasswordManualSetHook.class, notificationEmailHooker);
      hookHandlerService.attachHooker(OpenTerminalHandlerHook.class, userOpenTerminalHooker);
   }

   private void registerSecurityTargets(SecurityService securityService) {
      securityService.registerSecurityTarget(Group.class, User.class, OrganisationalUnit.class);
   }

}
