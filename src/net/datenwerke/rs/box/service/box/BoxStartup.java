package net.datenwerke.rs.box.service.box;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.box.service.box.hooker.BoxDatasinkProviderHooker;
import net.datenwerke.rs.box.service.box.hooker.BoxRefreshTokenGeneratorHooker;
import net.datenwerke.rs.box.service.box.hooker.ScheduleAsBoxFileEmailNotificationHooker;
import net.datenwerke.rs.box.service.box.hooker.ScheduleConfigAsBoxDatasinkHooker;
import net.datenwerke.rs.core.service.datasinkmanager.hooks.DatasinkProviderHook;
import net.datenwerke.rs.oauth.server.oauth.hooks.OAuthRefreshTokenGeneratorHook;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.ScheduleConfigProviderHook;
import net.datenwerke.scheduler.service.scheduler.hooks.SchedulerExecutionHook;

public class BoxStartup {

   @Inject
   public BoxStartup(HookHandlerService hookHandler, Provider<BoxDatasinkProviderHooker> boxDatasinkProviderHooker,
         Provider<ScheduleAsBoxFileEmailNotificationHooker> emailBoxNotificationHooker,
         Provider<ScheduleConfigAsBoxDatasinkHooker> scheduleAsBoxConfigHooker,
         Provider<BoxRefreshTokenGeneratorHooker> boxRefreshTokenGeneratorHooker) {
      hookHandler.attachHooker(DatasinkProviderHook.class, boxDatasinkProviderHooker);
      hookHandler.attachHooker(SchedulerExecutionHook.class, emailBoxNotificationHooker);
      hookHandler.attachHooker(ScheduleConfigProviderHook.class, scheduleAsBoxConfigHooker);
      hookHandler.attachHooker(OAuthRefreshTokenGeneratorHook.class, boxRefreshTokenGeneratorHooker);
   }

}