package net.datenwerke.rs.adminutils.service.systemconsole.generalinfo;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;

public interface UsageStatisticsService {

   ImmutablePair<Long, Long> getReportCount();
   
   ImmutablePair<Long, Long> getSpecificReportCount(Class<? extends Report> reportClazz, Class<? extends Report> variantClazz);

   long getReportCount(Class<? extends Report> clazz);

}
