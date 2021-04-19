package net.datenwerke.rs.passwordpolicy.client.accountinhibition;

import net.datenwerke.gf.client.managerhelper.hooks.SimpleFormViewHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.authenticator.client.login.hookers.AccountInhibitionPostAuthenticateClientHook;
import net.datenwerke.rs.passwordpolicy.client.accountinhibition.hooker.AccountInhibitionUserFormHooker;

import com.google.inject.Inject;

/**
 * 
 *
 */
public class AccountInhibitionUIStartup {
	
	@Inject
	public AccountInhibitionUIStartup(
		HookHandlerService hookHandlerService,
		AccountInhibitionUserFormHooker accountInhibitionUserFormHooker, 
		AccountInhibitionPostAuthenticateClientHook accountInhibitionPostAuthenticateClientHook
		) {
		
		hookHandlerService.attachHooker(SimpleFormViewHook.class, accountInhibitionUserFormHooker);
		
	}

}
