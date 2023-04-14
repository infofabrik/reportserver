package net.datenwerke.rs.usagestatistics.service.usagestatistics;

import static java.util.stream.Collectors.toMap;

import java.util.AbstractMap.SimpleEntry;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.hooks.UsageStatisticsEntryProviderHook;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public class UsageStatisticsServiceImpl implements UsageStatisticsService {

   private final Provider<EntityManager> entityManagerProvider;
   private final Provider<ReportService> reportServiceProvider;
   private final Provider<HookHandlerService> hookHandlerProvider;
   
   @Inject
   public UsageStatisticsServiceImpl(
         Provider<EntityManager> entityManagerProvider,
         final Provider<ReportService> reportServiceProvider,
         final Provider<HookHandlerService> hookHandlerProvider
   ) {
      this.entityManagerProvider = entityManagerProvider;
      this.reportServiceProvider = reportServiceProvider;
      this.hookHandlerProvider = hookHandlerProvider;
   }
         
   @Override
   public long getReportCount(Class<? extends Report> clazz) {
      return getNodeCount(clazz);
   }
   
   @Override
   public ImmutablePair<Long, Long> getReportCount() {
      long totalCount = getReportCount(Report.class);
      long variantCount = reportServiceProvider.get().getInstalledReportVariantTypes()
            .stream()
            .map(this::getReportCount)
            .mapToLong(Long::valueOf)
            .sum();
      return ImmutablePair.of(totalCount - variantCount, variantCount);
   }

   @Override
   public ImmutablePair<Long, Long> getSpecificReportCount(Class<? extends Report> reportClazz,
         Class<? extends Report> variantClazz) {
      long totalCount = getReportCount(reportClazz);
      long variantCount = getReportCount(variantClazz);
      return ImmutablePair.of(totalCount - variantCount, variantCount);
   }

   @Override
   public long getNodeCount(Class<? extends AbstractNode<?>> nodeClazz) {
      return ((Number) entityManagerProvider.get().createQuery("SELECT COUNT(n) FROM " + nodeClazz.getSimpleName() + " n")
            .getSingleResult()).longValue();
   }

   @Override
   public Map<ImmutablePair<String, String>, Object> provideNodeCountValueEntry(String key, String msg,
         Class<? extends AbstractNode<?>> clazz) {
      return Stream.of(new SimpleEntry<>(ImmutablePair.of(key, msg), getNodeCount(clazz)))
            .collect(toMap(Entry::getKey, Entry::getValue, (val1, val2) -> val2, LinkedHashMap::new));
   }

   @Override
   public Map<ImmutablePair<String, String>, Object> provideReportCountValueEntry(String reportKey, String reportMsg,
         Class<? extends Report> reportClazz, String variantKey, String variantMsg,
         Class<? extends Report> variantClazz) {
      final ImmutablePair<Long, Long> reportCount = getSpecificReportCount(reportClazz, variantClazz);
      return Stream
            .of(new SimpleEntry<>(ImmutablePair.of(reportKey, reportMsg), reportCount.getLeft()),
                new SimpleEntry<>(ImmutablePair.of(variantKey, variantMsg), reportCount.getRight()))
            .collect(toMap(Entry::getKey, Entry::getValue, (val1, val2) -> val2, LinkedHashMap::new));
   }

   @Override
   public Map<ImmutablePair<String, String>, Map<ImmutablePair<String, String>, Object>> provideCategory(String key,
         String msg, Class<? extends UsageStatisticsEntryProviderHook> hookClazz) {
      return Collections.singletonMap(ImmutablePair.of(key, msg), 
            hookHandlerProvider.get().getHookers(hookClazz)
               .stream()
               .map(hooker -> hooker.provideEntry())
               .reduce(new LinkedHashMap<>(), (into, valuesToAdd) -> {
                  into.putAll(valuesToAdd);
                  return into;
               }));
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Object> provideTableTemplateCountValueEntry(String key, String msg,
         String type) {
      final long templateCount = ((Number) entityManagerProvider.get()
            .createQuery("SELECT COUNT(t) FROM TableReportTemplate t WHERE t.templateType = :templateType")
            .setParameter("templateType", type)
            .getSingleResult()).longValue();
      return Stream
            .of(new SimpleEntry<>(ImmutablePair.of(key, msg), templateCount))
            .collect(toMap(Entry::getKey, Entry::getValue, (val1, val2) -> val2, LinkedHashMap::new));
   }
}
