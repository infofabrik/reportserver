package net.datenwerke.rs.base.service.dbhelper;

import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Provider;

import net.datenwerke.rs.utils.misc.StringEscapeUtils;

import com.google.common.collect.Lists;
import com.google.inject.Inject;

import net.datenwerke.dtoservices.dtogenerator.annotations.AdditionalField;
import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeMethodToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.service.dbhelper.annotations.QueryConditionInMaxSize;
import net.datenwerke.rs.base.service.dbhelper.db.AmazonRedshift;
import net.datenwerke.rs.base.service.dbhelper.db.GoogleBigQuery;
import net.datenwerke.rs.base.service.dbhelper.db.MariaDB;
import net.datenwerke.rs.base.service.dbhelper.db.MySQL;
import net.datenwerke.rs.base.service.dbhelper.db.PostgreSQL;
import net.datenwerke.rs.base.service.dbhelper.db.mssql.MsSQL;
import net.datenwerke.rs.base.service.dbhelper.db.oracle.Oracle;
import net.datenwerke.rs.base.service.dbhelper.db.teradata.Teradata;
import net.datenwerke.rs.base.service.dbhelper.dtogen.post.DatabaseHelper2DtoPostProcessor;
import net.datenwerke.rs.base.service.dbhelper.hooks.InnerQueryModificationHook;
import net.datenwerke.rs.base.service.dbhelper.hooks.StatementModificationHook;
import net.datenwerke.rs.base.service.dbhelper.queries.AggregateHavingQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.ColumnFilterQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.ColumnQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.CountQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.FilterWhereQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.InnerFilterWhereQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.LimitQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.OffsetQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.OrderLimitQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.OrderOffsetQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.OrderQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.Query;
import net.datenwerke.rs.base.service.dbhelper.queries.SimpleStringQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.SimpleWrapperQuery;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.ColumnNamingService;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryReplacementHelper;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QuoteHelper;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.queryconditions.AndQueryCondition;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.queryconditions.EqualQueryCondition;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.queryconditions.GreaterEqualQueryCondition;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.queryconditions.GreaterQueryCondition;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.queryconditions.InQueryCondition;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.queryconditions.IsNullQueryCondition;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.queryconditions.LessEqualQueryCondition;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.queryconditions.LessQueryCondition;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.queryconditions.LikeQueryCondition;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.queryconditions.NotQueryCondition;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.queryconditions.OrQueryCondition;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.queryconditions.QryCondition;
import net.datenwerke.rs.base.service.reportengines.table.entities.AdditionalColumnSpec;
import net.datenwerke.rs.base.service.reportengines.table.entities.AggregateFunction;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.base.service.reportengines.table.entities.ColumnReference;
import net.datenwerke.rs.core.service.i18ntools.I18nToolsService;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterValue;

/**
 * 
 *
 */
@GenerateDto(
	dtoPackage="net.datenwerke.rs.base.client.dbhelper.dto",
	additionalFields = {
			@AdditionalField(name="jdbcDriverAvailable",type=Boolean.class)
		},
	poso2DtoPostProcessors = {DatabaseHelper2DtoPostProcessor.class}
)
abstract public class DatabaseHelper {

	public interface ResultSetObjectHandler {
		public Object getObject(int pos) throws SQLException;
	}
	
	@Inject
	protected static I18nToolsService i18nToolsService;
	
	@Inject
	protected static HookHandlerService hookHandlerService;
	
	@Inject
	@QueryConditionInMaxSize
	protected static Provider<Integer> queryConditionIntMaxSize;
	
	@ExposeMethodToClient
	public abstract String getDriver();
	
	/**
	 * A unique descriptor that is not longer than 32 characters.
	 */
	@ExposeMethodToClient
	public abstract String getDescriptor();
	
	@ExposeMethodToClient
	public abstract String getName();
	
