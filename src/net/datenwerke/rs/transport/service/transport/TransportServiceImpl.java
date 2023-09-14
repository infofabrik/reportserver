package net.datenwerke.rs.transport.service.transport;

import static net.datenwerke.eximport.ExImportHelperService.DOCUMENT_DATA_ELEMENT;
import static net.datenwerke.eximport.ExImportHelperService.DOCUMENT_HEAD_DATE_ELEMENT;
import static net.datenwerke.eximport.ExImportHelperService.DOCUMENT_HEAD_DESCRIPTION_ELEMENT;
import static net.datenwerke.eximport.ExImportHelperService.DOCUMENT_HEAD_ELEMENT;
import static net.datenwerke.eximport.ExImportHelperService.DOCUMENT_HEAD_NAME_ELEMENT;
import static net.datenwerke.eximport.ExImportHelperService.DOCUMENT_HEAD_VERSION_ELEMENT;
import static net.datenwerke.eximport.ExImportHelperService.DOCUMENT_ROOT_ELEMENT;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Provider;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.text.StringEscapeUtils;

import com.google.common.base.Charsets;

import net.datenwerke.eximport.ExportDataAnalyzerService;
import net.datenwerke.eximport.ExportDataProvider;
import net.datenwerke.eximport.ExportDataProviderImpl;
import net.datenwerke.eximport.ex.ExportFileProcessingHelper;
import net.datenwerke.eximport.ex.Exporter;
import net.datenwerke.eximport.obj.ExportedItem;
import net.datenwerke.eximport.obj.ItemProperty;
import net.datenwerke.rs.EnvironmentValidatorHelperService;
import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.GeneralInfoService;
import net.datenwerke.rs.core.service.datasinkmanager.entities.AbstractDatasinkManagerNode;
import net.datenwerke.rs.core.service.datasourcemanager.entities.AbstractDatasourceManagerNode;
import net.datenwerke.rs.core.service.reportmanager.entities.AbstractReportManagerNode;
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.rs.json.service.json.JsonService;
import net.datenwerke.rs.license.service.LicenseService;
import net.datenwerke.rs.transport.service.transport.entities.Transport;
import net.datenwerke.rs.transport.service.transport.entities.TransportFolder;
import net.datenwerke.rs.utils.crypto.HashUtil;
import net.datenwerke.rs.utils.misc.DateUtils;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.usermanager.entities.User;
import net.datenwerke.treedb.ext.service.eximport.helper.TreeNodeExportHelperService;
import net.datenwerke.treedb.service.treedb.AbstractNode;
import nu.xom.Element;
import nu.xom.Elements;

public class TransportServiceImpl implements TransportService {

   private final Provider<AuthenticatorService> authenticatorServiceProvider;
   private final Provider<GeneralInfoService> generalInfoServiceProvider;
   private final Provider<LicenseService> licenseServiceProvider;
   private final Provider<EnvironmentValidatorHelperService> envServiceProvider;
   private final Provider<JsonService> jsonServiceProvider;
   private final Provider<HashUtil> hashUtilProvider;
   private final Provider<TransportTreeService> transportTreeServiceProvider;
   private final Provider<TreeNodeExportHelperService> treeNodeExportHelperServiceProvider;
   private final Provider<ExportDataAnalyzerService> exportDataAnalyzerServiceProvider;
   private final Provider<ExportFileProcessingHelper> exportFileProcessingHelperProvider;
   
   private final static String INITIAL_PROP_USER_USERNAME = "USER_USERNAME";
   private final static String INITIAL_PROP_USER_FIRSTNAME = "USER_FIRSTNAME";
   private final static String INITIAL_PROP_USER_LASTNAME = "USER_LASTNAME";
   private final static String INITIAL_PROP_USER_EMAIL = "USER_EMAIL";
   private final static String INITIAL_PROP_SERVER_ID = "SERVER_ID";
   private final static String INITIAL_PROP_RS_VERSION = "RS_VERSION";
   private final static String INITIAL_PROP_SCHEMA_VERSION = "SCHEMA_VERSION";
   private final static String INITIAL_PROP_KEY = "KEY";
   private final static String INITIAL_PROP_XML = "XML";
   
