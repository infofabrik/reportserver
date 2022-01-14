package net.datenwerke.gf.client.managerhelper;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.hookers.BaseTreeComponentsForTreeNavToolbarHooker;
import net.datenwerke.gf.client.managerhelper.hooks.ManagerHelperTreeToolbarEnhancerHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

public class ManagerHelperUiStartup {

   @Inject
   public ManagerHelperUiStartup(HookHandlerService hookHandler,

         Provider<BaseTreeComponentsForTreeNavToolbarHooker> baseTreeComponentsHooker) {

      hookHandler.attachHooker(ManagerHelperTreeToolbarEnhancerHook.class, baseTreeComponentsHooker);
   }
}
