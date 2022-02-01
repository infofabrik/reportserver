package net.datenwerke.security.service.security;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.security.service.eventlogger.jpa.RemoveEntityEvent;
import net.datenwerke.security.service.security.eventhandler.SecurityIntegrityValidator;
import net.datenwerke.security.service.security.terminal.commands.HaspermissionCommand;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;

@Singleton
public class SecurityStartup {

   @Inject
   public SecurityStartup(
         EventBus eventBus, 
         SecurityIntegrityValidator integrityValidator,
         SecurityService securityService,
         HookHandlerService hookHandler,
         HaspermissionCommand haspermissionCommand
         ) {

      /* attach validator */
      eventBus.attachObjectEventHandler(RemoveEntityEvent.class, AbstractUserManagerNode.class, integrityValidator);

      /* register securee */
      securityService.registerSecuree(new SecurityServiceSecuree());
      
      /* terminal commands */
      hookHandler.attachHooker(TerminalCommandHook.class, haspermissionCommand);
   }
}
