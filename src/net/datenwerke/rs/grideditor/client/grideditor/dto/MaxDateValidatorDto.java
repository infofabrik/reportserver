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
import net.datenwerke.rs.grideditor.client.grideditor.dto.pa.MaxDateValidatorDtoPA;
import net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.MaxDateValidatorDto2PosoMap;
import net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MaxDateValidator;

/**
 * Dto for {@link MaxDateValidator}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class MaxDateValidatorDto extends ValidatorDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private Date maxDate;
	private  boolean maxDate_m;
	public static final String PROPERTY_MAX_DATE = "dpi-maxdatevalidator-maxdate";

	private transient static PropertyAccessor<MaxDateValidatorDto, Date> maxDate_pa = new PropertyAccessor<MaxDateValidatorDto, Date>() {
		@Override
		public void setValue(MaxDateValidatorDto container, Date object) {
			container.setMaxDate(object);
		}

		@Override
		public Date getValue(MaxDateValidatorDto container) {
			return container.getMaxDate();
		}

		@Override
		public Class<?> getType() {
			return Date.class;
		}

		@Override
		public String getPath() {
			return "maxDate";
		}

		@Override
		public void setModified(MaxDateValidatorDto container, boolean modified) {
			container.maxDate_m = modified;
		}

		@Override
		public boolean isModified(MaxDateValidatorDto container) {
			return container.isMaxDateModified();
		}
	};


	public MaxDateValidatorDto() {
		super();
	}

	public Date getMaxDate()  {
		if(! isDtoProxy()){
			return this.maxDate;
		}

		if(isMaxDateModified())
			return this.maxDate;

		if(! GWT.isClient())
			return null;

		Date _value = dtoManager.getProperty(this, instantiatePropertyAccess().maxDate());

		return _value;
	}


	public void setMaxDate(Date maxDate)  {
		/* old value */
		Date oldValue = null;
		if(GWT.isClient())
			oldValue = getMaxDate();

		/* set new value */
		this.maxDate = maxDate;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(maxDate_pa, oldValue, maxDate, this.maxDate_m));

		/* set indicator */
		this.maxDate_m = true;

		this.fireObjectChangedEvent(MaxDateValidatorDtoPA.INSTANCE.maxDate(), oldValue);
	}


	public boolean isMaxDateModified()  {
		return maxDate_m;
	}


	public static PropertyAccessor<MaxDateValidatorDto, Date> getMaxDatePropertyAccessor()  {
		return maxDate_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new MaxDateValidatorDto2PosoMap();
	}

	public MaxDateValidatorDtoPA instantiatePropertyAccess()  {
		return GWT.create(MaxDateValidatorDtoPA.class);
	}

	public void clearModified()  {
		this.maxDate = null;
		this.maxDate_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(maxDate_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(maxDate_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(maxDate_m)
			list.add(maxDate_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(maxDate_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
