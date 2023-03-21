package net.datenwerke.rs.usagestatistics.service.usagestatistics;

import com.google.inject.AbstractModule;

public class UsageStatisticsModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(UsageStatisticsService.class).to(UsageStatisticsServiceImpl.class);
      
      bind(UsageStatisticsStartup.class).asEagerSingleton();
   }

}
