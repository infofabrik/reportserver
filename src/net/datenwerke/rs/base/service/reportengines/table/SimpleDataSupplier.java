package net.datenwerke.rs.base.service.reportengines.table;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.base.service.datasources.table.impl.TableDataSource;
import net.datenwerke.rs.base.service.datasources.table.impl.config.TableDatasourceConfig;
import net.datenwerke.rs.base.service.datasources.transformers.DatasourceTransformationService;
import net.datenwerke.rs.base.service.reportengines.table.entities.AdditionalColumnSpec;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.base.service.reportengines.table.output.generator.DataCountOutputGenerator;
import net.datenwerke.rs.base.service.reportengines.table.output.generator.RSTableOutputGenerator;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainerProvider;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterContainerNode;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSetFactory;
import net.datenwerke.rs.utils.entitycloner.EntityClonerService;
import net.datenwerke.security.service.usermanager.entities.User;

/**
 * Simply supplies you with data in the form of an RSTableModel.
 *
 */
public class SimpleDataSupplier {
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	public interface DataConsumer{
		void consumeRow(Object[] row);
		void allConsumed();
	}
	
	private final Provider<RSTableOutputGenerator> outputGeneratorProvider;
	private final EntityClonerService entityClonerService;
	private final ParameterSetFactory parameterSetFactory;
	private final DatasourceTransformationService datasourceTransformationService;
	
	@Inject
	public SimpleDataSupplier(
		Provider<RSTableOutputGenerator> outputGeneratorProvider,
		EntityClonerService entityClonerService,
		ParameterSetFactory parameterSetFactory, 
		DatasourceTransformationService datasourceTransformationService 
		){
		
		/* store objects */
		this.outputGeneratorProvider = outputGeneratorProvider;
		this.entityClonerService = entityClonerService;
		this.parameterSetFactory = parameterSetFactory;
		this.datasourceTransformationService = datasourceTransformationService;
	}
	
	public TableDefinition getInfo(DatasourceContainerProvider container) throws ReportExecutorException{
		ParameterSet ps = null;
		if(container instanceof ParameterContainerNode){
			if(container instanceof Report)
				ps = parameterSetFactory.create((Report)container);
			else
				ps = parameterSetFactory.create();
			ps.addAll(((ParameterContainerNode) container).getParameterInstances());
		}
		
		/* get proper datasource */
		TableDataSource ds = datasourceTransformationService.transform(TableDataSource.class, container,ps);

		try{
			if(null != ps)
				ds.applyParameters(ps);
			
			ds.limit(0);
			ds.open();
			return ds.getPlainTableDefinition();
		} finally{
			ds.close();
		}
	}
	
	public TableDefinition getInfo(DatasourceContainerProvider container, Column... columns) throws ReportExecutorException{
		ParameterSet ps = null;
		if(container instanceof ParameterContainerNode){
			if(container instanceof Report)
				ps = parameterSetFactory.create((Report)container);
			else
				ps = parameterSetFactory.create();
			ps.addAll(((ParameterContainerNode) container).getParameterInstances());
		}
		
		/* get proper datasource */
		TableDataSource ds = datasourceTransformationService.transform(TableDataSource.class, container, ps);
		
		try{
			if(null != ps)
				ds.applyParameters(ps);
			
			ds.applyColumnConfiguration(Arrays.asList(columns));
			ds.limit(0);
			ds.open();
			return ds.getTableDefinition();
		} finally{
			ds.close();
		}
	}
	
	public RSTableModel getData(DatasourceContainerProvider container) throws ReportExecutorException{
		return getData(container, null, null, null);
	}
	
	public RSTableModel getData(DatasourceContainerProvider container, Report report, User user) throws ReportExecutorException {
		ParameterSet parameters = parameterSetFactory.create(user, report);
		return getData(container, parameters, null, null);
	}
	
	public RSTableModel getData(DatasourceContainerProvider container, ParameterSet parameters) throws ReportExecutorException{
		return getData(container, parameters, null, null);
	}
	
