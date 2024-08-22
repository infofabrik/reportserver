package net.datenwerke.rs.base.ext.service.reportmanager.eximport.hookers;

import static net.datenwerke.rs.base.ext.service.RemoteEntityImporterServiceImpl.handleError

import javax.inject.Inject

import org.apache.commons.text.StringEscapeUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.google.inject.Provider

import net.datenwerke.eximport.ExportDataAnalyzerServiceImpl
import net.datenwerke.eximport.ImportService
import net.datenwerke.eximport.im.ImportConfig
import net.datenwerke.eximport.im.ImportMode
import net.datenwerke.eximport.im.ImportResult
import net.datenwerke.rs.base.ext.service.RemoteEntityImports
import net.datenwerke.rs.base.ext.service.datasourcemanager.eximport.DatasourceManagerExporter
import net.datenwerke.rs.base.ext.service.hooks.RemoteEntityImporterHook
import net.datenwerke.rs.base.ext.service.reportmanager.eximport.ReportManagerExporter
import net.datenwerke.rs.core.service.datasourcemanager.DatasourceService
import net.datenwerke.rs.core.service.reportmanager.ReportService
import net.datenwerke.rs.core.service.reportmanager.entities.AbstractReportManagerNode
import net.datenwerke.rs.core.service.reportmanager.entities.ReportFolder
import net.datenwerke.rs.keyutils.service.keyutils.KeyMatchService
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImportItemConfig
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImporterConfig
import net.datenwerke.treedb.service.treedb.AbstractNode

class RemoteReportImporterHooker implements RemoteEntityImporterHook {

   private final Provider<ExportDataAnalyzerServiceImpl> analyzerServiceProvider
   private final Provider<ReportService> reportServiceProvider
   private final Provider<ImportService> importServiceProvider
   private final Provider<DatasourceService> datasourceServiceProvider
   private final Provider<KeyMatchService> keyMatchServiceProvider
   

   private final Logger logger = LoggerFactory.getLogger(getClass().name)

   @Inject
   public RemoteReportImporterHooker(
      Provider<ExportDataAnalyzerServiceImpl> analyzerServiceProvider,
      Provider<ReportService> reportServiceProvider,
      Provider<ImportService> importServiceProvider,
      Provider<DatasourceService> datasourceServiceProvider,
      Provider<KeyMatchService> keyMatchServiceProvider
   ) {
      this.analyzerServiceProvider = analyzerServiceProvider
      this.reportServiceProvider = reportServiceProvider
      this.importServiceProvider = importServiceProvider
      this.datasourceServiceProvider =  datasourceServiceProvider
      this.keyMatchServiceProvider = keyMatchServiceProvider
      
   }

   @Override
   public boolean consumes(RemoteEntityImports importType) {
      return importType == RemoteEntityImports.REPORTS
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
      if (!(targetNode instanceof AbstractReportManagerNode)) {
         handleError(check, "Node is not a report folder: '$targetNode'", results, IllegalArgumentException)
         if (check)
            return results
      }

      def analyzerService = analyzerServiceProvider.get()
      def importService = importServiceProvider.get()
      def treeConfig = new TreeNodeImporterConfig()
      config.addSpecificImporterConfigs treeConfig

      def exportRoot = analyzerService.getRoot(config.exportDataProvider, ReportManagerExporter)
      if(!exportRoot) {
         handleError(check, 'Could not find root', results, IllegalStateException)
         if (check)
            return results
      }
      def exportRootType = exportRoot.type
      if (exportRootType != ReportFolder) // in case we only exported one item
         exportRoot = targetNode

      importService.configureParents config, exportRoot.id as String, targetNode, ReportManagerExporter

      /* one more loop to check that keys and uuids do not exist in local RS */
      def reportService = reportServiceProvider.get()
      analyzerService.getExportedItemsFor(config.exportDataProvider, ReportManagerExporter).each {
         def keyProp = it.getPropertyByName('key')
         if(keyProp){
            def key = StringEscapeUtils.unescapeXml(keyProp.element.value)
            def existingReport = reportService.getReportByKey(key)
            if(existingReport){
               handleError(check, 
                  "A report with the key '$key' already exists in the local system: '$existingReport'", 
                     results, IllegalStateException)
            }
         } else {
            def nameProp = it.getPropertyByName('name')
            if (!nameProp)
               handleError(check,
                  "The remote report has no name. ID: ${analyzerService.getItemId(it.element)}", results, IllegalStateException)
            def remoteName = StringEscapeUtils.unescapeXml(nameProp.element.value)
            def type = analyzerService.getItemTypeAsClass(it.element)
            if (type != ReportFolder)
               handleError(check,
                  "The remote report with name '$remoteName' has no key.", results, IllegalStateException)
         }
         def uuidProp = it.getPropertyByName('uuid')
         if(uuidProp){
            def uuid = StringEscapeUtils.unescapeXml(uuidProp.element.value)
            def existingReport = reportService.getReportByUUID(uuid)
            if(existingReport) {
               handleError(check,
                  "A report with the UUID '$uuid' already exists in the local system: '$existingReport'", 
                     results, IllegalStateException)
            }
         }
      }

      /* match datasources */
      analyzerService.getExportedItemsFor(config.exportDataProvider, DatasourceManagerExporter).each {
         def nameProp = it.getPropertyByName('name')
         if (!nameProp)
            handleError(check,
               "The remote datasource has no name. ID: ${analyzerService.getItemId(it.element)}", results, IllegalStateException)
         def remoteName = StringEscapeUtils.unescapeXml(nameProp?.element?.value)
         def keyProp = it.getPropertyByName('key')
         if (!keyProp) 
            handleError(check, "Remote datasource '$remoteName' has no key.", results, IllegalStateException)
         if(keyProp?.type == String){
            def remoteKey = StringEscapeUtils.unescapeXml(keyProp.element.value)
            def ds = matchToLocalDatasource(remoteKey)
            if (!ds)
               handleError(check, "Datasource '$remoteName' with key '$remoteKey' could not be mapped to a local datasource.", 
                  results, IllegalArgumentException)
            else {
               def dsConfig = new TreeNodeImportItemConfig(it.id, ImportMode.REFERENCE, ds)
               config.addItemConfig dsConfig
            }
         }
      }

      /* complete import */
      if (check)
         return results
      else
         return importServiceProvider.get().importData(config)
   }
   
   def matchToLocalDatasource(remoteDatasourceKey) {
      def datasourceService = datasourceServiceProvider.get()
      
      def localDatasource = keyMatchServiceProvider.get().matchToLocalEntity(remoteDatasourceKey, 
         { datasourceService.getDatasourceByKey(it) }, 
         KeyMatchService.MAPPINGS_CONFIG_FILE,
         KeyMatchService.DATASOURCE_MAPPINGS_PATH, 
         KeyMatchService.DATASOURCE_PRIOS_PATH)
      
      return localDatasource
   }
   
}


