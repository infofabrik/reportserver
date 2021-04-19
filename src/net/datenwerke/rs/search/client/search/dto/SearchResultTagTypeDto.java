package net.datenwerke.rs.search.client.search.dto;

import com.google.gwt.core.client.GWT;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.rs.search.client.search.dto.pa.SearchResultTagTypeDtoPA;
import net.datenwerke.rs.search.client.search.dto.posomap.SearchResultTagTypeDto2PosoMap;
import net.datenwerke.rs.search.service.search.results.SearchResultTagType;

/**
 * Dto for {@link SearchResultTagType}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class SearchResultTagTypeDto extends RsDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String display;
	private  boolean display_m;
	public static final String PROPERTY_DISPLAY = "dpi-searchresulttagtype-display";

	private transient static PropertyAccessor<SearchResultTagTypeDto, String> display_pa = new PropertyAccessor<SearchResultTagTypeDto, String>() {
		@Override
		public void setValue(SearchResultTagTypeDto container, String object) {
			container.setDisplay(object);
		}

		@Override
		public String getValue(SearchResultTagTypeDto container) {
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
		public void setModified(SearchResultTagTypeDto container, boolean modified) {
			container.display_m = modified;
		}

		@Override
		public boolean isModified(SearchResultTagTypeDto container) {
			return container.isDisplayModified();
		}
	};

	private String type;
	private  boolean type_m;
	public static final String PROPERTY_TYPE = "dpi-searchresulttagtype-type";

	private transient static PropertyAccessor<SearchResultTagTypeDto, String> type_pa = new PropertyAccessor<SearchResultTagTypeDto, String>() {
		@Override
		public void setValue(SearchResultTagTypeDto container, String object) {
			container.setType(object);
		}

		@Override
		public String getValue(SearchResultTagTypeDto container) {
			return container.getType();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "type";
		}

		@Override
		public void setModified(SearchResultTagTypeDto container, boolean modified) {
			container.type_m = modified;
		}

		@Override
		public boolean isModified(SearchResultTagTypeDto container) {
			return container.isTypeModified();
		}
	};


	public SearchResultTagTypeDto() {
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

		this.fireObjectChangedEvent(SearchResultTagTypeDtoPA.INSTANCE.display(), oldValue);
	}


	public boolean isDisplayModified()  {
		return display_m;
	}


	public static PropertyAccessor<SearchResultTagTypeDto, String> getDisplayPropertyAccessor()  {
		return display_pa;
	}


	public String getType()  {
		if(! isDtoProxy()){
			return this.type;
		}

		if(isTypeModified())
			return this.type;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().type());

		return _value;
	}


	public void setType(String type)  {
		/* old value */
		String oldValue = null;
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

		this.fireObjectChangedEvent(SearchResultTagTypeDtoPA.INSTANCE.type(), oldValue);
	}


	public boolean isTypeModified()  {
		return type_m;
	}


	public static PropertyAccessor<SearchResultTagTypeDto, String> getTypePropertyAccessor()  {
		return type_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new SearchResultTagTypeDto2PosoMap();
	}

	public SearchResultTagTypeDtoPA instantiatePropertyAccess()  {
		return GWT.create(SearchResultTagTypeDtoPA.class);
	}

	public void clearModified()  {
		this.display = null;
		this.display_m = false;
		this.type = null;
		this.type_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(display_m)
			return true;
		if(type_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(display_pa);
		list.add(type_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(display_m)
			list.add(display_pa);
		if(type_m)
			list.add(type_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(display_pa);
			list.add(type_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
