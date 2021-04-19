package net.datenwerke.rs.uservariables.service.uservariables.eximport;

import net.datenwerke.eximport.im.entity.GenericEntityImporter;

public class UserVariableImporter extends GenericEntityImporter {

	@Override
	public Class<?>[] getRecognizedExporters() {
		return new Class<?>[]{UserVariableExporter.class};
	}
	
}
