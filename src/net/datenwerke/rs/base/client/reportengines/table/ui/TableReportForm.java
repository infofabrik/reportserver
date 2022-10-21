package net.datenwerke.rs.base.client.reportengines.table.ui;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.cell.core.client.ButtonCell.ButtonArrowAlign;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.menu.SeparatorMenuItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.codemirror.CodeMirrorPanel;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.binding.FormBinding;
import net.datenwerke.gxtdto.client.model.ListStringBaseModel;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.rs.base.client.datasources.config.DatabaseDatasourceConfigConfigurator.DatabaseSpecificFieldConfigExecution;
import net.datenwerke.rs.base.client.datasources.config.DatabaseDatasourceConfigConfigurator.DatabaseSpecificFieldConfigToolbar;
import net.datenwerke.rs.base.client.reportengines.table.TableReportUtilityDao;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.pa.TableReportDtoPA;
import net.datenwerke.rs.base.client.reportengines.table.ui.model.SpecialParameters;
import net.datenwerke.rs.core.client.datasourcemanager.DatasourceUIService;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerProviderDto;
import net.datenwerke.rs.core.client.datasourcemanager.helper.forms.DatasourceSelectionField;
import net.datenwerke.rs.core.client.datasourcemanager.provider.annotations.DatasourceTreeNoMondrian;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.locale.ReportmanagerMessages;
import net.datenwerke.rs.core.client.reportmanager.ui.forms.AbstractReportForm;
import net.datenwerke.rs.enterprise.client.EnterpriseUiService;
import net.datenwerke.rs.globalconstants.client.globalconstants.locale.GlobalConstantsMessages;
import net.datenwerke.rs.incubator.client.reportmetadata.locale.ReportMetadataMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class TableReportForm extends AbstractReportForm {

   private final TableReportUtilityDao tableReportDao;
   private final ToolbarService toolbarService;
   private final EnterpriseUiService enterpriseService;

   private final Provider<UITree> datasourceTreeProvider;

   private DatasourceSelectionField metadataDatasourceFieldCreator;
   private CheckBox allowCubify;

   @Inject
   public TableReportForm(DatasourceUIService datasourceService, TableReportUtilityDao tableReportDao,
         ToolbarService toolbarService, EnterpriseUiService enterpriseService,
         @DatasourceTreeNoMondrian Provider<UITree> datasourceTreeProvider) {
      super(datasourceService, datasourceTreeProvider);
      this.tableReportDao = tableReportDao;
      this.toolbarService = toolbarService;
      this.enterpriseService = enterpriseService;
      this.datasourceTreeProvider = datasourceTreeProvider;
   }

   @Override
   protected void initializeForm(FormPanel form, VerticalLayoutContainer fieldWrapper) {
      super.initializeForm(form, fieldWrapper);

      /* metadata datasource */
      addMetadataDatasourceField(fieldWrapper, true);

      /* cubify */
      allowCubify = new CheckBox();

      if (enterpriseService.isEnterprise()) {
         allowCubify.setBoxLabel(ReportmanagerMessages.INSTANCE.propertyAllowCubify());
         fieldWrapper.add(new FieldLabel(allowCubify, ReportmanagerMessages.INSTANCE.fieldLabelCubify()),
               new VerticalLayoutData(-1, -1, new Margins(0, 0, 5, 0)));
      }
   }

   @Override
   protected String getHeader() {
      return ReportmanagerMessages.INSTANCE.editTableReport() + " (" + getSelectedNode().getId() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
   }

   @Override
   public void doInitFormBinding(FormBinding binding, final AbstractNodeDto model) {
      super.doInitFormBinding(binding, model);

      if (enterpriseService.isEnterprise())
         binding.addBinding(allowCubify, model, TableReportDtoPA.INSTANCE.allowCubification());

      metadataDatasourceFieldCreator.initFormBinding(new DatasourceContainerProviderDto() {

         TableReportDto tableReport = (TableReportDto) model;

         @Override
         public void setDatasourceContainer(DatasourceContainerDto datasourceContainer) {
            tableReport.setMetadataDatasourceContainer(datasourceContainer);
         }

         @Override
         public DatasourceContainerDto getDatasourceContainer() {
            return tableReport.getMetadataDatasourceContainer();
         }
      });
   }

   private void addReportParameter(final CodeMirrorPanel codeMirrorPanel, final Menu menu,
         final DatasourceSelectionField datasourceFieldCreator) {
      ReportDto report = (ReportDto) getSelectedNode();
      for (ParameterDefinitionDto def : report.getParameterDefinitions()) {
         final String key = def.getKey();
         MenuItem item = new DwMenuItem(def.getName());
         item.addSelectionHandler(event -> {
            String value = codeMirrorPanel.getTextArea().getValue();
            value = null == value ? "" : value;
            value = key.startsWith("$") ? value + key : value + "${" + key + "}";
            codeMirrorPanel.getTextArea().setValue(value);
            datasourceFieldCreator.inheritChanges();
         });
         menu.add(item);
      }
   }

   private void addSpecialParameter(final CodeMirrorPanel codeMirrorPanel, final Menu subMenuUser,
         final MenuItem subMenuItemUser, final Menu subMenuReport, final MenuItem subMenuItemReport,
         final Menu subMenuLocale, final MenuItem subMenuItemLocale, final Menu subMenuGlobalConstants,
         final MenuItem subMenuItemGlobalConstants, final Menu subMenuMetadata, final MenuItem subMenuItemMetadata,
         final Menu subMenuExpressions, final MenuItem subMenuItemExpressions,
         final DatasourceSelectionField datasourceFieldCreator) {

      TableReportDto report = (TableReportDto) getSelectedNode();

      tableReportDao.getSpecialParameter(report, null, new RsAsyncCallback<Map<String, List<String>>>() {
         @Override
         public void onSuccess(Map<String, List<String>> result) {

            for (Entry<String, List<String>> entry : result.entrySet()) {
               String paramKey = entry.getKey();

               for (final String itemKey : entry.getValue()) {
                  MenuItem item = new MenuItem(itemKey);
                  item.addSelectionHandler(new SelectionHandler<Item>() {
                     @Override
                     public void onSelection(SelectionEvent<Item> event) {
                        String value = codeMirrorPanel.getTextArea().getValue();
                        value = null == value ? "" : value;
                        value = itemKey.startsWith("$") ? value + itemKey : value + "${" + itemKey + "}";
                        codeMirrorPanel.getTextArea().setValue(value);
                        datasourceFieldCreator.inheritChanges();
                     }
                  });

                  if (SpecialParameters._RS_USER.name().equals(paramKey)) {
                     subMenuUser.add(item);
                  }

                  if (SpecialParameters._RS_REPORT.name().equals(paramKey)) {
                     subMenuReport.add(item);
                  }

                  if (SpecialParameters._RS_LOCALE.name().equals(paramKey)) {
                     subMenuLocale.add(item);
                  }

                  if (SpecialParameters._RS_GLOBAL_CONSTANTS.name().equals(paramKey)) {
                     subMenuGlobalConstants.add(item);
                  }

                  if (SpecialParameters._RS_METADATA.name().equals(paramKey)) {
                     item.setText(itemKey.replace(SpecialParameters._RS_METADATA.name() + "_", ""));
                     subMenuMetadata.add(item);
                  }

                  if (null != subMenuExpressions && SpecialParameters._RS_EXPRESSION.name().equals(paramKey)) {
                     subMenuExpressions.add(item);
                  }
               }

            }

            subMenuItemUser.setSubMenu(subMenuUser);
            subMenuItemLocale.setSubMenu(subMenuLocale);
            subMenuItemReport.setSubMenu(subMenuReport);
            subMenuItemGlobalConstants.setSubMenu(subMenuGlobalConstants);
            subMenuItemMetadata.setSubMenu(subMenuMetadata);
            if (null != subMenuExpressions && null != subMenuItemExpressions)
               subMenuItemExpressions.setSubMenu(subMenuExpressions);
            datasourceFieldCreator.inheritChanges();
         }

         @Override
         public void onFailure(Throwable caught) {
            super.onFailure(caught);
         }

      });
   }

   @Override
   protected void addDatasourceField(Container container, boolean displayConfigFields) {
      datasourceFieldCreator = datasourceService.getSelectionField(container, displayConfigFields,
            datasourceTreeProvider);
      datasourceFieldCreator.setShouldShowDefaultAdditionalConfig(true);
      datasourceFieldCreator.addSelectionField();
      datasourceFieldCreator.addDisplayDefaultButton();

      /* add specific field */
      datasourceFieldCreator.addSpecificDatasourceConfig(new DatabaseSpecificFieldConfigToolbar() {

         @Override
         public void enhance(ToolBar toolbar, final CodeMirrorPanel codeMirrorPanel) {
            /* add parameter */
            DwTextButton selectParamBtn = toolbarService
                  .createSmallButtonLeft(ReportmanagerMessages.INSTANCE.selectParamBtnLabel(), BaseIcon.TABLE_ADD);
            selectParamBtn.setArrowAlign(ButtonArrowAlign.RIGHT);
            toolbar.add(selectParamBtn);

            final Menu menu = new DwMenu();
            selectParamBtn.setMenu(menu);

            menu.addBeforeShowHandler(event -> {
               menu.clear();
               addReportParameter(codeMirrorPanel, menu, datasourceFieldCreator);

               SeparatorMenuItem separator = new SeparatorMenuItem();
               menu.add(separator);

               final Menu subMenuUser = new DwMenu();
               final MenuItem subMenuItemUser = new DwMenuItem(ReportmanagerMessages.INSTANCE.currentUser());
               menu.add(subMenuItemUser);

               final Menu subMenuReport = new DwMenu();
               final MenuItem subMenuItemReport = new DwMenuItem(ReportmanagerMessages.INSTANCE.currentReport());
               menu.add(subMenuItemReport);

               final Menu subMenuLocale = new DwMenu();
               final MenuItem subMenuItemLocale = new DwMenuItem("Locale");
               menu.add(subMenuItemLocale);

               final Menu subMenuGlobalConstants = new DwMenu();
               final MenuItem subMenuItemGlobalConstants = new DwMenuItem(
                     GlobalConstantsMessages.INSTANCE.securityTitle());
               menu.add(subMenuItemGlobalConstants);

               final Menu subMenuMetadata = new DwMenu();
               final MenuItem subMenuItemMetadata = new DwMenuItem(ReportMetadataMessages.INSTANCE.header());
               menu.add(subMenuItemMetadata);

               final Menu subMenuExpressions = new DwMenu();
               final MenuItem subMenuItemExpressions = new DwMenuItem(ReportmanagerMessages.INSTANCE.expressions());
               menu.add(subMenuItemExpressions);

               addSpecialParameter(codeMirrorPanel, subMenuUser, subMenuItemUser, subMenuReport, subMenuItemReport,
                     subMenuLocale, subMenuItemLocale, subMenuGlobalConstants, subMenuItemGlobalConstants,
                     subMenuMetadata, subMenuItemMetadata, subMenuExpressions, subMenuItemExpressions,
                     datasourceFieldCreator);
            });
         }
      });

      datasourceFieldCreator.addSpecificDatasourceConfig(new DatabaseSpecificFieldConfigExecution() {

         @Override
         public void executeGetColumns(String query, final AsyncCallback<List<String>> callback) {
            TableReportDto report = (TableReportDto) getSelectedNode();
            DatasourceContainerDto container = report.getDatasourceContainer();
            tableReportDao.loadColumnDefinition(report, container, query, null, new RsAsyncCallback<List<ColumnDto>>() {
               @Override
               public void onSuccess(List<ColumnDto> result) {
                  callback.onSuccess(result.stream().map(ColumnDto::getName).collect(toList()));
               }

               @Override
               public void onFailure(Throwable caught) {
                  callback.onFailure(caught);
               }

            });
         }

         @Override
         public void executeGetData(PagingLoadConfig loadConfig, String query,
               AsyncCallback<PagingLoadResult<ListStringBaseModel>> callback) {
            TableReportDto report = (TableReportDto) getSelectedNode();
            DatasourceContainerDto container = report.getDatasourceContainer();

            tableReportDao.loadData(report, container, loadConfig, query, callback);
         }

      });
   }

   private void addMetadataDatasourceField(Container fieldWrapper, boolean displayConfigFields) {
      metadataDatasourceFieldCreator = datasourceService.getSelectionField(fieldWrapper, displayConfigFields,
            datasourceTreeProvider);
      metadataDatasourceFieldCreator.setLabel(ReportmanagerMessages.INSTANCE.metadataDataSource());
      metadataDatasourceFieldCreator.addSelectionField();
      metadataDatasourceFieldCreator.addDisplayDefaultButton();
      metadataDatasourceFieldCreator.addSpecificDatasourceConfig(new DatabaseSpecificFieldConfigToolbar() {

         @Override
         public void enhance(ToolBar toolbar, final CodeMirrorPanel codeMirrorPanel) {
            /* add parameter */
            DwTextButton selectParamBtn = toolbarService
                  .createSmallButtonLeft(ReportmanagerMessages.INSTANCE.selectParamBtnLabel(), BaseIcon.TABLE_ADD);
            selectParamBtn.setArrowAlign(ButtonArrowAlign.RIGHT);
            toolbar.add(selectParamBtn);

            final Menu menu = new DwMenu();
            selectParamBtn.setMenu(menu);

            menu.addBeforeShowHandler(event -> {
               menu.clear();

               final Menu subMenuUser = new DwMenu();
               final MenuItem subMenuItemUser = new DwMenuItem(ReportmanagerMessages.INSTANCE.currentUser());
               menu.add(subMenuItemUser);

               final Menu subMenuReport = new DwMenu();
               final MenuItem subMenuItemReport = new DwMenuItem(ReportmanagerMessages.INSTANCE.currentReport());
               menu.add(subMenuItemReport);

               final Menu subMenuLocale = new DwMenu();
               final MenuItem subMenuItemLocale = new DwMenuItem("Locale");
               menu.add(subMenuItemLocale);

               final Menu subMenuGlobalConstants = new DwMenu();
               final MenuItem subMenuItemGlobalConstants = new DwMenuItem(
                     GlobalConstantsMessages.INSTANCE.securityTitle());
               menu.add(subMenuItemGlobalConstants);

               final Menu subMenuMetadata = new DwMenu();
               final MenuItem subMenuItemMetadata = new DwMenuItem(ReportMetadataMessages.INSTANCE.header());
               menu.add(subMenuItemMetadata);

               addSpecialParameter(codeMirrorPanel, subMenuUser, subMenuItemUser, subMenuReport, subMenuItemReport,
                     subMenuLocale, subMenuItemLocale, subMenuGlobalConstants, subMenuItemGlobalConstants,
                     subMenuMetadata, subMenuItemMetadata, null, null, metadataDatasourceFieldCreator);
            });
         }
      });

      metadataDatasourceFieldCreator.addSpecificDatasourceConfig(new DatabaseSpecificFieldConfigExecution() {
         @Override
         public void executeGetColumns(final String query, final AsyncCallback<List<String>> callback) {

            TableReportDto report = (TableReportDto) getSelectedNode();
            tableReportDao.loadColumnDefinition(report, metadataDatasourceFieldCreator.getDatasourceContainer(), query,
                  null, new RsAsyncCallback<List<ColumnDto>>() {
                     @Override
                     public void onSuccess(List<ColumnDto> result) {
                        callback.onSuccess(result.stream().map(ColumnDto::getName).collect(toList()));
                     }

                     @Override
                     public void onFailure(Throwable caught) {
                        callback.onFailure(caught);
                     }

                  });

         }

         @Override
         public void executeGetData(final PagingLoadConfig loadConfig, final String query,
               final AsyncCallback<PagingLoadResult<ListStringBaseModel>> callback) {

            TableReportDto report = (TableReportDto) getSelectedNode();
            DatasourceContainerDto container = metadataDatasourceFieldCreator.getDatasourceContainer();

            tableReportDao.loadData(report, container, loadConfig, query, callback);

         }
      });
   }

}
