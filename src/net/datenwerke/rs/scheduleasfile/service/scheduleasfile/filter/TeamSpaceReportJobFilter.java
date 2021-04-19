package net.datenwerke.rs.scheduleasfile.service.scheduleasfile.filter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.scheduleasfile.service.scheduleasfile.action.ScheduleAsFileAction;
import net.datenwerke.rs.scheduleasfile.service.scheduleasfile.action.ScheduleAsFileAction_;
import net.datenwerke.rs.scheduler.server.scheduler.interfaces.AuthoritativeSchedulerCriteria;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob__;
import net.datenwerke.rs.teamspace.service.teamspace.TeamSpaceService;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.stores.jpa.filter.JobFilterCriteria;

import com.google.inject.Inject;

@GenerateDto(
	dtoPackage = "net.datenwerke.rs.scheduleasfile.client.scheduleasfile.filter.dto"
)
public class TeamSpaceReportJobFilter implements JobFilterCriteria, AuthoritativeSchedulerCriteria {

	@ExposeToClient
	private Long teamspaceId;

	@Transient @Inject
	private TeamSpaceService tsService;
	
	public void setTeamspaceId(Long teamspaceId) {
		this.teamspaceId = teamspaceId;
	}

	public Long getTeamspaceId() {
		return teamspaceId;
	}
	
	
	@Override
	public List<Predicate> prepareCriteriaQuery(CriteriaBuilder builder,
			CriteriaQuery<?> cQuery, Root<? extends AbstractJob> root) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(null != teamspaceId){
			Join<ReportExecuteJob, ScheduleAsFileAction> actions = root.join(ReportExecuteJob__.actions, JoinType.LEFT);
			predicates.add(actions.get(ScheduleAsFileAction_.teamspaceId).in(teamspaceId));
		} 
		
		return predicates;
	}

	@Override
	public boolean isAuthoritative() {
		if(null != teamspaceId){
			TeamSpace ts = tsService.getTeamSpaceById(teamspaceId);
			if(null != ts && tsService.mayAccess(ts))
				return true;
		}
		return false;
	}
	
	
}
