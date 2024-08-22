package net.datenwerke.security.client.usermanager.dto;

import com.google.gwt.core.client.GWT;
import java.lang.Boolean;
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
import net.datenwerke.security.client.usermanager.dto.SexDto;
import net.datenwerke.security.client.usermanager.dto.UserPropertyDto;
import net.datenwerke.security.client.usermanager.dto.pa.UserDtoPA;
import net.datenwerke.security.client.usermanager.dto.posomap.UserDto2PosoMap;
import net.datenwerke.security.service.usermanager.entities.User;

/**
 * Dto for {@link User}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class UserDto extends AbstractUserManagerNodeDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private boolean active;
	private  boolean active_m;
	public static final String PROPERTY_ACTIVE = "dpi-user-active";

	private transient static PropertyAccessor<UserDto, Boolean> active_pa = new PropertyAccessor<UserDto, Boolean>() {
		@Override
		public void setValue(UserDto container, Boolean object) {
			container.setActive(object);
		}

		@Override
		public Boolean getValue(UserDto container) {
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
		public void setModified(UserDto container, boolean modified) {
			container.active_m = modified;
		}

		@Override
		public boolean isModified(UserDto container) {
			return container.isActiveModified();
		}
	};

	private String email;
	private  boolean email_m;
	public static final String PROPERTY_EMAIL = "dpi-user-email";

	private transient static PropertyAccessor<UserDto, String> email_pa = new PropertyAccessor<UserDto, String>() {
		@Override
		public void setValue(UserDto container, String object) {
			container.setEmail(object);
		}

		@Override
		public String getValue(UserDto container) {
			return container.getEmail();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "email";
		}

		@Override
		public void setModified(UserDto container, boolean modified) {
			container.email_m = modified;
		}

		@Override
		public boolean isModified(UserDto container) {
			return container.isEmailModified();
		}
	};

	private String firstname;
	private  boolean firstname_m;
	public static final String PROPERTY_FIRSTNAME = "dpi-user-firstname";

	private transient static PropertyAccessor<UserDto, String> firstname_pa = new PropertyAccessor<UserDto, String>() {
		@Override
		public void setValue(UserDto container, String object) {
			container.setFirstname(object);
		}

		@Override
		public String getValue(UserDto container) {
			return container.getFirstname();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "firstname";
		}

		@Override
		public void setModified(UserDto container, boolean modified) {
			container.firstname_m = modified;
		}

		@Override
		public boolean isModified(UserDto container) {
			return container.isFirstnameModified();
		}
	};

	private Set<GroupDto> groups;
	private  boolean groups_m;
	public static final String PROPERTY_GROUPS = "dpi-user-groups";

	private transient static PropertyAccessor<UserDto, Set<GroupDto>> groups_pa = new PropertyAccessor<UserDto, Set<GroupDto>>() {
		@Override
		public void setValue(UserDto container, Set<GroupDto> object) {
			container.setGroups(object);
		}

		@Override
		public Set<GroupDto> getValue(UserDto container) {
			return container.getGroups();
		}

		@Override
		public Class<?> getType() {
			return Set.class;
		}

		@Override
		public String getPath() {
			return "groups";
		}

		@Override
		public void setModified(UserDto container, boolean modified) {
			container.groups_m = modified;
		}

		@Override
		public boolean isModified(UserDto container) {
			return container.isGroupsModified();
		}
	};

	private String lastname;
	private  boolean lastname_m;
	public static final String PROPERTY_LASTNAME = "dpi-user-lastname";

	private transient static PropertyAccessor<UserDto, String> lastname_pa = new PropertyAccessor<UserDto, String>() {
		@Override
		public void setValue(UserDto container, String object) {
			container.setLastname(object);
		}

		@Override
		public String getValue(UserDto container) {
			return container.getLastname();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "lastname";
		}

		@Override
		public void setModified(UserDto container, boolean modified) {
			container.lastname_m = modified;
		}

		@Override
		public boolean isModified(UserDto container) {
			return container.isLastnameModified();
		}
	};

	private String password;
	private  boolean password_m;
	public static final String PROPERTY_PASSWORD = "dpi-user-password";

	private transient static PropertyAccessor<UserDto, String> password_pa = new PropertyAccessor<UserDto, String>() {
		@Override
		public void setValue(UserDto container, String object) {
			container.setPassword(object);
		}

		@Override
		public String getValue(UserDto container) {
			return container.getPassword();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "password";
		}

		@Override
		public void setModified(UserDto container, boolean modified) {
			container.password_m = modified;
		}

		@Override
		public boolean isModified(UserDto container) {
			return container.isPasswordModified();
		}
	};

	private Set<UserPropertyDto> properties;
	private  boolean properties_m;
	public static final String PROPERTY_PROPERTIES = "dpi-user-properties";

	private transient static PropertyAccessor<UserDto, Set<UserPropertyDto>> properties_pa = new PropertyAccessor<UserDto, Set<UserPropertyDto>>() {
		@Override
		public void setValue(UserDto container, Set<UserPropertyDto> object) {
			container.setProperties(object);
		}

		@Override
		public Set<UserPropertyDto> getValue(UserDto container) {
			return container.getProperties();
		}

		@Override
		public Class<?> getType() {
			return Set.class;
		}

		@Override
		public String getPath() {
			return "properties";
		}

		@Override
		public void setModified(UserDto container, boolean modified) {
			container.properties_m = modified;
		}

		@Override
		public boolean isModified(UserDto container) {
			return container.isPropertiesModified();
		}
	};

	private SexDto sex;
	private  boolean sex_m;
	public static final String PROPERTY_SEX = "dpi-user-sex";

	private transient static PropertyAccessor<UserDto, SexDto> sex_pa = new PropertyAccessor<UserDto, SexDto>() {
		@Override
		public void setValue(UserDto container, SexDto object) {
			container.setSex(object);
		}

		@Override
		public SexDto getValue(UserDto container) {
			return container.getSex();
		}

		@Override
		public Class<?> getType() {
			return SexDto.class;
		}

		@Override
		public String getPath() {
			return "sex";
		}

		@Override
		public void setModified(UserDto container, boolean modified) {
			container.sex_m = modified;
		}

		@Override
		public boolean isModified(UserDto container) {
			return container.isSexModified();
		}
	};

	private Boolean superUser;
	private  boolean superUser_m;
	public static final String PROPERTY_SUPER_USER = "dpi-user-superuser";

	private transient static PropertyAccessor<UserDto, Boolean> superUser_pa = new PropertyAccessor<UserDto, Boolean>() {
		@Override
		public void setValue(UserDto container, Boolean object) {
			container.setSuperUser(object);
		}

		@Override
		public Boolean getValue(UserDto container) {
			return container.isSuperUser();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "superUser";
		}

		@Override
		public void setModified(UserDto container, boolean modified) {
			container.superUser_m = modified;
		}

		@Override
		public boolean isModified(UserDto container) {
			return container.isSuperUserModified();
		}
	};

	private String title;
	private  boolean title_m;
	public static final String PROPERTY_TITLE = "dpi-user-title";

	private transient static PropertyAccessor<UserDto, String> title_pa = new PropertyAccessor<UserDto, String>() {
		@Override
		public void setValue(UserDto container, String object) {
			container.setTitle(object);
		}

		@Override
		public String getValue(UserDto container) {
			return container.getTitle();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "title";
		}

		@Override
		public void setModified(UserDto container, boolean modified) {
			container.title_m = modified;
		}

		@Override
		public boolean isModified(UserDto container) {
			return container.isTitleModified();
		}
	};

	private String username;
	private  boolean username_m;
	public static final String PROPERTY_USERNAME = "dpi-user-username";

	private transient static PropertyAccessor<UserDto, String> username_pa = new PropertyAccessor<UserDto, String>() {
		@Override
		public void setValue(UserDto container, String object) {
			container.setUsername(object);
		}

		@Override
		public String getValue(UserDto container) {
			return container.getUsername();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "username";
		}

		@Override
		public void setModified(UserDto container, boolean modified) {
			container.username_m = modified;
		}

		@Override
		public boolean isModified(UserDto container) {
			return container.isUsernameModified();
		}
	};

	private Boolean hasPassword;
	private  boolean hasPassword_m;
	public static final String PROPERTY_HAS_PASSWORD = "dpi-user-haspassword";

	private transient static PropertyAccessor<UserDto, Boolean> hasPassword_pa = new PropertyAccessor<UserDto, Boolean>() {
		@Override
		public void setValue(UserDto container, Boolean object) {
			container.setHasPassword(object);
		}

		@Override
		public Boolean getValue(UserDto container) {
			return container.isHasPassword();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "hasPassword";
		}

		@Override
		public void setModified(UserDto container, boolean modified) {
			container.hasPassword_m = modified;
		}

		@Override
		public boolean isModified(UserDto container) {
			return container.isHasPasswordModified();
		}
	};


	public UserDto() {
		super();
	}

	public boolean isActive()  {
		if(! isDtoProxy()){
			return this.active;
		}

		if(isActiveModified())
			return this.active;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().active());

		return _value;
	}


	public void setActive(boolean active)  {
		/* old value */
		boolean oldValue = false;
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

		this.fireObjectChangedEvent(UserDtoPA.INSTANCE.active(), oldValue);
	}


	public boolean isActiveModified()  {
		return active_m;
	}


	public static PropertyAccessor<UserDto, Boolean> getActivePropertyAccessor()  {
		return active_pa;
	}


	public String getEmail()  {
		if(! isDtoProxy()){
			return this.email;
		}

		if(isEmailModified())
			return this.email;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().email());

		return _value;
	}


	public void setEmail(String email)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getEmail();

		/* set new value */
		this.email = email;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(email_pa, oldValue, email, this.email_m));

		/* set indicator */
		this.email_m = true;

		this.fireObjectChangedEvent(UserDtoPA.INSTANCE.email(), oldValue);
	}


	public boolean isEmailModified()  {
		return email_m;
	}


	public static PropertyAccessor<UserDto, String> getEmailPropertyAccessor()  {
		return email_pa;
	}


	public String getFirstname()  {
		if(! isDtoProxy()){
			return this.firstname;
		}

		if(isFirstnameModified())
			return this.firstname;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().firstname());

		return _value;
	}


	public void setFirstname(String firstname)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getFirstname();

		/* set new value */
		this.firstname = firstname;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(firstname_pa, oldValue, firstname, this.firstname_m));

		/* set indicator */
		this.firstname_m = true;

		this.fireObjectChangedEvent(UserDtoPA.INSTANCE.firstname(), oldValue);
	}


	public boolean isFirstnameModified()  {
		return firstname_m;
	}


	public static PropertyAccessor<UserDto, String> getFirstnamePropertyAccessor()  {
		return firstname_pa;
	}


	public Set<GroupDto> getGroups()  {
		if(! isDtoProxy()){
			Set<GroupDto> _currentValue = this.groups;
			if(null == _currentValue)
				this.groups = new HashSet<GroupDto>();

			return this.groups;
		}

		if(isGroupsModified())
			return this.groups;

		if(! GWT.isClient())
			return null;

		Set<GroupDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().groups());

		_value = new ChangeMonitoredSet<GroupDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isGroupsModified())
						setGroups((Set<GroupDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setGroups(Set<GroupDto> groups)  {
		/* old value */
		Set<GroupDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getGroups();

		/* set new value */
		this.groups = groups;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(groups_pa, oldValue, groups, this.groups_m));

		/* set indicator */
		this.groups_m = true;

		this.fireObjectChangedEvent(UserDtoPA.INSTANCE.groups(), oldValue);
	}


	public boolean isGroupsModified()  {
		return groups_m;
	}


	public static PropertyAccessor<UserDto, Set<GroupDto>> getGroupsPropertyAccessor()  {
		return groups_pa;
	}


	public String getLastname()  {
		if(! isDtoProxy()){
			return this.lastname;
		}

		if(isLastnameModified())
			return this.lastname;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().lastname());

		return _value;
	}


	public void setLastname(String lastname)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getLastname();

		/* set new value */
		this.lastname = lastname;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(lastname_pa, oldValue, lastname, this.lastname_m));

		/* set indicator */
		this.lastname_m = true;

		this.fireObjectChangedEvent(UserDtoPA.INSTANCE.lastname(), oldValue);
	}


	public boolean isLastnameModified()  {
		return lastname_m;
	}


	public static PropertyAccessor<UserDto, String> getLastnamePropertyAccessor()  {
		return lastname_pa;
	}


	public String getPassword()  {
		if(! isDtoProxy()){
			return this.password;
		}

		if(isPasswordModified())
			return this.password;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().password());

		return _value;
	}


	public void setPassword(String password)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getPassword();

		/* set new value */
		this.password = password;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(password_pa, oldValue, password, this.password_m));

		/* set indicator */
		this.password_m = true;

		this.fireObjectChangedEvent(UserDtoPA.INSTANCE.password(), oldValue);
	}


	public boolean isPasswordModified()  {
		return password_m;
	}


	public static PropertyAccessor<UserDto, String> getPasswordPropertyAccessor()  {
		return password_pa;
	}


	public Set<UserPropertyDto> getProperties()  {
		if(! isDtoProxy()){
			Set<UserPropertyDto> _currentValue = this.properties;
			if(null == _currentValue)
				this.properties = new HashSet<UserPropertyDto>();

			return this.properties;
		}

		if(isPropertiesModified())
			return this.properties;

		if(! GWT.isClient())
			return null;

		Set<UserPropertyDto> _value = dtoManager.getProperty(this, instantiatePropertyAccess().properties());

		_value = new ChangeMonitoredSet<UserPropertyDto>(_value);
		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isPropertiesModified())
						setProperties((Set<UserPropertyDto>) ((MonitoredCollection) event.getObject()).getUnderlyingCollection());
				}
			}
			);
		}
		return _value;
	}


	public void setProperties(Set<UserPropertyDto> properties)  {
		/* old value */
		Set<UserPropertyDto> oldValue = null;
		if(GWT.isClient())
			oldValue = getProperties();

		/* set new value */
		this.properties = properties;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(properties_pa, oldValue, properties, this.properties_m));

		/* set indicator */
		this.properties_m = true;

		this.fireObjectChangedEvent(UserDtoPA.INSTANCE.properties(), oldValue);
	}


	public boolean isPropertiesModified()  {
		return properties_m;
	}


	public static PropertyAccessor<UserDto, Set<UserPropertyDto>> getPropertiesPropertyAccessor()  {
		return properties_pa;
	}


	public SexDto getSex()  {
		if(! isDtoProxy()){
			return this.sex;
		}

		if(isSexModified())
			return this.sex;

		if(! GWT.isClient())
			return null;

		SexDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().sex());

		return _value;
	}


	public void setSex(SexDto sex)  {
		/* old value */
		SexDto oldValue = null;
		if(GWT.isClient())
			oldValue = getSex();

		/* set new value */
		this.sex = sex;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(sex_pa, oldValue, sex, this.sex_m));

		/* set indicator */
		this.sex_m = true;

		this.fireObjectChangedEvent(UserDtoPA.INSTANCE.sex(), oldValue);
	}


	public boolean isSexModified()  {
		return sex_m;
	}


	public static PropertyAccessor<UserDto, SexDto> getSexPropertyAccessor()  {
		return sex_pa;
	}


	public Boolean isSuperUser()  {
		if(! isDtoProxy()){
			return this.superUser;
		}

		if(isSuperUserModified())
			return this.superUser;

		if(! GWT.isClient())
			return null;

		Boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().superUser());

		return _value;
	}


	public void setSuperUser(Boolean superUser)  {
		/* old value */
		Boolean oldValue = null;
		if(GWT.isClient())
			oldValue = isSuperUser();

		/* set new value */
		this.superUser = superUser;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(superUser_pa, oldValue, superUser, this.superUser_m));

		/* set indicator */
		this.superUser_m = true;

		this.fireObjectChangedEvent(UserDtoPA.INSTANCE.superUser(), oldValue);
	}


	public boolean isSuperUserModified()  {
		return superUser_m;
	}


	public static PropertyAccessor<UserDto, Boolean> getSuperUserPropertyAccessor()  {
		return superUser_pa;
	}


	public String getTitle()  {
		if(! isDtoProxy()){
			return this.title;
		}

		if(isTitleModified())
			return this.title;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().title());

		return _value;
	}


	public void setTitle(String title)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getTitle();

		/* set new value */
		this.title = title;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(title_pa, oldValue, title, this.title_m));

		/* set indicator */
		this.title_m = true;

		this.fireObjectChangedEvent(UserDtoPA.INSTANCE.title(), oldValue);
	}


	public boolean isTitleModified()  {
		return title_m;
	}


	public static PropertyAccessor<UserDto, String> getTitlePropertyAccessor()  {
		return title_pa;
	}


	public String getUsername()  {
		if(! isDtoProxy()){
			return this.username;
		}

		if(isUsernameModified())
			return this.username;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().username());

		return _value;
	}


	public void setUsername(String username)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getUsername();

		/* set new value */
		this.username = username;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(username_pa, oldValue, username, this.username_m));

		/* set indicator */
		this.username_m = true;

		this.fireObjectChangedEvent(UserDtoPA.INSTANCE.username(), oldValue);
	}


	public boolean isUsernameModified()  {
		return username_m;
	}


	public static PropertyAccessor<UserDto, String> getUsernamePropertyAccessor()  {
		return username_pa;
	}


	public Boolean isHasPassword()  {
		if(! isDtoProxy()){
			return this.hasPassword;
		}

		if(isHasPasswordModified())
			return this.hasPassword;

		if(! GWT.isClient())
			return null;

		Boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().hasPassword());

		return _value;
	}


	public void setHasPassword(Boolean hasPassword)  {
		/* old value */
		Boolean oldValue = null;
		if(GWT.isClient())
			oldValue = isHasPassword();

		/* set new value */
		this.hasPassword = hasPassword;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(hasPassword_pa, oldValue, hasPassword, this.hasPassword_m));

		/* set indicator */
		this.hasPassword_m = true;

		this.fireObjectChangedEvent(UserDtoPA.INSTANCE.hasPassword(), oldValue);
	}


	public boolean isHasPasswordModified()  {
		return hasPassword_m;
	}


	public static PropertyAccessor<UserDto, Boolean> getHasPasswordPropertyAccessor()  {
		return hasPassword_pa;
	}


	@Override
	public String toDisplayTitle()  {
		try{
			return getLastname() + ", " + getFirstname();
		} catch(NullPointerException e){
			return BaseMessages.INSTANCE.unnamed();
		}
	}

	@Override
	public String toTypeDescription()  {
		return UserManagerMessages.INSTANCE.user();
	}

	@Override
	public BaseIcon toIcon()  {
		return BaseIcon.from("user");
	}

	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof UserDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((UserDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new UserDto2PosoMap();
	}

	public UserDtoPA instantiatePropertyAccess()  {
		return GWT.create(UserDtoPA.class);
	}

	public void clearModified()  {
		this.active = false;
		this.active_m = false;
		this.email = null;
		this.email_m = false;
		this.firstname = null;
		this.firstname_m = false;
		this.groups = null;
		this.groups_m = false;
		this.lastname = null;
		this.lastname_m = false;
		this.password = null;
		this.password_m = false;
		this.properties = null;
		this.properties_m = false;
		this.sex = null;
		this.sex_m = false;
		this.superUser = null;
		this.superUser_m = false;
		this.title = null;
		this.title_m = false;
		this.username = null;
		this.username_m = false;
		this.hasPassword = null;
		this.hasPassword_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(active_m)
			return true;
		if(email_m)
			return true;
		if(firstname_m)
			return true;
		if(groups_m)
			return true;
		if(lastname_m)
			return true;
		if(password_m)
			return true;
		if(properties_m)
			return true;
		if(sex_m)
			return true;
		if(superUser_m)
			return true;
		if(title_m)
			return true;
		if(username_m)
			return true;
		if(hasPassword_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(active_pa);
		list.add(email_pa);
		list.add(firstname_pa);
		list.add(groups_pa);
		list.add(lastname_pa);
		list.add(password_pa);
		list.add(properties_pa);
		list.add(sex_pa);
		list.add(superUser_pa);
		list.add(title_pa);
		list.add(username_pa);
		list.add(hasPassword_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(active_m)
			list.add(active_pa);
		if(email_m)
			list.add(email_pa);
		if(firstname_m)
			list.add(firstname_pa);
		if(groups_m)
			list.add(groups_pa);
		if(lastname_m)
			list.add(lastname_pa);
		if(password_m)
			list.add(password_pa);
		if(properties_m)
			list.add(properties_pa);
		if(sex_m)
			list.add(sex_pa);
		if(superUser_m)
			list.add(superUser_pa);
		if(title_m)
			list.add(title_pa);
		if(username_m)
			list.add(username_pa);
		if(hasPassword_m)
			list.add(hasPassword_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(active_pa);
			list.add(firstname_pa);
			list.add(lastname_pa);
			list.add(sex_pa);
			list.add(hasPassword_pa);
		}
		if(view.compareTo(DtoView.LIST) >= 0){
			list.add(username_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(email_pa);
			list.add(groups_pa);
			list.add(password_pa);
			list.add(properties_pa);
			list.add(superUser_pa);
			list.add(title_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(groups_pa);
		list.add(properties_pa);
		return list;
	}



	net.datenwerke.security.client.usermanager.dto.UserPropertyDto wl_0;
	net.datenwerke.security.client.usermanager.dto.SexDto wl_1;
	net.datenwerke.security.client.usermanager.dto.GroupDto wl_2;

}
