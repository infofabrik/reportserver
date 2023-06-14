package net.datenwerke.rs.transport.client.transport.eximport.im.ui;

import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.transport.client.transport.dto.decorator.TransportDtoDec;
import net.datenwerke.rs.transport.client.transport.eximport.im.TransportManagerImportDao;
import net.datenwerke.rs.transport.client.transport.eximport.im.dto.TransportManagerImportConfigDto;
import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;
import net.datenwerke.treedb.ext.client.eximport.im.ui.ImporterItemsPanel;

public class TransportImporterItemsPanel extends ImporterItemsPanel<TransportManagerImportConfigDto> {

   private final TransportManagerImportDao importDao;

   @Inject
   public TransportImporterItemsPanel(TransportManagerImportDao importDao) {
      super();

      /* store objects */
      this.importDao = importDao;

      /* load data */
      loadData();
   }

   private void loadData() {
      importDao.loadTree(new RsAsyncCallback<List<ImportTreeModel>>() {
         @Override
         public void onSuccess(List<ImportTreeModel> roots) {
            buildTree(roots);
         }
      });
   }

   protected void configureTree() {
      super.configureTree();
      tree.setIconProvider(model -> {
         if (TransportDtoDec.class.getName().equals(model.getType()))
            return BaseIcon.ARCHIVE.toImageResource();
         return BaseIcon.FOLDER_O.toImageResource();
      });
   }

}