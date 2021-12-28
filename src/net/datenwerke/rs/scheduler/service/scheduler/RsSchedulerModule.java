package net.datenwerke.rs.scheduler.service.scheduler;

import org.apache.commons.configuration2.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provides;

import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;
import net.datenwerke.rs.scheduler.service.scheduler.annotations.SchedulerModuleEmailAttachementName;
import net.datenwerke.rs.scheduler.service.scheduler.annotations.SchedulerModuleEmailSubject;
import net.datenwerke.rs.scheduler.service.scheduler.annotations.SchedulerModuleEmailText;
import net.datenwerke.rs.scheduler.service.scheduler.annotations.SchedulerModuleProperties;
import net.datenwerke.rs.scheduler.service.scheduler.annotations.SchedulerModuleStartupDelay;
import net.datenwerke.rs.scheduler.service.scheduler.genrights.GenRightsSchedulingModule;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.filter.ReportServerJobFilter;
import net.datenwerke.scheduler.service.scheduler.SchedulerModule;
import net.datenwerke.scheduler.service.scheduler.stores.jpa.JpaJobStore;

public class RsSchedulerModule extends AbstractReportServerModule {

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   public static final String CONFIG_FILE = "scheduler/scheduler.cf"; //$NON-NLS-1$

   public static final String PROPERTY_EMAIL_SUBJECT = "scheduler.mailaction.subject";
   public static final String PROPERTY_EMAIL_TEXT = "scheduler.mailaction.text";
   public static final String PROPERTY_EMAIL_ATTACHEMENT_NAME = "scheduler.mailaction.attachment.name";
   public static final String PROPERTY_STARTUP_DELAY = "scheduler.startupdelay";

   @Override
   protected void configure() {
      install(new SchedulerModule(JpaJobStore.class));

      /* startup */
      bind(RsSchedulerStartup.class).asEagerSingleton();

      requestStaticInjection(ReportServerJobFilter.class);

      install(new GenRightsSchedulingModule());
   }

   @Provides
   @Inject
   @SchedulerModuleProperties
   public Configuration providePropertyContainer(ConfigService configService) {
      return configService.getConfigFailsafe(CONFIG_FILE);
   }

   @Provides
   @Inject
   @SchedulerModuleStartupDelay
   public Long providePropertyContainer(@SchedulerModuleProperties Configuration config) {
      try {
         return config.getLong(PROPERTY_STARTUP_DELAY, 2 * 60 * 1000);
      } catch (RuntimeException e) {
         logger.warn("Could not read scheduler startup dealy. Default value is in effect.", e);
         return 2 * 60 * 1000l;
      }
   }

   @Provides
   @Inject
   @SchedulerModuleEmailSubject
   public String provideEmailSubject(@SchedulerModuleProperties Configuration config) {
      return config.getString(PROPERTY_EMAIL_SUBJECT, "${subject}"); //$NON-NLS-1$
   }

   @Provides
   @Inject
   @SchedulerModuleEmailText
   public String provideEmailText(@SchedulerModuleProperties Configuration config) {
      return config.getString(PROPERTY_EMAIL_TEXT, "${message}"); //$NON-NLS-1$
   }

   @Provides
   @Inject
   @SchedulerModuleEmailAttachementName
   public String provideEmailAttachementName(@SchedulerModuleProperties Configuration config) {
      return config.getString(PROPERTY_EMAIL_ATTACHEMENT_NAME, "rep-${report.getName()}-${RS_CURRENT_DATE}"); //$NON-NLS-1$
   }
}
