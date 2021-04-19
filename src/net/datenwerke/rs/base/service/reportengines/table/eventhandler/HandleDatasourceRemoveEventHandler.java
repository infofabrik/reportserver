package net.datenwerke.rs.base.service.reportengines.table.eventhandler;

import java.util.Iterator;
import java.util.List;

import net.datenwerke.rs.base.service.reportengines.table.TableReportUtils;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.utils.eventbus.EventHandler;
import net.datenwerke.rs.utils.exception.exceptions.NeedForcefulDeleteException;
import net.datenwerke.security.service.eventlogger.jpa.RemoveEntityEvent;

import com.google.inject.Inject;

public class HandleDatasourceRemoveEventHandler implements
		EventHandler<RemoveEntityEvent> {

	private final TableReportUtils tableReportUtils;
	
	@Inject
	public HandleDatasourceRemoveEventHandler(TableReportUtils tableReportUtils) {
		/* store obejcts */
		this.tableReportUtils = tableReportUtils;
	}

	@Override
	public void handle(RemoveEntityEvent event) {
		DatasourceDefinition ds = (DatasourceDefinition) event.getObject();
		
		List<TableReport> reports = tableReportUtils.getReportsWithMetadataDatasource(ds);
		if(null != reports && ! reports.isEmpty()){
			Iterator<TableReport> it = reports.iterator();
			StringBuilder error = new StringBuilder("Datasource " + ds.getId() + " is used in table reports. Report Ids: " + it.next().getId());
			while(it.hasNext())
				error.append(", ").append(it.next().getId());
			
			throw new NeedForcefulDeleteException(error.toString());
		}
	}

}
