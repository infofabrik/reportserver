package net.datenwerke.rs.userprofile.client.userprofile.hookers;

import java.util.List;

import net.datenwerke.gf.client.homepage.hooks.HomepageHeaderContentHook;
import net.datenwerke.gf.client.homepage.ui.DwMainViewportTopBarElement;
import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.gxtdto.client.dialog.properties.RpcPropertiesDialog;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utilityservices.submittracker.SubmitCompleteCallback;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.userprofile.client.userprofile.hooks.UserProfileCardProviderHook;
import net.datenwerke.rs.userprofile.client.userprofile.locale.UserProfileMessages;
import net.datenwerke.security.client.login.AuthenticateCallback;
import net.datenwerke.security.client.usermanager.dto.UserDto;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;

/**
 * 
 *
 */
public class HomepageProfileHooker implements HomepageHeaderContentHook {

	
	private final LoginService loginService;
	private final Provider<RpcPropertiesDialog> propertiesDialogProvider;
	private final HookHandlerService hookHandler;
	
	@Inject
	public HomepageProfileHooker(
		LoginService loginService,
		Provider<RpcPropertiesDialog> propertiesDialogProvider,
		HookHandlerService hookHandler
		){
		
		/* store objects */
		this.loginService = loginService;
		this.propertiesDialogProvider = propertiesDialogProvider;
		this.hookHandler = hookHandler;
	}
	
	
	@Override
	public DwMainViewportTopBarElement homepageHeaderContentHook_addTopRight(HBoxLayoutContainer container) {
		final UserDto user = loginService.getCurrentUser();
		
		return new DwMainViewportTopBarElement() {
			
			@Override
			public void onClick() {
				displayProfile();
			}
			
			@Override
			public String getName() {
				String textLabel = UserProfileMessages.INSTANCE.headerText(user.toDisplayTitle());
				
				return textLabel;
			}
		};
	}
	

	protected void displayProfile() {
		UserDto user = loginService.getCurrentUser();
		
		/* create window */
		final RpcPropertiesDialog profile = propertiesDialogProvider.get();
		profile.setSize(640, 480);
		profile.setHeading(UserProfileMessages.INSTANCE.profileHeader(user.toDisplayTitle()));
		profile.setMaskOnSubmit(BaseMessages.INSTANCE.loadingMsg());
		profile.setModal(true);
		profile.setPerformSubmitsConsecutively(true);

		/* load cards */
		final List<UserProfileCardProviderHook> cardProviders = hookHandler.getHookers(UserProfileCardProviderHook.class);
		for(UserProfileCardProviderHook cardProvider : cardProviders)
			profile.addCard(cardProvider);
		
		profile.setSubmitCompleteCallback(new SubmitCompleteCallback() {
			
			@Override
			public void onSuccess() {
				/* reauthenticate so that current user is up to date */
				loginService.tryReAuthenticate(new AuthenticateCallback() {
					@Override
					public void execute(boolean authenticateSucceeded) {
						profile.hide();						
					}
				});
			}
			
			@Override
			public void onFailure(Throwable t) {
				profile.hide();
			}
		});
		
		/* show window */
		profile.show();
	}

}
