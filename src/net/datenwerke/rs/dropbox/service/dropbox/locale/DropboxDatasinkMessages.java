package net.datenwerke.rs.dropbox.service.dropbox.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface DropboxDatasinkMessages extends Messages {

   public final static DropboxDatasinkMessages INSTANCE = LocalizationServiceImpl
         .getMessages(DropboxDatasinkMessages.class);

   String dropboxDatasinkTypeName();

   String dropboxDatasinks();

}
