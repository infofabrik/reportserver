package net.datenwerke.rs.incubator.service.schedulernotification;

import static java.util.stream.Collectors.joining;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.persistence.EntityManager;

import org.apache.commons.configuration2.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Injector;
import com.google.inject.Provider;

import net.datenwerke.gf.service.localization.RemoteMessageService;
import net.datenwerke.rs.base.service.parameterreplacements.provider.ReportForJuel;
import net.datenwerke.rs.base.service.parameterreplacements.provider.ReportJobForJuel;
import net.datenwerke.rs.base.service.parameterreplacements.provider.UserForJuel;
import net.datenwerke.rs.base.service.parameterreplacements.provider.UserListForJuelPrinter;
import net.datenwerke.rs.core.service.mail.MailService;
import net.datenwerke.rs.core.service.mail.MailTemplate;
import net.datenwerke.rs.core.service.mail.SimpleMail;
import net.datenwerke.rs.scheduler.service.scheduler.annotations.SchedulerModuleProperties;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;
import net.datenwerke.rs.utils.exception.ExceptionServices;
import net.datenwerke.rs.utils.exception.shared.LambdaExceptionUtil;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractAction;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractTrigger;
import net.datenwerke.scheduler.service.scheduler.entities.history.ActionEntry;
import net.datenwerke.scheduler.service.scheduler.entities.history.ExecutionLogEntry;
import net.datenwerke.scheduler.service.scheduler.entities.history.JobEntry;
import net.datenwerke.scheduler.service.scheduler.helper.VetoActionExecution;
import net.datenwerke.scheduler.service.scheduler.helper.VetoJobExecution;
import net.datenwerke.scheduler.service.scheduler.hooks.SchedulerExecutionHook;
import net.datenwerke.scheduler.service.scheduler.hooks.SchedulerInfoHook;
import net.datenwerke.security.service.usermanager.entities.User;

public class SchedulerNotificationHooker implements SchedulerExecutionHook, SchedulerInfoHook {

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   public static final String PROPERTY_NOTIFICATION_SCHEDULED_SUBJECT = "scheduler.notification.scheduled.subject";
   public static final String PROPERTY_NOTIFICATION_SCHEDULED_TEXT = "scheduler.notification.scheduled.text";

   public static final String PROPERTY_NOTIFICATION_UNSCHEDULED_SUBJECT = "scheduler.notification.unscheduled.subject";
   public static final String PROPERTY_NOTIFICATION_UNSCHEDULED_TEXT = "scheduler.notification.unscheduled.text";

   public static final String PROPERTY_NOTIFICATION_FAILURE_SUBJECT = "scheduler.notification.failed.subject";
   public static final String PROPERTY_NOTIFICATION_FAILURE_TEXT = "scheduler.notification.failed.text";

   private static final String PROPERTY_NOTIFICATION_DISABLED = "scheduler.notification[@disabled]";
   private static final String PROPERTY_NOTIFICATION_HTML = "scheduler.notification[@html]";

   private final Provider<EntityManager> entityManagerProvider;
   private final Injector injector;
   private final MailService mailService;
   private final ExceptionServices exceptionServices;
   private final Configuration config;

   private RemoteMessageService remoteMessageService;

   @Inject
   public SchedulerNotificationHooker(@SchedulerModuleProperties Configuration config, Provider<EntityManager> emp,
         Injector injector, MailService mailService, ExceptionServices exceptionServices,
         RemoteMessageService remoteMessageService) {

      this.config = config;
      this.entityManagerProvider = emp;
      this.injector = injector;
      this.mailService = mailService;
      this.exceptionServices = exceptionServices;
      this.remoteMessageService = remoteMessageService;
   }

   @Override
   public void jobExecutionAboutToStart(AbstractJob job, ExecutionLogEntry logEntry) {
      // TODO Auto-generated method stub

   }

   @Override
   public void actionExecutionAboutToStart(AbstractJob job, ExecutionLogEntry logEntry) {
      // TODO Auto-generated method stub

   }

   @Override
   public void executionEndedSuccessfully(AbstractJob job, ExecutionLogEntry logEntry) {
      if (!(job instanceof ReportExecuteJob))
         return;

   }

