package net.datenwerke.rs.tabletemplate.client.tabletemplate;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.client.reportengines.table.columnfilter.hooks.FilterViewEnhanceToolbarHook;
import net.datenwerke.rs.core.client.reportexporter.hooks.ReportExporterExportReportHook;
import net.datenwerke.rs.enterprise.client.EnterpriseCheckUiModule;
import net.datenwerke.rs.enterprise.client.EnterpriseUiService;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.hookers.EnhanceFilterToolbarHooker;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.hookers.ReportExportToTemplateHooker;

public class TableTemplateUIStartup {

	@Inject
	public TableTemplateUIStartup(
		final HookHandlerService hookHandler,
		final WaitOnEventUIService waitOnEventService,
		final EnterpriseUiService enterpriseUiService,
		
		final Provider<ReportExportToTemplateHooker> exportToTemplateHooker,
		final Provider<EnhanceFilterToolbarHooker> enhanceFilterToolbarProvider
		){
		
		waitOnEventService.callbackOnEvent(EnterpriseCheckUiModule.REPORTSERVER_ENTERPRISE_DETERMINED_AFTER_LOGIN, 
				ticket -> {
					if(enterpriseUiService.isEnterprise()){
						hookHandler.attachHooker(ReportExporterExportReportHook.class, new ReportExporterExportReportHook(exportToTemplateHooker), HookHandlerService.PRIORITY_LOWER);
						hookHandler.attachHooker(FilterViewEnhanceToolbarHook.class, enhanceFilterToolbarProvider, HookHandlerService.PRIORITY_LOW);
					}
					waitOnEventService.signalProcessingDone(ticket);
				});
	}
}
