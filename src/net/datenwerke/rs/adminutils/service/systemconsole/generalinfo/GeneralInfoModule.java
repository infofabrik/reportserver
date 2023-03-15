package net.datenwerke.rs.adminutils.service.systemconsole.generalinfo;

import com.google.inject.AbstractModule;

public class GeneralInfoModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(GeneralInfoService.class).to(GeneralInfoServiceImpl.class);
      bind(UsageStatisticsService.class).to(UsageStatisticsServiceImpl.class);
   }

}
