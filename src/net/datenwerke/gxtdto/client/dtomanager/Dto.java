package net.datenwerke.gxtdto.client.dtomanager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.datenwerke.gxtdto.client.dtomanager.dtomod.ObjectModificationsTracked;
import net.datenwerke.gxtdto.client.dtomanager.events.BeforeDtoDetachedEvent;
import net.datenwerke.gxtdto.client.dtomanager.events.DtoListener;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ListOfChanges;
import net.datenwerke.gxtdto.client.eventbus.EventBusHelper;
import net.datenwerke.gxtdto.client.eventbus.events.InstanceContainer;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.model.DwModel;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.NonFatalException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.UnloggableException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ValidationFailedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ViolatedSecurityExceptionDto;
import net.datenwerke.gxtdto.client.utils.StringEscapeUtils;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.sencha.gxt.core.client.ValueProvider;

/**
 * 
 *
 */
abstract public class Dto implements Serializable, ObjectModificationsTracked<Dto>, HasValueProviderByPath<Dto>, DwModel {

	public static final String GENERIC_CHANGE_EVENT_PROPERTY = "__xx__generic_property";
	
	transient protected ClientDtoManagerService dtoManager;
	
	transient boolean _silent = false;
	
	/* whitelist serializables */
	private ServerCallFailedException serverCallFailedException; 
	private NonFatalException nonFatalException;
	private ExpectedException expectedException;
	private ViolatedSecurityExceptionDto violatedSecurityExceptionDto;
	private ValidationFailedException validationFailedException;
	private UnloggableException unloggableException;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5000527499050649775L;

	/**
	 * Stores the DtoView this dto was generated with
	 */
	private DtoView dtoView;
	
	private Long proxyId = null;
	
	/**
	 * Flag: identifying dto as proxy
	 */
	private boolean proxy = false;
	
	transient private ListOfChanges<?> changeList;
	
	transient private InstanceContainer instanceContainer;
	
	public static final ValueProvider<Dto, String> DISPLAY_STRING_VALUE_PROVIDER = new ValueProvider<Dto, String>() {

		@Override
		public String getValue(Dto object) {
			if(null == object)
				return null;
			String title = StringEscapeUtils.unescapeHTML(object.toDisplayTitle());
			return title == null ? BaseMessages.INSTANCE.unknown() : title;
		}

		@Override
		public void setValue(Dto object, String value) {
		}

		@Override
		public String getPath() {
			return "displayTitle";
		}
	};
	
	/**
	 * Dummy constructor
	 */
	public Dto(){
		super();
	}
	
	/**
	 * Sets the {@link DtoView} that was used to generate this Dto.
	 * @param dtoView
	 */
	public void setDtoView(DtoView dtoView) {
		if(null != this.dtoView)
			throw new IllegalStateException("DtoView already set.");
		
		this.dtoView = dtoView;
	}

	/**
	 * Returns the {@link DtoView} that was used to generate this Dto.
	 */
	public DtoView getDtoView() {
		return dtoView;
	}

	public void markAsProxy(ClientDtoManagerService dtoManager, Long proxyId, Object dtoId, DtoView restriction) {
		if(! (this instanceof IdedDto))
			throw new IllegalStateException("This dto cannot be made a proxy.");

		/* id has to be set before marking dto as proxy */
		((IdedDto)this).setDtoId(dtoId);
		this.dtoManager = dtoManager;
		
		setDtoView(restriction);
		this.proxy = true;
		this.proxyId = proxyId;

		/* add detach listener */
		dtoManager.onDtoChange(this, new DtoListener() {
			@Override
			public void beforeDtoDetached(BeforeDtoDetachedEvent event) {
				super.beforeDtoDetached(event);
				
				event.addDtoForDetach(Dto.this);
			}
		});
	}
	
	public boolean isDtoProxy() {
		return proxy;
	}
	
	public Long getDtoProxyId(){
		return proxyId;
	}
	
	/**
	 * Sets the value of the underlying object if dto is a proxy.
	 */
	public void deepSet(ValueProvider valueProvider, Object value){
		// TODO: GXT3
		if(! isDtoProxy()){
			valueProvider.setValue(this, value);
			return;
		}

		if(! GWT.isClient())
			throw new IllegalStateException("We should not get to this point on the server.");
		
		dtoManager.setProperty(this, valueProvider, value);
	}
	

	public void clearModified(){};
	
	public String toDisplayTitle(){
		return toString();
	}
	
	public String toTypeDescription(){
		return BaseMessages.INSTANCE.unknown();
	}
	