	public RSTableModel getData(DatasourceContainerProvider container, ParameterSet parameters, Integer offset, Integer limit) throws ReportExecutorException{
		return getData(container, parameters, offset, limit, null, null);
	}
	
	public RSTableModel getData(DatasourceContainerProvider container, ParameterSet parameters, Integer offset, Integer limit, TableDatasourceConfig config, DataConsumer consumer) throws ReportExecutorException{
		/* try to generate parameter set if none is given */
		if(null == parameters)
			parameters = parameterSetFactory.safeCreate();
		
		/* get proper datasource */
		TableDataSource ds = datasourceTransformationService.transform(TableDataSource.class, container, parameters);
			
		if(null == ds)
			throw new ReportExecutorException("Could not load datasource");
		
		try{
			if(null != config)
				ds.applyConfig(config);
			
			/* paramters ?*/
			if(null != parameters)
				ds.applyParameters(parameters);
			
			/* limit or paged? */
			if(null != limit && null != offset)
				ds.paged(offset, limit);
			else if(null != limit)
				ds.limit(limit);
	
			/* get data */
			if(null == consumer)
				return getData(ds);
			else
				return streamData(ds, consumer);
		} finally {
			ds.close();
		}
	}
	
	public int getDataCount(DatasourceContainerProvider container) throws ReportExecutorException{
		return getDataCount(container, null);
	}
	
	public int getDataCount(DatasourceContainerProvider container, ParameterSet parameters) throws ReportExecutorException{
		return getDataCount(container, parameters, null);
	}
	
	public int getDataCount(DatasourceContainerProvider container, ParameterSet parameters, TableDatasourceConfig config) throws ReportExecutorException{
		/* get proper datasource */
//		TableDataSource ds = dataSourceTransformerProvider.get().transform(container, parameters);
		TableDataSource ds = datasourceTransformationService.transform(TableDataSource.class, container, parameters);
		try{
			if(null != config)
				ds.applyConfig(config);
			
			/* paramters ?*/
			if(null != parameters)
				ds.applyParameters(parameters);
			
			ds.countRows();
	
			return countData(ds);
		} finally {
			ds.close();
		}
	}
	
	public RSTableModel getColumnValuesPaged(DatasourceContainerProvider container, Column column, Integer offset, Integer length, boolean useFilters) throws ReportExecutorException{
		return getColumnValuesPaged(container, null, column, offset, length, useFilters); 
	}
	
	public RSTableModel getColumnValuesPaged(DatasourceContainerProvider container, ParameterSet parameters, Column column, Integer offset, Integer length, boolean useFilters) throws ReportExecutorException{
		return getColumnValuesPaged(container, parameters, column, null, offset, length, useFilters);
	}
	
	public RSTableModel getColumnValuesPaged(DatasourceContainerProvider container, ParameterSet parameters, Column column, List<AdditionalColumnSpec> additionalColumns, Integer offset, Integer length, boolean useFilters) throws ReportExecutorException{
		return _getColumnValuesPaged(container, parameters, Collections.singletonList(column), additionalColumns, offset, length, useFilters, false, null);
	}
	
	public RSTableModel getColumnValuesPaged(DatasourceContainerProvider container, ParameterSet parameters, Column column, List<AdditionalColumnSpec> additionalColumns, Integer offset, Integer length, boolean useFilters, DataConsumer consumer) throws ReportExecutorException{
		return _getColumnValuesPaged(container, parameters, Collections.singletonList(column), additionalColumns, offset, length, useFilters, false, consumer);
	}
	
	public RSTableModel getColumnValuesPaged(DatasourceContainerProvider container, ParameterSet parameters, List<Column> columns, List<AdditionalColumnSpec> additionalColumns, Integer offset, Integer length, boolean useFilters) throws ReportExecutorException{
		return _getColumnValuesPaged(container, parameters, columns, additionalColumns, offset, length, useFilters, false, null);
	}
	
	public RSTableModel getColumnValuesPaged(DatasourceContainerProvider container, ParameterSet parameters, List<Column> columns, List<AdditionalColumnSpec> additionalColumns, Integer offset, Integer length, boolean useFilters, DataConsumer consumer) throws ReportExecutorException{
		return _getColumnValuesPaged(container, parameters, columns, additionalColumns, offset, length, useFilters, false, consumer);
	}

