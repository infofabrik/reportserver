package net.datenwerke.rs.ldap.service.ldap

import javax.inject.Inject
import javax.naming.Context
import javax.naming.NamingEnumeration
import javax.naming.NamingException
import javax.naming.directory.Attribute
import javax.naming.directory.InitialDirContext
import javax.naming.directory.SearchControls
import javax.naming.directory.SearchResult
import javax.naming.ldap.LdapName

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datenwerke.rs.configservice.service.configservice.ConfigService
import net.datenwerke.rs.core.service.reportserver.ReportServerModule
import net.datenwerke.rs.ldap.service.ldap.exceptions.LdapException
import net.datenwerke.rs.terminal.service.terminal.TerminalService
import net.datenwerke.security.service.usermanager.UserManagerService
import net.datenwerke.security.service.usermanager.UserPropertiesService
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode
import net.datenwerke.security.service.usermanager.entities.Group
import net.datenwerke.security.service.usermanager.entities.OrganisationalUnit
import net.datenwerke.security.service.usermanager.entities.User
import net.datenwerke.security.service.usermanager.entities.UserProperty


class LdapServiceImpl implements LdapService {

   private static final String CONFIG_FILE = 'sso/ldap.cf'
   private static final String PROPERTY_URL = 'provider.url'
   private static final String PROPERTY_PORT = 'provider.port'
   private static final String PROPERTY_PRINCIPAL = 'security.principal'
   private static final String PROPERTY_CREDENTIALS = 'security.credentials'
   private static final String PROPERTY_BASE = 'base'
   private static final String PROPERTY_FILTER = 'filter'
   private static final String PROPERTY_EXTERNAL_DIR = 'externalDir'
   private static final String PROPERTY_WRITE_PROTECTION = 'writeProtection'
   private static final String PROPERTY_INCLUDE_NAMESPACE = 'includeNamespace'
   private static final String PROPERTY_LOG_RESULTING_TREE = 'logResultingTree'

   private static final String PROPERTY_OBJECT_CLASS = 'objectClass'
   private static final String PROPERTY_GUID = 'guid'
   private static final String PROPERTY_OU_OBJECT_CLASS = 'ouObjectClass'
   private static final String PROPERTY_OU_NAME = 'ouName'
   private static final String PROPERTY_GROUP_OBJECT_CLASS = 'groupObjectClass'
   private static final String PROPERTY_GROUP_NAME = 'groupName'
   private static final String PROPERTY_GROUP_MEMBER = 'groupMember'
   private static final String PROPERTY_USER_OBJECT_CLASS = 'userObjectClass'
   private static final String PROPERTY_USER_FIRSTNAME = 'userFirstname'
   private static final String PROPERTY_USER_LASTNAME = 'userLastname'
   private static final String PROPERTY_USER_USERNAME = 'userUsername'
   private static final String PROPERTY_USER_MAIL = 'userMail'

   // [0]: property path in config file [1]: default value
   def PROPERTY_ATTRIBUTES = [
      (PROPERTY_OBJECT_CLASS): [
         'attributes.objectClass',
         'objectClass'
      ],
      (PROPERTY_GUID): [
         'attributes.guid',
         'objectGUID'
      ],
      (PROPERTY_OU_OBJECT_CLASS): [
         'attributes.organizationalUnit.objectClass',
         'organizationalUnit'
      ],
      (PROPERTY_OU_NAME): [
         'attributes.organizationalUnit.name',
         'name'
      ],
      (PROPERTY_GROUP_OBJECT_CLASS): [
         'attributes.group.objectClass',
         'group'
      ],
      (PROPERTY_GROUP_NAME): [
         'attributes.group.name',
         'name'
      ],
      (PROPERTY_GROUP_MEMBER): [
         'attributes.group.member',
         'member'
      ],
      (PROPERTY_USER_OBJECT_CLASS): [
         'attributes.user.objectClass',
         'user'
      ],
      (PROPERTY_USER_FIRSTNAME): [
         'attributes.user.firstname',
         'givenName'
      ],
      (PROPERTY_USER_LASTNAME): [
         'attributes.user.lastname',
         'sn'
      ],
      (PROPERTY_USER_USERNAME): [
         'attributes.user.username',
         'sAMAccountName'
      ],
      (PROPERTY_USER_MAIL): [
         'attributes.user.mail',
         'mail'
      ]
   ]

   /* the user-configured attributes, or defaults if nothing configured */
   def attributes = [:]

   Logger logger = LoggerFactory.getLogger(getClass().getName());

   def providerUrl

   private final ConfigService configService
   private final TerminalService terminalService
   private final UserManagerService userManagerService
   private final UserPropertiesService userPropertiesService

   OrganisationalUnit targetNode

