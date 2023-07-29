package net.datenwerke.rs.incubator.service.outputformatauth.hookers;
import java.util.Arrays;
import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.rs.base.service.reportengines.table.TableReportUtils;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.core.service.reportmanager.hooks.ReportExecutionNotificationHook;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.saiku.service.saiku.SaikuModule;
import net.datenwerke.security.service.usermanager.entities.User;
public class LargeReportExecutionVetoer implements ReportExecutionNotificationHook {
   
   private final TableReportUtils tableReportUtils;
   private static final int EXPORT_MAX_SIZE = 100000;
   
   /* always allow certain formats */
   private final List<String> allowedFormats = Arrays.asList(
         ReportExecutorService.OUTPUT_FORMAT_TABLE, 
         ReportExecutorService.OUTPUT_FORMAT_METADATA,
         ReportExecutorService.OUTPUT_FORMAT_DATACOUNT,
         SaikuModule.OUTPUT_FORMAT_CHART_HTML
   );
   @Inject
   public LargeReportExecutionVetoer(TableReportUtils tableReportUtils) {
      this.tableReportUtils = tableReportUtils;
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
         TableReport tableReport = (TableReport) report;
         int dataCount = tableReportUtils.getReportInformation(tableReport, null).getDataCount();
      
         if (dataCount > EXPORT_MAX_SIZE) {
            throw new ReportExecutorException("Your report is too large to be exported, please add filters "
                  + "or similar to limit its output. \r\n"
                  + "Current number of rows: " + dataCount 
                  + ". Maximum number of rows: " + EXPORT_MAX_SIZE + "\r\n");
         }
      }
   }
}