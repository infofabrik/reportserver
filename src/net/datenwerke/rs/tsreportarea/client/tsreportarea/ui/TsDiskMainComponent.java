package net.datenwerke.rs.tsreportarea.client.tsreportarea.ui;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.util.DelayedTask;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.SortDir;
import com.sencha.gxt.data.shared.Store.StoreSortInfo;
import com.sencha.gxt.dnd.core.client.DndDragCancelEvent;
import com.sencha.gxt.dnd.core.client.DndDragStartEvent;
import com.sencha.gxt.dnd.core.client.DndDropEvent;
import com.sencha.gxt.dnd.core.client.DragSource;
import com.sencha.gxt.dnd.core.client.TreeDragSource;
import com.sencha.gxt.dnd.core.client.TreeDropTarget;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.sencha.gxt.widget.core.client.info.DefaultInfoConfig;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.info.InfoConfig;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.SeparatorMenuItem;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;
import com.sencha.gxt.widget.core.client.toolbar.SeparatorToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;
import com.sencha.gxt.widget.core.client.tree.Tree.TreeNode;
import com.sencha.gxt.widget.core.client.tree.TreeSelectionModel;

import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.gf.client.treedb.TreeDBUIService;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.UiTreeFactory;
import net.datenwerke.gf.client.treedb.stores.EnhancedTreeStore;
import net.datenwerke.gf.client.upload.FileUploadUIModule;
import net.datenwerke.gf.client.upload.FileUploadUiService;
import net.datenwerke.gf.client.upload.dto.FileToUpload;
import net.datenwerke.gf.client.upload.dto.UploadProperties;
import net.datenwerke.gf.client.upload.filter.FileUploadFilter;
import net.datenwerke.gf.client.upload.simpleform.FileUpload;
import net.datenwerke.gf.client.upload.simpleform.SFFCFileUpload;
import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwBorderContainer;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwCardContainer;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwNorthSouthContainer;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.clipboard.ClipboardDtoItem;
import net.datenwerke.gxtdto.client.clipboard.ClipboardItem;
import net.datenwerke.gxtdto.client.clipboard.ClipboardUiService;
import net.datenwerke.gxtdto.client.clipboard.processor.ClipboardCopyProcessor;
import net.datenwerke.gxtdto.client.clipboard.processor.ClipboardDtoPasteProcessor;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.dtomanager.stores.DtoAwareListStore;
import net.datenwerke.gxtdto.client.dtomanager.stores.TreeDto;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCAllowBlank;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCPlaceHolder;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.objectinformation.ObjectPreviewTabPanel;
import net.datenwerke.gxtdto.client.servercommunication.callback.NotamCallback;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.helper.ObjectHolder;
import net.datenwerke.rs.core.client.reportmanager.dto.interfaces.ReportVariantDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerUiModule;
import net.datenwerke.rs.teamspace.client.teamspace.TeamSpaceUIModule;
import net.datenwerke.rs.teamspace.client.teamspace.TeamSpaceUIService;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.TsDiskDao;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.TsDiskTreeLoaderDao;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.TsDiskTreeManagerDao;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.TsDiskUIModule;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFolderDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskGeneralReferenceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskReportReferenceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskRootDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.container.TsDiskItemList;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.pa.TsDiskFolderDtoPA;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.hooks.GeneralReferenceHandlerHook;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.hooks.TsFavoriteListViewHook;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.hooks.TsFavoriteMainToolbarEnhancerHook;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.hooks.TsFavoriteMenuHook;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.locale.TsFavoriteMessages;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.listviews.TsDiskListView;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

/**
 * 
 *
 */
public class TsDiskMainComponent extends DwBorderContainer {

   private static final double LISTPANEL_DEFAULT_SIZE = 0.4f;

   public static final String FAVORITE_ITEM_DRAG_GROUP = "favoriteItemDragGroup";

   private final ClipboardUiService clipboardService;
   private final LoginService loginService;
   private final TreeDBUIService treeDBUIService;
   private final HookHandlerService hookHandlerService;
   private final TsDiskDao favoriteDao;
   private final TsDiskTreeManagerDao treeManagerDao;
   private final TsDiskTreeLoaderDao treeLoaderDao;
   private final Provider<ImportReportDialogCreator> importDialogCreator;
   private final Provider<ObjectPreviewTabPanel> objectInformationPanelProvider;
   private final ToolbarService toolbarService;
   private final TeamSpaceUIService teamSpaceService;
   private final UiTreeFactory uiTreeFactory;

   private VerticalLayoutContainer mainPanel;
   private DwBorderContainer mainPanelBorderContainer;

   private DwContentPanel listPanel;
   private DwCardContainer listCardContainer;
   private DwContentPanel detailPanel;
   private DwContentPanel treePanel;
   private DwNorthSouthContainer treePanelNsContainer;

   private BorderLayoutData listPanelLayoutData;

   private ToolBar mainToolbar;
   private ToolBar detailToolbar;
   private ToolBar explorerToolbar;