   boolean includeNamespace

   def guidMap = [:]
   def nodesInDirectoryByName = [:]
   def nodesInDirectoryByGuid = [:]
   TreeMap<LdapName, SearchResult> searchResults

   def inhibitedNodes = []
   def addedNodes = []

   @Inject
   public LdapServiceImpl(
   ConfigService configService,
   TerminalService terminalService,
   UserManagerService userManagerService,
   UserPropertiesService userPropertiesService
   ) {
      this.configService = configService
      this.terminalService = terminalService
      this.userManagerService = userManagerService
      this.userPropertiesService = userPropertiesService

      def url = configService.getConfigFailsafe(CONFIG_FILE).getString(PROPERTY_URL, 'ldap://directory.example.com')
      int port = configService.getConfigFailsafe(CONFIG_FILE).getInteger(PROPERTY_PORT, 389)
      providerUrl = "$url:$port"

      includeNamespace = configService.getConfigFailsafe(CONFIG_FILE).getBoolean(PROPERTY_INCLUDE_NAMESPACE, false)
   }

   private class AdGUID {
      byte[] bytes

      private void addByte(StringBuilder sb, int k) {
         if(k<=0xF)
            sb << '0'
         sb << Integer.toHexString(k)
      }

      @Override
      public String toString() {
         StringBuilder sb = new StringBuilder()
         addByte(sb, (int)bytes[3] & 0xFF)
         addByte(sb, (int)bytes[2] & 0xFF)
         addByte(sb, (int)bytes[1] & 0xFF)
         addByte(sb, (int)bytes[0] & 0xFF)
         sb << '-'
         addByte(sb, (int)bytes[5] & 0xFF)
         addByte(sb, (int)bytes[4] & 0xFF)
         sb << '-'
         addByte(sb, (int)bytes[7] & 0xFF)
         addByte(sb, (int)bytes[6] & 0xFF)
         sb << '-'
         addByte(sb, (int)bytes[8] & 0xFF)
         addByte(sb, (int)bytes[9] & 0xFF)
         sb << '-'
         addByte(sb, (int)bytes[10] & 0xFF)
         addByte(sb, (int)bytes[11] & 0xFF)
         addByte(sb, (int)bytes[12] & 0xFF)
         addByte(sb, (int)bytes[13] & 0xFF)
         addByte(sb, (int)bytes[14] & 0xFF)
         addByte(sb, (int)bytes[15] & 0xFF)

         return sb.toString()
      }
   }

   private Properties compileProperties(){
      Properties props = new Properties()

      props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory")
      props.setProperty(Context.PROVIDER_URL, providerUrl)
      props.setProperty(Context.URL_PKG_PREFIXES, "com.sun.jndi.url")
      props.setProperty(Context.REFERRAL, "throw")
      props.setProperty(Context.SECURITY_AUTHENTICATION, "simple")

      props.setProperty(Context.SECURITY_PRINCIPAL,
            configService.getConfigFailsafe(CONFIG_FILE).getString(PROPERTY_PRINCIPAL,
            'CN=ldaptest,CN=Users,DC=directory,DC=example,DC=com'))
      props.setProperty(Context.SECURITY_CREDENTIALS,
            configService.getConfigFailsafe(CONFIG_FILE).getString(PROPERTY_CREDENTIALS,
            'ldaptest'))

      /* return these as binary */
      props.put("java.naming.ldap.attributes.binary", attributes[PROPERTY_GUID])
      return props
   }

   private void createGuidMap(AbstractUserManagerNode current) {
      def map = [:]
      createGuidMap(current, map )

      guidMap = map
   }

   private void createGuidMap(AbstractUserManagerNode current, Map<String, AbstractUserManagerNode> map){
      map.put(current.guid, current)

      current.children.each { createGuidMap(it, map)}
   }

   private String getStringAttribute(SearchResult sr, String attributeName) throws NamingException{
      if (null == sr.attributes[attributeName])
         throw new LdapException("Failed to retrieve attribute '$attributeName' from ${sr.nameInNamespace}")

      return sr.attributes[attributeName].get().toString()
   }

   private String getGuid(SearchResult sr) throws NamingException{
      def guidAsBytes = sr.attributes[attributes[PROPERTY_GUID]].get() as byte[]
      AdGUID guid = new AdGUID(bytes: guidAsBytes)
      return guid.toString()

   }

   private void loadAllAttributes() {
      def config = configService.getConfigFailsafe(CONFIG_FILE)
      PROPERTY_ATTRIBUTES.each { prop, val ->
         attributes[prop] = config.getString(val[0], val[1])
      }
   }

