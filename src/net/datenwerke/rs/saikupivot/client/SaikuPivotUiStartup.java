package net.datenwerke.rs.saikupivot.client;

import com.google.inject.Inject;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.reportexecutor.hooks.ReportViewHook;
import net.datenwerke.rs.saikupivot.client.table.SaikuTableReportPreviewViewFactory;

public class SaikuPivotUiStartup {

   @Inject
   public SaikuPivotUiStartup(HookHandlerService hookHandlerService,

         SaikuTableReportPreviewViewFactory saikuTableReportPreviewViewFactory

   ) {
      hookHandlerService.attachHooker(ReportViewHook.class, new ReportViewHook(saikuTableReportPreviewViewFactory),
            HookHandlerService.PRIORITY_LOW);

   }
}
