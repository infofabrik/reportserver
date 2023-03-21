package net.datenwerke.security.service.security;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.hooks.GeneralInfoCategoryProviderHook;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.security.service.eventlogger.jpa.RemoveEntityEvent;
import net.datenwerke.security.service.security.eventhandler.SecurityIntegrityValidator;
import net.datenwerke.security.service.security.hookers.GeneralInfoSecurityCategoryProviderHooker;
import net.datenwerke.security.service.security.terminal.commands.HaspermissionCommand;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;

@Singleton
public class SecurityStartup {

   @Inject
   public SecurityStartup(
         final EventBus eventBus, 
         final SecurityIntegrityValidator integrityValidator,
         final SecurityService securityService,
         final HookHandlerService hookHandler,
         final HaspermissionCommand haspermissionCommand,
         final Provider<GeneralInfoSecurityCategoryProviderHooker> generalInfoSecurityCategoryProviderHooker
         ) {

      /* attach validator */
      eventBus.attachObjectEventHandler(RemoveEntityEvent.class, AbstractUserManagerNode.class, integrityValidator);

      /* register securee */
      securityService.registerSecuree(new SecurityServiceSecuree());
      
      /* terminal commands */
      hookHandler.attachHooker(TerminalCommandHook.class, haspermissionCommand);
      
      hookHandler.attachHooker(GeneralInfoCategoryProviderHook.class, generalInfoSecurityCategoryProviderHooker,
            HookHandlerService.PRIORITY_LOW + 50);
   }
}
