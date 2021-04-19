package net.datenwerke.rs.base.service.dbhelper.querybuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import net.datenwerke.gxtdto.client.utils.SqlTypes;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;
import net.datenwerke.rs.base.service.dbhelper.queries.Query;
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
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column.OrderPrecedence;
import net.datenwerke.rs.base.service.reportengines.table.entities.Order;
import net.datenwerke.rs.core.service.i18ntools.I18nToolsService;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterValue;

import com.google.inject.Inject;

public class QueryBuilder{
	
	public final static String uniqueColumnPrefix = "xx__rs_col_";
	public final static String uniqueAddColumnSpecPrefix = "xx__rs_acols_";
	
	@Inject
	private static HookHandlerService hookHandler;
	
	@Inject
	private static I18nToolsService i18nToolsService;
	
	public enum BaseCommand {SELECT};

	public class OrderDefinition{
		private final Column column;
		private final Order order;
		private final OrderPrecedence precedence;
		public OrderDefinition(Column column, Order order, OrderPrecedence precedence) {
			super();
			this.column = column;
			this.order = order;
			this.precedence = precedence;
		}
		public Column getColumn() {
			return column;
		}
		public Order getOrder() {
			return order;
		}
		public OrderPrecedence getPrecedence() {
			return precedence;
		}
	};
	
	private boolean countRows;
	
	private DatabaseHelper dbHelper;
	
	private QueryReplacementHelper queryReplacementHelper;
	private Map<String, ParameterValue> parameterReplacementMap;
	
	/**
	 * Stores a list of vectors containing the selected columns and their respective aliases
	 */
	private ArrayList<Column> columns = new ArrayList<Column>();
	private ArrayList<String> sourceTables = new ArrayList<String>();
	private ArrayList<QryCondition> conditions = new ArrayList<QryCondition>();
	private ArrayList<QryCondition> innerConditions = new ArrayList<QryCondition>();
	private ArrayList<OrderDefinition> orders = new ArrayList<OrderDefinition>();
	private List<AdditionalColumnSpec> additionalColumns = new ArrayList<AdditionalColumnSpec>();
	private int limit = -1;
	private boolean distinct = false;
	private int offset = -1;
	private List<String> plainColumnNames;
	
	private BaseCommand baseCommand;

	private String innerQuery;
	private List<String> queryComments = new ArrayList<String>();
	
	public QueryBuilder(DatabaseHelper dbHelper, Map<String, ParameterValue> parameterReplacementMap, QueryReplacementHelper queryReplacementHelper) {
		this.dbHelper = dbHelper;
		this.parameterReplacementMap = parameterReplacementMap;
		this.queryReplacementHelper = queryReplacementHelper;
	}
	
	public QueryBuilder(DatabaseHelper dbHelper, String innerQuery, Map<String, ParameterValue> parameterReplacementMap, QueryReplacementHelper queryReplacementHelper) {
		this.dbHelper = dbHelper;
		this.innerQuery = innerQuery;
		this.parameterReplacementMap = parameterReplacementMap;
		this.queryReplacementHelper = queryReplacementHelper;
		select().from(dbHelper.nestedSelect(innerQuery));
	}
	
	public String getInnerQuery(){
		return innerQuery;
	}
	
	public BaseCommand getBaseCommand(){
		return baseCommand;
	}
	
	public boolean isDirty(){
		return 
			 isCountRows() ||
			 isLimit() ||
			 isOffset() ||
			 isDistinct() ||
			 columns.size() != 0 ||
			 conditions.size() != 0 ||
			 orders.size() != 0 ||
			 ( sourceTables.size() != 1 || ( sourceTables.size() > 0 && !sourceTables.get(0).equals("(" + getInnerQuery() + ")"))) //$NON-NLS-1$ //$NON-NLS-2$
		    ;
	}


	public void addQueryComment(String comment) {
		this.queryComments .add(comment);
	}
	
	public List<String> getQueryComments() {
		return queryComments;
	}

	public QueryBuilder select(){
		baseCommand = baseCommand.SELECT;
		return this;
	}
	
	
	public QueryBuilder column(Column col){
		columns.add(col);
		return this;
	}
	
	public QueryBuilder setAdditionalColumns(
			List<AdditionalColumnSpec> additionalColumns) {
		if(null == additionalColumns)
			this.additionalColumns = new ArrayList<AdditionalColumnSpec>();
		else
			this.additionalColumns = additionalColumns;
		return this;
	}
	
