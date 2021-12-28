package net.datenwerke.gxtdto.client.eventbus.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

import net.datenwerke.gxtdto.client.eventbus.EventBusHelper;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.eventbus.handlers.has.HasObjectChangedEventHandler;

public final class InstanceContainer<T> implements HasObjectChangedEventHandler<T> {

   private final T instance;

   public InstanceContainer(T instance) {
      this.instance = instance;
   }

   public T getInstance() {
      return instance;
   }

   @Override
   public void fireEvent(GwtEvent<?> event) {
      EventBusHelper.EVENT_BUS.fireEventFromSource(event, this);
   }

   @Override
   public HandlerRegistration addObjectChangedHandler(ObjectChangedEventHandler<T> handler) {
      throw new IllegalStateException("not implemented");
   }

   @Override
   public HandlerRegistration addInstanceChangedHandler(ObjectChangedEventHandler<T> handler) {
      throw new IllegalStateException("not implemented");
   };
}