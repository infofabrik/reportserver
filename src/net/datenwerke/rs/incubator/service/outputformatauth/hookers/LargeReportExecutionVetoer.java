package net.datenwerke.rs.incubator.service.outputformatauth.hookers;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.configuration2.Configuration;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.base.client.AvailableReportProperties;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportInformation;
import net.datenwerke.rs.base.service.reportengines.table.TableReportUtils;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportProperty;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportStringProperty;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.core.service.reportmanager.hooks.ReportExecutionNotificationHook;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.core.service.reportserver.ReportServerService;
import net.datenwerke.rs.saiku.service.saiku.SaikuModule;
import net.datenwerke.security.service.usermanager.entities.User;
public class LargeReportExecutionVetoer implements ReportExecutionNotificationHook {
   
   private final TableReportUtils tableReportUtils;
   private final Provider<ConfigService> configServiceProvider;
   private static final int DEFAULT_EXPORT_MAX_SIZE = 10000;
   
   /* always allow certain formats */
   private final List<String> allowedFormats = Arrays.asList(
         ReportExecutorService.OUTPUT_FORMAT_TABLE, 
         ReportExecutorService.OUTPUT_FORMAT_METADATA,
         ReportExecutorService.OUTPUT_FORMAT_DATACOUNT,
         ReportExecutorService.OUTPUT_FORMAT_REPORTINFORMATION,
         SaikuModule.OUTPUT_FORMAT_CHART_HTML
   );
   @Inject
   public LargeReportExecutionVetoer(
         TableReportUtils tableReportUtils,
         Provider<ConfigService> configServiceProvider
         ) {
      this.tableReportUtils = tableReportUtils;
      this.configServiceProvider = configServiceProvider;
   }
   
   @Override
   public void notifyOfReportExecution(Report report, ParameterSet parameterSet, User user, String outputFormat,
         ReportExecutionConfig[] configs) throws ReportExecutorException {
   }
   @Override
   public void notifyOfReportsSuccessfulExecution(CompiledReport compiledReport, Report report,
         ParameterSet parameterSet, User user, String outputFormat, ReportExecutionConfig[] configs) {
   }
   @Override
   public void notifyOfReportsUnsuccessfulExecution(ReportExecutorException e, Report report, ParameterSet parameterSet,
         User user, String outputFormat, ReportExecutionConfig[] configs) {
   }
   @Override
   public void doVetoReportExecution(Report report, ParameterSet parameterSet, User user, String outputFormat,
         ReportExecutionConfig[] configs) throws ReportExecutorException {
      if (!allowedFormats.contains(outputFormat) && report instanceof TableReport) {
         ReportProperty doVeto = report.getReportProperty(AvailableReportProperties.PROPERTY_VETO_LARGE_REPORT_EXPORT.getValue());
         String doVetoValue = doVeto != null ? ((ReportStringProperty) doVeto).getStrValue() : "auto";
         if (!doVetoValue.equals("auto"))
            return;
         TableReport tableReport = (TableReport) report;
         if (tableReport.isCubeFlag())
            return;
         TableReportInformation reportInformation = tableReportUtils.getReportInformation(tableReport, null);
         if (reportInformation == null)
            return;
         int dataCount = reportInformation.getDataCount();
      
         Configuration config = configServiceProvider.get().getConfigFailsafe(ReportServerService.CONFIG_FILE);
         int maxRecords = config.getInt("export.maximumrecords", DEFAULT_EXPORT_MAX_SIZE);
         
         if (dataCount > maxRecords) {
            throw new ReportExecutorException("Your report is too large to be exported, please add filters "
                  + "or similar to limit its output. "
                  + "Current number of rows: " + dataCount 
                  + ". Maximum number of rows: " + maxRecords);
         }
      }
   }
}