package net.datenwerke.gf.client.managerhelper.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.ListLoadConfig;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoader;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwNorthSouthContainer;
import net.datenwerke.gxtdto.client.forms.selection.SelectionMode;
import net.datenwerke.gxtdto.client.forms.selection.SelectionPopup;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.ui.helper.grid.DeletableRowsGrid;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class ToolBarEnhancedDeletableRowsGridForSelection extends DwContentPanel {

	protected final Grid grid;

	public ToolBarEnhancedDeletableRowsGridForSelection() {
		grid = null;
	}

	public ToolBarEnhancedDeletableRowsGridForSelection(ListStore store, ColumnModel cm,
			ListStore<? extends RsDto> allElementsStore, ListLoader allElementsLoader,
			Map allElementsDisplayProperties) {
		grid = new DeletableRowsGrid(store, cm);

		initPanel(grid, allElementsStore, allElementsLoader, allElementsDisplayProperties);
	}

	protected void initPanel(final Grid grid, final ListStore<? extends RsDto> allElementsStore,
			final ListLoader<ListLoadConfig, ListLoadResult<? extends RsDto>> allElementsLoader,
			final Map allElementsDisplayProperties) {
		setBodyBorder(false);
		setHeaderVisible(false);

		DwNorthSouthContainer nsContainer = new DwNorthSouthContainer();
		setWidget(nsContainer);

		/* toolbar */
		ToolBar tb = new DwToolBar();
		nsContainer.setNorthWidget(tb);
		nsContainer.setWidget(grid);

		/* add buttons */
		DwTextButton addButton = new DwTextButton(BaseMessages.INSTANCE.add(), BaseIcon.REMOVE);
		addButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {

				SelectionPopup selectionPanel = new SelectionPopup(allElementsStore, allElementsDisplayProperties) {
					@Override
					protected void itemsSelected(List selectedItems) {

						for (Object item : selectedItems)
							if (null == grid.getStore().findModel(item))
								grid.getStore().add(item);
					}
				};
				selectionPanel.setLoader(allElementsLoader);
				selectionPanel.loadData();
				selectionPanel.setSelectionMode(SelectionMode.MULTI);

				List selectedUsers = new ArrayList();
				for (Object member : grid.getStore().getAll())
					selectedUsers.add(member);
				selectionPanel.setSelectedItems(selectedUsers);
				selectionPanel.show();

			}
		});
		tb.add(addButton);

		/* remove buttons */
		DwTextButton removeButton = new DwTextButton(BaseMessages.INSTANCE.remove(), BaseIcon.DELETE);
		removeButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				List selectedItems = grid.getSelectionModel().getSelectedItems();
				for (Object node : selectedItems)
					if (null != grid.getStore().findModel(node))
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

	public Grid getGrid() {
		return grid;
	}
}