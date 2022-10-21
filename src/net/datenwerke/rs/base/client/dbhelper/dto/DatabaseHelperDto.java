package net.datenwerke.rs.base.client.dbhelper.dto;

import com.google.gwt.core.client.GWT;
import java.lang.Boolean;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.rs.base.client.dbhelper.dto.pa.DatabaseHelperDtoPA;
import net.datenwerke.rs.base.client.dbhelper.dto.posomap.DatabaseHelperDto2PosoMap;
import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;

/**
 * Dto for {@link DatabaseHelper}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class DatabaseHelperDto extends RsDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String descriptor;
	private  boolean descriptor_m;
	public static final String PROPERTY_DESCRIPTOR = "dpi-databasehelper-descriptor";

	private transient static PropertyAccessor<DatabaseHelperDto, String> descriptor_pa = new PropertyAccessor<DatabaseHelperDto, String>() {
		@Override
		public void setValue(DatabaseHelperDto container, String object) {
			container.setDescriptor(object);
		}

		@Override
		public String getValue(DatabaseHelperDto container) {
			return container.getDescriptor();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "descriptor";
		}

		@Override
		public void setModified(DatabaseHelperDto container, boolean modified) {
			container.descriptor_m = modified;
		}

		@Override
		public boolean isModified(DatabaseHelperDto container) {
			return container.isDescriptorModified();
		}
	};

	private String driver;
	private  boolean driver_m;
	public static final String PROPERTY_DRIVER = "dpi-databasehelper-driver";

	private transient static PropertyAccessor<DatabaseHelperDto, String> driver_pa = new PropertyAccessor<DatabaseHelperDto, String>() {
		@Override
		public void setValue(DatabaseHelperDto container, String object) {
			container.setDriver(object);
		}

		@Override
		public String getValue(DatabaseHelperDto container) {
			return container.getDriver();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "driver";
		}

		@Override
		public void setModified(DatabaseHelperDto container, boolean modified) {
			container.driver_m = modified;
		}

		@Override
		public boolean isModified(DatabaseHelperDto container) {
			return container.isDriverModified();
		}
	};

	private String name;
	private  boolean name_m;
	public static final String PROPERTY_NAME = "dpi-databasehelper-name";

	private transient static PropertyAccessor<DatabaseHelperDto, String> name_pa = new PropertyAccessor<DatabaseHelperDto, String>() {
		@Override
		public void setValue(DatabaseHelperDto container, String object) {
			container.setName(object);
		}

		@Override
		public String getValue(DatabaseHelperDto container) {
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
		public void setModified(DatabaseHelperDto container, boolean modified) {
			container.name_m = modified;
		}

		@Override
		public boolean isModified(DatabaseHelperDto container) {
			return container.isNameModified();
		}
	};

	private Boolean jdbcDriverAvailable;
	private  boolean jdbcDriverAvailable_m;
	public static final String PROPERTY_JDBC_DRIVER_AVAILABLE = "dpi-databasehelper-jdbcdriveravailable";

	private transient static PropertyAccessor<DatabaseHelperDto, Boolean> jdbcDriverAvailable_pa = new PropertyAccessor<DatabaseHelperDto, Boolean>() {
		@Override
		public void setValue(DatabaseHelperDto container, Boolean object) {
			container.setJdbcDriverAvailable(object);
		}

		@Override
		public Boolean getValue(DatabaseHelperDto container) {
			return container.isJdbcDriverAvailable();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "jdbcDriverAvailable";
		}

		@Override
		public void setModified(DatabaseHelperDto container, boolean modified) {
			container.jdbcDriverAvailable_m = modified;
		}

		@Override
		public boolean isModified(DatabaseHelperDto container) {
			return container.isJdbcDriverAvailableModified();
		}
	};


	public DatabaseHelperDto() {
		super();
	}

	public String getDescriptor()  {
		if(! isDtoProxy()){
			return this.descriptor;
		}

		if(isDescriptorModified())
			return this.descriptor;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().descriptor());

		return _value;
	}


	public void setDescriptor(String descriptor)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getDescriptor();

		/* set new value */
		this.descriptor = descriptor;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(descriptor_pa, oldValue, descriptor, this.descriptor_m));

		/* set indicator */
		this.descriptor_m = true;

		this.fireObjectChangedEvent(DatabaseHelperDtoPA.INSTANCE.descriptor(), oldValue);
	}


	public boolean isDescriptorModified()  {
		return descriptor_m;
	}


	public static PropertyAccessor<DatabaseHelperDto, String> getDescriptorPropertyAccessor()  {
		return descriptor_pa;
	}


	public String getDriver()  {
		if(! isDtoProxy()){
			return this.driver;
		}

		if(isDriverModified())
			return this.driver;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().driver());

		return _value;
	}


	public void setDriver(String driver)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getDriver();

		/* set new value */
		this.driver = driver;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(driver_pa, oldValue, driver, this.driver_m));

		/* set indicator */
		this.driver_m = true;

		this.fireObjectChangedEvent(DatabaseHelperDtoPA.INSTANCE.driver(), oldValue);
	}


	public boolean isDriverModified()  {
		return driver_m;
	}


	public static PropertyAccessor<DatabaseHelperDto, String> getDriverPropertyAccessor()  {
		return driver_pa;
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

		this.fireObjectChangedEvent(DatabaseHelperDtoPA.INSTANCE.name(), oldValue);
	}


	public boolean isNameModified()  {
		return name_m;
	}


	public static PropertyAccessor<DatabaseHelperDto, String> getNamePropertyAccessor()  {
		return name_pa;
	}


	public Boolean isJdbcDriverAvailable()  {
		if(! isDtoProxy()){
			return this.jdbcDriverAvailable;
		}

		if(isJdbcDriverAvailableModified())
			return this.jdbcDriverAvailable;

		if(! GWT.isClient())
			return null;

		Boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().jdbcDriverAvailable());

		return _value;
	}


	public void setJdbcDriverAvailable(Boolean jdbcDriverAvailable)  {
		/* old value */
		Boolean oldValue = null;
		if(GWT.isClient())
			oldValue = isJdbcDriverAvailable();

		/* set new value */
		this.jdbcDriverAvailable = jdbcDriverAvailable;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(jdbcDriverAvailable_pa, oldValue, jdbcDriverAvailable, this.jdbcDriverAvailable_m));

		/* set indicator */
		this.jdbcDriverAvailable_m = true;

		this.fireObjectChangedEvent(DatabaseHelperDtoPA.INSTANCE.jdbcDriverAvailable(), oldValue);
	}


	public boolean isJdbcDriverAvailableModified()  {
		return jdbcDriverAvailable_m;
	}


	public static PropertyAccessor<DatabaseHelperDto, Boolean> getJdbcDriverAvailablePropertyAccessor()  {
		return jdbcDriverAvailable_pa;
	}


	@Override
	public String toString()  {
		return super.toString();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new DatabaseHelperDto2PosoMap();
	}

	public DatabaseHelperDtoPA instantiatePropertyAccess()  {
		return GWT.create(DatabaseHelperDtoPA.class);
	}

	public void clearModified()  {
		this.descriptor = null;
		this.descriptor_m = false;
		this.driver = null;
		this.driver_m = false;
		this.name = null;
		this.name_m = false;
		this.jdbcDriverAvailable = null;
		this.jdbcDriverAvailable_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(descriptor_m)
			return true;
		if(driver_m)
			return true;
		if(name_m)
			return true;
		if(jdbcDriverAvailable_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(descriptor_pa);
		list.add(driver_pa);
		list.add(name_pa);
		list.add(jdbcDriverAvailable_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(descriptor_m)
			list.add(descriptor_pa);
		if(driver_m)
			list.add(driver_pa);
		if(name_m)
			list.add(name_pa);
		if(jdbcDriverAvailable_m)
			list.add(jdbcDriverAvailable_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(jdbcDriverAvailable_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(descriptor_pa);
			list.add(driver_pa);
			list.add(name_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
