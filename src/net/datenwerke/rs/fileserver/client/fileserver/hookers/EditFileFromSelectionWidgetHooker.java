package net.datenwerke.rs.fileserver.client.fileserver.hookers;

import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

import net.datenwerke.gf.client.treedb.selection.SingleTreeSelectionField;
import net.datenwerke.gf.client.treedb.selection.hooks.AddSelectionFieldMenuItemHook;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerUiService;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.decorator.FileServerFileDtoDec;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class EditFileFromSelectionWidgetHooker implements AddSelectionFieldMenuItemHook {

   private final FileServerUiService fileService;

   @Inject
   public EditFileFromSelectionWidgetHooker(FileServerUiService fileService) {
      this.fileService = fileService;
   }

   @Override
   public void addMenuEntries(SingleTreeSelectionField field, Menu menu, final MenuNodeProvider provider) {
      if (!field.isValidType(FileServerFileDtoDec.class))
         return;

      final MenuItem editItem = new DwMenuItem(BaseMessages.INSTANCE.edit(), BaseIcon.EDIT);
      menu.add(editItem);

      editItem.addSelectionHandler(event -> {
         AbstractNodeDto node = provider.getNode();
         if (node instanceof FileServerFileDto)
            fileService.editFileDirectly((FileServerFileDto) node, true, false, false, false);
      });

      editItem.disable();

      menu.addShowHandler(event -> {
         AbstractNodeDto node = provider.getNode();
         if (node instanceof FileServerFileDto)
            editItem.enable();
         else
            editItem.disable();
      });

   }

}
