package net.datenwerke.rs.base.client.parameters.datasource.dto;

import com.google.gwt.core.client.GWT;
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
import net.datenwerke.rs.base.client.parameters.datasource.dto.pa.DatasourceParameterDataDtoPA;
import net.datenwerke.rs.base.client.parameters.datasource.dto.posomap.DatasourceParameterDataDto2PosoMap;
import net.datenwerke.rs.base.service.parameters.datasource.DatasourceParameterData;

/**
 * Dto for {@link DatasourceParameterData}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class DatasourceParameterDataDto extends RsDto implements IdedDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private Long id;
	private  boolean id_m;
	public static final String PROPERTY_ID = "dpi-datasourceparameterdata-id";

	private transient static PropertyAccessor<DatasourceParameterDataDto, Long> id_pa = new PropertyAccessor<DatasourceParameterDataDto, Long>() {
		@Override
		public void setValue(DatasourceParameterDataDto container, Long object) {
			// id field
		}

		@Override
		public Long getValue(DatasourceParameterDataDto container) {
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
		public void setModified(DatasourceParameterDataDto container, boolean modified) {
			container.id_m = modified;
		}

		@Override
		public boolean isModified(DatasourceParameterDataDto container) {
			return container.isIdModified();
		}
	};

	private String key;
	private  boolean key_m;
	public static final String PROPERTY_KEY = "dpi-datasourceparameterdata-key";

	private transient static PropertyAccessor<DatasourceParameterDataDto, String> key_pa = new PropertyAccessor<DatasourceParameterDataDto, String>() {
		@Override
		public void setValue(DatasourceParameterDataDto container, String object) {
			container.setKey(object);
		}

		@Override
		public String getValue(DatasourceParameterDataDto container) {
			return container.getKey();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "key";
		}

		@Override
		public void setModified(DatasourceParameterDataDto container, boolean modified) {
			container.key_m = modified;
		}

		@Override
		public boolean isModified(DatasourceParameterDataDto container) {
			return container.isKeyModified();
		}
	};

	private String value;
	private  boolean value_m;
	public static final String PROPERTY_VALUE = "dpi-datasourceparameterdata-value";

	private transient static PropertyAccessor<DatasourceParameterDataDto, String> value_pa = new PropertyAccessor<DatasourceParameterDataDto, String>() {
		@Override
		public void setValue(DatasourceParameterDataDto container, String object) {
			container.setValue(object);
		}

		@Override
		public String getValue(DatasourceParameterDataDto container) {
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
		public void setModified(DatasourceParameterDataDto container, boolean modified) {
			container.value_m = modified;
		}

		@Override
		public boolean isModified(DatasourceParameterDataDto container) {
			return container.isValueModified();
		}
	};


	public DatasourceParameterDataDto() {
		super();
	}

	public Long getId()  {
		if(! isDtoProxy()){
			return this.id;
		}

		if(isIdModified())
			return this.id;

		if(! GWT.isClient())
			return null;

		Long _value = dtoManager.getProperty(this, instantiatePropertyAccess().id());

		return _value;
	}


	public void setId(Long id)  {
		/* old value */
		Long oldValue = null;
		if(GWT.isClient())
			oldValue = getId();

		/* set new value */
		this.id = id;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(id_pa, oldValue, id, this.id_m));

		/* set indicator */
		this.id_m = true;

		this.fireObjectChangedEvent(DatasourceParameterDataDtoPA.INSTANCE.id(), oldValue);
	}


	public boolean isIdModified()  {
		return id_m;
	}


	public static PropertyAccessor<DatasourceParameterDataDto, Long> getIdPropertyAccessor()  {
		return id_pa;
	}


	public String getKey()  {
		if(! isDtoProxy()){
			return this.key;
		}

		if(isKeyModified())
			return this.key;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().key());

		return _value;
	}


	public void setKey(String key)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getKey();

		/* set new value */
		this.key = key;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(key_pa, oldValue, key, this.key_m));

		/* set indicator */
		this.key_m = true;

		this.fireObjectChangedEvent(DatasourceParameterDataDtoPA.INSTANCE.key(), oldValue);
	}


	public boolean isKeyModified()  {
		return key_m;
	}


	public static PropertyAccessor<DatasourceParameterDataDto, String> getKeyPropertyAccessor()  {
		return key_pa;
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

		this.fireObjectChangedEvent(DatasourceParameterDataDtoPA.INSTANCE.value(), oldValue);
	}


	public boolean isValueModified()  {
		return value_m;
	}


	public static PropertyAccessor<DatasourceParameterDataDto, String> getValuePropertyAccessor()  {
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
		if(! (obj instanceof DatasourceParameterDataDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((DatasourceParameterDataDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new DatasourceParameterDataDto2PosoMap();
	}

	public DatasourceParameterDataDtoPA instantiatePropertyAccess()  {
		return GWT.create(DatasourceParameterDataDtoPA.class);
	}

	public void clearModified()  {
		this.id = null;
		this.id_m = false;
		this.key = null;
		this.key_m = false;
		this.value = null;
		this.value_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(id_m)
			return true;
		if(key_m)
			return true;
		if(value_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(id_pa);
		list.add(key_pa);
		list.add(value_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(id_m)
			list.add(id_pa);
		if(key_m)
			list.add(key_pa);
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
			list.add(key_pa);
			list.add(value_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
