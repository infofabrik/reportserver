package net.datenwerke.gxtdto.client.forms.selection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.Event;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.ListLoadConfig;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoader;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridSelectionModel;

import net.datenwerke.gxtdto.client.forms.locale.FormsMessages;
import net.datenwerke.gxtdto.client.forms.selection.SelectionPopup.ItemsSelectedCallback;

/**
 * 
 *
 * @param <M>
 */
public class SimpleGridSelectionPopup<M> extends Grid<M> {

   private Map<ValueProvider<M, String>, String> displayProperties;
   private ListStore<M> allStore;
   private boolean allStoreLoaded = false;
   private boolean alwaysReloadData = false;

   private SelectionMode popupSelectionMode = SelectionMode.MULTI;
   private ListLoader<ListLoadConfig, ListLoadResult<M>> allItemsStoreloader;
   private String header;

   public SimpleGridSelectionPopup(String displayName, ValueProvider<M, String> displayProperty,
         ListStore<M> selectedStore, ListStore<M> allStore) {
      super(selectedStore, init(Collections.singletonMap(displayProperty, displayName), -1));
      this.allStore = allStore;
      this.displayProperties = Collections.singletonMap(displayProperty, displayName);

      initializeUI();
   }

   public SimpleGridSelectionPopup(Map<ValueProvider<M, String>, String> displayProperties, int columnWidth,
         ListStore<M> selectedStore, ListStore<M> allStore) {
      super(selectedStore, init(displayProperties, columnWidth));
      this.allStore = allStore;
      this.displayProperties = displayProperties;

      initializeUI();
   }

   private static <M> ColumnModel<M> init(Map<ValueProvider<M, String>, String> displayProperties, int columnWidth) {
      /* create columns */
      List<ColumnConfig<M, ?>> configs = new ArrayList<ColumnConfig<M, ?>>();

      /* add main column */
      for (ValueProvider<M, String> vp : displayProperties.keySet()) {
         ColumnConfig<M, String> cc = new ColumnConfig<M, String>(vp, 300, displayProperties.get(vp));
         if (columnWidth != -1)
            cc.setWidth(columnWidth);
         cc.setMenuDisabled(true);
         configs.add(cc);
      }

      return new ColumnModel<M>(configs);
   }

   private void initializeUI() {
      getView().setTrackMouseOver(false);

      getView().setEmptyText(FormsMessages.INSTANCE.noDataSelected());
      if (getColumnModel().getColumnCount() != 0)
         getView().setAutoExpandColumn(getColumnModel().getColumn(getColumnModel().getColumnCount() - 1));

      setHeight(200);

      /* set selection model - has to be set after column model */
      setSelectionModel(new GridSelectionModel<M>());

      /* from Grid constructor */
      setAllowTextSelection(false);
   }

   public void setPopupSelectionMode(SelectionMode mode) {
      this.popupSelectionMode = mode;
   }

   @Override
   protected void onDoubleClick(Event e) {
      super.onDoubleClick(e);

      SelectionPopup<M> selectionPanel = new SelectionPopup<M>(allStore, displayProperties);

      selectionPanel.setSelectionCallback(new ItemsSelectedCallback<M>() {

         @Override
         public void itemsSelected(List<M> items) {
            store.clear();
            store.addAll(items);
            SimpleGridSelectionPopup.this.itemsSelected(items);
         }
      });

      selectionPanel.setSelectionMode(popupSelectionMode);
      selectionPanel.setSelectedItems(store.getAll());
      if (null != allItemsStoreloader)
         selectionPanel.setLoader(allItemsStoreloader);
      if (!allStoreLoaded) {
         selectionPanel.loadData();
         allStoreLoaded = true && !alwaysReloadData;
      }

      if (null != header)
         selectionPanel.setHeading(header);
      selectionPanel.show();
   }

   protected void itemsSelected(List<M> selectedItems) {
   }

   public void reloadData() {
      allStoreLoaded = false;
   }

   public void alwaysReloadData() {
      alwaysReloadData = true;
   }

   public void setAllItemsStoreLoader(ListLoader<ListLoadConfig, ListLoadResult<M>> loader) {
      this.allItemsStoreloader = loader;
   }

   public void setHeader(String header) {
      this.header = header;
   }
}
