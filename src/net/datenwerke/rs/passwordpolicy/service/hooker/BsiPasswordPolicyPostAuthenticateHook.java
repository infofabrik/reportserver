package net.datenwerke.rs.passwordpolicy.service.hooker;

import java.util.Date;

import com.google.inject.Inject;

import net.datenwerke.async.DwAsyncService;
import net.datenwerke.async.helpers.TransactionalRunnableFactory;
import net.datenwerke.rs.passwordpolicy.client.PasswordExpiredAuthenticationResultInfo;
import net.datenwerke.rs.passwordpolicy.service.BsiPasswordPolicy;
import net.datenwerke.rs.passwordpolicy.service.BsiPasswordPolicyService;
import net.datenwerke.rs.passwordpolicy.service.BsiPasswordPolicyUserMetadata;
import net.datenwerke.rs.utils.misc.DateUtils;
import net.datenwerke.security.client.login.resultinfos.AccountLockedAuthenticateResultInfo;
import net.datenwerke.security.service.authenticator.AuthenticationResult;
import net.datenwerke.security.service.authenticator.hooks.PostAuthenticateHook;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.entities.User;

public class BsiPasswordPolicyPostAuthenticateHook implements PostAuthenticateHook {

   private final static int MILLISECONDS_PER_MINUTE = 60 * 1000;

   private final UserManagerService userManagerService;
   private final DwAsyncService dwAsyncService;
   private final TransactionalRunnableFactory trFactory;

   private final BsiPasswordPolicyService bsiPasswordPolicyService;

   @Inject
   public BsiPasswordPolicyPostAuthenticateHook(UserManagerService userManagerService, DwAsyncService dwAsyncService,
         TransactionalRunnableFactory trFactory, BsiPasswordPolicyService bsiPasswordPolicyService) {
      this.userManagerService = userManagerService;
      this.dwAsyncService = dwAsyncService;
      this.trFactory = trFactory;
      this.bsiPasswordPolicyService = bsiPasswordPolicyService;
   }

   @Override
   public void authenticated(final AuthenticationResult authRes) {
      if (!bsiPasswordPolicyService.isActive())
         return;

      User user = authRes.getUser();
      if (null != user) {
         final BsiPasswordPolicyUserMetadata data = bsiPasswordPolicyService.getUserMetadata(user);

         BsiPasswordPolicy bsiPasswordPolicy = bsiPasswordPolicyService.getPolicy();

         /* Check if there are failed login attempts to reset */
         if (null != data.getLastFailedLogin()) {
            long sinceLastFailure = (System.currentTimeMillis() - data.getLastFailedLogin().getTime())
                  / MILLISECONDS_PER_MINUTE;
            if (sinceLastFailure > bsiPasswordPolicy.getAccountLockoutAutoResetTimeout()) {
               data.setFailedLoginCount(0);
               data.setLastFailedLogin(null);
            }
         }

         /* Check if account is locked */
         if (data.getFailedLoginCount() > bsiPasswordPolicy.getAccountLockoutThreshold()) {
            authRes.setAllowed(false);
            authRes.addInfo(new AccountLockedAuthenticateResultInfo(new Date((long) data.getLastFailedLogin().getTime()
                  + ((long) bsiPasswordPolicy.getAccountLockoutAutoResetTimeout() * (long) MILLISECONDS_PER_MINUTE))));
            return;
         }

         if (authRes.isAllowed()) {
            /* process successful login */
            data.registerSuccessfulLogin();

            /* Check if password has expired */
            if (bsiPasswordPolicy.getPasswordMaxAge() > 0) {
               if (null != user.getPassword()) {
                  int expiresIn = 0;

                  if (null != data.getLastChangedPassword()) {

                     expiresIn = bsiPasswordPolicy.getPasswordMaxAge()
                           - DateUtils.getDeltaDays(data.getLastChangedPassword(), new Date());
                  }

                  if (expiresIn <= 5) {
                     authRes.addInfo(new PasswordExpiredAuthenticationResultInfo(user.getUsername(), expiresIn));
                  }

                  if (expiresIn <= 0) {
                     authRes.setAllowed(false);
                  }
               }
            }

            /* Check if password change is enforced */
            if (data.isEnforcePasswordChange()) {
               authRes.addInfo(new PasswordExpiredAuthenticationResultInfo(user.getUsername(), 0));
               authRes.setAllowed(false);
            }

         } else {
            /* process failed login */
            data.registerFailedLogin();
         }

         final long userId = authRes.getUser().getId();
         Runnable updateUserProperties = trFactory.create(new Runnable() {

            @Override
            public void run() {
               User user = (User) userManagerService.getNodeById(userId);
               bsiPasswordPolicyService.updateUserMetadata(user, data);
               userManagerService.merge(user);

            }
         });

         dwAsyncService.submit(updateUserProperties);
      }

   }

}