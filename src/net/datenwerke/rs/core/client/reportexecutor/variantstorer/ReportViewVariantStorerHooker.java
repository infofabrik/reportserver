package net.datenwerke.rs.core.client.reportexecutor.variantstorer;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.sencha.gxt.core.client.util.Format;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer.HBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.DialogHideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.DwWindow.OnButtonClickHandler;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwSplitButton;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwAlertMessageBox;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwConfirmMessageBox;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.actions.SetValueFieldAction;
import net.datenwerke.gxtdto.client.forms.simpleform.conditions.FieldChanged;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.servercommunication.callback.ModalAsyncCallback;
import net.datenwerke.rs.core.client.reportexecutor.events.ExecutorEventHandler;
import net.datenwerke.rs.core.client.reportexecutor.events.VariantChangedEvent;
import net.datenwerke.rs.core.client.reportexecutor.events.VariantCreatedEvent;
import net.datenwerke.rs.core.client.reportexecutor.locale.ReportexecutorMessages;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorInformation;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorMainPanel;
import net.datenwerke.rs.core.client.reportmanager.dto.interfaces.ReportVariantDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.pa.ReportDtoPA;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFolderDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.helper.simpleform.SFFCTsTeamSpaceSelector;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.locale.TsFavoriteMessages;
import net.datenwerke.security.client.security.dto.ExecuteDto;
import net.datenwerke.security.client.security.dto.WriteDto;

/**
 * 
 *
 */
public class ReportViewVariantStorerHooker implements VariantStorerHook {

   private ExecutorEventHandler eventHandler;
   private VariantStorerConfig config;

   @Override
   public boolean reportPreviewViewToolbarHook_addLeft(ToolBar toolbar, ReportDto report,
         ReportExecutorInformation info, ReportExecutorMainPanel mainPanel) {
      return false;
   }

   @Override
   public boolean reportPreviewViewToolbarHook_addRight(ToolBar toolbar, ReportDto report,
         ReportExecutorInformation info, ReportExecutorMainPanel mainPanel) {
      if (! report.hasAccessRight(WriteDto.class) || ! report.hasAccessRight(ExecuteDto.class))
         return false;

      /* store variant */
      TextButton storeVariantBtn = createStoreVariantButton(report, info.getExecuteReportToken());
      toolbar.add(storeVariantBtn);

      return true;
   }

   private TextButton createStoreVariantButton(final ReportDto report, final String executeToken) {
      TextButton btn = null;
      if (report instanceof ReportVariantDto && config.allowEditVariant()) {
         btn = new DwSplitButton(ReportexecutorMessages.INSTANCE.store());
         ((DwSplitButton) btn).setIcon(BaseIcon.REPORT_DISK);
         btn.addSelectHandler(new SelectHandler() {

            @Override
            public void onSelect(SelectEvent event) {
               if (config.displayEditVariantOnStore())
                  displayStoreVariantDialog(report, executeToken, true);
               else {
                  ConfirmMessageBox cmb = new DwConfirmMessageBox(
                        ReportexecutorMessages.INSTANCE.editVariantConfirmTitle(),
                        ReportexecutorMessages.INSTANCE.editVariantConfirmMsg());
                  cmb.addDialogHideHandler(new DialogHideHandler() {
                     @Override
                     public void onDialogHide(DialogHideEvent event) {
                        if (event.getHideButton() == PredefinedButton.YES) {
                           /* prepare callback */
                           AsyncCallback<ReportDto> callback = new ModalAsyncCallback<ReportDto>(
                                 ReportexecutorMessages.INSTANCE.storedSuccessfully()) {
                              @Override
                              public void doOnSuccess(ReportDto resultReport) {
                                 VariantChangedEvent event = new VariantChangedEvent();
                                 event.setVariant(resultReport);
                                 eventHandler.handleEvent(event);
                              }
                           };

                           /* perform server call */
                           config.getServerCallHandler().editVariant(report, executeToken, report.getName(),
                                 report.getDescription(), callback);
                        }
                     }
                  });
                  cmb.show();
               }
            }
         });

         Menu menu = new DwMenu();

         MenuItem saveNewItem = new DwMenuItem(ReportexecutorMessages.INSTANCE.storeNew(), BaseIcon.REPORT_ADD);
         saveNewItem.addSelectionHandler(new SelectionHandler<Item>() {

            @Override
            public void onSelection(SelectionEvent<Item> event) {
               displayStoreVariantDialog(report, executeToken, false);
            }
         });
         menu.add(saveNewItem);

         btn.setMenu(menu);
      } else {
         btn = new DwTextButton(ReportexecutorMessages.INSTANCE.store(), BaseIcon.REPORT_DISK);
         btn.addSelectHandler(new SelectHandler() {

            @Override
            public void onSelect(SelectEvent event) {
               displayStoreVariantDialog(report, executeToken, false);
            }
         });
      }

      return btn;
   }

