package net.datenwerke.rs.fileserver.client.fileserver.provider;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTree;
import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTreeFactory;
import net.datenwerke.gf.client.treedb.TreeDBUIService;
import net.datenwerke.gf.client.treedb.stores.EnhancedTreeStore;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerTreeLoaderDao;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerTreeManagerDao;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerUiModule;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.posomap.FileServerFolderDto2PosoMap;

public class FolderTreeProvider implements Provider<ManagerHelperTree> {

   private final TreeDBUIService treeDBUIService;
   private final FileServerTreeLoaderDao treeLoader;
   private final FileServerTreeManagerDao treeManager;
   private final ManagerHelperTreeFactory treeFactory;

   @Inject
   public FolderTreeProvider(TreeDBUIService treeDBUIService, FileServerTreeLoaderDao treeLoader,
         FileServerTreeManagerDao treeManager, ManagerHelperTreeFactory treeFactory) {

      this.treeDBUIService = treeDBUIService;
      this.treeLoader = treeLoader;
      this.treeManager = treeManager;
      this.treeFactory = treeFactory;
   }

   public ManagerHelperTree get() {
      /* store */
      List<Dto2PosoMapper> filters = new ArrayList<Dto2PosoMapper>();
      filters.add(new FileServerFolderDto2PosoMap());
      EnhancedTreeStore store = treeDBUIService.getUITreeStore(AbstractFileServerNodeDto.class, treeLoader, true,
            filters);

      /* build tree */
      final ManagerHelperTree tree = treeFactory.create(FileServerUiModule.class, store, treeLoader, treeManager);
      tree.configureIconProvider();

      return tree;
   }
}
