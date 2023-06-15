package net.datenwerke.rs.transport.service.transport;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.eximport.hooks.ExporterProviderHook;
import net.datenwerke.eximport.hooks.ImporterProviderHook;
import net.datenwerke.gf.service.history.hooks.HistoryUrlBuilderHook;
import net.datenwerke.gf.service.lifecycle.hooks.ConfigDoneHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.ext.service.hooks.ExportConfigHook;
import net.datenwerke.rs.base.ext.service.hooks.RemoteEntityImporterHook;
import net.datenwerke.rs.eximport.service.eximport.im.http.hooks.HttpImportConfigurationProviderHook;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.transport.service.transport.entities.Transport;
import net.datenwerke.rs.transport.service.transport.entities.TransportFolder;
import net.datenwerke.rs.transport.service.transport.eximport.HttpTransportManagerImportConfigurationHooker;
import net.datenwerke.rs.transport.service.transport.eximport.TransportManagerExporter;
import net.datenwerke.rs.transport.service.transport.eximport.TransportManagerImporter;
import net.datenwerke.rs.transport.service.transport.eximport.hookers.RemoteTransportImporterHooker;
import net.datenwerke.rs.transport.service.transport.eximport.hookers.TransportExportConfigHooker;
import net.datenwerke.rs.transport.service.transport.history.TransportManagerHistoryUrlBuilderHooker;
import net.datenwerke.rs.transport.service.transport.terminal.commands.TransportCommand;
import net.datenwerke.rs.transport.service.transport.terminal.commands.TransportCreateSubcommand;
import net.datenwerke.rs.transport.service.transport.terminal.commands.TransportSubCommandHook;
import net.datenwerke.security.service.security.SecurityService;

public class TransportStartup {

   @Inject
   public TransportStartup(
         final HookHandlerService hookHandler,
         final Provider<SecurityService> securityServiceProvider,
         final Provider<TransportCommand> transportCommand,
         final Provider<TransportCreateSubcommand> transportCreateCommand,
         final Provider<TransportManagerHistoryUrlBuilderHooker> managerUrlBuilder,
         final Provider<TransportExportConfigHooker> exportConfigHookerProvider,
         final Provider<TransportManagerExporter> transportManagerExporter,
         final Provider<TransportManagerImporter> transportImporterProvider,
         final Provider<HttpTransportManagerImportConfigurationHooker> transportHttpImportConfigHookerProvider,
         final Provider<RemoteTransportImporterHooker> remoteTransportImporterHooker
   ) {

      hookHandler.attachHooker(TerminalCommandHook.class, transportCommand);
      hookHandler.attachHooker(TransportSubCommandHook.class, transportCreateCommand);
      
      /* history */
      hookHandler.attachHooker(HistoryUrlBuilderHook.class, managerUrlBuilder);
      
      hookHandler.attachHooker(ExportConfigHook.class, exportConfigHookerProvider);
      hookHandler.attachHooker(RemoteEntityImporterHook.class, remoteTransportImporterHooker);
      
      hookHandler.attachHooker(ExporterProviderHook.class, new ExporterProviderHook(transportManagerExporter));
      hookHandler.attachHooker(ImporterProviderHook.class, new ImporterProviderHook(transportImporterProvider));
      hookHandler.attachHooker(HttpImportConfigurationProviderHook.class, transportHttpImportConfigHookerProvider);
      
      /* register security targets */
      hookHandler.attachHooker(ConfigDoneHook.class, () -> {
         /* secure folder */
         securityServiceProvider.get().registerSecurityTarget(TransportFolder.class);
         securityServiceProvider.get().registerSecurityTarget(Transport.class);
      });
   }
}