	public QueryBuilder from(String tableName){
		sourceTables.add(tableName);
		return this;
	}
	
	public QueryBuilder where(QryCondition cond){
		conditions.add(cond);
		return this;
	}
	
	public QueryBuilder innerWhere(QryCondition cond){
		innerConditions.add(cond);
		return this;
	}
	
	public QueryBuilder limit(int i){
		limit = i;
		return this;
	}
	
	public QueryBuilder addOrderBy(Column column){
		return addOrderBy(column, Order.ASC, OrderPrecedence.NORMAL);
	}
	
	public QueryBuilder addOrderBy(Column column, Order order, OrderPrecedence precedence){
		orders.add(new OrderDefinition(column, order, precedence));
		
		return this;
	}
	
	public ArrayList<Column> getColumns() {
		return columns;
	}
	
	public List<AdditionalColumnSpec> getAdditionalColumns() {
		return additionalColumns;
	}

	public ArrayList<String> getSourceTables() {
		return sourceTables;
	}

	public ArrayList<QryCondition> getConditions() {
		return conditions;
	}
	
	public ArrayList<QryCondition> getInnerConditions() {
		return innerConditions;
	}

	public ArrayList<QryCondition> getConditionsWhere() {
		ArrayList<QryCondition> res = new ArrayList<QryCondition>();
		
		qcl: for(QryCondition qc : conditions){
			for(Column c : qc.getContainedColumns()){
				if(null == c.getAggregateFunction()){
					res.add(qc);
					continue qcl;
				}
			}
		}
		return res;
	}
	
	public ArrayList<QryCondition> getConditionsHaving() {
		ArrayList<QryCondition> res = new ArrayList<QryCondition>();

		qcl: for(QryCondition qc : conditions){
			for(Column c : qc.getContainedColumns()){
				if(null != c.getAggregateFunction()){
					res.add(qc);
					continue qcl;
				}
			}
		}
		return res;
	}

	public int getLimit() {
		return limit;
	}
	
	public boolean isLimit(){
		return limit != -1;
	}

	public boolean isCountRows() {
		return countRows;
	}

	public void setCountRows(boolean countRows) {
		this.countRows = countRows;
	}

	public int getOffset() {
		return offset;
	}
	
	public boolean isOffset(){
		return offset != -1;
	}
	
	public boolean isDistinct() {
		return distinct;
	}
	
	/**
	 * Returns the order definitions by precedence
	 * 
	 */
	public List<OrderDefinition> getOrderDefinitions(){
		List<OrderDefinition> byPrecedence = new ArrayList<QueryBuilder.OrderDefinition>(); 
		
		for(OrderPrecedence prec : OrderPrecedence.values())
			for(OrderDefinition def : orders)
				if(prec.equals(def.getPrecedence()))
					byPrecedence.add(def);
		
		for(OrderDefinition def : orders)
			if(null == def.getPrecedence())
				byPrecedence.add(def);
		
		return byPrecedence;
	}
	
	@Override
	public String toString() {
		return dbHelper.buildQueryToString(this);
	}

	public void distinct() {
		distinct = true;
	}


	public void offset(int offset) {
		this.offset = offset;
	}

	public String getUniqueColumnIdentifier(Column column){
		// TODO: see also line 98 in TableDbDatasource
		int index = columns.indexOf(column);
		String id = column.getName();
		if(-1 != index)
		  id = uniqueColumnPrefix + index;
		return id;
	}
	
	public String getUniqueAddColumnSpecIdentifier(AdditionalColumnSpec col) {
		int i = 1;
		for (AdditionalColumnSpec addCol : additionalColumns) {
			if(addCol.getName().equals(col.getName()))
				return uniqueAddColumnSpecPrefix + i;
			i++;
		}
		throw new IllegalStateException("Could not find additional column");
	}

	
	public void setPlainColumnNames(List<String> plainColumnNames) {
		this.plainColumnNames = plainColumnNames;
	}
	
	public DatabaseHelper getDbHelper() {
		return dbHelper;
	}
	
