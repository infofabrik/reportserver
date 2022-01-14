package net.datenwerke.gxtdto.client.eventbus.handlers;

import com.google.gwt.event.shared.EventHandler;

import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;

public interface ObjectChangedEventHandler<T> extends EventHandler {

   void onObjectChangedEvent(ObjectChangedEvent<T> event);
}
