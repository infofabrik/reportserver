package net.datenwerke.rs.core.client.reportvariants.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.SortDir;
import com.sencha.gxt.data.shared.Store;
import com.sencha.gxt.data.shared.Store.StoreSortInfo;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.CellClickEvent;
import com.sencha.gxt.widget.core.client.event.CellClickEvent.CellClickHandler;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.DialogHideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.StoreFilterField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridSelectionModel;
import com.sencha.gxt.widget.core.client.info.DefaultInfoConfig;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.info.InfoConfig;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.menu.SeparatorMenuItem;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;
import com.sencha.gxt.widget.core.client.toolbar.SeparatorToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gf.client.managerhelper.mainpanel.AbstractEmbeddingTabbedComponentsView;
import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.btn.impl.DwRemoveButton;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwAlertMessageBox;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwConfirmMessageBox;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCAllowBlank;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCTeamSpace;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.objectinformation.ObjectInfoPanelService;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.gxtdto.client.utils.modelkeyprovider.DtoIdModelKeyProvider;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorDao;
import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorUIService;
import net.datenwerke.rs.core.client.reportexporter.ReportExporterUIService;
import net.datenwerke.rs.core.client.reportexporter.exporter.ReportExporter;
import net.datenwerke.rs.core.client.reportexporter.exporter.ReportExporter.ConfigurationFinishedCallback;
import net.datenwerke.rs.core.client.reportexporter.locale.ReportExporterMessages;
import net.datenwerke.rs.core.client.reportmanager.ReportManagerTreeLoaderDao;
import net.datenwerke.rs.core.client.reportmanager.ReportManagerTreeManagerDao;
import net.datenwerke.rs.core.client.reportmanager.dto.interfaces.ReportVariantDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.pa.ReportDtoPA;
import net.datenwerke.rs.core.client.reportmanager.locale.ReportmanagerMessages;
import net.datenwerke.rs.core.client.reportvariants.locale.ReportVariantsMessages;
import net.datenwerke.rs.reportdoc.client.ReportDocumentationUiService;
import net.datenwerke.rs.reportdoc.client.locale.ReportDocumentationMessages;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.locale.ScheduleAsFileMessages;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.TsDiskDao;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFolderDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskReportReferenceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.helper.simpleform.SFFCTsTeamSpaceSelector;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.locale.TsFavoriteMessages;
import net.datenwerke.security.client.security.dto.DeleteDto;
import net.datenwerke.security.client.security.dto.ExecuteDto;
import net.datenwerke.security.client.security.dto.WriteDto;
import net.datenwerke.security.client.treedb.dto.decorator.SecuredAbstractNodeDtoDec;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;
import net.datenwerke.treedb.client.treedb.locale.TreedbMessages;

public class ReportVariantsView extends AbstractEmbeddingTabbedComponentsView {

   public static final String VIEW_ID = "ReportVariantsView";

   private final ReportManagerTreeLoaderDao treeLoader;
   private final ReportManagerTreeManagerDao treeManager;
   private final ToolbarService toolbarService;
   private final ReportExecutorUIService reportExecutorService;
   private final ReportDocumentationUiService reportDocService;
   private final ReportExporterUIService reportExporterService;
   private final ReportManagerTreeLoaderDao reportLoaderDao;
   private final ReportExecutorDao reportExecutorDao;
   private final TsDiskDao diskDao;

   private final ObjectInfoPanelService objectInfoService;

   private ListStore<ReportDto> store;
   private Grid<ReportDto> grid;

