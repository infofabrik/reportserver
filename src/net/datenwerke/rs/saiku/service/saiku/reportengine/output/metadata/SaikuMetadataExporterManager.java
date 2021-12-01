package net.datenwerke.rs.saiku.service.saiku.reportengine.output.metadata;

import java.util.Set;

import javax.inject.Inject;

import net.datenwerke.rs.core.service.reportmanager.metadata.AbstractReportMetadataExporterManager;

import com.google.inject.Provider;

public class SaikuMetadataExporterManager extends AbstractReportMetadataExporterManager<SaikuMetadataExporter> {
	
	@Inject
	public SaikuMetadataExporterManager(Provider<Set<SaikuMetadataExporter>> exporters) {
		super(exporters);
	}

}
