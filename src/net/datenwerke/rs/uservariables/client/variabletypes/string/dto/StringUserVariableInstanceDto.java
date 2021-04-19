package net.datenwerke.rs.uservariables.client.variabletypes.string.dto;

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
import net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableInstanceDto;
import net.datenwerke.rs.uservariables.client.variabletypes.string.dto.pa.StringUserVariableInstanceDtoPA;
import net.datenwerke.rs.uservariables.client.variabletypes.string.dto.posomap.StringUserVariableInstanceDto2PosoMap;
import net.datenwerke.rs.uservariables.service.variabletypes.string.StringUserVariableInstance;

/**
 * Dto for {@link StringUserVariableInstance}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class StringUserVariableInstanceDto extends UserVariableInstanceDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String value;
	private  boolean value_m;
	public static final String PROPERTY_VALUE = "dpi-stringuservariableinstance-value";

	private transient static PropertyAccessor<StringUserVariableInstanceDto, String> value_pa = new PropertyAccessor<StringUserVariableInstanceDto, String>() {
		@Override
		public void setValue(StringUserVariableInstanceDto container, String object) {
			container.setValue(object);
		}

		@Override
		public String getValue(StringUserVariableInstanceDto container) {
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
		public void setModified(StringUserVariableInstanceDto container, boolean modified) {
			container.value_m = modified;
		}

		@Override
		public boolean isModified(StringUserVariableInstanceDto container) {
			return container.isValueModified();
		}
	};


	public StringUserVariableInstanceDto() {
		super();
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

		this.fireObjectChangedEvent(StringUserVariableInstanceDtoPA.INSTANCE.value(), oldValue);
	}


	public boolean isValueModified()  {
		return value_m;
	}


	public static PropertyAccessor<StringUserVariableInstanceDto, String> getValuePropertyAccessor()  {
		return value_pa;
	}


	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof StringUserVariableInstanceDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((StringUserVariableInstanceDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new StringUserVariableInstanceDto2PosoMap();
	}

	public StringUserVariableInstanceDtoPA instantiatePropertyAccess()  {
		return GWT.create(StringUserVariableInstanceDtoPA.class);
	}

	public void clearModified()  {
		this.value = null;
		this.value_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(value_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(value_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(value_m)
			list.add(value_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(value_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
