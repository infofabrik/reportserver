package net.datenwerke.rs.incubator.service.exportmetadata.hookers;

import java.util.Collection;
import java.util.Collections;

import net.datenwerke.rs.base.service.reportengines.table.hooks.TableExportHook;
import net.datenwerke.rs.base.service.reportengines.table.output.generator.TableOutputGenerator;
import net.datenwerke.rs.base.service.reportengines.table.output.generator.LegacyTablePDFOutputGenerator;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.incubator.service.exportmetadata.ExportMetadataService;
import net.datenwerke.security.service.usermanager.entities.User;

import com.google.inject.Inject;
import com.lowagie.text.Document;

public class MetadataTablePdfExportHooker implements TableExportHook {

	private final ExportMetadataService exportMetadataService;
	
	@Inject
	public MetadataTablePdfExportHooker(
		ExportMetadataService exportMetadataService
		) {
		
		/* store objects */
		this.exportMetadataService = exportMetadataService;
	}
	
	@Override
	public Collection<String> getFormats() {
		return Collections.singletonList(ReportExecutorService.OUTPUT_FORMAT_PDF);
	}

	@Override
	public void afterExport(Report report, User user, CompiledReport compiledReport,
			TableOutputGenerator outputGenerator, String outputFormat) {

	}

	@Override
	public void beforeExport(Report report, User user, TableDefinition td,
			TableOutputGenerator outputGenerator, String outputFormat) {
		if(outputGenerator instanceof LegacyTablePDFOutputGenerator){
			Document document = ((LegacyTablePDFOutputGenerator)outputGenerator).getDocument();
			
			document.addAuthor(exportMetadataService.getAuthor(report, user));
			document.addCreator(exportMetadataService.getCreator(report, user));
			document.addTitle(exportMetadataService.getTitle(report, user));
		}
	}

}
