package net.datenwerke.rs.emaildatasink.client.emaildatasink.dto;

import com.google.gwt.core.client.GWT;
import java.lang.Boolean;
import java.lang.NullPointerException;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.dtomanager.PropertyAccessor;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.ChangeTracker;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.dto.pa.EmailDatasinkDtoPA;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.dto.posomap.EmailDatasinkDto2PosoMap;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions.EmailDatasink;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * Dto for {@link EmailDatasink}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class EmailDatasinkDto extends DatasinkDefinitionDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String encryptionPolicy;
	private  boolean encryptionPolicy_m;
	public static final String PROPERTY_ENCRYPTION_POLICY = "dpi-emaildatasink-encryptionpolicy";

	private transient static PropertyAccessor<EmailDatasinkDto, String> encryptionPolicy_pa = new PropertyAccessor<EmailDatasinkDto, String>() {
		@Override
		public void setValue(EmailDatasinkDto container, String object) {
			container.setEncryptionPolicy(object);
		}

		@Override
		public String getValue(EmailDatasinkDto container) {
			return container.getEncryptionPolicy();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "encryptionPolicy";
		}

		@Override
		public void setModified(EmailDatasinkDto container, boolean modified) {
			container.encryptionPolicy_m = modified;
		}

		@Override
		public boolean isModified(EmailDatasinkDto container) {
			return container.isEncryptionPolicyModified();
		}
	};

	private boolean forceSender;
	private  boolean forceSender_m;
	public static final String PROPERTY_FORCE_SENDER = "dpi-emaildatasink-forcesender";

	private transient static PropertyAccessor<EmailDatasinkDto, Boolean> forceSender_pa = new PropertyAccessor<EmailDatasinkDto, Boolean>() {
		@Override
		public void setValue(EmailDatasinkDto container, Boolean object) {
			container.setForceSender(object);
		}

		@Override
		public Boolean getValue(EmailDatasinkDto container) {
			return container.isForceSender();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "forceSender";
		}

		@Override
		public void setModified(EmailDatasinkDto container, boolean modified) {
			container.forceSender_m = modified;
		}

		@Override
		public boolean isModified(EmailDatasinkDto container) {
			return container.isForceSenderModified();
		}
	};

	private String host;
	private  boolean host_m;
	public static final String PROPERTY_HOST = "dpi-emaildatasink-host";

	private transient static PropertyAccessor<EmailDatasinkDto, String> host_pa = new PropertyAccessor<EmailDatasinkDto, String>() {
		@Override
		public void setValue(EmailDatasinkDto container, String object) {
			container.setHost(object);
		}

		@Override
		public String getValue(EmailDatasinkDto container) {
			return container.getHost();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "host";
		}

		@Override
		public void setModified(EmailDatasinkDto container, boolean modified) {
			container.host_m = modified;
		}

		@Override
		public boolean isModified(EmailDatasinkDto container) {
			return container.isHostModified();
		}
	};

	private String password;
	private  boolean password_m;
	public static final String PROPERTY_PASSWORD = "dpi-emaildatasink-password";

	private transient static PropertyAccessor<EmailDatasinkDto, String> password_pa = new PropertyAccessor<EmailDatasinkDto, String>() {
		@Override
		public void setValue(EmailDatasinkDto container, String object) {
			container.setPassword(object);
		}

		@Override
		public String getValue(EmailDatasinkDto container) {
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
		public void setModified(EmailDatasinkDto container, boolean modified) {
			container.password_m = modified;
		}

		@Override
		public boolean isModified(EmailDatasinkDto container) {
			return container.isPasswordModified();
		}
	};

	private int port;
	private  boolean port_m;
	public static final String PROPERTY_PORT = "dpi-emaildatasink-port";

	private transient static PropertyAccessor<EmailDatasinkDto, Integer> port_pa = new PropertyAccessor<EmailDatasinkDto, Integer>() {
		@Override
		public void setValue(EmailDatasinkDto container, Integer object) {
			container.setPort(object);
		}

		@Override
		public Integer getValue(EmailDatasinkDto container) {
			return container.getPort();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "port";
		}

		@Override
		public void setModified(EmailDatasinkDto container, boolean modified) {
			container.port_m = modified;
		}

		@Override
		public boolean isModified(EmailDatasinkDto container) {
			return container.isPortModified();
		}
	};

	private String sender;
	private  boolean sender_m;
	public static final String PROPERTY_SENDER = "dpi-emaildatasink-sender";

	private transient static PropertyAccessor<EmailDatasinkDto, String> sender_pa = new PropertyAccessor<EmailDatasinkDto, String>() {
		@Override
		public void setValue(EmailDatasinkDto container, String object) {
			container.setSender(object);
		}

		@Override
		public String getValue(EmailDatasinkDto container) {
			return container.getSender();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "sender";
		}

		@Override
		public void setModified(EmailDatasinkDto container, boolean modified) {
			container.sender_m = modified;
		}

		@Override
		public boolean isModified(EmailDatasinkDto container) {
			return container.isSenderModified();
		}
	};

	private String senderName;
	private  boolean senderName_m;
	public static final String PROPERTY_SENDER_NAME = "dpi-emaildatasink-sendername";

	private transient static PropertyAccessor<EmailDatasinkDto, String> senderName_pa = new PropertyAccessor<EmailDatasinkDto, String>() {
		@Override
		public void setValue(EmailDatasinkDto container, String object) {
			container.setSenderName(object);
		}

		@Override
		public String getValue(EmailDatasinkDto container) {
			return container.getSenderName();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "senderName";
		}

		@Override
		public void setModified(EmailDatasinkDto container, boolean modified) {
			container.senderName_m = modified;
		}

		@Override
		public boolean isModified(EmailDatasinkDto container) {
			return container.isSenderNameModified();
		}
	};

	private boolean sslEnable;
	private  boolean sslEnable_m;
	public static final String PROPERTY_SSL_ENABLE = "dpi-emaildatasink-sslenable";

	private transient static PropertyAccessor<EmailDatasinkDto, Boolean> sslEnable_pa = new PropertyAccessor<EmailDatasinkDto, Boolean>() {
		@Override
		public void setValue(EmailDatasinkDto container, Boolean object) {
			container.setSslEnable(object);
		}

		@Override
		public Boolean getValue(EmailDatasinkDto container) {
			return container.isSslEnable();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "sslEnable";
		}

		@Override
		public void setModified(EmailDatasinkDto container, boolean modified) {
			container.sslEnable_m = modified;
		}

		@Override
		public boolean isModified(EmailDatasinkDto container) {
			return container.isSslEnableModified();
		}
	};

	private boolean tlsEnable;
	private  boolean tlsEnable_m;
	public static final String PROPERTY_TLS_ENABLE = "dpi-emaildatasink-tlsenable";

	private transient static PropertyAccessor<EmailDatasinkDto, Boolean> tlsEnable_pa = new PropertyAccessor<EmailDatasinkDto, Boolean>() {
		@Override
		public void setValue(EmailDatasinkDto container, Boolean object) {
			container.setTlsEnable(object);
		}

		@Override
		public Boolean getValue(EmailDatasinkDto container) {
			return container.isTlsEnable();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "tlsEnable";
		}

		@Override
		public void setModified(EmailDatasinkDto container, boolean modified) {
			container.tlsEnable_m = modified;
		}

		@Override
		public boolean isModified(EmailDatasinkDto container) {
			return container.isTlsEnableModified();
		}
	};

	private boolean tlsRequire;
	private  boolean tlsRequire_m;
	public static final String PROPERTY_TLS_REQUIRE = "dpi-emaildatasink-tlsrequire";

	private transient static PropertyAccessor<EmailDatasinkDto, Boolean> tlsRequire_pa = new PropertyAccessor<EmailDatasinkDto, Boolean>() {
		@Override
		public void setValue(EmailDatasinkDto container, Boolean object) {
			container.setTlsRequire(object);
		}

		@Override
		public Boolean getValue(EmailDatasinkDto container) {
			return container.isTlsRequire();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "tlsRequire";
		}

		@Override
		public void setModified(EmailDatasinkDto container, boolean modified) {
			container.tlsRequire_m = modified;
		}

		@Override
		public boolean isModified(EmailDatasinkDto container) {
			return container.isTlsRequireModified();
		}
	};

	private String username;
	private  boolean username_m;
	public static final String PROPERTY_USERNAME = "dpi-emaildatasink-username";

	private transient static PropertyAccessor<EmailDatasinkDto, String> username_pa = new PropertyAccessor<EmailDatasinkDto, String>() {
		@Override
		public void setValue(EmailDatasinkDto container, String object) {
			container.setUsername(object);
		}

		@Override
		public String getValue(EmailDatasinkDto container) {
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
		public void setModified(EmailDatasinkDto container, boolean modified) {
			container.username_m = modified;
		}

		@Override
		public boolean isModified(EmailDatasinkDto container) {
			return container.isUsernameModified();
		}
	};

	private Boolean hasPassword;
	private  boolean hasPassword_m;
	public static final String PROPERTY_HAS_PASSWORD = "dpi-emaildatasink-haspassword";

	private transient static PropertyAccessor<EmailDatasinkDto, Boolean> hasPassword_pa = new PropertyAccessor<EmailDatasinkDto, Boolean>() {
		@Override
		public void setValue(EmailDatasinkDto container, Boolean object) {
			container.setHasPassword(object);
		}

		@Override
		public Boolean getValue(EmailDatasinkDto container) {
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
		public void setModified(EmailDatasinkDto container, boolean modified) {
			container.hasPassword_m = modified;
		}

		@Override
		public boolean isModified(EmailDatasinkDto container) {
			return container.isHasPasswordModified();
		}
	};


	public EmailDatasinkDto() {
		super();
	}

	public String getEncryptionPolicy()  {
		if(! isDtoProxy()){
			return this.encryptionPolicy;
		}

		if(isEncryptionPolicyModified())
			return this.encryptionPolicy;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().encryptionPolicy());

		return _value;
	}


	public void setEncryptionPolicy(String encryptionPolicy)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getEncryptionPolicy();

		/* set new value */
		this.encryptionPolicy = encryptionPolicy;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(encryptionPolicy_pa, oldValue, encryptionPolicy, this.encryptionPolicy_m));

		/* set indicator */
		this.encryptionPolicy_m = true;

		this.fireObjectChangedEvent(EmailDatasinkDtoPA.INSTANCE.encryptionPolicy(), oldValue);
	}


	public boolean isEncryptionPolicyModified()  {
		return encryptionPolicy_m;
	}


	public static PropertyAccessor<EmailDatasinkDto, String> getEncryptionPolicyPropertyAccessor()  {
		return encryptionPolicy_pa;
	}


	public boolean isForceSender()  {
		if(! isDtoProxy()){
			return this.forceSender;
		}

		if(isForceSenderModified())
			return this.forceSender;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().forceSender());

		return _value;
	}


	public void setForceSender(boolean forceSender)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isForceSender();

		/* set new value */
		this.forceSender = forceSender;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(forceSender_pa, oldValue, forceSender, this.forceSender_m));

		/* set indicator */
		this.forceSender_m = true;

		this.fireObjectChangedEvent(EmailDatasinkDtoPA.INSTANCE.forceSender(), oldValue);
	}


	public boolean isForceSenderModified()  {
		return forceSender_m;
	}


	public static PropertyAccessor<EmailDatasinkDto, Boolean> getForceSenderPropertyAccessor()  {
		return forceSender_pa;
	}


	public String getHost()  {
		if(! isDtoProxy()){
			return this.host;
		}

		if(isHostModified())
			return this.host;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().host());

		return _value;
	}


	public void setHost(String host)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getHost();

		/* set new value */
		this.host = host;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(host_pa, oldValue, host, this.host_m));

		/* set indicator */
		this.host_m = true;

		this.fireObjectChangedEvent(EmailDatasinkDtoPA.INSTANCE.host(), oldValue);
	}


	public boolean isHostModified()  {
		return host_m;
	}


	public static PropertyAccessor<EmailDatasinkDto, String> getHostPropertyAccessor()  {
		return host_pa;
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

		this.fireObjectChangedEvent(EmailDatasinkDtoPA.INSTANCE.password(), oldValue);
	}


	public boolean isPasswordModified()  {
		return password_m;
	}


	public static PropertyAccessor<EmailDatasinkDto, String> getPasswordPropertyAccessor()  {
		return password_pa;
	}


	public int getPort()  {
		if(! isDtoProxy()){
			return this.port;
		}

		if(isPortModified())
			return this.port;

		if(! GWT.isClient())
			return 0;

		int _value = dtoManager.getProperty(this, instantiatePropertyAccess().port());

		return _value;
	}


	public void setPort(int port)  {
		/* old value */
		int oldValue = 0;
		if(GWT.isClient())
			oldValue = getPort();

		/* set new value */
		this.port = port;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(port_pa, oldValue, port, this.port_m));

		/* set indicator */
		this.port_m = true;

		this.fireObjectChangedEvent(EmailDatasinkDtoPA.INSTANCE.port(), oldValue);
	}


	public boolean isPortModified()  {
		return port_m;
	}


	public static PropertyAccessor<EmailDatasinkDto, Integer> getPortPropertyAccessor()  {
		return port_pa;
	}


	public String getSender()  {
		if(! isDtoProxy()){
			return this.sender;
		}

		if(isSenderModified())
			return this.sender;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().sender());

		return _value;
	}


	public void setSender(String sender)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getSender();

		/* set new value */
		this.sender = sender;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(sender_pa, oldValue, sender, this.sender_m));

		/* set indicator */
		this.sender_m = true;

		this.fireObjectChangedEvent(EmailDatasinkDtoPA.INSTANCE.sender(), oldValue);
	}


	public boolean isSenderModified()  {
		return sender_m;
	}


	public static PropertyAccessor<EmailDatasinkDto, String> getSenderPropertyAccessor()  {
		return sender_pa;
	}


	public String getSenderName()  {
		if(! isDtoProxy()){
			return this.senderName;
		}

		if(isSenderNameModified())
			return this.senderName;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().senderName());

		return _value;
	}


	public void setSenderName(String senderName)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getSenderName();

		/* set new value */
		this.senderName = senderName;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(senderName_pa, oldValue, senderName, this.senderName_m));

		/* set indicator */
		this.senderName_m = true;

		this.fireObjectChangedEvent(EmailDatasinkDtoPA.INSTANCE.senderName(), oldValue);
	}


	public boolean isSenderNameModified()  {
		return senderName_m;
	}


	public static PropertyAccessor<EmailDatasinkDto, String> getSenderNamePropertyAccessor()  {
		return senderName_pa;
	}


	public boolean isSslEnable()  {
		if(! isDtoProxy()){
			return this.sslEnable;
		}

		if(isSslEnableModified())
			return this.sslEnable;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().sslEnable());

		return _value;
	}


	public void setSslEnable(boolean sslEnable)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isSslEnable();

		/* set new value */
		this.sslEnable = sslEnable;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(sslEnable_pa, oldValue, sslEnable, this.sslEnable_m));

		/* set indicator */
		this.sslEnable_m = true;

		this.fireObjectChangedEvent(EmailDatasinkDtoPA.INSTANCE.sslEnable(), oldValue);
	}


	public boolean isSslEnableModified()  {
		return sslEnable_m;
	}


	public static PropertyAccessor<EmailDatasinkDto, Boolean> getSslEnablePropertyAccessor()  {
		return sslEnable_pa;
	}


	public boolean isTlsEnable()  {
		if(! isDtoProxy()){
			return this.tlsEnable;
		}

		if(isTlsEnableModified())
			return this.tlsEnable;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().tlsEnable());

		return _value;
	}


	public void setTlsEnable(boolean tlsEnable)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isTlsEnable();

		/* set new value */
		this.tlsEnable = tlsEnable;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(tlsEnable_pa, oldValue, tlsEnable, this.tlsEnable_m));

		/* set indicator */
		this.tlsEnable_m = true;

		this.fireObjectChangedEvent(EmailDatasinkDtoPA.INSTANCE.tlsEnable(), oldValue);
	}


	public boolean isTlsEnableModified()  {
		return tlsEnable_m;
	}


	public static PropertyAccessor<EmailDatasinkDto, Boolean> getTlsEnablePropertyAccessor()  {
		return tlsEnable_pa;
	}


	public boolean isTlsRequire()  {
		if(! isDtoProxy()){
			return this.tlsRequire;
		}

		if(isTlsRequireModified())
			return this.tlsRequire;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().tlsRequire());

		return _value;
	}


	public void setTlsRequire(boolean tlsRequire)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isTlsRequire();

		/* set new value */
		this.tlsRequire = tlsRequire;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(tlsRequire_pa, oldValue, tlsRequire, this.tlsRequire_m));

		/* set indicator */
		this.tlsRequire_m = true;

		this.fireObjectChangedEvent(EmailDatasinkDtoPA.INSTANCE.tlsRequire(), oldValue);
	}


	public boolean isTlsRequireModified()  {
		return tlsRequire_m;
	}


	public static PropertyAccessor<EmailDatasinkDto, Boolean> getTlsRequirePropertyAccessor()  {
		return tlsRequire_pa;
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

		this.fireObjectChangedEvent(EmailDatasinkDtoPA.INSTANCE.username(), oldValue);
	}


	public boolean isUsernameModified()  {
		return username_m;
	}


	public static PropertyAccessor<EmailDatasinkDto, String> getUsernamePropertyAccessor()  {
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

		this.fireObjectChangedEvent(EmailDatasinkDtoPA.INSTANCE.hasPassword(), oldValue);
	}


	public boolean isHasPasswordModified()  {
		return hasPassword_m;
	}


	public static PropertyAccessor<EmailDatasinkDto, Boolean> getHasPasswordPropertyAccessor()  {
		return hasPassword_pa;
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
	public BaseIcon toIcon()  {
		return BaseIcon.from("send");
	}

	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof EmailDatasinkDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((EmailDatasinkDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new EmailDatasinkDto2PosoMap();
	}

	public EmailDatasinkDtoPA instantiatePropertyAccess()  {
		return GWT.create(EmailDatasinkDtoPA.class);
	}

	public void clearModified()  {
		this.encryptionPolicy = null;
		this.encryptionPolicy_m = false;
		this.forceSender = false;
		this.forceSender_m = false;
		this.host = null;
		this.host_m = false;
		this.password = null;
		this.password_m = false;
		this.port = 0;
		this.port_m = false;
		this.sender = null;
		this.sender_m = false;
		this.senderName = null;
		this.senderName_m = false;
		this.sslEnable = false;
		this.sslEnable_m = false;
		this.tlsEnable = false;
		this.tlsEnable_m = false;
		this.tlsRequire = false;
		this.tlsRequire_m = false;
		this.username = null;
		this.username_m = false;
		this.hasPassword = null;
		this.hasPassword_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(encryptionPolicy_m)
			return true;
		if(forceSender_m)
			return true;
		if(host_m)
			return true;
		if(password_m)
			return true;
		if(port_m)
			return true;
		if(sender_m)
			return true;
		if(senderName_m)
			return true;
		if(sslEnable_m)
			return true;
		if(tlsEnable_m)
			return true;
		if(tlsRequire_m)
			return true;
		if(username_m)
			return true;
		if(hasPassword_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(encryptionPolicy_pa);
		list.add(forceSender_pa);
		list.add(host_pa);
		list.add(password_pa);
		list.add(port_pa);
		list.add(sender_pa);
		list.add(senderName_pa);
		list.add(sslEnable_pa);
		list.add(tlsEnable_pa);
		list.add(tlsRequire_pa);
		list.add(username_pa);
		list.add(hasPassword_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(encryptionPolicy_m)
			list.add(encryptionPolicy_pa);
		if(forceSender_m)
			list.add(forceSender_pa);
		if(host_m)
			list.add(host_pa);
		if(password_m)
			list.add(password_pa);
		if(port_m)
			list.add(port_pa);
		if(sender_m)
			list.add(sender_pa);
		if(senderName_m)
			list.add(senderName_pa);
		if(sslEnable_m)
			list.add(sslEnable_pa);
		if(tlsEnable_m)
			list.add(tlsEnable_pa);
		if(tlsRequire_m)
			list.add(tlsRequire_pa);
		if(username_m)
			list.add(username_pa);
		if(hasPassword_m)
			list.add(hasPassword_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(hasPassword_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(encryptionPolicy_pa);
			list.add(forceSender_pa);
			list.add(host_pa);
			list.add(password_pa);
			list.add(port_pa);
			list.add(sender_pa);
			list.add(senderName_pa);
			list.add(sslEnable_pa);
			list.add(tlsEnable_pa);
			list.add(tlsRequire_pa);
			list.add(username_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		return list;
	}




}
