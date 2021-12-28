package net.datenwerke.rs.legacysaiku.service.saiku.reportengine.output.generator;

import java.util.List;

import javax.inject.Inject;

import org.legacysaiku.olap.dto.SaikuDimensionSelection;
import org.legacysaiku.olap.dto.resultset.CellDataSet;
import org.legacysaiku.service.util.export.CsvExporter;
import org.olap4j.CellSet;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.RECCsv;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.legacysaiku.service.saiku.reportengine.output.object.CompiledCSVSaikuReport;
import net.datenwerke.rs.legacysaiku.service.saiku.reportengine.output.object.CompiledRSSaikuReport;

public class SaikuCSVOutputGenerator extends SaikuOutputGeneratorImpl {

	@Inject
	public SaikuCSVOutputGenerator(HookHandlerService hookHandler) {
		super(hookHandler);
		// TODO Auto-generated constructor stub
	}


	@Override
	public String[] getFormats() {
		return new String[]{ReportExecutorService.OUTPUT_FORMAT_CSV};
	}

	@Override
	public CompiledReport getFormatInfo() {
		return new CompiledCSVSaikuReport();
	}


	@Override
	public CompiledRSSaikuReport exportReport(CellDataSet cellDataSet,
			CellSet cellset, List<SaikuDimensionSelection> filters,
			String outputFormat, ReportExecutionConfig... configs)
			throws ReportExecutorException {

		RECCsv config = getConfig(RECCsv.class, configs);
		String delimiter = ";";
		String quotationMark = "\"";
		if(null != config){
			delimiter = null == config.getSeparator() ? "" : config.getSeparator();
			quotationMark = null == config.getQuote() ? "" : config.getQuote();
		}
		
		byte[] csv = CsvExporter.exportCsv(cellset, delimiter, quotationMark, getCellSetFormatter());

		return new CompiledCSVSaikuReport(new String(csv));
	}

}
