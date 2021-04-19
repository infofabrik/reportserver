package net.datenwerke.rs.base.service.reportengines.table.entities.supervisor;

import java.util.Collection;

import net.datenwerke.rs.base.service.reportengines.table.TableReportUtils;
import net.datenwerke.rs.base.service.reportengines.table.entities.AdditionalColumnSpec;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.PreFilter;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.supervisor.Dto2ReportSupervisor;

import com.google.inject.Inject;

public class Dto2TableReportSupervisor extends Dto2ReportSupervisor {

	private final TableReportUtils trus;

	@Inject
	public Dto2TableReportSupervisor(
			TableReportUtils trus
		){
		this.trus = trus;
	}
	
	@Override
	public void enclosedObjectsRemovedFromCollection(ReportDto dto,
			Report poso, Collection<?> objectCollection, String fieldname) {
		super.enclosedObjectsRemovedFromCollection(dto, poso, objectCollection,
				fieldname);
		
		if(null == objectCollection)
			return;
		
		for(Object o : objectCollection){
			if(o instanceof AdditionalColumnSpec)
				trus.remove((AdditionalColumnSpec)o);
			else if(o instanceof Column)
				trus.remove((Column)o);
			if(o instanceof PreFilter)
				trus.remove((PreFilter)o);
		}
	}
}
