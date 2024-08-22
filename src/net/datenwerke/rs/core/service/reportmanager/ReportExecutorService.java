package net.datenwerke.rs.core.service.reportmanager;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.basereports.CompiledReportMetadata;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.security.service.usermanager.entities.User;

/**
 * Service providing functionality to execute reports.
 * 
 *
 */
public interface ReportExecutorService {

   public static final String OUTPUT_FORMAT_PDF = "PDF"; //$NON-NLS-1$
   public static final String OUTPUT_FORMAT_EXCEL = "EXCEL"; //$NON-NLS-1$
   public static final String OUTPUT_FORMAT_JXLS_TEMPLATE = "JXLS_TEMPLATE"; //$NON-NLS-1$
   public static final String OUTPUT_FORMAT_WORD = "WORD"; //$NON-NLS-1$
   public static final String OUTPUT_FORMAT_HTML = "HTML"; //$NON-NLS-1$
   public static final String OUTPUT_FORMAT_PNG = "PNG"; //$NON-NLS-1$
   public static final String OUTPUT_FORMAT_CSV = "CSV"; //$NON-NLS-1$
   public static final String OUTPUT_FORMAT_JSON = "JSON"; //$NON-NLS-1$
   public static final String OUTPUT_FORMAT_JSON_COMPACT = "JSONC"; //$NON-NLS-1$
   public static final String OUTPUT_FORMAT_RTF = "RTF"; //$NON-NLS-1$
   public static final String OUTPUT_FORMAT_TABLE = "RS_TABLE"; //$NON-NLS-1$
   public static final String OUTPUT_FORMAT_METADATA = "RS_METADATA"; //$NON-NLS-1$
   public static final String OUTPUT_FORMAT_DATACOUNT = "RS_DATACOUNT"; //$NON-NLS-1$
   public static final String OUTPUT_FORMAT_SIMPLE_BEAN = "RS_SIMPLE_BEAN";
   public static final String OUTPUT_FORMAT_CHART_DATA = "RS_CHART_DATA";
   public static final String OUTPUT_FORMAT_TEXT = "TEXT";
   public static final String OUTPUT_FORMAT_XML = "XML";
   public static final String METADATA_FORMAT_PLAIN = "METADATA_PLAIN";
   public static final String OUTPUT_FORMAT_STREAM_TABLE = "RS_STREAM_TABLE"; //$NON-NLS-1$
   public static final String OUTPUT_FORMAT_REPORTINFORMATION = "RS_REPORTINFORMATION";

   public void isExecutable(Report report, String outputFormat, ReportExecutionConfig... configs)
         throws ReportExecutorException;

   public void isExecutable(Report report, User user, String outputFormat, ReportExecutionConfig... configs)
         throws ReportExecutorException;

   public void isExecutable(Report report, ParameterSet additionalParameters, User user, String outputFormat,
         ReportExecutionConfig... configs) throws ReportExecutorException;

   public CompiledReport execute(Report report, String outputFormat) throws ReportExecutorException;

   public CompiledReport execute(Report report, ParameterSet parameterSet, String outputFormat)
         throws ReportExecutorException;

   public CompiledReport execute(Report report, String outputFormat, ReportExecutionConfig... configs)
         throws ReportExecutorException;

   public CompiledReport execute(Report report, User user, String outputFormat, ReportExecutionConfig... configs)
         throws ReportExecutorException;

   public CompiledReport execute(Report report, ParameterSet parameterSet, String outputFormat,
         ReportExecutionConfig... configs) throws ReportExecutorException;

   public CompiledReport execute(Report report, ParameterSet parameterSet, User user, String outputFormat,
         ReportExecutionConfig... configs) throws ReportExecutorException;

   public List<Map<String, Object>> variantTest(Report report, List<DatasourceDefinition> datasources);

   public CompiledReportMetadata exportMetadata(Report report, String outputFormat) throws ReportExecutorException;;

   public CompiledReportMetadata exportMetadata(Report report, User user, ParameterSet parameterSet,
         String outputFormat) throws ReportExecutorException;

   CompiledReport execute(OutputStream os, Report report, ParameterSet parameterSet, User user, String outputFormat,
         ReportExecutionConfig... configs) throws ReportExecutorException;

   public CompiledReport executeDry(Report report, ParameterSet backLinkSet, User user, String outputFormat,
         ReportExecutionConfig... reportExecutorConfigs) throws ReportExecutorException;

   boolean supportsStreaming(Report report, ParameterSet parameterSet, User user, String outputFormat,
         ReportExecutionConfig... configs) throws ReportExecutorException;
}
