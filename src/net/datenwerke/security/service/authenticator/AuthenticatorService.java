package net.datenwerke.security.service.authenticator;

import java.util.Map;
import java.util.Set;

import net.datenwerke.security.client.login.AuthToken;
import net.datenwerke.security.service.usermanager.entities.User;

/**
 * 
 *
 */
public interface AuthenticatorService {

   public AuthenticationResult authenticate(AuthToken[] tokens);

   /**
    * Returns if a user was previously successfully authenticated using the
    * authenticate method()
    * 
    */
   public boolean isAuthenticated();

   /**
    * Returns the authenticated user
    * 
    * @throws RuntimeException if no user was authenticated
    */
   public User getCurrentUser();

   public Set<String> getRequiredClientModules();

   public void logoff();

   void su(User user);

   Map<Long, Long> getLastRequests();

   void setAuthenticated(Long userId);

   void setAuthenticatedInThread(Long userId);

   void logoffUserInThread();
   
   Set<ReportServerPAM> getStaticPams();

}
