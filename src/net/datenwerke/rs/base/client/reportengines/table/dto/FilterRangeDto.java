package net.datenwerke.rs.base.client.reportengines.table.dto;

import com.google.gwt.core.client.GWT;
import java.lang.IllegalStateException;
import java.lang.Long;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.IdedDto;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.rs.base.client.reportengines.table.dto.pa.FilterRangeDtoPA;
import net.datenwerke.rs.base.client.reportengines.table.dto.posomap.FilterRangeDto2PosoMap;
import net.datenwerke.rs.base.service.reportengines.table.entities.FilterRange;

/**
 * Dto for {@link FilterRange}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class FilterRangeDto extends RsDto implements IdedDto {


	private static final long serialVersionUID = 1;

	private Long dtoId;


	/* Fields */
	private Long id;
	private  boolean id_m;
	public static final String PROPERTY_ID = "dpi-filterrange-id";

	private transient static PropertyAccessor<FilterRangeDto, Long> id_pa = new PropertyAccessor<FilterRangeDto, Long>() {
		@Override
		public void setValue(FilterRangeDto container, Long object) {
			// id field
		}

		@Override
		public Long getValue(FilterRangeDto container) {
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
		public void setModified(FilterRangeDto container, boolean modified) {
			container.id_m = modified;
		}

		@Override
		public boolean isModified(FilterRangeDto container) {
			return container.isIdModified();
		}
	};

	private String rangeFrom;
	private  boolean rangeFrom_m;
	public static final String PROPERTY_RANGE_FROM = "dpi-filterrange-rangefrom";

	private transient static PropertyAccessor<FilterRangeDto, String> rangeFrom_pa = new PropertyAccessor<FilterRangeDto, String>() {
		@Override
		public void setValue(FilterRangeDto container, String object) {
			container.setRangeFrom(object);
		}

		@Override
		public String getValue(FilterRangeDto container) {
			return container.getRangeFrom();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "rangeFrom";
		}

		@Override
		public void setModified(FilterRangeDto container, boolean modified) {
			container.rangeFrom_m = modified;
		}

		@Override
		public boolean isModified(FilterRangeDto container) {
			return container.isRangeFromModified();
		}
	};

	private String rangeTo;
	private  boolean rangeTo_m;
	public static final String PROPERTY_RANGE_TO = "dpi-filterrange-rangeto";

	private transient static PropertyAccessor<FilterRangeDto, String> rangeTo_pa = new PropertyAccessor<FilterRangeDto, String>() {
		@Override
		public void setValue(FilterRangeDto container, String object) {
			container.setRangeTo(object);
		}

		@Override
		public String getValue(FilterRangeDto container) {
			return container.getRangeTo();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "rangeTo";
		}

		@Override
		public void setModified(FilterRangeDto container, boolean modified) {
			container.rangeTo_m = modified;
		}

		@Override
		public boolean isModified(FilterRangeDto container) {
			return container.isRangeToModified();
		}
	};


	public FilterRangeDto() {
		super();
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


	public static PropertyAccessor<FilterRangeDto, Long> getIdPropertyAccessor()  {
		return id_pa;
	}


	public String getRangeFrom()  {
		if(! isDtoProxy()){
			return this.rangeFrom;
		}

		if(isRangeFromModified())
			return this.rangeFrom;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().rangeFrom());

		return _value;
	}


	public void setRangeFrom(String rangeFrom)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getRangeFrom();

		/* set new value */
		this.rangeFrom = rangeFrom;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(rangeFrom_pa, oldValue, rangeFrom, this.rangeFrom_m));

		/* set indicator */
		this.rangeFrom_m = true;

		this.fireObjectChangedEvent(FilterRangeDtoPA.INSTANCE.rangeFrom(), oldValue);
	}


	public boolean isRangeFromModified()  {
		return rangeFrom_m;
	}


	public static PropertyAccessor<FilterRangeDto, String> getRangeFromPropertyAccessor()  {
		return rangeFrom_pa;
	}


	public String getRangeTo()  {
		if(! isDtoProxy()){
			return this.rangeTo;
		}

		if(isRangeToModified())
			return this.rangeTo;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().rangeTo());

		return _value;
	}


	public void setRangeTo(String rangeTo)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getRangeTo();

		/* set new value */
		this.rangeTo = rangeTo;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(rangeTo_pa, oldValue, rangeTo, this.rangeTo_m));

		/* set indicator */
		this.rangeTo_m = true;

		this.fireObjectChangedEvent(FilterRangeDtoPA.INSTANCE.rangeTo(), oldValue);
	}


	public boolean isRangeToModified()  {
		return rangeTo_m;
	}


	public static PropertyAccessor<FilterRangeDto, String> getRangeToPropertyAccessor()  {
		return rangeTo_pa;
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
		if(! (obj instanceof FilterRangeDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((FilterRangeDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new FilterRangeDto2PosoMap();
	}

	public FilterRangeDtoPA instantiatePropertyAccess()  {
		return GWT.create(FilterRangeDtoPA.class);
	}

	public void clearModified()  {
		this.id = null;
		this.id_m = false;
		this.rangeFrom = null;
		this.rangeFrom_m = false;
		this.rangeTo = null;
		this.rangeTo_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(id_m)
			return true;
		if(rangeFrom_m)
			return true;
		if(rangeTo_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(id_pa);
		list.add(rangeFrom_pa);
		list.add(rangeTo_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(id_m)
			list.add(id_pa);
		if(rangeFrom_m)
			list.add(rangeFrom_pa);
		if(rangeTo_m)
			list.add(rangeTo_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(id_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(rangeFrom_pa);
			list.add(rangeTo_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