   private void loadFromDirectory() throws NamingException {
      Properties props = compileProperties()
      String originBase = providerUrl.endsWith("/")?providerUrl:providerUrl + "/"

      this.nodesInDirectoryByName = [:]
      this.nodesInDirectoryByGuid = [:]
      this.addedNodes = []
      this.inhibitedNodes = []

      InitialDirContext ctx = null
      try {
         ctx = new InitialDirContext(props)

         SearchControls searchControls = new SearchControls()
         searchControls.searchScope = SearchControls.SUBTREE_SCOPE
         /* we set the attributes we want explicitely, so we also get operational attributes */
         searchControls.attributesToReturn = (attributes.values() as String[])

         LdapName ldapBaseName = new LdapName(configService.getConfigFailsafe(CONFIG_FILE).getString(PROPERTY_BASE,
               'OU=EXAMPLE,DC=directory,DC=example,DC=com'))
         NamingEnumeration<SearchResult> results = ctx.search(ldapBaseName,
               configService.getConfigFailsafe(CONFIG_FILE).getString(PROPERTY_FILTER,
               '(|(objectClass=organizationalUnit)(objectClass=user)(objectClass=group))'), searchControls)

         /* order search results by name to make sure children never get processed before their parent */
         searchResults = new TreeMap<>()

         results.each { searchResults.put(new LdapName(it.nameInNamespace), it)}

         searchResults.values().each { sr ->
            LdapName nodeName = new LdapName(isIncludeNamespace() ? sr.nameInNamespace : sr.name)
            LdapName nodeNameInNamespace = new LdapName(sr.nameInNamespace)

            /* skip empty nodes */
            if(nodeName.size() != 0) {

               /* get parent node */
               LdapName parentName = (LdapName) nodeNameInNamespace.getPrefix(Math.max(0, nodeNameInNamespace.size() - 1))
               AbstractUserManagerNode parent = this.nodesInDirectoryByName.get(parentName)
               if(!parent){
                  if(parentName.equals(new LdapName(
                        configService.getConfigFailsafe(CONFIG_FILE).getString(PROPERTY_BASE,
                        'OU=EXAMPLE,DC=directory,DC=example,DC=com')))){
                     /* root node */
                     parent = targetNode
                  } else {
                     throw new IllegalStateException("Missing parent for " + sr.nameInNamespace)
                  }
               }

               /* create node */
               Attribute objectClass = sr.attributes[(attributes[PROPERTY_OBJECT_CLASS])]
               AbstractUserManagerNode umNode = null
               if(objectClass.contains(attributes[PROPERTY_OU_OBJECT_CLASS]))
                  umNode = createOUNode(sr, parent)
               else if(objectClass.contains(attributes[PROPERTY_USER_OBJECT_CLASS]))
                  umNode = createUserNode(sr, parent)
               else if(objectClass.contains(attributes[PROPERTY_GROUP_OBJECT_CLASS]))
                  umNode = createGroupNode(sr, parent)

               /* make sure the node is not null */
               assert umNode

               /* set common attributes */
               if (configService.getConfigFailsafe(CONFIG_FILE).getBoolean(PROPERTY_WRITE_PROTECTION, true))
                  umNode.writeProtection = true

               umNode.guid = getGuid(sr)
               umNode.origin = "$originBase${sr.nameInNamespace}"

               nodesInDirectoryByName.put(new LdapName(sr.nameInNamespace), umNode)
               nodesInDirectoryByGuid.put(getGuid(sr), umNode)
            }
         }
      } finally {
         if(null != ctx)
            ctx.close()
      }
   }

   private AbstractUserManagerNode createGroupNode(SearchResult sr, AbstractUserManagerNode parent) throws NamingException {
      def node = guidMap[getGuid(sr)]
      if(!node){
         node = new Group()
         addedNodes << node
      }
      parent.addChild node

      /* copy Group attributes */
      node.name = getStringAttribute(sr, attributes[PROPERTY_GROUP_NAME])

      return node
   }


   private AbstractUserManagerNode createUserNode(SearchResult sr, AbstractUserManagerNode parent) throws NamingException {
      def node = guidMap[getGuid(sr)]
      if(!node){
         node = new User()
         addedNodes << node
      }
      parent.addChild node

      /* copy User attributes */
      node.firstname = getStringAttribute(sr, attributes[PROPERTY_USER_FIRSTNAME])
      node.lastname = getStringAttribute(sr, attributes[PROPERTY_USER_LASTNAME])
      node.username = getStringAttribute(sr, attributes[PROPERTY_USER_USERNAME])
      node.email = getStringAttribute(sr, attributes[PROPERTY_USER_MAIL])

      setInhibitUser node, false

      return node
   }

