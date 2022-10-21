package net.datenwerke.rs.tabletemplate.client.tabletemplate.dto;

import com.google.gwt.core.client.GWT;
import java.lang.IllegalStateException;
import java.lang.Long;
import java.lang.NullPointerException;
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
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.pa.TableReportTemplateDtoPA;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.posomap.TableReportTemplateDto2PosoMap;
import net.datenwerke.rs.tabletemplate.service.tabletemplate.entities.TableReportTemplate;

/**
 * Dto for {@link TableReportTemplate}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class TableReportTemplateDto extends RsDto implements IdedDto {


	private static final long serialVersionUID = 1;

	private Long dtoId;


	/* Fields */
	private String contentType;
	private  boolean contentType_m;
	public static final String PROPERTY_CONTENT_TYPE = "dpi-tablereporttemplate-contenttype";

	private transient static PropertyAccessor<TableReportTemplateDto, String> contentType_pa = new PropertyAccessor<TableReportTemplateDto, String>() {
		@Override
		public void setValue(TableReportTemplateDto container, String object) {
			container.setContentType(object);
		}

		@Override
		public String getValue(TableReportTemplateDto container) {
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
		public void setModified(TableReportTemplateDto container, boolean modified) {
			container.contentType_m = modified;
		}

		@Override
		public boolean isModified(TableReportTemplateDto container) {
			return container.isContentTypeModified();
		}
	};

	private String description;
	private  boolean description_m;
	public static final String PROPERTY_DESCRIPTION = "dpi-tablereporttemplate-description";

	private transient static PropertyAccessor<TableReportTemplateDto, String> description_pa = new PropertyAccessor<TableReportTemplateDto, String>() {
		@Override
		public void setValue(TableReportTemplateDto container, String object) {
			container.setDescription(object);
		}

		@Override
		public String getValue(TableReportTemplateDto container) {
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
		public void setModified(TableReportTemplateDto container, boolean modified) {
			container.description_m = modified;
		}

		@Override
		public boolean isModified(TableReportTemplateDto container) {
			return container.isDescriptionModified();
		}
	};

	private String fileExtension;
	private  boolean fileExtension_m;
	public static final String PROPERTY_FILE_EXTENSION = "dpi-tablereporttemplate-fileextension";

	private transient static PropertyAccessor<TableReportTemplateDto, String> fileExtension_pa = new PropertyAccessor<TableReportTemplateDto, String>() {
		@Override
		public void setValue(TableReportTemplateDto container, String object) {
			container.setFileExtension(object);
		}

		@Override
		public String getValue(TableReportTemplateDto container) {
			return container.getFileExtension();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "fileExtension";
		}

		@Override
		public void setModified(TableReportTemplateDto container, boolean modified) {
			container.fileExtension_m = modified;
		}

		@Override
		public boolean isModified(TableReportTemplateDto container) {
			return container.isFileExtensionModified();
		}
	};

	private Long id;
	private  boolean id_m;
	public static final String PROPERTY_ID = "dpi-tablereporttemplate-id";

	private transient static PropertyAccessor<TableReportTemplateDto, Long> id_pa = new PropertyAccessor<TableReportTemplateDto, Long>() {
		@Override
		public void setValue(TableReportTemplateDto container, Long object) {
			// id field
		}

		@Override
		public Long getValue(TableReportTemplateDto container) {
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
		public void setModified(TableReportTemplateDto container, boolean modified) {
			container.id_m = modified;
		}

		@Override
		public boolean isModified(TableReportTemplateDto container) {
			return container.isIdModified();
		}
	};

	private String key;
	private  boolean key_m;
	public static final String PROPERTY_KEY = "dpi-tablereporttemplate-key";

	private transient static PropertyAccessor<TableReportTemplateDto, String> key_pa = new PropertyAccessor<TableReportTemplateDto, String>() {
		@Override
		public void setValue(TableReportTemplateDto container, String object) {
			container.setKey(object);
		}

		@Override
		public String getValue(TableReportTemplateDto container) {
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
		public void setModified(TableReportTemplateDto container, boolean modified) {
			container.key_m = modified;
		}

		@Override
		public boolean isModified(TableReportTemplateDto container) {
			return container.isKeyModified();
		}
	};

	private String name;
	private  boolean name_m;
	public static final String PROPERTY_NAME = "dpi-tablereporttemplate-name";

	private transient static PropertyAccessor<TableReportTemplateDto, String> name_pa = new PropertyAccessor<TableReportTemplateDto, String>() {
		@Override
		public void setValue(TableReportTemplateDto container, String object) {
			container.setName(object);
		}

		@Override
		public String getValue(TableReportTemplateDto container) {
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
		public void setModified(TableReportTemplateDto container, boolean modified) {
			container.name_m = modified;
		}

		@Override
		public boolean isModified(TableReportTemplateDto container) {
			return container.isNameModified();
		}
	};

	private String templateType;
	private  boolean templateType_m;
	public static final String PROPERTY_TEMPLATE_TYPE = "dpi-tablereporttemplate-templatetype";

	private transient static PropertyAccessor<TableReportTemplateDto, String> templateType_pa = new PropertyAccessor<TableReportTemplateDto, String>() {
		@Override
		public void setValue(TableReportTemplateDto container, String object) {
			container.setTemplateType(object);
		}

		@Override
		public String getValue(TableReportTemplateDto container) {
			return container.getTemplateType();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "templateType";
		}

		@Override
		public void setModified(TableReportTemplateDto container, boolean modified) {
			container.templateType_m = modified;
		}

		@Override
		public boolean isModified(TableReportTemplateDto container) {
			return container.isTemplateTypeModified();
		}
	};

	private Long temporaryId;
	private  boolean temporaryId_m;
	public static final String PROPERTY_TEMPORARY_ID = "dpi-tablereporttemplate-temporaryid";

	private transient static PropertyAccessor<TableReportTemplateDto, Long> temporaryId_pa = new PropertyAccessor<TableReportTemplateDto, Long>() {
		@Override
		public void setValue(TableReportTemplateDto container, Long object) {
			container.setTemporaryId(object);
		}

		@Override
		public Long getValue(TableReportTemplateDto container) {
			return container.getTemporaryId();
		}

		@Override
		public Class<?> getType() {
			return Long.class;
		}

		@Override
		public String getPath() {
			return "temporaryId";
		}

		@Override
		public void setModified(TableReportTemplateDto container, boolean modified) {
			container.temporaryId_m = modified;
		}

		@Override
		public boolean isModified(TableReportTemplateDto container) {
			return container.isTemporaryIdModified();
		}
	};


	public TableReportTemplateDto() {
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

		this.fireObjectChangedEvent(TableReportTemplateDtoPA.INSTANCE.contentType(), oldValue);
	}


	public boolean isContentTypeModified()  {
		return contentType_m;
	}


	public static PropertyAccessor<TableReportTemplateDto, String> getContentTypePropertyAccessor()  {
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

		this.fireObjectChangedEvent(TableReportTemplateDtoPA.INSTANCE.description(), oldValue);
	}


	public boolean isDescriptionModified()  {
		return description_m;
	}


	public static PropertyAccessor<TableReportTemplateDto, String> getDescriptionPropertyAccessor()  {
		return description_pa;
	}


	public String getFileExtension()  {
		if(! isDtoProxy()){
			return this.fileExtension;
		}

		if(isFileExtensionModified())
			return this.fileExtension;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().fileExtension());

		return _value;
	}


	public void setFileExtension(String fileExtension)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getFileExtension();

		/* set new value */
		this.fileExtension = fileExtension;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(fileExtension_pa, oldValue, fileExtension, this.fileExtension_m));

		/* set indicator */
		this.fileExtension_m = true;

		this.fireObjectChangedEvent(TableReportTemplateDtoPA.INSTANCE.fileExtension(), oldValue);
	}


	public boolean isFileExtensionModified()  {
		return fileExtension_m;
	}


	public static PropertyAccessor<TableReportTemplateDto, String> getFileExtensionPropertyAccessor()  {
		return fileExtension_pa;
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


	public static PropertyAccessor<TableReportTemplateDto, Long> getIdPropertyAccessor()  {
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

		this.fireObjectChangedEvent(TableReportTemplateDtoPA.INSTANCE.key(), oldValue);
	}


	public boolean isKeyModified()  {
		return key_m;
	}


	public static PropertyAccessor<TableReportTemplateDto, String> getKeyPropertyAccessor()  {
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

		this.fireObjectChangedEvent(TableReportTemplateDtoPA.INSTANCE.name(), oldValue);
	}


	public boolean isNameModified()  {
		return name_m;
	}


	public static PropertyAccessor<TableReportTemplateDto, String> getNamePropertyAccessor()  {
		return name_pa;
	}


	public String getTemplateType()  {
		if(! isDtoProxy()){
			return this.templateType;
		}

		if(isTemplateTypeModified())
			return this.templateType;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().templateType());

		return _value;
	}


	public void setTemplateType(String templateType)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getTemplateType();

		/* set new value */
		this.templateType = templateType;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(templateType_pa, oldValue, templateType, this.templateType_m));

		/* set indicator */
		this.templateType_m = true;

		this.fireObjectChangedEvent(TableReportTemplateDtoPA.INSTANCE.templateType(), oldValue);
	}


	public boolean isTemplateTypeModified()  {
		return templateType_m;
	}


	public static PropertyAccessor<TableReportTemplateDto, String> getTemplateTypePropertyAccessor()  {
		return templateType_pa;
	}


	public Long getTemporaryId()  {
		if(! isDtoProxy()){
			return this.temporaryId;
		}

		if(isTemporaryIdModified())
			return this.temporaryId;

		if(! GWT.isClient())
			return null;

		Long _value = dtoManager.getProperty(this, instantiatePropertyAccess().temporaryId());

		return _value;
	}


	public void setTemporaryId(Long temporaryId)  {
		/* old value */
		Long oldValue = null;
		if(GWT.isClient())
			oldValue = getTemporaryId();

		/* set new value */
		this.temporaryId = temporaryId;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(temporaryId_pa, oldValue, temporaryId, this.temporaryId_m));

		/* set indicator */
		this.temporaryId_m = true;

		this.fireObjectChangedEvent(TableReportTemplateDtoPA.INSTANCE.temporaryId(), oldValue);
	}


	public boolean isTemporaryIdModified()  {
		return temporaryId_m;
	}


	public static PropertyAccessor<TableReportTemplateDto, Long> getTemporaryIdPropertyAccessor()  {
		return temporaryId_pa;
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
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof TableReportTemplateDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((TableReportTemplateDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new TableReportTemplateDto2PosoMap();
	}

	public TableReportTemplateDtoPA instantiatePropertyAccess()  {
		return GWT.create(TableReportTemplateDtoPA.class);
	}

	public void clearModified()  {
		this.contentType = null;
		this.contentType_m = false;
		this.description = null;
		this.description_m = false;
		this.fileExtension = null;
		this.fileExtension_m = false;
		this.id = null;
		this.id_m = false;
		this.key = null;
		this.key_m = false;
		this.name = null;
		this.name_m = false;
		this.templateType = null;
		this.templateType_m = false;
		this.temporaryId = null;
		this.temporaryId_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(contentType_m)
			return true;
		if(description_m)
			return true;
		if(fileExtension_m)
			return true;
		if(id_m)
			return true;
		if(key_m)
			return true;
		if(name_m)
			return true;
		if(templateType_m)
			return true;
		if(temporaryId_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(contentType_pa);
		list.add(description_pa);
		list.add(fileExtension_pa);
		list.add(id_pa);
		list.add(key_pa);
		list.add(name_pa);
		list.add(templateType_pa);
		list.add(temporaryId_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(contentType_m)
			list.add(contentType_pa);
		if(description_m)
			list.add(description_pa);
		if(fileExtension_m)
			list.add(fileExtension_pa);
		if(id_m)
			list.add(id_pa);
		if(key_m)
			list.add(key_pa);
		if(name_m)
			list.add(name_pa);
		if(templateType_m)
			list.add(templateType_pa);
		if(temporaryId_m)
			list.add(temporaryId_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(id_pa);
			list.add(name_pa);
			list.add(temporaryId_pa);
		}
		if(view.compareTo(DtoView.LIST) >= 0){
			list.add(contentType_pa);
			list.add(description_pa);
			list.add(fileExtension_pa);
			list.add(key_pa);
			list.add(templateType_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
