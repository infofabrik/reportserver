package net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.listviews;

import java.util.List;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.core.client.util.DelayedTask;
import com.sencha.gxt.dnd.core.client.DND.Feedback;
import com.sencha.gxt.dnd.core.client.DndDragCancelEvent;
import com.sencha.gxt.dnd.core.client.DndDragEnterEvent;
import com.sencha.gxt.dnd.core.client.DndDragStartEvent;
import com.sencha.gxt.dnd.core.client.DndDropEvent;
import com.sencha.gxt.dnd.core.client.GridDragSource;
import com.sencha.gxt.dnd.core.client.GridDropTarget;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.event.RowDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.RowDoubleClickEvent.RowDoubleClickHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent.SelectionChangedHandler;

import net.datenwerke.gxtdto.client.ui.helper.grid.ExtendedKeyNav;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.TsDiskUIService;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFolderDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.hooks.TsFavoriteListViewHook;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.ItemSelector;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.TsDiskMainComponent;

/**
 * 
 *
 */
public class TsDiskGridListView implements TsFavoriteListViewHook {

   private final TsDiskUIService favoriteService;
   protected int doubleClick;

   @Inject
   public TsDiskGridListView(TsDiskUIService favoriteService) {

      /* store objects */
      this.favoriteService = favoriteService;
   }

   @Override
   public ImageResource getViewIcon() {
      return BaseIcon.ITEMS_DETAIL.toImageResource();
   }

