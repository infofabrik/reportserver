package net.datenwerke.rs.base.service.reportengines.jasper.output.metadata;

import java.util.Set;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.core.service.reportmanager.metadata.AbstractReportMetadataExporterManager;


/**
 * Manages the output generators for table reports.
 * 
 * 
 * <p>
 * Currently only one instance of each output generator is created. This might
 * lead to problems and should be investigated.
 * </p>
 *
 *
 */
public class JasperMetadataExporterManager extends AbstractReportMetadataExporterManager<JasperMetadataExporter> {
	
	@Inject
	public JasperMetadataExporterManager(
			Provider<Set<JasperMetadataExporter>> exporters) {
		super(exporters);
	}
	
}
