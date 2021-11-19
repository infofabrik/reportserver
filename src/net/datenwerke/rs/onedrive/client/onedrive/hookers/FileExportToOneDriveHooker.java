package net.datenwerke.rs.onedrive.client.onedrive.hookers;

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
import net.datenwerke.rs.onedrive.client.onedrive.OneDriveDao;
import net.datenwerke.rs.onedrive.client.onedrive.OneDriveUiModule;
import net.datenwerke.rs.onedrive.client.onedrive.provider.annotations.DatasinkTreeOneDrive;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.locale.ScheduleAsFileMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class FileExportToOneDriveHooker implements FileExportExternalEntryProviderHook {

   private final Provider<UITree> treeProvider;
   private final Provider<OneDriveDao> datasinkDaoProvider;

   private final Provider<EnterpriseUiService> enterpriseServiceProvider;
   private final Provider<FileServerUiService> fileServerUiServiceProvider;

   @Inject
   public FileExportToOneDriveHooker(
         @DatasinkTreeOneDrive Provider<UITree> treeProvider,
         Provider<OneDriveDao> datasinkDaoProvider,
         Provider<EnterpriseUiService> enterpriseServiceProvider,
         Provider<FileServerUiService> fileServerUiServiceProvider
         ) {
      this.treeProvider = treeProvider;
      this.datasinkDaoProvider = datasinkDaoProvider;
      this.enterpriseServiceProvider = enterpriseServiceProvider;
      this.fileServerUiServiceProvider = fileServerUiServiceProvider;
   }

   @Override
   public FileSendToMenuItem getMenuEntry(Menu menu, FileServerTreeManagerDao treeHandler) {
      FileSendToMenuItem item = new FileSendToMenuItem(OneDriveUiModule.ONE_DRIVE_NAME, 
            treeHandler, BaseIcon.CLOUD_UPLOAD.toImageResource());
      item.addMenuSelectionListener((tree, node) -> displayExportDialog((FileServerFileDto)node));
      return item;

   }

   protected void displayExportDialog(final FileServerFileDto toExport) {
      fileServerUiServiceProvider.get().displayFileSendToDatasinkDialog(
            BaseIcon.CLOUD_UPLOAD, OneDriveUiModule.ONE_DRIVE_NAME, toExport.getName(), treeProvider, datasinkDaoProvider, 
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
      return enterpriseServiceProvider.get().isEnterprise();
   }

}
