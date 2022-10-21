package net.datenwerke.rs.base.client.parameters.datasource.dto;

import com.google.gwt.core.client.GWT;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.ChangeMonitoredList;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.MonitoredCollection;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.eventbus.handlers.has.HasObjectChangedEventHandler;
import net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDataDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.pa.DatasourceParameterInstanceDtoPA;
import net.datenwerke.rs.base.client.parameters.datasource.dto.posomap.DatasourceParameterInstanceDto2PosoMap;
import net.datenwerke.rs.base.service.parameters.datasource.DatasourceParameterInstance;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;

/**
 * Dto for {@link DatasourceParameterInstance}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class DatasourceParameterInstanceDto extends ParameterInstanceDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private List<DatasourceParameterDataDto> multiValue;
	private  boolean multiValue_m;
	public static final String PROPERTY_MULTI_VALUE = "dpi-datasourceparameterinstance-multivalue";

	private transient static PropertyAccessor<DatasourceParameterInstanceDto, List<DatasourceParameterDataDto>> multiValue_pa = new PropertyAccessor<DatasourceParameterInstanceDto, List<DatasourceParameterDataDto>>() {
		@Override
		public void setValue(DatasourceParameterInstanceDto container, List<DatasourceParameterDataDto> object) {
			container.setMultiValue(object);
		}

		@Override
		public List<DatasourceParameterDataDto> getValue(DatasourceParameterInstanceDto container) {
			return container.getMultiValue();
		}

		@Override
		public Class<?> getType() {
			return List.class;
		}

		@Override
		public String getPath() {
			return "multiValue";
		}

		@Override
		public void setModified(DatasourceParameterInstanceDto container, boolean modified) {
			container.multiValue_m = modified;
		}

		@Override
		public boolean isModified(DatasourceParameterInstanceDto container) {
			return container.isMultiValueModified();
		}
	};

	private DatasourceParameterDataDto singleValue;
	private  boolean singleValue_m;
	public static final String PROPERTY_SINGLE_VALUE = "dpi-datasourceparameterinstance-singlevalue";

	private transient static PropertyAccessor<DatasourceParameterInstanceDto, DatasourceParameterDataDto> singleValue_pa = new PropertyAccessor<DatasourceParameterInstanceDto, DatasourceParameterDataDto>() {
		@Override
		public void setValue(DatasourceParameterInstanceDto container, DatasourceParameterDataDto object) {
			container.setSingleValue(object);
		}

		@Override
		public DatasourceParameterDataDto getValue(DatasourceParameterInstanceDto container) {
			return container.getSingleValue();
		}

		@Override
		public Class<?> getType() {
			return DatasourceParameterDataDto.class;
		}

		@Override
		public String getPath() {
			return "singleValue";
		}

		@Override
		public void setModified(DatasourceParameterInstanceDto container, boolean modified) {
			container.singleValue_m = modified;
		}

		@Override
		public boolean isModified(DatasourceParameterInstanceDto container) {
			return container.isSingleValueModified();
		}
	};


	public DatasourceParameterInstanceDto() {
		super();
	}

	public List<DatasourceParameterDataDto> getMultiValue()  {
		if(! isDtoProxy()){
			List<DatasourceParameterDataDto> _currentValue = this.multiValue;
			if(null == _currentValue)
				this.multiValue = new ArrayList<DatasourceParameterDataDto>();

			return this.multiValue;
		}

		if(isMultiValueModified())
			return this.multiValue;

		if(! GWT.isClient())
			return null;

		List<DatasourceParameterDataDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().multiValue());

		_value = new ChangeMonitoredList<DatasourceParameterDataDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isMultiValueModified())
						setMultiValue((List<DatasourceParameterDataDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setMultiValue(List<DatasourceParameterDataDto> multiValue)  {
		/* old value */
		List<DatasourceParameterDataDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getMultiValue();

		/* set new value */
		this.multiValue = multiValue;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(multiValue_pa, oldValue, multiValue, this.multiValue_m));

		/* set indicator */
		this.multiValue_m = true;

		this.fireObjectChangedEvent(DatasourceParameterInstanceDtoPA.INSTANCE.multiValue(), oldValue);
	}


	public boolean isMultiValueModified()  {
		return multiValue_m;
	}


	public static PropertyAccessor<DatasourceParameterInstanceDto, List<DatasourceParameterDataDto>> getMultiValuePropertyAccessor()  {
		return multiValue_pa;
	}


	public DatasourceParameterDataDto getSingleValue()  {
		if(! isDtoProxy()){
			return this.singleValue;
		}

		if(isSingleValueModified())
			return this.singleValue;

		if(! GWT.isClient())
			return null;

		DatasourceParameterDataDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().singleValue());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isSingleValueModified())
						setSingleValue((DatasourceParameterDataDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setSingleValue(DatasourceParameterDataDto singleValue)  {
		/* old value */
		DatasourceParameterDataDto oldValue = null;
		if(GWT.isClient())
			oldValue = getSingleValue();

		/* set new value */
		this.singleValue = singleValue;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(singleValue_pa, oldValue, singleValue, this.singleValue_m));

		/* set indicator */
		this.singleValue_m = true;

		this.fireObjectChangedEvent(DatasourceParameterInstanceDtoPA.INSTANCE.singleValue(), oldValue);
	}


	public boolean isSingleValueModified()  {
		return singleValue_m;
	}


	public static PropertyAccessor<DatasourceParameterInstanceDto, DatasourceParameterDataDto> getSingleValuePropertyAccessor()  {
		return singleValue_pa;
	}


	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof DatasourceParameterInstanceDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((DatasourceParameterInstanceDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new DatasourceParameterInstanceDto2PosoMap();
	}

	public DatasourceParameterInstanceDtoPA instantiatePropertyAccess()  {
		return GWT.create(DatasourceParameterInstanceDtoPA.class);
	}

	public void clearModified()  {
		this.multiValue = null;
		this.multiValue_m = false;
		this.singleValue = null;
		this.singleValue_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(multiValue_m)
			return true;
		if(singleValue_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(multiValue_pa);
		list.add(singleValue_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(multiValue_m)
			list.add(multiValue_pa);
		if(singleValue_m)
			list.add(singleValue_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(multiValue_pa);
			list.add(singleValue_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(multiValue_pa);
		list.add(singleValue_pa);
		return list;
	}



	net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDataDto wl_0;

}
