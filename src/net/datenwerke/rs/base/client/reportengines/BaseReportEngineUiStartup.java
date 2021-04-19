package net.datenwerke.rs.base.client.reportengines;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewToolbarConfiguratorHook;
import net.datenwerke.gxtdto.client.objectinformation.hooks.ObjectInfoKeyInfoProvider;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.client.reportengines.jasper.execute.Jasper2Excel;
import net.datenwerke.rs.base.client.reportengines.jasper.execute.Jasper2HTML;
import net.datenwerke.rs.base.client.reportengines.jasper.execute.Jasper2PDF;
import net.datenwerke.rs.base.client.reportengines.jasper.execute.Jasper2RTF;
import net.datenwerke.rs.base.client.reportengines.jasper.hookers.JasperReportConfigHooker;
import net.datenwerke.rs.base.client.reportengines.jasper.hookers.JasperReportDownloadJRXMLsToolbarConfiguratorHooker;
import net.datenwerke.rs.base.client.reportengines.jasper.hookers.JasperReportViewStatusBarInfoHooker;
import net.datenwerke.rs.base.client.reportengines.jasper.ui.JasperReportPreviewViewFactory;
import net.datenwerke.rs.base.client.reportengines.table.execute.Table2CSV;
import net.datenwerke.rs.base.client.reportengines.table.execute.Table2Excel;
import net.datenwerke.rs.base.client.reportengines.table.execute.Table2HTML;
import net.datenwerke.rs.base.client.reportengines.table.execute.Table2JXLS;
import net.datenwerke.rs.base.client.reportengines.table.execute.Table2PDF;
import net.datenwerke.rs.base.client.reportengines.table.hookers.CubifyHooker;
import net.datenwerke.rs.base.client.reportengines.table.hookers.TableReportConfigHooker;
import net.datenwerke.rs.base.client.reportengines.table.hookers.TableReportPreExportHooker;
import net.datenwerke.rs.base.client.reportengines.table.hookers.TableReportViewStatusBarInfoHooker;
import net.datenwerke.rs.base.client.reportengines.table.hooks.TableReportPreviewCellEnhancerHook;
import net.datenwerke.rs.base.client.reportengines.table.previewenhancer.GeoLocationEnhancer;
import net.datenwerke.rs.base.client.reportengines.table.previewenhancer.LinkToEnhancer;
import net.datenwerke.rs.base.client.reportengines.table.ui.TableReportPreviewViewFactory;
import net.datenwerke.rs.core.client.reportexecutor.hooks.ReportExecutorViewToolbarHook;
import net.datenwerke.rs.core.client.reportexecutor.hooks.ReportPreviewViewStatusbarHook;
import net.datenwerke.rs.core.client.reportexecutor.hooks.ReportViewHook;
import net.datenwerke.rs.core.client.reportexporter.hooks.ReportExporterExportReportHook;
import net.datenwerke.rs.core.client.reportexporter.hooks.ReportExporterPreExportHook;
import net.datenwerke.rs.core.client.reportmanager.hooks.ReportTypeConfigHook;
import net.datenwerke.rs.core.client.reportmanager.objectinfo.ReportObjectInfo;
import net.datenwerke.rs.enterprise.client.EnterpriseCheckUiModule;
import net.datenwerke.rs.enterprise.client.EnterpriseUiService;
import net.datenwerke.rs.saiku.client.saiku.hookers.CubeExportHooker;

public class BaseReportEngineUiStartup {

