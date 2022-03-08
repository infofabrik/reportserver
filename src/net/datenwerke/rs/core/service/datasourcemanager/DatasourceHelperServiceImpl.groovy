package net.datenwerke.rs.core.service.datasourcemanager

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
      if (! tableExists(datasource, table))
         throw new IllegalArgumentException("Table '$table' not found")
         
      def metadata = []
      dbPoolService.getConnection(datasource.connectionConfig).get().withCloseable { conn ->
         assert conn

         def sql = new Sql(conn)

         def metaResultSet = conn.metaData.getColumns(null, null, table, null)
         while (metaResultSet.next()) {
            def colDefinition = [:]
            
            colDefinition.COLUMN_NAME =  metaResultSet.getString 'COLUMN_NAME'
            colDefinition.TYPE_NAME =  metaResultSet.getString 'TYPE_NAME'
            colDefinition.RS_TYPE = dbHelperService.getDatabaseHelper(conn).mapSQLTypeToJava(metaResultSet.getInt('DATA_TYPE')).simpleName
            colDefinition.COLUMN_SIZE =  metaResultSet.getInt 'COLUMN_SIZE'
            colDefinition.DECIMAL_DIGITS =  metaResultSet.getInt 'DECIMAL_DIGITS'
            colDefinition.ORDINAL_POSITION =  metaResultSet.getInt 'ORDINAL_POSITION'
            colDefinition.IS_NULLABLE =  metaResultSet.getString 'IS_NULLABLE'
            colDefinition.IS_AUTOINCREMENT =  metaResultSet.getString 'IS_AUTOINCREMENT'
            
            metadata << colDefinition  
         }
      }
      metadata
   }

}
