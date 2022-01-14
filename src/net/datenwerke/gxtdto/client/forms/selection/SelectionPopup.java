package net.datenwerke.gxtdto.client.forms.selection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.inject.Inject;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.Store;
import com.sencha.gxt.data.shared.Store.StoreFilter;
import com.sencha.gxt.data.shared.loader.ListLoadConfig;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoader;
import com.sencha.gxt.data.shared.loader.LoadEvent;
import com.sencha.gxt.data.shared.loader.LoadExceptionEvent;
import com.sencha.gxt.data.shared.loader.LoadExceptionEvent.LoadExceptionHandler;
import com.sencha.gxt.data.shared.loader.LoadHandler;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.dnd.core.client.DND.Feedback;
import com.sencha.gxt.dnd.core.client.DndDropEvent;
import com.sencha.gxt.dnd.core.client.GridDragSource;
import com.sencha.gxt.dnd.core.client.GridDropTarget;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.RowDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.RowDoubleClickEvent.RowDoubleClickHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.StoreFilterField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwBorderContainer;
import net.datenwerke.gxtdto.client.forms.locale.FormsMessages;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.stores.HasLoader;
import net.datenwerke.gxtdto.client.ui.helper.grid.DeletableRowsGrid;
import net.datenwerke.gxtdto.client.ui.helper.grid.ExtendedKeyNav;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * 
 *
 */
public class SelectionPopup<M> extends DwWindow {

   public interface ItemsSelectedCallback<M> {
      void itemsSelected(List<M> items);
   }

   protected final int PANEL_WIDTH = 800;
   protected final int PANEL_HEIGHT = 600;

   @Inject
   protected static ToolbarService toolbarService;

   protected SelectionMode selectionMode = SelectionMode.SINGLE;

   protected final Map<ValueProvider<M, String>, String> displayProperties;
   protected Map<ValueProvider<M, String>, String> displayPropertiesRight;

   protected final ListStore<M> allItemsStore;
   protected ListStore<M> selectedItemsStore;

   protected Component selectionComponent;
   protected Component selectedListComponent;

   protected Grid<M> selectionGrid;

   protected StoreFilterField<M> textFilter;

   protected ItemsSelectedCallback<M> selectionCallback = new ItemsSelectedCallback<M>() {

      @Override
      public void itemsSelected(List<M> items) {
      }
   };
   private DwTextButton addAllButton;
   private ListLoader<ListLoadConfig, ListLoadResult<M>> loader;
   private boolean loaderPrepared;

   /**
    * 
    * @param allItemsStore   the store with all selectable items
    * @param displayProperty a list with properties to be displayed
    */
   public SelectionPopup(ListStore<M> allItemsStore, Map<ValueProvider<M, String>, String> displayProperty) {
      this.displayProperties = displayProperty;
      this.allItemsStore = allItemsStore;

      if (allItemsStore instanceof HasLoader && ((HasLoader) allItemsStore).getLoader() instanceof ListLoader)
         setLoader(
               (ListLoader<ListLoadConfig, ListLoadResult<M>>) ((HasLoader<ListLoadConfig, ListLoadResult<M>>) allItemsStore)
                     .getLoader());

      /* initialize UI */
      initializeUI();
   }

   /**
    * 
    * @param allItemsStore the store with all selectable items
    */
   public SelectionPopup(ListStore<M> allItemsStore, Map<ValueProvider<M, String>, String> displayPropertyLeft,
         Map<ValueProvider<M, String>, String> displayPropertyRight) {
      this.displayProperties = displayPropertyLeft;
      this.displayPropertiesRight = displayPropertyRight;
      this.allItemsStore = allItemsStore;

      if (allItemsStore instanceof HasLoader && ((HasLoader) allItemsStore).getLoader() instanceof ListLoader)
         setLoader(
               (ListLoader<ListLoadConfig, ListLoadResult<M>>) ((HasLoader<ListLoadConfig, ListLoadResult<M>>) allItemsStore)
                     .getLoader());

      /* initialize UI */
      initializeUI();
   }

   public SelectionPopup(ListStore<M> store, ValueProvider<M, String> nameProvider) {
      this(store, Collections.singletonMap(nameProvider, BaseMessages.INSTANCE.name()));
   }