   private EnhancedTreeStore treeStore;
   private DtoAwareListStore<AbstractTsDiskNodeDto> listStore;
   private List<AbstractTsDiskNodeDto> currentPath;

   private TsDiskFolderDto currentFolder;
   private TeamSpaceDto currentSpace;

   private boolean inDrag = false;

   private boolean appInitialized = false;

   private UITree tree;

   private Map<String, TsDiskListView> views = new HashMap<>();
   private TsDiskListView currentView;

   private VerticalLayoutContainer listPanelContainer;
   private VerticalLayoutContainer detailPanelContainer;
   
   private final Provider<FileUploadUiService> fileUploadServiceProvider;
   
   private Long fileUploadMaxFileSizeByte = null;
   private List<String> fileUploadEndingWhiteList = null;

   @Inject
   public TsDiskMainComponent(
         ClipboardUiService clipboardService, 
         LoginService loginService,
         TreeDBUIService treeDBUIService, 
         HookHandlerService hookHandlerService, 
         TsDiskDao favoriteDao,
         TsDiskTreeLoaderDao treeLoaderDao, 
         TsDiskTreeManagerDao treeManagerDao,
         Provider<ImportReportDialogCreator> importDialogCreator,
         Provider<ObjectPreviewTabPanel> objectInformationPanelProvider, 
         ToolbarService toolbarService,
         TeamSpaceUIService teamSpaceService, 
         UiTreeFactory uiTreeFactory,
         Provider<FileUploadUiService> fileUploadServiceProvider
         ) {

      /* store objects */
      this.clipboardService = clipboardService;
      this.loginService = loginService;
      this.treeDBUIService = treeDBUIService;
      this.hookHandlerService = hookHandlerService;
      this.favoriteDao = favoriteDao;
      this.treeLoaderDao = treeLoaderDao;
      this.treeManagerDao = treeManagerDao;
      this.importDialogCreator = importDialogCreator;
      this.objectInformationPanelProvider = objectInformationPanelProvider;
      this.toolbarService = toolbarService;
      this.teamSpaceService = teamSpaceService;
      this.uiTreeFactory = uiTreeFactory;
      this.fileUploadServiceProvider = fileUploadServiceProvider;
      
      favoriteDao.getMaxUploadFileSizeBytes(new AsyncCallback<Long>() {
         @Override
         public void onSuccess(final Long result) {
            fileUploadMaxFileSizeByte = result;
         }

         @Override
         public void onFailure(Throwable caught) {
         }
      });
      
      favoriteDao.getFileUploadEndingWhiteList(new AsyncCallback<List<String>>() {
         @Override
         public void onSuccess(List<String> result) {
            fileUploadEndingWhiteList = result;
         }
         @Override
         public void onFailure(Throwable caught) {
         }
      });

      /* init */
      initializeUI();
   }

   private void initializeUI() {
      /* init tab panel */
      setBorders(false);
      getElement().addClassName("rs-teamspace-rep-p");

      /* toolbar */
      mainToolbar = new DwToolBar();
      mainToolbar.addStyleName("rs-teamspace-tb-main");
      detailToolbar = new DwToolBar();
      detailToolbar.addStyleName("rs-teamspace-tb-detail");

      /* create tree */
      createTreePanel();

      /* create main */
      createMainPanel();

      /* add components */
      BorderLayoutData westData = new BorderLayoutData(200);
      westData.setMargins(new Margins(0, 1, 0, 0));
      westData.setSplit(true);
      westData.setFloatable(false);
      setWestWidget(treePanel, westData);

      BorderLayoutData centerData = new BorderLayoutData();
      centerData.setMargins(new Margins(0, 0, 0, 0));
      setCenterWidget(mainPanel, centerData);
   }

   protected void initClipboard(final TsDiskListView listView) {
      clipboardService.registerPasteHandler(listView.getComponent(), new ClipboardDtoPasteProcessor(ReportDto.class) {
         @Override
         public void doPaste(ClipboardDtoItem item) {
            ReportDto report = (ReportDto) item.getDto();
            if (null != report)
               importReport(report, (report instanceof ReportVariantDto), true);
         }
      });

      clipboardService.registerCopyHandler(listView.getComponent(), new ClipboardCopyProcessor() {
         @Override
         public ClipboardItem getItem() {
            List<AbstractTsDiskNodeDto> selected = listView.getSelected();
            AbstractTsDiskNodeDto node = null != selected && !selected.isEmpty() ? selected.get(0) : null;
            if (null != node)
               return new ClipboardDtoItem(node);
            return null;
         }
      });
   }

