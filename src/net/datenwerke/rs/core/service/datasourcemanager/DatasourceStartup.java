package net.datenwerke.rs.core.service.datasourcemanager;

import java.util.Set;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.eximport.hooks.ExporterProviderHook;
import net.datenwerke.eximport.hooks.ImporterProviderHook;
import net.datenwerke.gf.service.history.hooks.HistoryUrlBuilderHook;
import net.datenwerke.gf.service.lifecycle.hooks.ConfigDoneHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.hooks.GeneralInfoCategoryProviderHook;
import net.datenwerke.rs.base.ext.service.datasourcemanager.eximport.DatasourceManagerExporter;
import net.datenwerke.rs.base.ext.service.datasourcemanager.eximport.DatasourceManagerImporter;
import net.datenwerke.rs.base.ext.service.datasourcemanager.eximport.HttpDatasourceManagerImportConfigurationHooker;
import net.datenwerke.rs.base.ext.service.datasourcemanager.eximport.hookers.DatasourceExportConfigHooker;
import net.datenwerke.rs.base.ext.service.datasourcemanager.eximport.hookers.ExportAllDatasourcesHooker;
import net.datenwerke.rs.base.ext.service.datasourcemanager.eximport.hookers.ImportAllDatasourcesHooker;
import net.datenwerke.rs.base.ext.service.datasourcemanager.eximport.hookers.RemoteDatasourceImporterHooker;
import net.datenwerke.rs.base.ext.service.hooks.ExportConfigHook;
import net.datenwerke.rs.base.ext.service.hooks.RemoteEntityImporterHook;
import net.datenwerke.rs.core.service.datasourcemanager.annotations.ReportServerDatasourceDefinitions;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceFolder;
import net.datenwerke.rs.core.service.datasourcemanager.eventhandlers.HandleDatasourceForceRemoveEventHandler;
import net.datenwerke.rs.core.service.datasourcemanager.genrights.DatasourceManagerAdminViewSecurityTarget;
import net.datenwerke.rs.core.service.datasourcemanager.history.DatasourceManagerHistoryUrlBuilderHooker;
import net.datenwerke.rs.core.service.datasourcemanager.hookers.DatasourceCategoryProviderHooker;
import net.datenwerke.rs.core.service.datasourcemanager.hookers.UsageStatisticsDatasourceFoldersProviderHooker;
import net.datenwerke.rs.core.service.datasourcemanager.hookers.UsageStatisticsTotalDatasourcesProviderHooker;
import net.datenwerke.rs.core.service.datasourcemanager.hooks.UsageStatisticsDatasourceEntryProviderHook;
import net.datenwerke.rs.eximport.service.eximport.hooks.ExportAllHook;
import net.datenwerke.rs.eximport.service.eximport.hooks.ImportAllHook;
import net.datenwerke.rs.eximport.service.eximport.im.http.hooks.HttpImportConfigurationProviderHook;
import net.datenwerke.rs.terminal.service.terminal.hookers.DatasorceOpenTerminalHooker;
import net.datenwerke.rs.terminal.service.terminal.hooks.OpenTerminalHandlerHook;
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

         final Provider<DatasourceManagerHistoryUrlBuilderHooker> datasourceManagerUrlBuilder,
         final HandleDatasourceForceRemoveEventHandler handleDatasourceForceRemoveHandler,
         
         final Provider<DatasourceCategoryProviderHooker> usageStatistics,
         final Provider<UsageStatisticsTotalDatasourcesProviderHooker> usageStatsTotalProvider,
         final Provider<UsageStatisticsDatasourceFoldersProviderHooker> usageStatsFolderProvider,
         
         final Provider<DatasourceManagerExporter> datasourceExporterProvider,
         final Provider<DatasourceManagerImporter> datasourceImporterProvider,
         final Provider<HttpDatasourceManagerImportConfigurationHooker> datasourceHttpImportConfigHookerProvider,
         
         final Provider<ExportAllDatasourcesHooker> exportAllDatasources,
         final Provider<ImportAllDatasourcesHooker> importAllDatasources,
         
         final Provider<DatasourceExportConfigHooker> datasourceExportConfigHooker,
         Provider<RemoteDatasourceImporterHooker> remoteDatasourceImporterHooker,
         
         final Provider<DatasorceOpenTerminalHooker> datasorceOpenTerminalHooker
         ) {

      eventBus.attachObjectEventHandler(ForceRemoveEntityEvent.class, DatasourceDefinition.class,
            handleDatasourceForceRemoveHandler);

      /* history */
      hookHandler.attachHooker(HistoryUrlBuilderHook.class, datasourceManagerUrlBuilder);
      
      hookHandler.attachHooker(ExporterProviderHook.class, new ExporterProviderHook(datasourceExporterProvider));
      hookHandler.attachHooker(ImporterProviderHook.class, new ImporterProviderHook(datasourceImporterProvider));
      hookHandler.attachHooker(HttpImportConfigurationProviderHook.class, datasourceHttpImportConfigHookerProvider);
      
      hookHandler.attachHooker(ExportAllHook.class, exportAllDatasources);
      hookHandler.attachHooker(ImportAllHook.class, importAllDatasources);
      
      hookHandler.attachHooker(ExportConfigHook.class, datasourceExportConfigHooker);
      
      hookHandler.attachHooker(RemoteEntityImporterHook.class, remoteDatasourceImporterHooker);
      
      /* register security targets */
      hookHandler.attachHooker(ConfigDoneHook.class, () -> {
         /* secure folder */
         securityServiceProvider.get().registerSecurityTarget(DatasourceFolder.class);

         /* secure datasource definition entities */
         installedDataSourceDefinitions.get()
            .forEach(securityServiceProvider.get()::registerSecurityTarget);
         
         securityServiceProvider.get().registerSecurityTarget(DatasourceManagerAdminViewSecurityTarget.class);
      });
      
      hookHandler.attachHooker(UsageStatisticsDatasourceEntryProviderHook.class, usageStatsTotalProvider,
            HookHandlerService.PRIORITY_LOW);
      hookHandler.attachHooker(UsageStatisticsDatasourceEntryProviderHook.class, usageStatsFolderProvider,
            HookHandlerService.PRIORITY_LOW + 5);
      hookHandler.attachHooker(GeneralInfoCategoryProviderHook.class, usageStatistics,
            HookHandlerService.PRIORITY_LOW + 56);
      
      hookHandler.attachHooker(OpenTerminalHandlerHook.class, datasorceOpenTerminalHooker);
   }
}
