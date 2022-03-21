package net.datenwerke.rs.base.service.datasources

import java.lang.reflect.Method
import java.sql.DatabaseMetaData

import javax.inject.Inject

import groovy.sql.Sql
import net.datenwerke.dbpool.DbPoolService
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource
import net.datenwerke.rs.base.service.dbhelper.DBHelperService
import net.datenwerke.rs.utils.reflection.MethodMetadata

class DatasourceHelperServiceImpl implements DatasourceHelperService {

   private final DbPoolService dbPoolService
   private final DBHelperService dbHelperService
   
   @Inject
   DatasourceHelperServiceImpl(
      DbPoolService dbPoolService,
      DBHelperService dbHelperService
   ) {
      this.dbPoolService = dbPoolService
      this.dbHelperService = dbHelperService
   }

   @Override
   Map<String, Object> copyTable(DatabaseDatasource sourceDatasource, String sourceTable,
         DatabaseDatasource destinationDatasource, String destinationTable, List<String> primaryKeys,
         boolean copyPrimaryKeys, int batchSize) {
      if (! tableExists(sourceDatasource, sourceTable))
         throw new IllegalArgumentException("'$sourceTable' does not exist")
      if (! tableExists(destinationDatasource, destinationTable))
         throw new IllegalArgumentException("'$destinationTable' does not exist")
        
      def copyTableHelper = new CopyTableHelper(sourceDatasource: sourceDatasource, sourceTable: sourceTable, 
         destinationDatasource: destinationDatasource, destinationTable: destinationTable,
         primaryKeys: primaryKeys, copyPrimaryKeys: copyPrimaryKeys, batchSize: batchSize
         )

      Date start = new Date()
      
      def results = [:]

      dbPoolService.getConnection(sourceDatasource.connectionConfig).get().withCloseable { sourceConn ->
         dbPoolService.getConnection(destinationDatasource.connectionConfig).get().withCloseable { destinationConn ->
            assert sourceConn && destinationConn

            def sourceSql = new Sql(sourceConn)
            def destinationSql = new Sql(destinationConn)

            def metaResultSet = sourceConn.metaData.getColumns(null, null, sourceTable, null)
            copyTableHelper.readMetadata metaResultSet
            def insertStmt = copyTableHelper.prepareInsertStmt metaResultSet

            def selectStmt = copyTableHelper.prepareSelectStmt()

            copyTableHelper.printDebugInfo results

            destinationSql.withTransaction {
               destinationSql.withBatch(batchSize, insertStmt) { stmt ->
                  sourceSql.eachRow(selectStmt) { row -> copyTableHelper.insertRow(row, stmt) }
               }
            }
         }
      }
      results
   }
   

   @Override
   public boolean tableExists(DatabaseDatasource datasource, String table) {
      dbPoolService.getConnection(datasource.connectionConfig).get().withCloseable { conn ->
         assert conn

         def sql = new Sql(conn)

         def metaResultSet = conn.metaData.getColumns(null, null, table, null)
         metaResultSet.next()
      }
   }
   
   @Override
   public List<String> getNonExistingColumns(DatabaseDatasource datasource, String table, List<String> columns) {
      if (! tableExists(datasource, table))
         throw new IllegalArgumentException("Table '$table' not found")
      
      dbPoolService.getConnection(datasource.connectionConfig).get().withCloseable { conn ->
         assert conn

         def sql = new Sql(conn)

         def allColumns = []
         def metaResultSet = conn.metaData.getColumns(null, null, table, null)
         while (metaResultSet.next())
            allColumns << metaResultSet.getString('COLUMN_NAME').toUpperCase()
            
         def notContained = columns.inject([]) { result, col -> ! allColumns.contains(col.toUpperCase())? result << col: result }
         notContained
      }
   }

   @Override
   public List<Map<String, Object>> getColumnMetadata(DatabaseDatasource datasource, String table) {
      return getColumnMetadata(datasource, table, new ArrayList<String>())
   }
   
