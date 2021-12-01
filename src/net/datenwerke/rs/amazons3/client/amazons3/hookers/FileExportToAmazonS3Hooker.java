package net.datenwerke.rs.amazons3.client.amazons3.hookers;

import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.menu.Menu;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.helper.menu.FileSendToMenuItem;
import net.datenwerke.gxtdto.client.servercommunication.callback.NotamCallback;
import net.datenwerke.rs.amazons3.client.amazons3.AmazonS3Dao;
import net.datenwerke.rs.amazons3.client.amazons3.AmazonS3UiService;
import net.datenwerke.rs.amazons3.client.amazons3.provider.annotations.DatasinkTreeAmazonS3;
import net.datenwerke.rs.core.client.datasinkmanager.DatasinkUIModule;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.enterprise.client.EnterpriseUiService;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerTreeManagerDao;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerUiService;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.provider.treehooks.FileExportExternalEntryProviderHook;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.locale.ScheduleAsFileMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class FileExportToAmazonS3Hooker implements FileExportExternalEntryProviderHook {

   private final Provider<UITree> treeProvider;
   private final Provider<AmazonS3Dao> datasinkDaoProvider;

   private final Provider<EnterpriseUiService> enterpriseServiceProvider;
   private final Provider<FileServerUiService> fileServerUiServiceProvider;
   private final Provider<AmazonS3UiService> amazonS3UiService;

   @Inject
   public FileExportToAmazonS3Hooker(
         @DatasinkTreeAmazonS3 Provider<UITree> treeProvider,
         Provider<AmazonS3Dao> datasinkDaoProvider,
         Provider<EnterpriseUiService> enterpriseServiceProvider,
         Provider<FileServerUiService> fileServerUiServiceProvider,
         Provider<AmazonS3UiService> amazonS3UiService
         ) {
      this.treeProvider = treeProvider;
      this.datasinkDaoProvider = datasinkDaoProvider;
      this.enterpriseServiceProvider = enterpriseServiceProvider;
      this.fileServerUiServiceProvider = fileServerUiServiceProvider;
      this.amazonS3UiService = amazonS3UiService;
   }

   @Override
   public void createMenuEntry(final Menu menu, final FileServerTreeManagerDao treeHandler) {
      final FileSendToMenuItem item = new FileSendToMenuItem("Amazon S3", treeHandler, BaseIcon.AMAZON.toImageResource());
      item.addMenuSelectionListener((tree, node) -> displayExportDialog((FileServerFileDto)node));
      menu.add(item);
      item.setAvailableCallback(() -> isAvailable());
      item.disable();
   }

   protected void displayExportDialog(final FileServerFileDto toExport) {
      fileServerUiServiceProvider.get().displayFileSendToDatasinkDialog(
            BaseIcon.AMAZON, "Amazon S3", toExport.getName(), treeProvider, datasinkDaoProvider, 
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
            && amazonS3UiService.get().getStorageEnabledConfigs().containsKey(StorageType.AMAZONS3);
   }

}
