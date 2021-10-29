package net.datenwerke.scheduler.service.scheduler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executors;

import org.apache.commons.configuration2.Configuration;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.dto.ReportScheduleJobListInformation;
import net.datenwerke.rs.scheduler.service.scheduler.genrights.SchedulingAdminSecurityTarget;
import net.datenwerke.rs.scheduler.service.scheduler.genrights.SchedulingBasicSecurityTarget;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.filter.ReportServerJobFilter;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.rs.utils.config.ConfigService;
import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.rs.utils.properties.ApplicationPropertiesService;
import net.datenwerke.scheduler.service.scheduler.annotations.DwSchedulerConfig;
import net.datenwerke.scheduler.service.scheduler.annotations.SchedulerDefaultJobStore;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractTrigger;
import net.datenwerke.scheduler.service.scheduler.events.JobScheduled;
import net.datenwerke.scheduler.service.scheduler.events.JobUnscheduled;
import net.datenwerke.scheduler.service.scheduler.events.SchedulerFailedToSopEvent;
import net.datenwerke.scheduler.service.scheduler.events.SchedulerRestartEvent;
import net.datenwerke.scheduler.service.scheduler.events.SchedulerShutdownEvent;
import net.datenwerke.scheduler.service.scheduler.events.SchedulerShutdownNowEvent;
import net.datenwerke.scheduler.service.scheduler.events.SchedulerShutdownWatchdogEvent;
import net.datenwerke.scheduler.service.scheduler.events.SchedulerStartEvent;
import net.datenwerke.scheduler.service.scheduler.events.SchedulerStartedEvent;
import net.datenwerke.scheduler.service.scheduler.exceptions.SchedulerNotEnabledException;
import net.datenwerke.scheduler.service.scheduler.exceptions.SchedulerStartupException;
import net.datenwerke.scheduler.service.scheduler.hooks.SchedulerInfoHook;
import net.datenwerke.scheduler.service.scheduler.stores.JobStore;
import net.datenwerke.scheduler.service.scheduler.stores.jpa.filter.JobFilterConfiguration;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.eventlogger.annotations.FireForceRemoveEntityEvents;
import net.datenwerke.security.service.eventlogger.annotations.FireMergeEntityEvents;
import net.datenwerke.security.service.eventlogger.annotations.FireRemoveEntityEvents;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.SecurityServiceSecuree;
import net.datenwerke.security.service.security.exceptions.ViolatedSecurityException;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.usermanager.entities.User;

public class SchedulerServiceImpl implements SchedulerService {

	private final Provider<SchedulerDaemon> daemonProvider;
	private final Provider<Injector> injectorProvider;
	private final HookHandlerService hookHandler;
	private final EventBus eventBus;
	private final Provider<SchedulerWatchdog> watchdogProvider;
	private final Provider<SecurityService> securityServiceProvider;
	private final Provider<AuthenticatorService> authenticatorServiceProvider;

	private SchedulerWatchdog watchdog;
	private SchedulerDaemon schedulerDaemon;
	private Thread daemonThread;
	private Thread watchdogThread;
	
	private JobStore jobStore;
	
	private boolean orderdShutdown = true; 
	private int threadNr = 1;
	private Provider<Configuration> schedulerConfig;
	private ConfigService configService;
	
	private final ApplicationPropertiesService propertiesService;
	
	@Inject
	public SchedulerServiceImpl(
		@SchedulerDefaultJobStore JobStore defaultStore,
		@DwSchedulerConfig Provider<Configuration> schedulerConfig,
		
		EventBus eventBus,
		HookHandlerService hookHandler,
		Provider<Injector> injectorProvider,
		Provider<SchedulerDaemon> daemonProvider,
		Provider<SchedulerWatchdog> watchdogProvider,
		ConfigService configService,
		Provider<SecurityService> securityServiceProvider,
		Provider<AuthenticatorService> authenticatorServiceProvider,
		ApplicationPropertiesService propertiesService
		){
		
		/* store objects */
		this.jobStore = defaultStore;
		this.schedulerConfig = schedulerConfig;
		this.eventBus = eventBus;
		this.hookHandler = hookHandler;
		this.injectorProvider = injectorProvider;
		this.daemonProvider = daemonProvider;
		this.watchdogProvider = watchdogProvider;
		this.configService = configService;
		this.securityServiceProvider = securityServiceProvider;
		this.authenticatorServiceProvider = authenticatorServiceProvider;
		this.propertiesService = propertiesService;
	}
	
	@Override
	public boolean isActive() {
		return null != daemonThread && daemonThread.isAlive() && null != schedulerDaemon && ! schedulerDaemon.isShutdown();
	}
	
