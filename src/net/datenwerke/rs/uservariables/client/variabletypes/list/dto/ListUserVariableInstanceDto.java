package net.datenwerke.rs.uservariables.client.variabletypes.list.dto;

import com.google.gwt.core.client.GWT;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableInstanceDto;
import net.datenwerke.rs.uservariables.client.variabletypes.list.dto.pa.ListUserVariableInstanceDtoPA;
import net.datenwerke.rs.uservariables.client.variabletypes.list.dto.posomap.ListUserVariableInstanceDto2PosoMap;
import net.datenwerke.rs.uservariables.service.variabletypes.list.ListUserVariableInstance;

/**
 * Dto for {@link ListUserVariableInstance}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class ListUserVariableInstanceDto extends UserVariableInstanceDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private Set<String> value;
	private  boolean value_m;
	public static final String PROPERTY_VALUE = "dpi-listuservariableinstance-value";

	private transient static PropertyAccessor<ListUserVariableInstanceDto, Set<String>> value_pa = new PropertyAccessor<ListUserVariableInstanceDto, Set<String>>() {
		@Override
		public void setValue(ListUserVariableInstanceDto container, Set<String> object) {
			container.setValue(object);
		}

		@Override
		public Set<String> getValue(ListUserVariableInstanceDto container) {
			return container.getValue();
		}

		@Override
		public Class<?> getType() {
			return Set.class;
		}

		@Override
		public String getPath() {
			return "value";
		}

		@Override
		public void setModified(ListUserVariableInstanceDto container, boolean modified) {
			container.value_m = modified;
		}

		@Override
		public boolean isModified(ListUserVariableInstanceDto container) {
			return container.isValueModified();
		}
	};


	public ListUserVariableInstanceDto() {
		super();
	}

	public Set<String> getValue()  {
		if(! isDtoProxy()){
			Set<String> _currentValue = this.value;
			if(null == _currentValue)
				this.value = new HashSet<String>();

			return this.value;
		}

		if(isValueModified())
			return this.value;

		if(! GWT.isClient())
			return null;

		Set<String> _value = dtoManager.getProperty(this, instantiatePropertyAccess().value());

		return _value;
	}


	public void setValue(Set<String> value)  {
		/* old value */
		Set<String> oldValue = null;
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

		this.fireObjectChangedEvent(ListUserVariableInstanceDtoPA.INSTANCE.value(), oldValue);
	}


	public boolean isValueModified()  {
		return value_m;
	}


	public static PropertyAccessor<ListUserVariableInstanceDto, Set<String>> getValuePropertyAccessor()  {
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
		if(! (obj instanceof ListUserVariableInstanceDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((ListUserVariableInstanceDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new ListUserVariableInstanceDto2PosoMap();
	}

	public ListUserVariableInstanceDtoPA instantiatePropertyAccess()  {
		return GWT.create(ListUserVariableInstanceDtoPA.class);
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