	@Inject
	public BaseReportEngineUiStartup(
		HookHandlerService hookHandler,	
		
		final JasperReportPreviewViewFactory jasperReportPreviewViewFactory, 

		final JasperReportDownloadJRXMLsToolbarConfiguratorHooker jasperReportDownloadJRXMLs,
		
		final TableReportPreviewViewFactory htmlReportFactory,
		
		final Provider<TableReportViewStatusBarInfoHooker> statusBarInfoHooker,
		final Provider<JasperReportViewStatusBarInfoHooker> jasperStatusBarInfoHooker,
		
		final Provider<CubifyHooker> cubifyHooker,
		final Provider<CubeExportHooker> cubeExportHooker,
		
		final Provider<GeoLocationEnhancer> geoLocationEnhancer,
		final Provider<LinkToEnhancer> linkToEnhancer,
		
		final ReportObjectInfo ReportObjectInfo,
		
		final JasperReportConfigHooker jasperReportConfigHooker,
		final TableReportConfigHooker tableReportConfigHooker,
		
		final Provider<Table2HTML> table2HTML,
		final Provider<Table2CSV> table2CSV,
		final Provider<Table2PDF> table2PDF,
		final Provider<Table2Excel> table2Excel,
		final Provider<Table2JXLS> table2JXLS,
		
		final Provider<Jasper2HTML> jasper2HTML,
		final Provider<Jasper2PDF> jasper2PDF,
		final Provider<Jasper2Excel> jasper2Excel,
		final Provider<Jasper2RTF> jasper2RTF,
		
		final Provider<TableReportPreExportHooker> preExportHooker,
		
		final Provider<EnterpriseUiService> enterpriseServiceProvider,
		final Provider<WaitOnEventUIService> waitOnEventServiceProvider
		){
		
		/* icon provider */
		hookHandler.attachHooker(ReportTypeConfigHook.class, tableReportConfigHooker,10);
		hookHandler.attachHooker(ReportTypeConfigHook.class, jasperReportConfigHooker,30);
	
		/* attach hooks */
		hookHandler.attachHooker(
				ReportViewHook.class,
				new ReportViewHook(htmlReportFactory),
				HookHandlerService.PRIORITY_LOW);
		
		
		/* cubify */
		hookHandler.attachHooker(ReportExecutorViewToolbarHook.class, cubifyHooker, HookHandlerService.PRIORITY_LOWER);
		hookHandler.attachHooker(ReportExecutorViewToolbarHook.class, cubeExportHooker, HookHandlerService.PRIORITY_LOWER);
		
		
		/* report view statusbar */
		hookHandler.attachHooker(ReportPreviewViewStatusbarHook.class, statusBarInfoHooker);
		hookHandler.attachHooker(ReportPreviewViewStatusbarHook.class, jasperStatusBarInfoHooker);
		
		/* view enhancer */
		hookHandler.attachHooker(TableReportPreviewCellEnhancerHook.class, geoLocationEnhancer);
		hookHandler.attachHooker(TableReportPreviewCellEnhancerHook.class, linkToEnhancer);

		
		/* attach hooks */
		hookHandler.attachHooker(
				ReportViewHook.class,
				new ReportViewHook(jasperReportPreviewViewFactory),
				HookHandlerService.PRIORITY_LOW);

		hookHandler.attachHooker(MainPanelViewToolbarConfiguratorHook.class, jasperReportDownloadJRXMLs);
		
		/* the preview does not work properly at the moment .. this should be fixed before adding it again */
//		hookHandler.attachHooker(MainPanelViewToolbarConfiguratorHook.class, jasperReportPropertiesViewToolbarConfigurator);
	
		/* pre export check */
		hookHandler.attachHooker(ReportExporterPreExportHook.class, preExportHooker);
		
		/* object info */
		hookHandler.attachHooker(ObjectInfoKeyInfoProvider.class, ReportObjectInfo);
		
		/* attach table export hooks */
		hookHandler.attachHooker(ReportExporterExportReportHook.class, new ReportExporterExportReportHook(table2Excel));
		hookHandler.attachHooker(ReportExporterExportReportHook.class, new ReportExporterExportReportHook(table2CSV), HookHandlerService.PRIORITY_LOW);
		hookHandler.attachHooker(ReportExporterExportReportHook.class, new ReportExporterExportReportHook(table2HTML), HookHandlerService.PRIORITY_LOWER + 10);
		hookHandler.attachHooker(ReportExporterExportReportHook.class, new ReportExporterExportReportHook(table2PDF), HookHandlerService.PRIORITY_LOWER + 20);
		
		waitOnEventServiceProvider.get().callbackOnEvent(EnterpriseCheckUiModule.REPORTSERVER_ENTERPRISE_DETERMINED_AFTER_LOGIN, ticket -> {
			if (enterpriseServiceProvider.get().isEnterprise())
				hookHandler.attachHooker(ReportExporterExportReportHook.class, new ReportExporterExportReportHook(table2JXLS), HookHandlerService.PRIORITY_LOWER + 30);
			
			waitOnEventServiceProvider.get().signalProcessingDone(ticket);
		});
		
		
		hookHandler.attachHooker(ReportExporterExportReportHook.class, new ReportExporterExportReportHook(jasper2Excel), HookHandlerService.PRIORITY_LOW);
		hookHandler.attachHooker(ReportExporterExportReportHook.class, new ReportExporterExportReportHook(jasper2PDF));
		hookHandler.attachHooker(ReportExporterExportReportHook.class, new ReportExporterExportReportHook(jasper2HTML), HookHandlerService.PRIORITY_LOW);
		hookHandler.attachHooker(ReportExporterExportReportHook.class, new ReportExporterExportReportHook(jasper2RTF), HookHandlerService.PRIORITY_LOW);
		

	}
}
