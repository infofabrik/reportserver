package net.datenwerke.rs.passwordpolicy.service.hooker;

import com.google.inject.Inject;

import net.datenwerke.rs.passwordpolicy.service.BsiPasswordPolicyService;
import net.datenwerke.rs.passwordpolicy.service.BsiPasswordPolicyUserMetadata;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.entities.User;
import net.datenwerke.security.service.usermanager.hooks.PasswordSetHook;

public class BsiPasswordPolicyPasswordSetHook implements PasswordSetHook {

   private final UserManagerService userManagerService;
   private final BsiPasswordPolicyService bsiPasswordPolicyService;

   @Inject
   public BsiPasswordPolicyPasswordSetHook(UserManagerService userManagerService,
         BsiPasswordPolicyService bsiPasswordPolicyService) {
      this.userManagerService = userManagerService;
      this.bsiPasswordPolicyService = bsiPasswordPolicyService;
   }

   @Override
   public void passwordWasSet(User user) {
      if (!bsiPasswordPolicyService.isActive())
         return;

      BsiPasswordPolicyUserMetadata userMetadata = bsiPasswordPolicyService.getUserMetadata(user);

      userMetadata.setFailedLoginCount(0);
      userMetadata.setLastChangedPassword(null);
      userMetadata.enforcePasswordChangeOnNextLogin();

      bsiPasswordPolicyService.updateUserMetadata(user, userMetadata);

      userManagerService.merge(user);
   }

}
