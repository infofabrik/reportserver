package net.datenwerke.rs.onedrive.service.onedrive.action;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkFilenameFolderConfig;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.onedrive.service.onedrive.OneDriveService;
import net.datenwerke.rs.onedrive.service.onedrive.definitions.OneDriveDatasink;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;
import net.datenwerke.rs.utils.juel.SimpleJuel;
import net.datenwerke.rs.utils.zip.ZipUtilsService;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractAction;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.exceptions.ActionExecutionException;

@Entity
@Table(name = "SCHED_ACTION_AS_ONEDRIVE_FILE")
@Inheritance(strategy = InheritanceType.JOINED)
public class ScheduleAsOneDriveFileAction extends AbstractAction {

   @Transient
   @Inject
   private Provider<SimpleJuel> simpleJuelProvider;

   @Transient
   @Inject
   private OneDriveService oneDriveService;

   @Transient
   @Inject
   private DatasinkService datasinkService;

   @EnclosedEntity
   @OneToOne
   private OneDriveDatasink oneDriveDatasink;

   @Transient
   private Report report;

   @Transient
   private String filename;

   private String name;
   private String folder;

   private Boolean compressed = false;

   public Boolean isCompressed() {
      return compressed;
   }

   public void setCompressed(Boolean compressed) {
      this.compressed = compressed;
   }

   @Transient
   @Inject
   private ZipUtilsService zipUtilsService;

   @Override
   public void execute(AbstractJob job) throws ActionExecutionException {
      if (!(job instanceof ReportExecuteJob))
         throw new ActionExecutionException("No idea what job that is");

      ReportExecuteJob rJob = (ReportExecuteJob) job;

      /* did everything go as planned ? */
      if (null == rJob.getExecutedReport())
         return;

      if (!datasinkService.isEnabled(oneDriveService) || !datasinkService.isSchedulingEnabled(oneDriveService))
         throw new ActionExecutionException("OneDrive scheduling is disabled");

      report = rJob.getReport();

      SimpleJuel juel = simpleJuelProvider.get();
      juel.addReplacement("now", new SimpleDateFormat("yyyyMMddhhmm").format(Calendar.getInstance().getTime()));
      filename = null == name ? "" : juel.parse(name);

      sendViaOneDriveDatasink(rJob, filename);

      if (null == name || name.trim().isEmpty())
         throw new ActionExecutionException("name is empty");

      if (null == oneDriveDatasink)
         throw new ActionExecutionException("OneDrive datasink is empty");

      if (null == folder || folder.trim().isEmpty())
         throw new ActionExecutionException("folder is empty");

   }

   private void sendViaOneDriveDatasink(ReportExecuteJob rJob, String filename) throws ActionExecutionException {
      try {
         if (compressed) {
            String filenameScheduling = filename + ".zip";
            try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
               Object reportObj = rJob.getExecutedReport().getReport();
               String reportFileExtension = rJob.getExecutedReport().getFileExtension();
               zipUtilsService.createZip(
                     zipUtilsService.cleanFilename(rJob.getReport().getName() + "." + reportFileExtension), reportObj,
                     os);
               datasinkService.exportIntoDatasink(os.toByteArray(), oneDriveDatasink, oneDriveService,
                     new DatasinkFilenameFolderConfig() {

                        @Override
                        public String getFilename() {
                           return filenameScheduling;
                        }

                        @Override
                        public String getFolder() {
                           return folder;
                        }

                     });
            }
         } else {
            String filenameScheduling = filename + "." + rJob.getExecutedReport().getFileExtension();
            datasinkService.exportIntoDatasink(rJob.getExecutedReport().getReport(), oneDriveDatasink, oneDriveService,
                  new DatasinkFilenameFolderConfig() {

                     @Override
                     public String getFilename() {
                        return filenameScheduling;
                     }

                     @Override
                     public String getFolder() {
                        return folder;
                     }

                  });
         }
      } catch (Exception e) {
         throw new ActionExecutionException("report could not be sent to OneDrive - SharePoint (O365)", e);
      }
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

   public OneDriveDatasink getOneDriveDatasink() {
      return oneDriveDatasink;
   }

   public void setOneDriveDatasink(OneDriveDatasink oneDriveDatasink) {
      this.oneDriveDatasink = oneDriveDatasink;
   }

}