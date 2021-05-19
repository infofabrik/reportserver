package net.datenwerke.rs.emaildatasink.service.emaildatasink.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.EmailDatasinkService;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions.EmailDatasink;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;
import net.datenwerke.rs.utils.juel.SimpleJuel;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractAction;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.exceptions.ActionExecutionException;

@Entity
@Table(name = "SCHED_ACTION_AS_EMAIL_FILE")
@Inheritance(strategy = InheritanceType.JOINED)
public class ScheduleAsEmailFileAction extends AbstractAction {

   private static final String PROPERTY_SUBJECT = "subject";
   private static final String PROPERTY_MESSAGE = "message";

   @Transient
   @Inject
   private Provider<SimpleJuel> simpleJuelProvider;
   @Transient
   @Inject
   private EmailDatasinkService emailDatasinkService;

   @EnclosedEntity
   @OneToOne
   private EmailDatasink emailDatasink;

   @Transient
   private Report report;

   @Transient
   private String filename;

   private String name;

   private String subject = "";

   @Lob
   @Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
   private String message = "";

   @Override
   public void execute(AbstractJob job) throws ActionExecutionException {
      if (!(job instanceof ReportExecuteJob))
         throw new ActionExecutionException("No idea what job that is");

      ReportExecuteJob rJob = (ReportExecuteJob) job;

      /* did everything go as planned ? */
      if (null == rJob.getExecutedReport())
         return;

      if (!emailDatasinkService.isEmailEnabled() || !emailDatasinkService.isEmailSchedulingEnabled())
         throw new ActionExecutionException("email scheduling is disabled");

      CompiledReport compiledReport = rJob.getExecutedReport();
      report = rJob.getReport();

      SimpleJuel juel = simpleJuelProvider.get();
      juel.addReplacement("now", new SimpleDateFormat("yyyyMMddhhmm").format(Calendar.getInstance().getTime()));
      juel.addReplacement(PROPERTY_SUBJECT, getSubject());
      juel.addReplacement(PROPERTY_MESSAGE, getMessage());

      filename = null == name ? "" : juel.parse(name);

      filename += "." + compiledReport.getFileExtension();

      if (null == name || name.trim().isEmpty())
         throw new ActionExecutionException("name is empty");

      if (null == emailDatasink)
         throw new ActionExecutionException("email datasink is empty");

      try {
         emailDatasinkService.sendToEmailDatasink(compiledReport.getReport(), emailDatasink, juel.parse(subject),
               juel.parse(message), rJob.getRecipients(), filename, false);
      } catch (Exception e) {
         throw new ActionExecutionException("report could not be sent to Email Datasink", e);
      }
   }

   public String getMessage() {
      return message;
   }

   public String getSubject() {
      return subject;
   }

   public void setSubject(String subject) {
      this.subject = subject;
   }

   public void setMessage(String message) {
      this.message = message;
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

   public Report getReport() {
      return report;
   }

   public EmailDatasink getEmailDatasink() {
      return emailDatasink;
   }

   public void setEmailDatasink(EmailDatasink emailDatasink) {
      this.emailDatasink = emailDatasink;
   }

}