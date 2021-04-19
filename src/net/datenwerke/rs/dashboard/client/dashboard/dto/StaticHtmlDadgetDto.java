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
import net.datenwerke.rs.dashboard.client.dashboard.dto.pa.StaticHtmlDadgetDtoPA;
import net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.StaticHtmlDadgetDto2PosoMap;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.StaticHtmlDadget;

/**
 * Dto for {@link StaticHtmlDadget}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class StaticHtmlDadgetDto extends DadgetDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String data;
	private  boolean data_m;
	public static final String PROPERTY_DATA = "dpi-statichtmldadget-data";

	private transient static PropertyAccessor<StaticHtmlDadgetDto, String> data_pa = new PropertyAccessor<StaticHtmlDadgetDto, String>() {
		@Override
		public void setValue(StaticHtmlDadgetDto container, String object) {
			container.setData(object);
		}

		@Override
		public String getValue(StaticHtmlDadgetDto container) {
			return container.getData();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "data";
		}

		@Override
		public void setModified(StaticHtmlDadgetDto container, boolean modified) {
			container.data_m = modified;
		}

		@Override
		public boolean isModified(StaticHtmlDadgetDto container) {
			return container.isDataModified();
		}
	};

	private String title;
	private  boolean title_m;
	public static final String PROPERTY_TITLE = "dpi-statichtmldadget-title";

	private transient static PropertyAccessor<StaticHtmlDadgetDto, String> title_pa = new PropertyAccessor<StaticHtmlDadgetDto, String>() {
		@Override
		public void setValue(StaticHtmlDadgetDto container, String object) {
			container.setTitle(object);
		}

		@Override
		public String getValue(StaticHtmlDadgetDto container) {
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
		public void setModified(StaticHtmlDadgetDto container, boolean modified) {
			container.title_m = modified;
		}

		@Override
		public boolean isModified(StaticHtmlDadgetDto container) {
			return container.isTitleModified();
		}
	};


	public StaticHtmlDadgetDto() {
		super();
	}

	public String getData()  {
		if(! isDtoProxy()){
			return this.data;
		}

		if(isDataModified())
			return this.data;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().data());

		return _value;
	}


	public void setData(String data)  {
		/* old value */
		String oldValue = null;
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

		this.fireObjectChangedEvent(StaticHtmlDadgetDtoPA.INSTANCE.data(), oldValue);
	}


	public boolean isDataModified()  {
		return data_m;
	}


	public static PropertyAccessor<StaticHtmlDadgetDto, String> getDataPropertyAccessor()  {
		return data_pa;
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

		this.fireObjectChangedEvent(StaticHtmlDadgetDtoPA.INSTANCE.title(), oldValue);
	}


	public boolean isTitleModified()  {
		return title_m;
	}


	public static PropertyAccessor<StaticHtmlDadgetDto, String> getTitlePropertyAccessor()  {
		return title_pa;
	}


	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof StaticHtmlDadgetDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((StaticHtmlDadgetDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new StaticHtmlDadgetDto2PosoMap();
	}

	public StaticHtmlDadgetDtoPA instantiatePropertyAccess()  {
		return GWT.create(StaticHtmlDadgetDtoPA.class);
	}

	public void clearModified()  {
		this.data = null;
		this.data_m = false;
		this.title = null;
		this.title_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(data_m)
			return true;
		if(title_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(data_pa);
		list.add(title_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(data_m)
			list.add(data_pa);
		if(title_m)
			list.add(title_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(data_pa);
			list.add(title_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
