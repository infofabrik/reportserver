package net.datenwerke.rs.base.service.reportengines.table.eventhandler;

import net.datenwerke.rs.base.service.reportengines.table.TableReportUtilsImpl;
import net.datenwerke.rs.base.service.reportengines.table.entities.AdditionalColumnSpec;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.utils.eventbus.EventHandler;
import net.datenwerke.security.service.eventlogger.jpa.JpaEvent;
import net.datenwerke.security.service.eventlogger.jpa.MergeEntityEvent;
import net.datenwerke.security.service.eventlogger.jpa.PersistEntityEvent;

import com.google.inject.Inject;

public class HandleTableReportStoredEventHandler implements EventHandler<JpaEvent> {

	private final TableReportUtilsImpl service;
	
	@Inject
	public HandleTableReportStoredEventHandler(TableReportUtilsImpl service) {
		this.service = service;
	}

	@Override
	public void handle(JpaEvent event) {
		if(! (event instanceof PersistEntityEvent) && !(event instanceof MergeEntityEvent))
			return;
		
		Object obj = event.getObject();
		if(! (obj instanceof TableReport))
			return;
		
		TableReport report = (TableReport) obj;
		
		/* persist additional columns */
		if(null != report.getAdditionalColumns())
			for(AdditionalColumnSpec spec : report.getAdditionalColumns())
				if(null == spec.getId())
					service.persist(spec);
		
		/* persist columns */
		if(null != report.getAdditionalColumns())
			for(Column col : report.getColumns())
				if(null == col.getId())
					service.persist(col);
	}



}
