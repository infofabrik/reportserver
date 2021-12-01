package net.datenwerke.rs.incubator.service.schedulernotification;

import com.google.inject.AbstractModule;

public class SchedulerNotificationModule extends AbstractModule {

	public static String NOTIFICATION_ENABLE_PROPERTY = "scheduler.notification.enable";
	
	@Override
	protected void configure() {
		bind(SchedulerNotificationStartup.class).asEagerSingleton();
	}

}
