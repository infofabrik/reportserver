package net.datenwerke.rs.terminal.service.terminal.objresolver;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalSessionDeamonHook;
import net.datenwerke.rs.terminal.service.terminal.objresolver.baseresolver.HqlResolver;
import net.datenwerke.rs.terminal.service.terminal.objresolver.baseresolver.IdResolver;
import net.datenwerke.rs.terminal.service.terminal.objresolver.hooks.ObjectResolverHook;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class ObjectResolverStartup {

	@Inject
	public ObjectResolverStartup(	
		HookHandlerService hookHandler,
		
		Provider<ObjectResolverDeamon> objectResolverDeamonProvider,
		
		Provider<IdResolver> idResolverProvider,
		Provider<HqlResolver> hqlResolverProvider
		
		){
		
		hookHandler.attachHooker(TerminalSessionDeamonHook.class, objectResolverDeamonProvider);
		
		/* resolver */
		hookHandler.attachHooker(ObjectResolverHook.class, idResolverProvider);
		hookHandler.attachHooker(ObjectResolverHook.class, hqlResolverProvider);
	}
}
