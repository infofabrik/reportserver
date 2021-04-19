package net.datenwerke.rs.dashboard.client.dashboard.dto;

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
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.ChangeMonitoredList;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.MonitoredCollection;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.eventbus.handlers.has.HasObjectChangedEventHandler;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.pa.DashboardContainerDtoPA;
import net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.DashboardContainerDto2PosoMap;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardContainer;

/**
 * Dto for {@link DashboardContainer}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class DashboardContainerDto extends RsDto implements IdedDto {


	private static final long serialVersionUID = 1;

	private Long dtoId;


	/* Fields */
	private List<DashboardDto> dashboards;
	private  boolean dashboards_m;
	public static final String PROPERTY_DASHBOARDS = "dpi-dashboardcontainer-dashboards";

	private transient static PropertyAccessor<DashboardContainerDto, List<DashboardDto>> dashboards_pa = new PropertyAccessor<DashboardContainerDto, List<DashboardDto>>() {
		@Override
		public void setValue(DashboardContainerDto container, List<DashboardDto> object) {
			container.setDashboards(object);
		}

		@Override
		public List<DashboardDto> getValue(DashboardContainerDto container) {
			return container.getDashboards();
		}

		@Override
		public Class<?> getType() {
			return List.class;
		}

		@Override
		public String getPath() {
			return "dashboards";
		}

		@Override
		public void setModified(DashboardContainerDto container, boolean modified) {
			container.dashboards_m = modified;
		}

		@Override
		public boolean isModified(DashboardContainerDto container) {
			return container.isDashboardsModified();
		}
	};

	private Long id;
	private  boolean id_m;
	public static final String PROPERTY_ID = "dpi-dashboardcontainer-id";

	private transient static PropertyAccessor<DashboardContainerDto, Long> id_pa = new PropertyAccessor<DashboardContainerDto, Long>() {
		@Override
		public void setValue(DashboardContainerDto container, Long object) {
			// id field
		}

		@Override
		public Long getValue(DashboardContainerDto container) {
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
		public void setModified(DashboardContainerDto container, boolean modified) {
			container.id_m = modified;
		}

		@Override
		public boolean isModified(DashboardContainerDto container) {
			return container.isIdModified();
		}
	};


	public DashboardContainerDto() {
		super();
	}

	public List<DashboardDto> getDashboards()  {
		if(! isDtoProxy()){
			List<DashboardDto> _currentValue = this.dashboards;
			if(null == _currentValue)
				this.dashboards = new ArrayList<DashboardDto>();

			return this.dashboards;
		}

		if(isDashboardsModified())
			return this.dashboards;

		if(! GWT.isClient())
			return null;

		List<DashboardDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().dashboards());

		_value = new ChangeMonitoredList<DashboardDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isDashboardsModified())
						setDashboards((List<DashboardDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setDashboards(List<DashboardDto> dashboards)  {
		/* old value */
		List<DashboardDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getDashboards();

		/* set new value */
		this.dashboards = dashboards;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(dashboards_pa, oldValue, dashboards, this.dashboards_m));

		/* set indicator */
		this.dashboards_m = true;

		this.fireObjectChangedEvent(DashboardContainerDtoPA.INSTANCE.dashboards(), oldValue);
	}


	public boolean isDashboardsModified()  {
		return dashboards_m;
	}


	public static PropertyAccessor<DashboardContainerDto, List<DashboardDto>> getDashboardsPropertyAccessor()  {
		return dashboards_pa;
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


	public static PropertyAccessor<DashboardContainerDto, Long> getIdPropertyAccessor()  {
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
		if(! (obj instanceof DashboardContainerDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((DashboardContainerDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new DashboardContainerDto2PosoMap();
	}

	public DashboardContainerDtoPA instantiatePropertyAccess()  {
		return GWT.create(DashboardContainerDtoPA.class);
	}

	public void clearModified()  {
		this.dashboards = null;
		this.dashboards_m = false;
		this.id = null;
		this.id_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(dashboards_m)
			return true;
		if(id_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(dashboards_pa);
		list.add(id_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(dashboards_m)
			list.add(dashboards_pa);
		if(id_m)
			list.add(id_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(dashboards_pa);
			list.add(id_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(dashboards_pa);
		return list;
	}



	net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardDto wl_0;

}
