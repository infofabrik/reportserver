package net.datenwerke.rs.core.client.datasourcemanager.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTree;
import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTreeFactory;
import net.datenwerke.gf.client.treedb.TreeDBUIService;
import net.datenwerke.gf.client.treedb.stores.EnhancedTreeStore;
import net.datenwerke.rs.core.client.datasourcemanager.DatasourceTreeLoaderDao;
import net.datenwerke.rs.core.client.datasourcemanager.DatasourceTreeManagerDao;
import net.datenwerke.rs.core.client.datasourcemanager.DatasourceUIModule;
import net.datenwerke.rs.core.client.datasourcemanager.dto.AbstractDatasourceManagerNodeDto;

public class BasicTreeProvider implements Provider<ManagerHelperTree> {

   private final TreeDBUIService treeDBUIService;
   private final DatasourceTreeLoaderDao datasourceTreeLoader;
   private final DatasourceTreeManagerDao datasourceTreeManager;
   private final ManagerHelperTreeFactory treeFactory;

   @Inject
   public BasicTreeProvider(TreeDBUIService treeDBUIService, DatasourceTreeLoaderDao datasourceTreeLoader,
         DatasourceTreeManagerDao datasourceTreeManager, ManagerHelperTreeFactory treeFactory) {

      this.treeDBUIService = treeDBUIService;
      this.datasourceTreeLoader = datasourceTreeLoader;
      this.datasourceTreeManager = datasourceTreeManager;
      this.treeFactory = treeFactory;
   }

   public ManagerHelperTree get() {
      /* store */
      EnhancedTreeStore store = treeDBUIService.getUITreeStore(AbstractDatasourceManagerNodeDto.class,
            datasourceTreeLoader, true);

      /* build tree */
      final ManagerHelperTree tree = treeFactory.create(DatasourceUIModule.class, store, datasourceTreeLoader,
            datasourceTreeManager);
      tree.configureIconProvider();

      return tree;
   }
}
