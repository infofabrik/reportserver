package net.datenwerke.rs.base.client.reportengines.table.helpers.filter;

import java.util.List;

import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwNorthSouthContainer;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.rs.base.client.reportengines.table.columnfilter.FilterService;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

import com.google.inject.Inject;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

/**
 * 
 *
 * @param <M>
 */
public abstract class GridView<M> extends DwNorthSouthContainer{

	@Inject
	protected static FilterService filterService;
	
	protected final ListStore<M> store;
	protected final SelectionPanel<M> selectionPanel;
	protected final ColumnDto column;

	private Grid<M> grid;
	
	public GridView(ListStore<M> store, ColumnDto column, SelectionPanel<M> selectionPanel) {
		this.store = store;
		this.selectionPanel = selectionPanel;
		this.column = column;
		
		setBorders(false);
		
		/* create grid */
		grid = createGrid(store);
		createGridDropTarget(grid);
		
		/* create wrapper */
		createToolbar();
		setWidget(grid);
	}
	
	
	protected void createToolbar() {
		ToolBar toolbar = new DwToolBar();
		setNorthWidget(toolbar);
		
		/* remove buttons */
		DwTextButton removeButton = new DwTextButton(BaseMessages.INSTANCE.remove(), BaseIcon.DELETE);
		removeButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				List<M> selectedItems = grid.getSelectionModel().getSelectedItems();
				for(M node : selectedItems)
					store.remove(node);
			}
		});

		toolbar.add(removeButton);
		DwTextButton removeAllButton = new DwTextButton(BaseMessages.INSTANCE.removeAll(), BaseIcon.DELETE);
		removeAllButton.addSelectHandler(new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
				store.clear();
			}
		});

		toolbar.add(removeAllButton);
	}

	protected abstract Grid<M> createGrid(ListStore<M> store);
		
	protected abstract void createGridDropTarget(Grid<M> grid);
}
