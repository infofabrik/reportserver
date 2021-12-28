package net.datenwerke.rs.scheduler.service.scheduler.eventhandler;

import java.util.Iterator;
import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.filter.ReportServerJobFilter;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.rs.utils.eventbus.EventHandler;
import net.datenwerke.rs.utils.exception.exceptions.NeedForcefulDeleteException;
import net.datenwerke.scheduler.service.scheduler.SchedulerService;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.security.service.eventlogger.jpa.RemoveEntityEvent;

public class HandleReportRemoveEventHandler implements
		EventHandler<RemoveEntityEvent> {

	@Inject private SchedulerService scheduler;
	
	@Override
	public void handle(RemoveEntityEvent event) {
		Report report = (Report) event.getObject();
		
		ReportServerJobFilter filter = new ReportServerJobFilter();
		filter.setJobType(ReportExecuteJob.class);
		filter.setActive(true);
		filter.setInActive(false);
		filter.setAnyUser();
		filter.addReport(report);
		
		List<AbstractJob> jobs = scheduler.getJobsBy(filter);
		if(null != jobs && ! jobs.isEmpty()){
			Iterator<AbstractJob> it = jobs.iterator();
			StringBuilder error = new StringBuilder("Report " + report.getId() + " is scheduled. Job Ids: " + it.next().getId());
			while(it.hasNext())
				error.append(", ").append(it.next().getId());
			
			throw new NeedForcefulDeleteException(error.toString());
		}
		
		/* take care of inactive jobs */
		filter.setActive(false);
		filter.setInActive(true);
		jobs = scheduler.getJobsBy(filter);
		if(null != jobs && ! jobs.isEmpty()){
			for(AbstractJob job : jobs){
				((ReportExecuteJob)job).setReport(null);
				scheduler.unschedule(job);
			}
		}
	}

}
