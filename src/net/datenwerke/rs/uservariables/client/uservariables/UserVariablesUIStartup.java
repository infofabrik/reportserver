package net.datenwerke.rs.uservariables.client.uservariables;

import com.google.inject.Inject;

import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewProviderHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.parameters.hooks.ParameterProviderHook;
import net.datenwerke.rs.uservariables.client.uservariables.genrights.UserVariableAdminViewSecurityTargetDomainHooker;
import net.datenwerke.rs.uservariables.client.uservariables.hookers.MainPanelViewProviderHooker;
import net.datenwerke.rs.uservariables.client.uservariables.hookers.ParameterProviderHooker;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;
import net.datenwerke.security.client.security.hooks.GenericTargetProviderHook;

public class UserVariablesUIStartup {

	@Inject
	public UserVariablesUIStartup(
		final HookHandlerService hookHandler,
		final SecurityUIService securityService,
		
		final ParameterProviderHooker parameterProvider,
		
		UserVariableAdminViewSecurityTargetDomainHooker securityTargetDomain,
		MainPanelViewProviderHooker mainPanelViewProviderHooker
		){
		
		hookHandler.attachHooker(ParameterProviderHook.class, parameterProvider);
		
		/* attach security target domains */
		hookHandler.attachHooker(GenericTargetProviderHook.class, new GenericTargetProviderHook(securityTargetDomain.genericSecurityViewDomainHook_getTargetId()));
		hookHandler.attachHooker(GenericSecurityViewDomainHook.class, securityTargetDomain);
		hookHandler.attachHooker(MainPanelViewProviderHook.class, mainPanelViewProviderHooker, HookHandlerService.PRIORITY_LOW);
	}
}
