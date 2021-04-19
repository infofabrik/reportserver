package net.datenwerke.rs.uservariables.client.uservariables.dto;

import com.google.gwt.core.client.GWT;
import java.lang.IllegalStateException;
import java.lang.Long;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.IdedDto;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.eventbus.handlers.has.HasObjectChangedEventHandler;
import net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableDefinitionDto;
import net.datenwerke.rs.uservariables.client.uservariables.dto.pa.UserVariableInstanceDtoPA;
import net.datenwerke.rs.uservariables.client.uservariables.dto.posomap.UserVariableInstanceDto2PosoMap;
import net.datenwerke.rs.uservariables.service.uservariables.entities.UserVariableInstance;
import net.datenwerke.security.client.usermanager.dto.AbstractUserManagerNodeDto;

/**
 * Dto for {@link UserVariableInstance}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class UserVariableInstanceDto extends RsDto implements IdedDto {


	private static final long serialVersionUID = 1;

	private Long dtoId;


	/* Fields */
	private UserVariableDefinitionDto definition;
	private  boolean definition_m;
	public static final String PROPERTY_DEFINITION = "dpi-uservariableinstance-definition";

	private transient static PropertyAccessor<UserVariableInstanceDto, UserVariableDefinitionDto> definition_pa = new PropertyAccessor<UserVariableInstanceDto, UserVariableDefinitionDto>() {
		@Override
		public void setValue(UserVariableInstanceDto container, UserVariableDefinitionDto object) {
			container.setDefinition(object);
		}

		@Override
		public UserVariableDefinitionDto getValue(UserVariableInstanceDto container) {
			return container.getDefinition();
		}

		@Override
		public Class<?> getType() {
			return UserVariableDefinitionDto.class;
		}

		@Override
		public String getPath() {
			return "definition";
		}

		@Override
		public void setModified(UserVariableInstanceDto container, boolean modified) {
			container.definition_m = modified;
		}

		@Override
		public boolean isModified(UserVariableInstanceDto container) {
			return container.isDefinitionModified();
		}
	};

	private AbstractUserManagerNodeDto folk;
	private  boolean folk_m;
	public static final String PROPERTY_FOLK = "dpi-uservariableinstance-folk";

	private transient static PropertyAccessor<UserVariableInstanceDto, AbstractUserManagerNodeDto> folk_pa = new PropertyAccessor<UserVariableInstanceDto, AbstractUserManagerNodeDto>() {
		@Override
		public void setValue(UserVariableInstanceDto container, AbstractUserManagerNodeDto object) {
			container.setFolk(object);
		}

		@Override
		public AbstractUserManagerNodeDto getValue(UserVariableInstanceDto container) {
			return container.getFolk();
		}

		@Override
		public Class<?> getType() {
			return AbstractUserManagerNodeDto.class;
		}

		@Override
		public String getPath() {
			return "folk";
		}

		@Override
		public void setModified(UserVariableInstanceDto container, boolean modified) {
			container.folk_m = modified;
		}

		@Override
		public boolean isModified(UserVariableInstanceDto container) {
			return container.isFolkModified();
		}
	};

	private Long id;
	private  boolean id_m;
	public static final String PROPERTY_ID = "dpi-uservariableinstance-id";

	private transient static PropertyAccessor<UserVariableInstanceDto, Long> id_pa = new PropertyAccessor<UserVariableInstanceDto, Long>() {
		@Override
		public void setValue(UserVariableInstanceDto container, Long object) {
			// id field
		}

		@Override
		public Long getValue(UserVariableInstanceDto container) {
			return container.getId();
		}

		@Override
		public Class<?> getType() {
			return Long.class;
		}

		@Override
		public String getPath() {
			return "id";
		}

		@Override
		public void setModified(UserVariableInstanceDto container, boolean modified) {
			container.id_m = modified;
		}

		@Override
		public boolean isModified(UserVariableInstanceDto container) {
			return container.isIdModified();
		}
	};


	public UserVariableInstanceDto() {
		super();
	}

	public UserVariableDefinitionDto getDefinition()  {
		if(! isDtoProxy()){
			return this.definition;
		}

		if(isDefinitionModified())
			return this.definition;

		if(! GWT.isClient())
			return null;

		UserVariableDefinitionDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().definition());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isDefinitionModified())
						setDefinition((UserVariableDefinitionDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setDefinition(UserVariableDefinitionDto definition)  {
		/* old value */
		UserVariableDefinitionDto oldValue = null;
		if(GWT.isClient())
			oldValue = getDefinition();

		/* set new value */
		this.definition = definition;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(definition_pa, oldValue, definition, this.definition_m));

		/* set indicator */
		this.definition_m = true;

		this.fireObjectChangedEvent(UserVariableInstanceDtoPA.INSTANCE.definition(), oldValue);
	}


	public boolean isDefinitionModified()  {
		return definition_m;
	}


	public static PropertyAccessor<UserVariableInstanceDto, UserVariableDefinitionDto> getDefinitionPropertyAccessor()  {
		return definition_pa;
	}


	public AbstractUserManagerNodeDto getFolk()  {
		if(! isDtoProxy()){
			return this.folk;
		}

		if(isFolkModified())
			return this.folk;

		if(! GWT.isClient())
			return null;

		AbstractUserManagerNodeDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().folk());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isFolkModified())
						setFolk((AbstractUserManagerNodeDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setFolk(AbstractUserManagerNodeDto folk)  {
		/* old value */
		AbstractUserManagerNodeDto oldValue = null;
		if(GWT.isClient())
			oldValue = getFolk();

		/* set new value */
		this.folk = folk;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(folk_pa, oldValue, folk, this.folk_m));

		/* set indicator */
		this.folk_m = true;

		this.fireObjectChangedEvent(UserVariableInstanceDtoPA.INSTANCE.folk(), oldValue);
	}


	public boolean isFolkModified()  {
		return folk_m;
	}


	public static PropertyAccessor<UserVariableInstanceDto, AbstractUserManagerNodeDto> getFolkPropertyAccessor()  {
		return folk_pa;
	}


	public final Long getId()  {
		return dtoId;
	}

	public final void setId(Long id)  {
		if (null != dtoId)
			throw new IllegalStateException("Id already set!");
		this.dtoId = id;
	}

	public boolean isIdModified()  {
		return id_m;
	}


	public static PropertyAccessor<UserVariableInstanceDto, Long> getIdPropertyAccessor()  {
		return id_pa;
	}


	@Override
	public void setDtoId(Object id)  {
		setId((Long) id);
	}

	@Override
	public Object getDtoId()  {
		return getId();
	}

	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof UserVariableInstanceDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((UserVariableInstanceDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new UserVariableInstanceDto2PosoMap();
	}

	public UserVariableInstanceDtoPA instantiatePropertyAccess()  {
		return GWT.create(UserVariableInstanceDtoPA.class);
	}

	public void clearModified()  {
		this.definition = null;
		this.definition_m = false;
		this.folk = null;
		this.folk_m = false;
		this.id = null;
		this.id_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(definition_m)
			return true;
		if(folk_m)
			return true;
		if(id_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(definition_pa);
		list.add(folk_pa);
		list.add(id_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(definition_m)
			list.add(definition_pa);
		if(folk_m)
			list.add(folk_pa);
		if(id_m)
			list.add(id_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(id_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(definition_pa);
			list.add(folk_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(definition_pa);
		list.add(folk_pa);
		return list;
	}



	net.datenwerke.security.client.usermanager.dto.AbstractUserManagerNodeDto wl_0;
	net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableDefinitionDto wl_1;

}
