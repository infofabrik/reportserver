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
import net.datenwerke.security.service.usermanager.entities.OrganisationalUnit

class RemoteEntityImporterServiceImpl implements RemoteEntityImporterService {

   private final Provider<TerminalService> terminalServiceProvider
   private final Provider<HookHandlerService> hookHandlerServiceProvider
   
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
      def importType = checkPreconditions(localTarget, remoteEntityPath)
     
     def remoteUrl = "$restUrl/node-exporter$remoteEntityPath${(includeVariants?';includeVariants=true':'')}?user=$user&apikey=$apikey"
     def httpConnection = new URL(remoteUrl).openConnection()
     if (httpConnection.responseCode != httpConnection.HTTP_OK)
        throw new IllegalStateException("Connection response code: ${httpConnection.responseCode}")
        
     def response = new JsonSlurper().parse(httpConnection.inputStream.newReader())
     def exportXml = response.export
     if (!exportXml)
        throw new IllegalStateException('Nothing to import')
        
      /* prepare import */
      def config = new ImportConfig(new ExportDataProviderImpl(exportXml.bytes))
      def importers = hookHandlerServiceProvider.get().getHookers(RemoteEntityImporterHook)
            .findAll { it.consumes(importType) }
      if (!importers)
         throw new IllegalArgumentException("Not yet supported: '$importType'")

      if (importers.size() != 1)
         throw new IllegalStateException('More than one importers found')

      return importers[0].importRemoteEntity(config, terminalServiceProvider.get().getObjectByQuery(localTarget))
   }
   
   private RemoteEntityImports checkPreconditions(String localTarget, String remoteEntityPath) {
      def exportType = RemoteEntityImports.values().findAll { remoteEntityPath.startsWith("/$it.manager") }?.get(0)
      if (!exportType)
         throw new IllegalArgumentException('No export type found')
      if (!localTarget.startsWith("/$exportType.manager"))
         throw new IllegalArgumentException("Incorrect target: '$localTarget'")
         
      def targetNode = terminalServiceProvider.get().getObjectByQuery(localTarget)
      if (!targetNode)
         throw new IllegalArgumentException("Node does not exist: '$localTarget'")
         
      if (targetNode.children)
         throw new IllegalArgumentException("Node is not empty: '$localTarget'")
         
      return exportType
   }
}
