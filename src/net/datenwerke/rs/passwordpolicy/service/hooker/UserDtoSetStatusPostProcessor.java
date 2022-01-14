package net.datenwerke.rs.passwordpolicy.service.hooker;

import java.util.Date;

import com.google.inject.Inject;

import net.datenwerke.rs.passwordpolicy.service.BsiPasswordPolicy;
import net.datenwerke.rs.passwordpolicy.service.BsiPasswordPolicyService;
import net.datenwerke.rs.passwordpolicy.service.BsiPasswordPolicyUserMetadata;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.service.usermanager.entities.User;
import net.datenwerke.security.service.usermanager.hooks.UserDtoPostProcessorHook;

public class UserDtoSetStatusPostProcessor implements UserDtoPostProcessorHook {

   private final BsiPasswordPolicyService bsiPasswordPolicyService;

   @Inject
   public UserDtoSetStatusPostProcessor(BsiPasswordPolicyService bsiPasswordPolicy) {
      this.bsiPasswordPolicyService = bsiPasswordPolicy;
   }

   @Override
   public void adaptDto(User user, UserDto userDto) {
      if (!bsiPasswordPolicyService.isActive() || !userDto.isActive())
         return;

      BsiPasswordPolicyUserMetadata data = bsiPasswordPolicyService.getUserMetadata(user);

      if (null != data.getAccountInhibited() && data.getAccountInhibited())
         userDto.setActive(false);
      else if (null != data.getAccountExpirationDate() && data.getAccountExpirationDate().before(new Date()))
         userDto.setActive(false);
      else {
         BsiPasswordPolicy policy = bsiPasswordPolicyService.getPolicy();

         if (data.getFailedLoginCount() > policy.getAccountLockoutThreshold())
            userDto.setActive(false);
      }
   }

}
