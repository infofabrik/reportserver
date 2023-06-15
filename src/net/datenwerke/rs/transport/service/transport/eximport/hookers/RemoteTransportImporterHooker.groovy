package net.datenwerke.rs.transport.service.transport.eximport.hookers

import static net.datenwerke.rs.base.ext.service.RemoteEntityImporterServiceImpl.handleError

import javax.inject.Inject

import org.apache.commons.text.StringEscapeUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.google.inject.Provider

import net.datenwerke.eximport.ExportDataAnalyzerServiceImpl
import net.datenwerke.eximport.ImportService
import net.datenwerke.eximport.im.ImportConfig
import net.datenwerke.eximport.im.ImportResult
import net.datenwerke.rs.base.ext.service.RemoteEntityImports
import net.datenwerke.rs.base.ext.service.hooks.RemoteEntityImporterHook
import net.datenwerke.rs.transport.service.transport.TransportTreeService
import net.datenwerke.rs.transport.service.transport.entities.AbstractTransportManagerNode
import net.datenwerke.rs.transport.service.transport.entities.Transport
import net.datenwerke.rs.transport.service.transport.entities.TransportFolder
import net.datenwerke.rs.transport.service.transport.eximport.TransportManagerExporter
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImporterConfig
import net.datenwerke.treedb.service.treedb.AbstractNode

class RemoteTransportImporterHooker implements RemoteEntityImporterHook {

   private final Provider<ExportDataAnalyzerServiceImpl> analyzerServiceProvider
   private final Provider<ImportService> importServiceProvider
   private final Provider<TransportTreeService> transportServiceProvider
   
   private final Logger logger = LoggerFactory.getLogger(getClass().name)
   
   @Inject
   public RemoteTransportImporterHooker(
      Provider<ExportDataAnalyzerServiceImpl> analyzerServiceProvider,
      Provider<ImportService> importServiceProvider,
      Provider<TransportTreeService> transportServiceProvider
      ) {
      this.analyzerServiceProvider = analyzerServiceProvider
      this.importServiceProvider = importServiceProvider
      this.transportServiceProvider = transportServiceProvider
   }
   
   @Override
   public boolean consumes(RemoteEntityImports importType) {
      return importType == RemoteEntityImports.TRANSPORTS
   }
   
   @Override
   public ImportResult importRemoteEntity(ImportConfig config, AbstractNode targetNode, String requestedRemoteEntity) {
      return doImportRemoteEntity(config, targetNode, false, [:])
   }

   @Override
   public Map<String, String> checkImportRemoteEntity(ImportConfig config, AbstractNode targetNode,
         Map<String, String> previousCheckResults, String requestedRemoteEntity) {
      return doImportRemoteEntity(config, targetNode, true, previousCheckResults)
   }

   private doImportRemoteEntity(ImportConfig config, AbstractNode targetNode, boolean check, Map<String, String> results) {
      if (!(targetNode instanceof AbstractTransportManagerNode)) {
         handleError(check, "Node is not a transport folder: '$targetNode'", results, IllegalArgumentException)
         if (check)
            return results
      }
         
      def analyzerService = analyzerServiceProvider.get()
      def importService = importServiceProvider.get()
      def treeConfig = new TreeNodeImporterConfig()
      config.addSpecificImporterConfigs treeConfig

      def exportRoot = analyzerService.getRoot(config.exportDataProvider, TransportManagerExporter)
      if(!exportRoot) {
         handleError(check, 'Could not find root', results, IllegalStateException)
         if (check)
            return results
      }
      def exportRootType = exportRoot.type
      if (exportRootType != TransportFolder) // in case we only exported one item
         exportRoot = targetNode

      importService.configureParents config, exportRoot.id as String, targetNode, TransportManagerExporter
      
      /* one more loop to check that keys do not exist in local RS */
      def transportService = transportServiceProvider.get()
      analyzerService.getExportedItemsFor(config.exportDataProvider, TransportManagerExporter)
      .each {
         def keyProp = it.getPropertyByName('key')
         def createdOnProp = it.getPropertyByName('createdOn')
         if(keyProp){
            def key = StringEscapeUtils.unescapeXml(keyProp.element.value)
            def existingTransport = transportService.getTransportByKey(key)
            if(existingTransport){
               handleError(check,
                  "A transport with the key '$key' already exists in the local system: '$existingTransport'",
                     results, IllegalStateException)
            }
         } else {
            def type = analyzerService.getItemTypeAsClass(it.element)
            if (type == Transport)
               handleError(check,
                     "The transport with creation date '$createdOnProp' has no key.", results, IllegalStateException)
         }
      }

      /* complete import */
      if (check)
         return results
      else
         return importService.importData(config)
   }
   
}
