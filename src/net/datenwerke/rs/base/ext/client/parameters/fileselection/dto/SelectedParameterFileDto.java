package net.datenwerke.rs.base.ext.client.parameters.fileselection.dto;

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
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.IdedDto;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.eventbus.handlers.has.HasObjectChangedEventHandler;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.UploadedParameterFileDto;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.pa.SelectedParameterFileDtoPA;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.posomap.SelectedParameterFileDto2PosoMap;
import net.datenwerke.rs.base.ext.service.parameters.fileselection.SelectedParameterFile;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;

/**
 * Dto for {@link SelectedParameterFile}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class SelectedParameterFileDto extends RsDto implements IdedDto {


	private static final long serialVersionUID = 1;

	private Long dtoId;


	/* Fields */
	private AbstractFileServerNodeDto fileServerFile;
	private  boolean fileServerFile_m;
	public static final String PROPERTY_FILE_SERVER_FILE = "dpi-selectedparameterfile-fileserverfile";

	private transient static PropertyAccessor<SelectedParameterFileDto, AbstractFileServerNodeDto> fileServerFile_pa = new PropertyAccessor<SelectedParameterFileDto, AbstractFileServerNodeDto>() {
		@Override
		public void setValue(SelectedParameterFileDto container, AbstractFileServerNodeDto object) {
			container.setFileServerFile(object);
		}

		@Override
		public AbstractFileServerNodeDto getValue(SelectedParameterFileDto container) {
			return container.getFileServerFile();
		}

		@Override
		public Class<?> getType() {
			return AbstractFileServerNodeDto.class;
		}

		@Override
		public String getPath() {
			return "fileServerFile";
		}

		@Override
		public void setModified(SelectedParameterFileDto container, boolean modified) {
			container.fileServerFile_m = modified;
		}

		@Override
		public boolean isModified(SelectedParameterFileDto container) {
			return container.isFileServerFileModified();
		}
	};

	private Long id;
	private  boolean id_m;
	public static final String PROPERTY_ID = "dpi-selectedparameterfile-id";

	private transient static PropertyAccessor<SelectedParameterFileDto, Long> id_pa = new PropertyAccessor<SelectedParameterFileDto, Long>() {
		@Override
		public void setValue(SelectedParameterFileDto container, Long object) {
			// id field
		}

		@Override
		public Long getValue(SelectedParameterFileDto container) {
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
		public void setModified(SelectedParameterFileDto container, boolean modified) {
			container.id_m = modified;
		}

		@Override
		public boolean isModified(SelectedParameterFileDto container) {
			return container.isIdModified();
		}
	};

	private String name;
	private  boolean name_m;
	public static final String PROPERTY_NAME = "dpi-selectedparameterfile-name";

	private transient static PropertyAccessor<SelectedParameterFileDto, String> name_pa = new PropertyAccessor<SelectedParameterFileDto, String>() {
		@Override
		public void setValue(SelectedParameterFileDto container, String object) {
			container.setName(object);
		}

		@Override
		public String getValue(SelectedParameterFileDto container) {
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
		public void setModified(SelectedParameterFileDto container, boolean modified) {
			container.name_m = modified;
		}

		@Override
		public boolean isModified(SelectedParameterFileDto container) {
			return container.isNameModified();
		}
	};

	private AbstractTsDiskNodeDto teamSpaceFile;
	private  boolean teamSpaceFile_m;
	public static final String PROPERTY_TEAM_SPACE_FILE = "dpi-selectedparameterfile-teamspacefile";

	private transient static PropertyAccessor<SelectedParameterFileDto, AbstractTsDiskNodeDto> teamSpaceFile_pa = new PropertyAccessor<SelectedParameterFileDto, AbstractTsDiskNodeDto>() {
		@Override
		public void setValue(SelectedParameterFileDto container, AbstractTsDiskNodeDto object) {
			container.setTeamSpaceFile(object);
		}

		@Override
		public AbstractTsDiskNodeDto getValue(SelectedParameterFileDto container) {
			return container.getTeamSpaceFile();
		}

		@Override
		public Class<?> getType() {
			return AbstractTsDiskNodeDto.class;
		}

		@Override
		public String getPath() {
			return "teamSpaceFile";
		}

		@Override
		public void setModified(SelectedParameterFileDto container, boolean modified) {
			container.teamSpaceFile_m = modified;
		}

		@Override
		public boolean isModified(SelectedParameterFileDto container) {
			return container.isTeamSpaceFileModified();
		}
	};

	private UploadedParameterFileDto uploadedFile;
	private  boolean uploadedFile_m;
	public static final String PROPERTY_UPLOADED_FILE = "dpi-selectedparameterfile-uploadedfile";

	private transient static PropertyAccessor<SelectedParameterFileDto, UploadedParameterFileDto> uploadedFile_pa = new PropertyAccessor<SelectedParameterFileDto, UploadedParameterFileDto>() {
		@Override
		public void setValue(SelectedParameterFileDto container, UploadedParameterFileDto object) {
			container.setUploadedFile(object);
		}

		@Override
		public UploadedParameterFileDto getValue(SelectedParameterFileDto container) {
			return container.getUploadedFile();
		}

		@Override
		public Class<?> getType() {
			return UploadedParameterFileDto.class;
		}

		@Override
		public String getPath() {
			return "uploadedFile";
		}

		@Override
		public void setModified(SelectedParameterFileDto container, boolean modified) {
			container.uploadedFile_m = modified;
		}

		@Override
		public boolean isModified(SelectedParameterFileDto container) {
			return container.isUploadedFileModified();
		}
	};


	public SelectedParameterFileDto() {
		super();
	}

	public AbstractFileServerNodeDto getFileServerFile()  {
		if(! isDtoProxy()){
			return this.fileServerFile;
		}

		if(isFileServerFileModified())
			return this.fileServerFile;

		if(! GWT.isClient())
			return null;

		AbstractFileServerNodeDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().fileServerFile());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isFileServerFileModified())
						setFileServerFile((AbstractFileServerNodeDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setFileServerFile(AbstractFileServerNodeDto fileServerFile)  {
		/* old value */
		AbstractFileServerNodeDto oldValue = null;
		if(GWT.isClient())
			oldValue = getFileServerFile();

		/* set new value */
		this.fileServerFile = fileServerFile;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(fileServerFile_pa, oldValue, fileServerFile, this.fileServerFile_m));

		/* set indicator */
		this.fileServerFile_m = true;

		this.fireObjectChangedEvent(SelectedParameterFileDtoPA.INSTANCE.fileServerFile(), oldValue);
	}


	public boolean isFileServerFileModified()  {
		return fileServerFile_m;
	}


	public static PropertyAccessor<SelectedParameterFileDto, AbstractFileServerNodeDto> getFileServerFilePropertyAccessor()  {
		return fileServerFile_pa;
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


	public static PropertyAccessor<SelectedParameterFileDto, Long> getIdPropertyAccessor()  {
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

		this.fireObjectChangedEvent(SelectedParameterFileDtoPA.INSTANCE.name(), oldValue);
	}


	public boolean isNameModified()  {
		return name_m;
	}


	public static PropertyAccessor<SelectedParameterFileDto, String> getNamePropertyAccessor()  {
		return name_pa;
	}


	public AbstractTsDiskNodeDto getTeamSpaceFile()  {
		if(! isDtoProxy()){
			return this.teamSpaceFile;
		}

		if(isTeamSpaceFileModified())
			return this.teamSpaceFile;

		if(! GWT.isClient())
			return null;

		AbstractTsDiskNodeDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().teamSpaceFile());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isTeamSpaceFileModified())
						setTeamSpaceFile((AbstractTsDiskNodeDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setTeamSpaceFile(AbstractTsDiskNodeDto teamSpaceFile)  {
		/* old value */
		AbstractTsDiskNodeDto oldValue = null;
		if(GWT.isClient())
			oldValue = getTeamSpaceFile();

		/* set new value */
		this.teamSpaceFile = teamSpaceFile;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(teamSpaceFile_pa, oldValue, teamSpaceFile, this.teamSpaceFile_m));

		/* set indicator */
		this.teamSpaceFile_m = true;

		this.fireObjectChangedEvent(SelectedParameterFileDtoPA.INSTANCE.teamSpaceFile(), oldValue);
	}


	public boolean isTeamSpaceFileModified()  {
		return teamSpaceFile_m;
	}


	public static PropertyAccessor<SelectedParameterFileDto, AbstractTsDiskNodeDto> getTeamSpaceFilePropertyAccessor()  {
		return teamSpaceFile_pa;
	}


	public UploadedParameterFileDto getUploadedFile()  {
		if(! isDtoProxy()){
			return this.uploadedFile;
		}

		if(isUploadedFileModified())
			return this.uploadedFile;

		if(! GWT.isClient())
			return null;

		UploadedParameterFileDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().uploadedFile());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isUploadedFileModified())
						setUploadedFile((UploadedParameterFileDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setUploadedFile(UploadedParameterFileDto uploadedFile)  {
		/* old value */
		UploadedParameterFileDto oldValue = null;
		if(GWT.isClient())
			oldValue = getUploadedFile();

		/* set new value */
		this.uploadedFile = uploadedFile;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(uploadedFile_pa, oldValue, uploadedFile, this.uploadedFile_m));

		/* set indicator */
		this.uploadedFile_m = true;

		this.fireObjectChangedEvent(SelectedParameterFileDtoPA.INSTANCE.uploadedFile(), oldValue);
	}


	public boolean isUploadedFileModified()  {
		return uploadedFile_m;
	}


	public static PropertyAccessor<SelectedParameterFileDto, UploadedParameterFileDto> getUploadedFilePropertyAccessor()  {
		return uploadedFile_pa;
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
		if(! (obj instanceof SelectedParameterFileDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((SelectedParameterFileDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new SelectedParameterFileDto2PosoMap();
	}

	public SelectedParameterFileDtoPA instantiatePropertyAccess()  {
		return GWT.create(SelectedParameterFileDtoPA.class);
	}

	public void clearModified()  {
		this.fileServerFile = null;
		this.fileServerFile_m = false;
		this.id = null;
		this.id_m = false;
		this.name = null;
		this.name_m = false;
		this.teamSpaceFile = null;
		this.teamSpaceFile_m = false;
		this.uploadedFile = null;
		this.uploadedFile_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(fileServerFile_m)
			return true;
		if(id_m)
			return true;
		if(name_m)
			return true;
		if(teamSpaceFile_m)
			return true;
		if(uploadedFile_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(fileServerFile_pa);
		list.add(id_pa);
		list.add(name_pa);
		list.add(teamSpaceFile_pa);
		list.add(uploadedFile_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(fileServerFile_m)
			list.add(fileServerFile_pa);
		if(id_m)
			list.add(id_pa);
		if(name_m)
			list.add(name_pa);
		if(teamSpaceFile_m)
			list.add(teamSpaceFile_pa);
		if(uploadedFile_m)
			list.add(uploadedFile_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(id_pa);
			list.add(name_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(fileServerFile_pa);
			list.add(teamSpaceFile_pa);
			list.add(uploadedFile_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(fileServerFile_pa);
		list.add(teamSpaceFile_pa);
		list.add(uploadedFile_pa);
		return list;
	}



	net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto wl_0;
	net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto wl_1;
	net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.UploadedParameterFileDto wl_2;

}