	public BaseIcon toIcon(){
		return BaseIcon.FILE;
	}
	
	public HandlerRegistration addInstanceChangedHandler(ObjectChangedEventHandler<Dto> handler){
		if(! GWT.isClient())
			throw new IllegalStateException("only available on client");
		if(null == instanceContainer)
			instanceContainer = new InstanceContainer(this);
		return EventBusHelper.EVENT_BUS.addHandlerToSource(ObjectChangedEvent.TYPE, instanceContainer, handler);
	}
	
	public HandlerRegistration addObjectChangedHandler(ObjectChangedEventHandler<Dto> handler){
		if(! GWT.isClient())
			throw new IllegalStateException("only available on client");
		 return EventBusHelper.EVENT_BUS.addHandlerToSource(ObjectChangedEvent.TYPE, this, handler);
	}
	
	public void fireEvent(GwtEvent<?> event) {
		EventBusHelper.EVENT_BUS.fireEventFromSource(event,this);
	}

	public void fireObjectChangedEvent(ValueProvider vp, Object oldValue){
		if(!_silent){
			try{
				if(vp.getValue(this) != oldValue){
					if(null != instanceContainer)
						ObjectChangedEvent.fire(instanceContainer, this, vp, oldValue);
					ObjectChangedEvent.fire(this, this, vp, oldValue);
				}
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void fireObjectChangedEvent(ValueProvider vp){
		if(!_silent){
			if(null != instanceContainer)
				ObjectChangedEvent.fire(instanceContainer, this, vp);
			ObjectChangedEvent.fire(this, this, vp);
		}
	}
	
	public void fireObjectChangedEvent(){
		if(!_silent){
			if(null != instanceContainer)
				ObjectChangedEvent.fire(instanceContainer, this, null);
			ObjectChangedEvent.fire(this, this, null);
		}
	}
	
	public List<PropertyAccessor> getPropertyAccessors(){
		return new ArrayList<PropertyAccessor>();
	}
	
	public List<PropertyAccessor> getModifiedPropertyAccessors(){
		return new ArrayList<PropertyAccessor>();
	}
	
	public List<PropertyAccessor> getPropertyAccessorsForDtos(){
		return new ArrayList<PropertyAccessor>();
	}
	
	
	public List<PropertyAccessor> getPropertyAccessorsByView(DtoView view){
		return new ArrayList<PropertyAccessor>();
	}
	
	@Override
	public ValueProvider<Dto, ?> getValueProviderByPath(String path) {
		if(null== path)
			return null;
		
		if(path.contains(".")){
			String[] parts = path.split("\\.",1);
			for(PropertyAccessor pa : getPropertyAccessors()){
				if(parts[0].equals(pa.getPath())){
					Object obj = pa.getValue(this);
					if(obj instanceof Dto)
						return ((Dto)obj).getValueProviderByPath(parts[1]);
				}
			}
		} else
			for(PropertyAccessor pa : getPropertyAccessors())
				if(path.equals(pa.getPath()))
					return pa;
		return null;
	}
	
	public void silenceEvents(boolean silent){
		_silent = silent;
	}
	
	public boolean isSilenceEvents(){
		return _silent;
	}
	
	@Override
	public boolean isModified() {
		return false;
	}

	public int trackChanges(){
		if(null != changeList)
			return changeList.size();
		
		changeList = new ListOfChanges(this);
		
		return 0;
	}
	
	public boolean isTrackChanges(){
		return null != changeList;
	}
	
	protected void addChange(ChangeTracker tracker){
		if(null != changeList)
			changeList.add(tracker);
	}
	
	public ListOfChanges rollback(){
		return rollback(0);
	}
	
	public ListOfChanges rollback(int rollBackStep){
		if(null != changeList)
			changeList.undoUpto(rollBackStep);
		
		ListOfChanges changes = changeList.doClone();
		
		if(0 == rollBackStep)
			changeList = null;
		
		return changes;
	}
	
	public ListOfChanges commit(){
		return commit(false);
	}
	
	public ListOfChanges commit(boolean keepOnTracking){
		ListOfChanges changes = changeList;
		changeList = null;
		
		if(keepOnTracking)
			trackChanges();
		
		return changes;
	}
	
	public void undo(){
		undo(1);
	}
	
	public void undo(int steps){
		if(null != changeList)
			changeList.undo(steps);
	}
	
	public void redo(){
		redo(1);
	}
	
	public void redo(int steps){
		if(null != changeList)
			changeList.redo(steps);
	}


}
