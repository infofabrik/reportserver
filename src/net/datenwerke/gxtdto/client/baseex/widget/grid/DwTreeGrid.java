package net.datenwerke.gxtdto.client.baseex.widget.grid;

import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.tree.TreeStyle;
import com.sencha.gxt.widget.core.client.treegrid.TreeGrid;

import net.datenwerke.gxtdto.client.baseex.widget.DwTreePanel;

public class DwTreeGrid<M> extends TreeGrid<M> {

   public DwTreeGrid(TreeStore<M> store, ColumnModel<M> cm, ColumnConfig<M, ?> treeColumn) {
      super(store, cm, treeColumn);
      _setTreeStyle(this, new DwTreePanel.DwTreeStyle());
      setView(new DwTreeGridView<M>());
   }

   /* resort to violator pattern */
   protected native void _setTreeStyle(TreeGrid<M> grid, TreeStyle ts) /*-{
	  grid.@com.sencha.gxt.widget.core.client.treegrid.TreeGrid::style = ts;
	}-*/;

}
