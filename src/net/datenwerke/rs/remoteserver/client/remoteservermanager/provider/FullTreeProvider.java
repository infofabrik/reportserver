package net.datenwerke.rs.remoteserver.client.remoteservermanager.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTree;
import net.datenwerke.gf.client.treedb.dnd.UITreeDragDropConfiguration;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.RemoteServerTreeManagerDao;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.RemoteServerUIModule;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerFolderDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.ui.RemoteServerManagerMainPanel;

public class FullTreeProvider implements Provider<ManagerHelperTree> {

   private final BasicTreeProvider basicTreeProvider;
   private final RemoteServerTreeManagerDao remoteserverTreeHandler;
   private final RemoteServerManagerMainPanel mainPanel;

   @Inject
   public FullTreeProvider(BasicTreeProvider basicTreeProvider, RemoteServerTreeManagerDao remoteserverTreeHandler,
         RemoteServerManagerMainPanel mainPanel) {

      this.basicTreeProvider = basicTreeProvider;
      this.remoteserverTreeHandler = remoteserverTreeHandler;
      this.mainPanel = mainPanel;
   }

   public ManagerHelperTree get() {
      /* build tree */
      final ManagerHelperTree tree = basicTreeProvider.get();
      tree.getStore().enableDtoAwareness(true);

      /* dnd */
      UITreeDragDropConfiguration dndConfig = new UITreeDragDropConfiguration();
      dndConfig.addDropTarget(RemoteServerFolderDto.class);
      tree.enableDragDrop(remoteserverTreeHandler, dndConfig);
      tree.enableClipboardProvider();

      /* set selections */
      tree.setSelectionProvider(mainPanel);

      /* double click */
      tree.enableDoubleClickAction();

      /* history location */
      tree.setHistoryLocation(RemoteServerUIModule.REMOTE_SERVER_FAV_HISTORY_TOKEN);

      /* tree menu */
      tree.configureMenuProvider(RemoteServerUIModule.ADMIN_TREE_MENU_NAME);

      return tree;
   }
}
