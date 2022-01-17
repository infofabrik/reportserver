package net.datenwerke.rs.birt.service.reportengine.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface BirtEngineMessages extends Messages {

   public final static BirtEngineMessages INSTANCE = LocalizationServiceImpl.getMessages(BirtEngineMessages.class);

   String birtReportTypeName();
   
   String reportVariantTypeName();

   String commandBirt_description();

   String commandBirt_sub_shutdown_description();

   String shutdownComplete();
}
