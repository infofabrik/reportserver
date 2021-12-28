package net.datenwerke.security.client.security.passwordpolicy.locale;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface PasswordPolicyMessages extends Messages {

   public static final PasswordPolicyMessages INSTANCE = GWT.create(PasswordPolicyMessages.class);

   String accountLockedMessage(Date date);

   String accountLockedHeading();

   String passwordExpiration();

   String passwordExpirationWarning(int expiresIn);

   String passwordExpired();

   String activateButtonLabel();

   String activateButtonTooltip();

   String accountInhibitedMessage();

   String accountInhibitedHeading();
}
