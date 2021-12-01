package net.datenwerke.rs.scheduler.service.scheduler.eventhandler;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.inject.Inject;

import net.datenwerke.rs.scheduler.service.scheduler.jobs.ReportServerJob;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.filter.ReportServerJobFilter;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.rs.scripting.service.jobs.ScriptExecuteJob;
import net.datenwerke.rs.utils.eventbus.EventHandler;
import net.datenwerke.rs.utils.exception.exceptions.NeedForcefulDeleteException;
import net.datenwerke.scheduler.service.scheduler.SchedulerService;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.eventlogger.jpa.RemoveEntityEvent;
import net.datenwerke.security.service.security.exceptions.ViolatedSecurityException;
import net.datenwerke.security.service.usermanager.entities.User;

public class HandleUserRemoveEventHandler implements EventHandler<RemoveEntityEvent> {

	private final SchedulerService schedulerService;
	private final AuthenticatorService authenticatorService;

	@Inject
	public HandleUserRemoveEventHandler(SchedulerService schedulerService, AuthenticatorService authenticatorService) {
		this.schedulerService = schedulerService;
		this.authenticatorService = authenticatorService;
	}

	@Override
	public void handle(RemoveEntityEvent event) {
		User user = (User) event.getObject();

		checkIfActiveJobs(user);

		handleReportExecutionJobs(user);
		handleScriptExecutionJobs(user);
	}

	private void checkIfActiveJobs(User user) {
		ReportServerJobFilter filter = new ReportServerJobFilter();
		filter.setJobType(ReportExecuteJob.class);
		filter.setActive(true);
		filter.setInActive(false);
		filter.setFromUser(user);

		StringBuilder error = new StringBuilder();
		boolean hasError = false;

		List<AbstractJob> jobs = schedulerService.getJobsBy(filter);
		if (null != jobs && !jobs.isEmpty()) {
			Iterator<AbstractJob> it = jobs.iterator();
			error.append("User " + user.getId() + " has active scheduled report execution jobs: Job Ids: "
					+ it.next().getId());
			while (it.hasNext())
				error.append(", ").append(it.next().getId());
			error.append(".");
			hasError = true;
		}

		filter.setJobType(ScriptExecuteJob.class);
		jobs = schedulerService.getJobsBy(filter);
		if (null != jobs && !jobs.isEmpty()) {
			Iterator<AbstractJob> it = jobs.iterator();
			error.append((hasError ? " " : "") + "User " + user.getId()
					+ " has active scheduled script execution jobs: Job Ids: " + it.next().getId());
			while (it.hasNext())
				error.append(", ").append(it.next().getId());
			error.append(".");
			hasError = true;
		}

		if (hasError)
			throw new NeedForcefulDeleteException(error.toString());
	}

	private void removeExecutorFromInactiveJobs(User user, Class<? extends AbstractJob> jobType) {

		/* remove from inactive */
		ReportServerJobFilter filter = new ReportServerJobFilter();
		filter.setJobType(jobType);
		filter.setFromUser(user);
		filter.setActive(false);
		filter.setInActive(true);
		List<AbstractJob> jobs = schedulerService.getJobsBy(filter);
		if (null != jobs && !jobs.isEmpty()) {
			for (AbstractJob job : jobs) {
				ReportServerJob rsJob = ((ReportServerJob) job);
				/* We don't check here if the user has permissions. The job is archived and if the user wants to
				 * reschedule the job, the checks are done there. We also don't set the current user as scheduledBy, since
				 * the job is not scheduled.
				 */
				rsJob.setExecutor(null);
				schedulerService.merge(job);
			}
		}
	}

	private void handleReportExecutionJobs(User user) {

		removeExecutorFromInactiveJobs(user, ReportExecuteJob.class);

		removeFromOwners(user);
		removeFromRecipients(user);
		removeFromScheduledBy(user);
	}
	
