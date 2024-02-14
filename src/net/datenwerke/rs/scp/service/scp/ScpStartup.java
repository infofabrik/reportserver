package net.datenwerke.rs.scp.service.scp;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.upload.hooks.FileUploadHandlerHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.datasinkmanager.hookers.factory.DatasinkDefaultMergeHookerFactory;
import net.datenwerke.rs.core.service.datasinkmanager.hooks.DatasinkProviderHook;
import net.datenwerke.rs.core.service.datasinkmanager.hooks.UsageStatisticsDatasinkEntryProviderHook;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.ScheduleConfigProviderHook;
import net.datenwerke.rs.scp.service.scp.definitions.ScpDatasink;
import net.datenwerke.rs.scp.service.scp.hooker.ScheduleAsScpFileEmailNotificationHooker;
import net.datenwerke.rs.scp.service.scp.hooker.ScheduleConfigAsScpFileHooker;
import net.datenwerke.rs.scp.service.scp.hooker.ScpDatasinkProviderHooker;
import net.datenwerke.rs.scp.service.scp.hooker.ScpPrivateKeyUploadHooker;
import net.datenwerke.rs.scp.service.scp.hooker.UsageStatisticsProviderHooker;
import net.datenwerke.rs.utils.entitymerge.service.hooks.EntityMergeHook;
import net.datenwerke.scheduler.service.scheduler.hooks.SchedulerExecutionHook;

public class ScpStartup {
   @Inject
   public ScpStartup(
         final HookHandlerService hookHandler, 
         final Provider<ScpDatasinkProviderHooker> scpDatasinkProvider,
         final Provider<ScheduleConfigAsScpFileHooker> scheduleAsScpFileConfigHooker,
         final Provider<ScheduleAsScpFileEmailNotificationHooker> emailScpNotificationHooker,

         final Provider<ScpPrivateKeyUploadHooker> scpPrivateKeyUploadHooker,
         
         final Provider<UsageStatisticsProviderHooker> usageStats,
         
         final Provider<DatasinkDefaultMergeHookerFactory> datasinkFactory
         ) {

      hookHandler.attachHooker(FileUploadHandlerHook.class, scpPrivateKeyUploadHooker);

      hookHandler.attachHooker(DatasinkProviderHook.class, scpDatasinkProvider);
      hookHandler.attachHooker(ScheduleConfigProviderHook.class, scheduleAsScpFileConfigHooker);
      hookHandler.attachHooker(SchedulerExecutionHook.class, emailScpNotificationHooker);
      
      /* entity merge */
      hookHandler.attachHooker(EntityMergeHook.class, datasinkFactory.get().create(ScpDatasink.class));
      
      hookHandler.attachHooker(UsageStatisticsDatasinkEntryProviderHook.class, usageStats,
            HookHandlerService.PRIORITY_MEDIUM + 55);

   }

}
