package net.datenwerke.rs.tsreportarea.client.tsreportarea.hookers;

import java.util.List;

import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.resources.BaseResources;
import net.datenwerke.rs.teamspace.client.teamspace.TeamSpaceUIService;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.hooks.TsFavoriteMenuHook;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.locale.TsFavoriteMessages;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.ItemSelector;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.TsDiskMainComponent;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

public class MenuAddFolderHooker implements TsFavoriteMenuHook {
	
	private final TeamSpaceUIService teamSpaceService;
	
	@Inject
	public MenuAddFolderHooker(
		TeamSpaceUIService teamSpaceService
		){
	
		/* store objects */
		this.teamSpaceService = teamSpaceService;
	}
	
	
	@Override
	public boolean addContextMenuEntries(Menu menu, final List<AbstractTsDiskNodeDto> items, ItemSelector selector, 
			final TsDiskMainComponent mainComponent) {
		if(null != items && ! items.isEmpty())
			return false;
		if(! teamSpaceService.isUser(mainComponent.getCurrentSpace()))
			return false;
		if(! selector.isInFolder())
			return false;
		
		MenuItem addItem = new DwMenuItem(TsFavoriteMessages.INSTANCE.addFolderText(), BaseIcon.FOLDER_ADD);
		menu.add(addItem);
		addItem.addSelectionHandler(new SelectionHandler<Item>() {
			
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				mainComponent.displayAddFolderDialog();
			}
		});
		
		return true;
	}


}
