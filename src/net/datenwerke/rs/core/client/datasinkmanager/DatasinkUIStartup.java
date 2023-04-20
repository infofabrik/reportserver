package net.datenwerke.rs.core.client.datasinkmanager;

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
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHook;
import net.datenwerke.gxtdto.client.objectinformation.hooks.ObjectInfoKeyInfoProvider;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.datasinkmanager.helper.forms.simpleform.DatasinkSelectionFieldProvider;
import net.datenwerke.rs.core.client.datasinkmanager.hookers.MainPanelViewProviderHooker;
import net.datenwerke.rs.core.client.datasinkmanager.objectinfo.DatasinkFolderObjectInfo;
import net.datenwerke.rs.core.client.datasinkmanager.objectinfo.DatasinkObjectInfo;
import net.datenwerke.rs.core.client.datasinkmanager.provider.annotations.DatasinkManagerAdminViewTree;
import net.datenwerke.rs.core.client.datasinkmanager.provider.treehooker.DatasinkManagerTreeConfigurationHooker;
import net.datenwerke.rs.core.client.datasinkmanager.security.DatasinkManagerGenericTargetIdentifier;
import net.datenwerke.rs.core.client.datasinkmanager.security.DatasinkManagerViewSecurityTargetDomainHooker;
import net.datenwerke.rs.core.client.datasinkmanager.ui.DatasinkManagerPanel;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.ReadDto;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;
import net.datenwerke.security.client.security.hooks.GenericTargetProviderHook;

/**
 * 
 *
 */
public class DatasinkUIStartup {

   @Inject
   public DatasinkUIStartup(final HookHandlerService hookHandler,
         Provider<DatasinkSelectionFieldProvider> datasinkSimpleFormProvider,

         final WaitOnEventUIService waitOnEventService, final DatasinkUIService datasinkService,

         DatasinkManagerViewSecurityTargetDomainHooker securityTargetDomain,

         MainPanelViewProviderHooker mainPanelViewProvider, 
         final Provider<DatasinkAdminModule> adminModuleProvider,
         final SecurityUIService securityService,

         final DatasinkManagerTreeConfigurationHooker treeConfigurator,
         
         final DatasinkObjectInfo datasinkObjectInfo,
         final DatasinkFolderObjectInfo datasinkFolderObjectInfo,

         HistoryUiService historyService, @DatasinkManagerAdminViewTree Provider<UITree> datasinkManagerTree,
         EventBus eventBus, Provider<DatasinkManagerPanel> datasinkManagerAdminPanel) {

      /* config tree */
      hookHandler.attachHooker(TreeConfiguratorHook.class, treeConfigurator);

      /* attach Hooks */
      hookHandler.attachHooker(FormFieldProviderHook.class, datasinkSimpleFormProvider);

      /* attach security target domains */
      hookHandler.attachHooker(GenericTargetProviderHook.class,
            new GenericTargetProviderHook(securityTargetDomain.genericSecurityViewDomainHook_getTargetId()));
      hookHandler.attachHooker(GenericSecurityViewDomainHook.class, securityTargetDomain);

      /* attach views */
      hookHandler.attachHooker(MainPanelViewProviderHook.class, mainPanelViewProvider);
      
      /* object info */
      hookHandler.attachHooker(ObjectInfoKeyInfoProvider.class, datasinkObjectInfo);
      hookHandler.attachHooker(ObjectInfoKeyInfoProvider.class, datasinkFolderObjectInfo);
      
      /* test if user has rights to see datasource manager admin view */
      waitOnEventService.callbackOnEvent(AdministrationUIService.REPORTSERVER_EVENT_HAS_ADMIN_RIGHTS, ticket -> {
         if (securityService.hasRight(DatasinkManagerGenericTargetIdentifier.class, ReadDto.class))
            hookHandler.attachHooker(AdminModuleProviderHook.class, new AdminModuleProviderHook(adminModuleProvider),
                  HookHandlerService.PRIORITY_HIGH + 20);

         waitOnEventService.signalProcessingDone(ticket);
      });

      /* configureHistory */
      historyService.addHistoryCallback(DatasinkUIModule.DATASINK_FAV_HISTORY_TOKEN, new TreeDBHistoryCallback(
            datasinkManagerTree, eventBus, datasinkManagerAdminPanel, AdministrationUIModule.ADMIN_PANEL_ID));
   }

}
