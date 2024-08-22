package net.datenwerke.rs.ldapserver.client.ldapserver.dto;

import com.google.gwt.core.client.GWT;
import java.lang.Boolean;
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
import net.datenwerke.rs.ldapserver.client.ldapserver.dto.pa.LdapServerDtoPA;
import net.datenwerke.rs.ldapserver.client.ldapserver.dto.posomap.LdapServerDto2PosoMap;
import net.datenwerke.rs.ldapserver.service.ldapserver.entities.LdapServer;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerDefinitionDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto;

/**
 * Dto for {@link LdapServer}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class LdapServerDto extends RemoteServerDefinitionDto {


	private static final long serialVersionUID = 1;


	/* Fields */
	private String attrAdd;
	private  boolean attrAdd_m;
	public static final String PROPERTY_ATTR_ADD = "dpi-ldapserver-attradd";

	private transient static PropertyAccessor<LdapServerDto, String> attrAdd_pa = new PropertyAccessor<LdapServerDto, String>() {
		@Override
		public void setValue(LdapServerDto container, String object) {
			container.setAttrAdd(object);
		}

		@Override
		public String getValue(LdapServerDto container) {
			return container.getAttrAdd();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "attrAdd";
		}

		@Override
		public void setModified(LdapServerDto container, boolean modified) {
			container.attrAdd_m = modified;
		}

		@Override
		public boolean isModified(LdapServerDto container) {
			return container.isAttrAddModified();
		}
	};

	private String attrGroupMember;
	private  boolean attrGroupMember_m;
	public static final String PROPERTY_ATTR_GROUP_MEMBER = "dpi-ldapserver-attrgroupmember";

	private transient static PropertyAccessor<LdapServerDto, String> attrGroupMember_pa = new PropertyAccessor<LdapServerDto, String>() {
		@Override
		public void setValue(LdapServerDto container, String object) {
			container.setAttrGroupMember(object);
		}

		@Override
		public String getValue(LdapServerDto container) {
			return container.getAttrGroupMember();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "attrGroupMember";
		}

		@Override
		public void setModified(LdapServerDto container, boolean modified) {
			container.attrGroupMember_m = modified;
		}

		@Override
		public boolean isModified(LdapServerDto container) {
			return container.isAttrGroupMemberModified();
		}
	};

	private String attrGroupName;
	private  boolean attrGroupName_m;
	public static final String PROPERTY_ATTR_GROUP_NAME = "dpi-ldapserver-attrgroupname";

	private transient static PropertyAccessor<LdapServerDto, String> attrGroupName_pa = new PropertyAccessor<LdapServerDto, String>() {
		@Override
		public void setValue(LdapServerDto container, String object) {
			container.setAttrGroupName(object);
		}

		@Override
		public String getValue(LdapServerDto container) {
			return container.getAttrGroupName();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "attrGroupName";
		}

		@Override
		public void setModified(LdapServerDto container, boolean modified) {
			container.attrGroupName_m = modified;
		}

		@Override
		public boolean isModified(LdapServerDto container) {
			return container.isAttrGroupNameModified();
		}
	};

	private String attrGroupObjClass;
	private  boolean attrGroupObjClass_m;
	public static final String PROPERTY_ATTR_GROUP_OBJ_CLASS = "dpi-ldapserver-attrgroupobjclass";

	private transient static PropertyAccessor<LdapServerDto, String> attrGroupObjClass_pa = new PropertyAccessor<LdapServerDto, String>() {
		@Override
		public void setValue(LdapServerDto container, String object) {
			container.setAttrGroupObjClass(object);
		}

		@Override
		public String getValue(LdapServerDto container) {
			return container.getAttrGroupObjClass();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "attrGroupObjClass";
		}

		@Override
		public void setModified(LdapServerDto container, boolean modified) {
			container.attrGroupObjClass_m = modified;
		}

		@Override
		public boolean isModified(LdapServerDto container) {
			return container.isAttrGroupObjClassModified();
		}
	};

	private String attrGuid;
	private  boolean attrGuid_m;
	public static final String PROPERTY_ATTR_GUID = "dpi-ldapserver-attrguid";

	private transient static PropertyAccessor<LdapServerDto, String> attrGuid_pa = new PropertyAccessor<LdapServerDto, String>() {
		@Override
		public void setValue(LdapServerDto container, String object) {
			container.setAttrGuid(object);
		}

		@Override
		public String getValue(LdapServerDto container) {
			return container.getAttrGuid();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "attrGuid";
		}

		@Override
		public void setModified(LdapServerDto container, boolean modified) {
			container.attrGuid_m = modified;
		}

		@Override
		public boolean isModified(LdapServerDto container) {
			return container.isAttrGuidModified();
		}
	};

	private String attrObjClass;
	private  boolean attrObjClass_m;
	public static final String PROPERTY_ATTR_OBJ_CLASS = "dpi-ldapserver-attrobjclass";

	private transient static PropertyAccessor<LdapServerDto, String> attrObjClass_pa = new PropertyAccessor<LdapServerDto, String>() {
		@Override
		public void setValue(LdapServerDto container, String object) {
			container.setAttrObjClass(object);
		}

		@Override
		public String getValue(LdapServerDto container) {
			return container.getAttrObjClass();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "attrObjClass";
		}

		@Override
		public void setModified(LdapServerDto container, boolean modified) {
			container.attrObjClass_m = modified;
		}

		@Override
		public boolean isModified(LdapServerDto container) {
			return container.isAttrObjClassModified();
		}
	};

	private String attrOrgUnitObjClass;
	private  boolean attrOrgUnitObjClass_m;
	public static final String PROPERTY_ATTR_ORG_UNIT_OBJ_CLASS = "dpi-ldapserver-attrorgunitobjclass";

	private transient static PropertyAccessor<LdapServerDto, String> attrOrgUnitObjClass_pa = new PropertyAccessor<LdapServerDto, String>() {
		@Override
		public void setValue(LdapServerDto container, String object) {
			container.setAttrOrgUnitObjClass(object);
		}

		@Override
		public String getValue(LdapServerDto container) {
			return container.getAttrOrgUnitObjClass();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "attrOrgUnitObjClass";
		}

		@Override
		public void setModified(LdapServerDto container, boolean modified) {
			container.attrOrgUnitObjClass_m = modified;
		}

		@Override
		public boolean isModified(LdapServerDto container) {
			return container.isAttrOrgUnitObjClassModified();
		}
	};

	private String attrOrgUnitObjName;
	private  boolean attrOrgUnitObjName_m;
	public static final String PROPERTY_ATTR_ORG_UNIT_OBJ_NAME = "dpi-ldapserver-attrorgunitobjname";

	private transient static PropertyAccessor<LdapServerDto, String> attrOrgUnitObjName_pa = new PropertyAccessor<LdapServerDto, String>() {
		@Override
		public void setValue(LdapServerDto container, String object) {
			container.setAttrOrgUnitObjName(object);
		}

		@Override
		public String getValue(LdapServerDto container) {
			return container.getAttrOrgUnitObjName();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "attrOrgUnitObjName";
		}

		@Override
		public void setModified(LdapServerDto container, boolean modified) {
			container.attrOrgUnitObjName_m = modified;
		}

		@Override
		public boolean isModified(LdapServerDto container) {
			return container.isAttrOrgUnitObjNameModified();
		}
	};

	private String attrUserFirstname;
	private  boolean attrUserFirstname_m;
	public static final String PROPERTY_ATTR_USER_FIRSTNAME = "dpi-ldapserver-attruserfirstname";

	private transient static PropertyAccessor<LdapServerDto, String> attrUserFirstname_pa = new PropertyAccessor<LdapServerDto, String>() {
		@Override
		public void setValue(LdapServerDto container, String object) {
			container.setAttrUserFirstname(object);
		}

		@Override
		public String getValue(LdapServerDto container) {
			return container.getAttrUserFirstname();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "attrUserFirstname";
		}

		@Override
		public void setModified(LdapServerDto container, boolean modified) {
			container.attrUserFirstname_m = modified;
		}

		@Override
		public boolean isModified(LdapServerDto container) {
			return container.isAttrUserFirstnameModified();
		}
	};

	private String attrUserLastname;
	private  boolean attrUserLastname_m;
	public static final String PROPERTY_ATTR_USER_LASTNAME = "dpi-ldapserver-attruserlastname";

	private transient static PropertyAccessor<LdapServerDto, String> attrUserLastname_pa = new PropertyAccessor<LdapServerDto, String>() {
		@Override
		public void setValue(LdapServerDto container, String object) {
			container.setAttrUserLastname(object);
		}

		@Override
		public String getValue(LdapServerDto container) {
			return container.getAttrUserLastname();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "attrUserLastname";
		}

		@Override
		public void setModified(LdapServerDto container, boolean modified) {
			container.attrUserLastname_m = modified;
		}

		@Override
		public boolean isModified(LdapServerDto container) {
			return container.isAttrUserLastnameModified();
		}
	};

	private String attrUserMail;
	private  boolean attrUserMail_m;
	public static final String PROPERTY_ATTR_USER_MAIL = "dpi-ldapserver-attrusermail";

	private transient static PropertyAccessor<LdapServerDto, String> attrUserMail_pa = new PropertyAccessor<LdapServerDto, String>() {
		@Override
		public void setValue(LdapServerDto container, String object) {
			container.setAttrUserMail(object);
		}

		@Override
		public String getValue(LdapServerDto container) {
			return container.getAttrUserMail();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "attrUserMail";
		}

		@Override
		public void setModified(LdapServerDto container, boolean modified) {
			container.attrUserMail_m = modified;
		}

		@Override
		public boolean isModified(LdapServerDto container) {
			return container.isAttrUserMailModified();
		}
	};

	private String attrUserObjClass;
	private  boolean attrUserObjClass_m;
	public static final String PROPERTY_ATTR_USER_OBJ_CLASS = "dpi-ldapserver-attruserobjclass";

	private transient static PropertyAccessor<LdapServerDto, String> attrUserObjClass_pa = new PropertyAccessor<LdapServerDto, String>() {
		@Override
		public void setValue(LdapServerDto container, String object) {
			container.setAttrUserObjClass(object);
		}

		@Override
		public String getValue(LdapServerDto container) {
			return container.getAttrUserObjClass();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "attrUserObjClass";
		}

		@Override
		public void setModified(LdapServerDto container, boolean modified) {
			container.attrUserObjClass_m = modified;
		}

		@Override
		public boolean isModified(LdapServerDto container) {
			return container.isAttrUserObjClassModified();
		}
	};

	private String attrUserUsername;
	private  boolean attrUserUsername_m;
	public static final String PROPERTY_ATTR_USER_USERNAME = "dpi-ldapserver-attruserusername";

	private transient static PropertyAccessor<LdapServerDto, String> attrUserUsername_pa = new PropertyAccessor<LdapServerDto, String>() {
		@Override
		public void setValue(LdapServerDto container, String object) {
			container.setAttrUserUsername(object);
		}

		@Override
		public String getValue(LdapServerDto container) {
			return container.getAttrUserUsername();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "attrUserUsername";
		}

		@Override
		public void setModified(LdapServerDto container, boolean modified) {
			container.attrUserUsername_m = modified;
		}

		@Override
		public boolean isModified(LdapServerDto container) {
			return container.isAttrUserUsernameModified();
		}
	};

	private OrganisationalUnitDto externalDir;
	private  boolean externalDir_m;
	public static final String PROPERTY_EXTERNAL_DIR = "dpi-ldapserver-externaldir";

	private transient static PropertyAccessor<LdapServerDto, OrganisationalUnitDto> externalDir_pa = new PropertyAccessor<LdapServerDto, OrganisationalUnitDto>() {
		@Override
		public void setValue(LdapServerDto container, OrganisationalUnitDto object) {
			container.setExternalDir(object);
		}

		@Override
		public OrganisationalUnitDto getValue(LdapServerDto container) {
			return container.getExternalDir();
		}

		@Override
		public Class<?> getType() {
			return OrganisationalUnitDto.class;
		}

		@Override
		public String getPath() {
			return "externalDir";
		}

		@Override
		public void setModified(LdapServerDto container, boolean modified) {
			container.externalDir_m = modified;
		}

		@Override
		public boolean isModified(LdapServerDto container) {
			return container.isExternalDirModified();
		}
	};

	private boolean flattenTree;
	private  boolean flattenTree_m;
	public static final String PROPERTY_FLATTEN_TREE = "dpi-ldapserver-flattentree";

	private transient static PropertyAccessor<LdapServerDto, Boolean> flattenTree_pa = new PropertyAccessor<LdapServerDto, Boolean>() {
		@Override
		public void setValue(LdapServerDto container, Boolean object) {
			container.setFlattenTree(object);
		}

		@Override
		public Boolean getValue(LdapServerDto container) {
			return container.isFlattenTree();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "flattenTree";
		}

		@Override
		public void setModified(LdapServerDto container, boolean modified) {
			container.flattenTree_m = modified;
		}

		@Override
		public boolean isModified(LdapServerDto container) {
			return container.isFlattenTreeModified();
		}
	};

	private String ldapBase;
	private  boolean ldapBase_m;
	public static final String PROPERTY_LDAP_BASE = "dpi-ldapserver-ldapbase";

	private transient static PropertyAccessor<LdapServerDto, String> ldapBase_pa = new PropertyAccessor<LdapServerDto, String>() {
		@Override
		public void setValue(LdapServerDto container, String object) {
			container.setLdapBase(object);
		}

		@Override
		public String getValue(LdapServerDto container) {
			return container.getLdapBase();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "ldapBase";
		}

		@Override
		public void setModified(LdapServerDto container, boolean modified) {
			container.ldapBase_m = modified;
		}

		@Override
		public boolean isModified(LdapServerDto container) {
			return container.isLdapBaseModified();
		}
	};

	private boolean ldapDisabled;
	private  boolean ldapDisabled_m;
	public static final String PROPERTY_LDAP_DISABLED = "dpi-ldapserver-ldapdisabled";

	private transient static PropertyAccessor<LdapServerDto, Boolean> ldapDisabled_pa = new PropertyAccessor<LdapServerDto, Boolean>() {
		@Override
		public void setValue(LdapServerDto container, Boolean object) {
			container.setLdapDisabled(object);
		}

		@Override
		public Boolean getValue(LdapServerDto container) {
			return container.isLdapDisabled();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "ldapDisabled";
		}

		@Override
		public void setModified(LdapServerDto container, boolean modified) {
			container.ldapDisabled_m = modified;
		}

		@Override
		public boolean isModified(LdapServerDto container) {
			return container.isLdapDisabledModified();
		}
	};

	private String ldapFilter;
	private  boolean ldapFilter_m;
	public static final String PROPERTY_LDAP_FILTER = "dpi-ldapserver-ldapfilter";

	private transient static PropertyAccessor<LdapServerDto, String> ldapFilter_pa = new PropertyAccessor<LdapServerDto, String>() {
		@Override
		public void setValue(LdapServerDto container, String object) {
			container.setLdapFilter(object);
		}

		@Override
		public String getValue(LdapServerDto container) {
			return container.getLdapFilter();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "ldapFilter";
		}

		@Override
		public void setModified(LdapServerDto container, boolean modified) {
			container.ldapFilter_m = modified;
		}

		@Override
		public boolean isModified(LdapServerDto container) {
			return container.isLdapFilterModified();
		}
	};

	private boolean logResultingTree;
	private  boolean logResultingTree_m;
	public static final String PROPERTY_LOG_RESULTING_TREE = "dpi-ldapserver-logresultingtree";

	private transient static PropertyAccessor<LdapServerDto, Boolean> logResultingTree_pa = new PropertyAccessor<LdapServerDto, Boolean>() {
		@Override
		public void setValue(LdapServerDto container, Boolean object) {
			container.setLogResultingTree(object);
		}

		@Override
		public Boolean getValue(LdapServerDto container) {
			return container.isLogResultingTree();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "logResultingTree";
		}

		@Override
		public void setModified(LdapServerDto container, boolean modified) {
			container.logResultingTree_m = modified;
		}

		@Override
		public boolean isModified(LdapServerDto container) {
			return container.isLogResultingTreeModified();
		}
	};

	private String providerHost;
	private  boolean providerHost_m;
	public static final String PROPERTY_PROVIDER_HOST = "dpi-ldapserver-providerhost";

	private transient static PropertyAccessor<LdapServerDto, String> providerHost_pa = new PropertyAccessor<LdapServerDto, String>() {
		@Override
		public void setValue(LdapServerDto container, String object) {
			container.setProviderHost(object);
		}

		@Override
		public String getValue(LdapServerDto container) {
			return container.getProviderHost();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "providerHost";
		}

		@Override
		public void setModified(LdapServerDto container, boolean modified) {
			container.providerHost_m = modified;
		}

		@Override
		public boolean isModified(LdapServerDto container) {
			return container.isProviderHostModified();
		}
	};

	private int providerPort;
	private  boolean providerPort_m;
	public static final String PROPERTY_PROVIDER_PORT = "dpi-ldapserver-providerport";

	private transient static PropertyAccessor<LdapServerDto, Integer> providerPort_pa = new PropertyAccessor<LdapServerDto, Integer>() {
		@Override
		public void setValue(LdapServerDto container, Integer object) {
			container.setProviderPort(object);
		}

		@Override
		public Integer getValue(LdapServerDto container) {
			return container.getProviderPort();
		}

		@Override
		public Class<?> getType() {
			return Integer.class;
		}

		@Override
		public String getPath() {
			return "providerPort";
		}

		@Override
		public void setModified(LdapServerDto container, boolean modified) {
			container.providerPort_m = modified;
		}

		@Override
		public boolean isModified(LdapServerDto container) {
			return container.isProviderPortModified();
		}
	};

	private String securityCredentials;
	private  boolean securityCredentials_m;
	public static final String PROPERTY_SECURITY_CREDENTIALS = "dpi-ldapserver-securitycredentials";

	private transient static PropertyAccessor<LdapServerDto, String> securityCredentials_pa = new PropertyAccessor<LdapServerDto, String>() {
		@Override
		public void setValue(LdapServerDto container, String object) {
			container.setSecurityCredentials(object);
		}

		@Override
		public String getValue(LdapServerDto container) {
			return container.getSecurityCredentials();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "securityCredentials";
		}

		@Override
		public void setModified(LdapServerDto container, boolean modified) {
			container.securityCredentials_m = modified;
		}

		@Override
		public boolean isModified(LdapServerDto container) {
			return container.isSecurityCredentialsModified();
		}
	};

	private String securityEncryption;
	private  boolean securityEncryption_m;
	public static final String PROPERTY_SECURITY_ENCRYPTION = "dpi-ldapserver-securityencryption";

	private transient static PropertyAccessor<LdapServerDto, String> securityEncryption_pa = new PropertyAccessor<LdapServerDto, String>() {
		@Override
		public void setValue(LdapServerDto container, String object) {
			container.setSecurityEncryption(object);
		}

		@Override
		public String getValue(LdapServerDto container) {
			return container.getSecurityEncryption();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "securityEncryption";
		}

		@Override
		public void setModified(LdapServerDto container, boolean modified) {
			container.securityEncryption_m = modified;
		}

		@Override
		public boolean isModified(LdapServerDto container) {
			return container.isSecurityEncryptionModified();
		}
	};

	private String securityPrincipal;
	private  boolean securityPrincipal_m;
	public static final String PROPERTY_SECURITY_PRINCIPAL = "dpi-ldapserver-securityprincipal";

	private transient static PropertyAccessor<LdapServerDto, String> securityPrincipal_pa = new PropertyAccessor<LdapServerDto, String>() {
		@Override
		public void setValue(LdapServerDto container, String object) {
			container.setSecurityPrincipal(object);
		}

		@Override
		public String getValue(LdapServerDto container) {
			return container.getSecurityPrincipal();
		}

		@Override
		public Class<?> getType() {
			return String.class;
		}

		@Override
		public String getPath() {
			return "securityPrincipal";
		}

		@Override
		public void setModified(LdapServerDto container, boolean modified) {
			container.securityPrincipal_m = modified;
		}

		@Override
		public boolean isModified(LdapServerDto container) {
			return container.isSecurityPrincipalModified();
		}
	};

	private boolean writeProtection;
	private  boolean writeProtection_m;
	public static final String PROPERTY_WRITE_PROTECTION = "dpi-ldapserver-writeprotection";

	private transient static PropertyAccessor<LdapServerDto, Boolean> writeProtection_pa = new PropertyAccessor<LdapServerDto, Boolean>() {
		@Override
		public void setValue(LdapServerDto container, Boolean object) {
			container.setWriteProtection(object);
		}

		@Override
		public Boolean getValue(LdapServerDto container) {
			return container.isWriteProtection();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "writeProtection";
		}

		@Override
		public void setModified(LdapServerDto container, boolean modified) {
			container.writeProtection_m = modified;
		}

		@Override
		public boolean isModified(LdapServerDto container) {
			return container.isWriteProtectionModified();
		}
	};

	private Boolean hasSecurityCredentials;
	private  boolean hasSecurityCredentials_m;
	public static final String PROPERTY_HAS_SECURITY_CREDENTIALS = "dpi-ldapserver-hassecuritycredentials";

	private transient static PropertyAccessor<LdapServerDto, Boolean> hasSecurityCredentials_pa = new PropertyAccessor<LdapServerDto, Boolean>() {
		@Override
		public void setValue(LdapServerDto container, Boolean object) {
			container.setHasSecurityCredentials(object);
		}

		@Override
		public Boolean getValue(LdapServerDto container) {
			return container.isHasSecurityCredentials();
		}

		@Override
		public Class<?> getType() {
			return Boolean.class;
		}

		@Override
		public String getPath() {
			return "hasSecurityCredentials";
		}

		@Override
		public void setModified(LdapServerDto container, boolean modified) {
			container.hasSecurityCredentials_m = modified;
		}

		@Override
		public boolean isModified(LdapServerDto container) {
			return container.isHasSecurityCredentialsModified();
		}
	};


	public LdapServerDto() {
		super();
	}

	public String getAttrAdd()  {
		if(! isDtoProxy()){
			return this.attrAdd;
		}

		if(isAttrAddModified())
			return this.attrAdd;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().attrAdd());

		return _value;
	}


	public void setAttrAdd(String attrAdd)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getAttrAdd();

		/* set new value */
		this.attrAdd = attrAdd;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(attrAdd_pa, oldValue, attrAdd, this.attrAdd_m));

		/* set indicator */
		this.attrAdd_m = true;

		this.fireObjectChangedEvent(LdapServerDtoPA.INSTANCE.attrAdd(), oldValue);
	}


	public boolean isAttrAddModified()  {
		return attrAdd_m;
	}


	public static PropertyAccessor<LdapServerDto, String> getAttrAddPropertyAccessor()  {
		return attrAdd_pa;
	}


	public String getAttrGroupMember()  {
		if(! isDtoProxy()){
			return this.attrGroupMember;
		}

		if(isAttrGroupMemberModified())
			return this.attrGroupMember;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().attrGroupMember());

		return _value;
	}


	public void setAttrGroupMember(String attrGroupMember)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getAttrGroupMember();

		/* set new value */
		this.attrGroupMember = attrGroupMember;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(attrGroupMember_pa, oldValue, attrGroupMember, this.attrGroupMember_m));

		/* set indicator */
		this.attrGroupMember_m = true;

		this.fireObjectChangedEvent(LdapServerDtoPA.INSTANCE.attrGroupMember(), oldValue);
	}


	public boolean isAttrGroupMemberModified()  {
		return attrGroupMember_m;
	}


	public static PropertyAccessor<LdapServerDto, String> getAttrGroupMemberPropertyAccessor()  {
		return attrGroupMember_pa;
	}


	public String getAttrGroupName()  {
		if(! isDtoProxy()){
			return this.attrGroupName;
		}

		if(isAttrGroupNameModified())
			return this.attrGroupName;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().attrGroupName());

		return _value;
	}


	public void setAttrGroupName(String attrGroupName)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getAttrGroupName();

		/* set new value */
		this.attrGroupName = attrGroupName;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(attrGroupName_pa, oldValue, attrGroupName, this.attrGroupName_m));

		/* set indicator */
		this.attrGroupName_m = true;

		this.fireObjectChangedEvent(LdapServerDtoPA.INSTANCE.attrGroupName(), oldValue);
	}


	public boolean isAttrGroupNameModified()  {
		return attrGroupName_m;
	}


	public static PropertyAccessor<LdapServerDto, String> getAttrGroupNamePropertyAccessor()  {
		return attrGroupName_pa;
	}


	public String getAttrGroupObjClass()  {
		if(! isDtoProxy()){
			return this.attrGroupObjClass;
		}

		if(isAttrGroupObjClassModified())
			return this.attrGroupObjClass;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().attrGroupObjClass());

		return _value;
	}


	public void setAttrGroupObjClass(String attrGroupObjClass)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getAttrGroupObjClass();

		/* set new value */
		this.attrGroupObjClass = attrGroupObjClass;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(attrGroupObjClass_pa, oldValue, attrGroupObjClass, this.attrGroupObjClass_m));

		/* set indicator */
		this.attrGroupObjClass_m = true;

		this.fireObjectChangedEvent(LdapServerDtoPA.INSTANCE.attrGroupObjClass(), oldValue);
	}


	public boolean isAttrGroupObjClassModified()  {
		return attrGroupObjClass_m;
	}


	public static PropertyAccessor<LdapServerDto, String> getAttrGroupObjClassPropertyAccessor()  {
		return attrGroupObjClass_pa;
	}


	public String getAttrGuid()  {
		if(! isDtoProxy()){
			return this.attrGuid;
		}

		if(isAttrGuidModified())
			return this.attrGuid;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().attrGuid());

		return _value;
	}


	public void setAttrGuid(String attrGuid)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getAttrGuid();

		/* set new value */
		this.attrGuid = attrGuid;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(attrGuid_pa, oldValue, attrGuid, this.attrGuid_m));

		/* set indicator */
		this.attrGuid_m = true;

		this.fireObjectChangedEvent(LdapServerDtoPA.INSTANCE.attrGuid(), oldValue);
	}


	public boolean isAttrGuidModified()  {
		return attrGuid_m;
	}


	public static PropertyAccessor<LdapServerDto, String> getAttrGuidPropertyAccessor()  {
		return attrGuid_pa;
	}


	public String getAttrObjClass()  {
		if(! isDtoProxy()){
			return this.attrObjClass;
		}

		if(isAttrObjClassModified())
			return this.attrObjClass;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().attrObjClass());

		return _value;
	}


	public void setAttrObjClass(String attrObjClass)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getAttrObjClass();

		/* set new value */
		this.attrObjClass = attrObjClass;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(attrObjClass_pa, oldValue, attrObjClass, this.attrObjClass_m));

		/* set indicator */
		this.attrObjClass_m = true;

		this.fireObjectChangedEvent(LdapServerDtoPA.INSTANCE.attrObjClass(), oldValue);
	}


	public boolean isAttrObjClassModified()  {
		return attrObjClass_m;
	}


	public static PropertyAccessor<LdapServerDto, String> getAttrObjClassPropertyAccessor()  {
		return attrObjClass_pa;
	}


	public String getAttrOrgUnitObjClass()  {
		if(! isDtoProxy()){
			return this.attrOrgUnitObjClass;
		}

		if(isAttrOrgUnitObjClassModified())
			return this.attrOrgUnitObjClass;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().attrOrgUnitObjClass());

		return _value;
	}


	public void setAttrOrgUnitObjClass(String attrOrgUnitObjClass)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getAttrOrgUnitObjClass();

		/* set new value */
		this.attrOrgUnitObjClass = attrOrgUnitObjClass;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(attrOrgUnitObjClass_pa, oldValue, attrOrgUnitObjClass, this.attrOrgUnitObjClass_m));

		/* set indicator */
		this.attrOrgUnitObjClass_m = true;

		this.fireObjectChangedEvent(LdapServerDtoPA.INSTANCE.attrOrgUnitObjClass(), oldValue);
	}


	public boolean isAttrOrgUnitObjClassModified()  {
		return attrOrgUnitObjClass_m;
	}


	public static PropertyAccessor<LdapServerDto, String> getAttrOrgUnitObjClassPropertyAccessor()  {
		return attrOrgUnitObjClass_pa;
	}


	public String getAttrOrgUnitObjName()  {
		if(! isDtoProxy()){
			return this.attrOrgUnitObjName;
		}

		if(isAttrOrgUnitObjNameModified())
			return this.attrOrgUnitObjName;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().attrOrgUnitObjName());

		return _value;
	}


	public void setAttrOrgUnitObjName(String attrOrgUnitObjName)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getAttrOrgUnitObjName();

		/* set new value */
		this.attrOrgUnitObjName = attrOrgUnitObjName;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(attrOrgUnitObjName_pa, oldValue, attrOrgUnitObjName, this.attrOrgUnitObjName_m));

		/* set indicator */
		this.attrOrgUnitObjName_m = true;

		this.fireObjectChangedEvent(LdapServerDtoPA.INSTANCE.attrOrgUnitObjName(), oldValue);
	}


	public boolean isAttrOrgUnitObjNameModified()  {
		return attrOrgUnitObjName_m;
	}


	public static PropertyAccessor<LdapServerDto, String> getAttrOrgUnitObjNamePropertyAccessor()  {
		return attrOrgUnitObjName_pa;
	}


	public String getAttrUserFirstname()  {
		if(! isDtoProxy()){
			return this.attrUserFirstname;
		}

		if(isAttrUserFirstnameModified())
			return this.attrUserFirstname;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().attrUserFirstname());

		return _value;
	}


	public void setAttrUserFirstname(String attrUserFirstname)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getAttrUserFirstname();

		/* set new value */
		this.attrUserFirstname = attrUserFirstname;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(attrUserFirstname_pa, oldValue, attrUserFirstname, this.attrUserFirstname_m));

		/* set indicator */
		this.attrUserFirstname_m = true;

		this.fireObjectChangedEvent(LdapServerDtoPA.INSTANCE.attrUserFirstname(), oldValue);
	}


	public boolean isAttrUserFirstnameModified()  {
		return attrUserFirstname_m;
	}


	public static PropertyAccessor<LdapServerDto, String> getAttrUserFirstnamePropertyAccessor()  {
		return attrUserFirstname_pa;
	}


	public String getAttrUserLastname()  {
		if(! isDtoProxy()){
			return this.attrUserLastname;
		}

		if(isAttrUserLastnameModified())
			return this.attrUserLastname;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().attrUserLastname());

		return _value;
	}


	public void setAttrUserLastname(String attrUserLastname)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getAttrUserLastname();

		/* set new value */
		this.attrUserLastname = attrUserLastname;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(attrUserLastname_pa, oldValue, attrUserLastname, this.attrUserLastname_m));

		/* set indicator */
		this.attrUserLastname_m = true;

		this.fireObjectChangedEvent(LdapServerDtoPA.INSTANCE.attrUserLastname(), oldValue);
	}


	public boolean isAttrUserLastnameModified()  {
		return attrUserLastname_m;
	}


	public static PropertyAccessor<LdapServerDto, String> getAttrUserLastnamePropertyAccessor()  {
		return attrUserLastname_pa;
	}


	public String getAttrUserMail()  {
		if(! isDtoProxy()){
			return this.attrUserMail;
		}

		if(isAttrUserMailModified())
			return this.attrUserMail;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().attrUserMail());

		return _value;
	}


	public void setAttrUserMail(String attrUserMail)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getAttrUserMail();

		/* set new value */
		this.attrUserMail = attrUserMail;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(attrUserMail_pa, oldValue, attrUserMail, this.attrUserMail_m));

		/* set indicator */
		this.attrUserMail_m = true;

		this.fireObjectChangedEvent(LdapServerDtoPA.INSTANCE.attrUserMail(), oldValue);
	}


	public boolean isAttrUserMailModified()  {
		return attrUserMail_m;
	}


	public static PropertyAccessor<LdapServerDto, String> getAttrUserMailPropertyAccessor()  {
		return attrUserMail_pa;
	}


	public String getAttrUserObjClass()  {
		if(! isDtoProxy()){
			return this.attrUserObjClass;
		}

		if(isAttrUserObjClassModified())
			return this.attrUserObjClass;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().attrUserObjClass());

		return _value;
	}


	public void setAttrUserObjClass(String attrUserObjClass)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getAttrUserObjClass();

		/* set new value */
		this.attrUserObjClass = attrUserObjClass;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(attrUserObjClass_pa, oldValue, attrUserObjClass, this.attrUserObjClass_m));

		/* set indicator */
		this.attrUserObjClass_m = true;

		this.fireObjectChangedEvent(LdapServerDtoPA.INSTANCE.attrUserObjClass(), oldValue);
	}


	public boolean isAttrUserObjClassModified()  {
		return attrUserObjClass_m;
	}


	public static PropertyAccessor<LdapServerDto, String> getAttrUserObjClassPropertyAccessor()  {
		return attrUserObjClass_pa;
	}


	public String getAttrUserUsername()  {
		if(! isDtoProxy()){
			return this.attrUserUsername;
		}

		if(isAttrUserUsernameModified())
			return this.attrUserUsername;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().attrUserUsername());

		return _value;
	}


	public void setAttrUserUsername(String attrUserUsername)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getAttrUserUsername();

		/* set new value */
		this.attrUserUsername = attrUserUsername;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(attrUserUsername_pa, oldValue, attrUserUsername, this.attrUserUsername_m));

		/* set indicator */
		this.attrUserUsername_m = true;

		this.fireObjectChangedEvent(LdapServerDtoPA.INSTANCE.attrUserUsername(), oldValue);
	}


	public boolean isAttrUserUsernameModified()  {
		return attrUserUsername_m;
	}


	public static PropertyAccessor<LdapServerDto, String> getAttrUserUsernamePropertyAccessor()  {
		return attrUserUsername_pa;
	}


	public OrganisationalUnitDto getExternalDir()  {
		if(! isDtoProxy()){
			return this.externalDir;
		}

		if(isExternalDirModified())
			return this.externalDir;

		if(! GWT.isClient())
			return null;

		OrganisationalUnitDto _value = dtoManager.getProperty(this, instantiatePropertyAccess().externalDir());

		if(_value instanceof HasObjectChangedEventHandler){
			((HasObjectChangedEventHandler)_value).addObjectChangedHandler(new net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler(){
				@Override
				public void onObjectChangedEvent(net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent event){
					if(! isExternalDirModified())
						setExternalDir((OrganisationalUnitDto) event.getObject());
				}
			}
			);
		}
		return _value;
	}


	public void setExternalDir(OrganisationalUnitDto externalDir)  {
		/* old value */
		OrganisationalUnitDto oldValue = null;
		if(GWT.isClient())
			oldValue = getExternalDir();

		/* set new value */
		this.externalDir = externalDir;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(externalDir_pa, oldValue, externalDir, this.externalDir_m));

		/* set indicator */
		this.externalDir_m = true;

		this.fireObjectChangedEvent(LdapServerDtoPA.INSTANCE.externalDir(), oldValue);
	}


	public boolean isExternalDirModified()  {
		return externalDir_m;
	}


	public static PropertyAccessor<LdapServerDto, OrganisationalUnitDto> getExternalDirPropertyAccessor()  {
		return externalDir_pa;
	}


	public boolean isFlattenTree()  {
		if(! isDtoProxy()){
			return this.flattenTree;
		}

		if(isFlattenTreeModified())
			return this.flattenTree;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().flattenTree());

		return _value;
	}


	public void setFlattenTree(boolean flattenTree)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isFlattenTree();

		/* set new value */
		this.flattenTree = flattenTree;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(flattenTree_pa, oldValue, flattenTree, this.flattenTree_m));

		/* set indicator */
		this.flattenTree_m = true;

		this.fireObjectChangedEvent(LdapServerDtoPA.INSTANCE.flattenTree(), oldValue);
	}


	public boolean isFlattenTreeModified()  {
		return flattenTree_m;
	}


	public static PropertyAccessor<LdapServerDto, Boolean> getFlattenTreePropertyAccessor()  {
		return flattenTree_pa;
	}


	public String getLdapBase()  {
		if(! isDtoProxy()){
			return this.ldapBase;
		}

		if(isLdapBaseModified())
			return this.ldapBase;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().ldapBase());

		return _value;
	}


	public void setLdapBase(String ldapBase)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getLdapBase();

		/* set new value */
		this.ldapBase = ldapBase;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(ldapBase_pa, oldValue, ldapBase, this.ldapBase_m));

		/* set indicator */
		this.ldapBase_m = true;

		this.fireObjectChangedEvent(LdapServerDtoPA.INSTANCE.ldapBase(), oldValue);
	}


	public boolean isLdapBaseModified()  {
		return ldapBase_m;
	}


	public static PropertyAccessor<LdapServerDto, String> getLdapBasePropertyAccessor()  {
		return ldapBase_pa;
	}


	public boolean isLdapDisabled()  {
		if(! isDtoProxy()){
			return this.ldapDisabled;
		}

		if(isLdapDisabledModified())
			return this.ldapDisabled;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().ldapDisabled());

		return _value;
	}


	public void setLdapDisabled(boolean ldapDisabled)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isLdapDisabled();

		/* set new value */
		this.ldapDisabled = ldapDisabled;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(ldapDisabled_pa, oldValue, ldapDisabled, this.ldapDisabled_m));

		/* set indicator */
		this.ldapDisabled_m = true;

		this.fireObjectChangedEvent(LdapServerDtoPA.INSTANCE.ldapDisabled(), oldValue);
	}


	public boolean isLdapDisabledModified()  {
		return ldapDisabled_m;
	}


	public static PropertyAccessor<LdapServerDto, Boolean> getLdapDisabledPropertyAccessor()  {
		return ldapDisabled_pa;
	}


	public String getLdapFilter()  {
		if(! isDtoProxy()){
			return this.ldapFilter;
		}

		if(isLdapFilterModified())
			return this.ldapFilter;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().ldapFilter());

		return _value;
	}


	public void setLdapFilter(String ldapFilter)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getLdapFilter();

		/* set new value */
		this.ldapFilter = ldapFilter;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(ldapFilter_pa, oldValue, ldapFilter, this.ldapFilter_m));

		/* set indicator */
		this.ldapFilter_m = true;

		this.fireObjectChangedEvent(LdapServerDtoPA.INSTANCE.ldapFilter(), oldValue);
	}


	public boolean isLdapFilterModified()  {
		return ldapFilter_m;
	}


	public static PropertyAccessor<LdapServerDto, String> getLdapFilterPropertyAccessor()  {
		return ldapFilter_pa;
	}


	public boolean isLogResultingTree()  {
		if(! isDtoProxy()){
			return this.logResultingTree;
		}

		if(isLogResultingTreeModified())
			return this.logResultingTree;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().logResultingTree());

		return _value;
	}


	public void setLogResultingTree(boolean logResultingTree)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isLogResultingTree();

		/* set new value */
		this.logResultingTree = logResultingTree;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(logResultingTree_pa, oldValue, logResultingTree, this.logResultingTree_m));

		/* set indicator */
		this.logResultingTree_m = true;

		this.fireObjectChangedEvent(LdapServerDtoPA.INSTANCE.logResultingTree(), oldValue);
	}


	public boolean isLogResultingTreeModified()  {
		return logResultingTree_m;
	}


	public static PropertyAccessor<LdapServerDto, Boolean> getLogResultingTreePropertyAccessor()  {
		return logResultingTree_pa;
	}


	public String getProviderHost()  {
		if(! isDtoProxy()){
			return this.providerHost;
		}

		if(isProviderHostModified())
			return this.providerHost;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().providerHost());

		return _value;
	}


	public void setProviderHost(String providerHost)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getProviderHost();

		/* set new value */
		this.providerHost = providerHost;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(providerHost_pa, oldValue, providerHost, this.providerHost_m));

		/* set indicator */
		this.providerHost_m = true;

		this.fireObjectChangedEvent(LdapServerDtoPA.INSTANCE.providerHost(), oldValue);
	}


	public boolean isProviderHostModified()  {
		return providerHost_m;
	}


	public static PropertyAccessor<LdapServerDto, String> getProviderHostPropertyAccessor()  {
		return providerHost_pa;
	}


	public int getProviderPort()  {
		if(! isDtoProxy()){
			return this.providerPort;
		}

		if(isProviderPortModified())
			return this.providerPort;

		if(! GWT.isClient())
			return 0;

		int _value = dtoManager.getProperty(this, instantiatePropertyAccess().providerPort());

		return _value;
	}


	public void setProviderPort(int providerPort)  {
		/* old value */
		int oldValue = 0;
		if(GWT.isClient())
			oldValue = getProviderPort();

		/* set new value */
		this.providerPort = providerPort;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(providerPort_pa, oldValue, providerPort, this.providerPort_m));

		/* set indicator */
		this.providerPort_m = true;

		this.fireObjectChangedEvent(LdapServerDtoPA.INSTANCE.providerPort(), oldValue);
	}


	public boolean isProviderPortModified()  {
		return providerPort_m;
	}


	public static PropertyAccessor<LdapServerDto, Integer> getProviderPortPropertyAccessor()  {
		return providerPort_pa;
	}


	public String getSecurityCredentials()  {
		if(! isDtoProxy()){
			return this.securityCredentials;
		}

		if(isSecurityCredentialsModified())
			return this.securityCredentials;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().securityCredentials());

		return _value;
	}


	public void setSecurityCredentials(String securityCredentials)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getSecurityCredentials();

		/* set new value */
		this.securityCredentials = securityCredentials;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(securityCredentials_pa, oldValue, securityCredentials, this.securityCredentials_m));

		/* set indicator */
		this.securityCredentials_m = true;

		this.fireObjectChangedEvent(LdapServerDtoPA.INSTANCE.securityCredentials(), oldValue);
	}


	public boolean isSecurityCredentialsModified()  {
		return securityCredentials_m;
	}


	public static PropertyAccessor<LdapServerDto, String> getSecurityCredentialsPropertyAccessor()  {
		return securityCredentials_pa;
	}


	public String getSecurityEncryption()  {
		if(! isDtoProxy()){
			return this.securityEncryption;
		}

		if(isSecurityEncryptionModified())
			return this.securityEncryption;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().securityEncryption());

		return _value;
	}


	public void setSecurityEncryption(String securityEncryption)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getSecurityEncryption();

		/* set new value */
		this.securityEncryption = securityEncryption;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(securityEncryption_pa, oldValue, securityEncryption, this.securityEncryption_m));

		/* set indicator */
		this.securityEncryption_m = true;

		this.fireObjectChangedEvent(LdapServerDtoPA.INSTANCE.securityEncryption(), oldValue);
	}


	public boolean isSecurityEncryptionModified()  {
		return securityEncryption_m;
	}


	public static PropertyAccessor<LdapServerDto, String> getSecurityEncryptionPropertyAccessor()  {
		return securityEncryption_pa;
	}


	public String getSecurityPrincipal()  {
		if(! isDtoProxy()){
			return this.securityPrincipal;
		}

		if(isSecurityPrincipalModified())
			return this.securityPrincipal;

		if(! GWT.isClient())
			return null;

		String _value = dtoManager.getProperty(this, instantiatePropertyAccess().securityPrincipal());

		return _value;
	}


	public void setSecurityPrincipal(String securityPrincipal)  {
		/* old value */
		String oldValue = null;
		if(GWT.isClient())
			oldValue = getSecurityPrincipal();

		/* set new value */
		this.securityPrincipal = securityPrincipal;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(securityPrincipal_pa, oldValue, securityPrincipal, this.securityPrincipal_m));

		/* set indicator */
		this.securityPrincipal_m = true;

		this.fireObjectChangedEvent(LdapServerDtoPA.INSTANCE.securityPrincipal(), oldValue);
	}


	public boolean isSecurityPrincipalModified()  {
		return securityPrincipal_m;
	}


	public static PropertyAccessor<LdapServerDto, String> getSecurityPrincipalPropertyAccessor()  {
		return securityPrincipal_pa;
	}


	public boolean isWriteProtection()  {
		if(! isDtoProxy()){
			return this.writeProtection;
		}

		if(isWriteProtectionModified())
			return this.writeProtection;

		if(! GWT.isClient())
			return false;

		boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().writeProtection());

		return _value;
	}


	public void setWriteProtection(boolean writeProtection)  {
		/* old value */
		boolean oldValue = false;
		if(GWT.isClient())
			oldValue = isWriteProtection();

		/* set new value */
		this.writeProtection = writeProtection;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(writeProtection_pa, oldValue, writeProtection, this.writeProtection_m));

		/* set indicator */
		this.writeProtection_m = true;

		this.fireObjectChangedEvent(LdapServerDtoPA.INSTANCE.writeProtection(), oldValue);
	}


	public boolean isWriteProtectionModified()  {
		return writeProtection_m;
	}


	public static PropertyAccessor<LdapServerDto, Boolean> getWriteProtectionPropertyAccessor()  {
		return writeProtection_pa;
	}


	public Boolean isHasSecurityCredentials()  {
		if(! isDtoProxy()){
			return this.hasSecurityCredentials;
		}

		if(isHasSecurityCredentialsModified())
			return this.hasSecurityCredentials;

		if(! GWT.isClient())
			return null;

		Boolean _value = dtoManager.getProperty(this, instantiatePropertyAccess().hasSecurityCredentials());

		return _value;
	}


	public void setHasSecurityCredentials(Boolean hasSecurityCredentials)  {
		/* old value */
		Boolean oldValue = null;
		if(GWT.isClient())
			oldValue = isHasSecurityCredentials();

		/* set new value */
		this.hasSecurityCredentials = hasSecurityCredentials;

		if(! GWT.isClient())
			return;

		if(isTrackChanges())
			addChange(new ChangeTracker(hasSecurityCredentials_pa, oldValue, hasSecurityCredentials, this.hasSecurityCredentials_m));

		/* set indicator */
		this.hasSecurityCredentials_m = true;

		this.fireObjectChangedEvent(LdapServerDtoPA.INSTANCE.hasSecurityCredentials(), oldValue);
	}


	public boolean isHasSecurityCredentialsModified()  {
		return hasSecurityCredentials_m;
	}


	public static PropertyAccessor<LdapServerDto, Boolean> getHasSecurityCredentialsPropertyAccessor()  {
		return hasSecurityCredentials_pa;
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
		return BaseIcon.from("upload");
	}

	@Override
	public int hashCode()  {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object obj)  {
		if(! (obj instanceof LdapServerDto))
			return false;

		if(null == getId())
			return super.equals(obj);
		return getId().equals(((LdapServerDto)obj).getId());
	}

	@Override
	public String toString()  {
		return getClass().getName() + ": " + getId();
	}

	public static Dto2PosoMapper newPosoMapper()  {
		return new LdapServerDto2PosoMap();
	}

	public LdapServerDtoPA instantiatePropertyAccess()  {
		return GWT.create(LdapServerDtoPA.class);
	}

	public void clearModified()  {
		this.attrAdd = null;
		this.attrAdd_m = false;
		this.attrGroupMember = null;
		this.attrGroupMember_m = false;
		this.attrGroupName = null;
		this.attrGroupName_m = false;
		this.attrGroupObjClass = null;
		this.attrGroupObjClass_m = false;
		this.attrGuid = null;
		this.attrGuid_m = false;
		this.attrObjClass = null;
		this.attrObjClass_m = false;
		this.attrOrgUnitObjClass = null;
		this.attrOrgUnitObjClass_m = false;
		this.attrOrgUnitObjName = null;
		this.attrOrgUnitObjName_m = false;
		this.attrUserFirstname = null;
		this.attrUserFirstname_m = false;
		this.attrUserLastname = null;
		this.attrUserLastname_m = false;
		this.attrUserMail = null;
		this.attrUserMail_m = false;
		this.attrUserObjClass = null;
		this.attrUserObjClass_m = false;
		this.attrUserUsername = null;
		this.attrUserUsername_m = false;
		this.externalDir = null;
		this.externalDir_m = false;
		this.flattenTree = false;
		this.flattenTree_m = false;
		this.ldapBase = null;
		this.ldapBase_m = false;
		this.ldapDisabled = false;
		this.ldapDisabled_m = false;
		this.ldapFilter = null;
		this.ldapFilter_m = false;
		this.logResultingTree = false;
		this.logResultingTree_m = false;
		this.providerHost = null;
		this.providerHost_m = false;
		this.providerPort = 0;
		this.providerPort_m = false;
		this.securityCredentials = null;
		this.securityCredentials_m = false;
		this.securityEncryption = null;
		this.securityEncryption_m = false;
		this.securityPrincipal = null;
		this.securityPrincipal_m = false;
		this.writeProtection = false;
		this.writeProtection_m = false;
		this.hasSecurityCredentials = null;
		this.hasSecurityCredentials_m = false;
	}


	public boolean isModified()  {
		if(super.isModified())
			return true;
		if(attrAdd_m)
			return true;
		if(attrGroupMember_m)
			return true;
		if(attrGroupName_m)
			return true;
		if(attrGroupObjClass_m)
			return true;
		if(attrGuid_m)
			return true;
		if(attrObjClass_m)
			return true;
		if(attrOrgUnitObjClass_m)
			return true;
		if(attrOrgUnitObjName_m)
			return true;
		if(attrUserFirstname_m)
			return true;
		if(attrUserLastname_m)
			return true;
		if(attrUserMail_m)
			return true;
		if(attrUserObjClass_m)
			return true;
		if(attrUserUsername_m)
			return true;
		if(externalDir_m)
			return true;
		if(flattenTree_m)
			return true;
		if(ldapBase_m)
			return true;
		if(ldapDisabled_m)
			return true;
		if(ldapFilter_m)
			return true;
		if(logResultingTree_m)
			return true;
		if(providerHost_m)
			return true;
		if(providerPort_m)
			return true;
		if(securityCredentials_m)
			return true;
		if(securityEncryption_m)
			return true;
		if(securityPrincipal_m)
			return true;
		if(writeProtection_m)
			return true;
		if(hasSecurityCredentials_m)
			return true;
		return false;
	}


	public List<PropertyAccessor> getPropertyAccessors()  {
		List<PropertyAccessor> list = super.getPropertyAccessors();
		list.add(attrAdd_pa);
		list.add(attrGroupMember_pa);
		list.add(attrGroupName_pa);
		list.add(attrGroupObjClass_pa);
		list.add(attrGuid_pa);
		list.add(attrObjClass_pa);
		list.add(attrOrgUnitObjClass_pa);
		list.add(attrOrgUnitObjName_pa);
		list.add(attrUserFirstname_pa);
		list.add(attrUserLastname_pa);
		list.add(attrUserMail_pa);
		list.add(attrUserObjClass_pa);
		list.add(attrUserUsername_pa);
		list.add(externalDir_pa);
		list.add(flattenTree_pa);
		list.add(ldapBase_pa);
		list.add(ldapDisabled_pa);
		list.add(ldapFilter_pa);
		list.add(logResultingTree_pa);
		list.add(providerHost_pa);
		list.add(providerPort_pa);
		list.add(securityCredentials_pa);
		list.add(securityEncryption_pa);
		list.add(securityPrincipal_pa);
		list.add(writeProtection_pa);
		list.add(hasSecurityCredentials_pa);
		return list;
	}


	public List<PropertyAccessor> getModifiedPropertyAccessors()  {
		List<PropertyAccessor> list = super.getModifiedPropertyAccessors();
		if(attrAdd_m)
			list.add(attrAdd_pa);
		if(attrGroupMember_m)
			list.add(attrGroupMember_pa);
		if(attrGroupName_m)
			list.add(attrGroupName_pa);
		if(attrGroupObjClass_m)
			list.add(attrGroupObjClass_pa);
		if(attrGuid_m)
			list.add(attrGuid_pa);
		if(attrObjClass_m)
			list.add(attrObjClass_pa);
		if(attrOrgUnitObjClass_m)
			list.add(attrOrgUnitObjClass_pa);
		if(attrOrgUnitObjName_m)
			list.add(attrOrgUnitObjName_pa);
		if(attrUserFirstname_m)
			list.add(attrUserFirstname_pa);
		if(attrUserLastname_m)
			list.add(attrUserLastname_pa);
		if(attrUserMail_m)
			list.add(attrUserMail_pa);
		if(attrUserObjClass_m)
			list.add(attrUserObjClass_pa);
		if(attrUserUsername_m)
			list.add(attrUserUsername_pa);
		if(externalDir_m)
			list.add(externalDir_pa);
		if(flattenTree_m)
			list.add(flattenTree_pa);
		if(ldapBase_m)
			list.add(ldapBase_pa);
		if(ldapDisabled_m)
			list.add(ldapDisabled_pa);
		if(ldapFilter_m)
			list.add(ldapFilter_pa);
		if(logResultingTree_m)
			list.add(logResultingTree_pa);
		if(providerHost_m)
			list.add(providerHost_pa);
		if(providerPort_m)
			list.add(providerPort_pa);
		if(securityCredentials_m)
			list.add(securityCredentials_pa);
		if(securityEncryption_m)
			list.add(securityEncryption_pa);
		if(securityPrincipal_m)
			list.add(securityPrincipal_pa);
		if(writeProtection_m)
			list.add(writeProtection_pa);
		if(hasSecurityCredentials_m)
			list.add(hasSecurityCredentials_pa);
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsByView(net.datenwerke.gxtdto.client.dtomanager.DtoView view)  {
		List<PropertyAccessor> list = super.getPropertyAccessorsByView(view);
		if(view.compareTo(DtoView.MINIMAL) >= 0){
			list.add(hasSecurityCredentials_pa);
		}
		if(view.compareTo(DtoView.NORMAL) >= 0){
			list.add(attrAdd_pa);
			list.add(attrGroupMember_pa);
			list.add(attrGroupName_pa);
			list.add(attrGroupObjClass_pa);
			list.add(attrGuid_pa);
			list.add(attrObjClass_pa);
			list.add(attrOrgUnitObjClass_pa);
			list.add(attrOrgUnitObjName_pa);
			list.add(attrUserFirstname_pa);
			list.add(attrUserLastname_pa);
			list.add(attrUserMail_pa);
			list.add(attrUserObjClass_pa);
			list.add(attrUserUsername_pa);
			list.add(externalDir_pa);
			list.add(flattenTree_pa);
			list.add(ldapBase_pa);
			list.add(ldapDisabled_pa);
			list.add(ldapFilter_pa);
			list.add(logResultingTree_pa);
			list.add(providerHost_pa);
			list.add(providerPort_pa);
			list.add(securityCredentials_pa);
			list.add(securityEncryption_pa);
			list.add(securityPrincipal_pa);
			list.add(writeProtection_pa);
		}
		return list;
	}


	public List<PropertyAccessor> getPropertyAccessorsForDtos()  {
		List<PropertyAccessor> list = super.getPropertyAccessorsForDtos();
		list.add(externalDir_pa);
		return list;
	}



	net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto wl_0;

}