	private void removeFromScheduledBy(User user) {
		ReportServerJobFilter filter = new ReportServerJobFilter();
		filter.setJobType(ReportExecuteJob.class);
		filter.setActive(true);
		filter.setInActive(true);
		filter.setScheduledBy(user);
		List<AbstractJob> jobs = schedulerService.getJobsBy(filter);
		if (null != jobs && !jobs.isEmpty()) {
			for (AbstractJob job : jobs) {
				if (job.isActive()) { /* We check for rights only on active jobs. */
					try {
						if (job instanceof ReportExecuteJob)
							schedulerService.assertJobChangeAllowed((ReportExecuteJob) job);
					} catch (ViolatedSecurityException e) {
						throw new ViolatedSecurityException(e.getMessage()
								+ " User is in the \"scheduled by\" field in scheduler job with job id: " + job.getId(),
								e);
					}
				}
				((ReportExecuteJob) job).setScheduledBy(null);
				if (job.isActive())
					((ReportServerJob) job).setScheduledBy(authenticatorService.getCurrentUser());
				schedulerService.merge(job);
			}
		}
	}
	
	private void removeFromRecipients(User user) {
		ReportServerJobFilter filter = new ReportServerJobFilter();
		filter.setJobType(ReportExecuteJob.class);
		filter.setActive(true);
		filter.setInActive(true);
		filter.setFromUser(null);
		filter.setToUser(user);
		List<AbstractJob> jobs = schedulerService.getJobsBy(filter);
		if (null != jobs && !jobs.isEmpty()) {
			for (AbstractJob job : jobs) {
				List<User> recipients = ((ReportExecuteJob) job).getRecipients();
				Iterator<User> it = recipients.iterator();
				while (it.hasNext()) {
					if (user.equals(it.next())) {
						if (job.isActive()) { /* We check for rights only on active jobs. */
							try {
								if (job instanceof ReportExecuteJob)
									schedulerService.assertJobChangeAllowed((ReportExecuteJob)job);
							} catch (ViolatedSecurityException e) {
								throw new ViolatedSecurityException(e.getMessage() + " User is recipient in scheduler job with job id: " + job.getId(), e);
							}
						}
						it.remove();
						if (job.isActive())
							((ReportServerJob) job).setScheduledBy(authenticatorService.getCurrentUser());
					}
				}
				((ReportExecuteJob) job).setRecipients(recipients);
				schedulerService.merge(job);
			}
		}
	}
	
	private void removeFromOwners(User user) {
		ReportServerJobFilter filter = new ReportServerJobFilter();
		filter.setJobType(ReportExecuteJob.class);
		filter.setActive(true);
		filter.setInActive(true);
		filter.setFromUser(null);
		filter.setOwner(user);
		List<AbstractJob> jobs = schedulerService.getJobsBy(filter);
		if (null != jobs && !jobs.isEmpty()) {
			for (AbstractJob job : jobs) {
				Set<User> owners = new HashSet<User>(((ReportExecuteJob) job).getOwners());
				Iterator<User> it = owners.iterator();
				while (it.hasNext()) {
					if (user.equals(it.next())) {
						if (job.isActive()) { /* We check for rights only on active jobs. */
							try {
								if (job instanceof ReportExecuteJob)
									schedulerService.assertJobChangeAllowed((ReportExecuteJob)job);
							} catch (ViolatedSecurityException e) {
								throw new ViolatedSecurityException(e.getMessage() + " User is owner in scheduler job with job id: " + job.getId(), e);
							}
						}
						it.remove();
						
						if (job.isActive())
							((ReportServerJob) job).setScheduledBy(authenticatorService.getCurrentUser());
					}
				}
				((ReportExecuteJob) job).setOwners(owners);
				schedulerService.merge(job);
			}
		}
	}

	private void handleScriptExecutionJobs(User user) {
		removeExecutorFromInactiveJobs(user, ScriptExecuteJob.class);
	}
}
