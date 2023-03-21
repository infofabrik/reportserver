package net.datenwerke.rs.usagestatistics.service.usagestatistics.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface UsageStatisticsMessages extends Messages {

   public final static UsageStatisticsMessages INSTANCE = LocalizationServiceImpl
         .getMessages(UsageStatisticsMessages.class);

   String usageStatistics();

}