   protected void initializeUI() {
      setWidth(getDefaultPanelWidth());
      setHeight(getDefaultPanelHeight());
      setModal(true);

      getButtonBar().clear();

      DwBorderContainer borderContainer = new DwBorderContainer();
      setWidget(borderContainer);

      /* create store */
      selectedItemsStore = new ListStore<M>(allItemsStore.getKeyProvider());

      /* create components */
      selectionComponent = initSelectionComponent();
      selectedListComponent = initSelectedListComponent();

      /* define layout and add components */
      BorderLayoutData eastData = new BorderLayoutData(getDefaultEastSize());
      eastData.setMaxSize(2 * getDefaultEastSize());
      eastData.setSplit(true);

      BorderLayoutData centerData = new BorderLayoutData();
      borderContainer.setEastWidget(selectedListComponent, eastData);
      borderContainer.setCenterWidget(selectionComponent, centerData);

      /* create buttons */
      DwTextButton cnlButton = new DwTextButton(BaseMessages.INSTANCE.cancel());
      cnlButton.addSelectHandler(new SelectHandler() {

         @Override
         public void onSelect(SelectEvent event) {
            hide();
            SelectionPopup.this.cancelSelected();
         }
      });
      addButton(cnlButton);

      DwTextButton okButton = new DwTextButton(BaseMessages.INSTANCE.apply());
      okButton.addSelectHandler(new SelectHandler() {
         @Override
         public void onSelect(SelectEvent event) {
            hide();
            SelectionPopup.this.itemsSelected(new ArrayList<M>(selectedItemsStore.getAll()));
         }
      });
      addButton(okButton);
   }

   protected int getDefaultEastSize() {
      return PANEL_WIDTH / 2;
   }

   protected int getDefaultPanelHeight() {
      return PANEL_HEIGHT;
   }

   protected int getDefaultPanelWidth() {
      return PANEL_WIDTH;
   }

   protected void cancelSelected() {

   }

   public void loadData() {
      if (null != loader) {
         if (!loaderPrepared) {
            loaderPrepared = true;

            loader.addLoadHandler(new LoadResultListStoreBinding<ListLoadConfig, M, ListLoadResult<M>>(allItemsStore));

            /* disable parts */
            selectionComponent.disable();
            selectionComponent.mask(BaseMessages.INSTANCE.loadingMsg());

            /* load data */
            loader.addLoadHandler(new LoadHandler<ListLoadConfig, ListLoadResult<M>>() {

               @Override
               public void onLoad(LoadEvent<ListLoadConfig, ListLoadResult<M>> event) {
                  selectionComponent.enable();

                  /* possibly disable add all */
                  addAllButton.setEnabled(selectionMode != SelectionMode.SINGLE);

                  selectionComponent.unmask();
                  selectionGrid.unmask();
                  dataLoaded();
               }

            });
            loader.addLoadExceptionHandler(new LoadExceptionHandler<ListLoadConfig>() {
               @Override
               public void onLoadException(LoadExceptionEvent<ListLoadConfig> event) {
                  selectionComponent.unmask();
                  selectionComponent.enable();
                  selectionGrid.mask(BaseMessages.INSTANCE.error());
               }
            });
         }

         loader.load();
      } else {
         // throw new IllegalStateException("No loader given");
      }
   }

   protected void dataLoaded() {

   }

   protected Component initSelectedListComponent() {
      VerticalLayoutContainer vContainer = new VerticalLayoutContainer();

      /* create grid */
      final Grid<M> grid = createGridForSelected(selectedItemsStore);

      /* create toolbar */
      DwToolBar toolbar = new DwToolBar();
      vContainer.add(toolbar, new VerticalLayoutData(1, -1));

      DwTextButton removeButton = new DwTextButton(BaseMessages.INSTANCE.remove(), BaseIcon.DELETE);
      removeButton.addSelectHandler(new SelectHandler() {

         @Override
         public void onSelect(SelectEvent event) {
            List<M> selectedItems = grid.getSelectionModel().getSelectedItems();
            for (M node : selectedItems)
               if (null != selectedItemsStore.findModel(node))
                  selectedItemsStore.remove(node);
         }
      });
      toolbar.add(removeButton);

      DwTextButton removeAllButton = new DwTextButton(BaseMessages.INSTANCE.removeAll(), BaseIcon.DELETE);
      removeAllButton.addSelectHandler(new SelectHandler() {

         @Override
         public void onSelect(SelectEvent event) {
            selectedItemsStore.clear();
         }
      });
      toolbar.add(removeAllButton);

      /* add grid */
      vContainer.add(grid, new VerticalLayoutData(1, 1));

      DwContentPanel wrapper = DwContentPanel.newHeadlessInstance(vContainer);
      wrapper.addClassName("rs-sel-pop-r");

      return wrapper;
   }

