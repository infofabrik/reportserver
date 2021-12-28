package net.datenwerke.rs.compiledreportstore;

import java.util.List;

import net.datenwerke.rs.compiledreportstore.entities.PersistentCompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;

public interface CompiledReportStoreService {

   PersistentCompiledReport getById(Long id);

   void persist(PersistentCompiledReport cReport);

   PersistentCompiledReport merge(PersistentCompiledReport cReport);

   void remove(PersistentCompiledReport cReport);

   PersistentCompiledReport toPersistenReport(CompiledReport compiledReport, Report report);

   List<PersistentCompiledReport> getCompiledReportsFor(Report report);

   void removeForReport(Report report);

   void unsetForReport(Report report);

}