package net.datenwerke.rs.core.client.parameters.dto;

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
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.client.parameters.dto.pa.ParameterInstanceDtoPA;
import net.datenwerke.rs.core.client.parameters.dto.posomap.ParameterInstanceDto2PosoMap;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance;

/**
 * Dto for {@link ParameterInstance}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class ParameterInstanceDto extends RsDto implements IdedDto {


	private static final long serialVersionUID = 1;

	private Long dtoId;


	/* Fields */
	private ParameterDefinitionDto definition;
	private  boolean definition_m;
	public static final String PROPERTY_DEFINITION = "dpi-parameterinstance-definition";

	private transient static PropertyAccessor<ParameterInstanceDto, ParameterDefinitionDto> definition_pa = new PropertyAccessor<ParameterInstanceDto, ParameterDefinitionDto>() {
		@Override
		public void setValue(ParameterInstanceDto container, ParameterDefinitionDto object) {
			container.setDefinition(object);
		}

		@Override
		public ParameterDefinitionDto getValue(ParameterInstanceDto container) {
			return container.getDefinition();
		}

		@Override
		public Class<?> getType() {
			return ParameterDefinitionDto.class;
		}

		@Override
		public String getPath() {
			return "definition";
		}

		@Override
		public void setModified(ParameterInstanceDto container, boolean modified) {
			container.definition_m = modified;
		}

		@Override
		public boolean isModified(ParameterInstanceDto container) {
			return container.isDefinitionModified();
		}
	};

	private Long id;
	private  boolean id_m;
	public static final String PROPERTY_ID = "dpi-parameterinstance-id";

	private transient static PropertyAccessor<ParameterInstanceDto, Long> id_pa = new PropertyAccessor<ParameterInstanceDto, Long>() {
		@Override
		public void setValue(ParameterInstanceDto container, Long object) {
			// id field
		}

		@Override
		public Long getValue(ParameterInstanceDto container) {
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
		public void setModified(ParameterInstanceDto container, boolean modified) {
			container.id_m = modified;
		}

		@Override
		public boolean isModified(ParameterInstanceDto container) {
			return container.isIdModified();
		}
	};

	private boolean stillDefault;
	private  boolean stillDefault_m;
	public static final String PROPERTY_STILL_DEFAULT = "dpi-parameterinstance-stilldefault";

	private transient static PropertyAccessor<ParameterInstanceDto, Boolean> stillDefault_pa = new PropertyAccessor<ParameterInstanceDto, Boolean>() {
		@Override
		public void setValue(ParameterInstanceDto container, Boolean object) {
			container.setStillDefault(object);
		}

		@Override
		public Boolean getValue(ParameterInstanceDto container) {
			return container.isStillDefault();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "stillDefault";
		}

		@Override
		public void setModified(ParameterInstanceDto container, boolean modified) {
			container.stillDefault_m = modified;
		}

		@Override
		public boolean isModified(ParameterInstanceDto container) {
			return container.isStillDefaultModified();
		}
	};


	public ParameterInstanceDto() {
		super();
	}

	public ParameterDefinitionDto getDefinition()  {
		if(! isDtoProxy()){
			return this.definition;
		}

		if(isDefinitionModified())
			return this.definition;

		if(! GWT.isClient())
			return null;

		ParameterDefinitionDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().definition());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isDefinitionModified())
						setDefinition((ParameterDefinitionDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setDefinition(ParameterDefinitionDto definition)  {
		/* old value */
		ParameterDefinitionDto oldValue = null;
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

		this.fireObjectChangedEvent(ParameterInstanceDtoPA.INSTANCE.definition(), oldValue);
	}


	public boolean isDefinitionModified()  {
		return definition_m;
	}


	public static PropertyAccessor<ParameterInstanceDto, ParameterDefinitionDto> getDefinitionPropertyAccessor()  {
		return definition_pa;
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


	public static PropertyAccessor<ParameterInstanceDto, Long> getIdPropertyAccessor()  {
		return id_pa;
	}


	public boolean isStillDefault()  {
		if(! isDtoProxy()){
			return this.stillDefault;
		}

		if(isStillDefaultModified())
			return this.stillDefault;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().stillDefault());

		return _value;
	}


	public void setStillDefault(boolean stillDefault)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isStillDefault();

		/* set new value */
		this.stillDefault = stillDefault;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(stillDefault_pa, oldValue, stillDefault, this.stillDefault_m));

		/* set indicator */
		this.stillDefault_m = true;

		this.fireObjectChangedEvent(ParameterInstanceDtoPA.INSTANCE.stillDefault(), oldValue);
	}


	public boolean isStillDefaultModified()  {
		return stillDefault_m;
	}


	public static PropertyAccessor<ParameterInstanceDto, Boolean> getStillDefaultPropertyAccessor()  {
		return stillDefault_pa;
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
		if(! (obj instanceof ParameterInstanceDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((ParameterInstanceDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new ParameterInstanceDto2PosoMap();
	}

	public ParameterInstanceDtoPA instantiatePropertyAccess()  {
		return GWT.create(ParameterInstanceDtoPA.class);
	}

	public void clearModified()  {
		this.definition = null;
		this.definition_m = false;
		this.id = null;
		this.id_m = false;
		this.stillDefault = false;
		this.stillDefault_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(definition_m)
			return true;
		if(id_m)
			return true;
		if(stillDefault_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(definition_pa);
		list.add(id_pa);
		list.add(stillDefault_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(definition_m)
			list.add(definition_pa);
		if(id_m)
			list.add(id_pa);
		if(stillDefault_m)
			list.add(stillDefault_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(definition_pa);
			list.add(id_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(stillDefault_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(definition_pa);
		return list;
	}



	net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto wl_0;

}
