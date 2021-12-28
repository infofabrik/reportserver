package net.datenwerke.rs.base.ext.client.dashboardmanager.eximport.ex;

import com.google.inject.Inject;

import net.datenwerke.gf.client.administration.AdministrationUIService;
import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewToolbarConfiguratorHook;
import net.datenwerke.gxtdto.client.waitonevent.SynchronousCallbackOnEventTrigger;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventTicket;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.ext.client.dashboardmanager.eximport.ex.hookers.ExportButtonHook;
import net.datenwerke.rs.eximport.client.eximport.security.ExportGenericTargetIdentifier;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.ExecuteDto;

public class DashboardManagerExportUIStartup {

	@Inject
	public DashboardManagerExportUIStartup(
		final HookHandlerService hookHandler,
		final WaitOnEventUIService waitOnEventService,
		final SecurityUIService securityService,
		final ExportButtonHook exportButtonHook
		){

		/* test if user has rights to see dashboard manager admin view */
		waitOnEventService.callbackOnEvent(AdministrationUIService.REPORTSERVER_EVENT_HAS_ADMIN_RIGHTS, new SynchronousCallbackOnEventTrigger(){
			public void execute(final WaitOnEventTicket ticket) {
				if(securityService.hasRight(ExportGenericTargetIdentifier.class, ExecuteDto.class))
					hookHandler.attachHooker(MainPanelViewToolbarConfiguratorHook.class, exportButtonHook);

				waitOnEventService.signalProcessingDone(ticket);
			}
		});
	}
}
