package net.datenwerke.rs.base.ext.service.datasinkmanager.eximport.hookers

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
import net.datenwerke.rs.base.ext.service.datasinkmanager.eximport.DatasinkManagerExporter
import net.datenwerke.rs.base.ext.service.hooks.RemoteEntityImporterHook
import net.datenwerke.rs.core.service.datasinkmanager.DatasinkTreeService
import net.datenwerke.rs.core.service.datasinkmanager.entities.AbstractDatasinkManagerNode
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkFolder
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImporterConfig
import net.datenwerke.treedb.service.treedb.AbstractNode

class RemoteDatasinkImporterHooker implements RemoteEntityImporterHook {

   private final Provider<ExportDataAnalyzerServiceImpl> analyzerServiceProvider
   private final Provider<ImportService> importServiceProvider
   private final Provider<DatasinkTreeService> datasinkServiceProvider
   
   private final Logger logger = LoggerFactory.getLogger(getClass().name)
   
   @Inject
   public RemoteDatasinkImporterHooker(
      Provider<ExportDataAnalyzerServiceImpl> analyzerServiceProvider,
      Provider<ImportService> importServiceProvider,
      Provider<DatasinkTreeService> datasinkTreeServiceProvider
      ) {
      this.analyzerServiceProvider = analyzerServiceProvider
      this.importServiceProvider = importServiceProvider
      this.datasinkServiceProvider = datasinkTreeServiceProvider
   }
   
   @Override
   public boolean consumes(RemoteEntityImports importType) {
      return importType == RemoteEntityImports.DATASINKS
   }
   
   @Override
   public ImportResult importRemoteEntity(ImportConfig config, AbstractNode targetNode, String requestedRemoteEntity,
      String exportXml) {
      return doImportRemoteEntity(config, targetNode, false, [:])
   }

   @Override
   public Map<String, String> checkImportRemoteEntity(ImportConfig config, AbstractNode targetNode,
         Map<String, String> previousCheckResults, String requestedRemoteEntity) {
      return doImportRemoteEntity(config, targetNode, true, previousCheckResults)
   }

   private doImportRemoteEntity(ImportConfig config, AbstractNode targetNode, boolean check, Map<String, String> results) {
      if (!(targetNode instanceof AbstractDatasinkManagerNode)) {
         handleError(check, "Node is not a datasink folder: '$targetNode'", results, IllegalArgumentException)
         if (check)
            return results
      }
         
      def analyzerService = analyzerServiceProvider.get()
      def importService = importServiceProvider.get()
      def treeConfig = new TreeNodeImporterConfig()
      config.addSpecificImporterConfigs treeConfig

      def exportRoot = analyzerService.getRoot(config.exportDataProvider, DatasinkManagerExporter)
      if(!exportRoot) {
         handleError(check, 'Could not find root', results, IllegalStateException)
         if (check)
            return results
      }
      def exportRootType = exportRoot.type
      if (exportRootType != DatasinkFolder) // in case we only exported one item
         exportRoot = targetNode
         
      importService.configureParents config, exportRoot.id as String, targetNode, DatasinkManagerExporter
      
      /* one more loop to check that keys do not exist in local RS */
      def datasinkService = datasinkServiceProvider.get()
      analyzerService.getExportedItemsFor(config.exportDataProvider, DatasinkManagerExporter).each {
         def keyProp = it.getPropertyByName('key')
         if(keyProp){
            def key = StringEscapeUtils.unescapeXml(keyProp.element.value)
            def existingDatasink = datasinkService.getDatasinkByKey(key)
            if(existingDatasink){
               handleError(check,
                  "A datasink with the key '$key' already exists in the local system: '$existingDatasink'", 
                     results, IllegalStateException)
            }
         } else {
            def nameProp = it.getPropertyByName('name')
            if (!nameProp)
               handleError(check,
                  "The remote datasink has no name. ID: ${analyzerService.getItemId(it.element)}", results, IllegalStateException)
            def remoteName = StringEscapeUtils.unescapeXml(nameProp?.element?.value)
            def type = analyzerService.getItemTypeAsClass(it.element)
            if (type != DatasinkFolder)
               handleError(check,
                  "The remote datasink with name '$remoteName' has no key.", results, IllegalStateException)
         }
      }

      /* complete import */
      if (check)
         return results
      else
         return importService.importData(config)
   }
   
}
