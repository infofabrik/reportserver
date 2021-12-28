package net.datenwerke.rs.dropbox.client.dropbox.provider;

import java.util.Arrays;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTree;
import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTreeFactory;
import net.datenwerke.gf.client.treedb.TreeDBUIService;
import net.datenwerke.gf.client.treedb.stores.EnhancedTreeStore;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.rs.core.client.datasinkmanager.DatasinkTreeLoaderDao;
import net.datenwerke.rs.core.client.datasinkmanager.DatasinkTreeManagerDao;
import net.datenwerke.rs.core.client.datasinkmanager.DatasinkUIModule;
import net.datenwerke.rs.core.client.datasinkmanager.dto.AbstractDatasinkManagerNodeDto;
import net.datenwerke.rs.core.client.datasinkmanager.dto.posomap.DatasinkFolderDto2PosoMap;
import net.datenwerke.rs.dropbox.client.dropbox.dto.posomap.DropboxDatasinkDto2PosoMap;

public class DropboxTreeProvider implements Provider<ManagerHelperTree> {

   private final TreeDBUIService treeDBUIService;
   private final DatasinkTreeLoaderDao datasinkTreeLoader;
   private final DatasinkTreeManagerDao datasinkTreeManager;
   private final ManagerHelperTreeFactory treeFactory;

   @Inject
   public DropboxTreeProvider(TreeDBUIService treeDBUIService, DatasinkTreeLoaderDao datasinkTreeLoader,
         DatasinkTreeManagerDao datasinkTreeManager, ManagerHelperTreeFactory treeFactory) {

      this.treeDBUIService = treeDBUIService;
      this.datasinkTreeLoader = datasinkTreeLoader;
      this.datasinkTreeManager = datasinkTreeManager;
      this.treeFactory = treeFactory;
   }

   @Override
   public ManagerHelperTree get() {
      /* store */
      List<Dto2PosoMapper> filters = Arrays.asList(new DatasinkFolderDto2PosoMap(), new DropboxDatasinkDto2PosoMap());

      EnhancedTreeStore store = treeDBUIService.getUITreeStore(AbstractDatasinkManagerNodeDto.class, datasinkTreeLoader,
            false, filters);

      /* build tree */
      final ManagerHelperTree tree = treeFactory.create(DatasinkUIModule.class, store, datasinkTreeLoader,
            datasinkTreeManager);
      tree.configureIconProvider();

      return tree;
   }

}
