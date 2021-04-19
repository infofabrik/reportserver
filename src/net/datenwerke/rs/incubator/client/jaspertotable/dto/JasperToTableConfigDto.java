package net.datenwerke.rs.incubator.client.jaspertotable.dto;

import com.google.gwt.core.client.GWT;
import java.lang.Boolean;
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
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerProviderDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportPropertyDto;
import net.datenwerke.rs.incubator.client.jaspertotable.dto.pa.JasperToTableConfigDtoPA;
import net.datenwerke.rs.incubator.client.jaspertotable.dto.posomap.JasperToTableConfigDto2PosoMap;
import net.datenwerke.rs.incubator.service.jaspertotable.entities.JasperToTableConfig;

/**
 * Dto for {@link JasperToTableConfig}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class JasperToTableConfigDto extends ReportPropertyDto implements DatasourceContainerProviderDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private DatasourceContainerDto datasourceContainer;
	private  boolean datasourceContainer_m;
	public static final String PROPERTY_DATASOURCE_CONTAINER = "dpi-jaspertotableconfig-datasourcecontainer";

	private transient static PropertyAccessor<JasperToTableConfigDto, DatasourceContainerDto> datasourceContainer_pa = new PropertyAccessor<JasperToTableConfigDto, DatasourceContainerDto>() {
		@Override
		public void setValue(JasperToTableConfigDto container, DatasourceContainerDto object) {
			container.setDatasourceContainer(object);
		}

		@Override
		public DatasourceContainerDto getValue(JasperToTableConfigDto container) {
			return container.getDatasourceContainer();
		}

		@Override
		public Class<?> getType() {
			return DatasourceContainerDto.class;
		}

		@Override
		public String getPath() {
			return "datasourceContainer";
		}

		@Override
		public void setModified(JasperToTableConfigDto container, boolean modified) {
			container.datasourceContainer_m = modified;
		}

		@Override
		public boolean isModified(JasperToTableConfigDto container) {
			return container.isDatasourceContainerModified();
		}
	};

	private Boolean active;
	private  boolean active_m;
	public static final String PROPERTY_ACTIVE = "dpi-jaspertotableconfig-active";

	private transient static PropertyAccessor<JasperToTableConfigDto, Boolean> active_pa = new PropertyAccessor<JasperToTableConfigDto, Boolean>() {
		@Override
		public void setValue(JasperToTableConfigDto container, Boolean object) {
			container.setActive(object);
		}

		@Override
		public Boolean getValue(JasperToTableConfigDto container) {
			return container.isActive();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "active";
		}

		@Override
		public void setModified(JasperToTableConfigDto container, boolean modified) {
			container.active_m = modified;
		}

		@Override
		public boolean isModified(JasperToTableConfigDto container) {
			return container.isActiveModified();
		}
	};


	public JasperToTableConfigDto() {
		super();
	}

	public DatasourceContainerDto getDatasourceContainer()  {
		if(! isDtoProxy()){
			return this.datasourceContainer;
		}

		if(isDatasourceContainerModified())
			return this.datasourceContainer;

		if(! GWT.isClient())
			return null;

		DatasourceContainerDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().datasourceContainer());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isDatasourceContainerModified())
						setDatasourceContainer((DatasourceContainerDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setDatasourceContainer(DatasourceContainerDto datasourceContainer)  {
		/* old value */
		DatasourceContainerDto oldValue = null;
		if(GWT.isClient())
			oldValue = getDatasourceContainer();

		/* set new value */
		this.datasourceContainer = datasourceContainer;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(datasourceContainer_pa, oldValue, datasourceContainer, this.datasourceContainer_m));

		/* set indicator */
		this.datasourceContainer_m = true;

		this.fireObjectChangedEvent(JasperToTableConfigDtoPA.INSTANCE.datasourceContainer(), oldValue);
	}


	public boolean isDatasourceContainerModified()  {
		return datasourceContainer_m;
	}


	public static PropertyAccessor<JasperToTableConfigDto, DatasourceContainerDto> getDatasourceContainerPropertyAccessor()  {
		return datasourceContainer_pa;
	}


	public Boolean isActive()  {
		if(! isDtoProxy()){
			return this.active;
		}

		if(isActiveModified())
			return this.active;

		if(! GWT.isClient())
			return null;

		Boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().active());

		return _value;
	}


	public void setActive(Boolean active)  {
		/* old value */
		Boolean oldValue = null;
		if(GWT.isClient())
			oldValue = isActive();

		/* set new value */
		this.active = active;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(active_pa, oldValue, active, this.active_m));

		/* set indicator */
		this.active_m = true;

		this.fireObjectChangedEvent(JasperToTableConfigDtoPA.INSTANCE.active(), oldValue);
	}


	public boolean isActiveModified()  {
		return active_m;
	}


	public static PropertyAccessor<JasperToTableConfigDto, Boolean> getActivePropertyAccessor()  {
		return active_pa;
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
		if(! (obj instanceof JasperToTableConfigDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((JasperToTableConfigDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new JasperToTableConfigDto2PosoMap();
	}

	public JasperToTableConfigDtoPA instantiatePropertyAccess()  {
		return GWT.create(JasperToTableConfigDtoPA.class);
	}

	public void clearModified()  {
		this.datasourceContainer = null;
		this.datasourceContainer_m = false;
		this.active = null;
		this.active_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(datasourceContainer_m)
			return true;
		if(active_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(datasourceContainer_pa);
		list.add(active_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(datasourceContainer_m)
			list.add(datasourceContainer_pa);
		if(active_m)
			list.add(active_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(active_pa);
		}
		if(view.compareTo(DtoView.ALL) >= 0){
			list.add(datasourceContainer_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(datasourceContainer_pa);
		return list;
	}



	net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto wl_0;

}
