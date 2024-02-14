package net.datenwerke.rs.transport.client.transport.dto;

import com.google.gwt.core.client.GWT;
import java.lang.NullPointerException;
import java.lang.String;
import java.util.ArrayList;
import java.util.Date;
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
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.transport.client.transport.dto.AbstractTransportManagerNodeDto;
import net.datenwerke.rs.transport.client.transport.dto.pa.TransportDtoPA;
import net.datenwerke.rs.transport.client.transport.dto.posomap.TransportDto2PosoMap;
import net.datenwerke.rs.transport.client.transport.locale.TransportMessages;
import net.datenwerke.rs.transport.service.transport.entities.Transport;
import net.datenwerke.security.client.usermanager.dto.UserDto;

/**
 * Dto for {@link Transport}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
abstract public class TransportDto extends AbstractTransportManagerNodeDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private UserDto appliedBy;
	private  boolean appliedBy_m;
	public static final String PROPERTY_APPLIED_BY = "dpi-transport-appliedby";

	private transient static PropertyAccessor<TransportDto, UserDto> appliedBy_pa = new PropertyAccessor<TransportDto, UserDto>() {
		@Override
		public void setValue(TransportDto container, UserDto object) {
			container.setAppliedBy(object);
		}

		@Override
		public UserDto getValue(TransportDto container) {
			return container.getAppliedBy();
		}

		@Override
		public Class<?> getType() {
			return UserDto.class;
		}

		@Override
		public String getPath() {
			return "appliedBy";
		}

		@Override
		public void setModified(TransportDto container, boolean modified) {
			container.appliedBy_m = modified;
		}

		@Override
		public boolean isModified(TransportDto container) {
			return container.isAppliedByModified();
		}
	};

	private Date appliedOn;
	private  boolean appliedOn_m;
	public static final String PROPERTY_APPLIED_ON = "dpi-transport-appliedon";

	private transient static PropertyAccessor<TransportDto, Date> appliedOn_pa = new PropertyAccessor<TransportDto, Date>() {
		@Override
		public void setValue(TransportDto container, Date object) {
			container.setAppliedOn(object);
		}

		@Override
		public Date getValue(TransportDto container) {
			return container.getAppliedOn();
		}

		@Override
		public Class<?> getType() {
			return Date.class;
		}

		@Override
		public String getPath() {
			return "appliedOn";
		}

		@Override
		public void setModified(TransportDto container, boolean modified) {
			container.appliedOn_m = modified;
		}

		@Override
		public boolean isModified(TransportDto container) {
			return container.isAppliedOnModified();
		}
	};

	private String appliedProtocol;
	private  boolean appliedProtocol_m;
	public static final String PROPERTY_APPLIED_PROTOCOL = "dpi-transport-appliedprotocol";

	private transient static PropertyAccessor<TransportDto, String> appliedProtocol_pa = new PropertyAccessor<TransportDto, String>() {
		@Override
		public void setValue(TransportDto container, String object) {
			container.setAppliedProtocol(object);
		}

		@Override
		public String getValue(TransportDto container) {
			return container.getAppliedProtocol();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "appliedProtocol";
		}

		@Override
		public void setModified(TransportDto container, boolean modified) {
			container.appliedProtocol_m = modified;
		}

		@Override
		public boolean isModified(TransportDto container) {
			return container.isAppliedProtocolModified();
		}
	};

	private boolean closed;
	private  boolean closed_m;
	public static final String PROPERTY_CLOSED = "dpi-transport-closed";

	private transient static PropertyAccessor<TransportDto, Boolean> closed_pa = new PropertyAccessor<TransportDto, Boolean>() {
		@Override
		public void setValue(TransportDto container, Boolean object) {
			container.setClosed(object);
		}

		@Override
		public Boolean getValue(TransportDto container) {
			return container.isClosed();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "closed";
		}

		@Override
		public void setModified(TransportDto container, boolean modified) {
			container.closed_m = modified;
		}

		@Override
		public boolean isModified(TransportDto container) {
			return container.isClosedModified();
		}
	};

	private String creatorEmail;
	private  boolean creatorEmail_m;
	public static final String PROPERTY_CREATOR_EMAIL = "dpi-transport-creatoremail";

	private transient static PropertyAccessor<TransportDto, String> creatorEmail_pa = new PropertyAccessor<TransportDto, String>() {
		@Override
		public void setValue(TransportDto container, String object) {
			container.setCreatorEmail(object);
		}

		@Override
		public String getValue(TransportDto container) {
			return container.getCreatorEmail();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "creatorEmail";
		}

		@Override
		public void setModified(TransportDto container, boolean modified) {
			container.creatorEmail_m = modified;
		}

		@Override
		public boolean isModified(TransportDto container) {
			return container.isCreatorEmailModified();
		}
	};

	private String creatorFirstname;
	private  boolean creatorFirstname_m;
	public static final String PROPERTY_CREATOR_FIRSTNAME = "dpi-transport-creatorfirstname";

	private transient static PropertyAccessor<TransportDto, String> creatorFirstname_pa = new PropertyAccessor<TransportDto, String>() {
		@Override
		public void setValue(TransportDto container, String object) {
			container.setCreatorFirstname(object);
		}

		@Override
		public String getValue(TransportDto container) {
			return container.getCreatorFirstname();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "creatorFirstname";
		}

		@Override
		public void setModified(TransportDto container, boolean modified) {
			container.creatorFirstname_m = modified;
		}

		@Override
		public boolean isModified(TransportDto container) {
			return container.isCreatorFirstnameModified();
		}
	};

	private String creatorLastname;
	private  boolean creatorLastname_m;
	public static final String PROPERTY_CREATOR_LASTNAME = "dpi-transport-creatorlastname";

	private transient static PropertyAccessor<TransportDto, String> creatorLastname_pa = new PropertyAccessor<TransportDto, String>() {
		@Override
		public void setValue(TransportDto container, String object) {
			container.setCreatorLastname(object);
		}

		@Override
		public String getValue(TransportDto container) {
			return container.getCreatorLastname();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "creatorLastname";
		}

		@Override
		public void setModified(TransportDto container, boolean modified) {
			container.creatorLastname_m = modified;
		}

		@Override
		public boolean isModified(TransportDto container) {
			return container.isCreatorLastnameModified();
		}
	};

	private String creatorUsername;
	private  boolean creatorUsername_m;
	public static final String PROPERTY_CREATOR_USERNAME = "dpi-transport-creatorusername";

	private transient static PropertyAccessor<TransportDto, String> creatorUsername_pa = new PropertyAccessor<TransportDto, String>() {
		@Override
		public void setValue(TransportDto container, String object) {
			container.setCreatorUsername(object);
		}

		@Override
		public String getValue(TransportDto container) {
			return container.getCreatorUsername();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "creatorUsername";
		}

		@Override
		public void setModified(TransportDto container, boolean modified) {
			container.creatorUsername_m = modified;
		}

		@Override
		public boolean isModified(TransportDto container) {
			return container.isCreatorUsernameModified();
		}
	};

	private String description;
	private  boolean description_m;
	public static final String PROPERTY_DESCRIPTION = "dpi-transport-description";

	private transient static PropertyAccessor<TransportDto, String> description_pa = new PropertyAccessor<TransportDto, String>() {
		@Override
		public void setValue(TransportDto container, String object) {
			container.setDescription(object);
		}

		@Override
		public String getValue(TransportDto container) {
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
		public void setModified(TransportDto container, boolean modified) {
			container.description_m = modified;
		}

		@Override
		public boolean isModified(TransportDto container) {
			return container.isDescriptionModified();
		}
	};

	private UserDto importedBy;
	private  boolean importedBy_m;
	public static final String PROPERTY_IMPORTED_BY = "dpi-transport-importedby";

	private transient static PropertyAccessor<TransportDto, UserDto> importedBy_pa = new PropertyAccessor<TransportDto, UserDto>() {
		@Override
		public void setValue(TransportDto container, UserDto object) {
			container.setImportedBy(object);
		}

		@Override
		public UserDto getValue(TransportDto container) {
			return container.getImportedBy();
		}

		@Override
		public Class<?> getType() {
			return UserDto.class;
		}

		@Override
		public String getPath() {
			return "importedBy";
		}

		@Override
		public void setModified(TransportDto container, boolean modified) {
			container.importedBy_m = modified;
		}

		@Override
		public boolean isModified(TransportDto container) {
			return container.isImportedByModified();
		}
	};

	private Date importedOn;
	private  boolean importedOn_m;
	public static final String PROPERTY_IMPORTED_ON = "dpi-transport-importedon";

	private transient static PropertyAccessor<TransportDto, Date> importedOn_pa = new PropertyAccessor<TransportDto, Date>() {
		@Override
		public void setValue(TransportDto container, Date object) {
			container.setImportedOn(object);
		}

		@Override
		public Date getValue(TransportDto container) {
			return container.getImportedOn();
		}

		@Override
		public Class<?> getType() {
			return Date.class;
		}

		@Override
		public String getPath() {
			return "importedOn";
		}

		@Override
		public void setModified(TransportDto container, boolean modified) {
			container.importedOn_m = modified;
		}

		@Override
		public boolean isModified(TransportDto container) {
			return container.isImportedOnModified();
		}
	};

	private String key;
	private  boolean key_m;
	public static final String PROPERTY_KEY = "dpi-transport-key";

	private transient static PropertyAccessor<TransportDto, String> key_pa = new PropertyAccessor<TransportDto, String>() {
		@Override
		public void setValue(TransportDto container, String object) {
			container.setKey(object);
		}

		@Override
		public String getValue(TransportDto container) {
			return container.getKey();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "key";
		}

		@Override
		public void setModified(TransportDto container, boolean modified) {
			container.key_m = modified;
		}

		@Override
		public boolean isModified(TransportDto container) {
			return container.isKeyModified();
		}
	};

	private String rsSchemaVersion;
	private  boolean rsSchemaVersion_m;
	public static final String PROPERTY_RS_SCHEMA_VERSION = "dpi-transport-rsschemaversion";

	private transient static PropertyAccessor<TransportDto, String> rsSchemaVersion_pa = new PropertyAccessor<TransportDto, String>() {
		@Override
		public void setValue(TransportDto container, String object) {
			container.setRsSchemaVersion(object);
		}

		@Override
		public String getValue(TransportDto container) {
			return container.getRsSchemaVersion();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "rsSchemaVersion";
		}

		@Override
		public void setModified(TransportDto container, boolean modified) {
			container.rsSchemaVersion_m = modified;
		}

		@Override
		public boolean isModified(TransportDto container) {
			return container.isRsSchemaVersionModified();
		}
	};

	private String rsVersion;
	private  boolean rsVersion_m;
	public static final String PROPERTY_RS_VERSION = "dpi-transport-rsversion";

	private transient static PropertyAccessor<TransportDto, String> rsVersion_pa = new PropertyAccessor<TransportDto, String>() {
		@Override
		public void setValue(TransportDto container, String object) {
			container.setRsVersion(object);
		}

		@Override
		public String getValue(TransportDto container) {
			return container.getRsVersion();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "rsVersion";
		}

		@Override
		public void setModified(TransportDto container, boolean modified) {
			container.rsVersion_m = modified;
		}

		@Override
		public boolean isModified(TransportDto container) {
			return container.isRsVersionModified();
		}
	};

	private String serverId;
	private  boolean serverId_m;
	public static final String PROPERTY_SERVER_ID = "dpi-transport-serverid";

	private transient static PropertyAccessor<TransportDto, String> serverId_pa = new PropertyAccessor<TransportDto, String>() {
		@Override
		public void setValue(TransportDto container, String object) {
			container.setServerId(object);
		}

		@Override
		public String getValue(TransportDto container) {
			return container.getServerId();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "serverId";
		}

		@Override
		public void setModified(TransportDto container, boolean modified) {
			container.serverId_m = modified;
		}

		@Override
		public boolean isModified(TransportDto container) {
			return container.isServerIdModified();
		}
	};

	private String status;
	private  boolean status_m;
	public static final String PROPERTY_STATUS = "dpi-transport-status";

	private transient static PropertyAccessor<TransportDto, String> status_pa = new PropertyAccessor<TransportDto, String>() {
		@Override
		public void setValue(TransportDto container, String object) {
			container.setStatus(object);
		}

		@Override
		public String getValue(TransportDto container) {
			return container.getStatus();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "status";
		}

		@Override
		public void setModified(TransportDto container, boolean modified) {
			container.status_m = modified;
		}

		@Override
		public boolean isModified(TransportDto container) {
			return container.isStatusModified();
		}
	};

	private String xml;
	private  boolean xml_m;
	public static final String PROPERTY_XML = "dpi-transport-xml";

	private transient static PropertyAccessor<TransportDto, String> xml_pa = new PropertyAccessor<TransportDto, String>() {
		@Override
		public void setValue(TransportDto container, String object) {
			container.setXml(object);
		}

		@Override
		public String getValue(TransportDto container) {
			return container.getXml();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "xml";
		}

		@Override
		public void setModified(TransportDto container, boolean modified) {
			container.xml_m = modified;
		}

		@Override
		public boolean isModified(TransportDto container) {
			return container.isXmlModified();
		}
	};

	private String shortKey;
	private  boolean shortKey_m;
	public static final String PROPERTY_SHORT_KEY = "dpi-transport-shortkey";

	private transient static PropertyAccessor<TransportDto, String> shortKey_pa = new PropertyAccessor<TransportDto, String>() {
		@Override
		public void setValue(TransportDto container, String object) {
			container.setShortKey(object);
		}

		@Override
		public String getValue(TransportDto container) {
			return container.getShortKey();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "shortKey";
		}

		@Override
		public void setModified(TransportDto container, boolean modified) {
			container.shortKey_m = modified;
		}

		@Override
		public boolean isModified(TransportDto container) {
			return container.isShortKeyModified();
		}
	};

	private String createdOnStr;
	private  boolean createdOnStr_m;
	public static final String PROPERTY_CREATED_ON_STR = "dpi-transport-createdonstr";

	private transient static PropertyAccessor<TransportDto, String> createdOnStr_pa = new PropertyAccessor<TransportDto, String>() {
		@Override
		public void setValue(TransportDto container, String object) {
			container.setCreatedOnStr(object);
		}

		@Override
		public String getValue(TransportDto container) {
			return container.getCreatedOnStr();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "createdOnStr";
		}

		@Override
		public void setModified(TransportDto container, boolean modified) {
			container.createdOnStr_m = modified;
		}

		@Override
		public boolean isModified(TransportDto container) {
			return container.isCreatedOnStrModified();
		}
	};

	private String importedOnStr;
	private  boolean importedOnStr_m;
	public static final String PROPERTY_IMPORTED_ON_STR = "dpi-transport-importedonstr";

	private transient static PropertyAccessor<TransportDto, String> importedOnStr_pa = new PropertyAccessor<TransportDto, String>() {
		@Override
		public void setValue(TransportDto container, String object) {
			container.setImportedOnStr(object);
		}

		@Override
		public String getValue(TransportDto container) {
			return container.getImportedOnStr();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "importedOnStr";
		}

		@Override
		public void setModified(TransportDto container, boolean modified) {
			container.importedOnStr_m = modified;
		}

		@Override
		public boolean isModified(TransportDto container) {
			return container.isImportedOnStrModified();
		}
	};

	private String appliedOnStr;
	private  boolean appliedOnStr_m;
	public static final String PROPERTY_APPLIED_ON_STR = "dpi-transport-appliedonstr";

	private transient static PropertyAccessor<TransportDto, String> appliedOnStr_pa = new PropertyAccessor<TransportDto, String>() {
		@Override
		public void setValue(TransportDto container, String object) {
			container.setAppliedOnStr(object);
		}

		@Override
		public String getValue(TransportDto container) {
			return container.getAppliedOnStr();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "appliedOnStr";
		}

		@Override
		public void setModified(TransportDto container, boolean modified) {
			container.appliedOnStr_m = modified;
		}

		@Override
		public boolean isModified(TransportDto container) {
			return container.isAppliedOnStrModified();
		}
	};

	private String importedByStr;
	private  boolean importedByStr_m;
	public static final String PROPERTY_IMPORTED_BY_STR = "dpi-transport-importedbystr";

	private transient static PropertyAccessor<TransportDto, String> importedByStr_pa = new PropertyAccessor<TransportDto, String>() {
		@Override
		public void setValue(TransportDto container, String object) {
			container.setImportedByStr(object);
		}

		@Override
		public String getValue(TransportDto container) {
			return container.getImportedByStr();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "importedByStr";
		}

		@Override
		public void setModified(TransportDto container, boolean modified) {
			container.importedByStr_m = modified;
		}

		@Override
		public boolean isModified(TransportDto container) {
			return container.isImportedByStrModified();
		}
	};

	private String appliedByStr;
	private  boolean appliedByStr_m;
	public static final String PROPERTY_APPLIED_BY_STR = "dpi-transport-appliedbystr";

	private transient static PropertyAccessor<TransportDto, String> appliedByStr_pa = new PropertyAccessor<TransportDto, String>() {
		@Override
		public void setValue(TransportDto container, String object) {
			container.setAppliedByStr(object);
		}

		@Override
		public String getValue(TransportDto container) {
			return container.getAppliedByStr();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "appliedByStr";
		}

		@Override
		public void setModified(TransportDto container, boolean modified) {
			container.appliedByStr_m = modified;
		}

		@Override
		public boolean isModified(TransportDto container) {
			return container.isAppliedByStrModified();
		}
	};


	public TransportDto() {
		super();
	}

	public UserDto getAppliedBy()  {
		if(! isDtoProxy()){
			return this.appliedBy;
		}

		if(isAppliedByModified())
			return this.appliedBy;

		if(! GWT.isClient())
			return null;

		UserDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().appliedBy());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isAppliedByModified())
						setAppliedBy((UserDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setAppliedBy(UserDto appliedBy)  {
		/* old value */
		UserDto oldValue = null;
		if(GWT.isClient())
			oldValue = getAppliedBy();

		/* set new value */
		this.appliedBy = appliedBy;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(appliedBy_pa, oldValue, appliedBy, this.appliedBy_m));

		/* set indicator */
		this.appliedBy_m = true;

		this.fireObjectChangedEvent(TransportDtoPA.INSTANCE.appliedBy(), oldValue);
	}


	public boolean isAppliedByModified()  {
		return appliedBy_m;
	}


	public static PropertyAccessor<TransportDto, UserDto> getAppliedByPropertyAccessor()  {
		return appliedBy_pa;
	}


	public Date getAppliedOn()  {
		if(! isDtoProxy()){
			return this.appliedOn;
		}

		if(isAppliedOnModified())
			return this.appliedOn;

		if(! GWT.isClient())
			return null;

		Date _value = dtoManager.getProperty(this, instantiatePropertyAccess().appliedOn());

		return _value;
	}


	public void setAppliedOn(Date appliedOn)  {
		/* old value */
		Date oldValue = null;
		if(GWT.isClient())
			oldValue = getAppliedOn();

		/* set new value */
		this.appliedOn = appliedOn;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(appliedOn_pa, oldValue, appliedOn, this.appliedOn_m));

		/* set indicator */
		this.appliedOn_m = true;

		this.fireObjectChangedEvent(TransportDtoPA.INSTANCE.appliedOn(), oldValue);
	}


	public boolean isAppliedOnModified()  {
		return appliedOn_m;
	}


	public static PropertyAccessor<TransportDto, Date> getAppliedOnPropertyAccessor()  {
		return appliedOn_pa;
	}


	public String getAppliedProtocol()  {
		if(! isDtoProxy()){
			return this.appliedProtocol;
		}

		if(isAppliedProtocolModified())
			return this.appliedProtocol;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().appliedProtocol());

		return _value;
	}


	public void setAppliedProtocol(String appliedProtocol)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getAppliedProtocol();

		/* set new value */
		this.appliedProtocol = appliedProtocol;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(appliedProtocol_pa, oldValue, appliedProtocol, this.appliedProtocol_m));

		/* set indicator */
		this.appliedProtocol_m = true;

		this.fireObjectChangedEvent(TransportDtoPA.INSTANCE.appliedProtocol(), oldValue);
	}


	public boolean isAppliedProtocolModified()  {
		return appliedProtocol_m;
	}


	public static PropertyAccessor<TransportDto, String> getAppliedProtocolPropertyAccessor()  {
		return appliedProtocol_pa;
	}


	public boolean isClosed()  {
		if(! isDtoProxy()){
			return this.closed;
		}

		if(isClosedModified())
			return this.closed;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().closed());

		return _value;
	}


	public void setClosed(boolean closed)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isClosed();

		/* set new value */
		this.closed = closed;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(closed_pa, oldValue, closed, this.closed_m));

		/* set indicator */
		this.closed_m = true;

		this.fireObjectChangedEvent(TransportDtoPA.INSTANCE.closed(), oldValue);
	}


	public boolean isClosedModified()  {
		return closed_m;
	}


	public static PropertyAccessor<TransportDto, Boolean> getClosedPropertyAccessor()  {
		return closed_pa;
	}


	public String getCreatorEmail()  {
		if(! isDtoProxy()){
			return this.creatorEmail;
		}

		if(isCreatorEmailModified())
			return this.creatorEmail;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().creatorEmail());

		return _value;
	}


	public void setCreatorEmail(String creatorEmail)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getCreatorEmail();

		/* set new value */
		this.creatorEmail = creatorEmail;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(creatorEmail_pa, oldValue, creatorEmail, this.creatorEmail_m));

		/* set indicator */
		this.creatorEmail_m = true;

		this.fireObjectChangedEvent(TransportDtoPA.INSTANCE.creatorEmail(), oldValue);
	}


	public boolean isCreatorEmailModified()  {
		return creatorEmail_m;
	}


	public static PropertyAccessor<TransportDto, String> getCreatorEmailPropertyAccessor()  {
		return creatorEmail_pa;
	}


	public String getCreatorFirstname()  {
		if(! isDtoProxy()){
			return this.creatorFirstname;
		}

		if(isCreatorFirstnameModified())
			return this.creatorFirstname;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().creatorFirstname());

		return _value;
	}


	public void setCreatorFirstname(String creatorFirstname)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getCreatorFirstname();

		/* set new value */
		this.creatorFirstname = creatorFirstname;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(creatorFirstname_pa, oldValue, creatorFirstname, this.creatorFirstname_m));

		/* set indicator */
		this.creatorFirstname_m = true;

		this.fireObjectChangedEvent(TransportDtoPA.INSTANCE.creatorFirstname(), oldValue);
	}


	public boolean isCreatorFirstnameModified()  {
		return creatorFirstname_m;
	}


	public static PropertyAccessor<TransportDto, String> getCreatorFirstnamePropertyAccessor()  {
		return creatorFirstname_pa;
	}


	public String getCreatorLastname()  {
		if(! isDtoProxy()){
			return this.creatorLastname;
		}

		if(isCreatorLastnameModified())
			return this.creatorLastname;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().creatorLastname());

		return _value;
	}


	public void setCreatorLastname(String creatorLastname)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getCreatorLastname();

		/* set new value */
		this.creatorLastname = creatorLastname;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(creatorLastname_pa, oldValue, creatorLastname, this.creatorLastname_m));

		/* set indicator */
		this.creatorLastname_m = true;

		this.fireObjectChangedEvent(TransportDtoPA.INSTANCE.creatorLastname(), oldValue);
	}


	public boolean isCreatorLastnameModified()  {
		return creatorLastname_m;
	}


	public static PropertyAccessor<TransportDto, String> getCreatorLastnamePropertyAccessor()  {
		return creatorLastname_pa;
	}


	public String getCreatorUsername()  {
		if(! isDtoProxy()){
			return this.creatorUsername;
		}

		if(isCreatorUsernameModified())
			return this.creatorUsername;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().creatorUsername());

		return _value;
	}


	public void setCreatorUsername(String creatorUsername)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getCreatorUsername();

		/* set new value */
		this.creatorUsername = creatorUsername;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(creatorUsername_pa, oldValue, creatorUsername, this.creatorUsername_m));

		/* set indicator */
		this.creatorUsername_m = true;

		this.fireObjectChangedEvent(TransportDtoPA.INSTANCE.creatorUsername(), oldValue);
	}


	public boolean isCreatorUsernameModified()  {
		return creatorUsername_m;
	}


	public static PropertyAccessor<TransportDto, String> getCreatorUsernamePropertyAccessor()  {
		return creatorUsername_pa;
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

		this.fireObjectChangedEvent(TransportDtoPA.INSTANCE.description(), oldValue);
	}


	public boolean isDescriptionModified()  {
		return description_m;
	}


	public static PropertyAccessor<TransportDto, String> getDescriptionPropertyAccessor()  {
		return description_pa;
	}


	public UserDto getImportedBy()  {
		if(! isDtoProxy()){
			return this.importedBy;
		}

		if(isImportedByModified())
			return this.importedBy;

		if(! GWT.isClient())
			return null;

		UserDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().importedBy());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isImportedByModified())
						setImportedBy((UserDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setImportedBy(UserDto importedBy)  {
		/* old value */
		UserDto oldValue = null;
		if(GWT.isClient())
			oldValue = getImportedBy();

		/* set new value */
		this.importedBy = importedBy;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(importedBy_pa, oldValue, importedBy, this.importedBy_m));

		/* set indicator */
		this.importedBy_m = true;

		this.fireObjectChangedEvent(TransportDtoPA.INSTANCE.importedBy(), oldValue);
	}


	public boolean isImportedByModified()  {
		return importedBy_m;
	}


	public static PropertyAccessor<TransportDto, UserDto> getImportedByPropertyAccessor()  {
		return importedBy_pa;
	}


	public Date getImportedOn()  {
		if(! isDtoProxy()){
			return this.importedOn;
		}

		if(isImportedOnModified())
			return this.importedOn;

		if(! GWT.isClient())
			return null;

		Date _value = dtoManager.getProperty(this, instantiatePropertyAccess().importedOn());

		return _value;
	}


	public void setImportedOn(Date importedOn)  {
		/* old value */
		Date oldValue = null;
		if(GWT.isClient())
			oldValue = getImportedOn();

		/* set new value */
		this.importedOn = importedOn;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(importedOn_pa, oldValue, importedOn, this.importedOn_m));

		/* set indicator */
		this.importedOn_m = true;

		this.fireObjectChangedEvent(TransportDtoPA.INSTANCE.importedOn(), oldValue);
	}


	public boolean isImportedOnModified()  {
		return importedOn_m;
	}


	public static PropertyAccessor<TransportDto, Date> getImportedOnPropertyAccessor()  {
		return importedOn_pa;
	}


	public String getKey()  {
		if(! isDtoProxy()){
			return this.key;
		}

		if(isKeyModified())
			return this.key;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().key());

		return _value;
	}


	public void setKey(String key)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getKey();

		/* set new value */
		this.key = key;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(key_pa, oldValue, key, this.key_m));

		/* set indicator */
		this.key_m = true;

		this.fireObjectChangedEvent(TransportDtoPA.INSTANCE.key(), oldValue);
	}


	public boolean isKeyModified()  {
		return key_m;
	}


	public static PropertyAccessor<TransportDto, String> getKeyPropertyAccessor()  {
		return key_pa;
	}


	public String getRsSchemaVersion()  {
		if(! isDtoProxy()){
			return this.rsSchemaVersion;
		}

		if(isRsSchemaVersionModified())
			return this.rsSchemaVersion;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().rsSchemaVersion());

		return _value;
	}


	public void setRsSchemaVersion(String rsSchemaVersion)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getRsSchemaVersion();

		/* set new value */
		this.rsSchemaVersion = rsSchemaVersion;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(rsSchemaVersion_pa, oldValue, rsSchemaVersion, this.rsSchemaVersion_m));

		/* set indicator */
		this.rsSchemaVersion_m = true;

		this.fireObjectChangedEvent(TransportDtoPA.INSTANCE.rsSchemaVersion(), oldValue);
	}


	public boolean isRsSchemaVersionModified()  {
		return rsSchemaVersion_m;
	}


	public static PropertyAccessor<TransportDto, String> getRsSchemaVersionPropertyAccessor()  {
		return rsSchemaVersion_pa;
	}


	public String getRsVersion()  {
		if(! isDtoProxy()){
			return this.rsVersion;
		}

		if(isRsVersionModified())
			return this.rsVersion;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().rsVersion());

		return _value;
	}


	public void setRsVersion(String rsVersion)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getRsVersion();

		/* set new value */
		this.rsVersion = rsVersion;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(rsVersion_pa, oldValue, rsVersion, this.rsVersion_m));

		/* set indicator */
		this.rsVersion_m = true;

		this.fireObjectChangedEvent(TransportDtoPA.INSTANCE.rsVersion(), oldValue);
	}


	public boolean isRsVersionModified()  {
		return rsVersion_m;
	}


	public static PropertyAccessor<TransportDto, String> getRsVersionPropertyAccessor()  {
		return rsVersion_pa;
	}


	public String getServerId()  {
		if(! isDtoProxy()){
			return this.serverId;
		}

		if(isServerIdModified())
			return this.serverId;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().serverId());

		return _value;
	}


	public void setServerId(String serverId)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getServerId();

		/* set new value */
		this.serverId = serverId;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(serverId_pa, oldValue, serverId, this.serverId_m));

		/* set indicator */
		this.serverId_m = true;

		this.fireObjectChangedEvent(TransportDtoPA.INSTANCE.serverId(), oldValue);
	}


	public boolean isServerIdModified()  {
		return serverId_m;
	}


	public static PropertyAccessor<TransportDto, String> getServerIdPropertyAccessor()  {
		return serverId_pa;
	}


	public String getStatus()  {
		if(! isDtoProxy()){
			return this.status;
		}

		if(isStatusModified())
			return this.status;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().status());

		return _value;
	}


	public void setStatus(String status)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getStatus();

		/* set new value */
		this.status = status;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(status_pa, oldValue, status, this.status_m));

		/* set indicator */
		this.status_m = true;

		this.fireObjectChangedEvent(TransportDtoPA.INSTANCE.status(), oldValue);
	}


	public boolean isStatusModified()  {
		return status_m;
	}


	public static PropertyAccessor<TransportDto, String> getStatusPropertyAccessor()  {
		return status_pa;
	}


	public String getXml()  {
		if(! isDtoProxy()){
			return this.xml;
		}

		if(isXmlModified())
			return this.xml;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().xml());

		return _value;
	}


	public void setXml(String xml)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getXml();

		/* set new value */
		this.xml = xml;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(xml_pa, oldValue, xml, this.xml_m));

		/* set indicator */
		this.xml_m = true;

		this.fireObjectChangedEvent(TransportDtoPA.INSTANCE.xml(), oldValue);
	}


	public boolean isXmlModified()  {
		return xml_m;
	}


	public static PropertyAccessor<TransportDto, String> getXmlPropertyAccessor()  {
		return xml_pa;
	}


	public String getShortKey()  {
		if(! isDtoProxy()){
			return this.shortKey;
		}

		if(isShortKeyModified())
			return this.shortKey;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().shortKey());

		return _value;
	}


	public void setShortKey(String shortKey)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getShortKey();

		/* set new value */
		this.shortKey = shortKey;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(shortKey_pa, oldValue, shortKey, this.shortKey_m));

		/* set indicator */
		this.shortKey_m = true;

		this.fireObjectChangedEvent(TransportDtoPA.INSTANCE.shortKey(), oldValue);
	}


	public boolean isShortKeyModified()  {
		return shortKey_m;
	}


	public static PropertyAccessor<TransportDto, String> getShortKeyPropertyAccessor()  {
		return shortKey_pa;
	}


	public String getCreatedOnStr()  {
		if(! isDtoProxy()){
			return this.createdOnStr;
		}

		if(isCreatedOnStrModified())
			return this.createdOnStr;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().createdOnStr());

		return _value;
	}


	public void setCreatedOnStr(String createdOnStr)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getCreatedOnStr();

		/* set new value */
		this.createdOnStr = createdOnStr;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(createdOnStr_pa, oldValue, createdOnStr, this.createdOnStr_m));

		/* set indicator */
		this.createdOnStr_m = true;

		this.fireObjectChangedEvent(TransportDtoPA.INSTANCE.createdOnStr(), oldValue);
	}


	public boolean isCreatedOnStrModified()  {
		return createdOnStr_m;
	}


	public static PropertyAccessor<TransportDto, String> getCreatedOnStrPropertyAccessor()  {
		return createdOnStr_pa;
	}


	public String getImportedOnStr()  {
		if(! isDtoProxy()){
			return this.importedOnStr;
		}

		if(isImportedOnStrModified())
			return this.importedOnStr;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().importedOnStr());

		return _value;
	}


	public void setImportedOnStr(String importedOnStr)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getImportedOnStr();

		/* set new value */
		this.importedOnStr = importedOnStr;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(importedOnStr_pa, oldValue, importedOnStr, this.importedOnStr_m));

		/* set indicator */
		this.importedOnStr_m = true;

		this.fireObjectChangedEvent(TransportDtoPA.INSTANCE.importedOnStr(), oldValue);
	}


	public boolean isImportedOnStrModified()  {
		return importedOnStr_m;
	}


	public static PropertyAccessor<TransportDto, String> getImportedOnStrPropertyAccessor()  {
		return importedOnStr_pa;
	}


	public String getAppliedOnStr()  {
		if(! isDtoProxy()){
			return this.appliedOnStr;
		}

		if(isAppliedOnStrModified())
			return this.appliedOnStr;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().appliedOnStr());

		return _value;
	}


	public void setAppliedOnStr(String appliedOnStr)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getAppliedOnStr();

		/* set new value */
		this.appliedOnStr = appliedOnStr;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(appliedOnStr_pa, oldValue, appliedOnStr, this.appliedOnStr_m));

		/* set indicator */
		this.appliedOnStr_m = true;

		this.fireObjectChangedEvent(TransportDtoPA.INSTANCE.appliedOnStr(), oldValue);
	}


	public boolean isAppliedOnStrModified()  {
		return appliedOnStr_m;
	}


	public static PropertyAccessor<TransportDto, String> getAppliedOnStrPropertyAccessor()  {
		return appliedOnStr_pa;
	}


	public String getImportedByStr()  {
		if(! isDtoProxy()){
			return this.importedByStr;
		}

		if(isImportedByStrModified())
			return this.importedByStr;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().importedByStr());

		return _value;
	}


	public void setImportedByStr(String importedByStr)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getImportedByStr();

		/* set new value */
		this.importedByStr = importedByStr;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(importedByStr_pa, oldValue, importedByStr, this.importedByStr_m));

		/* set indicator */
		this.importedByStr_m = true;

		this.fireObjectChangedEvent(TransportDtoPA.INSTANCE.importedByStr(), oldValue);
	}


	public boolean isImportedByStrModified()  {
		return importedByStr_m;
	}


	public static PropertyAccessor<TransportDto, String> getImportedByStrPropertyAccessor()  {
		return importedByStr_pa;
	}


	public String getAppliedByStr()  {
		if(! isDtoProxy()){
			return this.appliedByStr;
		}

		if(isAppliedByStrModified())
			return this.appliedByStr;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().appliedByStr());

		return _value;
	}


	public void setAppliedByStr(String appliedByStr)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getAppliedByStr();

		/* set new value */
		this.appliedByStr = appliedByStr;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(appliedByStr_pa, oldValue, appliedByStr, this.appliedByStr_m));

		/* set indicator */
		this.appliedByStr_m = true;

		this.fireObjectChangedEvent(TransportDtoPA.INSTANCE.appliedByStr(), oldValue);
	}


	public boolean isAppliedByStrModified()  {
		return appliedByStr_m;
	}


	public static PropertyAccessor<TransportDto, String> getAppliedByStrPropertyAccessor()  {
		return appliedByStr_pa;
	}


	@Override
	public String toDisplayTitle()  {
		try{
			return getCreatedOnStr() + "-" + getShortKey();
		} catch(NullPointerException e){
			return BaseMessages.INSTANCE.unnamed();
		}
	}

	@Override
	public String toTypeDescription()  {
		return TransportMessages.INSTANCE.transport();
	}

	@Override
	public BaseIcon toIcon()  {
		return BaseIcon.from("archive");
	}

	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof TransportDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((TransportDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new TransportDto2PosoMap();
	}

	public TransportDtoPA instantiatePropertyAccess()  {
		return GWT.create(TransportDtoPA.class);
	}

	public void clearModified()  {
		this.appliedBy = null;
		this.appliedBy_m = false;
		this.appliedOn = null;
		this.appliedOn_m = false;
		this.appliedProtocol = null;
		this.appliedProtocol_m = false;
		this.closed = false;
		this.closed_m = false;
		this.creatorEmail = null;
		this.creatorEmail_m = false;
		this.creatorFirstname = null;
		this.creatorFirstname_m = false;
		this.creatorLastname = null;
		this.creatorLastname_m = false;
		this.creatorUsername = null;
		this.creatorUsername_m = false;
		this.description = null;
		this.description_m = false;
		this.importedBy = null;
		this.importedBy_m = false;
		this.importedOn = null;
		this.importedOn_m = false;
		this.key = null;
		this.key_m = false;
		this.rsSchemaVersion = null;
		this.rsSchemaVersion_m = false;
		this.rsVersion = null;
		this.rsVersion_m = false;
		this.serverId = null;
		this.serverId_m = false;
		this.status = null;
		this.status_m = false;
		this.xml = null;
		this.xml_m = false;
		this.shortKey = null;
		this.shortKey_m = false;
		this.createdOnStr = null;
		this.createdOnStr_m = false;
		this.importedOnStr = null;
		this.importedOnStr_m = false;
		this.appliedOnStr = null;
		this.appliedOnStr_m = false;
		this.importedByStr = null;
		this.importedByStr_m = false;
		this.appliedByStr = null;
		this.appliedByStr_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(appliedBy_m)
			return true;
		if(appliedOn_m)
			return true;
		if(appliedProtocol_m)
			return true;
		if(closed_m)
			return true;
		if(creatorEmail_m)
			return true;
		if(creatorFirstname_m)
			return true;
		if(creatorLastname_m)
			return true;
		if(creatorUsername_m)
			return true;
		if(description_m)
			return true;
		if(importedBy_m)
			return true;
		if(importedOn_m)
			return true;
		if(key_m)
			return true;
		if(rsSchemaVersion_m)
			return true;
		if(rsVersion_m)
			return true;
		if(serverId_m)
			return true;
		if(status_m)
			return true;
		if(xml_m)
			return true;
		if(shortKey_m)
			return true;
		if(createdOnStr_m)
			return true;
		if(importedOnStr_m)
			return true;
		if(appliedOnStr_m)
			return true;
		if(importedByStr_m)
			return true;
		if(appliedByStr_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(appliedBy_pa);
		list.add(appliedOn_pa);
		list.add(appliedProtocol_pa);
		list.add(closed_pa);
		list.add(creatorEmail_pa);
		list.add(creatorFirstname_pa);
		list.add(creatorLastname_pa);
		list.add(creatorUsername_pa);
		list.add(description_pa);
		list.add(importedBy_pa);
		list.add(importedOn_pa);
		list.add(key_pa);
		list.add(rsSchemaVersion_pa);
		list.add(rsVersion_pa);
		list.add(serverId_pa);
		list.add(status_pa);
		list.add(xml_pa);
		list.add(shortKey_pa);
		list.add(createdOnStr_pa);
		list.add(importedOnStr_pa);
		list.add(appliedOnStr_pa);
		list.add(importedByStr_pa);
		list.add(appliedByStr_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(appliedBy_m)
			list.add(appliedBy_pa);
		if(appliedOn_m)
			list.add(appliedOn_pa);
		if(appliedProtocol_m)
			list.add(appliedProtocol_pa);
		if(closed_m)
			list.add(closed_pa);
		if(creatorEmail_m)
			list.add(creatorEmail_pa);
		if(creatorFirstname_m)
			list.add(creatorFirstname_pa);
		if(creatorLastname_m)
			list.add(creatorLastname_pa);
		if(creatorUsername_m)
			list.add(creatorUsername_pa);
		if(description_m)
			list.add(description_pa);
		if(importedBy_m)
			list.add(importedBy_pa);
		if(importedOn_m)
			list.add(importedOn_pa);
		if(key_m)
			list.add(key_pa);
		if(rsSchemaVersion_m)
			list.add(rsSchemaVersion_pa);
		if(rsVersion_m)
			list.add(rsVersion_pa);
		if(serverId_m)
			list.add(serverId_pa);
		if(status_m)
			list.add(status_pa);
		if(xml_m)
			list.add(xml_pa);
		if(shortKey_m)
			list.add(shortKey_pa);
		if(createdOnStr_m)
			list.add(createdOnStr_pa);
		if(importedOnStr_m)
			list.add(importedOnStr_pa);
		if(appliedOnStr_m)
			list.add(appliedOnStr_pa);
		if(importedByStr_m)
			list.add(importedByStr_pa);
		if(appliedByStr_m)
			list.add(appliedByStr_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(shortKey_pa);
			list.add(createdOnStr_pa);
			list.add(importedOnStr_pa);
			list.add(appliedOnStr_pa);
			list.add(importedByStr_pa);
			list.add(appliedByStr_pa);
		}
		if(view.compareTo(DtoView.LIST) >= 0){
			list.add(key_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(appliedBy_pa);
			list.add(appliedOn_pa);
			list.add(appliedProtocol_pa);
			list.add(closed_pa);
			list.add(creatorEmail_pa);
			list.add(creatorFirstname_pa);
			list.add(creatorLastname_pa);
			list.add(creatorUsername_pa);
			list.add(description_pa);
			list.add(importedBy_pa);
			list.add(importedOn_pa);
			list.add(rsSchemaVersion_pa);
			list.add(rsVersion_pa);
			list.add(serverId_pa);
			list.add(status_pa);
			list.add(xml_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(appliedBy_pa);
		list.add(importedBy_pa);
		return list;
	}



	net.datenwerke.security.client.usermanager.dto.UserDto wl_0;

}
