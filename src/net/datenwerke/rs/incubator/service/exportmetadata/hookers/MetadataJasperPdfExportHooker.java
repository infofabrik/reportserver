package net.datenwerke.rs.incubator.service.exportmetadata.hookers;

import java.util.Collection;
import java.util.Collections;

import net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReport;
import net.datenwerke.rs.base.service.reportengines.jasper.hooks.JasperExportHook;
import net.datenwerke.rs.base.service.reportengines.jasper.output.object.CompiledRSJasperReport;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.incubator.service.exportmetadata.ExportMetadataService;
import net.datenwerke.security.service.usermanager.entities.User;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;

import com.google.inject.Inject;

/**
 * 
 * 
 *         This is a ExportCallback used to set information metadata in supported file
 *         formats. Currently the only supported one is PDF.
 * 
 */
public class MetadataJasperPdfExportHooker implements JasperExportHook {
	
	private final ExportMetadataService exportMetadataService;
	
	@Inject
	public MetadataJasperPdfExportHooker(
		ExportMetadataService exportMetadataService
		) {
	
		/* store objects */
		this.exportMetadataService = exportMetadataService;
	}

	/**
	 * Sets the three configurable metadata fields for PDF files.
	 */
	@Override
	public void beforeExport(String type, JRExporter exporter, JasperReport report, User user) {
		if (type.equals(ReportExecutorService.OUTPUT_FORMAT_PDF)) {
			JRPdfExporter pdf = (JRPdfExporter) exporter;

			pdf.setParameter(JRPdfExporterParameter.METADATA_CREATOR, exportMetadataService.getCreator(report, user));
			pdf.setParameter(JRPdfExporterParameter.METADATA_AUTHOR, exportMetadataService.getAuthor(report, user));
			pdf.setParameter(JRPdfExporterParameter.METADATA_TITLE, exportMetadataService.getTitle(report, user));
		}
	}

	@Override
	public Collection<String> getFormats() {
		return Collections.singletonList(ReportExecutorService.OUTPUT_FORMAT_PDF);
	}

	/**
	 * Nothing to do after an export.
	 */
	@Override
	public void afterExport(String type, JRExporter exporter, JasperReport report, CompiledRSJasperReport compiledReport, User user) {

	}

}
