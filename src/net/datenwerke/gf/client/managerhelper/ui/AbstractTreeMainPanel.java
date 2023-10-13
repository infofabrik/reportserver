package net.datenwerke.gf.client.managerhelper.ui;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel.TabPanelAppearance;
import com.sencha.gxt.widget.core.client.TabPanel.TabPanelBottomAppearance;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;

import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewToolbarConfiguratorHook;
import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.DwTabPanel;
import net.datenwerke.gxtdto.client.theme.CssClassConstant;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwHookableToolbar;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.treedb.client.treedb.TreeDbManagerDao;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

/**
 * Base component, that renders the main field of a tree manager component.
 * 
 * 
 * 
 *
 */
abstract public class AbstractTreeMainPanel extends DwContentPanel {

   @CssClassConstant
   public static final String CSS_NAME = "rs-mngr-main";

   @CssClassConstant
   public static final String CSS_TOOLBAR_NAME = "rs-mngr-main-tb";

   @Inject
   private static HookHandlerService hookHandler;

   @Inject
   private Provider<DwHookableToolbar> toolbarProvider;

   /**
    * Stores the tree manager that is invoked if forms are submitted.
    */
   private final TreeDbManagerDao treeManager;

   final Map<MainPanelView, Widget> viewComponents = new HashMap<MainPanelView, Widget>();
   final Map<MainPanelView, VerticalLayoutContainer> mainPanelLookup = new HashMap<MainPanelView, VerticalLayoutContainer>();

   private AbstractTreeNavigationPanel treePanel;

   private DwTabPanel tabPanel;

   private String showTabOnSelection;

   public AbstractTreeMainPanel(TreeDbManagerDao treeManager) {

      /* store objects */
      this.treeManager = treeManager;

      initializeUI();
   }

   @Override
   public String getCssName() {
      return super.getCssName() + " " + CSS_NAME;
   }

   public String getCssToolbarName() {
      return CSS_TOOLBAR_NAME;
   }

   private void initializeUI() {
      setHeaderVisible(false);
   }

   public TreeDbManagerDao getTreeDbManager() {
      return treeManager;
   }

   public void displayTreeSelection(final List<MainPanelView> views, final AbstractNodeDto selectedNode,
         final UITree tree) {
      /* clear container */
      clear();

      /* clear maps */
      viewComponents.clear();
      mainPanelLookup.clear();

      /* build tab panel */
      tabPanel = new DwTabPanel(GWT.<TabPanelAppearance>create(TabPanelBottomAppearance.class));

      tabPanel.setAutoSelect(false);
      tabPanel.setBodyBorder(false);
      tabPanel.setBorders(false);

      /* add module's widget */
      boolean bFirst = true;
      for (final MainPanelView view : views) {
         /* init */

         /* create inner tab panel */
         final VerticalLayoutContainer viewWrapper = new VerticalLayoutContainer();
         viewWrapper.setBorders(false);
         DwHookableToolbar toolbar = createToolbar(view, selectedNode);
         viewWrapper.add(toolbar, new VerticalLayoutData(1, 39));

         /* add to lookup table */
         mainPanelLookup.put(view, viewWrapper);

         /* add tab to panel */
         TabItemConfig tabItemConfig = new TabItemConfig(view.getComponentHeader());
         if (null != view.getIcon())
            tabItemConfig.setIcon(view.getIcon());
         tabPanel.add(viewWrapper, tabItemConfig);

         /* initialize view */
         if (bFirst) {
            bFirst = false;
            if (null == showTabOnSelection || !viewsContain(showTabOnSelection, views)) {
               display(view, selectedNode, tree, false);
               tabPanel.setActiveWidget(viewWrapper);
            }
         }

         if (null != showTabOnSelection && showTabOnSelection.equals(view.getViewId())) {
            display(view, selectedNode, tree, false);
            tabPanel.setActiveWidget(viewWrapper);
         }

      }

      /* add listener to tab selection */
      tabPanel.addSelectionHandler(new SelectionHandler<Widget>() {
         @Override
         public void onSelection(SelectionEvent<Widget> event) {
            Widget viewWrapper = event.getSelectedItem();
            for (Entry<MainPanelView, VerticalLayoutContainer> e : mainPanelLookup.entrySet()) {
               if (e.getValue() == viewWrapper) {
                  display(e.getKey(), selectedNode, tree, false);
                  break;
               }
            }
         }
      });

      /* add tab panel */
      setWidget(tabPanel);

      /* rerender */
      forceLayout();
   }
   