	@Override
	public boolean isShutdown() {
		return null == daemonThread || null == schedulerDaemon || schedulerDaemon.isShutdown();
	}
	
	@Override
	public boolean isTerminated() {
		return null == daemonThread || null == schedulerDaemon || schedulerDaemon.isTerminated();
	}
	
	@Override
	public void shutdown() {
		eventBus.fireEvent(new SchedulerShutdownEvent());
		
		/* remember that we have been shutdown properly */
		orderdShutdown = true;
		
		if(null != schedulerDaemon && ! schedulerDaemon.isShutdown()){
			schedulerDaemon.shutdown();
			daemonThread.interrupt();
		}
	}

	@Override
	public List<Runnable> shutdownNow() {
		eventBus.fireEvent(new SchedulerShutdownNowEvent());
		
		shutdown();
		
		return schedulerDaemon.shutdownNow();
	}
	
	public boolean isOrderdShutdown() {
		return orderdShutdown;
	}
	
	@Override
	public void restart() throws SchedulerStartupException {
		eventBus.fireEvent(new SchedulerRestartEvent());
		
		SchedulerDaemon sd = schedulerDaemon;
		if(null == sd){
			start();
			return;
		}
		
		if(! sd.isShutdown())
			shutdown();
		
		try {
			if(null != daemonThread)
				daemonThread.join(1000*60);
		} catch (InterruptedException e) {
			schedulerDaemon.shutdownNow();
			
			try {
				if(null != daemonThread)
					daemonThread.join(1000*60);
			} catch (InterruptedException e2) {
				eventBus.fireEvent(new SchedulerFailedToSopEvent());
				
				throw new IllegalStateException("Could not shutdown scheduler");
			}
		}
		
		start();
	}

	@Override
	public synchronized void start() throws SchedulerStartupException {
		if(!isEnabled())
			throw new SchedulerNotEnabledException();
		
		eventBus.fireEvent(new SchedulerStartEvent());

		if(schedulerDaemon != null){
			if(isActive())
				return;
			else
				schedulerDaemon.shutdownNow();
		}
		
		/* make sure no daemon is running */
		try {
			if(null != daemonThread)
				daemonThread.join(1000*60);
		} catch (InterruptedException e1) {
			eventBus.fireEvent(new SchedulerFailedToSopEvent());
			
			throw new IllegalStateException("Could not shutdown scheduler");
		}
		
		schedulerDaemon = daemonProvider.get();

		/* we have not been shutdown properly */
		orderdShutdown = false;
		
		daemonThread = Executors.defaultThreadFactory().newThread(schedulerDaemon);
		daemonThread.setDaemon(true);
		daemonThread.setName("scheduler-thread-" + threadNr++);
		daemonThread.start();
		
		/* start watchdog */
		startWatchdog();
		
		eventBus.fireEvent(new SchedulerStartedEvent());
	}
	
	@Override
	public void startWatchdog() {
		if(null != watchdogThread && watchdogThread.isAlive() && ! watchdog.isShutdown())
			return;
		
		watchdog = watchdogProvider.get();
		
		watchdogThread = Executors.defaultThreadFactory().newThread(watchdog);
		watchdogThread.setDaemon(true);
		watchdogThread.setName("scheduler-watchdog");
		watchdogThread.start();
	}
	
	@Override
	public boolean isWatchdogActive(){
		return null != watchdogThread && watchdogThread.isAlive() && null != watchdog && ! watchdog.isShutdown();
	}
	

	@Override
	public void restartWatchdog(){
		shutdownWatchdog();
		startWatchdog();
	}
	
	@Override
	public void shutdownWatchdog(){
		eventBus.fireEvent(new SchedulerShutdownWatchdogEvent());
		
		if(null != watchdog)
			watchdog.shutdown();
	}

	@Override
	public JobStore getJobStore(){
		return jobStore;
	}

	@Override
	public void schedule(final AbstractJob job, final AbstractTrigger trigger) {
		injectorProvider.get().injectMembers(trigger);
		injectorProvider.get().injectMembers(trigger);
		
		hookHandler.getHookers(SchedulerInfoHook.class)
			.forEach(hooker -> hooker.beforeJobSchedule(job, trigger));
		
		jobStore.scheduleJob(job, trigger);
		
		eventBus.fireEvent(new JobScheduled(job));
		
		hookHandler.getHookers(SchedulerInfoHook.class)
			.forEach(hooker -> hooker.jobScheduled(job, trigger));
	}
	
