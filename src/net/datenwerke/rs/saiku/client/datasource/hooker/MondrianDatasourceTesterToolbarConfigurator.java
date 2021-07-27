package net.datenwerke.rs.saiku.client.datasource.hooker;

import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;
import com.google.gwt.http.client.Request;

import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewToolbarConfiguratorHook;
import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.servercommunication.callback.ModalAsyncCallback;
import net.datenwerke.gxtdto.client.servercommunication.callback.TimeoutCallback;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.rs.adminutils.client.datasourcetester.locale.DatasourceTesterMessages;
import net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceDto;
import net.datenwerke.rs.saiku.client.saiku.SaikuDao;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class MondrianDatasourceTesterToolbarConfigurator implements MainPanelViewToolbarConfiguratorHook {

   final DatasourceTesterMessages messages = GWT.create(DatasourceTesterMessages.class);

   private final SaikuDao saikuDao;
   private final ToolbarService toolbarUtils;

   @Inject
   public MondrianDatasourceTesterToolbarConfigurator(
         ToolbarService toolbarUtils,
         SaikuDao saikuDao
         ) {

      this.toolbarUtils = toolbarUtils;
      this.saikuDao = saikuDao;
   }

   @Override
   public void mainPanelViewToolbarConfiguratorHook_addLeft(MainPanelView view, ToolBar toolbar,
         AbstractNodeDto selectedNode) {
      if (!(view instanceof SimpleFormView))
         return;
      if (!(selectedNode instanceof MondrianDatasourceDto))
         return;

      final MondrianDatasourceDto datasourceDto = (MondrianDatasourceDto) selectedNode;
      
      DwTextButton datasourceTestBtn = toolbarUtils.createSmallButtonLeft(messages.testConnection(), BaseIcon.LINK);
      datasourceTestBtn.addSelectHandler(event -> {
         ModalAsyncCallback<Boolean> callback = new ModalAsyncCallback<Boolean>(BaseMessages.INSTANCE.error(),
               messages.testFailed(), messages.success(), messages.testSuccess(), messages.pleaseWait(),
               messages.testingTitle(), messages.testingProgressMessage()) {
         };
         Request request = saikuDao.testConnection(datasourceDto, new TimeoutCallback<Boolean>(120000, callback));
         callback.setRequest(request);
      });

      toolbar.add(datasourceTestBtn);
   }

   @Override
   public void mainPanelViewToolbarConfiguratorHook_addRight(MainPanelView view, ToolBar toolbar,
         AbstractNodeDto selectedNode) {

   }

}
