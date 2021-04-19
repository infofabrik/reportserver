package net.datenwerke.rs.core.client.datasourcemanager.dto;

import com.google.gwt.core.client.GWT;
import java.lang.NullPointerException;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.FolderDto;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.datasourcemanager.dto.AbstractDatasourceManagerNodeDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.pa.DatasourceFolderDtoPA;
import net.datenwerke.rs.core.client.datasourcemanager.dto.posomap.DatasourceFolderDto2PosoMap;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceFolder;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * Dto for {@link DatasourceFolder}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class DatasourceFolderDto extends AbstractDatasourceManagerNodeDto implements FolderDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String description;
	private  boolean description_m;
	public static final String PROPERTY_DESCRIPTION = "dpi-datasourcefolder-description";

	private transient static PropertyAccessor<DatasourceFolderDto, String> description_pa = new PropertyAccessor<DatasourceFolderDto, String>() {
		@Override
		public void setValue(DatasourceFolderDto container, String object) {
			container.setDescription(object);
		}

		@Override
		public String getValue(DatasourceFolderDto container) {
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
		public void setModified(DatasourceFolderDto container, boolean modified) {
			container.description_m = modified;
		}

		@Override
		public boolean isModified(DatasourceFolderDto container) {
			return container.isDescriptionModified();
		}
	};

	private String name;
	private  boolean name_m;
	public static final String PROPERTY_NAME = "dpi-datasourcefolder-name";

	private transient static PropertyAccessor<DatasourceFolderDto, String> name_pa = new PropertyAccessor<DatasourceFolderDto, String>() {
		@Override
		public void setValue(DatasourceFolderDto container, String object) {
			container.setName(object);
		}

		@Override
		public String getValue(DatasourceFolderDto container) {
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
		public void setModified(DatasourceFolderDto container, boolean modified) {
			container.name_m = modified;
		}

		@Override
		public boolean isModified(DatasourceFolderDto container) {
			return container.isNameModified();
		}
	};


	public DatasourceFolderDto() {
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

		this.fireObjectChangedEvent(DatasourceFolderDtoPA.INSTANCE.description(), oldValue);
	}


	public boolean isDescriptionModified()  {
		return description_m;
	}


	public static PropertyAccessor<DatasourceFolderDto, String> getDescriptionPropertyAccessor()  {
		return description_pa;
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

		this.fireObjectChangedEvent(DatasourceFolderDtoPA.INSTANCE.name(), oldValue);
	}


	public boolean isNameModified()  {
		return name_m;
	}


	public static PropertyAccessor<DatasourceFolderDto, String> getNamePropertyAccessor()  {
		return name_pa;
	}


	@Override
	public String toDisplayTitle()  {
		try{
			if(null == getName())
				return BaseMessages.INSTANCE.unnamed();
			return getName().toString();
		} catch(NullPointerException e){
			return BaseMessages.INSTANCE.unnamed();
		}
	}

	@Override
	public String toTypeDescription()  {
		return BaseMessages.INSTANCE.folder();
	}

	@Override
	public BaseIcon toIcon()  {
		return BaseIcon.from("folder");
	}

	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof DatasourceFolderDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((DatasourceFolderDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new DatasourceFolderDto2PosoMap();
	}

	public DatasourceFolderDtoPA instantiatePropertyAccess()  {
		return GWT.create(DatasourceFolderDtoPA.class);
	}

	public void clearModified()  {
		this.description = null;
		this.description_m = false;
		this.name = null;
		this.name_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(description_m)
			return true;
		if(name_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(description_pa);
		list.add(name_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(description_m)
			list.add(description_pa);
		if(name_m)
			list.add(name_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(description_pa);
			list.add(name_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
