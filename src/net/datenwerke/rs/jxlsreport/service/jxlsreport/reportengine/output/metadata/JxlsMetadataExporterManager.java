package net.datenwerke.rs.jxlsreport.service.jxlsreport.reportengine.output.metadata;

import java.util.Set;

import javax.inject.Inject;

import com.google.inject.Provider;

import net.datenwerke.rs.core.service.reportmanager.metadata.AbstractReportMetadataExporterManager;

public class JxlsMetadataExporterManager extends AbstractReportMetadataExporterManager<JxlsMetadataExporter> {
	
	@Inject
	public JxlsMetadataExporterManager(Provider<Set<JxlsMetadataExporter>> exporters) {
		super(exporters);
	}

}
