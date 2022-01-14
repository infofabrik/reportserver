package net.datenwerke.scheduler.service.scheduler.stores;

import java.util.Collection;

import com.google.inject.Inject;
import com.google.inject.Injector;

import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractTrigger;
import net.datenwerke.scheduler.service.scheduler.entities.JobExecutionStatus;

public abstract class JobStoreImpl implements JobStore {

   @Inject
   protected Injector injector;

   @Override
   public void scheduleJob(AbstractJob job, AbstractTrigger trigger) {
      job.setTrigger(trigger);

      /* notify job */
      job.hasBeenScheduled();

      /* init trigger */
      if (!trigger.isInitialized())
         trigger.initialize();

      if (null == trigger.getNextScheduledFireTime())
         throw new IllegalArgumentException("Trigger will never fire");
   }

   @Override
   public JobExecutionCompanion getExecutionCompanion() {
      return injector.getInstance(JobExecutionCompanionImpl.class);
   }

   @Override
   public void initJob(AbstractJob job) {
      if (null != job)
         injector.injectMembers(job);
   }

   @Override
   public void initJobs(Collection<AbstractJob> jobs) {
      for (AbstractJob job : jobs)
         initJob(job);
   }

   @Override
   public void clearErrorState(AbstractJob job) {
      job.setExecutionStatus(JobExecutionStatus.INACTIVE);
   }

   @Override
   public void merge(AbstractJob job) {
   }

}
