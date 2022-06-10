package net.datenwerke.rs.core.client.reportexporter.hookers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.inject.Inject;
import com.sencha.gxt.cell.core.client.ButtonCell.ButtonArrowAlign;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.dialog.error.DetailErrorDialog;
import net.datenwerke.gxtdto.client.dialog.error.SimpleErrorDialog;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.json.SimpleFormJsonConfig;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.helper.simpleform.ExportTypeSelection;
import net.datenwerke.rs.core.client.helper.simpleform.config.SFFCExportTypeSelector;
import net.datenwerke.rs.core.client.reportexecutor.hooks.ReportExecutorViewToolbarHook;
import net.datenwerke.rs.core.client.reportexecutor.reportdispatcher.InlineReportView;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorInformation;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorMainPanel;
import net.datenwerke.rs.core.client.reportexporter.ReportExporterUIService;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportexporter.hooks.ExportExternalEntryProviderHook;
import net.datenwerke.rs.core.client.reportexporter.locale.ReportExporterMessages;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.sendto.SendToClientConfig;
import net.datenwerke.rs.core.client.sendto.SendToDao;
import net.datenwerke.rs.core.client.sendto.SendToJsonConfig;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * 
 *
 */
public class ExportExternalButtonHooker implements ReportExecutorViewToolbarHook {

   public static final String FORMAT_FORM_KEY = "__xx_format";

   private final HookHandlerService hookHandler;
   private final ToolbarService toolbarService;
   private final SendToDao sendToDao;

   @Inject
   public ExportExternalButtonHooker(
         HookHandlerService hookHandler, 
         ToolbarService toolbarService,
         ReportExporterUIService reportExporterService, 
         SendToDao sendToDao
         ) {

      /* store objects */
      this.hookHandler = hookHandler;
      this.toolbarService = toolbarService;
      this.sendToDao = sendToDao;
   }

