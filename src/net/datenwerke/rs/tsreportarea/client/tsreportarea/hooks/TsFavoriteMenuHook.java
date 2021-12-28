package net.datenwerke.rs.tsreportarea.client.tsreportarea.hooks;

import java.util.List;

import com.sencha.gxt.widget.core.client.menu.Menu;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.ItemSelector;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.TsDiskMainComponent;

public interface TsFavoriteMenuHook extends Hook {

   public boolean addContextMenuEntries(Menu menu, List<AbstractTsDiskNodeDto> abstractTsFavoriteNodeDto,
         ItemSelector itemSelector, TsDiskMainComponent mainComponent);

}
