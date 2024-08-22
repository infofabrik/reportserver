package net.datenwerke.gf.client.treedb.helper.menu;

import java.util.List;

import com.google.inject.Provider;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.selection.TreeSelectionPopup;
import net.datenwerke.gxtdto.client.servercommunication.callback.NotamCallback;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.dto.WriteDto;
import net.datenwerke.security.client.treedb.dto.decorator.SecuredAbstractNodeDtoDec;
import net.datenwerke.treedb.client.treedb.TreeDbManagerDao;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;
import net.datenwerke.treedb.client.treedb.locale.TreedbMessages;

public class MoveToFolderMenuItem extends TreeMenuItem {

   public MoveToFolderMenuItem(final TreeDbManagerDao treeManager, final Provider<UITree> managerTreeProvider) {
      super();
      setIcon(BaseIcon.FOLDER_OPEN_O);
      setText(TreedbMessages.INSTANCE.moveTo());
      addMenuSelectionListener((tree, node) -> moveToAnotherFolder(treeManager, managerTreeProvider, node));
   }

   @Override
   public void toBeDisplayed(AbstractNodeDto selectedItem) {
      disable();

      /* try to get parent */
      AbstractNodeDto parent = tree.getParentNode(selectedItem);
      if (null != parent && (!(parent instanceof SecuredAbstractNodeDtoDec)
            || !((SecuredAbstractNodeDtoDec) parent).isAccessRightsLoaded()
            || (((SecuredAbstractNodeDtoDec) parent).hasAccessRight(WriteDto.class)
                  && ((SecuredAbstractNodeDtoDec) parent).hasInheritedAccessRight(WriteDto.class)))) {
         enable();
      }
   }
   
   private void moveToAnotherFolder(TreeDbManagerDao treeManager, Provider<UITree> managerTreeProvider,
         AbstractNodeDto node) {
      TreeSelectionPopup popup = new TreeSelectionPopup(managerTreeProvider.get(), SecuredAbstractNodeDtoDec.class) {
         @Override
         protected void itemsSelected(List<AbstractNodeDto> selectedItems) {
            for (AbstractNodeDto targetFolder : selectedItems) {
                  treeManager.moveNodeAppend(node, targetFolder,
                        new NotamCallback<AbstractNodeDto>(TreedbMessages.INSTANCE.moved()));
            }
         }
      };
      
      popup.setHeading(TreedbMessages.INSTANCE.moveTo());
      popup.show();
   }
}
