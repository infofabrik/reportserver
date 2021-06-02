package net.datenwerke.rs.onedrive.service.onedrive.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface OneDriveDatasinkMessages extends Messages {

   public final static OneDriveDatasinkMessages INSTANCE = LocalizationServiceImpl
         .getMessages(OneDriveDatasinkMessages.class);

   String oneDriveDatasinkTypeName();

}