package net.datenwerke.rs.grideditor.client.grideditor.ui;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Style.WhiteSpace;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.SortInfo;
import com.sencha.gxt.data.shared.Store.Record;
import com.sencha.gxt.data.shared.Store.StoreSortInfo;
import com.sencha.gxt.data.shared.loader.DataProxy;
import com.sencha.gxt.data.shared.loader.ListLoadConfig;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoadResultBean;
import com.sencha.gxt.data.shared.loader.ListLoader;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.Status;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent.CellDoubleClickHandler;
import com.sencha.gxt.widget.core.client.event.CellMouseDownEvent;
import com.sencha.gxt.widget.core.client.event.CellMouseDownEvent.CellMouseDownHandler;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.DialogHideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.Field;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.IntegerField;
import com.sencha.gxt.widget.core.client.form.LongField;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.BigDecimalPropertyEditor;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.DoublePropertyEditor;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.HeaderGroupConfig;
import com.sencha.gxt.widget.core.client.grid.editing.AbstractGridEditing;
import com.sencha.gxt.widget.core.client.grid.editing.ClicksToEdit;
import com.sencha.gxt.widget.core.client.grid.editing.GridEditing;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.menu.SeparatorMenuItem;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar.PagingToolBarAppearance;
import com.sencha.gxt.widget.core.client.toolbar.SeparatorToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.form.DwNumberField;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwNorthSouthContainer;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwAlertMessageBox;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwConfirmMessageBox;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.grid.DwGridRowEditing;
import net.datenwerke.gxtdto.client.i18n.I18nToolsUIService;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.model.DwModel;
import net.datenwerke.gxtdto.client.ui.helper.grid.ExtendedKeyNav;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.gxtdto.client.utils.SqlTypes;
import net.datenwerke.gxtdto.client.utils.modelkeyprovider.BasicObjectModelKeyProvider;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorDao;
import net.datenwerke.rs.core.client.reportexecutor.locale.ReportexecutorMessages;
import net.datenwerke.rs.core.client.reportexecutor.ui.aware.CloseableAware;
import net.datenwerke.rs.core.client.reportexecutor.ui.preview.AbstractReportPreviewView;
import net.datenwerke.rs.core.client.reportexecutor.ui.preview.PageablePreviewView;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.grideditor.client.grideditor.GridEditorDao;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorColumnConfigDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorConfigDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorDataDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordEntryDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorReloadConfig;
import net.datenwerke.rs.grideditor.client.grideditor.dto.ValidatorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.EditorDtoDec;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorDataDtoDec;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorRecordDtoDec;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorRecordEntryDtoDec;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.ValidatorDtoDec;
import net.datenwerke.rs.grideditor.client.grideditor.locale.GridEditorMessages;
import net.datenwerke.rs.grideditor.client.grideditor.vp.RowValueProviderBoolean;
import net.datenwerke.rs.grideditor.client.grideditor.vp.RowValueProviderDate;
import net.datenwerke.rs.grideditor.client.grideditor.vp.RowValueProviderDecimal;
import net.datenwerke.rs.grideditor.client.grideditor.vp.RowValueProviderDouble;
import net.datenwerke.rs.grideditor.client.grideditor.vp.RowValueProviderFloat;
import net.datenwerke.rs.grideditor.client.grideditor.vp.RowValueProviderInteger;
import net.datenwerke.rs.grideditor.client.grideditor.vp.RowValueProviderLong;
import net.datenwerke.rs.grideditor.client.grideditor.vp.RowValueProviderString;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.dto.WriteDto;

