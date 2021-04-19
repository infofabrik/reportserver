package net.datenwerke.eximport.ex.entity;

import java.util.Collection;

import javax.xml.stream.XMLStreamException;

import net.datenwerke.eximport.EnclosedObjectConfig;
import net.datenwerke.eximport.ex.ExportItemConfig;
import net.datenwerke.eximport.ex.ExportSupervisor;
import net.datenwerke.eximport.ex.ExporterImpl;
import net.datenwerke.eximport.ex.objectexporters.BasicObjectExporter;
import net.datenwerke.eximport.ex.objectexporters.EntityObjectExporter;
import net.datenwerke.eximport.ex.objectexporters.EntityObjectExporterFactory;

import com.google.inject.Inject;

public abstract class GenericEntityExporter extends ExporterImpl<EntityExportItemConfig> {

	private EntityObjectExporterFactory entityExporterFactory;
	
	@Inject
	public void setEntityExporter(EntityObjectExporterFactory entityExporterFactory){
		this.entityExporterFactory = entityExporterFactory;
	}
	
	@Override
	public boolean consumes(ExportItemConfig<?> config) {
		if(! super.consumes(config)) 
			return false;
		
		return consumes(config.getItem());
	}
	
	@Override
	public boolean consumes(Object object) {
		if(null == object)
			return false;
		for(Class<?> exportableType : getExportableTypes())
			if(exportableType.isAssignableFrom(object.getClass()))
				return true;
		return false;
	}
	
	@Override
	protected void doExport(ExportSupervisor exportSupervisor,
			EntityExportItemConfig item) throws XMLStreamException {
		Object toExport = item.getItem();
		
		/* export */
		BasicObjectExporter exporter = entityExporterFactory.create(exportSupervisor, item.getId(), toExport);
		exporter.export();
	}

	@Override
	protected void doAddReferences(ExportSupervisor exportSupervisor,
			EntityExportItemConfig item, Collection<ExportItemConfig<?>> references) {
		EntityObjectExporter exporter = entityExporterFactory.create(exportSupervisor, item.getId(), item.getItem());
		references.addAll(exporter.getReferences());
	}
	
	@Override
	protected void doAddEnclosed(ExportSupervisor exportSupervisor, EntityExportItemConfig item,
			Collection<EnclosedObjectConfig> enclosed) {
		EntityObjectExporter exporter = entityExporterFactory.create(exportSupervisor, item.getId(), item.getItem());
		enclosed.addAll(exporter.getEnclosed());
	}
	
	@Override
	public ExportItemConfig<?> generateExportConfig(Object object) {
		for(Class<?> exportableType : getExportableTypes())
			if(exportableType.isAssignableFrom(object.getClass()))
				return new EntityExportItemConfig(object);
		return null;
	}
	
	abstract protected Class<?>[] getExportableTypes();
}