   private void createMainPanel() {
      mainPanel = new VerticalLayoutContainer();

      mainPanelBorderContainer = new DwBorderContainer();
      mainPanel.add(mainPanelBorderContainer, new VerticalLayoutData(1, 1));

      /* northern list panel to display items */
      listPanel = new DwContentPanel();
      listPanelContainer = new VerticalLayoutContainer();
      listPanel.setWidget(listPanelContainer);
      listPanelContainer.add(mainToolbar, new VerticalLayoutData(1, -1));
      listPanel.addClassName("rs-teamspace-list");
      listPanel.setHeaderVisible(false);

      /* detail panel for displaying details to a specific report */
      detailPanel = new DwContentPanel();
      detailPanel.setHeaderVisible(false);
      detailPanel.addClassName("rs-teamspace-detail");
      detailPanelContainer = new VerticalLayoutContainer();
      detailPanel.setWidget(detailPanelContainer);
      detailPanelContainer.add(detailToolbar, new VerticalLayoutData(1, -1));

      listPanelLayoutData = new BorderLayoutData(LISTPANEL_DEFAULT_SIZE);
      listPanelLayoutData.setSplit(true);
      listPanelLayoutData.setMaxSize(Integer.MAX_VALUE);
      listPanelLayoutData.setCollapsible(true);
      listPanelLayoutData.setFloatable(false);

      /* add panels */
      mainPanelBorderContainer.setWestWidget(listPanel, listPanelLayoutData);

      mainPanelBorderContainer.setCenterWidget(detailPanel);
   }

   private void createTreePanel() {
      treePanel = new DwContentPanel();
      treePanel.setHeaderVisible(false);
      treePanel.addStyleName("rs-teamspace-rep-tp");

      treePanelNsContainer = new DwNorthSouthContainer();
      treePanel.setWidget(treePanelNsContainer);

      explorerToolbar = new DwToolBar();
      treePanelNsContainer.setNorthWidget(explorerToolbar);
   }

   private void initStores() {
      /* init tree store */
      RpcProxy<AbstractNodeDto, List<AbstractNodeDto>> proxy = new RpcProxy<AbstractNodeDto, List<AbstractNodeDto>>() {

         @Override
         public void load(AbstractNodeDto loadConfig, AsyncCallback<List<AbstractNodeDto>> callback) {
            if (null == loadConfig)
               treeLoaderDao.getRoot(callback);
            else if (loadConfig instanceof AbstractNodeDto)
               treeLoaderDao.getChildren((AbstractNodeDto) loadConfig, false, null, null, callback);
         }

      };

      treeStore = treeDBUIService.getUITreeStore(TsDiskFolderDto.class, treeLoaderDao, proxy, true);
      treeStore.addTypeFilter(TsDiskRootDto.class);

      /* init list store */
      listStore = new DtoAwareListStore<AbstractTsDiskNodeDto>();
      listStore.clearSortInfo();

      StoreSortInfo<AbstractTsDiskNodeDto> ssi = new StoreSortInfo<AbstractTsDiskNodeDto>(
            new Comparator<AbstractTsDiskNodeDto>() {
               @Override
               public int compare(AbstractTsDiskNodeDto o1, AbstractTsDiskNodeDto o2) {
                  if (o1 instanceof TreeDto && o2 instanceof TreeDto) {
                     if ((o1 instanceof TsDiskFolderDto) && !(o2 instanceof TsDiskFolderDto))
                        return -1;
                     else if (o2 instanceof TsDiskFolderDto && !(o1 instanceof TsDiskFolderDto))
                        return 1;

                     String o1Title = o1.toDisplayTitle();
                     String o2Title = o2.toDisplayTitle();

                     if (null != o1Title && null != o2Title)
                        return o1Title.compareToIgnoreCase(o2Title);
                     if (null == o1Title && null == o2Title)
                        return 0;
                     if (null == o1Title)
                        return 1;
                     else
                        return -1;
                  }
                  return 0;
               }
            }, SortDir.ASC);
      listStore.addSortInfo(ssi);

      mainPanel.mask(BaseMessages.INSTANCE.loadingMsg());
      listStore.clear();
      favoriteDao.getItemsIn(currentSpace, null, new RsAsyncCallback<TsDiskItemList>() {
         @Override
         public void onSuccess(TsDiskItemList result) {
            listStore.clear();
            listStore.addAll(result.getItems());
            mainPanel.unmask();
         }
      });
   }

