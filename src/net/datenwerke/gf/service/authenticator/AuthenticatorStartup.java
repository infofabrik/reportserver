package net.datenwerke.gf.service.authenticator;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.authenticator.hookers.GeneralInfoPamCategoryProviderHooker;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.hooks.GeneralInfoCategoryProviderHook;

public class AuthenticatorStartup {

   @Inject
   public AuthenticatorStartup(HookHandlerService hookHandlerService,
         Provider<GeneralInfoPamCategoryProviderHooker> generalInfoPamCategoryProviderHooker
         ) {

      hookHandlerService.attachHooker(GeneralInfoCategoryProviderHook.class, generalInfoPamCategoryProviderHooker,
            HookHandlerService.PRIORITY_LOW + 10);
   }

}
