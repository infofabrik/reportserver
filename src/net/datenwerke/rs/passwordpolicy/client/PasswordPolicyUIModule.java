package net.datenwerke.rs.passwordpolicy.client;

import net.datenwerke.rs.passwordpolicy.client.accountinhibition.AccountInhibitionUIModule;
import net.datenwerke.rs.passwordpolicy.client.activateuser.ActivateUserUIModule;

import com.google.gwt.inject.client.AbstractGinModule;

public class PasswordPolicyUIModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(PasswordPolicyUIStartup.class).asEagerSingleton();
	
		/* install submodule */
		install(new ActivateUserUIModule());
		install(new AccountInhibitionUIModule());
	}
}
