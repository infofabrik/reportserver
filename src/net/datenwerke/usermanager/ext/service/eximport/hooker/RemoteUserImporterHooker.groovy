package net.datenwerke.usermanager.ext.service.eximport.hooker;

import javax.inject.Inject

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.google.inject.Provider

import net.datenwerke.eximport.ExportDataAnalyzerService
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

   private final Provider<ExportDataAnalyzerService> analyzerServiceProvider
   private final Provider<UserManagerService> userManagerServiceProvider
   private final Provider<ImportService> importServiceProvider
   
   private final Logger logger = LoggerFactory.getLogger(getClass().name)
   
   @Inject
   public RemoteUserImporterHooker(
      Provider<ExportDataAnalyzerService> analyzerServiceProvider,
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
      if (!(targetNode instanceof OrganisationalUnit))
         throw new IllegalArgumentException("Node is not an organizational unit: '$targetNode'")
         
      def analyzerService = analyzerServiceProvider.get()
      def treeConfig = new TreeNodeImporterConfig()
      config.addSpecificImporterConfigs treeConfig

      /* loop over items to find root */
      def exportRootId = null
      def exportRootName = null
      analyzerService.getExportedItemsFor(config.exportDataProvider, UserManagerExporter).each {
         def nameprop = it.getPropertyByName('name')
         if(!it.getPropertyByName('parent') && OrganisationalUnit == it.type ){
            exportRootId = it.id
            exportRootName = nameprop?.element?.value
         }
      }
      if(!exportRootId)
         throw new IllegalStateException('Could not find root')

      //   tout.println "Root folder: $exportRootName($exportRootId)"

      /* one more loop to configure user import */
      analyzerService.getExportedItemsFor(config.exportDataProvider, UserManagerExporter).each {
         def parentProp = it.getPropertyByName('parent')
         def usernameProp = it.getPropertyByName('username')

         if(usernameProp && userManagerServiceProvider.get().getUserByName(usernameProp.element.value)) {
            logger.info("Skipping: '${usernameProp.element.value}' because username already exists")
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
      return importServiceProvider.get().importData(config)
   }
}
