package net.datenwerke.rs.tabledatasink.service.tabledatasink;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.datasinkmanager.hookers.factory.DatasinkDefaultMergeHookerFactory;
import net.datenwerke.rs.core.service.datasinkmanager.hooks.DatasinkProviderHook;
import net.datenwerke.rs.core.service.datasinkmanager.hooks.UsageStatisticsDatasinkEntryProviderHook;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.ScheduleConfigProviderHook;
import net.datenwerke.rs.tabledatasink.service.tabledatasink.definitions.TableDatasink;
import net.datenwerke.rs.tabledatasink.service.tabledatasink.hooker.ScheduleAsTableDatasinkFileEmailNotificationHooker;
import net.datenwerke.rs.tabledatasink.service.tabledatasink.hooker.ScheduleConfigAsTableDatasinkHooker;
import net.datenwerke.rs.tabledatasink.service.tabledatasink.hooker.TableDatasinkProviderHooker;
import net.datenwerke.rs.tabledatasink.service.tabledatasink.hooker.UsageStatisticsProviderHooker;
import net.datenwerke.rs.utils.entitymerge.service.hooks.EntityMergeHook;
import net.datenwerke.scheduler.service.scheduler.hooks.SchedulerExecutionHook;

public class TableDatasinkStartup {

   @Inject
   public TableDatasinkStartup(
         final HookHandlerService hookHandler,
         final Provider<TableDatasinkProviderHooker> tableDatasinkProviderHooker,
         final Provider<ScheduleAsTableDatasinkFileEmailNotificationHooker> emailTableDatasinkNotificationHooker,
         final Provider<ScheduleConfigAsTableDatasinkHooker> scheduleAsTableDatasinkConfigHooker,
         final Provider<UsageStatisticsProviderHooker> usageStats,
         final Provider<DatasinkDefaultMergeHookerFactory> datasinkFactory
         ) {
      hookHandler.attachHooker(DatasinkProviderHook.class, tableDatasinkProviderHooker);
      hookHandler.attachHooker(SchedulerExecutionHook.class, emailTableDatasinkNotificationHooker);
      hookHandler.attachHooker(ScheduleConfigProviderHook.class, scheduleAsTableDatasinkConfigHooker);
      
      /* entity merge */
      hookHandler.attachHooker(EntityMergeHook.class, datasinkFactory.get().create(TableDatasink.class));
      
      hookHandler.attachHooker(UsageStatisticsDatasinkEntryProviderHook.class, usageStats,
            HookHandlerService.PRIORITY_MEDIUM + 70);
   }

}
