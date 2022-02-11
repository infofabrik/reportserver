package net.datenwerke.rs.tabledatasink.service.tabledatasink.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface TableDatasinkMessages extends Messages {
   public final static TableDatasinkMessages INSTANCE = LocalizationServiceImpl
         .getMessages(TableDatasinkMessages.class);

   String tableDatasinkTypeName();

}
