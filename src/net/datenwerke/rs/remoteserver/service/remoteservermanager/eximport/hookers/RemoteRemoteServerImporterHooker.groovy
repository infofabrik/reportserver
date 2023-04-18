package net.datenwerke.rs.remoteserver.service.remoteservermanager.eximport.hookers

import static net.datenwerke.rs.base.ext.service.RemoteEntityImporterServiceImpl.handleError

import javax.inject.Inject

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.google.inject.Provider

import net.datenwerke.eximport.ExportDataAnalyzerServiceImpl
import net.datenwerke.eximport.ImportService
import net.datenwerke.eximport.im.ImportConfig
import net.datenwerke.eximport.im.ImportResult
import net.datenwerke.rs.base.ext.service.RemoteEntityImports
import net.datenwerke.rs.base.ext.service.hooks.RemoteEntityImporterHook
import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.AbstractRemoteServerManagerNode
import net.datenwerke.rs.remoteserver.service.remoteservermanager.eximport.RemoteServerManagerExporter
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImporterConfig
import net.datenwerke.treedb.service.treedb.AbstractNode

class RemoteRemoteServerImporterHooker implements RemoteEntityImporterHook {

   private final Provider<ExportDataAnalyzerServiceImpl> analyzerServiceProvider
   private final Provider<ImportService> importServiceProvider
   
   private final Logger logger = LoggerFactory.getLogger(getClass().name)
   
   @Inject
   public RemoteRemoteServerImporterHooker(
      Provider<ExportDataAnalyzerServiceImpl> analyzerServiceProvider,
      Provider<ImportService> importServiceProvider
      ) {
      this.analyzerServiceProvider = analyzerServiceProvider
      this.importServiceProvider = importServiceProvider
   }
   
   @Override
   public boolean consumes(RemoteEntityImports importType) {
      return importType == RemoteEntityImports.REMOTESERVERS
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
      if (!(targetNode instanceof AbstractRemoteServerManagerNode)) {
         handleError(check, "Node is not a remote server folder: '$targetNode'", results, IllegalArgumentException)
         if (check)
            return results
      }
         
      def analyzerService = analyzerServiceProvider.get()
      def importService = importServiceProvider.get()
      def treeConfig = new TreeNodeImporterConfig()
      config.addSpecificImporterConfigs treeConfig

      def exportRootId = analyzerService.getRootId(config.exportDataProvider, RemoteServerManagerExporter)
      if(!exportRootId) {
         handleError(check, 'Could not find root', results, IllegalStateException)
         if (check)
            return results
      }

      importService.configureParents config, exportRootId, targetNode, RemoteServerManagerExporter

      /* complete import */
      if (check)
         return results
      else
         return importService.importData(config)
   }
   
}
