package net.datenwerke.gf.client.treedb;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.sencha.gxt.core.shared.event.CancellableEvent;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.data.shared.event.StoreUpdateEvent;
import com.sencha.gxt.data.shared.loader.TreeLoader;
import com.sencha.gxt.dnd.core.client.DND.Feedback;
import com.sencha.gxt.dnd.core.client.DndDragMoveEvent;
import com.sencha.gxt.dnd.core.client.DndDragStartEvent;
import com.sencha.gxt.dnd.core.client.DndDragStartEvent.DndDragStartHandler;
import com.sencha.gxt.dnd.core.client.DndDropEvent;
import com.sencha.gxt.dnd.core.client.DragSource;
import com.sencha.gxt.widget.core.client.event.BeforeShowContextMenuEvent;
import com.sencha.gxt.widget.core.client.event.BeforeShowContextMenuEvent.BeforeShowContextMenuHandler;
import com.sencha.gxt.widget.core.client.event.ExpandItemEvent;
import com.sencha.gxt.widget.core.client.event.ExpandItemEvent.ExpandItemHandler;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.tree.TreeSelectionModel;

import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTree;
import net.datenwerke.gf.client.treedb.dnd.UITreeDragDropConfiguration;
import net.datenwerke.gf.client.treedb.dnd.UITreeDragSource;
import net.datenwerke.gf.client.treedb.dnd.UITreeDropTarget;
import net.datenwerke.gf.client.treedb.helper.menu.TreeDBUIMenuProvider;
import net.datenwerke.gf.client.treedb.helper.menu.TreeMenuItem;
import net.datenwerke.gf.client.treedb.stores.EnhancedTreeStore;
import net.datenwerke.gf.client.treedb.stores.EnhancedTreeStoreLoadListener;
import net.datenwerke.gxtdto.client.awareness.TreePanelAware;
import net.datenwerke.gxtdto.client.baseex.widget.DwTreePanel;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.clipboard.ClipboardDtoItem;
import net.datenwerke.gxtdto.client.clipboard.ClipboardItem;
import net.datenwerke.gxtdto.client.clipboard.ClipboardUiService;
import net.datenwerke.gxtdto.client.clipboard.processor.ClipboardCopyProcessor;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.dtomanager.stores.DtoAwareTreeStore;
import net.datenwerke.gxtdto.client.dtomanager.stores.PositionSortableStore;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.servercommunication.callback.NotamCallback;
import net.datenwerke.gxtdto.client.stores.HasLoader;
import net.datenwerke.gxtdto.client.utilityservices.menu.DwHookableMenu;
import net.datenwerke.rs.core.client.reportvariants.ui.ReportVariantsView;
import net.datenwerke.security.client.treedb.dto.decorator.SecuredAbstractNodeDtoDec;
import net.datenwerke.treedb.client.treedb.TreeDbLoaderDao;
import net.datenwerke.treedb.client.treedb.TreeDbLoaderManagerContainer;
import net.datenwerke.treedb.client.treedb.TreeDbManagerDao;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;
import net.datenwerke.treedb.client.treedb.dto.decorator.AbstractNodeDtoDec;
import net.datenwerke.treedb.client.treedb.dto.pa.AbstractNodeDtoPA;
import net.datenwerke.treedb.client.treedb.locale.TreedbMessages;

/**
 * 
 *
 */
public class UITree extends DwTreePanel<AbstractNodeDto> implements TreeDbLoaderManagerContainer {

   interface UiTreeCallbackListener {
      public void handleEvent();
   }

   private final ClipboardUiService clipboardService;

   /**
    * Stores the node that was associated with the context menu last.
    */
   private AbstractNodeDto contextMenuNode;
   protected boolean inDrag = false;

   private boolean isAllLoaded = false;

   private UITreeDropTarget dropTarget;

   private TreeDbLoaderDao loaderDao;
   private TreeDbManagerDao treeManagerDao;

   private int rightClickX;

   private int rightClickY;

   private List<Class<? extends RsDto>> types;

