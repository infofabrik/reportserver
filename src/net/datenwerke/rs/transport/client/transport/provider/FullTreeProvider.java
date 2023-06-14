package net.datenwerke.rs.transport.client.transport.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTree;
import net.datenwerke.gf.client.treedb.dnd.UITreeDragDropConfiguration;
import net.datenwerke.rs.transport.client.transport.TransportTreeManagerDao;
import net.datenwerke.rs.transport.client.transport.TransportUIModule;
import net.datenwerke.rs.transport.client.transport.dto.TransportFolderDto;
import net.datenwerke.rs.transport.client.transport.ui.TransportManagerMainPanel;

public class FullTreeProvider implements Provider<ManagerHelperTree> {

   private final BasicTreeProvider basicTreeProvider;
   private final TransportTreeManagerDao treeHandler;
   private final TransportManagerMainPanel mainPanel;

   @Inject
   public FullTreeProvider(
         BasicTreeProvider basicTreeProvider, 
         TransportTreeManagerDao treeHandler,
         TransportManagerMainPanel mainPanel
         ) {

      this.basicTreeProvider = basicTreeProvider;
      this.treeHandler = treeHandler;
      this.mainPanel = mainPanel;
   }

   public ManagerHelperTree get() {
      /* build tree */
      final ManagerHelperTree tree = basicTreeProvider.get();
      tree.getStore().enableDtoAwareness(true);

      /* dnd */
      UITreeDragDropConfiguration dndConfig = new UITreeDragDropConfiguration();
      dndConfig.addDropTarget(TransportFolderDto.class);
      tree.enableDragDrop(treeHandler, dndConfig);
      tree.enableClipboardProvider();

      /* set selections */
      tree.setSelectionProvider(mainPanel);

      /* double click */
      tree.enableDoubleClickAction();

      /* history location */
      tree.setHistoryLocation(TransportUIModule.TRANSPORT_FAV_HISTORY_TOKEN);

      /* tree menu */
      tree.configureMenuProvider(TransportUIModule.ADMIN_TREE_MENU_NAME);

      return tree;
   }
}
