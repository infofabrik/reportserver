package net.datenwerke.rs.dashboard.client.dashboard.provider;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTree;
import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTreeFactory;
import net.datenwerke.gf.client.treedb.TreeDBUIService;
import net.datenwerke.gf.client.treedb.stores.EnhancedTreeStore;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.rs.dashboard.client.dashboard.DashboardTreeLoaderDao;
import net.datenwerke.rs.dashboard.client.dashboard.DashboardTreeManagerDao;
import net.datenwerke.rs.dashboard.client.dashboard.DashboardUiModule;
import net.datenwerke.rs.dashboard.client.dashboard.dto.AbstractDashboardManagerNodeDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.DadgetNodeDto2PosoMap;
import net.datenwerke.rs.dashboard.client.dashboard.dto.posomap.DashboardFolderDto2PosoMap;

public class DadgetsTreeProvider implements Provider<ManagerHelperTree> {

   private final TreeDBUIService treeDBUIService;
   private final DashboardTreeLoaderDao treeLoader;
   private final DashboardTreeManagerDao treeManager;
   private final ManagerHelperTreeFactory treeFactory;

   @Inject
   public DadgetsTreeProvider(TreeDBUIService treeDBUIService, DashboardTreeLoaderDao treeLoader,
         DashboardTreeManagerDao treeManager, ManagerHelperTreeFactory treeFactory) {

      this.treeDBUIService = treeDBUIService;
      this.treeLoader = treeLoader;
      this.treeManager = treeManager;
      this.treeFactory = treeFactory;
   }

   public ManagerHelperTree get() {
      /* store */
      List<Dto2PosoMapper> filters = new ArrayList<Dto2PosoMapper>();
      filters.add(new DashboardFolderDto2PosoMap());
      filters.add(new DadgetNodeDto2PosoMap());
      EnhancedTreeStore store = treeDBUIService.getUITreeStore(AbstractDashboardManagerNodeDto.class, treeLoader, false,
            filters);

      /* build tree */
      final ManagerHelperTree tree = treeFactory.create(DashboardUiModule.class, store, treeLoader, treeManager);
      tree.configureIconProvider();

      return tree;
   }
}
