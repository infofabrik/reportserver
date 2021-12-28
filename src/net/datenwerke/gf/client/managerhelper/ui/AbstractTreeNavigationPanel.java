package net.datenwerke.gf.client.managerhelper.ui;

import java.util.Collection;
import java.util.Stack;

import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gf.client.managerhelper.hooks.ManagerHelperTreeToolbarEnhancerHook;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.theme.CssClassConstant;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.treedb.client.treedb.TreeDbManagerContainer;
import net.datenwerke.treedb.client.treedb.TreeDbManagerDao;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

/**
 * 
 *
 */
abstract public class AbstractTreeNavigationPanel extends DwContentPanel implements TreeDbManagerContainer {

   @CssClassConstant
   public static final String CSS_NAME = "rs-mngr-nav";

   @CssClassConstant
   public static final String CSS_TOOLBAR_NAME = "rs-mngr-nav-tb";

   private final HookHandlerService hookHandler;
   final private UITree tree;

   private ToolBar toolbar;
   private AbstractTreeManagerPanel managerPanel;

   public AbstractTreeNavigationPanel(HookHandlerService hookHandler, UITree tree) {
      this.hookHandler = hookHandler;
      this.tree = tree;

      initializeUI();
   }

   @Override
   public String getCssName() {
      return super.getCssName() + " " + CSS_NAME;
   }

   public static String getCssToolbarName() {
      return CSS_TOOLBAR_NAME;
   }

   protected void initializeUI() {
      setHeaderVisible(false);

      VerticalLayoutContainer container = new VerticalLayoutContainer();
      add(container);

      /* toolbar */
      container.add(initToolbar(), new VerticalLayoutContainer.VerticalLayoutData(1, 39));

      /* add tree */
      container.add(tree, new VerticalLayoutContainer.VerticalLayoutData(1, 1));
   }

   protected ToolBar initToolbar() {
      toolbar = new DwToolBar();
      toolbar.getElement().addClassName(getCssToolbarName());

      Collection<ManagerHelperTreeToolbarEnhancerHook> tbEnhancer = hookHandler
            .getHookers(ManagerHelperTreeToolbarEnhancerHook.class);
      for (ManagerHelperTreeToolbarEnhancerHook enhancer : tbEnhancer)
         enhancer.treeNavigationToolbarEnhancerHook_addLeft(toolbar, tree, this);
      toolbar.add(new FillToolItem());
      for (ManagerHelperTreeToolbarEnhancerHook enhancer : tbEnhancer)
         enhancer.treeNavigationToolbarEnhancerHook_addRight(toolbar, tree, this);

      return toolbar;
   }

   public ToolBar getToolbar() {
      return toolbar;
   }

   public void init(AbstractTreeManagerPanel managerPanel) {
      this.managerPanel = managerPanel;
   }

   @Override
   public TreeDbManagerDao getTreeManager() {
      return managerPanel.getTreeDbManager();
   }

   public UITree getTree() {
      return tree;
   }

   public String getPath(AbstractNodeDto selectedNode) {
      Stack<String> stack = new Stack<String>();

      stack.push(selectedNode.toDisplayTitle());
      AbstractNodeDto parent = tree.getStore().getParent(selectedNode);
      while (null != parent) {
         if (null == parent.getParentNodeId())
            stack.push(parent.getRootName());
         else
            stack.push(parent.toDisplayTitle());
         parent = tree.getStore().getParent(parent);
      }

      StringBuilder sb = new StringBuilder();
      while (!stack.isEmpty())
         sb.append("/").append(stack.pop());
      return sb.toString();
   }
}
