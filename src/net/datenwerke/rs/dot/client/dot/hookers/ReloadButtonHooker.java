package net.datenwerke.rs.dot.client.dot.hookers;

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

public class ReloadButtonHooker implements MainPanelViewToolbarConfiguratorHook {

   private final ToolbarService toolbarUtils;

   @Inject
   public ReloadButtonHooker(ToolbarService toolbarUtils) {
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
      if (!"_file_svg_preview".equals(view.getViewId()))
         return false;

      return true;
   }

}