   private void initTree() {
      /* build tree */
      tree = uiTreeFactory.create(treeStore);
      tree.addClassName("rs-teamspace-explorer");

      TreeSelectionModel<AbstractNodeDto> treeSelectionModel = new TreeSelectionModel<AbstractNodeDto>();
      treeSelectionModel.setSelectionMode(SelectionMode.SINGLE);
      treeSelectionModel.addSelectionChangedHandler(se -> {
         if (inDrag || null == se.getSelection() || se.getSelection().isEmpty())
            return;

         final AbstractTsDiskNodeDto item = (AbstractTsDiskNodeDto) se.getSelection().get(0);
         if ((null != item && item.equals(currentFolder)) || (null == item && null == currentFolder))
            return;

         DelayedTask task = new DelayedTask() {
            @Override
            public void onExecute() {
               if (!isInDrag())
                  itemOpened(item);
            }
         };

         if (null != se.getSelection().get(0))
            task.delay(200);
         else
            task.cancel();
      });

      tree.setSelectionModel(treeSelectionModel);
      tree.setAutoLoad(false);

      /* icons */
      tree.setIconProvider(model -> BaseIcon.FOLDER_O.toImageResource());

      /* menu */
      setMenuChanger(treePanel, new ItemSelector() {
         @Override
         public List<AbstractTsDiskNodeDto> getSelectedItems() {
            List<AbstractTsDiskNodeDto> list = new ArrayList<AbstractTsDiskNodeDto>();
            for (AbstractNodeDto node : tree.getSelectionModel().getSelectedItems())
               if (node instanceof AbstractTsDiskNodeDto)
                  list.add((AbstractTsDiskNodeDto) node);
            return list;
         }

         @Override
         public boolean isInFolder() {
            return false;
         }
      });

      /* Toolbar in Explorer Pane */
      explorerToolbar.clear();
//		explorerToolbar.add(new FillToolItem());

      /* expand all button */
      DwTextButton expandAllButton = new DwTextButton(BaseIcon.EXPAND_ALL);
      expandAllButton.setToolTip(BaseMessages.INSTANCE.expandAll());
      expandAllButton.addSelectHandler(event -> tree.expandAll());
      explorerToolbar.add(expandAllButton);

      /* collapse all button */
      DwTextButton collapseAllButton = new DwTextButton(BaseIcon.COLLAPSE_ALL);
      collapseAllButton.setToolTip(BaseMessages.INSTANCE.collapseAll());
      collapseAllButton.addSelectHandler(event -> tree.collapseAll());
      explorerToolbar.add(collapseAllButton);

      /* reload button */
      DwTextButton reloadButton = new DwTextButton(BaseIcon.REFRESH);
      reloadButton.setToolTip(BaseMessages.INSTANCE.refresh());
      reloadButton.addSelectHandler(event -> refreshTree());
      explorerToolbar.add(reloadButton);

      /* drag drop */
      if (allowDrag()) {
         DragSource dragSource = new TreeDragSource<AbstractNodeDto>(tree) {

            @Override
            protected void onDragStart(DndDragStartEvent event) {
               super.onDragStart(event);
               if (event.isCancelled())
                  setInDrag(false);
               else
                  setInDrag(true);
            }

            @Override
            protected void onDragCancelled(DndDragCancelEvent event) {
               super.onDragCancelled(event);
               setInDrag(false);
            }

            @Override
            protected void onDragDrop(DndDropEvent event) {
               super.onDragDrop(event);
               setInDrag(false);
            }

            @Override
            protected void onDragFail(DndDropEvent event) {
               super.onDragFail(event);
               setInDrag(false);
            }
         };
         dragSource.setGroup(FAVORITE_ITEM_DRAG_GROUP);
         TreeDropTarget<AbstractNodeDto> dropTarget = new TreeDropTarget<AbstractNodeDto>(tree) {

            @Override
            protected void handleAppendDrop(DndDropEvent event, TreeNode<AbstractNodeDto> item) {
               List dataList = (List) event.getData();
               if (null == dataList || dataList.isEmpty())
                  return;

               List<AbstractTsDiskNodeDto> nodes = new ArrayList<AbstractTsDiskNodeDto>();
               for (Object obj : dataList) {
                  if (obj instanceof AbstractTsDiskNodeDto)
                     nodes.add((AbstractTsDiskNodeDto) obj);
                  if (obj instanceof com.sencha.gxt.data.shared.TreeStore.TreeNode)
                     nodes.add((AbstractTsDiskNodeDto) ((com.sencha.gxt.data.shared.TreeStore.TreeNode) obj).getData());
               }

               if (null == item) {
                  moveNodes(nodes, (AbstractTsDiskNodeDto) treeStore.getRootItems().get(0));
               } else {
                  AbstractTsDiskNodeDto newParent = (AbstractTsDiskNodeDto) item.getModel();
                  moveNodes(nodes, newParent);
               }
            }

         };
         dropTarget.setGroup(FAVORITE_ITEM_DRAG_GROUP);
         dropTarget.setAllowDropOnLeaf(true);
         dropTarget.setAllowSelfAsSource(true);
      }

      /* add components to panel */
      VerticalLayoutContainer wrapper = new VerticalLayoutContainer();
      wrapper.add(explorerToolbar, new VerticalLayoutData(1, -1));
      wrapper.add(tree, new VerticalLayoutData(1, 1));

      treePanel.setWidget(wrapper);

      treePanel.forceLayout();
   }

   public void setMenuChanger(final Component comp, final ItemSelector itemSelector) {
      comp.setContextMenu(new DwMenu());
      comp.addBeforeShowContextMenuHandler(event -> comp.setContextMenu(createContextMenu(itemSelector)));
   }

   public Menu createContextMenu(ItemSelector itemSelector) {
      final List<AbstractTsDiskNodeDto> abstractTsFavoriteNodeDto = itemSelector.getSelectedItems();

      Menu menu = new DwMenu();

      /* */
      Iterator<TsFavoriteMenuHook> iterator = hookHandlerService.getHookers(TsFavoriteMenuHook.class).iterator();
      while (iterator.hasNext()) {
         TsFavoriteMenuHook menuHook = iterator.next();
         boolean sep = menuHook.addContextMenuEntries(menu, abstractTsFavoriteNodeDto, itemSelector, this);
         if (sep && iterator.hasNext())
            menu.add(new SeparatorMenuItem());
      }

      if (menu.getWidgetCount() == 0)
         return null;

      return menu;
   }

