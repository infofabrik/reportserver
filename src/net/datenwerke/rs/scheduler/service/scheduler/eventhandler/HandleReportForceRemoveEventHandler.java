package net.datenwerke.rs.scheduler.service.scheduler.eventhandler;

import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.filter.ReportServerJobFilter;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.rs.utils.eventbus.EventHandler;
import net.datenwerke.scheduler.service.scheduler.SchedulerService;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.security.service.eventlogger.jpa.ForceRemoveEntityEvent;

public class HandleReportForceRemoveEventHandler implements
		EventHandler<ForceRemoveEntityEvent> {

	@Inject private SchedulerService scheduler;
	
	@Override
	public void handle(ForceRemoveEntityEvent event) {
		Report report = (Report) event.getObject();
		
		ReportServerJobFilter filter = new ReportServerJobFilter();
		filter.setJobType(ReportExecuteJob.class);
		filter.setActive(true);
		filter.setInActive(false);
		filter.setAnyUser();
		filter.addReport(report);
		
		List<AbstractJob> jobs = scheduler.getJobsBy(filter);
		if(null != jobs && ! jobs.isEmpty()){
			for(AbstractJob job : jobs){
				((ReportExecuteJob)job).setReport(null);
				scheduler.unschedule(job);
			}
		}
	}

}
