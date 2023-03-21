package net.datenwerke.security.service.authenticator;

import java.util.Map;
import java.util.Set;

import com.google.inject.AbstractModule;

import net.datenwerke.security.client.login.AuthToken;
import net.datenwerke.security.service.usermanager.entities.User;

public class AuthenticateEverythingModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(AuthenticatorService.class).toInstance(new AuthenticatorService() {

         @Override
         public void su(User user) {

         }

         @Override
         public void logoff() {
         }

         @Override
         public boolean isAuthenticated() {
            return true;
         }

         @Override
         public Set<String> getRequiredClientModules() {
            return null;
         }

         @Override
         public Map<Long, Long> getLastRequests() {
            return null;
         }

         @Override
         public User getCurrentUser() {
            return null;
         }

         @Override
         public AuthenticationResult authenticate(AuthToken[] tokens) {
            return null;
         }

         @Override
         public void setAuthenticated(Long userId) {
            // TODO Auto-generated method stub

         }

         @Override
         public void logoffUserInThread() {
            // TODO Auto-generated method stub

         }

         @Override
         public void setAuthenticatedInThread(Long userId) {
            // TODO Auto-generated method stub

         }

         @Override
         public Set<ReportServerPAM> getStaticPams() {
            // TODO Auto-generated method stub
            return null;
         }

      });
   }

}
