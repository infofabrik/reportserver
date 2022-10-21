package net.datenwerke.rs.googledrive.service.googledrive.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface GoogleDriveDatasinkMessages extends Messages {

   public final static GoogleDriveDatasinkMessages INSTANCE = LocalizationServiceImpl
         .getMessages(GoogleDriveDatasinkMessages.class);

   String googleDriveDatasinkTypeName();

}
