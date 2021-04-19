package net.datenwerke.rs.grideditor.client.grideditor.dto;

import com.google.gwt.core.client.GWT;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.ValidatorDtoDec;
import net.datenwerke.rs.grideditor.client.grideditor.dto.pa.MaxBigDecimalValidatorDtoPA;
import net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.MaxBigDecimalValidatorDto2PosoMap;
import net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MaxBigDecimalValidator;

/**
 * Dto for {@link MaxBigDecimalValidator}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class MaxBigDecimalValidatorDto extends ValidatorDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private BigDecimal number;
	private  boolean number_m;
	public static final String PROPERTY_NUMBER = "dpi-maxbigdecimalvalidator-number";

	private transient static PropertyAccessor<MaxBigDecimalValidatorDto, BigDecimal> number_pa = new PropertyAccessor<MaxBigDecimalValidatorDto, BigDecimal>() {
		@Override
		public void setValue(MaxBigDecimalValidatorDto container, BigDecimal object) {
			container.setNumber(object);
		}

		@Override
		public BigDecimal getValue(MaxBigDecimalValidatorDto container) {
			return container.getNumber();
		}

		@Override
		public Class<?> getType() {
			return BigDecimal.class;
		}

		@Override
		public String getPath() {
			return "number";
		}

		@Override
		public void setModified(MaxBigDecimalValidatorDto container, boolean modified) {
			container.number_m = modified;
		}

		@Override
		public boolean isModified(MaxBigDecimalValidatorDto container) {
			return container.isNumberModified();
		}
	};


	public MaxBigDecimalValidatorDto() {
		super();
	}

	public BigDecimal getNumber()  {
		if(! isDtoProxy()){
			return this.number;
		}

		if(isNumberModified())
			return this.number;

		if(! GWT.isClient())
			return null;

		BigDecimal _value = dtoManager.getProperty(this, instantiatePropertyAccess().number());

		return _value;
	}


	public void setNumber(BigDecimal number)  {
		/* old value */
		BigDecimal oldValue = null;
		if(GWT.isClient())
			oldValue = getNumber();

		/* set new value */
		this.number = number;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(number_pa, oldValue, number, this.number_m));

		/* set indicator */
		this.number_m = true;

		this.fireObjectChangedEvent(MaxBigDecimalValidatorDtoPA.INSTANCE.number(), oldValue);
	}


	public boolean isNumberModified()  {
		return number_m;
	}


	public static PropertyAccessor<MaxBigDecimalValidatorDto, BigDecimal> getNumberPropertyAccessor()  {
		return number_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new MaxBigDecimalValidatorDto2PosoMap();
	}

	public MaxBigDecimalValidatorDtoPA instantiatePropertyAccess()  {
		return GWT.create(MaxBigDecimalValidatorDtoPA.class);
	}

	public void clearModified()  {
		this.number = null;
		this.number_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(number_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(number_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(number_m)
			list.add(number_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(number_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
