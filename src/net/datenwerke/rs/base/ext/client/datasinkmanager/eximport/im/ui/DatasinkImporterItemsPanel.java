package net.datenwerke.rs.base.ext.client.datasinkmanager.eximport.im.ui;

import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.rs.base.ext.client.datasinkmanager.eximport.im.DatasinkManagerImportDao;
import net.datenwerke.rs.base.ext.client.datasinkmanager.eximport.im.dto.DatasinkManagerImportConfigDto;
import net.datenwerke.rs.dropbox.client.dropbox.dto.DropboxDatasinkDto;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.dto.EmailDatasinkDto;
import net.datenwerke.rs.ftp.client.ftp.dto.FtpDatasinkDto;
import net.datenwerke.rs.ftp.client.ftp.dto.FtpsDatasinkDto;
import net.datenwerke.rs.ftp.client.ftp.dto.SftpDatasinkDto;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.dto.LocalFileSystemDatasinkDto;
import net.datenwerke.rs.samba.client.samba.dto.SambaDatasinkDto;
import net.datenwerke.rs.scp.client.scp.dto.ScpDatasinkDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;
import net.datenwerke.treedb.ext.client.eximport.im.ui.ImporterItemsPanel;

public class DatasinkImporterItemsPanel extends ImporterItemsPanel<DatasinkManagerImportConfigDto> {

   private final DatasinkManagerImportDao dsImportDao;

   @Inject
   public DatasinkImporterItemsPanel(DatasinkManagerImportDao dsImportDao) {
      super();

      /* store objects */
      this.dsImportDao = dsImportDao;

      /* load data */
      loadData();
   }

   private void loadData() {
      dsImportDao.loadTree(new RsAsyncCallback<List<ImportTreeModel>>() {
         @Override
         public void onSuccess(List<ImportTreeModel> roots) {
            buildTree(roots);
         }
      });
   }

   protected void configureTree() {
      super.configureTree();
      tree.setIconProvider(model -> {
         if (FtpDatasinkDto.class.getName().equals(model.getType()))
            return BaseIcon.UPLOAD.toImageResource();
         if (SftpDatasinkDto.class.getName().equals(model.getType()))
            return BaseIcon.ARROW_CIRCLE_UP.toImageResource();
         if (FtpsDatasinkDto.class.getName().equals(model.getType()))
            return BaseIcon.ARROW_CIRCLE_O_UP.toImageResource();
         if (LocalFileSystemDatasinkDto.class.getName().equals(model.getType()))
            return BaseIcon.SERVER.toImageResource();
         if (SambaDatasinkDto.class.getName().equals(model.getType()))
            return BaseIcon.ANGLE_DOUBLE_UP.toImageResource();
         if (ScpDatasinkDto.class.getName().equals(model.getType()))
            return BaseIcon.ARROW_UP.toImageResource();
         if (EmailDatasinkDto.class.getName().equals(model.getType()))
            return BaseIcon.SEND.toImageResource();
         if (DropboxDatasinkDto.class.getName().equals(model.getType()))
            return BaseIcon.DROPBOX.toImageResource();
         return BaseIcon.FOLDER_O.toImageResource();
      });
   }

}