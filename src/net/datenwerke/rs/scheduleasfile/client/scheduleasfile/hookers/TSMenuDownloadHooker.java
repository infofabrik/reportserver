package net.datenwerke.rs.scheduleasfile.client.scheduleasfile.hookers;

import java.util.List;

import net.datenwerke.gf.client.uiutils.ClientDownloadHelper;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.ScheduleAsFileUiModule;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.dto.ExecutedReportFileReferenceDto;
import net.datenwerke.rs.teamspace.client.teamspace.TeamSpaceUIService;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.TsDiskTreeManagerDao;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.hooks.TsFavoriteMenuHook;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.locale.TsFavoriteMessages;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.ItemSelector;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.TsDiskMainComponent;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

public class TSMenuDownloadHooker implements TsFavoriteMenuHook {

private final static TsFavoriteMessages messages = GWT.create(TsFavoriteMessages.class);
	
	
	private final TsDiskTreeManagerDao treeManagerDao;
	private final TeamSpaceUIService teamSpaceService;
	private final ClientDownloadHelper clientDownloadHelper;
	
	@Inject
	public TSMenuDownloadHooker(
		TsDiskTreeManagerDao treeManagerDao,
		TeamSpaceUIService teamSpaceService, 
		ClientDownloadHelper clientDownloadHelper
		){
	
		/* store objects */
		this.treeManagerDao = treeManagerDao;
		this.teamSpaceService = teamSpaceService;
		this.clientDownloadHelper = clientDownloadHelper;
	}
	
	@Override
	public boolean addContextMenuEntries(Menu menu, final List<AbstractTsDiskNodeDto> items, ItemSelector selector, final TsDiskMainComponent mainComponent) {
		if(null == items || items.isEmpty() || items.size() > 1)
			return false;
		if(! teamSpaceService.isGuest(mainComponent.getCurrentSpace()))
			return false;

		final AbstractTsDiskNodeDto item = items.get(0);
		if(! (item instanceof ExecutedReportFileReferenceDto))
			return false;

		MenuItem downloadItem = new DwMenuItem(BaseMessages.INSTANCE.save(), BaseIcon.DOWNLOAD);
		downloadItem.addSelectionHandler(new SelectionHandler<Item>() {
			
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				String id = String.valueOf(item.getId());
				String url = GWT.getModuleBaseURL() +  ScheduleAsFileUiModule.EXPORT_SERVLET + "?fileId=" + id + "&download=true"; //$NON-NLS-1$
				
				clientDownloadHelper.triggerDownload(url);
			}
		});

		menu.add(downloadItem);
		
		return true;
	}

}
