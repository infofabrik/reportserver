package net.datenwerke.rs.ftp.client.ftp.dto;

import com.google.gwt.core.client.GWT;
import java.lang.Boolean;
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
import net.datenwerke.rs.ftp.client.ftp.dto.pa.FtpDatasinkDtoPA;
import net.datenwerke.rs.ftp.client.ftp.dto.posomap.FtpDatasinkDto2PosoMap;
import net.datenwerke.rs.ftp.service.ftp.definitions.FtpDatasink;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * Dto for {@link FtpDatasink}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class FtpDatasinkDto extends DatasinkDefinitionDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String folder;
	private  boolean folder_m;
	public static final String PROPERTY_FOLDER = "dpi-ftpdatasink-folder";

	private transient static PropertyAccessor<FtpDatasinkDto, String> folder_pa = new PropertyAccessor<FtpDatasinkDto, String>() {
		@Override
		public void setValue(FtpDatasinkDto container, String object) {
			container.setFolder(object);
		}

		@Override
		public String getValue(FtpDatasinkDto container) {
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
		public void setModified(FtpDatasinkDto container, boolean modified) {
			container.folder_m = modified;
		}

		@Override
		public boolean isModified(FtpDatasinkDto container) {
			return container.isFolderModified();
		}
	};

	private String ftpMode;
	private  boolean ftpMode_m;
	public static final String PROPERTY_FTP_MODE = "dpi-ftpdatasink-ftpmode";

	private transient static PropertyAccessor<FtpDatasinkDto, String> ftpMode_pa = new PropertyAccessor<FtpDatasinkDto, String>() {
		@Override
		public void setValue(FtpDatasinkDto container, String object) {
			container.setFtpMode(object);
		}

		@Override
		public String getValue(FtpDatasinkDto container) {
			return container.getFtpMode();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "ftpMode";
		}

		@Override
		public void setModified(FtpDatasinkDto container, boolean modified) {
			container.ftpMode_m = modified;
		}

		@Override
		public boolean isModified(FtpDatasinkDto container) {
			return container.isFtpModeModified();
		}
	};

	private String host;
	private  boolean host_m;
	public static final String PROPERTY_HOST = "dpi-ftpdatasink-host";

	private transient static PropertyAccessor<FtpDatasinkDto, String> host_pa = new PropertyAccessor<FtpDatasinkDto, String>() {
		@Override
		public void setValue(FtpDatasinkDto container, String object) {
			container.setHost(object);
		}

		@Override
		public String getValue(FtpDatasinkDto container) {
			return container.getHost();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "host";
		}

		@Override
		public void setModified(FtpDatasinkDto container, boolean modified) {
			container.host_m = modified;
		}

		@Override
		public boolean isModified(FtpDatasinkDto container) {
			return container.isHostModified();
		}
	};

	private String password;
	private  boolean password_m;
	public static final String PROPERTY_PASSWORD = "dpi-ftpdatasink-password";

	private transient static PropertyAccessor<FtpDatasinkDto, String> password_pa = new PropertyAccessor<FtpDatasinkDto, String>() {
		@Override
		public void setValue(FtpDatasinkDto container, String object) {
			container.setPassword(object);
		}

		@Override
		public String getValue(FtpDatasinkDto container) {
			return container.getPassword();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "password";
		}

		@Override
		public void setModified(FtpDatasinkDto container, boolean modified) {
			container.password_m = modified;
		}

		@Override
		public boolean isModified(FtpDatasinkDto container) {
			return container.isPasswordModified();
		}
	};

	private int port;
	private  boolean port_m;
	public static final String PROPERTY_PORT = "dpi-ftpdatasink-port";

	private transient static PropertyAccessor<FtpDatasinkDto, Integer> port_pa = new PropertyAccessor<FtpDatasinkDto, Integer>() {
		@Override
		public void setValue(FtpDatasinkDto container, Integer object) {
			container.setPort(object);
		}

		@Override
		public Integer getValue(FtpDatasinkDto container) {
			return container.getPort();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "port";
		}

		@Override
		public void setModified(FtpDatasinkDto container, boolean modified) {
			container.port_m = modified;
		}

		@Override
		public boolean isModified(FtpDatasinkDto container) {
			return container.isPortModified();
		}
	};

	private String username;
	private  boolean username_m;
	public static final String PROPERTY_USERNAME = "dpi-ftpdatasink-username";

	private transient static PropertyAccessor<FtpDatasinkDto, String> username_pa = new PropertyAccessor<FtpDatasinkDto, String>() {
		@Override
		public void setValue(FtpDatasinkDto container, String object) {
			container.setUsername(object);
		}

		@Override
		public String getValue(FtpDatasinkDto container) {
			return container.getUsername();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "username";
		}

		@Override
		public void setModified(FtpDatasinkDto container, boolean modified) {
			container.username_m = modified;
		}

		@Override
		public boolean isModified(FtpDatasinkDto container) {
			return container.isUsernameModified();
		}
	};

	private Boolean hasPassword;
	private  boolean hasPassword_m;
	public static final String PROPERTY_HAS_PASSWORD = "dpi-ftpdatasink-haspassword";

	private transient static PropertyAccessor<FtpDatasinkDto, Boolean> hasPassword_pa = new PropertyAccessor<FtpDatasinkDto, Boolean>() {
		@Override
		public void setValue(FtpDatasinkDto container, Boolean object) {
			container.setHasPassword(object);
		}

		@Override
		public Boolean getValue(FtpDatasinkDto container) {
			return container.isHasPassword();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "hasPassword";
		}

		@Override
		public void setModified(FtpDatasinkDto container, boolean modified) {
			container.hasPassword_m = modified;
		}

		@Override
		public boolean isModified(FtpDatasinkDto container) {
			return container.isHasPasswordModified();
		}
	};


	public FtpDatasinkDto() {
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

		this.fireObjectChangedEvent(FtpDatasinkDtoPA.INSTANCE.folder(), oldValue);
	}


	public boolean isFolderModified()  {
		return folder_m;
	}


	public static PropertyAccessor<FtpDatasinkDto, String> getFolderPropertyAccessor()  {
		return folder_pa;
	}


	public String getFtpMode()  {
		if(! isDtoProxy()){
			return this.ftpMode;
		}

		if(isFtpModeModified())
			return this.ftpMode;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().ftpMode());

		return _value;
	}


	public void setFtpMode(String ftpMode)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getFtpMode();

		/* set new value */
		this.ftpMode = ftpMode;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(ftpMode_pa, oldValue, ftpMode, this.ftpMode_m));

		/* set indicator */
		this.ftpMode_m = true;

		this.fireObjectChangedEvent(FtpDatasinkDtoPA.INSTANCE.ftpMode(), oldValue);
	}


	public boolean isFtpModeModified()  {
		return ftpMode_m;
	}


	public static PropertyAccessor<FtpDatasinkDto, String> getFtpModePropertyAccessor()  {
		return ftpMode_pa;
	}


	public String getHost()  {
		if(! isDtoProxy()){
			return this.host;
		}

		if(isHostModified())
			return this.host;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().host());

		return _value;
	}


	public void setHost(String host)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getHost();

		/* set new value */
		this.host = host;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(host_pa, oldValue, host, this.host_m));

		/* set indicator */
		this.host_m = true;

		this.fireObjectChangedEvent(FtpDatasinkDtoPA.INSTANCE.host(), oldValue);
	}


	public boolean isHostModified()  {
		return host_m;
	}


	public static PropertyAccessor<FtpDatasinkDto, String> getHostPropertyAccessor()  {
		return host_pa;
	}


	public String getPassword()  {
		if(! isDtoProxy()){
			return this.password;
		}

		if(isPasswordModified())
			return this.password;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().password());

		return _value;
	}


	public void setPassword(String password)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getPassword();

		/* set new value */
		this.password = password;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(password_pa, oldValue, password, this.password_m));

		/* set indicator */
		this.password_m = true;

		this.fireObjectChangedEvent(FtpDatasinkDtoPA.INSTANCE.password(), oldValue);
	}


	public boolean isPasswordModified()  {
		return password_m;
	}


	public static PropertyAccessor<FtpDatasinkDto, String> getPasswordPropertyAccessor()  {
		return password_pa;
	}


	public int getPort()  {
		if(! isDtoProxy()){
			return this.port;
		}

		if(isPortModified())
			return this.port;

		if(! GWT.isClient())
			return 0;

		int _value = dtoManager.getProperty(this, instantiatePropertyAccess().port());

		return _value;
	}


	public void setPort(int port)  {
		/* old value */
		int oldValue = 0;
		if(GWT.isClient())
			oldValue = getPort();

		/* set new value */
		this.port = port;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(port_pa, oldValue, port, this.port_m));

		/* set indicator */
		this.port_m = true;

		this.fireObjectChangedEvent(FtpDatasinkDtoPA.INSTANCE.port(), oldValue);
	}


	public boolean isPortModified()  {
		return port_m;
	}


	public static PropertyAccessor<FtpDatasinkDto, Integer> getPortPropertyAccessor()  {
		return port_pa;
	}


	public String getUsername()  {
		if(! isDtoProxy()){
			return this.username;
		}

		if(isUsernameModified())
			return this.username;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().username());

		return _value;
	}


	public void setUsername(String username)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getUsername();

		/* set new value */
		this.username = username;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(username_pa, oldValue, username, this.username_m));

		/* set indicator */
		this.username_m = true;

		this.fireObjectChangedEvent(FtpDatasinkDtoPA.INSTANCE.username(), oldValue);
	}


	public boolean isUsernameModified()  {
		return username_m;
	}


	public static PropertyAccessor<FtpDatasinkDto, String> getUsernamePropertyAccessor()  {
		return username_pa;
	}


	public Boolean isHasPassword()  {
		if(! isDtoProxy()){
			return this.hasPassword;
		}

		if(isHasPasswordModified())
			return this.hasPassword;

		if(! GWT.isClient())
			return null;

		Boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().hasPassword());

		return _value;
	}


	public void setHasPassword(Boolean hasPassword)  {
		/* old value */
		Boolean oldValue = null;
		if(GWT.isClient())
			oldValue = isHasPassword();

		/* set new value */
		this.hasPassword = hasPassword;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(hasPassword_pa, oldValue, hasPassword, this.hasPassword_m));

		/* set indicator */
		this.hasPassword_m = true;

		this.fireObjectChangedEvent(FtpDatasinkDtoPA.INSTANCE.hasPassword(), oldValue);
	}


	public boolean isHasPasswordModified()  {
		return hasPassword_m;
	}


	public static PropertyAccessor<FtpDatasinkDto, Boolean> getHasPasswordPropertyAccessor()  {
		return hasPassword_pa;
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
		if(! (obj instanceof FtpDatasinkDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((FtpDatasinkDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new FtpDatasinkDto2PosoMap();
	}

	public FtpDatasinkDtoPA instantiatePropertyAccess()  {
		return GWT.create(FtpDatasinkDtoPA.class);
	}

	public void clearModified()  {
		this.folder = null;
		this.folder_m = false;
		this.ftpMode = null;
		this.ftpMode_m = false;
		this.host = null;
		this.host_m = false;
		this.password = null;
		this.password_m = false;
		this.port = 0;
		this.port_m = false;
		this.username = null;
		this.username_m = false;
		this.hasPassword = null;
		this.hasPassword_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(folder_m)
			return true;
		if(ftpMode_m)
			return true;
		if(host_m)
			return true;
		if(password_m)
			return true;
		if(port_m)
			return true;
		if(username_m)
			return true;
		if(hasPassword_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(folder_pa);
		list.add(ftpMode_pa);
		list.add(host_pa);
		list.add(password_pa);
		list.add(port_pa);
		list.add(username_pa);
		list.add(hasPassword_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(folder_m)
			list.add(folder_pa);
		if(ftpMode_m)
			list.add(ftpMode_pa);
		if(host_m)
			list.add(host_pa);
		if(password_m)
			list.add(password_pa);
		if(port_m)
			list.add(port_pa);
		if(username_m)
			list.add(username_pa);
		if(hasPassword_m)
			list.add(hasPassword_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(hasPassword_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(folder_pa);
			list.add(ftpMode_pa);
			list.add(host_pa);
			list.add(password_pa);
			list.add(port_pa);
			list.add(username_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
