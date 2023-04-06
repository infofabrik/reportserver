package net.datenwerke.rs.remoteserver.client.remoteservermanager.dto;

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
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerDefinitionDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.pa.RemoteServerDtoPA;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.posomap.RemoteServerDto2PosoMap;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.RemoteServer;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * Dto for {@link RemoteServer}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class RemoteServerDto extends RemoteServerDefinitionDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String apikey;
	private  boolean apikey_m;
	public static final String PROPERTY_APIKEY = "dpi-remoteserver-apikey";

	private transient static PropertyAccessor<RemoteServerDto, String> apikey_pa = new PropertyAccessor<RemoteServerDto, String>() {
		@Override
		public void setValue(RemoteServerDto container, String object) {
			container.setApikey(object);
		}

		@Override
		public String getValue(RemoteServerDto container) {
			return container.getApikey();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "apikey";
		}

		@Override
		public void setModified(RemoteServerDto container, boolean modified) {
			container.apikey_m = modified;
		}

		@Override
		public boolean isModified(RemoteServerDto container) {
			return container.isApikeyModified();
		}
	};

	private String key;
	private  boolean key_m;
	public static final String PROPERTY_KEY = "dpi-remoteserver-key";

	private transient static PropertyAccessor<RemoteServerDto, String> key_pa = new PropertyAccessor<RemoteServerDto, String>() {
		@Override
		public void setValue(RemoteServerDto container, String object) {
			container.setKey(object);
		}

		@Override
		public String getValue(RemoteServerDto container) {
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
		public void setModified(RemoteServerDto container, boolean modified) {
			container.key_m = modified;
		}

		@Override
		public boolean isModified(RemoteServerDto container) {
			return container.isKeyModified();
		}
	};

	private String url;
	private  boolean url_m;
	public static final String PROPERTY_URL = "dpi-remoteserver-url";

	private transient static PropertyAccessor<RemoteServerDto, String> url_pa = new PropertyAccessor<RemoteServerDto, String>() {
		@Override
		public void setValue(RemoteServerDto container, String object) {
			container.setUrl(object);
		}

		@Override
		public String getValue(RemoteServerDto container) {
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
		public void setModified(RemoteServerDto container, boolean modified) {
			container.url_m = modified;
		}

		@Override
		public boolean isModified(RemoteServerDto container) {
			return container.isUrlModified();
		}
	};

	private String username;
	private  boolean username_m;
	public static final String PROPERTY_USERNAME = "dpi-remoteserver-username";

	private transient static PropertyAccessor<RemoteServerDto, String> username_pa = new PropertyAccessor<RemoteServerDto, String>() {
		@Override
		public void setValue(RemoteServerDto container, String object) {
			container.setUsername(object);
		}

		@Override
		public String getValue(RemoteServerDto container) {
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
		public void setModified(RemoteServerDto container, boolean modified) {
			container.username_m = modified;
		}

		@Override
		public boolean isModified(RemoteServerDto container) {
			return container.isUsernameModified();
		}
	};

	private Boolean hasApikey;
	private  boolean hasApikey_m;
	public static final String PROPERTY_HAS_APIKEY = "dpi-remoteserver-hasapikey";

	private transient static PropertyAccessor<RemoteServerDto, Boolean> hasApikey_pa = new PropertyAccessor<RemoteServerDto, Boolean>() {
		@Override
		public void setValue(RemoteServerDto container, Boolean object) {
			container.setHasApikey(object);
		}

		@Override
		public Boolean getValue(RemoteServerDto container) {
			return container.isHasApikey();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "hasApikey";
		}

		@Override
		public void setModified(RemoteServerDto container, boolean modified) {
			container.hasApikey_m = modified;
		}

		@Override
		public boolean isModified(RemoteServerDto container) {
			return container.isHasApikeyModified();
		}
	};


	public RemoteServerDto() {
		super();
	}

	public String getApikey()  {
		if(! isDtoProxy()){
			return this.apikey;
		}

		if(isApikeyModified())
			return this.apikey;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().apikey());

		return _value;
	}


	public void setApikey(String apikey)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getApikey();

		/* set new value */
		this.apikey = apikey;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(apikey_pa, oldValue, apikey, this.apikey_m));

		/* set indicator */
		this.apikey_m = true;

		this.fireObjectChangedEvent(RemoteServerDtoPA.INSTANCE.apikey(), oldValue);
	}


	public boolean isApikeyModified()  {
		return apikey_m;
	}


	public static PropertyAccessor<RemoteServerDto, String> getApikeyPropertyAccessor()  {
		return apikey_pa;
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

		this.fireObjectChangedEvent(RemoteServerDtoPA.INSTANCE.key(), oldValue);
	}


	public boolean isKeyModified()  {
		return key_m;
	}


	public static PropertyAccessor<RemoteServerDto, String> getKeyPropertyAccessor()  {
		return key_pa;
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

		this.fireObjectChangedEvent(RemoteServerDtoPA.INSTANCE.url(), oldValue);
	}


	public boolean isUrlModified()  {
		return url_m;
	}


	public static PropertyAccessor<RemoteServerDto, String> getUrlPropertyAccessor()  {
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

		this.fireObjectChangedEvent(RemoteServerDtoPA.INSTANCE.username(), oldValue);
	}


	public boolean isUsernameModified()  {
		return username_m;
	}


	public static PropertyAccessor<RemoteServerDto, String> getUsernamePropertyAccessor()  {
		return username_pa;
	}


	public Boolean isHasApikey()  {
		if(! isDtoProxy()){
			return this.hasApikey;
		}

		if(isHasApikeyModified())
			return this.hasApikey;

		if(! GWT.isClient())
			return null;

		Boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().hasApikey());

		return _value;
	}


	public void setHasApikey(Boolean hasApikey)  {
		/* old value */
		Boolean oldValue = null;
		if(GWT.isClient())
			oldValue = isHasApikey();

		/* set new value */
		this.hasApikey = hasApikey;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(hasApikey_pa, oldValue, hasApikey, this.hasApikey_m));

		/* set indicator */
		this.hasApikey_m = true;

		this.fireObjectChangedEvent(RemoteServerDtoPA.INSTANCE.hasApikey(), oldValue);
	}


	public boolean isHasApikeyModified()  {
		return hasApikey_m;
	}


	public static PropertyAccessor<RemoteServerDto, Boolean> getHasApikeyPropertyAccessor()  {
		return hasApikey_pa;
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
		if(! (obj instanceof RemoteServerDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((RemoteServerDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new RemoteServerDto2PosoMap();
	}

	public RemoteServerDtoPA instantiatePropertyAccess()  {
		return GWT.create(RemoteServerDtoPA.class);
	}

	public void clearModified()  {
		this.apikey = null;
		this.apikey_m = false;
		this.key = null;
		this.key_m = false;
		this.url = null;
		this.url_m = false;
		this.username = null;
		this.username_m = false;
		this.hasApikey = null;
		this.hasApikey_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(apikey_m)
			return true;
		if(key_m)
			return true;
		if(url_m)
			return true;
		if(username_m)
			return true;
		if(hasApikey_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(apikey_pa);
		list.add(key_pa);
		list.add(url_pa);
		list.add(username_pa);
		list.add(hasApikey_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(apikey_m)
			list.add(apikey_pa);
		if(key_m)
			list.add(key_pa);
		if(url_m)
			list.add(url_pa);
		if(username_m)
			list.add(username_pa);
		if(hasApikey_m)
			list.add(hasApikey_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(hasApikey_pa);
		}
		if(view.compareTo(DtoView.LIST) >= 0){
			list.add(key_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(apikey_pa);
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
