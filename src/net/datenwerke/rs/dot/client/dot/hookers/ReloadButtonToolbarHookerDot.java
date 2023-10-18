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
import net.datenwerke.rs.fileserver.client.fileserver.hookers.ReloadButtonToolbarHooker;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class ReloadButtonToolbarHookerDot extends ReloadButtonToolbarHooker implements MainPanelViewToolbarConfiguratorHook {

   @Inject
   public ReloadButtonToolbarHookerDot(ToolbarService toolbarUtils) {
      super(toolbarUtils);
   }

   @Override
   protected String getExpectedViewID() {
      return "_file_svg_preview";
   }
}