   @Inject
   public ReportVariantsView(ReportManagerTreeLoaderDao treeLoader, ReportManagerTreeManagerDao treeManager,
         ToolbarService toolbarService, ReportExecutorUIService reportExecutorService,
         ReportDocumentationUiService reportDocService, TsDiskDao diskDao, ObjectInfoPanelService objectInfoService,
         ReportExporterUIService reportExporterService, ReportManagerTreeLoaderDao reportLoaderDao,
         ReportExecutorDao reportExecutorDao) {
      this.treeLoader = treeLoader;
      this.treeManager = treeManager;
      this.toolbarService = toolbarService;
      this.reportExecutorService = reportExecutorService;
      this.reportDocService = reportDocService;
      this.diskDao = diskDao;
      this.objectInfoService = objectInfoService;
      this.reportExporterService = reportExporterService;
      this.reportLoaderDao = reportLoaderDao;
      this.reportExecutorDao = reportExecutorDao;
   }

   @Override
   public String getViewId() {
      return VIEW_ID;
   }

   @Override
   public boolean isSticky() {
      return true;
   }

   @Override
   public ImageResource getIcon() {
      return BaseIcon.REPORT.toImageResource();
   }

   @Override
   public String getComponentHeader() {
      return ReportVariantsMessages.INSTANCE.header();
   }

   @Override
   public Widget getViewComponent(AbstractNodeDto selectedNode) {
      VerticalLayoutContainer nsContainer = new VerticalLayoutContainer();

      HorizontalLayoutContainer contentsContainer = new HorizontalLayoutContainer();

      /* prepare store */
      store = new ListStore<ReportDto>(new DtoIdModelKeyProvider());
      store.setAutoCommit(true);
      store.addSortInfo(new StoreSortInfo<ReportDto>(ReportDtoPA.INSTANCE.name(), SortDir.ASC));

      mask(BaseMessages.INSTANCE.loadingMsg());
      treeLoader.getChildren(selectedNode, false, null, null, new RsAsyncCallback<List<AbstractNodeDto>>() {
         @Override
         public void onSuccess(List<AbstractNodeDto> result) {
            unmask();
            if (null != result) {
               for (AbstractNodeDto node : result)
                  if (node instanceof ReportVariantDto)
                     store.add((ReportDto) node);
            }
         }
      });

      /* create grid */
      createGrid();

      VerticalLayoutContainer gridContainer = new VerticalLayoutContainer();
      gridContainer.setScrollMode(ScrollMode.AUTOY);
      gridContainer.add(grid, new VerticalLayoutData(-1, 1));

      contentsContainer.add(gridContainer, new HorizontalLayoutData(.35, 1));
      contentsContainer.add(tabsetContainer, new HorizontalLayoutData(.65, 500));

      /* create toolbar */
      ToolBar toolbar = createToolbar();

      nsContainer.add(toolbar, new VerticalLayoutData(1, 50));
      nsContainer.add(contentsContainer, new VerticalLayoutData(1, 1));

      DwContentPanel main = new DwContentPanel();
      main.setLightHeader();
      main.setHeading(ReportVariantsMessages.INSTANCE.header());
      main.add(nsContainer);
      main.setInfoText(ReportVariantsMessages.INSTANCE.description());

      VerticalLayoutContainer wrapper = new VerticalLayoutContainer();
      wrapper.add(main, new VerticalLayoutData(1, 1, new Margins(10)));

      return wrapper;
   }

