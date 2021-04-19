package net.datenwerke.rs.base.client.datasources.dto;

import com.google.gwt.core.client.GWT;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.ChangeMonitoredList;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.MonitoredCollection;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.eventbus.handlers.has.HasObjectChangedEventHandler;
import net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorConfigDto;
import net.datenwerke.rs.base.client.datasources.dto.pa.FormatBasedDatasourceConfigDtoPA;
import net.datenwerke.rs.base.client.datasources.dto.posomap.FormatBasedDatasourceConfigDto2PosoMap;
import net.datenwerke.rs.base.service.datasources.definitions.FormatBasedDatasourceConfig;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionConfigDto;

/**
 * Dto for {@link FormatBasedDatasourceConfig}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class FormatBasedDatasourceConfigDto extends DatasourceDefinitionConfigDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private List<DatasourceConnectorConfigDto> connectorConfig;
	private  boolean connectorConfig_m;
	public static final String PROPERTY_CONNECTOR_CONFIG = "dpi-formatbaseddatasourceconfig-connectorconfig";

	private transient static PropertyAccessor<FormatBasedDatasourceConfigDto, List<DatasourceConnectorConfigDto>> connectorConfig_pa = new PropertyAccessor<FormatBasedDatasourceConfigDto, List<DatasourceConnectorConfigDto>>() {
		@Override
		public void setValue(FormatBasedDatasourceConfigDto container, List<DatasourceConnectorConfigDto> object) {
			container.setConnectorConfig(object);
		}

		@Override
		public List<DatasourceConnectorConfigDto> getValue(FormatBasedDatasourceConfigDto container) {
			return container.getConnectorConfig();
		}

		@Override
		public Class<?> getType() {
			return List.class;
		}

		@Override
		public String getPath() {
			return "connectorConfig";
		}

		@Override
		public void setModified(FormatBasedDatasourceConfigDto container, boolean modified) {
			container.connectorConfig_m = modified;
		}

		@Override
		public boolean isModified(FormatBasedDatasourceConfigDto container) {
			return container.isConnectorConfigModified();
		}
	};


	public FormatBasedDatasourceConfigDto() {
		super();
	}

	public List<DatasourceConnectorConfigDto> getConnectorConfig()  {
		if(! isDtoProxy()){
			List<DatasourceConnectorConfigDto> _currentValue = this.connectorConfig;
			if(null == _currentValue)
				this.connectorConfig = new ArrayList<DatasourceConnectorConfigDto>();

			return this.connectorConfig;
		}

		if(isConnectorConfigModified())
			return this.connectorConfig;

		if(! GWT.isClient())
			return null;

		List<DatasourceConnectorConfigDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().connectorConfig());

		_value = new ChangeMonitoredList<DatasourceConnectorConfigDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isConnectorConfigModified())
						setConnectorConfig((List<DatasourceConnectorConfigDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setConnectorConfig(List<DatasourceConnectorConfigDto> connectorConfig)  {
		/* old value */
		List<DatasourceConnectorConfigDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getConnectorConfig();

		/* set new value */
		this.connectorConfig = connectorConfig;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(connectorConfig_pa, oldValue, connectorConfig, this.connectorConfig_m));

		/* set indicator */
		this.connectorConfig_m = true;

		this.fireObjectChangedEvent(FormatBasedDatasourceConfigDtoPA.INSTANCE.connectorConfig(), oldValue);
	}


	public boolean isConnectorConfigModified()  {
		return connectorConfig_m;
	}


	public static PropertyAccessor<FormatBasedDatasourceConfigDto, List<DatasourceConnectorConfigDto>> getConnectorConfigPropertyAccessor()  {
		return connectorConfig_pa;
	}


	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof FormatBasedDatasourceConfigDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((FormatBasedDatasourceConfigDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new FormatBasedDatasourceConfigDto2PosoMap();
	}

	public FormatBasedDatasourceConfigDtoPA instantiatePropertyAccess()  {
		return GWT.create(FormatBasedDatasourceConfigDtoPA.class);
	}

	public void clearModified()  {
		this.connectorConfig = null;
		this.connectorConfig_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(connectorConfig_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(connectorConfig_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(connectorConfig_m)
			list.add(connectorConfig_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(connectorConfig_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(connectorConfig_pa);
		return list;
	}



	net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorConfigDto wl_0;

}
