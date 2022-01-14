package net.datenwerke.gf.client.treedb.selection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.Store.StoreFilter;
import com.sencha.gxt.data.shared.TreeStore.TreeNode;
import com.sencha.gxt.dnd.core.client.DndDragStartEvent;
import com.sencha.gxt.dnd.core.client.DndDropEvent;
import com.sencha.gxt.dnd.core.client.DropTarget;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.NorthSouthContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.gf.client.managerhelper.hooks.ManagerHelperTreeToolbarEnhancerHook;
import net.datenwerke.gf.client.managerhelper.hooks.TreeSelectionToolbarEnhancerHook;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.dnd.UITreeDragSource;
import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwBorderContainer;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwNorthSouthContainer;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwAlertMessageBox;
import net.datenwerke.gxtdto.client.dialog.error.DetailErrorDialog;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.forms.selection.SelectionMode;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.ui.helper.grid.DeletableRowsGrid;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.gxtdto.client.utils.modelkeyprovider.DtoIdModelKeyProvider;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

/**
 * 
 *
 */
public class TreeSelectionPopup extends DwWindow {

   @Inject
   private static ToolbarService toolbarService;

   @Inject
   private static HookHandlerService hookHandler;

   private SelectionMode selectionMode = SelectionMode.SINGLE;
   private final UITree treePanel;
   private HandlerRegistration treePanelDoubleClickHandlerRegistration;
   private final Class<? extends RsDto>[] types;

   private ListStore<AbstractNodeDto> selectedItemsStore;
   private Component selectionComponent;

   private SelectionFilter selectionFilter = SelectionFilter.DUMMY_FILTER;

   public TreeSelectionPopup(UITree treePanel, Class<? extends RsDto>... types) {
      this.treePanel = treePanel;
      this.types = types;
      this.treePanel.setTypes(types);

      /* initialize UI */
      initializeUI();

   }

   private void initializeUI() {
      setWidth(700);
      setHeight(525);
      setBodyBorder(true);
      setModal(true);

      DwBorderContainer borderContainer = new DwBorderContainer();
      setWidget(borderContainer);

      /* selection mode */
      treePanel.getSelectionModel().setSelectionMode(com.sencha.gxt.core.client.Style.SelectionMode.SINGLE);

      /* create buttons */
      DwTextButton cnlButton = new DwTextButton(BaseMessages.INSTANCE.cancel());
      cnlButton.addSelectHandler(new SelectHandler() {

         @Override
         public void onSelect(SelectEvent event) {
            hide();
         }
      });
      addButton(cnlButton);

      DwTextButton okButton = new DwTextButton(BaseMessages.INSTANCE.apply());
      okButton.addSelectHandler(new SelectHandler() {

         @Override
         public void onSelect(SelectEvent event) {
            onValueSelected();
         }
      });
      addButton(okButton);

      /* create components */
      selectionComponent = initSelectionComponent();
      Component selectedListComponent = initSelectedListComponent();

      /* define layout and add components */
      BorderLayoutData westData = new BorderLayoutData(370);

      borderContainer.setWestWidget(selectionComponent, westData);
      borderContainer.setCenterWidget(selectedListComponent);
   }

   protected void validateSelectedItems(List<AbstractNodeDto> selectedItems,
         AsyncCallback<Boolean> itemsValidatedCallback) {
      itemsValidatedCallback.onSuccess(true);
   }

   private void onValueSelected() {
      final List<AbstractNodeDto> selectedItems = selectedItemsStore.getAll();
      validateSelectedItems(selectedItems, new AsyncCallback<Boolean>() {

         @Override
         public void onFailure(Throwable caught) {
            new DetailErrorDialog(caught).show();
         }

         @Override
         public void onSuccess(Boolean result) {
            if (result) {
               hide();
               itemsSelected(selectedItems);
            }
         }
      });
   }

