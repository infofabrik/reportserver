package net.datenwerke.rs.localfsdatasink.client.localfsdatasink.hookers;

import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.helper.menu.FileSendToMenuItem;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.servercommunication.callback.NotamCallback;
import net.datenwerke.rs.core.client.datasinkmanager.DatasinkUIModule;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.datasinkmanager.locale.DatasinksMessages;
import net.datenwerke.rs.enterprise.client.EnterpriseUiService;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerTreeManagerDao;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerUiService;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.provider.treehooks.FileExportExternalEntryProviderHook;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.LocalFileSystemDao;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.provider.annotations.DatasinkTreeLocalFileSystem;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.locale.ScheduleAsFileMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class FileExportToLocalFileSystemHooker implements FileExportExternalEntryProviderHook {

   private final Provider<UITree> treeProvider;
   private final Provider<LocalFileSystemDao> datasinkDaoProvider;

   private final Provider<EnterpriseUiService> enterpriseServiceProvider;
   private final Provider<FileServerUiService> fileServerUiServiceProvider;

   @Inject
   public FileExportToLocalFileSystemHooker(
         @DatasinkTreeLocalFileSystem Provider<UITree> treeProvider,
         Provider<LocalFileSystemDao> datasinkDaoProvider,
         Provider<EnterpriseUiService> enterpriseServiceProvider,
         Provider<FileServerUiService> fileServerUiServiceProvider
         ) {
      this.treeProvider = treeProvider;
      this.datasinkDaoProvider = datasinkDaoProvider;
      this.enterpriseServiceProvider = enterpriseServiceProvider;
      this.fileServerUiServiceProvider = fileServerUiServiceProvider;
   }

   @Override
   public void createMenuEntry(final Menu menu, final FileServerTreeManagerDao treeHandler) {
      if (enterpriseServiceProvider.get().isEnterprise()) {
         datasinkDaoProvider.get().getStorageEnabledConfigs(new AsyncCallback<Map<StorageType, Boolean>>() {
  
            @Override
            public void onSuccess(Map<StorageType, Boolean> result) {
               // item is only added if enabled in configuration
               if (result.get(StorageType.LOCALFILESYSTEM)) {
                  FileSendToMenuItem item = new FileSendToMenuItem(DatasinksMessages.INSTANCE.localFileSystem(), treeHandler,
                        BaseIcon.SERVER.toImageResource());
                  item.addMenuSelectionListener((tree, node) -> displayExportDialog((FileServerFileDto)node));
                  menu.add(item);
                  item.setAvailableCallback(() -> isAvailable());
               }
            }
  
            @Override
            public void onFailure(Throwable caught) {
            }
         });
      } else {
         // we add item but disable it
         MenuItem item = new DwMenuItem(DatasinksMessages.INSTANCE.localFileSystem(), BaseIcon.SERVER);
         menu.add(item);
         item.disable();
      }
   }

   protected void displayExportDialog(final FileServerFileDto toExport) {
      fileServerUiServiceProvider.get().displayFileSendToDatasinkDialog(
            BaseIcon.SERVER, DatasinksMessages.INSTANCE.localFileSystem(), toExport.getName(), treeProvider, datasinkDaoProvider, 
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
