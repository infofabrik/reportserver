package net.datenwerke.rs.amazons3.service.amazons3.action;

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

import net.datenwerke.rs.amazons3.service.amazons3.AmazonS3Service;
import net.datenwerke.rs.amazons3.service.amazons3.definitions.AmazonS3Datasink;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;
import net.datenwerke.rs.utils.juel.SimpleJuel;
import net.datenwerke.rs.utils.zip.ZipUtilsService;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractAction;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.exceptions.ActionExecutionException;

@Entity
@Table(name = "SCHED_ACTION_AS_AMAZONS3_FILE")
@Inheritance(strategy = InheritanceType.JOINED)
public class ScheduleAsAmazonS3FileAction extends AbstractAction {

   @Transient
   @Inject
   private Provider<SimpleJuel> simpleJuelProvider;
   @Transient
   @Inject
   private AmazonS3Service amazonS3Service;

   @EnclosedEntity
   @OneToOne
   private AmazonS3Datasink amazonS3Datasink;

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

      if (!amazonS3Service.isEnabled() || !amazonS3Service.isSchedulingEnabled())
         throw new ActionExecutionException("AmazonS3 scheduling is disabled");

      report = rJob.getReport();

      SimpleJuel juel = simpleJuelProvider.get();
      juel.addReplacement("now", new SimpleDateFormat("yyyyMMddhhmm").format(Calendar.getInstance().getTime()));
      filename = null == name ? "" : juel.parse(name);
      
      sendViaAmazonS3Datasink(rJob, filename);

      if (null == name || name.trim().isEmpty())
         throw new ActionExecutionException("name is empty");

      if (null == amazonS3Datasink)
         throw new ActionExecutionException("AmazonS3 datasink is empty");

      if (null == folder || folder.trim().isEmpty())
         throw new ActionExecutionException("folder is empty");
      
   }
   
   private void sendViaAmazonS3Datasink(ReportExecuteJob rJob, String filename) throws ActionExecutionException {
      try {
         if (compressed) {
            String filenameScheduling = filename + ".zip";
            try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
               Object reportObj = rJob.getExecutedReport().getReport();
               String reportFileExtension = rJob.getExecutedReport().getFileExtension();
               zipUtilsService.createZip(
                     zipUtilsService.cleanFilename(rJob.getReport().getName() + "." + reportFileExtension), reportObj,
                     os);
               amazonS3Service.exportIntoDatasink(os.toByteArray(), amazonS3Datasink, filenameScheduling, folder);
            }
         } else {
            String filenameScheduling = filename + "." + rJob.getExecutedReport().getFileExtension();
            amazonS3Service.exportIntoDatasink(rJob.getExecutedReport().getReport(), amazonS3Datasink,
                  filenameScheduling, folder);
         }
      } catch (Exception e) {
         throw new ActionExecutionException("report could not be sent to Amazon S3", e);
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

   public AmazonS3Datasink getAmazonS3Datasink() {
      return amazonS3Datasink;
   }

   public void setAmazonS3Datasink(AmazonS3Datasink amazonS3Datasink) {
      this.amazonS3Datasink = amazonS3Datasink;
   }

}