package net.datenwerke.gf.client.managerhelper.helper;

import java.util.Collection;
import java.util.List;

import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.selection.TreeSelectionPopup;
import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwNorthSouthContainer;
import net.datenwerke.gxtdto.client.forms.selection.SelectionMode;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.ui.helper.grid.DeletableRowsGrid;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

/**
 * 
 *
 */
public class ToolBarEnhancedDeletableRowsGridForTreeItemSelection extends DwContentPanel {
	
	protected final Grid grid; 
	
	public ToolBarEnhancedDeletableRowsGridForTreeItemSelection(){
		grid = null;
	}
	
	public ToolBarEnhancedDeletableRowsGridForTreeItemSelection(ListStore store, ColumnModel cm, UITree tree, Class<? extends AbstractNodeDto>... types){
		grid = new DeletableRowsGrid(store, cm);
		initPanel(grid, tree, types);
	}
	
	public ToolBarEnhancedDeletableRowsGridForTreeItemSelection(ListStore store, ColumnModel cm, UITree tree, Collection<Class<? extends AbstractNodeDto>> types){
		grid = new DeletableRowsGrid<AbstractNodeDto>(store, cm);
		if(null != types)
			initPanel(grid, tree, types.toArray(new Class[]{}));
		else 
			initPanel(grid, tree);
	}


	protected void initPanel(final Grid grid, final UITree tree, final Class<? extends AbstractNodeDto>... types) {
		setBodyBorder(false);
		setHeaderVisible(false);
		
		DwNorthSouthContainer nsContainer = new DwNorthSouthContainer();
		setWidget(nsContainer);
		
		/* toolbar */
		ToolBar tb = new DwToolBar();
		nsContainer.setNorthWidget(tb);
		nsContainer.setWidget(grid);
		
		/* add buttons */
		DwTextButton addButton = new DwTextButton(BaseMessages.INSTANCE.add(), BaseIcon.REMOVE);// BaseResources.INSTANCE.iconCogAdd16());
		addButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				TreeSelectionPopup popup = new TreeSelectionPopup(tree, types){
					@Override
					protected void itemsSelected(List<AbstractNodeDto> selectedItems) {
						for(AbstractNodeDto item : selectedItems)
							if(null == grid.getStore().findModel(item))
								grid.getStore().add(item);
					}
				};
				popup.setSelectedValues(grid.getStore().getAll());
				popup.setSelectionMode(SelectionMode.MULTI);
				popup.setHeaderIcon(BaseIcon.ADD);
				popup.setHeading(BaseMessages.INSTANCE.add());
				popup.show();
			}
		});
		tb.add(addButton);
		
		/* remove buttons */
		DwTextButton removeButton = new DwTextButton(BaseMessages.INSTANCE.remove(), BaseIcon.DELETE);
		removeButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				List<AbstractNodeDto> selectedItems = grid.getSelectionModel().getSelectedItems();
				for(AbstractNodeDto node : selectedItems)
					if(null != grid.getStore().findModel(node))
						grid.getStore().remove(node);
			}
		});
		tb.add(removeButton);
		
		DwTextButton removeAllButton = new DwTextButton(BaseMessages.INSTANCE.removeAll(), BaseIcon.DELETE);
		removeAllButton.addSelectHandler(new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
				grid.getStore().clear();
			}
		});
		tb.add(removeAllButton);
	}

	public Grid<AbstractNodeDto> getGrid() {
		return grid;
	}
}