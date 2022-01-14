package net.datenwerke.rs.passwordpolicy.service;

import net.datenwerke.security.service.authenticator.hooks.PostAuthenticateHook;
import net.datenwerke.security.service.usermanager.hooks.ChangePasswordHook;
import net.datenwerke.security.service.usermanager.hooks.PasswordSetHook;

public interface PasswordPolicy {

   public PasswordComplexitySpecification getPasswordComplexitySpecification();

   public PostAuthenticateHook getPostAuthenticateHooker();

   public ChangePasswordHook getChangePasswordHooker();

   public PasswordSetHook getPasswordSetHooker();

}