	public String getStringQuoteChar() {
		return "'";
	}
	public String getIdentifierQuoteChar(){
		return "\"";
	}
	
	
	public ResultSetObjectHandler createResultSetHandler(final ResultSet resultSet, Connection con) throws SQLException {
		return pos -> resultSet.getObject(pos);
	}

	
	public String buildQueryToString(QueryBuilder builder){
		StringBuffer buf = new StringBuffer();
		
		if(! builder.isDirty() && null != builder.getInnerQuery())
			return builder.getInnerQuery();
		
		buildQuery(builder).appendToBuffer(buf);
		
		if(null != builder.getQueryComments()){
			for(String comment : builder.getQueryComments()){
				buf.append(" ");
				buf.append(createComment(comment));
			}
		}
		
		String query = buf.toString();

		for(StatementModificationHook hook : hookHandlerService.getHookers(StatementModificationHook.class)){
			String q = hook.modifyStatement(query, builder);
			if(null != q)
				query = q;
		}
		
		return query;
	}
	
	
	public Query buildQuery(final QueryBuilder builder){
		
		ColumnNamingService uniqueNameService = new ColumnNamingService() {
			@Override
			public String getColumnName(Column column) {
				if(column instanceof AdditionalColumnSpec)
					return getIdentifierQuoteChar() + builder.getUniqueAddColumnSpecIdentifier(((AdditionalColumnSpec)column)) + getIdentifierQuoteChar();
				return getIdentifierQuoteChar() + builder.getUniqueColumnIdentifier(column) + getIdentifierQuoteChar();
			}
		};
		
		final QuoteHelper quoteHelper = new QuoteHelper(this, builder);
		
		ColumnNamingService baseNameService = new ColumnNamingService() {
			@Override
			public String getColumnName(Column column) {
				if(column instanceof ColumnReference)
					return getIdentifierQuoteChar() + builder.getUniqueAddColumnSpecIdentifier(((ColumnReference)column).getReference()) + getIdentifierQuoteChar();
				return quoteHelper.quoteIdentifier(column.getName());
			}
		};
		
		switch(builder.getBaseCommand()){
			case SELECT:

				/* preprocess inner query */
				String innerQuery = builder.getInnerQuery();
				for(InnerQueryModificationHook hook : hookHandlerService.getHookers(InnerQueryModificationHook.class)){
					innerQuery = hook.modifyQuery(innerQuery, builder);
				}
				
				/* select columns:
				 * As we cannot be sure that the query is a simple SELECT A FROM B we
				 * have to wrap it in any case. If we do not perform column selection
				 * we use a dummy wrapper  
				 * */
				Query currentInnerQuery = null;
				if(!builder.getColumns().isEmpty() ||!builder.getAdditionalColumns().isEmpty()){
					currentInnerQuery = new SimpleStringQuery(innerQuery);
					
					currentInnerQuery = getNewColumnQuery(currentInnerQuery, builder, baseNameService, uniqueNameService);
				} else {
					currentInnerQuery = new SimpleWrapperQuery(innerQuery);
				}
				
				/* filter/where */
				if(!builder.getInnerConditions().isEmpty())
					currentInnerQuery = getNewInnerFilterWhereQuery(currentInnerQuery, builder, baseNameService);

				if(!builder.getConditionsWhere().isEmpty())
					currentInnerQuery = getNewFilterWhereQuery(currentInnerQuery, builder, uniqueNameService);
				
				/* aggregate/having */
				currentInnerQuery = getNewAggregateHavingQuery(currentInnerQuery, builder, uniqueNameService);

				/* filter columns */
				currentInnerQuery = getNewColumnFilterQuery(currentInnerQuery, builder, uniqueNameService);
				
				/* Patch for sorting issues (e.g. RS-3230). This will be refactored in RS-3239. */
				if (builder.getDbHelper() instanceof MariaDB 
						|| builder.getDbHelper() instanceof Teradata
						|| builder.getDbHelper() instanceof MsSQL
						|| builder.getDbHelper() instanceof MySQL
						|| builder.getDbHelper() instanceof PostgreSQL
						|| builder.getDbHelper() instanceof Oracle
						|| builder.getDbHelper() instanceof GoogleBigQuery
						|| builder.getDbHelper() instanceof AmazonRedshift
						) {
					/* Order by */
					if(!builder.getOrderDefinitions().isEmpty() && !builder.isLimit() && !builder.isOffset())
						currentInnerQuery = getNewOrderQuery(currentInnerQuery, builder, uniqueNameService);
					
					/* Order by and limit/offset */
					if(!builder.getOrderDefinitions().isEmpty() && ( builder.isLimit() || builder.isOffset() ))
						if (builder.isLimit() && !builder.isOffset())
							return getNewOrderLimitQuery(currentInnerQuery, builder, uniqueNameService);
						else if(builder.isOffset())
							return getNewOrderOffsetQuery(currentInnerQuery, builder, uniqueNameService);

					/* limit */
					if(builder.getOrderDefinitions().isEmpty() && builder.isLimit() && !builder.isOffset())
						currentInnerQuery = getNewLimitQuery(currentInnerQuery, builder);
					
					/* offset */
					if(builder.getOrderDefinitions().isEmpty() && builder.isOffset())
						currentInnerQuery = getNewOffsetQuery(currentInnerQuery, builder, uniqueNameService);
				} else {
					/* Order by */
					if(!builder.getOrderDefinitions().isEmpty())
						currentInnerQuery = getNewOrderQuery(currentInnerQuery, builder, uniqueNameService);
					
					/* limit */
					if(builder.isLimit() && !builder.isOffset())
						currentInnerQuery = getNewLimitQuery(currentInnerQuery, builder);
					
					/* offset */
					if(builder.isOffset())
						currentInnerQuery = getNewOffsetQuery(currentInnerQuery, builder, uniqueNameService);
				}
				
				/* count */
				if(builder.isCountRows())
					currentInnerQuery = getNewCountQuery(currentInnerQuery);
				
				return currentInnerQuery;
				
			default:
				throw new RuntimeException("Unkown base command");
		}

	}

