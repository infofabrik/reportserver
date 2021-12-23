package net.datenwerke.rs.emaildatasink.client.emaildatasink.hookers;

import java.util.List;
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
import net.datenwerke.rs.emaildatasink.client.emaildatasink.EmailDatasinkDao;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.EmailDatasinkUiModule;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.EmailDatasinkUiService;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.dto.EmailDatasinkDto;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.provider.annotations.DatasinkTreeEmail;
import net.datenwerke.rs.enterprise.client.EnterpriseUiService;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerTreeManagerDao;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerUiService;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto;
import net.datenwerke.rs.fileserver.client.fileserver.provider.treehooks.FileExportExternalEntryProviderHook;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.locale.ScheduleAsFileMessages;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownUser;

public class FileExportToEmailHooker implements FileExportExternalEntryProviderHook{
   
   private final Provider<EnterpriseUiService> enterpriseServiceProvider;
   private final Provider<EmailDatasinkUiService> emailUiService;
   private final Provider<FileServerUiService> fileServerUiServiceProvider;
   private final Provider<UITree> treeProvider;
   private final Provider<EmailDatasinkDao> datasinkDaoProvider;
   
   @Inject
   public FileExportToEmailHooker(Provider<EnterpriseUiService> enterpriseServiceProvider,
         Provider<EmailDatasinkUiService> emailUiService, Provider<FileServerUiService> fileServerUiServiceProvider,
         @DatasinkTreeEmail Provider<UITree> treeProvider, Provider<EmailDatasinkDao> datasinkDaoProvider) {
      this.enterpriseServiceProvider = enterpriseServiceProvider;
      this.emailUiService = emailUiService;
      this.fileServerUiServiceProvider = fileServerUiServiceProvider;
      this.treeProvider = treeProvider;
      this.datasinkDaoProvider = datasinkDaoProvider;
   }

   @Override
   public void createMenuEntry(Menu menu, FileServerTreeManagerDao treeHandler) {
      final FileSendToMenuItem item = new FileSendToMenuItem("Email - SMTP", treeHandler, EmailDatasinkUiModule.ICON.toImageResource());
      item.addMenuSelectionListener((tree, node) ->  displayExportDialog((AbstractFileServerNodeDto)node));
      menu.add(item);
      item.setAvailableCallback(() -> isAvailable());
      item.disable();
      
   }
   protected void displayExportDialog(final AbstractFileServerNodeDto toExport) {
      String name=null;
      if(toExport instanceof FileServerFolderDto)
         name = ((FileServerFolderDto)toExport).getName();
      else if(toExport instanceof FileServerFileDto)
         name = ((FileServerFileDto)toExport).getName();
      
      if (null == name)
         throw new IllegalArgumentException("Name is null");
      
      fileServerUiServiceProvider.get().displayFileSendToDatasinkDialog(
            EmailDatasinkDto.class,
            name, treeProvider, datasinkDaoProvider, toExport, 
            new AsyncCallback<Map<String,Object>>() {
               
               @Override
               public void onSuccess(Map<String,Object> result) {
                  datasinkDaoProvider.get().exportFileIntoDatasink(toExport,
                        (DatasinkDefinitionDto) result.get(DatasinkUIModule.DATASINK_KEY),
                        (String) result.get(DatasinkUIModule.DATASINK_FILENAME), 
                        (String) result.get(DatasinkUIModule.DATASINK_FOLDER), 
                        (Boolean) result.get(DatasinkUIModule.DATASINK_COMPRESSED_KEY),
                        (String) result.get(EmailDatasinkUiModule.DATASINK_SUBJECT),
                        (String) result.get(EmailDatasinkUiModule.DATASINK_MESSSAGE),
                        (List<StrippedDownUser>) result.get(EmailDatasinkUiModule.DATASINK_RCPTLIST),
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
            && emailUiService.get().getStorageEnabledConfigs().containsKey(StorageType.EMAIL);
   }

}