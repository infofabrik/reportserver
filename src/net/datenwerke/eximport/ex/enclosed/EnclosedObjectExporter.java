package net.datenwerke.eximport.ex.enclosed;

import java.util.Collection;
import java.util.Collections;

import javax.xml.stream.XMLStreamException;

import com.google.inject.Inject;

import net.datenwerke.eximport.EnclosedObjectConfig;
import net.datenwerke.eximport.ExImportHelperService;
import net.datenwerke.eximport.ex.ExportItemConfig;
import net.datenwerke.eximport.ex.ExportSupervisor;
import net.datenwerke.eximport.ex.Exporter;
import net.datenwerke.eximport.ex.ExporterSpecificExportConfig;
import net.datenwerke.eximport.ex.objectexporters.BasicObjectExporter;
import net.datenwerke.eximport.ex.objectexporters.BasicObjectExporterFactory;
import net.datenwerke.eximport.obj.ComplexItemProperty;
import net.datenwerke.eximport.obj.ExportedItem;

/**
 * 
 *
 */
public class EnclosedObjectExporter implements Exporter {

	private static final String EXPORTER_ID = "EnclosedObjectExporter";

	protected final BasicObjectExporterFactory exporterFactory;
	protected final ExImportHelperService eiHelper;
	
	@Inject
	public EnclosedObjectExporter(
		BasicObjectExporterFactory exporterFactory,
		ExImportHelperService eiHelper
		){
		
		/* store objects */
		this.exporterFactory = exporterFactory;
		this.eiHelper = eiHelper;
	}
	
	@Override
	public String getExporterId() {
		return EXPORTER_ID;
	}

	
	@Override
	public Collection<ExportItemConfig<?>> addReferences(
			ExportSupervisor exportSupervisor,
			Collection<ExportItemConfig<?>> queue) {
		return Collections.emptyList();
	}

	@Override
	public void configure(Collection<ExporterSpecificExportConfig> specificConfigs, Collection<ExportItemConfig<?>> configItems) {
	}

	@Override
	public boolean consumes(ExportItemConfig<?> config) {
		return false;
	}

	@Override
	public boolean consumes(Object object) {
		return false;
	}
	
	@Override
	public boolean consumesEnclosedObject(EnclosedObjectConfig config) {
		return true;
	}

	@Override
	public void export(ExportSupervisor exportSupervisor) {
	}

	@Override
	public void exportEnclosed(final ExportSupervisor exportSupervisor, EnclosedObjectConfig config) throws XMLStreamException {
		BasicObjectExporter exporter = exporterFactory.create(exportSupervisor, config.getId(), config.getEnclosed());
		exporter.exportWithoutBaseElementCreation();
	}

	@Override
	public ExportItemConfig<?> generateExportConfig(Object object) {
		return null;
	}

	@Override
	public ExportItemConfig<?> getConfigFor(Object value) {
		return null;
	}

	@Override
	public boolean hasConfigFor(Object value) {
		return false;
	}

	@Override
	public Collection<EnclosedObjectConfig> addEnclosed(
			ExportSupervisor exportSupervisor,
			Collection<ExportItemConfig<?>> configItems) {
		return Collections.emptySet();
	}

	@Override
	public Collection<EnclosedObjectConfig> addEnclosed(
			ExportSupervisor exportSupervisor, EnclosedObjectConfig enclosedCon) {
		BasicObjectExporter exporter = exporterFactory.create(exportSupervisor, enclosedCon.getId(), enclosedCon.getEnclosed());
		return exporter.getEnclosed();
	}

	@Override
	public Collection<ExportItemConfig<?>> addReferences(
			ExportSupervisor exportSupervisor, EnclosedObjectConfig enclosedCon) {
		BasicObjectExporter exporter = exporterFactory.create(exportSupervisor, enclosedCon.getId(), enclosedCon.getEnclosed());
		return exporter.getReferences();

	}
	
	@Override
	public String getDisplayNameFor(ExportedItem exportedItem) {
		ComplexItemProperty nameProperty = (ComplexItemProperty) exportedItem.getPropertyByName("name");
		if(null != nameProperty)
			return nameProperty.getElement().getValue();
		
		else return "";
	}

}
