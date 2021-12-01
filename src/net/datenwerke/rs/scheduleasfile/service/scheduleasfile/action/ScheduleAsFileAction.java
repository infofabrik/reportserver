package net.datenwerke.rs.scheduleasfile.service.scheduleasfile.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.compiledreportstore.CompiledReportStoreService;
import net.datenwerke.rs.compiledreportstore.entities.PersistentCompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.scheduleasfile.service.scheduleasfile.entities.ExecutedReportFileReference;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.rs.teamspace.service.teamspace.TeamSpaceService;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpaceRole;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.TsDiskService;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.AbstractTsDiskNode;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskFolder;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskRoot;
import net.datenwerke.rs.utils.juel.SimpleJuel;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractAction;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.exceptions.ActionExecutionException;
import net.datenwerke.security.service.security.exceptions.ViolatedSecurityException;

import com.google.inject.Inject;
import com.google.inject.Provider;

@Entity
@Table(name="SCHED_ACTION_AS_FILE")
@Inheritance(strategy=InheritanceType.JOINED)
public class ScheduleAsFileAction extends AbstractAction {

	@Transient @Inject private HookHandlerService hookHandler;
	@Transient @Inject private Provider<SimpleJuel> simpleJuelProvider;
	@Transient @Inject private TsDiskService tsDiskService;
	@Transient @Inject private TeamSpaceService tsService;
	@Transient @Inject private CompiledReportStoreService compiledReportService;
	@Transient @Inject private Provider<EntityManager> entityManagerProvider ;
	
	@Transient private TeamSpace teamspace;
	@Transient private AbstractTsDiskNode folder;
	@Transient private Report report;
	
	private String name;
	private String description;
	private Long folderId;
	private Long teamspaceId;
	
	@Transient private ExecutedReportFileReference reference;
	
	@Override
	public void execute(AbstractJob job) throws ActionExecutionException {
		if(! (job instanceof ReportExecuteJob))
			throw new ActionExecutionException("No idea what job that is");
		
		ReportExecuteJob rJob = (ReportExecuteJob) job;
		
		CompiledReport compiledReport = rJob.getExecutedReport();
		report = rJob.getReport();
		
		PersistentCompiledReport pReport = compiledReportService.toPersistenReport(compiledReport, report);
		
		reference = new ExecutedReportFileReference();
		reference.setCompiledReport(pReport);
		reference.setOutputFormat(rJob.getOutputFormat());
		reference.setDescription(description);
		
		SimpleJuel juel = simpleJuelProvider.get();
		juel.addReplacement("now", new SimpleDateFormat("yyyyMMddhhmm").format(Calendar.getInstance().getTime()));
		String parsedName = null == name ? "" : juel.parse(name);
		reference.setName(parsedName);
		
		folder = tsDiskService.getNodeById(folderId);
		if(null == folder)
			throw new ActionExecutionException("could not load folder with id: " + folderId + ". Was it deleted?");
			
//			EntityManager em = entityManagerProvider.get();
//			em.lock(folder, LockModeType.PESSIMISTIC_WRITE);	
		teamspace = tsDiskService.getTeamSpaceFor(folder);
		
		if(null == teamspace)
			throw new ActionExecutionException("could not load teamspace for folder with id: " + folderId);
		
		if(!tsService.hasRole(rJob.getExecutor(), teamspace, TeamSpaceRole.USER))
			throw new ViolatedSecurityException("user " + rJob.getExecutor().getId() + " has insufficient rights to writ to teamspace " + teamspace.getId());
		
		if(null == folder || (! (folder instanceof TsDiskRoot ) && !(folder instanceof TsDiskFolder)))
			throw new ActionExecutionException("could not load folder with id: " + folderId);
		
		folder.addChild(reference);
		
		tsDiskService.persist(reference);
		tsDiskService.merge(folder);
	}
	
	public ExecutedReportFileReference getExecutedReportFileReference() {
		return reference;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getFolderId() {
		return folderId;
	}

	public void setFolderId(Long folderId) {
		this.folderId = folderId;
	}

	public Long getTeamspaceId() {
		return teamspaceId;
	}
	
	public void setTeamspaceId(Long teamspaceId) {
		this.teamspaceId = teamspaceId;
	}
	
	public TeamSpace getTeamspace() {
		return teamspace;
	}
	
	public AbstractTsDiskNode getFolder() {
		return folder;
	}
	
	public Report getReport() {
		return report;
	}
}
