package net.datenwerke.rs.core.client.datasourcemanager;

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
import net.datenwerke.gxtdto.client.objectinformation.hooks.ObjectInfoAdditionalInfoProvider;
import net.datenwerke.gxtdto.client.objectinformation.hooks.ObjectInfoKeyInfoProvider;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.datasourcemanager.helper.forms.simpleform.DatasourceSelectionFieldProvider;
import net.datenwerke.rs.core.client.datasourcemanager.helper.forms.simpleform.DatasourceSimpleFormProvider;
import net.datenwerke.rs.core.client.datasourcemanager.hookers.MainPanelViewProviderHooker;
import net.datenwerke.rs.core.client.datasourcemanager.objectinfo.DatabaseDatasourceAdditionalObjectInfo;
import net.datenwerke.rs.core.client.datasourcemanager.objectinfo.DatasourceObjectInfo;
import net.datenwerke.rs.core.client.datasourcemanager.provider.annotations.DatasourceManagerAdminViewTree;
import net.datenwerke.rs.core.client.datasourcemanager.provider.treehooker.DatasourceManagerTreeConfigurationHooker;
import net.datenwerke.rs.core.client.datasourcemanager.security.DatasourceManagerGenericTargetIdentifier;
import net.datenwerke.rs.core.client.datasourcemanager.security.DatasourceManagerViewSecurityTargetDomainHooker;
import net.datenwerke.rs.core.client.datasourcemanager.ui.DatasourceManagerPanel;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.ReadDto;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;
import net.datenwerke.security.client.security.hooks.GenericTargetProviderHook;

/**
 * 
 *
 */
public class DatasourceUIStartup {

   @Inject
   public DatasourceUIStartup(final HookHandlerService hookHandler,
         Provider<DatasourceSimpleFormProvider> datasourceSimpleFormProvider,
         Provider<DatasourceSelectionFieldProvider> datasourceSelectionFieldProvider,

         final WaitOnEventUIService waitOnEventService, final DatasourceUIService datasourceService,

         DatasourceManagerViewSecurityTargetDomainHooker securityTargetDomain,

         MainPanelViewProviderHooker mainPanelViewProvider, final Provider<DatasourceAdminModule> adminModuleProvider,
         final SecurityUIService securityService,

         final DatasourceManagerTreeConfigurationHooker treeConfigurator,
         
         final DatasourceObjectInfo datasourceObjectInfo,
         final DatabaseDatasourceAdditionalObjectInfo datasourceAdditionalObjectInfo,

         HistoryUiService historyService, @DatasourceManagerAdminViewTree Provider<UITree> datasourceManagerTree,
         EventBus eventBus, Provider<DatasourceManagerPanel> datasourceManagerAdminPanel) {

      /* config tree */
      hookHandler.attachHooker(TreeConfiguratorHook.class, treeConfigurator);

      /* attach Hooks */
      hookHandler.attachHooker(FormFieldProviderHook.class, datasourceSimpleFormProvider);
      hookHandler.attachHooker(FormFieldProviderHook.class, datasourceSelectionFieldProvider);

      /* attach security target domains */
      hookHandler.attachHooker(GenericTargetProviderHook.class,
            new GenericTargetProviderHook(securityTargetDomain.genericSecurityViewDomainHook_getTargetId()));
      hookHandler.attachHooker(GenericSecurityViewDomainHook.class, securityTargetDomain);

      /* attach views */
      hookHandler.attachHooker(MainPanelViewProviderHook.class, mainPanelViewProvider);
      hookHandler.attachHooker(ObjectInfoKeyInfoProvider.class, datasourceObjectInfo);
      hookHandler.attachHooker(ObjectInfoAdditionalInfoProvider.class, datasourceAdditionalObjectInfo);
      
      /* test if user has rights to see datasource manager admin view */
      waitOnEventService.callbackOnEvent(AdministrationUIService.REPORTSERVER_EVENT_HAS_ADMIN_RIGHTS, ticket -> {
         if (securityService.hasRight(DatasourceManagerGenericTargetIdentifier.class, ReadDto.class))
            hookHandler.attachHooker(AdminModuleProviderHook.class, new AdminModuleProviderHook(adminModuleProvider),
                  HookHandlerService.PRIORITY_HIGH + 20);

         waitOnEventService.signalProcessingDone(ticket);
      });

      /* configureHistory */
      historyService.addHistoryCallback(DatasourceUIModule.DATASOURCE_FAV_HISTORY_TOKEN, new TreeDBHistoryCallback(
            datasourceManagerTree, eventBus, datasourceManagerAdminPanel, AdministrationUIModule.ADMIN_PANEL_ID));
   }

}
