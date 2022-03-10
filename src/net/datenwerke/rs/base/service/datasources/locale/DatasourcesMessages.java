package net.datenwerke.rs.base.service.datasources.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface DatasourcesMessages extends Messages {

   public final static DatasourcesMessages INSTANCE = LocalizationServiceImpl.getMessages(DatasourcesMessages.class);

   String databaseDatasourceTypeName();

   String commandSql_description();

   String commandSql_datasource();

   String exceptionCouldNotExecuteStmt(String message);

   String exceptionCouldNotOpenDatasource(String message);

   String exceptionCouldNotPrepareStmt(String message);

   String commandConnPoolStats_description();
   
   String commandCopyTableContents_description();
   
   String commandCopyTableContents_sourceDatasource();
   
   String commandCopyTableContents_sourceTable();
   
   String commandCopyTableContents_destinationDatasource();
   
   String commandCopyTableContents_destinationTable();
   
   String commandCopyTableContents_primaryKeys();
   
   String commandCopyTableContents_copyPrimaryKeys();
   
   String commandCopyTableContents_batchSize();
   
   String commandTableExists_description();
   
   String commandTableExists_datasource();
   
   String commandTableExists_table();
   
   String commandColumnsExist_description();
   
   String commandColumnsExist_datasource();
   
   String commandColumnsExist_table();
   
   String commandColumnsExist_columns();
   
   String commandColumnMetadata_description();
   
   String commandColumnMetadata_datasource();
   
   String commandColumnMetadata_table();
   
   String commandColumnMetadata_columns();
   
   String commandDatasourceMetadata_description();
   
   String commandDatasourceMetadata_datasource();
   
   String commandDatasourceMetadata_methodName();
   
   String commandDatasourceMetadata_methodArg();
   
   
}