   @Override
   public void executionEndedAbnormally(AbstractJob job, ExecutionLogEntry logEntry, Exception e) {
      if (!(job instanceof ReportExecuteJob))
         return;

      if (config.getBoolean(PROPERTY_NOTIFICATION_DISABLED, false))
         return;

      e.printStackTrace();

      try {
         job = entityManagerProvider.get().find(job.getClass(), job.getId());
         injector.injectMembers(job);
         List<User> recipients = new ArrayList<>();
         recipients.add(((ReportExecuteJob) job).getExecutor());

         Map<String, Object> datamap = new HashMap<String, Object>();
         datamap.put("report", new ReportForJuel(((ReportExecuteJob) job).getReport()));
         datamap.put("user", UserForJuel.createInstance(((ReportExecuteJob) job).getExecutor()));
         datamap.put("executor", UserForJuel.createInstance(((ReportExecuteJob) job).getExecutor()));
         datamap.put("scheduledBy", UserForJuel.createInstance(((ReportExecuteJob) job).getScheduledBy()));
         datamap.put("job", new ReportJobForJuel((ReportExecuteJob) job));
         datamap.put("owners",
               UserListForJuelPrinter.createInstance(new ArrayList<>(((ReportExecuteJob) job).getOwners()),
                     config.getBoolean(PROPERTY_NOTIFICATION_HTML, false)));

         datamap.put("reportName", String.valueOf(
               null == ((ReportExecuteJob) job).getReport() ? "null" : ((ReportExecuteJob) job).getReport().getName()));
         datamap.put("errMsg", String.valueOf(null != e ? e.getMessage() : ""));
         datamap.put("stacktrace", String.valueOf((null != e) ? exceptionServices.exceptionToString(e) : ""));

         if (null != ((ReportExecuteJob) job).getRecipients()) {
            datamap.put("recipients",
                  (((ReportExecuteJob) job).getRecipients()).stream()
                        .map(rec -> rec.getFirstname() + " " + rec.getLastname() + " <" + rec.getEmail() + ">")
                        .collect(joining("\r\n")) + "\r\n");
         }

         String currentLanguage = LocalizationServiceImpl.getLocale().getLanguage();
         datamap.put("msgs", remoteMessageService.getMessages(currentLanguage));

         String subjectTemplate = config.getString(PROPERTY_NOTIFICATION_FAILURE_SUBJECT);
         String messageTemplate = config.getString(PROPERTY_NOTIFICATION_FAILURE_TEXT);

         sendMessage(subjectTemplate, messageTemplate, recipients, datamap);
      } catch (Exception ex) {
         logger.warn("scheduler notification error", ex);
      }
   }

   @Override
   public VetoJobExecution doesVetoExecution(AbstractJob job, ExecutionLogEntry logEntry) {
      return null;
   }

   @Override
   public void informAboutVeto(AbstractJob job, ExecutionLogEntry logEntry, VetoJobExecution veto) {

   }

   @Override
   public void actionExecutionEndedAbnormally(AbstractJob job, AbstractAction action, ActionEntry actionEntry,
         Exception e) {

   }

   @Override
   public void jobExecutionEndedAbnormally(AbstractJob job, JobEntry jobEntry, Exception e) {
   }

   @Override
   public void beforeJobSchedule(AbstractJob job, AbstractTrigger trigger) {

   }

   @Override
   public void jobScheduled(AbstractJob job, AbstractTrigger trigger) {
      if (!(job instanceof ReportExecuteJob))
         return;

      if (config.getBoolean(PROPERTY_NOTIFICATION_DISABLED, false))
         return;

      try {
         List<User> recipients = ((ReportExecuteJob) job).getRecipients();

         Map<String, Object> datamap = new HashMap<>();
         datamap.put("report", new ReportForJuel(((ReportExecuteJob) job).getReport()));
         datamap.put("user", UserForJuel.createInstance(((ReportExecuteJob) job).getExecutor()));
         datamap.put("executor", UserForJuel.createInstance(((ReportExecuteJob) job).getExecutor()));
         datamap.put("scheduledBy", UserForJuel.createInstance(((ReportExecuteJob) job).getScheduledBy()));
         datamap.put("job", new ReportJobForJuel((ReportExecuteJob) job));
         datamap.put("owners",
               UserListForJuelPrinter.createInstance(new ArrayList<>(((ReportExecuteJob) job).getOwners()),
                     config.getBoolean(PROPERTY_NOTIFICATION_HTML, false)));

         datamap.put("reportName", String.valueOf(
               null == ((ReportExecuteJob) job).getReport() ? "null" : ((ReportExecuteJob) job).getReport().getName()));

         if (null != job.getTrigger()) {
            datamap.put("nextDates",
                  job.getTrigger().getNextScheduleTimes(3).stream()
                        .map(date -> SimpleDateFormat.getDateTimeInstance().format(date)).collect(joining("\r\n"))
                        + "\r\n");
         } else
            datamap.put("nextDates", "");

         User user = ((ReportExecuteJob) job).getExecutor();
         if (null != user) {
            String name = user.getFirstname() + " " + user.getLastname() + " <" + user.getEmail() + ">";
            datamap.put("scheduleUser", name);
         }

         String currentLanguage = LocalizationServiceImpl.getLocale().getLanguage();
         datamap.put("msgs", remoteMessageService.getMessages(currentLanguage));

         String subjectTemplate = config.getString(PROPERTY_NOTIFICATION_SCHEDULED_SUBJECT);
         String messageTemplate = config.getString(PROPERTY_NOTIFICATION_SCHEDULED_TEXT);

         sendMessage(subjectTemplate, messageTemplate, recipients, datamap);
      } catch (Exception ex) {
         logger.warn("scheduler notification error", ex);
      }
   }

