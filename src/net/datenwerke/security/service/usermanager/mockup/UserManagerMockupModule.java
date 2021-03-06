package net.datenwerke.security.service.usermanager.mockup;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

import net.datenwerke.rs.utils.crypto.PasswordHasher;
import net.datenwerke.security.service.crypto.passwordhasher.DummyPasswordHasherImpl;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.UserManagerServiceImpl;

/**
 * 
 *
 */
public class UserManagerMockupModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(UserManagerService.class).to(UserManagerServiceImpl.class).in(Scopes.SINGLETON);
      bind(PasswordHasher.class).to(DummyPasswordHasherImpl.class);
   }

}
