package net.datenwerke.rs.dashboard.client.dashboard.dto;

import com.google.gwt.core.client.GWT;
import java.lang.NullPointerException;
import java.lang.String;
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
import net.datenwerke.rs.dashboard.client.dashboard.dto.AbstractDashboardManagerNodeDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.pa.DashboardNodeDtoPA;
import net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.DashboardNodeDto2PosoMap;
import net.datenwerke.rs.dashboard.client.dashboard.locale.DashboardMessages;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardNode;

/**
 * Dto for {@link DashboardNode}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class DashboardNodeDto extends AbstractDashboardManagerNodeDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private DashboardDto dashboard;
	private  boolean dashboard_m;
	public static final String PROPERTY_DASHBOARD = "dpi-dashboardnode-dashboard";

	private transient static PropertyAccessor<DashboardNodeDto, DashboardDto> dashboard_pa = new PropertyAccessor<DashboardNodeDto, DashboardDto>() {
		@Override
		public void setValue(DashboardNodeDto container, DashboardDto object) {
			container.setDashboard(object);
		}

		@Override
		public DashboardDto getValue(DashboardNodeDto container) {
			return container.getDashboard();
		}

		@Override
		public Class<?> getType() {
			return DashboardDto.class;
		}

		@Override
		public String getPath() {
			return "dashboard";
		}

		@Override
		public void setModified(DashboardNodeDto container, boolean modified) {
			container.dashboard_m = modified;
		}

		@Override
		public boolean isModified(DashboardNodeDto container) {
			return container.isDashboardModified();
		}
	};

	private String description;
	private  boolean description_m;
	public static final String PROPERTY_DESCRIPTION = "dpi-dashboardnode-description";

	private transient static PropertyAccessor<DashboardNodeDto, String> description_pa = new PropertyAccessor<DashboardNodeDto, String>() {
		@Override
		public void setValue(DashboardNodeDto container, String object) {
			container.setDescription(object);
		}

		@Override
		public String getValue(DashboardNodeDto container) {
			return container.getDescription();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "description";
		}

		@Override
		public void setModified(DashboardNodeDto container, boolean modified) {
			container.description_m = modified;
		}

		@Override
		public boolean isModified(DashboardNodeDto container) {
			return container.isDescriptionModified();
		}
	};

	private String name;
	private  boolean name_m;
	public static final String PROPERTY_NAME = "dpi-dashboardnode-name";

	private transient static PropertyAccessor<DashboardNodeDto, String> name_pa = new PropertyAccessor<DashboardNodeDto, String>() {
		@Override
		public void setValue(DashboardNodeDto container, String object) {
			container.setName(object);
		}

		@Override
		public String getValue(DashboardNodeDto container) {
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
		public void setModified(DashboardNodeDto container, boolean modified) {
			container.name_m = modified;
		}

		@Override
		public boolean isModified(DashboardNodeDto container) {
			return container.isNameModified();
		}
	};


	public DashboardNodeDto() {
		super();
	}

	public DashboardDto getDashboard()  {
		if(! isDtoProxy()){
			return this.dashboard;
		}

		if(isDashboardModified())
			return this.dashboard;

		if(! GWT.isClient())
			return null;

		DashboardDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().dashboard());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isDashboardModified())
						setDashboard((DashboardDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setDashboard(DashboardDto dashboard)  {
		/* old value */
		DashboardDto oldValue = null;
		if(GWT.isClient())
			oldValue = getDashboard();

		/* set new value */
		this.dashboard = dashboard;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(dashboard_pa, oldValue, dashboard, this.dashboard_m));

		/* set indicator */
		this.dashboard_m = true;

		this.fireObjectChangedEvent(DashboardNodeDtoPA.INSTANCE.dashboard(), oldValue);
	}


	public boolean isDashboardModified()  {
		return dashboard_m;
	}


	public static PropertyAccessor<DashboardNodeDto, DashboardDto> getDashboardPropertyAccessor()  {
		return dashboard_pa;
	}


	public String getDescription()  {
		if(! isDtoProxy()){
			return this.description;
		}

		if(isDescriptionModified())
			return this.description;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().description());

		return _value;
	}


	public void setDescription(String description)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getDescription();

		/* set new value */
		this.description = description;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(description_pa, oldValue, description, this.description_m));

		/* set indicator */
		this.description_m = true;

		this.fireObjectChangedEvent(DashboardNodeDtoPA.INSTANCE.description(), oldValue);
	}


	public boolean isDescriptionModified()  {
		return description_m;
	}


	public static PropertyAccessor<DashboardNodeDto, String> getDescriptionPropertyAccessor()  {
		return description_pa;
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

		this.fireObjectChangedEvent(DashboardNodeDtoPA.INSTANCE.name(), oldValue);
	}


	public boolean isNameModified()  {
		return name_m;
	}


	public static PropertyAccessor<DashboardNodeDto, String> getNamePropertyAccessor()  {
		return name_pa;
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
	public String toTypeDescription()  {
		return DashboardMessages.INSTANCE.dashboard();
	}

	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof DashboardNodeDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((DashboardNodeDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new DashboardNodeDto2PosoMap();
	}

	public DashboardNodeDtoPA instantiatePropertyAccess()  {
		return GWT.create(DashboardNodeDtoPA.class);
	}

	public void clearModified()  {
		this.dashboard = null;
		this.dashboard_m = false;
		this.description = null;
		this.description_m = false;
		this.name = null;
		this.name_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(dashboard_m)
			return true;
		if(description_m)
			return true;
		if(name_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(dashboard_pa);
		list.add(description_pa);
		list.add(name_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(dashboard_m)
			list.add(dashboard_pa);
		if(description_m)
			list.add(description_pa);
		if(name_m)
			list.add(name_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(description_pa);
			list.add(name_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(dashboard_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(dashboard_pa);
		return list;
	}



	net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardDto wl_0;

}
