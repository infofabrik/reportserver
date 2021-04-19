package net.datenwerke.rs.teamspace.client.teamspace.dto;

import com.google.gwt.core.client.GWT;
import java.lang.Boolean;
import java.lang.IllegalStateException;
import java.lang.Long;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.IdedDto;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.ChangeMonitoredSet;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.MonitoredCollection;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.eventbus.handlers.has.HasObjectChangedEventHandler;
import net.datenwerke.rs.teamspace.client.teamspace.dto.AppPropertyDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.pa.TeamSpaceAppDtoPA;
import net.datenwerke.rs.teamspace.client.teamspace.dto.posomap.TeamSpaceAppDto2PosoMap;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpaceApp;

/**
 * Dto for {@link TeamSpaceApp}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class TeamSpaceAppDto extends RsDto implements IdedDto {


	private static final long serialVersionUID = 1;

	private Long dtoId;


	/* Fields */
	private Set<AppPropertyDto> appProperties;
	private  boolean appProperties_m;
	public static final String PROPERTY_APP_PROPERTIES = "dpi-teamspaceapp-appproperties";

	private transient static PropertyAccessor<TeamSpaceAppDto, Set<AppPropertyDto>> appProperties_pa = new PropertyAccessor<TeamSpaceAppDto, Set<AppPropertyDto>>() {
		@Override
		public void setValue(TeamSpaceAppDto container, Set<AppPropertyDto> object) {
			container.setAppProperties(object);
		}

		@Override
		public Set<AppPropertyDto> getValue(TeamSpaceAppDto container) {
			return container.getAppProperties();
		}

		@Override
		public Class<?> getType() {
			return Set.class;
		}

		@Override
		public String getPath() {
			return "appProperties";
		}

		@Override
		public void setModified(TeamSpaceAppDto container, boolean modified) {
			container.appProperties_m = modified;
		}

		@Override
		public boolean isModified(TeamSpaceAppDto container) {
			return container.isAppPropertiesModified();
		}
	};

	private Long id;
	private  boolean id_m;
	public static final String PROPERTY_ID = "dpi-teamspaceapp-id";

	private transient static PropertyAccessor<TeamSpaceAppDto, Long> id_pa = new PropertyAccessor<TeamSpaceAppDto, Long>() {
		@Override
		public void setValue(TeamSpaceAppDto container, Long object) {
			// id field
		}

		@Override
		public Long getValue(TeamSpaceAppDto container) {
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
		public void setModified(TeamSpaceAppDto container, boolean modified) {
			container.id_m = modified;
		}

		@Override
		public boolean isModified(TeamSpaceAppDto container) {
			return container.isIdModified();
		}
	};

	private Boolean installed;
	private  boolean installed_m;
	public static final String PROPERTY_INSTALLED = "dpi-teamspaceapp-installed";

	private transient static PropertyAccessor<TeamSpaceAppDto, Boolean> installed_pa = new PropertyAccessor<TeamSpaceAppDto, Boolean>() {
		@Override
		public void setValue(TeamSpaceAppDto container, Boolean object) {
			container.setInstalled(object);
		}

		@Override
		public Boolean getValue(TeamSpaceAppDto container) {
			return container.isInstalled();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "installed";
		}

		@Override
		public void setModified(TeamSpaceAppDto container, boolean modified) {
			container.installed_m = modified;
		}

		@Override
		public boolean isModified(TeamSpaceAppDto container) {
			return container.isInstalledModified();
		}
	};

	private String type;
	private  boolean type_m;
	public static final String PROPERTY_TYPE = "dpi-teamspaceapp-type";

	private transient static PropertyAccessor<TeamSpaceAppDto, String> type_pa = new PropertyAccessor<TeamSpaceAppDto, String>() {
		@Override
		public void setValue(TeamSpaceAppDto container, String object) {
			container.setType(object);
		}

		@Override
		public String getValue(TeamSpaceAppDto container) {
			return container.getType();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "type";
		}

		@Override
		public void setModified(TeamSpaceAppDto container, boolean modified) {
			container.type_m = modified;
		}

		@Override
		public boolean isModified(TeamSpaceAppDto container) {
			return container.isTypeModified();
		}
	};


	public TeamSpaceAppDto() {
		super();
	}

	public Set<AppPropertyDto> getAppProperties()  {
		if(! isDtoProxy()){
			Set<AppPropertyDto> _currentValue = this.appProperties;
			if(null == _currentValue)
				this.appProperties = new HashSet<AppPropertyDto>();

			return this.appProperties;
		}

		if(isAppPropertiesModified())
			return this.appProperties;

		if(! GWT.isClient())
			return null;

		Set<AppPropertyDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().appProperties());

		_value = new ChangeMonitoredSet<AppPropertyDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isAppPropertiesModified())
						setAppProperties((Set<AppPropertyDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setAppProperties(Set<AppPropertyDto> appProperties)  {
		/* old value */
		Set<AppPropertyDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getAppProperties();

		/* set new value */
		this.appProperties = appProperties;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(appProperties_pa, oldValue, appProperties, this.appProperties_m));

		/* set indicator */
		this.appProperties_m = true;

		this.fireObjectChangedEvent(TeamSpaceAppDtoPA.INSTANCE.appProperties(), oldValue);
	}


	public boolean isAppPropertiesModified()  {
		return appProperties_m;
	}


	public static PropertyAccessor<TeamSpaceAppDto, Set<AppPropertyDto>> getAppPropertiesPropertyAccessor()  {
		return appProperties_pa;
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


	public static PropertyAccessor<TeamSpaceAppDto, Long> getIdPropertyAccessor()  {
		return id_pa;
	}


	public Boolean isInstalled()  {
		if(! isDtoProxy()){
			return this.installed;
		}

		if(isInstalledModified())
			return this.installed;

		if(! GWT.isClient())
			return null;

		Boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().installed());

		return _value;
	}


	public void setInstalled(Boolean installed)  {
		/* old value */
		Boolean oldValue = null;
		if(GWT.isClient())
			oldValue = isInstalled();

		/* set new value */
		this.installed = installed;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(installed_pa, oldValue, installed, this.installed_m));

		/* set indicator */
		this.installed_m = true;

		this.fireObjectChangedEvent(TeamSpaceAppDtoPA.INSTANCE.installed(), oldValue);
	}


	public boolean isInstalledModified()  {
		return installed_m;
	}


	public static PropertyAccessor<TeamSpaceAppDto, Boolean> getInstalledPropertyAccessor()  {
		return installed_pa;
	}


	public String getType()  {
		if(! isDtoProxy()){
			return this.type;
		}

		if(isTypeModified())
			return this.type;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().type());

		return _value;
	}


	public void setType(String type)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getType();

		/* set new value */
		this.type = type;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(type_pa, oldValue, type, this.type_m));

		/* set indicator */
		this.type_m = true;

		this.fireObjectChangedEvent(TeamSpaceAppDtoPA.INSTANCE.type(), oldValue);
	}


	public boolean isTypeModified()  {
		return type_m;
	}


	public static PropertyAccessor<TeamSpaceAppDto, String> getTypePropertyAccessor()  {
		return type_pa;
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
		if(! (obj instanceof TeamSpaceAppDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((TeamSpaceAppDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new TeamSpaceAppDto2PosoMap();
	}

	public TeamSpaceAppDtoPA instantiatePropertyAccess()  {
		return GWT.create(TeamSpaceAppDtoPA.class);
	}

	public void clearModified()  {
		this.appProperties = null;
		this.appProperties_m = false;
		this.id = null;
		this.id_m = false;
		this.installed = null;
		this.installed_m = false;
		this.type = null;
		this.type_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(appProperties_m)
			return true;
		if(id_m)
			return true;
		if(installed_m)
			return true;
		if(type_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(appProperties_pa);
		list.add(id_pa);
		list.add(installed_pa);
		list.add(type_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(appProperties_m)
			list.add(appProperties_pa);
		if(id_m)
			list.add(id_pa);
		if(installed_m)
			list.add(installed_pa);
		if(type_m)
			list.add(type_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(id_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(appProperties_pa);
			list.add(installed_pa);
			list.add(type_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(appProperties_pa);
		return list;
	}



	net.datenwerke.rs.teamspace.client.teamspace.dto.AppPropertyDto wl_0;

}