   @Inject
   public TransportServiceImpl(
         Provider<AuthenticatorService> authenticatorServiceProvider,
         Provider<GeneralInfoService> generalInfoServiceProvider,
         Provider<LicenseService> licenseServiceProvider,
         Provider<EnvironmentValidatorHelperService> envServiceProvider,
         Provider<JsonService> jsonServiceProvider,
         Provider<HashUtil> hashUtilProvider,
         Provider<TransportTreeService> transportTreeServiceProvider,
         Provider<TreeNodeExportHelperService> treeNodeExportHelperServiceProvider,
         Provider<ExportDataAnalyzerService> exportDataAnalyzerServiceProvider,
         Provider<ExportFileProcessingHelper> exportFileProcessHelperProvider
         ) {
      this.authenticatorServiceProvider = authenticatorServiceProvider;
      this.generalInfoServiceProvider = generalInfoServiceProvider;
      this.licenseServiceProvider = licenseServiceProvider;
      this.envServiceProvider = envServiceProvider;
      this.jsonServiceProvider = jsonServiceProvider;
      this.hashUtilProvider = hashUtilProvider;
      this.transportTreeServiceProvider = transportTreeServiceProvider;
      this.treeNodeExportHelperServiceProvider = treeNodeExportHelperServiceProvider;
      this.exportDataAnalyzerServiceProvider = exportDataAnalyzerServiceProvider;
      this.exportFileProcessingHelperProvider = exportFileProcessHelperProvider;
   }
         
   
   @Override
   public String createTransport(String description, TransportFolder parent) {
      Map<String,String> asMap = createInitialProperties();
      
      Transport transport = new Transport();
      setInitialProperties(transport, asMap, true);
      transport.setDescription(description);
      transport.setParent(parent);
      
      transportTreeServiceProvider.get().persist(transport);
      return asMap.get(INITIAL_PROP_KEY);
   }


   @Override
   public void setInitialProperties(Transport transport, Map<String,String> initialProperties, boolean setXml) {
      transport.setServerId(initialProperties.get(INITIAL_PROP_SERVER_ID));
      transport.setRsVersion(initialProperties.get(INITIAL_PROP_RS_VERSION));
      transport.setRsSchemaVersion(initialProperties.get(INITIAL_PROP_SCHEMA_VERSION));
      transport.setCreatorUsername(initialProperties.get(INITIAL_PROP_USER_USERNAME));
      transport.setCreatorFirstname(initialProperties.get(INITIAL_PROP_USER_FIRSTNAME));
      transport.setCreatorLastname(initialProperties.get(INITIAL_PROP_USER_LASTNAME));
      transport.setCreatorEmail(initialProperties.get(INITIAL_PROP_USER_EMAIL));
      transport.setKey(initialProperties.get(INITIAL_PROP_KEY));
      if (setXml)
         transport.setXml(initialProperties.get(INITIAL_PROP_XML));
   }
   
   @Override
   public Map<String,String> createInitialProperties() {
      User user = authenticatorServiceProvider.get().getCurrentUser();
      String serverId = licenseServiceProvider.get().getServerId();
      String rsVersion = generalInfoServiceProvider.get().getRsVersion();
      String schemaVersion = "unknown";
      try {
         schemaVersion = envServiceProvider.get().getSchemaVersion();
      } catch (SQLException e) {
         schemaVersion = ExceptionUtils.getRootCauseMessage(e);
      }
      String username = user.getUsername();
      String firstname = user.getFirstname();
      String lastname = user.getLastname();
      String email = user.getEmail();
      
      Map<String,String> asMap = new HashMap<>();
      // now is important for getting different hash values
      asMap.put("now", DateUtils.formatCurrentDate()); 
      asMap.put(INITIAL_PROP_SERVER_ID, serverId);
      asMap.put(INITIAL_PROP_RS_VERSION, rsVersion);
      asMap.put(INITIAL_PROP_SCHEMA_VERSION, schemaVersion);
      asMap.put(INITIAL_PROP_USER_USERNAME, username);
      asMap.put(INITIAL_PROP_USER_FIRSTNAME, firstname);
      asMap.put(INITIAL_PROP_USER_LASTNAME, lastname);
      asMap.put(INITIAL_PROP_USER_EMAIL, email);
      asMap.put(INITIAL_PROP_XML, "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
            + "<" + DOCUMENT_ROOT_ELEMENT + " xmlns=\"http://reportserver.datenwerke.net/eximport\">"
            + "<" + DOCUMENT_HEAD_ELEMENT + ">"
            + "<" + DOCUMENT_HEAD_NAME_ELEMENT + ">Transport</" + DOCUMENT_HEAD_NAME_ELEMENT + ">"
            + "<" + DOCUMENT_HEAD_DESCRIPTION_ELEMENT + "/>"
            + "<" + DOCUMENT_HEAD_DATE_ELEMENT + ">" + new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss Z").format(new Date())
            + "</" + DOCUMENT_HEAD_DATE_ELEMENT + ">"
            + "<" + DOCUMENT_HEAD_VERSION_ELEMENT + "/>"
            + "</" + DOCUMENT_HEAD_ELEMENT + ">"
            + "<" + DOCUMENT_DATA_ELEMENT + ">"
            + "</" + DOCUMENT_DATA_ELEMENT + ">"
            + "</"+ DOCUMENT_ROOT_ELEMENT + ">\n"
            + "");
      
      String asJson = jsonServiceProvider.get().map2Json(asMap);
      String hash = hashUtilProvider.get().sha1(asJson);
      asMap.put(INITIAL_PROP_KEY, hash);
      return asMap;
   }


