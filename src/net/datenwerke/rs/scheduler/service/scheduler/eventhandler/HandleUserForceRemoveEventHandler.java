package net.datenwerke.rs.scheduler.service.scheduler.eventhandler;

import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.rs.scheduler.service.scheduler.jobs.ReportServerJob;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.filter.ReportServerJobFilter;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.rs.scripting.service.jobs.ScriptExecuteJob;
import net.datenwerke.rs.utils.eventbus.EventHandler;
import net.datenwerke.scheduler.service.scheduler.SchedulerService;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.eventlogger.jpa.ForceRemoveEntityEvent;
import net.datenwerke.security.service.security.exceptions.ViolatedSecurityException;
import net.datenwerke.security.service.usermanager.entities.User;

public class HandleUserForceRemoveEventHandler implements EventHandler<ForceRemoveEntityEvent> {

	private final SchedulerService schedulerService;
	private final AuthenticatorService authenticatorService;

	@Inject
	public HandleUserForceRemoveEventHandler(SchedulerService schedulerService, AuthenticatorService authenticatorService) {
		this.schedulerService = schedulerService;
		this.authenticatorService = authenticatorService;
	}

	@Override
	public void handle(ForceRemoveEntityEvent event) {
		User user = (User) event.getObject();

		removeExecutorFromActiveJobs(user, ReportExecuteJob.class);
		removeExecutorFromActiveJobs(user, ScriptExecuteJob.class);

	}

	private void removeExecutorFromActiveJobs(User user, Class<? extends AbstractJob> jobType) {
		ReportServerJobFilter filter = new ReportServerJobFilter();
		filter.setJobType(jobType);
		filter.setActive(true);
		filter.setInActive(false);
		filter.setFromUser(user);

		List<AbstractJob> jobs = schedulerService.getJobsBy(filter);
		if (null != jobs && !jobs.isEmpty()) {
			for (AbstractJob job : jobs) {
				ReportServerJob rsJob = (ReportServerJob) job;
				try {
					if (rsJob instanceof ReportExecuteJob)
						schedulerService.assertJobChangeAllowed((ReportExecuteJob)rsJob);
					
					schedulerService.assertJobExecutorChangeAllowed(rsJob.getExecutor(), null);
				} catch (ViolatedSecurityException e) {
					throw new ViolatedSecurityException(e.getMessage()+ " JobId: " + rsJob.getId(), e);
				}
				rsJob.setExecutor(null);
				/* Since we unschedule the job, we don't write the current user to the scheduledBy field. */
				schedulerService.unschedule(job);
			}
		}
	}

}
