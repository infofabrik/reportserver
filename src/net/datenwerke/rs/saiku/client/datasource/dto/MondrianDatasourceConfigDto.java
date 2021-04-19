package net.datenwerke.rs.saiku.client.datasource.dto;

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
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionConfigDto;
import net.datenwerke.rs.saiku.client.datasource.dto.pa.MondrianDatasourceConfigDtoPA;
import net.datenwerke.rs.saiku.client.datasource.dto.posomap.MondrianDatasourceConfigDto2PosoMap;
import net.datenwerke.rs.saiku.service.datasource.MondrianDatasourceConfig;

/**
 * Dto for {@link MondrianDatasourceConfig}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class MondrianDatasourceConfigDto extends DatasourceDefinitionConfigDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String cubeName;
	private  boolean cubeName_m;
	public static final String PROPERTY_CUBE_NAME = "dpi-mondriandatasourceconfig-cubename";

	private transient static PropertyAccessor<MondrianDatasourceConfigDto, String> cubeName_pa = new PropertyAccessor<MondrianDatasourceConfigDto, String>() {
		@Override
		public void setValue(MondrianDatasourceConfigDto container, String object) {
			container.setCubeName(object);
		}

		@Override
		public String getValue(MondrianDatasourceConfigDto container) {
			return container.getCubeName();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "cubeName";
		}

		@Override
		public void setModified(MondrianDatasourceConfigDto container, boolean modified) {
			container.cubeName_m = modified;
		}

		@Override
		public boolean isModified(MondrianDatasourceConfigDto container) {
			return container.isCubeNameModified();
		}
	};


	public MondrianDatasourceConfigDto() {
		super();
	}

	public String getCubeName()  {
		if(! isDtoProxy()){
			return this.cubeName;
		}

		if(isCubeNameModified())
			return this.cubeName;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().cubeName());

		return _value;
	}


	public void setCubeName(String cubeName)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getCubeName();

		/* set new value */
		this.cubeName = cubeName;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(cubeName_pa, oldValue, cubeName, this.cubeName_m));

		/* set indicator */
		this.cubeName_m = true;

		this.fireObjectChangedEvent(MondrianDatasourceConfigDtoPA.INSTANCE.cubeName(), oldValue);
	}


	public boolean isCubeNameModified()  {
		return cubeName_m;
	}


	public static PropertyAccessor<MondrianDatasourceConfigDto, String> getCubeNamePropertyAccessor()  {
		return cubeName_pa;
	}


	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof MondrianDatasourceConfigDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((MondrianDatasourceConfigDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new MondrianDatasourceConfigDto2PosoMap();
	}

	public MondrianDatasourceConfigDtoPA instantiatePropertyAccess()  {
		return GWT.create(MondrianDatasourceConfigDtoPA.class);
	}

	public void clearModified()  {
		this.cubeName = null;
		this.cubeName_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(cubeName_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(cubeName_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(cubeName_m)
			list.add(cubeName_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(cubeName_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
