package net.datenwerke.rs.core.client.reportexporter.hookers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwAlertMessageBox;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCBoolean;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.servercommunication.callback.NotamCallback;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.helper.simpleform.ExportTypeSelection;
import net.datenwerke.rs.core.client.helper.simpleform.config.SFFCExportTypeSelector;
import net.datenwerke.rs.core.client.reportexecutor.hooks.PrepareReportModelForStorageOrExecutionHook;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorInformation;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorMainPanel;
import net.datenwerke.rs.core.client.reportexporter.ReportExporterDao;
import net.datenwerke.rs.core.client.reportexporter.hooks.ExportExternalEntryProviderHook;
import net.datenwerke.rs.core.client.reportexporter.locale.ReportExporterMessages;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.scheduler.client.scheduler.locale.SchedulerMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownUser;

public class ExportViaMailHooker implements ExportExternalEntryProviderHook {

   private final ReportExporterDao reDao;
   private final LoginService loginService;
   private final HookHandlerService hookHandler;

   @Inject
   public ExportViaMailHooker(ReportExporterDao reDao, LoginService loginService, HookHandlerService hookHandler) {
      this.reDao = reDao;
      this.loginService = loginService;
      this.hookHandler = hookHandler;
   }

   @Override
   public void getMenuEntry(Menu menu, final ReportDto report, final ReportExecutorInformation info,
         final ReportExecutorMainPanel mainPanel) {

      MenuItem item = new DwMenuItem(ReportExporterMessages.INSTANCE.exportViaMailLabel() + " (deprecated)",
            BaseIcon.ENVELOPE_O);
      menu.add(item);
      item.addSelectionHandler(event -> displayExportDialog(report, info));
   }

   protected void displayExportDialog(final ReportDto report, final ReportExecutorInformation info) {
      final DwWindow window = new DwWindow();
      window.setHeaderIcon(BaseIcon.ENVELOPE_O);
      window.setHeading(ReportExporterMessages.INSTANCE.exportViaMailLabel() + " (deprecated)");
      window.setSize(600, 600);

      final SimpleForm form = SimpleForm.getInlineInstance();

      final String formatKey = form.addField(ExportTypeSelection.class,
            ReportExporterMessages.INSTANCE.exportTypeLabel(), new SFFCExportTypeSelector() {

               @Override
               public ReportDto getReport() {
                  return report;
               }

               @Override
               public String getExecuteReportToken() {
                  return info.getExecuteReportToken();
               }

               @Override
               public Optional<Class<? extends DatasinkDefinitionDto>> getDatasinkType() {
                  return Optional.empty();
               }
            });
      final String rcptKey = form.addField(StrippedDownUser.class, ReportExporterMessages.INSTANCE.selectUserLabel());

      final String subjKey = form.addField(String.class, ReportExporterMessages.INSTANCE.subjectLabel());
      final String msgKey = form.addField(String.class, ReportExporterMessages.INSTANCE.messageLabel(),
            new SFFCTextAreaImpl());

      form.setLabelAlign(LabelAlign.LEFT);

      final String compressedKey = form.addField(Boolean.class, "", new SFFCBoolean() {
         @Override
         public String getBoxLabel() {
            return SchedulerMessages.INSTANCE.reportCompress();
         }
      });

      window.add(form, new MarginData(10));

      UserDto user = loginService.getCurrentUser();
      StrippedDownUser sUser = StrippedDownUser.fromUser(user);

      List<StrippedDownUser> users = new ArrayList<>();
      users.add(sUser);

      form.setValue(subjKey, "");
      form.setValue(msgKey, "");
      form.setValue(rcptKey, users);

      form.loadFields();

      window.addCancelButton();

      DwTextButton submitBtn = new DwTextButton(BaseMessages.INSTANCE.submit());
      submitBtn.addSelectHandler(event -> {
         List<StrippedDownUser> rcptList = (List<StrippedDownUser>) form.getValue(rcptKey);
         String subject = (String) form.getValue(subjKey);
         String message = (String) form.getValue(msgKey);
         boolean compressed = (boolean) form.getValue(compressedKey);
         ExportTypeSelection type = (ExportTypeSelection) form.getValue(formatKey);

         if (!type.isConfigured()) {
            new DwAlertMessageBox(BaseMessages.INSTANCE.error(),
                  ReportExporterMessages.INSTANCE.exportTypeNotConfigured()).show();
            return;
         }

         hookHandler.getHookers(PrepareReportModelForStorageOrExecutionHook.class).stream()
               .filter(hooker -> hooker.consumes(report))
               .forEach(hooker -> hooker.prepareForExecutionOrStorage(report, info.getExecuteReportToken()));

         reDao.exportViaMail(report, info.getExecuteReportToken(), type.getOutputFormat(),
               type.getExportConfiguration(), subject, message, compressed, rcptList,
               new NotamCallback<Void>(ReportExporterMessages.INSTANCE.messageSend()));

         window.hide();
      });
      window.addButton(submitBtn);

      window.show();
   }

}
