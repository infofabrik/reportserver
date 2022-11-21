package net.datenwerke.rs.saiku.client.datasource.dto;

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
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.saiku.client.datasource.dto.pa.MondrianDatasourceDtoPA;
import net.datenwerke.rs.saiku.client.datasource.dto.posomap.MondrianDatasourceDto2PosoMap;
import net.datenwerke.rs.saiku.service.datasource.MondrianDatasource;

/**
 * Dto for {@link MondrianDatasource}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class MondrianDatasourceDto extends DatasourceDefinitionDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String mondrianSchema;
	private  boolean mondrianSchema_m;
	public static final String PROPERTY_MONDRIAN_SCHEMA = "dpi-mondriandatasource-mondrianschema";

	private transient static PropertyAccessor<MondrianDatasourceDto, String> mondrianSchema_pa = new PropertyAccessor<MondrianDatasourceDto, String>() {
		@Override
		public void setValue(MondrianDatasourceDto container, String object) {
			container.setMondrianSchema(object);
		}

		@Override
		public String getValue(MondrianDatasourceDto container) {
			return container.getMondrianSchema();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "mondrianSchema";
		}

		@Override
		public void setModified(MondrianDatasourceDto container, boolean modified) {
			container.mondrianSchema_m = modified;
		}

		@Override
		public boolean isModified(MondrianDatasourceDto container) {
			return container.isMondrianSchemaModified();
		}
	};

	private String password;
	private  boolean password_m;
	public static final String PROPERTY_PASSWORD = "dpi-mondriandatasource-password";

	private transient static PropertyAccessor<MondrianDatasourceDto, String> password_pa = new PropertyAccessor<MondrianDatasourceDto, String>() {
		@Override
		public void setValue(MondrianDatasourceDto container, String object) {
			container.setPassword(object);
		}

		@Override
		public String getValue(MondrianDatasourceDto container) {
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
		public void setModified(MondrianDatasourceDto container, boolean modified) {
			container.password_m = modified;
		}

		@Override
		public boolean isModified(MondrianDatasourceDto container) {
			return container.isPasswordModified();
		}
	};

	private String properties;
	private  boolean properties_m;
	public static final String PROPERTY_PROPERTIES = "dpi-mondriandatasource-properties";

	private transient static PropertyAccessor<MondrianDatasourceDto, String> properties_pa = new PropertyAccessor<MondrianDatasourceDto, String>() {
		@Override
		public void setValue(MondrianDatasourceDto container, String object) {
			container.setProperties(object);
		}

		@Override
		public String getValue(MondrianDatasourceDto container) {
			return container.getProperties();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "properties";
		}

		@Override
		public void setModified(MondrianDatasourceDto container, boolean modified) {
			container.properties_m = modified;
		}

		@Override
		public boolean isModified(MondrianDatasourceDto container) {
			return container.isPropertiesModified();
		}
	};

	private String url;
	private  boolean url_m;
	public static final String PROPERTY_URL = "dpi-mondriandatasource-url";

	private transient static PropertyAccessor<MondrianDatasourceDto, String> url_pa = new PropertyAccessor<MondrianDatasourceDto, String>() {
		@Override
		public void setValue(MondrianDatasourceDto container, String object) {
			container.setUrl(object);
		}

		@Override
		public String getValue(MondrianDatasourceDto container) {
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
		public void setModified(MondrianDatasourceDto container, boolean modified) {
			container.url_m = modified;
		}

		@Override
		public boolean isModified(MondrianDatasourceDto container) {
			return container.isUrlModified();
		}
	};

	private String username;
	private  boolean username_m;
	public static final String PROPERTY_USERNAME = "dpi-mondriandatasource-username";

	private transient static PropertyAccessor<MondrianDatasourceDto, String> username_pa = new PropertyAccessor<MondrianDatasourceDto, String>() {
		@Override
		public void setValue(MondrianDatasourceDto container, String object) {
			container.setUsername(object);
		}

		@Override
		public String getValue(MondrianDatasourceDto container) {
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
		public void setModified(MondrianDatasourceDto container, boolean modified) {
			container.username_m = modified;
		}

		@Override
		public boolean isModified(MondrianDatasourceDto container) {
			return container.isUsernameModified();
		}
	};

	private Boolean hasPassword;
	private  boolean hasPassword_m;
	public static final String PROPERTY_HAS_PASSWORD = "dpi-mondriandatasource-haspassword";

	private transient static PropertyAccessor<MondrianDatasourceDto, Boolean> hasPassword_pa = new PropertyAccessor<MondrianDatasourceDto, Boolean>() {
		@Override
		public void setValue(MondrianDatasourceDto container, Boolean object) {
			container.setHasPassword(object);
		}

		@Override
		public Boolean getValue(MondrianDatasourceDto container) {
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
		public void setModified(MondrianDatasourceDto container, boolean modified) {
			container.hasPassword_m = modified;
		}

		@Override
		public boolean isModified(MondrianDatasourceDto container) {
			return container.isHasPasswordModified();
		}
	};


	public MondrianDatasourceDto() {
		super();
	}

	public String getMondrianSchema()  {
		if(! isDtoProxy()){
			return this.mondrianSchema;
		}

		if(isMondrianSchemaModified())
			return this.mondrianSchema;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().mondrianSchema());

		return _value;
	}


	public void setMondrianSchema(String mondrianSchema)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getMondrianSchema();

		/* set new value */
		this.mondrianSchema = mondrianSchema;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(mondrianSchema_pa, oldValue, mondrianSchema, this.mondrianSchema_m));

		/* set indicator */
		this.mondrianSchema_m = true;

		this.fireObjectChangedEvent(MondrianDatasourceDtoPA.INSTANCE.mondrianSchema(), oldValue);
	}


	public boolean isMondrianSchemaModified()  {
		return mondrianSchema_m;
	}


	public static PropertyAccessor<MondrianDatasourceDto, String> getMondrianSchemaPropertyAccessor()  {
		return mondrianSchema_pa;
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

		this.fireObjectChangedEvent(MondrianDatasourceDtoPA.INSTANCE.password(), oldValue);
	}


	public boolean isPasswordModified()  {
		return password_m;
	}


	public static PropertyAccessor<MondrianDatasourceDto, String> getPasswordPropertyAccessor()  {
		return password_pa;
	}


	public String getProperties()  {
		if(! isDtoProxy()){
			return this.properties;
		}

		if(isPropertiesModified())
			return this.properties;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().properties());

		return _value;
	}


	public void setProperties(String properties)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getProperties();

		/* set new value */
		this.properties = properties;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(properties_pa, oldValue, properties, this.properties_m));

		/* set indicator */
		this.properties_m = true;

		this.fireObjectChangedEvent(MondrianDatasourceDtoPA.INSTANCE.properties(), oldValue);
	}


	public boolean isPropertiesModified()  {
		return properties_m;
	}


	public static PropertyAccessor<MondrianDatasourceDto, String> getPropertiesPropertyAccessor()  {
		return properties_pa;
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

		this.fireObjectChangedEvent(MondrianDatasourceDtoPA.INSTANCE.url(), oldValue);
	}


	public boolean isUrlModified()  {
		return url_m;
	}


	public static PropertyAccessor<MondrianDatasourceDto, String> getUrlPropertyAccessor()  {
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

		this.fireObjectChangedEvent(MondrianDatasourceDtoPA.INSTANCE.username(), oldValue);
	}


	public boolean isUsernameModified()  {
		return username_m;
	}


	public static PropertyAccessor<MondrianDatasourceDto, String> getUsernamePropertyAccessor()  {
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

		this.fireObjectChangedEvent(MondrianDatasourceDtoPA.INSTANCE.hasPassword(), oldValue);
	}


	public boolean isHasPasswordModified()  {
		return hasPassword_m;
	}


	public static PropertyAccessor<MondrianDatasourceDto, Boolean> getHasPasswordPropertyAccessor()  {
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
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof MondrianDatasourceDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((MondrianDatasourceDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new MondrianDatasourceDto2PosoMap();
	}

	public MondrianDatasourceDtoPA instantiatePropertyAccess()  {
		return GWT.create(MondrianDatasourceDtoPA.class);
	}

	public void clearModified()  {
		this.mondrianSchema = null;
		this.mondrianSchema_m = false;
		this.password = null;
		this.password_m = false;
		this.properties = null;
		this.properties_m = false;
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
		if(mondrianSchema_m)
			return true;
		if(password_m)
			return true;
		if(properties_m)
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
		list.add(mondrianSchema_pa);
		list.add(password_pa);
		list.add(properties_pa);
		list.add(url_pa);
		list.add(username_pa);
		list.add(hasPassword_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(mondrianSchema_m)
			list.add(mondrianSchema_pa);
		if(password_m)
			list.add(password_pa);
		if(properties_m)
			list.add(properties_pa);
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
			list.add(mondrianSchema_pa);
			list.add(password_pa);
			list.add(properties_pa);
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
