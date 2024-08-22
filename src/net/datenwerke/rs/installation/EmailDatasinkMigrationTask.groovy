package net.datenwerke.rs.installation

import javax.persistence.EntityManager

import org.apache.commons.configuration2.Configuration

import com.google.inject.Inject
import com.google.inject.Provider

import net.datenwerke.gf.service.properties.PropertiesService
import net.datenwerke.rs.configservice.service.configservice.ConfigService
import net.datenwerke.rs.core.service.datasinkmanager.DatasinkTreeService
import net.datenwerke.rs.core.service.datasinkmanager.entities.AbstractDatasinkManagerNode
import net.datenwerke.rs.emaildatasink.service.emaildatasink.action.ScheduleAsEmailFileAction
import net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions.EmailDatasink
import net.datenwerke.rs.keyutils.service.keyutils.KeyNameGeneratorService
import net.datenwerke.rs.scheduler.service.scheduler.mail.MailReportAction
import net.datenwerke.rs.utils.misc.DateUtils
import net.datenwerke.scheduler.service.scheduler.SchedulerService

public class EmailDatasinkMigrationTask implements DbInstallationTask {

   private final Provider<DatasinkTreeService> datasinkServiceProvider
   private final Provider<EntityManager> emp
   private final Provider<PropertiesService> propertiesServiceProvider
   private final Provider<ConfigService> configServiceProvider
   private final Provider<SchedulerService> schedulerServiceProvider
   private final Provider<KeyNameGeneratorService> keyNameGeneratorServiceProvider
   
   private final String emailDatasinkTaskProperty = "rs:emailDatasinkTask";

   @Inject
   public EmailDatasinkMigrationTask(
         Provider<DatasinkTreeService> datasinkServiceProvider,
         Provider<EntityManager> emp,
         Provider<PropertiesService> propertiesServiceProvider,
         Provider<ConfigService> configServiceProvider,
         Provider<SchedulerService> schedulerServiceProvider,
         Provider<KeyNameGeneratorService> keyNameGeneratorServiceProvider
         ) {

      /* store objects */
      this.datasinkServiceProvider = datasinkServiceProvider
      this.emp = emp
      this.propertiesServiceProvider = propertiesServiceProvider
      this.configServiceProvider = configServiceProvider
      this.schedulerServiceProvider = schedulerServiceProvider
      this.keyNameGeneratorServiceProvider = keyNameGeneratorServiceProvider
   }

   @Override
   public void executeOnStartup() {
      /* legacy mail.cf */
      if (!propertiesServiceProvider.get().containsKey(emailDatasinkTaskProperty))
         runMailUpgrade()
   }
   
   private void runMailUpgrade() {
      EmailDatasink emailDatasink = generateAndInstallDatasink()
      
      migrateSchedulerJobs(emailDatasink)
      
      propertiesServiceProvider.get().setProperty(emailDatasinkTaskProperty, DateUtils.formatCurrentDate())
   }
   
   private void migrateSchedulerJobs(EmailDatasink emailDatasink) {
      def attachmentNameTemplate = configServiceProvider.get()
            .getConfigFailsafe('scheduler/scheduler.cf')
            .getString('scheduler.mailaction.attachment.name', 'rep-${report.getName()}-${RS_CURRENT_DATE}')
//      println "Attachment template: $attachmentNameTemplate"

      def allJobs = schedulerServiceProvider.get().jobStore.allJobs
//      println "All jobs: $allJobs"

      def actions = allJobs*.actions
//      println "All actions: $actions"

      def jobsToMigrate = allJobs
            .findAll{ MailReportAction in it.actions*.class }
//      println "Jobs to migrate: " + jobsToMigrate

      def entityManager = emp.get()
      jobsToMigrate.each{ job ->
//         println "Migrating job '$job'..."

         def actionsToMigrate = job.actions.findAll{ MailReportAction == it.class }
         def migratedActions = []
         actionsToMigrate.each { action ->
//            println "Migrating action: '$action'..."
            def migratedAction = new ScheduleAsEmailFileAction()
            migratedAction.emailDatasink = emailDatasink
            migratedAction.message = action.message
            migratedAction.subject = action.subject
            migratedAction.compressed = action.compressed
            migratedAction.name = attachmentNameTemplate
            migratedActions << migratedAction
//            println "Migrated action: '$migratedAction'"
         }
         assert actionsToMigrate.size() == migratedActions.size()
         job.actions.removeAll actionsToMigrate
         actionsToMigrate.each{
            def toRemove = entityManager.find(it.class, it.id)
            assert toRemove
            entityManager.remove toRemove
         }
         job.actions.addAll migratedActions
      }
   }
   
   private EmailDatasink generateAndInstallDatasink() {
      Configuration config = configServiceProvider.get().getConfigFailsafe('mail/mail.cf')
      
      AbstractDatasinkManagerNode root = datasinkServiceProvider.get().roots[0]
      
      EmailDatasink emailDatasink = new EmailDatasink();
      emailDatasink.name = 'Auto-Generated Email Datasink'
      emailDatasink.key = keyNameGeneratorServiceProvider.get().generateDefaultKey()
      root.addChild emailDatasink
      
      emailDatasink.host = config.getString('smtp.host', 'mail.host.net')
      emailDatasink.port = config.getInteger('smtp.port', 25)
      emailDatasink.username = config.getString('smtp.username', 'rs@host.net')
      emailDatasink.password = config.getString('stmp.password', '')
      emailDatasink.sslEnable = config.getBoolean('smtp.ssl', false)
      emailDatasink.tlsEnable = config.getBoolean('smtp.tls.enable', false)
      emailDatasink.tlsRequire = config.getBoolean('smtp.tls.require', false)
      emailDatasink.sender = config.getString('mail.sender', 'rs@host.net')
      emailDatasink.senderName = config.getString('mail.senderName', 'ReportServer')
      emailDatasink.forceSender = config.getBoolean('mail.forceSender', false)
      emailDatasink.encryptionPolicy = config.getString('mail.encryptionPolicy', 'allow_mixed')
      
      datasinkServiceProvider.get().persist emailDatasink
      
      return emailDatasink
   }

   @Override
   public void executeOnFirstRun() {
   }

}
