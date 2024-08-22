package net.datenwerke.scheduler.service.scheduler

import static net.datenwerke.rs.utils.misc.DateUtils.formatCurrentDate

import javax.inject.Inject
import javax.persistence.Transient

import org.apache.commons.configuration2.Configuration
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.google.inject.Provider

import net.datenwerke.gf.service.localization.RemoteMessageService
import net.datenwerke.rs.base.service.parameterreplacements.provider.ReportForJuel
import net.datenwerke.rs.base.service.parameterreplacements.provider.ReportJobForJuel
import net.datenwerke.rs.base.service.parameterreplacements.provider.UserForJuel
import net.datenwerke.rs.base.service.parameterreplacements.provider.UserListForJuelPrinter
import net.datenwerke.rs.core.service.datasinkmanager.DatasinkModule
import net.datenwerke.rs.core.service.mail.MailTemplate
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException
import net.datenwerke.rs.scheduler.client.scheduler.locale.SchedulerMessages
import net.datenwerke.rs.scheduler.service.scheduler.annotations.SchedulerModuleProperties
import net.datenwerke.rs.scheduler.service.scheduler.genrights.SchedulingBasicSecurityTarget
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob
import net.datenwerke.rs.scripting.service.jobs.ScriptExecuteJob
import net.datenwerke.rs.utils.juel.SimpleJuel
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl
import net.datenwerke.scheduler.service.scheduler.entities.AbstractAction
import net.datenwerke.security.service.security.SecurityService
import net.datenwerke.security.service.security.SecurityServiceSecuree
import net.datenwerke.security.service.security.rights.Execute
import net.datenwerke.security.service.usermanager.UserManagerService
import net.datenwerke.security.service.usermanager.entities.User

public class SchedulerHelperServiceImpl implements SchedulerHelperService {
   
   @Transient
   private final Logger logger = LoggerFactory.getLogger(getClass().name)
   
   private final Provider<SchedulerService> schedulerServiceProvider
   private final Provider<UserManagerService> userManagerServiceProvider
   private final Provider<SecurityService> securityServiceProvider
   private final Provider<SimpleJuel> simpleJuelProvider
   private final Provider<Configuration> configProvider
   private final Provider<ReportExecutorService> reportExecutorServiceProvider
   private final Provider<RemoteMessageService> remoteMessageServiceProvider
   
   @Inject
   public SchedulerHelperServiceImpl(
      Provider<SchedulerService> schedulerServiceProvider,
      Provider<UserManagerService> userManagerServiceProvider,
      Provider<SecurityService> securityServiceProvider,
      Provider<SimpleJuel> simpleJuelProvider,
      @SchedulerModuleProperties Provider<Configuration> configProvider,
      Provider<ReportExecutorService> reportExecutorServiceProvider,
      Provider<RemoteMessageService> remoteMessageServiceProvider
      ) {
      this.schedulerServiceProvider = schedulerServiceProvider
      this.userManagerServiceProvider = userManagerServiceProvider
      this.securityServiceProvider = securityServiceProvider
      this.simpleJuelProvider = simpleJuelProvider
      this.configProvider = configProvider
      this.reportExecutorServiceProvider = reportExecutorServiceProvider
      this.remoteMessageServiceProvider = remoteMessageServiceProvider
   }

   @Override
   public void replaceSchedulerUser(final User oldUser, final User newUser) {
      def schedulerService = schedulerServiceProvider.get()
      
      // check permissions
      schedulerService.jobStore.allJobs
            .findAll { it.active } // we don't want archived jobs
            .each { job ->
               if (job instanceof ReportExecuteJob)
                  assertReportJobPermissions job, oldUser, newUser
               else if (job instanceof ScriptExecuteJob)
                  assertScriptJobPermissions job, oldUser, newUser
            }
      
      // replace user
      schedulerService.jobStore.allJobs
         .findAll { it.active } // we don't want archived jobs
         .each{ job -> // Both ReportExecuteJobs and ScriptExecuteJobs
            if (job.scheduledBy == oldUser)
               job.scheduledBy = newUser
   
            /* Important: set executor before setting owner because of 
             * net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob.prePersist()
             */
            if (job.executor == oldUser)
               job.executor = newUser
            if (job instanceof ReportExecuteJob) {
               def owners = job.owners
               if (oldUser in owners) {
                  owners = owners - oldUser + newUser
                  job.owners = owners
               }
               def recipients = userManagerServiceProvider.get().getUsers(job.recipientsIds)
               if (oldUser in recipients) {
                  recipients = recipients - oldUser + newUser
                  job.recipients = (recipients as Set) as List // remove duplicates
               }
            }
            schedulerService.merge job
         }
      
   }
   
   private def assertReportJobPermissions(job, oldUser, newUser) {
      def securityService = securityServiceProvider.get()
      if (job.scheduledBy == oldUser
            || job.executor == oldUser
            || oldUser in job.owners) {
         if (!securityService.checkRights(newUser, SchedulingBasicSecurityTarget, SecurityServiceSecuree, Execute))
            throw new IllegalStateException("'$newUser' does not have 'SchedulingBasicSecurityTarget' permission: Execute")
         if (!securityService.checkRights(newUser, job.report, SecurityServiceSecuree, Execute))
            throw new IllegalStateException("'$newUser' does not have 'Execute' permission on report: '${job.report}'")
      }
      def recipients = userManagerServiceProvider.get().getUsers(job.recipientsIds)
      if (oldUser in recipients) {
         if(!newUser.email) 
            throw new IllegalStateException("'$newUser' email is empty")
      }
   }
   
