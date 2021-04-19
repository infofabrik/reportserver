package net.datenwerke.rs.passwordpolicy.service;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.configservice.service.configservice.hooks.ReloadConfigNotificationHook;
import net.datenwerke.rs.passwordpolicy.service.hooker.UserDtoSetStatusPostProcessor;
import net.datenwerke.security.service.authenticator.hooks.PostAuthenticateHook;
import net.datenwerke.security.service.usermanager.hooks.ChangePasswordHook;
import net.datenwerke.security.service.usermanager.hooks.PasswordSetHook;
import net.datenwerke.security.service.usermanager.hooks.UserDtoPostProcessorHook;

import com.google.inject.Inject;

public class PasswordPolicyStartup {
	
	@Inject
	public PasswordPolicyStartup(
		HookHandlerService hookHandlerService, 
		PasswordPolicy passwordPolicy, 
		UserDtoSetStatusPostProcessor setStatusPostProcessor,
		BsiPasswordPolicyService passwordPolicyService
		) {
		
		hookHandlerService.attachHooker(PostAuthenticateHook.class, passwordPolicy.getPostAuthenticateHooker());
		hookHandlerService.attachHooker(ChangePasswordHook.class, passwordPolicy.getChangePasswordHooker());
		hookHandlerService.attachHooker(PasswordSetHook.class, passwordPolicy.getPasswordSetHooker());
		
		hookHandlerService.attachHooker(UserDtoPostProcessorHook.class, setStatusPostProcessor);
		
		if(passwordPolicyService instanceof ReloadConfigNotificationHook)
			hookHandlerService.attachHooker(ReloadConfigNotificationHook.class, (ReloadConfigNotificationHook)passwordPolicyService);
	}

}
