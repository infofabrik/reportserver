package net.datenwerke.rs.scheduler.service.scheduler.jobs.report;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.AssociationOverride;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.inject.Inject;

import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.ReportServerJob;
import net.datenwerke.rs.scheduler.service.scheduler.locale.SchedulerMessages;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.entities.history.JobEntry;
import net.datenwerke.scheduler.service.scheduler.exceptions.JobExecutionException;
import net.datenwerke.scheduler.service.scheduler.jobs.BaseJob__;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.SecurityServiceSecuree;
import net.datenwerke.security.service.security.exceptions.ViolatedSecurityException;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.security.rights.Right;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;
import net.datenwerke.security.service.usermanager.entities.User;

/**
 * 
 *
 */
@Entity
@Table(name="SCHED_EXECUTE_REPORT_JOB")
@Inheritance(strategy=InheritanceType.JOINED)
@AssociationOverride(name=BaseJob__.baseProperties, 
                     joinTable=@JoinTable(name="SCHED_REP_EXEC_JOB_2_PROP"))
public class ReportExecuteJob extends ReportServerJob {

	private final static String EXPORT_CONFIG_KEY = "exportConfigurations";
	
	@Inject @Transient private ReportExecutorService reportExecutor;
	
	@Inject @Transient private SecurityService securityService;
	
	@JoinTable(name="SCHED_JOB_2_OWNER")
	@ManyToMany
	private Set<User> owners = new HashSet<>();
	
	@ManyToOne
	private Report report; 
	
	private String outputFormat;
	
	@JoinTable(name="SCHED_REP_EXEC_JOB_2_RCPT", joinColumns=@JoinColumn(name="report_execute_job_id"))
	@ElementCollection(fetch=FetchType.EAGER)
	private Set<Long> rcptIDs = new HashSet<Long>();
	
	@Transient
	private CompiledReport executedReport;
	
	@PrePersist @PreUpdate
	private final void prePersist(){
		if(null == owners)
			owners = new HashSet<>();
		if(null != getExecutor()){
			addOwner(getExecutor());
		} 
	}
	
	public void setRecipients(List<User> recipients){
		rcptIDs.clear();
		for(AbstractUserManagerNode recipient : recipients){
			if(recipient instanceof User)
				rcptIDs.add(recipient.getId());
		}
	}
	
	public List<Long> getRecipientsIds(){
		return new ArrayList<Long>(rcptIDs);
	}
	
	public List<User> getRecipients(){
		/* combine them */
		Set<Long> ids = new HashSet<Long>(rcptIDs);
		return new ArrayList<User>(userService.getUsers(ids, true));
	}

	/**
	 * Set the report instance to use.
	 * @param report
	 */
	public void setReport(Report report){
		this.report = report;
	}

	/**
	 * Sets the ouput format to use.
	 * @param outputFormat
	 */
	public void setOutputFormat(String outputFormat) {
		this.outputFormat = outputFormat;
	}
	
	public void setExportConfiguration(List<ReportExecutionConfig> exportConfiguration) {
		setComplexProperty(EXPORT_CONFIG_KEY, exportConfiguration);
	}
	
	public List<ReportExecutionConfig> getExportConfiguration() {
		List<ReportExecutionConfig> list = getComplexProperty(EXPORT_CONFIG_KEY);
		if(null == list)
			return new ArrayList<ReportExecutionConfig>();
		return list;
	}
	
	@Override
	protected void doExecute() throws JobExecutionException {
		Report report = getReport();
		if(null == report)
			throw new JobExecutionException("Could not find report with id: " + getReportId());
		
		/* execute report */
		try {
			try{
				authenticatorServiceProvider.get().setAuthenticatedInThread(getExecutor().getId());
				
				assertOwnersHaveReportRights(getOwners(), report, Execute.class);
				
				executedReport = reportExecutor.execute(report, getExecutor(), outputFormat, getExportConfiguration().toArray(new ReportExecutionConfig[]{}));
			}finally{
				authenticatorServiceProvider.get().logoffUserInThread();
			}
		} catch (ReportExecutorException e) {
			throw new JobExecutionException(e);
		} catch (ViolatedSecurityException e) {
			throw new JobExecutionException(e);
		}
	}
	
	private void assertOwnersHaveReportRights(Set<User> owners, Report report, Class<? extends Right>... rights) {
		Iterator<User> ownerIt = owners.iterator();
		
		Set<User> ownersMissingRights = new HashSet<>();
		while (ownerIt.hasNext()) {
			User owner = ownerIt.next();
			if (! securityService.checkRights(owner, report, SecurityServiceSecuree.class, rights)) {
				ownersMissingRights.add(owner);
			}
		}
		if (! ownersMissingRights.isEmpty())
			throw new ViolatedSecurityException(SchedulerMessages.INSTANCE.errorOwnersMissingRights() + " " + ownersMissingRights);
	}
	
	public Report getReport(){
		return report;
	}
	
	public CompiledReport getExecutedReport(){
		return executedReport;
	}


	@Override
	public void adjustJobEntryForFailure(JobEntry jobEntry) {
		jobEntry.addHistoryProperty("reportId", ""+(null != report ? report.getId() : "null"));
	}

	public String getOutputFormat() {
		return outputFormat;
	}
	
	public Long getReportId() {
		return null != report ? report.getId() : null;
	}

	@Override
	public void copyTransientFieldsFrom(AbstractJob job) {
		if(job instanceof ReportExecuteJob)
			this.executedReport = ((ReportExecuteJob) job).executedReport;
	}
	
	public Set<User> getOwners() {
		return owners;
	}
	public void setOwners(Set<User> owners) {
		if(null == this.owners)
			this.owners = new HashSet<>();
		this.owners.clear();
		if(null != owners)
			this.owners.addAll(owners);
	}
	public void addOwner(User owner) {
		if(null == this.owners)
			this.owners = new HashSet<>();
		if(! this.owners.contains(owner))
			this.owners.add(owner);
	}

}
