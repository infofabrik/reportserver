package net.datenwerke.rs.tabletemplate.service.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface TableTemplateMessages extends Messages {

   public final static TableTemplateMessages INSTANCE = LocalizationServiceImpl.getMessages(TableTemplateMessages.class);

   public String tableReportUsageStatistics();

}
