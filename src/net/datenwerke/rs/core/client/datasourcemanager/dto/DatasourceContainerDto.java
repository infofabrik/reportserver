package net.datenwerke.rs.core.client.datasourcemanager.dto;

import com.google.gwt.core.client.GWT;
import java.lang.IllegalStateException;
import java.lang.Long;
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
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionConfigDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.pa.DatasourceContainerDtoPA;
import net.datenwerke.rs.core.client.datasourcemanager.dto.posomap.DatasourceContainerDto2PosoMap;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;

/**
 * Dto for {@link DatasourceContainer}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class DatasourceContainerDto extends RsDto implements IdedDto {


	private static final long serialVersionUID = 1;

	private Long dtoId;


	/* Fields */
	private DatasourceDefinitionDto datasource;
	private  boolean datasource_m;
	public static final String PROPERTY_DATASOURCE = "dpi-datasourcecontainer-datasource";

	private transient static PropertyAccessor<DatasourceContainerDto, DatasourceDefinitionDto> datasource_pa = new PropertyAccessor<DatasourceContainerDto, DatasourceDefinitionDto>() {
		@Override
		public void setValue(DatasourceContainerDto container, DatasourceDefinitionDto object) {
			container.setDatasource(object);
		}

		@Override
		public DatasourceDefinitionDto getValue(DatasourceContainerDto container) {
			return container.getDatasource();
		}

		@Override
		public Class<?> getType() {
			return DatasourceDefinitionDto.class;
		}

		@Override
		public String getPath() {
			return "datasource";
		}

		@Override
		public void setModified(DatasourceContainerDto container, boolean modified) {
			container.datasource_m = modified;
		}

		@Override
		public boolean isModified(DatasourceContainerDto container) {
			return container.isDatasourceModified();
		}
	};

	private DatasourceDefinitionConfigDto datasourceConfig;
	private  boolean datasourceConfig_m;
	public static final String PROPERTY_DATASOURCE_CONFIG = "dpi-datasourcecontainer-datasourceconfig";

	private transient static PropertyAccessor<DatasourceContainerDto, DatasourceDefinitionConfigDto> datasourceConfig_pa = new PropertyAccessor<DatasourceContainerDto, DatasourceDefinitionConfigDto>() {
		@Override
		public void setValue(DatasourceContainerDto container, DatasourceDefinitionConfigDto object) {
			container.setDatasourceConfig(object);
		}

		@Override
		public DatasourceDefinitionConfigDto getValue(DatasourceContainerDto container) {
			return container.getDatasourceConfig();
		}

		@Override
		public Class<?> getType() {
			return DatasourceDefinitionConfigDto.class;
		}

		@Override
		public String getPath() {
			return "datasourceConfig";
		}

		@Override
		public void setModified(DatasourceContainerDto container, boolean modified) {
			container.datasourceConfig_m = modified;
		}

		@Override
		public boolean isModified(DatasourceContainerDto container) {
			return container.isDatasourceConfigModified();
		}
	};

	private Long id;
	private  boolean id_m;
	public static final String PROPERTY_ID = "dpi-datasourcecontainer-id";

	private transient static PropertyAccessor<DatasourceContainerDto, Long> id_pa = new PropertyAccessor<DatasourceContainerDto, Long>() {
		@Override
		public void setValue(DatasourceContainerDto container, Long object) {
			// id field
		}

		@Override
		public Long getValue(DatasourceContainerDto container) {
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
		public void setModified(DatasourceContainerDto container, boolean modified) {
			container.id_m = modified;
		}

		@Override
		public boolean isModified(DatasourceContainerDto container) {
			return container.isIdModified();
		}
	};


	public DatasourceContainerDto() {
		super();
	}

	public DatasourceDefinitionDto getDatasource()  {
		if(! isDtoProxy()){
			return this.datasource;
		}

		if(isDatasourceModified())
			return this.datasource;

		if(! GWT.isClient())
			return null;

		DatasourceDefinitionDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().datasource());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isDatasourceModified())
						setDatasource((DatasourceDefinitionDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setDatasource(DatasourceDefinitionDto datasource)  {
		/* old value */
		DatasourceDefinitionDto oldValue = null;
		if(GWT.isClient())
			oldValue = getDatasource();

		/* set new value */
		this.datasource = datasource;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(datasource_pa, oldValue, datasource, this.datasource_m));

		/* set indicator */
		this.datasource_m = true;

		this.fireObjectChangedEvent(DatasourceContainerDtoPA.INSTANCE.datasource(), oldValue);
	}


	public boolean isDatasourceModified()  {
		return datasource_m;
	}


	public static PropertyAccessor<DatasourceContainerDto, DatasourceDefinitionDto> getDatasourcePropertyAccessor()  {
		return datasource_pa;
	}


	public DatasourceDefinitionConfigDto getDatasourceConfig()  {
		if(! isDtoProxy()){
			return this.datasourceConfig;
		}

		if(isDatasourceConfigModified())
			return this.datasourceConfig;

		if(! GWT.isClient())
			return null;

		DatasourceDefinitionConfigDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().datasourceConfig());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isDatasourceConfigModified())
						setDatasourceConfig((DatasourceDefinitionConfigDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setDatasourceConfig(DatasourceDefinitionConfigDto datasourceConfig)  {
		/* old value */
		DatasourceDefinitionConfigDto oldValue = null;
		if(GWT.isClient())
			oldValue = getDatasourceConfig();

		/* set new value */
		this.datasourceConfig = datasourceConfig;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(datasourceConfig_pa, oldValue, datasourceConfig, this.datasourceConfig_m));

		/* set indicator */
		this.datasourceConfig_m = true;

		this.fireObjectChangedEvent(DatasourceContainerDtoPA.INSTANCE.datasourceConfig(), oldValue);
	}


	public boolean isDatasourceConfigModified()  {
		return datasourceConfig_m;
	}


	public static PropertyAccessor<DatasourceContainerDto, DatasourceDefinitionConfigDto> getDatasourceConfigPropertyAccessor()  {
		return datasourceConfig_pa;
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


	public static PropertyAccessor<DatasourceContainerDto, Long> getIdPropertyAccessor()  {
		return id_pa;
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
		if(! (obj instanceof DatasourceContainerDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((DatasourceContainerDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new DatasourceContainerDto2PosoMap();
	}

	public DatasourceContainerDtoPA instantiatePropertyAccess()  {
		return GWT.create(DatasourceContainerDtoPA.class);
	}

	public void clearModified()  {
		this.datasource = null;
		this.datasource_m = false;
		this.datasourceConfig = null;
		this.datasourceConfig_m = false;
		this.id = null;
		this.id_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(datasource_m)
			return true;
		if(datasourceConfig_m)
			return true;
		if(id_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(datasource_pa);
		list.add(datasourceConfig_pa);
		list.add(id_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(datasource_m)
			list.add(datasource_pa);
		if(datasourceConfig_m)
			list.add(datasourceConfig_pa);
		if(id_m)
			list.add(id_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(id_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(datasource_pa);
			list.add(datasourceConfig_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(datasource_pa);
		list.add(datasourceConfig_pa);
		return list;
	}



	net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto wl_0;
	net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionConfigDto wl_1;

}
