package net.datenwerke.rs.ftp.service.ftp.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.inject.Inject;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkFilenameFolderConfig;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.ftp.service.ftp.definitions.FtpsDatasink;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;
import net.datenwerke.rs.utils.juel.SimpleJuel;
import net.datenwerke.scheduler.service.scheduler.SchedulerHelperService;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractAction;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.exceptions.ActionExecutionException;

@Entity
@Table(name = "SCHED_ACTION_AS_FTPS_FILE")
@Inheritance(strategy = InheritanceType.JOINED)
public class ScheduleAsFtpsFileAction extends AbstractAction {

   @Transient
   @Inject
   private SchedulerHelperService schedulerHelperService;

   @Transient
   @Inject
   private DatasinkService datasinkService;

   @EnclosedEntity
   @OneToOne(fetch = FetchType.LAZY)
   private FtpsDatasink ftpsDatasink;

   @Transient
   private Report report;

   @Transient
   private String filename;

   private String name;
   private String folder;

   private boolean compressed;

   public boolean isCompressed() {
      return compressed;
   }

   public void setCompressed(boolean compressed) {
      this.compressed = compressed;
   }

   @Override
   public void execute(AbstractJob job) throws ActionExecutionException {
      if (!(job instanceof ReportExecuteJob))
         throw new ActionExecutionException("No idea what job that is");

      ReportExecuteJob rJob = (ReportExecuteJob) job;

      /* did everything go as planned ? */
      if (null == rJob.getExecutedReport())
         return;

      if (!datasinkService.isEnabled(ftpsDatasink.getDatasinkService()) 
            || !datasinkService.isSchedulingEnabled(ftpsDatasink.getDatasinkService()))
         throw new ActionExecutionException("ftps scheduling is disabled");

      report = rJob.getReport();

      SimpleJuel juel = schedulerHelperService.getConfiguredJuel(rJob);
      juel.addReplacement("now", new SimpleDateFormat("yyyyMMddhhmm").format(Calendar.getInstance().getTime()));
      filename = null == name ? "" : juel.parse(name);

      sendViaFTPSDatasink(rJob, filename);

      if (null == name || name.trim().isEmpty())
         throw new ActionExecutionException("name is empty");

      if (null == ftpsDatasink)
         throw new ActionExecutionException("ftps datasink is empty");

      if (null == folder || folder.trim().isEmpty())
         throw new ActionExecutionException("folder is empty");

   }

   private void sendViaFTPSDatasink(final ReportExecuteJob rJob, final String filename)
         throws ActionExecutionException {
      datasinkService.exportIntoDatasink(rJob, compressed, ftpsDatasink, new DatasinkFilenameFolderConfig() {

         @Override
         public String getFilename() {
            return datasinkService.getFilenameForDatasink(rJob, compressed, filename);
         }

         @Override
         public String getFolder() {
            return folder;
         }
      });
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getFilename() {
      return filename;
   }

   public String getFolder() {
      return folder;
   }

   public void setFolder(String folder) {
      this.folder = folder;
   }

   public Report getReport() {
      return report;
   }

   public FtpsDatasink getFtpsDatasink() {
      return ftpsDatasink;
   }

   public void setFtpsDatasink(FtpsDatasink ftpsDatasink) {
      this.ftpsDatasink = ftpsDatasink;
   }

}