   private ToolBar createToolbar() {
      ToolBar toolbar = new DwToolBar();

      /* filter */
      StoreFilterField<ReportDto> textFilter = new StoreFilterField<ReportDto>() {
         @Override
         protected boolean doSelect(Store<ReportDto> store, ReportDto parent, ReportDto item, String filter) {
            if (null == filter)
               return true;

            filter = filter.toLowerCase();

            return (null != item.getName() && item.getName().toLowerCase().contains(filter))
                  || (null != item.getId() && String.valueOf(item.getId()).startsWith(filter))
                  || (null != item.getKey() && item.getKey().toLowerCase().contains(filter))
                  || (null != item.getDescription() && item.getDescription().toLowerCase().contains(filter));
         }

      };
      textFilter.bind(store);

      /* add items to toolbar */
      toolbarService.addPlainToolbarItem(toolbar, BaseIcon.SEARCH);
      toolbar.add(textFilter);

      toolbar.add(new SeparatorToolItem());

      /* open */
      DwTextButton execBtn = new DwTextButton(ReportmanagerMessages.INSTANCE.execute(), BaseIcon.EXECUTE);
      toolbar.add(execBtn);
      execBtn.addSelectHandler(new SelectHandler() {
         @Override
         public void onSelect(SelectEvent event) {
            if (null != grid.getSelectionModel().getSelectedItem())
               reportExecutorService.executeReport(grid.getSelectionModel().getSelectedItem());
         }
      });

      /* documentation */
      DwTextButton showDocumentationBtn = new DwTextButton(ReportDocumentationMessages.INSTANCE.berichtsdokuHelpText(),
            BaseIcon.BOOK);
      toolbar.add(showDocumentationBtn);
      showDocumentationBtn.addSelectHandler(new SelectHandler() {
         @Override
         public void onSelect(SelectEvent event) {
            if (null != grid.getSelectionModel().getSelectedItem())
               reportDocService.openDocumentationForopen(grid.getSelectionModel().getSelectedItem());
         }
      });

      toolbar.add(new SeparatorToolItem());

      DwTextButton importTsBtn = new DwTextButton(TsFavoriteMessages.INSTANCE.importVariantIntoTeamSpaceLabel(),
            BaseIcon.GROUP);
      importTsBtn.addSelectHandler(new SelectHandler() {

         @Override
         public void onSelect(SelectEvent event) {
            if (null != grid.getSelectionModel().getSelectedItem())
               importVariantIntoTeamSpace(grid.getSelectionModel().getSelectedItem());
         }
      });
      toolbar.add(importTsBtn);

      toolbar.add(new FillToolItem());

      DwRemoveButton rmvBtn = new DwRemoveButton() {
         @Override
         protected void onRemove() {
            doDeleteSelectedVariant();
         }

         @Override
         protected boolean canRemoveSingle() {
            return grid.getSelectionModel().getSelectedItem() != null;
         }

         @Override
         public String getRemoveConfirmMessage() {
            ReportDto selected = grid.getSelectionModel().getSelectedItem();
            if (null != selected)
               return ReportmanagerMessages.INSTANCE
                     .deleteVariantConfirmMessage(selected.getName() + " (" + selected.getId() + ")");
            throw new IllegalStateException();
         }
      };
      rmvBtn.disableRemoveAll();
      rmvBtn.setConfirmSingleItem(true);
      rmvBtn.setProtectSingleItem(true);

      toolbar.add(rmvBtn);

      return toolbar;
   }

   private void doDeleteSelectedVariant() {
      final ReportDto selected = grid.getSelectionModel().getSelectedItem();

      ReportVariantsView.this.mask(BaseMessages.INSTANCE.storingMsg());
      treeManager.deleteNodeAndAskForMoreForce(selected, new RsAsyncCallback<Boolean>() {
         @Override
         public void onSuccess(Boolean result) {
            ReportVariantsView.this.unmask();
            if (Boolean.TRUE.equals(result)) {
               store.remove(selected);
               Info.display(BaseMessages.INSTANCE.changesApplied(), TreedbMessages.INSTANCE.deleted());
            }
         }

         @Override
         public void onFailure(Throwable caught) {
            super.onFailure(caught);
            ReportVariantsView.this.unmask();
         }
      });
   }

