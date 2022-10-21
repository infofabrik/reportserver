package net.datenwerke.rs.base.client.datasources.dto;

import com.google.gwt.core.client.GWT;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.rs.base.client.datasources.dto.pa.DatabaseDatasourceConfigDtoPA;
import net.datenwerke.rs.base.client.datasources.dto.posomap.DatabaseDatasourceConfigDto2PosoMap;
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasourceConfig;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionConfigDto;

/**
 * Dto for {@link DatabaseDatasourceConfig}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class DatabaseDatasourceConfigDto extends DatasourceDefinitionConfigDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String query;
	private  boolean query_m;
	public static final String PROPERTY_QUERY = "dpi-databasedatasourceconfig-query";

	private transient static PropertyAccessor<DatabaseDatasourceConfigDto, String> query_pa = new PropertyAccessor<DatabaseDatasourceConfigDto, String>() {
		@Override
		public void setValue(DatabaseDatasourceConfigDto container, String object) {
			container.setQuery(object);
		}

		@Override
		public String getValue(DatabaseDatasourceConfigDto container) {
			return container.getQuery();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "query";
		}

		@Override
		public void setModified(DatabaseDatasourceConfigDto container, boolean modified) {
			container.query_m = modified;
		}

		@Override
		public boolean isModified(DatabaseDatasourceConfigDto container) {
			return container.isQueryModified();
		}
	};


	public DatabaseDatasourceConfigDto() {
		super();
	}

	public String getQuery()  {
		if(! isDtoProxy()){
			return this.query;
		}

		if(isQueryModified())
			return this.query;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().query());

		return _value;
	}


	public void setQuery(String query)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getQuery();

		/* set new value */
		this.query = query;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(query_pa, oldValue, query, this.query_m));

		/* set indicator */
		this.query_m = true;

		this.fireObjectChangedEvent(DatabaseDatasourceConfigDtoPA.INSTANCE.query(), oldValue);
	}


	public boolean isQueryModified()  {
		return query_m;
	}


	public static PropertyAccessor<DatabaseDatasourceConfigDto, String> getQueryPropertyAccessor()  {
		return query_pa;
	}


	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof DatabaseDatasourceConfigDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((DatabaseDatasourceConfigDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new DatabaseDatasourceConfigDto2PosoMap();
	}

	public DatabaseDatasourceConfigDtoPA instantiatePropertyAccess()  {
		return GWT.create(DatabaseDatasourceConfigDtoPA.class);
	}

	public void clearModified()  {
		this.query = null;
		this.query_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(query_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(query_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(query_m)
			list.add(query_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(query_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
