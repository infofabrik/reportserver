package net.datenwerke.rs.dashboard.client.dashboard.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTree;
import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTreeFactory;
import net.datenwerke.gf.client.treedb.TreeDBUIService;
import net.datenwerke.gf.client.treedb.stores.EnhancedTreeStore;
import net.datenwerke.rs.dashboard.client.dashboard.DashboardTreeLoaderDao;
import net.datenwerke.rs.dashboard.client.dashboard.DashboardTreeManagerDao;
import net.datenwerke.rs.dashboard.client.dashboard.DashboardUiModule;
import net.datenwerke.rs.dashboard.client.dashboard.dto.AbstractDashboardManagerNodeDto;

public class BasicTreeProvider implements Provider<ManagerHelperTree> {

   private final TreeDBUIService treeDBUIService;
   private final DashboardTreeLoaderDao treeLoader;
   private final DashboardTreeManagerDao treeManager;
   private final ManagerHelperTreeFactory treeFactory;

   @Inject
   public BasicTreeProvider(TreeDBUIService treeDBUIService, DashboardTreeLoaderDao treeLoader,
         DashboardTreeManagerDao treeManager, ManagerHelperTreeFactory treeFactory) {

      this.treeDBUIService = treeDBUIService;
      this.treeLoader = treeLoader;
      this.treeManager = treeManager;
      this.treeFactory = treeFactory;
   }

   public ManagerHelperTree get() {
      /* store */
      EnhancedTreeStore store = treeDBUIService.getUITreeStore(AbstractDashboardManagerNodeDto.class, treeLoader,
            true);

      /* build tree */
      final ManagerHelperTree tree = treeFactory.create(DashboardUiModule.class, store, treeLoader, treeManager);
      tree.configureIconProvider();

      return tree;
   }
}
