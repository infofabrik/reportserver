package net.datenwerke.rs.saiku.service.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface SaikuMessages extends Messages {

   public final static SaikuMessages INSTANCE = LocalizationServiceImpl.getMessages(SaikuMessages.class);

   String reportTypeName();

   String undefinedMeasureGroup();

   String errorClearCacheXMLA();

   String mondrianDatasourceTypeName();
   
   String saikuReports();
   
   String saikuReportVariants();

   String mondrianDatasources();

}