   public void moveNodes(List<AbstractTsDiskNodeDto> nodes, AbstractTsDiskNodeDto newParent) {
      if (null == nodes || nodes.isEmpty())
         return;

      for (AbstractTsDiskNodeDto model : nodes)
         if (model.equals(newParent))
            return;

      /* remove from list store */
      if (!newParent.equals(currentFolder))
         nodes.forEach(model -> listStore.remove(model));

      treeManagerDao.moveNodesAppend(new ArrayList<AbstractNodeDto>(nodes), newParent,
            new NotamCallback<List<AbstractNodeDto>>(TsFavoriteMessages.INSTANCE.movedNodes()));
   }

   protected void refreshTree() {
      treeStore.clear();
      treeStore.getLoader().load();
   }

   protected void initList() {
      /* clear panel and toolbar */
      if (listPanelContainer.getWidgetCount() > 1)
         listPanelContainer.remove(1);

      /* set layout */
      listCardContainer = new DwCardContainer();
      listPanelContainer.add(listCardContainer, new VerticalLayoutData(1, 1));

      initToolbars();

      mainPanel.forceLayout();
   }

   protected void initToolbars() {
      mainToolbar.clear();
      detailToolbar.clear();

      /* add add remove */
      if (teamSpaceService.isUser(currentSpace)) {
         DwTextButton addFolderBtn = toolbarService.createSmallButtonLeft(TsFavoriteMessages.INSTANCE.addFolderText(),
               BaseIcon.FOLDER_ADD);
         mainToolbar.add(addFolderBtn);
         addFolderBtn.addSelectHandler(event -> displayAddFolderDialog());

         if (teamSpaceService.isManager(currentSpace)) {
            DwTextButton importReportBtn = toolbarService
                  .createSmallButtonLeft(TsFavoriteMessages.INSTANCE.importReportText(), BaseIcon.REPORT_ADD);
            mainToolbar.add(importReportBtn);
            importReportBtn.addSelectHandler(event -> importDialogCreator.get().displayDialog(TsDiskMainComponent.this));
            
            DwTextButton uploadFileBtn = toolbarService
                  .createSmallButtonLeft(TsFavoriteMessages.INSTANCE.uploadFileText(), BaseIcon.REPORT_ADD);
            mainToolbar.add(uploadFileBtn);
            uploadFileBtn.addSelectHandler(event -> displayUploadFileDialog());
            
            favoriteDao.isFileUploadEnabled(new RsAsyncCallback<Boolean>() {
               @Override
               public void onSuccess(Boolean result) {
                  if (!result) 
                     mainToolbar.remove(uploadFileBtn);
               }

               @Override
               public void onFailure(Throwable caught) {
                  super.onFailure(caught);
               }
            });
         }
      }

      Iterator<TsFavoriteMainToolbarEnhancerHook> iterator = hookHandlerService
            .getHookers(TsFavoriteMainToolbarEnhancerHook.class).iterator();
      while (iterator.hasNext()) {
         TsFavoriteMainToolbarEnhancerHook enhancer = iterator.next();
         boolean separator = enhancer.enhanceToolbarLeft(mainToolbar, this);
         if (iterator.hasNext() && separator)
            mainToolbar.add(new SeparatorToolItem());
      }

      mainToolbar.add(new FillToolItem());

      iterator = hookHandlerService.getHookers(TsFavoriteMainToolbarEnhancerHook.class).iterator();
      while (iterator.hasNext()) {
         TsFavoriteMainToolbarEnhancerHook enhancer = iterator.next();
         boolean separator = enhancer.enhanceToolbarRight(mainToolbar, this);
         if (iterator.hasNext() && separator)
            mainToolbar.add(new SeparatorToolItem());
      }

      /* refresh */
      DwTextButton refreshBtn = toolbarService.createSmallButtonLeft(BaseIcon.REFRESH);
      refreshBtn.addSelectHandler(event -> {
         refreshList();
      });

      mainToolbar.add(refreshBtn);
      mainToolbar.add(new SeparatorToolItem());

      /* init views */
      String userPropViewId = loginService.getCurrentUser()
            .getUserPropertyValue(TsDiskUIModule.USER_PROPERTY_VIEW_VIEW_ID);
      List<TsFavoriteListViewHook> listViews = hookHandlerService.getHookers(TsFavoriteListViewHook.class);
      for (final TsFavoriteListViewHook view : listViews) {
         final TsDiskListView listView = view.getListView(this);
         final Widget component = listView.getComponent();
         final String viewId = view.getViewId();
         listCardContainer.add(component);
         initClipboard(listView);
         views.put(viewId, listView);

         if (null != userPropViewId && userPropViewId.equals(viewId)) {
            currentView = listView;
            listCardContainer.setActiveWidget(component);
         } else if (null == userPropViewId && null == currentView)
            currentView = listView;

         /* create button */
         DwTextButton viewBtn = new DwTextButton();
         viewBtn.setIcon(view.getViewIcon());
         mainToolbar.add(viewBtn);

         viewBtn.addSelectHandler(event -> {
            listCardContainer.setActiveWidget(component);
            sendChangedViewNotice(viewId);

            currentView = listView;
         });
      }
   }
   
