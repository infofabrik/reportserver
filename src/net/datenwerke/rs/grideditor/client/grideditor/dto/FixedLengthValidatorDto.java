package net.datenwerke.rs.grideditor.client.grideditor.dto;

import com.google.gwt.core.client.GWT;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.ValidatorDtoDec;
import net.datenwerke.rs.grideditor.client.grideditor.dto.pa.FixedLengthValidatorDtoPA;
import net.datenwerke.rs.grideditor.client.grideditor.dto.posomap.FixedLengthValidatorDto2PosoMap;
import net.datenwerke.rs.grideditor.service.grideditor.definition.validator.FixedLengthValidator;

/**
 * Dto for {@link FixedLengthValidator}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class FixedLengthValidatorDto extends ValidatorDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private int length;
	private  boolean length_m;
	public static final String PROPERTY_LENGTH = "dpi-fixedlengthvalidator-length";

	private transient static PropertyAccessor<FixedLengthValidatorDto, Integer> length_pa = new PropertyAccessor<FixedLengthValidatorDto, Integer>() {
		@Override
		public void setValue(FixedLengthValidatorDto container, Integer object) {
			container.setLength(object);
		}

		@Override
		public Integer getValue(FixedLengthValidatorDto container) {
			return container.getLength();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "length";
		}

		@Override
		public void setModified(FixedLengthValidatorDto container, boolean modified) {
			container.length_m = modified;
		}

		@Override
		public boolean isModified(FixedLengthValidatorDto container) {
			return container.isLengthModified();
		}
	};


	public FixedLengthValidatorDto() {
		super();
	}

	public int getLength()  {
		if(! isDtoProxy()){
			return this.length;
		}

		if(isLengthModified())
			return this.length;

		if(! GWT.isClient())
			return 0;

		int _value = dtoManager.getProperty(this, instantiatePropertyAccess().length());

		return _value;
	}


	public void setLength(int length)  {
		/* old value */
		int oldValue = 0;
		if(GWT.isClient())
			oldValue = getLength();

		/* set new value */
		this.length = length;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(length_pa, oldValue, length, this.length_m));

		/* set indicator */
		this.length_m = true;

		this.fireObjectChangedEvent(FixedLengthValidatorDtoPA.INSTANCE.length(), oldValue);
	}


	public boolean isLengthModified()  {
		return length_m;
	}


	public static PropertyAccessor<FixedLengthValidatorDto, Integer> getLengthPropertyAccessor()  {
		return length_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new FixedLengthValidatorDto2PosoMap();
	}

	public FixedLengthValidatorDtoPA instantiatePropertyAccess()  {
		return GWT.create(FixedLengthValidatorDtoPA.class);
	}

	public void clearModified()  {
		this.length = 0;
		this.length_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(length_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(length_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(length_m)
			list.add(length_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(length_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