   private Component initSelectedListComponent() {
      DwContentPanel wrapper = DwContentPanel.newInlineInstance();
      wrapper.addClassName("rs-sel-pop-r");

      NorthSouthContainer nsContainer = new DwNorthSouthContainer();
      wrapper.setWidget(nsContainer);

      /* create store */
      selectedItemsStore = new ListStore<AbstractNodeDto>(new DtoIdModelKeyProvider());

      /* configure columns */
      List<ColumnConfig<AbstractNodeDto, ?>> columns = new ArrayList<ColumnConfig<AbstractNodeDto, ?>>();

      ColumnConfig<AbstractNodeDto, String> nameConfig = new ColumnConfig<AbstractNodeDto, String>(
            Dto.DISPLAY_STRING_VALUE_PROVIDER, 300, BaseMessages.INSTANCE.name());
      columns.add(nameConfig);

      /* create grid */
      final Grid<AbstractNodeDto> grid = new DeletableRowsGrid<AbstractNodeDto>(selectedItemsStore,
            new ColumnModel<AbstractNodeDto>(columns));
      grid.setHideHeaders(true);
      grid.setHeight(400);

      /* create toolbar */
      ToolBar toolbar = new DwToolBar();
      nsContainer.setNorthWidget(toolbar);

      DwTextButton removeButton = new DwTextButton(BaseMessages.INSTANCE.remove(), BaseIcon.DELETE);
      removeButton.addSelectHandler(new SelectHandler() {

         @Override
         public void onSelect(SelectEvent event) {
            List<AbstractNodeDto> selectedItems = grid.getSelectionModel().getSelectedItems();
            for (AbstractNodeDto node : selectedItems)
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

      /* drop target */
      new DropTarget(wrapper) {

         @Override
         protected void onDragDrop(DndDropEvent event) {
            Object data = event.getData();
            if (data instanceof List) {
               List<?> list = (List) data;
               if (null != list && list.size() > 0) {
                  if (SelectionMode.SINGLE == getSelectionMode()) {
                     grid.getStore().clear();
                     if (null != list && !list.isEmpty() && list.get(0) instanceof TreeNode)
                        grid.getStore().add(((TreeNode<AbstractNodeDto>) list.get(0)).getData());
                  } else {
                     for (Object model : list)
                        if (model instanceof TreeNode)
                           if (null == grid.getStore().findModel(((TreeNode<AbstractNodeDto>) model).getData()))
                              grid.getStore().add(((TreeNode<AbstractNodeDto>) model).getData());
                  }
               }
            }
         }
      };

      /* add grid */
      nsContainer.setWidget(grid);

      return wrapper;
   }

   private Component initSelectionComponent() {
      /* wrapper */
      DwContentPanel wrapper = DwContentPanel.newInlineInstance();
      wrapper.addClassName("rs-sel-pop-l");

      VerticalLayoutContainer nsContainer = new VerticalLayoutContainer();
      wrapper.setWidget(nsContainer);

      /* draggable */
      new UITreeDragSource(treePanel) {
         @Override
         protected void onDragStart(DndDragStartEvent event) {
            super.onDragStart(event);
            List<TreeNode<AbstractNodeDto>> list = (List<TreeNode<AbstractNodeDto>>) event.getData();
            if (null != list && list.size() > 0) {
               for (TreeNode<AbstractNodeDto> model : list) {
                  if (!isAllowedType(model.getData().getClass())
                        || (null != selectionFilter.allowSelectionOf(model.getData()))) {
                     event.setCancelled(true);
                     return;
                  }
               }
            }
         }
      };

      /* create toolbar */
      ToolBar toolbar = new DwToolBar();
      nsContainer.add(toolbar, new VerticalLayoutData(1, -1));

      Collection<TreeSelectionToolbarEnhancerHook> tbEnhancer = hookHandler
            .getHookers(TreeSelectionToolbarEnhancerHook.class);
      for (TreeSelectionToolbarEnhancerHook enhancer : tbEnhancer)
         enhancer.treeNavigationToolbarEnhancerHook_addLeft(toolbar, treePanel, treePanel);
      toolbar.add(new FillToolItem());
      for (ManagerHelperTreeToolbarEnhancerHook enhancer : tbEnhancer)
         enhancer.treeNavigationToolbarEnhancerHook_addRight(toolbar, treePanel, treePanel);

      addDOMHandlers();

      /* configure tree */
      nsContainer.add(treePanel, new VerticalLayoutData(1, 1));

      return wrapper;
   }

   protected boolean isAllowedType(Class<?> needle) {
      if (null == types || types.length == 0)
         return true;

      while (null != needle) {
         for (Class<?> type : types)
            if (type.equals(needle))
               return true;
         needle = needle.getSuperclass();
      }
      return false;
   }

   public void setSelectionMode(SelectionMode selectionMode) {
      this.selectionMode = selectionMode;
      if (selectionMode == SelectionMode.MULTI)
         treePanel.getSelectionModel().setSelectionMode(com.sencha.gxt.core.client.Style.SelectionMode.MULTI);
   }

   public SelectionMode getSelectionMode() {
      return selectionMode;
   }

   /**
    * To be overriden
    * 
    * @param selectedItems
    */
   protected void itemsSelected(List<AbstractNodeDto> selectedItems) {
      // override
   }

   public void setSelectedValues(AbstractNodeDto... selectedDTOs) {
      for (AbstractNodeDto dto : selectedDTOs)
         selectedItemsStore.add(dto);
   }

   public void setSelectedValues(Collection<AbstractNodeDto> selectedDTOs) {
      for (AbstractNodeDto dto : selectedDTOs)
         selectedItemsStore.add(dto);
   }

   @Override
   public void show() {
      addDOMHandlers();
      super.show();

      treePanel.getStore().removeFilters();

      if (null != treePanel.getStore().getFilters()) {
         Iterator<StoreFilter<AbstractNodeDto>> it = treePanel.getStore().getFilters().iterator();
         Set<StoreFilter<AbstractNodeDto>> copyFilter = new HashSet<StoreFilter<AbstractNodeDto>>();
         while (it.hasNext())
            copyFilter.add(it.next());
         for (StoreFilter<AbstractNodeDto> filter : copyFilter)
            treePanel.getStore().removeFilter(filter);
      }

   }

   private void addDOMHandlers() {
      /* selection */
      if (null == treePanelDoubleClickHandlerRegistration) {
         treePanelDoubleClickHandlerRegistration = treePanel.addDomHandler(new DoubleClickHandler() {

            @Override
            public void onDoubleClick(DoubleClickEvent event) {
               AbstractNodeDto dto = treePanel.getEventTargetNode(event);
               if (null != dto) {
                  if (isAllowedType(dto.getClass())) {
                     String error = selectionFilter.allowSelectionOf(dto);
                     if (null != error)
                        new DwAlertMessageBox(BaseMessages.INSTANCE.error(), error).show();
                     else if (null == selectedItemsStore.findModel(dto)) {
                        if (selectionMode == SelectionMode.SINGLE)
                           selectedItemsStore.clear();
                        selectedItemsStore.add(dto);

                        if (selectionMode == SelectionMode.SINGLE) {
                           onValueSelected();
                        }
                     }
                  }
               }
            }
         }, DoubleClickEvent.getType());
      }
   }

   private void removeDOMHandlers() {
      if (null != treePanelDoubleClickHandlerRegistration) {
         treePanelDoubleClickHandlerRegistration.removeHandler();
         treePanelDoubleClickHandlerRegistration = null;
      }
   }

   @Override
   public void hide() {
      super.hide();
      Scheduler.get().scheduleDeferred(new ScheduledCommand() {
         @Override
         public void execute() {
            treePanel.getStore().removeFilters();
            removeDOMHandlers();
         }
      });
   }

   public SelectionFilter getSelectionFilter() {
      return selectionFilter;
   }

   public void setSelectionFilter(SelectionFilter selectionFilter) {
      this.selectionFilter = selectionFilter;
   }

}
