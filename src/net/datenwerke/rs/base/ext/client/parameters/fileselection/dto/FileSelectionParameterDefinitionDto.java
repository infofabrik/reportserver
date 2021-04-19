package net.datenwerke.rs.base.ext.client.parameters.fileselection.dto;

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
import net.datenwerke.rs.base.client.parameters.locale.RsMessages;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.pa.FileSelectionParameterDefinitionDtoPA;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.posomap.FileSelectionParameterDefinitionDto2PosoMap;
import net.datenwerke.rs.base.ext.service.parameters.fileselection.FileSelectionParameterDefinition;
import net.datenwerke.rs.core.client.parameters.dto.decorator.ParameterDefinitionDtoDec;

/**
 * Dto for {@link FileSelectionParameterDefinition}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class FileSelectionParameterDefinitionDto extends ParameterDefinitionDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private boolean allowDownload;
	private  boolean allowDownload_m;
	public static final String PROPERTY_ALLOW_DOWNLOAD = "dpi-fileselectionparameterdefinition-allowdownload";

	private transient static PropertyAccessor<FileSelectionParameterDefinitionDto, Boolean> allowDownload_pa = new PropertyAccessor<FileSelectionParameterDefinitionDto, Boolean>() {
		@Override
		public void setValue(FileSelectionParameterDefinitionDto container, Boolean object) {
			container.setAllowDownload(object);
		}

		@Override
		public Boolean getValue(FileSelectionParameterDefinitionDto container) {
			return container.isAllowDownload();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "allowDownload";
		}

		@Override
		public void setModified(FileSelectionParameterDefinitionDto container, boolean modified) {
			container.allowDownload_m = modified;
		}

		@Override
		public boolean isModified(FileSelectionParameterDefinitionDto container) {
			return container.isAllowDownloadModified();
		}
	};

	private boolean allowFileServerSelection;
	private  boolean allowFileServerSelection_m;
	public static final String PROPERTY_ALLOW_FILE_SERVER_SELECTION = "dpi-fileselectionparameterdefinition-allowfileserverselection";

	private transient static PropertyAccessor<FileSelectionParameterDefinitionDto, Boolean> allowFileServerSelection_pa = new PropertyAccessor<FileSelectionParameterDefinitionDto, Boolean>() {
		@Override
		public void setValue(FileSelectionParameterDefinitionDto container, Boolean object) {
			container.setAllowFileServerSelection(object);
		}

		@Override
		public Boolean getValue(FileSelectionParameterDefinitionDto container) {
			return container.isAllowFileServerSelection();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "allowFileServerSelection";
		}

		@Override
		public void setModified(FileSelectionParameterDefinitionDto container, boolean modified) {
			container.allowFileServerSelection_m = modified;
		}

		@Override
		public boolean isModified(FileSelectionParameterDefinitionDto container) {
			return container.isAllowFileServerSelectionModified();
		}
	};

	private boolean allowFileUpload;
	private  boolean allowFileUpload_m;
	public static final String PROPERTY_ALLOW_FILE_UPLOAD = "dpi-fileselectionparameterdefinition-allowfileupload";

	private transient static PropertyAccessor<FileSelectionParameterDefinitionDto, Boolean> allowFileUpload_pa = new PropertyAccessor<FileSelectionParameterDefinitionDto, Boolean>() {
		@Override
		public void setValue(FileSelectionParameterDefinitionDto container, Boolean object) {
			container.setAllowFileUpload(object);
		}

		@Override
		public Boolean getValue(FileSelectionParameterDefinitionDto container) {
			return container.isAllowFileUpload();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "allowFileUpload";
		}

		@Override
		public void setModified(FileSelectionParameterDefinitionDto container, boolean modified) {
			container.allowFileUpload_m = modified;
		}

		@Override
		public boolean isModified(FileSelectionParameterDefinitionDto container) {
			return container.isAllowFileUploadModified();
		}
	};

	private boolean allowTeamSpaceSelection;
	private  boolean allowTeamSpaceSelection_m;
	public static final String PROPERTY_ALLOW_TEAM_SPACE_SELECTION = "dpi-fileselectionparameterdefinition-allowteamspaceselection";

	private transient static PropertyAccessor<FileSelectionParameterDefinitionDto, Boolean> allowTeamSpaceSelection_pa = new PropertyAccessor<FileSelectionParameterDefinitionDto, Boolean>() {
		@Override
		public void setValue(FileSelectionParameterDefinitionDto container, Boolean object) {
			container.setAllowTeamSpaceSelection(object);
		}

		@Override
		public Boolean getValue(FileSelectionParameterDefinitionDto container) {
			return container.isAllowTeamSpaceSelection();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "allowTeamSpaceSelection";
		}

		@Override
		public void setModified(FileSelectionParameterDefinitionDto container, boolean modified) {
			container.allowTeamSpaceSelection_m = modified;
		}

		@Override
		public boolean isModified(FileSelectionParameterDefinitionDto container) {
			return container.isAllowTeamSpaceSelectionModified();
		}
	};

	private String allowedFileExtensions;
	private  boolean allowedFileExtensions_m;
	public static final String PROPERTY_ALLOWED_FILE_EXTENSIONS = "dpi-fileselectionparameterdefinition-allowedfileextensions";

	private transient static PropertyAccessor<FileSelectionParameterDefinitionDto, String> allowedFileExtensions_pa = new PropertyAccessor<FileSelectionParameterDefinitionDto, String>() {
		@Override
		public void setValue(FileSelectionParameterDefinitionDto container, String object) {
			container.setAllowedFileExtensions(object);
		}

		@Override
		public String getValue(FileSelectionParameterDefinitionDto container) {
			return container.getAllowedFileExtensions();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "allowedFileExtensions";
		}

		@Override
		public void setModified(FileSelectionParameterDefinitionDto container, boolean modified) {
			container.allowedFileExtensions_m = modified;
		}

		@Override
		public boolean isModified(FileSelectionParameterDefinitionDto container) {
			return container.isAllowedFileExtensionsModified();
		}
	};

	private String fileSizeString;
	private  boolean fileSizeString_m;
	public static final String PROPERTY_FILE_SIZE_STRING = "dpi-fileselectionparameterdefinition-filesizestring";

	private transient static PropertyAccessor<FileSelectionParameterDefinitionDto, String> fileSizeString_pa = new PropertyAccessor<FileSelectionParameterDefinitionDto, String>() {
		@Override
		public void setValue(FileSelectionParameterDefinitionDto container, String object) {
			container.setFileSizeString(object);
		}

		@Override
		public String getValue(FileSelectionParameterDefinitionDto container) {
			return container.getFileSizeString();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "fileSizeString";
		}

		@Override
		public void setModified(FileSelectionParameterDefinitionDto container, boolean modified) {
			container.fileSizeString_m = modified;
		}

		@Override
		public boolean isModified(FileSelectionParameterDefinitionDto container) {
			return container.isFileSizeStringModified();
		}
	};

	private int height;
	private  boolean height_m;
	public static final String PROPERTY_HEIGHT = "dpi-fileselectionparameterdefinition-height";

	private transient static PropertyAccessor<FileSelectionParameterDefinitionDto, Integer> height_pa = new PropertyAccessor<FileSelectionParameterDefinitionDto, Integer>() {
		@Override
		public void setValue(FileSelectionParameterDefinitionDto container, Integer object) {
			container.setHeight(object);
		}

		@Override
		public Integer getValue(FileSelectionParameterDefinitionDto container) {
			return container.getHeight();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "height";
		}

		@Override
		public void setModified(FileSelectionParameterDefinitionDto container, boolean modified) {
			container.height_m = modified;
		}

		@Override
		public boolean isModified(FileSelectionParameterDefinitionDto container) {
			return container.isHeightModified();
		}
	};

	private Integer maxNumberOfFiles;
	private  boolean maxNumberOfFiles_m;
	public static final String PROPERTY_MAX_NUMBER_OF_FILES = "dpi-fileselectionparameterdefinition-maxnumberoffiles";

	private transient static PropertyAccessor<FileSelectionParameterDefinitionDto, Integer> maxNumberOfFiles_pa = new PropertyAccessor<FileSelectionParameterDefinitionDto, Integer>() {
		@Override
		public void setValue(FileSelectionParameterDefinitionDto container, Integer object) {
			container.setMaxNumberOfFiles(object);
		}

		@Override
		public Integer getValue(FileSelectionParameterDefinitionDto container) {
			return container.getMaxNumberOfFiles();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "maxNumberOfFiles";
		}

		@Override
		public void setModified(FileSelectionParameterDefinitionDto container, boolean modified) {
			container.maxNumberOfFiles_m = modified;
		}

		@Override
		public boolean isModified(FileSelectionParameterDefinitionDto container) {
			return container.isMaxNumberOfFilesModified();
		}
	};

	private Integer minNumberOfFiles;
	private  boolean minNumberOfFiles_m;
	public static final String PROPERTY_MIN_NUMBER_OF_FILES = "dpi-fileselectionparameterdefinition-minnumberoffiles";

	private transient static PropertyAccessor<FileSelectionParameterDefinitionDto, Integer> minNumberOfFiles_pa = new PropertyAccessor<FileSelectionParameterDefinitionDto, Integer>() {
		@Override
		public void setValue(FileSelectionParameterDefinitionDto container, Integer object) {
			container.setMinNumberOfFiles(object);
		}

		@Override
		public Integer getValue(FileSelectionParameterDefinitionDto container) {
			return container.getMinNumberOfFiles();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "minNumberOfFiles";
		}

		@Override
		public void setModified(FileSelectionParameterDefinitionDto container, boolean modified) {
			container.minNumberOfFiles_m = modified;
		}

		@Override
		public boolean isModified(FileSelectionParameterDefinitionDto container) {
			return container.isMinNumberOfFilesModified();
		}
	};

	private int width;
	private  boolean width_m;
	public static final String PROPERTY_WIDTH = "dpi-fileselectionparameterdefinition-width";

	private transient static PropertyAccessor<FileSelectionParameterDefinitionDto, Integer> width_pa = new PropertyAccessor<FileSelectionParameterDefinitionDto, Integer>() {
		@Override
		public void setValue(FileSelectionParameterDefinitionDto container, Integer object) {
			container.setWidth(object);
		}

		@Override
		public Integer getValue(FileSelectionParameterDefinitionDto container) {
			return container.getWidth();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "width";
		}

		@Override
		public void setModified(FileSelectionParameterDefinitionDto container, boolean modified) {
			container.width_m = modified;
		}

		@Override
		public boolean isModified(FileSelectionParameterDefinitionDto container) {
			return container.isWidthModified();
		}
	};

	private long fileSize;
	private  boolean fileSize_m;
	public static final String PROPERTY_FILE_SIZE = "dpi-fileselectionparameterdefinition-filesize";

	private transient static PropertyAccessor<FileSelectionParameterDefinitionDto, Long> fileSize_pa = new PropertyAccessor<FileSelectionParameterDefinitionDto, Long>() {
		@Override
		public void setValue(FileSelectionParameterDefinitionDto container, Long object) {
			container.setFileSize(object);
		}

		@Override
		public Long getValue(FileSelectionParameterDefinitionDto container) {
			return container.getFileSize();
		}

		@Override
		public Class<?> getType() {
			return Long.class;
		}

		@Override
		public String getPath() {
			return "fileSize";
		}

		@Override
		public void setModified(FileSelectionParameterDefinitionDto container, boolean modified) {
			container.fileSize_m = modified;
		}

		@Override
		public boolean isModified(FileSelectionParameterDefinitionDto container) {
			return container.isFileSizeModified();
		}
	};


	public FileSelectionParameterDefinitionDto() {
		super();
	}

	public boolean isAllowDownload()  {
		if(! isDtoProxy()){
			return this.allowDownload;
		}

		if(isAllowDownloadModified())
			return this.allowDownload;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().allowDownload());

		return _value;
	}


	public void setAllowDownload(boolean allowDownload)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isAllowDownload();

		/* set new value */
		this.allowDownload = allowDownload;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(allowDownload_pa, oldValue, allowDownload, this.allowDownload_m));

		/* set indicator */
		this.allowDownload_m = true;

		this.fireObjectChangedEvent(FileSelectionParameterDefinitionDtoPA.INSTANCE.allowDownload(), oldValue);
	}


	public boolean isAllowDownloadModified()  {
		return allowDownload_m;
	}


	public static PropertyAccessor<FileSelectionParameterDefinitionDto, Boolean> getAllowDownloadPropertyAccessor()  {
		return allowDownload_pa;
	}


	public boolean isAllowFileServerSelection()  {
		if(! isDtoProxy()){
			return this.allowFileServerSelection;
		}

		if(isAllowFileServerSelectionModified())
			return this.allowFileServerSelection;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().allowFileServerSelection());

		return _value;
	}


	public void setAllowFileServerSelection(boolean allowFileServerSelection)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isAllowFileServerSelection();

		/* set new value */
		this.allowFileServerSelection = allowFileServerSelection;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(allowFileServerSelection_pa, oldValue, allowFileServerSelection, this.allowFileServerSelection_m));

		/* set indicator */
		this.allowFileServerSelection_m = true;

		this.fireObjectChangedEvent(FileSelectionParameterDefinitionDtoPA.INSTANCE.allowFileServerSelection(), oldValue);
	}


	public boolean isAllowFileServerSelectionModified()  {
		return allowFileServerSelection_m;
	}


	public static PropertyAccessor<FileSelectionParameterDefinitionDto, Boolean> getAllowFileServerSelectionPropertyAccessor()  {
		return allowFileServerSelection_pa;
	}


	public boolean isAllowFileUpload()  {
		if(! isDtoProxy()){
			return this.allowFileUpload;
		}

		if(isAllowFileUploadModified())
			return this.allowFileUpload;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().allowFileUpload());

		return _value;
	}


	public void setAllowFileUpload(boolean allowFileUpload)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isAllowFileUpload();

		/* set new value */
		this.allowFileUpload = allowFileUpload;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(allowFileUpload_pa, oldValue, allowFileUpload, this.allowFileUpload_m));

		/* set indicator */
		this.allowFileUpload_m = true;

		this.fireObjectChangedEvent(FileSelectionParameterDefinitionDtoPA.INSTANCE.allowFileUpload(), oldValue);
	}


	public boolean isAllowFileUploadModified()  {
		return allowFileUpload_m;
	}


	public static PropertyAccessor<FileSelectionParameterDefinitionDto, Boolean> getAllowFileUploadPropertyAccessor()  {
		return allowFileUpload_pa;
	}


	public boolean isAllowTeamSpaceSelection()  {
		if(! isDtoProxy()){
			return this.allowTeamSpaceSelection;
		}

		if(isAllowTeamSpaceSelectionModified())
			return this.allowTeamSpaceSelection;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().allowTeamSpaceSelection());

		return _value;
	}


	public void setAllowTeamSpaceSelection(boolean allowTeamSpaceSelection)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isAllowTeamSpaceSelection();

		/* set new value */
		this.allowTeamSpaceSelection = allowTeamSpaceSelection;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(allowTeamSpaceSelection_pa, oldValue, allowTeamSpaceSelection, this.allowTeamSpaceSelection_m));

		/* set indicator */
		this.allowTeamSpaceSelection_m = true;

		this.fireObjectChangedEvent(FileSelectionParameterDefinitionDtoPA.INSTANCE.allowTeamSpaceSelection(), oldValue);
	}


	public boolean isAllowTeamSpaceSelectionModified()  {
		return allowTeamSpaceSelection_m;
	}


	public static PropertyAccessor<FileSelectionParameterDefinitionDto, Boolean> getAllowTeamSpaceSelectionPropertyAccessor()  {
		return allowTeamSpaceSelection_pa;
	}


	public String getAllowedFileExtensions()  {
		if(! isDtoProxy()){
			return this.allowedFileExtensions;
		}

		if(isAllowedFileExtensionsModified())
			return this.allowedFileExtensions;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().allowedFileExtensions());

		return _value;
	}


	public void setAllowedFileExtensions(String allowedFileExtensions)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getAllowedFileExtensions();

		/* set new value */
		this.allowedFileExtensions = allowedFileExtensions;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(allowedFileExtensions_pa, oldValue, allowedFileExtensions, this.allowedFileExtensions_m));

		/* set indicator */
		this.allowedFileExtensions_m = true;

		this.fireObjectChangedEvent(FileSelectionParameterDefinitionDtoPA.INSTANCE.allowedFileExtensions(), oldValue);
	}


	public boolean isAllowedFileExtensionsModified()  {
		return allowedFileExtensions_m;
	}


	public static PropertyAccessor<FileSelectionParameterDefinitionDto, String> getAllowedFileExtensionsPropertyAccessor()  {
		return allowedFileExtensions_pa;
	}


	public String getFileSizeString()  {
		if(! isDtoProxy()){
			return this.fileSizeString;
		}

		if(isFileSizeStringModified())
			return this.fileSizeString;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().fileSizeString());

		return _value;
	}


	public void setFileSizeString(String fileSizeString)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getFileSizeString();

		/* set new value */
		this.fileSizeString = fileSizeString;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(fileSizeString_pa, oldValue, fileSizeString, this.fileSizeString_m));

		/* set indicator */
		this.fileSizeString_m = true;

		this.fireObjectChangedEvent(FileSelectionParameterDefinitionDtoPA.INSTANCE.fileSizeString(), oldValue);
	}


	public boolean isFileSizeStringModified()  {
		return fileSizeString_m;
	}


	public static PropertyAccessor<FileSelectionParameterDefinitionDto, String> getFileSizeStringPropertyAccessor()  {
		return fileSizeString_pa;
	}


	public int getHeight()  {
		if(! isDtoProxy()){
			return this.height;
		}

		if(isHeightModified())
			return this.height;

		if(! GWT.isClient())
			return 0;

		int _value = dtoManager.getProperty(this, instantiatePropertyAccess().height());

		return _value;
	}


	public void setHeight(int height)  {
		/* old value */
		int oldValue = 0;
		if(GWT.isClient())
			oldValue = getHeight();

		/* set new value */
		this.height = height;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(height_pa, oldValue, height, this.height_m));

		/* set indicator */
		this.height_m = true;

		this.fireObjectChangedEvent(FileSelectionParameterDefinitionDtoPA.INSTANCE.height(), oldValue);
	}


	public boolean isHeightModified()  {
		return height_m;
	}


	public static PropertyAccessor<FileSelectionParameterDefinitionDto, Integer> getHeightPropertyAccessor()  {
		return height_pa;
	}


	public Integer getMaxNumberOfFiles()  {
		if(! isDtoProxy()){
			return this.maxNumberOfFiles;
		}

		if(isMaxNumberOfFilesModified())
			return this.maxNumberOfFiles;

		if(! GWT.isClient())
			return null;

		Integer _value = dtoManager.getProperty(this, instantiatePropertyAccess().maxNumberOfFiles());

		return _value;
	}


	public void setMaxNumberOfFiles(Integer maxNumberOfFiles)  {
		/* old value */
		Integer oldValue = null;
		if(GWT.isClient())
			oldValue = getMaxNumberOfFiles();

		/* set new value */
		this.maxNumberOfFiles = maxNumberOfFiles;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(maxNumberOfFiles_pa, oldValue, maxNumberOfFiles, this.maxNumberOfFiles_m));

		/* set indicator */
		this.maxNumberOfFiles_m = true;

		this.fireObjectChangedEvent(FileSelectionParameterDefinitionDtoPA.INSTANCE.maxNumberOfFiles(), oldValue);
	}


	public boolean isMaxNumberOfFilesModified()  {
		return maxNumberOfFiles_m;
	}


	public static PropertyAccessor<FileSelectionParameterDefinitionDto, Integer> getMaxNumberOfFilesPropertyAccessor()  {
		return maxNumberOfFiles_pa;
	}


	public Integer getMinNumberOfFiles()  {
		if(! isDtoProxy()){
			return this.minNumberOfFiles;
		}

		if(isMinNumberOfFilesModified())
			return this.minNumberOfFiles;

		if(! GWT.isClient())
			return null;

		Integer _value = dtoManager.getProperty(this, instantiatePropertyAccess().minNumberOfFiles());

		return _value;
	}


	public void setMinNumberOfFiles(Integer minNumberOfFiles)  {
		/* old value */
		Integer oldValue = null;
		if(GWT.isClient())
			oldValue = getMinNumberOfFiles();

		/* set new value */
		this.minNumberOfFiles = minNumberOfFiles;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(minNumberOfFiles_pa, oldValue, minNumberOfFiles, this.minNumberOfFiles_m));

		/* set indicator */
		this.minNumberOfFiles_m = true;

		this.fireObjectChangedEvent(FileSelectionParameterDefinitionDtoPA.INSTANCE.minNumberOfFiles(), oldValue);
	}


	public boolean isMinNumberOfFilesModified()  {
		return minNumberOfFiles_m;
	}


	public static PropertyAccessor<FileSelectionParameterDefinitionDto, Integer> getMinNumberOfFilesPropertyAccessor()  {
		return minNumberOfFiles_pa;
	}


	public int getWidth()  {
		if(! isDtoProxy()){
			return this.width;
		}

		if(isWidthModified())
			return this.width;

		if(! GWT.isClient())
			return 0;

		int _value = dtoManager.getProperty(this, instantiatePropertyAccess().width());

		return _value;
	}


	public void setWidth(int width)  {
		/* old value */
		int oldValue = 0;
		if(GWT.isClient())
			oldValue = getWidth();

		/* set new value */
		this.width = width;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(width_pa, oldValue, width, this.width_m));

		/* set indicator */
		this.width_m = true;

		this.fireObjectChangedEvent(FileSelectionParameterDefinitionDtoPA.INSTANCE.width(), oldValue);
	}


	public boolean isWidthModified()  {
		return width_m;
	}


	public static PropertyAccessor<FileSelectionParameterDefinitionDto, Integer> getWidthPropertyAccessor()  {
		return width_pa;
	}


	public long getFileSize()  {
		if(! isDtoProxy()){
			return this.fileSize;
		}

		if(isFileSizeModified())
			return this.fileSize;

		if(! GWT.isClient())
			return 0;

		long _value = dtoManager.getProperty(this, instantiatePropertyAccess().fileSize());

		return _value;
	}


	public void setFileSize(long fileSize)  {
		/* old value */
		long oldValue = 0;
		if(GWT.isClient())
			oldValue = getFileSize();

		/* set new value */
		this.fileSize = fileSize;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(fileSize_pa, oldValue, fileSize, this.fileSize_m));

		/* set indicator */
		this.fileSize_m = true;

		this.fireObjectChangedEvent(FileSelectionParameterDefinitionDtoPA.INSTANCE.fileSize(), oldValue);
	}


	public boolean isFileSizeModified()  {
		return fileSize_m;
	}


	public static PropertyAccessor<FileSelectionParameterDefinitionDto, Long> getFileSizePropertyAccessor()  {
		return fileSize_pa;
	}


	@Override
	public String toDisplayTitle()  {
		try{
			return RsMessages.INSTANCE.fileSelectionParameterText();
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
		if(! (obj instanceof FileSelectionParameterDefinitionDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((FileSelectionParameterDefinitionDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new FileSelectionParameterDefinitionDto2PosoMap();
	}

	public FileSelectionParameterDefinitionDtoPA instantiatePropertyAccess()  {
		return GWT.create(FileSelectionParameterDefinitionDtoPA.class);
	}

	public void clearModified()  {
		this.allowDownload = false;
		this.allowDownload_m = false;
		this.allowFileServerSelection = false;
		this.allowFileServerSelection_m = false;
		this.allowFileUpload = false;
		this.allowFileUpload_m = false;
		this.allowTeamSpaceSelection = false;
		this.allowTeamSpaceSelection_m = false;
		this.allowedFileExtensions = null;
		this.allowedFileExtensions_m = false;
		this.fileSizeString = null;
		this.fileSizeString_m = false;
		this.height = 0;
		this.height_m = false;
		this.maxNumberOfFiles = null;
		this.maxNumberOfFiles_m = false;
		this.minNumberOfFiles = null;
		this.minNumberOfFiles_m = false;
		this.width = 0;
		this.width_m = false;
		this.fileSize = 0;
		this.fileSize_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(allowDownload_m)
			return true;
		if(allowFileServerSelection_m)
			return true;
		if(allowFileUpload_m)
			return true;
		if(allowTeamSpaceSelection_m)
			return true;
		if(allowedFileExtensions_m)
			return true;
		if(fileSizeString_m)
			return true;
		if(height_m)
			return true;
		if(maxNumberOfFiles_m)
			return true;
		if(minNumberOfFiles_m)
			return true;
		if(width_m)
			return true;
		if(fileSize_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(allowDownload_pa);
		list.add(allowFileServerSelection_pa);
		list.add(allowFileUpload_pa);
		list.add(allowTeamSpaceSelection_pa);
		list.add(allowedFileExtensions_pa);
		list.add(fileSizeString_pa);
		list.add(height_pa);
		list.add(maxNumberOfFiles_pa);
		list.add(minNumberOfFiles_pa);
		list.add(width_pa);
		list.add(fileSize_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(allowDownload_m)
			list.add(allowDownload_pa);
		if(allowFileServerSelection_m)
			list.add(allowFileServerSelection_pa);
		if(allowFileUpload_m)
			list.add(allowFileUpload_pa);
		if(allowTeamSpaceSelection_m)
			list.add(allowTeamSpaceSelection_pa);
		if(allowedFileExtensions_m)
			list.add(allowedFileExtensions_pa);
		if(fileSizeString_m)
			list.add(fileSizeString_pa);
		if(height_m)
			list.add(height_pa);
		if(maxNumberOfFiles_m)
			list.add(maxNumberOfFiles_pa);
		if(minNumberOfFiles_m)
			list.add(minNumberOfFiles_pa);
		if(width_m)
			list.add(width_pa);
		if(fileSize_m)
			list.add(fileSize_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(allowDownload_pa);
			list.add(allowFileServerSelection_pa);
			list.add(allowFileUpload_pa);
			list.add(allowTeamSpaceSelection_pa);
			list.add(allowedFileExtensions_pa);
			list.add(fileSizeString_pa);
			list.add(height_pa);
			list.add(maxNumberOfFiles_pa);
			list.add(minNumberOfFiles_pa);
			list.add(width_pa);
			list.add(fileSize_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
