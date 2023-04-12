package net.datenwerke.rs.usagestatistics.service.usagestatistics;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public interface UsageStatisticsService {

   ImmutablePair<Long, Long> getReportCount();
   
   ImmutablePair<Long, Long> getSpecificReportCount(Class<? extends Report> reportClazz, Class<? extends Report> variantClazz);
   
   long getNodeCount(Class<? extends AbstractNode<?>> nodeClazz);

   long getReportCount(Class<? extends Report> clazz);

}
