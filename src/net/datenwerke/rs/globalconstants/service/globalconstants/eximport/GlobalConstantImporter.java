package net.datenwerke.rs.globalconstants.service.globalconstants.eximport;

import net.datenwerke.eximport.im.entity.GenericEntityImporter;

public class GlobalConstantImporter extends GenericEntityImporter {

	@Override
	public Class<?>[] getRecognizedExporters() {
		return new Class<?>[]{GlobalConstantExporter.class};
	}
	
}
