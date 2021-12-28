package net.datenwerke.rs.base.service.parameterreplacements;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.service.parameterreplacements.hookers.ParameterSetReplacementProviderHooker;
import net.datenwerke.rs.core.service.reportmanager.hooks.ParameterSetReplacementProviderHook;

public class BaseParameterReplacementStartup {

	@Inject
	public BaseParameterReplacementStartup(
		HookHandlerService hookHandler,
		Provider<ParameterSetReplacementProviderHooker> replacementProvider
		){
		
		hookHandler.attachHooker(ParameterSetReplacementProviderHook.class, replacementProvider);
	}
}
