package net.datenwerke.rs.authenticator.service.pam;

import javax.inject.Inject;

import net.datenwerke.rs.utils.crypto.PasswordHasher;
import net.datenwerke.security.service.usermanager.UserManagerService;

public class UserPasswordPAMAuthoritative extends UserPasswordPAM {

	@Inject
	public UserPasswordPAMAuthoritative(UserManagerService userManagerService, PasswordHasher passwordHasher) {
		super(userManagerService, passwordHasher);
	}
	
	@Override
	protected boolean isAuthoritative() {
		return true;
	}

}
