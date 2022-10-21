package net.datenwerke.rs.authenticator.cr.server;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.authenticator.client.login.dto.ChallengeResponseContainer;
import net.datenwerke.rs.authenticator.cr.client.ChallengeResponseRpcService;
import net.datenwerke.rs.authenticator.cr.service.ChallengeResponseService;
import net.datenwerke.rs.utils.crypto.PasswordHasher;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.annotation.SecurityChecked;

/**
 * 
 *
 */
@Singleton
public class ChallengeResponseRpcServiceImpl extends SecuredRemoteServiceServlet
      implements ChallengeResponseRpcService {

   private static final long serialVersionUID = -5730511102140345917L;
   private final ChallengeResponseService challengeResponseService;
   private final PasswordHasher passwordHasher;

   @Inject
   public ChallengeResponseRpcServiceImpl(ChallengeResponseService challengeResponseService,
         PasswordHasher passwordHasher) {
      this.challengeResponseService = challengeResponseService;
      this.passwordHasher = passwordHasher;
   }

   @Override
   @SecurityChecked(bypass = true)
   @Transactional(rollbackOn = { Exception.class })
   public ChallengeResponseContainer requestChallenge() throws ServerCallFailedException {
      return challengeResponseService.requestChallenge();
   }

   @Override
   @SecurityChecked(bypass = true)
   @Transactional(rollbackOn = { Exception.class })
   public String getHmacPassphrase() {
      return passwordHasher.getHmacPassphrase();
   }

}
