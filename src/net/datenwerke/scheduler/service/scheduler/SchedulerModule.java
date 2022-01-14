package net.datenwerke.scheduler.service.scheduler;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.XMLConfiguration;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.utils.config.ConfigFileNotFoundException;
import net.datenwerke.scheduler.service.scheduler.annotations.DwSchedulerConfig;
import net.datenwerke.scheduler.service.scheduler.annotations.SchedulerAsyncPoolName;
import net.datenwerke.scheduler.service.scheduler.annotations.SchedulerCheckInterval;
import net.datenwerke.scheduler.service.scheduler.annotations.SchedulerDefaultJobStore;
import net.datenwerke.scheduler.service.scheduler.annotations.SchedulerNrOfWorkingThreads;
import net.datenwerke.scheduler.service.scheduler.annotations.SchedulerWaitBeforeForcedShutdown;
import net.datenwerke.scheduler.service.scheduler.annotations.StandardVetoDelay;
import net.datenwerke.scheduler.service.scheduler.annotations.StandardVetoRandomDelay;
import net.datenwerke.scheduler.service.scheduler.stores.JobStore;
import net.datenwerke.scheduler.service.scheduler.stores.RamJobStore;

public class SchedulerModule extends AbstractModule {

   public static final String CONFIG_FILE = "scheduler/scheduler.cf";

   protected static final String PROPERTY_ASYNC_POOL_NAME = "scheduler.properties.asyncpoolname";
   protected static final String PROPERTY_CHECK_INTERVAL = "scheduler.properties.checkinterval";
   protected static final String PROPERTY_NR_WORKING_THREADS = "scheduler.properties.workingthreads";
   protected static final String PROPERTY_WAIT_BEFORE_FORCED_SHUTDOWN = "scheduler.properties.shutdownwait";
   protected static final String PROPERTY_STANDARD_VETO_DELAY = "scheduler.properties.stdvetodelay";
   protected static final String PROPERTY_VETO_RANDOM_DELAY = "scheduler.properties.rndvetodealy";
   protected static final String PROPERTY_SCHEDULER_DISABLED = "scheduler.properties.disabled";

   protected static final String PROPERTY_KEY_SCHEDULER_DISABLE = "rs.scheduler.disable";

   private final Class<? extends JobStore> storeType;

   public SchedulerModule() {
      this.storeType = RamJobStore.class;
   }

   public SchedulerModule(Class<? extends JobStore> storeType) {

      this.storeType = storeType;
   }

   @Override
   protected void configure() {
      bind(JobStore.class).annotatedWith(SchedulerDefaultJobStore.class).to(storeType);

      /* services */
      bind(SchedulerService.class).to(SchedulerServiceImpl.class).in(Singleton.class);

      bind(SchedulerStartup.class).asEagerSingleton();
   }

   @Provides
   @Inject
   @DwSchedulerConfig
   public Configuration providePropertyContainer(ConfigService configService) {
      try {
         return configService.getConfigFailsafe(CONFIG_FILE);
      } catch (ConfigFileNotFoundException e) {
         return new XMLConfiguration();
      }
   }

   @Provides
   @Inject
   @SchedulerAsyncPoolName
   public String provideAsyncPoolName(@DwSchedulerConfig Configuration config) {
      return config.getString(PROPERTY_ASYNC_POOL_NAME, "dwScheduler");
   }

   @Provides
   @Inject
   @SchedulerCheckInterval
   public Long provideSchedulerCheckInterval(@DwSchedulerConfig Configuration config) {
      return config.getLong(PROPERTY_CHECK_INTERVAL, 10 * 1000l);
   }

   @Provides
   @Inject
   @SchedulerNrOfWorkingThreads
   public Integer provideSchedulerNrOfWorkingThreads(@DwSchedulerConfig Configuration config) {
      return config.getInteger(PROPERTY_NR_WORKING_THREADS, 5);
   }

   @Provides
   @Inject
   @SchedulerWaitBeforeForcedShutdown
   public Long provideSchedulerWaitBeforeForcedShutdown(@DwSchedulerConfig Configuration config) {
      return config.getLong(PROPERTY_WAIT_BEFORE_FORCED_SHUTDOWN, 60 * 1000l);
   }

   @Provides
   @Inject
   @StandardVetoDelay
   public Integer provideStandardVetoDelay(@DwSchedulerConfig Configuration config) {
      return config.getInteger(PROPERTY_STANDARD_VETO_DELAY, 180);
   }

   @Provides
   @Inject
   @StandardVetoRandomDelay
   public Integer provideStandardVetoRandomDelay(@DwSchedulerConfig Configuration config) {
      return config.getInteger(PROPERTY_VETO_RANDOM_DELAY, 20);
   }

}
