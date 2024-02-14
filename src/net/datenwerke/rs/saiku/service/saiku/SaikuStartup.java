package net.datenwerke.rs.saiku.service.saiku;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.config.ClientConfigExposerHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.service.hooks.UsageStatisticsReportEntryProviderHook;
import net.datenwerke.rs.core.server.reportexport.hooks.ReportExportViaSessionHook;
import net.datenwerke.rs.core.service.datasourcemanager.hookers.factory.DatasourceDefaultMergeHookerFactory;
import net.datenwerke.rs.core.service.datasourcemanager.hooks.DatasourceProviderHook;
import net.datenwerke.rs.core.service.datasourcemanager.hooks.UsageStatisticsDatasourceEntryProviderHook;
import net.datenwerke.rs.core.service.reportmanager.hookers.factory.ReportDefaultMergeHookerFactory;
import net.datenwerke.rs.core.service.reportmanager.hooks.ReportEngineProviderHook;
import net.datenwerke.rs.core.service.reportmanager.hooks.ReportTypeProviderHook;
import net.datenwerke.rs.core.service.reportmanager.hooks.VariantCreatorHook;
import net.datenwerke.rs.core.service.reportmanager.hooks.VariantToBeStoredHook;
import net.datenwerke.rs.saiku.service.datasource.MondrianDatasource;
import net.datenwerke.rs.saiku.service.hooker.MondrianDatasourceProviderHooker;
import net.datenwerke.rs.saiku.service.hooker.ReportExportViaSessionHooker;
import net.datenwerke.rs.saiku.service.hooker.SaikuClientConfigExposerHooker;
import net.datenwerke.rs.saiku.service.hooker.SaikuJuelParameterAdapter;
import net.datenwerke.rs.saiku.service.hooker.SaikuReportTypeProviderHooker;
import net.datenwerke.rs.saiku.service.hooker.UsageStatisticsMondrianDatasourcesProviderHooker;
import net.datenwerke.rs.saiku.service.hooker.UsageStatisticsSaikuProviderHooker;
import net.datenwerke.rs.saiku.service.hooker.VariantCreatedAdjustSaikuQueryHooker;
import net.datenwerke.rs.saiku.service.hooker.VariantStoreHooker;
import net.datenwerke.rs.saiku.service.hooks.SaikuQueryParameterAdapterHook;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReport;
import net.datenwerke.rs.saiku.service.saiku.reportengine.hookers.BaseSaikuOutputGeneratorProvider;
import net.datenwerke.rs.saiku.service.saiku.reportengine.hookers.SaikuReportEngineProviderHooker;
import net.datenwerke.rs.saiku.service.saiku.reportengine.hooks.SaikuOutputGeneratorProviderHook;
import net.datenwerke.rs.utils.entitymerge.service.hooks.EntityMergeHook;

public class SaikuStartup {

   @Inject
   public SaikuStartup(
         final HookHandlerService hookHandler, 
         final MondrianDatasourceProviderHooker mondrianDatasourceProvider,
         final SaikuReportEngineProviderHooker saikuReportEngineProviderHooker,
         final ReportExportViaSessionHooker reportExportViaSessionHooker,
         final VariantStoreHooker variantStoreHooker, 
         final SaikuReportTypeProviderHooker saikuReportTypeProviderHooker,
         final VariantCreatedAdjustSaikuQueryHooker adjustSaikuQueryHooker,

         final SaikuClientConfigExposerHooker clientConfigExposer,

         final Provider<BaseSaikuOutputGeneratorProvider> baseOutputGenerators,

         final SaikuJuelParameterAdapter juelParameterAdapter,
         
         final Provider<UsageStatisticsSaikuProviderHooker> usageStatsSaikuProvider,
         
         final Provider<UsageStatisticsMondrianDatasourcesProviderHooker> usageStatsMondrianDatasourceProvider,
         
         final Provider<ReportDefaultMergeHookerFactory> reportFactory,
         
         final Provider<DatasourceDefaultMergeHookerFactory> datasourceFactory
         ) {

      hookHandler.attachHooker(ReportEngineProviderHook.class, saikuReportEngineProviderHooker);
      hookHandler.attachHooker(DatasourceProviderHook.class, mondrianDatasourceProvider);
      hookHandler.attachHooker(ReportExportViaSessionHook.class, reportExportViaSessionHooker);
      hookHandler.attachHooker(VariantCreatorHook.class, adjustSaikuQueryHooker);
      hookHandler.attachHooker(VariantToBeStoredHook.class, variantStoreHooker);

      hookHandler.attachHooker(ReportTypeProviderHook.class, saikuReportTypeProviderHooker);

      hookHandler.attachHooker(ClientConfigExposerHook.class, clientConfigExposer);

      hookHandler.attachHooker(SaikuQueryParameterAdapterHook.class, juelParameterAdapter);
      
      /* entity merge */
      hookHandler.attachHooker(EntityMergeHook.class, reportFactory.get().create(SaikuReport.class));
      hookHandler.attachHooker(EntityMergeHook.class, datasourceFactory.get().create(MondrianDatasource.class));

      /* base exporters */
      hookHandler.attachHooker(SaikuOutputGeneratorProviderHook.class, baseOutputGenerators,
            HookHandlerService.PRIORITY_LOW);
      
      hookHandler.attachHooker(UsageStatisticsReportEntryProviderHook.class, usageStatsSaikuProvider,
            HookHandlerService.PRIORITY_LOW + 30);
      
      hookHandler.attachHooker(UsageStatisticsDatasourceEntryProviderHook.class, usageStatsMondrianDatasourceProvider,
            HookHandlerService.PRIORITY_LOW + 15);
   }
}

