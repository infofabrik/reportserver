package net.datenwerke.rs.uservariables.client.uservariables.hookers;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewProviderHookImpl;
import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.uservariables.client.uservariables.genrights.UserVariableAdminViewGenericTargetIdentifier;
import net.datenwerke.rs.uservariables.client.uservariables.mainpanel.UserVariablesDefinitionPanel;
import net.datenwerke.rs.uservariables.client.uservariables.mainpanel.UserVariablesView;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.ReadDto;
import net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class MainPanelViewProviderHooker extends MainPanelViewProviderHookImpl {

   public static final String USERMANAGER_VIEW_PROVIDER_ID = "USERMANAGER_MAIN_VIEW_PROVIDER";

   private final Provider<UserVariablesView> userVariablesViewProvider;
   private final Provider<UserVariablesDefinitionPanel> defProvider;
   private final SecurityUIService securityService;

   @Inject
   public MainPanelViewProviderHooker(HookHandlerService hookHandlerService,
         Provider<UserVariablesView> userVariablesViewProvider, Provider<UserVariablesDefinitionPanel> defProvider,
         SecurityUIService securityService) {
      super(hookHandlerService);
      this.userVariablesViewProvider = userVariablesViewProvider;
      this.defProvider = defProvider;
      this.securityService = securityService;
   }

   public List<MainPanelView> getPrimaryViews(AbstractNodeDto node) {
      List<MainPanelView> views = new ArrayList<MainPanelView>();

      if (node instanceof OrganisationalUnitDto && Boolean.TRUE.equals(((OrganisationalUnitDto) node).isIsUserRoot()))
         if (securityService.hasRight(UserVariableAdminViewGenericTargetIdentifier.class, ReadDto.class))
            views.add(defProvider.get());

      if (node instanceof UserDto || node instanceof OrganisationalUnitDto)
         views.add(userVariablesViewProvider.get());

      return views;
   }

   @Override
   public String getViewProviderId() {
      return USERMANAGER_VIEW_PROVIDER_ID;
   }

}
