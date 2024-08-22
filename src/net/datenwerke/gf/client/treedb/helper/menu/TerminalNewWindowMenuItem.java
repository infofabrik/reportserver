package net.datenwerke.gf.client.treedb.helper.menu;

import java.util.HashMap;

import com.google.gwt.http.client.URL;
import com.google.inject.Provider;

import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.utilityservices.UtilsUIService;
import net.datenwerke.rs.terminal.client.terminal.TerminalDao;
import net.datenwerke.rs.terminal.client.terminal.TerminalUIModule;
import net.datenwerke.rs.terminal.client.terminal.locale.TerminalMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class TerminalNewWindowMenuItem extends TreeMenuItem {

   public TerminalNewWindowMenuItem(Provider<UtilsUIService> utilsUIServiceProvider, TerminalDao terminalDao) {
      super();
      setIcon(BaseIcon.TERMINAL);
      setText(TerminalMessages.INSTANCE.openTerminalSessionHereInNewWindow());
      String url = "#" + TerminalUIModule.TERMINAL_ID;
      addMenuSelectionListener((tree, node) -> {
         terminalDao.init(node, tree.getTreeManager().getBaseNodeMapper(),
               new RsAsyncCallback<HashMap<String, String>>() {
                  @Override
                  public void onSuccess(HashMap<String, String> result) {
                     utilsUIServiceProvider.get().redirectInPopup(url + "/terminalpath:" + URL.encodeQueryString(result.get("pathWay")));
                  }
               });
      });
   }
}
