package net.datenwerke.rs.scp.client.scp.hookers;

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
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto;
import net.datenwerke.rs.fileserver.client.fileserver.provider.treehooks.FileExportExternalEntryProviderHook;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.locale.ScheduleAsFileMessages;
import net.datenwerke.rs.scp.client.scp.ScpDao;
import net.datenwerke.rs.scp.client.scp.ScpUiService;
import net.datenwerke.rs.scp.client.scp.provider.annotations.DatasinkTreeScp;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class FileExportToScpHooker implements FileExportExternalEntryProviderHook {

   private final Provider<UITree> treeProvider;
   private final Provider<ScpDao> datasinkDaoProvider;

   private final Provider<EnterpriseUiService> enterpriseServiceProvider;
   private final Provider<FileServerUiService> fileServerUiServiceProvider;
   private final Provider<ScpUiService> scpUiService;

   @Inject
   public FileExportToScpHooker(
         @DatasinkTreeScp Provider<UITree> treeProvider,
         Provider<ScpDao> datasinkDaoProvider,
         Provider<EnterpriseUiService> enterpriseServiceProvider,
         Provider<FileServerUiService> fileServerUiServiceProvider,
         Provider<ScpUiService> scpUiService
         ) {
      this.treeProvider = treeProvider;
      this.datasinkDaoProvider = datasinkDaoProvider;
      this.enterpriseServiceProvider = enterpriseServiceProvider;
      this.fileServerUiServiceProvider = fileServerUiServiceProvider;
      this.scpUiService = scpUiService;
   }

   @Override
   public void createMenuEntry(final Menu menu, final FileServerTreeManagerDao treeHandler) {
      FileSendToMenuItem item = new FileSendToMenuItem("SCP", treeHandler, BaseIcon.ARROW_UP.toImageResource());
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
            BaseIcon.ARROW_UP, "SCP", name, treeProvider, datasinkDaoProvider, toExport, 
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
            && scpUiService.get().getStorageEnabledConfigs().containsKey(StorageType.SCP);
   }

}
