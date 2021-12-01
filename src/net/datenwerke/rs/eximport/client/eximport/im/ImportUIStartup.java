package net.datenwerke.rs.eximport.client.eximport.im;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.administration.AdministrationUIService;
import net.datenwerke.gf.client.administration.hooks.AdminModuleProviderHook;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.eximport.client.eximport.security.ImportGenericTargetIdentifier;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.ReadDto;

public class ImportUIStartup {

	@Inject
	public ImportUIStartup(
		final HookHandlerService hookHandler,
		final WaitOnEventUIService waitOnEventService,
		final SecurityUIService securityService,
		final Provider<ImportAdminModule> importAdminModule
	){
		
		/* test if user has rights to see mass mail view */
		waitOnEventService.callbackOnEvent(AdministrationUIService.REPORTSERVER_EVENT_HAS_ADMIN_RIGHTS, ticket -> {
			if(securityService.hasRight(ImportGenericTargetIdentifier.class, ReadDto.class))
				hookHandler.attachHooker(AdminModuleProviderHook.class, new AdminModuleProviderHook(importAdminModule), HookHandlerService.PRIORITY_HIGH + 80);

			waitOnEventService.signalProcessingDone(ticket);
		});
	}

	
}
