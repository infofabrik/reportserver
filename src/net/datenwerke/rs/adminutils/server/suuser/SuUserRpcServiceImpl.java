package net.datenwerke.rs.adminutils.server.suuser;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.adminutils.client.suuser.rpc.SuUserRpcService;
import net.datenwerke.rs.adminutils.service.su.genrights.SuSecurityTarget;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.annotation.GenericTargetVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.exceptions.ViolatedSecurityException;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;
import net.datenwerke.security.service.usermanager.entities.User;

/**
 * 
 *
 */
@Singleton
public class SuUserRpcServiceImpl extends SecuredRemoteServiceServlet implements SuUserRpcService {

   /**
    * 
    */
   private static final long serialVersionUID = -7294232233898833127L;

   private final Provider<AuthenticatorService> authenticatorServiceProvider;
   private final UserManagerService userManagerService;
   private final SecurityService securityService;

   @Inject
   public SuUserRpcServiceImpl(Provider<AuthenticatorService> authenticatorServiceProvider,
         UserManagerService userManagerService, SecurityService securityService) {

      /* store objects */
      this.authenticatorServiceProvider = authenticatorServiceProvider;
      this.userManagerService = userManagerService;
      this.securityService = securityService;
   }

   @Override
   @SecurityChecked(genericTargetVerification = @GenericTargetVerification(target = SuSecurityTarget.class, verify = @RightsVerification(rights = Execute.class)))
   @Transactional(rollbackOn = { Exception.class })
   public void su(String username) throws ServerCallFailedException {
      User user = userManagerService.getUserByName(username);
      if (null == user)
         throw new ExpectedException("Could not find user: " + username);

      authenticatorServiceProvider.get().su(user);
   }

   @Override
   @SecurityChecked(genericTargetVerification = @GenericTargetVerification(target = SuSecurityTarget.class, verify = @RightsVerification(rights = Execute.class)))
   @Transactional(rollbackOn = { Exception.class })
   public void su(Long id) throws ExpectedException {
      AbstractUserManagerNode node = (AbstractUserManagerNode) userManagerService.getNodeById(id);
      if (null == node || !(node instanceof AbstractUserManagerNode))
         throw new ExpectedException("Could not find user: " + id);

      if (!securityService.checkRights(node, Execute.class)) {
         throw new ViolatedSecurityException("Insufficient priviledges to log in as " + node.getName());
      }

      authenticatorServiceProvider.get().su((User) node);
   }

}
