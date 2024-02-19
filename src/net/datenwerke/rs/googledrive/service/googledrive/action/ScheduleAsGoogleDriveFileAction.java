package net.datenwerke.rs.googledrive.service.googledrive.action;

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
import net.datenwerke.rs.googledrive.service.googledrive.definitions.GoogleDriveDatasink;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;
import net.datenwerke.rs.utils.juel.SimpleJuel;
import net.datenwerke.scheduler.service.scheduler.SchedulerHelperService;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractAction;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.exceptions.ActionExecutionException;

@Entity
@Table(name = "SCHED_ACTION_AS_GOOGLEDRIVE_FILE")
@Inheritance(strategy = InheritanceType.JOINED)
public class ScheduleAsGoogleDriveFileAction extends AbstractAction {

   @Transient
   @Inject
   private SchedulerHelperService schedulerHelperService;

   @Transient
   @Inject
   private DatasinkService datasinkService;

   @EnclosedEntity
   @OneToOne(fetch = FetchType.LAZY)
   private GoogleDriveDatasink googleDriveDatasink;

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

      if (!datasinkService.isEnabled(googleDriveDatasink.getDatasinkService()) 
            || !datasinkService.isSchedulingEnabled(googleDriveDatasink.getDatasinkService()))
         throw new ActionExecutionException("Google Drive scheduling is disabled");

      report = rJob.getReport();

      SimpleJuel juel = schedulerHelperService.getConfiguredJuel(rJob);
      filename = null == name ? "" : juel.parse(name);

      sendViaGoogleDriveDatasink(rJob, filename);

      if (null == name || name.trim().isEmpty())
         throw new ActionExecutionException("name is empty");

      if (null == googleDriveDatasink)
         throw new ActionExecutionException("Google Drive datasink is empty");

      if (null == folder || folder.trim().isEmpty())
         throw new ActionExecutionException("folder is empty");

   }

   private void sendViaGoogleDriveDatasink(final ReportExecuteJob rJob, final String filename)
         throws ActionExecutionException {
      datasinkService.exportIntoDatasink(rJob, compressed, googleDriveDatasink, new DatasinkFilenameFolderConfig() {

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

   public GoogleDriveDatasink getGoogleDriveDatasink() {
      return googleDriveDatasink;
   }

   public void setGoogleDriveDatasink(GoogleDriveDatasink googleDriveDatasink) {
      this.googleDriveDatasink = googleDriveDatasink;
   }

}
