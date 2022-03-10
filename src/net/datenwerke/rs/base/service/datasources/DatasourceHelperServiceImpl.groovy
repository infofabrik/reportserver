package net.datenwerke.rs.base.service.datasources

import java.lang.reflect.Method
import java.sql.DatabaseMetaData
import java.util.stream.IntStream

import javax.inject.Inject

import groovy.sql.Sql
import net.datenwerke.dbpool.DbPoolService
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource
import net.datenwerke.rs.base.service.dbhelper.DBHelperService
import net.datenwerke.rs.base.service.reportengines.table.SimpleDataSupplier
import net.datenwerke.rs.base.service.reportengines.table.SimpleDataSupplier.DataConsumer
import net.datenwerke.rs.base.service.reportengines.table.entities.Column
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition
import net.datenwerke.rs.core.service.reportmanager.interfaces.ReportVariant
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSetFactory
import net.datenwerke.rs.dsbundle.service.dsbundle.DatasourceBundleService
import net.datenwerke.rs.dsbundle.service.dsbundle.entities.DatabaseBundle
import net.datenwerke.rs.tabledatasink.service.tabledatasink.definitions.TableDatasink
import net.datenwerke.rs.terminal.service.terminal.TerminalSession
import net.datenwerke.security.service.usermanager.entities.User

class DatasourceHelperServiceImpl implements DatasourceHelperService {

   final TerminalSession terminalSession
   final DbPoolService dbPoolService
   final SimpleDataSupplier simpleDataSupplier
   final ParameterSetFactory parameterSetFactory
   final DatasourceBundleService datasourceBundleService
   final DBHelperService dbHelperService
   
   @Inject
   DatasourceHelperServiceImpl(
      TerminalSession terminalSession,
      DbPoolService dbPoolService,
      SimpleDataSupplier simpleDataSupplier,
      ParameterSetFactory parameterSetFactory,
      DatasourceBundleService datasourceBundleService,
      DBHelperService dbHelperService
   ) {
      this.terminalSession = terminalSession
      this.dbPoolService = dbPoolService
      this.simpleDataSupplier = simpleDataSupplier
      this.parameterSetFactory = parameterSetFactory
      this.datasourceBundleService = datasourceBundleService
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
   public void exportIntoTable(TableReport tableReport, User user, TableDatasink dstTableDatasink) {
      def srcDatasource = tableReport.datasourceContainer.datasource
      def dstDatasource = dstTableDatasink.datasourceContainer.datasource
      
      if (dstDatasource instanceof DatabaseBundle) {
         /* init parameter set */
         def parameterSet = parameterSetFactory.create user, tableReport
         parameterSet.addAll tableReport.parameterInstances
         dstDatasource = datasourceBundleService.getEffectiveDatasource dstTableDatasink, parameterSet 
      }
      def dstTable = dstTableDatasink.tableName
      def primaryKeys = dstTableDatasink.primaryKeys.split(';') as List
      
      if (! tableExists(dstDatasource, dstTable))
         throw new IllegalArgumentException("Table '$dstTable' does not exist")
         
      def nonExistingColumns = getNonExistingColumns dstDatasource, dstTable, primaryKeys
      if (! nonExistingColumns.isEmpty() )
         throw new IllegalArgumentException("The following columns were not found in table '$dstTable': $nonExistingColumns")
         
//      println "Exporting ds '$srcDatasource' into '$dstTable'"
         
      def exportIntoTableHelper = new ExportIntoTableHelper()
      
      if (!(tableReport instanceof ReportVariant))
         tableReport.selectAllColumns = true
         
      TableDefinition tableDef = simpleDataSupplier.getInfo(tableReport, user, tableReport.columns as Column[])
      
//      println "Columns: $tableDef.columnNames"
      
      def copyPrimaryKeys = dstTableDatasink.copyPrimaryKeys
      def batchSize = dstTableDatasink.batchSize
      
      def insertStmt = exportIntoTableHelper.prepareInsertStmt(tableDef, dstTable, primaryKeys, copyPrimaryKeys)
//      println insertStmt
      
      dbPoolService
         .getConnection(dstDatasource.connectionConfig).get().withCloseable { dstConn ->
            Sql dstSql = new Sql(dstConn)

            assert dstSql

            dstSql.withTransaction {
               dstSql.withBatch(batchSize, insertStmt) { stmt ->
                  def dataConsumer = [
                     consumeRow: {  exportIntoTableHelper.insertRow(it, stmt, tableDef, primaryKeys, copyPrimaryKeys) },
                     allConsumed: {  }
                  ] as DataConsumer
                  simpleDataSupplier.getData(tableReport, user, null, null, null, dataConsumer, 
                     tableReport.columns as Column[])
               }
            }
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
   Object fetchDatasourceMetadata(DatabaseDatasource datasource, String methodName, List<String> args) {
      dbPoolService.getConnection(datasource.connectionConfig).get().withCloseable { conn ->
         assert conn

         Class<DatabaseMetaData> connMetaData = conn.metaData.class
         Method[] connMetaDataGetMethods = connMetaData.getMethods()
         List<Method> nameMatchingMethods = getMethodByName(connMetaDataGetMethods, methodName)
         List<Method> nameAndArgsMatchingMethods = nameMatchingMethods.stream()
            .filter{method -> method.getParameterCount() == args.size()}
            .toList()
         if(nameAndArgsMatchingMethods.size() == 0) {
            throw new Exception("No method matching arg size and name")
         } else if (nameAndArgsMatchingMethods.size() > 1) {
            throw new Exception("Too many methods matching arg size and name")
         }
         Method method = nameAndArgsMatchingMethods[0]
         List<Object> convertedArgs = convertArgsToParamTypes(method, args)
         return method.invoke(conn.metaData, *convertedArgs)        
      }
   }
   
   private List<Method> getMethodByName(Method[] methods, String methodName) {
      def matchingMethods = methods.stream()
         .filter{ method -> method.name.equals(methodName)}
         .toList()
      return matchingMethods
   }
   
   private List<Object>convertArgsToParamTypes(Method method, List<String> args) {
      Class<?>[] types = method.getParameterTypes()
      List<Object> convertedArgs= IntStream.range(0,types.length)
            .mapToObj{i -> mapArgToClass(types[i], args[i])}
            .toList()    
      return convertedArgs
   }
   
   private Object mapArgToClass(Class<?> classObj, String arg){
      if(arg.equals("null"))
         return null
      switch(classObj.simpleName) {
         case("boolean"):
            return arg.toBoolean()
         case("String"):
            return arg
         case("int"):
            return arg.toInteger()
         default:
            throw new Exception("unknown parameter type detected")
      }
   }
   
}
