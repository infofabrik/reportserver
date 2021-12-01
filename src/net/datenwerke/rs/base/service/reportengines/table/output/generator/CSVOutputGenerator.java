package net.datenwerke.rs.base.service.reportengines.table.output.generator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import com.google.inject.Inject;

import net.datenwerke.rs.base.service.reportengines.table.entities.Column.CellFormatter;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.base.service.reportengines.table.output.object.CompiledCSVTableReport;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.RECCsv;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.core.service.reportserver.ReportServerService;
import net.datenwerke.security.service.usermanager.entities.User;

/**
 * 
 *
 */
public class CSVOutputGenerator extends TableOutputGeneratorImpl{
	
	private String delimiter; //$NON-NLS-1$
	private String quotationMark; //$NON-NLS-1$
	private String newline;  //$NON-NLS-1$
	protected boolean haveSeenFieldInRow = false;
	protected StringBuilder stringBuffer;
	
	protected PrintWriter writer;
	
	public static final String CONFIG_FILE = "exportfilemd/csvexport.cf";
	public static final String CSV_LINE_SEPARATOR_PROPERTY = "csv.lineSeparator"; 
	public static final String CSV_SEPARATOR_PROPERTY = "csv.separator"; 
	public static final String CSV_QUOTATION_PROPERTY = "csv.quote"; 
	
	private final ConfigService configService;
	private final ReportServerService reportServerService;
	
	@Inject
	public CSVOutputGenerator(ConfigService configService, ReportServerService reportServerService) {
		super();
		this.reportServerService = reportServerService;
		
		this.configService = configService;
		this.newline = getLineSeparator();
		this.delimiter = getDelimiter();
		this.quotationMark = getQuotationMark();
		this.charset = getCharset();
	}
	
	@Override
	public void addField(Object field, CellFormatter formatter) throws IOException {
		if(haveSeenFieldInRow)
			stringBuffer.append(delimiter);
		stringBuffer.append(quotationMark);
		if(! "".equals(quotationMark))
			stringBuffer.append(String.valueOf(formatter.format(getValueOf(field))).replaceAll(quotationMark, "\\\\" + quotationMark));
		else
			stringBuffer.append(String.valueOf(formatter.format(getValueOf(field))));
		stringBuffer.append(quotationMark);
		
		if(null != writer){
			writer.write(stringBuffer.toString());
			stringBuffer.delete(0, stringBuffer.length());
		}
		haveSeenFieldInRow = true;
	}

	@Override
	public void close() throws IOException {
		stringBuffer.append(newline);
		if(null != writer){
			writer.write(stringBuffer.toString());
			stringBuffer.delete(0, stringBuffer.length());
			
			writer.close();
		}
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
	
	private String getCharset() {
		return reportServerService.getCharset();
	}

	@Override
	public String[] getFormats() {
		return new String[]{ReportExecutorService.OUTPUT_FORMAT_CSV};
	}

	@Override
	public CompiledReport getTableObject() {
		return new CompiledCSVTableReport(stringBuffer.toString());
	}

	@Override
	public void initialize(OutputStream os, TableDefinition td, boolean withSubtotals, TableReport report, TableReport orgReport, CellFormatter[] cellFormatters, ParameterSet parameters, User user, ReportExecutionConfig... configs) throws IOException {
		super.initialize(os, td, withSubtotals, report, orgReport, cellFormatters, parameters, user, configs);

		RECCsv config = getConfig(RECCsv.class);
		if(null != config){
			delimiter = null == config.getSeparator() ? "" : config.getSeparator();
			quotationMark = null == config.getQuote() ? "" : config.getQuote();
			newline = null == config.getLineSeparator() ? "" : config.getLineSeparator();
			newline = newline.replace("\\r", "\r").replace("\\n", "\n");
			charset = null == config.getCharset() ? charset : config.getCharset();
		}
		
		/* initialize buffer */
		stringBuffer = new StringBuilder();
		if(null != os)
			writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(os, charset)));
		
		/* add header */
		if(null == config || config.isPrintHeader()){
			boolean first = true;
			for(String name : td.getColumnNames()){
				if(first)
					first = false;
				else
					stringBuffer.append(delimiter);
					
				stringBuffer.append(quotationMark);
				stringBuffer.append(name);
				stringBuffer.append(quotationMark);
			}
			stringBuffer.append(newline);
		}
		
		if(null != writer){
			writer.write(stringBuffer.toString());
			stringBuffer.delete(0, stringBuffer.length());
		}
	}
	
	@Override
	public void nextRow() throws IOException {
		stringBuffer.append(newline);
		
		if(null != writer){
			writer.write(stringBuffer.toString());
			stringBuffer.delete(0, stringBuffer.length());
		}
		
		haveSeenFieldInRow = false;
	}

	@Override
	public CompiledReport getFormatInfo() {
		return new CompiledCSVTableReport(null);
	}

	
}
