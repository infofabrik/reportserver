package net.datenwerke.rs.core.service.datasinkmanager.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface DatasinkManagerMessages extends Messages {
   
   public final static DatasinkManagerMessages INSTANCE = LocalizationServiceImpl
         .getMessages(DatasinkManagerMessages.class);

   String historyUrlBuilderName();

   String historyUrlBuilderIcon();

   String datasinkFolderTypeName();

   String totalDatasinks();

   String datasinkUsageStatistics();
}
