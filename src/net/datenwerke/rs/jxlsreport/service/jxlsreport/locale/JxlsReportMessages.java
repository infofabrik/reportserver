package net.datenwerke.rs.jxlsreport.service.jxlsreport.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface JxlsReportMessages extends Messages {

   public final static JxlsReportMessages INSTANCE = LocalizationServiceImpl.getMessages(JxlsReportMessages.class);

   String reportTypeName();
   
   String reportVariantTypeName();
   
   String jxlsReports();
   
   String jxlsReportVariants();

}
