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

   /* [path, default] */
   public static final List<String> PROPERTY_HOST = new ArrayList<>(
         Arrays.asList("provider.host", "directory.example.com"));
   public static final List<Object> PROPERTY_PORT = new ArrayList<>(
         Arrays.asList("provider.port", 389));

   public static final List<String> PROPERTY_ENCRYPTION_PROTOCOL = new ArrayList<>(
         Arrays.asList("security.encryption", "none"));
   public static final List<String> PROPERTY_PRINCIPAL = new ArrayList<>(
         Arrays.asList("security.principal", "CN=ldaptest,CN=Users,DC=directory,DC=example,DC=com"));
   public static final List<String> PROPERTY_CREDENTIALS = new ArrayList<>(
         Arrays.asList("security.credentials", "ldaptest"));

   public static final List<String> PROPERTY_BASE = new ArrayList<>(
         Arrays.asList("base", "OU=EXAMPLE,DC=directory,DC=example,DC=com"));
   public static final List<String> PROPERTY_FILTER = new ArrayList<>(
         Arrays.asList("filter", "(|(objectClass=organizationalUnit)(objectClass=user)(objectClass=group))"));
   public static final List<String> PROPERTY_EXTERNAL_DIR = new ArrayList<>(
         Arrays.asList("externalDir", "/usermanager/external"));
   public static final List<Object> PROPERTY_WRITE_PROTECTION = new ArrayList<>(
         Arrays.asList("writeProtection", true));
   public static final List<Object> PROPERTY_INCLUDE_NAMESPACE = new ArrayList<>(
         Arrays.asList("includeNamespace", false));
   public static final List<Object> PROPERTY_LOG_RESULTING_TREE = new ArrayList<>(
         Arrays.asList("logResultingTree", false));

   /* Object properties: [property name, path, default] */
   public static final List<String> PROPERTY_OBJECT_CLASS = new ArrayList<>(
         Arrays.asList("objectClass", "attributes.objectClass", "objectClass"));
   public static final List<String> PROPERTY_GUID = new ArrayList<>(
         Arrays.asList("guid", "attributes.guid", "objectGUID"));
   
   /* Organisational unit properties: [property name, path, default] */
   public static final List<String> PROPERTY_OU_OBJECT_CLASS = new ArrayList<>(
         Arrays.asList("ouObjectClass", "attributes.organizationalUnit.objectClass", "organizationalUnit"));
   public static final List<String> PROPERTY_OU_NAME = new ArrayList<>(
         Arrays.asList("ouName", "attributes.organizationalUnit.name", "name"));
   
   /* Group properties: [property name, path, default] */
   public static final List<String> PROPERTY_GROUP_OBJECT_CLASS = new ArrayList<>(
         Arrays.asList("groupObjectClass", "attributes.group.objectClass", "group"));
   public static final List<String> PROPERTY_GROUP_NAME = new ArrayList<>(
         Arrays.asList("groupName", "attributes.group.name", "name"));
   public static final List<String> PROPERTY_GROUP_MEMBER = new ArrayList<>(
         Arrays.asList("groupMember", "attributes.group.member", "member"));
   
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
   
   /**
    * Returns a list with the LDAP users found.
    * 
    * @return the users found
    * @throws LdapException if an error happens during node retrieval
    */
   List<Map<String, String>> testUsers() throws LdapException;
   
   /**
    * Returns a list with the LDAP groups found.
    * 
    * @return the groups found
    * @throws LdapException if an error happens during node retrieval
    */
   List<Map<String, String>> testGroups() throws LdapException;
   
   /**
    * Returns a list with the LDAP organisational units found.
    * 
    * @return the organisational units found
    * @throws LdapException if an error happens during node retrieval
    */
   List<Map<String, String>> testOrganisationalUnits() throws LdapException;
}
