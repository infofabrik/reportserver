package net.datenwerke.security.ext.client.usermanager.hookers;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewProviderHookImpl;
import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.security.client.usermanager.dto.GroupDto;
import net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.ext.client.security.ui.SecurityView;
import net.datenwerke.security.ext.client.usermanager.ui.forms.GroupForm;
import net.datenwerke.security.ext.client.usermanager.ui.forms.OrganisationalUnitForm;
import net.datenwerke.security.ext.client.usermanager.ui.forms.UserForm;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class MainPanelViewProviderHooker extends MainPanelViewProviderHookImpl {

   public static final String USERMANAGER_VIEW_PROVIDER_ID = "USERMANAGER_MAIN_VIEW_PROVIDER";
   private final Provider<UserForm> userFormProvider;
   private final Provider<GroupForm> groupFormProvider;
   private final Provider<OrganisationalUnitForm> ouFormProvider;
   private final Provider<SecurityView> securityViewProvider;

   @Inject
   public MainPanelViewProviderHooker(HookHandlerService hookHandler,

         Provider<UserForm> userFormProvider, Provider<GroupForm> roleFormProvider,
         Provider<OrganisationalUnitForm> ouFormProvider, Provider<SecurityView> securityViewProvider) {
      super(hookHandler);

      /* store objects */
      this.userFormProvider = userFormProvider;
      this.groupFormProvider = roleFormProvider;
      this.ouFormProvider = ouFormProvider;
      this.securityViewProvider = securityViewProvider;
   }

   public List<MainPanelView> getPrimaryViews(AbstractNodeDto node) {
      if (node instanceof UserDto)
         return getViewForUser();
      if (node instanceof GroupDto)
         return getViewForGroup();
      if (node instanceof OrganisationalUnitDto)
         return getViewForOU();
      return new ArrayList<MainPanelView>();
   }

   private List<MainPanelView> getViewForOU() {
      List<MainPanelView> views = new ArrayList<MainPanelView>();
      views.add(ouFormProvider.get());
      views.add(securityViewProvider.get());
      return views;
   }

   private List<MainPanelView> getViewForGroup() {
      List<MainPanelView> views = new ArrayList<MainPanelView>();
      views.add(groupFormProvider.get());
      views.add(securityViewProvider.get());
      return views;
   }

   private List<MainPanelView> getViewForUser() {
      List<MainPanelView> views = new ArrayList<MainPanelView>();
      views.add(userFormProvider.get());
      views.add(securityViewProvider.get());
      return views;
   }

   @Override
   public String getViewProviderId() {
      return USERMANAGER_VIEW_PROVIDER_ID;
   }

}
