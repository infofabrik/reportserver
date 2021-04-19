package net.datenwerke.rs.core.client.reportexecutor.events;

import java.util.ArrayList;
import java.util.List;

public class CompositeExecutorEventHandler implements ExecutorEventHandler {

	private final List<ExecutorEventHandler> handlers;
	
	public CompositeExecutorEventHandler(ExecutorEventHandler... handlers){
		this.handlers = new ArrayList<ExecutorEventHandler>();
		for(ExecutorEventHandler handler:handlers)
			this.handlers.add(handler);
	}
	
	public void addHandler(ExecutorEventHandler handler) {
		handlers.add(handler);
	}
	
	@Override
	public void handleEvent(ReportExecutorEvent event) {
		for(ExecutorEventHandler handler:handlers)
			handler.handleEvent(event);
	}

}