   @Override
   public List<Map<String, Object>> getColumnMetadata(DatabaseDatasource datasource, String table, 
      List<String> additionalColumns) {
      if (! tableExists(datasource, table))
         throw new IllegalArgumentException("Table '$table' not found")
         
      def metadata = []
      dbPoolService.getConnection(datasource.connectionConfig).get().withCloseable { conn ->
         assert conn

         def sql = new Sql(conn)

         def metaResultSet = conn.metaData.getColumns(null, null, table, null)
         def returnTypes = databaseMetaDataGetColumnsReturnTypes()
         Closure metaFetcher = { colDefinition, col -> 
            switch(returnTypes[col]) {
               case String:
                  colDefinition[col] = metaResultSet.getString(col)
                  break
               case Integer:
                  colDefinition[col] = metaResultSet.getInt(col)
                  break
               case Short:
                  colDefinition[col] = metaResultSet.getShort(col)
                  break
               default:
                  colDefinition[col] = "$col is not a column meta data"
            }
         }
         while (metaResultSet.next()) {
            def colDefinition = [:]
            metaFetcher colDefinition, 'COLUMN_NAME'
            metaFetcher colDefinition, 'TYPE_NAME'
            colDefinition.RS_TYPE = dbHelperService.getDatabaseHelper(conn)
                  .mapSQLTypeToJava(metaResultSet.getInt('DATA_TYPE')).simpleName
            metaFetcher colDefinition, 'COLUMN_SIZE'
            metaFetcher colDefinition, 'DECIMAL_DIGITS'
            metaFetcher colDefinition, 'ORDINAL_POSITION'
            metaFetcher colDefinition, 'IS_NULLABLE'
            metaFetcher colDefinition, 'IS_AUTOINCREMENT'
            additionalColumns.each { metaFetcher colDefinition, it }
            
            metadata << colDefinition
         }
      }
      metadata
   }
  
   
   private def databaseMetaDataGetColumnsReturnTypes() {
      [
         TABLE_CAT          :   String,
         TABLE_SCHEM        :   String,
         TABLE_NAME         :   String,
         COLUMN_NAME        :   String,
         DATA_TYPE          :   Integer,
         TYPE_NAME          :   String,
         COLUMN_SIZE        :   Integer,
         BUFFER_LENGTH      :   String,
         DECIMAL_DIGITS     :   Integer,
         NUM_PREC_RADIX     :   Integer,
         NULLABLE           :   Integer,
         REMARKS            :   String,
         COLUMN_DEF         :   String,
         SQL_DATA_TYPE      :   Integer,
         SQL_DATETIME_SUB   :   Integer,
         CHAR_OCTET_LENGTH  :   Integer,
         ORDINAL_POSITION   :   Integer,
         IS_NULLABLE        :   String,
         SCOPE_CATALOG      :   String,
         SCOPE_SCHEMA       :   String,
         SCOPE_TABLE        :   String,
         SOURCE_DATA_TYPE   :   Short,
         IS_AUTOINCREMENT   :   String,
         IS_GENERATEDCOLUMN :   String
      ]
   }
   
   @Override
   Map<String, Object> fetchDatasourceMetadata(DatabaseDatasource datasource, Map<String, List<String>> methodDescriptions) {
      dbPoolService.getConnection(datasource.connectionConfig).get().withCloseable { conn ->
         assert conn

         Map<String, MethodMetadata<DatabaseMetaData>> methodsMap = [:]
         Map<String, Object> results = [:]

         methodDescriptions.keySet().forEach{ key ->
            methodsMap.put(key, new MethodMetadata(conn.metaData.class, key, methodDescriptions.get(key)))
         }

         methodsMap.keySet().forEach{ key ->
            results.put(key, methodsMap.get(key).invokeMethodOn(conn.metaData))
         }
         return results
      }
   }
   
   @Override
   Map<String, Object> fetchInfoDatasourceMetadata(DatabaseDatasource datasource) {
      def allMethods = [:]
      getDatasourceGeneralInfoDefinition().values().each{v -> allMethods.put(v, new ArrayList<String>())}
      getDatasourceUrlInfoDefinition().values().each{v -> allMethods.put(v, new ArrayList<String>())}
      getDatasourceFunctionsInfoDefinition().values().each{v -> allMethods.put(v, new ArrayList<String>())}
      getDatasourceSupportsInfoDefinition().values().each{v -> allMethods.put(v, new ArrayList<String>())}
      return fetchDatasourceMetadata(datasource, allMethods)
   }
   
   @Override
   public Map<String, Object> getDatasourceInfoDefinition(){
      def datasourceInfoDefintion = [:]
      datasourceInfoDefintion.put("generalInfo", getDatasourceGeneralInfoDefinition())
      datasourceInfoDefintion.put("urlInfo", getDatasourceUrlInfoDefinition())
      datasourceInfoDefintion.put("functionsSection", getDatasourceFunctionsInfoDefinition())
      datasourceInfoDefintion.put("supportsSection", getDatasourceSupportsInfoDefinition())
      return datasourceInfoDefintion
   }
   
   
   public Map<String, String> getDatasourceGeneralInfoDefinition() {
      [
         "Database name"        : "getDatabaseProductName",
         "Database version"     : "getDatabaseProductVersion",
         "Driver name"          : "getDriverName",
         "Driver version"       : "getDriverVersion",
         "JDBC major version"   : "getJDBCMajorVersion",
         "JDBC minor version"   : "getJDBCMinorVersion"
      ]
   }
   
   public Map<String, String> getDatasourceUrlInfoDefinition() {
      [
      "JDBC URL"    : "getURL",
      "User name"   : "getUserName"
      ]
   }
   
   public Map<String, String> getDatasourceFunctionsInfoDefinition() {
      [
      "Numeric functions"       : "getNumericFunctions",
      "String functions"        : "getStringFunctions",
      "Time and date functions" : "getTimeDateFunctions",
      "System functions"        : "getSystemFunctions"
      ]
   }
   
   public Map<String, String> getDatasourceSupportsInfoDefinition() {
      def supportsSection = [:]
      Method[] methods = DatabaseMetaData.class.getMethods();
      Arrays.asList(methods).stream()
            .filter{method -> method.getParameterCount() == 0}
            .map{method -> method.getName()}
            .filter{name -> name.startsWith("supports")}
            .each{name -> supportsSection.put(name, name)}
      supportsSection      
   }
   
}