   @Override
   public TsDiskListView getListView(final TsDiskMainComponent mainComponent) {
      /* create model */
      ColumnModel<AbstractTsDiskNodeDto> cm = new ColumnModel<AbstractTsDiskNodeDto>(
            favoriteService.createGridColumnConfig(AbstractTsDiskNodeDto.class));

      /* create grid */
      final Grid<AbstractTsDiskNodeDto> grid = new Grid<AbstractTsDiskNodeDto>(mainComponent.getListStore(), cm) {
         @Override
         protected void onMouseDown(Event e) {
            super.onMouseDown(e);

            com.google.gwt.dom.client.Element target = com.google.gwt.dom.client.Element.as(e.getEventTarget());
            int rowIndex = view.findRowIndex(target);
            if (rowIndex == -1)
               this.getSelectionModel().deselectAll();
         }
      };
      grid.getSelectionModel().setSelectionMode(SelectionMode.MULTI);
      new ExtendedKeyNav(grid) {
         @Override
         protected void onSelectAll() {
            grid.getSelectionModel().selectAll();
         };

         @Override
         public void onDelete(NativeEvent evt) {
         }
      };

      mainComponent.setMenuChanger(grid, new ItemSelector() {
         @Override
         public List<AbstractTsDiskNodeDto> getSelectedItems() {
            return grid.getSelectionModel().getSelectedItems();
         }

         @Override
         public boolean isInFolder() {
            return true;
         }
      });

      /* make name column auto expand */
      grid.getView().setAutoExpandColumn(cm.getColumn(2));

      /* select */
      grid.getSelectionModel().addSelectionChangedHandler(new SelectionChangedHandler<AbstractTsDiskNodeDto>() {
         @Override
         public void onSelectionChanged(SelectionChangedEvent<AbstractTsDiskNodeDto> event) {
            if (event.getSelection().isEmpty())
               return;

            final AbstractTsDiskNodeDto item = event.getSelection().get(0);
            DelayedTask task = new DelayedTask() {

               @Override
               public void onExecute() {
                  if (!mainComponent.isInDrag() && doubleClick <= 0)
                     mainComponent.itemSelected(item);
                  else
                     grid.getSelectionModel().deselectAll();
               }
            };

            if (null != item)
               task.delay(200);
            else
               task.cancel();
         }
      });

      /* open */
      grid.addRowDoubleClickHandler(new RowDoubleClickHandler() {
         @Override
         public void onRowDoubleClick(RowDoubleClickEvent event) {
            doubleClick++;
            AbstractTsDiskNodeDto item = grid.getStore().get(event.getRowIndex());
            if (null != item)
               mainComponent.itemOpened(item);

            DelayedTask task = new DelayedTask() {
               @Override
               public void onExecute() {
                  doubleClick--;
                  if (doubleClick < 0)
                     doubleClick = 0;
               }
            };

            task.delay(500);
         }
      });

      if (mainComponent.allowDrag()) {
         GridDragSource<AbstractTsDiskNodeDto> dragSource = new GridDragSource<AbstractTsDiskNodeDto>(grid) {

            @Override
            protected void onDragStart(DndDragStartEvent event) {
               super.onDragStart(event);

               /*
                * make sure something is really dragged .. for some reason a drag operation is
                * started (but not stopped) when changing column sizes
                */
               Object data = event.getData();
               if (null != data && data instanceof List && !((List) data).isEmpty()
                     && ((List) data).get(0) instanceof AbstractTsDiskNodeDto) {
                  mainComponent.setInDrag(true);
               }
            }

            @Override
            protected void onDragCancelled(DndDragCancelEvent event) {
               super.onDragCancelled(event);
               mainComponent.setInDrag(false);
            }

            @Override
            protected void onDragDrop(DndDropEvent event) {
               /* do not call super class .. remove item from store in drop target */
               mainComponent.setInDrag(false);
            }

            @Override
            protected void onDragFail(DndDropEvent event) {
               super.onDragFail(event);
               mainComponent.setInDrag(false);
            }
         };
         dragSource.setGroup(TsDiskMainComponent.FAVORITE_ITEM_DRAG_GROUP);

         GridDropTarget<AbstractTsDiskNodeDto> dropTarget = new GridDropTarget<AbstractTsDiskNodeDto>(grid) {

            @Override
            protected void onDragDrop(DndDropEvent e) {
               /* find item */
               AbstractTsDiskNodeDto dropOnItem = getDropOnItem(
                     e.getDragEndEvent().getNativeEvent().getEventTarget().<Element>cast());
               if (null != dropOnItem) {
                  AbstractTsDiskNodeDto parent = (AbstractTsDiskNodeDto) dropOnItem;

                  if (parent instanceof TsDiskFolderDto) {
                     mainComponent.moveNodes((List) e.getData(), parent);
                  }
               } else {
                  e.getStatusProxy().setStatus(false);
               }
            }

            /**
             * Only allow drops on folders
             */
            @Override
            protected void onDragMove(com.sencha.gxt.dnd.core.client.DndDragMoveEvent e) {
               super.onDragMove(e);
               if (e.isCancelled())
                  return;

               if (!testEvent(e.getDragMoveEvent().getNativeEvent().getEventTarget().<Element>cast(),
                     e.getDragSource().getData())) {
                  e.setCancelled(true);
                  e.getStatusProxy().setStatus(false);
               }
            }

            @Override
            protected void onDragEnter(DndDragEnterEvent e) {
               super.onDragEnter(e);
               if (e.isCancelled())
                  return;

               if (!testEvent(e.getDragEnterEvent().getNativeEvent().getEventTarget().<Element>cast(),
                     e.getDragSource().getData())) {
                  e.setCancelled(true);
                  e.getStatusProxy().setStatus(false);
               }
            }

            protected boolean testEvent(com.google.gwt.dom.client.Element element, Object data) {
               /* find item */
               AbstractTsDiskNodeDto dropOnItem = getDropOnItem(element);

               boolean allow = false;
               if (null != dropOnItem) {
                  boolean dropOnSelf = false;
                  List<Object> models = prepareDropData(data, true);
                  for (Object model : models) {
                     if (dropOnItem.equals(model)) {
                        dropOnSelf = true;
                        break;
                     }
                  }

                  if (!dropOnSelf) {
                     if (dropOnItem instanceof TsDiskFolderDto)
                        allow = true;
                  }
               }

               if (!allow)
                  return false;
               return true;
            }

            protected AbstractTsDiskNodeDto getDropOnItem(com.google.gwt.dom.client.Element el) {
               Element row = grid.getView().findRow(el).cast();

               if (row != null) {
                  int idx = grid.getView().findRowIndex(row);
                  return grid.getStore().get(idx);
               }

               return null;
            }

         };
         dropTarget.setAllowSelfAsSource(true);
         dropTarget.setGroup(TsDiskMainComponent.FAVORITE_ITEM_DRAG_GROUP);
         dropTarget.setFeedback(Feedback.APPEND);
      }

      final FlowLayoutContainer wrapper = new FlowLayoutContainer();
      wrapper.setScrollMode(ScrollMode.AUTOY);
      wrapper.add(grid);

      /* return grid */
      return new TsDiskListView() {

         @Override
         public Widget getComponent() {
            return wrapper;
         }

         @Override
         public void select(AbstractTsDiskNodeDto model) {
            grid.getSelectionModel().select(model, false);
         }

         @Override
         public List<AbstractTsDiskNodeDto> getSelected() {
            return grid.getSelectionModel().getSelectedItems();
         }
      };
   }

   @Override
   public String getViewId() {
      return "gridListView";
   }

}
