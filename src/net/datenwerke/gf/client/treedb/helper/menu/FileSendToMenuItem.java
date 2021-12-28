package net.datenwerke.gf.client.treedb.helper.menu;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.security.client.security.dto.ReadDto;
import net.datenwerke.security.client.treedb.dto.decorator.SecuredAbstractNodeDtoDec;
import net.datenwerke.treedb.client.treedb.TreeDbManagerDao;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class FileSendToMenuItem extends TreeMenuItem {

   private AvailabilityCallback availableCallback = AvailabilityCallback.TRUE_INSTANCE;

   public FileSendToMenuItem(String text, final TreeDbManagerDao treeManager, ImageResource icon) {
      super();

      if (null != icon)
         setIcon(icon);

      setText(text);
   }

   @Override
   public void toBeDisplayed(AbstractNodeDto selectedItem) {
      disable();

      if (!availableCallback.isAvailable())
         return;

      if (!(selectedItem instanceof SecuredAbstractNodeDtoDec)
            || !((SecuredAbstractNodeDtoDec) selectedItem).isAccessRightsLoaded()
            || (((SecuredAbstractNodeDtoDec) selectedItem).hasAccessRight(ReadDto.class))) {
         enable();
      }
   }

   public void setAvailableCallback(AvailabilityCallback availableCallback) {
      this.availableCallback = availableCallback;
   }
}
