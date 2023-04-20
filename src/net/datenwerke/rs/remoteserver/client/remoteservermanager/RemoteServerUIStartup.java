package net.datenwerke.rs.remoteserver.client.remoteservermanager;

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
import net.datenwerke.gxtdto.client.objectinformation.hooks.ObjectInfoKeyInfoProvider;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.hookers.MainPanelViewProviderHooker;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.objectinfo.RemoteServerFolderObjectInfo;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.objectinfo.RemoteServerObjectInfo;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.provider.annotations.RemoteServerManagerAdminViewTree;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.provider.treehooker.RemoteServerManagerTreeConfigurationHooker;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.security.RemoteServerManagerGenericTargetIdentifier;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.security.RemoteServerManagerViewSecurityTargetDomainHooker;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.ui.RemoteServerManagerPanel;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.ReadDto;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;
import net.datenwerke.security.client.security.hooks.GenericTargetProviderHook;

public class RemoteServerUIStartup {

   @Inject
   public RemoteServerUIStartup(
         final HookHandlerService hookHandler,
         final WaitOnEventUIService waitOnEventService,
         final RemoteServerManagerViewSecurityTargetDomainHooker securityTargetDomain,
         final MainPanelViewProviderHooker mainPanelViewProvider, 
         final Provider<RemoteServerAdminModule> adminModuleProvider,
         final SecurityUIService securityService,
         final RemoteServerManagerTreeConfigurationHooker treeConfigurator,
         final RemoteServerObjectInfo remoteServerObjectInfo,
         final RemoteServerFolderObjectInfo remoteServerFolderObjectInfo,
         final HistoryUiService historyService, 
         final @RemoteServerManagerAdminViewTree Provider<UITree> remoteserverManagerTree,
         final EventBus eventBus, 
         final Provider<RemoteServerManagerPanel> remoteserverManagerAdminPanel
         ) {

      /* config tree */
      hookHandler.attachHooker(TreeConfiguratorHook.class, treeConfigurator);
      
      /* attach security target domains */
      hookHandler.attachHooker(GenericTargetProviderHook.class,
            new GenericTargetProviderHook(securityTargetDomain.genericSecurityViewDomainHook_getTargetId()));
      hookHandler.attachHooker(GenericSecurityViewDomainHook.class, securityTargetDomain);

      /* attach views */
      hookHandler.attachHooker(MainPanelViewProviderHook.class, mainPanelViewProvider);
      
      /* object info */
      hookHandler.attachHooker(ObjectInfoKeyInfoProvider.class, remoteServerObjectInfo);
      hookHandler.attachHooker(ObjectInfoKeyInfoProvider.class, remoteServerFolderObjectInfo);
            
            
      /* test if user has rights to see remote server manager admin view */
      waitOnEventService.callbackOnEvent(AdministrationUIService.REPORTSERVER_EVENT_HAS_ADMIN_RIGHTS, ticket -> {
         if (securityService.hasRight(RemoteServerManagerGenericTargetIdentifier.class, ReadDto.class))
            /* attach admin hooker */
            hookHandler.attachHooker(AdminModuleProviderHook.class, new AdminModuleProviderHook(adminModuleProvider),
                  HookHandlerService.PRIORITY_HIGH + 55);

         waitOnEventService.signalProcessingDone(ticket);
      });
            

      /* configureHistory */
      historyService.addHistoryCallback(RemoteServerUIModule.REMOTE_SERVER_FAV_HISTORY_TOKEN, new TreeDBHistoryCallback(
            remoteserverManagerTree, eventBus, remoteserverManagerAdminPanel, AdministrationUIModule.ADMIN_PANEL_ID));
   }

}