   protected Component initSelectionComponent() {
      VerticalLayoutContainer vContainer = new VerticalLayoutContainer();

      /* toolbar */
      textFilter = new StoreFilterField<M>() {

         protected boolean doSelect(Store<M> store, M parent, M item, String filter) {
            /* get title and filter on title */
            for (ValueProvider<M, String> filterProperty : displayProperties.keySet()) {
               Object obj = filterProperty.getValue(item);
               String title = null == obj ? "" : obj.toString();

               if (null != title && title.toLowerCase().contains(filter.toLowerCase()))
                  return true;
            }

            return false;
         }

      };
      textFilter.bind(allItemsStore);

      /* create toolbar */
      DwToolBar toolbar = new DwToolBar();
      vContainer.add(toolbar, new VerticalLayoutData(1, -1));

      initSelectionToolbar(toolbar);

      /* add items to toolbar */
      toolbarService.addPlainToolbarItem(toolbar, BaseIcon.BINOCULARS);
      toolbar.add(textFilter);
      toolbar.add(new FillToolItem());

      addAllButton = new DwTextButton(FormsMessages.INSTANCE.addAll(), BaseIcon.CHECK);
      addAllButton.addSelectHandler(new SelectHandler() {
         @Override
         public void onSelect(SelectEvent event) {
            /* build new Set of selected */
            List<M> newSelection = new ArrayList<M>(selectedItemsStore.getAll());
            for (M model : allItemsStore.getAll())
               if (!newSelection.contains(model))
                  newSelection.add(model);

            selectedItemsStore.clear();
            selectedItemsStore.addAll(convertModelsForSelection(newSelection));
         }
      });
      addAllButton.disable();
      toolbar.add(addAllButton);

      /* create grid and add it wrapper panel */
      selectionGrid = createGridForSelection(allItemsStore);
      selectionGrid.getSelectionModel().setSelectionMode(com.sencha.gxt.core.client.Style.SelectionMode.MULTI);

      vContainer.add(selectionGrid, new VerticalLayoutData(1, 1));

      selectionGrid.getView().setEmptyText(FormsMessages.INSTANCE.noDataAvailable());

      /* selection */
      selectionGrid.addRowDoubleClickHandler(new RowDoubleClickHandler() {

         @Override
         public void onRowDoubleClick(RowDoubleClickEvent event) {
            M model = allItemsStore.get(event.getRowIndex());
            if (canBeAddedToSelection(model)) {
               if (selectionMode == SelectionMode.SINGLE)
                  selectedItemsStore.clear();
               selectedItemsStore.add(convertModelForSelection(model));

               if (selectionMode == SelectionMode.SINGLE) {
                  M selectedItem = allItemsStore.get(event.getRowIndex());
                  hide();
                  itemsSelected(new ArrayList<M>(Arrays.asList(selectedItem)));
               }
            }
         }
      });

      DwContentPanel wrapper = DwContentPanel.newHeadlessInstance(vContainer);
      wrapper.addClassName("rs-sel-pop-l");

      return wrapper;
   }

   protected void initSelectionToolbar(DwToolBar toolbar) {
   }

   protected M convertModelForSelection(M model) {
      return model;
   }

   protected List<? extends M> convertModelsForSelection(List<M> selection) {
      return selection;
   }

   protected boolean canBeAddedToSelection(M model) {
      if (null == model)
         return false;
      return (null == selectedItemsStore.findModel(model)) || selectionMode == SelectionMode.MULTI_PERMIT_DOUBLES;
   }

   protected Grid<M> createGridForSelection(ListStore<M> store) {
      /* configure columns */
      List<ColumnConfig<M, ?>> columns = getColumnConfigForSelectionGrid();

      /* create grid */
      selectionGrid = new Grid<M>(store, new ColumnModel<M>(columns));
      selectionGrid.getView().setAutoExpandColumn(getAutoExpandColumn(columns));
      new ExtendedKeyNav(selectionGrid) {
         protected void onSelectAll() {
            selectionGrid.getSelectionModel().selectAll();
         };
      };

      GridDragSource<M> tmpSource = new GridDragSource<M>(selectionGrid) {
         @Override
         protected void onDragDrop(DndDropEvent event) {
            // do not call super as to not remove item
         }
      };

      return selectionGrid;
   }

   protected ColumnConfig<M, ?> getAutoExpandColumn(List<ColumnConfig<M, ?>> columns) {
      return columns.get(columns.size() - 1);
   }

   protected List<ColumnConfig<M, ?>> getColumnConfigForSelectionGrid() {
      List<ColumnConfig<M, ?>> columns = new ArrayList<ColumnConfig<M, ?>>();

      for (ValueProvider<M, String> vp : displayProperties.keySet()) {
         ColumnConfig<M, ?> cc = new ColumnConfig<M, String>(vp, 300, displayProperties.get(vp));
         cc.setWidth(((PANEL_WIDTH / 2) - 25) / displayProperties.size());
         cc.setMenuDisabled(true);

         columns.add(cc);
      }

      return columns;
   }

