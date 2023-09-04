package net.datenwerke.gf.service.authenticator.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface AuthenticatorMessages extends Messages {

   public final static AuthenticatorMessages INSTANCE = LocalizationServiceImpl.getMessages(AuthenticatorMessages.class);

   public String pamsLabel();

   public String pamConfiguration();

}
