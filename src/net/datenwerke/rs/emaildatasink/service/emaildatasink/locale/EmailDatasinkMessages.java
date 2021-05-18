package net.datenwerke.rs.emaildatasink.service.emaildatasink.locale;

import net.datenwerke.rs.utils.localization.Messages;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;

public interface EmailDatasinkMessages extends Messages {

   public final static EmailDatasinkMessages INSTANCE = LocalizationServiceImpl
         .getMessages(EmailDatasinkMessages.class);

   String emailDatasinkTypeName();

}
