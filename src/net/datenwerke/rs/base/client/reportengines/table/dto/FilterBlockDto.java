package net.datenwerke.rs.base.client.reportengines.table.dto;

import com.google.gwt.core.client.GWT;
import java.lang.IllegalStateException;
import java.lang.Long;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.IdedDto;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.ChangeMonitoredSet;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.MonitoredCollection;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.eventbus.handlers.has.HasObjectChangedEventHandler;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterBlockDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterSpecDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.pa.FilterBlockDtoPA;
import net.datenwerke.rs.base.client.reportengines.table.dto.posomap.FilterBlockDto2PosoMap;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.FilterBlock;

/**
 * Dto for {@link FilterBlock}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class FilterBlockDto extends RsDto implements IdedDto {


	private static final long serialVersionUID = 1;

	private Long dtoId;


	/* Fields */
	private Set<FilterBlockDto> childBlocks;
	private  boolean childBlocks_m;
	public static final String PROPERTY_CHILD_BLOCKS = "dpi-filterblock-childblocks";

	private transient static PropertyAccessor<FilterBlockDto, Set<FilterBlockDto>> childBlocks_pa = new PropertyAccessor<FilterBlockDto, Set<FilterBlockDto>>() {
		@Override
		public void setValue(FilterBlockDto container, Set<FilterBlockDto> object) {
			container.setChildBlocks(object);
		}

		@Override
		public Set<FilterBlockDto> getValue(FilterBlockDto container) {
			return container.getChildBlocks();
		}

		@Override
		public Class<?> getType() {
			return Set.class;
		}

		@Override
		public String getPath() {
			return "childBlocks";
		}

		@Override
		public void setModified(FilterBlockDto container, boolean modified) {
			container.childBlocks_m = modified;
		}

		@Override
		public boolean isModified(FilterBlockDto container) {
			return container.isChildBlocksModified();
		}
	};

	private String description;
	private  boolean description_m;
	public static final String PROPERTY_DESCRIPTION = "dpi-filterblock-description";

	private transient static PropertyAccessor<FilterBlockDto, String> description_pa = new PropertyAccessor<FilterBlockDto, String>() {
		@Override
		public void setValue(FilterBlockDto container, String object) {
			container.setDescription(object);
		}

		@Override
		public String getValue(FilterBlockDto container) {
			return container.getDescription();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "description";
		}

		@Override
		public void setModified(FilterBlockDto container, boolean modified) {
			container.description_m = modified;
		}

		@Override
		public boolean isModified(FilterBlockDto container) {
			return container.isDescriptionModified();
		}
	};

	private Set<FilterSpecDto> filters;
	private  boolean filters_m;
	public static final String PROPERTY_FILTERS = "dpi-filterblock-filters";

	private transient static PropertyAccessor<FilterBlockDto, Set<FilterSpecDto>> filters_pa = new PropertyAccessor<FilterBlockDto, Set<FilterSpecDto>>() {
		@Override
		public void setValue(FilterBlockDto container, Set<FilterSpecDto> object) {
			container.setFilters(object);
		}

		@Override
		public Set<FilterSpecDto> getValue(FilterBlockDto container) {
			return container.getFilters();
		}

		@Override
		public Class<?> getType() {
			return Set.class;
		}

		@Override
		public String getPath() {
			return "filters";
		}

		@Override
		public void setModified(FilterBlockDto container, boolean modified) {
			container.filters_m = modified;
		}

		@Override
		public boolean isModified(FilterBlockDto container) {
			return container.isFiltersModified();
		}
	};

	private Long id;
	private  boolean id_m;
	public static final String PROPERTY_ID = "dpi-filterblock-id";

	private transient static PropertyAccessor<FilterBlockDto, Long> id_pa = new PropertyAccessor<FilterBlockDto, Long>() {
		@Override
		public void setValue(FilterBlockDto container, Long object) {
			// id field
		}

		@Override
		public Long getValue(FilterBlockDto container) {
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
		public void setModified(FilterBlockDto container, boolean modified) {
			container.id_m = modified;
		}

		@Override
		public boolean isModified(FilterBlockDto container) {
			return container.isIdModified();
		}
	};


	public FilterBlockDto() {
		super();
	}

	public Set<FilterBlockDto> getChildBlocks()  {
		if(! isDtoProxy()){
			Set<FilterBlockDto> _currentValue = this.childBlocks;
			if(null == _currentValue)
				this.childBlocks = new HashSet<FilterBlockDto>();

			return this.childBlocks;
		}

		if(isChildBlocksModified())
			return this.childBlocks;

		if(! GWT.isClient())
			return null;

		Set<FilterBlockDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().childBlocks());

		_value = new ChangeMonitoredSet<FilterBlockDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isChildBlocksModified())
						setChildBlocks((Set<FilterBlockDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setChildBlocks(Set<FilterBlockDto> childBlocks)  {
		/* old value */
		Set<FilterBlockDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getChildBlocks();

		/* set new value */
		this.childBlocks = childBlocks;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(childBlocks_pa, oldValue, childBlocks, this.childBlocks_m));

		/* set indicator */
		this.childBlocks_m = true;

		this.fireObjectChangedEvent(FilterBlockDtoPA.INSTANCE.childBlocks(), oldValue);
	}


	public boolean isChildBlocksModified()  {
		return childBlocks_m;
	}


	public static PropertyAccessor<FilterBlockDto, Set<FilterBlockDto>> getChildBlocksPropertyAccessor()  {
		return childBlocks_pa;
	}


	public String getDescription()  {
		if(! isDtoProxy()){
			return this.description;
		}

		if(isDescriptionModified())
			return this.description;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().description());

		return _value;
	}


	public void setDescription(String description)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getDescription();

		/* set new value */
		this.description = description;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(description_pa, oldValue, description, this.description_m));

		/* set indicator */
		this.description_m = true;

		this.fireObjectChangedEvent(FilterBlockDtoPA.INSTANCE.description(), oldValue);
	}


	public boolean isDescriptionModified()  {
		return description_m;
	}


	public static PropertyAccessor<FilterBlockDto, String> getDescriptionPropertyAccessor()  {
		return description_pa;
	}


	public Set<FilterSpecDto> getFilters()  {
		if(! isDtoProxy()){
			Set<FilterSpecDto> _currentValue = this.filters;
			if(null == _currentValue)
				this.filters = new HashSet<FilterSpecDto>();

			return this.filters;
		}

		if(isFiltersModified())
			return this.filters;

		if(! GWT.isClient())
			return null;

		Set<FilterSpecDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().filters());

		_value = new ChangeMonitoredSet<FilterSpecDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isFiltersModified())
						setFilters((Set<FilterSpecDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setFilters(Set<FilterSpecDto> filters)  {
		/* old value */
		Set<FilterSpecDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getFilters();

		/* set new value */
		this.filters = filters;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(filters_pa, oldValue, filters, this.filters_m));

		/* set indicator */
		this.filters_m = true;

		this.fireObjectChangedEvent(FilterBlockDtoPA.INSTANCE.filters(), oldValue);
	}


	public boolean isFiltersModified()  {
		return filters_m;
	}


	public static PropertyAccessor<FilterBlockDto, Set<FilterSpecDto>> getFiltersPropertyAccessor()  {
		return filters_pa;
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


	public static PropertyAccessor<FilterBlockDto, Long> getIdPropertyAccessor()  {
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
		if(! (obj instanceof FilterBlockDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((FilterBlockDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new FilterBlockDto2PosoMap();
	}

	public FilterBlockDtoPA instantiatePropertyAccess()  {
		return GWT.create(FilterBlockDtoPA.class);
	}

	public void clearModified()  {
		this.childBlocks = null;
		this.childBlocks_m = false;
		this.description = null;
		this.description_m = false;
		this.filters = null;
		this.filters_m = false;
		this.id = null;
		this.id_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(childBlocks_m)
			return true;
		if(description_m)
			return true;
		if(filters_m)
			return true;
		if(id_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(childBlocks_pa);
		list.add(description_pa);
		list.add(filters_pa);
		list.add(id_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(childBlocks_m)
			list.add(childBlocks_pa);
		if(description_m)
			list.add(description_pa);
		if(filters_m)
			list.add(filters_pa);
		if(id_m)
			list.add(id_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(description_pa);
			list.add(id_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(childBlocks_pa);
			list.add(filters_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(childBlocks_pa);
		list.add(filters_pa);
		return list;
	}



	net.datenwerke.rs.base.client.reportengines.table.dto.FilterSpecDto wl_0;
	net.datenwerke.rs.base.client.reportengines.table.dto.FilterBlockDto wl_1;

}
