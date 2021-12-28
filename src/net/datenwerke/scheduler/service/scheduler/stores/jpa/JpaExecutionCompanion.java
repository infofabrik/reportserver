package net.datenwerke.scheduler.service.scheduler.stores.jpa;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.UnitOfWork;

import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.entities.JobExecutionStatus;
import net.datenwerke.scheduler.service.scheduler.entities.Outcome;
import net.datenwerke.scheduler.service.scheduler.entities.history.ActionEntry;
import net.datenwerke.scheduler.service.scheduler.entities.history.ExecutionLogEntry;
import net.datenwerke.scheduler.service.scheduler.entities.history.JobEntry;
import net.datenwerke.scheduler.service.scheduler.stores.JobExecutionCompanionImpl;
import net.datenwerke.scheduler.service.scheduler.tasks.SchedulerTask;

public class JpaExecutionCompanion extends JobExecutionCompanionImpl {
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	private final Provider<EntityManager> entityManagerProvider;
	private final UnitOfWork unitOfWork;
	
	@Inject
	public JpaExecutionCompanion(
		Provider<EntityManager> entityManagerProvider,
		UnitOfWork unitOfWork
		){
		
		/* store objects */
		this.entityManagerProvider = entityManagerProvider;
		this.unitOfWork = unitOfWork;
	}

	@Override
	public AbstractJob initJobForExecution(AbstractJob job){
		EntityManager em = entityManagerProvider.get();
		job = em.find(AbstractJob.class, job.getId());

		em.getTransaction().begin();
		
		super.initJobForExecution(job);
		
		em.getTransaction().commit();
		
		return job;
	}

	
	@Override
	public AbstractJob beginSchedulerTask(AbstractJob job) {
		unitOfWork.begin();
		
		EntityManager em = entityManagerProvider.get();
		em.getTransaction().begin();
		job = em.find(AbstractJob.class, job.getId());
		
		super.beginSchedulerTask(job);
		
		em.getTransaction().commit();
		
		return job;
	}
	
	@Override
	public void endSchedulerTask(AbstractJob job, ExecutionLogEntry logEntry, boolean badFailure) {
		try{
			EntityManager em = entityManagerProvider.get();
			em.getTransaction().begin();
			
			try{
				if(null == logEntry){
					// something went wrong badly !!
					logEntry = new ExecutionLogEntry();
					
					job.getHistory().addExecutionLogEntry(logEntry);
					em.persist(logEntry);
				}
				logEntry.setEnd(new Date());

				/* reset job */
	 			if(badFailure) {
	 				job.setExecutionStatus(JobExecutionStatus.BAD_FAILURE);
	 				job.setLastOutcome(Outcome.FAILURE);
				} else 
					job.setExecutionStatus(JobExecutionStatus.INACTIVE);
				
	 			em.merge(job);
	 			
				em.getTransaction().commit();
			} finally {
				if(em.getTransaction().isActive())
					em.getTransaction().rollback();
			}
		} catch(Exception e){
			logger.error( e.getMessage(), e);
		} finally {
			unitOfWork.end();
		}
	}
	
	@Override
	public void failExecutingJob(AbstractJob job) {
		EntityManager em = entityManagerProvider.get();
		em.getTransaction().begin();
		
		super.failExecutingJob(job);
		
		em.getTransaction().commit();
	}
	
	@Override
	public void resetWaitingJob(AbstractJob job) {
		EntityManager em = entityManagerProvider.get();
		em.getTransaction().begin();
		
		super.resetWaitingJob(job);
		
		em.getTransaction().commit();
	}
	
	@Override
	public void beginInnerTransaction(SchedulerTask schedulerTask) {
		EntityManager em = entityManagerProvider.get();

		if(em.getTransaction().isActive())
			throw new IllegalStateException("nested transactions are not allowed");

		em.getTransaction().begin();

		/* fix job and logentry after commit in case an earlier rollback occured */
		AbstractJob job = schedulerTask.getJob();
		ExecutionLogEntry logEntry = schedulerTask.getLogEntry();

		AbstractJob reloadedJob = em.find(job.getClass(), job.getId());
		schedulerTask.setJob(reloadedJob);
		schedulerTask.setLogEntry(em.find(logEntry.getClass(), logEntry.getId()));

		/* inject members into job */
		injector.injectMembers(reloadedJob);
		injector.injectMembers(reloadedJob.getTrigger());
		
		/* copy transient fields */
		reloadedJob.copyTransientFieldsFrom(job);
	}
	
	@Override
	public void finishInnerTransaction(SchedulerTask schedulerTask, boolean success) {
		EntityManager em = entityManagerProvider.get();
		
		try{
			if(success){
				AbstractJob job = schedulerTask.getJob();
				
				em.merge(job);
				em.getTransaction().commit();
			} else {
				em.getTransaction().rollback();
			}
		} finally {
			try{
				if(em.getTransaction().isActive())
					em.getTransaction().rollback();
			}catch(Exception e){
			}
		}
	}
	
	/**
	 * should not be wrapped in transaction
	 */
	@Override
	public ExecutionLogEntry initHistoryEntry(AbstractJob job) {
		ExecutionLogEntry logEntry = new ExecutionLogEntry();
		
		if(! job.getTrigger().isExecuteOnce())
			logEntry.setScheduledStart(job.getTrigger().getNextScheduledFireTime());
		logEntry.setStart(Calendar.getInstance().getTime());
		
		EntityManager em = entityManagerProvider.get();
		em.getTransaction().begin();
		
		job.getHistory().addExecutionLogEntry(logEntry);
		em.persist(logEntry);
		
		em.getTransaction().commit();
		
		return logEntry;
	}

	@Override
	public ActionEntry initActionEntry(ExecutionLogEntry logEntry) {
		ActionEntry entry = super.initActionEntry(logEntry);
		entityManagerProvider.get().persist(entry);
		
		return entry;
	}
	
	@Override
	public JobEntry initJobEntry(ExecutionLogEntry logEntry) {
		JobEntry entry = super.initJobEntry(logEntry);
		entityManagerProvider.get().persist(entry);
		
		return entry;
	}
	
	@Override
	public void flush() {
		EntityManager em = entityManagerProvider.get();
		em.flush();
	}
}
