package net.datenwerke.rs.box.client.box.hookers;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Label;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewToolbarConfiguratorHook;
import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utilityservices.UtilsUIService;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.rs.core.client.datasinkmanager.locale.DatasinksMessages;
import net.datenwerke.rs.box.client.box.BoxDao;
import net.datenwerke.rs.box.client.box.dto.BoxDatasinkDto;
import net.datenwerke.rs.oauth.client.oauth.OAuthAuthenticationUriInfo;
import net.datenwerke.rs.oauth.client.oauth.OAuthDao;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class BoxDatasinkOAuthToolbarConfigurator implements MainPanelViewToolbarConfiguratorHook {

   final DatasinksMessages messages = GWT.create(DatasinksMessages.class);

   private final ToolbarService toolbarUtils;
   private final OAuthDao oAuthDao;
   private final UtilsUIService utilsUiService;

   @Inject
   public BoxDatasinkOAuthToolbarConfigurator(ToolbarService toolbarUtils, BoxDao boxDao, OAuthDao oAuthDao,
         UtilsUIService utilsUiService) {
      this.toolbarUtils = toolbarUtils;
      this.oAuthDao = oAuthDao;
      this.utilsUiService = utilsUiService;
   }

   @Override
   public void mainPanelViewToolbarConfiguratorHook_addLeft(MainPanelView view, ToolBar toolbar,
         AbstractNodeDto selectedNode) {
      if (!(view instanceof SimpleFormView))
         return;
      if (!(selectedNode instanceof BoxDatasinkDto))
         return;
      DwTextButton oauthBtn = toolbarUtils
            .createSmallButtonLeft(BaseMessages.INSTANCE.datasinkOauth2AuthenticationSetup(), BaseIcon.SIGN_IN);

      final BoxDatasinkDto boxDatasinkDto = (BoxDatasinkDto) selectedNode;
      oauthBtn.addSelectHandler(event -> displayAuthorizationDialog(boxDatasinkDto));

      toolbar.add(oauthBtn);

   }

   private void displayAuthorizationDialog(final BoxDatasinkDto boxDatasinkDto) {

      oAuthDao.generateAuthenticationUrl(boxDatasinkDto, new RsAsyncCallback<OAuthAuthenticationUriInfo>() {
         @Override
         public void onSuccess(OAuthAuthenticationUriInfo result) {

            final DwWindow window = new DwWindow();
            window.setHeading(BaseMessages.INSTANCE.datasinkOauth2AuthenticationSetup());
            window.setSize(600, 190);

            VerticalLayoutContainer hlc = new VerticalLayoutContainer();
            window.add(hlc, new MarginData(10));

            Label textNote = new Label(BaseMessages.INSTANCE.oauthNote1());
            hlc.add(textNote);

            Label textRedirection = new Label(BaseMessages.INSTANCE.oauthNote2());
            hlc.add(textRedirection);

            String redirectUri = result.getRedirectUri();
            Label textRedirectUri = new Label(redirectUri);
            hlc.add(textRedirectUri);

            DwTextButton authenticationBtn = new DwTextButton(BaseMessages.INSTANCE.oauthStart());
            authenticationBtn
                  .addSelectHandler(event -> utilsUiService.redirectWithoutAsking(result.getAuthenticationUri()));

            window.addButton(authenticationBtn);

            window.show();
         }
      });

   }

   @Override
   public void mainPanelViewToolbarConfiguratorHook_addRight(MainPanelView view, ToolBar toolbar,
         AbstractNodeDto selectedNode) {
   }

}
