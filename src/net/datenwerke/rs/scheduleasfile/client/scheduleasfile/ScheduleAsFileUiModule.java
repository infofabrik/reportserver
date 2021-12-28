package net.datenwerke.rs.scheduleasfile.client.scheduleasfile;

import com.google.gwt.inject.client.AbstractGinModule;

public class ScheduleAsFileUiModule extends AbstractGinModule {

   public static final String EXPORT_SERVLET = "exportScheduledAsFile";

   @Override
   protected void configure() {
      bind(ScheduleAsFileUiStartup.class).asEagerSingleton();
   }

}