   private def assertScriptJobPermissions(job, oldUser, newUser) {
      def securityService = securityServiceProvider.get()
      if (job.scheduledBy == oldUser
            || job.executor == oldUser) {
         if (!securityService.checkRights(newUser, SchedulingBasicSecurityTarget, SecurityServiceSecuree, Execute))
            throw new IllegalStateException("'$newUser' does not have 'SchedulingBasicSecurityTarget' permission: Execute")
         if (!securityService.checkRights(newUser, job.script, SecurityServiceSecuree, Execute))
            throw new IllegalStateException("'$newUser' does not have 'Execute' permission on script: '${job.script}'")
      }
   }

   @Override
   public SimpleJuel getConfiguredJuel(ReportExecuteJob job) {
      SimpleJuel juel = simpleJuelProvider.get()

      juel.addReplacement('jobId', job.id as String)
      juel.addReplacement('hasData', job.executedReport.hasData())

      /* addReport */
      juel.addReplacement("report", new ReportForJuel(job.report))
      juel.addReplacement("job", new ReportJobForJuel(job));
      juel.addReplacement("user", UserForJuel.createInstance(job.executor))
      juel.addReplacement("executor", UserForJuel.createInstance(job.executor))
      juel.addReplacement("scheduledBy", UserForJuel.createInstance(job.scheduledBy))
      juel.addReplacement("recipients", UserListForJuelPrinter.createInstance(job.recipients, isHTML()))
      juel.addReplacement("owners", UserListForJuelPrinter.createInstance(new ArrayList<>(job.owners), isHTML()))
      juel.addReplacement("now", formatCurrentDate())

      /* metadata description */
      try {
         String metadata = (String) reportExecutorServiceProvider.get()
               .exportMetadata(job.report, job.executor, null, ReportExecutorService.METADATA_FORMAT_PLAIN)
               .metadata
         juel.addReplacement('metadata', metadata)
      } catch (ReportExecutorException e) {
         logger.warn(e.message, e)
      }

      return juel
   }
   
   @Override
   public boolean isHTML() {
      try {
         return configProvider.get().getBoolean('scheduler.mailaction[@html]', false);
      } catch (Exception e) {
         return false;
      }
   }

   private Map<String, Object> getDatamap(ReportExecuteJob job, AbstractAction action, Map<String, String> properties, Exception e, 
      HashMap<String, HashMap<String, String>> msgs) {
      Map<String, Object> datamap = new HashMap<>()
      
      assert action.metaClass.hasProperty(action, 'report')
      datamap['report'] = new ReportForJuel(action.report)
      
      datamap['job'] =  new ReportJobForJuel(job)
      UserForJuel executor = UserForJuel.createInstance(job.executor)
      datamap['user'] =  executor
      datamap['executor'] =  executor
      datamap['scheduledBy'] =  UserForJuel.createInstance(job.scheduledBy)

      datamap['owners'] =  UserListForJuelPrinter.createInstance(new ArrayList<>(job.owners),
            configProvider.get().getBoolean(properties.get(DatasinkModule.PROPERTY_HTML), false))

      if (action.metaClass.hasProperty(action, 'folder'))
         datamap['folder'] =  action.folder
      
      SimpleJuel juel = getConfiguredJuel(job)
      if (action.metaClass.hasProperty(action, 'filename')) {
         datamap['name'] =  juel.parse(action.filename)
         datamap['filename'] =  juel.parse(action.filename)
      }
      
      if (null != e) {
         datamap['exception'] = e
         ByteArrayOutputStream bos = new ByteArrayOutputStream()
         PrintWriter pw = new PrintWriter(bos)
         e.printStackTrace(pw)
         pw.close()
         datamap['trace'] = bos as String
      }
      
      datamap['msgs'] = msgs
      
      return datamap
   }

   @Override
   public MailTemplate getMailTemplate(ReportExecuteJob job, AbstractAction action, Map<String, String> properties, Exception e) {
         
      String currentLanguage = LocalizationServiceImpl.getLocale().getLanguage();
      HashMap<String, HashMap<String, String>> msgs = remoteMessageServiceProvider.get().getMessages(currentLanguage)
      
      Map<String, Object> datamap = getDatamap(job, action, properties, e, msgs)

      String subjectTemplate = configProvider.get().getString(properties.get(DatasinkModule.PROPERTY_SUBJECT),
            msgs.get(SchedulerMessages.class.getCanonicalName()).get(properties.get(DatasinkModule.PROPERTY_TRANSLATIONS_SUBJECT)))
      String messageTemplate = configProvider.get().getString(properties.get(DatasinkModule.PROPERTY_TEXT),
            msgs.get(SchedulerMessages.class.getCanonicalName()).get(properties.get(DatasinkModule.PROPERTY_TRANSLATIONS_TEXT)))

      MailTemplate mailTemplate = new MailTemplate()
      mailTemplate.html = configProvider.get().getBoolean(properties.get(DatasinkModule.PROPERTY_HTML), false)
      mailTemplate.subjectTemplate = subjectTemplate
      mailTemplate.messageTemplate = messageTemplate
      mailTemplate.dataMap = datamap
      
      return mailTemplate
   }
   
}