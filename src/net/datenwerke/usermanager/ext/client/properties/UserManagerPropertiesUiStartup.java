package net.datenwerke.usermanager.ext.client.properties;

import com.google.inject.Inject;

import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewProviderHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.usermanager.ext.client.properties.hookers.UserPropertiesViewProviderHooker;

public class UserManagerPropertiesUiStartup {

   @Inject
   public UserManagerPropertiesUiStartup(HookHandlerService hookHandler,

         UserPropertiesViewProviderHooker mainPanelViewProvider) {

      hookHandler.attachHooker(MainPanelViewProviderHook.class, mainPanelViewProvider,
            HookHandlerService.PRIORITY_LOW - 5);

   }
}
