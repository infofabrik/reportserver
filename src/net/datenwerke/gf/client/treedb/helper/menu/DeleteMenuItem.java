package net.datenwerke.gf.client.treedb.helper.menu;

import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.DialogHideHandler;
import com.sencha.gxt.widget.core.client.info.Info;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwConfirmMessageBox;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.dto.DeleteDto;
import net.datenwerke.security.client.treedb.dto.decorator.SecuredAbstractNodeDtoDec;
import net.datenwerke.treedb.client.treedb.TreeDbManagerDao;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;
import net.datenwerke.treedb.client.treedb.locale.TreedbMessages;

public class DeleteMenuItem extends TreeMenuItem {

   public DeleteMenuItem(final TreeDbManagerDao treeManager) {
      super();

      setIcon(BaseIcon.DELETE);
      setText(BaseMessages.INSTANCE.remove());
      addMenuSelectionListener(new TreeMenuSelectionEvent() {

         public void menuItemSelected(final UITree tree, final AbstractNodeDto node) {
            /* confirm delete */
            ConfirmMessageBox cmb = new DwConfirmMessageBox(BaseMessages.INSTANCE.confirmDeleteTitle(),
                  BaseMessages.INSTANCE.confirmDeleteMsg(node.toDisplayTitle()));

            cmb.addDialogHideHandler(new DialogHideHandler() {

               @Override
               public void onDialogHide(DialogHideEvent event) {
                  if (event.getHideButton() == PredefinedButton.YES) {
                     /* delete */
                     treeManager.deleteNodeAndAskForMoreForce(node, new RsAsyncCallback<Boolean>() {
                        @Override
                        public void onSuccess(Boolean result) {
                           if (Boolean.TRUE.equals(result)) {
                              tree.getStore().remove(node);
                              Info.display(BaseMessages.INSTANCE.changesApplied(), TreedbMessages.INSTANCE.deleted());
                           }
                        }
                     });

                     /* select parent */
                     final AbstractNodeDto parent = tree.getStore().getParent(node);
                     if (null != parent) {
                        /*
                         * https://www.sencha.com/forum/showthread.php?308983-GXT-3.1.4.-
                         * TreeSelectionModel-RightClick-sets-mousdown-flag&p=1128504#post1128504
                         */
                        tree.releaseMouseDownFlag();
                        tree.getSelectionModel().select(parent, false);
                     }
                  }
               }
            });

            cmb.show();
         }
      });
   }

   @Override
   public void toBeDisplayed(AbstractNodeDto selectedItem) {
      disable();

      if (!(selectedItem instanceof SecuredAbstractNodeDtoDec)
            || !((SecuredAbstractNodeDtoDec) selectedItem).isAccessRightsLoaded()
            || ((SecuredAbstractNodeDtoDec) selectedItem).hasAccessRight(DeleteDto.class))
         enable();
   }
}
