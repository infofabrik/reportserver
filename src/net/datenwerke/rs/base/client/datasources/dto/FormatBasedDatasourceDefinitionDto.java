package net.datenwerke.rs.base.client.datasources.dto;

import com.google.gwt.core.client.GWT;
import java.lang.NullPointerException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.eventbus.handlers.has.HasObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorDto;
import net.datenwerke.rs.base.client.datasources.dto.pa.FormatBasedDatasourceDefinitionDtoPA;
import net.datenwerke.rs.base.client.datasources.dto.posomap.FormatBasedDatasourceDefinitionDto2PosoMap;
import net.datenwerke.rs.base.service.datasources.definitions.FormatBasedDatasourceDefinition;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;

/**
 * Dto for {@link FormatBasedDatasourceDefinition}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class FormatBasedDatasourceDefinitionDto extends DatasourceDefinitionDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private DatasourceConnectorDto connector;
	private  boolean connector_m;
	public static final String PROPERTY_CONNECTOR = "dpi-formatbaseddatasourcedefinition-connector";

	private transient static PropertyAccessor<FormatBasedDatasourceDefinitionDto, DatasourceConnectorDto> connector_pa = new PropertyAccessor<FormatBasedDatasourceDefinitionDto, DatasourceConnectorDto>() {
		@Override
		public void setValue(FormatBasedDatasourceDefinitionDto container, DatasourceConnectorDto object) {
			container.setConnector(object);
		}

		@Override
		public DatasourceConnectorDto getValue(FormatBasedDatasourceDefinitionDto container) {
			return container.getConnector();
		}

		@Override
		public Class<?> getType() {
			return DatasourceConnectorDto.class;
		}

		@Override
		public String getPath() {
			return "connector";
		}

		@Override
		public void setModified(FormatBasedDatasourceDefinitionDto container, boolean modified) {
			container.connector_m = modified;
		}

		@Override
		public boolean isModified(FormatBasedDatasourceDefinitionDto container) {
			return container.isConnectorModified();
		}
	};


	public FormatBasedDatasourceDefinitionDto() {
		super();
	}

	public DatasourceConnectorDto getConnector()  {
		if(! isDtoProxy()){
			return this.connector;
		}

		if(isConnectorModified())
			return this.connector;

		if(! GWT.isClient())
			return null;

		DatasourceConnectorDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().connector());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isConnectorModified())
						setConnector((DatasourceConnectorDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setConnector(DatasourceConnectorDto connector)  {
		/* old value */
		DatasourceConnectorDto oldValue = null;
		if(GWT.isClient())
			oldValue = getConnector();

		/* set new value */
		this.connector = connector;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(connector_pa, oldValue, connector, this.connector_m));

		/* set indicator */
		this.connector_m = true;

		this.fireObjectChangedEvent(FormatBasedDatasourceDefinitionDtoPA.INSTANCE.connector(), oldValue);
	}


	public boolean isConnectorModified()  {
		return connector_m;
	}


	public static PropertyAccessor<FormatBasedDatasourceDefinitionDto, DatasourceConnectorDto> getConnectorPropertyAccessor()  {
		return connector_pa;
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
		if(! (obj instanceof FormatBasedDatasourceDefinitionDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((FormatBasedDatasourceDefinitionDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new FormatBasedDatasourceDefinitionDto2PosoMap();
	}

	public FormatBasedDatasourceDefinitionDtoPA instantiatePropertyAccess()  {
		return GWT.create(FormatBasedDatasourceDefinitionDtoPA.class);
	}

	public void clearModified()  {
		this.connector = null;
		this.connector_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(connector_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(connector_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(connector_m)
			list.add(connector_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(connector_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(connector_pa);
		return list;
	}



	net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorDto wl_0;

}
