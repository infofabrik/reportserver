package net.datenwerke.rs.core.service.datasourcemanager;

import java.util.List;
import java.util.Map;

import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.tabledatasink.service.tabledatasink.definitions.TableDatasink;
import net.datenwerke.security.service.usermanager.entities.User;

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

   void exportIntoTable(TableReport tableReport, User user, TableDatasink dstTableDatasink);
   
   List<Map<String,Object>> getColumnMetadata(DatabaseDatasource datasource, String table);
}
