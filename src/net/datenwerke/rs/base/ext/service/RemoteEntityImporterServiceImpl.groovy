package net.datenwerke.rs.base.ext.service

import javax.inject.Inject

import com.google.inject.Provider

import groovy.json.JsonSlurper
import net.datenwerke.eximport.ExportDataProviderImpl
import net.datenwerke.eximport.im.ImportConfig
import net.datenwerke.eximport.im.ImportResult
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService
import net.datenwerke.rs.base.ext.service.hooks.RemoteEntityImporterHook
import net.datenwerke.rs.terminal.service.terminal.TerminalService

class RemoteEntityImporterServiceImpl implements RemoteEntityImporterService {

   private final Provider<TerminalService> terminalServiceProvider
   private final Provider<HookHandlerService> hookHandlerServiceProvider
   
   public final static String EXPORT_TYPE_PROPERTY = 'ExportType'
   public final static String STATUS = 'Status'
   public final static String STATUS_OK = 'OK'
   public final static String STATUS_FAIL = 'FAIL'
   
   @Inject
   public RemoteEntityImporterServiceImpl(
      Provider<TerminalService> terminalServiceProvider,
      Provider<HookHandlerService> hookHandlerServiceProvider
      ) {
         this.terminalServiceProvider = terminalServiceProvider
         this.hookHandlerServiceProvider = hookHandlerServiceProvider
         
      }
   
   @Override
   public ImportResult importRemoteEntities(String restUrl, String user, String apikey, String remoteEntityPath, String localTarget,
         boolean includeVariants) {
      return doImportRemoteEntities(restUrl, user, apikey, remoteEntityPath, localTarget, includeVariants, false, [:])
   }
   
   @Override
   public Map<String, Object> checkImportRemoteEntities(String restUrl, String user, String apikey,
         String remoteEntityPath, String localTarget, boolean includeVariants, Map<String, Object> errors) {
      return doImportRemoteEntities(restUrl, user, apikey, remoteEntityPath, localTarget, includeVariants, true, errors)
   }
   
   private doImportRemoteEntities(String restUrl, String user, String apikey, String remoteEntityPath, String localTarget, 
      boolean includeVariants, check, Map<String,Object> results) {
      checkPreconditions localTarget, remoteEntityPath, results, check
      
      def status = results[STATUS]
      if (status == STATUS_FAIL)
         return results
      def importType = results[EXPORT_TYPE_PROPERTY]
      
      def remoteUrl = "$restUrl/node-exporter$remoteEntityPath${(includeVariants?';includeVariants=true':'')}?user=$user&apikey=$apikey"
      def httpConnection = new URL(remoteUrl).openConnection()
      if (httpConnection.responseCode != httpConnection.HTTP_OK) {
         handleError(check, "Connection response code: ${httpConnection.responseCode}", results, IllegalStateException)
         if (check)
            return results
      }
      def response = new JsonSlurper().parse(httpConnection.inputStream.newReader())
      def exportXml = response.export
      if (!exportXml) {
         handleError(check, 'Nothing to import', results, IllegalStateException)
         if (check)
            return results
      }
         
       /* prepare import */
       def config = new ImportConfig(new ExportDataProviderImpl(exportXml.bytes))
       def importers = hookHandlerServiceProvider.get().getHookers(RemoteEntityImporterHook)
             .findAll { it.consumes(importType) }
       if (!importers) {
          handleError(check, "Not yet supported: '$importType'", results, IllegalArgumentException)
          if (check)
             return results
       }
 
       if (importers.size() != 1) {
          handleError(check, 'More than one importer found', results, IllegalStateException)
          if (check)
             return results
       }
 
       if (check) {
          results[RemoteEntityImporterServiceImpl.STATUS] = RemoteEntityImporterServiceImpl.STATUS_OK
          return importers[0].checkImportRemoteEntity(config, terminalServiceProvider.get().getObjectByQuery(localTarget), results)
       } else
          return importers[0].importRemoteEntity(config, terminalServiceProvider.get().getObjectByQuery(localTarget))
   }
   
   private checkPreconditions(String localTarget, String remoteEntityPath, results, check) {
      def exportType = RemoteEntityImports.values().findAll { remoteEntityPath.startsWith("/$it.manager") }?.get(0)
      if (!exportType) {
         handleError(check, 'No export type found', results, IllegalArgumentException)
         if (check)
            return results
      } else {
         results << [(EXPORT_TYPE_PROPERTY): exportType]
      }
      if (!localTarget.startsWith("/$exportType.manager")) {
         handleError(check, "Incorrect target: '$localTarget'", results, IllegalArgumentException)
         if (check)
            return results
      }
         
      def targetNode = terminalServiceProvider.get().getObjectByQuery(localTarget)
      if (!targetNode) {
         handleError(check, "Node does not exist: '$localTarget'", results, IllegalArgumentException)
         if (check)
            return results
      }
         
      if (targetNode.children) {
         handleError(check, "Node is not empty: '$localTarget'", results, IllegalArgumentException)
         if (check)
            return results
      }
         
      return exportType
   }
   
   public static void handleError(check, msg, results, exceptionClazz) {
      if (!check) {
         throw exceptionClazz.getDeclaredConstructor(String).newInstance(msg as String)
      } else {
         results << [(STATUS) : STATUS_FAIL]
         def keyName = "${results.size()-1}: ${exceptionClazz.simpleName}" as String
         results << [(keyName): msg]
      }
   }

}
