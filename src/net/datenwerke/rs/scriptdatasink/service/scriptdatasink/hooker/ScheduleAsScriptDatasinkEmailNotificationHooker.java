package net.datenwerke.rs.scriptdatasink.service.scriptdatasink.hooker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.mail.Address;
import javax.mail.MessagingException;

import org.apache.commons.configuration2.Configuration;

import com.google.inject.Provider;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule;
import net.datenwerke.rs.core.service.mail.MailService;
import net.datenwerke.rs.core.service.mail.MailTemplate;
import net.datenwerke.rs.core.service.mail.SimpleMail;
import net.datenwerke.rs.scheduler.service.scheduler.annotations.SchedulerModuleProperties;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.rs.scriptdatasink.service.scriptdatasink.action.ScheduleAsScriptDatasinkFileAction;
import net.datenwerke.scheduler.service.scheduler.SchedulerHelperService;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractAction;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.entities.history.ExecutionLogEntry;
import net.datenwerke.scheduler.service.scheduler.hooks.adapter.SchedulerExecutionHookAdapter;
import net.datenwerke.security.service.usermanager.entities.User;

public class ScheduleAsScriptDatasinkEmailNotificationHooker extends SchedulerExecutionHookAdapter {

   private static final Map<String, String> PROPERTIES = new HashMap<String, String>() {
      private static final long serialVersionUID = -4530850796051224912L;
      {
         put(DatasinkModule.PROPERTY_SUBJECT, "scheduler.fileactionScriptDatasink.subject");
         put(DatasinkModule.PROPERTY_TEXT, "scheduler.fileactionScriptDatasink.text");
         put(DatasinkModule.PROPERTY_DISABLED, "scheduler.fileactionScriptDatasink[@disabled]");
         put(DatasinkModule.PROPERTY_HTML, "scheduler.fileactionScriptDatasink[@html]");
         put(DatasinkModule.PROPERTY_TRANSLATIONS_SUBJECT, "fileactionScriptDatasinkMsgSubject");
         put(DatasinkModule.PROPERTY_TRANSLATIONS_TEXT, "fileactionScriptDatasinkMsgText");
      }
   };
   
   private final Configuration config;
   private final MailService mailService;
   
   private final Provider<SchedulerHelperService> schedulerHelperServiceProvider;

   @Inject
   public ScheduleAsScriptDatasinkEmailNotificationHooker(
         @SchedulerModuleProperties Configuration config,
         MailService mailService, 
         Provider<SchedulerHelperService> schedulerHelperServiceProvider
         ) {

      this.config = config;
      this.mailService = mailService;
      this.schedulerHelperServiceProvider = schedulerHelperServiceProvider;
   }

   @Override
   public void actionExecutionEndedSuccessfully(AbstractJob abstractJob, AbstractAction abstractAction,
         ExecutionLogEntry logEntry) {
      if (!(abstractJob instanceof ReportExecuteJob))
         return;

      if (!(abstractAction instanceof ScheduleAsScriptDatasinkFileAction))
         return;

      ReportExecuteJob job = (ReportExecuteJob) abstractJob;

      try {
         sendmail((ScheduleAsScriptDatasinkFileAction) abstractAction, job, null);
      } catch (MessagingException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   private void sendmail(ScheduleAsScriptDatasinkFileAction action, ReportExecuteJob job, Exception e)
         throws MessagingException {
      if (config.getBoolean(PROPERTIES.get(DatasinkModule.PROPERTY_DISABLED), false))
         return;

      List<User> recipients = job.getRecipients();
      if (null == recipients || recipients.isEmpty())
         return;

      MailTemplate mailTemplate = schedulerHelperServiceProvider.get().getMailTemplate(job, action, PROPERTIES, e);

      SimpleMail simpleMail = mailService.newTemplateMail(mailTemplate);
      simpleMail.setRecipients(javax.mail.Message.RecipientType.BCC,
            mailService.getEmailList(recipients).stream().toArray(Address[]::new));

      mailService.sendMail(simpleMail);
   }

}