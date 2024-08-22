package net.datenwerke.rs.base.service.datasources

import groovy.sql.InParameter
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource

class CopyTableHelper {
   
   DatabaseDatasource sourceDatasource
   def sourceTable
   DatabaseDatasource destinationDatasource
   def destinationTable
   def primaryKeys = []
   def copyPrimaryKeys
   def batchSize
   
   def allColNames = []
   def allColDataTypes = []
   def primaryKeyIndexes = []
   def selectStmt
   def insertStmt
   
   
   def prepareInsertStmt(metaResultSet) {
      def insertingColNames = filterColumns allColNames
   
      insertStmt = """\
               INSERT INTO $destinationTable ( ${insertingColNames.join(',')} ) 
               values ( ${('?'*insertingColNames.size() as List).join(',')} )
         """.stripIndent() as String
         
      insertStmt
   }
   
   def filterColumns(columnList) {
      if (!copyPrimaryKeys)  {
         // filter out all primary keys from list
         return columnList.withIndex().findAll {
            element, index -> !primaryKeyIndexes.any{ index == it }
         }.collect{ element -> element[0] }
      } else return columnList
   }
   
   def insertRow(row, stmt) {
      def vals = row.toRowResult().values() as List
   
      def withTypes = vals.indexed().collect { idx, v ->
         [
            getType: { -> allColDataTypes[idx] as int },
            getValue: { -> v }
         ] as InParameter
      }
      
      withTypes = filterColumns withTypes
      
//      println "types: ${withTypes.collect{ it.getType().toString() } }"
//      println "Inserting values: $vals"
      
      stmt.addBatch withTypes
   }
   

   def readMetadata(metaResultSet) {
      def counter = 0
      while (metaResultSet.next()) {
         def columnName = metaResultSet.getString('COLUMN_NAME').toUpperCase(Locale.ROOT)
         def idx = primaryKeys.findIndexOf{ f -> f == columnName }
         if (-1 != idx) {
            //primary key found
            primaryKeyIndexes << counter
         }
         allColNames << columnName.toUpperCase(Locale.ROOT)
         
         //col type
         def dataType = metaResultSet.getString('DATA_TYPE')
         allColDataTypes << dataType
         
         counter++
      }
      assert primaryKeyIndexes.size() == primaryKeys.size()
      assert allColDataTypes.size() == allColNames.size()
      assert allColNames.containsAll(primaryKeys)
   }

   def printDebugInfo(results, duration) {
      results << ['All columns': allColNames]
      results << ['Datatypes': allColDataTypes]
      results << ['Indexes of primary keys': primaryKeyIndexes]
      results << ['SELECT statement': selectStmt]
      results << ['INSERT statement': insertStmt]
      results << ['Duration': duration as String]
   }
   
   def prepareSelectStmt() {
      selectStmt = "SELECT ${allColNames.join(',')} FROM $sourceTable" as String
      selectStmt
   }   
}