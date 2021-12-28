package net.datenwerke.rs.legacysaiku.service.saiku;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.server.reportexport.hooks.ReportExportViaSessionHook;
import net.datenwerke.rs.core.service.datasourcemanager.hooks.DatasourceProviderHook;
import net.datenwerke.rs.core.service.reportmanager.hooks.ReportEngineProviderHook;
import net.datenwerke.rs.core.service.reportmanager.hooks.ReportTypeProviderHook;
import net.datenwerke.rs.core.service.reportmanager.hooks.VariantToBeStoredHook;
import net.datenwerke.rs.legacysaiku.service.hooker.MondrianDatasourceProviderHooker;
import net.datenwerke.rs.legacysaiku.service.hooker.ReportExportViaSessionHooker;
import net.datenwerke.rs.legacysaiku.service.hooker.SaikuReportTypeProviderHooker;
import net.datenwerke.rs.legacysaiku.service.hooker.VariantStoreHooker;
import net.datenwerke.rs.legacysaiku.service.saiku.reportengine.hookers.BaseSaikuOutputGeneratorProvider;
import net.datenwerke.rs.legacysaiku.service.saiku.reportengine.hookers.SaikuReportEngineProviderHooker;
import net.datenwerke.rs.legacysaiku.service.saiku.reportengine.hooks.SaikuOutputGeneratorProviderHook;

public class SaikuStartup {

   @Inject
   public SaikuStartup(HookHandlerService hookHandler, MondrianDatasourceProviderHooker mondrianDatasourceProvider,
         VariantStoreHooker variantStoreHooker, SaikuReportEngineProviderHooker saikuReportEngineProviderHooker,
         ReportExportViaSessionHooker reportExportViaSessionHooker,

         SaikuReportTypeProviderHooker saikuReportTypeProviderHooker,

         Provider<BaseSaikuOutputGeneratorProvider> baseOutputGenerators) {

      hookHandler.attachHooker(ReportEngineProviderHook.class, saikuReportEngineProviderHooker);
      hookHandler.attachHooker(DatasourceProviderHook.class, mondrianDatasourceProvider);
      hookHandler.attachHooker(VariantToBeStoredHook.class, variantStoreHooker);
      hookHandler.attachHooker(ReportExportViaSessionHook.class, reportExportViaSessionHooker);

      hookHandler.attachHooker(ReportTypeProviderHook.class, saikuReportTypeProviderHooker);

      /* base exporters */
      hookHandler.attachHooker(SaikuOutputGeneratorProviderHook.class, baseOutputGenerators,
            HookHandlerService.PRIORITY_LOW);
   }
}
