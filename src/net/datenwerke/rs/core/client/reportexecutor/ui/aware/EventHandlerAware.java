package net.datenwerke.rs.core.client.reportexecutor.ui.aware;

import net.datenwerke.rs.core.client.reportexecutor.events.ExecutorEventHandler;

public interface EventHandlerAware {

	public void makeAwareOfEventHandler(ExecutorEventHandler eventHandler);
}
