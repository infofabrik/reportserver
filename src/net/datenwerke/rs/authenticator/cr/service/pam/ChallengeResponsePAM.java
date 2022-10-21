package net.datenwerke.rs.authenticator.cr.service.pam;

import com.google.inject.Inject;

import net.datenwerke.rs.authenticator.client.login.dto.ChallengeResponseAuthToken;
import net.datenwerke.rs.authenticator.client.login.dto.ChallengeResponseContainer;
import net.datenwerke.rs.authenticator.cr.client.pam.ChallengeResponseClientPam;
import net.datenwerke.rs.authenticator.cr.service.ChallengeResponseService;
import net.datenwerke.security.client.login.AuthToken;
import net.datenwerke.security.service.authenticator.AuthenticationResult;
import net.datenwerke.security.service.authenticator.ReportServerPAM;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.entities.User;

public class ChallengeResponsePAM implements ReportServerPAM {

   private static final String CLIENT_MODULE_NAME = ChallengeResponseClientPam.class.getName();
   private final UserManagerService userManagerService;
   private final ChallengeResponseService challengeResponseService;

   @Inject
   public ChallengeResponsePAM(UserManagerService userManagerService,
         ChallengeResponseService challengeResponseService) {
      this.userManagerService = userManagerService;
      this.challengeResponseService = challengeResponseService;
   }

   @Override

   public AuthenticationResult authenticate(AuthToken[] tokens) {
      for (Object token : tokens) {
         if (token instanceof ChallengeResponseAuthToken) {
            ChallengeResponseAuthToken crToken = (ChallengeResponseAuthToken) token;
            User u = authenticate(crToken.getUsername(), crToken.getChallengeResponse());
            if (null != u) {
               return new AuthenticationResult(true, u);
            } else {
               User usr = userManagerService.getUserOrNull(crToken.getUsername());
               AuthenticationResult result = new AuthenticationResult(false, usr);
               return result;
            }
         }
      }

      return new AuthenticationResult(false, null);
   }

   private User authenticate(String username, ChallengeResponseContainer container) {
      User user = userManagerService.getUserOrNull(username);

      if (null == user)
         return null;

      boolean res = challengeResponseService.validateResponse(container, user.getPassword());

      if (res) {
         return user;
      } else
         return null;
   }

   @Override
   public String getClientModuleName() {
      return CLIENT_MODULE_NAME;
   }
}
