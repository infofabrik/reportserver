package net.datenwerke.rs.passwordpolicy.server;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.passwordpolicy.service.lostpassword.LostPasswordService;
import net.datenwerke.security.client.security.lostpassword.rpc.LostPasswordRpcService;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.annotation.SecurityChecked;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

/**
 * 
 *
 */
@Singleton
public class LostPasswordRpcServiceImpl extends SecuredRemoteServiceServlet implements LostPasswordRpcService {

   /**
    * 
    */
   private static final long serialVersionUID = 5238457329967237907L;

   private final LostPasswordService lostPasswordService;

   @Inject
   public LostPasswordRpcServiceImpl(LostPasswordService lostPasswordService) {

      /* storeo objects */
      this.lostPasswordService = lostPasswordService;
   }

   @Override
   @SecurityChecked(bypass = true)
   @Transactional(rollbackOn = { Exception.class })
   public String requestNewPassword(String username) throws ServerCallFailedException {
      return lostPasswordService.requestNewPassword(username);
   }
}
