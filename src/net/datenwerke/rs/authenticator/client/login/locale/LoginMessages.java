package net.datenwerke.rs.authenticator.client.login.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface LoginMessages extends Messages{

	public final static LoginMessages INSTANCE = GWT.create(LoginMessages.class);
	
	String loggedOut();
	String notLoggedIn();
	String loginFailed();
	String loginMessage();
	String username();
	String password();
	String logout();
	String expirationWarningMessage();
	String expirationWarningTitle();
	String loggingOut();
	String options();
	String login();
}
