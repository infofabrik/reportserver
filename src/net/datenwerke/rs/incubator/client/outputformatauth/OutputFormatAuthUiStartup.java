package net.datenwerke.rs.incubator.client.outputformatauth;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.reportexporter.hooks.VetoReportExporterHook;
import net.datenwerke.rs.incubator.client.outputformatauth.hookers.ExportOptionVetoer;

public class OutputFormatAuthUiStartup {

   @Inject
   public OutputFormatAuthUiStartup(HookHandlerService hookHandler,

         Provider<ExportOptionVetoer> exportVetoer) {

      hookHandler.attachHooker(VetoReportExporterHook.class, exportVetoer);
   }
}