	@Override
	public void schedule(final AbstractJob job, final AbstractTrigger trigger, final AbstractJob previous) {
		injectorProvider.get().injectMembers(job);
		injectorProvider.get().injectMembers(trigger);

		hookHandler.getHookers(SchedulerInfoHook.class)
			.forEach(hooker -> hooker.beforeJobRescheduled(job, trigger, previous));
		
		job.setLinkToPrevious(previous);
		jobStore.scheduleJob(job, trigger);
		
		eventBus.fireEvent(new JobScheduled(job));
		
		hookHandler.getHookers(SchedulerInfoHook.class)
			.forEach(hooker -> hooker.jobRescheduled(job, trigger));
	}

	@Override
	public void unschedule(final AbstractJob job) {
		hookHandler.getHookers(SchedulerInfoHook.class)
			.forEach(hooker -> hooker.beforeJobUnschedule(job));
		
		jobStore.unschedule(job);
		
		eventBus.fireEvent(new JobUnscheduled(job));
		
		hookHandler.getHookers(SchedulerInfoHook.class)
			.forEach(hooker -> hooker.jobUnscheduled(job));
	}
	
	@Override
	public void clearErrorState(AbstractJob job) {
		jobStore.clearErrorState(job);
	}

	@Override
	@FireRemoveEntityEvents
	public void remove(final AbstractJob job) {
		hookHandler.getHookers(SchedulerInfoHook.class)
			.forEach(hooker -> hooker.beforeJobRemove(job));
		
		jobStore.remove(job);
		
		hookHandler.getHookers(SchedulerInfoHook.class)
			.forEach(hooker -> hooker.jobRemoved(job));
	}
	
	@Override
	@FireForceRemoveEntityEvents
	public void forceRemove(AbstractJob job) {
		jobStore.remove(job);
	}

	@Override
	public AbstractJob getJobById(Long id) {
		if(null == id)
			return null;
		return jobStore.getJobById((long)id);
	}

	@Override
	@FireMergeEntityEvents
	public void merge(AbstractJob job) {
		jobStore.merge(job);
	}

	@Override
	public SchedulerDaemon getDeamon() {
		return schedulerDaemon;
	}

	@Override
	public List<AbstractJob> getJobsBy(JobFilterConfiguration filterConfig) {
		return getJobStore().getJobsBy(filterConfig);
	}

	@Override
	public void enable() {
		Configuration configuration = schedulerConfig.get();
		configuration.setProperty(SchedulerModule.PROPERTY_SCHEDULER_DISABLED, false);
		configService.storeConfig(configuration, SchedulerModule.CONFIG_FILE);
	}

	@Override
	public void disable() {
		Configuration configuration = schedulerConfig.get();
		configuration.setProperty(SchedulerModule.PROPERTY_SCHEDULER_DISABLED, true);
		configService.storeConfig(configuration, SchedulerModule.CONFIG_FILE);
	}

	@Override
	public boolean isEnabled() {
		if ( propertiesService.getBoolean(SchedulerModule.PROPERTY_KEY_SCHEDULER_DISABLE, false) )
			return false;
			
		return !schedulerConfig.get().getBoolean(SchedulerModule.PROPERTY_SCHEDULER_DISABLED, false);
	}

	@Override
	public List<ReportScheduleJobListInformation> getReportJobList(Report report) {
		List<ReportScheduleJobListInformation> jobsInfos = new ArrayList<>();
		JobStore jobStore = getJobStore();
		JobFilterConfiguration filterConf = new JobFilterConfiguration();
        filterConf.setInActive(true);
        filterConf.setActive(true);
        filterConf.setJobType(ReportExecuteJob.class);
        
        ReportServerJobFilter reportFilter = new ReportServerJobFilter();
        reportFilter.setAnyUser();
        reportFilter.setInActive(true);
        reportFilter.setActive(true);
        reportFilter.setJobType(ReportExecuteJob.class);
        reportFilter.addReport(report);
        
        Collection<AbstractJob> jobs = jobStore.getJobsBy(filterConf, reportFilter);
        for (AbstractJob job: jobs) {
            ReportExecuteJob reportExecuteJob = (ReportExecuteJob) job;
            
            ReportScheduleJobListInformation jobInfo = new ReportScheduleJobListInformation();
            jobInfo.setReportDeleted(false);
            jobInfo.setJobId(reportExecuteJob.getId());
            jobInfo.setJobTitle(reportExecuteJob.getTitle());
            jobInfo.setJobDescription(reportExecuteJob.getDescription());
            jobInfo.setReportId(report.getId());
            jobInfo.setReportName(report.getName());
            jobInfo.setReportDescription(report.getDescription());
            jobInfo.setActive(reportExecuteJob.isActive());
            jobInfo.setLastScheduled(reportExecuteJob.getLastExecution());
            jobInfo.setNextScheduled(job.getTrigger().getNextScheduledFireTime());
            
            jobsInfos.add(jobInfo);
        }
		return jobsInfos;
	}
	
