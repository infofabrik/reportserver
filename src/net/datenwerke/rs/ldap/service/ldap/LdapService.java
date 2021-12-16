package net.datenwerke.rs.ldap.service.ldap;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.ldap.service.ldap.exceptions.LdapException;

@ImplementedBy(DummyLdapServiceImpl.class)
public interface LdapService {

   public static final String CONFIG_FILE = "sso/ldap.cf";

   public static final String PROPERTY_URL = "provider.url";
   public static final String PROPERTY_PORT = "provider.port";
   public static final String PROPERTY_PRINCIPAL = "security.principal";
   public static final String PROPERTY_CREDENTIALS = "security.credentials";
   public static final String PROPERTY_BASE = "base";
   public static final String PROPERTY_FILTER = "filter";
   public static final String PROPERTY_EXTERNAL_DIR = "externalDir";
   public static final String PROPERTY_WRITE_PROTECTION = "writeProtection";
   public static final String PROPERTY_INCLUDE_NAMESPACE = "includeNamespace";
   public static final String PROPERTY_LOG_RESULTING_TREE = "logResultingTree";

   public static final String PROPERTY_OBJECT_CLASS = "objectClass";
   public static final String PROPERTY_GUID = "guid";
   public static final String PROPERTY_OU_OBJECT_CLASS = "ouObjectClass";
   public static final String PROPERTY_OU_NAME = "ouName";
   public static final String PROPERTY_GROUP_OBJECT_CLASS = "groupObjectClass";
   public static final String PROPERTY_GROUP_NAME = "groupName";
   public static final String PROPERTY_GROUP_MEMBER = "groupMember";
   public static final String PROPERTY_USER_OBJECT_CLASS = "userObjectClass";
   public static final String PROPERTY_USER_FIRSTNAME = "userFirstname";
   public static final String PROPERTY_USER_LASTNAME = "userLastname";
   public static final String PROPERTY_USER_USERNAME = "userUsername";
   public static final String PROPERTY_USER_MAIL = "userMail";

   /**
    * Imports LDAP users as configured in sso/ldap.cf
    * 
    * @throws LdapException if the LDAP users cannot be imported
    */
   void importUsers() throws LdapException;
}
