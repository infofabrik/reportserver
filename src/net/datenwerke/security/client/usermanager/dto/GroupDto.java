package net.datenwerke.security.client.usermanager.dto;

import com.google.gwt.core.client.GWT;
import java.lang.NullPointerException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.ChangeMonitoredSet;
import net.datenwerke.gxtdto.client.dtomanager.dtomod.collections.MonitoredCollection;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.eventbus.handlers.has.HasObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.locale.UserManagerMessages;
import net.datenwerke.security.client.usermanager.dto.AbstractUserManagerNodeDto;
import net.datenwerke.security.client.usermanager.dto.GroupDto;
import net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.client.usermanager.dto.pa.GroupDtoPA;
import net.datenwerke.security.client.usermanager.dto.posomap.GroupDto2PosoMap;
import net.datenwerke.security.service.usermanager.entities.Group;

/**
 * Dto for {@link Group}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class GroupDto extends AbstractUserManagerNodeDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String description;
	private  boolean description_m;
	public static final String PROPERTY_DESCRIPTION = "dpi-group-description";

	private transient static PropertyAccessor<GroupDto, String> description_pa = new PropertyAccessor<GroupDto, String>() {
		@Override
		public void setValue(GroupDto container, String object) {
			container.setDescription(object);
		}

		@Override
		public String getValue(GroupDto container) {
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
		public void setModified(GroupDto container, boolean modified) {
			container.description_m = modified;
		}

		@Override
		public boolean isModified(GroupDto container) {
			return container.isDescriptionModified();
		}
	};

	private String name;
	private  boolean name_m;
	public static final String PROPERTY_NAME = "dpi-group-name";

	private transient static PropertyAccessor<GroupDto, String> name_pa = new PropertyAccessor<GroupDto, String>() {
		@Override
		public void setValue(GroupDto container, String object) {
			container.setName(object);
		}

		@Override
		public String getValue(GroupDto container) {
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
		public void setModified(GroupDto container, boolean modified) {
			container.name_m = modified;
		}

		@Override
		public boolean isModified(GroupDto container) {
			return container.isNameModified();
		}
	};

	private Set<OrganisationalUnitDto> ous;
	private  boolean ous_m;
	public static final String PROPERTY_OUS = "dpi-group-ous";

	private transient static PropertyAccessor<GroupDto, Set<OrganisationalUnitDto>> ous_pa = new PropertyAccessor<GroupDto, Set<OrganisationalUnitDto>>() {
		@Override
		public void setValue(GroupDto container, Set<OrganisationalUnitDto> object) {
			container.setOus(object);
		}

		@Override
		public Set<OrganisationalUnitDto> getValue(GroupDto container) {
			return container.getOus();
		}

		@Override
		public Class<?> getType() {
			return Set.class;
		}

		@Override
		public String getPath() {
			return "ous";
		}

		@Override
		public void setModified(GroupDto container, boolean modified) {
			container.ous_m = modified;
		}

		@Override
		public boolean isModified(GroupDto container) {
			return container.isOusModified();
		}
	};

	private Set<GroupDto> referencedGroups;
	private  boolean referencedGroups_m;
	public static final String PROPERTY_REFERENCED_GROUPS = "dpi-group-referencedgroups";

	private transient static PropertyAccessor<GroupDto, Set<GroupDto>> referencedGroups_pa = new PropertyAccessor<GroupDto, Set<GroupDto>>() {
		@Override
		public void setValue(GroupDto container, Set<GroupDto> object) {
			container.setReferencedGroups(object);
		}

		@Override
		public Set<GroupDto> getValue(GroupDto container) {
			return container.getReferencedGroups();
		}

		@Override
		public Class<?> getType() {
			return Set.class;
		}

		@Override
		public String getPath() {
			return "referencedGroups";
		}

		@Override
		public void setModified(GroupDto container, boolean modified) {
			container.referencedGroups_m = modified;
		}

		@Override
		public boolean isModified(GroupDto container) {
			return container.isReferencedGroupsModified();
		}
	};

	private Set<UserDto> users;
	private  boolean users_m;
	public static final String PROPERTY_USERS = "dpi-group-users";

	private transient static PropertyAccessor<GroupDto, Set<UserDto>> users_pa = new PropertyAccessor<GroupDto, Set<UserDto>>() {
		@Override
		public void setValue(GroupDto container, Set<UserDto> object) {
			container.setUsers(object);
		}

		@Override
		public Set<UserDto> getValue(GroupDto container) {
			return container.getUsers();
		}

		@Override
		public Class<?> getType() {
			return Set.class;
		}

		@Override
		public String getPath() {
			return "users";
		}

		@Override
		public void setModified(GroupDto container, boolean modified) {
			container.users_m = modified;
		}

		@Override
		public boolean isModified(GroupDto container) {
			return container.isUsersModified();
		}
	};


	public GroupDto() {
		super();
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

		this.fireObjectChangedEvent(GroupDtoPA.INSTANCE.description(), oldValue);
	}


	public boolean isDescriptionModified()  {
		return description_m;
	}


	public static PropertyAccessor<GroupDto, String> getDescriptionPropertyAccessor()  {
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

		this.fireObjectChangedEvent(GroupDtoPA.INSTANCE.name(), oldValue);
	}


	public boolean isNameModified()  {
		return name_m;
	}


	public static PropertyAccessor<GroupDto, String> getNamePropertyAccessor()  {
		return name_pa;
	}


	public Set<OrganisationalUnitDto> getOus()  {
		if(! isDtoProxy()){
			Set<OrganisationalUnitDto> _currentValue = this.ous;
			if(null == _currentValue)
				this.ous = new HashSet<OrganisationalUnitDto>();

			return this.ous;
		}

		if(isOusModified())
			return this.ous;

		if(! GWT.isClient())
			return null;

		Set<OrganisationalUnitDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().ous());

		_value = new ChangeMonitoredSet<OrganisationalUnitDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isOusModified())
						setOus((Set<OrganisationalUnitDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setOus(Set<OrganisationalUnitDto> ous)  {
		/* old value */
		Set<OrganisationalUnitDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getOus();

		/* set new value */
		this.ous = ous;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(ous_pa, oldValue, ous, this.ous_m));

		/* set indicator */
		this.ous_m = true;

		this.fireObjectChangedEvent(GroupDtoPA.INSTANCE.ous(), oldValue);
	}


	public boolean isOusModified()  {
		return ous_m;
	}


	public static PropertyAccessor<GroupDto, Set<OrganisationalUnitDto>> getOusPropertyAccessor()  {
		return ous_pa;
	}


	public Set<GroupDto> getReferencedGroups()  {
		if(! isDtoProxy()){
			Set<GroupDto> _currentValue = this.referencedGroups;
			if(null == _currentValue)
				this.referencedGroups = new HashSet<GroupDto>();

			return this.referencedGroups;
		}

		if(isReferencedGroupsModified())
			return this.referencedGroups;

		if(! GWT.isClient())
			return null;

		Set<GroupDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().referencedGroups());

		_value = new ChangeMonitoredSet<GroupDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isReferencedGroupsModified())
						setReferencedGroups((Set<GroupDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setReferencedGroups(Set<GroupDto> referencedGroups)  {
		/* old value */
		Set<GroupDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getReferencedGroups();

		/* set new value */
		this.referencedGroups = referencedGroups;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(referencedGroups_pa, oldValue, referencedGroups, this.referencedGroups_m));

		/* set indicator */
		this.referencedGroups_m = true;

		this.fireObjectChangedEvent(GroupDtoPA.INSTANCE.referencedGroups(), oldValue);
	}


	public boolean isReferencedGroupsModified()  {
		return referencedGroups_m;
	}


	public static PropertyAccessor<GroupDto, Set<GroupDto>> getReferencedGroupsPropertyAccessor()  {
		return referencedGroups_pa;
	}


	public Set<UserDto> getUsers()  {
		if(! isDtoProxy()){
			Set<UserDto> _currentValue = this.users;
			if(null == _currentValue)
				this.users = new HashSet<UserDto>();

			return this.users;
		}

		if(isUsersModified())
			return this.users;

		if(! GWT.isClient())
			return null;

		Set<UserDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().users());

		_value = new ChangeMonitoredSet<UserDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isUsersModified())
						setUsers((Set<UserDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setUsers(Set<UserDto> users)  {
		/* old value */
		Set<UserDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getUsers();

		/* set new value */
		this.users = users;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(users_pa, oldValue, users, this.users_m));

		/* set indicator */
		this.users_m = true;

		this.fireObjectChangedEvent(GroupDtoPA.INSTANCE.users(), oldValue);
	}


	public boolean isUsersModified()  {
		return users_m;
	}


	public static PropertyAccessor<GroupDto, Set<UserDto>> getUsersPropertyAccessor()  {
		return users_pa;
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
		return UserManagerMessages.INSTANCE.group();
	}

	@Override
	public BaseIcon toIcon()  {
		return BaseIcon.from("group");
	}

	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof GroupDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((GroupDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new GroupDto2PosoMap();
	}

	public GroupDtoPA instantiatePropertyAccess()  {
		return GWT.create(GroupDtoPA.class);
	}

	public void clearModified()  {
		this.description = null;
		this.description_m = false;
		this.name = null;
		this.name_m = false;
		this.ous = null;
		this.ous_m = false;
		this.referencedGroups = null;
		this.referencedGroups_m = false;
		this.users = null;
		this.users_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(description_m)
			return true;
		if(name_m)
			return true;
		if(ous_m)
			return true;
		if(referencedGroups_m)
			return true;
		if(users_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(description_pa);
		list.add(name_pa);
		list.add(ous_pa);
		list.add(referencedGroups_pa);
		list.add(users_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(description_m)
			list.add(description_pa);
		if(name_m)
			list.add(name_pa);
		if(ous_m)
			list.add(ous_pa);
		if(referencedGroups_m)
			list.add(referencedGroups_pa);
		if(users_m)
			list.add(users_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(description_pa);
			list.add(name_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(ous_pa);
			list.add(referencedGroups_pa);
			list.add(users_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(ous_pa);
		list.add(referencedGroups_pa);
		list.add(users_pa);
		return list;
	}



	net.datenwerke.security.client.usermanager.dto.UserDto wl_0;
	net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto wl_1;
	net.datenwerke.security.client.usermanager.dto.GroupDto wl_2;

}
