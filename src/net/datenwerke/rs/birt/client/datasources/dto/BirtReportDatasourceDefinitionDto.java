package net.datenwerke.rs.birt.client.datasources.dto;

import com.google.gwt.core.client.GWT;
import java.lang.NullPointerException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.birt.client.datasources.dto.pa.BirtReportDatasourceDefinitionDtoPA;
import net.datenwerke.rs.birt.client.datasources.dto.posomap.BirtReportDatasourceDefinitionDto2PosoMap;
import net.datenwerke.rs.birt.service.datasources.birtreport.entities.BirtReportDatasourceDefinition;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;

/**
 * Dto for {@link BirtReportDatasourceDefinition}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class BirtReportDatasourceDefinitionDto extends DatasourceDefinitionDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private int databaseCache;
	private  boolean databaseCache_m;
	public static final String PROPERTY_DATABASE_CACHE = "dpi-birtreportdatasourcedefinition-databasecache";

	private transient static PropertyAccessor<BirtReportDatasourceDefinitionDto, Integer> databaseCache_pa = new PropertyAccessor<BirtReportDatasourceDefinitionDto, Integer>() {
		@Override
		public void setValue(BirtReportDatasourceDefinitionDto container, Integer object) {
			container.setDatabaseCache(object);
		}

		@Override
		public Integer getValue(BirtReportDatasourceDefinitionDto container) {
			return container.getDatabaseCache();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "databaseCache";
		}

		@Override
		public void setModified(BirtReportDatasourceDefinitionDto container, boolean modified) {
			container.databaseCache_m = modified;
		}

		@Override
		public boolean isModified(BirtReportDatasourceDefinitionDto container) {
			return container.isDatabaseCacheModified();
		}
	};


	public BirtReportDatasourceDefinitionDto() {
		super();
	}

	public int getDatabaseCache()  {
		if(! isDtoProxy()){
			return this.databaseCache;
		}

		if(isDatabaseCacheModified())
			return this.databaseCache;

		if(! GWT.isClient())
			return 0;

		int _value = dtoManager.getProperty(this, instantiatePropertyAccess().databaseCache());

		return _value;
	}


	public void setDatabaseCache(int databaseCache)  {
		/* old value */
		int oldValue = 0;
		if(GWT.isClient())
			oldValue = getDatabaseCache();

		/* set new value */
		this.databaseCache = databaseCache;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(databaseCache_pa, oldValue, databaseCache, this.databaseCache_m));

		/* set indicator */
		this.databaseCache_m = true;

		this.fireObjectChangedEvent(BirtReportDatasourceDefinitionDtoPA.INSTANCE.databaseCache(), oldValue);
	}


	public boolean isDatabaseCacheModified()  {
		return databaseCache_m;
	}


	public static PropertyAccessor<BirtReportDatasourceDefinitionDto, Integer> getDatabaseCachePropertyAccessor()  {
		return databaseCache_pa;
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
		if(! (obj instanceof BirtReportDatasourceDefinitionDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((BirtReportDatasourceDefinitionDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new BirtReportDatasourceDefinitionDto2PosoMap();
	}

	public BirtReportDatasourceDefinitionDtoPA instantiatePropertyAccess()  {
		return GWT.create(BirtReportDatasourceDefinitionDtoPA.class);
	}

	public void clearModified()  {
		this.databaseCache = 0;
		this.databaseCache_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(databaseCache_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(databaseCache_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(databaseCache_m)
			list.add(databaseCache_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(databaseCache_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