	private Object convertFilterValue(Object odata, Integer type){
		if(null == type)
			return odata;
		
		if(SqlTypes.isNumerical(type)){
			return parseNumberData(odata, type);
		}else if(SqlTypes.isDateLikeType(type)){
			Class c = DatabaseHelper.mapSQLTypeToJava(type);
			if(c.equals(java.sql.Date.class)){
				return java.sql.Date.valueOf(String.valueOf(odata));
			}else if(c.equals(java.sql.Time.class)){
				return java.sql.Time.valueOf(String.valueOf(odata));
			}else if(c.equals(java.sql.Timestamp.class)){
				return java.sql.Timestamp.valueOf(String.valueOf(odata));
			}
		}else if(SqlTypes.BOOLEAN == type.intValue()){
			return Boolean.valueOf(String.valueOf(odata));
		}

		return odata;
	}
	
	
	public List<String> nextReplacement(Collection<?> dataCollection, Integer type) {
		List<String> replacementCollection = new ArrayList<String>();

		for(Object odata : dataCollection){
			Object data = convertFilterValue(odata, type);

			String replacement = queryReplacementHelper.nextReplacement(parameterReplacementMap, data);
			replacementCollection.add(replacement);
		}

	return replacementCollection;
}
	
	public Object nextReplacement(Object odata, Integer type) {
		Object data = convertFilterValue(odata, type);
	
		String replacement = queryReplacementHelper.nextReplacement(parameterReplacementMap, data);
		return replacement;
	}
	
	private Object parseNumberData(Object odata, Integer type) {
		if(odata instanceof String){
			if(dbHelper.containsWildcard((String)odata)){
				return dbHelper.parseWildcardNumber((String)odata);
			}
		}
		try {
			return i18nToolsService.getSystemNumberFormatter().parse(odata.toString());
		} catch (Exception e) {
			return odata;
		} 
	}
	
	
	public QueryReplacementHelper getQueryReplacementHelper() {
		return queryReplacementHelper;
	}
	
	public void setQueryReplacementHelper(
			QueryReplacementHelper queryReplacementHelper) {
		this.queryReplacementHelper = queryReplacementHelper;
	}
	
	public Map<String, ParameterValue> getParameterReplacementMap() {
		return parameterReplacementMap;
	}
	
	public void setParameterReplacementMap(Map<String, ParameterValue> parameterReplacementMap) {
		this.parameterReplacementMap = parameterReplacementMap;
	}

	public Query getQuery() {
		return dbHelper.buildQuery(this);
	}		
	
	public AndQueryCondition getNewAndQueryCondition(QryCondition first, QryCondition second){
		return dbHelper.getNewAndQueryCondition(first, second);
	}
	
	public GreaterQueryCondition getNewGreaterQueryCondition(Column column, Object data){
		return dbHelper.getNewGreaterQueryCondition(column, data, this);
	}

	public EqualQueryCondition getNewEqualQueryCondition(Column column, Object data){
		return dbHelper.getNewEqualQueryCondition(column, data, this);
	}
	
	public GreaterEqualQueryCondition getNewGreaterEqualQueryCondition(Column column, Object data){
		return dbHelper.getNewGreaterEqualQueryCondition(column, data, this);
	}
	
	public LessQueryCondition getNewLessQueryCondition(Column column, Object data){
		return dbHelper.getNewLessQueryCondition(column, data, this);
	}
	
	public LessEqualQueryCondition getNewLessEqualQueryCondition(Column column, Object data){
		return dbHelper.getNewLessEqualQueryCondition(column, data, this);
	}

	public InQueryCondition getNewInQueryCondition(Column column, Collection dataCollection){
		return dbHelper.getNewInQueryCondition(column, dataCollection, this);
	}
	
	public LikeQueryCondition getNewLikeQueryCondition(Column column, Object data){
		return dbHelper.getNewLikeQueryCondition(column, data, this);
	}
	
	public NotQueryCondition getNewNotQueryCondition(QryCondition inner){
		return dbHelper.getNewNotQueryCondition(inner);
	}
	
	public IsNullQueryCondition getNewIsNullQueryCondition(Column column){
		return dbHelper.getNewIsNullQueryCndition(column);
	}
	
	public OrQueryCondition getNewOrQueryCondition(QryCondition first, QryCondition second){
		return dbHelper.getNewOrQueryCondition(first, second);
	}
	
	public HookHandlerService getHookHandler(){
		return hookHandler;
	}
	
	public List<String> getPlainColumnNames(){
		return plainColumnNames;
	}

	public boolean ignoreHiddenColumns() {
		return isDistinct() || hasAggregateColumns();
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