   @Inject
   public UITree(ClipboardUiService clipboardService, @Assisted TreeStore<AbstractNodeDto> store) {
      super(store, Dto.DISPLAY_STRING_VALUE_PROVIDER);

      this.clipboardService = clipboardService;

      if (store instanceof HasLoader)
         setLoader(
               (TreeLoader<AbstractNodeDto>) ((HasLoader<AbstractNodeDto, List<AbstractNodeDto>>) store).getLoader());

      if (store instanceof TreePanelAware)
         ((TreePanelAware) store).makeAwareOfTreePanel(this);

      /* initializes the tree */
      initUITree();
   }

   public void setLoaderDao(TreeDbLoaderDao loaderDao) {
      this.loaderDao = loaderDao;
   }

   public void setManagerDao(TreeDbManagerDao treeManagerDao) {
      this.treeManagerDao = treeManagerDao;
   }

   @Override
   public TreeDbLoaderDao getTreeLoader() {
      return loaderDao;
   }

   @Override
   public TreeDbManagerDao getTreeManager() {
      return treeManagerDao;
   }

   protected void initUITree() {
      setBorders(false);

      addDomHandler(new MouseUpHandler() {
         @Override
         public void onMouseUp(MouseUpEvent event) {
            setInDrag(false);
         }
      }, MouseUpEvent.getType());

      addDomHandler(new DoubleClickHandler() {

         @Override
         public void onDoubleClick(DoubleClickEvent event) {
            if (null != getSelectionModel().getSelectedItem()) {
               onDoubleClicked(getSelectionModel().getSelectedItem(), event);
            }
         }
      }, DoubleClickEvent.getType());
   }

   protected void onDoubleClicked(AbstractNodeDto selectedItem, DoubleClickEvent event) {
   }

   public void enableClipboardProvider() {
      /* clipboard */
      clipboardService.registerCopyHandler(this, new ClipboardCopyProcessor() {
         @Override
         public ClipboardItem getItem() {
            if (null != getSelectionModel().getSelectedItem())
               return new ClipboardDtoItem(getSelectionModel().getSelectedItem());
            return null;
         }
      });
   }

   public AbstractNodeDto getContextMenuNode() {
      return contextMenuNode;
   }

   @Override
   protected void onRightClick(Event event) {
      rightClickX = event.getClientX();
      rightClickY = event.getClientY();
      super.onRightClick(event);
   }

   public void setMenuProvider(final TreeDBUIMenuProvider provider) {
      /* initialize all menus */
      provider.initializeMenus(this);

      /* set dummy menu */
      setContextMenu(new DwMenu());

      /* set listener */
      addBeforeShowContextMenuHandler(new BeforeShowContextMenuHandler() {

         @Override
         public void onBeforeShowContextMenu(BeforeShowContextMenuEvent event) {
            final AbstractNodeDto node = getSelectionModel().getSelectedItem();
            if (null == node) {
               event.setCancelled(true);
               return;
            }

            if (node instanceof SecuredAbstractNodeDtoDec
                  && !((SecuredAbstractNodeDtoDec) node).isAccessRightsLoaded()) {
               if (null != loaderDao) {
                  loaderDao.loadNode(node, new RsAsyncCallback<AbstractNodeDto>() {
                     public void onSuccess(AbstractNodeDto result) {
                        updateContextMenu(node, provider, null);
                        onShowContextMenu(rightClickX, rightClickY);
                     };
                  });
                  event.setCancelled(true);
                  return;
               }
            }

            updateContextMenu(node, provider, event);
         }

      });

   }

   private void updateContextMenu(AbstractNodeDto node, TreeDBUIMenuProvider provider, CancellableEvent event) {
      /* get menu */
      Menu m = provider.getMenuFor(node.getClass());
      if (null == m) {
         setContextMenu(new DwMenu());
         if (null != event)
            event.setCancelled(true);
         return;
      }

      notifyMenuItems(m, node);

      if (m instanceof DwHookableMenu) {
         ((DwHookableMenu) m).getHookConfig().put("id", String.valueOf(node.getId()));
         ((DwHookableMenu) m).getHookConfig().put("classname", node.getClass().getName());
      }

      /* set menu */
      setContextMenu(m);

      /* store contextmenu node */
      contextMenuNode = node;
   }

