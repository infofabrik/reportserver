package net.datenwerke.rs.search.client.search.dto;

import com.google.gwt.core.client.GWT;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.ChangeMonitoredSet;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.MonitoredCollection;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.eventbus.handlers.has.HasObjectChangedEventHandler;
import net.datenwerke.rs.search.client.search.dto.SearchResultTagDto;
import net.datenwerke.rs.search.client.search.dto.pa.SearchFilterDtoPA;
import net.datenwerke.rs.search.client.search.dto.posomap.SearchFilterDto2PosoMap;
import net.datenwerke.rs.search.service.search.results.SearchFilter;

/**
 * Dto for {@link SearchFilter}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class SearchFilterDto extends RsDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private int limit;
	private  boolean limit_m;
	public static final String PROPERTY_LIMIT = "dpi-searchfilter-limit";

	private transient static PropertyAccessor<SearchFilterDto, Integer> limit_pa = new PropertyAccessor<SearchFilterDto, Integer>() {
		@Override
		public void setValue(SearchFilterDto container, Integer object) {
			container.setLimit(object);
		}

		@Override
		public Integer getValue(SearchFilterDto container) {
			return container.getLimit();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "limit";
		}

		@Override
		public void setModified(SearchFilterDto container, boolean modified) {
			container.limit_m = modified;
		}

		@Override
		public boolean isModified(SearchFilterDto container) {
			return container.isLimitModified();
		}
	};

	private int offset;
	private  boolean offset_m;
	public static final String PROPERTY_OFFSET = "dpi-searchfilter-offset";

	private transient static PropertyAccessor<SearchFilterDto, Integer> offset_pa = new PropertyAccessor<SearchFilterDto, Integer>() {
		@Override
		public void setValue(SearchFilterDto container, Integer object) {
			container.setOffset(object);
		}

		@Override
		public Integer getValue(SearchFilterDto container) {
			return container.getOffset();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "offset";
		}

		@Override
		public void setModified(SearchFilterDto container, boolean modified) {
			container.offset_m = modified;
		}

		@Override
		public boolean isModified(SearchFilterDto container) {
			return container.isOffsetModified();
		}
	};

	private Set<SearchResultTagDto> tags;
	private  boolean tags_m;
	public static final String PROPERTY_TAGS = "dpi-searchfilter-tags";

	private transient static PropertyAccessor<SearchFilterDto, Set<SearchResultTagDto>> tags_pa = new PropertyAccessor<SearchFilterDto, Set<SearchResultTagDto>>() {
		@Override
		public void setValue(SearchFilterDto container, Set<SearchResultTagDto> object) {
			container.setTags(object);
		}

		@Override
		public Set<SearchResultTagDto> getValue(SearchFilterDto container) {
			return container.getTags();
		}

		@Override
		public Class<?> getType() {
			return Set.class;
		}

		@Override
		public String getPath() {
			return "tags";
		}

		@Override
		public void setModified(SearchFilterDto container, boolean modified) {
			container.tags_m = modified;
		}

		@Override
		public boolean isModified(SearchFilterDto container) {
			return container.isTagsModified();
		}
	};

	private Dto baseType;
	private  boolean baseType_m;
	public static final String PROPERTY_BASE_TYPE = "dpi-searchfilter-basetype";

	private transient static PropertyAccessor<SearchFilterDto, Dto> baseType_pa = new PropertyAccessor<SearchFilterDto, Dto>() {
		@Override
		public void setValue(SearchFilterDto container, Dto object) {
			container.setBaseType(object);
		}

		@Override
		public Dto getValue(SearchFilterDto container) {
			return container.getBaseType();
		}

		@Override
		public Class<?> getType() {
			return Dto.class;
		}

		@Override
		public String getPath() {
			return "baseType";
		}

		@Override
		public void setModified(SearchFilterDto container, boolean modified) {
			container.baseType_m = modified;
		}

		@Override
		public boolean isModified(SearchFilterDto container) {
			return container.isBaseTypeModified();
		}
	};


	public SearchFilterDto() {
		super();
	}

	public int getLimit()  {
		if(! isDtoProxy()){
			return this.limit;
		}

		if(isLimitModified())
			return this.limit;

		if(! GWT.isClient())
			return 0;

		int _value = dtoManager.getProperty(this, instantiatePropertyAccess().limit());

		return _value;
	}


	public void setLimit(int limit)  {
		/* old value */
		int oldValue = 0;
		if(GWT.isClient())
			oldValue = getLimit();

		/* set new value */
		this.limit = limit;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(limit_pa, oldValue, limit, this.limit_m));

		/* set indicator */
		this.limit_m = true;

		this.fireObjectChangedEvent(SearchFilterDtoPA.INSTANCE.limit(), oldValue);
	}


	public boolean isLimitModified()  {
		return limit_m;
	}


	public static PropertyAccessor<SearchFilterDto, Integer> getLimitPropertyAccessor()  {
		return limit_pa;
	}


	public int getOffset()  {
		if(! isDtoProxy()){
			return this.offset;
		}

		if(isOffsetModified())
			return this.offset;

		if(! GWT.isClient())
			return 0;

		int _value = dtoManager.getProperty(this, instantiatePropertyAccess().offset());

		return _value;
	}


	public void setOffset(int offset)  {
		/* old value */
		int oldValue = 0;
		if(GWT.isClient())
			oldValue = getOffset();

		/* set new value */
		this.offset = offset;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(offset_pa, oldValue, offset, this.offset_m));

		/* set indicator */
		this.offset_m = true;

		this.fireObjectChangedEvent(SearchFilterDtoPA.INSTANCE.offset(), oldValue);
	}


	public boolean isOffsetModified()  {
		return offset_m;
	}


	public static PropertyAccessor<SearchFilterDto, Integer> getOffsetPropertyAccessor()  {
		return offset_pa;
	}


	public Set<SearchResultTagDto> getTags()  {
		if(! isDtoProxy()){
			Set<SearchResultTagDto> _currentValue = this.tags;
			if(null == _currentValue)
				this.tags = new HashSet<SearchResultTagDto>();

			return this.tags;
		}

		if(isTagsModified())
			return this.tags;

		if(! GWT.isClient())
			return null;

		Set<SearchResultTagDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().tags());

		_value = new ChangeMonitoredSet<SearchResultTagDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isTagsModified())
						setTags((Set<SearchResultTagDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setTags(Set<SearchResultTagDto> tags)  {
		/* old value */
		Set<SearchResultTagDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getTags();

		/* set new value */
		this.tags = tags;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(tags_pa, oldValue, tags, this.tags_m));

		/* set indicator */
		this.tags_m = true;

		this.fireObjectChangedEvent(SearchFilterDtoPA.INSTANCE.tags(), oldValue);
	}


	public boolean isTagsModified()  {
		return tags_m;
	}


	public static PropertyAccessor<SearchFilterDto, Set<SearchResultTagDto>> getTagsPropertyAccessor()  {
		return tags_pa;
	}


	public Dto getBaseType()  {
		if(! isDtoProxy()){
			return this.baseType;
		}

		if(isBaseTypeModified())
			return this.baseType;

		if(! GWT.isClient())
			return null;

		Dto _value = dtoManager.getProperty(this, instantiatePropertyAccess().baseType());

		return _value;
	}


	public void setBaseType(Dto baseType)  {
		/* old value */
		Dto oldValue = null;
		if(GWT.isClient())
			oldValue = getBaseType();

		/* set new value */
		this.baseType = baseType;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(baseType_pa, oldValue, baseType, this.baseType_m));

		/* set indicator */
		this.baseType_m = true;

		this.fireObjectChangedEvent(SearchFilterDtoPA.INSTANCE.baseType(), oldValue);
	}


	public boolean isBaseTypeModified()  {
		return baseType_m;
	}


	public static PropertyAccessor<SearchFilterDto, Dto> getBaseTypePropertyAccessor()  {
		return baseType_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new SearchFilterDto2PosoMap();
	}

	public SearchFilterDtoPA instantiatePropertyAccess()  {
		return GWT.create(SearchFilterDtoPA.class);
	}

	public void clearModified()  {
		this.limit = 0;
		this.limit_m = false;
		this.offset = 0;
		this.offset_m = false;
		this.tags = null;
		this.tags_m = false;
		this.baseType = null;
		this.baseType_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(limit_m)
			return true;
		if(offset_m)
			return true;
		if(tags_m)
			return true;
		if(baseType_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(limit_pa);
		list.add(offset_pa);
		list.add(tags_pa);
		list.add(baseType_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(limit_m)
			list.add(limit_pa);
		if(offset_m)
			list.add(offset_pa);
		if(tags_m)
			list.add(tags_pa);
		if(baseType_m)
			list.add(baseType_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(baseType_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(limit_pa);
			list.add(offset_pa);
			list.add(tags_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(tags_pa);
		return list;
	}



	net.datenwerke.rs.search.client.search.dto.SearchResultTagDto wl_0;

}
