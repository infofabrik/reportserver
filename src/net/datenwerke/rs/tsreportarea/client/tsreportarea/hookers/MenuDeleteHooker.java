package net.datenwerke.rs.tsreportarea.client.tsreportarea.hookers;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.gxtdto.client.baseex.widget.mb.DwConfirmMessageBox;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.servercommunication.callback.NotamCallback;
import net.datenwerke.rs.teamspace.client.teamspace.TeamSpaceUIService;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.TsDiskTreeManagerDao;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFolderDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskRootDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.hooks.TsFavoriteMenuHook;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.locale.TsFavoriteMessages;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.ItemSelector;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.TsDiskMainComponent;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.DialogHideHandler;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

public class MenuDeleteHooker implements TsFavoriteMenuHook {

	
	private final static TsFavoriteMessages messages = GWT.create(TsFavoriteMessages.class);
	
	
	private final TsDiskTreeManagerDao treeManagerDao;
	private final TeamSpaceUIService teamSpaceService;
	
	@Inject
	public MenuDeleteHooker(
		TsDiskTreeManagerDao treeManagerDao,
		TeamSpaceUIService teamSpaceService
		){
	
		/* store objects */
		this.treeManagerDao = treeManagerDao;
		this.teamSpaceService = teamSpaceService;
	}
	
	@Override
	public boolean addContextMenuEntries(Menu menu, List<AbstractTsDiskNodeDto> items, ItemSelector selector, final TsDiskMainComponent mainComponent) {
		if(null == items || items.isEmpty())
			return false;
		if(! teamSpaceService.isUser(mainComponent.getCurrentSpace()))
			return false;
		for(AbstractTsDiskNodeDto node : items)
			if(node instanceof TsDiskRootDto)
				return false;
		
		final List<AbstractNodeDto> nodes = new ArrayList<AbstractNodeDto>(items);
		
		/* delete */
		MenuItem deleteItem = new DwMenuItem(BaseMessages.INSTANCE.remove(), BaseIcon.DELETE);
		deleteItem.addSelectionHandler(new SelectionHandler<Item>() {
			
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				if(null != nodes && ! nodes.isEmpty()){
					String msg = null;
					if(nodes.size() == 1)
						msg = BaseMessages.INSTANCE.confirmDeleteMsg(nodes.get(0).toDisplayTitle());
					else
						msg = BaseMessages.INSTANCE.confirmDeleteManyMsg(nodes.size());
					
					ConfirmMessageBox cmb = new DwConfirmMessageBox(BaseMessages.INSTANCE.confirmDeleteTitle(), msg);
					cmb.addDialogHideHandler(new DialogHideHandler() {
						
						@Override
						public void onDialogHide(DialogHideEvent event) {
							if (event.getHideButton() == PredefinedButton.YES) {
								treeManagerDao.setState(mainComponent.getCurrentSpace());
								treeManagerDao.deleteNodesAndAskForMoreForce(nodes, new NotamCallback<Boolean>(messages.itemDeleted()){
									public void doOnSuccess(Boolean result) {
										if(! result)
											return;
										for(AbstractNodeDto node : nodes)
											mainComponent.getListStore().remove((AbstractTsDiskNodeDto)node);
									
										List<AbstractTsDiskNodeDto> currentPath = mainComponent.getCurrentPath();
										boolean found = false;
										for(AbstractNodeDto node : nodes){
											AbstractTsDiskNodeDto item = (AbstractTsDiskNodeDto)node;
											if(currentPath.contains(item)){
												int idx = 0;
												for(AbstractTsDiskNodeDto newParent : currentPath){
													if(newParent.equals(item)){
														if(idx == 1)
															mainComponent.folderOpened(null);
														else
															mainComponent.folderOpened((TsDiskFolderDto) currentPath.get(idx-1));
														
														found = true;
														break;
													}
													idx++;
												}
											}
										}
										
										if(!found && ! currentPath.isEmpty())
											mainComponent.folderOpened((TsDiskFolderDto) currentPath.get(0));
										else
											mainComponent.folderOpened(null);
									};
								});
							}
						}
					});
					
					cmb.show();
				}
			}
		});

		menu.add(deleteItem);
		
		return true;
	}

}