   private DwHookableToolbar createToolbar(final MainPanelView view, final AbstractNodeDto selectedNode) {
      /* add toolbar */
      DwHookableToolbar toolbar = toolbarProvider.get();
      toolbar.getElement().addClassName(getCssToolbarName());
      toolbar.setContainerName(getToolbarName());


      /* configure dwToolbar */
      toolbar.getHookConfig().put("id", String.valueOf(selectedNode.getId()));
      toolbar.getHookConfig().put("classname", selectedNode.getClass().getName());
      toolbar.getHookConfig().put("path", treePanel.getPath(selectedNode));

      /* configure toolbar */
      Collection<MainPanelViewToolbarConfiguratorHook> toolbarConfigurators = hookHandler
            .getHookers(MainPanelViewToolbarConfiguratorHook.class);
      for (MainPanelViewToolbarConfiguratorHook configurator : toolbarConfigurators)
         configurator.mainPanelViewToolbarConfiguratorHook_addLeft(view, toolbar, selectedNode);

      toolbar.addBaseHookersLeft();

      toolbar.add(new FillToolItem());

      for (MainPanelViewToolbarConfiguratorHook configurator : toolbarConfigurators)
         configurator.mainPanelViewToolbarConfiguratorHook_addRight(view, toolbar, selectedNode);

      toolbar.addBaseHookersRight();
      return toolbar;
   }

   private boolean viewsContain(String viewId, List<MainPanelView> views) {
      for (MainPanelView view : views)
         if (null != view.getViewId() && view.getViewId().equals(viewId))
            return true;

      return false;
   }

   protected void display(MainPanelView view, AbstractNodeDto selectedNode, UITree tree, boolean select) {
      VerticalLayoutContainer viewWrapper = mainPanelLookup.get(view);
      if (null == viewWrapper)
         return;

      if (!viewComponents.containsKey(view)) {
         view.initializeView(selectedNode, tree, treeManager, AbstractTreeMainPanel.this);
         Widget viewComponent = view.getViewComponent(selectedNode);
         viewComponents.put(view, viewComponent);

         viewWrapper.add(viewComponent, new VerticalLayoutData(1, 1));
         view.viewAdded(viewWrapper);
      }

      if (select)
         tabPanel.setActiveWidget(viewWrapper, false);

      if (view.isSticky())
         showTabOnSelection = view.getViewId();
      else
         showTabOnSelection = null;

      viewWrapper.forceLayout();
   }

   protected String getToolbarName() {
      return "AbstractTreeMainPanelToolbar";
   }

   public void reloadView(MainPanelView view, AbstractNodeDto selectedNode) {
      if (viewComponents.containsKey(view)) {
         Widget viewComponent = view.getViewComponent(selectedNode);
         viewComponents.put(view, viewComponent);

         VerticalLayoutContainer mainPanel = mainPanelLookup.get(view);

         mainPanel.clear();
         DwHookableToolbar toolbar = createToolbar(view, selectedNode);
         mainPanel.add(toolbar, new VerticalLayoutData(1, 39));
         mainPanel.add(viewComponent, new VerticalLayoutData(1, 1));
         mainPanel.forceLayout();
      }
   }

   public void init(AbstractTreeManagerPanel managerPanel) {
   }

   public void setTree(AbstractTreeNavigationPanel treePanel) {
      this.treePanel = treePanel;
   }

   public void showTabOnSelection(String id) {
      this.showTabOnSelection = id;
   }

   public void showTab(String id, AbstractNodeDto selectedNode, UITree tree) {
      if (null == id)
         return;

      for (MainPanelView view : mainPanelLookup.keySet()) {
         if (id.equals(view.getViewId())) {
            display(view, selectedNode, tree, true);
            break;
         }
      }
   }
}
