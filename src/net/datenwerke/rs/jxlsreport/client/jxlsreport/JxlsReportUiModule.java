package net.datenwerke.rs.jxlsreport.client.jxlsreport;

import com.google.gwt.inject.client.AbstractGinModule;

public class JxlsReportUiModule extends AbstractGinModule {

   public static final String UPLOAD_REPORT_ID_FIELD = "reportID";
   public static final String UPLOAD_HANDLER_ID = "jxlsreport_upload_handler";

   @Override
   protected void configure() {
      bind(JxlsReportUiStartup.class).asEagerSingleton();
   }

}
