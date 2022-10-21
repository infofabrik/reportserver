package net.datenwerke.rs.base.service.reportengines.jasper;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.download.hooks.FileDownloadHandlerHook;
import net.datenwerke.gf.service.fileselection.hooks.FileSelectionHandlerHook;
import net.datenwerke.gf.service.upload.hooks.FileUploadHandlerHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.service.reportengines.jasper.hookers.BaseJasperOutputGeneratorProvider;
import net.datenwerke.rs.base.service.reportengines.jasper.hookers.JasperReportJRXMLDownloadHooker;
import net.datenwerke.rs.base.service.reportengines.jasper.hookers.JasperReportMasterUploadHooker;
import net.datenwerke.rs.base.service.reportengines.jasper.hookers.JasperReportSubreportHandler;
import net.datenwerke.rs.base.service.reportengines.jasper.hooks.JasperOutputGeneratorProviderHook;

public class JasperReportStartup {

   @Inject
   public JasperReportStartup(JasperReportMasterUploadHooker jasperReportMasterUploadHooker,
         JasperReportSubreportHandler subreportHandler, JasperReportJRXMLDownloadHooker jrxmlDownloadHandler,

         Provider<BaseJasperOutputGeneratorProvider> baseOutputGenerators,

         HookHandlerService hookHandlerService) {

      hookHandlerService.attachHooker(FileUploadHandlerHook.class, jasperReportMasterUploadHooker);

      hookHandlerService.attachHooker(FileSelectionHandlerHook.class, subreportHandler);

      hookHandlerService.attachHooker(FileDownloadHandlerHook.class, jrxmlDownloadHandler);

      /* base exporters */
      hookHandlerService.attachHooker(JasperOutputGeneratorProviderHook.class, baseOutputGenerators,
            HookHandlerService.PRIORITY_LOW);
   }
}
