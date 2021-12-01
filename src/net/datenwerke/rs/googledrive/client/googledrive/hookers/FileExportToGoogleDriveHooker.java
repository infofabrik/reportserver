package net.datenwerke.rs.googledrive.client.googledrive.hookers;

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
import net.datenwerke.rs.enterprise.client.EnterpriseUiService;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerTreeManagerDao;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerUiService;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.provider.treehooks.FileExportExternalEntryProviderHook;
import net.datenwerke.rs.googledrive.client.googledrive.GoogleDriveDao;
import net.datenwerke.rs.googledrive.client.googledrive.GoogleDriveUiService;
import net.datenwerke.rs.googledrive.client.googledrive.provider.annotations.DatasinkTreeGoogleDrive;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.locale.ScheduleAsFileMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class FileExportToGoogleDriveHooker implements FileExportExternalEntryProviderHook {

   private final Provider<UITree> treeProvider;
   private final Provider<GoogleDriveDao> datasinkDaoProvider;

   private final Provider<EnterpriseUiService> enterpriseServiceProvider;
   private final Provider<FileServerUiService> fileServerUiServiceProvider;
   private final Provider<GoogleDriveUiService> googleDriveUiService;

   @Inject
   public FileExportToGoogleDriveHooker(
         @DatasinkTreeGoogleDrive Provider<UITree> treeProvider,
         Provider<GoogleDriveDao> datasinkDaoProvider,
         Provider<EnterpriseUiService> enterpriseServiceProvider,
         Provider<FileServerUiService> fileServerUiServiceProvider,
         Provider<GoogleDriveUiService> googleDriveUiService
         ) {
      this.treeProvider = treeProvider;
      this.datasinkDaoProvider = datasinkDaoProvider;
      this.enterpriseServiceProvider = enterpriseServiceProvider;
      this.fileServerUiServiceProvider = fileServerUiServiceProvider;
      this.googleDriveUiService = googleDriveUiService;
   }

   @Override
   public void createMenuEntry(final Menu menu, final FileServerTreeManagerDao treeHandler) {
      FileSendToMenuItem item = new FileSendToMenuItem("Google Drive", treeHandler, BaseIcon.GOOGLE.toImageResource());
      item.addMenuSelectionListener((tree, node) -> displayExportDialog((FileServerFileDto)node));
      menu.add(item);
      item.setAvailableCallback(() -> isAvailable());
      item.disable();
   }

   protected void displayExportDialog(final FileServerFileDto toExport) {
      fileServerUiServiceProvider.get().displayFileSendToDatasinkDialog(
            BaseIcon.GOOGLE, "Google Drive", toExport.getName(), treeProvider, datasinkDaoProvider, 
            new AsyncCallback<Map<String,Object>>() {
               
               @Override
               public void onSuccess(Map<String,Object> result) {
                  datasinkDaoProvider.get().exportFileIntoDatasink(toExport,
                        (DatasinkDefinitionDto) result.get(DatasinkUIModule.DATASINK_KEY), 
                        (String) result.get(DatasinkUIModule.DATASINK_FILENAME), 
                        (String)result.get(DatasinkUIModule.DATASINK_FOLDER), 
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
            && googleDriveUiService.get().getStorageEnabledConfigs().containsKey(StorageType.GOOGLEDRIVE);
   }

}
