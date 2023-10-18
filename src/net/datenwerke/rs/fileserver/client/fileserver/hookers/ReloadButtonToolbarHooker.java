package net.datenwerke.rs.fileserver.client.fileserver.hookers;

import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewToolbarConfiguratorHook;
import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public abstract class ReloadButtonToolbarHooker implements MainPanelViewToolbarConfiguratorHook {

   private final ToolbarService toolbarUtils;
   
   @Inject
   public ReloadButtonToolbarHooker(ToolbarService toolbarUtils) {
      this.toolbarUtils = toolbarUtils;
   }

   @Override
   public void mainPanelViewToolbarConfiguratorHook_addLeft(MainPanelView view, ToolBar toolbar,
         AbstractNodeDto selectedNode) {
   }

   @Override
   public void mainPanelViewToolbarConfiguratorHook_addRight(MainPanelView view, ToolBar toolbar,
         AbstractNodeDto selectedNode) {
      if (!viewApplies(view, selectedNode)) return;
      DwTextButton reloadButton = toolbarUtils.createSmallButtonLeft(BaseMessages.INSTANCE.reload(), BaseIcon.REFRESH);
      reloadButton.addSelectHandler(new SelectHandler() {
         @Override
         public void onSelect(SelectEvent event) {
            view.reloadNodeAndView();
         }
      });
      toolbar.add(reloadButton);
   }

   private boolean viewApplies(MainPanelView view, AbstractNodeDto selectedNode) {
      if (!(selectedNode instanceof AbstractFileServerNodeDto))
         return false;
      if (!getExpectedViewID().equals(view.getViewId()))
         return false;

      return true;
   }
   
   protected abstract String getExpectedViewID();

}
