package net.datenwerke.hookhandler.shared.hookhandler;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

import net.datenwerke.hookhandler.shared.hookhandler.annotations.ConcurrentMap;

public class ClientHookHandlerModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(HookHandlerService.class).to(HookHandlerServiceImpl.class).in(Singleton.class);
      bind(Map.class).annotatedWith(ConcurrentMap.class).to(HashMap.class);
   }
}
