package net.datenwerke.security.ext.client.usermanager;

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
import net.datenwerke.gxtdto.client.waitonevent.SynchronousCallbackOnEventTrigger;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventTicket;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.ReadDto;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;
import net.datenwerke.security.client.security.hooks.GenericTargetProviderHook;
import net.datenwerke.security.ext.client.usermanager.helper.simpleform.StrippedDownUserProvider;
import net.datenwerke.security.ext.client.usermanager.helper.simpleform.UserProvider;
import net.datenwerke.security.ext.client.usermanager.hookers.MainPanelViewProviderHooker;
import net.datenwerke.security.ext.client.usermanager.objectinfo.GroupObjectInfo;
import net.datenwerke.security.ext.client.usermanager.objectinfo.UserDetailsObjectInfo;
import net.datenwerke.security.ext.client.usermanager.objectinfo.UserGroupsObjectInfo;
import net.datenwerke.security.ext.client.usermanager.objectinfo.UserObjectInfo;
import net.datenwerke.security.ext.client.usermanager.objectinfo.UserOrganisationalUnitObjectInfo;
import net.datenwerke.security.ext.client.usermanager.provider.annotations.UserManagerAdminViewTree;
import net.datenwerke.security.ext.client.usermanager.provider.treehookers.UserManagerTreeConfigurationHooker;
import net.datenwerke.security.ext.client.usermanager.security.UserManagerAdminViewGenericTargetIdentifier;
import net.datenwerke.security.ext.client.usermanager.security.UserManagerAdminViewSecurityTargetDomainHooker;
import net.datenwerke.security.ext.client.usermanager.ui.UserManagerPanel;

public class UserManagerUIStartup {

   @Inject
   public UserManagerUIStartup(final WaitOnEventUIService waitOnEventService,

         final UserManagerDao userManagerDao,

         final Provider<UserManagerAdminModule> adminModuleProvider,

         MainPanelViewProviderHooker mainPanelViewProvider,

         final HookHandlerService hookHandler,

         UserManagerAdminViewSecurityTargetDomainHooker securityTargetDomain,

         final SecurityUIService securityService,

         final UserManagerTreeLoaderDao umdao,

         final UserManagerTreeConfigurationHooker treeConfigurator,
         
         final UserObjectInfo userObjectInfo,
         
         final UserDetailsObjectInfo userDetailsObjectInfo,
         
         final UserGroupsObjectInfo userGroupsObjectInfo,
         
         final UserOrganisationalUnitObjectInfo userOrganisationalUnitObjectInfo,
         
         final GroupObjectInfo groupObjectInfo,

         final Provider<UserProvider> simpleFormUserProvider,
         Provider<StrippedDownUserProvider> strippedDownUserProvider,

         HistoryUiService historyService, @UserManagerAdminViewTree Provider<UITree> userManagerTree, EventBus eventBus,
         Provider<UserManagerPanel> userManagerAdminPanel) {

      /* config tree */
      hookHandler.attachHooker(TreeConfiguratorHook.class, treeConfigurator);
      
      /* object info */
      hookHandler.attachHooker(ObjectInfoKeyInfoProvider.class, userObjectInfo);
      hookHandler.attachHooker(ObjectInfoAdditionalInfoProvider.class, userDetailsObjectInfo);
      hookHandler.attachHooker(ObjectInfoAdditionalInfoProvider.class, userGroupsObjectInfo);
      hookHandler.attachHooker(ObjectInfoAdditionalInfoProvider.class, userOrganisationalUnitObjectInfo);
      
      hookHandler.attachHooker(ObjectInfoKeyInfoProvider.class, groupObjectInfo);

      /* attach security target domains */
      hookHandler.attachHooker(GenericTargetProviderHook.class,
            new GenericTargetProviderHook(securityTargetDomain.genericSecurityViewDomainHook_getTargetId()));
      hookHandler.attachHooker(GenericSecurityViewDomainHook.class, securityTargetDomain);

      /* attach views */
      hookHandler.attachHooker(MainPanelViewProviderHook.class, mainPanelViewProvider);

      /* test if user has rights to see user manager admin view */
      waitOnEventService.callbackOnEvent(AdministrationUIService.REPORTSERVER_EVENT_HAS_ADMIN_RIGHTS,
            new SynchronousCallbackOnEventTrigger() {
               public void execute(final WaitOnEventTicket ticket) {
                  if (securityService.hasRight(UserManagerAdminViewGenericTargetIdentifier.class, ReadDto.class))
                     hookHandler.attachHooker(AdminModuleProviderHook.class,
                           new AdminModuleProviderHook(adminModuleProvider), HookHandlerService.PRIORITY_HIGH + 10);

                  waitOnEventService.signalProcessingDone(ticket);
               }
            });

      /* simpleform */
      hookHandler.attachHooker(FormFieldProviderHook.class, simpleFormUserProvider, HookHandlerService.PRIORITY_HIGH);
      hookHandler.attachHooker(FormFieldProviderHook.class, strippedDownUserProvider, HookHandlerService.PRIORITY_LOW);

      /* configureHistory */
      historyService.addHistoryCallback(UserManagerUIModule.USERMANAGER_FAV_HISTORY_TOKEN, new TreeDBHistoryCallback(
            userManagerTree, eventBus, userManagerAdminPanel, AdministrationUIModule.ADMIN_PANEL_ID));

   }

}
