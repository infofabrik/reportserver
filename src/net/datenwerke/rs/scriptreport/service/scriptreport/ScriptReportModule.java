package net.datenwerke.rs.scriptreport.service.scriptreport;

import com.google.inject.AbstractModule;

public class ScriptReportModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(ScriptReportStartup.class).asEagerSingleton();
   }

}
