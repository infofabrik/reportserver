package net.datenwerke.rs.remoteserver.service.remoteservermanager.eximport.hookers

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
import net.datenwerke.rs.remoteserver.service.remoteservermanager.RemoteServerTreeService
import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.AbstractRemoteServerManagerNode
import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.RemoteServerFolder
import net.datenwerke.rs.remoteserver.service.remoteservermanager.eximport.RemoteServerManagerExporter
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImporterConfig
import net.datenwerke.treedb.service.treedb.AbstractNode

class RemoteRemoteServerImporterHooker implements RemoteEntityImporterHook {

   private final Provider<ExportDataAnalyzerServiceImpl> analyzerServiceProvider
   private final Provider<ImportService> importServiceProvider
   private final Provider<RemoteServerTreeService> remoteServerServiceProvider
   
   private final Logger logger = LoggerFactory.getLogger(getClass().name)
   
   @Inject
   public RemoteRemoteServerImporterHooker(
      Provider<ExportDataAnalyzerServiceImpl> analyzerServiceProvider,
      Provider<ImportService> importServiceProvider,
      Provider<RemoteServerTreeService> remoteServerServiceProvider
      ) {
      this.analyzerServiceProvider = analyzerServiceProvider
      this.importServiceProvider = importServiceProvider
      this.remoteServerServiceProvider = remoteServerServiceProvider
   }
   
   @Override
   public boolean consumes(RemoteEntityImports importType) {
      return importType == RemoteEntityImports.REMOTESERVERS
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
      if (!(targetNode instanceof AbstractRemoteServerManagerNode)) {
         handleError(check, "Node is not a remote server folder: '$targetNode'", results, IllegalArgumentException)
         if (check)
            return results
      }
         
      def analyzerService = analyzerServiceProvider.get()
      def importService = importServiceProvider.get()
      def treeConfig = new TreeNodeImporterConfig()
      config.addSpecificImporterConfigs treeConfig

      def exportRoot = analyzerService.getRoot(config.exportDataProvider, RemoteServerManagerExporter)
      if(!exportRoot) {
         handleError(check, 'Could not find root', results, IllegalStateException)
         if (check)
            return results
      }
      def exportRootType = exportRoot.type
      if (exportRootType != RemoteServerFolder) // in case we only exported one item
         exportRoot = targetNode

      importService.configureParents config, exportRoot.id as String, targetNode, RemoteServerManagerExporter
      
      /* one more loop to check that keys do not exist in local RS */
      def remoteServerService = remoteServerServiceProvider.get()
      analyzerService.getExportedItemsFor(config.exportDataProvider, RemoteServerManagerExporter).each {
         def keyProp = it.getPropertyByName('key')
         if(keyProp){
            def key = StringEscapeUtils.unescapeXml(keyProp.element.value)
            def existingRemoteServer = remoteServerService.getRemoteServerByKey(key)
            if(existingRemoteServer){
               handleError(check,
                  "A remote server with the key '$key' already exists in the local system: '$existingRemoteServer'",
                     results, IllegalStateException)
            }
         } else {
            def nameProp = it.getPropertyByName('name')
            if (!nameProp)
               handleError(check,
                  "The remote server has no name. ID: ${analyzerService.getItemId(it.element)}", results, IllegalStateException)
            def remoteName = StringEscapeUtils.unescapeXml(nameProp?.element?.value)
            def type = analyzerService.getItemTypeAsClass(it.element)
            if (type != RemoteServerFolder)
               handleError(check,
                  "The remote server with name '$remoteName' has no key.", results, IllegalStateException)
         }
      }

      /* complete import */
      if (check)
         return results
      else
         return importService.importData(config)
   }
   
}
