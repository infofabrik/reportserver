package net.datenwerke.rs.incubator.service.exportmetadata;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.service.reportengines.jasper.hooks.JasperExportHook;
import net.datenwerke.rs.base.service.reportengines.table.hooks.TableExportHook;
import net.datenwerke.rs.incubator.service.exportmetadata.hookers.MetadataJasperPdfExportHooker;
import net.datenwerke.rs.incubator.service.exportmetadata.hookers.MetadataTablePdfExportHooker;

public class ExportMetadataStartup {

   @Inject
   public ExportMetadataStartup(HookHandlerService hookHandlerService,

         Provider<MetadataJasperPdfExportHooker> jasperPdfExportHookerProvider,
         Provider<MetadataTablePdfExportHooker> tablePdfExportHookerProvider) {

      hookHandlerService.attachHooker(JasperExportHook.class, jasperPdfExportHookerProvider);
      hookHandlerService.attachHooker(TableExportHook.class, tablePdfExportHookerProvider);
   }
}
