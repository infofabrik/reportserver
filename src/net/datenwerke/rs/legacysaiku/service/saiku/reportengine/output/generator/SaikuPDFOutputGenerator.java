package net.datenwerke.rs.legacysaiku.service.saiku.reportengine.output.generator;

import java.util.List;

import javax.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorRuntimeException;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReportVariant;
import net.datenwerke.rs.legacysaiku.service.saiku.reportengine.output.object.CompiledPDFSaikuReport;
import net.datenwerke.rs.legacysaiku.service.saiku.reportengine.output.object.CompiledRSSaikuReport;

import org.olap4j.CellSet;
import org.legacysaiku.olap.dto.SaikuDimensionSelection;
import org.legacysaiku.olap.dto.resultset.CellDataSet;
import org.legacysaiku.web.svg.PdfReport;

import com.google.inject.Inject;

public class SaikuPDFOutputGenerator extends SaikuOutputGeneratorImpl {
	
	
	private Provider<PdfReport> pdfReportProvider;


	@Inject
	public SaikuPDFOutputGenerator(HookHandlerService hookHandler, Provider<PdfReport> pdfReportProvider) {
		super(hookHandler);
		this.pdfReportProvider = pdfReportProvider;
	}


	public String[] getFormats() {
		return new String[]{ReportExecutorService.OUTPUT_FORMAT_PDF};
	}

	@Override
	public CompiledReport getFormatInfo() {
		return new CompiledPDFSaikuReport();
	}


	@Override
	public CompiledRSSaikuReport exportReport(CellDataSet cellDataSet,
			CellSet cellset, List<SaikuDimensionSelection> filters,
			String outputFormat, ReportExecutionConfig... configs)
			throws ReportExecutorException {
		
		String reportName = report.getName();
		if(report instanceof SaikuReportVariant){
			reportName = (((SaikuReportVariant) report).getBaseReport().getName() + ": " + report.getName());
		}
		
		PdfReport pdf = pdfReportProvider.get();
		try {
			pdf.setHeader(reportName);
			byte[] doc  = pdf.pdf(cellDataSet, "");
			return new CompiledPDFSaikuReport(doc);
		} catch(Exception e){
			throw new ReportExecutorRuntimeException(e);
		}
	}

}