	public RSTableModel getDistinctColumnValuesPaged(DatasourceContainerProvider container, Column column, Integer offset, Integer length, boolean useFilters) throws ReportExecutorException{
		return getDistinctColumnValuesPaged(container, null, column, offset, length, useFilters); 
	}
	
	public RSTableModel getDistinctColumnValuesPaged(DatasourceContainerProvider container, ParameterSet parameters, Column column, Integer offset, Integer length, boolean useFilters) throws ReportExecutorException{
		return getDistinctColumnValuesPaged(container, parameters, column, null, offset, length, useFilters);
	}
	
	public RSTableModel getDistinctColumnValuesPaged(DatasourceContainerProvider container, ParameterSet parameters, Column column, List<AdditionalColumnSpec> additionalColumns, Integer offset, Integer length, boolean useFilters, String executeToken) throws ReportExecutorException{
		return _getColumnValuesPaged(container, parameters, Collections.singletonList(column), additionalColumns, offset, length, useFilters, true, null, executeToken);
	}
	
	public RSTableModel getDistinctColumnValuesPaged(DatasourceContainerProvider container, ParameterSet parameters, Column column, List<AdditionalColumnSpec> additionalColumns, Integer offset, Integer length, boolean useFilters) throws ReportExecutorException{
		return _getColumnValuesPaged(container, parameters, Collections.singletonList(column), additionalColumns, offset, length, useFilters, true, null);
	}
	
	public RSTableModel getDistinctColumnValuesPaged(DatasourceContainerProvider container, ParameterSet parameters, Column column, List<AdditionalColumnSpec> additionalColumns, Integer offset, Integer length, boolean useFilters, DataConsumer consumer) throws ReportExecutorException{
		return _getColumnValuesPaged(container, parameters, Collections.singletonList(column), additionalColumns, offset, length, useFilters, true, consumer);
	}
	
	public RSTableModel getDistinctColumnValuesPaged(DatasourceContainerProvider container, ParameterSet parameters, List<Column> columns, List<AdditionalColumnSpec> additionalColumns, Integer offset, Integer length, boolean useFilters) throws ReportExecutorException{
		return _getColumnValuesPaged(container, parameters, columns, additionalColumns, offset, length, useFilters, true, null);
	}
	
	public RSTableModel getDistinctColumnValuesPaged(DatasourceContainerProvider container, ParameterSet parameters, List<Column> columns, List<AdditionalColumnSpec> additionalColumns, Integer offset, Integer length, boolean useFilters, DataConsumer consumer) throws ReportExecutorException{
		return _getColumnValuesPaged(container, parameters, columns, additionalColumns, offset, length, useFilters, true, consumer);
	}
	
	public RSTableModel _getColumnValuesPaged(DatasourceContainerProvider container, ParameterSet parameters, List<Column> column, List<AdditionalColumnSpec> additionalColumns, Integer offset, Integer length, boolean useFilters, boolean distinct, DataConsumer streamConsumer) throws ReportExecutorException{
		return _getColumnValuesPaged(container, parameters, column, additionalColumns, offset, length, useFilters, distinct, streamConsumer, null);
	}
	
	public RSTableModel _getColumnValuesPaged(DatasourceContainerProvider container, ParameterSet parameters, List<Column> column, List<AdditionalColumnSpec> additionalColumns, Integer offset, Integer length, boolean useFilters, boolean distinct, DataConsumer streamConsumer, String executorToken) throws ReportExecutorException{
		/* get proper datasource */
//		TableDataSource ds = dataSourceTransformerProvider.get().transform(container, parameters);
		TableDataSource ds = datasourceTransformationService.transform(TableDataSource.class, container, parameters);
		
		try{
			if (container instanceof TableReport)
				applyTableReportSettings(ds, (TableReport) container, parameters, column, useFilters);
			else
				ds.applyColumnConfiguration(column);
	
			if(null != additionalColumns)
				ds.addAdditionalColumnSpecs(additionalColumns);
			
			if(distinct)
				ds.distinct(true);
			
			if(null != length)
				ds.paged(offset, length);
			
			/* paramters ?*/
			if(null != parameters)
				ds.applyParameters(parameters);
			
			/* get data */
			if(null == streamConsumer)
				return getData(ds, executorToken);
			else
				return streamData(ds, streamConsumer, executorToken);
		} finally {
			ds.close();
		}
	}
	
