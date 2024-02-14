package net.datenwerke.rs.emaildatasink.service.emaildatasink;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.datasinkmanager.hookers.factory.DatasinkDefaultMergeHookerFactory;
import net.datenwerke.rs.core.service.datasinkmanager.hooks.DatasinkProviderHook;
import net.datenwerke.rs.core.service.datasinkmanager.hooks.UsageStatisticsDatasinkEntryProviderHook;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions.EmailDatasink;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.hooker.EmailDatasinkProviderHooker;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.hooker.ScheduleAsEmailDatasinkEmailNotificationHooker;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.hooker.ScheduleConfigAsEmailDatasinkHooker;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.hooker.UsageStatisticsProviderHooker;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.ScheduleConfigProviderHook;
import net.datenwerke.rs.utils.entitymerge.service.hooks.EntityMergeHook;
import net.datenwerke.scheduler.service.scheduler.hooks.SchedulerExecutionHook;

public class EmailDatasinkStartup {

   @Inject
   public EmailDatasinkStartup(
         final HookHandlerService hookHandler,
         final Provider<EmailDatasinkProviderHooker> emailDatasinkProvider,
         final Provider<ScheduleAsEmailDatasinkEmailNotificationHooker> emailNotificationHooker,
         final Provider<ScheduleConfigAsEmailDatasinkHooker> scheduleAsEmailConfigHooker,
         
         final Provider<UsageStatisticsProviderHooker> usageStats,
         final Provider<DatasinkDefaultMergeHookerFactory> datasinkFactory
         ) {
      hookHandler.attachHooker(DatasinkProviderHook.class, emailDatasinkProvider);
      hookHandler.attachHooker(SchedulerExecutionHook.class, emailNotificationHooker);
      hookHandler.attachHooker(ScheduleConfigProviderHook.class, scheduleAsEmailConfigHooker);
      
      /* entity merge */
      hookHandler.attachHooker(EntityMergeHook.class, datasinkFactory.get().create(EmailDatasink.class));
      
      hookHandler.attachHooker(UsageStatisticsDatasinkEntryProviderHook.class, usageStats,
            HookHandlerService.PRIORITY_MEDIUM + 15);
   }

}