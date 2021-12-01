package net.datenwerke.rs.scheduler.service.scheduler.jobs.filter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report__;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob__;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.stores.jpa.filter.JobFilterConfiguration;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.usermanager.entities.User;
import net.datenwerke.security.service.usermanager.entities.User__;

@GenerateDto(
	dtoPackage = "net.datenwerke.rs.scheduler.client.scheduler.dto"
)
public class ReportServerJobFilter extends JobFilterConfiguration {

	@Inject
	static private Provider<AuthenticatorService> authenticatorServiceProvider; 
	
	@ExposeToClient
	private boolean toCurrentUser = false;
	
	@ExposeToClient
	private boolean fromCurrentUser = false;
	
	@ExposeToClient
	private Set<Long> reports = new HashSet<Long>();
	
	@ExposeToClient
	private User toUser = null;
	
	@ExposeToClient
	private User fromUser = null;
	
	@ExposeToClient
	private User scheduledBy = null;
	
	@ExposeToClient
	private String reportId = null;
	
	@ExposeToClient
	private User owner;
	
	public void setAnyUser(){
		fromCurrentUser = false;
		toCurrentUser = false;
		toUser = null;
		fromUser = null;
		owner = null;
		scheduledBy = null;
	}
	
	public boolean isAnyUser() {
		return ! fromCurrentUser && ! toCurrentUser &&  null == toUser & null == fromUser && null == owner && null == scheduledBy;
	}

	public void setToCurrentUser(boolean toCurrentUser) {
		this.toCurrentUser = toCurrentUser;
	}

	public boolean isToCurrentUser() {
		return toCurrentUser;
	}

	public void setFromCurrentUser(boolean fromCurrentUser) {
		this.fromCurrentUser = fromCurrentUser;
	}

	public boolean isFromCurrentUser() {
		return fromCurrentUser;
	}
	
	public Set<Long> getReports() {
		return reports;
	}
	
	public void setReports(Set<Long> reports) {
		this.reports = reports;
	}
	
	public void addReport(Long reportId) {
		this.reports.add(reportId);
	}

	public void addReport(Report report) {
		this.reports.add(report.getId());
	}

	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}

	public User getFromUser() {
		return fromUser;
	}

	public void setToUser(User toUser) {
		this.toUser = toUser;
	}

	public User getToUser() {
		return toUser;
	}
	
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public User getOwner() {
		return owner;
	}
	
	public String getReportId() {
		return reportId;
	}
	
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	
	public User getScheduledBy() {
		return scheduledBy;
	}
	
	public void setScheduledBy(User scheduledBy) {
		this.scheduledBy = scheduledBy;
	}
	
	@Override
	public boolean validateSortField(String sortField) {
		if("reportId".equals(sortField))
			return true;
		if("reportName".equals(sortField))
			return true;
		if("executorId".equals(sortField))
			return true;
		if("executorLastName".equals(sortField))
			return true;
		
		return super.validateSortField(sortField);
	}

	@Override
	public Expression<?> transformSortField(String sortField, CriteriaBuilder builder, CriteriaQuery<AbstractJob> cQuery, Root<? extends AbstractJob> root) {
		if("reportId".equals(sortField))
			return root.join(ReportExecuteJob__.report, JoinType.LEFT).get(Report__.id);
		if("reportName".equals(sortField))
			return root.join(ReportExecuteJob__.report, JoinType.LEFT).get(Report__.name);
		if("executorId".equals(sortField))
			return root.join(ReportExecuteJob__.executor, JoinType.LEFT).get(User__.id);
		if("executorLastName".equals(sortField))
			return root.join(ReportExecuteJob__.executor, JoinType.LEFT).get(User__.lastname);
		
		return super.transformSortField(sortField, builder, cQuery, root);
	}

	
	@Override
	public List<Predicate> prepareCriteriaQuery(CriteriaBuilder builder,
			CriteriaQuery<?> cQuery, Root<? extends AbstractJob> root) {
		List<Predicate> predicates = super.prepareCriteriaQuery(builder, cQuery, root);
		
		if(fromCurrentUser){
			/* Current user is executor OR current user is owner */
			AuthenticatorService authService = authenticatorServiceProvider.get();
			User currentUser = authService.getCurrentUser();
			
			Join<ReportExecuteJob, User> executor = root.join(ReportExecuteJob__.executor, JoinType.LEFT);
			Predicate currentIsExecutor = executor.in(currentUser);
			
			Join<ReportExecuteJob, User> owners = root.join(ReportExecuteJob__.owners);
			Predicate currentIsOwner = owners.in(currentUser);
			
			predicates.add(builder.or(currentIsExecutor, currentIsOwner));
		} else if (null != fromUser && null != owner) {
			if (fromUser.equals(owner)) {
				/* Given user is executor OR given user is owner. */
				Join<ReportExecuteJob, User> executor = root.join(ReportExecuteJob__.executor, JoinType.LEFT);
				Predicate isExecutor = executor.in(fromUser);
				
				Join<ReportExecuteJob, User> owners = root.join(ReportExecuteJob__.owners);
				Predicate isOwner = owners.in(owner);
				
				predicates.add(builder.or(isExecutor, isOwner));
			} else {
				/* Given user is executor AND given owner is owner. */
				Join<ReportExecuteJob, User> executor = root.join(ReportExecuteJob__.executor, JoinType.LEFT);
				Predicate isExecutor = executor.in(fromUser);
				
				Join<ReportExecuteJob, User> owners = root.join(ReportExecuteJob__.owners);
				Predicate isOwner = owners.in(owner);
				
				predicates.add(isExecutor);
				predicates.add(isOwner);
			}
		} else if (null != fromUser) {
			/* Given user is executor. */
			Join<ReportExecuteJob, User> user = root.join(ReportExecuteJob__.executor);
			predicates.add(user.in(fromUser));
		} else if (null != owner) {
			/* Given owner is owner. */
			Join<ReportExecuteJob, User> owners = root.join(ReportExecuteJob__.owners);
			predicates.add(owners.in(owner));
		}
		
		if(toCurrentUser){
			AuthenticatorService authService = authenticatorServiceProvider.get();
			User currentUser = authService.getCurrentUser();
			
			Join<ReportExecuteJob, User> rcpt = root.join(ReportExecuteJob__.rcptIDs);
			predicates.add(rcpt.in(currentUser.getId()));
		} else if(null != toUser){
			Join<ReportExecuteJob, User> rcpt = root.join(ReportExecuteJob__.rcptIDs);
			predicates.add(rcpt.in(toUser.getId()));
		}
		
		if(null != fromUser && fromCurrentUser)
			throw new IllegalArgumentException();
		if(null != toUser && toCurrentUser)
			throw new IllegalArgumentException();
		
		if(null != getReports() && ! getReports().isEmpty())
			predicates.add(root.join(ReportExecuteJob__.report).get(Report__.id).in(getReports()));
		
		if(null != reportId){
			String query = reportId.replace("?", "_").replace("*", "%");
			if(! "".equals(query.trim()))
				predicates.add(builder.like(root.join(ReportExecuteJob__.report).get(Report__.id).as(String.class), query));
		}
		
		if (null != scheduledBy) {
			predicates.add(root.join(ReportExecuteJob__.scheduledBy).in(scheduledBy.getId()));
		}
		
		return predicates;
	}

}
