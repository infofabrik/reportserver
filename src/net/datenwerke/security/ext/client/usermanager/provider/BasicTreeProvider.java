package net.datenwerke.security.ext.client.usermanager.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTree;
import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTreeFactory;
import net.datenwerke.gf.client.treedb.TreeDBUIService;
import net.datenwerke.gf.client.treedb.stores.EnhancedTreeStore;
import net.datenwerke.security.client.usermanager.dto.AbstractUserManagerNodeDto;
import net.datenwerke.security.ext.client.usermanager.UserManagerTreeLoaderDao;
import net.datenwerke.security.ext.client.usermanager.UserManagerTreeManagerDao;
import net.datenwerke.security.ext.client.usermanager.UserManagerUIModule;

/**
 * Provides the user manager tree with all goodies.
 *
 */
public class BasicTreeProvider implements Provider<ManagerHelperTree> {

   private final TreeDBUIService treeDBUIService;
   private final UserManagerTreeLoaderDao userManagerTreeLoader;
   private final UserManagerTreeManagerDao userManagerTreeManager;
   private final ManagerHelperTreeFactory treeFactory;

   @Inject
   public BasicTreeProvider(TreeDBUIService treeDBUIService, UserManagerTreeLoaderDao userManagerTreeLoader,
         UserManagerTreeManagerDao userManagerTreeManager, ManagerHelperTreeFactory treeFactory) {

      /* store objects */
      this.treeDBUIService = treeDBUIService;
      this.userManagerTreeLoader = userManagerTreeLoader;
      this.userManagerTreeManager = userManagerTreeManager;
      this.treeFactory = treeFactory;
   }

   public ManagerHelperTree get() {
      /* store */
      EnhancedTreeStore store = treeDBUIService.getUITreeStore(AbstractUserManagerNodeDto.class, userManagerTreeLoader,
            false);

      /* build tree */
      final ManagerHelperTree tree = treeFactory.create(UserManagerUIModule.class, store, userManagerTreeLoader,
            userManagerTreeManager);
      tree.configureIconProvider();

      return tree;
   }
}
