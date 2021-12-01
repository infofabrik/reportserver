package net.datenwerke.rs.base.service.reportengines.table.output.generator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;

import net.datenwerke.rs.base.service.reportengines.table.entities.Column.CellFormatter;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.base.service.reportengines.table.output.object.CompiledJSONTableReport;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.security.service.usermanager.entities.User;

/**
 * 
 *
 */
public class JSONOutputGenerator extends TableOutputGeneratorImpl{
	

	protected StringBuffer stringBuffer;
	
	protected PrintWriter writer;
	
	protected boolean first = true;
	int cell = 0;
	
	@Override
	public void addField(Object field, CellFormatter formatter) throws IOException {
		String name = td.getColumnNames().get(cell).replace("\\","\\\\").replace("\"", "\\\"").replaceAll("[\\r\\n\\s]+", " ");
		
		if(cell > 0)
			stringBuffer.append(",");
		
		stringBuffer.append("\"").append(name).append("\"").append(":");
		if(field instanceof Number || field instanceof BigDecimal)
			stringBuffer.append(field);
		else if(field == null)
			stringBuffer.append("null");
		else if(field instanceof Boolean)
			stringBuffer.append(Boolean.TRUE.equals(field) ? "true" : "false");
		else {
			Object value = getValueOf(field, formatter);
			stringBuffer.append("\"").append(String.valueOf(value).replace("\\","\\\\").replace("\"", "\\\"").replaceAll("[\\r\\n\\s]+", " ")).append("\"");
		}
		
		if(null != writer){
			writer.write(stringBuffer.toString());
			stringBuffer.delete(0, stringBuffer.length());
		}
		
		cell++;
	}

	@Override
	public void close() throws IOException {
		stringBuffer.append("}]");
		
		if(null != writer){
			writer.write(stringBuffer.toString());
			stringBuffer.delete(0, stringBuffer.length());
			
			writer.close();
		}
	}

	@Override
	public String[] getFormats() {
		return new String[]{ReportExecutorService.OUTPUT_FORMAT_JSON};
	}

	@Override
	public CompiledReport getTableObject() {
		return new CompiledJSONTableReport(stringBuffer.toString());
	}

	@Override
	public void initialize(OutputStream os, TableDefinition td, boolean withSubtotals, TableReport report, TableReport orgReport, CellFormatter[] cellFormatters, ParameterSet parameters, User user, ReportExecutionConfig... configs) throws IOException {
		super.initialize(os, td, withSubtotals, report, orgReport, cellFormatters, parameters, user, configs);

		/* initialize buffer */
		stringBuffer = new StringBuffer();
		if(null != os)
			writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(os, charset)));
		
		stringBuffer.append("[{");
	}
	
	@Override
	public void nextRow() throws IOException {
		stringBuffer.append("},{");
		
		if(null != writer){
			writer.write(stringBuffer.toString());
			stringBuffer.delete(0, stringBuffer.length());
		}
		
		cell = 0;
	}

	@Override
	public CompiledReport getFormatInfo() {
		return new CompiledJSONTableReport(null);
	}

	
}
