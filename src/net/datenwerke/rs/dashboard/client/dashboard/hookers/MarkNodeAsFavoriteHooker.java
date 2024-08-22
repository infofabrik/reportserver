package net.datenwerke.rs.dashboard.client.dashboard.hookers;

import java.util.List;

import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.rs.dashboard.client.dashboard.DashboardDao;
import net.datenwerke.rs.dashboard.client.dashboard.locale.DashboardMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskRootDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.hooks.TsFavoriteMenuHook;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.ItemSelector;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.TsDiskMainComponent;

public class MarkNodeAsFavoriteHooker implements TsFavoriteMenuHook {

   private DashboardDao dashboardDao;

   @Inject
   public MarkNodeAsFavoriteHooker(DashboardDao dashboardDao) {

      this.dashboardDao = dashboardDao;
   }

   @Override
   public boolean addContextMenuEntries(Menu menu, final List<AbstractTsDiskNodeDto> items, ItemSelector selector,
         final TsDiskMainComponent mainComponent) {
      if (null == items || items.isEmpty() || items.size() > 1)
         return false;
      if (items.get(0) instanceof TsDiskRootDto)
         return false;

      MenuItem addItem = new DwMenuItem(DashboardMessages.INSTANCE.addToFavorites(), BaseIcon.STAR_O);
      menu.add(addItem);
      addItem.addSelectionHandler(event -> dashboardDao.addToFavorites(items.get(0), new RsAsyncCallback<Void>()));

      return true;
   }

}
