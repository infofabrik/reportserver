package net.datenwerke.rs.localfsdatasink.client.localfsdatasink.dto;

import com.google.gwt.core.client.GWT;
import java.lang.NullPointerException;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.dto.pa.LocalFileSystemDatasinkDtoPA;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.dto.posomap.LocalFileSystemDatasinkDto2PosoMap;
import net.datenwerke.rs.localfsdatasink.service.localfsdatasink.definitions.LocalFileSystemDatasink;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * Dto for {@link LocalFileSystemDatasink}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class LocalFileSystemDatasinkDto extends DatasinkDefinitionDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String folder;
	private  boolean folder_m;
	public static final String PROPERTY_FOLDER = "dpi-localfilesystemdatasink-folder";

	private transient static PropertyAccessor<LocalFileSystemDatasinkDto, String> folder_pa = new PropertyAccessor<LocalFileSystemDatasinkDto, String>() {
		@Override
		public void setValue(LocalFileSystemDatasinkDto container, String object) {
			container.setFolder(object);
		}

		@Override
		public String getValue(LocalFileSystemDatasinkDto container) {
			return container.getFolder();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "folder";
		}

		@Override
		public void setModified(LocalFileSystemDatasinkDto container, boolean modified) {
			container.folder_m = modified;
		}

		@Override
		public boolean isModified(LocalFileSystemDatasinkDto container) {
			return container.isFolderModified();
		}
	};

	private String path;
	private  boolean path_m;
	public static final String PROPERTY_PATH = "dpi-localfilesystemdatasink-path";

	private transient static PropertyAccessor<LocalFileSystemDatasinkDto, String> path_pa = new PropertyAccessor<LocalFileSystemDatasinkDto, String>() {
		@Override
		public void setValue(LocalFileSystemDatasinkDto container, String object) {
			container.setPath(object);
		}

		@Override
		public String getValue(LocalFileSystemDatasinkDto container) {
			return container.getPath();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "path";
		}

		@Override
		public void setModified(LocalFileSystemDatasinkDto container, boolean modified) {
			container.path_m = modified;
		}

		@Override
		public boolean isModified(LocalFileSystemDatasinkDto container) {
			return container.isPathModified();
		}
	};


	public LocalFileSystemDatasinkDto() {
		super();
	}

	public String getFolder()  {
		if(! isDtoProxy()){
			return this.folder;
		}

		if(isFolderModified())
			return this.folder;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().folder());

		return _value;
	}


	public void setFolder(String folder)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getFolder();

		/* set new value */
		this.folder = folder;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(folder_pa, oldValue, folder, this.folder_m));

		/* set indicator */
		this.folder_m = true;

		this.fireObjectChangedEvent(LocalFileSystemDatasinkDtoPA.INSTANCE.folder(), oldValue);
	}


	public boolean isFolderModified()  {
		return folder_m;
	}


	public static PropertyAccessor<LocalFileSystemDatasinkDto, String> getFolderPropertyAccessor()  {
		return folder_pa;
	}


	public String getPath()  {
		if(! isDtoProxy()){
			return this.path;
		}

		if(isPathModified())
			return this.path;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().path());

		return _value;
	}


	public void setPath(String path)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getPath();

		/* set new value */
		this.path = path;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(path_pa, oldValue, path, this.path_m));

		/* set indicator */
		this.path_m = true;

		this.fireObjectChangedEvent(LocalFileSystemDatasinkDtoPA.INSTANCE.path(), oldValue);
	}


	public boolean isPathModified()  {
		return path_m;
	}


	public static PropertyAccessor<LocalFileSystemDatasinkDto, String> getPathPropertyAccessor()  {
		return path_pa;
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
	public BaseIcon toIcon()  {
		return BaseIcon.from("upload");
	}

	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof LocalFileSystemDatasinkDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((LocalFileSystemDatasinkDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new LocalFileSystemDatasinkDto2PosoMap();
	}

	public LocalFileSystemDatasinkDtoPA instantiatePropertyAccess()  {
		return GWT.create(LocalFileSystemDatasinkDtoPA.class);
	}

	public void clearModified()  {
		this.folder = null;
		this.folder_m = false;
		this.path = null;
		this.path_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(folder_m)
			return true;
		if(path_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(folder_pa);
		list.add(path_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(folder_m)
			list.add(folder_pa);
		if(path_m)
			list.add(path_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(folder_pa);
			list.add(path_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
