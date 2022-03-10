package net.datenwerke.rs.base.service.datasources;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.dbpool.hooks.DbPoolConnectionHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.service.datasources.definitions.CsvDatasource;
import net.datenwerke.rs.base.service.datasources.eventhandler.HandleCsvDatasourceMergeEvents;
import net.datenwerke.rs.base.service.datasources.hooker.BaseDatasourceProviderHooker;
import net.datenwerke.rs.base.service.datasources.hooker.StandardConnectionHook;
import net.datenwerke.rs.base.service.datasources.statementmanager.db.MonetDbStatementCancler;
import net.datenwerke.rs.base.service.datasources.statementmanager.hooks.StatementCancellationHook;
import net.datenwerke.rs.base.service.datasources.table.hookers.QueryCommentAdderHooker;
import net.datenwerke.rs.base.service.datasources.table.impl.hooks.TableDbDatasourceOpenedHook;
import net.datenwerke.rs.base.service.datasources.terminal.commands.ColumnsExistCommand;
import net.datenwerke.rs.base.service.datasources.terminal.commands.ColumnsMetadataCommand;
import net.datenwerke.rs.base.service.datasources.terminal.commands.CopyTableContentsCommand;
import net.datenwerke.rs.base.service.datasources.terminal.commands.DatasourceMetadataCommand;
import net.datenwerke.rs.base.service.datasources.terminal.commands.SqlTerminalCommand;
import net.datenwerke.rs.base.service.datasources.terminal.commands.TableExistsCommand;
import net.datenwerke.rs.base.service.datasources.transformers.DataSourceDefinitionTransformer;
import net.datenwerke.rs.base.service.datasources.transformers.birtreport.Birt2ConnectionTransformer;
import net.datenwerke.rs.base.service.datasources.transformers.birtreport.Birt2JdbcDatasourceTransformer;
import net.datenwerke.rs.base.service.datasources.transformers.birtreport.Birt2TableTransformer;
import net.datenwerke.rs.base.service.datasources.transformers.csv.Csv2ConnectionTransformer;
import net.datenwerke.rs.base.service.datasources.transformers.csv.Csv2InputStreamTransformer;
import net.datenwerke.rs.base.service.datasources.transformers.csv.Csv2JasperTransformer;
import net.datenwerke.rs.base.service.datasources.transformers.csv.Csv2JdcbDatasourceTransformer;
import net.datenwerke.rs.base.service.datasources.transformers.csv.Csv2QueryTransformer;
import net.datenwerke.rs.base.service.datasources.transformers.csv.Csv2TableTransformer;
import net.datenwerke.rs.base.service.datasources.transformers.csv.Csv2TempTableResultTransformer;
import net.datenwerke.rs.base.service.datasources.transformers.database.Database2JasperTransformer;
import net.datenwerke.rs.base.service.datasources.transformers.database.Database2JdbcConnectionTransformer;
import net.datenwerke.rs.base.service.datasources.transformers.database.Database2JdbcDatasourceTransformer;
import net.datenwerke.rs.base.service.datasources.transformers.database.Database2TableTransformer;
import net.datenwerke.rs.core.service.datasourcemanager.hooks.DatasourceProviderHook;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.security.service.eventlogger.jpa.MergeEntityEvent;

public class DatasourceExtensionStartup {

