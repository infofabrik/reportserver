package net.datenwerke.scheduler.service.scheduler.stores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.NotImplementedException;

import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractTrigger;
import net.datenwerke.scheduler.service.scheduler.entities.JobExecutionStatus;
import net.datenwerke.scheduler.service.scheduler.stores.jpa.filter.JobFilterConfiguration;
import net.datenwerke.scheduler.service.scheduler.stores.jpa.filter.JobFilterCriteria;

public class RamJobStore extends JobStoreImpl {

	private Set<AbstractJob> jobSet = new HashSet<AbstractJob>();
	
	@Override
	public void scheduleJob(AbstractJob job, AbstractTrigger trigger) {
		super.scheduleJob(job, trigger);
		
		jobSet.add(job);
	}

	@Override
	public Collection<AbstractJob> getActiveJobs() {
		List<AbstractJob> jobs = new ArrayList<AbstractJob>();
		for(AbstractJob job : jobSet)
			if(null != job.getTrigger().getNextScheduledFireTime())
				jobs.add(job);
		return jobs;
	}

	@Override
	public Collection<AbstractJob> getAllJobs() {
		return jobSet;
	}

	@Override
	public Collection<AbstractJob> getMisfiredJobs() {
		Date now = new Date();
		List<AbstractJob> jobs = new ArrayList<AbstractJob>();
		for(AbstractJob job : jobSet){
			Date scheduled = job.getTrigger().getNextScheduledFireTime();
			JobExecutionStatus status = job.getExecutionStatus();
			if(null != scheduled && ((scheduled.before(now) && status.equals(JobExecutionStatus.INACTIVE)) || job.getTrigger().isExecuteOnce()) )
				jobs.add(job);
		}
		return jobs;
	}

	@Override
	public Collection<AbstractJob> getMisfiredJobs(Date before) {
		List<AbstractJob> jobs = new ArrayList<AbstractJob>();
		for(AbstractJob job : jobSet){
			Date scheduled = job.getTrigger().getNextScheduledFireTime();
			JobExecutionStatus status = job.getExecutionStatus();
			if(null != scheduled && scheduled.before(before) && status.equals(JobExecutionStatus.INACTIVE))
				jobs.add(job);
		}
		return jobs;
	}
	
	@Override
	public Collection<AbstractJob> getExecutingJobs() {
		List<AbstractJob> jobs = new ArrayList<AbstractJob>();
		for(AbstractJob job : jobSet)
			if(JobExecutionStatus.EXECUTING.equals(job.getExecutionStatus()) || JobExecutionStatus.EXECUTING_ACTIONS.equals(job.getExecutionStatus()))
				jobs.add(job);
		return jobs;	
	}
	
	@Override
	public Collection<AbstractJob> getWaitingJobs() {
		List<AbstractJob> jobs = new ArrayList<AbstractJob>();
		for(AbstractJob job : jobSet)
			if(JobExecutionStatus.WAITING.equals(job.getExecutionStatus()))
				jobs.add(job);
		return jobs;	
	}

	@Override
	public void unschedule(AbstractJob job) {
		job.getTrigger().setNextScheduledFireTime(null);
	}

	@Override
	public void remove(AbstractJob job) {
		jobSet.remove(job);
	}
	
	@Override
	public void clearErrorState(AbstractJob job) {
		job.setExecutionStatus(JobExecutionStatus.INACTIVE);
	}

	@Override
	public AbstractJob getJobById(long id) {
		for(AbstractJob job : jobSet)
			if(null != job.getId())
				if(id == (long)job.getId())
					return job;
		return null;
	}

	@Override
	public List<AbstractJob> getJobsBy(JobFilterConfiguration filterConfig, JobFilterCriteria... addConfigs) {
		throw new NotImplementedException("not implemented");
	}




}
