package net.datenwerke.scheduler.service.scheduler;

import java.util.List;

import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.dto.ReportScheduleJobListInformation;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractTrigger;
import net.datenwerke.scheduler.service.scheduler.exceptions.SchedulerStartupException;
import net.datenwerke.scheduler.service.scheduler.stores.JobStore;
import net.datenwerke.scheduler.service.scheduler.stores.jpa.filter.JobFilterConfiguration;
import net.datenwerke.security.service.security.exceptions.ViolatedSecurityException;
import net.datenwerke.security.service.usermanager.entities.User;

public interface SchedulerService {

	
	public void start() throws SchedulerStartupException;

	public boolean isActive();
	public boolean isShutdown();
	public void shutdown();
	public List<Runnable> shutdownNow();
	boolean isTerminated();
	
	public void enable();
	public void disable();
	public boolean isEnabled();

	JobStore getJobStore();

	public void schedule(AbstractJob job, AbstractTrigger trigger);
	public void unschedule(AbstractJob job);
	public void remove(AbstractJob job);

	public AbstractJob getJobById(Long object);

	public void schedule(AbstractJob job, AbstractTrigger trigger, AbstractJob previous);

	public void merge(AbstractJob job);

	public SchedulerDaemon getDeamon();
	
	public List<AbstractJob> getJobsBy(JobFilterConfiguration filterConfig);

	void forceRemove(AbstractJob job);

	boolean isWatchdogActive();

	void restart() throws SchedulerStartupException;

	void shutdownWatchdog();

	public boolean isOrderdShutdown();

	void clearErrorState(AbstractJob job);

	void restartWatchdog();

	void startWatchdog();

	List<ReportScheduleJobListInformation> getReportJobList(Report report);
	
	void assertJobExecutorChangeAllowed(User previousExecutor, User newExecutor) throws ViolatedSecurityException;
	
	void assertJobChangeAllowed(ReportExecuteJob job) throws ViolatedSecurityException;

	List<ReportScheduleJobListInformation> getOwnReportJobList(Report report);

	List<ReportScheduleJobListInformation> getReportJobListFromCurrentUser(Report report);

	List<ReportScheduleJobListInformation> getReportJobListToCurrentUser(Report report);
	
}
