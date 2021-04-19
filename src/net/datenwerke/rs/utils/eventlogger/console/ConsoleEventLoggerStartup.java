package net.datenwerke.rs.utils.eventlogger.console;

import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.rs.utils.eventlogger.eventbus.LoggedEvent;

import com.google.inject.Inject;

public class ConsoleEventLoggerStartup {

	@Inject
	public ConsoleEventLoggerStartup(
		EventBus eventBus,
		
		ConsoleEventHandler eventHandler
		){
		
		eventBus.attachEventHandler(LoggedEvent.class, eventHandler);
	}
}
