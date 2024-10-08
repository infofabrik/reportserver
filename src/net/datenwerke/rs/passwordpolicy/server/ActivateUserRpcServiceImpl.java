package net.datenwerke.rs.passwordpolicy.server;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.persist.Transactional;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.passwordpolicy.client.activateuser.rpc.ActivateUserRpcService;
import net.datenwerke.rs.passwordpolicy.service.activateuser.ActivateUserService;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.annotation.ArgumentVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Write;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.entities.User;

/**
 * 
 *
 */
@Singleton
public class ActivateUserRpcServiceImpl extends SecuredRemoteServiceServlet implements ActivateUserRpcService {

   /**
    * 
    */
   private static final long serialVersionUID = -136267079623263062L;

   private UserManagerService userManagerService;
   private ActivateUserService activateUserService;

   @Inject
   public ActivateUserRpcServiceImpl(UserManagerService userManagerService, ActivateUserService activateUserService) {

      this.userManagerService = userManagerService;
      this.activateUserService = activateUserService;
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   @SecurityChecked(argumentVerification = {
         @ArgumentVerification(name = "user", isDto = true, verify = @RightsVerification(rights = Write.class)) })
   public void activateAccount(@Named("user") UserDto user, boolean force) throws ServerCallFailedException {
      try {
         User u = (User) userManagerService.getNodeById(user.getId());
         activateUserService.activateAccount(u, force);
      } catch (Exception e) {
         throw new ServerCallFailedException(e);
      }
   }

}
