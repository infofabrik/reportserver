package net.datenwerke.eximport.ex.objectexporters;

import net.datenwerke.eximport.ex.ExportSupervisor;

public interface EntityObjectExporterFactory extends BasicObjectExporterFactory{

	public EntityObjectExporter create(ExportSupervisor exportSupervisor, String id, Object toExport);
}
