package net.datenwerke.gxtdto.client.eventbus.handlers.has;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;

public interface HasObjectChangedEventHandler<T> extends HasHandlers {

	/**
	 * listen to changes of this object.
	 * 
	 * @param handler
	 */
	public HandlerRegistration addObjectChangedHandler(ObjectChangedEventHandler<T> handler);
	
	/**
	 * listen to changes of this particular instance.
	 * S
	 * @param handler
	 */
	public HandlerRegistration addInstanceChangedHandler(ObjectChangedEventHandler<T> handler);
}
