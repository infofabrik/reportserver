package net.datenwerke.rs.base.ext.service.datasourcemanager.eximport.hookers

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
import net.datenwerke.rs.base.ext.service.datasourcemanager.eximport.DatasourceManagerExporter
import net.datenwerke.rs.base.ext.service.hooks.RemoteEntityImporterHook
import net.datenwerke.rs.core.service.datasourcemanager.DatasourceService
import net.datenwerke.rs.core.service.datasourcemanager.entities.AbstractDatasourceManagerNode
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceFolder
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImporterConfig
import net.datenwerke.treedb.service.treedb.AbstractNode

class RemoteDatasourceImporterHooker implements RemoteEntityImporterHook {

   private final Provider<ExportDataAnalyzerServiceImpl> analyzerServiceProvider
   private final Provider<ImportService> importServiceProvider
   private final Provider<DatasourceService> datasourceServiceProvider
   
   private final Logger logger = LoggerFactory.getLogger(getClass().name)
   
   @Inject
   public RemoteDatasourceImporterHooker(
      Provider<ExportDataAnalyzerServiceImpl> analyzerServiceProvider,
      Provider<ImportService> importServiceProvider,
      Provider<DatasourceService> datasourceServiceProvider
      ) {
      this.analyzerServiceProvider = analyzerServiceProvider
      this.importServiceProvider = importServiceProvider
      this.datasourceServiceProvider = datasourceServiceProvider
   }
   
   @Override
   public boolean consumes(RemoteEntityImports importType) {
      return importType == RemoteEntityImports.DATASOURCES
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
      if (!(targetNode instanceof AbstractDatasourceManagerNode)) {
         handleError(check, "Node is not a datasource folder: '$targetNode'", results, IllegalArgumentException)
         if (check)
            return results
      }
         
      def analyzerService = analyzerServiceProvider.get()
      def importService = importServiceProvider.get()
      def treeConfig = new TreeNodeImporterConfig()
      config.addSpecificImporterConfigs treeConfig

      def exportRoot = analyzerService.getRoot(config.exportDataProvider, DatasourceManagerExporter)
      if(!exportRoot) {
         handleError(check, 'Could not find root', results, IllegalStateException)
         if (check)
            return results
      }
      def exportRootType = exportRoot.type
      if (exportRootType != DatasourceFolder) // in case we only exported one item
         exportRoot = targetNode

      importService.configureParents config, exportRoot.id as String, targetNode, DatasourceManagerExporter
      
      /* one more loop to check that keys do not exist in local RS */
      def datasourceService = datasourceServiceProvider.get()
      analyzerService.getExportedItemsFor(config.exportDataProvider, DatasourceManagerExporter).each {
         def keyProp = it.getPropertyByName('key')
         if(keyProp){
            def key = StringEscapeUtils.unescapeXml(keyProp.element.value)
            def existingDatasource = datasourceService.getDatasourceByKey(key)
            if(existingDatasource){
               handleError(check,
                  "A datasource with the key '$key' already exists in the local system: '$existingDatasource'",
                     results, IllegalStateException)
            }
         } else {
            def nameProp = it.getPropertyByName('name')
            if (!nameProp)
               handleError(check,
                  "The remote datasource has no name. ID: ${analyzerService.getItemId(it.element)}", results, IllegalStateException)
            def remoteName = StringEscapeUtils.unescapeXml(nameProp?.element?.value)
            def type = analyzerService.getItemTypeAsClass(it.element)
            if (type != DatasourceFolder)
               handleError(check,
                  "The remote datasource with name '$remoteName' has no key.", results, IllegalStateException)
         }
      }

      /* complete import */
      if (check)
         return results
      else
         return importService.importData(config)
   }
   
}
