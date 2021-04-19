package net.datenwerke.rs.base.service.reportengines.table.output.generator;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.sql.Clob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.base.service.reportengines.table.entities.Column.CellFormatter;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.core.service.reportserver.ReportServerService;
import net.datenwerke.security.service.usermanager.entities.User;

public abstract class TableOutputGeneratorImpl implements TableOutputGenerator {
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	@Inject
	protected static Provider<ReportServerService> reportServerService;
	
	protected ReportExecutionConfig[] configs;

	protected TableReport report;
	protected TableReport originalReport;
	
	protected TableDefinition td;
	protected OutputStream os;
	
	protected String charset;

	protected boolean withSubtotals;

	protected ParameterSet parameters;
	protected User user;

	protected CellFormatter[] cellFormatters;

	@Override
	public void initialize(OutputStream os, TableDefinition td, boolean withSubtotals, TableReport report, TableReport originalReport, CellFormatter[] cellFormatters, 
			ParameterSet parameters, User user, ReportExecutionConfig... configs) throws IOException {
		this.configs = configs;
		this.report = report;
		this.originalReport = originalReport;
		this.cellFormatters = cellFormatters;
		this.td = td;
		this.os = os;
		this.withSubtotals = withSubtotals;
		this.parameters = parameters;
		this.user = user;
		
		this.charset = reportServerService.get().getCharset();
	}
	
	protected boolean hasConfig(Class<? extends ReportExecutionConfig> type){
		return null != getConfig(type);
	}
	
	protected <R extends ReportExecutionConfig> R getConfig(Class<? extends R> type){
		for(ReportExecutionConfig config : configs)
			if(type.isAssignableFrom(config.getClass()))
				return (R) config;
		return null;
	}

	protected ReportExecutionConfig[] getConfigs() {
		return configs;
	}

	protected Report getReport() {
		return report;
	}

	protected TableDefinition getTd() {
		return td;
	}
	
	@Override
	public boolean isCatchAll() {
		return false;
	}
	
	protected Object getValueOf(Object field) {
		if(! (field instanceof Clob))
			return field;
		else {
			Clob clob = (Clob) field;
			
			try {
				Reader is = clob.getCharacterStream();
				StringBuffer sb = new StringBuffer();
				int length = (int) clob.length();
				if (length > 0) {
					char[] buffer = new char[length];

					int count = 0;
					while ((count = is.read(buffer)) != -1)
						sb.append(buffer);
					
					return sb.toString();
				}
				
				return "";
			} catch (Exception e) {
				logger.warn( e.getMessage(), e);
			}
		}
		return field;
	}
	
	protected String getValueOf(Object field, CellFormatter formatter) {
		return formatter.format(getValueOf(field));
	}
	
	@Override
	public void addGroupRow(int[] subtotalIndices, Object[] subtotals, int[] subtotalGroupFieldIndices, Object[] subtotalGroupFieldValues, int rowSize, CellFormatter[] cellFormatters) throws IOException{
	}
	
	@Override
	public boolean supportsStreaming() {
		return true;
	}
}
