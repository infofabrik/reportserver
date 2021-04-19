package net.datenwerke.rs.search.client.search.dto;

import com.google.gwt.core.client.GWT;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.eventbus.handlers.has.HasObjectChangedEventHandler;
import net.datenwerke.rs.search.client.search.dto.SearchResultTagTypeDto;
import net.datenwerke.rs.search.client.search.dto.pa.SearchResultTagDtoPA;
import net.datenwerke.rs.search.client.search.dto.posomap.SearchResultTagDto2PosoMap;
import net.datenwerke.rs.search.service.search.results.SearchResultTag;

/**
 * Dto for {@link SearchResultTag}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class SearchResultTagDto extends RsDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String display;
	private  boolean display_m;
	public static final String PROPERTY_DISPLAY = "dpi-searchresulttag-display";

	private transient static PropertyAccessor<SearchResultTagDto, String> display_pa = new PropertyAccessor<SearchResultTagDto, String>() {
		@Override
		public void setValue(SearchResultTagDto container, String object) {
			container.setDisplay(object);
		}

		@Override
		public String getValue(SearchResultTagDto container) {
			return container.getDisplay();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "display";
		}

		@Override
		public void setModified(SearchResultTagDto container, boolean modified) {
			container.display_m = modified;
		}

		@Override
		public boolean isModified(SearchResultTagDto container) {
			return container.isDisplayModified();
		}
	};

	private SearchResultTagTypeDto type;
	private  boolean type_m;
	public static final String PROPERTY_TYPE = "dpi-searchresulttag-type";

	private transient static PropertyAccessor<SearchResultTagDto, SearchResultTagTypeDto> type_pa = new PropertyAccessor<SearchResultTagDto, SearchResultTagTypeDto>() {
		@Override
		public void setValue(SearchResultTagDto container, SearchResultTagTypeDto object) {
			container.setType(object);
		}

		@Override
		public SearchResultTagTypeDto getValue(SearchResultTagDto container) {
			return container.getType();
		}

		@Override
		public Class<?> getType() {
			return SearchResultTagTypeDto.class;
		}

		@Override
		public String getPath() {
			return "type";
		}

		@Override
		public void setModified(SearchResultTagDto container, boolean modified) {
			container.type_m = modified;
		}

		@Override
		public boolean isModified(SearchResultTagDto container) {
			return container.isTypeModified();
		}
	};

	private String value;
	private  boolean value_m;
	public static final String PROPERTY_VALUE = "dpi-searchresulttag-value";

	private transient static PropertyAccessor<SearchResultTagDto, String> value_pa = new PropertyAccessor<SearchResultTagDto, String>() {
		@Override
		public void setValue(SearchResultTagDto container, String object) {
			container.setValue(object);
		}

		@Override
		public String getValue(SearchResultTagDto container) {
			return container.getValue();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "value";
		}

		@Override
		public void setModified(SearchResultTagDto container, boolean modified) {
			container.value_m = modified;
		}

		@Override
		public boolean isModified(SearchResultTagDto container) {
			return container.isValueModified();
		}
	};


	public SearchResultTagDto() {
		super();
	}

	public String getDisplay()  {
		if(! isDtoProxy()){
			return this.display;
		}

		if(isDisplayModified())
			return this.display;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().display());

		return _value;
	}


	public void setDisplay(String display)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getDisplay();

		/* set new value */
		this.display = display;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(display_pa, oldValue, display, this.display_m));

		/* set indicator */
		this.display_m = true;

		this.fireObjectChangedEvent(SearchResultTagDtoPA.INSTANCE.display(), oldValue);
	}


	public boolean isDisplayModified()  {
		return display_m;
	}


	public static PropertyAccessor<SearchResultTagDto, String> getDisplayPropertyAccessor()  {
		return display_pa;
	}


	public SearchResultTagTypeDto getType()  {
		if(! isDtoProxy()){
			return this.type;
		}

		if(isTypeModified())
			return this.type;

		if(! GWT.isClient())
			return null;

		SearchResultTagTypeDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().type());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isTypeModified())
						setType((SearchResultTagTypeDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setType(SearchResultTagTypeDto type)  {
		/* old value */
		SearchResultTagTypeDto oldValue = null;
		if(GWT.isClient())
			oldValue = getType();

		/* set new value */
		this.type = type;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(type_pa, oldValue, type, this.type_m));

		/* set indicator */
		this.type_m = true;

		this.fireObjectChangedEvent(SearchResultTagDtoPA.INSTANCE.type(), oldValue);
	}


	public boolean isTypeModified()  {
		return type_m;
	}


	public static PropertyAccessor<SearchResultTagDto, SearchResultTagTypeDto> getTypePropertyAccessor()  {
		return type_pa;
	}


	public String getValue()  {
		if(! isDtoProxy()){
			return this.value;
		}

		if(isValueModified())
			return this.value;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().value());

		return _value;
	}


	public void setValue(String value)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getValue();

		/* set new value */
		this.value = value;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(value_pa, oldValue, value, this.value_m));

		/* set indicator */
		this.value_m = true;

		this.fireObjectChangedEvent(SearchResultTagDtoPA.INSTANCE.value(), oldValue);
	}


	public boolean isValueModified()  {
		return value_m;
	}


	public static PropertyAccessor<SearchResultTagDto, String> getValuePropertyAccessor()  {
		return value_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new SearchResultTagDto2PosoMap();
	}

	public SearchResultTagDtoPA instantiatePropertyAccess()  {
		return GWT.create(SearchResultTagDtoPA.class);
	}

	public void clearModified()  {
		this.display = null;
		this.display_m = false;
		this.type = null;
		this.type_m = false;
		this.value = null;
		this.value_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(display_m)
			return true;
		if(type_m)
			return true;
		if(value_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(display_pa);
		list.add(type_pa);
		list.add(value_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(display_m)
			list.add(display_pa);
		if(type_m)
			list.add(type_pa);
		if(value_m)
			list.add(value_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(display_pa);
			list.add(type_pa);
			list.add(value_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(type_pa);
		return list;
	}



	net.datenwerke.rs.search.client.search.dto.SearchResultTagTypeDto wl_0;

}
