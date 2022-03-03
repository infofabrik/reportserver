package net.datenwerke.rs.ldap.service.ldap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.naming.directory.SearchResult;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.ldap.service.ldap.exceptions.LdapException;

@ImplementedBy(DummyLdapServiceImpl.class)
public interface LdapService {

   public static final String CONFIG_FILE = "sso/ldap.cf";

   public static final String PROPERTY_HOST = "provider.host";
   public static final String PROPERTY_PORT = "provider.port";

   public static final String PROPERTY_ENCRYPTION_PROTOCOL = "security.encryption";
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
   
   /* User properties: [property name, path, default] */
   public static final List<String> PROPERTY_USER_OBJECT_CLASS = new ArrayList<>(
         Arrays.asList("userObjectClass", "attributes.user.objectClass", "user"));
   public static final List<String> PROPERTY_USER_FIRSTNAME = new ArrayList<>(
         Arrays.asList("userFirstname", "attributes.user.firstname", "givenName"));
   public static final List<String> PROPERTY_USER_LASTNAME = new ArrayList<>(
         Arrays.asList("userLastname", "attributes.user.lastname", "sn"));
   public static final List<String> PROPERTY_USER_USERNAME = new ArrayList<>(
         Arrays.asList("userUsername", "attributes.user.username", "sAMAccountName"));
   public static final List<String> PROPERTY_USER_MAIL = new ArrayList<>(
         Arrays.asList("userMail", "attributes.user.mail", "mail"));
   
   
   /**
    * Imports LDAP users as configured in sso/ldap.cf
    * 
    * @throws LdapException if the LDAP users cannot be imported
    */
   void importUsers() throws LdapException;

   /**
    * Reads a given attribute value of a given LDAP SearchResult. Note that you
    * have to include the property needed in the ldap.cf configuration file
    * (additionalReturningAttributes) in order to the attribute to be retrieved if
    * you need non-standard attributes.
    * 
    * @param sr            the SearchResult
    * @param attributeName the attribute to read
    * @return the attribute value as String
    * @throws LdapException if the attribute does not exist or an error happens
    *                       during attribute retrieval
    */
   String getStringAttribute(SearchResult sr, String attributeName) throws LdapException;

   /**
    * Returns a list with the nodes found with the LDAP filter installed.
    * 
    * @return the nodes found
    * @throws LdapException if an error happens during node retrieval
    */
   List<String> testFilter() throws LdapException;
   
   List<Map<String, String>> testUsers() throws LdapException;
}
