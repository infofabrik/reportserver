package net.datenwerke.gf.client.treedb.helper.menu;

import com.google.inject.Inject;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gxtdto.client.objectinformation.ObjectInfoPanelService;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;
import net.datenwerke.treedb.client.treedb.locale.TreedbMessages;

public class InfoMenuItem extends TreeMenuItem {

   @Inject
   private static ObjectInfoPanelService objectInfoService;

   public InfoMenuItem() {
      super();

      setText(TreedbMessages.INSTANCE.infoMenuLabel());
      setIcon(BaseIcon.INFO.toImageResource());

      addMenuSelectionListener(new TreeMenuSelectionEvent() {

         public void menuItemSelected(final UITree tree, final AbstractNodeDto node) {
            objectInfoService.displayInformationOn(node);
         }
      });
   }
}