package net.datenwerke.rs.transport.service.transport;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.eximport.hooks.ExporterProviderHook;
import net.datenwerke.eximport.hooks.ImporterProviderHook;
import net.datenwerke.gf.service.history.hooks.HistoryUrlBuilderHook;
import net.datenwerke.gf.service.lifecycle.hooks.ConfigDoneHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.hooks.GeneralInfoCategoryProviderHook;
import net.datenwerke.rs.base.ext.service.hooks.ExportConfigHook;
import net.datenwerke.rs.base.ext.service.hooks.RemoteEntityImporterHook;
import net.datenwerke.rs.eximport.service.eximport.im.http.hooks.HttpImportConfigurationProviderHook;
import net.datenwerke.rs.terminal.service.terminal.hookers.TransportOpenTerminalHooker;
import net.datenwerke.rs.terminal.service.terminal.hooks.OpenTerminalHandlerHook;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.transport.service.transport.entities.Transport;
import net.datenwerke.rs.transport.service.transport.entities.TransportFolder;
import net.datenwerke.rs.transport.service.transport.eximport.HttpTransportManagerImportConfigurationHooker;
import net.datenwerke.rs.transport.service.transport.eximport.TransportManagerExporter;
import net.datenwerke.rs.transport.service.transport.eximport.TransportManagerImporter;
import net.datenwerke.rs.transport.service.transport.eximport.hookers.RemoteTransportImporterHooker;
import net.datenwerke.rs.transport.service.transport.eximport.hookers.TransportExportConfigHooker;
import net.datenwerke.rs.transport.service.transport.genrights.TransportAdminViewSecurityTarget;
import net.datenwerke.rs.transport.service.transport.genrights.TransportManagementAdminViewSecurityTarget;
import net.datenwerke.rs.transport.service.transport.history.TransportManagerHistoryUrlBuilderHooker;
import net.datenwerke.rs.transport.service.transport.hookers.ConfigMappingsExistPreconditionHooker;
import net.datenwerke.rs.transport.service.transport.hookers.ConfigTransportExistPreconditionHooker;
import net.datenwerke.rs.transport.service.transport.hookers.LocalMappingsPreconditionHooker;
import net.datenwerke.rs.transport.service.transport.hookers.PermissionPreconditionHooker;
import net.datenwerke.rs.transport.service.transport.hookers.SchemaPreconditionHooker;
import net.datenwerke.rs.transport.service.transport.hookers.SuperUserConfiguredPreconditionHooker;
import net.datenwerke.rs.transport.service.transport.hookers.TransportCategoryProviderHooker;
import net.datenwerke.rs.transport.service.transport.hookers.TransportClosedPreconditionHooker;
import net.datenwerke.rs.transport.service.transport.hookers.TransportMappingsPreconditionHooker;
import net.datenwerke.rs.transport.service.transport.hookers.UsageStatisticsTransportFoldersProviderHooker;
import net.datenwerke.rs.transport.service.transport.hookers.UsageStatisticsTransportsProviderHooker;
import net.datenwerke.rs.transport.service.transport.hookers.VersionPreconditionHooker;
import net.datenwerke.rs.transport.service.transport.hooks.ApplyPreconditionHook;
import net.datenwerke.rs.transport.service.transport.hooks.TransportEntryProviderHook;
import net.datenwerke.rs.transport.service.transport.terminal.commands.TransportAddSubcommand;
import net.datenwerke.rs.transport.service.transport.terminal.commands.TransportApplySubcommand;
import net.datenwerke.rs.transport.service.transport.terminal.commands.TransportCloseSubcommand;
import net.datenwerke.rs.transport.service.transport.terminal.commands.TransportCommand;
import net.datenwerke.rs.transport.service.transport.terminal.commands.TransportCreateSubcommand;
import net.datenwerke.rs.transport.service.transport.terminal.commands.TransportDescribeSubcommand;
import net.datenwerke.rs.transport.service.transport.terminal.commands.TransportRemoveSubcommand;
import net.datenwerke.rs.transport.service.transport.terminal.commands.TransportRpullSubcommand;
import net.datenwerke.rs.transport.service.transport.terminal.commands.TransportSubCommandHook;
import net.datenwerke.security.service.security.SecurityService;

public class TransportStartup {

