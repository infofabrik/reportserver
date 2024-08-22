package net.datenwerke.rs.remoteserver.client.remoteservermanager.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTree;
import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTreeFactory;
import net.datenwerke.gf.client.treedb.TreeDBUIService;
import net.datenwerke.gf.client.treedb.stores.EnhancedTreeStore;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.RemoteServerTreeLoaderDao;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.RemoteServerTreeManagerDao;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.RemoteServerUIModule;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.AbstractRemoteServerManagerNodeDto;

public class BasicTreeProvider implements Provider<ManagerHelperTree> {

   private final TreeDBUIService treeDBUIService;
   private final RemoteServerTreeLoaderDao remoteserverTreeLoader;
   private final RemoteServerTreeManagerDao remoteserverTreeManager;
   private final ManagerHelperTreeFactory treeFactory;

   @Inject
   public BasicTreeProvider(TreeDBUIService treeDBUIService, RemoteServerTreeLoaderDao remoteserverTreeLoader,
         RemoteServerTreeManagerDao remoteserverTreeManager, ManagerHelperTreeFactory treeFactory) {

      this.treeDBUIService = treeDBUIService;
      this.remoteserverTreeLoader = remoteserverTreeLoader;
      this.remoteserverTreeManager = remoteserverTreeManager;
      this.treeFactory = treeFactory;
   }

   public ManagerHelperTree get() {
      /* store */
      EnhancedTreeStore store = treeDBUIService.getUITreeStore(AbstractRemoteServerManagerNodeDto.class, remoteserverTreeLoader,
            true);

      /* build tree */
      final ManagerHelperTree tree = treeFactory.create(RemoteServerUIModule.class, store, remoteserverTreeLoader,
            remoteserverTreeManager);
      tree.configureIconProvider();

      return tree;
   }
}
