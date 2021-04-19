package net.datenwerke.rs.jxlsreport.service.jxlsreport.reportengine.output.metadata;

import java.util.Set;

import javax.inject.Inject;

import net.datenwerke.rs.core.service.reportmanager.metadata.AbstractReportMetadataExporterManager;

import com.google.inject.Provider;

public class JxlsMetadataExporterManager extends AbstractReportMetadataExporterManager<JxlsMetadataExporter> {
	
	@Inject
	public JxlsMetadataExporterManager(Provider<Set<JxlsMetadataExporter>> exporters) {
		super(exporters);
	}

}
