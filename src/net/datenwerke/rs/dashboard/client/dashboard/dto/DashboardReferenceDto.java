package net.datenwerke.rs.dashboard.client.dashboard.dto;

import com.google.gwt.core.client.GWT;
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
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardNodeDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DashboardDtoDec;
import net.datenwerke.rs.dashboard.client.dashboard.dto.pa.DashboardReferenceDtoPA;
import net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.DashboardReferenceDto2PosoMap;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardReference;

/**
 * Dto for {@link DashboardReference}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class DashboardReferenceDto extends DashboardDtoDec {


	private static final long serialVersionUID = 1;


	/* Fields */
	private DashboardNodeDto dashboardNode;
	private  boolean dashboardNode_m;
	public static final String PROPERTY_DASHBOARD_NODE = "dpi-dashboardreference-dashboardnode";

	private transient static PropertyAccessor<DashboardReferenceDto, DashboardNodeDto> dashboardNode_pa = new PropertyAccessor<DashboardReferenceDto, DashboardNodeDto>() {
		@Override
		public void setValue(DashboardReferenceDto container, DashboardNodeDto object) {
			container.setDashboardNode(object);
		}

		@Override
		public DashboardNodeDto getValue(DashboardReferenceDto container) {
			return container.getDashboardNode();
		}

		@Override
		public Class<?> getType() {
			return DashboardNodeDto.class;
		}

		@Override
		public String getPath() {
			return "dashboardNode";
		}

		@Override
		public void setModified(DashboardReferenceDto container, boolean modified) {
			container.dashboardNode_m = modified;
		}

		@Override
		public boolean isModified(DashboardReferenceDto container) {
			return container.isDashboardNodeModified();
		}
	};


	public DashboardReferenceDto() {
		super();
	}

	public DashboardNodeDto getDashboardNode()  {
		if(! isDtoProxy()){
			return this.dashboardNode;
		}

		if(isDashboardNodeModified())
			return this.dashboardNode;

		if(! GWT.isClient())
			return null;

		DashboardNodeDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().dashboardNode());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isDashboardNodeModified())
						setDashboardNode((DashboardNodeDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setDashboardNode(DashboardNodeDto dashboardNode)  {
		/* old value */
		DashboardNodeDto oldValue = null;
		if(GWT.isClient())
			oldValue = getDashboardNode();

		/* set new value */
		this.dashboardNode = dashboardNode;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(dashboardNode_pa, oldValue, dashboardNode, this.dashboardNode_m));

		/* set indicator */
		this.dashboardNode_m = true;

		this.fireObjectChangedEvent(DashboardReferenceDtoPA.INSTANCE.dashboardNode(), oldValue);
	}


	public boolean isDashboardNodeModified()  {
		return dashboardNode_m;
	}


	public static PropertyAccessor<DashboardReferenceDto, DashboardNodeDto> getDashboardNodePropertyAccessor()  {
		return dashboardNode_pa;
	}


	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof DashboardReferenceDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((DashboardReferenceDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new DashboardReferenceDto2PosoMap();
	}

	public DashboardReferenceDtoPA instantiatePropertyAccess()  {
		return GWT.create(DashboardReferenceDtoPA.class);
	}

	public void clearModified()  {
		this.dashboardNode = null;
		this.dashboardNode_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(dashboardNode_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(dashboardNode_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(dashboardNode_m)
			list.add(dashboardNode_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(dashboardNode_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(dashboardNode_pa);
		return list;
	}



	net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardNodeDto wl_0;

}
