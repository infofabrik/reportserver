package net.datenwerke.rs.transport.client.transport.provider;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTree;
import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTreeFactory;
import net.datenwerke.gf.client.treedb.TreeDBUIService;
import net.datenwerke.gf.client.treedb.stores.EnhancedTreeStore;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.rs.transport.client.transport.TransportTreeLoaderDao;
import net.datenwerke.rs.transport.client.transport.TransportTreeManagerDao;
import net.datenwerke.rs.transport.client.transport.TransportUIModule;
import net.datenwerke.rs.transport.client.transport.dto.AbstractTransportManagerNodeDto;
import net.datenwerke.rs.transport.client.transport.dto.posomap.TransportFolderDto2PosoMap;

public class FolderTreeProvider implements Provider<ManagerHelperTree> {

   private final TreeDBUIService treeDBUIService;
   private final TransportTreeLoaderDao treeLoader;
   private final TransportTreeManagerDao treeManager;
   private final ManagerHelperTreeFactory treeFactory;

   @Inject
   public FolderTreeProvider(
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

   @Override
   public ManagerHelperTree get() {
      /* store */
      List<Dto2PosoMapper> filters = new ArrayList<Dto2PosoMapper>();
      filters.add(new TransportFolderDto2PosoMap());
      EnhancedTreeStore store = treeDBUIService.getUITreeStore(AbstractTransportManagerNodeDto.class, treeLoader,
            false, filters);

      /* build tree */
      final ManagerHelperTree tree = treeFactory.create(TransportUIModule.class, store, treeLoader, treeManager);
      tree.configureIconProvider();

      return tree;
   }

}
