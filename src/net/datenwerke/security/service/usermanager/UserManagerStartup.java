package net.datenwerke.security.service.usermanager;

import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.security.service.eventlogger.jpa.RemoveEntityEvent;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.usermanager.entities.Group;
import net.datenwerke.security.service.usermanager.entities.OrganisationalUnit;
import net.datenwerke.security.service.usermanager.entities.User;
import net.datenwerke.security.service.usermanager.eventhandler.UserRemoveEventHandler;
import net.datenwerke.security.service.usermanager.hooks.NotificationEmailChangedPasswordHook;
import net.datenwerke.security.service.usermanager.hooks.PasswordManualSetHook;

import com.google.inject.Inject;

public class UserManagerStartup {

	@Inject
	public UserManagerStartup(
		EventBus eventBus,
		SecurityService securityService,
		
		UserRemoveEventHandler userRemoveEventHandler,
		HookHandlerService hookHandlerService, 
		NotificationEmailChangedPasswordHook notificationEmailHooker 
		){
		
		eventBus.attachObjectEventHandler(RemoveEntityEvent.class, User.class, userRemoveEventHandler);
		
		registerSecurityTargets(securityService);
		
		hookHandlerService.attachHooker(PasswordManualSetHook.class, notificationEmailHooker);
	}
	
	
	private void registerSecurityTargets(SecurityService securityService) {
		securityService.registerSecurityTarget(
			Group.class,
			User.class,
			OrganisationalUnit.class
		);
	}

}
