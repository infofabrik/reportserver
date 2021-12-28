package net.datenwerke.rs.base.service.parameters.eventhandlers;

import java.util.Iterator;
import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.rs.base.service.parameters.datasource.DatasourceParameterDefinition;
import net.datenwerke.rs.base.service.parameters.datasource.DatasourceParameterService;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.reportmanager.ReportParameterService;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.utils.eventbus.EventHandler;
import net.datenwerke.rs.utils.exception.exceptions.NeedForcefulDeleteException;
import net.datenwerke.security.service.eventlogger.jpa.RemoveEntityEvent;

public class HandleDatasourceRemoveEventHandler implements
		EventHandler<RemoveEntityEvent> {

	private final DatasourceParameterService datasourceParameterService;
	private final ReportParameterService reportParameterService;
	
	@Inject
	public HandleDatasourceRemoveEventHandler(
		DatasourceParameterService DatasourceParameterService,
		ReportParameterService reportParameterService ) {
		
		/* store obejcts */
		this.datasourceParameterService = DatasourceParameterService;
		this.reportParameterService = reportParameterService;
	}

	@Override
	public void handle(RemoveEntityEvent event) {
		DatasourceDefinition ds = (DatasourceDefinition) event.getObject();
		
		List<DatasourceParameterDefinition> parameters = datasourceParameterService.getParametersWithDatasource(ds);
		if(null != parameters && ! parameters.isEmpty()){
			Iterator<DatasourceParameterDefinition> it = parameters.iterator();
			Report report =  reportParameterService.getReportWithParameter(it.next());
			StringBuilder error = new StringBuilder();
			if(null != report)
				error.append("Datasource " + ds.getId() + " is used in parameters. Report Ids: " + report.getId());
			while(it.hasNext()){
				report =  reportParameterService.getReportWithParameter(it.next());
				if(null != report)
					error.append(", ").append(report.getId());
			}
			
			throw new NeedForcefulDeleteException(error.toString());
		}
	}
	
	

}
