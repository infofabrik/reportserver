package net.datenwerke.gf.client.managerhelper.hooks;

import java.util.Collection;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public interface MainPanelViewProviderConfigHook extends Hook {

   boolean applies(String viewProviderId, AbstractNodeDto node);

   Collection<? extends MainPanelView> gatherViews(String viewProviderId, AbstractNodeDto node);

}
