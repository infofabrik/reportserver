package net.datenwerke.rs.reportdoc.service;

import java.util.List;

import javax.script.ScriptException;

import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;

/**
 * 
 *
 */
public interface ReportDocumentationService {

   public static final String RESOURCES_DIR = "/../../resources/groovy/";

   CompiledReport executeDocumentation(Report report, Long tsId, String format) throws ScriptException;

   CompiledReport executeDocumentationDeployAnalyze(Report newReport, Report destinationReport, String format,
         boolean ignoreCase) throws ScriptException;

   CompiledReport executeDocumentationVariantTest(Report report, List<DatasourceDefinition> datasources, String format)
         throws ScriptException;

   CompiledReport executeDocumentationRev(Report report, Long revision, String format) throws ScriptException;

   CompiledReport executeRevisions(Report report, String format) throws ScriptException;

}
