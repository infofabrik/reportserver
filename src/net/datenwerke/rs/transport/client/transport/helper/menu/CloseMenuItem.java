package net.datenwerke.rs.transport.client.transport.helper.menu;

import net.datenwerke.gf.client.managerhelper.locale.ManagerhelperMessages;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.helper.menu.TreeMenuItem;
import net.datenwerke.gf.client.treedb.helper.menu.TreeMenuSelectionEvent;
import net.datenwerke.gxtdto.client.servercommunication.callback.NotamCallback;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.transport.client.transport.dto.TransportDto;
import net.datenwerke.rs.transport.client.transport.locale.TransportMessages;
import net.datenwerke.security.client.security.dto.WriteDto;
import net.datenwerke.security.client.treedb.dto.decorator.SecuredAbstractNodeDtoDec;
import net.datenwerke.treedb.client.treedb.TreeDbManagerDao;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class CloseMenuItem extends TreeMenuItem {

   public CloseMenuItem(final TreeDbManagerDao treeManager) {
      super();

      setIcon(BaseIcon.CLOSE);
      setText(TransportMessages.INSTANCE.close());

      addMenuSelectionListener(new TreeMenuSelectionEvent() {
         public void menuItemSelected(final UITree tree, final AbstractNodeDto node) {

            if (node instanceof TransportDto) {
               ((TransportDto) node).setClosed(true);
            }

            treeManager.updateNode(node, new NotamCallback<AbstractNodeDto>(ManagerhelperMessages.INSTANCE.updated()) {
            });
         }
      });
   }

   @Override
   public void toBeDisplayed(AbstractNodeDto selectedItem) {
      disable();

      boolean isClosed = false;
      if (selectedItem instanceof TransportDto) {
         isClosed = ((TransportDto) selectedItem).isClosed();
      }

      /* try to get parent */
      AbstractNodeDto parent = tree.getParentNode(selectedItem);
      if (!isClosed && null != parent
            && (!(parent instanceof SecuredAbstractNodeDtoDec)
                  || !((SecuredAbstractNodeDtoDec) parent).isAccessRightsLoaded()
                  || (((SecuredAbstractNodeDtoDec) parent).hasAccessRight(WriteDto.class)
                        && ((SecuredAbstractNodeDtoDec) parent).hasInheritedAccessRight(WriteDto.class)))) {
         enable();
      }
   }
}
