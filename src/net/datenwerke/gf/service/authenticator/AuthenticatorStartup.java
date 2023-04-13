package net.datenwerke.gf.service.authenticator;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.authenticator.hookers.PamCategoryProviderHooker;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.hooks.GeneralInfoCategoryProviderHook;

public class AuthenticatorStartup {

   @Inject
   public AuthenticatorStartup(HookHandlerService hookHandlerService,
         Provider<PamCategoryProviderHooker> generalInfoPamCategoryProviderHooker
         ) {

      hookHandlerService.attachHooker(GeneralInfoCategoryProviderHook.class, generalInfoPamCategoryProviderHooker,
            HookHandlerService.PRIORITY_LOW + 10);
   }

}
