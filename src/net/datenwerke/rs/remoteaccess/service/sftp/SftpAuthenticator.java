package net.datenwerke.rs.remoteaccess.service.sftp;

import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import org.apache.sshd.server.auth.password.PasswordAuthenticator;
import org.apache.sshd.server.session.ServerSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Provider;

import net.datenwerke.rs.remoteaccess.service.sftp.genrights.SftpSecurityTarget;
import net.datenwerke.rs.utils.crypto.PasswordHasher;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.SecurityServiceSecuree;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.entities.User;

public class SftpAuthenticator implements PasswordAuthenticator {

   protected final Logger logger = LoggerFactory.getLogger(getClass().getName());

   private final Provider<AuthenticatorService> authenticatorServiceProvider;
   private final Provider<UserManagerService> userManagerServiceProvider;
   private final Provider<PasswordHasher> passwordHasherProvider;
   private Provider<SecurityService> securityServiceProvider;

   private Long userId;

   @Inject
   public SftpAuthenticator(Provider<AuthenticatorService> authenticatorServiceProvider,
         Provider<UserManagerService> userManagerServiceProvider, Provider<PasswordHasher> passwordHasherProvider,
         Provider<SecurityService> securityServiceProvider) {
      this.authenticatorServiceProvider = authenticatorServiceProvider;
      this.userManagerServiceProvider = userManagerServiceProvider;
      this.passwordHasherProvider = passwordHasherProvider;
      this.securityServiceProvider = securityServiceProvider;
   }

   @Override
   public boolean authenticate(final String username, final String password, ServerSession session) {
      Callable<User> callable = SftpRequestWrapper.wrapRequest(new Callable<User>() {

         @Override
         public User call() throws Exception {
            User user = getUserOrNull(username);

            if (null == user)
               return null;

            if (null != user.getPassword()
                  && passwordHasherProvider.get().validatePassword(user.getPassword(), password)) {
               authenticatorServiceProvider.get().setAuthenticated(user.getId());
               return user;
            }

            return null;
         }
      }, session);

      try {
         User user = callable.call();

         SecurityService securityService = securityServiceProvider.get();
         boolean hasAccess = securityService.checkRights(user, SftpSecurityTarget.class, SecurityServiceSecuree.class,
               Execute.class);

         if (!hasAccess) {
            this.userId = null;
            return false;
         }

         if (null != user)
            this.userId = user.getId();

         return null != user;
      } catch (Exception e) {
         logger.warn(e.getMessage(), e);
         return false;
      }
   }

   public Long getUserId() {
      return userId;
   }

   public User getUser() {
      if (null != userId)
         return (User) userManagerServiceProvider.get().getNodeById(userId);
      return null;
   }

   protected User getUserOrNull(String username) {
      try {
         return userManagerServiceProvider.get().getUserByName(username);
      } catch (NoResultException ex) {
         return null;
      }
   }

   public boolean isAuthenticated() {
      return userId != null;
   }

   public void authenticate() {
      User user = getUser();
      if (null != user)
         authenticatorServiceProvider.get().setAuthenticated(user.getId());
   }

}
