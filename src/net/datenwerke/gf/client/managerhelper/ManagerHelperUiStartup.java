package net.datenwerke.gf.client.managerhelper;

import net.datenwerke.gf.client.managerhelper.hookers.BaseTreeComponentsForTreeNavToolbarHooker;
import net.datenwerke.gf.client.managerhelper.hooks.ManagerHelperTreeToolbarEnhancerHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class ManagerHelperUiStartup {

	@Inject
	public ManagerHelperUiStartup(
		HookHandlerService hookHandler,
		
		Provider<BaseTreeComponentsForTreeNavToolbarHooker> baseTreeComponentsHooker
		){
		
		hookHandler.attachHooker(ManagerHelperTreeToolbarEnhancerHook.class, baseTreeComponentsHooker);
	}
}
