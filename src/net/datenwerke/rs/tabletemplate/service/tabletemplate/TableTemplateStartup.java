package net.datenwerke.rs.tabletemplate.service.tabletemplate;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.hooks.GeneralInfoCategoryProviderHook;
import net.datenwerke.rs.tabletemplate.service.tabletemplate.hookers.TemplateCategoryProviderHooker;

public class TableTemplateStartup {

   @Inject
   public TableTemplateStartup(
         final HookHandlerService hookHandlerService,
         final Provider<TemplateCategoryProviderHooker> usageStatistics
         ) {

      hookHandlerService.attachHooker(GeneralInfoCategoryProviderHook.class, usageStatistics,
            HookHandlerService.PRIORITY_LOW + 75);
   }
}
