package net.datenwerke.rs.remoteserver.client.remoteservermanager.provider;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTree;
import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTreeFactory;
import net.datenwerke.gf.client.treedb.TreeDBUIService;
import net.datenwerke.gf.client.treedb.stores.EnhancedTreeStore;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.RemoteServerTreeLoaderDao;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.RemoteServerTreeManagerDao;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.RemoteServerUIModule;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.AbstractRemoteServerManagerNodeDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.posomap.RemoteServerFolderDto2PosoMap;

public class FolderTreeProvider implements Provider<ManagerHelperTree> {

   private final TreeDBUIService treeDBUIService;
   private final RemoteServerTreeLoaderDao remoteserverTreeLoader;
   private final RemoteServerTreeManagerDao remoteserverTreeManager;
   private final ManagerHelperTreeFactory treeFactory;

   @Inject
   public FolderTreeProvider(TreeDBUIService treeDBUIService, RemoteServerTreeLoaderDao remoteserverTreeLoader,
         RemoteServerTreeManagerDao remoteserverTreeManager, ManagerHelperTreeFactory treeFactory) {

      this.treeDBUIService = treeDBUIService;
      this.remoteserverTreeLoader = remoteserverTreeLoader;
      this.remoteserverTreeManager = remoteserverTreeManager;
      this.treeFactory = treeFactory;
   }

   @Override
   public ManagerHelperTree get() {
      /* store */
      List<Dto2PosoMapper> filters = new ArrayList<Dto2PosoMapper>();
      filters.add(new RemoteServerFolderDto2PosoMap());
      EnhancedTreeStore store = treeDBUIService.getUITreeStore(AbstractRemoteServerManagerNodeDto.class, remoteserverTreeLoader,
            true, filters);

      /* build tree */
      final ManagerHelperTree tree = treeFactory.create(RemoteServerUIModule.class, store, remoteserverTreeLoader,
            remoteserverTreeManager);
      tree.configureIconProvider();

      return tree;
   }

}
