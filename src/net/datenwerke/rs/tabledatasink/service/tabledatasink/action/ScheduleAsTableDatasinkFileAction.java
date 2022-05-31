package net.datenwerke.rs.tabledatasink.service.tabledatasink.action;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.rs.tabledatasink.service.tabledatasink.definitions.TableDatasink;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;
import net.datenwerke.rs.utils.juel.SimpleJuel;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractAction;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.exceptions.ActionExecutionException;

@Entity
@Table(name = "SCHED_ACTION_AS_TABLE_DATASINK_FILE")
@Inheritance(strategy = InheritanceType.JOINED)
public class ScheduleAsTableDatasinkFileAction extends AbstractAction {

   @Transient
   @Inject
   private Provider<SimpleJuel> simpleJuelProvider;

   @Transient
   @Inject
   private DatasinkService datasinkService;

   @EnclosedEntity
   @OneToOne
   private TableDatasink tableDatasink;

   @Transient
   private Report report;

   private String name;

   @Override
   public void execute(AbstractJob job) throws ActionExecutionException {
      if (!(job instanceof ReportExecuteJob))
         throw new ActionExecutionException("No idea what job that is");

      ReportExecuteJob rJob = (ReportExecuteJob) job;

      /* did everything go as planned ? */
      if (null == rJob.getExecutedReport())
         return;

      if (!datasinkService.isEnabled(tableDatasink.getDatasinkService())
            || !datasinkService.isSchedulingEnabled(tableDatasink.getDatasinkService()))
         throw new ActionExecutionException("Table datasink scheduling is disabled");

      report = rJob.getReport();

      sendViaTableDatasink(rJob);

      if (null == name || name.trim().isEmpty())
         throw new ActionExecutionException("name is empty");

      if (null == tableDatasink)
         throw new ActionExecutionException("Table datasink is empty");

   }

   private void sendViaTableDatasink(ReportExecuteJob rJob) throws ActionExecutionException {
      try {
         datasinkService.exportIntoDatasink(rJob.getExecutedReport().getReport(), rJob.getExecutor(), tableDatasink,
               null);
      } catch (Exception e) {
         throw new ActionExecutionException("report could not be sent to table datasink", e);
      }
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Report getReport() {
      return report;
   }

   public TableDatasink getTableDatasink() {
      return tableDatasink;
   }

   public void setTableDatasink(TableDatasink tableDatasink) {
      this.tableDatasink = tableDatasink;
   }

}
