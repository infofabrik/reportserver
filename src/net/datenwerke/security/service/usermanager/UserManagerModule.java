package net.datenwerke.security.service.usermanager;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

import net.datenwerke.security.service.genrights.usermanager.GenRightsUserManagerModule;

/**
 * 
 *
 */
public class UserManagerModule extends AbstractModule {

   public static String USER_PROPERTY_USER_LOCALE = "user:locale";

   @Override
   protected void configure() {
      bind(UserManagerService.class).to(UserManagerServiceImpl.class).in(Scopes.SINGLETON);
      bind(UserPropertiesService.class).to(UserPropertiesServiceImpl.class);

      bind(UserManagerStartup.class).asEagerSingleton();

      /* submodules */
      install(new GenRightsUserManagerModule());
   }

}
