package net.datenwerke.rs.fileserver.client.fileserver.dto;

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
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.pa.FileServerFolderDtoPA;
import net.datenwerke.rs.fileserver.client.fileserver.dto.posomap.FileServerFolderDto2PosoMap;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * Dto for {@link FileServerFolder}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class FileServerFolderDto extends AbstractFileServerNodeDto implements FolderDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String description;
	private  boolean description_m;
	public static final String PROPERTY_DESCRIPTION = "dpi-fileserverfolder-description";

	private transient static PropertyAccessor<FileServerFolderDto, String> description_pa = new PropertyAccessor<FileServerFolderDto, String>() {
		@Override
		public void setValue(FileServerFolderDto container, String object) {
			container.setDescription(object);
		}

		@Override
		public String getValue(FileServerFolderDto container) {
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
		public void setModified(FileServerFolderDto container, boolean modified) {
			container.description_m = modified;
		}

		@Override
		public boolean isModified(FileServerFolderDto container) {
			return container.isDescriptionModified();
		}
	};

	private String name;
	private  boolean name_m;
	public static final String PROPERTY_NAME = "dpi-fileserverfolder-name";

	private transient static PropertyAccessor<FileServerFolderDto, String> name_pa = new PropertyAccessor<FileServerFolderDto, String>() {
		@Override
		public void setValue(FileServerFolderDto container, String object) {
			container.setName(object);
		}

		@Override
		public String getValue(FileServerFolderDto container) {
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
		public void setModified(FileServerFolderDto container, boolean modified) {
			container.name_m = modified;
		}

		@Override
		public boolean isModified(FileServerFolderDto container) {
			return container.isNameModified();
		}
	};

	private boolean publiclyAccessible;
	private  boolean publiclyAccessible_m;
	public static final String PROPERTY_PUBLICLY_ACCESSIBLE = "dpi-fileserverfolder-publiclyaccessible";

	private transient static PropertyAccessor<FileServerFolderDto, Boolean> publiclyAccessible_pa = new PropertyAccessor<FileServerFolderDto, Boolean>() {
		@Override
		public void setValue(FileServerFolderDto container, Boolean object) {
			container.setPubliclyAccessible(object);
		}

		@Override
		public Boolean getValue(FileServerFolderDto container) {
			return container.isPubliclyAccessible();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "publiclyAccessible";
		}

		@Override
		public void setModified(FileServerFolderDto container, boolean modified) {
			container.publiclyAccessible_m = modified;
		}

		@Override
		public boolean isModified(FileServerFolderDto container) {
			return container.isPubliclyAccessibleModified();
		}
	};


	public FileServerFolderDto() {
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

		this.fireObjectChangedEvent(FileServerFolderDtoPA.INSTANCE.description(), oldValue);
	}


	public boolean isDescriptionModified()  {
		return description_m;
	}


	public static PropertyAccessor<FileServerFolderDto, String> getDescriptionPropertyAccessor()  {
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

		this.fireObjectChangedEvent(FileServerFolderDtoPA.INSTANCE.name(), oldValue);
	}


	public boolean isNameModified()  {
		return name_m;
	}


	public static PropertyAccessor<FileServerFolderDto, String> getNamePropertyAccessor()  {
		return name_pa;
	}


	public boolean isPubliclyAccessible()  {
		if(! isDtoProxy()){
			return this.publiclyAccessible;
		}

		if(isPubliclyAccessibleModified())
			return this.publiclyAccessible;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().publiclyAccessible());

		return _value;
	}


	public void setPubliclyAccessible(boolean publiclyAccessible)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isPubliclyAccessible();

		/* set new value */
		this.publiclyAccessible = publiclyAccessible;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(publiclyAccessible_pa, oldValue, publiclyAccessible, this.publiclyAccessible_m));

		/* set indicator */
		this.publiclyAccessible_m = true;

		this.fireObjectChangedEvent(FileServerFolderDtoPA.INSTANCE.publiclyAccessible(), oldValue);
	}


	public boolean isPubliclyAccessibleModified()  {
		return publiclyAccessible_m;
	}


	public static PropertyAccessor<FileServerFolderDto, Boolean> getPubliclyAccessiblePropertyAccessor()  {
		return publiclyAccessible_pa;
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
		if(! (obj instanceof FileServerFolderDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((FileServerFolderDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new FileServerFolderDto2PosoMap();
	}

	public FileServerFolderDtoPA instantiatePropertyAccess()  {
		return GWT.create(FileServerFolderDtoPA.class);
	}

	public void clearModified()  {
		this.description = null;
		this.description_m = false;
		this.name = null;
		this.name_m = false;
		this.publiclyAccessible = false;
		this.publiclyAccessible_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(description_m)
			return true;
		if(name_m)
			return true;
		if(publiclyAccessible_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(description_pa);
		list.add(name_pa);
		list.add(publiclyAccessible_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(description_m)
			list.add(description_pa);
		if(name_m)
			list.add(name_pa);
		if(publiclyAccessible_m)
			list.add(publiclyAccessible_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(description_pa);
			list.add(name_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(publiclyAccessible_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
