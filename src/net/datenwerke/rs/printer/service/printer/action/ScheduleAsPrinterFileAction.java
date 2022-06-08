package net.datenwerke.rs.printer.service.printer.action;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.inject.Inject;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkConfiguration;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.printer.service.printer.definitions.PrinterDatasink;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractAction;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.exceptions.ActionExecutionException;

@Entity
@Table(name = "SCHED_ACTION_AS_PRINTER_FILE")
@Inheritance(strategy = InheritanceType.JOINED)
public class ScheduleAsPrinterFileAction extends AbstractAction {

   @Transient
   @Inject
   private DatasinkService datasinkService;

   @EnclosedEntity
   @OneToOne
   private PrinterDatasink printerDatasink;

   @Transient
   private Report report;

   @Override
   public void execute(AbstractJob job) throws ActionExecutionException {
      if (!(job instanceof ReportExecuteJob))
         throw new ActionExecutionException("No idea what job that is");

      ReportExecuteJob rJob = (ReportExecuteJob) job;

      /* did everything go as planned ? */
      if (null == rJob.getExecutedReport())
         return;

      if (!datasinkService.isEnabled(printerDatasink.getDatasinkService())
            || !datasinkService.isSchedulingEnabled(printerDatasink.getDatasinkService()))
         throw new ActionExecutionException("Printer scheduling is disabled");

      report = rJob.getReport();

      sendViaPrinterDatasink(rJob);

      if (null == printerDatasink)
         throw new ActionExecutionException("Printer datasink is empty");
   }

   private void sendViaPrinterDatasink(ReportExecuteJob rJob) throws ActionExecutionException {
      try {
         datasinkService.exportIntoDatasink(rJob.getExecutedReport().getReport(), rJob.getExecutor(), printerDatasink,
               new DatasinkConfiguration() {
               });
      } catch (Exception e) {
         throw new ActionExecutionException("report could not be sent to Printer datasink", e);
      }
   }

   public Report getReport() {
      return report;
   }

   public PrinterDatasink getPrinterDatasink() {
      return printerDatasink;
   }

   public void setPrinterDatasink(PrinterDatasink printerDatasink) {
      this.printerDatasink = printerDatasink;
   }

}
