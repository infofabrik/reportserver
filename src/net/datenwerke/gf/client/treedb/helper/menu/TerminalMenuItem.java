package net.datenwerke.gf.client.treedb.helper.menu;
import com.google.inject.Provider;

import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.terminal.client.terminal.TerminalUIService;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class TerminalMenuItem extends TreeMenuItem {

   public TerminalMenuItem(Provider<TerminalUIService> terminalUIServiceProvider) {
      super();
      setIcon(BaseIcon.TERMINAL);
      setText(BaseMessages.INSTANCE.openTerminalSessionHere());
      addMenuSelectionListener((tree, node) -> {
         terminalUIServiceProvider.get().displayTerminalWindow(node, tree.getTreeManager().getBaseNodeMapper());
      });
   }
}
