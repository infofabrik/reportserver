package net.datenwerke.rs.localfsdatasink.client.localfsdatasink.hookers;

import java.util.Map;
import java.util.Optional;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.menu.Menu;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gxtdto.client.servercommunication.callback.NotamCallback;
import net.datenwerke.rs.core.client.datasinkmanager.DatasinkUIModule;
import net.datenwerke.rs.core.client.datasinkmanager.DatasinkUIService;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.datasinkmanager.helper.menu.FileSendToMenuItem;
import net.datenwerke.rs.enterprise.client.EnterpriseUiService;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerTreeManagerDao;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto;
import net.datenwerke.rs.fileserver.client.fileserver.provider.treehooks.FileExportExternalEntryProviderHook;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.LocalFileSystemDao;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.LocalFileSystemUiModule;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.LocalFileSystemUiService;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.dto.LocalFileSystemDatasinkDto;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.provider.annotations.DatasinkTreeLocalFileSystem;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.locale.ScheduleAsFileMessages;

public class FileExportToLocalFileSystemHooker implements FileExportExternalEntryProviderHook {

   private final Provider<UITree> treeProvider;
   private final Provider<LocalFileSystemDao> datasinkDaoProvider;

   private final Provider<EnterpriseUiService> enterpriseServiceProvider;
   private final Provider<DatasinkUIService> datasinkUiServiceProvider;
   private final Provider<LocalFileSystemUiService> localFileSystemUiService;

   @Inject
   public FileExportToLocalFileSystemHooker(
         @DatasinkTreeLocalFileSystem Provider<UITree> treeProvider,
         Provider<LocalFileSystemDao> datasinkDaoProvider, 
         Provider<EnterpriseUiService> enterpriseServiceProvider,
         Provider<DatasinkUIService> datasinkUiServiceProvider,
         Provider<LocalFileSystemUiService> localFileSystemUiService
         ) {
      this.treeProvider = treeProvider;
      this.datasinkDaoProvider = datasinkDaoProvider;
      this.enterpriseServiceProvider = enterpriseServiceProvider;
      this.datasinkUiServiceProvider = datasinkUiServiceProvider;
      this.localFileSystemUiService = localFileSystemUiService;
   }

   @Override
   public void createMenuEntry(final Menu menu, final FileServerTreeManagerDao treeHandler) {
      FileSendToMenuItem item = new FileSendToMenuItem(LocalFileSystemUiModule.NAME, treeHandler,
            LocalFileSystemUiModule.ICON.toImageResource());
      item.addMenuSelectionListener((tree, node) -> displayExportDialog((AbstractFileServerNodeDto) node));
      menu.add(item);
      item.setAvailableCallback(() -> isAvailable());
      item.disable();
   }

   protected void displayExportDialog(final AbstractFileServerNodeDto toExport) {
      String name = "";
      if (toExport instanceof FileServerFolderDto)
         name = ((FileServerFolderDto) toExport).getName();
      else if (toExport instanceof FileServerFileDto)
         name = ((FileServerFileDto) toExport).getName();
      datasinkUiServiceProvider.get().displaySendToDatasinkDialog(LocalFileSystemDatasinkDto.class, name, treeProvider,
            datasinkDaoProvider, toExport, Optional.empty(), new AsyncCallback<Map<String, Object>>() {

               @Override
               public void onSuccess(Map<String, Object> result) {
                  datasinkDaoProvider.get().exportFileIntoDatasink(toExport,
                        (DatasinkDefinitionDto) result.get(DatasinkUIModule.DATASINK_KEY),
                        (String) result.get(DatasinkUIModule.DATASINK_FILENAME),
                        (String) result.get(DatasinkUIModule.DATASINK_FOLDER),
                        (Boolean) result.get(DatasinkUIModule.DATASINK_COMPRESSED_KEY),
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
            && localFileSystemUiService.get().getStorageEnabledConfigs().containsKey(StorageType.LOCALFILESYSTEM);
   }

}
