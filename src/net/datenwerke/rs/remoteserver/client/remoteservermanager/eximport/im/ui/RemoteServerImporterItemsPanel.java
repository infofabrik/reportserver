package net.datenwerke.rs.remoteserver.client.remoteservermanager.eximport.im.ui;

import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.rs.remotersrestserver.client.remotersrestserver.dto.RemoteRsRestServerDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.eximport.im.RemoteServerManagerImportDao;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.eximport.im.dto.RemoteServerManagerImportConfigDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;
import net.datenwerke.treedb.ext.client.eximport.im.ui.ImporterItemsPanel;

public class RemoteServerImporterItemsPanel extends ImporterItemsPanel<RemoteServerManagerImportConfigDto> {

   private final RemoteServerManagerImportDao importDao;

   @Inject
   public RemoteServerImporterItemsPanel(RemoteServerManagerImportDao importDao) {
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
         if (RemoteRsRestServerDto.class.getName().equals(model.getType()))
            return BaseIcon.LAPTOP.toImageResource();
         return BaseIcon.FOLDER_O.toImageResource();
      });
   }

}