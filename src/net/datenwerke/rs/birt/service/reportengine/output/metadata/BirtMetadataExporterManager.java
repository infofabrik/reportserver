package net.datenwerke.rs.birt.service.reportengine.output.metadata;

import java.util.Set;

import net.datenwerke.rs.core.service.reportmanager.metadata.AbstractReportMetadataExporterManager;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class BirtMetadataExporterManager extends AbstractReportMetadataExporterManager<BirtMetadataExporter> {

	@Inject
	public BirtMetadataExporterManager(Provider<Set<BirtMetadataExporter>> exporters) {
		super(exporters);
	}

}
