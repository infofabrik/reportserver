package net.datenwerke.rs.base.service.reportengines.table.output.generator;

import java.io.IOException;
import java.io.OutputStream;

import net.datenwerke.rs.base.service.reportengines.table.entities.Column.CellFormatter;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.output.ReportOutputGenerator;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.security.service.usermanager.entities.User;

/**
 * An output generator for dynamic lists.
 *  
 *
 */
public interface TableOutputGenerator extends ReportOutputGenerator{


	/**
	 * Initializes the generator before the first data point is provided.
	 * 
	 * @param os The output stream to be used. If no output stream is provided (the object is null), then the report should be generated in memory.
	 */
	public void initialize(OutputStream os, TableDefinition td, boolean withSubtotals, TableReport report, TableReport originalReport, CellFormatter[] cellFormatters, ParameterSet parameters, User user, ReportExecutionConfig... configs) throws IOException;
	
	/**
	 * Informs the generator that the current data row is finished and a new row is about to begin
	 * 
	 * @throws IOException
	 */
	public void nextRow() throws IOException;
	
	/**
	 * Adds a new cell to the output.
	 * 
	 * @param field
	 * @param cellFormatter
	 * @throws IOException
	 */
	public void addField(Object field, CellFormatter cellFormatter) throws IOException;
	
	/**
	 * Informs the generator that the data is completely processed. 
	 * 
	 * @throws IOException
	 */
	public void close() throws IOException; 
	
	/**
	 * Is called upon completion to get the final report object. In case an 
	 * outputstream is used (see {@link #initialize(OutputStream, TableDefinition, boolean, TableReport, TableReport, CellFormatter[], ParameterSet, User, ReportExecutionConfig...)}
	 * the {@link CompiledReport} object should not contain actual report data.
	 * 
	 * @return The finished report
	 */
	public CompiledReport getTableObject();

	/**
	 * In case a report is exported using subtotals, this method is called whenever a group is finished and an aggregation row needs to be added.
	 * 
	 */
	public void addGroupRow(int[] subtotalIndices, Object[] subtotals, int[] subtotalGroupFieldIndices, Object[] subtotalGroupFieldValues, int rowSize, CellFormatter[] cellFormatters) throws IOException;

	/**
	 * Return true if the output generator is able to stream data. 
	 * 
	 */
	public boolean supportsStreaming();


}
