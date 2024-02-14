package net.datenwerke.rs.terminal.client.terminal.helper.menu;
import com.google.inject.Provider;

import net.datenwerke.gf.client.treedb.helper.menu.TreeMenuItem;
import net.datenwerke.rs.terminal.client.terminal.TerminalUIService;
import net.datenwerke.rs.terminal.client.terminal.locale.TerminalMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class TerminalMenuItem extends TreeMenuItem {

   public TerminalMenuItem(Provider<TerminalUIService> terminalUIServiceProvider) {
      super();
      setIcon(BaseIcon.TERMINAL);
      setText(TerminalMessages.INSTANCE.openTerminalSessionHere());
      addMenuSelectionListener((tree, node) -> {
         terminalUIServiceProvider.get().displayTerminalWindow(node, tree.getTreeManager().getBaseNodeMapper());
      });
   }
}
