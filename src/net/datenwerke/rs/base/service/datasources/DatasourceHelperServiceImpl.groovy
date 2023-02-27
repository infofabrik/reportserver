package net.datenwerke.rs.base.service.datasources

import static net.datenwerke.rs.base.client.datasources.DatasourceInfoType.DATABASE
import static net.datenwerke.rs.base.client.datasources.DatasourceInfoType.DATABASE_FUNCTIONS
import static net.datenwerke.rs.base.client.datasources.DatasourceInfoType.DATABASE_SUPPORTS
import static net.datenwerke.rs.base.client.datasources.DatasourceInfoType.JDBC_URL

import java.sql.DatabaseMetaData
import java.sql.SQLException
import java.time.Duration
import java.time.Instant

import javax.inject.Inject
import javax.inject.Provider

import groovy.sql.Sql
import net.datenwerke.dbpool.DbPoolService
import net.datenwerke.gf.service.history.HistoryService
import net.datenwerke.rs.base.client.datasources.DatasourceInfoType
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasourceConfig
import net.datenwerke.rs.base.service.datasources.locale.DatasourcesMessages
import net.datenwerke.rs.base.service.dbhelper.DBHelperService
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition
import net.datenwerke.rs.utils.reflection.MethodMetadata

class DatasourceHelperServiceImpl implements DatasourceHelperService {

   private final DbPoolService dbPoolService
   private final DBHelperService dbHelperService
   private final Provider<HistoryService> historyServiceProvider
   
   @Inject
   DatasourceHelperServiceImpl(
      DbPoolService dbPoolService,
      DBHelperService dbHelperService,
      Provider<HistoryService> historyServiceProvider
   ) {
      this.dbPoolService = dbPoolService
      this.dbHelperService = dbHelperService
      this.historyServiceProvider = historyServiceProvider
   }

   @Override
   Map<String, Object> copyTable(DatabaseDatasource sourceDatasource, String sourceTable,
         DatabaseDatasource destinationDatasource, String destinationTable, List<String> primaryKeys,
         boolean copyPrimaryKeys, int batchSize) {
      if (! tableExists(sourceDatasource, sourceTable))
         throw new IllegalArgumentException("'$sourceTable' does not exist")
      if (! tableExists(destinationDatasource, destinationTable))
         throw new IllegalArgumentException("'$destinationTable' does not exist")
         
      Instant start = Instant.now()
        
      def copyTableHelper = new CopyTableHelper(sourceDatasource: sourceDatasource, sourceTable: sourceTable, 
         destinationDatasource: destinationDatasource, destinationTable: destinationTable,
         primaryKeys: primaryKeys, copyPrimaryKeys: copyPrimaryKeys, batchSize: batchSize
         )

      dbPoolService.getConnection(sourceDatasource.connectionConfig).get().withCloseable { sourceConn ->
         dbPoolService.getConnection(destinationDatasource.connectionConfig).get().withCloseable { destinationConn ->
            assert sourceConn && destinationConn

            def sourceSql = new Sql(sourceConn)
            def destinationSql = new Sql(destinationConn)

            def metaResultSet = sourceConn.metaData.getColumns(sourceConn.catalog, null, sourceTable, null)
            copyTableHelper.readMetadata metaResultSet
            def insertStmt = copyTableHelper.prepareInsertStmt metaResultSet

            def selectStmt = copyTableHelper.prepareSelectStmt()

            destinationSql.withTransaction {
               destinationSql.withBatch(batchSize, insertStmt) { stmt ->
                  sourceSql.eachRow(selectStmt) { row -> copyTableHelper.insertRow(row, stmt) }
               }
            }
         }
      }
      
      def results = [:]
      Instant end = Instant.now()
      def duration = Duration.between(start, end)
      copyTableHelper.printDebugInfo results, duration
      results
   }
   

