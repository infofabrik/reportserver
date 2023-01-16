package net.datenwerke.rs.base.service.datasources;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import net.datenwerke.rs.base.client.datasources.DatasourceInfoType;
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;

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
         Map<String, List<String>> methodDescriptions) throws SQLException;

   /**
    * Fetches general metadata information about a given datasource. Information
    * about which data is acquired getDatasourceInfoDefinition() provides a config
    * map
    * 
    * @param datasource            the datasource
    * @param datasourceInfo        if true, fetches general information
    * @param jdbcUrlInfo           if true, fetches URL information
    * @param databaseFunctionsInfo if true, fetches information on functions
    * @param databaseSupportsInfo  if true, fechtes information on supports
    * @return the map containing <methodname, result>
    */
   Map<String, Object> fetchInfoDatasourceMetadata(DatabaseDatasource datasource, boolean datasourceInfo,
         boolean jdbcUrlInfo, boolean databaseFunctionsInfo, boolean databaseSupportsInfo) throws SQLException;

   /**
    * Provides a map containing what information is fetched when using
    * fetchDatasourceMetadata(). This map has keys of type
    * {@link DatasourceInfoType}. Each key points to another Map of type
    * <String,String> with the structure 'method description' -> 'method name'
    * 
    * @return the config map
    */
   Map<DatasourceInfoType, Object> getDatasourceInfoDefinition();

   String getQuery(DatasourceContainer datasourceContainer);
   
   Map<String, Object> getGeneralInformation(DatasourceDefinition datasource);

}