	public int getColumnValuesCount(DatasourceContainerProvider container, Column column, boolean useFilters) throws ReportExecutorException{
		return getColumnValuesCount(container, null, column, useFilters);
	}
	
	public int getColumnValuesCount(DatasourceContainerProvider container, ParameterSet parameters, Column column, boolean useFilters) throws ReportExecutorException{
		return getColumnValuesCount(container, parameters, column, null, useFilters);
	}
	
	public int getColumnValuesCount(DatasourceContainerProvider container, ParameterSet parameters, Column column, List<AdditionalColumnSpec> additionalColumns, boolean useFilters) throws ReportExecutorException{
		return _getColumnValuesCount(container, parameters, Collections.singletonList(column), additionalColumns, useFilters, false);
	}
	
	public int getDistinctColumnValuesCount(DatasourceContainerProvider container, Column column, boolean useFilters) throws ReportExecutorException{
		return getDistinctColumnValuesCount(container, null, column, useFilters);
	}
	
	public int getDistinctColumnValuesCount(DatasourceContainerProvider container, ParameterSet parameters, Column column, boolean useFilters) throws ReportExecutorException{
		return getDistinctColumnValuesCount(container, parameters, column, null, useFilters);
	}
	
	public int getDistinctColumnValuesCount(DatasourceContainerProvider container, ParameterSet parameters, Column column, List<AdditionalColumnSpec> additionalColumns, boolean useFilters) throws ReportExecutorException{
		return _getColumnValuesCount(container, parameters, Collections.singletonList(column), additionalColumns, useFilters, true);
	}
	
	public int _getColumnValuesCount(DatasourceContainerProvider container, ParameterSet parameters, List<Column> columns, List<AdditionalColumnSpec> additionalColumns, boolean useFilters, boolean distinct) throws ReportExecutorException{
		/* get proper datasource */
//		TableDataSource ds = dataSourceTransformerProvider.get().transform(container, parameters);
		TableDataSource ds = datasourceTransformationService.transform(TableDataSource.class, container, parameters);
		
		try{
			if (container instanceof TableReport)
				applyTableReportSettings(ds, (TableReport) container, parameters, columns, useFilters);
			else
				ds.applyColumnConfiguration(columns);
	
			if(distinct)
				ds.distinct(true);
			
			ds.countRows();
			
			if(null != parameters)
				ds.applyParameters(parameters);
			
			if(null != additionalColumns)
				ds.addAdditionalColumnSpecs(additionalColumns);
			
			return countData(ds);
		} finally {
			ds.close();
		}
	}
	
	public List<String> getColumnNames(DatasourceContainerProvider container) throws ReportExecutorException{
		ParameterSet parameters = parameterSetFactory.safeCreate();
		
		/* get proper datasource */
		TableDataSource ds = datasourceTransformationService.transform(TableDataSource.class, container,parameters);
		try{
			if(null != parameters)
				ds.applyParameters(parameters);
			
			ds.limit(0);
			return getData(ds).getTableDefinition().getColumnNames();
		} finally {
			ds.close();
		}
	}
	
