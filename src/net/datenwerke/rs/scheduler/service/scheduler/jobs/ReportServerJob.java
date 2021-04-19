package net.datenwerke.rs.scheduler.service.scheduler.jobs;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.scheduler.service.scheduler.SchedulerService;
import net.datenwerke.scheduler.service.scheduler.jobs.BaseJob;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.entities.User;

/**
 * 
 *
 */
@MappedSuperclass
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class ReportServerJob extends BaseJob {

	@Transient
	@Inject protected Provider<AuthenticatorService> authenticatorServiceProvider;
	
	@Transient
	@Inject protected UserManagerService userService;
	
	@ManyToOne
	private User executor;
	
	@ManyToOne
	private User scheduledBy;
	
	public User getScheduledBy() {
		return scheduledBy;
	}
	
	public void setScheduledBy(User scheduledBy) {
		this.scheduledBy = scheduledBy;
	}
	
	public User getExecutor() {
		return executor;
	}

	/**
	 * If no executor is set, the currently authenticated use is used.
	 * @param executor
	 */
	public void setExecutor(User executor) {
		this.executor = executor;
	}
	
	public void setExecutor() {
		this.executor = authenticatorServiceProvider.get().getCurrentUser();
	}

}
