package net.datenwerke.security.client.usermanager.dto;

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
import net.datenwerke.security.client.usermanager.dto.pa.UserPropertyDtoPA;
import net.datenwerke.security.client.usermanager.dto.posomap.UserPropertyDto2PosoMap;
import net.datenwerke.security.service.usermanager.entities.UserProperty;

/**
 * Dto for {@link UserProperty}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class UserPropertyDto extends RsDto implements IdedDto {


	private static final long serialVersionUID = 1;

	private Long dtoId;


	/* Fields */
	private Long id;
	private  boolean id_m;
	public static final String PROPERTY_ID = "dpi-userproperty-id";

	private transient static PropertyAccessor<UserPropertyDto, Long> id_pa = new PropertyAccessor<UserPropertyDto, Long>() {
		@Override
		public void setValue(UserPropertyDto container, Long object) {
			// id field
		}

		@Override
		public Long getValue(UserPropertyDto container) {
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
		public void setModified(UserPropertyDto container, boolean modified) {
			container.id_m = modified;
		}

		@Override
		public boolean isModified(UserPropertyDto container) {
			return container.isIdModified();
		}
	};

	private String key;
	private  boolean key_m;
	public static final String PROPERTY_KEY = "dpi-userproperty-key";

	private transient static PropertyAccessor<UserPropertyDto, String> key_pa = new PropertyAccessor<UserPropertyDto, String>() {
		@Override
		public void setValue(UserPropertyDto container, String object) {
			container.setKey(object);
		}

		@Override
		public String getValue(UserPropertyDto container) {
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
		public void setModified(UserPropertyDto container, boolean modified) {
			container.key_m = modified;
		}

		@Override
		public boolean isModified(UserPropertyDto container) {
			return container.isKeyModified();
		}
	};

	private String value;
	private  boolean value_m;
	public static final String PROPERTY_VALUE = "dpi-userproperty-value";

	private transient static PropertyAccessor<UserPropertyDto, String> value_pa = new PropertyAccessor<UserPropertyDto, String>() {
		@Override
		public void setValue(UserPropertyDto container, String object) {
			container.setValue(object);
		}

		@Override
		public String getValue(UserPropertyDto container) {
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
		public void setModified(UserPropertyDto container, boolean modified) {
			container.value_m = modified;
		}

		@Override
		public boolean isModified(UserPropertyDto container) {
			return container.isValueModified();
		}
	};


	public UserPropertyDto() {
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


	public static PropertyAccessor<UserPropertyDto, Long> getIdPropertyAccessor()  {
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

		this.fireObjectChangedEvent(UserPropertyDtoPA.INSTANCE.key(), oldValue);
	}


	public boolean isKeyModified()  {
		return key_m;
	}


	public static PropertyAccessor<UserPropertyDto, String> getKeyPropertyAccessor()  {
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

		this.fireObjectChangedEvent(UserPropertyDtoPA.INSTANCE.value(), oldValue);
	}


	public boolean isValueModified()  {
		return value_m;
	}


	public static PropertyAccessor<UserPropertyDto, String> getValuePropertyAccessor()  {
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
		if(! (obj instanceof UserPropertyDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((UserPropertyDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new UserPropertyDto2PosoMap();
	}

	public UserPropertyDtoPA instantiatePropertyAccess()  {
		return GWT.create(UserPropertyDtoPA.class);
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