   private AbstractUserManagerNode createOUNode(SearchResult sr, AbstractUserManagerNode parent) throws NamingException {
      def node = guidMap[getGuid(sr)]
      if(!node){
         node = new OrganisationalUnit()
         addedNodes << node
      }
      parent.addChild node

      /* copy OU attributes */
      node.name = getStringAttribute(sr, attributes[PROPERTY_OU_NAME])

      return node
   }


   private void postprocessGroups() throws NamingException {

      /* clear */
      nodesInDirectoryByName
            .findAll { key, val -> val instanceof Group }
            .each { entry, group ->
               group.users.clear()
               group.ous.clear()
               group.referencedGroups.clear()
            }

      /* add appropriate users to groups */
      nodesInDirectoryByName
            .findAll { name, val -> val instanceof Group }
            .each { key, group ->
               SearchResult sr = searchResults[key]
               def memberAttribute = attributes[PROPERTY_GROUP_MEMBER]
               def memberVal = sr.attributes[memberAttribute]
               if(memberVal){
                  sr.attributes[memberAttribute].getAll().each {
                     LdapName memberName = new LdapName(it.toString())
                     AbstractUserManagerNode member = nodesInDirectoryByName[memberName]
                     if(member){
                        if(member instanceof User)
                           group.addUser member
                        else if(member instanceof OrganisationalUnit)
                           group.addOu member
                        else if(member instanceof Group)
                           group.addReferencedGroup member
                     }
                  }
               }
            }
   }

   private void printTree(AbstractUserManagerNode current){
      StringBuilder sb = new StringBuilder()
      List<AbstractUserManagerNode> rl = current.rootLine.reverse()

      rl.each{ sb << "${it.name}." }

      sb << "${current.name}[${current.class.simpleName}]"

      if(current instanceof Group){
         Group group = (Group) current
         sb << " (${group.users.size() + group.ous.size() + group.referencedGroups.size()} members)"
      }

      logger.info(sb.toString())

      current.children.each{ printTree(it) }
   }


   private void inhibitRemovedUsers(AbstractUserManagerNode current) {
      current.children.each{  inhibitRemovedUsers(it) }

      if(current instanceof User &&
            current.origin &&
            current.origin.startsWith(providerUrl) &&
            !nodesInDirectoryByGuid.containsKey(current.guid)){
         setInhibitUser current, true
         inhibitedNodes << current
      }
   }

   private void createExternalRootDir() {
      def externalDir = configService.getConfigFailsafe(CONFIG_FILE).getString(PROPERTY_EXTERNAL_DIR,
            '/usermanager/external')
      def targetNode = terminalService.getObjectByLocation(externalDir)

      if (!(targetNode instanceof OrganisationalUnit) && targetNode)
         throw new IllegalArgumentException("Not an organizational unit")

      if(!targetNode){
         def umRoot = userManagerService.roots[0]
         targetNode = new OrganisationalUnit(externalDir[externalDir.lastIndexOf('/')+1..-1])
         umRoot.addChild targetNode
         userManagerService.persist targetNode
      }

      assert targetNode

      this.targetNode = targetNode
   }

   private void setInhibitUser(User user, boolean inhibit){
      UserProperty inhibitionProperty = user.getProperty(ReportServerModule.USER_PROPERTY_ACCOUNT_INHIBITED)

      if(!inhibitionProperty){
         inhibitionProperty = new UserProperty(ReportServerModule.USER_PROPERTY_ACCOUNT_INHIBITED, inhibit)
         userPropertiesService.setProperty(user, inhibitionProperty)
      } else
         inhibitionProperty.value = inhibit
   }

   @Override
   public void importUsers() throws LdapException{

      try {
         loadAllAttributes()

         createExternalRootDir()

         createGuidMap targetNode

         loadFromDirectory()
         postprocessGroups()

         inhibitRemovedUsers targetNode

         logger.info('Starting LDAP Import...')
         logger.info("Retrieved nodes from LDAP directory: ${nodesInDirectoryByGuid.size()}")
         logger.info("Nodes added: ${addedNodes.size()}")
         logger.info("Nodes inhibited: ${inhibitedNodes.size()}")
         int overallCount = countImportedNodes(targetNode) - 1
         logger.info("Overall nodes in ReportServer: $overallCount")

         if((overallCount - inhibitedNodes.size()) != nodesInDirectoryByGuid.size())
            throw new LdapException('Failed to import user data from directory')
         else
            logger.info('LDAP Import done.')

         if (configService.getConfigFailsafe(CONFIG_FILE).getBoolean(PROPERTY_LOG_RESULTING_TREE, false))
            printTree targetNode
      } catch (Exception e) {
         throw new LdapException(e)
      }
   }


   private int countImportedNodes(AbstractUserManagerNode current) {
      int i = 1
      current.children.each { i += countImportedNodes(it) }

      return i
   }
}