   private void notifyMenuItems(Menu m, AbstractNodeDto node) {
      for (Widget item : m) {
         if (item instanceof TreeMenuItem)
            ((TreeMenuItem) item).toBeDisplayed(node);
         if (item instanceof MenuItem && null != ((MenuItem) item).getSubMenu())
            notifyMenuItems(((MenuItem) item).getSubMenu(), node);
      }
   }

   /**
    * Enables drag and drop within tree.
    * 
    * @param dndConfig
    */
   public void enableDragDrop(final TreeDbManagerDao treeManager, final UITreeDragDropConfiguration dndConfig) {
      /* create source and target */
      DragSource dragSource = new UITreeDragSource(this);
      dropTarget = new UITreeDropTarget(this) {

         protected void handleInsertDrop(DndDropEvent event, TreeNode<AbstractNodeDto> item, int index) {
            List<?> droppedItems = (List<?>) event.getData();
            if (droppedItems.size() > 0) {
               AbstractNodeDto draggedNode = (AbstractNodeDto) ((TreeStore.TreeNode<AbstractNodeDto>) droppedItems
                     .get(0)).getData();

               if (null != draggedNode) {
                  AbstractNodeDto parent = getWidget().getStore().getParent(item.getModel());

                  /* get correct index */
                  int idx = getWidget().getStore().indexOf(item.getModel());
                  idx = status == 0 ? idx : idx + 1;

                  int oldPos = draggedNode.getPosition();

                  /* update positions */
                  if (null != parent) {
                     for (AbstractNodeDto child : getStore().getChildren(parent)) {
                        int childPos = child.getPosition();
                        if (idx > oldPos && childPos > oldPos && childPos <= idx)
                           child.deepSet(AbstractNodeDtoPA.INSTANCE.position(), childPos - 1);
                        if (idx < oldPos && childPos < oldPos && childPos >= idx)
                           child.deepSet(AbstractNodeDtoPA.INSTANCE.position(), childPos + 1);
                     }
                  }
                  draggedNode.deepSet(AbstractNodeDtoPA.INSTANCE.position(), idx);

                  /* make server call */
                  treeManager.moveNodeInsert(draggedNode, (AbstractNodeDto) item.getModel(), idx,
                        new NotamCallback<AbstractNodeDto>(TreedbMessages.INSTANCE.moved()));
               }
            }

            /* do not call super class */
            // we are using dto aware stores so they handle the rest!
         };

         protected void handleAppendDrop(DndDropEvent event, TreeNode<AbstractNodeDto> item) {
            List<?> droppedItems = (List<?>) event.getData();
            if (droppedItems.size() > 0) {
               final AbstractNodeDto draggedNode = (AbstractNodeDto) ((TreeStore.TreeNode<AbstractNodeDto>) droppedItems
                     .get(0)).getData();

               if (null != draggedNode) {
                  AbstractNodeDtoDec parent = (AbstractNodeDtoDec) item.getModel();

                  /* set new position */
                  draggedNode.deepSet(AbstractNodeDtoPA.INSTANCE.position(), getStore().getChildCount(parent));

                  /* tell node, that it has children */
                  item.setLeaf(false);
                  parent.setHasChildren(true);

                  treeManager.moveNodeAppend(draggedNode, parent,
                        new NotamCallback<AbstractNodeDto>(TreedbMessages.INSTANCE.moved()) {
                           @Override
                           public void doOnFailure(Throwable caught) {
                              super.doOnFailure(caught);

                              /* reregister dto */
                              if (store instanceof DtoAwareTreeStore)
                                 ((DtoAwareTreeStore<AbstractNodeDto>) store).reCheckDto(draggedNode);
                           }
                        });
               }
            }

            /* do not call super class */
            // we are using dto aware stores so they handle the rest!
         }

         /* make sure only proper targets are allowed as drop target */
         @Override
         protected boolean allowDropOnNode(DndDragMoveEvent event, TreeNode<AbstractNodeDto> item) {
            /* mark whether or not to allow drop */
            boolean allowDrop = false;

            if (null != item) {
               AbstractNodeDto targetNode = item.getModel();
               if (null != targetNode) {
                  AbstractNodeDto source = UITree.this.getSelectionModel().getSelectedItem();

                  /* make sure nodes are not equal */
                  if (source.equals(targetNode))
                     return false;

                  if (!dndConfig.isDropTarget(source.getClass(), targetNode.getClass()))
                     allowDrop = false;
                  else
                     allowDrop = true;
               }
            } else
               allowDrop = false;

            /* allow drop? */
            return allowDrop;
         }

         protected boolean allowDropBetweenNode(DndDragMoveEvent event, TreeNode<AbstractNodeDto> item, boolean before,
               int idx) {
            /* only allow if store is capable */
            if (!(store instanceof PositionSortableStore)
                  || !((PositionSortableStore) store).isPositionSortableEnabled())
               return false;

            /* mark whether or not to allow drop */
            boolean allowDrop = false;
            if (null != item) {
               AbstractNodeDto parent = item.getModel();
               parent = UITree.this.getStore().getParent(parent);

               if (null != parent) {
                  AbstractNodeDto source = UITree.this.getSelectionModel().getSelectedItem();
                  if (!dndConfig.isDropTarget(source.getClass(), parent.getClass()))
                     allowDrop = false;
                  else
                     allowDrop = true;
               }
            } else
               allowDrop = false;

            return allowDrop;
         }
      };

      /* allow self as source */
      dropTarget.setAllowSelfAsSource(true);
      dropTarget.setAllowDropOnLeaf(true);
      dropTarget.setAutoExpand(true);
      dropTarget.setAutoScroll(true);
      dropTarget.setFeedback(Feedback.BOTH);

      /* specify listeners */
      dragSource.addDragStartHandler(new DndDragStartHandler() {

         @Override
         public void onDragStart(DndDragStartEvent event) {
            TreeNode<AbstractNodeDto> node = UITree.this.findNode(event.getDragStartEvent().getStartElement());
            if (null == node) {
               event.setCancelled(true);
               event.getStatusProxy().setStatus(false);
               return;
            }

            AbstractNodeDto source = node.getModel();
            if (null == source || UITree.this.getStore().getRootItems().get(0).equals(source)) {
               event.setCancelled(true);
               event.getStatusProxy().setStatus(false);
               return;
            }

            /* remember, that we are in a drag operation */
            UITree.this.setInDrag(true);
         }
      });
   }

