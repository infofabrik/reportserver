package net.datenwerke.rs.scriptreport.service.scriptreport;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.service.hooks.UsageStatisticsReportEntryProviderHook;
import net.datenwerke.rs.scriptreport.service.scriptreport.hookers.UsageStatisticsScriptReportProviderHooker;

public class ScriptReportStartup {

   @Inject
   public ScriptReportStartup(
         final HookHandlerService hookHandler, 
         final Provider<UsageStatisticsScriptReportProviderHooker> usageStatsScriptReportProvider
         ) {

      hookHandler.attachHooker(UsageStatisticsReportEntryProviderHook.class, usageStatsScriptReportProvider,
            HookHandlerService.PRIORITY_LOW + 40);
   }
}
