package net.datenwerke.rs.dropbox.service.dropbox;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.datasinkmanager.hooks.DatasinkProviderHook;
import net.datenwerke.rs.dropbox.service.dropbox.hooker.DropboxDatasinkProviderHooker;
import net.datenwerke.rs.dropbox.service.dropbox.hooker.ScheduleAsDropboxFileEmailNotificationHooker;
import net.datenwerke.rs.dropbox.service.dropbox.hooker.ScheduleConfigAsDropboxDatasinkHooker;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.ScheduleConfigProviderHook;
import net.datenwerke.scheduler.service.scheduler.hooks.SchedulerExecutionHook;

public class DropboxStartup {

	@Inject
	public DropboxStartup(HookHandlerService hookHandler,
			Provider<DropboxDatasinkProviderHooker> dropboxDatasinkProviderHooker,
			Provider<ScheduleAsDropboxFileEmailNotificationHooker> emailDropboxNotificationHooker,
			Provider<ScheduleConfigAsDropboxDatasinkHooker> scheduleAsDropboxConfigHooker) {
		hookHandler.attachHooker(DatasinkProviderHook.class, dropboxDatasinkProviderHooker);
		hookHandler.attachHooker(SchedulerExecutionHook.class, emailDropboxNotificationHooker);
		hookHandler.attachHooker(ScheduleConfigProviderHook.class, scheduleAsDropboxConfigHooker);
	}

}