   protected void refreshList() {
      if (null != currentFolder)
         folderOpened(currentFolder);
      else
         folderOpened(null);
   }

   protected void sendChangedViewNotice(String viewId) {
      favoriteDao.sendUserViewChangedNotice(viewId, new RsAsyncCallback<Void>());
   }

   protected void initDetail() {
      detailPanel.forceLayout();
   }

   public void setCurrentSpace(TeamSpaceDto currentSpace) {
      if (!appInitialized) {
         this.currentSpace = currentSpace;

         /* init dao */
         treeLoaderDao.setState(currentSpace);
         treeManagerDao.setState(currentSpace);

         /* create store */
         initStores();

         /* tree */
         initTree();

         /* init list */
         initList();
         initDetail();

         /* remember initialization */
         appInitialized = true;

         forceLayout();
      }
   }

   public void displayAddFolderDialog() {
      final DwWindow dialog = DwWindow.newAutoSizeDialog(400);
      dialog.setHeading(TsFavoriteMessages.INSTANCE.addFolderText());
      dialog.setHeaderIcon(BaseIcon.FOLDER_ADD);
      dialog.setModal(true);
      dialog.setSize(430, 260);

      /* create form */
      final SimpleForm form = SimpleForm.getInlineInstance();
      dialog.add(form, new MarginData(10));
      form.setLabelAlign(LabelAlign.LEFT);

      form.addField(String.class, TsDiskFolderDtoPA.INSTANCE.name(), TsFavoriteMessages.INSTANCE.newFolderNameLabel(),
            new SFFCAllowBlank() {
               @Override
               public boolean allowBlank() {
                  return false;
               }
            }, new SFFCPlaceHolder() {

               @Override
               public String getPlaceholder() {
                  return BaseMessages.INSTANCE.unnamed();
               }
            });

      form.addField(String.class, TsDiskFolderDtoPA.INSTANCE.description(),
            TsFavoriteMessages.INSTANCE.newFolderDescriptionLabel(), new SFFCTextAreaImpl(80));

      /* bind dummy */
      final TsDiskFolderDto dummy = new TsDiskFolderDto();
      form.bind(dummy);

      /* add buttons */
      DwTextButton cancelBtn = new DwTextButton(BaseMessages.INSTANCE.cancel());
      dialog.getButtonBar().add(cancelBtn);
      cancelBtn.addSelectHandler(event -> dialog.hide());

      DwTextButton submitBtn = new DwTextButton(TsFavoriteMessages.INSTANCE.addFolderText());
      dialog.getButtonBar().add(submitBtn);
      submitBtn.addSelectHandler(event -> {
         if (form.isValid()) {
            dialog.mask(BaseMessages.INSTANCE.storingMsg());
            favoriteDao.createFolder(currentSpace, currentFolder, dummy, new RsAsyncCallback<TsDiskFolderDto>() {
               @Override
               public void onSuccess(TsDiskFolderDto result) {
                  listStore.add(result);
                  dialog.hide();
               }
            });
         }
      });

      dialog.show();
   }
   