   @Override
   public void addElement(Transport transport, AbstractNode<?> element, boolean includeVariants) {
      if (!isTransportable(element))
         throw new IllegalArgumentException("Element is not transportable: '" + element + "'");
      if (transport.isClosed())
         throw new IllegalArgumentException("Transport is closed: '" + transport + "'");
      
      TreeNodeExportHelperService exportService = treeNodeExportHelperServiceProvider.get();
      ExportDataAnalyzerService analyzerService = exportDataAnalyzerServiceProvider.get();
      ExportFileProcessingHelper xmlHelper = exportFileProcessingHelperProvider.get();

      String currentXml = transport.getXml();

      xmlHelper.setXml(currentXml);
      
      final String exportedXml = exportService.export(element, includeVariants, "Transport", true);
      ExportDataProvider exportedDataProvider = new ExportDataProviderImpl(exportedXml.getBytes(Charsets.UTF_8));
      ExportDataProvider currentDataProvider = new ExportDataProviderImpl(currentXml.getBytes(Charsets.UTF_8));
      
      try {
      
         Elements exportedExporterData = analyzerService.getExporterElements(exportedDataProvider);
         Collection<Class<? extends Exporter>> currentExporterDataClasses = analyzerService.getExporterClasses(currentDataProvider);
         
         
         Iterator<Element> exportedExporterDataIt = exportedExporterData.iterator();
         while (exportedExporterDataIt.hasNext()) {
            Element exportedExporterDataElement = exportedExporterDataIt.next();
            Class<? extends Exporter> exportedClazz = analyzerService.getExporterClass(exportedExporterDataElement);
            if (!currentExporterDataClasses.contains(exportedClazz)) {
               // exporterData with this class does not exist, we can add the complete exporterData block
               System.out.println("Adding exporter class to current exporters: " + exportedClazz);
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
                     System.out.println("Element already contained. We replace it: " + exportedItemId);
                     xmlHelper.replaceExportedItem(exportedItemId, exportedClazz.getName(), exportedItem.toXML());
                  } else {
                     // the item does not exist. We can just add it
                     System.out.println("Element not contained. We add it");
                     xmlHelper.addExportedItem(exportedClazz.getName(), exportedItem.toXML());
                  }
               }
               
               System.out.println("Elements: " + currentItems.toString());
            }
            System.out.println("Export found: " + exportedClazz);
         }
         
         transport.setXml(xmlHelper.serialize());
         transportTreeServiceProvider.get().merge(transport);
      } catch (ClassNotFoundException e) {
         throw new IllegalArgumentException(e);
      }
   }


   @Override
   public boolean isTransportable(AbstractNode<?> node) {
      return node instanceof AbstractReportManagerNode
            || node instanceof AbstractDatasinkManagerNode
            || node instanceof AbstractDatasourceManagerNode
            || node instanceof AbstractFileServerNode;
   }


   @Override
   public Map<String, Object> getMetadata(Transport transport) {
      Map<String, Object> metadata = new LinkedHashMap<>();
      metadata.put("Key", transport.getKey());
      metadata.put("Closed", transport.isClosed());
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
               .stream()
               .filter(exportedItem -> !exportedItem.getType().getSimpleName().endsWith("Folder"))
               .forEach(exportedItem -> {
                  Map<String, String> itemProps = new LinkedHashMap<>();
                  ItemProperty keyProp = exportedItem.getPropertyByName("key");
                  if (null != keyProp) {
                     itemProps.put("Key", StringEscapeUtils.unescapeXml(keyProp.getElement().getValue()));
                  } else {
                     itemProps.put("Key", null);
                  }
   
                  ItemProperty nameProp = exportedItem.getPropertyByName("name");
                  if (null != nameProp) {
                     itemProps.put("Name", StringEscapeUtils.unescapeXml(nameProp.getElement().getValue()));
                  } else {
                     itemProps.put("Name", null);
                  }
   
                  elements.add(itemProps);
               });
            results.put(dataClass.getSimpleName(), elements);
         }
         
      } catch (ClassNotFoundException e) {
         throw new IllegalArgumentException(e);
      }
      return results;
   }

}