	public String aggregateFunction(AggregateFunction function, String columnName) {
		switch(function){
		case COUNT_DISTINCT:
			return "COUNT(DISTINCT("+columnName+"))";
		default:
			return function.name()+"("+columnName+")";
		}
	}

	public String quoteAlias(String alias){
		return "\"" + alias + "\"";
	}

	public String escapeString(String string){
		return StringEscapeUtils.escapeSql(string);
	}

	public String conditionIn(String column, List<String> data, boolean caseSensitive) {
		StringBuffer buf = new StringBuffer(" ");
		
		int queryConditionIntMaxSize = DatabaseHelper.queryConditionIntMaxSize.get();
		
		if(data.size() > queryConditionIntMaxSize)
			buf.append("(");

		boolean first = true;
		for(List<String> sublist : Lists.partition(data, queryConditionIntMaxSize)){
			Iterator<String> it = sublist.iterator();
			
			if(! first)
				buf.append(" OR ");
			
			if(! caseSensitive)
				buf.append("LOWER(");  //$NON-NLS-1$
			buf.append(column);
			if(! caseSensitive)
				buf.append(") "); //$NON-NLS-1$
			else
				buf.append(' ');
			
			buf.append("IN (") //$NON-NLS-1$
				.append(it.next());
			
			while(it.hasNext())
				buf.append(", ") //$NON-NLS-1$
				   .append(it.next());
			
			buf.append(')'); //$NON-NLS-1$
			
			first = false;
		}
			
		if(data.size() > queryConditionIntMaxSize)
			buf.append(")");
		
		return buf.toString();
	}

	public String createDummyQuery(){
		return "SELECT * FROM DUAL"; //$NON-NLS-1$
	}


	public boolean containsWildcard(String data) {
		return data.contains("%") || data.contains("_");
	}
	
	
	public String parseWildcardNumber(String odata) {
		return odata.replaceAll("[^0-9%\\?\\.]", "");
	}
	
	
	public String nestedSelect(String innerQuery){
		return "("+ innerQuery + ") origStmt";
	}
	
	protected Object createComment(String comment) {
		return new StringBuilder(" /* ").append(comment.replace("/*", "").replace("*/", "")).append(" */ ");
	}

	/**
	 * Can be used to put a function on top of a column for comparision (for example clobs in oracle need a to_char).
	 * @param name
	 * @param column
	 */
	public String prepareColumnForComparison(String name, Column column) {
		return name;
	}
	

	public String prepareColumnForSorting(String name, Column column) {
		return name;
	}
	

	public String prepareColumnForDistinctQuery(String name, Column col) {
		return name;
	}
	
	public static Class<?> mapSQLTypeToJava(int columnType) {
		switch(columnType){
		case Types.CHAR:
		case Types.VARCHAR:
		case Types.LONGVARCHAR:
		case Types.NVARCHAR:
		case Types.NCHAR:
		case Types.LONGNVARCHAR:
		case Types.SQLXML:
			// we map everything not-known to a string
		case Types.OTHER:
			return String.class;
		case Types.NUMERIC:
		case Types.DECIMAL:
			return BigDecimal.class;
		case Types.BIT:
		case Types.BOOLEAN:
			return Boolean.class;
		case Types.TINYINT:
			return Byte.class;
		case Types.SMALLINT:
			return Short.class;
		case Types.INTEGER:
			return Integer.class;
		case Types.BIGINT:
			return Long.class;
		case Types.REAL:
			return Float.class;
		case Types.FLOAT:
		case Types.DOUBLE:
			return Double.class;
		case Types.BINARY:
		case Types.VARBINARY:
		case Types.LONGVARBINARY:
			return Byte[].class;
		case Types.DATE:
			return Date.class;
		case Types.TIME:
			return Time.class;
		case Types.TIMESTAMP:
		case -102: // Oracle: Timestamp with Local Timezone
			return Timestamp.class;
		case Types.CLOB:
			return Clob.class;
		case Types.BLOB:
			return Blob.class;
		case Types.ARRAY:
			return Array.class;
		case Types.NULL:
			return Object.class;
		}
		
		throw new IllegalArgumentException("Could not map " + columnType + " to a java class"); //$NON-NLS-1$ //$NON-NLS-2$
	}
	
	
	
	/* Factory Methods */
	
	public QueryBuilder createNestedQuery(String innerQuery, Map<String, ParameterValue> parameteReplacementMap, QueryReplacementHelper queryReplacementHelper, List<String> plainColumnNames){
		QueryBuilder builder = new QueryBuilder(this, innerQuery, parameteReplacementMap, queryReplacementHelper);
		builder.setPlainColumnNames(plainColumnNames);
		return builder;
	}

