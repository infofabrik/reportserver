package net.datenwerke.eximport.ex.objectexporters;

import net.datenwerke.eximport.ex.ExportSupervisor;

public interface BasicObjectExporterFactory {

	public BasicObjectExporter create(ExportSupervisor exportSupervisor, String id, Object toExport);
	
}
