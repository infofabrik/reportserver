package net.datenwerke.rs.base.service.reportengines.table.utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import com.google.inject.Provider;

import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.datatype.DataTypeFactory;
import liquibase.datatype.DatabaseDataType;
import liquibase.datatype.LiquibaseDataType;
import liquibase.exception.DatabaseException;
import net.datenwerke.dbpool.DbPoolService;
import net.datenwerke.dbpool.config.ConnectionPoolConfig;
import net.datenwerke.rs.base.service.reportengines.table.output.generator.RSTableOutputGenerator;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableRow;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;

public class TableModelDbHelper {

	private final Provider<RSTableOutputGenerator> outputGeneratorProvider;
	private final DbPoolService dbPoolService;
	
	public enum OverwriteMode {
		DROP_CREATE, TRUNCATE_INSERT, APPEND, SKIP, FAIL
	}

	@Inject
	public TableModelDbHelper(Provider<RSTableOutputGenerator> outputGeneratorProvider, DbPoolService dbPoolService) {
		this.outputGeneratorProvider = outputGeneratorProvider;
		this.dbPoolService = dbPoolService;
	}


	public RSTableModel selectAsRsTableModel(ConnectionPoolConfig cpc, String query) throws SQLException, IOException, InterruptedException, ExecutionException{
		Connection conn = null;
		try{
			conn = (Connection) dbPoolService.getConnection(cpc).get();
			return selectAsRsTableModel(conn, query);
		}finally{
			if(null != conn)
				conn.close();
		}
	}
	
	public RSTableModel selectAsRsTableModel(Connection connection, String query) throws SQLException, IOException{
		ResultSet resultSet = connection.prepareStatement(query).executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();
		TableDefinition  td = TableDefinition.fromResultSetMetaData(metaData);
		
		RSTableOutputGenerator outputGenerator = outputGeneratorProvider.get();
		outputGenerator.initialize(null, td, false, null, null, null, null, null);

		/* gather data */
		while(resultSet.next()){
			/* add Fields */
			for(int i = 0; i < td.size(); i++)
				outputGenerator.addField(resultSet.getObject(i+1), null);
			
			/* next row */
			outputGenerator.nextRow();
		}
		resultSet.close();
		
		return (RSTableModel) outputGenerator.getTableObject();
	}
	
