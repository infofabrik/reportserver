package net.datenwerke.rs.scripting.client.scripting.dto;

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
import net.datenwerke.rs.scripting.client.scripting.dto.DisplayConditionTypeDto;
import net.datenwerke.rs.scripting.client.scripting.dto.pa.DisplayConditionDtoPA;
import net.datenwerke.rs.scripting.client.scripting.dto.posomap.DisplayConditionDto2PosoMap;
import net.datenwerke.rs.scripting.service.scripting.extensions.DisplayCondition;

/**
 * Dto for {@link DisplayCondition}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class DisplayConditionDto extends RsDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String propertyName;
	private  boolean propertyName_m;
	public static final String PROPERTY_PROPERTY_NAME = "dpi-displaycondition-propertyname";

	private transient static PropertyAccessor<DisplayConditionDto, String> propertyName_pa = new PropertyAccessor<DisplayConditionDto, String>() {
		@Override
		public void setValue(DisplayConditionDto container, String object) {
			container.setPropertyName(object);
		}

		@Override
		public String getValue(DisplayConditionDto container) {
			return container.getPropertyName();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "propertyName";
		}

		@Override
		public void setModified(DisplayConditionDto container, boolean modified) {
			container.propertyName_m = modified;
		}

		@Override
		public boolean isModified(DisplayConditionDto container) {
			return container.isPropertyNameModified();
		}
	};

	private DisplayConditionTypeDto type;
	private  boolean type_m;
	public static final String PROPERTY_TYPE = "dpi-displaycondition-type";

	private transient static PropertyAccessor<DisplayConditionDto, DisplayConditionTypeDto> type_pa = new PropertyAccessor<DisplayConditionDto, DisplayConditionTypeDto>() {
		@Override
		public void setValue(DisplayConditionDto container, DisplayConditionTypeDto object) {
			container.setType(object);
		}

		@Override
		public DisplayConditionTypeDto getValue(DisplayConditionDto container) {
			return container.getType();
		}

		@Override
		public Class<?> getType() {
			return DisplayConditionTypeDto.class;
		}

		@Override
		public String getPath() {
			return "type";
		}

		@Override
		public void setModified(DisplayConditionDto container, boolean modified) {
			container.type_m = modified;
		}

		@Override
		public boolean isModified(DisplayConditionDto container) {
			return container.isTypeModified();
		}
	};

	private String value;
	private  boolean value_m;
	public static final String PROPERTY_VALUE = "dpi-displaycondition-value";

	private transient static PropertyAccessor<DisplayConditionDto, String> value_pa = new PropertyAccessor<DisplayConditionDto, String>() {
		@Override
		public void setValue(DisplayConditionDto container, String object) {
			container.setValue(object);
		}

		@Override
		public String getValue(DisplayConditionDto container) {
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
		public void setModified(DisplayConditionDto container, boolean modified) {
			container.value_m = modified;
		}

		@Override
		public boolean isModified(DisplayConditionDto container) {
			return container.isValueModified();
		}
	};


	public DisplayConditionDto() {
		super();
	}

	public String getPropertyName()  {
		if(! isDtoProxy()){
			return this.propertyName;
		}

		if(isPropertyNameModified())
			return this.propertyName;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().propertyName());

		return _value;
	}


	public void setPropertyName(String propertyName)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getPropertyName();

		/* set new value */
		this.propertyName = propertyName;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(propertyName_pa, oldValue, propertyName, this.propertyName_m));

		/* set indicator */
		this.propertyName_m = true;

		this.fireObjectChangedEvent(DisplayConditionDtoPA.INSTANCE.propertyName(), oldValue);
	}


	public boolean isPropertyNameModified()  {
		return propertyName_m;
	}


	public static PropertyAccessor<DisplayConditionDto, String> getPropertyNamePropertyAccessor()  {
		return propertyName_pa;
	}


	public DisplayConditionTypeDto getType()  {
		if(! isDtoProxy()){
			return this.type;
		}

		if(isTypeModified())
			return this.type;

		if(! GWT.isClient())
			return null;

		DisplayConditionTypeDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().type());

		return _value;
	}


	public void setType(DisplayConditionTypeDto type)  {
		/* old value */
		DisplayConditionTypeDto oldValue = null;
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

		this.fireObjectChangedEvent(DisplayConditionDtoPA.INSTANCE.type(), oldValue);
	}


	public boolean isTypeModified()  {
		return type_m;
	}


	public static PropertyAccessor<DisplayConditionDto, DisplayConditionTypeDto> getTypePropertyAccessor()  {
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

		this.fireObjectChangedEvent(DisplayConditionDtoPA.INSTANCE.value(), oldValue);
	}


	public boolean isValueModified()  {
		return value_m;
	}


	public static PropertyAccessor<DisplayConditionDto, String> getValuePropertyAccessor()  {
		return value_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new DisplayConditionDto2PosoMap();
	}

	public DisplayConditionDtoPA instantiatePropertyAccess()  {
		return GWT.create(DisplayConditionDtoPA.class);
	}

	public void clearModified()  {
		this.propertyName = null;
		this.propertyName_m = false;
		this.type = null;
		this.type_m = false;
		this.value = null;
		this.value_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(propertyName_m)
			return true;
		if(type_m)
			return true;
		if(value_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(propertyName_pa);
		list.add(type_pa);
		list.add(value_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(propertyName_m)
			list.add(propertyName_pa);
		if(type_m)
			list.add(type_pa);
		if(value_m)
			list.add(value_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(propertyName_pa);
			list.add(type_pa);
			list.add(value_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}



	net.datenwerke.rs.scripting.client.scripting.dto.DisplayConditionTypeDto wl_0;

}
