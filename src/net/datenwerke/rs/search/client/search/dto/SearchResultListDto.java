package net.datenwerke.rs.search.client.search.dto;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
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
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.ChangeMonitoredList;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.ChangeMonitoredSet;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.MonitoredCollection;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.eventbus.handlers.has.HasObjectChangedEventHandler;
import net.datenwerke.rs.search.client.search.dto.SearchResultEntryDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultTagDto;
import net.datenwerke.rs.search.client.search.dto.pa.SearchResultListDtoPA;
import net.datenwerke.rs.search.client.search.dto.posomap.SearchResultListDto2PosoMap;
import net.datenwerke.rs.search.service.search.results.SearchResultList;

/**
 * Dto for {@link SearchResultList}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class SearchResultListDto extends RsDto implements PagingLoadResult {


	private static final long serialVersionUID = 1;


	/* Fields */
	private List<SearchResultEntryDto> data;
	private  boolean data_m;
	public static final String PROPERTY_DATA = "dpi-searchresultlist-data";

	private transient static PropertyAccessor<SearchResultListDto, List<SearchResultEntryDto>> data_pa = new PropertyAccessor<SearchResultListDto, List<SearchResultEntryDto>>() {
		@Override
		public void setValue(SearchResultListDto container, List<SearchResultEntryDto> object) {
			container.setData(object);
		}

		@Override
		public List<SearchResultEntryDto> getValue(SearchResultListDto container) {
			return container.getData();
		}

		@Override
		public Class<?> getType() {
			return List.class;
		}

		@Override
		public String getPath() {
			return "data";
		}

		@Override
		public void setModified(SearchResultListDto container, boolean modified) {
			container.data_m = modified;
		}

		@Override
		public boolean isModified(SearchResultListDto container) {
			return container.isDataModified();
		}
	};

	private int offset;
	private  boolean offset_m;
	public static final String PROPERTY_OFFSET = "dpi-searchresultlist-offset";

	private transient static PropertyAccessor<SearchResultListDto, Integer> offset_pa = new PropertyAccessor<SearchResultListDto, Integer>() {
		@Override
		public void setValue(SearchResultListDto container, Integer object) {
			container.setOffset(object);
		}

		@Override
		public Integer getValue(SearchResultListDto container) {
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
		public void setModified(SearchResultListDto container, boolean modified) {
			container.offset_m = modified;
		}

		@Override
		public boolean isModified(SearchResultListDto container) {
			return container.isOffsetModified();
		}
	};

	private Set<SearchResultTagDto> tags;
	private  boolean tags_m;
	public static final String PROPERTY_TAGS = "dpi-searchresultlist-tags";

	private transient static PropertyAccessor<SearchResultListDto, Set<SearchResultTagDto>> tags_pa = new PropertyAccessor<SearchResultListDto, Set<SearchResultTagDto>>() {
		@Override
		public void setValue(SearchResultListDto container, Set<SearchResultTagDto> object) {
			container.setTags(object);
		}

		@Override
		public Set<SearchResultTagDto> getValue(SearchResultListDto container) {
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
		public void setModified(SearchResultListDto container, boolean modified) {
			container.tags_m = modified;
		}

		@Override
		public boolean isModified(SearchResultListDto container) {
			return container.isTagsModified();
		}
	};

	private int totalLength;
	private  boolean totalLength_m;
	public static final String PROPERTY_TOTAL_LENGTH = "dpi-searchresultlist-totallength";

	private transient static PropertyAccessor<SearchResultListDto, Integer> totalLength_pa = new PropertyAccessor<SearchResultListDto, Integer>() {
		@Override
		public void setValue(SearchResultListDto container, Integer object) {
			container.setTotalLength(object);
		}

		@Override
		public Integer getValue(SearchResultListDto container) {
			return container.getTotalLength();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "totalLength";
		}

		@Override
		public void setModified(SearchResultListDto container, boolean modified) {
			container.totalLength_m = modified;
		}

		@Override
		public boolean isModified(SearchResultListDto container) {
			return container.isTotalLengthModified();
		}
	};


	public SearchResultListDto() {
		super();
	}

	public List<SearchResultEntryDto> getData()  {
		if(! isDtoProxy()){
			List<SearchResultEntryDto> _currentValue = this.data;
			if(null == _currentValue)
				this.data = new ArrayList<SearchResultEntryDto>();

			return this.data;
		}

		if(isDataModified())
			return this.data;

		if(! GWT.isClient())
			return null;

		List<SearchResultEntryDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().data());

		_value = new ChangeMonitoredList<SearchResultEntryDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isDataModified())
						setData((List<SearchResultEntryDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setData(List<SearchResultEntryDto> data)  {
		/* old value */
		List<SearchResultEntryDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getData();

		/* set new value */
		this.data = data;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(data_pa, oldValue, data, this.data_m));

		/* set indicator */
		this.data_m = true;

		this.fireObjectChangedEvent(SearchResultListDtoPA.INSTANCE.data(), oldValue);
	}


	public boolean isDataModified()  {
		return data_m;
	}


	public static PropertyAccessor<SearchResultListDto, List<SearchResultEntryDto>> getDataPropertyAccessor()  {
		return data_pa;
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

		this.fireObjectChangedEvent(SearchResultListDtoPA.INSTANCE.offset(), oldValue);
	}


	public boolean isOffsetModified()  {
		return offset_m;
	}


	public static PropertyAccessor<SearchResultListDto, Integer> getOffsetPropertyAccessor()  {
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

		this.fireObjectChangedEvent(SearchResultListDtoPA.INSTANCE.tags(), oldValue);
	}


	public boolean isTagsModified()  {
		return tags_m;
	}


	public static PropertyAccessor<SearchResultListDto, Set<SearchResultTagDto>> getTagsPropertyAccessor()  {
		return tags_pa;
	}


	public int getTotalLength()  {
		if(! isDtoProxy()){
			return this.totalLength;
		}

		if(isTotalLengthModified())
			return this.totalLength;

		if(! GWT.isClient())
			return 0;

		int _value = dtoManager.getProperty(this, instantiatePropertyAccess().totalLength());

		return _value;
	}


	public void setTotalLength(int totalLength)  {
		/* old value */
		int oldValue = 0;
		if(GWT.isClient())
			oldValue = getTotalLength();

		/* set new value */
		this.totalLength = totalLength;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(totalLength_pa, oldValue, totalLength, this.totalLength_m));

		/* set indicator */
		this.totalLength_m = true;

		this.fireObjectChangedEvent(SearchResultListDtoPA.INSTANCE.totalLength(), oldValue);
	}


	public boolean isTotalLengthModified()  {
		return totalLength_m;
	}


	public static PropertyAccessor<SearchResultListDto, Integer> getTotalLengthPropertyAccessor()  {
		return totalLength_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new SearchResultListDto2PosoMap();
	}

	public SearchResultListDtoPA instantiatePropertyAccess()  {
		return GWT.create(SearchResultListDtoPA.class);
	}

	public void clearModified()  {
		this.data = null;
		this.data_m = false;
		this.offset = 0;
		this.offset_m = false;
		this.tags = null;
		this.tags_m = false;
		this.totalLength = 0;
		this.totalLength_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(data_m)
			return true;
		if(offset_m)
			return true;
		if(tags_m)
			return true;
		if(totalLength_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(data_pa);
		list.add(offset_pa);
		list.add(tags_pa);
		list.add(totalLength_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(data_m)
			list.add(data_pa);
		if(offset_m)
			list.add(offset_pa);
		if(tags_m)
			list.add(tags_pa);
		if(totalLength_m)
			list.add(totalLength_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(data_pa);
			list.add(offset_pa);
			list.add(tags_pa);
			list.add(totalLength_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(data_pa);
		list.add(tags_pa);
		return list;
	}



	net.datenwerke.rs.search.client.search.dto.SearchResultEntryDto wl_0;
	net.datenwerke.rs.search.client.search.dto.SearchResultTagDto wl_1;

}
