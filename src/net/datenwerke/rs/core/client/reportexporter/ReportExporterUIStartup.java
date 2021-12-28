package net.datenwerke.rs.core.client.reportexporter;

import com.google.inject.Inject;

import net.datenwerke.gf.client.dispatcher.DispatcherService;
import net.datenwerke.gxtdto.client.utilityservices.UtilsUIService;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

public class ReportExporterUIStartup {

   @Inject
   public ReportExporterUIStartup(HookHandlerService hookHandler, final WaitOnEventUIService waitOnEventService,
         final UtilsUIService utilsService, final ReportExporterUIService exporterService,
         final ReportExporterDao reportExporterDao, final DispatcherService dispatcherService) {

   }
}