	public List<Object[]> getColumns(DatasourceContainerProvider container) throws ReportExecutorException{
		ParameterSet parameters = parameterSetFactory.safeCreate();
		
		/* get proper datasource */
		TableDataSource ds = datasourceTransformationService.transform(TableDataSource.class, container,parameters);
		try{
			if(null != parameters)
				ds.applyParameters(parameters);
			
			ds.limit(0);
			return getData(ds).getTableDefinition().getColumns();
		} finally {
			ds.close();
		}
	}
	
	
	private int countData(TableDataSource ds) throws ReportExecutorException {
		/* get data */
		DataCountOutputGenerator dataCountOutputGenerator = new DataCountOutputGenerator();
		try{
			/* open datasource */
			ds.open();
			
			/* create TableModel */
			TableDefinition td = ds.getTableDefinition();
			dataCountOutputGenerator.initialize(null, td, false, null, null, null, null, null);

			/* gather data */
			while(ds.next()){
				/* add Fields */
				for(int i = 0; i < td.size(); i++)
					dataCountOutputGenerator.addField(ds.getFieldValue(i+1), null);
				
				/* next row */
				dataCountOutputGenerator.nextRow();
			}
		} catch(IOException e){ 
			throw new ReportExecutorException(e);
		} finally {
			ds.close();
		}
		
		return dataCountOutputGenerator.getTableObject().getDataCount();
	}
	
	private void applyTableReportSettings(TableDataSource ds, TableReport tableReport, ParameterSet paramSet, List<Column> columns, boolean useFilters) {
		/* apply parameters */
		if(null == paramSet){
			paramSet = parameterSetFactory.create(tableReport);
			paramSet.addAll(tableReport.getParameterInstances());
			ds.applyParameters(paramSet);
		}

		if(useFilters){
			/* create a new list with the columns to be used */
			List<Column> columnsToUse = new ArrayList<Column>();
			columnsToUse.addAll(columns);

			for(Column col : tableReport.getColumns()){
				if(columns.contains(col))
					continue;
				col.setHidden(true);
				columnsToUse.add(col);
			}
			
			ds.applyColumnConfiguration(columnsToUse);
			ds.setPreFilter(tableReport.getPreFilter().getRootBlock());
		}else{
			List<Column> columnsToUse = new ArrayList<Column>();
			
			/* create new Column object (no filters, no aliases and unhide it) */
			for(Column column : columns){
				Column newColumn = entityClonerService.cloneEntity(column);
				newColumn.setAlias(null);
				newColumn.setFilter(null);
				newColumn.setNullHandling(null);
				/* the like filter is used for filtering in the dialog and was overwritten by the caller accordingly */
				//newColumn.setLikeFilter(null);
				newColumn.setHidden(false);
				
				columnsToUse.add(newColumn);
			}

			ds.applyColumnConfiguration(columnsToUse);
		}
	}

	
	public RSTableModel getData(TableDataSource ds) throws ReportExecutorException{
		return getData(ds, null);
	}
	
	public RSTableModel getData(TableDataSource ds, String executorToken) throws ReportExecutorException{
		/* get data */
		RSTableOutputGenerator outputGenerator = outputGeneratorProvider.get();
		try{
			/* open datasource */
			ds.open(executorToken);
			
			/* create TableModel */
			TableDefinition td = ds.getTableDefinition();
			outputGenerator.initialize(null, td, false, null, null, null, null, null);

			/* gather data */
			while(ds.next()){
				/* add Fields */
				for(int i = 0; i < td.size(); i++)
					outputGenerator.addField(ds.getFieldValue(i+1), null);
				
				/* next row */
				outputGenerator.nextRow();
			}
		} catch(IOException e){ 
			throw new ReportExecutorException(e);
		}finally {
			if(null != ds)
				ds.close();
		}
		
		return (RSTableModel) outputGenerator.getTableObject();
	}
	
	public RSTableModel streamData(TableDataSource ds, DataConsumer consumer) throws ReportExecutorException{
		return streamData(ds, consumer, null);
	}
	
	public RSTableModel streamData(TableDataSource ds, DataConsumer consumer, String executorToken) throws ReportExecutorException{
		try{
			/* open datasource */
			ds.open(executorToken);
			
			/* create TableModel */
			TableDefinition td = ds.getTableDefinition();

			Object[] row = new Object[td.size()];
			
			/* gather data */
			while(ds.next()){
				/* add Fields */
				for(int i = 0; i < td.size(); i++)
					row[i] = ds.getFieldValue(i+1);
				
				/* next row */
				consumer.consumeRow(row);
			}
			
			consumer.allConsumed();
			
			return new RSTableModel(td);
		} finally {
			if(null != ds && ds.isOpen())
				ds.close();
		}
	}
}
