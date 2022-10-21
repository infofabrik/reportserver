package net.datenwerke.rs.authenticator.cr.service.pam;

import com.google.inject.Inject;

import net.datenwerke.rs.authenticator.cr.service.ChallengeResponseService;
import net.datenwerke.security.service.usermanager.UserManagerService;

public class ChallengeResponseServerPAM extends ChallengeResponsePAM {

   @Inject
   public ChallengeResponseServerPAM(UserManagerService userManagerService,
         ChallengeResponseService challengeResponseService) {
      super(userManagerService, challengeResponseService);
   }

   @Override
   public String getClientModuleName() {
      return null;
   }
}
