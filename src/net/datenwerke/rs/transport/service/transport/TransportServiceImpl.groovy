package net.datenwerke.rs.transport.service.transport;

import static net.datenwerke.eximport.ExImportHelperService.DOCUMENT_DATA_ELEMENT;
import static net.datenwerke.eximport.ExImportHelperService.DOCUMENT_HEAD_DATE_ELEMENT;
import static net.datenwerke.eximport.ExImportHelperService.DOCUMENT_HEAD_DESCRIPTION_ELEMENT;
import static net.datenwerke.eximport.ExImportHelperService.DOCUMENT_HEAD_ELEMENT;
import static net.datenwerke.eximport.ExImportHelperService.DOCUMENT_HEAD_NAME_ELEMENT;
import static net.datenwerke.eximport.ExImportHelperService.DOCUMENT_HEAD_VERSION_ELEMENT;
import static net.datenwerke.eximport.ExImportHelperService.DOCUMENT_ROOT_ELEMENT;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.inject.Inject;
import javax.inject.Provider;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.configuration2.Configuration
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.text.StringEscapeUtils;

import com.google.common.base.Charsets;
import com.google.inject.name.Named;
import com.mysql.cj.util.StringUtils;

import groovy.xml.XmlSlurper
import groovy.xml.XmlUtil
import groovy.xml.slurpersupport.GPathResult
import groovy.xml.slurpersupport.NodeChild
import net.datenwerke.eximport.ExportDataAnalyzerService;
import net.datenwerke.eximport.ExportDataProvider;
import net.datenwerke.eximport.ExportDataProviderImpl;
import net.datenwerke.eximport.ex.ExportFileProcessingHelper;
import net.datenwerke.eximport.ex.Exporter;
import net.datenwerke.eximport.im.ImportResult
import net.datenwerke.eximport.obj.ExportedItem;
import net.datenwerke.eximport.obj.ItemProperty
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService
import net.datenwerke.rs.EnvironmentValidatorHelperService
import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.GeneralInfoService;
import net.datenwerke.rs.base.ext.service.RemoteEntityImporterService
import net.datenwerke.rs.configservice.service.configservice.ConfigService
import net.datenwerke.rs.core.service.datasinkmanager.DatasinkTreeService
import net.datenwerke.rs.core.service.datasinkmanager.entities.AbstractDatasinkManagerNode;
import net.datenwerke.rs.core.service.datasourcemanager.DatasourceService
import net.datenwerke.rs.core.service.datasourcemanager.entities.AbstractDatasourceManagerNode;
import net.datenwerke.rs.core.service.reportmanager.ReportService
import net.datenwerke.rs.core.service.reportmanager.entities.AbstractReportManagerNode;
import net.datenwerke.rs.eximport.service.eximport.im.http.HttpImportService
import net.datenwerke.rs.fileserver.service.fileserver.FileServerService
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.rs.json.service.json.JsonService
import net.datenwerke.rs.license.service.LicenseService
import net.datenwerke.rs.remotersrestserver.service.remotersrestserver.entities.RemoteRsRestServer
import net.datenwerke.rs.remoteserver.service.remoteservermanager.RemoteServerTreeService
import net.datenwerke.rs.terminal.service.terminal.TerminalService
import net.datenwerke.rs.terminal.service.terminal.TerminalSession
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException
import net.datenwerke.rs.transport.client.transport.dto.TransportElementDto
import net.datenwerke.rs.transport.service.transport.entities.AbstractTransportManagerNode
import net.datenwerke.rs.transport.service.transport.entities.Transport;
import net.datenwerke.rs.transport.service.transport.entities.TransportFolder
import net.datenwerke.rs.transport.service.transport.hooks.ApplyPreconditionHook
import net.datenwerke.rs.utils.crypto.HashUtil
import net.datenwerke.rs.utils.misc.DateUtils;
import net.datenwerke.security.service.authenticator.AuthenticatorService
import net.datenwerke.security.service.security.SecurityService
import net.datenwerke.security.service.security.annotation.ArgumentVerification
import net.datenwerke.security.service.security.annotation.RightsVerification
import net.datenwerke.security.service.security.annotation.SecurityChecked
import net.datenwerke.security.service.security.rights.Execute
import net.datenwerke.security.service.security.rights.Read
import net.datenwerke.security.service.security.rights.Write
import net.datenwerke.security.service.usermanager.entities.User
import net.datenwerke.treedb.ext.service.eximport.helper.TreeNodeExportHelperService
import net.datenwerke.treedb.service.treedb.AbstractNode
import net.datenwerke.treedb.service.treedb.TreeDBManager
import nu.xom.Element
import nu.xom.Elements


