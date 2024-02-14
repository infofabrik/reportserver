package net.datenwerke.rs.base.service.reportengines.jasper;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.download.hooks.FileDownloadHandlerHook;
import net.datenwerke.gf.service.fileselection.hooks.FileSelectionHandlerHook;
import net.datenwerke.gf.service.upload.hooks.FileUploadHandlerHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReport;
import net.datenwerke.rs.base.service.reportengines.jasper.hookers.BaseJasperOutputGeneratorProvider;
import net.datenwerke.rs.base.service.reportengines.jasper.hookers.JasperReportJRXMLDownloadHooker;
import net.datenwerke.rs.base.service.reportengines.jasper.hookers.JasperReportMasterUploadHooker;
import net.datenwerke.rs.base.service.reportengines.jasper.hookers.JasperReportSubreportHandler;
import net.datenwerke.rs.base.service.reportengines.jasper.hooks.JasperOutputGeneratorProviderHook;
import net.datenwerke.rs.core.service.reportmanager.hookers.factory.ReportDefaultMergeHookerFactory;
import net.datenwerke.rs.utils.entitymerge.service.hooks.EntityMergeHook;

public class JasperReportStartup {

   @Inject
   public JasperReportStartup(
         final JasperReportMasterUploadHooker jasperReportMasterUploadHooker,
         final JasperReportSubreportHandler subreportHandler, 
         final JasperReportJRXMLDownloadHooker jrxmlDownloadHandler,

         final Provider<BaseJasperOutputGeneratorProvider> baseOutputGenerators,

         final HookHandlerService hookHandlerService,
         
         final Provider<ReportDefaultMergeHookerFactory> reportFactory
         ) {

      hookHandlerService.attachHooker(FileUploadHandlerHook.class, jasperReportMasterUploadHooker);

      hookHandlerService.attachHooker(FileSelectionHandlerHook.class, subreportHandler);

      hookHandlerService.attachHooker(FileDownloadHandlerHook.class, jrxmlDownloadHandler);
      
      /* entity merge */
      hookHandlerService.attachHooker(EntityMergeHook.class, reportFactory.get().create(JasperReport.class));

      /* base exporters */
      hookHandlerService.attachHooker(JasperOutputGeneratorProviderHook.class, baseOutputGenerators,
            HookHandlerService.PRIORITY_LOW);
   }
}
