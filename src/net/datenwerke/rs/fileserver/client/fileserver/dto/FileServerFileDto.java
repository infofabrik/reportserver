package net.datenwerke.rs.fileserver.client.fileserver.dto;

import com.google.gwt.core.client.GWT;
import java.lang.Integer;
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
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.pa.FileServerFileDtoPA;
import net.datenwerke.rs.fileserver.client.fileserver.dto.posomap.FileServerFileDto2PosoMap;
import net.datenwerke.rs.fileserver.client.fileserver.locale.FileServerMessages;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;

/**
 * Dto for {@link FileServerFile}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class FileServerFileDto extends AbstractFileServerNodeDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String contentType;
	private  boolean contentType_m;
	public static final String PROPERTY_CONTENT_TYPE = "dpi-fileserverfile-contenttype";

	private transient static PropertyAccessor<FileServerFileDto, String> contentType_pa = new PropertyAccessor<FileServerFileDto, String>() {
		@Override
		public void setValue(FileServerFileDto container, String object) {
			container.setContentType(object);
		}

		@Override
		public String getValue(FileServerFileDto container) {
			return container.getContentType();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "contentType";
		}

		@Override
		public void setModified(FileServerFileDto container, boolean modified) {
			container.contentType_m = modified;
		}

		@Override
		public boolean isModified(FileServerFileDto container) {
			return container.isContentTypeModified();
		}
	};

	private String description;
	private  boolean description_m;
	public static final String PROPERTY_DESCRIPTION = "dpi-fileserverfile-description";

	private transient static PropertyAccessor<FileServerFileDto, String> description_pa = new PropertyAccessor<FileServerFileDto, String>() {
		@Override
		public void setValue(FileServerFileDto container, String object) {
			container.setDescription(object);
		}

		@Override
		public String getValue(FileServerFileDto container) {
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
		public void setModified(FileServerFileDto container, boolean modified) {
			container.description_m = modified;
		}

		@Override
		public boolean isModified(FileServerFileDto container) {
			return container.isDescriptionModified();
		}
	};

	private String key;
	private  boolean key_m;
	public static final String PROPERTY_KEY = "dpi-fileserverfile-key";

	private transient static PropertyAccessor<FileServerFileDto, String> key_pa = new PropertyAccessor<FileServerFileDto, String>() {
		@Override
		public void setValue(FileServerFileDto container, String object) {
			container.setKey(object);
		}

		@Override
		public String getValue(FileServerFileDto container) {
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
		public void setModified(FileServerFileDto container, boolean modified) {
			container.key_m = modified;
		}

		@Override
		public boolean isModified(FileServerFileDto container) {
			return container.isKeyModified();
		}
	};

	private String name;
	private  boolean name_m;
	public static final String PROPERTY_NAME = "dpi-fileserverfile-name";

	private transient static PropertyAccessor<FileServerFileDto, String> name_pa = new PropertyAccessor<FileServerFileDto, String>() {
		@Override
		public void setValue(FileServerFileDto container, String object) {
			container.setName(object);
		}

		@Override
		public String getValue(FileServerFileDto container) {
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
		public void setModified(FileServerFileDto container, boolean modified) {
			container.name_m = modified;
		}

		@Override
		public boolean isModified(FileServerFileDto container) {
			return container.isNameModified();
		}
	};

	private Integer size;
	private  boolean size_m;
	public static final String PROPERTY_SIZE = "dpi-fileserverfile-size";

	private transient static PropertyAccessor<FileServerFileDto, Integer> size_pa = new PropertyAccessor<FileServerFileDto, Integer>() {
		@Override
		public void setValue(FileServerFileDto container, Integer object) {
			container.setSize(object);
		}

		@Override
		public Integer getValue(FileServerFileDto container) {
			return container.getSize();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "size";
		}

		@Override
		public void setModified(FileServerFileDto container, boolean modified) {
			container.size_m = modified;
		}

		@Override
		public boolean isModified(FileServerFileDto container) {
			return container.isSizeModified();
		}
	};


	public FileServerFileDto() {
		super();
	}

	public String getContentType()  {
		if(! isDtoProxy()){
			return this.contentType;
		}

		if(isContentTypeModified())
			return this.contentType;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().contentType());

		return _value;
	}


	public void setContentType(String contentType)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getContentType();

		/* set new value */
		this.contentType = contentType;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(contentType_pa, oldValue, contentType, this.contentType_m));

		/* set indicator */
		this.contentType_m = true;

		this.fireObjectChangedEvent(FileServerFileDtoPA.INSTANCE.contentType(), oldValue);
	}


	public boolean isContentTypeModified()  {
		return contentType_m;
	}


	public static PropertyAccessor<FileServerFileDto, String> getContentTypePropertyAccessor()  {
		return contentType_pa;
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

		this.fireObjectChangedEvent(FileServerFileDtoPA.INSTANCE.description(), oldValue);
	}


	public boolean isDescriptionModified()  {
		return description_m;
	}


	public static PropertyAccessor<FileServerFileDto, String> getDescriptionPropertyAccessor()  {
		return description_pa;
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

		this.fireObjectChangedEvent(FileServerFileDtoPA.INSTANCE.key(), oldValue);
	}


	public boolean isKeyModified()  {
		return key_m;
	}


	public static PropertyAccessor<FileServerFileDto, String> getKeyPropertyAccessor()  {
		return key_pa;
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

		this.fireObjectChangedEvent(FileServerFileDtoPA.INSTANCE.name(), oldValue);
	}


	public boolean isNameModified()  {
		return name_m;
	}


	public static PropertyAccessor<FileServerFileDto, String> getNamePropertyAccessor()  {
		return name_pa;
	}


	public Integer getSize()  {
		if(! isDtoProxy()){
			return this.size;
		}

		if(isSizeModified())
			return this.size;

		if(! GWT.isClient())
			return null;

		Integer _value = dtoManager.getProperty(this, instantiatePropertyAccess().size());

		return _value;
	}


	public void setSize(Integer size)  {
		/* old value */
		Integer oldValue = null;
		if(GWT.isClient())
			oldValue = getSize();

		/* set new value */
		this.size = size;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(size_pa, oldValue, size, this.size_m));

		/* set indicator */
		this.size_m = true;

		this.fireObjectChangedEvent(FileServerFileDtoPA.INSTANCE.size(), oldValue);
	}


	public boolean isSizeModified()  {
		return size_m;
	}


	public static PropertyAccessor<FileServerFileDto, Integer> getSizePropertyAccessor()  {
		return size_pa;
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
		return FileServerMessages.INSTANCE.file();
	}

	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof FileServerFileDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((FileServerFileDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new FileServerFileDto2PosoMap();
	}

	public FileServerFileDtoPA instantiatePropertyAccess()  {
		return GWT.create(FileServerFileDtoPA.class);
	}

	public void clearModified()  {
		this.contentType = null;
		this.contentType_m = false;
		this.description = null;
		this.description_m = false;
		this.key = null;
		this.key_m = false;
		this.name = null;
		this.name_m = false;
		this.size = null;
		this.size_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(contentType_m)
			return true;
		if(description_m)
			return true;
		if(key_m)
			return true;
		if(name_m)
			return true;
		if(size_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(contentType_pa);
		list.add(description_pa);
		list.add(key_pa);
		list.add(name_pa);
		list.add(size_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(contentType_m)
			list.add(contentType_pa);
		if(description_m)
			list.add(description_pa);
		if(key_m)
			list.add(key_pa);
		if(name_m)
			list.add(name_pa);
		if(size_m)
			list.add(size_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(description_pa);
			list.add(name_pa);
			list.add(size_pa);
		}
		if(view.compareTo(DtoView.LIST) >= 0){
			list.add(key_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(contentType_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
