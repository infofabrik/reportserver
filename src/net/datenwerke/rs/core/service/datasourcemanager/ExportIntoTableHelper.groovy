package net.datenwerke.rs.core.service.datasourcemanager

import groovy.sql.InParameter

class ExportIntoTableHelper {
   
   def insertRow(row, stmt, sourceTableDef, dstPrimaryKeys, copyPrimaryKeys) {
//      println "Adding $row"
      def colsToUse = getColsToUse(sourceTableDef, dstPrimaryKeys, copyPrimaryKeys)
      
      def primaryKeyIndizes = []
      if (!copyPrimaryKeys)
         primaryKeyIndizes = getPrimaryKeyIndizes sourceTableDef, dstPrimaryKeys
      
      def data = getValsToUse(row, primaryKeyIndizes, copyPrimaryKeys, sourceTableDef)
  
//      println 'Adding: '
//      println "types: ${data.collect{ it.type.toString() } }"
//      println "vals: ${data.collect{ it.value.toString() } }"
      
      stmt.addBatch data
   }
   
   def prepareInsertStmt(sourceTableDef, dstTableName, dstPrimaryKeys, copyPrimaryKeys) {
      
      def colsToUse = getColsToUse(sourceTableDef, dstPrimaryKeys, copyPrimaryKeys)
      
      def insertStmt = """\
         INSERT INTO $dstTableName ( ${colsToUse.join(',')} )
         VALUES
         ( ${('?'*colsToUse.size() as List).join(',')} )
         """.stripIndent()
      
      insertStmt as String
   }
   
   def getValsToUse(data, skipIndizes, copyPrimaryKeys, sourceTableDef) {
      def typed = getTypedData(data, sourceTableDef)
      if (copyPrimaryKeys)
         return typed
         
      def filtered = []
      typed.eachWithIndex{ it, index ->
         if (!skipIndizes.contains(index))
            filtered << it
      }
      
      filtered
   }
   
   def getTypedData(data, sourceTableDef) {
      def sqlTypes = sourceTableDef.sqlColumnTypes
      
      def typed = data.toList().indexed().collect { idx, v ->
         [
            getType: { -> sqlTypes[idx] as int },
            getValue: { -> v }
         ] as InParameter
      }
      
      typed as Object[]
   }
   
   def getColsToUse(sourceTableDef, dstPrimaryKeys, copyPrimaryKeys) {
      def colsToUse = sourceTableDef.columnNames
      if (!copyPrimaryKeys)
         colsToUse = colsToUse.findAll { col -> ! dstPrimaryKeys.any { it == col } }
         
      colsToUse
   }
   
   def getPrimaryKeyIndizes(sourceTableDef, dstPrimaryKeys) {
      def cols = sourceTableDef.columnNames
      
      def indizes = []
      cols.eachWithIndex{ it, index ->
          if (dstPrimaryKeys.contains(it))
            indizes << index
      }
      
      indizes
   }
}