   public void displayUploadFileDialog() {
      final DwWindow dialog = DwWindow.newAutoSizeDialog(400);
      dialog.setHeading(TsFavoriteMessages.INSTANCE.uploadFileText());
      dialog.setHeaderIcon(BaseIcon.REPORT_ADD);
      dialog.setModal(true);
      dialog.setSize(430, 360);

      /* create form */
      final SimpleForm form = SimpleForm.getInlineInstance();
      fileUploadServiceProvider.get().prepareForUpload(form);
      dialog.add(form, new MarginData(10));
      form.setLabelAlign(LabelAlign.LEFT);

      final String nameField = form.addField(String.class, TsDiskFolderDtoPA.INSTANCE.name(),
            TsFavoriteMessages.INSTANCE.newFolderNameLabel(),            
            new SFFCAllowBlank() {
               @Override
               public boolean allowBlank() {
                  return false;
               }
            }, new SFFCPlaceHolder() {

               @Override
               public String getPlaceholder() {
                  return BaseMessages.INSTANCE.unnamed();
               }
            });
      
      /* upload */
      FileUploadFilter uploadFilter = new FileUploadFilter() {
         @Override
         public String doProcess(String name, long size, String base64) {
            if (null == fileUploadMaxFileSizeByte)
               return "Max upload file size can not be determined.";
            if (null == fileUploadEndingWhiteList)
               return "File ending white list can not be determined.";
            
            if (size > fileUploadMaxFileSizeByte) 
               return TsFavoriteMessages.INSTANCE.uploadFileTooLarge(fileUploadMaxFileSizeByte);
            
            ObjectHolder<String> ending = new ObjectHolder<>();
            if (name.contains("."))
               ending.set(name.toLowerCase(Locale.ROOT).substring(name.lastIndexOf(".")+1));
            else
               return TsFavoriteMessages.INSTANCE.noFileEnding();
            
            boolean allowed = fileUploadEndingWhiteList
                  .stream()
                  .anyMatch(allowedType -> ending.get().matches(allowedType.toLowerCase(Locale.ROOT)));
            
            if (!allowed)
               return TsFavoriteMessages.INSTANCE.uploadFileEnding(ending.get());
            
            return null;
         }
      };
      final String uploadField = form.addField(FileUpload.class, FileServerUiModule.UPLOAD_SERVLET_KEY_UPLOAD,
            TsFavoriteMessages.INSTANCE.file(), new SFFCFileUpload() {
               @Override
               public UploadProperties getProperties() {
                  UploadProperties uploadProperties = new UploadProperties("file",
                        TeamSpaceUIModule.TEAMSPACE_FILE_IMPORT_HANDLER_ID);
                  uploadProperties.setFilter(uploadFilter);
                  return uploadProperties;
               }

               @Override
               public boolean enableHTML5() {
                  return true;
               }

               @Override
               public void filesUploaded(List<FileToUpload> list) {
               }

            });

      final String descriptionField = form.addField(String.class, TsDiskFolderDtoPA.INSTANCE.description(),
            TsFavoriteMessages.INSTANCE.newFolderDescriptionLabel(), new SFFCTextAreaImpl(80));

      /* bind dummy */
      final TsDiskFolderDto dummy = new TsDiskFolderDto();
      form.bind(dummy);
      
      form.addSubmitCompleteHandler(submitCompleteEvent -> {
         form.unmask();
         dialog.hide();
         refreshList();
         InfoConfig infoConfig = new DefaultInfoConfig(TsFavoriteMessages.INSTANCE.uploadFileText(),
               TsFavoriteMessages.INSTANCE.uploadFileSuccess());
         infoConfig.setWidth(350);
         Info.display(infoConfig);
      });

      /* add buttons */
      DwTextButton cancelBtn = new DwTextButton(BaseMessages.INSTANCE.cancel());
      dialog.getButtonBar().add(cancelBtn);
      cancelBtn.addSelectHandler(event -> dialog.hide());

      DwTextButton submitBtn = new DwTextButton(TsFavoriteMessages.INSTANCE.uploadFileText());
      dialog.getButtonBar().add(submitBtn);
      submitBtn.addSelectHandler(event -> {
         if (form.isValid()) {
            if (null == form.getValue(uploadField)) {
               InfoConfig infoConfig = new DefaultInfoConfig(TsFavoriteMessages.INSTANCE.uploadFileText(),
                     TsFavoriteMessages.INSTANCE.uploadFileEmpty());
               infoConfig.setWidth(350);
               Info.display(infoConfig);
               return;
            }
            form.addHidden(
                  FileUploadUIModule.UPLOAD_FILE_CONTEXT_PREFIX + TeamSpaceUIModule.TEAMSPACE_IMPORT_TEAMSPACE_ID,
                  currentSpace.getId());
            if (null != currentFolder) {
               form.addHidden(
                     FileUploadUIModule.UPLOAD_FILE_CONTEXT_PREFIX + TeamSpaceUIModule.TEAMSPACE_IMPORT_FOLDER_ID,
                     currentFolder.getId());
            }
            form.addHidden(
                  FileUploadUIModule.UPLOAD_FILE_CONTEXT_PREFIX + TeamSpaceUIModule.TEAMSPACE_IMPORT_NAME,
                  form.getValue(nameField));
            if (null != form.getValue(descriptionField)) {
               form.addHidden(
                     FileUploadUIModule.UPLOAD_FILE_CONTEXT_PREFIX + TeamSpaceUIModule.TEAMSPACE_IMPORT_DESCRIPTION,
                     form.getValue(descriptionField));
            }
            form.submit();
            InfoConfig infoConfig = new DefaultInfoConfig(TsFavoriteMessages.INSTANCE.uploadFileText(),
                  TsFavoriteMessages.INSTANCE.uploadFileUploading());
            infoConfig.setWidth(350);
            Info.display(infoConfig);
            form.mask(BaseMessages.INSTANCE.loadingMsg());
         }
      });

      dialog.show();
   }

   public void itemSelected(AbstractTsDiskNodeDto item) {
      History.newItem(TsDiskUIModule.TEAMSPACE_SELECT_ITEM_HISTORY_TOKEN + "/ts:" + getCurrentSpace().getId() + "&sel:"
            + item.getId() + (null != getCurrentFolder() ? "&id" + getCurrentFolder().getId() : ""), false);

      /* clear detail panel */
      if (detailPanelContainer.getWidgetCount() > 1)
         detailPanelContainer.remove(1);
      detailPanel.setHeaderVisible(false);

      ObjectPreviewTabPanel infoPanel = objectInformationPanelProvider.get();
      infoPanel.setBottomTabs(true);
      infoPanel.displayInformationOn(item);

      infoPanel.getHeader().addDomHandler(event -> {
         if (listPanel.isCollapsed())
            listPanel.expand();
         else
            listPanel.collapse();
      }, DoubleClickEvent.getType());

      if (infoPanel.hasItems()) {
         detailPanelContainer.add(infoPanel, new VerticalLayoutData(1, 1));
      }
      detailPanel.forceLayout();
   }

