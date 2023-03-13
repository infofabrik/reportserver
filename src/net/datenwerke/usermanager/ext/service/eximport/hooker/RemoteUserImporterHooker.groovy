package net.datenwerke.usermanager.ext.service.eximport.hooker;

import static net.datenwerke.rs.base.ext.service.RemoteEntityImporterServiceImpl.handleError

import javax.inject.Inject

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.google.inject.Provider

import net.datenwerke.eximport.ExportDataAnalyzerServiceImpl
import net.datenwerke.eximport.ImportService
import net.datenwerke.eximport.im.ImportConfig
import net.datenwerke.eximport.im.ImportMode
import net.datenwerke.eximport.im.ImportResult
import net.datenwerke.eximport.obj.ReferenceItemProperty
import net.datenwerke.rs.base.ext.service.RemoteEntityImports
import net.datenwerke.rs.base.ext.service.hooks.RemoteEntityImporterHook
import net.datenwerke.security.service.usermanager.UserManagerService
import net.datenwerke.security.service.usermanager.entities.OrganisationalUnit
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImportItemConfig
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImporterConfig
import net.datenwerke.treedb.service.treedb.AbstractNode
import net.datenwerke.usermanager.ext.service.eximport.UserManagerExporter

class RemoteUserImporterHooker implements RemoteEntityImporterHook {

   private final Provider<ExportDataAnalyzerServiceImpl> analyzerServiceProvider
   private final Provider<UserManagerService> userManagerServiceProvider
   private final Provider<ImportService> importServiceProvider
   
   private final Logger logger = LoggerFactory.getLogger(getClass().name)
   
   @Inject
   public RemoteUserImporterHooker(
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
      return importType == RemoteEntityImports.USERS
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
      if (!(targetNode instanceof OrganisationalUnit)) {
         handleError(check, "Node is not an organizational unit: '$targetNode'", results, IllegalArgumentException)
         if (check)
            return results
      }
         
      def analyzerService = analyzerServiceProvider.get()
      def treeConfig = new TreeNodeImporterConfig()
      config.addSpecificImporterConfigs treeConfig

      def exportRootId = analyzerService.getRootId(config.exportDataProvider, UserManagerExporter)
      if(!exportRootId) {
         handleError(check, 'Could not find root', results, IllegalStateException)
         if (check)
            return results
      }

      /* one more loop to configure user import */
      analyzerService.getExportedItemsFor(config.exportDataProvider, UserManagerExporter).each {
         def parentProp = it.getPropertyByName('parent')
         def usernameProp = it.getPropertyByName('username')

         if(usernameProp && userManagerServiceProvider.get().getUserByName(usernameProp.element.value)) {
            handleError(check, "Username '${usernameProp.element.value}' already exists", results, IllegalArgumentException)
         } else {
            if(parentProp){
               def itemConfig = new TreeNodeImportItemConfig(it.id)

               /* set parent */
               if(parentProp instanceof ReferenceItemProperty && exportRootId == parentProp.referenceId)
                  itemConfig.parent = targetNode

               config.addItemConfig itemConfig
            } else {
               /* add reference entry */
               config.addItemConfig(new TreeNodeImportItemConfig(it.id, ImportMode.REFERENCE, targetNode))
            }
         }
      }

      /* complete import */
      if (check)
         return results
      else
         return importServiceProvider.get().importData(config)
   }
   
}
