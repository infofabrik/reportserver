package net.datenwerke.rs.grideditor.service.grideditor;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.service.hooks.UsageStatisticsReportEntryProviderHook;
import net.datenwerke.rs.grideditor.service.grideditor.hookers.UsageStatisticsGridEditorProviderHooker;

public class GridEditorStartup {

   @Inject
   public GridEditorStartup(
         final HookHandlerService hookHandler,

         final Provider<UsageStatisticsGridEditorProviderHooker> usageStatsGridEditorProvider

   ) {
      hookHandler.attachHooker(UsageStatisticsReportEntryProviderHook.class, usageStatsGridEditorProvider,
            HookHandlerService.PRIORITY_LOW + 35);
   }
}
