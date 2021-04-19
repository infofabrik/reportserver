package net.datenwerke.rs.base.service.parameterreplacements;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.service.parameterreplacements.hookers.ParameterSetReplacementProviderHooker;
import net.datenwerke.rs.core.service.reportmanager.hooks.ParameterSetReplacementProviderHook;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class BaseParameterReplacementStartup {

	@Inject
	public BaseParameterReplacementStartup(
		HookHandlerService hookHandler,
		Provider<ParameterSetReplacementProviderHooker> replacementProvider
		){
		
		hookHandler.attachHooker(ParameterSetReplacementProviderHook.class, replacementProvider);
	}
}