public class TransportServiceImpl implements TransportService {

   private final Provider<AuthenticatorService> authenticatorServiceProvider
   private final Provider<GeneralInfoService> generalInfoServiceProvider
   private final Provider<HookHandlerService> hookHandlerProvider
   private final Provider<HttpImportService> httpImportServiceProvider
   private final Provider<LicenseService> licenseServiceProvider
   private final Provider<EnvironmentValidatorHelperService> envServiceProvider
   private final Provider<JsonService> jsonServiceProvider
   private final Provider<HashUtil> hashUtilProvider
   private final Provider<TransportTreeService> transportTreeServiceProvider
   private final Provider<TreeNodeExportHelperService> treeNodeExportHelperServiceProvider
   private final Provider<ExportDataAnalyzerService> exportDataAnalyzerServiceProvider
   private final Provider<ExportFileProcessingHelper> exportFileProcessingHelperProvider
   private final Provider<RemoteEntityImporterService> remoteEntityImporterServiceProvider
   private final Provider<ConfigService> configServiceProvider
   private final Provider<RemoteServerTreeService> remoteServerTreeServiceProvider
   private final Provider<TerminalService> terminalServiceProvider
   private final Provider<TerminalSession> terminalSessionProvider
   private final Provider<SecurityService> securityServiceProvider

   private final Provider<DatasinkTreeService> datasinkServiceProvider
   private final Provider<DatasourceService> datasourceServiceProvider
   private final Provider<ReportService> reportServiceProvider
   private final Provider<FileServerService> fileServerServiceProvider

   private final static String INITIAL_PROP_USER_USERNAME = "USER_USERNAME";
   private final static String INITIAL_PROP_USER_FIRSTNAME = "USER_FIRSTNAME";
   private final static String INITIAL_PROP_USER_LASTNAME = "USER_LASTNAME";
   private final static String INITIAL_PROP_USER_EMAIL = "USER_EMAIL";
   private final static String INITIAL_PROP_SERVER_ID = "SERVER_ID";
   private final static String INITIAL_PROP_RS_VERSION = "RS_VERSION";
   private final static String INITIAL_PROP_SCHEMA_VERSION = "SCHEMA_VERSION";
   private final static String INITIAL_PROP_STATUS = "STATUS";
   private final static String INITIAL_PROP_KEY = "KEY";
   private final static String INITIAL_PROP_XML = "XML";

   @Inject
   public TransportServiceImpl(
      Provider<AuthenticatorService> authenticatorServiceProvider,
      Provider<GeneralInfoService> generalInfoServiceProvider,
      Provider<HookHandlerService> hookHandlerProvider,
      Provider<HttpImportService> httpImportServiceProvider,
      Provider<LicenseService> licenseServiceProvider,
      Provider<EnvironmentValidatorHelperService> envServiceProvider,
      Provider<JsonService> jsonServiceProvider,
      Provider<HashUtil> hashUtilProvider,
      Provider<TransportTreeService> transportTreeServiceProvider,
      Provider<TreeNodeExportHelperService> treeNodeExportHelperServiceProvider,
      Provider<ExportDataAnalyzerService> exportDataAnalyzerServiceProvider,
      Provider<ExportFileProcessingHelper> exportFileProcessHelperProvider,
      Provider<RemoteEntityImporterService> remoteEntityImporterServiceProvider,
      Provider<RemoteServerTreeService> remoteServerTreeServiceProvider,
      Provider<TerminalService> terminalServiceProvider,
      Provider<TerminalSession> terminalSessionProvider,
      Provider<SecurityService> securityServiceProvider,
      /* For managers */
      Provider<DatasinkTreeService> datasinkServiceProvider,
      Provider<DatasourceService> datasourceServiceProvider,
      Provider<ReportService> reportServiceProvider,
      Provider<FileServerService> fileServerServiceProvider,
      
      Provider<ConfigService> configServiceProvider
   ) {
      this.authenticatorServiceProvider = authenticatorServiceProvider
      this.generalInfoServiceProvider = generalInfoServiceProvider
      this.hookHandlerProvider = hookHandlerProvider
      this.licenseServiceProvider = licenseServiceProvider
      this.envServiceProvider = envServiceProvider
      this.jsonServiceProvider = jsonServiceProvider
      this.hashUtilProvider = hashUtilProvider
      this.transportTreeServiceProvider = transportTreeServiceProvider
      this.treeNodeExportHelperServiceProvider = treeNodeExportHelperServiceProvider
      this.exportDataAnalyzerServiceProvider = exportDataAnalyzerServiceProvider
      this.exportFileProcessingHelperProvider = exportFileProcessHelperProvider
      this.reportServiceProvider = reportServiceProvider
      this.remoteEntityImporterServiceProvider = remoteEntityImporterServiceProvider
      this.remoteServerTreeServiceProvider = remoteServerTreeServiceProvider
      this.terminalServiceProvider = terminalServiceProvider
      this.terminalSessionProvider = terminalSessionProvider
      this.configServiceProvider = configServiceProvider
      this.datasinkServiceProvider = datasinkServiceProvider
      this.datasourceServiceProvider = datasourceServiceProvider
      this.reportServiceProvider = reportServiceProvider
      this.fileServerServiceProvider = fileServerServiceProvider
      this.securityServiceProvider = securityServiceProvider
   }
   
