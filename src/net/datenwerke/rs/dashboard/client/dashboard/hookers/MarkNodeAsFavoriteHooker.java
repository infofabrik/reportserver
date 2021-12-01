package net.datenwerke.rs.dashboard.client.dashboard.hookers;

import java.util.List;

import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.resources.BaseResources;
import net.datenwerke.rs.dashboard.client.dashboard.DashboardDao;
import net.datenwerke.rs.dashboard.client.dashboard.locale.DashboardMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskRootDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.hooks.TsFavoriteMenuHook;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.ItemSelector;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.TsDiskMainComponent;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

public class MarkNodeAsFavoriteHooker implements TsFavoriteMenuHook {
	
	private DashboardDao dashboardDao;


	@Inject
	public MarkNodeAsFavoriteHooker(
			DashboardDao dashboardDao
		){
		
		this.dashboardDao = dashboardDao;
	}
	
	
	@Override
	public boolean addContextMenuEntries(Menu menu, final List<AbstractTsDiskNodeDto> items, ItemSelector selector, 
			final TsDiskMainComponent mainComponent) {
		if(null == items || items.isEmpty() || items.size() > 1)
			return false;
		if(items.get(0) instanceof TsDiskRootDto)
			return false;
		
		MenuItem addItem = new DwMenuItem(DashboardMessages.INSTANCE.addToFavorites(), BaseIcon.STAR_O);
		menu.add(addItem);
		addItem.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				dashboardDao.addToFavorites(items.get(0), new RsAsyncCallback<Void>());
			}
		});

		
		return true;
	}

}