   protected void importVariantIntoTeamSpace(final ReportDto report) {
      final DwWindow window = DwWindow.newAutoSizeDialog(340);
      window.setHeading(TsFavoriteMessages.INSTANCE.importVariantHookName());
      window.setModal(true);
      window.setWidth(340);
      window.setHeight(530);

      final SimpleForm form = SimpleForm.getInlineInstance();

      final String teamSpaceKey = form.addField(TeamSpaceDto.class, TsFavoriteMessages.INSTANCE.selectTeamspaceLabel(),
            new SFFCTeamSpace() {
               @Override
               public boolean isMulti() {
                  return false;
               }

               @Override
               public boolean isLoadAll() {
                  return true;
               }
            });

      final String folderKey = form.addField(TsDiskFolderDto.class, ScheduleAsFileMessages.INSTANCE.folder(),
            new SFFCTsTeamSpaceSelector() {
               @Override
               public TeamSpaceDto getTeamSpace() {
                  return (TeamSpaceDto) form.getValue(teamSpaceKey);
               }
            });

      final String duplicateVariant = form.addField(Boolean.class, TsFavoriteMessages.INSTANCE.duplicateLabel());

      final String nameKey = form.addField(String.class, ScheduleAsFileMessages.INSTANCE.nameLabel(),
            new SFFCAllowBlank() {
               @Override
               public boolean allowBlank() {
                  return false;
               }
            });
      final String descKey = form.addField(String.class, ScheduleAsFileMessages.INSTANCE.descriptionLabel(),
            new SFFCTextAreaImpl());

      /* set properties */
      String description = null;
      if (null != report.getDescription() && !"".contentEquals(report.getDescription().trim()))
         description = report.getDescription();
      else if (null != report.getParentReportDescription()
            && !"".contentEquals(report.getParentReportDescription().trim())) {
         description = report.getParentReportDescription();
      }
      form.setValue(nameKey, report.getName());
      form.setValue(descKey, description);

      form.loadFields();

      window.add(form, new MarginData(10));

      DwTextButton submit = new DwTextButton(BaseMessages.INSTANCE.submit());
      window.addButton(submit);
      submit.addSelectHandler(new SelectHandler() {
         @Override
         public void onSelect(SelectEvent event) {
            if (!form.isValid())
               return;

            TeamSpaceDto teamSpace = (TeamSpaceDto) form.getValue(teamSpaceKey);
            String name = (String) form.getValue(nameKey);
            String description = (String) form.getValue(descKey);
            AbstractTsDiskNodeDto folder = (AbstractTsDiskNodeDto) form.getValue(folderKey);

            if (null == folder) {
               new DwAlertMessageBox(BaseMessages.INSTANCE.error(), ScheduleAsFileMessages.INSTANCE.noFolderSelected())
                     .show();
               return;
            }

            if (null == teamSpace)
               return;

            final boolean duplicate = (Boolean) form.getValue(duplicateVariant);

            window.mask(BaseMessages.INSTANCE.storingMsg());
            diskDao.importReport(teamSpace, (folder instanceof TsDiskFolderDto ? (TsDiskFolderDto) folder : null),
                  report, duplicate, name, description, false, new RsAsyncCallback<TsDiskReportReferenceDto>() {
                     @Override
                     public void onSuccess(TsDiskReportReferenceDto result) {
                        window.hide();
                        if (duplicate && null != result)
                           store.add(result.getReport());
                     }

                     @Override
                     public void onFailure(Throwable caught) {
                        window.unmask();
                     }
                  });
         }
      });

      window.show();
   }

