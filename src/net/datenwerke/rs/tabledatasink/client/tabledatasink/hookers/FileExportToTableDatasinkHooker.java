package net.datenwerke.rs.tabledatasink.client.tabledatasink.hookers;

import java.util.Map;
import java.util.Optional;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.menu.Menu;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.helper.menu.FileSendToMenuItem;
import net.datenwerke.gxtdto.client.servercommunication.callback.NotamCallback;
import net.datenwerke.rs.core.client.datasinkmanager.DatasinkUIModule;
import net.datenwerke.rs.core.client.datasinkmanager.DatasinkUIService;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.enterprise.client.EnterpriseUiService;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerTreeManagerDao;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto;
import net.datenwerke.rs.fileserver.client.fileserver.provider.treehooks.FileExportExternalEntryProviderHook;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.locale.ScheduleAsFileMessages;
import net.datenwerke.rs.tabledatasink.client.tabledatasink.TableDatasinkDao;
import net.datenwerke.rs.tabledatasink.client.tabledatasink.TableDatasinkUiModule;
import net.datenwerke.rs.tabledatasink.client.tabledatasink.TableDatasinkUiService;
import net.datenwerke.rs.tabledatasink.client.tabledatasink.dto.TableDatasinkDto;
import net.datenwerke.rs.tabledatasink.client.tabledatasink.provider.annotations.DatasinkTreeTableDatasink;

public class FileExportToTableDatasinkHooker implements FileExportExternalEntryProviderHook {

   private final Provider<UITree> treeProvider;
   private final Provider<TableDatasinkDao> datasinkDaoProvider;

   private final Provider<EnterpriseUiService> enterpriseServiceProvider;
   private final Provider<DatasinkUIService> datasinkUiServiceProvider;
   private final Provider<TableDatasinkUiService> tableDatasinkUiService;

   @Inject
   public FileExportToTableDatasinkHooker(
         @DatasinkTreeTableDatasink Provider<UITree> treeProvider,
         Provider<TableDatasinkDao> datasinkDaoProvider, 
         Provider<EnterpriseUiService> enterpriseServiceProvider,
         Provider<DatasinkUIService> datasinkUiServiceProvider,
         Provider<TableDatasinkUiService> tableDatasinkUiService
         ) {
      this.treeProvider = treeProvider;
      this.datasinkDaoProvider = datasinkDaoProvider;
      this.enterpriseServiceProvider = enterpriseServiceProvider;
      this.datasinkUiServiceProvider = datasinkUiServiceProvider;
      this.tableDatasinkUiService = tableDatasinkUiService;
   }

   @Override
   public void createMenuEntry(Menu menu, FileServerTreeManagerDao treeHandler) {
      final FileSendToMenuItem item = new FileSendToMenuItem(TableDatasinkUiModule.NAME, treeHandler,
            TableDatasinkUiModule.ICON.toImageResource());
      item.addMenuSelectionListener((tree, node) -> displayExportDialog((AbstractFileServerNodeDto) node));
      menu.add(item);
      item.setAvailableCallback(() -> isAvailable());
      item.disable();

   }

   protected void displayExportDialog(final AbstractFileServerNodeDto toExport) {
      if (!(toExport instanceof AbstractFileServerNodeDto))
         throw new IllegalArgumentException(toExport.getClass() + " not supported");

      String name = "";
      if (toExport instanceof FileServerFolderDto)
         name = ((FileServerFolderDto) toExport).getName();
      else if (toExport instanceof FileServerFileDto)
         name = ((FileServerFileDto) toExport).getName();

      datasinkUiServiceProvider.get().displaySendToDatasinkDialog(TableDatasinkDto.class, name, treeProvider,
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
            && tableDatasinkUiService.get().getStorageEnabledConfigs().containsKey(StorageType.TABLE_DATASINK);
   }

}