   @Override
   @SecurityChecked(
      argumentVerification = [
         @ArgumentVerification(
            name = "parent",
            verify = @RightsVerification(
               rights = [ Read.class, Write.class ]
            )
         )
      ]
   )
   public String createTransport(String description, @Named("parent") TransportFolder parent) {
      Map<String,String> asMap = createInitialProperties()

      Transport transport = new Transport()
      setInitialProperties(transport, asMap, true)
      transport.description = description
      transport.parent = parent

      transportTreeServiceProvider.get().persist transport
      return asMap[INITIAL_PROP_KEY]
   }

   @Override
   public void setInitialProperties(Transport transport, Map<String,String> initialProperties, boolean setXml) {
      transport.serverId         = initialProperties[INITIAL_PROP_SERVER_ID]
      transport.rsVersion        = initialProperties[INITIAL_PROP_RS_VERSION]
      transport.rsSchemaVersion  = initialProperties[INITIAL_PROP_SCHEMA_VERSION]
      transport.status           = initialProperties[INITIAL_PROP_STATUS]
      transport.creatorUsername  = initialProperties[INITIAL_PROP_USER_USERNAME]
      transport.creatorFirstname = initialProperties[INITIAL_PROP_USER_FIRSTNAME]
      transport.creatorLastname  = initialProperties[INITIAL_PROP_USER_LASTNAME]
      transport.creatorEmail     = initialProperties[INITIAL_PROP_USER_EMAIL]
      transport.key              = initialProperties[INITIAL_PROP_KEY]
      transport.appliedBy        = null 
      transport.appliedOn        = null
      transport.appliedProtocol  = null 
      if (setXml)
         transport.xml = initialProperties[INITIAL_PROP_XML]
   }

   @Override
   public Map<String,String> createInitialProperties() {
      User user = authenticatorServiceProvider.get().currentUser
      String serverId = licenseServiceProvider.get().serverId
      String rsVersion = generalInfoServiceProvider.get().rsVersion
      String schemaVersion = 'unknown'
      try {
         schemaVersion = envServiceProvider.get().schemaVersion
      } catch (SQLException e) {
         schemaVersion = ExceptionUtils.getRootCauseMessage(e)
      }
      String username = user.username
      String firstname = user.firstname
      String lastname = user.lastname
      String email = user.email

      Map<String,String> asMap = new HashMap<>()
      // now is important for getting different hash values
      asMap['now'] = DateUtils.formatCurrentDate()
      asMap[INITIAL_PROP_SERVER_ID] = serverId
      asMap[INITIAL_PROP_RS_VERSION] = rsVersion
      asMap[INITIAL_PROP_SCHEMA_VERSION] = schemaVersion
      asMap[INITIAL_PROP_USER_USERNAME] = username
      asMap[INITIAL_PROP_USER_FIRSTNAME] = firstname
      asMap[INITIAL_PROP_USER_LASTNAME] = lastname
      asMap[INITIAL_PROP_USER_EMAIL] = email
      asMap[INITIAL_PROP_STATUS] = TransportService.Status.CREATED.name()
      asMap[INITIAL_PROP_XML] =
            """<?xml version="1.0" encoding="UTF-8"?>
<${DOCUMENT_ROOT_ELEMENT} xmlns="http://reportserver.datenwerke.net/eximport">
    <${DOCUMENT_HEAD_ELEMENT}>
        <${DOCUMENT_HEAD_NAME_ELEMENT}>Transport</${DOCUMENT_HEAD_NAME_ELEMENT}>
        <${DOCUMENT_HEAD_DESCRIPTION_ELEMENT}/>
        <${DOCUMENT_HEAD_DATE_ELEMENT}>${new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss Z").format(new Date())}</${DOCUMENT_HEAD_DATE_ELEMENT}>
        <${DOCUMENT_HEAD_VERSION_ELEMENT}/>
    </${DOCUMENT_HEAD_ELEMENT}>
    <${DOCUMENT_DATA_ELEMENT}/>
</${DOCUMENT_ROOT_ELEMENT}>
"""

      String asJson = jsonServiceProvider.get().map2Json(asMap)
      String hash = hashUtilProvider.get().sha1(asJson)
      asMap[INITIAL_PROP_KEY] = hash
      return asMap
   }

