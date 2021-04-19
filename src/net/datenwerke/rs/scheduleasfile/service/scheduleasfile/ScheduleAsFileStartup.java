package net.datenwerke.rs.scheduleasfile.service.scheduleasfile;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.scheduleasfile.service.scheduleasfile.hooker.ScheduleAsFileEmailNotificationHooker;
import net.datenwerke.rs.scheduleasfile.service.scheduleasfile.hooker.ScheduleConfigAsFileHooker;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.ScheduleConfigProviderHook;
import net.datenwerke.scheduler.service.scheduler.hooks.SchedulerExecutionHook;

public class ScheduleAsFileStartup {

	@Inject
	public ScheduleAsFileStartup(
		HookHandlerService hookHandler,
		
		Provider<ScheduleConfigAsFileHooker> scheduleAsFileConfigHooker, 
		Provider<ScheduleAsFileEmailNotificationHooker> emailNotificationHooker
		
	){
		
		hookHandler.attachHooker(ScheduleConfigProviderHook.class, scheduleAsFileConfigHooker);
		
		hookHandler.attachHooker(SchedulerExecutionHook.class, emailNotificationHooker);
	}
}
