package net.datenwerke.rs.dropbox.client.dropbox.hookers;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.ui.Frame;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gf.client.history.HistoryDao;
import net.datenwerke.gf.client.history.dto.HistoryLinkDto;
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
import net.datenwerke.rs.dropbox.client.dropbox.DropboxDao;
import net.datenwerke.rs.dropbox.client.dropbox.dto.DropboxDatasinkDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class DropboxDatasinkOAuthToolbarConfigurator implements MainPanelViewToolbarConfiguratorHook {

   final DatasinksMessages messages = GWT.create(DatasinksMessages.class);

   private final ToolbarService toolbarUtils;
   private final HistoryDao historyDao;
   private final UtilsUIService utilsUiService;

   private static final String BASE_URL = "https://www.dropbox.com/oauth2/authorize?token_access_type=offline&response_type=code";

   @Inject
   public DropboxDatasinkOAuthToolbarConfigurator(ToolbarService toolbarUtils, DropboxDao dropboxDao,
         HistoryDao historyDao, UtilsUIService utilsUiService) {
      this.toolbarUtils = toolbarUtils;
      this.historyDao = historyDao;
      this.utilsUiService = utilsUiService;
   }

   @Override
   public void mainPanelViewToolbarConfiguratorHook_addLeft(MainPanelView view, ToolBar toolbar,
         AbstractNodeDto selectedNode) {
      if (!(view instanceof SimpleFormView))
         return;
      if (!(selectedNode instanceof DropboxDatasinkDto))
         return;
      DwTextButton oauthBtn = toolbarUtils.createSmallButtonLeft(BaseMessages.INSTANCE.datasinkOauth2AuthenticationSetup(),
            BaseIcon.SIGN_IN);
      
      final DropboxDatasinkDto dropboxDatasinkDto = (DropboxDatasinkDto) selectedNode;
      oauthBtn.addSelectHandler(selectHandlerEvent -> {
         final DwWindow window = new DwWindow();
         window.setWidth(500);
         window.setHeight(200);
         window.setCenterOnShow(true);
         historyDao.getLinksFor(dropboxDatasinkDto, new RsAsyncCallback<List<HistoryLinkDto>>() {
            @Override
            public void onSuccess(List<HistoryLinkDto> result) {
               if (null == result || result.isEmpty())
                  return;

               final String path = result.get(0).getHistoryToken();

               final String redirectUri = GWT.getModuleBaseURL() + "oauth";
               final String url = BASE_URL + "&client_id=" + dropboxDatasinkDto.getAppKey() + "&redirect_uri="
                     + redirectUri + "&state="
                     + URL.encodeQueryString("{\"datasinkId\":\"" + dropboxDatasinkDto.getId() + "\",\"path\":\"" + path
                           + "\"," + "\"redirect_uri\":\"" + redirectUri + "\"" + "}");
               window.setWidget(new Frame(url));
               utilsUiService.redirectWithoutAsking(url);
            }
         });
      });

      toolbar.add(oauthBtn);

   }

   @Override
   public void mainPanelViewToolbarConfiguratorHook_addRight(MainPanelView view, ToolBar toolbar,
         AbstractNodeDto selectedNode) {

   }

}