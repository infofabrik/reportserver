package net.datenwerke.rs.localfsdatasink.service.localfsdatasink.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface LocalFileSystemMessages extends Messages {

   public final static LocalFileSystemMessages INSTANCE = LocalizationServiceImpl
         .getMessages(LocalFileSystemMessages.class);

   String localFileSystemDatasinkTypeName();

}