package net.datenwerke.rs.transport.client.transport.hookers;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.info.DefaultInfoConfig;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.info.InfoConfig;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewToolbarConfiguratorHook;
import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.dialog.error.DetailErrorDialog;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.servercommunication.callback.ModalAsyncCallback;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.rs.enterprise.client.EnterpriseUiService;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.transport.client.transport.TransportDao;
import net.datenwerke.rs.transport.client.transport.TransportUiService;
import net.datenwerke.rs.transport.client.transport.dto.TransportCheckEntryDto;
import net.datenwerke.rs.transport.client.transport.dto.TransportDto;
import net.datenwerke.rs.transport.client.transport.locale.TransportMessages;
import net.datenwerke.security.client.security.dto.ExecuteDto;
import net.datenwerke.security.client.treedb.dto.decorator.SecuredAbstractNodeDtoDec;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class TransportApplyToolbarConfigurator implements MainPanelViewToolbarConfiguratorHook {

   final TransportMessages messages = GWT.create(TransportMessages.class);

   private final ToolbarService toolbarUtils;
   private final TransportDao dao;
   private final Provider<TransportUiService> transportUiServiceProvider;
   private final Provider<EnterpriseUiService> enterpriseServiceProvider;

   @Inject
   public TransportApplyToolbarConfigurator(
         ToolbarService toolbarUtils, 
         TransportDao dao,
         Provider<TransportUiService> transportUiServiceProvider,
         Provider<EnterpriseUiService> enterpriseServiceProvider
         ) {
      this.toolbarUtils = toolbarUtils;
      this.dao = dao;
      this.transportUiServiceProvider = transportUiServiceProvider;
      this.enterpriseServiceProvider = enterpriseServiceProvider;
   }

   @Override
   public void mainPanelViewToolbarConfiguratorHook_addLeft(MainPanelView view, ToolBar toolbar,
         AbstractNodeDto selectedNode) {
      if (!(view instanceof SimpleFormView))
         return;
      if (!(selectedNode instanceof TransportDto))
         return;
      if (!(selectedNode instanceof SecuredAbstractNodeDtoDec))
         return;
      SecuredAbstractNodeDtoDec securedNode = (SecuredAbstractNodeDtoDec) selectedNode;
      if (!securedNode.isAccessRightsLoaded())
         return;
      if (!securedNode.hasAccessRight(ExecuteDto.class))
         return;
      
      DwTextButton applyBtn = toolbarUtils.createSmallButtonLeft(messages.applyTransport(),
            BaseIcon.ADJUST);
      
      applyBtn.addSelectHandler(event -> {
         ModalAsyncCallback<Void> callback = new ModalAsyncCallback<Void>(null,
               null, messages.success(), messages.applySuccess(), messages.pleaseWait(),
               messages.applyingTitle(), messages.applyingProgressMessage()) {
            @Override
            public void doOnSuccess(Void result) {
               view.reloadNodeAndView();
            }
         };
         Request request = dao.apply((TransportDto) selectedNode, callback);
         callback.setRequest(request);
      });
      if (enterpriseServiceProvider.get().isEnterprise())
         toolbar.add(applyBtn);
      
      DwTextButton checkBtn = toolbarUtils.createSmallButtonLeft(messages.checkApplyPreconditions(),
            BaseIcon.BOOKMARK);
      checkBtn.addSelectHandler(event -> {
         startCheckPreconditionsProgress();
         dao.checkPreconditions((TransportDto) selectedNode, new RsAsyncCallback<List<TransportCheckEntryDto>>() {
            @Override
            public void onSuccess(List<TransportCheckEntryDto> result) {
               transportUiServiceProvider.get().displayAnalysisResult(result);
            }

            @Override
            public void onFailure(Throwable caught) {
               new DetailErrorDialog(caught).show();
            }
         });
      });
      toolbar.add(checkBtn);
   }

   protected void startCheckPreconditionsProgress() {
      InfoConfig infoConfig = new DefaultInfoConfig(messages.checkApplyPreconditions(), messages.analyzing());
      infoConfig.setWidth(350);
      infoConfig.setDisplay(3500);
      Info.display(infoConfig);
   }

   @Override
   public void mainPanelViewToolbarConfiguratorHook_addRight(MainPanelView view, ToolBar toolbar,
         AbstractNodeDto selectedNode) {
      // TODO Auto-generated method stub

   }

}