package net.datenwerke.rs.teamspace.client.teamspace.dto;

import com.google.gwt.core.client.GWT;
import java.lang.IllegalStateException;
import java.lang.Long;
import java.lang.NullPointerException;
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
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceAppDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceMemberDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.pa.TeamSpaceDtoPA;
import net.datenwerke.rs.teamspace.client.teamspace.dto.posomap.TeamSpaceDto2PosoMap;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace;
import net.datenwerke.security.client.usermanager.dto.UserDto;

/**
 * Dto for {@link TeamSpace}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class TeamSpaceDto extends RsDto implements IdedDto {


	private static final long serialVersionUID = 1;

	private Long dtoId;


	/* Fields */
	private Set<TeamSpaceAppDto> apps;
	private  boolean apps_m;
	public static final String PROPERTY_APPS = "dpi-teamspace-apps";

	private transient static PropertyAccessor<TeamSpaceDto, Set<TeamSpaceAppDto>> apps_pa = new PropertyAccessor<TeamSpaceDto, Set<TeamSpaceAppDto>>() {
		@Override
		public void setValue(TeamSpaceDto container, Set<TeamSpaceAppDto> object) {
			container.setApps(object);
		}

		@Override
		public Set<TeamSpaceAppDto> getValue(TeamSpaceDto container) {
			return container.getApps();
		}

		@Override
		public Class<?> getType() {
			return Set.class;
		}

		@Override
		public String getPath() {
			return "apps";
		}

		@Override
		public void setModified(TeamSpaceDto container, boolean modified) {
			container.apps_m = modified;
		}

		@Override
		public boolean isModified(TeamSpaceDto container) {
			return container.isAppsModified();
		}
	};

	private String description;
	private  boolean description_m;
	public static final String PROPERTY_DESCRIPTION = "dpi-teamspace-description";

	private transient static PropertyAccessor<TeamSpaceDto, String> description_pa = new PropertyAccessor<TeamSpaceDto, String>() {
		@Override
		public void setValue(TeamSpaceDto container, String object) {
			container.setDescription(object);
		}

		@Override
		public String getValue(TeamSpaceDto container) {
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
		public void setModified(TeamSpaceDto container, boolean modified) {
			container.description_m = modified;
		}

		@Override
		public boolean isModified(TeamSpaceDto container) {
			return container.isDescriptionModified();
		}
	};

	private Long id;
	private  boolean id_m;
	public static final String PROPERTY_ID = "dpi-teamspace-id";

	private transient static PropertyAccessor<TeamSpaceDto, Long> id_pa = new PropertyAccessor<TeamSpaceDto, Long>() {
		@Override
		public void setValue(TeamSpaceDto container, Long object) {
			// id field
		}

		@Override
		public Long getValue(TeamSpaceDto container) {
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
		public void setModified(TeamSpaceDto container, boolean modified) {
			container.id_m = modified;
		}

		@Override
		public boolean isModified(TeamSpaceDto container) {
			return container.isIdModified();
		}
	};

	private Set<TeamSpaceMemberDto> members;
	private  boolean members_m;
	public static final String PROPERTY_MEMBERS = "dpi-teamspace-members";

	private transient static PropertyAccessor<TeamSpaceDto, Set<TeamSpaceMemberDto>> members_pa = new PropertyAccessor<TeamSpaceDto, Set<TeamSpaceMemberDto>>() {
		@Override
		public void setValue(TeamSpaceDto container, Set<TeamSpaceMemberDto> object) {
			container.setMembers(object);
		}

		@Override
		public Set<TeamSpaceMemberDto> getValue(TeamSpaceDto container) {
			return container.getMembers();
		}

		@Override
		public Class<?> getType() {
			return Set.class;
		}

		@Override
		public String getPath() {
			return "members";
		}

		@Override
		public void setModified(TeamSpaceDto container, boolean modified) {
			container.members_m = modified;
		}

		@Override
		public boolean isModified(TeamSpaceDto container) {
			return container.isMembersModified();
		}
	};

	private String name;
	private  boolean name_m;
	public static final String PROPERTY_NAME = "dpi-teamspace-name";

	private transient static PropertyAccessor<TeamSpaceDto, String> name_pa = new PropertyAccessor<TeamSpaceDto, String>() {
		@Override
		public void setValue(TeamSpaceDto container, String object) {
			container.setName(object);
		}

		@Override
		public String getValue(TeamSpaceDto container) {
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
		public void setModified(TeamSpaceDto container, boolean modified) {
			container.name_m = modified;
		}

		@Override
		public boolean isModified(TeamSpaceDto container) {
			return container.isNameModified();
		}
	};

	private UserDto owner;
	private  boolean owner_m;
	public static final String PROPERTY_OWNER = "dpi-teamspace-owner";

	private transient static PropertyAccessor<TeamSpaceDto, UserDto> owner_pa = new PropertyAccessor<TeamSpaceDto, UserDto>() {
		@Override
		public void setValue(TeamSpaceDto container, UserDto object) {
			container.setOwner(object);
		}

		@Override
		public UserDto getValue(TeamSpaceDto container) {
			return container.getOwner();
		}

		@Override
		public Class<?> getType() {
			return UserDto.class;
		}

		@Override
		public String getPath() {
			return "owner";
		}

		@Override
		public void setModified(TeamSpaceDto container, boolean modified) {
			container.owner_m = modified;
		}

		@Override
		public boolean isModified(TeamSpaceDto container) {
			return container.isOwnerModified();
		}
	};


	public TeamSpaceDto() {
		super();
	}

	public Set<TeamSpaceAppDto> getApps()  {
		if(! isDtoProxy()){
			Set<TeamSpaceAppDto> _currentValue = this.apps;
			if(null == _currentValue)
				this.apps = new HashSet<TeamSpaceAppDto>();

			return this.apps;
		}

		if(isAppsModified())
			return this.apps;

		if(! GWT.isClient())
			return null;

		Set<TeamSpaceAppDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().apps());

		_value = new ChangeMonitoredSet<TeamSpaceAppDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isAppsModified())
						setApps((Set<TeamSpaceAppDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setApps(Set<TeamSpaceAppDto> apps)  {
		/* old value */
		Set<TeamSpaceAppDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getApps();

		/* set new value */
		this.apps = apps;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(apps_pa, oldValue, apps, this.apps_m));

		/* set indicator */
		this.apps_m = true;

		this.fireObjectChangedEvent(TeamSpaceDtoPA.INSTANCE.apps(), oldValue);
	}


	public boolean isAppsModified()  {
		return apps_m;
	}


	public static PropertyAccessor<TeamSpaceDto, Set<TeamSpaceAppDto>> getAppsPropertyAccessor()  {
		return apps_pa;
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

		this.fireObjectChangedEvent(TeamSpaceDtoPA.INSTANCE.description(), oldValue);
	}


	public boolean isDescriptionModified()  {
		return description_m;
	}


	public static PropertyAccessor<TeamSpaceDto, String> getDescriptionPropertyAccessor()  {
		return description_pa;
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


	public static PropertyAccessor<TeamSpaceDto, Long> getIdPropertyAccessor()  {
		return id_pa;
	}


	public Set<TeamSpaceMemberDto> getMembers()  {
		if(! isDtoProxy()){
			Set<TeamSpaceMemberDto> _currentValue = this.members;
			if(null == _currentValue)
				this.members = new HashSet<TeamSpaceMemberDto>();

			return this.members;
		}

		if(isMembersModified())
			return this.members;

		if(! GWT.isClient())
			return null;

		Set<TeamSpaceMemberDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().members());

		_value = new ChangeMonitoredSet<TeamSpaceMemberDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isMembersModified())
						setMembers((Set<TeamSpaceMemberDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setMembers(Set<TeamSpaceMemberDto> members)  {
		/* old value */
		Set<TeamSpaceMemberDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getMembers();

		/* set new value */
		this.members = members;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(members_pa, oldValue, members, this.members_m));

		/* set indicator */
		this.members_m = true;

		this.fireObjectChangedEvent(TeamSpaceDtoPA.INSTANCE.members(), oldValue);
	}


	public boolean isMembersModified()  {
		return members_m;
	}


	public static PropertyAccessor<TeamSpaceDto, Set<TeamSpaceMemberDto>> getMembersPropertyAccessor()  {
		return members_pa;
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

		this.fireObjectChangedEvent(TeamSpaceDtoPA.INSTANCE.name(), oldValue);
	}


	public boolean isNameModified()  {
		return name_m;
	}


	public static PropertyAccessor<TeamSpaceDto, String> getNamePropertyAccessor()  {
		return name_pa;
	}


	public UserDto getOwner()  {
		if(! isDtoProxy()){
			return this.owner;
		}

		if(isOwnerModified())
			return this.owner;

		if(! GWT.isClient())
			return null;

		UserDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().owner());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isOwnerModified())
						setOwner((UserDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setOwner(UserDto owner)  {
		/* old value */
		UserDto oldValue = null;
		if(GWT.isClient())
			oldValue = getOwner();

		/* set new value */
		this.owner = owner;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(owner_pa, oldValue, owner, this.owner_m));

		/* set indicator */
		this.owner_m = true;

		this.fireObjectChangedEvent(TeamSpaceDtoPA.INSTANCE.owner(), oldValue);
	}


	public boolean isOwnerModified()  {
		return owner_m;
	}


	public static PropertyAccessor<TeamSpaceDto, UserDto> getOwnerPropertyAccessor()  {
		return owner_pa;
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
	public String toDisplayTitle()  {
		try{
			return getName();
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
		if(! (obj instanceof TeamSpaceDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((TeamSpaceDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new TeamSpaceDto2PosoMap();
	}

	public TeamSpaceDtoPA instantiatePropertyAccess()  {
		return GWT.create(TeamSpaceDtoPA.class);
	}

	public void clearModified()  {
		this.apps = null;
		this.apps_m = false;
		this.description = null;
		this.description_m = false;
		this.id = null;
		this.id_m = false;
		this.members = null;
		this.members_m = false;
		this.name = null;
		this.name_m = false;
		this.owner = null;
		this.owner_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(apps_m)
			return true;
		if(description_m)
			return true;
		if(id_m)
			return true;
		if(members_m)
			return true;
		if(name_m)
			return true;
		if(owner_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(apps_pa);
		list.add(description_pa);
		list.add(id_pa);
		list.add(members_pa);
		list.add(name_pa);
		list.add(owner_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(apps_m)
			list.add(apps_pa);
		if(description_m)
			list.add(description_pa);
		if(id_m)
			list.add(id_pa);
		if(members_m)
			list.add(members_pa);
		if(name_m)
			list.add(name_pa);
		if(owner_m)
			list.add(owner_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(id_pa);
		}
		if(view.compareTo(DtoView.LIST) >= 0){
			list.add(description_pa);
			list.add(name_pa);
			list.add(owner_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(apps_pa);
		}
		if(view.compareTo(DtoView.ALL) >= 0){
			list.add(members_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(apps_pa);
		list.add(members_pa);
		list.add(owner_pa);
		return list;
	}



	net.datenwerke.security.client.usermanager.dto.UserDto wl_0;
	net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceAppDto wl_1;
	net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceMemberDto wl_2;

}
