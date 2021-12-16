package net.datenwerke.rs.dropbox.client.dropbox.hookers;

import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.menu.Menu;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.helper.menu.FileSendToMenuItem;
import net.datenwerke.gxtdto.client.servercommunication.callback.NotamCallback;
import net.datenwerke.rs.core.client.datasinkmanager.DatasinkUIModule;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.dropbox.client.dropbox.DropboxDao;
import net.datenwerke.rs.dropbox.client.dropbox.DropboxUiService;
import net.datenwerke.rs.dropbox.client.dropbox.provider.annotations.DatasinkTreeDropbox;
import net.datenwerke.rs.enterprise.client.EnterpriseUiService;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerTreeManagerDao;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerUiService;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto;
import net.datenwerke.rs.fileserver.client.fileserver.provider.treehooks.FileExportExternalEntryProviderHook;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.locale.ScheduleAsFileMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class FileExportToDropboxHooker implements FileExportExternalEntryProviderHook {

   private final Provider<UITree> treeProvider;
   private final Provider<DropboxDao> datasinkDaoProvider;

   private final Provider<EnterpriseUiService> enterpriseServiceProvider;
   private final Provider<FileServerUiService> fileServerUiServiceProvider;
   private Provider<DropboxUiService> dropboxUiService;

   @Inject
   public FileExportToDropboxHooker(
         @DatasinkTreeDropbox Provider<UITree> treeProvider,
         Provider<DropboxDao> datasinkDaoProvider,
         Provider<EnterpriseUiService> enterpriseServiceProvider,
         Provider<FileServerUiService> fileServerUiServiceProvider,
         Provider<DropboxUiService> dropboxUiService
         ) {
      this.treeProvider = treeProvider;
      this.datasinkDaoProvider = datasinkDaoProvider;
      this.enterpriseServiceProvider = enterpriseServiceProvider;
      this.fileServerUiServiceProvider = fileServerUiServiceProvider;
      this.dropboxUiService = dropboxUiService;
   }

   @Override
   public void createMenuEntry(final Menu menu, final FileServerTreeManagerDao treeHandler) {
      FileSendToMenuItem item = new FileSendToMenuItem("Dropbox", treeHandler, BaseIcon.DROPBOX.toImageResource());
      item.addMenuSelectionListener((tree, node) -> displayExportDialog((AbstractFileServerNodeDto)node));
      menu.add(item);
      item.setAvailableCallback(() -> isAvailable());
      item.disable();
   }

   protected void displayExportDialog(final AbstractFileServerNodeDto toExport) {
      String name="";
       if(toExport instanceof FileServerFolderDto)
         name = ((FileServerFolderDto)toExport).getName();
      else if(toExport instanceof FileServerFileDto)
        name = ((FileServerFileDto)toExport).getName();
      fileServerUiServiceProvider.get().displayFileSendToDatasinkDialog(
            BaseIcon.DROPBOX, "Dropbox", name, treeProvider, datasinkDaoProvider, toExport, 
            new AsyncCallback<Map<String,Object>>() {
               
               @Override
               public void onSuccess(Map<String,Object> result) {
                  datasinkDaoProvider.get().exportFileIntoDatasink(toExport,
                        (DatasinkDefinitionDto) result.get(DatasinkUIModule.DATASINK_KEY), 
                        (String) result.get(DatasinkUIModule.DATASINK_FILENAME), 
                        (String)result.get(DatasinkUIModule.DATASINK_FOLDER), 
                        (Boolean)result.get(DatasinkUIModule.DATASINK_COMPRESSED_KEY),
                        new NotamCallback<Void>(ScheduleAsFileMessages.INSTANCE.dataSent()));
               }
               @Override
               public void onFailure(Throwable caught) {
               }
            });
   }

   @Override
   public boolean isAvailable() {
      return enterpriseServiceProvider.get().isEnterprise()
            && dropboxUiService.get().getStorageEnabledConfigs().containsKey(StorageType.DROPBOX);
   }

}