   private void createGrid() {
      /* configure columns */
      List<ColumnConfig<ReportDto, ?>> columns = new ArrayList<ColumnConfig<ReportDto, ?>>();

      /* id column */
      ColumnConfig<ReportDto, Long> idConfig = new ColumnConfig<ReportDto, Long>(ReportDtoPA.INSTANCE.id(), 80,
            BaseMessages.INSTANCE.id());
      idConfig.setMenuDisabled(true);

      columns.add(idConfig);

      /* name column */
      ColumnConfig<ReportDto, String> nameConfig = new ColumnConfig<ReportDto, String>(ReportDtoPA.INSTANCE.name(), 200,
            BaseMessages.INSTANCE.name());
      nameConfig.setMenuDisabled(true);

      columns.add(nameConfig);

      /* key column */
      ColumnConfig<ReportDto, String> keyConfig = new ColumnConfig<ReportDto, String>(ReportDtoPA.INSTANCE.key(), 150,
            BaseMessages.INSTANCE.key());
      keyConfig.setMenuDisabled(true);

      columns.add(keyConfig);

      /* create grid */
      grid = new Grid<ReportDto>(store, new ColumnModel<ReportDto>(columns));
      grid.setSelectionModel(new GridSelectionModel<ReportDto>());
      grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
      grid.getView().setShowDirtyCells(true);
      grid.setContextMenu(new DwMenu());
      grid.addBeforeShowContextMenuHandler(event -> grid.setContextMenu(generateContextMenu()));

      grid.addCellClickHandler(new CellClickHandler() {

         @Override
         public void onCellClick(CellClickEvent event) {
            final ReportDto reportDto = store.get(event.getRowIndex());
            if (null != reportDto) {
               mask(BaseMessages.INSTANCE.loadingMsg());
               treeLoader.loadFullViewNode(reportDto, new RsAsyncCallback<AbstractNodeDto>() {

                  @Override
                  public void onSuccess(AbstractNodeDto fullNode) {
                     unmask();
                     createTabsetWidget(fullNode);
                  }
               });
            }
         }
      });

   }

   private Menu generateContextMenu() {
      Menu contextMenu = new Menu();

      contextMenu.add(generateExecuteReportItem());
      contextMenu.add(new SeparatorMenuItem());
      contextMenu.add(generateTestReportMenuItem());
      contextMenu.add(new SeparatorMenuItem());
      contextMenu.add(generateExportItem());
      contextMenu.add(new SeparatorMenuItem());
      AbstractNodeDto selectedItem = getSelectedNode();
      boolean enableDelete = (!(selectedItem instanceof SecuredAbstractNodeDtoDec)
            || !((SecuredAbstractNodeDtoDec) selectedItem).isAccessRightsLoaded()
            || (((SecuredAbstractNodeDtoDec) selectedItem).hasAccessRight(DeleteDto.class)
                  && ((SecuredAbstractNodeDtoDec) selectedItem).hasAccessRight(WriteDto.class)
                  && ((SecuredAbstractNodeDtoDec) selectedItem).hasAccessRight(ExecuteDto.class)));
      MenuItem deleteItem = generateDeleteReportMenuItem();
      contextMenu.add(deleteItem);
      if (! enableDelete) 
         deleteItem.disable();
      
      contextMenu.add(new SeparatorMenuItem());
      contextMenu.add(generateInfoMenuItem());
      contextMenu.add(generateImportIntoTsMenuItem());

      return contextMenu;
   }

   private MenuItem generateTestReportMenuItem() {
      MenuItem testMenuItem = new MenuItem();
      testMenuItem.setText(BaseMessages.INSTANCE.test());
      testMenuItem.setIcon(BaseIcon.REPORT.toImageResource());
      testMenuItem.addSelectionHandler(event -> {
         ReportDto reportDto = grid.getSelectionModel().getSelectedItem();
         if (null == reportDto)
            return;

         InfoConfig infoConfig = new DefaultInfoConfig(ReportExporterMessages.INSTANCE.reportIsBeingExportedTitle(),
               ReportExporterMessages.INSTANCE.reportIsBeingExportedMsg("TEST"));
         infoConfig.setWidth(350);
         infoConfig.setDisplay(3500);
         Info.display(infoConfig);

         reportDocService.openVariantTestForopen(reportDto, new ArrayList<DatasourceDefinitionDto>());
      });
      return testMenuItem;
   }

