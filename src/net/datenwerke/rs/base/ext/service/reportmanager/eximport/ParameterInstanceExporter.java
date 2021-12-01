package net.datenwerke.rs.base.ext.service.reportmanager.eximport;

import java.lang.reflect.Field;

import javax.xml.stream.XMLStreamException;

import net.datenwerke.eximport.EnclosedObjectConfig;
import net.datenwerke.eximport.ExImportHelperService;
import net.datenwerke.eximport.ex.ExportSupervisor;
import net.datenwerke.eximport.ex.enclosed.EnclosedEntityExporter;
import net.datenwerke.eximport.ex.objectexporters.BasicObjectExporter;
import net.datenwerke.eximport.ex.objectexporters.BasicObjectExporter.ObjectExporterAdjuster;
import net.datenwerke.eximport.ex.objectexporters.EntityObjectExporterFactory;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance;

import com.google.inject.Inject;

public class ParameterInstanceExporter extends EnclosedEntityExporter {

	public static final String KEY_ATTRIBUTE = "definitionKey";
	
	@Inject
	public ParameterInstanceExporter(
		EntityObjectExporterFactory exporterFactory, 
		ExImportHelperService eiHelper
		) {
		super(exporterFactory, eiHelper);
	}

	@Override
	public boolean consumesEnclosedObject(EnclosedObjectConfig config) {
		return config.getEnclosed() instanceof ParameterInstance<?>;
	}
	
	@Override
	public void exportEnclosed(final ExportSupervisor exportSupervisor, EnclosedObjectConfig config) throws XMLStreamException {
		BasicObjectExporter exporter = exporterFactory.create(exportSupervisor, config.getId(), config.getEnclosed());
		exporter.setAdjuster(new ObjectExporterAdjuster() {
			
			@Override
			public void preProcess(BasicObjectExporter exporter) throws XMLStreamException {
				ParameterInstance<?> instance = (ParameterInstance<?>) exporter.getToExport();
				eiHelper.setAttribute(exportSupervisor.getXmlStream(), KEY_ATTRIBUTE, instance.getDefinition().getKey());
			}

			@Override
			public void postProcessField(BasicObjectExporter basicObjectExporter,
					Field exportableField, Object value) {}

			@Override
			public void configureBase() throws XMLStreamException {
			}

			@Override
			public void postProcess(BasicObjectExporter basicObjectExporter) {
				
			}
		});
		exporter.exportWithoutBaseElementCreation();
	}
}
