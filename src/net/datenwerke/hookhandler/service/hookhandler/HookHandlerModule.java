package net.datenwerke.hookhandler.service.hookhandler;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerServiceImpl;
import net.datenwerke.hookhandler.shared.hookhandler.annotations.ConcurrentMap;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

public class HookHandlerModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(HookHandlerService.class).to(HookHandlerServiceImpl.class).in(Scopes.SINGLETON);
		bind(Map.class).annotatedWith(ConcurrentMap.class).to(ConcurrentHashMap.class);
	}
}