   protected Grid<M> createGridForSelected(ListStore<M> store) {
      /* configure columns */
      List<ColumnConfig<M, ?>> columns = new ArrayList<ColumnConfig<M, ?>>();

      Map<ValueProvider<M, String>, String> selectedDisplayProperties;
      if (null != displayPropertiesRight)
         selectedDisplayProperties = displayPropertiesRight;
      else
         selectedDisplayProperties = displayProperties;

      for (ValueProvider<M, String> vp : selectedDisplayProperties.keySet()) {
         ColumnConfig<M, ?> cc = new ColumnConfig<M, String>(vp, 280, selectedDisplayProperties.get(vp));
         cc.setWidth(((PANEL_WIDTH / 2) - (25 / selectedDisplayProperties.size())) / selectedDisplayProperties.size());
         cc.setMenuDisabled(true);
         adaptColumnInGridForSelection(cc);

         columns.add(cc);
      }

      /* create grid */
      Grid<M> grid = new DeletableRowsGrid<M>(store, new ColumnModel<M>(columns));
      grid.getView().setAutoExpandColumn(columns.get(columns.size() - 1));
      grid.getView().setAutoExpandMin(columns.get(columns.size() - 1).getWidth());

      GridDropTarget<M> tmpTarget = new GridDropTarget<M>(grid) {
         @Override
         protected void onDragDrop(DndDropEvent e) {
            Object data = e.getData();
            if (data instanceof List) {
               List dataToAdd = new ArrayList();
               for (Object obj : (List) data) {
                  try {
                     if (canBeAddedToSelection((M) obj))
                        dataToAdd.add(obj);
                  } catch (ClassCastException ex) {
                     // swallow
                  }
               }

               List<M> models = (List<M>) prepareDropData(dataToAdd, true);
               if (models.size() > 0) {
                  if (selectionMode == SelectionMode.SINGLE) {
                     grid.getStore().clear();
                     M first = models.get(0);
                     models.clear();
                     models.add(first);
                  }

                  if (feedback == Feedback.APPEND) {
                     grid.getStore().addAll(models);
                  } else {
                     grid.getStore().addAll(insertIndex, models);
                  }
               }
               insertIndex = -1;
               activeItem = null;
            }
         }
      };

      return grid;
   }

   protected void adaptColumnInGridForSelection(ColumnConfig<M, ?> cc) {
   }

   protected void itemsSelected(List<M> selectedItems) {
      selectionCallback.itemsSelected(selectedItems);
   }

   public void setSelectionMode(SelectionMode mode) {
      this.selectionMode = mode;

      addAllButton.setEnabled(selectionMode != SelectionMode.SINGLE);

      if (selectionMode == SelectionMode.SINGLE)
         selectionGrid.getSelectionModel().setSelectionMode(com.sencha.gxt.core.client.Style.SelectionMode.SINGLE);
      else
         selectionGrid.getSelectionModel().setSelectionMode(com.sencha.gxt.core.client.Style.SelectionMode.MULTI);
   }

   public void setSelectedItem(M model) {
      selectedItemsStore.clear();
      selectedItemsStore.add(convertModelForSelection(model));
   }

   public void setSelectedItems(List<M> models) {
      selectedItemsStore.clear();
      selectedItemsStore.addAll(convertModelsForSelection(models));
   }

   @Override
   public void show() {
      super.show();

      selectionGrid.getStore().setEnableFilters(false);

      if (null != selectionGrid.getStore().getFilters()) {
         Iterator<StoreFilter<M>> it = selectionGrid.getStore().getFilters().iterator();
         Set<StoreFilter<M>> copyFilter = new HashSet<StoreFilter<M>>();
         while (it.hasNext())
            copyFilter.add(it.next());
         for (StoreFilter<M> filter : copyFilter)
            selectionGrid.getStore().removeFilter(filter);
      }

      textFilter.clear();
      textFilter.unbind(selectionGrid.getStore());
      textFilter.bind(selectionGrid.getStore());
   }

   public void setSelectionCallback(ItemsSelectedCallback<M> selectionCallback) {
      this.selectionCallback = selectionCallback;
   }

   public ItemsSelectedCallback<M> getSelectionCallback() {
      return selectionCallback;
   }

   public Map<ValueProvider<M, String>, String> getDisplayProperties() {
      return displayProperties;
   }

   public void setLoader(ListLoader<ListLoadConfig, ListLoadResult<M>> loader) {
      this.loader = loader;
   }
}
