package net.datenwerke.rs.grideditor.client.grideditor.dto;

import com.google.gwt.core.client.GWT;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.ValidatorDtoDec;
import net.datenwerke.rs.grideditor.client.grideditor.dto.pa.MinDateValidatorDtoPA;
import net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.MinDateValidatorDto2PosoMap;
import net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MinDateValidator;

/**
 * Dto for {@link MinDateValidator}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class MinDateValidatorDto extends ValidatorDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private Date minDate;
	private  boolean minDate_m;
	public static final String PROPERTY_MIN_DATE = "dpi-mindatevalidator-mindate";

	private transient static PropertyAccessor<MinDateValidatorDto, Date> minDate_pa = new PropertyAccessor<MinDateValidatorDto, Date>() {
		@Override
		public void setValue(MinDateValidatorDto container, Date object) {
			container.setMinDate(object);
		}

		@Override
		public Date getValue(MinDateValidatorDto container) {
			return container.getMinDate();
		}

		@Override
		public Class<?> getType() {
			return Date.class;
		}

		@Override
		public String getPath() {
			return "minDate";
		}

		@Override
		public void setModified(MinDateValidatorDto container, boolean modified) {
			container.minDate_m = modified;
		}

		@Override
		public boolean isModified(MinDateValidatorDto container) {
			return container.isMinDateModified();
		}
	};


	public MinDateValidatorDto() {
		super();
	}

	public Date getMinDate()  {
		if(! isDtoProxy()){
			return this.minDate;
		}

		if(isMinDateModified())
			return this.minDate;

		if(! GWT.isClient())
			return null;

		Date _value = dtoManager.getProperty(this, instantiatePropertyAccess().minDate());

		return _value;
	}


	public void setMinDate(Date minDate)  {
		/* old value */
		Date oldValue = null;
		if(GWT.isClient())
			oldValue = getMinDate();

		/* set new value */
		this.minDate = minDate;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(minDate_pa, oldValue, minDate, this.minDate_m));

		/* set indicator */
		this.minDate_m = true;

		this.fireObjectChangedEvent(MinDateValidatorDtoPA.INSTANCE.minDate(), oldValue);
	}


	public boolean isMinDateModified()  {
		return minDate_m;
	}


	public static PropertyAccessor<MinDateValidatorDto, Date> getMinDatePropertyAccessor()  {
		return minDate_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new MinDateValidatorDto2PosoMap();
	}

	public MinDateValidatorDtoPA instantiatePropertyAccess()  {
		return GWT.create(MinDateValidatorDtoPA.class);
	}

	public void clearModified()  {
		this.minDate = null;
		this.minDate_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(minDate_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(minDate_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(minDate_m)
			list.add(minDate_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(minDate_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
