package net.datenwerke.gf.client.treedb.helper.menu;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.uiutils.ClientDownloadHelper;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.dto.ReadDto;
import net.datenwerke.security.client.treedb.dto.decorator.SecuredAbstractNodeDtoDec;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class DownloadMenuItem extends TreeMenuItem {

   public static interface DownloadMenuUrlGenerator {

      public String getUrl(AbstractNodeDto node);

   }

   public DownloadMenuItem(final DownloadMenuUrlGenerator downloadHelper) {
      super();

      setIcon(BaseIcon.DOWNLOAD);
      setText(BaseMessages.INSTANCE.download());
      addMenuSelectionListener(new TreeMenuSelectionEvent() {

         public void menuItemSelected(final UITree tree, final AbstractNodeDto node) {
            if (null == node)
               return;
            String url = downloadHelper.getUrl(node);
            ClientDownloadHelper.triggerDownload(url);
         }
      });
   }

   @Override
   public void toBeDisplayed(AbstractNodeDto selectedItem) {
      disable();

      if (!(selectedItem instanceof SecuredAbstractNodeDtoDec)
            || !((SecuredAbstractNodeDtoDec) selectedItem).isAccessRightsLoaded()
            || ((SecuredAbstractNodeDtoDec) selectedItem).hasAccessRight(ReadDto.class))
         enable();
   }
}
