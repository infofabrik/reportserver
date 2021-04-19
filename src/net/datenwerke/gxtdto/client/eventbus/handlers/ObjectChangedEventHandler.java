package net.datenwerke.gxtdto.client.eventbus.handlers;

import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;

import com.google.gwt.event.shared.EventHandler;

public interface ObjectChangedEventHandler<T> extends EventHandler {

	void onObjectChangedEvent(ObjectChangedEvent<T> event);
}
