package net.datenwerke.rs.scriptreport.service.scriptreport.hookers;

import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.base.service.hooks.UsageStatisticsReportEntryProviderHook;
import net.datenwerke.rs.scriptreport.service.scriptreport.entities.ScriptReport;
import net.datenwerke.rs.scriptreport.service.scriptreport.entities.ScriptReportVariant;
import net.datenwerke.rs.scriptreport.service.scriptreport.locale.ScriptReportMessages;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.UsageStatisticsService;

public class UsageStatisticsScriptReportProviderHooker implements UsageStatisticsReportEntryProviderHook {

   private final UsageStatisticsService usageStatisticsService;
   
   private final static String SCRIPT_REPORTS = "SCRIPT_REPORTS";
   private final static String SCRIPT_REPORTS_VAR = "SCRIPT_REPORTS_VAR";
   
   @Inject
   public UsageStatisticsScriptReportProviderHooker(
         UsageStatisticsService usageStatisticsService
         ) {
      this.usageStatisticsService = usageStatisticsService;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Object> provideEntry() {
      return usageStatisticsService.provideReportCountValueEntry(SCRIPT_REPORTS,
            ScriptReportMessages.INSTANCE.scriptReports(), ScriptReport.class, SCRIPT_REPORTS_VAR,
            ScriptReportMessages.INSTANCE.scriptReportVariants(), ScriptReportVariant.class);
   }

}
