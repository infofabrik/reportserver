package net.datenwerke.rs.tsreportarea.service.tsreportarea;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.eximport.hooks.ExporterProviderHook;
import net.datenwerke.eximport.hooks.ImporterProviderHook;
import net.datenwerke.gf.service.history.hooks.HistoryUrlBuilderHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.eximport.service.eximport.hooks.ExportAllHook;
import net.datenwerke.rs.eximport.service.eximport.hooks.ImportAllHook;
import net.datenwerke.rs.eximport.service.eximport.im.http.hooks.HttpImportPostProcessProviderHook;
import net.datenwerke.rs.search.service.search.hooks.SearchResultAllowHook;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace;
import net.datenwerke.rs.teamspace.service.teamspace.hooks.TeamSpaceAppDefinitionProviderHook;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.eventhandler.HandleReportForceRemoveEvent;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.eventhandler.HandleReportRemoveEvent;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.eventhandler.TeamSpaceRemovedCallback;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.eximport.TsDiskExporter;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.eximport.TsDiskImporter;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.eximport.hookers.ExportAllTsDiskHooker;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.eximport.hookers.ImportAllTsDiskHooker;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.eximport.hookers.ImportPostProcessorHooker;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.hookers.TeamSpaceNodeSearchResultCheckHooker;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.hookers.TsFavoriteHistoryUrlBuilderForReportsHooker;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.hookers.TsFavoriteHistoryUrlBuilderHooker;
import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.security.service.eventlogger.jpa.ForceRemoveEntityEvent;
import net.datenwerke.security.service.eventlogger.jpa.RemoveEntityEvent;

public class TsDiskStartup {

   @Inject
   public TsDiskStartup(
         HookHandlerService hookHandler, 
         Provider<TsDiskTeamSpaceAppDefinition> tsDiskAppDefinition,
         ImportPostProcessorHooker importPostProcessor, 
         TsFavoriteHistoryUrlBuilderHooker urlBuilder,
         TsFavoriteHistoryUrlBuilderForReportsHooker urlBuilderForReports,

         Provider<TsDiskExporter> exporterProvider, 
         Provider<TsDiskImporter> importerProvider,
         Provider<ExportAllTsDiskHooker> exportAllHooker, 
         Provider<ImportAllTsDiskHooker> importAllHooker,
         
         TeamSpaceNodeSearchResultCheckHooker teamSpaceSearchResultCheckHooker,

         EventBus eventBus, 
         TeamSpaceRemovedCallback removedTeamSpaceCallback,
         HandleReportRemoveEvent reportRemoveEventHandler, 
         HandleReportForceRemoveEvent reportForceRemoveEventHandler
         
         ) {

      eventBus.attachObjectEventHandler(RemoveEntityEvent.class, TeamSpace.class, removedTeamSpaceCallback);
      eventBus.attachObjectEventHandler(RemoveEntityEvent.class, Report.class, reportRemoveEventHandler);
      eventBus.attachObjectEventHandler(ForceRemoveEntityEvent.class, Report.class, reportForceRemoveEventHandler);

      hookHandler.attachHooker(TeamSpaceAppDefinitionProviderHook.class,
            new TeamSpaceAppDefinitionProviderHook(tsDiskAppDefinition));

      hookHandler.attachHooker(HttpImportPostProcessProviderHook.class, importPostProcessor);

      hookHandler.attachHooker(HistoryUrlBuilderHook.class, urlBuilder, HookHandlerService.PRIORITY_HIGH);
      hookHandler.attachHooker(HistoryUrlBuilderHook.class, urlBuilderForReports, HookHandlerService.PRIORITY_LOW);

      /* eximport */
      hookHandler.attachHooker(ExporterProviderHook.class, new ExporterProviderHook(exporterProvider));
      hookHandler.attachHooker(ImporterProviderHook.class, new ImporterProviderHook(importerProvider));
      hookHandler.attachHooker(ExportAllHook.class, exportAllHooker);
      hookHandler.attachHooker(ImportAllHook.class, importAllHooker);
      
      hookHandler.attachHooker(SearchResultAllowHook.class, teamSpaceSearchResultCheckHooker);
   }
}
