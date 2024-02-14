package net.datenwerke.rs.core.service.reportmanager.hookers.factory;

import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.hookers.ReportDefaultMergeHooker;

public interface ReportDefaultMergeHookerFactory {
   
   ReportDefaultMergeHooker<? extends Report> create(Class<? extends Report> targetClass);

}
