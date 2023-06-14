package net.datenwerke.rs.core.service.datasinkmanager;

import java.util.Set;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.eximport.hooks.ExporterProviderHook;
import net.datenwerke.eximport.hooks.ImporterProviderHook;
import net.datenwerke.gf.service.history.hooks.HistoryUrlBuilderHook;
import net.datenwerke.gf.service.lifecycle.hooks.ConfigDoneHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.hooks.GeneralInfoCategoryProviderHook;
import net.datenwerke.rs.base.ext.service.datasinkmanager.eximport.DatasinkManagerExporter;
import net.datenwerke.rs.base.ext.service.datasinkmanager.eximport.DatasinkManagerImporter;
import net.datenwerke.rs.base.ext.service.datasinkmanager.eximport.HttpDatasinkManagerImportConfigurationHooker;
import net.datenwerke.rs.base.ext.service.datasinkmanager.eximport.hookers.DatasinkExportConfigHooker;
import net.datenwerke.rs.base.ext.service.datasinkmanager.eximport.hookers.ExportAllDatasinksHooker;
import net.datenwerke.rs.base.ext.service.datasinkmanager.eximport.hookers.RemoteDatasinkImporterHooker;
import net.datenwerke.rs.base.ext.service.hooks.ExportConfigHook;
import net.datenwerke.rs.base.ext.service.hooks.RemoteEntityImporterHook;
import net.datenwerke.rs.core.service.datasinkmanager.annotations.ReportServerDatasinkDefinitions;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkFolder;
import net.datenwerke.rs.core.service.datasinkmanager.eventhandlers.HandleDatasinkForceRemoveEventHandler;
import net.datenwerke.rs.core.service.datasinkmanager.history.DatasinkManagerHistoryUrlBuilderHooker;
import net.datenwerke.rs.core.service.datasinkmanager.hookers.DatasinkCategoryProviderHooker;
import net.datenwerke.rs.core.service.datasinkmanager.hookers.UsageStatisticsDatasinkFoldersProviderHooker;
import net.datenwerke.rs.core.service.datasinkmanager.hookers.UsageStatisticsTotalDatasinksProviderHooker;
import net.datenwerke.rs.core.service.datasinkmanager.hooks.UsageStatisticsDatasinkEntryProviderHook;
import net.datenwerke.rs.core.service.datasinkmanager.terminal.operators.WriteIntoDatasinkOperator;
import net.datenwerke.rs.eximport.service.eximport.hooks.ExportAllHook;
import net.datenwerke.rs.eximport.service.eximport.im.http.hooks.HttpImportConfigurationProviderHook;
import net.datenwerke.rs.terminal.service.terminal.operator.TerminalCommandOperator;
import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.security.service.eventlogger.jpa.ForceRemoveEntityEvent;
import net.datenwerke.security.service.security.SecurityService;

public class DatasinkStartup {

   @Inject
   public DatasinkStartup(
         final HookHandlerService hookHandler, 
         final EventBus eventBus,
         final Provider<SecurityService> securityServiceProvider,
         final @ReportServerDatasinkDefinitions Provider<Set<Class<? extends DatasinkDefinition>>> installedDatasinkDefinitions,

         final Provider<DatasinkManagerHistoryUrlBuilderHooker> datasinkManagerUrlBuilder,
         final HandleDatasinkForceRemoveEventHandler handleDatasinkForceRemoveHandler,
         final Provider<WriteIntoDatasinkOperator> writeIntoDatasinkOperator,
         
         final Provider<DatasinkCategoryProviderHooker> usageStatistics,
         final Provider<UsageStatisticsTotalDatasinksProviderHooker> usageStatsTotalDatasinksProvider,
         final Provider<UsageStatisticsDatasinkFoldersProviderHooker> usageStatsFolderProvider,
         
         final Provider<DatasinkManagerExporter> datasinkManagerExporter,
         final Provider<DatasinkManagerImporter> datasinkImporterProvider,
         final Provider<HttpDatasinkManagerImportConfigurationHooker> datasinkHttpImportConfigHookerProvider,
         
         final Provider<ExportAllDatasinksHooker> exportAllDatasinks,
         final Provider<DatasinkExportConfigHooker> datasinkExportConfigHooker,
         final Provider<RemoteDatasinkImporterHooker> remoteDatasinkImporterHooker
         ) {

      eventBus.attachObjectEventHandler(ForceRemoveEntityEvent.class, DatasinkDefinition.class,
            handleDatasinkForceRemoveHandler);
      
      hookHandler.attachHooker(TerminalCommandOperator.class, writeIntoDatasinkOperator);
      
      hookHandler.attachHooker(UsageStatisticsDatasinkEntryProviderHook.class, usageStatsTotalDatasinksProvider,
            HookHandlerService.PRIORITY_MEDIUM);
      hookHandler.attachHooker(UsageStatisticsDatasinkEntryProviderHook.class, usageStatsFolderProvider,
            HookHandlerService.PRIORITY_MEDIUM + 3);

      /* history */
      hookHandler.attachHooker(HistoryUrlBuilderHook.class, datasinkManagerUrlBuilder);
      
      hookHandler.attachHooker(GeneralInfoCategoryProviderHook.class, usageStatistics,
            HookHandlerService.PRIORITY_LOW + 60);
      
      hookHandler.attachHooker(ExporterProviderHook.class, new ExporterProviderHook(datasinkManagerExporter));
      hookHandler.attachHooker(ImporterProviderHook.class, new ImporterProviderHook(datasinkImporterProvider));
      hookHandler.attachHooker(HttpImportConfigurationProviderHook.class, datasinkHttpImportConfigHookerProvider);
      
      hookHandler.attachHooker(ExportAllHook.class, exportAllDatasinks);
      hookHandler.attachHooker(ExportConfigHook.class, datasinkExportConfigHooker);
      hookHandler.attachHooker(RemoteEntityImporterHook.class, remoteDatasinkImporterHooker);

      /* register security targets */
      hookHandler.attachHooker(ConfigDoneHook.class, () -> {
         /* secure folder */
         securityServiceProvider.get().registerSecurityTarget(DatasinkFolder.class);

         /* secure datasink definition entities */
         installedDatasinkDefinitions.get()
            .forEach(securityServiceProvider.get()::registerSecurityTarget);
      });
   }
}
