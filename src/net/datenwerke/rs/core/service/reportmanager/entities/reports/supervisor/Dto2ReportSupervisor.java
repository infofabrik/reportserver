package net.datenwerke.rs.core.service.reportmanager.entities.reports.supervisor;

import java.util.Collection;

import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisor;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;

public class Dto2ReportSupervisor implements Dto2PosoSupervisor<ReportDto, Report> {

	@Override
	public void enclosedObjectsRemovedFromCollection(ReportDto dto,
			Report poso, Collection<?> objectCollection, String fieldname) {
		if(null == objectCollection)
			return;
		
		for(Object o : objectCollection){
			if(o instanceof ParameterInstance || o instanceof ParameterDefinition<?>)
				throw new IllegalArgumentException("Definition or instances should not be removed through merges");
		}
	}

	@Override
	public void referencedObjectRemoved(ReportDto dto, Report poso,
			Object removedProperty, Object replacement, String fieldname) {
		
	}

	@Override
	public void enclosedObjectRemoved(ReportDto dto, Report poso,
			Object removedProperty, Object replacement, String fieldname) {
		if(removedProperty instanceof DatasourceContainer)
			throw new IllegalArgumentException("Datasourcecontainers should not be allowed to be reomved");
	}

}
