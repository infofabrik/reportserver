package net.datenwerke.rs.dashboard.client.dashboard.dto;

import com.google.gwt.core.client.GWT;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DadgetDtoDec;
import net.datenwerke.rs.dashboard.client.dashboard.dto.pa.UrlDadgetDtoPA;
import net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.UrlDadgetDto2PosoMap;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.UrlDadget;

/**
 * Dto for {@link UrlDadget}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class UrlDadgetDto extends DadgetDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String title;
	private  boolean title_m;
	public static final String PROPERTY_TITLE = "dpi-urldadget-title";

	private transient static PropertyAccessor<UrlDadgetDto, String> title_pa = new PropertyAccessor<UrlDadgetDto, String>() {
		@Override
		public void setValue(UrlDadgetDto container, String object) {
			container.setTitle(object);
		}

		@Override
		public String getValue(UrlDadgetDto container) {
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
		public void setModified(UrlDadgetDto container, boolean modified) {
			container.title_m = modified;
		}

		@Override
		public boolean isModified(UrlDadgetDto container) {
			return container.isTitleModified();
		}
	};

	private String url;
	private  boolean url_m;
	public static final String PROPERTY_URL = "dpi-urldadget-url";

	private transient static PropertyAccessor<UrlDadgetDto, String> url_pa = new PropertyAccessor<UrlDadgetDto, String>() {
		@Override
		public void setValue(UrlDadgetDto container, String object) {
			container.setUrl(object);
		}

		@Override
		public String getValue(UrlDadgetDto container) {
			return container.getUrl();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "url";
		}

		@Override
		public void setModified(UrlDadgetDto container, boolean modified) {
			container.url_m = modified;
		}

		@Override
		public boolean isModified(UrlDadgetDto container) {
			return container.isUrlModified();
		}
	};


	public UrlDadgetDto() {
		super();
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

		this.fireObjectChangedEvent(UrlDadgetDtoPA.INSTANCE.title(), oldValue);
	}


	public boolean isTitleModified()  {
		return title_m;
	}


	public static PropertyAccessor<UrlDadgetDto, String> getTitlePropertyAccessor()  {
		return title_pa;
	}


	public String getUrl()  {
		if(! isDtoProxy()){
			return this.url;
		}

		if(isUrlModified())
			return this.url;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().url());

		return _value;
	}


	public void setUrl(String url)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getUrl();

		/* set new value */
		this.url = url;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(url_pa, oldValue, url, this.url_m));

		/* set indicator */
		this.url_m = true;

		this.fireObjectChangedEvent(UrlDadgetDtoPA.INSTANCE.url(), oldValue);
	}


	public boolean isUrlModified()  {
		return url_m;
	}


	public static PropertyAccessor<UrlDadgetDto, String> getUrlPropertyAccessor()  {
		return url_pa;
	}


	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof UrlDadgetDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((UrlDadgetDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new UrlDadgetDto2PosoMap();
	}

	public UrlDadgetDtoPA instantiatePropertyAccess()  {
		return GWT.create(UrlDadgetDtoPA.class);
	}

	public void clearModified()  {
		this.title = null;
		this.title_m = false;
		this.url = null;
		this.url_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(title_m)
			return true;
		if(url_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(title_pa);
		list.add(url_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(title_m)
			list.add(title_pa);
		if(url_m)
			list.add(url_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(title_pa);
			list.add(url_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
