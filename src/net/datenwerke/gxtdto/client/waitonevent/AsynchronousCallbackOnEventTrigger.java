package net.datenwerke.gxtdto.client.waitonevent;

import com.google.inject.Inject;

public abstract class AsynchronousCallbackOnEventTrigger implements SynchronousCallbackOnEventTrigger {
	
	@Inject
	private static WaitOnEventUIService waitOnEventService;
	
	public final void execute(WaitOnEventTicket ticket){
		doExecute();
		waitOnEventService.signalProcessingDone(ticket);
	}

	protected abstract void doExecute();

}
