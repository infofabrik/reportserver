package net.datenwerke.rs.usagestatistics.service.usagestatistics;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.hooks.GeneralInfoCategoryProviderHook;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.hookers.GeneralInfoUsageStatisticsCategoryProviderHooker;

public class UsageStatisticsStartup {

   @Inject
   public UsageStatisticsStartup(
         final HookHandlerService hookHandlerService,
         final Provider<GeneralInfoUsageStatisticsCategoryProviderHooker> usageStatistics
         ) {
      hookHandlerService.attachHooker(GeneralInfoCategoryProviderHook.class, usageStatistics,
            HookHandlerService.PRIORITY_LOW + 60);
         }

}