   @Override
   @SecurityChecked(
      argumentVerification = [
         @ArgumentVerification(
            name = "transport",
            verify = @RightsVerification(
               rights = [ Read.class, Write.class ]
            )
         ),
         @ArgumentVerification(
            name = "element",
            verify = @RightsVerification(
               rights = [ Read.class ]
            )
         )
      ]
   )
   public void addElement(@Named("transport") Transport transport, @Named("element") AbstractNode<?> element, boolean includeVariants) {      
      if (!isTransportable(element))
         throw new IllegalArgumentException("Element is not transportable: '$element'");
      if (isKeyNull(element))
         throw new IllegalArgumentException("Key of the Element is null: '$element'");
      if (transport.closed)
         throw new IllegalArgumentException("Transport is closed: '$transport'");

      TreeNodeExportHelperService exportService = treeNodeExportHelperServiceProvider.get()
      ExportDataAnalyzerService analyzerService = exportDataAnalyzerServiceProvider.get()
      ExportFileProcessingHelper xmlHelper = exportFileProcessingHelperProvider.get()

      String currentXml = transport.xml

      xmlHelper.xml = currentXml

      String exportedXml = exportService.export(element, includeVariants, "Transport", true, false);

      GPathResult xml = new XmlSlurper(false ,false).parseText(exportedXml)
      def exporterDataElements = xml.'**'.findAll { it.name() == 'exporterData' }
      exporterDataElements.drop(1)
         .findAll { it.@exporterType != 'net.datenwerke.rs.fileserver.service.fileserver.eximport.FileServerExporter' }
         .each  {it.replaceNode {} }
      
      exportedXml = XmlUtil.serialize(xml)

      ExportDataProvider exportedDataProvider = new ExportDataProviderImpl(exportedXml.getBytes(Charsets.UTF_8))
      ExportDataProvider currentDataProvider = new ExportDataProviderImpl(currentXml.getBytes(Charsets.UTF_8))

      try {
         Elements exportedExporterData = analyzerService.getExporterElements(exportedDataProvider);
         Collection<Class<? extends Exporter>> currentExporterDataClasses = analyzerService.getExporterClasses(currentDataProvider);

         Iterator<Element> exportedExporterDataIt = exportedExporterData.iterator();
         while (exportedExporterDataIt.hasNext()) {
            Element exportedExporterDataElement = exportedExporterDataIt.next();
            Class<? extends Exporter> exportedClazz = analyzerService.getExporterClass(exportedExporterDataElement);
            if (!currentExporterDataClasses.contains(exportedClazz)) {
               // exporterData with this class does not exist, we can add the complete exporterData block
//               println "Adding exporter class to current exporters: $exportedClazz"
               xmlHelper.addExporterDataBlock(exportedExporterDataElement.toXML()); 
            } else {
               // exporterData with this class exists. We have to analyze the items
               Elements currentItems = analyzerService.getExportedItemElementsFor(currentDataProvider, exportedClazz);
               Iterator<Element> currentItemsIt = currentItems.iterator();
               Set<String> currentItemsIds = new HashSet<>();
               while (currentItemsIt.hasNext()) {
                  Element currentItem = currentItemsIt.next();
                  currentItemsIds.add(analyzerService.getItemId(currentItem));
               }

               Elements exportedItems = analyzerService.getExportedItemElementsFor(exportedDataProvider, exportedClazz);
               Iterator<Element> exportedItemsIt = exportedItems.iterator();
               while (exportedItemsIt.hasNext()) {
                  Element exportedItem = exportedItemsIt.next();
                  String exportedItemId = analyzerService.getItemId(exportedItem);
                  if (currentItemsIds.contains(exportedItemId)) {
                     // the item exists. We have to replace it
//                     println "Element already contained. We replace it: $exportedItemId"
                     xmlHelper.replaceExportedItem(exportedItemId, exportedClazz.getName(), exportedItem.toXML());
                  } else {
                     // the item does not exist. We can just add it
//                     println "Element not contained. We add it"
                     xmlHelper.addExportedItem(exportedClazz.getName(), exportedItem.toXML());
                  }
               }

//               println "Elements: ${currentItems.toString()}"
            }
//            println "Export found: $exportedClazz"
         }
         /* add key information */
         String xmlWithKeys = addReferenceKeys(xmlHelper.serialize())
         checkKeyConstraint(xmlWithKeys)
         transport.setXml(xmlWithKeys);
         transportTreeServiceProvider.get().merge(transport);
      } catch (ClassNotFoundException e) {
         throw new IllegalArgumentException(e);
      }
   }
   
