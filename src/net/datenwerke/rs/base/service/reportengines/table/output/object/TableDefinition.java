package net.datenwerke.rs.base.service.reportengines.table.output.object;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gxtdto.client.utils.SqlTypes;
import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;

/**
 * 
 *
 */
@GenerateDto(
	dtoPackage="net.datenwerke.rs.base.client.reportengines.table.dto",
	generateDto2Poso=false
)
public class TableDefinition implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1753285090408101163L;

	@ExposeToClient
	private List<String> columnNames;
	
	private List<String> originalColumnNames;
	
	private List<Class<?>> columnTypes;
	
	@ExposeToClient
	private List<Integer> displaySizes;
	
	@ExposeToClient
	private HashMap<String, Integer> columnIndex;
	
	@ExposeToClient
	private List<Integer> sqlColumnTypes;
	
	public HashMap<String, Integer> getColumnIndex() {
		return columnIndex;
	}
	public TableDefinition(List<String> columnNames, List<Class<? extends Object>> columnTypes) {
		this(columnNames, columnTypes, columnTypesToSqlTypes(columnTypes));
	}

	public TableDefinition(List<String> columnNames, List<Class<?>> columnTypes, List<Integer> sqlColumnTypes) {
		this(columnNames, new ArrayList<String>(columnNames), columnTypes, sqlColumnTypes );
	}
	
	public TableDefinition(List<String> columnNames, List<String> originalColumnNames, List<Class<?>> columnTypes, List<Integer> sqlColumnTypes) {
		super();
		
		/* initialize display sizes */
		List<Integer> displaySizes = new ArrayList<Integer>();
		for(int i = 0; i < columnNames.size(); i++)
			displaySizes.add(0);

		this.columnNames = columnNames;
		this.originalColumnNames = originalColumnNames;
		this.columnTypes = columnTypes;
		this.displaySizes = displaySizes;
		this.sqlColumnTypes = sqlColumnTypes;
		
		buildColumnIndex();
	}
	
	private TableDefinition(List<String> columnNames, List<String> originalColumnNames, List<Class<?>> columnTypes, List<Integer> sqlColumnTypes, List<Integer> displaySizes) {
		super();
		this.columnNames = columnNames;
		this.originalColumnNames = originalColumnNames;
		this.columnTypes = columnTypes;
		this.sqlColumnTypes = sqlColumnTypes;
		this.displaySizes = displaySizes;
		
		buildColumnIndex();
	}

	public TableDefinition(List<String> names) {
		this(names, new ArrayList<Class<? extends Object>>());
	}
	
	public TableDefinition(){
		this(new ArrayList<String>());
	}

	public static TableDefinition fromResultSetMetaData(ResultSetMetaData metaData) throws SQLException{
		List<String> names = new ArrayList<String>();
		List<Class<?>> types = new ArrayList<Class<?>>();
		List<Integer> sqlColumnTypes = new ArrayList<Integer>();
		List<Integer> displaySizes = new ArrayList<Integer>();
		
		
		for(int i = 1; i <= metaData.getColumnCount(); i++){
			displaySizes.add(metaData.getColumnDisplaySize(i));
			names.add(metaData.getColumnLabel(i));
			types.add(DatabaseHelper.mapSQLTypeToJava(metaData.getColumnType(i)));
			sqlColumnTypes.add(metaData.getColumnType(i));
		}
		
		return new TableDefinition(names, names, types, sqlColumnTypes, displaySizes);
	}
	
	public static TableDefinition fromResultSetMetaData(ResultSetMetaData metaData, List<Column> columns) throws SQLException{
		List<String> names = new ArrayList<String>();
		List<Class<?>> types = new ArrayList<Class<?>>();
		List<Integer> sqlColumnTypes = new ArrayList<Integer>();
		List<Integer> displaySizes = new ArrayList<Integer>();
		
		for(int i = 1; i <= metaData.getColumnCount(); i++){
			String label = metaData.getColumnLabel(i);
			int colIdx = Integer.valueOf(label.substring(QueryBuilder.uniqueColumnPrefix.length())) + 1;
			
			if(null != columns && columns.get(colIdx - 1).isHidden())
				continue;
			
			displaySizes.add(metaData.getColumnDisplaySize(i));
			names.add(metaData.getColumnLabel(i));
			types.add(DatabaseHelper.mapSQLTypeToJava(metaData.getColumnType(i)));
			sqlColumnTypes.add(metaData.getColumnType(i));
			
		}
		
		return new TableDefinition(names, names, types, sqlColumnTypes, displaySizes);
	}
	
	protected static List<Integer> columnTypesToSqlTypes(List<Class<? extends Object>> columnTypes) {
		ArrayList<Integer> sqlTypes = new ArrayList<Integer>();
		
		for(Object o : columnTypes){
			if(null == o)
				sqlTypes.add(SqlTypes.VARCHAR);
			
			if(String.class.equals(o)){
				sqlTypes.add(SqlTypes.VARCHAR);
			} else if(Integer.class.equals(o)){
				sqlTypes.add(SqlTypes.INTEGER);
			} else if(Short.class.equals(o)){
				sqlTypes.add(SqlTypes.SMALLINT);
			} else if(Double.class.equals(o)){
				sqlTypes.add(SqlTypes.DOUBLE);
			} else if(Float.class.equals(o)){
				sqlTypes.add(SqlTypes.FLOAT);
			} else if(Date.class.equals(o)){
				sqlTypes.add(SqlTypes.DATE);
			} else if(BigDecimal.class.equals(o)){
				sqlTypes.add(SqlTypes.DECIMAL);
			}  else if(Character.class.equals(o)){
				sqlTypes.add(SqlTypes.CHAR);
			}  else if(Boolean.class.equals(o)){
				sqlTypes.add(SqlTypes.BOOLEAN);
			} else {
				/* default */
				sqlTypes.add(SqlTypes.VARCHAR);
			}
		}
		
		return sqlTypes;
	}

	
	public void setColumnIndex(HashMap<String, Integer> columnIndex) {
		this.columnIndex = columnIndex;
	}
	
	public TableDefinition addColumn(String name, Class<?> type){
		columnNames.add(name);
		displaySizes.add(0);
		columnTypes.add(type);
		
		buildColumnIndex();
		
		return this;
	}
	
	/**
	 * Returns a list with column definitions.
	 * 
	 * Each entry is a object array:
	 * <ul>
	 *  <li>[0] -&gt; name (String)</li>
	 *  <li>[1] -&gt; Javatype (Class&lt;?&gt;)</li>
	 *  <li>[2] -&gt;size (Integer)</li>
	 *  <li>[3] -&gt; sqlType (Integer)</li>
	 * </ul>
	 */
	public List<Object[]> getColumns(){
		List<Object[]> columns = new ArrayList<Object[]>();
		
		for(int i = 0; i < columnNames.size(); i++)
			columns.add(new Object[]{columnNames.get(i), columnTypes.get(i), displaySizes.get(i), sqlColumnTypes.get(i)});
		
		return columns;
	}
	
	private void buildColumnIndex() {
		this.columnIndex = new HashMap<String, Integer>();
		for(int i = 0; i < columnNames.size(); i++)
			columnIndex.put(columnNames.get(i), i);
	}

	public int size(){
		return columnNames.size();
	}

	public List<Integer> getDisplaySizes() {
		return displaySizes;
	}

	public void setDisplaySizes(List<Integer> displaySizes) {
		this.displaySizes = displaySizes;
	}

	public List<String> getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(List<String> columnNames) {
		this.columnNames = columnNames;
	}
	
	public List<String> getOriginalColumnNames() {
		return originalColumnNames;
	}
	
	public void setOriginalColumnNames(List<String> originalColumnNames) {
		this.originalColumnNames = originalColumnNames;
	}

	public List<Class<?>> getColumnTypes() {
		return columnTypes;
	}

	public void setColumnTypes(List<Class<?>> columnTypes) {
		this.columnTypes = columnTypes;
	}
	
	public List<Integer> getSqlColumnTypes() {
		return sqlColumnTypes;
	}

	public Integer getSqlColumnType(int colIndex) {
		return sqlColumnTypes.get(colIndex);
	}
	
	public void setSqlColumnTypes(List<Integer> sqlColumnTypes) {
		this.sqlColumnTypes = sqlColumnTypes;
	}

	public int getColumnIndexOf(String name) {
		return columnIndex.get(name);
	}

	public Class<?> getColumnType(int columnIndex) {
		return getColumnTypes().get(columnIndex);
	}

	public int getColumnCount() {
		return size();
	}

	@Override
	public String toString() {
		return "TableDefinition [columnNames=" + columnNames + ", columnTypes="
				+ columnTypes + "]";
	}
	
	
}
