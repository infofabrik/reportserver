package net.datenwerke.rs.scriptreport.service.scriptreport.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface ScriptReportMessages extends Messages {

   public final static ScriptReportMessages INSTANCE = LocalizationServiceImpl.getMessages(ScriptReportMessages.class);

   String scriptReportTypeName();
   
   String reportVariantTypeName();

}
