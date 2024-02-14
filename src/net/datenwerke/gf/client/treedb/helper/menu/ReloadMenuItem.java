package net.datenwerke.gf.client.treedb.helper.menu;

import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class ReloadMenuItem extends TreeMenuItem {

   public ReloadMenuItem() {
      super();

      setIcon(BaseIcon.REFRESH);
      setText(BaseMessages.INSTANCE.reload());
      addMenuSelectionListener((tree, node) -> tree.reload(node));
   }

}