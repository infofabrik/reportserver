package net.datenwerke.rs.emaildatasink.service.emaildatasink.action;

import static net.datenwerke.rs.scheduler.service.scheduler.RsSchedulerModule.PROPERTY_EMAIL_ATTACHEMENT_NAME;
import static net.datenwerke.rs.scheduler.service.scheduler.RsSchedulerModule.PROPERTY_EMAIL_HTML;
import static net.datenwerke.rs.scheduler.service.scheduler.RsSchedulerModule.PROPERTY_EMAIL_SUBJECT;
import static net.datenwerke.rs.scheduler.service.scheduler.RsSchedulerModule.PROPERTY_EMAIL_TEXT;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.configuration2.Configuration;
import org.hibernate.annotations.Type;

import com.google.inject.Inject;

import net.datenwerke.gf.service.localization.RemoteMessageService;
import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.configs.DatasinkEmailConfig2;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions.EmailDatasink;
import net.datenwerke.rs.scheduler.service.scheduler.annotations.SchedulerModuleProperties;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;
import net.datenwerke.rs.utils.juel.SimpleJuel;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.scheduler.service.scheduler.SchedulerHelperService;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractAction;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.exceptions.ActionExecutionException;
import net.datenwerke.security.service.usermanager.entities.User;

@Entity
@Table(name = "SCHED_ACTION_AS_EMAIL_FILE")
@Inheritance(strategy = InheritanceType.JOINED)
public class ScheduleAsEmailFileAction extends AbstractAction {

   private static final String PROPERTY_SUBJECT = "subject";
   private static final String PROPERTY_MESSAGE = "message";

   @Transient
   @Inject
   private SchedulerHelperService schedulerHelperService;

   @Transient
   @Inject
   private DatasinkService datasinkService;
   
   @Transient
   @Inject
   private RemoteMessageService remoteMessageService;
   
   @Transient
   @Inject
   @SchedulerModuleProperties
   private Configuration config;

   @EnclosedEntity
   @OneToOne(fetch = FetchType.LAZY)
   private EmailDatasink emailDatasink;

   private boolean compressed;

   public boolean isCompressed() {
      return compressed;
   }

   public void setCompressed(boolean compressed) {
      this.compressed = compressed;
   }

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

      if (!datasinkService.isEnabled(emailDatasink.getDatasinkService())
            || !datasinkService.isSchedulingEnabled(emailDatasink.getDatasinkService()))
         throw new ActionExecutionException("email scheduling is disabled");

      report = rJob.getReport();

      SimpleJuel juel = schedulerHelperService.getConfiguredJuel(rJob);
      juel.addReplacement(PROPERTY_SUBJECT, getSubject());
      juel.addReplacement(PROPERTY_MESSAGE, getMessage());
      /* enable msgs replacement */
      String currentLanguage = LocalizationServiceImpl.getLocale().getLanguage();
      juel.addReplacement("msgs", remoteMessageService.getMessages(currentLanguage));

      filename = null == name ? "" : juel.parse(name);
      sendViaEmailDatasink(rJob, juel, filename);

      if (null == name || name.trim().isEmpty())
         throw new ActionExecutionException("name is empty");

      if (null == emailDatasink)
         throw new ActionExecutionException("email datasink is empty");

   }

   private void sendViaEmailDatasink(final ReportExecuteJob rJob, final SimpleJuel juel, final String filename)
         throws ActionExecutionException {      
      datasinkService.exportIntoDatasink(rJob, compressed, emailDatasink, new DatasinkEmailConfig2() {
         
         /* default values */
         boolean isHtml     = false;    
         String sSubject    = subject; 
         String sMessage    = message;  
         String sFilename   = filename;
         
         boolean isInit = false;
         
         private void init() {
            /* only init once */
            if (isInit)
               return;
            /* values from config */
            isHtml = config.getBoolean(PROPERTY_EMAIL_HTML, isHtml);
            sSubject = config.getString(PROPERTY_EMAIL_SUBJECT, sSubject);
            sMessage = config.getString(PROPERTY_EMAIL_TEXT, sMessage);
            sFilename = config.getString(PROPERTY_EMAIL_ATTACHEMENT_NAME, sFilename);
            isInit = true;
         }
         
         
         @Override
         public String getFilename() {
            init();
            return datasinkService.getFilenameForDatasink(rJob, compressed, juel.parse(sFilename));
         }

         @Override
         public boolean isSendSyncEmail() {
            return true;
         }

         @Override
         public String getSubject() {
            init();
            return juel.parse(sSubject);
         }

         @Override
         public List<User> getRecipients() {
            return rJob.getRecipients();
         }

         @Override
         public String getBody() {
            init();
            return juel.parse(sMessage);
         }

         @Override
         public boolean isHtml() {
            return isHtml;
         }
      });
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