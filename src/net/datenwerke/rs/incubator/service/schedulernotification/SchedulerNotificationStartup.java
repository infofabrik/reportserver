package net.datenwerke.rs.incubator.service.schedulernotification;

import javax.inject.Inject;

import net.datenwerke.gf.service.lateinit.LateInitHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.scheduler.service.scheduler.SchedulerModule;
import net.datenwerke.scheduler.service.scheduler.hooks.SchedulerExecutionHook;
import net.datenwerke.scheduler.service.scheduler.hooks.SchedulerInfoHook;

import com.google.inject.Provider;

public class SchedulerNotificationStartup {

	@Inject
	public SchedulerNotificationStartup(
		final HookHandlerService hookHandler,
		final ConfigService configService,
		final Provider<SchedulerNotificationHooker> notificationHooker
		) {

		hookHandler.attachHooker(LateInitHook.class, new LateInitHook() {
			
			@Override
			public void initialize() {
				if(configService.getConfigFailsafe(SchedulerModule.CONFIG_FILE).getBoolean(SchedulerNotificationModule.NOTIFICATION_ENABLE_PROPERTY,true)){
					hookHandler.attachHooker(SchedulerExecutionHook.class, notificationHooker);
					hookHandler.attachHooker(SchedulerInfoHook.class, notificationHooker);
				}
			}
		});
	}
}
