package net.datenwerke.rs.adminutils.service.systemconsole.generalinfo;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.hookers.MainInfoCategoryProviderHooker;
import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.hooks.GeneralInfoCategoryProviderHook;

public class GeneralInfoStartup {

   @Inject
   public GeneralInfoStartup(
         HookHandlerService hookHandlerService,
         Provider<MainInfoCategoryProviderHooker> generalInfoMainCategoryProviderHooker
         ) {

            hookHandlerService.attachHooker(GeneralInfoCategoryProviderHook.class,
                  generalInfoMainCategoryProviderHooker, HookHandlerService.PRIORITY_LOW);
         }

}
