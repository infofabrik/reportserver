package net.datenwerke.rs.base.client.datasources.dto;

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
import net.datenwerke.rs.base.client.datasources.dto.pa.DatabaseDatasourceDtoPA;
import net.datenwerke.rs.base.client.datasources.dto.posomap.DatabaseDatasourceDto2PosoMap;
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * Dto for {@link DatabaseDatasource}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class DatabaseDatasourceDto extends DatasourceDefinitionDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String databaseDescriptor;
	private  boolean databaseDescriptor_m;
	public static final String PROPERTY_DATABASE_DESCRIPTOR = "dpi-databasedatasource-databasedescriptor";

	private transient static PropertyAccessor<DatabaseDatasourceDto, String> databaseDescriptor_pa = new PropertyAccessor<DatabaseDatasourceDto, String>() {
		@Override
		public void setValue(DatabaseDatasourceDto container, String object) {
			container.setDatabaseDescriptor(object);
		}

		@Override
		public String getValue(DatabaseDatasourceDto container) {
			return container.getDatabaseDescriptor();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "databaseDescriptor";
		}

		@Override
		public void setModified(DatabaseDatasourceDto container, boolean modified) {
			container.databaseDescriptor_m = modified;
		}

		@Override
		public boolean isModified(DatabaseDatasourceDto container) {
			return container.isDatabaseDescriptorModified();
		}
	};

	private String jdbcProperties;
	private  boolean jdbcProperties_m;
	public static final String PROPERTY_JDBC_PROPERTIES = "dpi-databasedatasource-jdbcproperties";

	private transient static PropertyAccessor<DatabaseDatasourceDto, String> jdbcProperties_pa = new PropertyAccessor<DatabaseDatasourceDto, String>() {
		@Override
		public void setValue(DatabaseDatasourceDto container, String object) {
			container.setJdbcProperties(object);
		}

		@Override
		public String getValue(DatabaseDatasourceDto container) {
			return container.getJdbcProperties();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "jdbcProperties";
		}

		@Override
		public void setModified(DatabaseDatasourceDto container, boolean modified) {
			container.jdbcProperties_m = modified;
		}

		@Override
		public boolean isModified(DatabaseDatasourceDto container) {
			return container.isJdbcPropertiesModified();
		}
	};

	private String password;
	private  boolean password_m;
	public static final String PROPERTY_PASSWORD = "dpi-databasedatasource-password";

	private transient static PropertyAccessor<DatabaseDatasourceDto, String> password_pa = new PropertyAccessor<DatabaseDatasourceDto, String>() {
		@Override
		public void setValue(DatabaseDatasourceDto container, String object) {
			container.setPassword(object);
		}

		@Override
		public String getValue(DatabaseDatasourceDto container) {
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
		public void setModified(DatabaseDatasourceDto container, boolean modified) {
			container.password_m = modified;
		}

		@Override
		public boolean isModified(DatabaseDatasourceDto container) {
			return container.isPasswordModified();
		}
	};

	private String url;
	private  boolean url_m;
	public static final String PROPERTY_URL = "dpi-databasedatasource-url";

	private transient static PropertyAccessor<DatabaseDatasourceDto, String> url_pa = new PropertyAccessor<DatabaseDatasourceDto, String>() {
		@Override
		public void setValue(DatabaseDatasourceDto container, String object) {
			container.setUrl(object);
		}

		@Override
		public String getValue(DatabaseDatasourceDto container) {
			return container.getUrl();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "url";
		}

		@Override
		public void setModified(DatabaseDatasourceDto container, boolean modified) {
			container.url_m = modified;
		}

		@Override
		public boolean isModified(DatabaseDatasourceDto container) {
			return container.isUrlModified();
		}
	};

	private String username;
	private  boolean username_m;
	public static final String PROPERTY_USERNAME = "dpi-databasedatasource-username";

	private transient static PropertyAccessor<DatabaseDatasourceDto, String> username_pa = new PropertyAccessor<DatabaseDatasourceDto, String>() {
		@Override
		public void setValue(DatabaseDatasourceDto container, String object) {
			container.setUsername(object);
		}

		@Override
		public String getValue(DatabaseDatasourceDto container) {
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
		public void setModified(DatabaseDatasourceDto container, boolean modified) {
			container.username_m = modified;
		}

		@Override
		public boolean isModified(DatabaseDatasourceDto container) {
			return container.isUsernameModified();
		}
	};

	private Boolean hasPassword;
	private  boolean hasPassword_m;
	public static final String PROPERTY_HAS_PASSWORD = "dpi-databasedatasource-haspassword";

	private transient static PropertyAccessor<DatabaseDatasourceDto, Boolean> hasPassword_pa = new PropertyAccessor<DatabaseDatasourceDto, Boolean>() {
		@Override
		public void setValue(DatabaseDatasourceDto container, Boolean object) {
			container.setHasPassword(object);
		}

		@Override
		public Boolean getValue(DatabaseDatasourceDto container) {
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
		public void setModified(DatabaseDatasourceDto container, boolean modified) {
			container.hasPassword_m = modified;
		}

		@Override
		public boolean isModified(DatabaseDatasourceDto container) {
			return container.isHasPasswordModified();
		}
	};


	public DatabaseDatasourceDto() {
		super();
	}

	public String getDatabaseDescriptor()  {
		if(! isDtoProxy()){
			return this.databaseDescriptor;
		}

		if(isDatabaseDescriptorModified())
			return this.databaseDescriptor;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().databaseDescriptor());

		return _value;
	}


	public void setDatabaseDescriptor(String databaseDescriptor)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getDatabaseDescriptor();

		/* set new value */
		this.databaseDescriptor = databaseDescriptor;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(databaseDescriptor_pa, oldValue, databaseDescriptor, this.databaseDescriptor_m));

		/* set indicator */
		this.databaseDescriptor_m = true;

		this.fireObjectChangedEvent(DatabaseDatasourceDtoPA.INSTANCE.databaseDescriptor(), oldValue);
	}


	public boolean isDatabaseDescriptorModified()  {
		return databaseDescriptor_m;
	}


	public static PropertyAccessor<DatabaseDatasourceDto, String> getDatabaseDescriptorPropertyAccessor()  {
		return databaseDescriptor_pa;
	}


	public String getJdbcProperties()  {
		if(! isDtoProxy()){
			return this.jdbcProperties;
		}

		if(isJdbcPropertiesModified())
			return this.jdbcProperties;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().jdbcProperties());

		return _value;
	}


	public void setJdbcProperties(String jdbcProperties)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getJdbcProperties();

		/* set new value */
		this.jdbcProperties = jdbcProperties;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(jdbcProperties_pa, oldValue, jdbcProperties, this.jdbcProperties_m));

		/* set indicator */
		this.jdbcProperties_m = true;

		this.fireObjectChangedEvent(DatabaseDatasourceDtoPA.INSTANCE.jdbcProperties(), oldValue);
	}


	public boolean isJdbcPropertiesModified()  {
		return jdbcProperties_m;
	}


	public static PropertyAccessor<DatabaseDatasourceDto, String> getJdbcPropertiesPropertyAccessor()  {
		return jdbcProperties_pa;
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

		this.fireObjectChangedEvent(DatabaseDatasourceDtoPA.INSTANCE.password(), oldValue);
	}


	public boolean isPasswordModified()  {
		return password_m;
	}


	public static PropertyAccessor<DatabaseDatasourceDto, String> getPasswordPropertyAccessor()  {
		return password_pa;
	}


	public String getUrl()  {
		if(! isDtoProxy()){
			return this.url;
		}

		if(isUrlModified())
			return this.url;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().url());

		return _value;
	}


	public void setUrl(String url)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getUrl();

		/* set new value */
		this.url = url;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(url_pa, oldValue, url, this.url_m));

		/* set indicator */
		this.url_m = true;

		this.fireObjectChangedEvent(DatabaseDatasourceDtoPA.INSTANCE.url(), oldValue);
	}


	public boolean isUrlModified()  {
		return url_m;
	}


	public static PropertyAccessor<DatabaseDatasourceDto, String> getUrlPropertyAccessor()  {
		return url_pa;
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

		this.fireObjectChangedEvent(DatabaseDatasourceDtoPA.INSTANCE.username(), oldValue);
	}


	public boolean isUsernameModified()  {
		return username_m;
	}


	public static PropertyAccessor<DatabaseDatasourceDto, String> getUsernamePropertyAccessor()  {
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

		this.fireObjectChangedEvent(DatabaseDatasourceDtoPA.INSTANCE.hasPassword(), oldValue);
	}


	public boolean isHasPasswordModified()  {
		return hasPassword_m;
	}


	public static PropertyAccessor<DatabaseDatasourceDto, Boolean> getHasPasswordPropertyAccessor()  {
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
		return BaseIcon.from("database");
	}

	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof DatabaseDatasourceDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((DatabaseDatasourceDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new DatabaseDatasourceDto2PosoMap();
	}

	public DatabaseDatasourceDtoPA instantiatePropertyAccess()  {
		return GWT.create(DatabaseDatasourceDtoPA.class);
	}

	public void clearModified()  {
		this.databaseDescriptor = null;
		this.databaseDescriptor_m = false;
		this.jdbcProperties = null;
		this.jdbcProperties_m = false;
		this.password = null;
		this.password_m = false;
		this.url = null;
		this.url_m = false;
		this.username = null;
		this.username_m = false;
		this.hasPassword = null;
		this.hasPassword_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(databaseDescriptor_m)
			return true;
		if(jdbcProperties_m)
			return true;
		if(password_m)
			return true;
		if(url_m)
			return true;
		if(username_m)
			return true;
		if(hasPassword_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(databaseDescriptor_pa);
		list.add(jdbcProperties_pa);
		list.add(password_pa);
		list.add(url_pa);
		list.add(username_pa);
		list.add(hasPassword_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(databaseDescriptor_m)
			list.add(databaseDescriptor_pa);
		if(jdbcProperties_m)
			list.add(jdbcProperties_pa);
		if(password_m)
			list.add(password_pa);
		if(url_m)
			list.add(url_pa);
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
			list.add(databaseDescriptor_pa);
			list.add(jdbcProperties_pa);
			list.add(password_pa);
			list.add(url_pa);
			list.add(username_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
