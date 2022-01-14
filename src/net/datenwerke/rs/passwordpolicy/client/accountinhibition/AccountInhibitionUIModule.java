package net.datenwerke.rs.passwordpolicy.client.accountinhibition;

import com.google.gwt.inject.client.AbstractGinModule;

public class AccountInhibitionUIModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(AccountInhibitionUIStartup.class).asEagerSingleton();

   }

}
