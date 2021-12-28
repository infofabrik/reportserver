package net.datenwerke.rs.passwordpolicy.service;

import net.datenwerke.security.service.usermanager.entities.User;

public interface BsiPasswordPolicyService {

   public BsiPasswordPolicyUserMetadata getUserMetadata(User user);

   public void updateUserMetadata(User user, BsiPasswordPolicyUserMetadata userMetadata);

   public boolean isActive();

   public BsiPasswordPolicy getPolicy();

}
