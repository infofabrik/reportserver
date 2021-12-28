package net.datenwerke.rs.birt.client.reportengines;

import com.google.gwt.inject.client.AbstractGinModule;

public class BirtUiModule extends AbstractGinModule {

   public static final String UPLOAD_REPORT_ID_FIELD = "reportID";
   public static final String UPLOAD_HANDLER_ID = "birtreport_upload_handler";

   @Override
   protected void configure() {
      bind(BirtUiStartup.class).asEagerSingleton();

   }

}
