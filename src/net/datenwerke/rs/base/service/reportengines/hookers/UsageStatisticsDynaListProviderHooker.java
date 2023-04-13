package net.datenwerke.rs.base.service.reportengines.hookers;

import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.base.service.hooks.UsageStatisticsReportEntryProviderHook;
import net.datenwerke.rs.base.service.reportengines.locale.ReportEnginesMessages;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReportVariant;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.UsageStatisticsService;

public class UsageStatisticsDynaListProviderHooker implements UsageStatisticsReportEntryProviderHook {

   private final UsageStatisticsService usageStatisticsService;
   
   private final static String DYNAMIC_LISTS = "DYNAMIC_LISTS";
   private final static String DYNAMIC_LIST_VAR = "DYNAMIC_LIST_VAR";
   
   @Inject
   public UsageStatisticsDynaListProviderHooker(
         UsageStatisticsService usageStatisticsService
         ) {
      this.usageStatisticsService = usageStatisticsService;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Object> provideEntry() {
      return usageStatisticsService.provideReportCountValueEntry(DYNAMIC_LISTS,
            ReportEnginesMessages.INSTANCE.dynamicLists(), TableReport.class, DYNAMIC_LIST_VAR,
            ReportEnginesMessages.INSTANCE.dynamicListVariants(), TableReportVariant.class);
   }

}
