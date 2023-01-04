package net.datenwerke.rs.authenticator.service.pam;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import net.datenwerke.security.client.login.AuthToken;
import net.datenwerke.security.service.authenticator.AuthenticationResult;
import net.datenwerke.security.service.authenticator.ReportServerPAM;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.entities.User;

public class EveryoneIsRootPAM implements ReportServerPAM {

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   private final UserManagerService userManagerService;

   @Inject
   public EveryoneIsRootPAM(UserManagerService userManagerService) {
      this.userManagerService = userManagerService;
   }

   @Override
   public AuthenticationResult authenticate(AuthToken[] tokens) {
      logger.warn("Login somebody as a super user without checking credentials!");

      User root = userManagerService.getUserByName("root");
      if (null != root && root.isSuperUser())
         return AuthenticationResult.grantAccess(root);

      Collection<User> allUsers = userManagerService.getAllUsers();
      for (User u : userManagerService.getAllUsers()) {
         if (u.isSuperUser())
            return AuthenticationResult.grantAccess(u);
      }

      logger.warn("Could not find a super user");

      /* login with the first user if everything fails */
      if (!allUsers.isEmpty())
         return AuthenticationResult.grantAccess(allUsers.iterator().next());

      logger.warn("Could not even find any user. Starting to panic.");

      /* can't do anything */
      return AuthenticationResult.cannotAuthenticate(isAuthoritative());
   }

   @Override
   public String getClientModuleName() {
      return null;
   }
   
   protected boolean isAuthoritative() {
      return false;
   }

}