   @Override
   public boolean tableExists(DatabaseDatasource datasource, String table) {
      dbPoolService.getConnection(datasource.connectionConfig).get().withCloseable { conn ->
         assert conn

         def sql = new Sql(conn)

         def metaResultSet = conn.metaData.getColumns(conn.catalog, null, table, null)
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
         def metaResultSet = conn.metaData.getColumns(conn.catalog, null, table, null)
         while (metaResultSet.next())
            allColumns << metaResultSet.getString('COLUMN_NAME').toUpperCase(Locale.ROOT)
            
         def notContained = columns.inject([]) { result, col -> 
            ! allColumns.contains(col.toUpperCase(Locale.ROOT))? result << col: result }
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

         def metaResultSet = conn.metaData.getColumns(conn.catalog, null, table, null)
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
   
   private def collectMetadata(conn, Map<String, List<String>> methodDescriptions) {
      methodDescriptions
         .collectEntries { key, value ->
            [(key): new MethodMetadata(conn.metaData.class, key, methodDescriptions[key]).invokeMethodOn(conn.metaData)]
         }
   }
   
   @Override
   Map<String, Object> fetchDatasourceMetadata(DatabaseDatasource datasource,
         Map<String, List<String>> methodDescriptions) {
      dbPoolService.getConnection(datasource.connectionConfig).get().withCloseable { conn ->
         assert conn
         return collectMetadata(conn, methodDescriptions)
      }
   }
   
   @Override
   Map<String, Object> fetchDatasourceMetadata(String driverClass, String jdbcUrl, String jdbcUsername,
         String jdbcPassword, Map<String, List<String>> methodDescriptions) {
         
      def db = [url:jdbcUrl, user:jdbcUsername, password:jdbcPassword, driver:driverClass]
      Sql.newInstance(db.url, db.user, db.password, db.driver).withCloseable { sql ->
         assert sql
         assert sql.connection
         return collectMetadata(sql.connection, methodDescriptions)
      }
   }
   
   private def getMethods(boolean datasourceInfo, boolean jdbcUrlInfo, boolean databaseFunctionsInfo,
         boolean databaseSupportsInfo) {
      (
         (datasourceInfo? getDatabaseInfoDefinition().values() : [:]) +
         (jdbcUrlInfo? getJDBCUrlInfoDefinition().values() : [:]) +
         (databaseFunctionsInfo? getDatabaseFunctionsInfoDefinition().values() : [:]) +
         (databaseSupportsInfo? getDatabaseSupportsInfoDefinition().values() : [:])
         - [:]
      ).collectEntries { [(it): []] }
   }
   
   @Override
   Map<String, Object> fetchInfoDatasourceMetadata(DatasourceDefinition datasource, boolean datasourceInfo,
         boolean jdbcUrlInfo, boolean databaseFunctionsInfo, boolean databaseSupportsInfo) {
         assert datasource instanceof DatabaseDatasource
      def allMethods = getMethods(datasourceInfo, jdbcUrlInfo, databaseFunctionsInfo, databaseSupportsInfo)
      return fetchDatasourceMetadata((DatabaseDatasource)datasource, allMethods)
   }
   
   @Override
   public Map<String, Object> fetchInfoDatasourceMetadata(String driverClass, String jdbcUrl, String jdbcUsername,
         String jdbcPassword, boolean datasourceInfo, boolean jdbcUrlInfo, boolean databaseFunctionsInfo,
         boolean databaseSupportsInfo) throws SQLException {
      def allMethods = getMethods(datasourceInfo, jdbcUrlInfo, databaseFunctionsInfo, databaseSupportsInfo)
      return fetchDatasourceMetadata(driverClass, jdbcUrl, jdbcUsername, jdbcPassword, allMethods);
   }
   
   @Override
   public Map<DatasourceInfoType, Map<String, String>> getDatasourceInfoDefinition(){
      [
         (DATABASE)                 :   getDatabaseInfoDefinition(),
         (JDBC_URL)                 :   getJDBCUrlInfoDefinition(),
         (DATABASE_FUNCTIONS)       :   getDatabaseFunctionsInfoDefinition(),
         (DATABASE_SUPPORTS)        :   getDatabaseSupportsInfoDefinition()
      ]
   }
   
   @Override
   String getQuery(DatasourceContainer datasourceContainer) {
      if (!(datasourceContainer?.datasourceConfig instanceof DatabaseDatasourceConfig))
         throw new IllegalArgumentException('not a DatabaseDatasourceConfig')
         
      return datasourceContainer?.datasourceConfig?.query
   }
   
   public Map<String, String> getDatabaseInfoDefinition() {
      def map = new LinkedHashMap()
      map = [
         (DatasourcesMessages.INSTANCE.databaseName())           : 'getDatabaseProductName',
         (DatasourcesMessages.INSTANCE.databaseVersion())        : 'getDatabaseProductVersion',
         (DatasourcesMessages.INSTANCE.jdbcDriverName())         : 'getDriverName',
         (DatasourcesMessages.INSTANCE.jdbcDriverVersion())      : 'getDriverVersion',
         (DatasourcesMessages.INSTANCE.jdbcMajorVersion())       : 'getJDBCMajorVersion',
         (DatasourcesMessages.INSTANCE.jdbcMinorVersion())       : 'getJDBCMinorVersion'
      ]
      map
   }
   
   public Map<String, String> getJDBCUrlInfoDefinition() {
      def map = new LinkedHashMap()
      map = [
         (DatasourcesMessages.INSTANCE.jdbcUrl())           : 'getURL',
         (DatasourcesMessages.INSTANCE.jdbcUsername())      : 'getUserName'
      ]
      map
   }
   
   public Map<String, String> getDatabaseFunctionsInfoDefinition() {
      def map = new LinkedHashMap()
      map = [
         (DatasourcesMessages.INSTANCE.numericFunctions())          : 'getNumericFunctions',
         (DatasourcesMessages.INSTANCE.stringFunctions())           : 'getStringFunctions',
         (DatasourcesMessages.INSTANCE.timeAndDateFunctions())      : 'getTimeDateFunctions',
         (DatasourcesMessages.INSTANCE.systemFunctions())           : 'getSystemFunctions'
      ]
      map
   }
   
   public Map<String, String> getDatabaseSupportsInfoDefinition() {
      return DatabaseMetaData.methods
         .findAll{it.parameterCount == 0}
         .collect{it.name}
         .findAll{it.startsWith('supports')}
         .collectEntries { [(it): it] }
   }

   @Override
   public Map<String, Object> getGeneralInformation(DatasourceDefinition datasource) {
      def map = new LinkedHashMap()
      map = [
         (DatasourcesMessages.INSTANCE.name()):             datasource.name,
         (DatasourcesMessages.INSTANCE.description()):      datasource.description,
         (DatasourcesMessages.INSTANCE.id()):               datasource.id,
         (DatasourcesMessages.INSTANCE.createdOn()):        datasource.createdOn,
         (DatasourcesMessages.INSTANCE.changedOn()):        datasource.lastUpdated,
         (DatasourcesMessages.INSTANCE.path()):             historyServiceProvider.get().getFormattedObjectPaths(datasource)
      ]
      map
   }

}