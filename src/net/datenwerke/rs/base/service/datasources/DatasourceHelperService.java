package net.datenwerke.rs.base.service.datasources;

import java.util.List;
import java.util.Map;

import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource;

public interface DatasourceHelperService {

   Map<String, Object> copyTable(DatabaseDatasource sourceDatasource, String sourceTable,
         DatabaseDatasource destinationDatasource, String destinationTable, List<String> primaryKeys,
         boolean copyPrimaryKeys, int batchSize);

   boolean tableExists(DatabaseDatasource datasource, String table);

   /**
    * Returns the columns which do not exist in the given table. This is a subset
    * of the columns parameter. If the result is empty, all columns are found in
    * the given table.
    * 
    * @param datasource the datasource
    * @param table      the table to check
    * @param columns    the columns to check
    * @return the non-existing columns in the table
    */
   List<String> getNonExistingColumns(DatabaseDatasource datasource, String table, List<String> columns);

   List<Map<String, Object>> getColumnMetadata(DatabaseDatasource datasource, String table);

   List<Map<String, Object>> getColumnMetadata(DatabaseDatasource datasource, String table,
         List<String> additionalColumns);

   /**
    * Dynamically calls a methods from the class DatabaseMetaData. Any method is
    * call-able. The call fails if argument count and name does not match exactly
    * one method of said class. The call fails as well if the args cannot be
    * converted into the needed parameter types. If "null" is passed as a String in
    * args it will be evaluated to null as an Object. Boolean and Integer are
    * converted using String.toBoolean() and String.toInteger()
    * 
    * @param datasource         the datasource
    * @param methodDescriptions is a map with the structure keys:= method name as
    *                           String | values:= parameters as List of String
    * @return a map with the structure keys:= method name as String | values:=
    *         return-object of called method
    */
   Map<String, Object> fetchDatasourceMetadata(DatabaseDatasource datasource,
         Map<String, List<String>> methodDescriptions);
}
