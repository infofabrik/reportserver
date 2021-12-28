package net.datenwerke.rs.fileserver.client.fileserver;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.administration.AdministrationUIModule;
import net.datenwerke.gf.client.administration.AdministrationUIService;
import net.datenwerke.gf.client.administration.hooks.AdminModuleProviderHook;
import net.datenwerke.gf.client.history.HistoryUiService;
import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewProviderHook;
import net.datenwerke.gf.client.managerhelper.hooks.TreeConfiguratorHook;
import net.datenwerke.gf.client.treedb.TreeDBHistoryCallback;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.selection.hooks.AddSelectionFieldMenuItemHook;
import net.datenwerke.gxtdto.client.waitonevent.SynchronousCallbackOnEventTrigger;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventTicket;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.eximport.client.eximport.im.hooks.ImporterConfiguratorHook;
import net.datenwerke.rs.fileserver.client.fileserver.eximport.im.hookers.FileServerUiImporterHooker;
import net.datenwerke.rs.fileserver.client.fileserver.fileeditors.text.EditTextFileView;
import net.datenwerke.rs.fileserver.client.fileserver.hookers.EditFileFromSelectionWidgetHooker;
import net.datenwerke.rs.fileserver.client.fileserver.hookers.MainPanelViewProviderHooker;
import net.datenwerke.rs.fileserver.client.fileserver.hooks.EditFileServerFileHook;
import net.datenwerke.rs.fileserver.client.fileserver.provider.annotations.FileServerManagerAdminViewTree;
import net.datenwerke.rs.fileserver.client.fileserver.provider.treehooker.FileServerManagerTreeConfigurationHooker;
import net.datenwerke.rs.fileserver.client.fileserver.security.FileServerManagerGenericTargetIdentifier;
import net.datenwerke.rs.fileserver.client.fileserver.security.FileServerManagerViewSecurityTargetDomainHooker;
import net.datenwerke.rs.fileserver.client.fileserver.terminal.commandproc.EditTerminalCommandProcessor;
import net.datenwerke.rs.fileserver.client.fileserver.ui.FileServerManagerPanel;
import net.datenwerke.rs.terminal.client.terminal.hooks.CommandResultProcessorHook;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.ReadDto;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;
import net.datenwerke.security.client.security.hooks.GenericTargetProviderHook;

public class FileServerUiStartup {

   @Inject
   public FileServerUiStartup(final HookHandlerService hookHandler, final WaitOnEventUIService waitOnEventService,
         final SecurityUIService securityService,

         Provider<EditTerminalCommandProcessor> commandProcessor,

         Provider<FileServerUiImporterHooker> importerHooker,

         Provider<EditFileFromSelectionWidgetHooker> editFileinSelectionWidget,

         final Provider<FileServerAdminModule> adminModuleProvider,
         final FileServerManagerTreeConfigurationHooker treeConfigurator,
         final MainPanelViewProviderHooker mainPanelViewProvider,

         final Provider<EditTextFileView> editTextFileProvider,

         final FileServerManagerViewSecurityTargetDomainHooker securityTargetDomain,

         @FileServerManagerAdminViewTree Provider<UITree> fileManagerTree, HistoryUiService historyService,
         EventBus eventBus, Provider<FileServerManagerPanel> fileManagerAdminPanel) {

      /* attach ex/importer */
      hookHandler.attachHooker(ImporterConfiguratorHook.class, importerHooker);

      /* attach security target domains */
      hookHandler.attachHooker(GenericTargetProviderHook.class,
            new GenericTargetProviderHook(securityTargetDomain.genericSecurityViewDomainHook_getTargetId()));
      hookHandler.attachHooker(GenericSecurityViewDomainHook.class, securityTargetDomain);

      /* attach views */
      hookHandler.attachHooker(MainPanelViewProviderHook.class, mainPanelViewProvider);
      hookHandler.attachHooker(EditFileServerFileHook.class, editTextFileProvider);

      /* misc */
      hookHandler.attachHooker(AddSelectionFieldMenuItemHook.class, editFileinSelectionWidget);

      /* terminal commands */
      hookHandler.attachHooker(CommandResultProcessorHook.class, commandProcessor);

      /* config tree */
      hookHandler.attachHooker(TreeConfiguratorHook.class, treeConfigurator);

      /* test if user has rights to see datasource manager admin view */
      waitOnEventService.callbackOnEvent(AdministrationUIService.REPORTSERVER_EVENT_HAS_ADMIN_RIGHTS,
            new SynchronousCallbackOnEventTrigger() {
               public void execute(final WaitOnEventTicket ticket) {
                  if (securityService.hasRight(FileServerManagerGenericTargetIdentifier.class, ReadDto.class)) {
                     hookHandler.attachHooker(AdminModuleProviderHook.class,
                           new AdminModuleProviderHook(adminModuleProvider), HookHandlerService.PRIORITY_HIGH + 40);
                  }
                  waitOnEventService.signalProcessingDone(ticket);
               }
            });

      historyService.addHistoryCallback(FileServerUiModule.FILESERVER_HISTORY_TOKEN, new TreeDBHistoryCallback(
            fileManagerTree, eventBus, fileManagerAdminPanel, AdministrationUIModule.ADMIN_PANEL_ID));
   }
}
