package net.datenwerke.security.service.authenticator.test;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import net.datenwerke.security.client.login.AuthToken;
import net.datenwerke.security.service.authenticator.AuthenticationResult;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.usermanager.entities.User;

public class AuthenticatorTestServiceImpl implements AuthenticatorService {

   private User currentUser;

   public AuthenticationResult authenticate(AuthToken[] tokens) {
      currentUser = new User();
      currentUser.setFirstname("Dummy"); //$NON-NLS-1$
      currentUser.setSuperUser(true);
      return new AuthenticationResult(true, currentUser);
   }

   public User getCurrentUser() {
      return currentUser;
   }

   public Set<String> getRequiredClientModules() {
      return null;
   }

   public boolean isAuthenticated() {
      return true;
   }

   public void logoff() {

   }

   @Override
   public void su(User user) {

   }

   @Override
   public Map<Long, Long> getLastRequests() {
      return Collections.emptyMap();
   }

   @Override
   public void setAuthenticated(Long userId) {
      authenticate(null);
   }

   @Override
   public void logoffUserInThread() {
      // TODO Auto-generated method stub

   }

   @Override
   public void setAuthenticatedInThread(Long userId) {
      // TODO Auto-generated method stub

   }
}
