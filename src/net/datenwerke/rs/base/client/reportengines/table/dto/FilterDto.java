package net.datenwerke.rs.base.client.reportengines.table.dto;

import com.google.gwt.core.client.GWT;
import java.lang.Boolean;
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
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.ChangeMonitoredList;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.MonitoredCollection;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.eventbus.handlers.has.HasObjectChangedEventHandler;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterRangeDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.pa.FilterDtoPA;
import net.datenwerke.rs.base.client.reportengines.table.dto.posomap.FilterDto2PosoMap;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.Filter;

/**
 * Dto for {@link Filter}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class FilterDto extends RsDto implements IdedDto {


	private static final long serialVersionUID = 1;

	private Long dtoId;


	/* Fields */
	private Boolean caseSensitive;
	private  boolean caseSensitive_m;
	public static final String PROPERTY_CASE_SENSITIVE = "dpi-filter-casesensitive";

	private transient static PropertyAccessor<FilterDto, Boolean> caseSensitive_pa = new PropertyAccessor<FilterDto, Boolean>() {
		@Override
		public void setValue(FilterDto container, Boolean object) {
			container.setCaseSensitive(object);
		}

		@Override
		public Boolean getValue(FilterDto container) {
			return container.isCaseSensitive();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "caseSensitive";
		}

		@Override
		public void setModified(FilterDto container, boolean modified) {
			container.caseSensitive_m = modified;
		}

		@Override
		public boolean isModified(FilterDto container) {
			return container.isCaseSensitiveModified();
		}
	};

	private List<FilterRangeDto> excludeRanges;
	private  boolean excludeRanges_m;
	public static final String PROPERTY_EXCLUDE_RANGES = "dpi-filter-excluderanges";

	private transient static PropertyAccessor<FilterDto, List<FilterRangeDto>> excludeRanges_pa = new PropertyAccessor<FilterDto, List<FilterRangeDto>>() {
		@Override
		public void setValue(FilterDto container, List<FilterRangeDto> object) {
			container.setExcludeRanges(object);
		}

		@Override
		public List<FilterRangeDto> getValue(FilterDto container) {
			return container.getExcludeRanges();
		}

		@Override
		public Class<?> getType() {
			return List.class;
		}

		@Override
		public String getPath() {
			return "excludeRanges";
		}

		@Override
		public void setModified(FilterDto container, boolean modified) {
			container.excludeRanges_m = modified;
		}

		@Override
		public boolean isModified(FilterDto container) {
			return container.isExcludeRangesModified();
		}
	};

	private List<String> excludeValues;
	private  boolean excludeValues_m;
	public static final String PROPERTY_EXCLUDE_VALUES = "dpi-filter-excludevalues";

	private transient static PropertyAccessor<FilterDto, List<String>> excludeValues_pa = new PropertyAccessor<FilterDto, List<String>>() {
		@Override
		public void setValue(FilterDto container, List<String> object) {
			container.setExcludeValues(object);
		}

		@Override
		public List<String> getValue(FilterDto container) {
			return container.getExcludeValues();
		}

		@Override
		public Class<?> getType() {
			return List.class;
		}

		@Override
		public String getPath() {
			return "excludeValues";
		}

		@Override
		public void setModified(FilterDto container, boolean modified) {
			container.excludeValues_m = modified;
		}

		@Override
		public boolean isModified(FilterDto container) {
			return container.isExcludeValuesModified();
		}
	};

	private Long id;
	private  boolean id_m;
	public static final String PROPERTY_ID = "dpi-filter-id";

	private transient static PropertyAccessor<FilterDto, Long> id_pa = new PropertyAccessor<FilterDto, Long>() {
		@Override
		public void setValue(FilterDto container, Long object) {
			// id field
		}

		@Override
		public Long getValue(FilterDto container) {
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
		public void setModified(FilterDto container, boolean modified) {
			container.id_m = modified;
		}

		@Override
		public boolean isModified(FilterDto container) {
			return container.isIdModified();
		}
	};

	private List<FilterRangeDto> includeRanges;
	private  boolean includeRanges_m;
	public static final String PROPERTY_INCLUDE_RANGES = "dpi-filter-includeranges";

	private transient static PropertyAccessor<FilterDto, List<FilterRangeDto>> includeRanges_pa = new PropertyAccessor<FilterDto, List<FilterRangeDto>>() {
		@Override
		public void setValue(FilterDto container, List<FilterRangeDto> object) {
			container.setIncludeRanges(object);
		}

		@Override
		public List<FilterRangeDto> getValue(FilterDto container) {
			return container.getIncludeRanges();
		}

		@Override
		public Class<?> getType() {
			return List.class;
		}

		@Override
		public String getPath() {
			return "includeRanges";
		}

		@Override
		public void setModified(FilterDto container, boolean modified) {
			container.includeRanges_m = modified;
		}

		@Override
		public boolean isModified(FilterDto container) {
			return container.isIncludeRangesModified();
		}
	};

	private List<String> includeValues;
	private  boolean includeValues_m;
	public static final String PROPERTY_INCLUDE_VALUES = "dpi-filter-includevalues";

	private transient static PropertyAccessor<FilterDto, List<String>> includeValues_pa = new PropertyAccessor<FilterDto, List<String>>() {
		@Override
		public void setValue(FilterDto container, List<String> object) {
			container.setIncludeValues(object);
		}

		@Override
		public List<String> getValue(FilterDto container) {
			return container.getIncludeValues();
		}

		@Override
		public Class<?> getType() {
			return List.class;
		}

		@Override
		public String getPath() {
			return "includeValues";
		}

		@Override
		public void setModified(FilterDto container, boolean modified) {
			container.includeValues_m = modified;
		}

		@Override
		public boolean isModified(FilterDto container) {
			return container.isIncludeValuesModified();
		}
	};


	public FilterDto() {
		super();
	}

	public Boolean isCaseSensitive()  {
		if(! isDtoProxy()){
			return this.caseSensitive;
		}

		if(isCaseSensitiveModified())
			return this.caseSensitive;

		if(! GWT.isClient())
			return null;

		Boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().caseSensitive());

		return _value;
	}


	public void setCaseSensitive(Boolean caseSensitive)  {
		/* old value */
		Boolean oldValue = null;
		if(GWT.isClient())
			oldValue = isCaseSensitive();

		/* set new value */
		this.caseSensitive = caseSensitive;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(caseSensitive_pa, oldValue, caseSensitive, this.caseSensitive_m));

		/* set indicator */
		this.caseSensitive_m = true;

		this.fireObjectChangedEvent(FilterDtoPA.INSTANCE.caseSensitive(), oldValue);
	}


	public boolean isCaseSensitiveModified()  {
		return caseSensitive_m;
	}


	public static PropertyAccessor<FilterDto, Boolean> getCaseSensitivePropertyAccessor()  {
		return caseSensitive_pa;
	}


	public List<FilterRangeDto> getExcludeRanges()  {
		if(! isDtoProxy()){
			List<FilterRangeDto> _currentValue = this.excludeRanges;
			if(null == _currentValue)
				this.excludeRanges = new ArrayList<FilterRangeDto>();

			return this.excludeRanges;
		}

		if(isExcludeRangesModified())
			return this.excludeRanges;

		if(! GWT.isClient())
			return null;

		List<FilterRangeDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().excludeRanges());

		_value = new ChangeMonitoredList<FilterRangeDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isExcludeRangesModified())
						setExcludeRanges((List<FilterRangeDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setExcludeRanges(List<FilterRangeDto> excludeRanges)  {
		/* old value */
		List<FilterRangeDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getExcludeRanges();

		/* set new value */
		this.excludeRanges = excludeRanges;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(excludeRanges_pa, oldValue, excludeRanges, this.excludeRanges_m));

		/* set indicator */
		this.excludeRanges_m = true;

		this.fireObjectChangedEvent(FilterDtoPA.INSTANCE.excludeRanges(), oldValue);
	}


	public boolean isExcludeRangesModified()  {
		return excludeRanges_m;
	}


	public static PropertyAccessor<FilterDto, List<FilterRangeDto>> getExcludeRangesPropertyAccessor()  {
		return excludeRanges_pa;
	}


	public List<String> getExcludeValues()  {
		if(! isDtoProxy()){
			List<String> _currentValue = this.excludeValues;
			if(null == _currentValue)
				this.excludeValues = new ArrayList<String>();

			return this.excludeValues;
		}

		if(isExcludeValuesModified())
			return this.excludeValues;

		if(! GWT.isClient())
			return null;

		List<String> _value = dtoManager.getProperty(this, instantiatePropertyAccess().excludeValues());

		return _value;
	}


	public void setExcludeValues(List<String> excludeValues)  {
		/* old value */
		List<String> oldValue = null;
		if(GWT.isClient())
			oldValue = getExcludeValues();

		/* set new value */
		this.excludeValues = excludeValues;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(excludeValues_pa, oldValue, excludeValues, this.excludeValues_m));

		/* set indicator */
		this.excludeValues_m = true;

		this.fireObjectChangedEvent(FilterDtoPA.INSTANCE.excludeValues(), oldValue);
	}


	public boolean isExcludeValuesModified()  {
		return excludeValues_m;
	}


	public static PropertyAccessor<FilterDto, List<String>> getExcludeValuesPropertyAccessor()  {
		return excludeValues_pa;
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


	public static PropertyAccessor<FilterDto, Long> getIdPropertyAccessor()  {
		return id_pa;
	}


	public List<FilterRangeDto> getIncludeRanges()  {
		if(! isDtoProxy()){
			List<FilterRangeDto> _currentValue = this.includeRanges;
			if(null == _currentValue)
				this.includeRanges = new ArrayList<FilterRangeDto>();

			return this.includeRanges;
		}

		if(isIncludeRangesModified())
			return this.includeRanges;

		if(! GWT.isClient())
			return null;

		List<FilterRangeDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().includeRanges());

		_value = new ChangeMonitoredList<FilterRangeDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isIncludeRangesModified())
						setIncludeRanges((List<FilterRangeDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setIncludeRanges(List<FilterRangeDto> includeRanges)  {
		/* old value */
		List<FilterRangeDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getIncludeRanges();

		/* set new value */
		this.includeRanges = includeRanges;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(includeRanges_pa, oldValue, includeRanges, this.includeRanges_m));

		/* set indicator */
		this.includeRanges_m = true;

		this.fireObjectChangedEvent(FilterDtoPA.INSTANCE.includeRanges(), oldValue);
	}


	public boolean isIncludeRangesModified()  {
		return includeRanges_m;
	}


	public static PropertyAccessor<FilterDto, List<FilterRangeDto>> getIncludeRangesPropertyAccessor()  {
		return includeRanges_pa;
	}


	public List<String> getIncludeValues()  {
		if(! isDtoProxy()){
			List<String> _currentValue = this.includeValues;
			if(null == _currentValue)
				this.includeValues = new ArrayList<String>();

			return this.includeValues;
		}

		if(isIncludeValuesModified())
			return this.includeValues;

		if(! GWT.isClient())
			return null;

		List<String> _value = dtoManager.getProperty(this, instantiatePropertyAccess().includeValues());

		return _value;
	}


	public void setIncludeValues(List<String> includeValues)  {
		/* old value */
		List<String> oldValue = null;
		if(GWT.isClient())
			oldValue = getIncludeValues();

		/* set new value */
		this.includeValues = includeValues;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(includeValues_pa, oldValue, includeValues, this.includeValues_m));

		/* set indicator */
		this.includeValues_m = true;

		this.fireObjectChangedEvent(FilterDtoPA.INSTANCE.includeValues(), oldValue);
	}


	public boolean isIncludeValuesModified()  {
		return includeValues_m;
	}


	public static PropertyAccessor<FilterDto, List<String>> getIncludeValuesPropertyAccessor()  {
		return includeValues_pa;
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
		if(! (obj instanceof FilterDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((FilterDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new FilterDto2PosoMap();
	}

	public FilterDtoPA instantiatePropertyAccess()  {
		return GWT.create(FilterDtoPA.class);
	}

	public void clearModified()  {
		this.caseSensitive = null;
		this.caseSensitive_m = false;
		this.excludeRanges = null;
		this.excludeRanges_m = false;
		this.excludeValues = null;
		this.excludeValues_m = false;
		this.id = null;
		this.id_m = false;
		this.includeRanges = null;
		this.includeRanges_m = false;
		this.includeValues = null;
		this.includeValues_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(caseSensitive_m)
			return true;
		if(excludeRanges_m)
			return true;
		if(excludeValues_m)
			return true;
		if(id_m)
			return true;
		if(includeRanges_m)
			return true;
		if(includeValues_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(caseSensitive_pa);
		list.add(excludeRanges_pa);
		list.add(excludeValues_pa);
		list.add(id_pa);
		list.add(includeRanges_pa);
		list.add(includeValues_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(caseSensitive_m)
			list.add(caseSensitive_pa);
		if(excludeRanges_m)
			list.add(excludeRanges_pa);
		if(excludeValues_m)
			list.add(excludeValues_pa);
		if(id_m)
			list.add(id_pa);
		if(includeRanges_m)
			list.add(includeRanges_pa);
		if(includeValues_m)
			list.add(includeValues_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(id_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(caseSensitive_pa);
			list.add(excludeRanges_pa);
			list.add(excludeValues_pa);
			list.add(includeRanges_pa);
			list.add(includeValues_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(excludeRanges_pa);
		list.add(includeRanges_pa);
		return list;
	}



	net.datenwerke.rs.base.client.reportengines.table.dto.FilterRangeDto wl_0;

}
