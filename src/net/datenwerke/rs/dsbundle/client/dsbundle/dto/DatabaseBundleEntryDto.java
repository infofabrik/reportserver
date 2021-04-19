package net.datenwerke.rs.dsbundle.client.dsbundle.dto;

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
import net.datenwerke.rs.core.client.datasourcemanager.dto.AbstractDatasourceManagerNodeDto;
import net.datenwerke.rs.dsbundle.client.dsbundle.dto.pa.DatabaseBundleEntryDtoPA;
import net.datenwerke.rs.dsbundle.client.dsbundle.dto.posomap.DatabaseBundleEntryDto2PosoMap;
import net.datenwerke.rs.dsbundle.service.dsbundle.entities.DatabaseBundleEntry;

/**
 * Dto for {@link DatabaseBundleEntry}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class DatabaseBundleEntryDto extends RsDto implements IdedDto {


	private static final long serialVersionUID = 1;

	private Long dtoId;


	/* Fields */
	private AbstractDatasourceManagerNodeDto database;
	private  boolean database_m;
	public static final String PROPERTY_DATABASE = "dpi-databasebundleentry-database";

	private transient static PropertyAccessor<DatabaseBundleEntryDto, AbstractDatasourceManagerNodeDto> database_pa = new PropertyAccessor<DatabaseBundleEntryDto, AbstractDatasourceManagerNodeDto>() {
		@Override
		public void setValue(DatabaseBundleEntryDto container, AbstractDatasourceManagerNodeDto object) {
			container.setDatabase(object);
		}

		@Override
		public AbstractDatasourceManagerNodeDto getValue(DatabaseBundleEntryDto container) {
			return container.getDatabase();
		}

		@Override
		public Class<?> getType() {
			return AbstractDatasourceManagerNodeDto.class;
		}

		@Override
		public String getPath() {
			return "database";
		}

		@Override
		public void setModified(DatabaseBundleEntryDto container, boolean modified) {
			container.database_m = modified;
		}

		@Override
		public boolean isModified(DatabaseBundleEntryDto container) {
			return container.isDatabaseModified();
		}
	};

	private Long id;
	private  boolean id_m;
	public static final String PROPERTY_ID = "dpi-databasebundleentry-id";

	private transient static PropertyAccessor<DatabaseBundleEntryDto, Long> id_pa = new PropertyAccessor<DatabaseBundleEntryDto, Long>() {
		@Override
		public void setValue(DatabaseBundleEntryDto container, Long object) {
			// id field
		}

		@Override
		public Long getValue(DatabaseBundleEntryDto container) {
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
		public void setModified(DatabaseBundleEntryDto container, boolean modified) {
			container.id_m = modified;
		}

		@Override
		public boolean isModified(DatabaseBundleEntryDto container) {
			return container.isIdModified();
		}
	};

	private String key;
	private  boolean key_m;
	public static final String PROPERTY_KEY = "dpi-databasebundleentry-key";

	private transient static PropertyAccessor<DatabaseBundleEntryDto, String> key_pa = new PropertyAccessor<DatabaseBundleEntryDto, String>() {
		@Override
		public void setValue(DatabaseBundleEntryDto container, String object) {
			container.setKey(object);
		}

		@Override
		public String getValue(DatabaseBundleEntryDto container) {
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
		public void setModified(DatabaseBundleEntryDto container, boolean modified) {
			container.key_m = modified;
		}

		@Override
		public boolean isModified(DatabaseBundleEntryDto container) {
			return container.isKeyModified();
		}
	};


	public DatabaseBundleEntryDto() {
		super();
	}

	public AbstractDatasourceManagerNodeDto getDatabase()  {
		if(! isDtoProxy()){
			return this.database;
		}

		if(isDatabaseModified())
			return this.database;

		if(! GWT.isClient())
			return null;

		AbstractDatasourceManagerNodeDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().database());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isDatabaseModified())
						setDatabase((AbstractDatasourceManagerNodeDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setDatabase(AbstractDatasourceManagerNodeDto database)  {
		/* old value */
		AbstractDatasourceManagerNodeDto oldValue = null;
		if(GWT.isClient())
			oldValue = getDatabase();

		/* set new value */
		this.database = database;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(database_pa, oldValue, database, this.database_m));

		/* set indicator */
		this.database_m = true;

		this.fireObjectChangedEvent(DatabaseBundleEntryDtoPA.INSTANCE.database(), oldValue);
	}


	public boolean isDatabaseModified()  {
		return database_m;
	}


	public static PropertyAccessor<DatabaseBundleEntryDto, AbstractDatasourceManagerNodeDto> getDatabasePropertyAccessor()  {
		return database_pa;
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


	public static PropertyAccessor<DatabaseBundleEntryDto, Long> getIdPropertyAccessor()  {
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

		this.fireObjectChangedEvent(DatabaseBundleEntryDtoPA.INSTANCE.key(), oldValue);
	}


	public boolean isKeyModified()  {
		return key_m;
	}


	public static PropertyAccessor<DatabaseBundleEntryDto, String> getKeyPropertyAccessor()  {
		return key_pa;
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
		if(! (obj instanceof DatabaseBundleEntryDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((DatabaseBundleEntryDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new DatabaseBundleEntryDto2PosoMap();
	}

	public DatabaseBundleEntryDtoPA instantiatePropertyAccess()  {
		return GWT.create(DatabaseBundleEntryDtoPA.class);
	}

	public void clearModified()  {
		this.database = null;
		this.database_m = false;
		this.id = null;
		this.id_m = false;
		this.key = null;
		this.key_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(database_m)
			return true;
		if(id_m)
			return true;
		if(key_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(database_pa);
		list.add(id_pa);
		list.add(key_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(database_m)
			list.add(database_pa);
		if(id_m)
			list.add(id_pa);
		if(key_m)
			list.add(key_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(id_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(database_pa);
			list.add(key_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(database_pa);
		return list;
	}



	net.datenwerke.rs.core.client.datasourcemanager.dto.AbstractDatasourceManagerNodeDto wl_0;

}
