package net.datenwerke.rs.saiku.service.saiku;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.config.ClientConfigExposerHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.server.reportexport.hooks.ReportExportViaSessionHook;
import net.datenwerke.rs.core.service.datasourcemanager.hooks.DatasourceProviderHook;
import net.datenwerke.rs.core.service.reportmanager.hooks.ReportEngineProviderHook;
import net.datenwerke.rs.core.service.reportmanager.hooks.ReportTypeProviderHook;
import net.datenwerke.rs.core.service.reportmanager.hooks.VariantCreatorHook;
import net.datenwerke.rs.core.service.reportmanager.hooks.VariantToBeStoredHook;
import net.datenwerke.rs.saiku.service.hooker.MondrianDatasourceProviderHooker;
import net.datenwerke.rs.saiku.service.hooker.ReportExportViaSessionHooker;
import net.datenwerke.rs.saiku.service.hooker.SaikuClientConfigExposerHooker;
import net.datenwerke.rs.saiku.service.hooker.SaikuJuelParameterAdapter;
import net.datenwerke.rs.saiku.service.hooker.SaikuReportTypeProviderHooker;
import net.datenwerke.rs.saiku.service.hooker.VariantCreatedAdjustSaikuQueryHooker;
import net.datenwerke.rs.saiku.service.hooker.VariantStoreHooker;
import net.datenwerke.rs.saiku.service.hooks.SaikuQueryParameterAdapterHook;
import net.datenwerke.rs.saiku.service.saiku.reportengine.hookers.BaseSaikuOutputGeneratorProvider;
import net.datenwerke.rs.saiku.service.saiku.reportengine.hookers.SaikuReportEngineProviderHooker;
import net.datenwerke.rs.saiku.service.saiku.reportengine.hooks.SaikuOutputGeneratorProviderHook;

public class SaikuStartup {

   @Inject
   public SaikuStartup(HookHandlerService hookHandler, MondrianDatasourceProviderHooker mondrianDatasourceProvider,
         SaikuReportEngineProviderHooker saikuReportEngineProviderHooker,
         ReportExportViaSessionHooker reportExportViaSessionHooker,
         VariantStoreHooker variantStoreHooker, 
         SaikuReportTypeProviderHooker saikuReportTypeProviderHooker,
         VariantCreatedAdjustSaikuQueryHooker adjustSaikuQueryHooker,

         SaikuClientConfigExposerHooker clientConfigExposer,

         Provider<BaseSaikuOutputGeneratorProvider> baseOutputGenerators,

         SaikuJuelParameterAdapter juelParameterAdapter) {

      hookHandler.attachHooker(ReportEngineProviderHook.class, saikuReportEngineProviderHooker);
      hookHandler.attachHooker(DatasourceProviderHook.class, mondrianDatasourceProvider);
      hookHandler.attachHooker(ReportExportViaSessionHook.class, reportExportViaSessionHooker);
      hookHandler.attachHooker(VariantCreatorHook.class, adjustSaikuQueryHooker);
      hookHandler.attachHooker(VariantToBeStoredHook.class, variantStoreHooker);

      hookHandler.attachHooker(ReportTypeProviderHook.class, saikuReportTypeProviderHooker);

      hookHandler.attachHooker(ClientConfigExposerHook.class, clientConfigExposer);

      hookHandler.attachHooker(SaikuQueryParameterAdapterHook.class, juelParameterAdapter);

      /* base exporters */
      hookHandler.attachHooker(SaikuOutputGeneratorProviderHook.class, baseOutputGenerators,
            HookHandlerService.PRIORITY_LOW);
   }
}

