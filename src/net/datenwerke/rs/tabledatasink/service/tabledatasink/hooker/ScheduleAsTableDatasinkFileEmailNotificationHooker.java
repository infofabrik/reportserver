package net.datenwerke.rs.tabledatasink.service.tabledatasink.hooker;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.mail.Address;
import javax.mail.MessagingException;

import org.apache.commons.configuration2.Configuration;

import net.datenwerke.gf.service.localization.RemoteMessageService;
import net.datenwerke.rs.tabledatasink.service.tabledatasink.action.ScheduleAsTableDatasinkFileAction;
import net.datenwerke.rs.base.service.parameterreplacements.provider.ReportForJuel;
import net.datenwerke.rs.base.service.parameterreplacements.provider.ReportJobForJuel;
import net.datenwerke.rs.base.service.parameterreplacements.provider.UserForJuel;
import net.datenwerke.rs.base.service.parameterreplacements.provider.UserListForJuelPrinter;
import net.datenwerke.rs.core.service.mail.MailService;
import net.datenwerke.rs.core.service.mail.MailTemplate;
import net.datenwerke.rs.core.service.mail.SimpleMail;
import net.datenwerke.rs.scheduler.client.scheduler.locale.SchedulerMessages;
import net.datenwerke.rs.scheduler.service.scheduler.annotations.SchedulerModuleProperties;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractAction;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.entities.history.ExecutionLogEntry;
import net.datenwerke.scheduler.service.scheduler.hooks.adapter.SchedulerExecutionHookAdapter;
import net.datenwerke.security.service.usermanager.entities.User;

public class ScheduleAsTableDatasinkFileEmailNotificationHooker extends SchedulerExecutionHookAdapter {

   private static final String PROPERTY_REPORT = "report";
   private static final String PROPERTY_JOB = "job";
   private static final String PROPERTY_EXECUTOR = "executor";
   private static final String PROPERTY_SCHEDULED_BY = "scheduledBy";
   private static final String PROPERTY_OWNERS = "owners";

   public static final String PROPERTY_EXCEPTION = "exception";
   public static final String PROPERTY_EXCEPTIONST = "trace";

   public static final String PROPERTY_TABLE_DATASINK_NOTIFICATION_SUBJECT_SUCCESS = "scheduler.fileactionTableDatasink.subject";
   public static final String PROPERTY_TABLE_DATASINK_NOTIFICATION_TEXT_SUCCESS = "scheduler.fileactionTableDatasink.text";

   private static final String PROPERTY_TABLE_DATASINK_NOTIFICATION_DISABLED = "scheduler.fileactionTableDatasink[@disabled]";
   private static final String PROPERTY_TABLE_DATASINK_NOTIFICATION_HTML = "scheduler.fileactionTableDatasink[@html]";

   private Configuration config;
   private MailService mailService;

   private RemoteMessageService remoteMessageService;

   @Inject
   public ScheduleAsTableDatasinkFileEmailNotificationHooker(@SchedulerModuleProperties Configuration config,
         MailService mailService, RemoteMessageService remoteMessageService) {

      this.config = config;
      this.mailService = mailService;
      this.remoteMessageService = remoteMessageService;
   }

   @Override
   public void actionExecutionEndedSuccessfully(AbstractJob abstractJob, AbstractAction abstractAction,
         ExecutionLogEntry logEntry) {
      if (!(abstractJob instanceof ReportExecuteJob))
         return;

      if (!(abstractAction instanceof ScheduleAsTableDatasinkFileAction))
         return;

      ReportExecuteJob job = (ReportExecuteJob) abstractJob;

      try {
         sendmail((ScheduleAsTableDatasinkFileAction) abstractAction, job, null);
      } catch (MessagingException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   private void sendmail(ScheduleAsTableDatasinkFileAction action, ReportExecuteJob job, Exception e)
         throws MessagingException {
      if (config.getBoolean(PROPERTY_TABLE_DATASINK_NOTIFICATION_DISABLED, false))
         return;

      List<User> recipients = ((ReportExecuteJob) job).getRecipients();
      if (null == recipients || recipients.isEmpty())
         return;

      Map<String, Object> datamap = new HashMap<String, Object>();

      datamap.put(PROPERTY_REPORT, new ReportForJuel(action.getReport()));
      datamap.put(PROPERTY_JOB, new ReportJobForJuel(job));
      UserForJuel executor = UserForJuel.createInstance(job.getExecutor());
      datamap.put("user", executor);
      datamap.put(PROPERTY_EXECUTOR, executor);
      datamap.put(PROPERTY_SCHEDULED_BY, UserForJuel.createInstance(job.getScheduledBy()));

      datamap.put(PROPERTY_OWNERS, UserListForJuelPrinter.createInstance(new ArrayList<>(job.getOwners()),
            config.getBoolean(PROPERTY_TABLE_DATASINK_NOTIFICATION_HTML, false)));

      if (null != e) {
         datamap.put(PROPERTY_EXCEPTION, e);
         ByteArrayOutputStream bos = new ByteArrayOutputStream();
         PrintWriter pw = new PrintWriter(bos);
         e.printStackTrace(pw);
         pw.close();
         datamap.put(PROPERTY_EXCEPTIONST, bos.toString());
      }

      String currentLanguage = LocalizationServiceImpl.getLocale().getLanguage();

      HashMap<String, HashMap<String, String>> msgs = remoteMessageService.getMessages(currentLanguage);
      datamap.put("msgs", msgs);

      String subjectTemplate = config.getString(PROPERTY_TABLE_DATASINK_NOTIFICATION_SUBJECT_SUCCESS,
            msgs.get(SchedulerMessages.class.getCanonicalName()).get("fileactionTableDatasinkMsgSubject"));
      String messageTemplate = config.getString(PROPERTY_TABLE_DATASINK_NOTIFICATION_TEXT_SUCCESS,
            msgs.get(SchedulerMessages.class.getCanonicalName()).get("fileactionTableDatasinkMsgText"));

      MailTemplate mailTemplate = new MailTemplate();
      mailTemplate.setHtml(config.getBoolean(PROPERTY_TABLE_DATASINK_NOTIFICATION_HTML, false));
      mailTemplate.setSubjectTemplate(subjectTemplate);
      mailTemplate.setMessageTemplate(messageTemplate);
      mailTemplate.setDataMap(datamap);

      SimpleMail simpleMail = mailService.newTemplateMail(mailTemplate);
      simpleMail.setRecipients(javax.mail.Message.RecipientType.BCC,
            mailService.getEmailList(recipients).stream().toArray(Address[]::new));

      mailService.sendMail(simpleMail);
   }

}
