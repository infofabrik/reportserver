package net.datenwerke.gf.client.treedb.helper.menu;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class ReloadMenuItem extends TreeMenuItem {

   public ReloadMenuItem() {
      super();

      setIcon(BaseIcon.REFRESH);
      setText(BaseMessages.INSTANCE.reload());
      addMenuSelectionListener(new TreeMenuSelectionEvent() {

         public void menuItemSelected(final UITree tree, final AbstractNodeDto node) {
            tree.reload(node);
         }
      });
   }

}