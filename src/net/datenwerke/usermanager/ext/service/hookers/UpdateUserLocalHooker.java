package net.datenwerke.usermanager.ext.service.hookers;

import java.util.Locale;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.utils.localization.hooks.LocaleChangedNotificationHook;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.usermanager.UserManagerModule;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.UserPropertiesService;
import net.datenwerke.security.service.usermanager.entities.User;

public class UpdateUserLocalHooker implements LocaleChangedNotificationHook {

	private final Provider<AuthenticatorService> authenticationServiceProvider;
	private final UserPropertiesService userPropertiesService;
	private final UserManagerService userManagerService;
	
	@Inject
	public UpdateUserLocalHooker(
			Provider<AuthenticatorService> authenticationServiceProvider,
			UserPropertiesService userPropertiesService,
			UserManagerService userManagerService) {
		super();
		this.authenticationServiceProvider = authenticationServiceProvider;
		this.userPropertiesService = userPropertiesService;
		this.userManagerService = userManagerService;
	}



	@Override
	public void localeChanged(Locale locale) {
		try{
			AuthenticatorService authService = authenticationServiceProvider.get();
			User user = authService.getCurrentUser();
			if(null != user){
				userPropertiesService.setPropertyValue(user, UserManagerModule.USER_PROPERTY_USER_LOCALE, null != locale ? locale.toLanguageTag() : "");
				userManagerService.merge(user);
			}
		} catch(Exception e){}
	}

}
