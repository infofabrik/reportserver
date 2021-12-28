package net.datenwerke.rs.authenticator.client.login.hookers;

import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;

import net.datenwerke.gf.client.homepage.hooks.HomepageHeaderContentHook;
import net.datenwerke.gf.client.homepage.ui.DwMainViewportTopBarElement;
import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.rs.authenticator.client.login.locale.LoginMessages;

/**
 * 
 *
 */
public class HomepageLogoutHook implements HomepageHeaderContentHook {
	
	private final LoginService loginService;
	
	@Inject
	public HomepageLogoutHook(
		LoginService loginService
		){
		
		/* store objects */
		this.loginService = loginService;
	}
	
	@Override
	public DwMainViewportTopBarElement homepageHeaderContentHook_addTopRight(HBoxLayoutContainer container) {
		return new DwMainViewportTopBarElement() {
			
			@Override
			public void onClick() {
				loginService.logoff();
			}
			
			@Override
			public String getName() {
				return LoginMessages.INSTANCE.logout();
			}
		};
	}
	



	
}
