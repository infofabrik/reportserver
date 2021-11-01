package net.datenwerke.rs.amazons3.service.amazons3.action;

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
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.amazons3.service.amazons3.AmazonS3Service;
import net.datenwerke.rs.amazons3.service.amazons3.definitions.AmazonS3Datasink;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;
import net.datenwerke.rs.utils.juel.SimpleJuel;
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

   @Override
   public void execute(AbstractJob job) throws ActionExecutionException {
      if (!(job instanceof ReportExecuteJob))
         throw new ActionExecutionException("No idea what job that is");

      ReportExecuteJob rJob = (ReportExecuteJob) job;

      /* did everything go as planned ? */
      if (null == rJob.getExecutedReport())
         return;

      if (!amazonS3Service.isAmazonS3Enabled() || !amazonS3Service.isAmazonS3SchedulingEnabled())
         throw new ActionExecutionException("AmazonS3 scheduling is disabled");

      CompiledReport compiledReport = rJob.getExecutedReport();
      report = rJob.getReport();

      SimpleJuel juel = simpleJuelProvider.get();
      juel.addReplacement("now", new SimpleDateFormat("yyyyMMddhhmm").format(Calendar.getInstance().getTime()));
      filename = null == name ? "" : juel.parse(name);

      filename += "." + compiledReport.getFileExtension();

      if (null == name || name.trim().isEmpty())
         throw new ActionExecutionException("name is empty");

      if (null == amazonS3Datasink)
         throw new ActionExecutionException("AmazonS3 datasink is empty");

      if (null == folder || folder.trim().isEmpty())
         throw new ActionExecutionException("folder is empty");

      try {
         amazonS3Service.exportIntoAmazonS3(compiledReport.getReport(), amazonS3Datasink, filename, folder);
      } catch (Exception e) {
         throw new ActionExecutionException("report could not be sent to amazonS3", e);
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
