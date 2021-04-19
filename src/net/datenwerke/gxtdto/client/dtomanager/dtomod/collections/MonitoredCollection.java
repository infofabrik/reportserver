package net.datenwerke.gxtdto.client.dtomanager.dtomod.collections;

import java.io.Serializable;
import java.util.Collection;

import net.datenwerke.gxtdto.client.dtomanager.dtomod.ObjectModificationsTracked;
import net.datenwerke.gxtdto.client.eventbus.EventBusHelper;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

public class MonitoredCollection<E, C extends Collection<E>> implements ObjectModificationsTracked<C>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2141244711015691087L;

	protected boolean modified = false;
	
	protected C underlyingCollection;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MonitoredCollection(C underlyingCollection){
		super();
		if(null == underlyingCollection)
			throw new IllegalArgumentException();
		
		this.underlyingCollection = underlyingCollection;
		
		for(E object : underlyingCollection){
			if(object instanceof ObjectModificationsTracked){
				((ObjectModificationsTracked)object).addInstanceChangedHandler(new ObjectChangedEventHandler() {
					public void onObjectChangedEvent(ObjectChangedEvent event){
						markModified();
					}
				});
			}
		}
	}
	
	@Override
	public void fireEvent(GwtEvent<?> event) {
		EventBusHelper.EVENT_BUS.fireEventFromSource(event, this);
	}

	@Override
	public boolean isModified() {
		return modified;
	}
	
	protected void markModified() {
		modified = true;
		ObjectChangedEvent.fire((ObjectModificationsTracked)this, this, null);
	}
	
	public C getUnderlyingCollection(){
		return underlyingCollection;
	}

	@Override
	public HandlerRegistration addObjectChangedHandler(
			ObjectChangedEventHandler<C> handler) {
		return EventBusHelper.EVENT_BUS.addHandlerToSource(ObjectChangedEvent.TYPE, this, handler);
		
	}
	
	@Override
	public HandlerRegistration addInstanceChangedHandler(ObjectChangedEventHandler<C> handler){
		return addObjectChangedHandler(handler);
	}
	


}
