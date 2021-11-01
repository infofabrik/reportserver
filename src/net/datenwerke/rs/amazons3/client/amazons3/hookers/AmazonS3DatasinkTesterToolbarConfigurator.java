package net.datenwerke.rs.amazons3.client.amazons3.hookers;

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
import net.datenwerke.rs.core.client.datasinkmanager.locale.DatasinksMessages;
import net.datenwerke.rs.amazons3.client.amazons3.AmazonS3Dao;
import net.datenwerke.rs.amazons3.client.amazons3.dto.AmazonS3DatasinkDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class AmazonS3DatasinkTesterToolbarConfigurator implements MainPanelViewToolbarConfiguratorHook {

   final DatasinksMessages messages = GWT.create(DatasinksMessages.class);

   private final ToolbarService toolbarUtils;
   private final AmazonS3Dao amazonS3Dao;

   @Inject
   public AmazonS3DatasinkTesterToolbarConfigurator(ToolbarService toolbarUtils, AmazonS3Dao amazonS3Dao) {
      this.toolbarUtils = toolbarUtils;
      this.amazonS3Dao = amazonS3Dao;
   }

   @Override
   public void mainPanelViewToolbarConfiguratorHook_addLeft(MainPanelView view, ToolBar toolbar,
         AbstractNodeDto selectedNode) {
      if (!(view instanceof SimpleFormView))
         return;
      if (!(selectedNode instanceof AmazonS3DatasinkDto))
         return;
      DwTextButton testBtn = toolbarUtils.createSmallButtonLeft(DatasinksMessages.INSTANCE.testDatasink(),
            BaseIcon.LINK);
      testBtn.addSelectHandler(event -> {
         ModalAsyncCallback<Boolean> callback = new ModalAsyncCallback<Boolean>(BaseMessages.INSTANCE.error(),
               messages.testFailed(), messages.success(), messages.testSuccess(), messages.pleaseWait(),
               messages.testingTitle(), messages.testingProgressMessage()) {
         };
         Request request = amazonS3Dao.testAmazonS3Datasink((AmazonS3DatasinkDto) selectedNode,
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