package net.datenwerke.rs.googledrive.client.googledrive.dto;

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
import net.datenwerke.rs.googledrive.client.googledrive.dto.pa.GoogleDriveDatasinkDtoPA;
import net.datenwerke.rs.googledrive.client.googledrive.dto.posomap.GoogleDriveDatasinkDto2PosoMap;
import net.datenwerke.rs.googledrive.service.googledrive.definitions.GoogleDriveDatasink;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * Dto for {@link GoogleDriveDatasink}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class GoogleDriveDatasinkDto extends DatasinkDefinitionDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String appKey;
	private  boolean appKey_m;
	public static final String PROPERTY_APP_KEY = "dpi-googledrivedatasink-appkey";

	private transient static PropertyAccessor<GoogleDriveDatasinkDto, String> appKey_pa = new PropertyAccessor<GoogleDriveDatasinkDto, String>() {
		@Override
		public void setValue(GoogleDriveDatasinkDto container, String object) {
			container.setAppKey(object);
		}

		@Override
		public String getValue(GoogleDriveDatasinkDto container) {
			return container.getAppKey();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "appKey";
		}

		@Override
		public void setModified(GoogleDriveDatasinkDto container, boolean modified) {
			container.appKey_m = modified;
		}

		@Override
		public boolean isModified(GoogleDriveDatasinkDto container) {
			return container.isAppKeyModified();
		}
	};

	private String folder;
	private  boolean folder_m;
	public static final String PROPERTY_FOLDER = "dpi-googledrivedatasink-folder";

	private transient static PropertyAccessor<GoogleDriveDatasinkDto, String> folder_pa = new PropertyAccessor<GoogleDriveDatasinkDto, String>() {
		@Override
		public void setValue(GoogleDriveDatasinkDto container, String object) {
			container.setFolder(object);
		}

		@Override
		public String getValue(GoogleDriveDatasinkDto container) {
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
		public void setModified(GoogleDriveDatasinkDto container, boolean modified) {
			container.folder_m = modified;
		}

		@Override
		public boolean isModified(GoogleDriveDatasinkDto container) {
			return container.isFolderModified();
		}
	};

	private String refreshToken;
	private  boolean refreshToken_m;
	public static final String PROPERTY_REFRESH_TOKEN = "dpi-googledrivedatasink-refreshtoken";

	private transient static PropertyAccessor<GoogleDriveDatasinkDto, String> refreshToken_pa = new PropertyAccessor<GoogleDriveDatasinkDto, String>() {
		@Override
		public void setValue(GoogleDriveDatasinkDto container, String object) {
			container.setRefreshToken(object);
		}

		@Override
		public String getValue(GoogleDriveDatasinkDto container) {
			return container.getRefreshToken();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "refreshToken";
		}

		@Override
		public void setModified(GoogleDriveDatasinkDto container, boolean modified) {
			container.refreshToken_m = modified;
		}

		@Override
		public boolean isModified(GoogleDriveDatasinkDto container) {
			return container.isRefreshTokenModified();
		}
	};

	private String secretKey;
	private  boolean secretKey_m;
	public static final String PROPERTY_SECRET_KEY = "dpi-googledrivedatasink-secretkey";

	private transient static PropertyAccessor<GoogleDriveDatasinkDto, String> secretKey_pa = new PropertyAccessor<GoogleDriveDatasinkDto, String>() {
		@Override
		public void setValue(GoogleDriveDatasinkDto container, String object) {
			container.setSecretKey(object);
		}

		@Override
		public String getValue(GoogleDriveDatasinkDto container) {
			return container.getSecretKey();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "secretKey";
		}

		@Override
		public void setModified(GoogleDriveDatasinkDto container, boolean modified) {
			container.secretKey_m = modified;
		}

		@Override
		public boolean isModified(GoogleDriveDatasinkDto container) {
			return container.isSecretKeyModified();
		}
	};

	private Boolean hasRefreshToken;
	private  boolean hasRefreshToken_m;
	public static final String PROPERTY_HAS_REFRESH_TOKEN = "dpi-googledrivedatasink-hasrefreshtoken";

	private transient static PropertyAccessor<GoogleDriveDatasinkDto, Boolean> hasRefreshToken_pa = new PropertyAccessor<GoogleDriveDatasinkDto, Boolean>() {
		@Override
		public void setValue(GoogleDriveDatasinkDto container, Boolean object) {
			container.setHasRefreshToken(object);
		}

		@Override
		public Boolean getValue(GoogleDriveDatasinkDto container) {
			return container.isHasRefreshToken();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "hasRefreshToken";
		}

		@Override
		public void setModified(GoogleDriveDatasinkDto container, boolean modified) {
			container.hasRefreshToken_m = modified;
		}

		@Override
		public boolean isModified(GoogleDriveDatasinkDto container) {
			return container.isHasRefreshTokenModified();
		}
	};

	private Boolean hasSecretKey;
	private  boolean hasSecretKey_m;
	public static final String PROPERTY_HAS_SECRET_KEY = "dpi-googledrivedatasink-hassecretkey";

	private transient static PropertyAccessor<GoogleDriveDatasinkDto, Boolean> hasSecretKey_pa = new PropertyAccessor<GoogleDriveDatasinkDto, Boolean>() {
		@Override
		public void setValue(GoogleDriveDatasinkDto container, Boolean object) {
			container.setHasSecretKey(object);
		}

		@Override
		public Boolean getValue(GoogleDriveDatasinkDto container) {
			return container.isHasSecretKey();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "hasSecretKey";
		}

		@Override
		public void setModified(GoogleDriveDatasinkDto container, boolean modified) {
			container.hasSecretKey_m = modified;
		}

		@Override
		public boolean isModified(GoogleDriveDatasinkDto container) {
			return container.isHasSecretKeyModified();
		}
	};


	public GoogleDriveDatasinkDto() {
		super();
	}

	public String getAppKey()  {
		if(! isDtoProxy()){
			return this.appKey;
		}

		if(isAppKeyModified())
			return this.appKey;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().appKey());

		return _value;
	}


	public void setAppKey(String appKey)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getAppKey();

		/* set new value */
		this.appKey = appKey;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(appKey_pa, oldValue, appKey, this.appKey_m));

		/* set indicator */
		this.appKey_m = true;

		this.fireObjectChangedEvent(GoogleDriveDatasinkDtoPA.INSTANCE.appKey(), oldValue);
	}


	public boolean isAppKeyModified()  {
		return appKey_m;
	}


	public static PropertyAccessor<GoogleDriveDatasinkDto, String> getAppKeyPropertyAccessor()  {
		return appKey_pa;
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

		this.fireObjectChangedEvent(GoogleDriveDatasinkDtoPA.INSTANCE.folder(), oldValue);
	}


	public boolean isFolderModified()  {
		return folder_m;
	}


	public static PropertyAccessor<GoogleDriveDatasinkDto, String> getFolderPropertyAccessor()  {
		return folder_pa;
	}


	public String getRefreshToken()  {
		if(! isDtoProxy()){
			return this.refreshToken;
		}

		if(isRefreshTokenModified())
			return this.refreshToken;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().refreshToken());

		return _value;
	}


	public void setRefreshToken(String refreshToken)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getRefreshToken();

		/* set new value */
		this.refreshToken = refreshToken;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(refreshToken_pa, oldValue, refreshToken, this.refreshToken_m));

		/* set indicator */
		this.refreshToken_m = true;

		this.fireObjectChangedEvent(GoogleDriveDatasinkDtoPA.INSTANCE.refreshToken(), oldValue);
	}


	public boolean isRefreshTokenModified()  {
		return refreshToken_m;
	}


	public static PropertyAccessor<GoogleDriveDatasinkDto, String> getRefreshTokenPropertyAccessor()  {
		return refreshToken_pa;
	}


	public String getSecretKey()  {
		if(! isDtoProxy()){
			return this.secretKey;
		}

		if(isSecretKeyModified())
			return this.secretKey;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().secretKey());

		return _value;
	}


	public void setSecretKey(String secretKey)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getSecretKey();

		/* set new value */
		this.secretKey = secretKey;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(secretKey_pa, oldValue, secretKey, this.secretKey_m));

		/* set indicator */
		this.secretKey_m = true;

		this.fireObjectChangedEvent(GoogleDriveDatasinkDtoPA.INSTANCE.secretKey(), oldValue);
	}


	public boolean isSecretKeyModified()  {
		return secretKey_m;
	}


	public static PropertyAccessor<GoogleDriveDatasinkDto, String> getSecretKeyPropertyAccessor()  {
		return secretKey_pa;
	}


	public Boolean isHasRefreshToken()  {
		if(! isDtoProxy()){
			return this.hasRefreshToken;
		}

		if(isHasRefreshTokenModified())
			return this.hasRefreshToken;

		if(! GWT.isClient())
			return null;

		Boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().hasRefreshToken());

		return _value;
	}


	public void setHasRefreshToken(Boolean hasRefreshToken)  {
		/* old value */
		Boolean oldValue = null;
		if(GWT.isClient())
			oldValue = isHasRefreshToken();

		/* set new value */
		this.hasRefreshToken = hasRefreshToken;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(hasRefreshToken_pa, oldValue, hasRefreshToken, this.hasRefreshToken_m));

		/* set indicator */
		this.hasRefreshToken_m = true;

		this.fireObjectChangedEvent(GoogleDriveDatasinkDtoPA.INSTANCE.hasRefreshToken(), oldValue);
	}


	public boolean isHasRefreshTokenModified()  {
		return hasRefreshToken_m;
	}


	public static PropertyAccessor<GoogleDriveDatasinkDto, Boolean> getHasRefreshTokenPropertyAccessor()  {
		return hasRefreshToken_pa;
	}


	public Boolean isHasSecretKey()  {
		if(! isDtoProxy()){
			return this.hasSecretKey;
		}

		if(isHasSecretKeyModified())
			return this.hasSecretKey;

		if(! GWT.isClient())
			return null;

		Boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().hasSecretKey());

		return _value;
	}


	public void setHasSecretKey(Boolean hasSecretKey)  {
		/* old value */
		Boolean oldValue = null;
		if(GWT.isClient())
			oldValue = isHasSecretKey();

		/* set new value */
		this.hasSecretKey = hasSecretKey;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(hasSecretKey_pa, oldValue, hasSecretKey, this.hasSecretKey_m));

		/* set indicator */
		this.hasSecretKey_m = true;

		this.fireObjectChangedEvent(GoogleDriveDatasinkDtoPA.INSTANCE.hasSecretKey(), oldValue);
	}


	public boolean isHasSecretKeyModified()  {
		return hasSecretKey_m;
	}


	public static PropertyAccessor<GoogleDriveDatasinkDto, Boolean> getHasSecretKeyPropertyAccessor()  {
		return hasSecretKey_pa;
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
		return BaseIcon.from("google_drive");
	}

	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof GoogleDriveDatasinkDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((GoogleDriveDatasinkDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new GoogleDriveDatasinkDto2PosoMap();
	}

	public GoogleDriveDatasinkDtoPA instantiatePropertyAccess()  {
		return GWT.create(GoogleDriveDatasinkDtoPA.class);
	}

	public void clearModified()  {
		this.appKey = null;
		this.appKey_m = false;
		this.folder = null;
		this.folder_m = false;
		this.refreshToken = null;
		this.refreshToken_m = false;
		this.secretKey = null;
		this.secretKey_m = false;
		this.hasRefreshToken = null;
		this.hasRefreshToken_m = false;
		this.hasSecretKey = null;
		this.hasSecretKey_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(appKey_m)
			return true;
		if(folder_m)
			return true;
		if(refreshToken_m)
			return true;
		if(secretKey_m)
			return true;
		if(hasRefreshToken_m)
			return true;
		if(hasSecretKey_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(appKey_pa);
		list.add(folder_pa);
		list.add(refreshToken_pa);
		list.add(secretKey_pa);
		list.add(hasRefreshToken_pa);
		list.add(hasSecretKey_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(appKey_m)
			list.add(appKey_pa);
		if(folder_m)
			list.add(folder_pa);
		if(refreshToken_m)
			list.add(refreshToken_pa);
		if(secretKey_m)
			list.add(secretKey_pa);
		if(hasRefreshToken_m)
			list.add(hasRefreshToken_pa);
		if(hasSecretKey_m)
			list.add(hasSecretKey_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(hasRefreshToken_pa);
			list.add(hasSecretKey_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(appKey_pa);
			list.add(folder_pa);
			list.add(refreshToken_pa);
			list.add(secretKey_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