   /**
    * Scan the current transport xml and assert if any key points to more than one id
    * per tree
    */
   private void checkKeyConstraint(String transportxml) throws IllegalArgumentException {
      def xml = new XmlSlurper(false, false).parseText(transportxml)
      def exporters = xml.'**'.findAll{it.name()=='exporterData'}
      exporters.each { exporter ->
         def keyIdMap = [:]
         def exportedItems = exporter.exportedItem
         exportedItems.each { item ->
            def id = item."@xml:id" as String
            id = id.split("-")[-1]
            def key = item.itemProperty.find{it.@name == "key"}?.text()
            if(!key)
               return //skip element
            keyIdMap.containsKey(key) ?
                  { throw new IllegalArgumentException("This transport contains a key:$key with object id:${keyIdMap[key]} already - cannot add item with the same key") }() :
                  keyIdMap.put(key, id)
         }
      }
   }
   
   @Override
   @SecurityChecked(
      argumentVerification = [
         @ArgumentVerification(
            name = "transport",
            verify = @RightsVerification(
               rights = [ Read.class, Write.class ]
            )
         )
      ]
   )
   void removeElements(@Named("transport") Transport transport, List<TransportElementDto> toRemove) {
      if (transport.closed)
         throw new IllegalArgumentException("Transport is closed: '$transport'")

      GPathResult xml = new XmlSlurper(false ,false).parseText(transport.xml)
      toRemove.each { transportElement ->
         List<NodeChild> foundExportedItems = xml.'**'.findAll{
               it.name() == 'exportedItem' &&
               it.parent().@exporterType == transportElement.type &&
               it.itemProperty.find{it.@name == 'key'}?.text() == transportElement.key               
            }
         if (!foundExportedItems)
            throw new IllegalArgumentException('No element found')
         if (foundExportedItems.size() > 1)
            throw new IllegalStateException('More than one element found!')
         xml = removeElement(foundExportedItems.get(0), xml)
      }
      transport.xml = XmlUtil.serialize(xml)
      transportTreeServiceProvider.get().merge transport
   }

