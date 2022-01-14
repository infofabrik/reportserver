package net.datenwerke.rs.passwordpolicy.client;

import com.google.gwt.inject.client.AbstractGinModule;

import net.datenwerke.rs.passwordpolicy.client.accountinhibition.AccountInhibitionUIModule;
import net.datenwerke.rs.passwordpolicy.client.activateuser.ActivateUserUIModule;

public class PasswordPolicyUIModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(PasswordPolicyUIStartup.class).asEagerSingleton();

      /* install submodule */
      install(new ActivateUserUIModule());
      install(new AccountInhibitionUIModule());
   }
}