	@Override
	public List<ReportScheduleJobListInformation> getOwnReportJobList(Report report) {
		List<ReportScheduleJobListInformation> toCurrentUser = getReportJobListToCurrentUser(report);
		List<ReportScheduleJobListInformation> fromCurrentUser = getReportJobListFromCurrentUser(report);

		List<ReportScheduleJobListInformation> asList = new ArrayList<>();
		asList.addAll(fromCurrentUser);

		toCurrentUser.stream().forEach(job -> {
			if (asList.stream().noneMatch(currentJob -> currentJob.getJobId().equals(job.getJobId())))
				asList.add(job);

		});

		return asList;
	}
	
	@Override
	public List<ReportScheduleJobListInformation> getReportJobListFromCurrentUser(Report report) {
		ReportServerJobFilter fromCurrentUserFilter = new ReportServerJobFilter();
		fromCurrentUserFilter.setFromCurrentUser(true);
		return getBasicReportJobList(report, fromCurrentUserFilter);
	}
	
	@Override
	public List<ReportScheduleJobListInformation> getReportJobListToCurrentUser(Report report) {
		ReportServerJobFilter toCurrentUserFilter = new ReportServerJobFilter();
		toCurrentUserFilter.setToCurrentUser(true);
		return getBasicReportJobList(report, toCurrentUserFilter);
	}
	
	private List<ReportScheduleJobListInformation> getBasicReportJobList(Report report, ReportServerJobFilter reportFilter) {
		List<ReportScheduleJobListInformation> jobsInfos = new ArrayList<>();
		JobStore jobStore = getJobStore();
		JobFilterConfiguration filterConf = new JobFilterConfiguration();
        filterConf.setInActive(true);
        filterConf.setActive(true);
        filterConf.setJobType(ReportExecuteJob.class);
        
        reportFilter.setInActive(true);
        reportFilter.setActive(true);
        reportFilter.setJobType(ReportExecuteJob.class);
        reportFilter.addReport(report);
        
        Collection<AbstractJob> jobs = jobStore.getJobsBy(filterConf, reportFilter);
        for (AbstractJob job: jobs) {
            ReportExecuteJob reportExecuteJob = (ReportExecuteJob) job;
            
            ReportScheduleJobListInformation jobInfo = new ReportScheduleJobListInformation();
            jobInfo.setReportDeleted(false);
            jobInfo.setJobId(reportExecuteJob.getId());
            jobInfo.setJobTitle(reportExecuteJob.getTitle());
            jobInfo.setJobDescription(reportExecuteJob.getDescription());
            jobInfo.setReportId(report.getId());
            jobInfo.setReportName(report.getName());
            jobInfo.setReportDescription(report.getDescription());
            jobInfo.setActive(reportExecuteJob.isActive());
            jobInfo.setLastScheduled(reportExecuteJob.getLastExecution());
            jobInfo.setNextScheduled(job.getTrigger().getNextScheduledFireTime());
            
            jobsInfos.add(jobInfo);
        }
		return jobsInfos;
	}

	@Override
	public void assertJobExecutorChangeAllowed(User previousExecutor, User newExecutor) throws ViolatedSecurityException {
		/* Only scheduler administrators can change the job executor. */
		if (! securityServiceProvider.get().checkRights(SchedulingAdminSecurityTarget.class, Execute.class)) {
			
			if (null == previousExecutor && null == newExecutor)
				return; /* ok, no change */
			
			if (null == previousExecutor && null != newExecutor)
				if (authenticatorServiceProvider.get().getCurrentUser().equals(newExecutor))
					return; /* ok, new executor is current user */
				else
					throw new ViolatedSecurityException("Not enough permissions to change the scheduler job executor.");
			
			if (null != previousExecutor && null == newExecutor) 
				throw new ViolatedSecurityException("Not enough permissions to change the scheduler job executor.");
			
			if (null != previousExecutor && null != newExecutor)
				if (previousExecutor.equals(newExecutor))
					return; /* ok, no change. */
				else
					throw new ViolatedSecurityException("Not enough permissions to change the scheduler job executor.");
		}
	}

	@Override
	public void assertJobChangeAllowed(ReportExecuteJob job) throws ViolatedSecurityException {
		Report report = job.getReport();
		
		if ( ! securityServiceProvider.get().checkRights(SchedulingBasicSecurityTarget.class, Execute.class))
			throw new ViolatedSecurityException("Not enough permissions to change the scheduler job.");
		
		if ( ! securityServiceProvider.get().checkRights(report, SecurityServiceSecuree.class, Execute.class))
			throw new ViolatedSecurityException("Not enough permissions to change the scheduler job.");
	}

}
