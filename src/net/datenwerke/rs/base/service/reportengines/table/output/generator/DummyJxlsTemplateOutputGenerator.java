package net.datenwerke.rs.base.service.reportengines.table.output.generator;

import java.io.IOException;
import java.io.OutputStream;

import net.datenwerke.rs.base.service.reportengines.table.entities.Column.CellFormatter;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.base.service.reportengines.table.output.object.CompiledXLSXTableReport;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.security.service.usermanager.entities.User;

public class DummyJxlsTemplateOutputGenerator implements SimpleJxlsTemplateOutputGenerator{

	@Override
	public void initialize(OutputStream os, TableDefinition td, boolean withSubtotals, TableReport report, TableReport orgReport, CellFormatter[] cellFormatters, ParameterSet parameters, User user, ReportExecutionConfig... configs) throws IOException {
	}

	@Override
	public String[] getFormats() {
		return new String[]{ReportExecutorService.OUTPUT_FORMAT_JXLS_TEMPLATE};
	}

	
	@Override
	public CompiledReport getTableObject() {
		return new CompiledXLSXTableReport(null);
	}


	@Override
	public CompiledReport getFormatInfo() {
		return new CompiledXLSXTableReport(null);
	}

	@Override
	public void nextRow() throws IOException {
		
	}

	@Override
	public void addField(Object field, CellFormatter cellFormatter) throws IOException {
	}

	@Override
	public void close() throws IOException {
	}

	@Override
	public void addGroupRow(int[] subtotalIndices, Object[] subtotals, int[] subtotalGroupFieldIndices,
			Object[] subtotalGroupFieldValues, int rowSize, CellFormatter[] cellFormatters) throws IOException {
	}

	@Override
	public boolean supportsStreaming() {
		return false;
	}

	@Override
	public boolean isCatchAll() {
		return false;
	}
	
}