package net.datenwerke.rs.amazons3.client.amazons3.dto;

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
import net.datenwerke.rs.amazons3.client.amazons3.dto.pa.AmazonS3DatasinkDtoPA;
import net.datenwerke.rs.amazons3.client.amazons3.dto.posomap.AmazonS3DatasinkDto2PosoMap;
import net.datenwerke.rs.amazons3.service.amazons3.definitions.AmazonS3Datasink;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * Dto for {@link AmazonS3Datasink}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class AmazonS3DatasinkDto extends DatasinkDefinitionDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String appKey;
	private  boolean appKey_m;
	public static final String PROPERTY_APP_KEY = "dpi-amazons3datasink-appkey";

	private transient static PropertyAccessor<AmazonS3DatasinkDto, String> appKey_pa = new PropertyAccessor<AmazonS3DatasinkDto, String>() {
		@Override
		public void setValue(AmazonS3DatasinkDto container, String object) {
			container.setAppKey(object);
		}

		@Override
		public String getValue(AmazonS3DatasinkDto container) {
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
		public void setModified(AmazonS3DatasinkDto container, boolean modified) {
			container.appKey_m = modified;
		}

		@Override
		public boolean isModified(AmazonS3DatasinkDto container) {
			return container.isAppKeyModified();
		}
	};

	private String bucketName;
	private  boolean bucketName_m;
	public static final String PROPERTY_BUCKET_NAME = "dpi-amazons3datasink-bucketname";

	private transient static PropertyAccessor<AmazonS3DatasinkDto, String> bucketName_pa = new PropertyAccessor<AmazonS3DatasinkDto, String>() {
		@Override
		public void setValue(AmazonS3DatasinkDto container, String object) {
			container.setBucketName(object);
		}

		@Override
		public String getValue(AmazonS3DatasinkDto container) {
			return container.getBucketName();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "bucketName";
		}

		@Override
		public void setModified(AmazonS3DatasinkDto container, boolean modified) {
			container.bucketName_m = modified;
		}

		@Override
		public boolean isModified(AmazonS3DatasinkDto container) {
			return container.isBucketNameModified();
		}
	};

	private String folder;
	private  boolean folder_m;
	public static final String PROPERTY_FOLDER = "dpi-amazons3datasink-folder";

	private transient static PropertyAccessor<AmazonS3DatasinkDto, String> folder_pa = new PropertyAccessor<AmazonS3DatasinkDto, String>() {
		@Override
		public void setValue(AmazonS3DatasinkDto container, String object) {
			container.setFolder(object);
		}

		@Override
		public String getValue(AmazonS3DatasinkDto container) {
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
		public void setModified(AmazonS3DatasinkDto container, boolean modified) {
			container.folder_m = modified;
		}

		@Override
		public boolean isModified(AmazonS3DatasinkDto container) {
			return container.isFolderModified();
		}
	};

	private String regionName;
	private  boolean regionName_m;
	public static final String PROPERTY_REGION_NAME = "dpi-amazons3datasink-regionname";

	private transient static PropertyAccessor<AmazonS3DatasinkDto, String> regionName_pa = new PropertyAccessor<AmazonS3DatasinkDto, String>() {
		@Override
		public void setValue(AmazonS3DatasinkDto container, String object) {
			container.setRegionName(object);
		}

		@Override
		public String getValue(AmazonS3DatasinkDto container) {
			return container.getRegionName();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "regionName";
		}

		@Override
		public void setModified(AmazonS3DatasinkDto container, boolean modified) {
			container.regionName_m = modified;
		}

		@Override
		public boolean isModified(AmazonS3DatasinkDto container) {
			return container.isRegionNameModified();
		}
	};

	private String secretKey;
	private  boolean secretKey_m;
	public static final String PROPERTY_SECRET_KEY = "dpi-amazons3datasink-secretkey";

	private transient static PropertyAccessor<AmazonS3DatasinkDto, String> secretKey_pa = new PropertyAccessor<AmazonS3DatasinkDto, String>() {
		@Override
		public void setValue(AmazonS3DatasinkDto container, String object) {
			container.setSecretKey(object);
		}

		@Override
		public String getValue(AmazonS3DatasinkDto container) {
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
		public void setModified(AmazonS3DatasinkDto container, boolean modified) {
			container.secretKey_m = modified;
		}

		@Override
		public boolean isModified(AmazonS3DatasinkDto container) {
			return container.isSecretKeyModified();
		}
	};

	private Boolean hasSecretKey;
	private  boolean hasSecretKey_m;
	public static final String PROPERTY_HAS_SECRET_KEY = "dpi-amazons3datasink-hassecretkey";

	private transient static PropertyAccessor<AmazonS3DatasinkDto, Boolean> hasSecretKey_pa = new PropertyAccessor<AmazonS3DatasinkDto, Boolean>() {
		@Override
		public void setValue(AmazonS3DatasinkDto container, Boolean object) {
			container.setHasSecretKey(object);
		}

		@Override
		public Boolean getValue(AmazonS3DatasinkDto container) {
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
		public void setModified(AmazonS3DatasinkDto container, boolean modified) {
			container.hasSecretKey_m = modified;
		}

		@Override
		public boolean isModified(AmazonS3DatasinkDto container) {
			return container.isHasSecretKeyModified();
		}
	};


	public AmazonS3DatasinkDto() {
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

		this.fireObjectChangedEvent(AmazonS3DatasinkDtoPA.INSTANCE.appKey(), oldValue);
	}


	public boolean isAppKeyModified()  {
		return appKey_m;
	}


	public static PropertyAccessor<AmazonS3DatasinkDto, String> getAppKeyPropertyAccessor()  {
		return appKey_pa;
	}


	public String getBucketName()  {
		if(! isDtoProxy()){
			return this.bucketName;
		}

		if(isBucketNameModified())
			return this.bucketName;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().bucketName());

		return _value;
	}


	public void setBucketName(String bucketName)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getBucketName();

		/* set new value */
		this.bucketName = bucketName;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(bucketName_pa, oldValue, bucketName, this.bucketName_m));

		/* set indicator */
		this.bucketName_m = true;

		this.fireObjectChangedEvent(AmazonS3DatasinkDtoPA.INSTANCE.bucketName(), oldValue);
	}


	public boolean isBucketNameModified()  {
		return bucketName_m;
	}


	public static PropertyAccessor<AmazonS3DatasinkDto, String> getBucketNamePropertyAccessor()  {
		return bucketName_pa;
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

		this.fireObjectChangedEvent(AmazonS3DatasinkDtoPA.INSTANCE.folder(), oldValue);
	}


	public boolean isFolderModified()  {
		return folder_m;
	}


	public static PropertyAccessor<AmazonS3DatasinkDto, String> getFolderPropertyAccessor()  {
		return folder_pa;
	}


	public String getRegionName()  {
		if(! isDtoProxy()){
			return this.regionName;
		}

		if(isRegionNameModified())
			return this.regionName;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().regionName());

		return _value;
	}


	public void setRegionName(String regionName)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getRegionName();

		/* set new value */
		this.regionName = regionName;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(regionName_pa, oldValue, regionName, this.regionName_m));

		/* set indicator */
		this.regionName_m = true;

		this.fireObjectChangedEvent(AmazonS3DatasinkDtoPA.INSTANCE.regionName(), oldValue);
	}


	public boolean isRegionNameModified()  {
		return regionName_m;
	}


	public static PropertyAccessor<AmazonS3DatasinkDto, String> getRegionNamePropertyAccessor()  {
		return regionName_pa;
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

		this.fireObjectChangedEvent(AmazonS3DatasinkDtoPA.INSTANCE.secretKey(), oldValue);
	}


	public boolean isSecretKeyModified()  {
		return secretKey_m;
	}


	public static PropertyAccessor<AmazonS3DatasinkDto, String> getSecretKeyPropertyAccessor()  {
		return secretKey_pa;
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

		this.fireObjectChangedEvent(AmazonS3DatasinkDtoPA.INSTANCE.hasSecretKey(), oldValue);
	}


	public boolean isHasSecretKeyModified()  {
		return hasSecretKey_m;
	}


	public static PropertyAccessor<AmazonS3DatasinkDto, Boolean> getHasSecretKeyPropertyAccessor()  {
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
		return BaseIcon.from("amazon");
	}

	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof AmazonS3DatasinkDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((AmazonS3DatasinkDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new AmazonS3DatasinkDto2PosoMap();
	}

	public AmazonS3DatasinkDtoPA instantiatePropertyAccess()  {
		return GWT.create(AmazonS3DatasinkDtoPA.class);
	}

	public void clearModified()  {
		this.appKey = null;
		this.appKey_m = false;
		this.bucketName = null;
		this.bucketName_m = false;
		this.folder = null;
		this.folder_m = false;
		this.regionName = null;
		this.regionName_m = false;
		this.secretKey = null;
		this.secretKey_m = false;
		this.hasSecretKey = null;
		this.hasSecretKey_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(appKey_m)
			return true;
		if(bucketName_m)
			return true;
		if(folder_m)
			return true;
		if(regionName_m)
			return true;
		if(secretKey_m)
			return true;
		if(hasSecretKey_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(appKey_pa);
		list.add(bucketName_pa);
		list.add(folder_pa);
		list.add(regionName_pa);
		list.add(secretKey_pa);
		list.add(hasSecretKey_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(appKey_m)
			list.add(appKey_pa);
		if(bucketName_m)
			list.add(bucketName_pa);
		if(folder_m)
			list.add(folder_pa);
		if(regionName_m)
			list.add(regionName_pa);
		if(secretKey_m)
			list.add(secretKey_pa);
		if(hasSecretKey_m)
			list.add(hasSecretKey_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(hasSecretKey_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(appKey_pa);
			list.add(bucketName_pa);
			list.add(folder_pa);
			list.add(regionName_pa);
			list.add(secretKey_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