   @Inject
   public TransportStartup(
         final HookHandlerService hookHandler,
         final Provider<SecurityService> securityServiceProvider,
         final Provider<TransportCommand> transportCommand,
         final Provider<TransportCreateSubcommand> transportCreateCommand,
         final Provider<TransportAddSubcommand> transportAddCommand,
         final Provider<TransportRemoveSubcommand> transportRemoveCommand,
         final Provider<TransportCloseSubcommand> transportCloseCommand,
         final Provider<TransportDescribeSubcommand> transportDescribeCommand,
         final Provider<TransportRpullSubcommand> transportRpullCommand,
         final Provider<TransportApplySubcommand> transportApplyCommand,
         final Provider<TransportManagerHistoryUrlBuilderHooker> managerUrlBuilder,
         final Provider<TransportExportConfigHooker> exportConfigHookerProvider,
         final Provider<TransportManagerExporter> transportManagerExporter,
         final Provider<TransportManagerImporter> transportImporterProvider,
         final Provider<HttpTransportManagerImportConfigurationHooker> transportHttpImportConfigHookerProvider,
         final Provider<RemoteTransportImporterHooker> remoteTransportImporterHooker,
         final Provider<UsageStatisticsTransportsProviderHooker> usageStatsTransportsProvider,
         final Provider<UsageStatisticsTransportFoldersProviderHooker> usageStatsTransportFolderProvider,
         final Provider<TransportCategoryProviderHooker> usageStatistics,
         final Provider<TransportOpenTerminalHooker> transportOpenTerminalHooker,
         
         final Provider<TransportClosedPreconditionHooker> transportClosedHooker,
         final Provider<VersionPreconditionHooker> versionHooker,
         final Provider<SchemaPreconditionHooker> schemaHooker,
         final Provider<PermissionPreconditionHooker> permissionHooker,
         final Provider<ConfigTransportExistPreconditionHooker> configTransportExistHooker,
         final Provider<ConfigMappingsExistPreconditionHooker> configMappingsExistHooker,
         final Provider<SuperUserConfiguredPreconditionHooker> superUserConfiguredHooker,
         final Provider<LocalMappingsPreconditionHooker> localMappingsHooker,
         final Provider<TransportMappingsPreconditionHooker> transportMappingsHooker
   ) {

      hookHandler.attachHooker(TerminalCommandHook.class, transportCommand);
      hookHandler.attachHooker(TransportSubCommandHook.class, transportCreateCommand);
      hookHandler.attachHooker(TransportSubCommandHook.class, transportAddCommand);
      hookHandler.attachHooker(TransportSubCommandHook.class, transportRemoveCommand);
      hookHandler.attachHooker(TransportSubCommandHook.class, transportCloseCommand);
      hookHandler.attachHooker(TransportSubCommandHook.class, transportDescribeCommand);
      hookHandler.attachHooker(TransportSubCommandHook.class, transportRpullCommand);
      //hookHandler.attachHooker(TransportSubCommandHook.class, transportApplyCommand); apply command is broken because of a already started transaction RS-8273
      hookHandler.attachHooker(OpenTerminalHandlerHook.class, transportOpenTerminalHooker);
      
      hookHandler.attachHooker(TransportEntryProviderHook.class, usageStatsTransportFolderProvider,
            HookHandlerService.PRIORITY_LOW);
      hookHandler.attachHooker(TransportEntryProviderHook.class, usageStatsTransportsProvider,
            HookHandlerService.PRIORITY_LOW + 3);
      
      hookHandler.attachHooker(GeneralInfoCategoryProviderHook.class, usageStatistics,
            HookHandlerService.PRIORITY_LOW + 73);
      
      /* history */
      hookHandler.attachHooker(HistoryUrlBuilderHook.class, managerUrlBuilder);
      
      hookHandler.attachHooker(ExportConfigHook.class, exportConfigHookerProvider);
      hookHandler.attachHooker(RemoteEntityImporterHook.class, remoteTransportImporterHooker);
      
      hookHandler.attachHooker(ExporterProviderHook.class, new ExporterProviderHook(transportManagerExporter));
      hookHandler.attachHooker(ImporterProviderHook.class, new ImporterProviderHook(transportImporterProvider));
      hookHandler.attachHooker(HttpImportConfigurationProviderHook.class, transportHttpImportConfigHookerProvider);
      
      /* apply preconditions */
      hookHandler.attachHooker(ApplyPreconditionHook.class, transportClosedHooker);
      hookHandler.attachHooker(ApplyPreconditionHook.class, versionHooker);
      hookHandler.attachHooker(ApplyPreconditionHook.class, schemaHooker);
      hookHandler.attachHooker(ApplyPreconditionHook.class, permissionHooker);
      hookHandler.attachHooker(ApplyPreconditionHook.class, configTransportExistHooker);
      hookHandler.attachHooker(ApplyPreconditionHook.class, configMappingsExistHooker);
      hookHandler.attachHooker(ApplyPreconditionHook.class, superUserConfiguredHooker);
      hookHandler.attachHooker(ApplyPreconditionHook.class, localMappingsHooker);
      hookHandler.attachHooker(ApplyPreconditionHook.class, transportMappingsHooker);
      
      /* register security targets */
      hookHandler.attachHooker(ConfigDoneHook.class, () -> {
         /* secure folder */
         securityServiceProvider.get().registerSecurityTarget(TransportFolder.class);
         securityServiceProvider.get().registerSecurityTarget(Transport.class);
         
         securityServiceProvider.get().registerSecurityTarget(TransportAdminViewSecurityTarget.class);
         securityServiceProvider.get().registerSecurityTarget(TransportManagementAdminViewSecurityTarget.class);
      });
      
   }
}
