package net.datenwerke.rs.fileserver.service.fileserver.eximport.hookers

import static net.datenwerke.rs.base.ext.service.RemoteEntityImporterServiceImpl.handleError

import javax.inject.Inject

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.google.inject.Provider

import net.datenwerke.eximport.ExportDataAnalyzerServiceImpl
import net.datenwerke.eximport.ImportService
import net.datenwerke.eximport.im.ImportConfig
import net.datenwerke.eximport.im.ImportResult
import net.datenwerke.eximport.obj.ReferenceItemProperty
import net.datenwerke.rs.base.ext.service.RemoteEntityImports
import net.datenwerke.rs.base.ext.service.hooks.RemoteEntityImporterHook
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode
import net.datenwerke.rs.fileserver.service.fileserver.eximport.FileServerExporter
import net.datenwerke.security.service.usermanager.UserManagerService
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImportItemConfig
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImporterConfig
import net.datenwerke.treedb.service.treedb.AbstractNode

class RemoteFileImporterHooker implements RemoteEntityImporterHook {

   private final Provider<ExportDataAnalyzerServiceImpl> analyzerServiceProvider
   private final Provider<UserManagerService> userManagerServiceProvider
   private final Provider<ImportService> importServiceProvider
   
   private final Logger logger = LoggerFactory.getLogger(getClass().name)
   
   @Inject
   public RemoteFileImporterHooker(
      Provider<ExportDataAnalyzerServiceImpl> analyzerServiceProvider,
      Provider<UserManagerService> userManagerServiceProvider,
      Provider<ImportService> importServiceProvider
      ) {
      this.analyzerServiceProvider = analyzerServiceProvider
      this.userManagerServiceProvider = userManagerServiceProvider
      this.importServiceProvider = importServiceProvider
   }
   
   @Override
   public boolean consumes(RemoteEntityImports importType) {
      return importType == RemoteEntityImports.FILESERVER
   }
   
   @Override
   public ImportResult importRemoteEntity(ImportConfig config, AbstractNode targetNode) {
      return doImportRemoteEntity(config, targetNode, false, [:])
   }

   @Override
   public Map<String, String> checkImportRemoteEntity(ImportConfig config, AbstractNode targetNode,
         Map<String, String> previousCheckResults) {
      return doImportRemoteEntity(config, targetNode, true, previousCheckResults)
   }

   private doImportRemoteEntity(ImportConfig config, AbstractNode targetNode, boolean check, Map<String, String> results) {
      if (!(targetNode instanceof AbstractFileServerNode)) {
         handleError(check, "Node is not a filesystem folder: '$targetNode'", results, IllegalArgumentException)
         if (check)
            return results
      }
         
      def analyzerService = analyzerServiceProvider.get()
      def treeConfig = new TreeNodeImporterConfig()
      config.addSpecificImporterConfigs treeConfig

      /* loop over items to find root */
      def exportRootId = null
      analyzerService.getExportedItemsFor(config.exportDataProvider, FileServerExporter).each {
         if(!it.getPropertyByName('parent')){
            exportRootId = it.id
         }
      }
      if(!exportRootId) {
         handleError(check, 'Could not find root', results, IllegalStateException)
         if (check)
            return results
      }

      /* one more loop to configure file import */
      analyzerService.getExportedItemsFor(config.exportDataProvider, FileServerExporter).each {
         def parentProp = it.getPropertyByName('parent')
         if(parentProp){
            def itemConfig = new TreeNodeImportItemConfig(it.id)
            /* set parent */
            if(parentProp instanceof ReferenceItemProperty && exportRootId == parentProp.referenceId)
               itemConfig.parent = targetNode

            config.addItemConfig itemConfig
         }
      }

      /* complete import */
      if (check)
         return results
      else
         return importServiceProvider.get().importData(config)
   }
   
}
