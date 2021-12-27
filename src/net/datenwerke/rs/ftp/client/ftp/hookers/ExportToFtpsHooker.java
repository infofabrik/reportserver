package net.datenwerke.rs.ftp.client.ftp.hookers;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.servercommunication.callback.NotamCallback;
import net.datenwerke.rs.core.client.datasinkmanager.DatasinkUIModule;
import net.datenwerke.rs.core.client.datasinkmanager.DatasinkUIService;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.helper.simpleform.ExportTypeSelection;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorInformation;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorMainPanel;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewConfiguration;
import net.datenwerke.rs.core.client.reportexporter.hooks.ExportExternalEntryProviderHook;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.ftp.client.ftp.FtpUiModule;
import net.datenwerke.rs.ftp.client.ftp.FtpsDao;
import net.datenwerke.rs.ftp.client.ftp.dto.FtpsDatasinkDto;
import net.datenwerke.rs.ftp.client.ftp.provider.annotations.DatasinkTreeFtps;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.locale.ScheduleAsFileMessages;

public class ExportToFtpsHooker implements ExportExternalEntryProviderHook {

   private final Provider<UITree> treeProvider;
   private final Provider<FtpsDao> datasinkDaoProvider;
   
   private final Provider<DatasinkUIService> datasinkUiServiceProvider;

   @Inject
   public ExportToFtpsHooker(
         @DatasinkTreeFtps Provider<UITree> treeProvider,
         Provider<FtpsDao> datasinkDaoProvider,
         Provider<DatasinkUIService> datasinkUiServiceProvider
         ) {
      this.treeProvider = treeProvider;
      this.datasinkDaoProvider = datasinkDaoProvider;
      this.datasinkUiServiceProvider = datasinkUiServiceProvider;
   }

   @Override
   public void getMenuEntry(final Menu menu, final ReportDto report, final ReportExecutorInformation info,
         final ReportExecutorMainPanel mainPanel) {
      datasinkDaoProvider.get().getStorageEnabledConfigs(new AsyncCallback<Map<StorageType, Boolean>>() {

         @Override
         public void onSuccess(Map<StorageType, Boolean> result) {
            if (result.get(StorageType.FTPS)) {
               MenuItem item = new DwMenuItem(FtpUiModule.FTPS_NAME, FtpUiModule.FTPS_ICON);
               menu.add(item);
               item.addSelectionHandler(event -> displayExportDialog(report, info, mainPanel.getViewConfigs()));
            }
         }

         @Override
         public void onFailure(Throwable caught) {
         }
      });

   }

   protected void displayExportDialog(final ReportDto report, final ReportExecutorInformation info,
         Collection<ReportViewConfiguration> configs) {
      datasinkUiServiceProvider.get().displaySendToDatasinkDialog(
            FtpsDatasinkDto.class,
            report.getName(), treeProvider, datasinkDaoProvider, report, 
            Optional.of(info),
            new AsyncCallback<Map<String,Object>>() {
               
               @Override
               public void onSuccess(Map<String,Object> result) {
                  final ExportTypeSelection formatType = (ExportTypeSelection) result.get(DatasinkUIModule.REPORT_FORMAT_KEY);
                  datasinkDaoProvider.get().exportReportIntoDatasink(
                        report, 
                        info.getExecuteReportToken(),
                        (DatasinkDefinitionDto) result.get(DatasinkUIModule.DATASINK_KEY), 
                        formatType.getOutputFormat(), 
                        formatType.getExportConfiguration(),
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

}