   @Override
   public void beforeJobRescheduled(AbstractJob job, AbstractTrigger trigger, AbstractJob previous) {
      // TODO Auto-generated method stub

   }

   @Override
   public void beforeJobUnschedule(AbstractJob job) {
      // TODO Auto-generated method stub

   }

   @Override
   public void jobRescheduled(AbstractJob job, AbstractTrigger trigger) {
      // TODO Auto-generated method stub

   }

   @Override
   public void jobUnscheduled(AbstractJob job) {
      if (!(job instanceof ReportExecuteJob))
         return;

      if (config.getBoolean(PROPERTY_NOTIFICATION_DISABLED, false))
         return;

      try {
         List<User> recipients = ((ReportExecuteJob) job).getRecipients();

         Map<String, Object> datamap = new HashMap<String, Object>();
         datamap.put("report", new ReportForJuel(((ReportExecuteJob) job).getReport()));
         datamap.put("user", UserForJuel.createInstance(((ReportExecuteJob) job).getExecutor()));
         datamap.put("executor", UserForJuel.createInstance(((ReportExecuteJob) job).getExecutor()));
         datamap.put("scheduledBy", UserForJuel.createInstance(((ReportExecuteJob) job).getScheduledBy()));
         datamap.put("job", new ReportJobForJuel((ReportExecuteJob) job));
         datamap.put("owners",
               UserListForJuelPrinter.createInstance(new ArrayList<>(((ReportExecuteJob) job).getOwners()),
                     config.getBoolean(PROPERTY_NOTIFICATION_HTML, false)));

         datamap.put("reportName", String.valueOf(
               null == ((ReportExecuteJob) job).getReport() ? "null" : ((ReportExecuteJob) job).getReport().getName()));

         String subjectTemplate = config.getString(PROPERTY_NOTIFICATION_UNSCHEDULED_SUBJECT);
         String messageTemplate = config.getString(PROPERTY_NOTIFICATION_UNSCHEDULED_TEXT);

         String currentLanguage = LocalizationServiceImpl.getLocale().getLanguage();
         datamap.put("msgs", remoteMessageService.getMessages(currentLanguage));

         sendMessage(subjectTemplate, messageTemplate, recipients, datamap);
      } catch (Exception ex) {
         logger.warn("scheduler notification error", ex);
      }
   }

   @Override
   public void beforeJobRemove(AbstractJob job) {
      // TODO Auto-generated method stub

   }

   @Override
   public void jobRemoved(AbstractJob job) {
      // TODO Auto-generated method stub

   }

   protected void sendMessage(String subject, String tpl, Collection<User> recipients, Map<String, Object> datamap)
         throws ObjectResolverException, MessagingException {
      if (null == recipients || recipients.isEmpty())
         return;

      MailTemplate messageTemplate = new MailTemplate();
      messageTemplate.setHtml(config.getBoolean(PROPERTY_NOTIFICATION_HTML, false));
      messageTemplate.setSubjectTemplate(subject);
      messageTemplate.setMessageTemplate(tpl);
      messageTemplate.setDataMap(datamap);

      SimpleMail message = mailService.newTemplateMail(messageTemplate);
      message.setRecipients(javax.mail.Message.RecipientType.BCC,
            recipients.stream().filter(user -> null != user.getEmail() && !"".equals(user.getEmail()))
                  .map(LambdaExceptionUtil.rethrowFunction(user -> new InternetAddress(user.getEmail())))
                  .toArray(Address[]::new));

      mailService.sendMail(message);
   }

   @Override
   public void actionExecutionEndedSuccessfully(AbstractJob job, AbstractAction action, ExecutionLogEntry logEntry) {
      // TODO Auto-generated method stub

   }

   @Override
   public VetoActionExecution doesVetoActionExecution(AbstractJob job, ExecutionLogEntry logEntry) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public void informAboutVeto(AbstractJob job, ExecutionLogEntry logEntry, VetoActionExecution veto) {
      // TODO Auto-generated method stub

   }

}
