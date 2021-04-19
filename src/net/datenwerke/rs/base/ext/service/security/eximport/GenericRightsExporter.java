package net.datenwerke.rs.base.ext.service.security.eximport;

import net.datenwerke.eximport.ex.entity.GenericEntityExporter;
import net.datenwerke.security.service.security.entities.GenericSecurityTargetEntity;

public class GenericRightsExporter extends GenericEntityExporter {

	public static final String EXPORTER_ID = "GenericRightsExporter";
	
	@Override
	public String getExporterId() {
		return EXPORTER_ID;
	}

	@Override
	protected Class<?>[] getExportableTypes() {
		return new Class<?>[]{GenericSecurityTargetEntity.class};
	}


}
