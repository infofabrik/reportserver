package net.datenwerke.rs.crystal.client.crystal;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewToolbarConfiguratorHook;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.waitonevent.SynchronousCallbackOnEventTrigger;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventTicket;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.reportexecutor.hooks.ReportViewHook;
import net.datenwerke.rs.core.client.reportexporter.hooks.ReportExporterExportReportHook;
import net.datenwerke.rs.core.client.reportmanager.hooks.ReportTypeConfigHook;
import net.datenwerke.rs.crystal.client.crystal.hookers.CrystalReportConfigHooker;
import net.datenwerke.rs.crystal.client.crystal.hookers.CrystalReportDadgetExporter;
import net.datenwerke.rs.crystal.client.crystal.hookers.CrystalReportDownloadToolbarConfiguratorHooker;
import net.datenwerke.rs.crystal.client.crystal.hookers.CrystalReportParameterProposerToolbarConfiguratorHooker;
import net.datenwerke.rs.crystal.client.crystal.reportengines.Crystal2CSV;
import net.datenwerke.rs.crystal.client.crystal.reportengines.Crystal2Excel;
import net.datenwerke.rs.crystal.client.crystal.reportengines.Crystal2PDF;
import net.datenwerke.rs.crystal.client.crystal.reportengines.Crystal2Word;
import net.datenwerke.rs.crystal.client.crystal.ui.CrystalReportPreviewViewFactory;
import net.datenwerke.rs.dashboard.client.dashboard.hooks.ReportDadgetExportHook;
import net.datenwerke.rs.dashboard.client.dashboard.security.DashboardViewGenericTargetIdentifier;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.ReadDto;

public class CrystalUiStartup {
	
	@Inject
	public CrystalUiStartup(
		final HookHandlerService hookHandler,
		final WaitOnEventUIService waitOnEventService,
		final SecurityUIService securityService,
		final CrystalUtilsDao crystalDao, 
		
		final CrystalReportConfigHooker crystalReportConfigHooker,
		final CrystalReportPreviewViewFactory crystalReportPreviewViewFactory,
		
		final CrystalReportDownloadToolbarConfiguratorHooker crystalReportDownloadRPT,
		
		final CrystalUiServiceImpl crystalService,
		
		final Provider<Crystal2CSV> crystal2CSV,
		final Provider<Crystal2Excel> crystal2Excel,
		final Provider<Crystal2PDF> crystal2PDF,
		final Provider<Crystal2Word> crystal2Word,
		final CrystalReportParameterProposerToolbarConfiguratorHooker crystalReportParameterProposerToolbarConfiguratorHooker,
		
		final Provider<CrystalReportDadgetExporter> reportDadgetExporterProvider
		) {
		
		hookHandler.attachHooker(ReportTypeConfigHook.class, crystalReportConfigHooker,40);
		
		hookHandler.attachHooker(ReportViewHook.class, new ReportViewHook(crystalReportPreviewViewFactory), HookHandlerService.PRIORITY_LOW);
		
		hookHandler.attachHooker(ReportExporterExportReportHook.class, new ReportExporterExportReportHook(crystal2PDF));
		hookHandler.attachHooker(ReportExporterExportReportHook.class, new ReportExporterExportReportHook(crystal2Word), HookHandlerService.PRIORITY_LOW);
		hookHandler.attachHooker(ReportExporterExportReportHook.class, new ReportExporterExportReportHook(crystal2Excel), HookHandlerService.PRIORITY_LOW);
		hookHandler.attachHooker(ReportExporterExportReportHook.class, new ReportExporterExportReportHook(crystal2CSV), HookHandlerService.PRIORITY_LOW);
		
		hookHandler.attachHooker(MainPanelViewToolbarConfiguratorHook.class, crystalReportDownloadRPT);
		hookHandler.attachHooker(MainPanelViewToolbarConfiguratorHook.class, crystalReportParameterProposerToolbarConfiguratorHooker);
		
		/* request callback after login and check for rights */
		waitOnEventService.callbackOnEvent(SecurityUIService.REPORTSERVER_EVENT_GENERIC_RIGHTS_LOADED, new SynchronousCallbackOnEventTrigger() {
			
			public void execute(final WaitOnEventTicket ticket) {
				waitOnEventService.signalProcessingDone(ticket);
				
				if(securityService.hasRight(DashboardViewGenericTargetIdentifier.class, ReadDto.class)){
					hookHandler.attachHooker(ReportDadgetExportHook.class, reportDadgetExporterProvider);
				}
			}
		});


		/* check if we should crystal reports (if admin rights are present) */
		waitOnEventService.callbackOnEvent(LoginService.REPORTSERVER_EVENT_AFTER_ANY_LOGIN, new SynchronousCallbackOnEventTrigger() {
			public void execute(final WaitOnEventTicket ticket) {
				waitOnEventService.signalProcessingDone(ticket);
				crystalDao.hasCrystalLibraries(new RsAsyncCallback<Boolean>(){
					@Override
					public void onSuccess(Boolean result) {
						crystalService.setAvailable(Boolean.TRUE.equals(result));
					}
				});
			}
		});
		
	}

}