	public int writeRsTableModel(RSTableModel rsTableModel, Connection connection, String tableName, OverwriteMode overwriteMode, String createTableStatement) throws DatabaseException, SQLException{
		Database db;
		synchronized (this) { // liquibase is not happy if two threads concurrently create a database factory
			db = DatabaseFactory.getInstance().findCorrectDatabaseImplementation( new JdbcConnection(connection));
		}
		
		StringBuilder createqry = new StringBuilder();
		StringBuilder insertstmt = new StringBuilder();
		StringBuilder values = new StringBuilder();

		
		createqry.append("CREATE TABLE ");
		createqry.append(tableName);
		createqry.append("(");
		
		insertstmt.append("INSERT INTO ");
		insertstmt.append(tableName);
		insertstmt.append("(");
		
		TableDefinition td = rsTableModel.getTableDefinition();
		for(int i = 0; i < td.getColumnCount(); i++){
			
			String columnName = td.getColumnNames().get(i);
			columnName = columnName.replaceAll("[^a-zA-Z0-9_]", "");
			
			createqry.append(columnName).append (" ");
			insertstmt.append(columnName);
			values.append("?");
			
			
			Class<?> coltype = td.getColumnType(i);
			String tname = coltype.getName();
			int precision = td.getDisplaySizes().get(i);
			int scale = 0;
			
			if(coltype.equals(String.class)){
				precision = Math.max(precision, 1);
				for (RSTableRow rsTableRow : rsTableModel.getData()) {
					if(null == rsTableRow.getAt(i))
						continue;
					precision = Math.max(precision, ((String)rsTableRow.getAt(i)).length());
				}
			}
			
			if(Short.class.equals(coltype)){
				coltype = Integer.class;
              	tname = coltype.getName();
            }
            if(Integer.class.equals(coltype)){
				precision = 0;
            }
            if(Date.class.equals(coltype)){
				precision = 0;
            } 
            if(BigDecimal.class.equals(coltype)){
                precision = Math.min(18, precision);
            }
			
			String s = tname;
			if(precision > 0 && scale > 0){
				s += "("+precision+","+scale+")";
			}else if(precision > 0){
				s += "("+precision+")";
			}else if(scale > 0){
				s += "("+scale+")";
			}

			// in contrast to above, datatype factory has its synchronized block...
			LiquibaseDataType lbdt = DataTypeFactory.getInstance().fromDescription(s, null);
			DatabaseDataType type = lbdt.toDatabaseDataType(db);
			
			createqry.append(type.toSql());
			
			if(i < td.getColumnCount() - 1){
				createqry.append(", ");
				insertstmt.append(", ");
				values.append(", ");
			}
		}
		
		createqry.append(")");
		insertstmt.append(") VALUES(").append(values).append(")");
		
		if(null != createTableStatement){
			createqry = new StringBuilder();
			createqry.append(createTableStatement);
		}
		
//		System.out.println(createqry);
		
		if(tableExists(connection, tableName)){
			switch(overwriteMode){
			case DROP_CREATE:
				connection.prepareStatement("DROP TABLE " + tableName).execute();
				connection.prepareStatement(createqry.toString()).execute();
				break;
			case SKIP:
				return -1;
			case TRUNCATE_INSERT:
				connection.prepareStatement("DELETE FROM " + tableName).execute();
				break;
			case FAIL:
				throw new RuntimeException("Table " + tableName + " already exists in target database");
			default:
				break;
			}
		}else{
			try{
				connection.prepareStatement(createqry.toString()).execute();
			}catch(SQLException e){
				throw new RuntimeException("Failed to create table (stmt: " + createqry.toString() + ")", e);
			}
		}
		
		PreparedStatement insert = connection.prepareStatement(insertstmt.toString());
		int updated = 0;
		for (RSTableRow rsTableRow : rsTableModel.getData()) {
			for(int i = 0; i < td.getColumnCount(); i++){
				insert.setObject(i+1, rsTableRow.getAt(i));
			}
			
			updated += insert.executeUpdate();
		}
		if(!connection.getAutoCommit())
			connection.commit();
		return updated;
	}
	
	private boolean tableExists(Connection connection, String tableName) throws SQLException{
		ResultSet tables = connection.getMetaData().getTables(null, null, null, Arrays.asList("TABLE").toArray(new String[0]));
		while(tables.next()){
			if(tables.getString("TABLE_NAME").toLowerCase().equals(tableName.toLowerCase()))
				return true;
		}
		tables.close();
		return false;
	}

	/**
	 * Note that you need to close the connection manually
	 * 
	 * @param rsTableModel
	 * @param connection
	 * @param tableName
	 * @throws DatabaseException
	 * @throws SQLException
	 */
	public int writeRsTableModel(RSTableModel rsTableModel, Connection connection, String tableName) throws DatabaseException, SQLException{
		return writeRsTableModel(rsTableModel, connection, tableName, OverwriteMode.FAIL, null);
	}
	
	public int writeRsTableModel(RSTableModel rsTableModel, ConnectionPoolConfig cpc, String tableName) throws DatabaseException, SQLException, InterruptedException, ExecutionException{
		Connection conn = null;
		try{
			conn = (Connection) dbPoolService.getConnection(cpc).get();
			return writeRsTableModel(rsTableModel, conn, tableName, OverwriteMode.FAIL, null);
		}finally{
			if(null != conn)
				conn.close();
		}
	}
	
	public int writeRsTableModel(RSTableModel rsTableModel, ConnectionPoolConfig cpc, String tableName, OverwriteMode overwriteMode, String createTableStatement) throws DatabaseException, SQLException, InterruptedException, ExecutionException{
		Connection conn = null;
		try{
			conn = (Connection) dbPoolService.getConnection(cpc).get();
			return writeRsTableModel(rsTableModel, conn, tableName, overwriteMode, createTableStatement);
		}finally{
			if(null != conn)
				conn.close();
		}
	}
}
