package net.datenwerke.rs.uservariables.client.uservariables.dto;

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
import net.datenwerke.rs.uservariables.client.uservariables.dto.pa.UserVariableDefinitionDtoPA;
import net.datenwerke.rs.uservariables.client.uservariables.dto.posomap.UserVariableDefinitionDto2PosoMap;
import net.datenwerke.rs.uservariables.service.uservariables.entities.UserVariableDefinition;

/**
 * Dto for {@link UserVariableDefinition}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class UserVariableDefinitionDto extends RsDto implements IdedDto {


	private static final long serialVersionUID = 1;

	private Long dtoId;


	/* Fields */
	private String description;
	private  boolean description_m;
	public static final String PROPERTY_DESCRIPTION = "dpi-uservariabledefinition-description";

	private transient static PropertyAccessor<UserVariableDefinitionDto, String> description_pa = new PropertyAccessor<UserVariableDefinitionDto, String>() {
		@Override
		public void setValue(UserVariableDefinitionDto container, String object) {
			container.setDescription(object);
		}

		@Override
		public String getValue(UserVariableDefinitionDto container) {
			return container.getDescription();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "description";
		}

		@Override
		public void setModified(UserVariableDefinitionDto container, boolean modified) {
			container.description_m = modified;
		}

		@Override
		public boolean isModified(UserVariableDefinitionDto container) {
			return container.isDescriptionModified();
		}
	};

	private Long id;
	private  boolean id_m;
	public static final String PROPERTY_ID = "dpi-uservariabledefinition-id";

	private transient static PropertyAccessor<UserVariableDefinitionDto, Long> id_pa = new PropertyAccessor<UserVariableDefinitionDto, Long>() {
		@Override
		public void setValue(UserVariableDefinitionDto container, Long object) {
			// id field
		}

		@Override
		public Long getValue(UserVariableDefinitionDto container) {
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
		public void setModified(UserVariableDefinitionDto container, boolean modified) {
			container.id_m = modified;
		}

		@Override
		public boolean isModified(UserVariableDefinitionDto container) {
			return container.isIdModified();
		}
	};

	private String name;
	private  boolean name_m;
	public static final String PROPERTY_NAME = "dpi-uservariabledefinition-name";

	private transient static PropertyAccessor<UserVariableDefinitionDto, String> name_pa = new PropertyAccessor<UserVariableDefinitionDto, String>() {
		@Override
		public void setValue(UserVariableDefinitionDto container, String object) {
			container.setName(object);
		}

		@Override
		public String getValue(UserVariableDefinitionDto container) {
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
		public void setModified(UserVariableDefinitionDto container, boolean modified) {
			container.name_m = modified;
		}

		@Override
		public boolean isModified(UserVariableDefinitionDto container) {
			return container.isNameModified();
		}
	};


	public UserVariableDefinitionDto() {
		super();
	}

	public String getDescription()  {
		if(! isDtoProxy()){
			return this.description;
		}

		if(isDescriptionModified())
			return this.description;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().description());

		return _value;
	}


	public void setDescription(String description)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getDescription();

		/* set new value */
		this.description = description;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(description_pa, oldValue, description, this.description_m));

		/* set indicator */
		this.description_m = true;

		this.fireObjectChangedEvent(UserVariableDefinitionDtoPA.INSTANCE.description(), oldValue);
	}


	public boolean isDescriptionModified()  {
		return description_m;
	}


	public static PropertyAccessor<UserVariableDefinitionDto, String> getDescriptionPropertyAccessor()  {
		return description_pa;
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


	public static PropertyAccessor<UserVariableDefinitionDto, Long> getIdPropertyAccessor()  {
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

		this.fireObjectChangedEvent(UserVariableDefinitionDtoPA.INSTANCE.name(), oldValue);
	}


	public boolean isNameModified()  {
		return name_m;
	}


	public static PropertyAccessor<UserVariableDefinitionDto, String> getNamePropertyAccessor()  {
		return name_pa;
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
		if(! (obj instanceof UserVariableDefinitionDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((UserVariableDefinitionDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new UserVariableDefinitionDto2PosoMap();
	}

	public UserVariableDefinitionDtoPA instantiatePropertyAccess()  {
		return GWT.create(UserVariableDefinitionDtoPA.class);
	}

	public void clearModified()  {
		this.description = null;
		this.description_m = false;
		this.id = null;
		this.id_m = false;
		this.name = null;
		this.name_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(description_m)
			return true;
		if(id_m)
			return true;
		if(name_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(description_pa);
		list.add(id_pa);
		list.add(name_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(description_m)
			list.add(description_pa);
		if(id_m)
			list.add(id_pa);
		if(name_m)
			list.add(name_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(description_pa);
			list.add(id_pa);
			list.add(name_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
