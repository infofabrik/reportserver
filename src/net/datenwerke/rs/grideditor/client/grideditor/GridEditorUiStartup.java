package net.datenwerke.rs.grideditor.client.grideditor;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gxtdto.client.waitonevent.SynchronousCallbackOnEventTrigger;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventTicket;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.reportexecutor.hooks.ReportViewHook;
import net.datenwerke.rs.core.client.reportexporter.hooks.ReportExporterExportReportHook;
import net.datenwerke.rs.core.client.reportmanager.hooks.ReportTypeConfigHook;
import net.datenwerke.rs.dashboard.client.dashboard.hooks.ReportDadgetExportHook;
import net.datenwerke.rs.dashboard.client.dashboard.security.DashboardViewGenericTargetIdentifier;
import net.datenwerke.rs.grideditor.client.grideditor.dashboard.ReportDadgetExporter;
import net.datenwerke.rs.grideditor.client.grideditor.execute.GridEditor2Excel;
import net.datenwerke.rs.grideditor.client.grideditor.hookers.GridEditorConfigHooker;
import net.datenwerke.rs.grideditor.client.grideditor.ui.GridEditorReportPreviewViewFactory;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.ReadDto;

public class GridEditorUiStartup {

	@Inject
	public GridEditorUiStartup(
			final HookHandlerService hookHandler,
			final WaitOnEventUIService waitOnEventService,
			final SecurityUIService securityService,
			
			GridEditorConfigHooker gridEditorReportConfigHooker,
			GridEditorReportPreviewViewFactory gridEditorReportPreviewViewFactory,
			
			Provider<GridEditor2Excel> gridEditor2Excel,
			
			final Provider<ReportDadgetExporter> reportDadgetExporterProvider
			) {

			hookHandler.attachHooker(ReportTypeConfigHook.class, gridEditorReportConfigHooker,80);
			hookHandler.attachHooker(ReportViewHook.class, new ReportViewHook(gridEditorReportPreviewViewFactory), HookHandlerService.PRIORITY_LOW);

			hookHandler.attachHooker(ReportExporterExportReportHook.class, new ReportExporterExportReportHook(gridEditor2Excel));
			
			waitOnEventService.callbackOnEvent(SecurityUIService.REPORTSERVER_EVENT_GENERIC_RIGHTS_LOADED, new SynchronousCallbackOnEventTrigger() {
				
				public void execute(final WaitOnEventTicket ticket) {
					if(securityService.hasRight(DashboardViewGenericTargetIdentifier.class, ReadDto.class)){
						hookHandler.attachHooker(ReportDadgetExportHook.class, reportDadgetExporterProvider);
					}

					waitOnEventService.signalProcessingDone(ticket);
				}
			});
		}
}
