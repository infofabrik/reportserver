package net.datenwerke.rs.authenticator.cr.service.pam;

import javax.inject.Inject;

import net.datenwerke.rs.authenticator.cr.service.ChallengeResponseService;
import net.datenwerke.security.service.usermanager.UserManagerService;

public class ChallengeResponsePAMAuthoritative extends ChallengeResponsePAM {

   @Inject
   public ChallengeResponsePAMAuthoritative(
         UserManagerService userManagerService,
         ChallengeResponseService challengeResponseService
         ) {
      super(userManagerService, challengeResponseService);
   }

   @Override
   protected boolean isAuthoritative() {
      return true;
   }

}
