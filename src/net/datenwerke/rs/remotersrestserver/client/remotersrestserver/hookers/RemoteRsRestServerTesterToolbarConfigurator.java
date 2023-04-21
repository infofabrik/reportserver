package net.datenwerke.rs.remotersrestserver.client.remotersrestserver.hookers;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewToolbarConfiguratorHook;
import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.servercommunication.callback.ModalAsyncCallback;
import net.datenwerke.gxtdto.client.servercommunication.callback.TimeoutCallback;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.rs.remotersrestserver.client.remotersrestserver.RemoteRsRestServerDao;
import net.datenwerke.rs.remotersrestserver.client.remotersrestserver.dto.RemoteRsRestServerDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.locale.RemoteServerMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class RemoteRsRestServerTesterToolbarConfigurator implements MainPanelViewToolbarConfiguratorHook {

   final RemoteServerMessages messages = GWT.create(RemoteServerMessages.class);

   private final ToolbarService toolbarUtils;
   private final RemoteRsRestServerDao dao;

   @Inject
   public RemoteRsRestServerTesterToolbarConfigurator(
         ToolbarService toolbarUtils, 
         RemoteRsRestServerDao dao
         ) {
      this.toolbarUtils = toolbarUtils;
      this.dao = dao;
   }

   @Override
   public void mainPanelViewToolbarConfiguratorHook_addLeft(MainPanelView view, ToolBar toolbar,
         AbstractNodeDto selectedNode) {
      if (!(view instanceof SimpleFormView))
         return;
      if (!(selectedNode instanceof RemoteRsRestServerDto))
         return;
      DwTextButton testBtn = toolbarUtils.createSmallButtonLeft("Test remote RS REST server",
            BaseIcon.LINK);
      testBtn.addSelectHandler(event -> {
         ModalAsyncCallback<Boolean> callback = new ModalAsyncCallback<Boolean>(BaseMessages.INSTANCE.error(),
               messages.testFailed(), messages.success(), messages.testSuccess(), messages.pleaseWait(),
               messages.testingTitle(), messages.testingProgressMessage()) {
         };
         Request request = dao.test((RemoteRsRestServerDto) selectedNode,
               new TimeoutCallback<Boolean>(120000, callback));
         callback.setRequest(request);
      });
      toolbar.add(testBtn);

   }

   @Override
   public void mainPanelViewToolbarConfiguratorHook_addRight(MainPanelView view, ToolBar toolbar,
         AbstractNodeDto selectedNode) {
      // TODO Auto-generated method stub

   }

}