   @Override
   public boolean reportPreviewViewToolbarHook_addLeft(ToolBar toolbar, final ReportDto report,
         final ReportExecutorInformation info, final ReportExecutorMainPanel mainPanel) {
      
      if (mainPanel.getViewConfigs()
         .stream()
         .anyMatch(config -> config instanceof InlineReportView))
         return false;
      
      DwTextButton exportBtn = toolbarService.createSmallButtonLeft(ReportExporterMessages.INSTANCE.sendToLabel(),
            BaseIcon.EXPORT);
      exportBtn.setArrowAlign(ButtonArrowAlign.RIGHT);
      toolbar.add(exportBtn);

      final Menu menu = new Menu();

      exportBtn.setMenu(menu);

      hookHandler.getHookers(ExportExternalEntryProviderHook.class)
         .forEach(hooker -> hooker.getMenuEntry(menu, report, info, mainPanel));
      
      sendToDao.loadClientConfigsFor(report, new RsAsyncCallback<ArrayList<SendToClientConfig>>() {
         @Override
         public void onSuccess(ArrayList<SendToClientConfig> result) {
            if (null != result) {
               for (final SendToClientConfig config : result) {
                  MenuItem item;
                  if (null != config.getIcon())
                     item = new DwMenuItem(config.getTitle(), BaseIcon.from(config.getIcon()));
                  else
                     item = new DwMenuItem(config.getTitle());
                  menu.add(item);
                  item.addSelectionHandler(new SelectionHandler<Item>() {

                     @Override
                     public void onSelection(SelectionEvent<Item> event) {
                        if (null == config.getForm() && !config.isSelectFormat())
                           sendTo(report, config, info.getExecuteReportToken(), mainPanel);
                        else {
                           final Window configWindow = new DwWindow();
                           configWindow.setWidth(100);
                           configWindow.setHeight(210);
                           configWindow.setHeading(config.getTitle());

                           VerticalLayoutContainer container = new VerticalLayoutContainer();
                           configWindow.add(container);

                           VerticalLayoutContainer formWrapper = new VerticalLayoutContainer();

                           /* formats */
                           final SimpleForm formatForm;
                           if (!config.isSelectFormat())
                              formatForm = null;
                           else {
                              formatForm = SimpleForm.getInlineInstance();
                              formatForm.addField(ExportTypeSelection.class, FORMAT_FORM_KEY,
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
                              formatForm.loadFields();
                              formWrapper.add(formatForm, new VerticalLayoutData(1, -1));
                           }

                           /* form */
                           final SimpleForm detailForm;
                           if (null == config.getForm())
                              detailForm = null;
                           else {
                              SendToJsonConfig formConfig;
                              try {
                                 formConfig = JsonUtils.safeEval(config.getForm()).cast();
                              } catch (IllegalArgumentException e) {
                                 new SimpleErrorDialog(BaseMessages.INSTANCE.error(), e.getMessage()).show();
                                 return;
                              }

                              configWindow.setWidth(formConfig.getWidth());
                              configWindow.setHeight(formConfig.getHeight());
                              configWindow.setHeading(config.getTitle());

                              try {
                                 detailForm = SimpleForm.fromJson((SimpleFormJsonConfig) formConfig.getForm().cast());
                                 detailForm.setHeaderVisible(false);
                                 detailForm.loadFields();
                              } catch (RuntimeException e) {
                                 new SimpleErrorDialog(BaseMessages.INSTANCE.error(), e.getMessage()).show();
                                 return;
                              }

                              formWrapper.add(detailForm, new VerticalLayoutData(1, 1));
                           }

                           container.add(formWrapper, new VerticalLayoutData(1, 1, new Margins(10)));

                           /* buttons */
                           DwToolBar tbar = new DwToolBar();
                           container.add(tbar, new VerticalLayoutData(1, -1));

                           tbar.add(new FillToolItem());

                           TextButton cancel = new TextButton(BaseMessages.INSTANCE.cancel());
                           cancel.addSelectHandler(selectEvent -> configWindow.hide());
                           tbar.add(cancel);

                           TextButton submit = new TextButton(BaseMessages.INSTANCE.submit());
                           submit.addSelectHandler(selectEvent -> {
                              ExportTypeSelection type = null == formatForm ? null
                                    : (ExportTypeSelection) formatForm.getValue(FORMAT_FORM_KEY);

                              if (null != type && !type.isConfigured()) {
                                 new AlertMessageBox(BaseMessages.INSTANCE.error(),
                                       ReportExporterMessages.INSTANCE.exportTypeNotConfigured()).show();
                                 return;
                              }

                              configWindow.hide();

                              String format = null == formatForm ? null
                                    : ((ExportTypeSelection) formatForm.getValue(FORMAT_FORM_KEY)).getOutputFormat();
                              List<ReportExecutionConfigDto> formatConfig = null == formatForm ? null
                                    : ((ExportTypeSelection) formatForm.getValue(FORMAT_FORM_KEY))
                                          .getExportConfiguration();

                              Map<String, String> values = null == detailForm ? new HashMap<>()
                                    : detailForm.getStringValueMap();
                              sendTo(report, config, format, formatConfig, values, info.getExecuteReportToken(),
                                    mainPanel);
                           });
                           tbar.add(submit);

                           configWindow.show();
                        }

                     }

                  });
               }
            }
         }
      });

      return true;
   }

   protected void sendTo(ReportDto report, SendToClientConfig config, String executorToken,
         ReportExecutorMainPanel mainPanel) {
      sendTo(report, config, null, null, new HashMap<String, String>(), executorToken, mainPanel);
   }

   protected void sendTo(ReportDto report, SendToClientConfig config, String format,
         List<ReportExecutionConfigDto> formatConfig, Map<String, String> values, String executorToken,
         final ReportExecutorMainPanel mainPanel) {
      mainPanel.mask(BaseMessages.INSTANCE.loadingMsg());
      sendToDao.sendTo(report, executorToken, config.getId(), format, formatConfig, values,
            new RsAsyncCallback<String>() {
               public void onSuccess(String result) {
                  Info.display(BaseMessages.INSTANCE.ok(), result);
                  mainPanel.unmask();
               };

               @Override
               public void onFailure(Throwable caught) {
                  mainPanel.unmask();
                  new DetailErrorDialog(caught).show();
                  super.onFailure(caught);
               }
            });
   }

   @Override
   public boolean reportPreviewViewToolbarHook_addRight(ToolBar toolbar, ReportDto report,
         ReportExecutorInformation info, ReportExecutorMainPanel mainPanel) {
      return false;
   }

   @Override
   public void reportPreviewViewToolbarHook_reportUpdated(ReportDto report, ReportExecutorInformation info) {
   }

}