   GPathResult removeElement(NodeChild nodeToRemove, GPathResult xml) {
      String currentNodeId = nodeToRemove.'@xml:id'
      String parentId = nodeToRemove.itemProperty.find{ it.'@name' == 'parent' }?.'@referenceId' as String
      
      Closure findParent = {gpath -> gpath.'**'.find {it.name() == 'exportedItem' && it.'@xml:id' == parentId}}
      Closure findChildrenNode = {parent -> parent.itemProperty.find{ it.'@name' == 'children'}}
      
      /* remove node to remove*/ 
      nodeToRemove.replaceNode {} 
      
      /* if parent == null we assume that we are at the root and no further references exist 
       * we also assume that a parent is always a folder type*/
      def parentNode = findParent(xml)
      if (parentNode == null) {
         nodeToRemove.parent().replaceNode {} // delete exporter
         return xml 
      }

      /* remove the references in "children" itemProperty */
      def childrenNodeOfParent = findChildrenNode(parentNode)
      childrenNodeOfParent.collectionValue.findAll{it.'@referenceId' == currentNodeId}*.replaceNode { } 

      /* we need to reinitialize the gpath or childrenNodeOfParent.collectionValue.size() will remain the same after replaceNode*/
      def xml_new = new XmlSlurper(false ,false).parseText(XmlUtil.serialize(xml))
      childrenNodeOfParent = findChildrenNode(findParent(xml_new)) 
      /* if a parent(-folder) contains no further child references we recursively remove the parent */
      if(childrenNodeOfParent.collectionValue.size() < 1)
         return removeElement(parentNode, xml) 
      return xml_new
   }

