package net.datenwerke.gf.client.managerhelper.hooks;

import java.util.List;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public interface MainPanelViewProviderHook extends Hook {

   public List<MainPanelView> mainPanelViewProviderHook_getView(AbstractNodeDto node);
}
