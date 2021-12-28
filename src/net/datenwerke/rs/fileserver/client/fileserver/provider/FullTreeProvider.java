package net.datenwerke.rs.fileserver.client.fileserver.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTree;
import net.datenwerke.gf.client.treedb.dnd.UITreeDragDropConfiguration;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerTreeManagerDao;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerUiModule;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto;
import net.datenwerke.rs.fileserver.client.fileserver.ui.FileServerManagerMainPanel;

public class FullTreeProvider implements Provider<ManagerHelperTree> {

   private final BasicTreeProvider basicTreeProvider;
   private final FileServerTreeManagerDao treeHandler;
   private final FileServerManagerMainPanel mainPanel;

   @Inject
   public FullTreeProvider(BasicTreeProvider basicTreeProvider, FileServerTreeManagerDao datasourceTreeHandler,
         FileServerManagerMainPanel mainPanel) {

      this.basicTreeProvider = basicTreeProvider;
      this.treeHandler = datasourceTreeHandler;
      this.mainPanel = mainPanel;
   }

   public ManagerHelperTree get() {
      /* build tree */
      final ManagerHelperTree tree = basicTreeProvider.get();
      tree.getStore().enableDtoAwareness(true);

      /* dnd */
      UITreeDragDropConfiguration dndConfig = new UITreeDragDropConfiguration();
      dndConfig.addDropTarget(FileServerFolderDto.class);
      tree.enableDragDrop(treeHandler, dndConfig);
      tree.enableClipboardProvider();

      /* set selections */
      tree.setSelectionProvider(mainPanel);

      /* double click */
      tree.enableDoubleClickAction();

      /* history location */
      tree.setHistoryLocation(FileServerUiModule.FILESERVER_HISTORY_TOKEN);

      /* tree menu */
      tree.configureMenuProvider(FileServerUiModule.ADMIN_TREE_MENU_NAME);

      return tree;
   }
}
