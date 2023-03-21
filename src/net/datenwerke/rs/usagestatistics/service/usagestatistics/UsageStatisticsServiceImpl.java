package net.datenwerke.rs.usagestatistics.service.usagestatistics;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.google.inject.Provider;

import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;

public class UsageStatisticsServiceImpl implements UsageStatisticsService {

   private final Provider<EntityManager> entityManagerProvider;
   private final Provider<ReportService> reportServiceProvider;
   
   @Inject
   public UsageStatisticsServiceImpl(
         Provider<EntityManager> entityManagerProvider,
         final Provider<ReportService> reportServiceProvider
   ) {
      this.entityManagerProvider = entityManagerProvider;
      this.reportServiceProvider = reportServiceProvider;
   }
         
   @Override
   public long getReportCount(Class<? extends Report> clazz) {
      return ((Number) entityManagerProvider.get().createQuery("SELECT COUNT(r) FROM " + clazz.getSimpleName() + " r")
            .getSingleResult()).longValue();
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
}
