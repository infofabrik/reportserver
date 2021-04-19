package net.datenwerke.rs.incubator.service.notify;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

import com.google.inject.Inject;

public class NotifyStartup {

	@Inject
	public NotifyStartup(
		HookHandlerService hookHandler
//		SendMessageOnScheduleHooker messageOnSchedule,
//		SendMessageOnScheduleFailure messageOnScheduleFailure
		){
		
//		hookHandler.attachHooker(ExecuteJobListenerHook.class, messageOnScheduleFailure);
//		hookHandler.attachHooker(ReportScheduleNotificationHook.class, messageOnSchedule);
	}
}