   public void itemOpened(AbstractTsDiskNodeDto item) {
      if (item instanceof TsDiskFolderDto)
         folderOpened((TsDiskFolderDto) item, null);
      else if (item instanceof TsDiskRootDto)
         folderOpened(null, null);
      else if (item instanceof TsDiskGeneralReferenceDto)
         generalReferenceOpenend((TsDiskGeneralReferenceDto) item);
   }

   private void generalReferenceOpenend(final TsDiskGeneralReferenceDto item) {
      History.newItem(TsDiskUIModule.TEAMSPACE_OPEN_ITEM_HISTORY_TOKEN + "/id:" + item.getId(), false);

      Optional<GeneralReferenceHandlerHook> responsibleHooker = hookHandlerService.getHookers(GeneralReferenceHandlerHook.class)
         .stream()
         .filter(hooker -> hooker.consumes(item))
         .findAny();
      
      if (responsibleHooker.isPresent())
         responsibleHooker.get().handle(item, this);
   }

   public void folderOpened(TsDiskFolderDto folder) {
      folderOpened(folder, null);
   }

   public void folderOpened(TsDiskFolderDto folder, final Long selectChildId) {
      folderOpened(folder, selectChildId, false);
   }

   public void folderOpened(TsDiskFolderDto folder, final Long selectChildId, final boolean execute) {
      History.newItem(TsDiskUIModule.TEAMSPACE_SELECT_ITEM_HISTORY_TOKEN + "/ts:" + getCurrentSpace().getId()
            + (null != folder ? "&id:" + folder.getId() : "") + (null != selectChildId ? "&sel:" + selectChildId : ""),
            false);

      listPanel.setHeading(TsFavoriteMessages.INSTANCE.listPanelHeader(null == folder ? "Root" : folder.getName()));

      currentFolder = folder;
      listStore.clear();
      mainPanel.mask(BaseMessages.INSTANCE.loadingMsg());
      favoriteDao.getItemsIn(currentSpace, currentFolder, new RsAsyncCallback<TsDiskItemList>() {
         @Override
         public void onSuccess(TsDiskItemList result) {
            listStore.clear();
            listStore.addAll(result.getItems());
            updateTree(result.getPath());
            if (null != selectChildId) {
               final AbstractTsDiskNodeDto model = result.getItem(selectChildId);
               if (null != model) {
                  Scheduler.get().scheduleDeferred(new ScheduledCommand() {
                     @Override
                     public void execute() {
                        currentView.select(model);
                        if (execute)
                           itemOpened(model);
                     }
                  });
               }
            }
            mainPanel.unmask();
         }
      });
   }

   public DtoAwareListStore<AbstractTsDiskNodeDto> getListStore() {
      return listStore;
   }

   public TsDiskFolderDto getCurrentFolder() {
      return currentFolder;
   }

   public TeamSpaceDto getCurrentSpace() {
      return currentSpace;
   }

   public AbstractTsDiskNodeDto getModelById(final Long id) {
      if (null == id)
         return null;
      
      return listStore.getAll()
         .stream()
         .filter(model -> id.equals(model.getId()))
         .findAny()
         .orElse(null);
   }

   public void importReport(ReportDto report, boolean copyReport, boolean reference) {
      mainPanel.mask(BaseMessages.INSTANCE.storingMsg());
      favoriteDao.importReport(currentSpace, currentFolder, report, copyReport, reference,
            new RsAsyncCallback<TsDiskReportReferenceDto>() {
               @Override
               public void onSuccess(TsDiskReportReferenceDto result) {
                  listStore.add(result);
                  mainPanel.unmask();
               }

               @Override
               public void onFailure(Throwable caught) {
                  mainPanel.unmask();
               }
            });
   }

   public boolean allowDrag() {
      return teamSpaceService.isUser(currentSpace);
   }

   protected void updateTree(List<AbstractTsDiskNodeDto> path) {
      if (!treePanel.isExpanded())
         return;

      if (null == currentFolder && tree.getStore().getRootCount() > 0)
         tree.getSelectionModel().select(treeStore.getRootItems().get(0), false);
      else if (null != treeStore.findModel(currentFolder))
         tree.getSelectionModel().select(currentFolder, false);
      else {
         path.add(currentFolder);
         tree.expandPath(path);
      }
   }

   public void setInDrag(boolean inDrag) {
      this.inDrag = inDrag;
   }

   public boolean isInDrag() {
      return inDrag;
   }

   public List<AbstractTsDiskNodeDto> getCurrentPath() {
      return currentPath;
   }

   public void addToStore(TsDiskGeneralReferenceDto result) {
      listStore.add(result);
   }

}
