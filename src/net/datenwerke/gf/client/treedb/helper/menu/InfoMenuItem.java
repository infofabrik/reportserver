package net.datenwerke.gf.client.treedb.helper.menu;

import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.objectinformation.ObjectInfoPanelService;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.client.treedb.locale.TreedbMessages;

public class InfoMenuItem extends TreeMenuItem {

   @Inject
   private static ObjectInfoPanelService objectInfoService;

   public InfoMenuItem() {
      super();

      setText(TreedbMessages.INSTANCE.infoMenuLabel());
      setIcon(BaseIcon.INFO.toImageResource());

      addMenuSelectionListener((tree, node) -> objectInfoService.displayInformationOn(node));
   }
}