   public AbstractNodeDto getParentNode(AbstractNodeDto item) {
      TreeNode<AbstractNodeDto> node = findNode(item);
      if (null == node)
         return null;
      AbstractNodeDto parent = getStore().getParent(node.getModel());
      if (null == parent)
         return null;
      return parent;
   }

   public boolean isNodeInTree(AbstractNodeDto item) {
      return null != findNode(item);
   }

   public boolean isInDrag() {
      return inDrag;
   }

   public void setInDrag(boolean inDrag) {
      this.inDrag = inDrag;
   }

   public boolean isAllLoaded() {
      return this.isAllLoaded;
   }

   public void loadAll() {
      TreeStore<AbstractNodeDto> store = getStore();
      if (store instanceof EnhancedTreeStore) {
         mask(BaseMessages.INSTANCE.loadingMsg());
         ((EnhancedTreeStore) store).loadEntireTree(new EnhancedTreeStoreLoadListener() {
            public void loadComplete() {
               isAllLoaded = true;
               superExpandAll();
               unmask();
            }
         }, false);
      }
   }

   private void superExpandAll() {
      super.expandAll();
   }

   public void reload() {
      if (null != loader) {
         isAllLoaded = false;
         getStore().clear();
         nodes.clear();
         loader.load();
      }
   }

   public void reload(AbstractNodeDto node) {
      if (null != loader) {
         getStore().removeChildren(node);
         loader.load(node);
      }
   }

