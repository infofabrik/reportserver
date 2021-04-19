package net.datenwerke.rs.incubator.client.jaspertotable;

import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewToolbarConfiguratorHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.reportexporter.hooks.ReportExporterExportReportHook;
import net.datenwerke.rs.incubator.client.jaspertotable.hooker.EditJasperToTablePropertiesHooker;
import net.datenwerke.rs.incubator.client.jaspertotable.hooker.Jasper2TableExcel;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class JasperToTableUIStartup {

	@Inject
	public JasperToTableUIStartup(
		HookHandlerService hookHandler,
		Provider<Jasper2TableExcel> jasper2TableExcel,
		Provider<EditJasperToTablePropertiesHooker> editConfigProvider
		){
		
		hookHandler.attachHooker(ReportExporterExportReportHook.class, new ReportExporterExportReportHook(jasper2TableExcel), HookHandlerService.PRIORITY_LOW);
		hookHandler.attachHooker(MainPanelViewToolbarConfiguratorHook.class, editConfigProvider);
	}
}