   private boolean isKeyNull(AbstractNode<?> element) {
      try {
         String key = (String) PropertyUtils.getProperty(element, "key");
         if (StringUtils.isNullOrEmpty(key)) {
            return true;
         }
      } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
         return true;
      }
      return false;
   }

   @Override
   public boolean isTransportable(AbstractNode<?> node) {
      return node instanceof AbstractReportManagerNode ||
            node instanceof AbstractDatasinkManagerNode ||
            node instanceof AbstractDatasourceManagerNode ||
            node instanceof AbstractFileServerNode
   }

   @Override
   public Map<String, Object> getMetadata(Transport transport) {
      Map<String, Object> metadata = new LinkedHashMap<>();
      metadata.put("Key", transport.getKey());
      metadata.put("Closed", transport.isClosed());
      metadata.put("Description", transport.getDescription());
      metadata.put("Created on", transport.getCreatedOnStr());
      metadata.put("Created by (first name)", transport.getCreatorFirstname());
      metadata.put("Created by (last name)", transport.getCreatorLastname());
      metadata.put("Created by (username)", transport.getCreatorUsername());
      metadata.put("Created by (email)", transport.getCreatorEmail());
      metadata.put("Server ID", transport.getServerId());
      metadata.put("ReportServer version", transport.getRsVersion());
      metadata.put("Schema version", transport.getRsSchemaVersion());
      return metadata;
   }

   @Override
   public Map<String, List<Map<String,String>>> getElements(Transport transport) {
      Map<String, List<Map<String,String>>> results = new HashMap<>();
      final ExportDataProvider exportedDataProvider = new ExportDataProviderImpl(
            transport.getXml().getBytes(Charsets.UTF_8));
      final ExportDataAnalyzerService analyzerService = exportDataAnalyzerServiceProvider.get();
      try {
         Collection<Class<? extends Exporter>> dataClasses = analyzerService.getExporterClasses(exportedDataProvider);
         Iterator<Class<? extends Exporter>> dataClassesIt = dataClasses.iterator();

         while (dataClassesIt.hasNext()) {
            Class<? extends Exporter> dataClass = dataClassesIt.next();

            List<Map<String, String>> elements = new ArrayList<>();
            List<ExportedItem> exportedItems = analyzerService.getExportedItemsFor(exportedDataProvider, dataClass);
            exportedItems
                  .findAll{!it.type.simpleName.endsWith("Folder")}
                  .each{
                     Map<String, String> itemProps = new LinkedHashMap<>();
                     ItemProperty keyProp = it.getPropertyByName("key");
                     if (null != keyProp) {
                        itemProps.put("Key", StringEscapeUtils.unescapeXml(keyProp.getElement().getValue()));
                     } else {
                        itemProps.put("Key", null);
                     }

                     ItemProperty nameProp = it.getPropertyByName("name");
                     if (null != nameProp) {
                        itemProps.put("Name", StringEscapeUtils.unescapeXml(nameProp.getElement().getValue()));
                     } else {
                        itemProps.put("Name", null);
                     }

                     elements.add(itemProps);
                  };
            results.put(dataClass.getSimpleName(), elements);
         }
      } catch (ClassNotFoundException e) {
         throw new IllegalArgumentException(e);
      }
      return results;
   }

   @Override
   public void close(Transport transport) {
      transport.closed = true
   }

   @Override
   public String addReferenceKeys(String transportXml) {
      def xml = new XmlSlurper(false, false).parseText(transportXml)

      List<TreeDBManager> treeManagers = getTransportableTreeManagers()
      /* get all properties with referenceIds */
      def properties = xml.'**'.findAll{it.name() == 'itemProperty' && it.@referenceId != null && it.@referenceId != ""}
      /* modify */
      properties.each {
         String id = it.@referenceId.text().split("-")[-1]
         // we assume that the first return is our object as Id is unique globally
         AbstractNode referencedObject = treeManagers.collect { it.getNodeById(id as Long) }.find { it != null }
         String key = referencedObject?.respondsTo("getKey") ? referencedObject.key : null
         if(key != null)
            it.@referenceKey = key
      }
      return XmlUtil.serialize(xml)
   }

   // all properties with referenceId and referenceKey
   @Override
   public Closure getReferencesClosure() {
      return { it.name() == 'itemProperty' && it.@referenceId.text() && it.@referenceKey.text() }
   }

   @Override
   public List<String> getRemoteReferenceKeys(String transportXml) {
      def xml = new XmlSlurper(false, false).parseText(transportXml)
      
      return xml.'**'
            .findAll(getReferencesClosure())
            *.@referenceKey
            *.toString()
   }

   public ImportResult rpull(RemoteRsRestServer remoteServer, String destinationPath) {
      if (!remoteServer.url)
         throw new TerminalException("Remote RS REST server has no REST URL");
      if (!remoteServer.url.trim().startsWith('http://')
            && !remoteServer.url.trim().startsWith('https://'))
         throw new TerminalException("URL contains no protocol: '$remoteServer.url''")

      def targetNode = terminalServiceProvider.get().getObjectByQuery(destinationPath)
      
      if (targetNode && !(targetNode instanceof TransportFolder))
         throw new IllegalArgumentException("Node is not a transport folder: '$destinationPath'")
         
      if (!targetNode) {
         def transportRoot = transportTreeServiceProvider.get().roots[0] 
         int startIndex = destinationPath.startsWith('/')? 2 : 1    
         List folderPathNames = destinationPath.split('/')[startIndex..-1]
         AbstractTransportManagerNode parent, child
         parent = transportRoot
         while(!folderPathNames.empty) {
            child = null
            def currentFolderName = folderPathNames.remove(0)
            child = parent.getChildren().findAll{it instanceof TransportFolder}.find {it.name == currentFolderName}
            if(child == null) {
               child = new TransportFolder(currentFolderName)
               parent.addChild(child)
               transportTreeServiceProvider.get().persist child
            }
            parent = child
         }
         transportTreeServiceProvider.get().merge transportRoot
      }
         
      return remoteEntityImporterServiceProvider.get().importRemoteEntities(remoteServer, '/transports',
            destinationPath, false, true);
   }

   @Override
   public ImportResult rpull() {
      Configuration config = configServiceProvider.get().getConfigFailsafe(TransportService.CONFIG_FILE)
      String remoteServerKey = config.getString('import.remote', 'REMOTE_SERVER')
      String target = config.getString('import.target', '/transports/import')
      def remoteServerDef = remoteServerTreeServiceProvider.get()
            .getRemoteServerByKey(remoteServerKey)
      if (!remoteServerDef)
         throw new IllegalArgumentException("Remote server with key '$remoteServerKey' was not found")
      if (!(remoteServerDef instanceof RemoteRsRestServer))
         throw new IllegalArgumentException('Remote server is not a REST server')

      return rpull((RemoteRsRestServer) remoteServerDef, target)
   }

   @Override
   @SecurityChecked(
      argumentVerification = [
         @ArgumentVerification(
            name = "transport",
            verify = @RightsVerification(
               rights = [ Execute.class ]
            )
         )
      ]
   )
   public Map<String, PreconditionResult> analyzeApplyPreconditions(@Named("transport") Transport transport) {
      return hookHandlerProvider.get().getHookers(ApplyPreconditionHook)
         .collectEntries { [it.key, it.analyze(transport)] }
   }

   @Override
   public boolean isAppliable(Transport transport) {
      return !analyzeApplyPreconditions(transport).find { it.value.result == PreconditionResult.Result.ERROR }
   }

   @Override
   public List<TreeDBManager> getTransportableTreeManagers() {
      return [
         fileServerServiceProvider.get(),
         datasinkServiceProvider.get(),
         datasourceServiceProvider.get(),
         reportServiceProvider.get()
      ]
   }
}
