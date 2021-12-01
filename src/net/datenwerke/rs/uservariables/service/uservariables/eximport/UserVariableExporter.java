package net.datenwerke.rs.uservariables.service.uservariables.eximport;

import net.datenwerke.eximport.ex.entity.GenericEntityExporter;
import net.datenwerke.rs.uservariables.service.uservariables.entities.UserVariableDefinition;
import net.datenwerke.rs.uservariables.service.uservariables.entities.UserVariableInstance;

public class UserVariableExporter extends GenericEntityExporter {

	private static final String EXPORTER_ID = "UserVariableExporter";
	
	@Override
	public String getExporterId() {
		return EXPORTER_ID;
	}
	
	
	@Override
	protected Class<?>[] getExportableTypes() {
		return new Class<?>[]{UserVariableDefinition.class, UserVariableInstance.class};
	}


}
