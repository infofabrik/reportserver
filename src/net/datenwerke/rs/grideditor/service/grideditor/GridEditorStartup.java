package net.datenwerke.rs.grideditor.service.grideditor;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.service.hooks.UsageStatisticsReportEntryProviderHook;
import net.datenwerke.rs.core.service.reportmanager.hookers.factory.ReportDefaultMergeHookerFactory;
import net.datenwerke.rs.grideditor.service.grideditor.entities.GridEditorReport;
import net.datenwerke.rs.grideditor.service.grideditor.hookers.UsageStatisticsGridEditorProviderHooker;
import net.datenwerke.rs.utils.entitymerge.service.hooks.EntityMergeHook;

public class GridEditorStartup {

   @Inject
   public GridEditorStartup(
         final HookHandlerService hookHandler,

         final Provider<UsageStatisticsGridEditorProviderHooker> usageStatsGridEditorProvider,
         
         final Provider<ReportDefaultMergeHookerFactory> reportFactory

   ) {
      hookHandler.attachHooker(UsageStatisticsReportEntryProviderHook.class, usageStatsGridEditorProvider,
            HookHandlerService.PRIORITY_LOW + 35);
      
      /* entity merge */
      hookHandler.attachHooker(EntityMergeHook.class, reportFactory.get().create(GridEditorReport.class));
   }
}
