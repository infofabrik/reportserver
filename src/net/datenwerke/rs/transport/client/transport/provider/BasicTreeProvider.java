package net.datenwerke.rs.transport.client.transport.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTree;
import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTreeFactory;
import net.datenwerke.gf.client.treedb.TreeDBUIService;
import net.datenwerke.gf.client.treedb.stores.EnhancedTreeStore;
import net.datenwerke.rs.transport.client.transport.TransportTreeLoaderDao;
import net.datenwerke.rs.transport.client.transport.TransportTreeManagerDao;
import net.datenwerke.rs.transport.client.transport.TransportUIModule;
import net.datenwerke.rs.transport.client.transport.dto.AbstractTransportManagerNodeDto;

public class BasicTreeProvider implements Provider<ManagerHelperTree> {

   private final TreeDBUIService treeDBUIService;
   private final TransportTreeLoaderDao treeLoader;
   private final TransportTreeManagerDao treeManager;
   private final ManagerHelperTreeFactory treeFactory;

   @Inject
   public BasicTreeProvider(
         TreeDBUIService treeDBUIService, 
         TransportTreeLoaderDao treeLoader,
         TransportTreeManagerDao treeManager, 
         ManagerHelperTreeFactory treeFactory
         ) {

      this.treeDBUIService = treeDBUIService;
      this.treeLoader = treeLoader;
      this.treeManager = treeManager;
      this.treeFactory = treeFactory;
   }

   public ManagerHelperTree get() {
      /* store */
      EnhancedTreeStore store = treeDBUIService.getUITreeStore(AbstractTransportManagerNodeDto.class, treeLoader,
            true);

      /* build tree */
      final ManagerHelperTree tree = treeFactory.create(TransportUIModule.class, store, treeLoader,
            treeManager);
      tree.configureIconProvider();

      return tree;
   }
}
