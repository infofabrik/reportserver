package net.datenwerke.rs.samba.service.samba;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.datasinkmanager.hooks.DatasinkProviderHook;
import net.datenwerke.rs.samba.service.samba.hooker.SambaDatasinkProviderHooker;
import net.datenwerke.rs.samba.service.samba.hooker.ScheduleAsSambaFileEmailNotificationHooker;
import net.datenwerke.rs.samba.service.samba.hooker.ScheduleConfigAsSambaFileHooker;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.ScheduleConfigProviderHook;
import net.datenwerke.scheduler.service.scheduler.hooks.SchedulerExecutionHook;

public class SambaStartup {

   @Inject
   public SambaStartup(HookHandlerService hookHandler,

         Provider<SambaDatasinkProviderHooker> sambaDatasinkProvider,
         Provider<SambaDatasinkProviderHooker> sftpDatasinkProvider,

         Provider<ScheduleConfigAsSambaFileHooker> scheduleAsSambaFileConfigHooker,
         Provider<ScheduleAsSambaFileEmailNotificationHooker> emailSambaNotificationHooker

   ) {

      hookHandler.attachHooker(DatasinkProviderHook.class, sambaDatasinkProvider);
      hookHandler.attachHooker(ScheduleConfigProviderHook.class, scheduleAsSambaFileConfigHooker);
      hookHandler.attachHooker(SchedulerExecutionHook.class, emailSambaNotificationHooker);

   }
}