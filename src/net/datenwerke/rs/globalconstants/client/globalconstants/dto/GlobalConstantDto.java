package net.datenwerke.rs.globalconstants.client.globalconstants.dto;

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
import net.datenwerke.rs.globalconstants.client.globalconstants.dto.pa.GlobalConstantDtoPA;
import net.datenwerke.rs.globalconstants.client.globalconstants.dto.posomap.GlobalConstantDto2PosoMap;
import net.datenwerke.rs.globalconstants.service.globalconstants.entities.GlobalConstant;

/**
 * Dto for {@link GlobalConstant}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class GlobalConstantDto extends RsDto implements IdedDto {


	private static final long serialVersionUID = 1;

	private Long dtoId;


	/* Fields */
	private Long id;
	private  boolean id_m;
	public static final String PROPERTY_ID = "dpi-globalconstant-id";

	private transient static PropertyAccessor<GlobalConstantDto, Long> id_pa = new PropertyAccessor<GlobalConstantDto, Long>() {
		@Override
		public void setValue(GlobalConstantDto container, Long object) {
			// id field
		}

		@Override
		public Long getValue(GlobalConstantDto container) {
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
		public void setModified(GlobalConstantDto container, boolean modified) {
			container.id_m = modified;
		}

		@Override
		public boolean isModified(GlobalConstantDto container) {
			return container.isIdModified();
		}
	};

	private String name;
	private  boolean name_m;
	public static final String PROPERTY_NAME = "dpi-globalconstant-name";

	private transient static PropertyAccessor<GlobalConstantDto, String> name_pa = new PropertyAccessor<GlobalConstantDto, String>() {
		@Override
		public void setValue(GlobalConstantDto container, String object) {
			container.setName(object);
		}

		@Override
		public String getValue(GlobalConstantDto container) {
			return container.getName();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "name";
		}

		@Override
		public void setModified(GlobalConstantDto container, boolean modified) {
			container.name_m = modified;
		}

		@Override
		public boolean isModified(GlobalConstantDto container) {
			return container.isNameModified();
		}
	};

	private String value;
	private  boolean value_m;
	public static final String PROPERTY_VALUE = "dpi-globalconstant-value";

	private transient static PropertyAccessor<GlobalConstantDto, String> value_pa = new PropertyAccessor<GlobalConstantDto, String>() {
		@Override
		public void setValue(GlobalConstantDto container, String object) {
			container.setValue(object);
		}

		@Override
		public String getValue(GlobalConstantDto container) {
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
		public void setModified(GlobalConstantDto container, boolean modified) {
			container.value_m = modified;
		}

		@Override
		public boolean isModified(GlobalConstantDto container) {
			return container.isValueModified();
		}
	};


	public GlobalConstantDto() {
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


	public static PropertyAccessor<GlobalConstantDto, Long> getIdPropertyAccessor()  {
		return id_pa;
	}


	public String getName()  {
		if(! isDtoProxy()){
			return this.name;
		}

		if(isNameModified())
			return this.name;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().name());

		return _value;
	}


	public void setName(String name)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getName();

		/* set new value */
		this.name = name;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(name_pa, oldValue, name, this.name_m));

		/* set indicator */
		this.name_m = true;

		this.fireObjectChangedEvent(GlobalConstantDtoPA.INSTANCE.name(), oldValue);
	}


	public boolean isNameModified()  {
		return name_m;
	}


	public static PropertyAccessor<GlobalConstantDto, String> getNamePropertyAccessor()  {
		return name_pa;
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

		this.fireObjectChangedEvent(GlobalConstantDtoPA.INSTANCE.value(), oldValue);
	}


	public boolean isValueModified()  {
		return value_m;
	}


	public static PropertyAccessor<GlobalConstantDto, String> getValuePropertyAccessor()  {
		return value_pa;
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
		if(! (obj instanceof GlobalConstantDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((GlobalConstantDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new GlobalConstantDto2PosoMap();
	}

	public GlobalConstantDtoPA instantiatePropertyAccess()  {
		return GWT.create(GlobalConstantDtoPA.class);
	}

	public void clearModified()  {
		this.id = null;
		this.id_m = false;
		this.name = null;
		this.name_m = false;
		this.value = null;
		this.value_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(id_m)
			return true;
		if(name_m)
			return true;
		if(value_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(id_pa);
		list.add(name_pa);
		list.add(value_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(id_m)
			list.add(id_pa);
		if(name_m)
			list.add(name_pa);
		if(value_m)
			list.add(value_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(id_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(name_pa);
			list.add(value_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