   private MenuItem generateExportItem() {
      final MenuItem exportItem = new DwMenuItem(ReportExporterMessages.INSTANCE.exportReport(),
            BaseIcon.PLAY_CIRCLE_O);
      final Menu subMenu = new DwMenu();
      exportItem.disable();
      exportItem.setSubMenu(subMenu);

      final ReportDto reportDto = grid.getSelectionModel().getSelectedItem();
      if (null == reportDto)
         return exportItem;

      /* load reference to ensure we have properties */
      reportLoaderDao.loadNode(reportDto, new RsAsyncCallback<AbstractNodeDto>() {
         @Override
         public void onSuccess(AbstractNodeDto result) {
            super.onSuccess(result);

            if (!(result instanceof ReportDto))
               return;

            for (final ReportExporter exporter : reportExporterService
                  .getCleanedUpAvailableExporters((ReportDto) result)) {
               MenuItem subExportItem = new DwMenuItem(exporter.getExportTitle(), exporter.getIcon());
               subMenu.add(subExportItem);
               subExportItem.addSelectionHandler(event -> {
                  exporter.displayConfiguration(reportDto, null, true, new ConfigurationFinishedCallback() {
                     @Override
                     public void success() {
                        reportExecutorDao.loadFullReportForExecution(reportDto, new RsAsyncCallback<ReportDto>() {
                           @Override
                           public void onSuccess(ReportDto result) {
                              exporter.export(result, null);
                           }
                        });
                     }
                  });
               });
            }

            if (subMenu.getWidgetCount() > 0)
               exportItem.enable();

         }

      });

      return exportItem;
   }

   private MenuItem generateDeleteReportMenuItem() {
      MenuItem deleteReportItem = new MenuItem();
      deleteReportItem.setText(BaseMessages.INSTANCE.remove());
      deleteReportItem.setIcon(BaseIcon.DELETE.toImageResource());
      deleteReportItem.addSelectionHandler(new SelectionHandler<Item>() {
         @Override
         public void onSelection(SelectionEvent<Item> event) {
            ReportDto node = grid.getSelectionModel().getSelectedItem();
            /* confirm delete */
            ConfirmMessageBox cmb = new DwConfirmMessageBox(BaseMessages.INSTANCE.confirmDeleteTitle(),
                  BaseMessages.INSTANCE.confirmDeleteMsg(node.toDisplayTitle()));

            cmb.addDialogHideHandler(new DialogHideHandler() {

               @Override
               public void onDialogHide(DialogHideEvent event) {
                  if (event.getHideButton() == PredefinedButton.YES) {
                     /* delete */
                     doDeleteSelectedVariant();
                  }
               }
            });

            cmb.show();
         }
      });

      return deleteReportItem;
   }

   private MenuItem generateImportIntoTsMenuItem() {
      MenuItem importReportItem = new MenuItem();
      importReportItem.setText(TsFavoriteMessages.INSTANCE.importVariantIntoTeamSpaceLabel());
      importReportItem.addSelectionHandler(event -> {
         ReportDto node = grid.getSelectionModel().getSelectedItem();
         if (null != node)
            importVariantIntoTeamSpace(node);
      });

      return importReportItem;
   }

   private MenuItem generateExecuteReportItem() {
      MenuItem executeReportItem = new MenuItem();
      executeReportItem.setText(ReportmanagerMessages.INSTANCE.execute());
      executeReportItem.setIcon(BaseIcon.EXECUTE.toImageResource());

      executeReportItem.addSelectionHandler(event -> {
         ReportDto node = grid.getSelectionModel().getSelectedItem();
         if (node instanceof ReportDto)
            reportExecutorService.executeReport((ReportDto) node);
      });

      return executeReportItem;
   }

   private MenuItem generateInfoMenuItem() {
      MenuItem infoMenuItem = new MenuItem();
      infoMenuItem.setText(TreedbMessages.INSTANCE.infoMenuLabel());
      infoMenuItem.setIcon(BaseIcon.INFO.toImageResource());
      infoMenuItem.addSelectionHandler(event -> {
         ReportDto node = grid.getSelectionModel().getSelectedItem();
         objectInfoService.displayInformationOn(node);
      });

      return infoMenuItem;
   }

   @Override
   protected void recordUpdated(Object boundModel) {
      store.update((ReportDto) boundModel);
   }

}