package net.datenwerke.rs.dsbundle.client.dsbundle.dto;

import com.google.gwt.core.client.GWT;
import java.lang.NullPointerException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.ChangeMonitoredSet;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.MonitoredCollection;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.eventbus.handlers.has.HasObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceDto;
import net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleEntryDto;
import net.datenwerke.rs.dsbundle.client.dsbundle.dto.pa.DatabaseBundleDtoPA;
import net.datenwerke.rs.dsbundle.client.dsbundle.dto.posomap.DatabaseBundleDto2PosoMap;
import net.datenwerke.rs.dsbundle.service.dsbundle.entities.DatabaseBundle;

/**
 * Dto for {@link DatabaseBundle}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class DatabaseBundleDto extends DatabaseDatasourceDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private Set<DatabaseBundleEntryDto> bundleEntries;
	private  boolean bundleEntries_m;
	public static final String PROPERTY_BUNDLE_ENTRIES = "dpi-databasebundle-bundleentries";

	private transient static PropertyAccessor<DatabaseBundleDto, Set<DatabaseBundleEntryDto>> bundleEntries_pa = new PropertyAccessor<DatabaseBundleDto, Set<DatabaseBundleEntryDto>>() {
		@Override
		public void setValue(DatabaseBundleDto container, Set<DatabaseBundleEntryDto> object) {
			container.setBundleEntries(object);
		}

		@Override
		public Set<DatabaseBundleEntryDto> getValue(DatabaseBundleDto container) {
			return container.getBundleEntries();
		}

		@Override
		public Class<?> getType() {
			return Set.class;
		}

		@Override
		public String getPath() {
			return "bundleEntries";
		}

		@Override
		public void setModified(DatabaseBundleDto container, boolean modified) {
			container.bundleEntries_m = modified;
		}

		@Override
		public boolean isModified(DatabaseBundleDto container) {
			return container.isBundleEntriesModified();
		}
	};

	private String keySource;
	private  boolean keySource_m;
	public static final String PROPERTY_KEY_SOURCE = "dpi-databasebundle-keysource";

	private transient static PropertyAccessor<DatabaseBundleDto, String> keySource_pa = new PropertyAccessor<DatabaseBundleDto, String>() {
		@Override
		public void setValue(DatabaseBundleDto container, String object) {
			container.setKeySource(object);
		}

		@Override
		public String getValue(DatabaseBundleDto container) {
			return container.getKeySource();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "keySource";
		}

		@Override
		public void setModified(DatabaseBundleDto container, boolean modified) {
			container.keySource_m = modified;
		}

		@Override
		public boolean isModified(DatabaseBundleDto container) {
			return container.isKeySourceModified();
		}
	};

	private String keySourceParamName;
	private  boolean keySourceParamName_m;
	public static final String PROPERTY_KEY_SOURCE_PARAM_NAME = "dpi-databasebundle-keysourceparamname";

	private transient static PropertyAccessor<DatabaseBundleDto, String> keySourceParamName_pa = new PropertyAccessor<DatabaseBundleDto, String>() {
		@Override
		public void setValue(DatabaseBundleDto container, String object) {
			container.setKeySourceParamName(object);
		}

		@Override
		public String getValue(DatabaseBundleDto container) {
			return container.getKeySourceParamName();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "keySourceParamName";
		}

		@Override
		public void setModified(DatabaseBundleDto container, boolean modified) {
			container.keySourceParamName_m = modified;
		}

		@Override
		public boolean isModified(DatabaseBundleDto container) {
			return container.isKeySourceParamNameModified();
		}
	};

	private String mappingSource;
	private  boolean mappingSource_m;
	public static final String PROPERTY_MAPPING_SOURCE = "dpi-databasebundle-mappingsource";

	private transient static PropertyAccessor<DatabaseBundleDto, String> mappingSource_pa = new PropertyAccessor<DatabaseBundleDto, String>() {
		@Override
		public void setValue(DatabaseBundleDto container, String object) {
			container.setMappingSource(object);
		}

		@Override
		public String getValue(DatabaseBundleDto container) {
			return container.getMappingSource();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "mappingSource";
		}

		@Override
		public void setModified(DatabaseBundleDto container, boolean modified) {
			container.mappingSource_m = modified;
		}

		@Override
		public boolean isModified(DatabaseBundleDto container) {
			return container.isMappingSourceModified();
		}
	};


	public DatabaseBundleDto() {
		super();
	}

	public Set<DatabaseBundleEntryDto> getBundleEntries()  {
		if(! isDtoProxy()){
			Set<DatabaseBundleEntryDto> _currentValue = this.bundleEntries;
			if(null == _currentValue)
				this.bundleEntries = new HashSet<DatabaseBundleEntryDto>();

			return this.bundleEntries;
		}

		if(isBundleEntriesModified())
			return this.bundleEntries;

		if(! GWT.isClient())
			return null;

		Set<DatabaseBundleEntryDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().bundleEntries());

		_value = new ChangeMonitoredSet<DatabaseBundleEntryDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isBundleEntriesModified())
						setBundleEntries((Set<DatabaseBundleEntryDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setBundleEntries(Set<DatabaseBundleEntryDto> bundleEntries)  {
		/* old value */
		Set<DatabaseBundleEntryDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getBundleEntries();

		/* set new value */
		this.bundleEntries = bundleEntries;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(bundleEntries_pa, oldValue, bundleEntries, this.bundleEntries_m));

		/* set indicator */
		this.bundleEntries_m = true;

		this.fireObjectChangedEvent(DatabaseBundleDtoPA.INSTANCE.bundleEntries(), oldValue);
	}


	public boolean isBundleEntriesModified()  {
		return bundleEntries_m;
	}


	public static PropertyAccessor<DatabaseBundleDto, Set<DatabaseBundleEntryDto>> getBundleEntriesPropertyAccessor()  {
		return bundleEntries_pa;
	}


	public String getKeySource()  {
		if(! isDtoProxy()){
			return this.keySource;
		}

		if(isKeySourceModified())
			return this.keySource;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().keySource());

		return _value;
	}


	public void setKeySource(String keySource)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getKeySource();

		/* set new value */
		this.keySource = keySource;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(keySource_pa, oldValue, keySource, this.keySource_m));

		/* set indicator */
		this.keySource_m = true;

		this.fireObjectChangedEvent(DatabaseBundleDtoPA.INSTANCE.keySource(), oldValue);
	}


	public boolean isKeySourceModified()  {
		return keySource_m;
	}


	public static PropertyAccessor<DatabaseBundleDto, String> getKeySourcePropertyAccessor()  {
		return keySource_pa;
	}


	public String getKeySourceParamName()  {
		if(! isDtoProxy()){
			return this.keySourceParamName;
		}

		if(isKeySourceParamNameModified())
			return this.keySourceParamName;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().keySourceParamName());

		return _value;
	}


	public void setKeySourceParamName(String keySourceParamName)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getKeySourceParamName();

		/* set new value */
		this.keySourceParamName = keySourceParamName;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(keySourceParamName_pa, oldValue, keySourceParamName, this.keySourceParamName_m));

		/* set indicator */
		this.keySourceParamName_m = true;

		this.fireObjectChangedEvent(DatabaseBundleDtoPA.INSTANCE.keySourceParamName(), oldValue);
	}


	public boolean isKeySourceParamNameModified()  {
		return keySourceParamName_m;
	}


	public static PropertyAccessor<DatabaseBundleDto, String> getKeySourceParamNamePropertyAccessor()  {
		return keySourceParamName_pa;
	}


	public String getMappingSource()  {
		if(! isDtoProxy()){
			return this.mappingSource;
		}

		if(isMappingSourceModified())
			return this.mappingSource;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().mappingSource());

		return _value;
	}


	public void setMappingSource(String mappingSource)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getMappingSource();

		/* set new value */
		this.mappingSource = mappingSource;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(mappingSource_pa, oldValue, mappingSource, this.mappingSource_m));

		/* set indicator */
		this.mappingSource_m = true;

		this.fireObjectChangedEvent(DatabaseBundleDtoPA.INSTANCE.mappingSource(), oldValue);
	}


	public boolean isMappingSourceModified()  {
		return mappingSource_m;
	}


	public static PropertyAccessor<DatabaseBundleDto, String> getMappingSourcePropertyAccessor()  {
		return mappingSource_pa;
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
		if(! (obj instanceof DatabaseBundleDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((DatabaseBundleDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new DatabaseBundleDto2PosoMap();
	}

	public DatabaseBundleDtoPA instantiatePropertyAccess()  {
		return GWT.create(DatabaseBundleDtoPA.class);
	}

	public void clearModified()  {
		this.bundleEntries = null;
		this.bundleEntries_m = false;
		this.keySource = null;
		this.keySource_m = false;
		this.keySourceParamName = null;
		this.keySourceParamName_m = false;
		this.mappingSource = null;
		this.mappingSource_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(bundleEntries_m)
			return true;
		if(keySource_m)
			return true;
		if(keySourceParamName_m)
			return true;
		if(mappingSource_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(bundleEntries_pa);
		list.add(keySource_pa);
		list.add(keySourceParamName_pa);
		list.add(mappingSource_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(bundleEntries_m)
			list.add(bundleEntries_pa);
		if(keySource_m)
			list.add(keySource_pa);
		if(keySourceParamName_m)
			list.add(keySourceParamName_pa);
		if(mappingSource_m)
			list.add(mappingSource_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(bundleEntries_pa);
			list.add(keySource_pa);
			list.add(keySourceParamName_pa);
			list.add(mappingSource_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(bundleEntries_pa);
		return list;
	}



	net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleEntryDto wl_0;

}
