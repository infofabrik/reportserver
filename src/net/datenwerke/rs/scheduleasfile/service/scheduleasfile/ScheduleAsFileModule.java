package net.datenwerke.rs.scheduleasfile.service.scheduleasfile;

import com.google.inject.AbstractModule;

public class ScheduleAsFileModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(ScheduleAsFileStartup.class).asEagerSingleton();
   }

}
