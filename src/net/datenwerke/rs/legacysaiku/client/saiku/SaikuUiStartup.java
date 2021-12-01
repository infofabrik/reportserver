package net.datenwerke.rs.legacysaiku.client.saiku;


import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gxtdto.client.waitonevent.SynchronousCallbackOnEventTrigger;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventTicket;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.reportexecutor.hooks.ReportViewHook;
import net.datenwerke.rs.core.client.reportexporter.hooks.ReportExporterExportReportHook;
import net.datenwerke.rs.dashboard.client.dashboard.security.DashboardViewGenericTargetIdentifier;
import net.datenwerke.rs.legacysaiku.client.saiku.reportengines.Saiku2CSV;
import net.datenwerke.rs.legacysaiku.client.saiku.reportengines.Saiku2ChartHTML;
import net.datenwerke.rs.legacysaiku.client.saiku.reportengines.Saiku2Excel;
import net.datenwerke.rs.legacysaiku.client.saiku.reportengines.Saiku2HTML;
import net.datenwerke.rs.legacysaiku.client.saiku.reportengines.Saiku2PDF;
import net.datenwerke.rs.legacysaiku.client.saiku.ui.SaikuReportPreviewViewFactory;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.ReadDto;

public class SaikuUiStartup {
	
	@Inject
	public SaikuUiStartup(
		final HookHandlerService hookHandler,
		final WaitOnEventUIService waitOnEventService,
		final SecurityUIService securityService,
		
		SaikuReportPreviewViewFactory saikuReportPreviewViewFactory,
		
		Provider<Saiku2Excel> saiku2Excel,
		Provider<Saiku2PDF> saiku2PDF,
		Provider<Saiku2CSV> saiku2CSV, 
		Provider<Saiku2HTML> saiku2HTML,
		Provider<Saiku2ChartHTML> saiku2ChartHTML
		
		
		) {
		
		hookHandler.attachHooker(ReportViewHook.class, new ReportViewHook(saikuReportPreviewViewFactory), HookHandlerService.PRIORITY_LOW);
		
		hookHandler.attachHooker(ReportExporterExportReportHook.class, new ReportExporterExportReportHook(saiku2Excel));
		hookHandler.attachHooker(ReportExporterExportReportHook.class, new ReportExporterExportReportHook(saiku2PDF), HookHandlerService.PRIORITY_LOW);
		hookHandler.attachHooker(ReportExporterExportReportHook.class, new ReportExporterExportReportHook(saiku2CSV), HookHandlerService.PRIORITY_LOW);
		hookHandler.attachHooker(ReportExporterExportReportHook.class, new ReportExporterExportReportHook(saiku2HTML), HookHandlerService.PRIORITY_LOW);
		hookHandler.attachHooker(ReportExporterExportReportHook.class, new ReportExporterExportReportHook(saiku2ChartHTML), HookHandlerService.PRIORITY_LOW);
		
		/* request callback after login and check for rights */
		waitOnEventService.callbackOnEvent(SecurityUIService.REPORTSERVER_EVENT_GENERIC_RIGHTS_LOADED, new SynchronousCallbackOnEventTrigger() {
			
			public void execute(final WaitOnEventTicket ticket) {
				waitOnEventService.signalProcessingDone(ticket);
			}
		});
	}

}
