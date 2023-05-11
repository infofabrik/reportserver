package net.datenwerke.rs.globalconstants.service.globalconstants.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface GlobalConstantsMessages extends Messages {

   public final static GlobalConstantsMessages INSTANCE = LocalizationServiceImpl
         .getMessages(GlobalConstantsMessages.class);

   String globalConstants();
   
   String totalGlobalConstants();
}
