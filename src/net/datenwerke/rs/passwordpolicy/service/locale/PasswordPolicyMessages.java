package net.datenwerke.rs.passwordpolicy.service.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface PasswordPolicyMessages extends Messages{

	public final static PasswordPolicyMessages INSTANCE = LocalizationServiceImpl.getMessages(PasswordPolicyMessages.class);

	String exceptionUserNotExists(String username);

	String exceptionCouldNotSendPassword(String message);
	
	String activateEmailSubject();
	String activateEmailSalutation();
	String activateEmailIntro();
	String activateEmailAccount();
	String activateEmailEnd();
	String activateEmailUsername();
	String activateEmailPassword();
	
	String lostPasswordMessageConfirmation();
	
	String lostPasswordSubject();
	String lostPasswordSalutation();
	String lostPasswordIntro();
	String lostPasswordUsername();
	String lostPasswordPassword();
	String lostPasswordEnd();
	
	String changedPasswordSubject();
	String changedPasswordSalutation();
	String changedPasswordIntro();
	String changedPasswordEnd();
	
	String createdPasswordSubject();
	String createdPasswordSalutation();
	String createdPasswordIntro();
	String createdPasswordEnd();

}

