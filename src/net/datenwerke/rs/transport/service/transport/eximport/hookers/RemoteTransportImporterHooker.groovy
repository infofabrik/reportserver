package net.datenwerke.rs.transport.service.transport.eximport.hookers

import static net.datenwerke.rs.base.ext.service.RemoteEntityImporterServiceImpl.handleError

import java.text.SimpleDateFormat

import javax.inject.Inject

import org.apache.commons.text.StringEscapeUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.google.inject.Provider

import groovy.xml.XmlSlurper
import groovy.xml.XmlUtil
import net.datenwerke.eximport.ExportDataAnalyzerServiceImpl
import net.datenwerke.eximport.ExportDataProviderImpl
import net.datenwerke.eximport.ImportService
import net.datenwerke.eximport.im.ImportConfig
import net.datenwerke.eximport.im.ImportResult
import net.datenwerke.rs.base.ext.service.RemoteEntityImports
import net.datenwerke.rs.base.ext.service.hooks.RemoteEntityImporterHook
import net.datenwerke.rs.transport.service.transport.TransportService
import net.datenwerke.rs.transport.service.transport.TransportTreeService
import net.datenwerke.rs.transport.service.transport.entities.AbstractTransportManagerNode
import net.datenwerke.rs.transport.service.transport.entities.Transport
import net.datenwerke.rs.transport.service.transport.entities.TransportFolder
import net.datenwerke.rs.transport.service.transport.eximport.TransportManagerExporter
import net.datenwerke.security.service.authenticator.AuthenticatorService
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImporterConfig
import net.datenwerke.treedb.service.treedb.AbstractNode

class RemoteTransportImporterHooker implements RemoteEntityImporterHook {

   private final Provider<ExportDataAnalyzerServiceImpl> analyzerServiceProvider
   private final Provider<ImportService> importServiceProvider
   private final Provider<TransportTreeService> transportTreeServiceProvider
   private final Provider<AuthenticatorService> authenticatorServiceProvider
   
   private final Logger logger = LoggerFactory.getLogger(getClass().name)
   
   @Inject
   public RemoteTransportImporterHooker(
      Provider<ExportDataAnalyzerServiceImpl> analyzerServiceProvider,
      Provider<ImportService> importServiceProvider,
      Provider<TransportTreeService> transportTreeServiceProvider,
      Provider<AuthenticatorService> authenticatorServiceProvider
      ) {
      this.analyzerServiceProvider = analyzerServiceProvider
      this.importServiceProvider = importServiceProvider
      this.transportTreeServiceProvider = transportTreeServiceProvider
      this.authenticatorServiceProvider = authenticatorServiceProvider
   }
   
   @Override
   public boolean consumes(RemoteEntityImports importType) {
      return importType == RemoteEntityImports.TRANSPORTS
   }
   
   @Override
   public ImportResult importRemoteEntity(ImportConfig config, AbstractNode targetNode, String requestedRemoteEntity, 
      String exportXml) {
      return doImportRemoteEntity(config, targetNode, false, true, [:], exportXml)
   }

   @Override
   public Map<String, String> checkImportRemoteEntity(ImportConfig config, AbstractNode targetNode,
         Map<String, String> previousCheckResults, String requestedRemoteEntity) {
      return doImportRemoteEntity(config, targetNode, true, false, previousCheckResults, null)
   }

   private doImportRemoteEntity(ImportConfig config, AbstractNode targetNode, boolean check, boolean skipExistingKeys, 
      Map<String, String> results, String exportXml) {
      if (!(targetNode instanceof AbstractTransportManagerNode)) {
         handleError(check, "Node is not a transport folder: '$targetNode'", results, IllegalArgumentException)
         if (check)
            return results
      }
         
      def analyzerService = analyzerServiceProvider.get()
      def importService = importServiceProvider.get()
      def treeConfig = new TreeNodeImporterConfig()
      config.addSpecificImporterConfigs treeConfig
      
      /* check that keys do not exist in local RS */
      def transportTreeService = transportTreeServiceProvider.get()
      def toSkip = []
      analyzerService.getExportedItemsFor(config.exportDataProvider, TransportManagerExporter)
      .each {
         def keyProp = it.getPropertyByName('key')
         def createdOnProp = it.getPropertyByName('createdOn')
         if(keyProp){
            def key = StringEscapeUtils.unescapeXml(keyProp.element.value)
            def existingTransport = transportTreeService.getTransportByKey(key)
            if(existingTransport){
               if (skipExistingKeys)
                  toSkip << it
               else {
                  handleError(check,
                        "A transport with the key '$key' already exists in the local system: '$existingTransport'",
                        results, IllegalStateException)
               }
            }
         } else {
            def type = analyzerService.getItemTypeAsClass(it.element)
            if (type == Transport)
               handleError(check,
                     "The transport with creation date '$createdOnProp' has no key.", results, IllegalStateException)
         }
      }

      if (toSkip) {
         def xml = new XmlSlurper(false, false).parseText(exportXml)
         // remove items from xml which for which we find a key locally and create new config
         toSkip.each{ skipElement -> 
            xml.'**'
               .find{it.name() == 'exportedItem' && it['@xml:id'] == skipElement.id}
               .replaceNode { } // we remove the node
         }
         exportXml = XmlUtil.serialize(xml)
         config = new ImportConfig(new ExportDataProviderImpl(exportXml.bytes))
      }
      
      def exportRoot = analyzerService.getRoot(config.exportDataProvider, TransportManagerExporter)
      if(exportRoot) {
         def exportRootType = exportRoot.type
         if (exportRootType != TransportFolder) // in case we only exported one item
            exportRoot = targetNode
   
         importService.configureParents config, exportRoot.id as String, targetNode, TransportManagerExporter
      } // else nothing to import
      
      /* complete import */
      if (check)
         return results
      else {
         ImportResult importResult = importService.importData(config)
         // set status, imported by and imported date
         importResult.importedObjects
            .findAll { k, v -> v instanceof Transport }
            .each { k, transport ->  
               transport.status = TransportService.Status.IMPORTED
               transport.importedOn = new Date()
               transport.importedBy = authenticatorServiceProvider.get().currentUser
               transportTreeService.merge transport
            }
         return importResult
      }
   }
   
}
