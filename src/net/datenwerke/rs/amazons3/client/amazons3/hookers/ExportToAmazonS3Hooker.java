package net.datenwerke.rs.amazons3.client.amazons3.hookers;

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
import net.datenwerke.rs.amazons3.client.amazons3.AmazonS3Dao;
import net.datenwerke.rs.amazons3.client.amazons3.AmazonS3UiModule;
import net.datenwerke.rs.amazons3.client.amazons3.dto.AmazonS3DatasinkDto;
import net.datenwerke.rs.amazons3.client.amazons3.provider.annotations.DatasinkTreeAmazonS3;
import net.datenwerke.rs.core.client.datasinkmanager.DatasinkUIModule;
import net.datenwerke.rs.core.client.datasinkmanager.DatasinkUIService;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.helper.simpleform.ExportTypeSelection;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorInformation;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorMainPanel;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewConfiguration;
import net.datenwerke.rs.core.client.reportexporter.hooks.ExportExternalEntryProviderHook;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.enterprise.client.EnterpriseUiService;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.locale.ScheduleAsFileMessages;

public class ExportToAmazonS3Hooker implements ExportExternalEntryProviderHook {

   private final Provider<UITree> treeProvider;
   private final Provider<AmazonS3Dao> datasinkDaoProvider;

   private final Provider<EnterpriseUiService> enterpriseServiceProvider;

   private final Provider<DatasinkUIService> datasinkUiServiceProvider;

   @Inject
   public ExportToAmazonS3Hooker(@DatasinkTreeAmazonS3 Provider<UITree> treeProvider,
         Provider<AmazonS3Dao> datasinkDaoProvider, Provider<EnterpriseUiService> enterpriseServiceProvider,
         Provider<DatasinkUIService> datasinkUiServiceProvider) {
      this.treeProvider = treeProvider;
      this.datasinkDaoProvider = datasinkDaoProvider;
      this.enterpriseServiceProvider = enterpriseServiceProvider;
      this.datasinkUiServiceProvider = datasinkUiServiceProvider;
   }

   @Override
   public void getMenuEntry(final Menu menu, final ReportDto report, final ReportExecutorInformation info,
         final ReportExecutorMainPanel mainPanel) {
      if (enterpriseServiceProvider.get().isEnterprise()) {
         datasinkDaoProvider.get().getStorageEnabledConfigs(new AsyncCallback<Map<StorageType, Boolean>>() {

            @Override
            public void onSuccess(Map<StorageType, Boolean> result) {
               if (result.get(StorageType.AMAZONS3)) {
                  MenuItem item = new DwMenuItem(AmazonS3UiModule.NAME, AmazonS3UiModule.ICON);
                  menu.add(item);
                  item.addSelectionHandler(event -> displayExportDialog(report, info, mainPanel.getViewConfigs()));
               }
            }

            @Override
            public void onFailure(Throwable caught) {
            }
         });
      } else {
         // we add item but disable it
         MenuItem item = new DwMenuItem(AmazonS3UiModule.NAME, AmazonS3UiModule.ICON);
         menu.add(item);
         item.disable();
      }

   }

   protected void displayExportDialog(final ReportDto report, final ReportExecutorInformation info,
         Collection<ReportViewConfiguration> configs) {
      datasinkUiServiceProvider.get().displaySendToDatasinkDialog(AmazonS3DatasinkDto.class, report.getName(),
            treeProvider, datasinkDaoProvider, report, Optional.of(info), new AsyncCallback<Map<String, Object>>() {

               @Override
               public void onSuccess(Map<String, Object> result) {
                  final ExportTypeSelection formatType = (ExportTypeSelection) result
                        .get(DatasinkUIModule.REPORT_FORMAT_KEY);
                  datasinkDaoProvider.get().exportReportIntoDatasink(report, info.getExecuteReportToken(),
                        (DatasinkDefinitionDto) result.get(DatasinkUIModule.DATASINK_KEY), formatType.getOutputFormat(),
                        formatType.getExportConfiguration(), (String) result.get(DatasinkUIModule.DATASINK_FILENAME),
                        (String) result.get(DatasinkUIModule.DATASINK_FOLDER),
                        (Boolean) result.get(DatasinkUIModule.DATASINK_COMPRESSED_KEY),
                        new NotamCallback<Void>(ScheduleAsFileMessages.INSTANCE.dataSent()));
               }

               @Override
               public void onFailure(Throwable caught) {
               }
            });
   }

}
