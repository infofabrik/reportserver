package net.datenwerke.rs.emaildatasink.client.emaildatasink.hookers;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.servercommunication.callback.NotamCallback;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.datasinkmanager.DatasinkUIModule;
import net.datenwerke.rs.core.client.datasinkmanager.DatasinkUIService;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.helper.simpleform.ExportTypeSelection;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorInformation;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorMainPanel;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewConfiguration;
import net.datenwerke.rs.core.client.reportexporter.hooks.ExportExternalEntryProviderHook;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.EmailDatasinkDao;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.EmailDatasinkUiModule;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.dto.EmailDatasinkDto;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.provider.annotations.DatasinkTreeEmail;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.locale.ScheduleAsFileMessages;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownUser;

public class ExportToEmailDatasinkHooker implements ExportExternalEntryProviderHook {

   private final Provider<UITree> treeProvider;
   private final Provider<EmailDatasinkDao> datasinkDaoProvider;
   private final Provider<DatasinkUIService> datasinkUiServiceProvider;

   @Inject
   public ExportToEmailDatasinkHooker(HookHandlerService hookHandler, LoginService loginService,
         @DatasinkTreeEmail Provider<UITree> treeProvider, Provider<EmailDatasinkDao> datasinkDaoProvider,
         Provider<DatasinkUIService> datasinkUiServiceProvider) {
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
            if (result.get(StorageType.EMAIL)) {
               MenuItem item = new DwMenuItem(EmailDatasinkUiModule.NAME, EmailDatasinkUiModule.ICON);
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
      datasinkUiServiceProvider.get().displaySendToDatasinkDialog(EmailDatasinkDto.class, report.getName(),
            treeProvider, datasinkDaoProvider, report, Optional.of(info), new AsyncCallback<Map<String, Object>>() {

               @Override
               public void onSuccess(Map<String, Object> result) {
                  final ExportTypeSelection formatType = (ExportTypeSelection) result
                        .get(DatasinkUIModule.REPORT_FORMAT_KEY);
                  datasinkDaoProvider.get().exportReportIntoDatasink(report, info.getExecuteReportToken(),
                        (DatasinkDefinitionDto) result.get(DatasinkUIModule.DATASINK_KEY), formatType.getOutputFormat(),
                        formatType.getExportConfiguration(), (String) result.get(DatasinkUIModule.DATASINK_FILENAME),
                        (String) result.get(EmailDatasinkUiModule.DATASINK_SUBJECT),
                        (String) result.get(EmailDatasinkUiModule.DATASINK_MESSSAGE),
                        (List<StrippedDownUser>) result.get(EmailDatasinkUiModule.DATASINK_RCPTLIST),
                        (Boolean) result.get(DatasinkUIModule.DATASINK_COMPRESSED_KEY),
                        new NotamCallback<Void>(ScheduleAsFileMessages.INSTANCE.dataSent()));
               }

               @Override
               public void onFailure(Throwable caught) {
               }
            });
   }

}
