package net.datenwerke.rs.usagestatistics.service.usagestatistics;

import java.util.Map;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.hooks.UsageStatisticsEntryProviderHook;

public interface UsageStatisticsService {

   ImmutablePair<Long, Long> getReportCount();

   ImmutablePair<Long, Long> getSpecificReportCount(Class<? extends Report> reportClazz,
         Class<? extends Report> variantClazz);

   long getNodeCount(Class<?> nodeClazz);

   long getReportCount(Class<? extends Report> clazz);

   Map<ImmutablePair<String, String>, Object> provideNodeCountValueEntry(String key, String msg,
         Class<?> clazz);

   Map<ImmutablePair<String, String>, Object> provideReportCountValueEntry(String reportKey, String reportMsg,
         Class<? extends Report> reportClazz, String variantKey, String variantMsg,
         Class<? extends Report> variantClazz);

   Map<ImmutablePair<String, String>, Map<ImmutablePair<String, String>, Object>> provideCategory(String key,
         String msg, Class<? extends UsageStatisticsEntryProviderHook> hookClazz);
   
   Map<ImmutablePair<String, String>, Object> provideTableTemplateCountValueEntry(String key, String msg,
         String type);
   
   long getDatasourceUsageCount(DatabaseHelper helper);

}
