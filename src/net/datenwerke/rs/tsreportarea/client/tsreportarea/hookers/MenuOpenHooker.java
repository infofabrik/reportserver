package net.datenwerke.rs.tsreportarea.client.tsreportarea.hookers;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.teamspace.client.teamspace.TeamSpaceUIService;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.TsDiskTreeManagerDao;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.hooks.TsFavoriteMenuHook;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.locale.TsFavoriteMessages;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.ItemSelector;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.TsDiskMainComponent;

public class MenuOpenHooker implements TsFavoriteMenuHook {

	
	private final static TsFavoriteMessages messages = GWT.create(TsFavoriteMessages.class);
	
	
	private final TsDiskTreeManagerDao treeManagerDao;
	private final TeamSpaceUIService teamSpaceService;
	
	@Inject
	public MenuOpenHooker(
		TsDiskTreeManagerDao treeManagerDao,
		TeamSpaceUIService teamSpaceService
		){
	
		/* store objects */
		this.treeManagerDao = treeManagerDao;
		this.teamSpaceService = teamSpaceService;
	}
	
	@Override
	public boolean addContextMenuEntries(Menu menu, final List<AbstractTsDiskNodeDto> items, ItemSelector selector, final TsDiskMainComponent mainComponent) {
		if(null == items || items.isEmpty() || items.size() > 1)
			return false;
		if(! teamSpaceService.isGuest(mainComponent.getCurrentSpace()))
			return false;

		/* open */
		MenuItem openItem = new DwMenuItem(BaseMessages.INSTANCE.open(), BaseIcon.ROTATE_RIGHT);
		openItem.addSelectionHandler(new SelectionHandler<Item>() {
			
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				mainComponent.itemOpened(items.get(0));
			}
		});

		menu.add(openItem);
		
		return true;
	}

}
