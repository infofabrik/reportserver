package net.datenwerke.rs.jxlsreport.client.jxlsreport;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewToolbarConfiguratorHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.reportexecutor.hooks.ReportViewHook;
import net.datenwerke.rs.core.client.reportexporter.hooks.ReportExporterExportReportHook;
import net.datenwerke.rs.core.client.reportmanager.hooks.ReportTypeConfigHook;
import net.datenwerke.rs.jxlsreport.client.jxlsreport.execute.Jxls2Excel;
import net.datenwerke.rs.jxlsreport.client.jxlsreport.execute.Jxls2Html;
import net.datenwerke.rs.jxlsreport.client.jxlsreport.hookers.JxlsReportConfigHooker;
import net.datenwerke.rs.jxlsreport.client.jxlsreport.hookers.JxlsReportFileDownloadToolbarConfiguratorHooker;
import net.datenwerke.rs.jxlsreport.client.jxlsreport.ui.JxlsReportPreviewViewFactory;

public class JxlsReportUiStartup {

	@Inject
	public JxlsReportUiStartup(HookHandlerService hookHandler,
		JxlsReportConfigHooker jxlsReportConfigHooker,
		JxlsReportPreviewViewFactory jxlsReportPreviewViewFactory,
		
		JxlsReportFileDownloadToolbarConfiguratorHooker jxlsReportFileDownloadToolbarConfiguratorHooker,
		
		Provider<Jxls2Excel> jxls2Excel,
		Provider<Jxls2Html> jxls2Html
		) {

		hookHandler.attachHooker(ReportTypeConfigHook.class, jxlsReportConfigHooker,50);
		hookHandler.attachHooker(ReportViewHook.class, new ReportViewHook(jxlsReportPreviewViewFactory), HookHandlerService.PRIORITY_LOW);

		hookHandler.attachHooker(MainPanelViewToolbarConfiguratorHook.class, jxlsReportFileDownloadToolbarConfiguratorHooker);

		hookHandler.attachHooker(ReportExporterExportReportHook.class, new ReportExporterExportReportHook(jxls2Excel));
		hookHandler.attachHooker(ReportExporterExportReportHook.class, new ReportExporterExportReportHook(jxls2Html), HookHandlerService.PRIORITY_LOWER);
		
		//			hookHandler.attachHooker(ReportExporterExportReportHook.class, new ReportExporterExportReportHook(crystal2Text), HookHandlerService.PRIORITY_LOW);
		//			hookHandler.attachHooker(ReportExporterExportReportHook.class, new ReportExporterExportReportHook(crystal2XML), HookHandlerService.PRIORITY_LOW);

	}
}
