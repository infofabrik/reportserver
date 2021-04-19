package net.datenwerke.rs.saiku.service.saiku.reportengine.output.generator;

import java.util.List;

import javax.inject.Inject;

import org.olap4j.CellSet;
import org.saiku.olap.dto.resultset.CellDataSet;
import org.saiku.olap.query2.ThinHierarchy;
import org.saiku.olap.util.formatter.ICellSetFormatter;
import org.saiku.service.util.export.CsvExporter;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.RECCsv;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.saiku.service.saiku.reportengine.output.object.CompiledCSVSaikuReport;
import net.datenwerke.rs.saiku.service.saiku.reportengine.output.object.CompiledRSSaikuReport;
import net.datenwerke.rs.utils.config.ConfigService;

public class SaikuCSVOutputGenerator extends SaikuOutputGeneratorImpl {
	
	private String delimiter; //$NON-NLS-1$
	private String quotationMark; //$NON-NLS-1$
	private String newline;  //$NON-NLS-1$
	
	private ConfigService configService;
	
	public static final String CONFIG_FILE = "exportfilemd/csvexport.cf";
	public static final String CSV_LINE_SEPARATOR_PROPERTY = "csv.lineSeparator"; 
	public static final String CSV_SEPARATOR_PROPERTY = "csv.separator"; 
	public static final String CSV_QUOTATION_PROPERTY = "csv.quote"; 

	@Inject
	public SaikuCSVOutputGenerator(HookHandlerService hookHandler, ConfigService configService) {
		super(hookHandler);
		this.configService = configService;
		this.newline = getLineSeparator();
		this.delimiter = getDelimiter();
		this.quotationMark = getQuotationMark();
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
			CellSet cellset, List<ThinHierarchy> filters, ICellSetFormatter formatter,
			String outputFormat, ReportExecutionConfig... configs)
			throws ReportExecutorException {

		RECCsv config = getConfig(RECCsv.class, configs);
		if(null != config){
			delimiter = null == config.getSeparator() ? "" : config.getSeparator();
			quotationMark = null == config.getQuote() ? "" : config.getQuote();
			newline = null == config.getLineSeparator() ? "" : config.getLineSeparator();
			newline = newline.replace("\\r", "\r").replace("\\n", "\n");
		}
		
		boolean printHeader = false;
		if(null == config || config.isPrintHeader()){
			printHeader = true;
		}
		
		byte[] csv = CsvExporter.exportCsv(cellset, delimiter, quotationMark, newline, printHeader, formatter);

		return new CompiledCSVSaikuReport(new String(csv));
	}

	private String getDelimiter(){
		return configService.getConfigFailsafe(CONFIG_FILE).getString(CSV_SEPARATOR_PROPERTY, ";");
	}
	private String getQuotationMark(){
		return configService.getConfigFailsafe(CONFIG_FILE).getString(CSV_QUOTATION_PROPERTY, "\"");
	}
	
	private String getLineSeparator(){
		return configService.getConfigFailsafe(CONFIG_FILE).getString(CSV_LINE_SEPARATOR_PROPERTY, "\r\n").replace("\\n", "\n").replace("\\r", "\r");
	}
}
