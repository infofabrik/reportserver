package net.datenwerke.rs.core.service.datasourcemanager;

import java.util.Set;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.history.hooks.HistoryUrlBuilderHook;
import net.datenwerke.gf.service.lifecycle.hooks.ConfigDoneHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.datasourcemanager.annotations.ReportServerDatasourceDefinitions;
import net.datenwerke.rs.core.service.datasourcemanager.commands.ColumnsExistCommand;
import net.datenwerke.rs.core.service.datasourcemanager.commands.ColumnsMetadataCommand;
import net.datenwerke.rs.core.service.datasourcemanager.commands.CopyTableContentsCommand;
import net.datenwerke.rs.core.service.datasourcemanager.commands.DatasourceMetadataCommand;
import net.datenwerke.rs.core.service.datasourcemanager.commands.SqlTerminalCommand;
import net.datenwerke.rs.core.service.datasourcemanager.commands.TableExistsCommand;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceFolder;
import net.datenwerke.rs.core.service.datasourcemanager.eventhandlers.HandleDatasourceForceRemoveEventHandler;
import net.datenwerke.rs.core.service.datasourcemanager.history.DatasourceManagerHistoryUrlBuilderHooker;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.security.service.eventlogger.jpa.ForceRemoveEntityEvent;
import net.datenwerke.security.service.security.SecurityService;

public class DatasourceStartup {

   @Inject
   public DatasourceStartup(
         final HookHandlerService hookHandler, 
         final EventBus eventBus,
         final Provider<SecurityService> securityServiceProvider,
         final @ReportServerDatasourceDefinitions Provider<Set<Class<? extends DatasourceDefinition>>> installedDataSourceDefinitions,

         Provider<DatasourceManagerHistoryUrlBuilderHooker> datasourceManagerUrlBuilder,
         HandleDatasourceForceRemoveEventHandler handleDatasourceForceRemoveHandler,
         Provider<SqlTerminalCommand> sqlCommand,
         Provider<CopyTableContentsCommand> copyTableContentsCommandProvider,
         Provider<TableExistsCommand> tableExistsCommandProvider,
         Provider<ColumnsExistCommand> columnsExistCommandProvider,
         Provider<ColumnsMetadataCommand> columnsMetadataCommandProvider,
         Provider<DatasourceMetadataCommand> datasourceMetadataCommandProvider
         ) {

      eventBus.attachObjectEventHandler(ForceRemoveEntityEvent.class, DatasourceDefinition.class,
            handleDatasourceForceRemoveHandler);

      /* history */
      hookHandler.attachHooker(HistoryUrlBuilderHook.class, datasourceManagerUrlBuilder);
      
      /* commands */
      hookHandler.attachHooker(TerminalCommandHook.class, sqlCommand);
      hookHandler.attachHooker(TerminalCommandHook.class, copyTableContentsCommandProvider);
      hookHandler.attachHooker(TerminalCommandHook.class, tableExistsCommandProvider);
      hookHandler.attachHooker(TerminalCommandHook.class, columnsExistCommandProvider);
      hookHandler.attachHooker(TerminalCommandHook.class, columnsMetadataCommandProvider);
      hookHandler.attachHooker(TerminalCommandHook.class, datasourceMetadataCommandProvider);


      /* register security targets */
      hookHandler.attachHooker(ConfigDoneHook.class, () -> {
         /* secure folder */
         securityServiceProvider.get().registerSecurityTarget(DatasourceFolder.class);

         /* secure datasource definition entities */
         installedDataSourceDefinitions.get()
            .forEach(dClass -> securityServiceProvider.get().registerSecurityTarget(dClass));
      });
   }
}
