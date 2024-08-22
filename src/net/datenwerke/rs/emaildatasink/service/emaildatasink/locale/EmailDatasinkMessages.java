package net.datenwerke.rs.emaildatasink.service.emaildatasink.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface EmailDatasinkMessages extends Messages {

   public final static EmailDatasinkMessages INSTANCE = LocalizationServiceImpl
         .getMessages(EmailDatasinkMessages.class);

   String emailDatasinkTypeName();

   String emailDatasinks();

}