public class GridEditorReportPreviewView extends AbstractReportPreviewView
      implements PageablePreviewView, CloseableAware {

   private final static PagingToolBarAppearance pagingAppearance = GWT
         .<PagingToolBarAppearance>create(PagingToolBarAppearance.class);

   private final GridEditorDao gridEditorDao;
   private final I18nToolsUIService i18nService;

   private List<GridEditorRecordDto> deletedRecords = new ArrayList<GridEditorRecordDto>();
   private List<GridEditorRecordDto> newRecords = new ArrayList<GridEditorRecordDto>();

   private final NumberField<Integer> pageField = new DwNumberField<Integer>(
         new NumberPropertyEditor.IntegerPropertyEditor());
   private DwContentPanel wrapper;

   private int currentPageNumber = 1;
   private SortInfo sortInfo;

   private List<ColumnConfig<GridEditorRecordDto, ?>> columns;

   private ListStore<GridEditorRecordDto> store;
   private GridEditorConfigDto editorConfig;
   private String executeToken;
   private AbstractGridEditing<GridEditorRecordDto> editing;
   private GridEditorDataDtoDec editorData;
   private Grid<GridEditorRecordDto> grid;
   private ToolBar statusbar;
   private boolean initial = true;

   private boolean preventReload = false;

   private Map<String, TextField> filterFields = new HashMap<String, TextField>();
   private Map<String, CheckBox> csFilterFields = new HashMap<String, CheckBox>();
   private int numberOfPages;
   private Status countDataSet;
   private Status countPages;
   private int maxId = 0;

   @Inject
   public GridEditorReportPreviewView(ReportExecutorDao rexDao, HookHandlerService hookHandler,
         I18nToolsUIService i18nService, GridEditorDao gridEditorDao) {
      super(rexDao, hookHandler);

      this.i18nService = i18nService;
      this.gridEditorDao = gridEditorDao;

      wrapper = DwContentPanel.newInlineInstance();
   }

   @Override
   protected void cancelExecution(String executeToken) {
   }

   /**
    * {@inheritDoc}
    */
   @Override
   protected void doLoadReport(DwModel reportExecutionResult) {
      if (reportExecutionResult instanceof GridEditorDataDto) {
         editorData = (GridEditorDataDtoDec) reportExecutionResult;
         editorConfig = editorData.getConfig();
         maxId = editorData.getDataRecords().size();

         numberOfPages = Math.max(1, ((editorConfig.getTotalNumberOfRecords() / getPageSize())
               + ((editorConfig.getTotalNumberOfRecords() % getPageSize()) == 0 ? 0 : 1)));

         if (initial) {
            /* clear wrapper before we potentially create a new grid */
            wrapper.clear();

            final Grid<GridEditorRecordDto> grid = createGrid();
            final ToolBar toolbar = createToolbar();
            final ToolBar statusBar = createStatusBar();

            DwNorthSouthContainer container = new DwNorthSouthContainer();
            container.setNorthWidget(toolbar);
            container.setWidget(grid);
            container.setSouthWidget(statusBar);

            wrapper.setWidget(container);

            initial = false;
         } else {
            store.clear();
            grid.getLoader().load();

            countDataSet
                  .clearStatus(ReportexecutorMessages.INSTANCE.records() + editorConfig.getTotalNumberOfRecords());

            if (null != countPages)
               countPages.clearStatus(ReportexecutorMessages.INSTANCE.pages() + numberOfPages);
         }
      }
   }

   protected ToolBar createStatusBar() {
      statusbar = new DwToolBar();

      if (editorConfig.isPaging()) {

         DwTextButton pagePrefBtn = new DwTextButton();
         pagePrefBtn.addSelectHandler(new SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
               if (currentPageNumber > 1) {
                  pageField.setValue(--currentPageNumber);
                  setPageNumber(currentPageNumber);
               }
            }
         });
         pagePrefBtn.setIcon(pagingAppearance.prev());
         statusbar.add(pagePrefBtn);

         pageField.setWidth(30);
         pageField.setValue(currentPageNumber);
         pageField.addValueChangeHandler(new ValueChangeHandler<Integer>() {
            @Override
            public void onValueChange(ValueChangeEvent<Integer> event) {
               if (null != pageField.getValue() && currentPageNumber != pageField.getValue().intValue()) {
                  setPageNumber(Math.max(0, Math.min(numberOfPages, pageField.getValue().intValue())));
               }
            }
         });

         statusbar.add(pageField);

         DwTextButton pageNextBtn = new DwTextButton();
         pageNextBtn.addSelectHandler(new SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
               if (numberOfPages == -1 || currentPageNumber < numberOfPages) {
                  pageField.setValue(++currentPageNumber);
                  setPageNumber(currentPageNumber);
               }
            }
         });
         pageNextBtn.setIcon(pagingAppearance.next());
         statusbar.add(pageNextBtn);

         countPages = new Status();
         countPages.setBorders(false);
         statusbar.add(countPages);
         countPages.clearStatus(ReportexecutorMessages.INSTANCE.pages() + numberOfPages);
      }

      countDataSet = new Status();
      countDataSet.setBorders(false);
      countDataSet.getElement().getStyle().setWhiteSpace(WhiteSpace.NOWRAP);
      countDataSet.clearStatus(ReportexecutorMessages.INSTANCE.records() + editorConfig.getTotalNumberOfRecords());

      statusbar.add(new FillToolItem());
      statusbar.add(countDataSet);

      return statusbar;
   }

   protected ToolBar createToolbar() {
      ToolBar toolbar = new DwToolBar();

      /* reload */
      DwTextButton reloadButton = new DwTextButton(BaseIcon.REFRESH);
      reloadButton.setToolTip(BaseMessages.INSTANCE.refresh());
      reloadButton.addSelectHandler(new SelectHandler() {
         @Override
         public void onSelect(SelectEvent event) {
            fullReload();
         }
      });
      toolbar.add(reloadButton);

      toolbar.add(new SeparatorToolItem());

      /* add column */
      if (editorConfig.isCanAddRecords()) {
         DwTextButton addButton = new DwTextButton(BaseMessages.INSTANCE.add(), BaseIcon.ADD);
         addButton.addSelectHandler(event -> {
            /* get new data and clone it */
            List<GridEditorRecordEntryDto> newRowData = new ArrayList<GridEditorRecordEntryDto>();

            int size = editorConfig.getColumnConfigs().size();
            for (int i = 0; i < size; i++) {
               GridEditorColumnConfigDto colConfigDef = editorConfig.getColumnConfigs().get(i);
               GridEditorRecordEntryDto defaultValue = colConfigDef.getDefaultValue();
               newRowData.add(((GridEditorRecordEntryDtoDec) defaultValue).copy());
            }

            int id = generateFreshId();
            GridEditorRecordDto newItem = new GridEditorRecordDtoDec();
            newItem.setId(id);
            newItem.setData(newRowData);
            newRecords.add(newItem);

            editing.cancelEditing();

            store.add(0, newItem);
            int row = store.indexOf(newItem);

            grid.getSelectionModel().select(row, false);
            grid.focus();
         });
         toolbar.add(addButton);
      }

      /* duplicate column */
      if (editorConfig.isCanDuplicateRecords()) {
         DwTextButton copyButton = new DwTextButton(BaseMessages.INSTANCE.duplicate(), BaseIcon.CLONE);
         copyButton.addSelectHandler(event -> duplicate(grid.getSelectionModel().getSelectedItems()));
         toolbar.add(copyButton);
      }

      if (editorConfig.isHasForm()) {
         DwTextButton editButton = new DwTextButton(BaseMessages.INSTANCE.edit(), BaseIcon.REPORT_GE);
         editButton.addSelectHandler(event -> {
            GridEditorRecordDto selectedItem = grid.getSelectionModel().getSelectedItem();
            if (null != selectedItem)
               displayForm(selectedItem);
         });
         toolbar.add(editButton);
      }

      toolbar.add(new SeparatorToolItem());

      /* delete column */
      if (editorConfig.isCanRemoveRecords()) {
         DwTextButton deleteButton = new DwTextButton(BaseMessages.INSTANCE.remove(), BaseIcon.DELETE);
         deleteButton
               .addSelectHandler(event -> grid.getSelectionModel().getSelectedItems().forEach(this::markRemoveRecord));
         toolbar.add(deleteButton);
      }

      toolbar.add(new FillToolItem());

      /* revert */
      DwTextButton revertButton = new DwTextButton(BaseMessages.INSTANCE.revert(), BaseIcon.REFRESH);
      revertButton.addSelectHandler(event -> revert());
      toolbar.add(revertButton);

      /* save */
      if (report.hasAccessRight(WriteDto.class)) {
         DwTextButton saveButton = new DwTextButton(BaseMessages.INSTANCE.save(), BaseIcon.CHECK);
         saveButton.addSelectHandler(event -> {
            if (editing.isEditing()) {
               if (editing.isErrorSummary())
                  return;
               editing.completeEditing();
            }
   
            wrapper.mask(BaseMessages.INSTANCE.storingMsg());
            List<GridEditorRecordDto> modified = new ArrayList<GridEditorRecordDto>();
            List<GridEditorRecordDto> modifiedOriginals = new ArrayList<GridEditorRecordDto>();
            for (Record r : store.getModifiedRecords()) {
               GridEditorRecordDto model = (GridEditorRecordDto) r.getModel();
               modified.add(model);
   
               /* clone model and add it */
               int id = model.getId();
               List<GridEditorRecordEntryDto> clonedData = new ArrayList<GridEditorRecordEntryDto>();
               for (int i = 0; i < model.getData().size(); i++)
                  clonedData.add(((GridEditorRecordEntryDtoDec) model.getData().get(i)).copy());
               GridEditorRecordDtoDec clone = new GridEditorRecordDtoDec();
               clone.setId(id);
               clone.setData(clonedData);
               modifiedOriginals.add(clone);
            }
   
            /* remove new from modified */
            modified = modified.stream().filter(rec -> !newRecords.contains(rec)).collect(Collectors.toList());
   
            /* commit changes */
            store.commitChanges();
   
            /* make server call */
            gridEditorDao.commitChanges(report, executeToken, modified, modifiedOriginals, deletedRecords, newRecords,
                  new RsAsyncCallback<Void>() {
                     @Override
                     public void onSuccess(Void result) {
                        /* unmask and reload */
                        wrapper.unmask();
                        deletedRecords.clear();
                        newRecords.clear();
                        reload();
                     }
   
                     @Override
                     public void onFailure(Throwable caught) {
                        wrapper.unmask();
                     }
                  });
         });
         toolbar.add(saveButton);
      }

      return toolbar;
   }

   protected void duplicate(List<GridEditorRecordDto> records) {
      if (null == records || records.isEmpty())
         return;

      records.forEach(this::duplicate);
   }

   protected void duplicate(GridEditorRecordDto record) {
      if (null == record)
         return;

      List<GridEditorRecordEntryDto> newRowData = new ArrayList<GridEditorRecordEntryDto>();

      int size = editorConfig.getColumnConfigs().size();
      for (int i = 0; i < size; i++) {
         GridEditorColumnConfigDto colConfigDef = editorConfig.getColumnConfigs().get(i);
         if (!colConfigDef.isPrimaryKey()) {
            GridEditorRecordEntryDto value = record.getData().get(i);
            newRowData.add(((GridEditorRecordEntryDtoDec) value).copy());
         } else {
            GridEditorRecordEntryDto defaultValue = colConfigDef.getDefaultValue();
            newRowData.add(((GridEditorRecordEntryDtoDec) defaultValue).copy());
         }
      }

      int id = generateFreshId();
      GridEditorRecordDto newItem = new GridEditorRecordDtoDec();
      newItem.setId(id);
      newItem.setData(newRowData);
      newRecords.add(newItem);

      editing.cancelEditing();

      int index = store.indexOf(record);
      store.add(index, newItem);

   }

   protected void fullReload() {
      /* sets initial to true */

      if (isModified()) {
         ConfirmMessageBox cmb = new DwConfirmMessageBox(GridEditorMessages.INSTANCE.discardChangesTitle(),
               GridEditorMessages.INSTANCE.discardChangesMsg());
         cmb.addDialogHideHandler(new DialogHideHandler() {
            @Override
            public void onDialogHide(DialogHideEvent event) {
               if (event.getHideButton() == PredefinedButton.YES) {
                  initial = true;
                  forceReload(1);
               }
            }
         });
         cmb.show();
      } else {
         initial = true;
         currentPageNumber = 1;
         super.reload();
      }
   }

   protected int generateFreshId() {
      return ++maxId;
   }

   protected void revert() {
      List<GridEditorRecordDto> records = new ArrayList<GridEditorRecordDto>(deletedRecords);
      deletedRecords.clear();
      store.rejectChanges();
      for (GridEditorRecordDto record : records)
         store.update(record);
   }

   protected void markRemoveRecord(GridEditorRecordDto record) {
      if (newRecords.contains(record)) {
         store.remove(record);
         newRecords.remove(record);
      } else {
         deletedRecords.add(record);
         store.update(record);
      }
   }

   protected boolean isModified() {
      return null != store
            && (!store.getModifiedRecords().isEmpty() || !deletedRecords.isEmpty() || !newRecords.isEmpty());
   }

   protected void clearModified() {
      deletedRecords.clear();
      newRecords.clear();
      store.clear();
   }

   protected Grid<GridEditorRecordDto> createGrid() {
      /* create data store */
      store = new ListStore<GridEditorRecordDto>(new BasicObjectModelKeyProvider<GridEditorRecordDto>());

      /* configure columns */
      columns = new ArrayList<ColumnConfig<GridEditorRecordDto, ?>>();

      /* create table */
      ColumnConfig<GridEditorRecordDto, GridEditorRecordDto> delColumn = new ColumnConfig<GridEditorRecordDto, GridEditorRecordDto>(
            new IdentityValueProvider<GridEditorRecordDto>("__icon"), 30);
      delColumn.setSortable(false);
      delColumn.setMenuDisabled(true);
      delColumn.setCell(new AbstractCell<GridEditorRecordDto>() {
         @Override
         public void render(com.google.gwt.cell.client.Cell.Context context, GridEditorRecordDto model,
               SafeHtmlBuilder sb) {
            if (deletedRecords.contains(model))
               sb.append(BaseIcon.DELETE.toSafeHtml());
            else if (newRecords.contains(model))
               sb.append(BaseIcon.ADD.toSafeHtml());
            else {
               Record record = store.getRecord(model);
               if (null != record && record.isDirty()) {
                  sb.append(BaseIcon.EDIT.toSafeHtml());
               }
            }
         }
      });
      columns.add(delColumn);

      int size = editorConfig.getColumnConfigs().size();
      for (int i = 0; i < size; i++) {
         GridEditorColumnConfigDto colConfigDef = editorConfig.getColumnConfigs().get(i);
         SafeHtmlBuilder header = new SafeHtmlBuilder().appendEscaped(colConfigDef.getDisplayName());

         /* name column */
         ColumnConfig<GridEditorRecordDto, ?> colConfig = getColumnConfig(i, header, colConfigDef);

         colConfig.setHorizontalHeaderAlignment(HorizontalAlignmentConstant.startOf(Direction.LTR));
         colConfig.setHorizontalAlignment(HorizontalAlignmentConstant.startOf(Direction.LTR));
         colConfig.setMenuDisabled(true);
         colConfig.setSortable(editorConfig.isSortable() && colConfigDef.isSortable());
         colConfig.setHidden(colConfigDef.isHidden());

         switch (colConfigDef.getType()) {
         case SqlTypes.SMALLINT:
         case SqlTypes.INTEGER:
         case SqlTypes.TINYINT:
         case SqlTypes.BIGINT:
         case SqlTypes.DECIMAL:
         case SqlTypes.NUMERIC:
            colConfig.setHorizontalAlignment(HorizontalAlignmentConstant.endOf(Direction.LTR));
            break;
         }

         columns.add(colConfig);
      }

      /* loader */
      ListLoader<ListLoadConfig, ListLoadResult<GridEditorRecordDto>> loader = new ListLoader<ListLoadConfig, ListLoadResult<GridEditorRecordDto>>(
            new DataProxy<ListLoadConfig, ListLoadResult<GridEditorRecordDto>>() {
               @Override
               public void load(ListLoadConfig loadConfig,
                     final Callback<ListLoadResult<GridEditorRecordDto>, Throwable> callback) {
                  if (store.getAll().isEmpty()) {
                     callback.onSuccess(new ListLoadResultBean<GridEditorRecordDto>(editorData.getDataRecords()));
                  } else {
                     if (!editorConfig.isPaging()) {
                        store.clearSortInfo();
                        SortInfo sortInfo = loadConfig.getSortInfo().get(0);
                        String field = sortInfo.getSortField();
                        int index = Integer.parseInt(field);

                        final ValueProvider<? super GridEditorRecordDto, String> vp = (ValueProvider<? super GridEditorRecordDto, String>) columns
                              .get(index).getValueProvider();
                        store.addSortInfo(new StoreSortInfo<GridEditorRecordDto>(vp, sortInfo.getSortDir()));
                     } else {
                        sortInfo = loadConfig.getSortInfo().get(0);
                        reload();
                     }
                  }
               }
            });
      if (editorConfig.isPaging())
         loader.setRemoteSort(true);
      loader.addLoadHandler(
            new LoadResultListStoreBinding<ListLoadConfig, GridEditorRecordDto, ListLoadResult<GridEditorRecordDto>>(
                  store));
      loader.load();

      /* filters */
      ColumnModel<GridEditorRecordDto> columnModel = new ColumnModel<GridEditorRecordDto>(columns);

      if (editorConfig.isFilterable()) {
         for (int i = 1; i < columns.size(); i++) {
            GridEditorColumnConfigDto colConfig = editorConfig.getColumnConfigs().get(i - 1);
            if (!colConfig.isFilterable())
               continue;

            final TextField filterField = new TextField();
            filterField.setAllowTextSelection(true);
            filterField.setWidth(Math.min(140, Math.max(50, colConfig.getWidth() - 40)));
            filterField.setAllowTextSelection(true);
            filterField.setAllowBlank(true);
            filterField.addChangeHandler(event -> reload());
            filterFields.put(colConfig.getName(), filterField);

            HorizontalLayoutContainer container = new HorizontalLayoutContainer();
            container.add(filterField, new HorizontalLayoutData(-1, -1, new Margins(0, 0, 25, 0)));
            container.setHeight(23);

            CheckBox csField = new CheckBox();
            csFilterFields.put(colConfig.getName(), csField);
            if (colConfig.isDefaultCaseSensitive() || colConfig.isEnforceCaseSensitivity())
               csField.setValue(true);
            csField.setWidth(10);
            csField.addChangeHandler(new ChangeHandler() {
               @Override
               public void onChange(ChangeEvent event) {
                  if (!"".equals(filterField.getCurrentValue()))
                     reload();
               }
            });
            if (!colConfig.isEnforceCaseSensitivity()) {
               container.add(new Label("Aa:"), new HorizontalLayoutData(-1, -1, new Margins(5, 3, 5, 5)));
               container.add(csField, new HorizontalLayoutData(-1, -1, new Margins(3, 0, 0, 0)));
            }

            HeaderGroupConfig hgc = new HeaderGroupConfig(container, 1, 1);
            hgc.setHorizontalAlignment(HorizontalAlignmentConstant.startOf(Direction.LTR));
            columnModel.addHeaderGroup(0, i, hgc);
         }

         HeaderGroupConfig hgc = new HeaderGroupConfig(BaseIcon.FILTER.toSafeHtml(), 1, 1);
         columnModel.addHeaderGroup(0, 0, hgc);
      }

      /* create grid */
      grid = new Grid<GridEditorRecordDto>(store, columnModel);
      grid.getSelectionModel().setSelectionMode(SelectionMode.MULTI);
      grid.getView().setStripeRows(true);
      grid.getView().setShowDirtyCells(true);
      grid.setBorders(true);
      // grid.getView().setAutoExpandColumn(columns.get(columns.size()-1));
      grid.setLoader(loader);
      grid.setColumnReordering(false);
      new ExtendedKeyNav(grid) {
         protected void onSelectAll() {
            grid.getSelectionModel().selectAll();
         };
      };

      grid.addCellMouseDownHandler(new CellMouseDownHandler() {
         @Override
         public void onCellMouseDown(CellMouseDownEvent event) {
            if (event.getEvent().getButton() == NativeEvent.BUTTON_RIGHT) {
               int cellIndex = event.getCellIndex();
               displayMenu(cellIndex, event.getRowIndex(), event);
            }
         }
      });

      grid.addCellDoubleClickHandler(new CellDoubleClickHandler() {
         @Override
         public void onCellClick(CellDoubleClickEvent event) {
            int index = event.getCellIndex();
            if (0 == index) {
               editing.cancelEditing();
               if (editorConfig.isHasForm())
                  displayForm(event.getRowIndex());
            }
         }
      });

      grid.addDomHandler(new KeyPressHandler() {

         @Override
         public void onKeyPress(KeyPressEvent event) {
            int charCode = event.getUnicodeCharCode();
            if (101 == charCode) {
               GridEditorRecordDto item = grid.getSelectionModel().getSelectedItem();
               if (null != item)
                  displayForm(item);
            }

         }
      }, KeyPressEvent.getType());

      /* editing */
      editing = new DwGridRowEditing<GridEditorRecordDto>(grid) {
         @Override
         protected void onEnter(NativeEvent evt) {
            if (isValid())
               completeEditing();
         };
      };
      editing.setClicksToEdit(ClicksToEdit.TWO);
      for (int i = 0; i < size; i++) {
         GridEditorColumnConfigDto colConfigDef = editorConfig.getColumnConfigs().get(i);
         configureEditor(colConfigDef, columns.get(i + 1), editing, true);
      }

      return grid;
   }

   protected void displayForm(int rowIndex) {
      GridEditorRecordDto record = store.get(rowIndex);
      displayForm(record);
   }

   protected void displayForm(final GridEditorRecordDto item) {
      if (null == item || editing.isEditing())
         return;

      /* construct window */
      final DwWindow editWindow = new DwWindow();
      editWindow.setModal(true);
      editWindow.setOnEsc(true);
      editWindow.setHeaderIcon(BaseIcon.REPORT_GE);
      editWindow.setHeading(GridEditorMessages.INSTANCE.editRecordHeading());

      /* size */
      int height = grid.getElement().getHeight(false);
      editWindow.setSize(550, Math.max(350, height));

      /* wrapper and layout */
      DwContentPanel wrapper = DwContentPanel.newInlineInstance();
      wrapper.setScrollMode(ScrollMode.AUTOY);
      editWindow.add(wrapper);

      VerticalLayoutContainer form = new VerticalLayoutContainer();
      wrapper.add(form, new MarginData(15));

      /* get record */
      Record record = store.getRecord(item);

      /* content */
      int size = editorConfig.getColumnConfigs().size();
      final List<DummyGridEditingCapturer> capturerList = new ArrayList<DummyGridEditingCapturer>();
      for (int i = 0; i < size; i++) {
         GridEditorColumnConfigDto colConfigDef = editorConfig.getColumnConfigs().get(i);
         ColumnConfig<GridEditorRecordDto, ?> column = columns.get(i + 1);

         if (colConfigDef.isHidden()) {
            capturerList.add(null);
            continue;
         }

         if (colConfigDef.isEditable()) {
            DummyGridEditingCapturer capturer = new DummyGridEditingCapturer();
            configureEditor(colConfigDef, column, capturer, false);
            capturerList.add(capturer);

            Field field = capturer.getField();
            FieldLabel label = new FieldLabel(field, colConfigDef.getDisplayName());
            label.setLabelWidth(220);
            form.add(label, new VerticalLayoutData(1, -1));

            /* set value */
            if (field != null) {
               Object value = record.getValue(column.getValueProvider());
               if (null != capturer.getConverter())
                  field.setValue(capturer.getConverter().convertModelValue(value));
               else
                  field.setValue(value);
            }
         } else {
            capturerList.add(null);

            TextField field = new TextField();
            field.setEnabled(false);
            Object value = record.getValue(column.getValueProvider());
            if (null != value)
               field.setValue(String.valueOf(value));
            FieldLabel label = new FieldLabel(field, colConfigDef.getDisplayName());
            label.setLabelWidth(220);
            form.add(label, new VerticalLayoutData(1, -1));
         }

      }

      /* buttons */
      DwTextButton cancel = new DwTextButton(BaseMessages.INSTANCE.cancel());
      editWindow.addButton(cancel);
      cancel.addSelectHandler(new SelectHandler() {
         @Override
         public void onSelect(SelectEvent event) {
            editWindow.hide();
         }
      });

      DwTextButton save = new DwTextButton(BaseMessages.INSTANCE.save());
      editWindow.addButton(save);
      save.addSelectHandler(new SelectHandler() {
         @Override
         public void onSelect(SelectEvent event) {
            /* validate */
            for (DummyGridEditingCapturer cap : capturerList) {
               if (null == cap)
                  continue;

               Field<?> field = cap.getField();
               if (field != null)
                  if (!field.validate())
                     return;
            }

            Record record = store.getRecord(item);
            int i = 1;
            for (DummyGridEditingCapturer cap : capturerList) {
               if (null == cap) {
                  i++;
                  continue;
               }

               Field<?> field = cap.getField();
               if (field != null) {
                  Object convertedValue = field.getValue();
                  if (null != cap.getConverter())
                     convertedValue = cap.getConverter().convertFieldValue(convertedValue);
                  record.addChange(columns.get(i++).getValueProvider(), convertedValue);
               }
            }
            /* close window */
            editWindow.hide();
         }
      });

      /* show window */
      editWindow.show();
   }

   protected void displayMenu(final int cellIndex, int rowIndex, CellMouseDownEvent event) {
      Menu menu = new DwMenu();

      if (editorConfig.isFilterable()) {
         MenuItem clearFilter = new DwMenuItem(GridEditorMessages.INSTANCE.clearFilters());
         menu.add(clearFilter);
         clearFilter.addSelectionHandler(selectionEvent -> {
            preventReload = true;
            filterFields.values().forEach(tf -> tf.setValue(null));
            preventReload = false;
            reload();
         });
         menu.add(new SeparatorMenuItem());
      }

      final GridEditorRecordDto record = store.get(rowIndex);

      /* set null */
      MenuItem setNull = new DwMenuItem(GridEditorMessages.INSTANCE.setNull());
      menu.add(setNull);
      setNull.addSelectionHandler(selectionHandler -> {
         Record r = store.getRecord(record);
         r.addChange(grid.getColumnModel().getColumn(cellIndex).getValueProvider(), null);
      });

      /* undelete and rollback */
      if (editorConfig.isCanDuplicateRecords()) {
         menu.add(new SeparatorMenuItem());

         MenuItem copyRow = new DwMenuItem(BaseMessages.INSTANCE.duplicate());
         menu.add(copyRow);
         copyRow.addSelectionHandler(selectionEvent -> duplicate(record));
      }

      if (editorConfig.isCanRemoveRecords()) {
         MenuItem deleteRow = new DwMenuItem(BaseMessages.INSTANCE.remove());
         menu.add(deleteRow);
         deleteRow.addSelectionHandler(selectionEvent -> markRemoveRecord(record));
      }

      /* undelete and rollback */
      menu.add(new SeparatorMenuItem());

      Record r = null;
      if (null != record)
         r = store.getRecord(record);

      MenuItem rollback = new DwMenuItem(GridEditorMessages.INSTANCE.rollbackRecord());
      if (null == r || (!r.isDirty() && !deletedRecords.contains(record)))
         rollback.disable();

      menu.add(rollback);
      rollback.addSelectionHandler(new SelectionHandler<Item>() {
         @Override
         public void onSelection(SelectionEvent<Item> event) {
            Record r = store.getRecord(record);
            r.revert();
            deletedRecords.remove(record);
            store.update(record);
         }
      });

      menu.showAt(event.getEvent().getClientX(), event.getEvent().getClientY());
   }

   protected void configureEditor(GridEditorColumnConfigDto colConfigDef,
         ColumnConfig<GridEditorRecordDto, ?> columnConfig, GridEditing<GridEditorRecordDto> editing,
         boolean forRowEditor) {
      if (!colConfigDef.isEditable() || colConfigDef.isHidden())
         return;

      Field field = null;
      if (null != colConfigDef.getEditor()) {
         EditorDtoDec editor = (EditorDtoDec) colConfigDef.getEditor();
         if (!forRowEditor || editor.isRowEditable())
            field = editor.addEditor(columnConfig, editing);
      } else {
         switch (colConfigDef.getType()) {
         case SqlTypes.BIT:
         case SqlTypes.BOOLEAN:
            field = new CheckBox();
            editing.addEditor((ColumnConfig<GridEditorRecordDto, Boolean>) columnConfig, field);
            break;
         case SqlTypes.CHAR:
         case SqlTypes.CLOB:
         case SqlTypes.LONGVARCHAR:
         case SqlTypes.VARCHAR:
         case SqlTypes.NVARCHAR:
         case SqlTypes.NCLOB:
         case SqlTypes.NCHAR:
         case SqlTypes.LONGNVARCHAR:
         case SqlTypes.SQLXML:
         case SqlTypes.OTHER:
            field = new TextField();
            editing.addEditor((ColumnConfig<GridEditorRecordDto, String>) columnConfig, field);
            break;

         case SqlTypes.BIGINT:
            field = new LongField();
            editing.addEditor((ColumnConfig<GridEditorRecordDto, Long>) columnConfig, field);
            break;
         case SqlTypes.SMALLINT:
         case SqlTypes.INTEGER:
         case SqlTypes.TINYINT:
            field = new IntegerField();
            editing.addEditor((ColumnConfig<GridEditorRecordDto, Integer>) columnConfig, field);
            break;
         case SqlTypes.DECIMAL:
         case SqlTypes.NUMERIC:
            BigDecimalPropertyEditor propEditor = new BigDecimalPropertyEditor() {
               @Override
               protected BigDecimal parseString(String value) {
                  value = i18nService.translateNumberFromUserToSystem(value);
                  return returnTypedValue(NumberFormat.getDecimalFormat().parse(value));
               }

               @Override
               public String render(Number value) {
                  String strValue = super.render(value);
                  strValue = i18nService.translateNumberFromSystemToUser(strValue);
                  return strValue;
               }
            };
            field = new DwNumberField<BigDecimal>(propEditor);
            ((NumberField) field).setDecimalSeparator(i18nService.getUserDecimalSeparator());
            ((NumberField) field).setAllowDecimals(true);
            editing.addEditor((ColumnConfig<GridEditorRecordDto, BigDecimal>) columnConfig, field);
            break;

         case SqlTypes.DATE:
         case SqlTypes.TIME:
         case SqlTypes.TIMESTAMP:
            field = new DateField();
            editing.addEditor((ColumnConfig<GridEditorRecordDto, Date>) columnConfig, field);
            break;

         case SqlTypes.DOUBLE:
            DoublePropertyEditor doublePropEditor = new DoublePropertyEditor() {
               @Override
               protected Double parseString(String value) {
                  value = i18nService.translateNumberFromUserToSystem(value);
                  return returnTypedValue(NumberFormat.getDecimalFormat().parse(value));
               }

               @Override
               public String render(Number value) {
                  String strValue = super.render(value);
                  strValue = i18nService.translateNumberFromSystemToUser(strValue);
                  return strValue;
               }
            };
            field = new DwNumberField<Double>(doublePropEditor);
            ((NumberField) field).setDecimalSeparator(i18nService.getUserDecimalSeparator());
            ((NumberField) field).setAllowDecimals(true);
            editing.addEditor((ColumnConfig<GridEditorRecordDto, Double>) columnConfig, field);
            break;
         case SqlTypes.FLOAT:
            DoublePropertyEditor floatPropEditor = new DoublePropertyEditor() {
               @Override
               protected Double parseString(String value) {
                  value = i18nService.translateNumberFromUserToSystem(value);
                  return returnTypedValue(NumberFormat.getDecimalFormat().parse(value));
               }

               @Override
               public String render(Number value) {
                  String strValue = super.render(value);
                  strValue = i18nService.translateNumberFromSystemToUser(strValue);
                  return strValue;
               }
            };
            field = new DwNumberField<Double>(floatPropEditor);
            ((NumberField) field).setDecimalSeparator(i18nService.getUserDecimalSeparator());
            ((NumberField) field).setAllowDecimals(true);
            editing.addEditor((ColumnConfig<GridEditorRecordDto, Float>) columnConfig, field);
            break;

         case SqlTypes.BLOB:
         case SqlTypes.LONGVARBINARY:
            break;

         }
      }

      /* validators */
      if (null != field) {
         for (ValidatorDto val : colConfigDef.getValidators()) {
            field.addValidator(((ValidatorDtoDec) val).getValidator());
         }
      }
   }

   protected ColumnConfig<GridEditorRecordDto, ?> getColumnConfig(int i, SafeHtmlBuilder header,
         GridEditorColumnConfigDto colConfigDef) {
      ColumnConfig<GridEditorRecordDto, ?> columnConfig = null;

      switch (colConfigDef.getType()) {
      case SqlTypes.BIT:
      case SqlTypes.BOOLEAN:
         columnConfig = new ColumnConfig<GridEditorRecordDto, Boolean>(new RowValueProviderBoolean(i),
               colConfigDef.getWidth(), header.toSafeHtml());
         break;
      case SqlTypes.CHAR:
      case SqlTypes.CLOB:
      case SqlTypes.LONGVARCHAR:
      case SqlTypes.VARCHAR:
      case SqlTypes.NVARCHAR:
      case SqlTypes.NCLOB:
      case SqlTypes.NCHAR:
      case SqlTypes.LONGNVARCHAR:
      case SqlTypes.SQLXML:
      case SqlTypes.OTHER:
      case SqlTypes.ROWID:
         columnConfig = new ColumnConfig<GridEditorRecordDto, String>(new RowValueProviderString(i),
               colConfigDef.getWidth(), header.toSafeHtml());
         break;

      case SqlTypes.BIGINT:
         columnConfig = new ColumnConfig<GridEditorRecordDto, Long>(new RowValueProviderLong(i),
               colConfigDef.getWidth(), header.toSafeHtml());
         break;
      case SqlTypes.INTEGER:
      case SqlTypes.SMALLINT:
      case SqlTypes.TINYINT:
         columnConfig = new ColumnConfig<GridEditorRecordDto, Integer>(new RowValueProviderInteger(i),
               colConfigDef.getWidth(), header.toSafeHtml());
         break;
      case SqlTypes.NUMERIC:
      case SqlTypes.DECIMAL:
         columnConfig = new ColumnConfig<GridEditorRecordDto, BigDecimal>(new RowValueProviderDecimal(i),
               colConfigDef.getWidth(), header.toSafeHtml());
         break;

      case SqlTypes.DATE:
      case SqlTypes.TIME:
      case SqlTypes.TIMESTAMP:
         columnConfig = new ColumnConfig<GridEditorRecordDto, Date>(new RowValueProviderDate(i),
               colConfigDef.getWidth(), header.toSafeHtml());
         break;

      case SqlTypes.DOUBLE:
         columnConfig = new ColumnConfig<GridEditorRecordDto, Double>(new RowValueProviderDouble(i),
               colConfigDef.getWidth(), header.toSafeHtml());
         break;
      case SqlTypes.FLOAT:
         columnConfig = new ColumnConfig<GridEditorRecordDto, Float>(new RowValueProviderFloat(i),
               colConfigDef.getWidth(), header.toSafeHtml());
         break;

      case SqlTypes.BLOB:
      case SqlTypes.LONGVARBINARY:
      default:
         columnConfig = new ColumnConfig<GridEditorRecordDto, String>(new RowValueProviderString(i),
               colConfigDef.getWidth(), header.toSafeHtml());
      }

      return columnConfig;
   }

   @Override
   public Widget doGetViewComponent() {
      return wrapper;
   }

   @Override
   public Request execute(ReportDto report, String executeToken, AsyncCallback<DwModel> callback) {
      GridEditorReloadConfig config = new GridEditorReloadConfig();
      config.setSortInfo(sortInfo);
      config.setPagenumber(currentPageNumber);

      for (String col : filterFields.keySet()) {
         String v = filterFields.get(col).getCurrentValue();
         Boolean cs = csFilterFields.get(col).getValue();
         if (null != v && !"".equals(v)) {
            config.addFilter(col, v, Boolean.TRUE.equals(cs));
         }
      }

      this.executeToken = executeToken;

      return reportExecutorDao.executeAs("GRID_EDITOR_GET_DATA", executeToken, report, config, callback);
   }

   @Override
   public void reload() {
      reload(1);
   }

   public void reload(final int pageNumber) {
      if (preventReload)
         return;

      if (isModified()) {
         ConfirmMessageBox cmb = new DwConfirmMessageBox(GridEditorMessages.INSTANCE.discardChangesTitle(),
               GridEditorMessages.INSTANCE.discardChangesMsg());
         cmb.addDialogHideHandler(new DialogHideHandler() {
            @Override
            public void onDialogHide(DialogHideEvent event) {
               if (event.getHideButton() == PredefinedButton.YES)
                  forceReload(pageNumber);
            }
         });
         cmb.show();
      } else {
         currentPageNumber = pageNumber;
         pageField.setValue(currentPageNumber);
         super.reload();
      }
   }

   public void forceReload(int pageNumber) {
      currentPageNumber = pageNumber;
      clearModified();
      super.reload();
   }

   @Override
   protected boolean isCreateStatusBar() {
      return false;
   }

   @Override
   public void setPageNumber(int number) {
      currentPageNumber = Math.max(1, number);
      reload(currentPageNumber);
   }

   @Override
   public int getPageNumber() {
      return currentPageNumber;
   }

   @Override
   public void makeAwareOfSelection() {
      if (report instanceof TableReportDto) {
         if (((TableReportDto) report).getColumns().isEmpty()) {
            new DwAlertMessageBox(ReportexecutorMessages.INSTANCE.failed(),
                  ReportexecutorMessages.INSTANCE.noColumnsSelected()).show();
            if (null != container)
               container.disable();
            return;
         }
      }

      currentPageNumber = 1;
      super.makeAwareOfSelection();
   }

   @Override
   public int getPageSize() {
      return null == editorConfig ? 100 : editorConfig.getDefaultPageSize();
   }

   public GridEditorConfigDto getEditorConfig() {
      return editorConfig;
   }

   @Override
   public boolean needToConfirmClose() {
      return isModified();
   }

}
