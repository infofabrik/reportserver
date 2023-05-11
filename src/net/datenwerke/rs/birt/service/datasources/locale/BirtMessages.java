package net.datenwerke.rs.birt.service.datasources.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface BirtMessages extends Messages {
   
   public final static BirtMessages INSTANCE = LocalizationServiceImpl
         .getMessages(BirtMessages.class);

   String birtReportDatasources();

}
