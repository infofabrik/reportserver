package net.datenwerke.rs.transport.client.transport;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.administration.AdministrationUIModule;
import net.datenwerke.gf.client.administration.AdministrationUIService;
import net.datenwerke.gf.client.administration.hooks.AdminModuleProviderHook;
import net.datenwerke.gf.client.history.HistoryUiService;
import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewProviderHook;
import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewToolbarConfiguratorHook;
import net.datenwerke.gf.client.managerhelper.hooks.TreeConfiguratorHook;
import net.datenwerke.gf.client.treedb.TreeDBHistoryCallback;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gxtdto.client.objectinformation.hooks.ObjectInfoKeyInfoProvider;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.eximport.client.eximport.im.hooks.ImporterConfiguratorHook;
import net.datenwerke.rs.transport.client.transport.eximport.im.hookers.TransportManagerUIImporterHooker;
import net.datenwerke.rs.transport.client.transport.hookers.MainPanelViewProviderHooker;
import net.datenwerke.rs.transport.client.transport.hookers.TransportApplyToolbarConfigurator;
import net.datenwerke.rs.transport.client.transport.objectinfo.TransportFolderObjectInfo;
import net.datenwerke.rs.transport.client.transport.objectinfo.TransportObjectInfo;
import net.datenwerke.rs.transport.client.transport.provider.annotations.TransportManagerAdminViewTree;
import net.datenwerke.rs.transport.client.transport.provider.treehooker.TransportManagerTreeConfigurationHooker;
import net.datenwerke.rs.transport.client.transport.security.TransportManagerGenericTargetIdentifier;
import net.datenwerke.rs.transport.client.transport.security.TransportManagerViewSecurityTargetDomainHooker;
import net.datenwerke.rs.transport.client.transport.ui.TransportManagerPanel;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.ReadDto;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;
import net.datenwerke.security.client.security.hooks.GenericTargetProviderHook;

public class TransportUIStartup {

   @Inject
   public TransportUIStartup(
         final HookHandlerService hookHandler,
         final WaitOnEventUIService waitOnEventService,
         final TransportManagerViewSecurityTargetDomainHooker securityTargetDomain,
         final MainPanelViewProviderHooker mainPanelViewProvider, 
         final Provider<TransportAdminModule> adminModuleProvider,
         final Provider<TransportManagerAdminModule> importedTransportsAdminModule,
         final SecurityUIService securityService,
         final TransportManagerTreeConfigurationHooker treeConfigurator,
         final TransportObjectInfo transportObjectInfo,
         final TransportFolderObjectInfo folderObjectInfo,
         final HistoryUiService historyService, 
         final @TransportManagerAdminViewTree Provider<UITree> managerTree,
         final EventBus eventBus, 
         final Provider<TransportManagerPanel> managerAdminPanel,
         final TransportManagerUIImporterHooker importerHooker,
         final TransportApplyToolbarConfigurator transportApply
         ) {

      /* config tree */
      hookHandler.attachHooker(TreeConfiguratorHook.class, treeConfigurator);
      
      /* attach security target domains */
      hookHandler.attachHooker(GenericTargetProviderHook.class,
            new GenericTargetProviderHook(securityTargetDomain.genericSecurityViewDomainHook_getTargetId()));
      hookHandler.attachHooker(GenericSecurityViewDomainHook.class, securityTargetDomain);

      /* attach views */
      hookHandler.attachHooker(MainPanelViewProviderHook.class, mainPanelViewProvider);
      
      /* transport apply button */
      hookHandler.attachHooker(MainPanelViewToolbarConfiguratorHook.class, transportApply);
      
      /* object info */
      hookHandler.attachHooker(ObjectInfoKeyInfoProvider.class, transportObjectInfo);
      hookHandler.attachHooker(ObjectInfoKeyInfoProvider.class, folderObjectInfo);
      
      hookHandler.attachHooker(ImporterConfiguratorHook.class, importerHooker);
            
      /* test if user has rights to see transport manager admin view */
      waitOnEventService.callbackOnEvent(AdministrationUIService.REPORTSERVER_EVENT_HAS_ADMIN_RIGHTS, ticket -> {
         if (securityService.hasRight(TransportManagerGenericTargetIdentifier.class, ReadDto.class)) {
            /* attach admin hooker */
            hookHandler.attachHooker(AdminModuleProviderHook.class, new AdminModuleProviderHook(adminModuleProvider),
                  HookHandlerService.PRIORITY_HIGH + 58);
            /* attach admin hooker */
            hookHandler.attachHooker(AdminModuleProviderHook.class, new AdminModuleProviderHook(importedTransportsAdminModule),
                  HookHandlerService.PRIORITY_HIGH + 59);            
         }

         waitOnEventService.signalProcessingDone(ticket);
      });
            

      /* configureHistory */
      historyService.addHistoryCallback(TransportUIModule.TRANSPORT_FAV_HISTORY_TOKEN, new TreeDBHistoryCallback(
            managerTree, eventBus, managerAdminPanel, AdministrationUIModule.ADMIN_PANEL_ID));
   }

}
