package net.datenwerke.rs.tabledatasink.service.tabledatasink;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.datasinkmanager.hooks.DatasinkProviderHook;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.ScheduleConfigProviderHook;
import net.datenwerke.rs.tabledatasink.service.tabledatasink.hooker.ScheduleAsTableDatasinkFileEmailNotificationHooker;
import net.datenwerke.rs.tabledatasink.service.tabledatasink.hooker.ScheduleConfigAsTableDatasinkHooker;
import net.datenwerke.rs.tabledatasink.service.tabledatasink.hooker.TableDatasinkProviderHooker;
import net.datenwerke.scheduler.service.scheduler.hooks.SchedulerExecutionHook;

public class TableDatasinkStartup {

   @Inject
   public TableDatasinkStartup(HookHandlerService hookHandler,
         Provider<TableDatasinkProviderHooker> tableDatasinkProviderHooker,
         Provider<ScheduleAsTableDatasinkFileEmailNotificationHooker> emailTableDatasinkNotificationHooker,
         Provider<ScheduleConfigAsTableDatasinkHooker> scheduleAsTableDatasinkConfigHooker) {
      hookHandler.attachHooker(DatasinkProviderHook.class, tableDatasinkProviderHooker);
      hookHandler.attachHooker(SchedulerExecutionHook.class, emailTableDatasinkNotificationHooker);
      hookHandler.attachHooker(ScheduleConfigProviderHook.class, scheduleAsTableDatasinkConfigHooker);
   }

}
