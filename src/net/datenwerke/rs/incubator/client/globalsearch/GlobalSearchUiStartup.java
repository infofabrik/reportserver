package net.datenwerke.rs.incubator.client.globalsearch;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.homepage.hooks.HomepageHeaderContentHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.incubator.client.globalsearch.hookers.GlobalSearchHooker;

public class GlobalSearchUiStartup {

   @Inject
   public GlobalSearchUiStartup(HookHandlerService hookHandler, Provider<GlobalSearchHooker> globalSearchHooker) {
      hookHandler.attachHooker(HomepageHeaderContentHook.class, globalSearchHooker, HookHandlerService.PRIORITY_LOW);
   }
}
