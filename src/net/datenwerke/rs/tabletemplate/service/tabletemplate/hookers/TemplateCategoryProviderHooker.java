package net.datenwerke.rs.tabletemplate.service.tabletemplate.hookers;

import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.google.inject.Provider;

import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.hooks.GeneralInfoCategoryProviderHook;
import net.datenwerke.rs.tabletemplate.service.locale.TableTemplateMessages;
import net.datenwerke.rs.tabletemplate.service.tabletemplate.hooks.UsageStatisticsTemplateEntryProviderHook;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.UsageStatisticsService;

public class TemplateCategoryProviderHooker implements GeneralInfoCategoryProviderHook {

   private final Provider<UsageStatisticsService> usageStatisticsServiceProvider;
   
   private final static String TYPE = "TEMPLATE_USAGE_STATISTICS";
   
   @Inject
   public TemplateCategoryProviderHooker(
         Provider<UsageStatisticsService> usageStatisticsServiceProvider
         ) {
      this.usageStatisticsServiceProvider = usageStatisticsServiceProvider;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Map<ImmutablePair<String, String>, Object>> provideCategory() {
      return usageStatisticsServiceProvider.get().provideCategory(TYPE,
            TableTemplateMessages.INSTANCE.tableReportUsageStatistics(), UsageStatisticsTemplateEntryProviderHook.class);
   }
}
