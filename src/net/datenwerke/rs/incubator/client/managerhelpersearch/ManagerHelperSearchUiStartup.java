package net.datenwerke.rs.incubator.client.managerhelpersearch;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.hookers.BaseTreeComponentsForTreeNavToolbarHooker;
import net.datenwerke.gf.client.managerhelper.hooks.ManagerHelperTreeToolbarEnhancerHook;
import net.datenwerke.gf.client.managerhelper.hooks.TreeSelectionToolbarEnhancerHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.incubator.client.managerhelpersearch.hookers.ManagerHelperSearchBar;
import net.datenwerke.rs.incubator.client.managerhelpersearch.hookers.TreeSelectionSearchBar;

public class ManagerHelperSearchUiStartup {

   @Inject
   public ManagerHelperSearchUiStartup(HookHandlerService hookHandler,

         Provider<ManagerHelperSearchBar> managerHelperSearcher, Provider<TreeSelectionSearchBar> treeSelectionSearcher,
         Provider<BaseTreeComponentsForTreeNavToolbarHooker> baseTreeComponentsHooker) {
      hookHandler.attachHooker(ManagerHelperTreeToolbarEnhancerHook.class, managerHelperSearcher);
      hookHandler.attachHooker(TreeSelectionToolbarEnhancerHook.class, treeSelectionSearcher);
      hookHandler.attachHooker(TreeSelectionToolbarEnhancerHook.class, baseTreeComponentsHooker);
   }
}