   /**
    * TODO:
    * http://www.sencha.com/forum/showthread.php?235044-Trees-can-have-quot-unsynchronized-quot-TreeStores-gt-NullPointers&p=866077#post866077
    */
   protected void onUpdate(StoreUpdateEvent<AbstractNodeDto> event) {
      for (AbstractNodeDto item : event.getItems()) {
         TreeNode<AbstractNodeDto> node = findNode(item);
         if (null == node)
            register(item);
      }
      super.onUpdate(event);
   }

   public void expandPath(List<? extends AbstractNodeDto> path) {
      ArrayList<Long> ids = new ArrayList<>();
      for (AbstractNodeDto node : path)
         ids.add(node.getId());

      expandPathByIds(ids);
   }

   public void expandPathByIds(final List<Long> path) {
      final List<Long> pathCopy = new ArrayList<>(path);
      final UiTreeCallbackListener callback = new UiTreeCallbackListener() {
         @Override
         public void handleEvent() {
            doHandleEvent(pathCopy, store);
         }
      };

      doExpandPathByIds(path, callback);
   }

   private void doExpandPathByIds(final List<Long> path, final UiTreeCallbackListener callback) {

      if (path.size() <= 1) {
         callback.handleEvent();
         return;
      }

      /* load first item and check if it is in tree */
      Long id = path.get(0);
      final AbstractNodeDto item = getStore().findModelWithKey(String.valueOf(id));
      if (null == item || !isNodeInTree(item)) {
         path.remove(0);
         doExpandPathByIds(path, callback);

         return;
      }

      /* remove from nodes */
      path.remove(0);

      if (isLeaf(item)) {
         callback.handleEvent();
         return;
      }
      if (isExpanded(item)) {
         doExpandPathByIds(path, callback);
         return;
      }

      final List<HandlerRegistration> dummy = new ArrayList<HandlerRegistration>();
      final HandlerRegistration addExpandHandler = addExpandHandler(new ExpandItemHandler<AbstractNodeDto>() {
         @Override
         public void onExpand(ExpandItemEvent<AbstractNodeDto> event) {
            if (event.getItem() == item) {
               for (HandlerRegistration reg : dummy)
                  reg.removeHandler();
               doExpandPathByIds(path, callback);
            }
         }
      });
      dummy.add(addExpandHandler);

      setExpanded(item, true);

   }

   protected void doHandleEvent(final List<Long> nodes, final TreeStore<AbstractNodeDto> store) {
      Scheduler.get().scheduleDeferred(new ScheduledCommand() {
         @Override
         public void execute() {
            int i = nodes.size() - 1;
            boolean showVariants = false;
            while (i >= 0) {
               AbstractNodeDto item = store.findModelWithKey(String.valueOf(nodes.get(i--)));
               if (null != item) {
                  getSelectionModel().select(item, false);
                  scrollIntoView(item);
                  break;
               } else {
                  showVariants = true;
               }
            }

            if (showVariants && UITree.this instanceof ManagerHelperTree)
               ((ManagerHelperTree) UITree.this).showTabOnSelection(ReportVariantsView.VIEW_ID);
         }
      });
   }

   /*
    * https://www.sencha.com/forum/showthread.php?308983-GXT-3.1.4.-
    * TreeSelectionModel-RightClick-sets-mousdown-flag&p=1128504#post1128504
    */
   public void releaseMouseDownFlag() {
      _releaseMouseDownFlag(getSelectionModel());
   }

   /* resort to violator pattern */
   protected native void _releaseMouseDownFlag(TreeSelectionModel<AbstractNodeDto> sm) /*-{
	  sm.@com.sencha.gxt.widget.core.client.selection.AbstractStoreSelectionModel::mouseDown = false;
	}-*/;

   public void setTypes(Class<? extends RsDto>[] types) {
      List<Class<? extends RsDto>> asList = new ArrayList<>();
      for (Class<? extends RsDto> type : types)
         asList.add(type);

      this.types = asList;
   }

   public List<Class<? extends RsDto>> getTypes() {
      return types;
   }

   @Override
   public void expandAll() {
      if (isAllLoaded)
         super.expandAll();
      else
         loadAll();
   }

}
