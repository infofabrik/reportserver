package net.datenwerke.rs.userprofile.client.userprofile;

import com.google.gwt.inject.client.AbstractGinModule;

public class UserProfileUIModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(UserProfileUIStartup.class).asEagerSingleton();
   }

}
