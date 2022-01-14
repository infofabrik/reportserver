package net.datenwerke.scheduler.service.scheduler.stores;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractTrigger;
import net.datenwerke.scheduler.service.scheduler.stores.jpa.filter.JobFilterConfiguration;
import net.datenwerke.scheduler.service.scheduler.stores.jpa.filter.JobFilterCriteria;

public interface JobStore {

   public void scheduleJob(AbstractJob job, AbstractTrigger trigger);

   public Collection<AbstractJob> getActiveJobs();

   public Collection<AbstractJob> getAllJobs();

   public Collection<AbstractJob> getMisfiredJobs();

   public Collection<AbstractJob> getMisfiredJobs(Date before);

   public Collection<AbstractJob> getExecutingJobs();

   public Collection<AbstractJob> getWaitingJobs();

   public void unschedule(AbstractJob job);

   public void remove(AbstractJob job);

   public JobExecutionCompanion getExecutionCompanion();

   void initJob(AbstractJob job);

   void initJobs(Collection<AbstractJob> jobs);

   public AbstractJob getJobById(long id);

   public void merge(AbstractJob job);

   List<AbstractJob> getJobsBy(JobFilterConfiguration filterConfig, JobFilterCriteria... addConfigs);

   public void clearErrorState(AbstractJob job);

}
