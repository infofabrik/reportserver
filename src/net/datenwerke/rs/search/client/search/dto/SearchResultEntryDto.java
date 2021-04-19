package net.datenwerke.rs.search.client.search.dto;

import com.google.gwt.core.client.GWT;
import java.lang.IllegalStateException;
import java.lang.Long;
import java.lang.NullPointerException;
import java.lang.String;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.gf.client.history.dto.HistoryLinkDto;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.IdedDto;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.ChangeMonitoredList;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.ChangeMonitoredSet;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.MonitoredCollection;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.eventbus.handlers.has.HasObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.model.DwModel;
import net.datenwerke.rs.search.client.search.dto.SearchResultTagDto;
import net.datenwerke.rs.search.client.search.dto.pa.SearchResultEntryDtoPA;
import net.datenwerke.rs.search.client.search.dto.posomap.SearchResultEntryDto2PosoMap;
import net.datenwerke.rs.search.service.search.results.SearchResultEntry;

/**
 * Dto for {@link SearchResultEntry}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class SearchResultEntryDto extends RsDto implements IdedDto {


	private static final long serialVersionUID = 1;

	private Long dtoId;


	/* Fields */
	private String description;
	private  boolean description_m;
	public static final String PROPERTY_DESCRIPTION = "dpi-searchresultentry-description";

	private transient static PropertyAccessor<SearchResultEntryDto, String> description_pa = new PropertyAccessor<SearchResultEntryDto, String>() {
		@Override
		public void setValue(SearchResultEntryDto container, String object) {
			container.setDescription(object);
		}

		@Override
		public String getValue(SearchResultEntryDto container) {
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
		public void setModified(SearchResultEntryDto container, boolean modified) {
			container.description_m = modified;
		}

		@Override
		public boolean isModified(SearchResultEntryDto container) {
			return container.isDescriptionModified();
		}
	};

	private String iconSmall;
	private  boolean iconSmall_m;
	public static final String PROPERTY_ICON_SMALL = "dpi-searchresultentry-iconsmall";

	private transient static PropertyAccessor<SearchResultEntryDto, String> iconSmall_pa = new PropertyAccessor<SearchResultEntryDto, String>() {
		@Override
		public void setValue(SearchResultEntryDto container, String object) {
			container.setIconSmall(object);
		}

		@Override
		public String getValue(SearchResultEntryDto container) {
			return container.getIconSmall();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "iconSmall";
		}

		@Override
		public void setModified(SearchResultEntryDto container, boolean modified) {
			container.iconSmall_m = modified;
		}

		@Override
		public boolean isModified(SearchResultEntryDto container) {
			return container.isIconSmallModified();
		}
	};

	private Long id;
	private  boolean id_m;
	public static final String PROPERTY_ID = "dpi-searchresultentry-id";

	private transient static PropertyAccessor<SearchResultEntryDto, Long> id_pa = new PropertyAccessor<SearchResultEntryDto, Long>() {
		@Override
		public void setValue(SearchResultEntryDto container, Long object) {
			// id field
		}

		@Override
		public Long getValue(SearchResultEntryDto container) {
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
		public void setModified(SearchResultEntryDto container, boolean modified) {
			container.id_m = modified;
		}

		@Override
		public boolean isModified(SearchResultEntryDto container) {
			return container.isIdModified();
		}
	};

	private Date lastModified;
	private  boolean lastModified_m;
	public static final String PROPERTY_LAST_MODIFIED = "dpi-searchresultentry-lastmodified";

	private transient static PropertyAccessor<SearchResultEntryDto, Date> lastModified_pa = new PropertyAccessor<SearchResultEntryDto, Date>() {
		@Override
		public void setValue(SearchResultEntryDto container, Date object) {
			container.setLastModified(object);
		}

		@Override
		public Date getValue(SearchResultEntryDto container) {
			return container.getLastModified();
		}

		@Override
		public Class<?> getType() {
			return Date.class;
		}

		@Override
		public String getPath() {
			return "lastModified";
		}

		@Override
		public void setModified(SearchResultEntryDto container, boolean modified) {
			container.lastModified_m = modified;
		}

		@Override
		public boolean isModified(SearchResultEntryDto container) {
			return container.isLastModifiedModified();
		}
	};

	private List<HistoryLinkDto> links;
	private  boolean links_m;
	public static final String PROPERTY_LINKS = "dpi-searchresultentry-links";

	private transient static PropertyAccessor<SearchResultEntryDto, List<HistoryLinkDto>> links_pa = new PropertyAccessor<SearchResultEntryDto, List<HistoryLinkDto>>() {
		@Override
		public void setValue(SearchResultEntryDto container, List<HistoryLinkDto> object) {
			container.setLinks(object);
		}

		@Override
		public List<HistoryLinkDto> getValue(SearchResultEntryDto container) {
			return container.getLinks();
		}

		@Override
		public Class<?> getType() {
			return List.class;
		}

		@Override
		public String getPath() {
			return "links";
		}

		@Override
		public void setModified(SearchResultEntryDto container, boolean modified) {
			container.links_m = modified;
		}

		@Override
		public boolean isModified(SearchResultEntryDto container) {
			return container.isLinksModified();
		}
	};

	private Long objectId;
	private  boolean objectId_m;
	public static final String PROPERTY_OBJECT_ID = "dpi-searchresultentry-objectid";

	private transient static PropertyAccessor<SearchResultEntryDto, Long> objectId_pa = new PropertyAccessor<SearchResultEntryDto, Long>() {
		@Override
		public void setValue(SearchResultEntryDto container, Long object) {
			container.setObjectId(object);
		}

		@Override
		public Long getValue(SearchResultEntryDto container) {
			return container.getObjectId();
		}

		@Override
		public Class<?> getType() {
			return Long.class;
		}

		@Override
		public String getPath() {
			return "objectId";
		}

		@Override
		public void setModified(SearchResultEntryDto container, boolean modified) {
			container.objectId_m = modified;
		}

		@Override
		public boolean isModified(SearchResultEntryDto container) {
			return container.isObjectIdModified();
		}
	};

	private Set<SearchResultTagDto> tags;
	private  boolean tags_m;
	public static final String PROPERTY_TAGS = "dpi-searchresultentry-tags";

	private transient static PropertyAccessor<SearchResultEntryDto, Set<SearchResultTagDto>> tags_pa = new PropertyAccessor<SearchResultEntryDto, Set<SearchResultTagDto>>() {
		@Override
		public void setValue(SearchResultEntryDto container, Set<SearchResultTagDto> object) {
			container.setTags(object);
		}

		@Override
		public Set<SearchResultTagDto> getValue(SearchResultEntryDto container) {
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
		public void setModified(SearchResultEntryDto container, boolean modified) {
			container.tags_m = modified;
		}

		@Override
		public boolean isModified(SearchResultEntryDto container) {
			return container.isTagsModified();
		}
	};

	private String title;
	private  boolean title_m;
	public static final String PROPERTY_TITLE = "dpi-searchresultentry-title";

	private transient static PropertyAccessor<SearchResultEntryDto, String> title_pa = new PropertyAccessor<SearchResultEntryDto, String>() {
		@Override
		public void setValue(SearchResultEntryDto container, String object) {
			container.setTitle(object);
		}

		@Override
		public String getValue(SearchResultEntryDto container) {
			return container.getTitle();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "title";
		}

		@Override
		public void setModified(SearchResultEntryDto container, boolean modified) {
			container.title_m = modified;
		}

		@Override
		public boolean isModified(SearchResultEntryDto container) {
			return container.isTitleModified();
		}
	};

	private DwModel resultObject;
	private  boolean resultObject_m;
	public static final String PROPERTY_RESULT_OBJECT = "dpi-searchresultentry-resultobject";

	private transient static PropertyAccessor<SearchResultEntryDto, DwModel> resultObject_pa = new PropertyAccessor<SearchResultEntryDto, DwModel>() {
		@Override
		public void setValue(SearchResultEntryDto container, DwModel object) {
			container.setResultObject(object);
		}

		@Override
		public DwModel getValue(SearchResultEntryDto container) {
			return container.getResultObject();
		}

		@Override
		public Class<?> getType() {
			return DwModel.class;
		}

		@Override
		public String getPath() {
			return "resultObject";
		}

		@Override
		public void setModified(SearchResultEntryDto container, boolean modified) {
			container.resultObject_m = modified;
		}

		@Override
		public boolean isModified(SearchResultEntryDto container) {
			return container.isResultObjectModified();
		}
	};


	public SearchResultEntryDto() {
		super();
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

		this.fireObjectChangedEvent(SearchResultEntryDtoPA.INSTANCE.description(), oldValue);
	}


	public boolean isDescriptionModified()  {
		return description_m;
	}


	public static PropertyAccessor<SearchResultEntryDto, String> getDescriptionPropertyAccessor()  {
		return description_pa;
	}


	public String getIconSmall()  {
		if(! isDtoProxy()){
			return this.iconSmall;
		}

		if(isIconSmallModified())
			return this.iconSmall;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().iconSmall());

		return _value;
	}


	public void setIconSmall(String iconSmall)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getIconSmall();

		/* set new value */
		this.iconSmall = iconSmall;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(iconSmall_pa, oldValue, iconSmall, this.iconSmall_m));

		/* set indicator */
		this.iconSmall_m = true;

		this.fireObjectChangedEvent(SearchResultEntryDtoPA.INSTANCE.iconSmall(), oldValue);
	}


	public boolean isIconSmallModified()  {
		return iconSmall_m;
	}


	public static PropertyAccessor<SearchResultEntryDto, String> getIconSmallPropertyAccessor()  {
		return iconSmall_pa;
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


	public static PropertyAccessor<SearchResultEntryDto, Long> getIdPropertyAccessor()  {
		return id_pa;
	}


	public Date getLastModified()  {
		if(! isDtoProxy()){
			return this.lastModified;
		}

		if(isLastModifiedModified())
			return this.lastModified;

		if(! GWT.isClient())
			return null;

		Date _value = dtoManager.getProperty(this, instantiatePropertyAccess().lastModified());

		return _value;
	}


	public void setLastModified(Date lastModified)  {
		/* old value */
		Date oldValue = null;
		if(GWT.isClient())
			oldValue = getLastModified();

		/* set new value */
		this.lastModified = lastModified;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(lastModified_pa, oldValue, lastModified, this.lastModified_m));

		/* set indicator */
		this.lastModified_m = true;

		this.fireObjectChangedEvent(SearchResultEntryDtoPA.INSTANCE.lastModified(), oldValue);
	}


	public boolean isLastModifiedModified()  {
		return lastModified_m;
	}


	public static PropertyAccessor<SearchResultEntryDto, Date> getLastModifiedPropertyAccessor()  {
		return lastModified_pa;
	}


	public List<HistoryLinkDto> getLinks()  {
		if(! isDtoProxy()){
			List<HistoryLinkDto> _currentValue = this.links;
			if(null == _currentValue)
				this.links = new ArrayList<HistoryLinkDto>();

			return this.links;
		}

		if(isLinksModified())
			return this.links;

		if(! GWT.isClient())
			return null;

		List<HistoryLinkDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().links());

		_value = new ChangeMonitoredList<HistoryLinkDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isLinksModified())
						setLinks((List<HistoryLinkDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setLinks(List<HistoryLinkDto> links)  {
		/* old value */
		List<HistoryLinkDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getLinks();

		/* set new value */
		this.links = links;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(links_pa, oldValue, links, this.links_m));

		/* set indicator */
		this.links_m = true;

		this.fireObjectChangedEvent(SearchResultEntryDtoPA.INSTANCE.links(), oldValue);
	}


	public boolean isLinksModified()  {
		return links_m;
	}


	public static PropertyAccessor<SearchResultEntryDto, List<HistoryLinkDto>> getLinksPropertyAccessor()  {
		return links_pa;
	}


	public Long getObjectId()  {
		if(! isDtoProxy()){
			return this.objectId;
		}

		if(isObjectIdModified())
			return this.objectId;

		if(! GWT.isClient())
			return null;

		Long _value = dtoManager.getProperty(this, instantiatePropertyAccess().objectId());

		return _value;
	}


	public void setObjectId(Long objectId)  {
		/* old value */
		Long oldValue = null;
		if(GWT.isClient())
			oldValue = getObjectId();

		/* set new value */
		this.objectId = objectId;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(objectId_pa, oldValue, objectId, this.objectId_m));

		/* set indicator */
		this.objectId_m = true;

		this.fireObjectChangedEvent(SearchResultEntryDtoPA.INSTANCE.objectId(), oldValue);
	}


	public boolean isObjectIdModified()  {
		return objectId_m;
	}


	public static PropertyAccessor<SearchResultEntryDto, Long> getObjectIdPropertyAccessor()  {
		return objectId_pa;
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

		this.fireObjectChangedEvent(SearchResultEntryDtoPA.INSTANCE.tags(), oldValue);
	}


	public boolean isTagsModified()  {
		return tags_m;
	}


	public static PropertyAccessor<SearchResultEntryDto, Set<SearchResultTagDto>> getTagsPropertyAccessor()  {
		return tags_pa;
	}


	public String getTitle()  {
		if(! isDtoProxy()){
			return this.title;
		}

		if(isTitleModified())
			return this.title;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().title());

		return _value;
	}


	public void setTitle(String title)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getTitle();

		/* set new value */
		this.title = title;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(title_pa, oldValue, title, this.title_m));

		/* set indicator */
		this.title_m = true;

		this.fireObjectChangedEvent(SearchResultEntryDtoPA.INSTANCE.title(), oldValue);
	}


	public boolean isTitleModified()  {
		return title_m;
	}


	public static PropertyAccessor<SearchResultEntryDto, String> getTitlePropertyAccessor()  {
		return title_pa;
	}


	public DwModel getResultObject()  {
		if(! isDtoProxy()){
			return this.resultObject;
		}

		if(isResultObjectModified())
			return this.resultObject;

		if(! GWT.isClient())
			return null;

		DwModel _value = dtoManager.getProperty(this, instantiatePropertyAccess().resultObject());

		return _value;
	}


	public void setResultObject(DwModel resultObject)  {
		/* old value */
		DwModel oldValue = null;
		if(GWT.isClient())
			oldValue = getResultObject();

		/* set new value */
		this.resultObject = resultObject;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(resultObject_pa, oldValue, resultObject, this.resultObject_m));

		/* set indicator */
		this.resultObject_m = true;

		this.fireObjectChangedEvent(SearchResultEntryDtoPA.INSTANCE.resultObject(), oldValue);
	}


	public boolean isResultObjectModified()  {
		return resultObject_m;
	}


	public static PropertyAccessor<SearchResultEntryDto, DwModel> getResultObjectPropertyAccessor()  {
		return resultObject_pa;
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
	public String toDisplayTitle()  {
		try{
			return getTitle();
		} catch(NullPointerException e){
			return BaseMessages.INSTANCE.unnamed();
		}
	}

	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof SearchResultEntryDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((SearchResultEntryDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new SearchResultEntryDto2PosoMap();
	}

	public SearchResultEntryDtoPA instantiatePropertyAccess()  {
		return GWT.create(SearchResultEntryDtoPA.class);
	}

	public void clearModified()  {
		this.description = null;
		this.description_m = false;
		this.iconSmall = null;
		this.iconSmall_m = false;
		this.id = null;
		this.id_m = false;
		this.lastModified = null;
		this.lastModified_m = false;
		this.links = null;
		this.links_m = false;
		this.objectId = null;
		this.objectId_m = false;
		this.tags = null;
		this.tags_m = false;
		this.title = null;
		this.title_m = false;
		this.resultObject = null;
		this.resultObject_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(description_m)
			return true;
		if(iconSmall_m)
			return true;
		if(id_m)
			return true;
		if(lastModified_m)
			return true;
		if(links_m)
			return true;
		if(objectId_m)
			return true;
		if(tags_m)
			return true;
		if(title_m)
			return true;
		if(resultObject_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(description_pa);
		list.add(iconSmall_pa);
		list.add(id_pa);
		list.add(lastModified_pa);
		list.add(links_pa);
		list.add(objectId_pa);
		list.add(tags_pa);
		list.add(title_pa);
		list.add(resultObject_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(description_m)
			list.add(description_pa);
		if(iconSmall_m)
			list.add(iconSmall_pa);
		if(id_m)
			list.add(id_pa);
		if(lastModified_m)
			list.add(lastModified_pa);
		if(links_m)
			list.add(links_pa);
		if(objectId_m)
			list.add(objectId_pa);
		if(tags_m)
			list.add(tags_pa);
		if(title_m)
			list.add(title_pa);
		if(resultObject_m)
			list.add(resultObject_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(id_pa);
			list.add(resultObject_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(description_pa);
			list.add(iconSmall_pa);
			list.add(lastModified_pa);
			list.add(links_pa);
			list.add(objectId_pa);
			list.add(tags_pa);
			list.add(title_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(links_pa);
		list.add(tags_pa);
		return list;
	}



	net.datenwerke.gf.client.history.dto.HistoryLinkDto wl_0;
	net.datenwerke.rs.search.client.search.dto.SearchResultTagDto wl_1;

}