	public AggregateHavingQuery getNewAggregateHavingQuery(Query nestedQuery, QueryBuilder queryBuilder, ColumnNamingService columnNamingService){
		return new AggregateHavingQuery(nestedQuery, queryBuilder, columnNamingService);
	}
	
	public ColumnFilterQuery getNewColumnFilterQuery(Query nestedQuery, QueryBuilder queryBuilder, ColumnNamingService uniqueNameService){
		return new ColumnFilterQuery(nestedQuery, queryBuilder, uniqueNameService);
	}
	
	public ColumnQuery getNewColumnQuery(Query nestedQuery, QueryBuilder queryBuilder, ColumnNamingService baseNameService, ColumnNamingService uniqueNameService){
		return new ColumnQuery(nestedQuery, queryBuilder, baseNameService, uniqueNameService);
	}
	
	public CountQuery getNewCountQuery(Query nestedQuery){
		return new CountQuery(nestedQuery);
	}
	
	public FilterWhereQuery getNewFilterWhereQuery(Query nestedQuery, QueryBuilder queryBuilder, ColumnNamingService columnNamingService){
		return new FilterWhereQuery(nestedQuery, queryBuilder, columnNamingService);
	}
	
	public InnerFilterWhereQuery getNewInnerFilterWhereQuery(Query nestedQuery, QueryBuilder queryBuilder, ColumnNamingService uniqueNameService){
		return new InnerFilterWhereQuery(nestedQuery, queryBuilder, uniqueNameService);
	}
	
	public LimitQuery getNewLimitQuery(Query nestedQuery, QueryBuilder queryBuilder){
		return new LimitQuery(nestedQuery, queryBuilder);
	}
	
	public OrderLimitQuery getNewOrderLimitQuery(Query nestedQuery, QueryBuilder queryBuilder, ColumnNamingService columnNamingService) {
		return new OrderLimitQuery(nestedQuery, queryBuilder, columnNamingService);
	}
	
	public OrderOffsetQuery getNewOrderOffsetQuery(Query nestedQuery, QueryBuilder queryBuilder, ColumnNamingService columnNamingService) {
		return new OrderOffsetQuery(nestedQuery, queryBuilder, columnNamingService);
	}
	
	public OffsetQuery getNewOffsetQuery(Query nestedQuery, QueryBuilder queryBuilder, ColumnNamingService columnNamingService){
		return new OffsetQuery(nestedQuery, queryBuilder, columnNamingService);
	}
	
	public OrderQuery getNewOrderQuery(Query nestedQuery, QueryBuilder queryBuilder, ColumnNamingService columnNamingService){
		return new OrderQuery(nestedQuery, queryBuilder, columnNamingService);
	}
	
	public AndQueryCondition getNewAndQueryCondition(QryCondition first, QryCondition second){
		return new AndQueryCondition(first, second);
	}

	public EqualQueryCondition getNewEqualQueryCondition(Column column, Object data, QueryBuilder queryBuilder){
		return new EqualQueryCondition(column, data, queryBuilder);
	}
	
	public GreaterQueryCondition getNewGreaterQueryCondition(Column column, Object data, QueryBuilder queryBuilder){
		return new GreaterQueryCondition(column, data, queryBuilder);
	}
	
	public GreaterEqualQueryCondition getNewGreaterEqualQueryCondition(Column column, Object data, QueryBuilder queryBuilder){
		return new GreaterEqualQueryCondition(column, data, queryBuilder);
	}

	public LessQueryCondition getNewLessQueryCondition(Column column, Object data, QueryBuilder queryBuilder){
		return new LessQueryCondition(column, data, queryBuilder);
	}
	
	public LessEqualQueryCondition getNewLessEqualQueryCondition(Column column, Object data, QueryBuilder queryBuilder){
		return new LessEqualQueryCondition(column, data, queryBuilder);
	}
	
	public InQueryCondition getNewInQueryCondition(Column column, Collection dataCollection, QueryBuilder queryBuilder){
		return new InQueryCondition(column, dataCollection, queryBuilder);
	}
	
	public LikeQueryCondition getNewLikeQueryCondition(Column column, Object data, QueryBuilder queryBuilder){
		return new LikeQueryCondition(column, data, queryBuilder);
	}
	
	public NotQueryCondition getNewNotQueryCondition(QryCondition inner){
		return new NotQueryCondition(inner);
	}
	
	public IsNullQueryCondition getNewIsNullQueryCndition(Column column){
		return new IsNullQueryCondition(column);
	}
	
	public OrQueryCondition getNewOrQueryCondition(QryCondition first, QryCondition second){
		return new OrQueryCondition(first, second);
	}
	


}


