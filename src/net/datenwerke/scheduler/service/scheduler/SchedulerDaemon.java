package net.datenwerke.scheduler.service.scheduler;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import net.datenwerke.async.DwAsyncPool;
import net.datenwerke.async.DwAsyncService;
import net.datenwerke.async.configurations.FixedThreadPoolConfig;
import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.rs.utils.exception.ExceptionServices;
import net.datenwerke.scheduler.service.scheduler.annotations.SchedulerAsyncPoolName;
import net.datenwerke.scheduler.service.scheduler.annotations.SchedulerCheckInterval;
import net.datenwerke.scheduler.service.scheduler.annotations.SchedulerNrOfWorkingThreads;
import net.datenwerke.scheduler.service.scheduler.annotations.SchedulerWaitBeforeForcedShutdown;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractTrigger;
import net.datenwerke.scheduler.service.scheduler.events.SchedulerDaemonSuicideEvent;
import net.datenwerke.scheduler.service.scheduler.stores.JobExecutionCompanion;
import net.datenwerke.scheduler.service.scheduler.stores.JobStore;
import net.datenwerke.scheduler.service.scheduler.tasks.SchedulerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.UnitOfWork;

public class SchedulerDaemon implements Runnable{

	private final Logger logger = LoggerFactory.getLogger(getClass().getName());
	
	private final ReentrantLock lock = new ReentrantLock();
	
	private final String asyncPoolName;
	private final Long checkInterval;
	private final Long waitBeforeForcedShutdown;
	
	private final EventBus eventBus;
	private final ExceptionServices exceptionService;
	private final DwAsyncService asyncService;
	private final Provider<SchedulerTask> taskProvider;
	private final Provider<UnitOfWork> unitOfWorkProvider;
	private final SchedulerService schedulerService;
	
	private final DwAsyncPool asyncPool;
	
	private boolean shutdown = false;
	private boolean terminated = false;

	
	@Inject
	public SchedulerDaemon(
		@SchedulerAsyncPoolName String asyncPoolName,
		@SchedulerCheckInterval Long checkInterval,
		@SchedulerNrOfWorkingThreads Integer nrOfWorkerThreads,
		@SchedulerWaitBeforeForcedShutdown Long waitBeforeForcedShutdown,
			
		EventBus eventBus,
		ExceptionServices exceptionService,
		DwAsyncService asyncService,
		Provider<SchedulerTask> taskProvider,
		Provider<UnitOfWork> unitOfWorkProvider,
		SchedulerService schedulerService
		){
	
		/* store config */
		this.asyncPoolName = asyncPoolName;
		this.checkInterval = checkInterval;
		this.waitBeforeForcedShutdown = waitBeforeForcedShutdown;
		
		/* store objects */
		this.eventBus = eventBus;
		this.exceptionService = exceptionService;
		this.asyncService = asyncService;
		this.taskProvider = taskProvider;
		this.unitOfWorkProvider = unitOfWorkProvider;
		this.schedulerService = schedulerService;
		
		/* init */
		asyncPool = asyncService.initPool(asyncPoolName, new FixedThreadPoolConfig(nrOfWorkerThreads));
	}

	@Override
	public void run() {
		boolean locked = lock.tryLock();
		if(! locked)
			throw new IllegalStateException("There seem to be multiple SchedulerDaemons around. We'll close down!");
		
		try{
			try{
				/* handle terminated jobs */
				handleTerminatedJobs();
				
				/* begin usual scheduler loop */
				while(! shutdown){
					doWork();
					
					/* sleep till next fire time */ 
					try {
						Thread.sleep(getNextSleepTime());
					} catch (InterruptedException e) {
					}
				}
			} catch(Throwable e){
				try{
					logger.warn( e.getMessage(), e);
				} finally {
					if(e instanceof Error){ // close down bad time
						shutdown = true;
						try{
							asyncService.shutdownPoolNow(asyncPoolName);
						} catch(Exception e2){/* ignore */}
						throw (Error) e;
					}
				}
			}
			
			/* oderly shutdown */
			asyncService.shutdownPool(asyncPoolName);
			boolean terminated = false;
			try {
				terminated = asyncService.awaitTerminationForPool(asyncPoolName, waitBeforeForcedShutdown, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
			}
			if(! terminated)
				asyncService.shutdownPoolNow(asyncPoolName);
			this.terminated = true;
		} finally {
			lock.unlock();
		}
	}


	private void handleTerminatedJobs() {
		UnitOfWork unitOfWork = null;
		
		try{
			unitOfWork = unitOfWorkProvider.get();
			unitOfWork.begin();
			
			JobStore jobStore = schedulerService.getJobStore();
			JobExecutionCompanion executionCompanion = jobStore.getExecutionCompanion();
			
			for(AbstractJob job : jobStore.getExecutingJobs())
				executionCompanion.failExecutingJob(job);
			
			for(AbstractJob job : jobStore.getWaitingJobs())
				executionCompanion.resetWaitingJob(job);
			
		} finally {
			if(null != unitOfWork)
				unitOfWork.end();
		}
	}

	private long getNextSleepTime() {
		return Math.max(0, checkInterval);
	}

	private void doWork() {
		UnitOfWork unitOfWork = null;
		
		try{
			unitOfWork = unitOfWorkProvider.get();
			unitOfWork.begin();
			
			JobStore jobStore = schedulerService.getJobStore();
			
			JobExecutionCompanion executionCompanion = jobStore.getExecutionCompanion();
			
			/* loop over jobs */
			for(AbstractJob job : jobStore.getMisfiredJobs()){
				final AbstractTrigger trigger = job.getTrigger();
				
				if(! trigger.confirmExecution(job))
					executionNotConfirmed(job);
				else {
					/* init job */
					job = executionCompanion.initJobForExecution(job);
					
					/* create task */
					SchedulerTask task = taskProvider.get();
					task.setJob(job);
					task.setJobStore(jobStore);
					task.setCompanion(executionCompanion);
					
					/* schedule task */
					asyncPool.submit(task);
				}
			}
		} catch(Exception e){
			// something terrible happened. print exception and prepare for suicide.
			logger.error( e.getMessage(), e);
			
			eventBus.fireEvent(new SchedulerDaemonSuicideEvent("exception", exceptionService.exceptionToString(e)));
			shutdown();
		} finally {
			if(null != unitOfWork)
				unitOfWork.end();
		}
	}


	private void executionNotConfirmed(AbstractJob job) {
		
	}

	public void shutdown(){
		shutdown = true;
	}
	
	public boolean isShutdown() {
		return shutdown;
	}

	public boolean isTerminated() {
		return terminated;
	}

	public List<Runnable> shutdownNow() {
		shutdown();
		return asyncService.shutdownPoolNow(asyncPoolName);
	}
	
	public DwAsyncPool getPool(){
		return asyncPool;
	}

}
