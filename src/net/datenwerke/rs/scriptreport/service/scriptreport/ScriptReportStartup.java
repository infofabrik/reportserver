package net.datenwerke.rs.scriptreport.service.scriptreport;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.service.hooks.UsageStatisticsReportEntryProviderHook;
import net.datenwerke.rs.core.service.reportmanager.hookers.factory.ReportDefaultMergeHookerFactory;
import net.datenwerke.rs.scriptreport.service.scriptreport.entities.ScriptReport;
import net.datenwerke.rs.scriptreport.service.scriptreport.hookers.UsageStatisticsScriptReportProviderHooker;
import net.datenwerke.rs.utils.entitymerge.service.hooks.EntityMergeHook;

public class ScriptReportStartup {

   @Inject
   public ScriptReportStartup(
         final HookHandlerService hookHandler, 
         final Provider<UsageStatisticsScriptReportProviderHooker> usageStatsScriptReportProvider,
         final Provider<ReportDefaultMergeHookerFactory> reportFactory
         ) {

      hookHandler.attachHooker(UsageStatisticsReportEntryProviderHook.class, usageStatsScriptReportProvider,
            HookHandlerService.PRIORITY_LOW + 40);
      
      /* entity merge */
      hookHandler.attachHooker(EntityMergeHook.class, reportFactory.get().create(ScriptReport.class));
   }
}
