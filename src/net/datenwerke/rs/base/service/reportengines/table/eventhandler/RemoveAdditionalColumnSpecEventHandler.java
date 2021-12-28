package net.datenwerke.rs.base.service.reportengines.table.eventhandler;

import com.google.inject.Inject;

import net.datenwerke.rs.base.service.reportengines.table.TableReportUtils;
import net.datenwerke.rs.base.service.reportengines.table.entities.AdditionalColumnSpec;
import net.datenwerke.rs.base.service.reportengines.table.entities.ColumnReference;
import net.datenwerke.rs.utils.eventbus.EventHandler;
import net.datenwerke.security.service.eventlogger.jpa.RemoveEntityEvent;

public class RemoveAdditionalColumnSpecEventHandler implements EventHandler<RemoveEntityEvent> {

	private final TableReportUtils utils;
	
	@Inject
	public RemoveAdditionalColumnSpecEventHandler(TableReportUtils utils) {
		this.utils = utils;
	}

	@Override
	public void handle(RemoveEntityEvent event) {
		AdditionalColumnSpec spec = (AdditionalColumnSpec) event.getObject();
		
		for(ColumnReference ref : utils.getColumnReferencesFor(spec)){
			ref.setReference(null);
			utils.merge(ref);
		}
	}

}
