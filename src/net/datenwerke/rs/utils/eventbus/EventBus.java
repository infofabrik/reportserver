package net.datenwerke.rs.utils.eventbus;

import com.google.inject.ImplementedBy;

@ImplementedBy(EventBusImpl.class)
public interface EventBus {

   void fireEvent(Event event);

   public <E extends Event> void attachEventHandler(Class<E> eventType, EventHandler<E> handler);

   public <E extends Event> boolean detachEventHandler(Class<E> eventType, EventHandler<E> handler);

   public <E extends ObjectEvent> void attachObjectEventHandler(Class<E> eventType, Class<?> objectType,
         EventHandler<E> handler);

   <E extends ObjectEvent> void detachObjectEventHandler(Class<E> eventType, Class<?> objectType,
         EventHandler<E> handler);
}
