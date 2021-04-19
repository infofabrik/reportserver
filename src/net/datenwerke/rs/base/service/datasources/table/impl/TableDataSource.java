package net.datenwerke.rs.base.service.datasources.table.impl;

import java.io.Closeable;
import java.util.List;

import net.datenwerke.rs.base.service.datasources.table.impl.config.TableDatasourceConfig;
import net.datenwerke.rs.base.service.reportengines.table.entities.AdditionalColumnSpec;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.FilterBlock;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainerProvider;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;

/**
 * Provides an interface for a table based data source.
 * 
 *
 */
public interface TableDataSource extends Closeable {

	/**
	 * Sets a flag that tells the datasource to not execute the report as it is, but to only count the rows.
	 */
	public void countRows();
	
	public DatasourceContainerProvider getDatasourceContainerProvider();
	
	public void applyParameters(ParameterSet paramterSet);
	
	public void applyColumnConfiguration(List<Column> columnList);
	
	public void limit(int number);
	
	public void distinct(boolean enableDistinct);
	
	public void paged(int offset, int length);
	
	/**
	 * <p>If DataSource is not "open" open will be called</p>
	 */
	public boolean next() throws ReportExecutorException;
	
	/**
	 * <p>If DataSource is not "open" open will be called and cursor will be moved to first "row"</p>
	 * 
	 */
	public Object getFieldValue(int pos) throws ReportExecutorException;
	
	/**
	 * <p>If DataSource is not "open" open will be called</p>
	 * 
	 */
	public TableDefinition getTableDefinition() throws ReportExecutorException;
	
	public TableDefinition getPlainTableDefinition();
	
	public void open() throws ReportExecutorException;
	
	public void open(String executorToken) throws ReportExecutorException;
	
	public boolean isOpen();
	
	public void close();

	public void setPreFilter(FilterBlock rootBlock);

	public void addAdditionalColumnSpecs(
			List<AdditionalColumnSpec> additionalColumns);

	public void setIgnoreAnyColumnConfiguration(boolean b);

	ParameterSet getParameters();

	void applyConfig(TableDatasourceConfig config);
	
	public void cancelStatement();

	public void addQueryComment(String comment);
}