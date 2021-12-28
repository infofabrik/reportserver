package net.datenwerke.gxtdto.client.eventbus.events;

import com.google.gwt.event.shared.GwtEvent;
import com.sencha.gxt.core.client.ValueProvider;

import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.eventbus.handlers.has.HasObjectChangedEventHandler;

public class ObjectChangedEvent<T> extends GwtEvent<ObjectChangedEventHandler<T>> {
	
	public static final Type<ObjectChangedEventHandler<?>> TYPE = new Type<ObjectChangedEventHandler<?>>();
	
	private T object;
	
	private ValueProvider<?, ?> valueProvider;

	private Object oldValue;
	
	/**
	 * Constructor.
	 */
	protected ObjectChangedEvent() {
	}
	
	public ObjectChangedEvent(T object, ValueProvider<?, ?> valueProvider, Object oldValue) {
		this.object = object;
		this.valueProvider = valueProvider;
		this.oldValue = oldValue;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ObjectChangedEventHandler<T>> getAssociatedType() {
		return (Type) TYPE;
	}

	@Override
	protected void dispatch(ObjectChangedEventHandler<T> handler) {
		 handler.onObjectChangedEvent(this);
	}

	public static <T> void fire(HasObjectChangedEventHandler<T> source, T object, ValueProvider<?, ?> valueProvider) {
		fire(source, object, valueProvider, null);
	}
	
	public static <T> void fire(HasObjectChangedEventHandler<T> source, T object, ValueProvider<?, ?> valueProvider, Object oldValue) {
		ObjectChangedEvent<T> event = new ObjectChangedEvent<T>(object, valueProvider, oldValue);
		source.fireEvent(event);
	}
	
	public T getObject(){
		return object;
	}
	
	public ValueProvider<?, ?> getValueProvider() {
		return valueProvider;
	}

	public String getPropertyPath() {
		return null != valueProvider ? valueProvider.getPath() : null;
	}
	
	public Object getOldValue() {
		return oldValue;
	}
	
}
