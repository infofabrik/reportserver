package net.datenwerke.rs.adminutils.client.systemconsole;

import java.util.HashSet;
import java.util.Set;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.administration.AdministrationUIService;
import net.datenwerke.gf.client.administration.hooks.AdminModuleProviderHook;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.adminutils.client.systemconsole.security.SystemConsoleGenericTargetIdentifier;
import net.datenwerke.rs.adminutils.client.systemconsole.security.SystemConsoleSecurityTargetDomainHooker;
import net.datenwerke.rs.enterprise.client.EnterpriseCheckUiModule;
import net.datenwerke.rs.enterprise.client.EnterpriseUiService;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.ReadDto;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;
import net.datenwerke.security.client.security.hooks.GenericTargetProviderHook;

public class SystemConsoleUiStartup {

	private final EnterpriseUiService enterpriseService;
	private final SecurityUIService securityService;
	private final HookHandlerService hookHandler;
	private final Provider<SystemConsoleAdminModule> adminModuleProvider;
	
	@Inject
	public SystemConsoleUiStartup(
			final HookHandlerService hookHandler, 
			final WaitOnEventUIService waitOnEventService,
			final SecurityUIService securityService,
			final Provider<SystemConsoleAdminModule> adminModuleProvider,
			final SystemConsoleSecurityTargetDomainHooker systemConsoleTargetDomain,
			final EnterpriseUiService enterpriseService
			) {
		
		this.securityService = securityService;
		this.enterpriseService = enterpriseService;
		this.hookHandler = hookHandler;
		this.adminModuleProvider = adminModuleProvider;
		
		hookHandler.attachHooker(GenericTargetProviderHook.class, new GenericTargetProviderHook(systemConsoleTargetDomain.genericSecurityViewDomainHook_getTargetId()));
		hookHandler.attachHooker(GenericSecurityViewDomainHook.class, systemConsoleTargetDomain);
		
		/*
		 * both events have to be received:
		 * EnterpriseCheckUiModule.REPORTSERVER_ENTERPRISE_DETERMINED_AFTER_LOGIN and
		 * AdministrationUIService.REPORTSERVER_EVENT_HAS_ADMIN_RIGHTS
		 */
		final Set<String> eventsReceived = new HashSet<>();
		waitOnEventService.callbackOnEvent(AdministrationUIService.REPORTSERVER_EVENT_HAS_ADMIN_RIGHTS, ticket -> {
			eventsReceived.add(AdministrationUIService.REPORTSERVER_EVENT_HAS_ADMIN_RIGHTS);
			
			if (eventsReceived.contains(EnterpriseCheckUiModule.REPORTSERVER_ENTERPRISE_DETERMINED_AFTER_LOGIN)) 
				installAdminHooker();
			
			waitOnEventService.signalProcessingDone(ticket);
		});
		
		waitOnEventService.callbackOnEvent(EnterpriseCheckUiModule.REPORTSERVER_ENTERPRISE_DETERMINED_AFTER_LOGIN, ticket -> {
			eventsReceived.add(EnterpriseCheckUiModule.REPORTSERVER_ENTERPRISE_DETERMINED_AFTER_LOGIN);
			
			if (eventsReceived.contains(AdministrationUIService.REPORTSERVER_EVENT_HAS_ADMIN_RIGHTS)) 
				installAdminHooker();
			
			waitOnEventService.signalProcessingDone(ticket);
		});

	}
	
	private void installAdminHooker() {
		if (enterpriseService.isEnterprise() && securityService.hasRight(SystemConsoleGenericTargetIdentifier.class, ReadDto.class))
			hookHandler.attachHooker(AdminModuleProviderHook.class,
					new AdminModuleProviderHook(adminModuleProvider), HookHandlerService.PRIORITY_HIGH + 120);
	}
}
