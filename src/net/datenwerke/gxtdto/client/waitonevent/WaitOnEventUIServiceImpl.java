package net.datenwerke.gxtdto.client.waitonevent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WaitOnEventUIServiceImpl implements WaitOnEventUIService {

	private final Map<String, Set<WaitOnEventTicket>> tickets = new HashMap<String, Set<WaitOnEventTicket>>();
	private final Set<String> currentEvents = new HashSet<String>();
	
	private final Map<String, Set<SynchronousCallbackOnEventTrigger>> eventCallbacks = new HashMap<String, Set<SynchronousCallbackOnEventTrigger>>();
	private final Map<String, List<CallbackOnEventDone>> doneCallbacks = new HashMap<String, List<CallbackOnEventDone>>();
	private final Map<String, Set<SynchronousCallbackOnEventTrigger>> permanentEventCallbacks = new HashMap<String, Set<SynchronousCallbackOnEventTrigger>>();
	private final Map<String, List<CallbackOnEventDone>> permanentDoneCallbacks = new HashMap<String, List<CallbackOnEventDone>>();
	
	
	
	public void callbackOnEvent(String event, SynchronousCallbackOnEventTrigger callback) {
		if(! eventCallbacks.containsKey(event))
			eventCallbacks.put(event, new HashSet<SynchronousCallbackOnEventTrigger>());
		eventCallbacks.get(event).add(callback);
	}
	
	public void permanentCallbackOnEvent(String event, SynchronousCallbackOnEventTrigger callback) {
		if(! permanentEventCallbacks.containsKey(event))
			permanentEventCallbacks.put(event, new HashSet<SynchronousCallbackOnEventTrigger>());
		permanentEventCallbacks.get(event).add(callback);
	}
	
	public void removePermanentCallbackOnEvent(String event, SynchronousCallbackOnEventTrigger callback) {
		if(permanentEventCallbacks.containsKey(event))
			permanentEventCallbacks.get(event).remove(callback);
	}
	
	public void callbackOnFinishingProcessing(String event, CallbackOnEventDone callback) {
		if(! doneCallbacks.containsKey(event))
			doneCallbacks.put(event, new ArrayList<CallbackOnEventDone>());
		
		/* if callback not already in queue, add it to the beginning */
		if(!doneCallbacks.get(event).contains(callback))
			doneCallbacks.get(event).add(0, callback); 
	}
	
	public void permanentCallbackOnFinishingProcessing(String event, CallbackOnEventDone callback) {
		if(! permanentDoneCallbacks.containsKey(event))
			permanentDoneCallbacks.put(event, new ArrayList<CallbackOnEventDone>());
		
		/* if callback not already in queue, add it to the beginning */
		if(!permanentDoneCallbacks.get(event).contains(callback))
			permanentDoneCallbacks.get(event).add(callback);
	}
	
	public void removePermanentCallbackOnFinishingProcessing(String event, CallbackOnEventDone callback) {
		if(permanentDoneCallbacks.containsKey(event))
			permanentDoneCallbacks.get(event).remove(callback);
	}

	public void triggerEvent(String event) {
		callEventCallbacks(event);
		
		testForEventFinish(event);
	}
	
	public void triggerEvent(String event, CallbackOnEventDone callback) {
		callbackOnFinishingProcessing(event, callback);
		triggerEvent(event);
	}

	private void callEventCallbacks(String event) {
		/* mark event as current */
		currentEvents.add(event);
		
		/* prepare ticket set */
		if(! tickets.containsKey(event))
			tickets.put(event, new HashSet<WaitOnEventTicket>());

		/* create a ticket for each callback */
		Set<WaitOnEventTicket> tempTicketList = new HashSet<WaitOnEventTicket>();
		if(eventCallbacks.containsKey(event)){
			for(SynchronousCallbackOnEventTrigger callback : eventCallbacks.get(event)){
				WaitOnEventTicket ticket = new WaitOnEventTicket(event);
				tickets.get(event).add(ticket);
				tempTicketList.add(ticket);
			}
		}
		if(permanentEventCallbacks.containsKey(event)){
			for(SynchronousCallbackOnEventTrigger callback : permanentEventCallbacks.get(event)){
				WaitOnEventTicket ticket = new WaitOnEventTicket(event);
				tickets.get(event).add(ticket);
				tempTicketList.add(ticket);
			}
		}
			
		/* call callbacks */
		Iterator<WaitOnEventTicket> ticketIterator = tempTicketList.iterator();
			
		/* call event callbacks */
		if(eventCallbacks.containsKey(event)){
			Iterator<SynchronousCallbackOnEventTrigger> iterator = eventCallbacks.get(event).iterator();
			while(iterator.hasNext()){
				SynchronousCallbackOnEventTrigger callback = iterator.next();
				callback.execute(ticketIterator.next());
			}
		}

		/* call permanent callbacks */
		if(permanentEventCallbacks.containsKey(event)){
			Iterator<SynchronousCallbackOnEventTrigger> iterator = permanentEventCallbacks.get(event).iterator();
			while(iterator.hasNext()){
				SynchronousCallbackOnEventTrigger callback = iterator.next();
				callback.execute(ticketIterator.next());
			}
		}
		
		if(ticketIterator.hasNext())
			throw new RuntimeException("Illegal state. We have more tickets than callbacks."); //$NON-NLS-1$

		/* cleanup */
		if(eventCallbacks.containsKey(event))
			eventCallbacks.get(event).clear();
	}

	public void signalProcessingDone( WaitOnEventTicket ticket) {
		String event = ticket.getEvent();	
		if(tickets.containsKey(event))
			tickets.get(event).remove(ticket);
	
		testForEventFinish(event);
	}
	
	private void testForEventFinish(String event){
		if(! tickets.containsKey(event) || tickets.get(event).isEmpty())
			triggerEventProcessingDone(event);
	}

	private void triggerEventProcessingDone(String event) {
		/* only trigger processing done, if we have not done so before */
		if(currentEvents.contains(event))
			currentEvents.remove(event);
		else
			return;
		
		/* call temp callbacks */
		if(doneCallbacks.containsKey(event)){
			for(CallbackOnEventDone callback : doneCallbacks.get(event))
				callback.execute();
			
			/* cleanup */
			doneCallbacks.get(event).clear();
		}
		
		/* call permanent callbacks */
		if(permanentDoneCallbacks.containsKey(event))
			for(CallbackOnEventDone callback : permanentDoneCallbacks.get(event))
				callback.execute();
	}

	

}
