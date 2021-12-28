package net.datenwerke.rs.core.service.reportmanager.eventhandler;

import java.util.Iterator;
import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.utils.eventbus.EventHandler;
import net.datenwerke.rs.utils.exception.exceptions.NeedForcefulDeleteException;
import net.datenwerke.security.service.eventlogger.jpa.RemoveEntityEvent;

public class HandleDatasourceRemoveEventHandler implements
		EventHandler<RemoveEntityEvent> {

	@Inject private ReportService reportService;
	
	@Override
	public void handle(RemoveEntityEvent event) {
		DatasourceDefinition ds = (DatasourceDefinition) event.getObject();
		
		List<Report> reports = reportService.getReportsByDatasource(ds);
		if(null != reports && ! reports.isEmpty()){
			Iterator<Report> it = reports.iterator();
			StringBuilder error = new StringBuilder("Datasource " + ds.getId() + " is used in reports. Report Ids: " + it.next().getId());
			while(it.hasNext())
				error.append(", ").append(it.next().getId());
			
			throw new NeedForcefulDeleteException(error.toString());
		}
	}

}