   @Inject
   public DatasourceExtensionStartup(HookHandlerService hookHandler, 
         EventBus eventBus,
         Provider<BaseDatasourceProviderHooker> databaseProvider, 
         StandardConnectionHook connectionHooker,
         QueryCommentAdderHooker commentAdder, 
         HandleCsvDatasourceMergeEvents csvDatasourceMergeHandler,

         Birt2TableTransformer birt2TableTransformer, 
         Birt2ConnectionTransformer birt2ConnectionTransformer,
         Birt2JdbcDatasourceTransformer birt2JdbcDatasourceTransformer,

         Csv2InputStreamTransformer csv2InputStreamTransformer, 
         Csv2JasperTransformer csv2JasperTransformer,
         Csv2TableTransformer csv2TableTransformer, 
         Csv2ConnectionTransformer csv2ConnectionTransformer,
         Csv2JdcbDatasourceTransformer csv2JdbcDatasourceTransformer, 
         Csv2QueryTransformer csv2QueryTransformer,
         Csv2TempTableResultTransformer csv2TempTableResultTransformer,

         Database2JasperTransformer database2JasperTransformer,
         Database2JdbcConnectionTransformer database2JdbcConnectionTransformer,
         Database2JdbcDatasourceTransformer database2JdbcDatasourceTransformer,
         Database2TableTransformer database2TableTransformer,

         Provider<MonetDbStatementCancler> monetDbCancler,
         
         Provider<SqlTerminalCommand> sqlCommand,
         Provider<CopyTableContentsCommand> copyTableContentsCommandProvider,
         Provider<TableExistsCommand> tableExistsCommandProvider,
         Provider<ColumnsExistCommand> columnsExistCommandProvider,
         Provider<ColumnsMetadataCommand> columnsMetadataCommandProvider,
         Provider<DatasourceMetadataCommand> datasourceMetadataCommandProvider
         
         ) {

      hookHandler.attachHooker(DatasourceProviderHook.class, databaseProvider);
      hookHandler.attachHooker(TableDbDatasourceOpenedHook.class, commentAdder);
      hookHandler.attachHooker(DbPoolConnectionHook.class, connectionHooker);

      hookHandler.attachHooker(DataSourceDefinitionTransformer.class, birt2TableTransformer);
      hookHandler.attachHooker(DataSourceDefinitionTransformer.class, birt2ConnectionTransformer);
      hookHandler.attachHooker(DataSourceDefinitionTransformer.class, birt2JdbcDatasourceTransformer);

      hookHandler.attachHooker(DataSourceDefinitionTransformer.class, csv2InputStreamTransformer);
      hookHandler.attachHooker(DataSourceDefinitionTransformer.class, csv2JasperTransformer);
      hookHandler.attachHooker(DataSourceDefinitionTransformer.class, csv2TableTransformer);
      hookHandler.attachHooker(DataSourceDefinitionTransformer.class, csv2ConnectionTransformer);
      hookHandler.attachHooker(DataSourceDefinitionTransformer.class, csv2JdbcDatasourceTransformer);
      hookHandler.attachHooker(DataSourceDefinitionTransformer.class, csv2QueryTransformer);
      hookHandler.attachHooker(DataSourceDefinitionTransformer.class, csv2TempTableResultTransformer);

      hookHandler.attachHooker(DataSourceDefinitionTransformer.class, database2JasperTransformer);
      hookHandler.attachHooker(DataSourceDefinitionTransformer.class, database2JdbcConnectionTransformer);
      hookHandler.attachHooker(DataSourceDefinitionTransformer.class, database2JdbcDatasourceTransformer);
      hookHandler.attachHooker(DataSourceDefinitionTransformer.class, database2TableTransformer);

      eventBus.attachObjectEventHandler(MergeEntityEvent.class, CsvDatasource.class, csvDatasourceMergeHandler);
      
      /* commands */
      hookHandler.attachHooker(TerminalCommandHook.class, sqlCommand);
      hookHandler.attachHooker(TerminalCommandHook.class, copyTableContentsCommandProvider);
      hookHandler.attachHooker(TerminalCommandHook.class, tableExistsCommandProvider);
      hookHandler.attachHooker(TerminalCommandHook.class, columnsExistCommandProvider);
      hookHandler.attachHooker(TerminalCommandHook.class, columnsMetadataCommandProvider);
      hookHandler.attachHooker(TerminalCommandHook.class, datasourceMetadataCommandProvider);

      hookHandler.attachHooker(StatementCancellationHook.class, monetDbCancler);
   }
}