   private void displayStoreVariantDialog(final ReportDto report, final String executeToken, final boolean edit) {
      final DwWindow dialog = DwWindow.newAutoSizeDialog(400);
      dialog.addStyleName("rs-saveas-d");
      dialog.setWidth(450);
      if (edit)
         dialog.setHeading(ReportexecutorMessages.INSTANCE.changeVariant(Format.ellipse(report.getName(), 100)));
      else
         dialog.setHeading(ReportexecutorMessages.INSTANCE.createNewVariant(Format.ellipse(report.getName(), 100)));

      dialog.setModal(true);
      dialog.setCenterOnShow(true);

      /* create form */
      DwContentPanel panelWrapper = DwContentPanel.newInlineInstance();
      panelWrapper.setLightDarkStyle();
      dialog.add(panelWrapper);

      VerticalLayoutContainer fieldWrapper = new VerticalLayoutContainer();
      panelWrapper.add(fieldWrapper, new MarginData(10));

      final TextField nameField = new TextField();
      nameField.setWidth(200);
      nameField.setEmptyText(BaseMessages.INSTANCE.name());
      nameField.setAllowBlank(false);

      FieldLabel nameLabel = new FieldLabel(nameField, BaseMessages.INSTANCE.saveAs());
      nameLabel.setLabelWidth(120);

      final HTML icon = new HTML(BaseIcon.CHEVRON_DOWN.toSafeHtml());
      icon.addStyleName("rs-saveas-detail-btn");

      nameLabel.addStyleName("rs-saveas-flabel");

      HBoxLayoutContainer hbox = new HBoxLayoutContainer();
      fieldWrapper.add(hbox, new VerticalLayoutData(-1, -1));
      hbox.setHBoxLayoutAlign(HBoxLayoutAlign.MIDDLE);
      hbox.setPack(BoxLayoutPack.START);
      hbox.setWidth(430);

      hbox.add(nameLabel);
      hbox.add(icon);

      final HTML separator = new HTML("<hr class='rs-saveas-sep'/>");
      separator.setVisible(false);

      fieldWrapper.add(separator, new VerticalLayoutData(1, -1));

      final SimpleForm form = SimpleForm.getInlineInstance();
      form.hide();
      form.setWidth(430);
      fieldWrapper.add(form, new VerticalLayoutData(1, -1, new Margins(5, 0, 0, 0)));
      form.setLabelAlign(LabelAlign.LEFT);

      final String teamSpaceKey = "tskey";
      final String folderKey = "folderkey";

      final String descKey = form.addField(String.class, ReportDtoPA.INSTANCE.description(),
            BaseMessages.INSTANCE.description(), new SFFCTextAreaImpl(80));

      if (!edit) {
         form.addField(TeamSpaceDto.class, teamSpaceKey, TsFavoriteMessages.INSTANCE.selectFromTeamSpaceText());

         form.addField(TsDiskFolderDto.class, folderKey, BaseMessages.INSTANCE.folder(), new SFFCTsTeamSpaceSelector() {
            @Override
            public TeamSpaceDto getTeamSpace() {
               return (TeamSpaceDto) form.getValue(teamSpaceKey);
            }
         });

         form.addCondition(teamSpaceKey, new FieldChanged(), new SetValueFieldAction(folderKey, null));
      }

      form.loadFields();

      icon.addClickHandler(event -> {
         if (separator.isVisible()) {
            separator.setVisible(false);
            form.hide();
            icon.setHTML(BaseIcon.CHEVRON_DOWN.toSafeHtml());
         } else {
            separator.setVisible(true);
            form.show();
            icon.setHTML(BaseIcon.CHEVRON_UP.toSafeHtml());
         }
         dialog.forceLayout();
      });

      if (edit) {
         nameField.setValue(report.getName());
         form.setValue(descKey, report.getDescription());
      } else {
         if (report instanceof ReportVariantDto) {
            nameField.setValue(report.getName() + " (copy)");
            form.setValue(descKey, report.getDescription());
         }
         form.setValue(teamSpaceKey, config.getTeamSpace());
         form.setValue(folderKey, config.getTeamSpaceFolder());
      }

      /* add buttons */
      dialog.addCancelButton();

      dialog.addSpecButton(new OnButtonClickHandler() {
         @Override
         public void onClick() {
            String name = nameField.getValue();
            if (null == name || "".equals(name.trim())) {
               new DwAlertMessageBox(BaseMessages.INSTANCE.warning(),
                     ReportexecutorMessages.INSTANCE.pleaseProvideName()).show();
               return;
            }

            String desc = (String) form.getValue(descKey);

            TeamSpaceDto teamSpace = null;
            TsDiskFolderDto folder = null;
            if (!edit) {
               teamSpace = (TeamSpaceDto) form.getValue(teamSpaceKey);
               AbstractTsDiskNodeDto folderNode = (AbstractTsDiskNodeDto) form.getValue(folderKey);
               if (folderNode instanceof TsDiskFolderDto)
                  folder = (TsDiskFolderDto) folderNode;
            }

            if (null == teamSpace && !edit && !config.allowNullTeamSpace()) {
               new DwAlertMessageBox(BaseMessages.INSTANCE.warning(),
                     ReportexecutorMessages.INSTANCE.pleaseProvideName()).show();
               return;
            }

            /* prepare callback and properties */
            AsyncCallback<ReportDto> callback = new ModalAsyncCallback<ReportDto>(
                  ReportexecutorMessages.INSTANCE.storedSuccessfully()) {
               @Override
               public void doOnSuccess(ReportDto resultReport) {
                  VariantCreatedEvent event = new VariantCreatedEvent();
                  event.setVariant(resultReport);
                  eventHandler.handleEvent(event);
               }
            };

            /* server call */
            if (edit)
               config.getServerCallHandler().editVariant(report, executeToken, name, desc, callback);
            else
               config.getServerCallHandler().createNewVariant(report, teamSpace, folder, executeToken, name, desc,
                     callback);

            dialog.hide();
         }
      }, edit ? ReportexecutorMessages.INSTANCE.editVariant() : ReportexecutorMessages.INSTANCE.createVariant(), false);

      dialog.show();
   }

   @Override
   public void reportPreviewViewToolbarHook_reportUpdated(ReportDto report, ReportExecutorInformation info) {
      // do not care
   }

   @Override
   public void setEventHandler(ExecutorEventHandler eventHandler) {
      this.eventHandler = eventHandler;
   }

   @Override
   public void setConfig(VariantStorerConfig variantStorerConfig) {
      this.config = variantStorerConfig;
   }

}
