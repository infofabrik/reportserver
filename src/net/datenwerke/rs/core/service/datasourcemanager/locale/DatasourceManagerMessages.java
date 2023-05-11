package net.datenwerke.rs.core.service.datasourcemanager.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface DatasourceManagerMessages extends Messages {
   
   public final static DatasourceManagerMessages INSTANCE = LocalizationServiceImpl
         .getMessages(DatasourceManagerMessages.class);

   String historyUrlBuilderName();

   String historyUrlBuilderIcon();

   String databaseFolderTypeName();
   
   String datasourceUsageStatistics();

   String totalNumberOfDatasources();
   
   String folders();
}
