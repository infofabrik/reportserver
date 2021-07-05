package net.datenwerke.gxtdto.client.waitonevent;

/**
 * 
 *
 */
public interface WaitOnEventUIService {

   /**
    * Callback will be removed after its execution.
    * 
    * @param event
    * @param callback
    */
   public void callbackOnEvent(String event, SynchronousCallbackOnEventTrigger callback);

   /**
    * Callback will be called after every occurance of the event.
    * 
    * @param event
    * @param callback
    */
   public void permanentCallbackOnEvent(String event, SynchronousCallbackOnEventTrigger callback);

   public void removePermanentCallbackOnEvent(String event, SynchronousCallbackOnEventTrigger callback);

   public void callbackOnFinishingProcessing(String event, CallbackOnEventDone callback);

   public void permanentCallbackOnFinishingProcessing(String event, CallbackOnEventDone callback);

   public void removePermanentCallbackOnFinishingProcessing(String event, CallbackOnEventDone callback);

   public void triggerEvent(String event);

   public void triggerEvent(String event, CallbackOnEventDone callback);

   public void signalProcessingDone(WaitOnEventTicket ticket);

}