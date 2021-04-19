package net.datenwerke.rs.grideditor.client.grideditor.dto;

import com.google.gwt.core.client.GWT;
import java.lang.Float;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.ValidatorDtoDec;
import net.datenwerke.rs.grideditor.client.grideditor.dto.pa.MinFloatValidatorDtoPA;
import net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.MinFloatValidatorDto2PosoMap;
import net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MinFloatValidator;

/**
 * Dto for {@link MinFloatValidator}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class MinFloatValidatorDto extends ValidatorDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private Float number;
	private  boolean number_m;
	public static final String PROPERTY_NUMBER = "dpi-minfloatvalidator-number";

	private transient static PropertyAccessor<MinFloatValidatorDto, Float> number_pa = new PropertyAccessor<MinFloatValidatorDto, Float>() {
		@Override
		public void setValue(MinFloatValidatorDto container, Float object) {
			container.setNumber(object);
		}

		@Override
		public Float getValue(MinFloatValidatorDto container) {
			return container.getNumber();
		}

		@Override
		public Class<?> getType() {
			return Float.class;
		}

		@Override
		public String getPath() {
			return "number";
		}

		@Override
		public void setModified(MinFloatValidatorDto container, boolean modified) {
			container.number_m = modified;
		}

		@Override
		public boolean isModified(MinFloatValidatorDto container) {
			return container.isNumberModified();
		}
	};


	public MinFloatValidatorDto() {
		super();
	}

	public Float getNumber()  {
		if(! isDtoProxy()){
			return this.number;
		}

		if(isNumberModified())
			return this.number;

		if(! GWT.isClient())
			return null;

		Float _value = dtoManager.getProperty(this, instantiatePropertyAccess().number());

		return _value;
	}


	public void setNumber(Float number)  {
		/* old value */
		Float oldValue = null;
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

		this.fireObjectChangedEvent(MinFloatValidatorDtoPA.INSTANCE.number(), oldValue);
	}


	public boolean isNumberModified()  {
		return number_m;
	}


	public static PropertyAccessor<MinFloatValidatorDto, Float> getNumberPropertyAccessor()  {
		return number_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new MinFloatValidatorDto2PosoMap();
	}

	public MinFloatValidatorDtoPA instantiatePropertyAccess()  {
		return GWT.create(MinFloatValidatorDtoPA.class);
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
