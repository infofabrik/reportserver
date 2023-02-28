package net.datenwerke.rs.base.ext.service.reportmanager.eximport.hookers;

import static net.datenwerke.rs.base.ext.service.RemoteEntityImporterServiceImpl.handleError

import javax.inject.Inject

import org.apache.commons.text.StringEscapeUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.google.inject.Provider

import net.datenwerke.eximport.ExportDataAnalyzerService
import net.datenwerke.eximport.ImportService
import net.datenwerke.eximport.im.ImportConfig
import net.datenwerke.eximport.im.ImportMode
import net.datenwerke.eximport.im.ImportResult
import net.datenwerke.eximport.obj.ReferenceItemProperty
import net.datenwerke.rs.base.ext.service.RemoteEntityImporterService
import net.datenwerke.rs.base.ext.service.RemoteEntityImports
import net.datenwerke.rs.base.ext.service.datasourcemanager.eximport.DatasourceManagerExporter
import net.datenwerke.rs.base.ext.service.hooks.RemoteEntityImporterHook
import net.datenwerke.rs.base.ext.service.reportmanager.eximport.ReportManagerExporter
import net.datenwerke.rs.configservice.service.configservice.ConfigService
import net.datenwerke.rs.core.service.datasourcemanager.DatasourceService
import net.datenwerke.rs.core.service.reportmanager.ReportService
import net.datenwerke.rs.core.service.reportmanager.entities.AbstractReportManagerNode
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImportItemConfig
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImporterConfig
import net.datenwerke.treedb.service.treedb.AbstractNode

class RemoteReportImporterHooker implements RemoteEntityImporterHook {

   private final Provider<ExportDataAnalyzerService> analyzerServiceProvider
   private final Provider<ReportService> reportServiceProvider
   private final Provider<ImportService> importServiceProvider
   private final Provider<DatasourceService> datasourceServiceProvider
   private final Provider<ConfigService> configServiceProvider

   private final Logger logger = LoggerFactory.getLogger(getClass().name)

   @Inject
   public RemoteReportImporterHooker(
      Provider<ExportDataAnalyzerService> analyzerServiceProvider,
      Provider<ReportService> reportServiceProvider,
      Provider<ImportService> importServiceProvider,
      Provider<DatasourceService> datasourceServiceProvider,
      Provider<ConfigService> configServiceProvider
   ) {
      this.analyzerServiceProvider = analyzerServiceProvider
      this.reportServiceProvider = reportServiceProvider
      this.importServiceProvider = importServiceProvider
      this.datasourceServiceProvider =  datasourceServiceProvider
      this.configServiceProvider = configServiceProvider
   }

   @Override
   public boolean consumes(RemoteEntityImports importType) {
      return importType == RemoteEntityImports.REPORTS
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
   
   private Map<String, String> getDatasourceNameMappings() {
      def config = configServiceProvider.get().getConfigFailsafe(RemoteEntityImporterService.CONFIG_FILE)
      def nameMappings = config.configurationsAt('mappings.datasources.name-mappings.name-mapping')
      return nameMappings
         .collectEntries{[it.getString('remote'), it.getString('local')]}
   }

   private doImportRemoteEntity(ImportConfig config, AbstractNode targetNode, boolean check, Map<String, String> results) {
      if (!(targetNode instanceof AbstractReportManagerNode)) {
         handleError(check, "Node is not a report folder: '$targetNode'", results, IllegalArgumentException)
         if (check)
            return results
      }

      def analyzerService = analyzerServiceProvider.get()
      def treeConfig = new TreeNodeImporterConfig()
      config.addSpecificImporterConfigs treeConfig

      /* loop over items to find root*/
      def exportRootId = null
      def exportRootName = null
      analyzerService.getExportedItemsFor(config.exportDataProvider, ReportManagerExporter).each {
         if(!it.getPropertyByName('parent')){
            exportRootId = it.id
            exportRootName = it.getPropertyByName('name').element.value
         }
      }
      if(!exportRootId) {
         handleError(check, 'Could not find root', results, IllegalStateException)
         if (check)
            return results
      }

      /* one more loop to configure report import */
      analyzerService.getExportedItemsFor(config.exportDataProvider, ReportManagerExporter).each {
         def parentProp = it.getPropertyByName('parent')
         if(parentProp){
            def itemConfig = new TreeNodeImportItemConfig(it.id)
            /* set parent */
            if(parentProp instanceof ReferenceItemProperty && exportRootId == parentProp.referenceId)
               itemConfig.parent = targetNode

            config.addItemConfig itemConfig
         }
      }

      /* one more loop to check that keys and uuids do not exist in local RS */
      def reportService = reportServiceProvider.get()
      analyzerService.getExportedItemsFor(config.exportDataProvider, ReportManagerExporter).each {
         def keyProp = it.getPropertyByName('key')
         if(keyProp){
            def key = StringEscapeUtils.unescapeXml(keyProp.element.value)
            def existingReport = reportService.getReportByKey(key)
            if(existingReport){
               handleError(check, 
                  "A report with the key '$key' already exists in the system: '$existingReport'", results, IllegalStateException)
            }
         }
         def uuidProp = it.getPropertyByName('uuid')
         if(uuidProp){
            def uuid = StringEscapeUtils.unescapeXml(uuidProp.element.value)
            def existingReport = reportService.getReportByUUID(uuid)
            if(existingReport) {
               handleError(check,
                  "A report with the UUID '$uuid' already exists in the system: '$existingReport'", results, IllegalStateException)
            }
         }
      }

      def datasourceNameMappings = getDatasourceNameMappings()
      
      /* match datasources */
      def datasourceService = datasourceServiceProvider.get()
      analyzerService.getExportedItemsFor(config.exportDataProvider, DatasourceManagerExporter).each {
         def nameProp = it.getPropertyByName('name')
         if(nameProp?.type == String){
            def remoteName = StringEscapeUtils.unescapeXml(nameProp.element.value)
            def localName = remoteName
            if (datasourceNameMappings.containsKey(remoteName))
               localName = datasourceNameMappings[remoteName]
            if (localName) {
               /* try to find matching datasource */
               def ds = datasourceService.getDatasourceByName(localName)
               if (!ds) 
                  handleError(check, "Datasource '$remoteName' could not be mapped.", results, IllegalArgumentException)
               else {
                  def dsConfig = new TreeNodeImportItemConfig(it.id, ImportMode.REFERENCE, ds)
                  config.addItemConfig dsConfig
               }
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
