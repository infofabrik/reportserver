package net.datenwerke.rs.box.service.box;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.box.service.box.definitions.BoxDatasink;
import net.datenwerke.rs.box.service.box.hooker.BoxDatasinkProviderHooker;
import net.datenwerke.rs.box.service.box.hooker.BoxRefreshTokenGeneratorHooker;
import net.datenwerke.rs.box.service.box.hooker.ScheduleAsBoxFileEmailNotificationHooker;
import net.datenwerke.rs.box.service.box.hooker.ScheduleConfigAsBoxDatasinkHooker;
import net.datenwerke.rs.box.service.box.hooker.UsageStatisticsProviderHooker;
import net.datenwerke.rs.core.service.datasinkmanager.hookers.factory.DatasinkDefaultMergeHookerFactory;
import net.datenwerke.rs.core.service.datasinkmanager.hooks.DatasinkProviderHook;
import net.datenwerke.rs.core.service.datasinkmanager.hooks.UsageStatisticsDatasinkEntryProviderHook;
import net.datenwerke.rs.oauth.server.oauth.hooks.OAuthRefreshTokenGeneratorHook;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.ScheduleConfigProviderHook;
import net.datenwerke.rs.utils.entitymerge.service.hooks.EntityMergeHook;
import net.datenwerke.scheduler.service.scheduler.hooks.SchedulerExecutionHook;

public class BoxStartup {

   @Inject
   public BoxStartup(
         final HookHandlerService hookHandler, 
         final Provider<BoxDatasinkProviderHooker> boxDatasinkProviderHooker,
         final Provider<ScheduleAsBoxFileEmailNotificationHooker> emailBoxNotificationHooker,
         final Provider<ScheduleConfigAsBoxDatasinkHooker> scheduleAsBoxConfigHooker,
         final Provider<BoxRefreshTokenGeneratorHooker> boxRefreshTokenGeneratorHooker,
         
         final Provider<UsageStatisticsProviderHooker> usageStats,
         final Provider<DatasinkDefaultMergeHookerFactory> datasinkFactory
         ) {
      hookHandler.attachHooker(DatasinkProviderHook.class, boxDatasinkProviderHooker);
      hookHandler.attachHooker(SchedulerExecutionHook.class, emailBoxNotificationHooker);
      hookHandler.attachHooker(ScheduleConfigProviderHook.class, scheduleAsBoxConfigHooker);
      hookHandler.attachHooker(OAuthRefreshTokenGeneratorHook.class, boxRefreshTokenGeneratorHooker);
      
      /* entity merge */
      hookHandler.attachHooker(EntityMergeHook.class, datasinkFactory.get().create(BoxDatasink.class));
      
      hookHandler.attachHooker(UsageStatisticsDatasinkEntryProviderHook.class, usageStats,
            HookHandlerService.PRIORITY_MEDIUM + 10);
   }

}