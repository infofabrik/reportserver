package net.datenwerke.rs.base.service.reportengines;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.hooks.GeneralInfoCategoryProviderHook;
import net.datenwerke.rs.base.service.hooks.UsageStatisticsReportEntryProviderHook;
import net.datenwerke.rs.base.service.reportengines.hookers.AdjustBaseReportForExecutionHooker;
import net.datenwerke.rs.base.service.reportengines.hookers.BaseReportEngineProviderHooker;
import net.datenwerke.rs.base.service.reportengines.hookers.BaseReportTypeProviderHooker;
import net.datenwerke.rs.base.service.reportengines.hookers.ConfigureBaseReportViaRequestHooker;
import net.datenwerke.rs.base.service.reportengines.hookers.UsageStatisticsDynaListProviderHooker;
import net.datenwerke.rs.base.service.reportengines.hookers.UsageStatisticsJasperProviderHooker;
import net.datenwerke.rs.base.service.reportengines.hookers.ReportCategoryProviderHooker;
import net.datenwerke.rs.base.service.reportengines.hookers.UsageStatisticsTotalReportProviderHooker;
import net.datenwerke.rs.core.service.reportmanager.hooks.AdjustReportForExecutionHook;
import net.datenwerke.rs.core.service.reportmanager.hooks.ConfigureReportViaHistoryLocationHook;
import net.datenwerke.rs.core.service.reportmanager.hooks.ConfigureReportViaHttpRequestHook;
import net.datenwerke.rs.core.service.reportmanager.hooks.ReportEngineProviderHook;
import net.datenwerke.rs.core.service.reportmanager.hooks.ReportTypeProviderHook;

public class BaseReportEnginesStartup {

   @Inject
   public BaseReportEnginesStartup(
         final HookHandlerService hookHandler,

         final Provider<AdjustBaseReportForExecutionHooker> adjustReportForExecutionProvider,
         final Provider<ConfigureBaseReportViaRequestHooker> adjustReportViaRequestProvider,
         final Provider<BaseReportEngineProviderHooker> reportEngineProvider,
         final Provider<BaseReportTypeProviderHooker> reportTypeProvider,
         
         final Provider<UsageStatisticsTotalReportProviderHooker> usageStatsTotalReportProvider,
         final Provider<UsageStatisticsDynaListProviderHooker> usageStatsDynaListProvider,
         final Provider<UsageStatisticsJasperProviderHooker> usageStatsJasperProvider,
         
         final Provider<ReportCategoryProviderHooker> usageStatistics
         ) {

      hookHandler.attachHooker(AdjustReportForExecutionHook.class, adjustReportForExecutionProvider);
      hookHandler.attachHooker(ConfigureReportViaHttpRequestHook.class, adjustReportViaRequestProvider);
      hookHandler.attachHooker(ConfigureReportViaHistoryLocationHook.class, adjustReportViaRequestProvider);
      hookHandler.attachHooker(ReportEngineProviderHook.class, reportEngineProvider);
      hookHandler.attachHooker(ReportTypeProviderHook.class, reportTypeProvider);
      
      hookHandler.attachHooker(GeneralInfoCategoryProviderHook.class, usageStatistics,
            HookHandlerService.PRIORITY_LOW + 65);
      
      hookHandler.attachHooker(UsageStatisticsReportEntryProviderHook.class, usageStatsTotalReportProvider,
            HookHandlerService.PRIORITY_LOW);
      hookHandler.attachHooker(UsageStatisticsReportEntryProviderHook.class, usageStatsDynaListProvider,
            HookHandlerService.PRIORITY_LOW + 5);
      hookHandler.attachHooker(UsageStatisticsReportEntryProviderHook.class, usageStatsJasperProvider,
            HookHandlerService.PRIORITY_LOW + 15);
   }
}
