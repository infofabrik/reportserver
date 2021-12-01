package net.datenwerke.rs.base.service.dbhelper.querybuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.annotation.Nullable;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.service.datasources.locale.DatasourcesMessages;
import net.datenwerke.rs.base.service.datasources.table.impl.TableDBDataSource;
import net.datenwerke.rs.base.service.datasources.table.impl.utils.JasperStyleParameterParser;
import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;
import net.datenwerke.rs.base.service.dbhelper.hookers.FilterExecutorHooker;
import net.datenwerke.rs.base.service.dbhelper.hooks.DbFilterExecutorHook;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.events.StatementPreparedEvent;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.queryconditions.QryCondition;
import net.datenwerke.rs.base.service.reportengines.table.entities.AdditionalColumnSpec;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.ColumnFilter;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.FilterBlock;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.FilterSpec;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterValue;
import net.datenwerke.rs.utils.eventbus.EventBus;
import net.sf.jasperreports.engine.JRException;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class ManagedQuery {
	
	private final HookHandlerService hookHandler;
	private final FilterExecutorHooker filterExecutor;
	private final EventBus eventBus;
	private final TableDBDataSource datasource;
	
	private List<String> plainColumnNames;

	private String query;
	private boolean countRows;
	private int limit = -1;
	private ParameterSet parameterSet;
	private DatabaseHelper dbHelper;
	private List<Column> columns;
	private boolean distinct;
	private Vector<Integer> paged;

	private FilterBlock preFilterRoot;
	private List<AdditionalColumnSpec> additionalColumns;
	private boolean ignoreAnyColumnConfiguration;
	
	private List<String> queryComments = new ArrayList<String>();
	
	@Inject
	public ManagedQuery(
		HookHandlerService hookHandler,
		FilterExecutorHooker filterExecutor,
		EventBus eventBus,
		@Assisted String query, 
		@Assisted DatabaseHelper dbHelper,
		@Assisted @Nullable TableDBDataSource datasource
		) {
		
		/* store objects */
		this.hookHandler = hookHandler;
		this.filterExecutor = filterExecutor;
		this.eventBus = eventBus;
		this.query = query;
		this.dbHelper = dbHelper;
		this.datasource = datasource;
	}
	
	public PreparedStatement prepareStatement(Connection connection) throws SQLException, ReportExecutorException{
		/* get map for replacements */
		Map<String, ParameterValue> pMap = new HashMap<String, ParameterValue>();
		if(null != parameterSet)
			pMap = parameterSet.getParameterMap();
		
		String prelimStmt = preparePrelimStatement(pMap, connection);
		
//		System.out.println(prelimStmt);
		
		/* create real stmt */
		JasperStyleParameterParser parser = new JasperStyleParameterParser(connection, prelimStmt, pMap, parameterSet);
		PreparedStatement stmt;
		try {
			stmt = parser.getStatement();
		} catch (JRException e) {
			ReportExecutorException ree = new ReportExecutorException(DatasourcesMessages.INSTANCE.exceptionCouldNotPrepareStmt(e.getLocalizedMessage()), e); 
			throw ree;
		}
		
		eventBus.fireEvent(new StatementPreparedEvent(prelimStmt, stmt.toString()));
		
		return stmt; 
	}
	
	public String preparePrelimStatement() throws SQLException, ReportExecutorException{
		Map<String, ParameterValue> pMap = new HashMap<String, ParameterValue>();
		if(null != parameterSet)
			pMap = parameterSet.getParameterMap();
		
		String prelimStmt = preparePrelimStatement(pMap, null);
		
		return prelimStmt;
	}
	
	public String preparePrelimStatement(Map<String, ParameterValue> pMap, Connection connection) throws SQLException, ReportExecutorException{
		/* get prefixer object */
		QueryReplacementHelper prefixer = new QueryReplacementHelper();
		
		QueryBuilder queryBuilder = dbHelper.createNestedQuery(query, pMap, prefixer, plainColumnNames);
		queryBuilder.setPlainColumnNames(plainColumnNames);
		queryBuilder.limit(limit);
		
		if(distinct)
			queryBuilder.distinct();
		if(null != paged){
			queryBuilder.offset(paged.get(0));
			queryBuilder.limit(paged.get(1));
		}
		
		if(isCountRows())
			queryBuilder.setCountRows(true);
		
		queryBuilder.setParameterReplacementMap(pMap);
		queryBuilder.setQueryReplacementHelper(prefixer);
		
		if(! ignoreAnyColumnConfiguration)
			queryBuilder.setAdditionalColumns(getAdditionalColumns());
		
		if(null != columns){
			for(Column column : columns){
				queryBuilder.column(column);
				
				/* if we should ignore everything, let us continue */
				if(ignoreAnyColumnConfiguration)
					continue;
				
				if(null != column.getOrder())
					queryBuilder.addOrderBy(column, column.getOrder(), column.getOrderPrecedence());
				
				ColumnFilter cf = new ColumnFilter();
				cf.setColumn(column);
				QryCondition innerCondition = filterExecutor.getQueryCondition(cf, pMap, prefixer, queryBuilder, this, connection);
				
				if(null != innerCondition)
					queryBuilder.where(innerCondition);
			}
		} 
		  
		if(! ignoreAnyColumnConfiguration){
			QryCondition preFilterCondition = addPreFilter(queryBuilder, preFilterRoot, pMap, prefixer, connection);
			if(null != preFilterCondition)
				queryBuilder.innerWhere(preFilterCondition);
		}
		
		/* add comments */
		for(String comment : queryComments){
			queryBuilder.addQueryComment(comment);
		}
		
		/* build preliminaryStmt */
		return queryBuilder.toString();
	}


	private QryCondition addPreFilter(QueryBuilder queryBuilder, FilterBlock filterBlock, Map<String, ParameterValue> pMap, QueryReplacementHelper prefixer, Connection connection) {
		if(null == filterBlock)
			return null;
		
		QryCondition queryCondition = null;
		if(null != filterBlock.getChildBlocks()){
			for(FilterBlock childBlock : filterBlock.getChildBlocks()){
				QryCondition innerCondition = addPreFilter(queryBuilder, childBlock, pMap, prefixer, connection);
				if(null != innerCondition){
					switch(filterBlock.getBlockType()){
					case OR:
						queryCondition = addQryConditionOR(queryCondition, innerCondition, queryBuilder);
						break;
					case AND:
						queryCondition = addQryConditionAND(queryCondition, innerCondition, queryBuilder);
						break;
					}
				}
			}
		}
		if(null != filterBlock.getFilters()){
			for(FilterSpec filter : filterBlock.getFilters()){
				QryCondition innerCondition = null;
				for(DbFilterExecutorHook filterExecutor : hookHandler.getHookers(DbFilterExecutorHook.class)){
					if(filterExecutor.consumes(filter)){
						innerCondition = filterExecutor.getQueryCondition(filter, pMap, prefixer, queryBuilder, this, connection);
						break;
					}
				}
				if(null != innerCondition){
					switch(filterBlock.getBlockType()){
					case OR:
						queryCondition = addQryConditionOR(queryCondition, innerCondition, queryBuilder);
						break;
					case AND:
						queryCondition = addQryConditionAND(queryCondition, innerCondition, queryBuilder);
						break;
					}
				}
			}
		}
		
		return queryCondition;
	}

	public QryCondition addQryConditionAND(QryCondition existing, QryCondition add, QueryBuilder queryBuilder){
		if(null == existing)
			return add;
		return queryBuilder.getNewAndQueryCondition(existing, add);
	}
	
	public QryCondition addQryConditionOR(QryCondition existing, QryCondition add, QueryBuilder queryBuilder){
		if(null == existing)
			return add;
		return queryBuilder.getNewOrQueryCondition(existing, add);
	}
	
	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void applyParameterSet(ParameterSet parameterSet) {
		this.parameterSet = parameterSet;
	}

	public void applyColumnConfiguration(List<Column> columnList) {
		this.columns = columnList;
	}
	
	public boolean isCountRows() {
		return countRows;
	}

	public void setCountRows(boolean countRows) {
		this.countRows = countRows;
	}

	public void distinct(boolean enableDistinct) {
		this.distinct = enableDistinct;
	}

	public void paged(int offset, int length) {
		this.paged = new Vector<Integer>(Arrays.asList(new Integer[]{offset, length}));
	}
	
	public List<Column> getColumns() {
		return columns;
	}

	public void preFilter(FilterBlock rootBlock) {
		this.preFilterRoot = rootBlock;
	}

	public String getInnerQuery() {
		return query;
	}

	public DatabaseHelper getDbHelper() {
		return dbHelper;
	}

	public void setPlainColumnNames(List<String> plainColumnNames) {
		this.plainColumnNames = plainColumnNames;
	}

	public void setAdditionalColumnSpecs(
			List<AdditionalColumnSpec> additionalColumns) {
		this.additionalColumns = additionalColumns;
	}
	
	public List<AdditionalColumnSpec> getAdditionalColumns() {
		return additionalColumns;
	}

	public TableDBDataSource getDatasource() {
		return datasource;
	}
	
	/**
	 * if true ignores additional columns and prefilter
	 * @param ignore
	 */
	public void setIgnoreAnyColumnConfiguration(boolean ignore) {
		this.ignoreAnyColumnConfiguration = ignore;
	}

	public Column getColumnByName(String name) {
		if(null == name)
			return null;
		for(Column col : columns)
			if(name.equals(col.getName()))
				return col;
		return null;
	}

	public void addQueryComment(String comment) {
		queryComments.add(comment);
	}

	public List<String> getPlainColumnNames() {
		return plainColumnNames;
	}

	public boolean isDistinct() {
		return distinct;
	}
	
	public boolean hasAggregateColumns(){
		for(Column col : getColumns()){
			if(null != col.getAggregateFunction()){
				return true;
			}
		}
		return false;
	}
}
