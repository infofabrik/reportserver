package net.datenwerke.rs.base.client.reportengines.table.cubeconfig;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.util.Format;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.SortDir;
import com.sencha.gxt.data.shared.event.StoreAddEvent;
import com.sencha.gxt.data.shared.event.StoreClearEvent;
import com.sencha.gxt.data.shared.event.StoreDataChangeEvent;
import com.sencha.gxt.data.shared.event.StoreFilterEvent;
import com.sencha.gxt.data.shared.event.StoreHandlers;
import com.sencha.gxt.data.shared.event.StoreRecordChangeEvent;
import com.sencha.gxt.data.shared.event.StoreRemoveEvent;
import com.sencha.gxt.data.shared.event.StoreSortEvent;
import com.sencha.gxt.data.shared.event.StoreUpdateEvent;
import com.sencha.gxt.dnd.core.client.DND.Feedback;
import com.sencha.gxt.dnd.core.client.DndDropEvent;
import com.sencha.gxt.dnd.core.client.DropTarget;
import com.sencha.gxt.dnd.core.client.GridDragSource;
import com.sencha.gxt.dnd.core.client.GridDropTarget;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.DialogHideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.event.StartEditEvent;
import com.sencha.gxt.widget.core.client.event.StartEditEvent.StartEditHandler;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridSelectionModel;
import com.sencha.gxt.widget.core.client.grid.editing.GridEditing;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.menu.SeparatorMenuItem;
import com.sencha.gxt.widget.core.client.tips.QuickTip;
import com.sencha.gxt.widget.core.client.toolbar.SeparatorToolItem;

import net.datenwerke.gxtdto.client.baseex.widget.btn.DwSplitButton;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwNorthSouthContainer;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwAlertMessageBox;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwConfirmMessageBox;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.forms.selection.SelectionMode;
import net.datenwerke.gxtdto.client.forms.selection.SelectionPopup;
import net.datenwerke.gxtdto.client.ui.helper.grid.ExtendedKeyNav;
import net.datenwerke.gxtdto.client.utilityservices.grid.GridHelperService;
import net.datenwerke.gxtdto.client.utilityservices.grid.GridHelperService.CCContainer;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.gxtdto.client.utils.SqlTypes;
import net.datenwerke.gxtdto.client.utils.modelkeyprovider.DtoIdModelKeyProvider;
import net.datenwerke.rs.base.client.reportengines.table.TableReportUtilityDao;
import net.datenwerke.rs.base.client.reportengines.table.columnfilter.locale.FilterMessages;
import net.datenwerke.rs.base.client.reportengines.table.dto.AggregateFunctionDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnReferenceDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnDtoDec;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatDtoDec;
import net.datenwerke.rs.base.client.reportengines.table.dto.pa.ColumnDtoPA;
import net.datenwerke.rs.base.client.reportengines.table.helpers.ColumnFormatWindow;
import net.datenwerke.rs.base.client.reportengines.table.helpers.ColumnSelector;
import net.datenwerke.rs.base.client.reportengines.table.locale.TableMessages;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorMainPanelView;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class CubeConfigView extends ReportExecutorMainPanelView {

   public static final String VIEW_ID = "cubeconfig";

   private final TableReportUtilityDao tableReportUtilityDao;
   private final ToolbarService toolbarService;
   private final GridHelperService gridHelperService;
   private final SqlTypes sqlTypes;

   private TableReportDto report;
   private DwToolBar toolbar;
   private Grid<ColumnDto> grid;
   private ListStore<ColumnDto> store;

   private boolean doNotProcessChangeEvents = false;

   @Inject
   public CubeConfigView(TableReportUtilityDao tableReportUtilityDao, ToolbarService toolbarService,
         GridHelperService gridHelperService, SqlTypes sqlTypes) {

      /* store objects */
      this.tableReportUtilityDao = tableReportUtilityDao;
      this.toolbarService = toolbarService;
      this.gridHelperService = gridHelperService;
      this.sqlTypes = sqlTypes;
   }

   @Override
   public String getViewId() {
      return VIEW_ID;
   }

   @Override
   public boolean wantsToBeDefault() {
      return true;
   }

   public void setReport(TableReportDto tableReport) {
      this.report = tableReport;
   }

   @Override
   public String getComponentHeader() {
      return TableMessages.INSTANCE.cubeConfigAspectHeader();
   }

   @Override
   public Widget getViewComponent() {
      createToolbar();
      createGrid();

      DwNorthSouthContainer contentPanel = new DwNorthSouthContainer();
      contentPanel.setNorthWidget(toolbar);
      contentPanel.setWidget(grid);

      return contentPanel;
   }

   private void createToolbar() {
      toolbar = new DwToolBar();
      toolbar.setBorders(false);

      DwTextButton selectColumnsButton = toolbarService.createSmallButtonLeft(TableMessages.INSTANCE.selectMeasures(),
            BaseIcon.TABLE_ADD);

      toolbar.add(selectColumnsButton);

      selectColumnsButton.addSelectHandler(new SelectHandler() {
         SelectionPopup<ColumnDto> columnSelector;

         @Override
         public void onSelect(SelectEvent event) {

            if (null != columnSelector) {
               columnSelector.setSelectedItems(store.getAll());
               columnSelector.show();
               columnSelector.forceLayout();
               return;
            }

            columnSelector = new ColumnSelector(tableReportUtilityDao, report, getExecuteReportToken()) {
               @Override
               protected void itemsSelected(List<ColumnDto> selectedItems) {
                  boolean oldValue = doNotProcessChangeEvents;
                  doNotProcessChangeEvents = true;
                  try {
                     store.clear();

                     List<ColumnDto> newStoreList = new ArrayList<ColumnDto>();
                     for (ColumnDto col : selectedItems)
                        newStoreList.add(((ColumnDtoDec) col).cloneColumnForSelection());

                     store.addAll(newStoreList);
                  } finally {
                     doNotProcessChangeEvents = oldValue;
                  }
               }
            };

            columnSelector.show();
            columnSelector.loadData();
            columnSelector.setSelectionMode(SelectionMode.MULTI_PERMIT_DOUBLES);
            columnSelector.setSelectedItems(store.getAll());
         }
      });

      DwSplitButton deleteColumnButton = new DwSplitButton(FilterMessages.INSTANCE.removeColumn());
      deleteColumnButton.setIcon(BaseIcon.DELETE);
      deleteColumnButton.addSelectHandler(new SelectHandler() {
         @Override
         public void onSelect(SelectEvent event) {
            removeSelectedItems();
         }
      });
      Menu delMenu = new DwMenu();
      deleteColumnButton.setMenu(delMenu);
      MenuItem deleteAllColumnsItem = new DwMenuItem(FilterMessages.INSTANCE.removeAllColumns(), BaseIcon.DELETE);
      deleteAllColumnsItem.addSelectionHandler(new SelectionHandler<Item>() {
         @Override
         public void onSelection(SelectionEvent<Item> event) {
            store.clear();
         }
      });
      delMenu.add(deleteAllColumnsItem);
      toolbar.add(deleteColumnButton);

      toolbar.add(new SeparatorToolItem());

      /* */
      DwTextButton editFormat = toolbarService.createSmallButtonLeft(FilterMessages.INSTANCE.editColumnFormat(),
            BaseIcon.FORMAT);
      editFormat.addSelectHandler(new SelectHandler() {
         @Override
         public void onSelect(SelectEvent event) {
            displayColumnFormatDialog();
         }
      });
      toolbar.add(editFormat);

   }

   private void createGrid() {
      store = new ListStore<ColumnDto>(new DtoIdModelKeyProvider());
      store.setAutoCommit(true);

      final TableReportDto tableReport = (TableReportDto) report;
      store.addAll(tableReport.getColumns());

      store.addStoreHandlers(new StoreHandlers<ColumnDto>() {

         @Override
         public void onSort(StoreSortEvent<ColumnDto> event) {
         }

         @Override
         public void onRecordChange(StoreRecordChangeEvent<ColumnDto> event) {
         }

         @Override
         public void onDataChange(StoreDataChangeEvent<ColumnDto> event) {
         }

         @Override
         public void onUpdate(StoreUpdateEvent<ColumnDto> event) {
            reloadColumnsFromReport();
            grid.getView().refresh(false);
         }

         @Override
         public void onClear(StoreClearEvent<ColumnDto> event) {
            reloadColumnsFromReport();
         }

         @Override
         public void onFilter(StoreFilterEvent<ColumnDto> event) {
         }

         @Override
         public void onRemove(StoreRemoveEvent<ColumnDto> event) {
            reloadColumnsFromReport();
         }

         @Override
         public void onAdd(StoreAddEvent<ColumnDto> event) {
            reloadColumnsFromReport();
         }

      });

      /* listen to report change events */
      report.addInstanceChangedHandler(new ObjectChangedEventHandler<Dto>() {
         @Override
         public void onObjectChangedEvent(ObjectChangedEvent<Dto> event) {
            if (!doNotProcessChangeEvents
                  && TableReportDto.getColumnsPropertyAccessor().getPath().equals(event.getPropertyPath())) {
               List<ColumnDto> cols = new ArrayList(report.getColumns());
               store.clear();
               store.addAll(cols);
            }
         }
      });

      /* add Columns */
      List<ColumnConfig<ColumnDto, ?>> columns = new LinkedList<ColumnConfig<ColumnDto, ?>>();

      /* add columns */
      addNameColumn(columns);
      addReccomendedAliasColumn(columns);
      ColumnConfig<ColumnDto, String> ccAlias = addAliasColumn(columns);
      addDescriptionColumn(columns);
      addConfigIndicatorColum(columns);
      CCContainer<ColumnDto, AggregateFunctionDto> cccAgg = addAggregateColumn(columns);
      ColumnConfig<ColumnDto, String> ccDimension = addDimensionColumn(columns);
      addTypeColumn(columns);

      /* create grid after columns */
      this.grid = new Grid<ColumnDto>(store, new ColumnModel<ColumnDto>(columns));

      /* editing */
      final GridEditing<ColumnDto> editing = new GridInlineEditing<ColumnDto>(grid);
      configAliasColumnEditing(ccAlias, editing);
      configAggregateColumnEditing(cccAgg, editing);
      configDimensionColumnEditing(ccDimension, editing);

      new QuickTip(grid);

      grid.setSelectionModel(new GridSelectionModel<ColumnDto>());
      grid.getSelectionModel().setSelectionMode(com.sencha.gxt.core.client.Style.SelectionMode.MULTI);
      grid.getView().setShowDirtyCells(false);
      new ExtendedKeyNav(grid) {
         protected void onSelectAll() {
            grid.getSelectionModel().selectAll();
         };

         @Override
         public void onDelete(NativeEvent evt) {
            if (!editing.isEditing()) {
               final ConfirmMessageBox cmb = new DwConfirmMessageBox(
                     FilterMessages.INSTANCE.deleteColumnOnKeyPressTitle(),
                     FilterMessages.INSTANCE.deleteColumnOnKeyPressText());
               cmb.addDialogHideHandler(new DialogHideHandler() {
                  @Override
                  public void onDialogHide(DialogHideEvent event) {
                     if (event.getHideButton() == PredefinedButton.YES) {
                        removeSelectedItems();
                     }
                  }
               });
               cmb.show();
            }
         }
      };

      Menu ctxMen = new DwMenu();
      grid.setContextMenu(ctxMen);

      final MenuItem editFormat = new DwMenuItem(FilterMessages.INSTANCE.editColumnFormat());
      editFormat.addSelectionHandler(new SelectionHandler<Item>() {
         @Override
         public void onSelection(SelectionEvent<Item> event) {
            displayColumnFormatDialog();
         }
      });
      ctxMen.add(editFormat);

      MenuItem removeFormat = new DwMenuItem(FilterMessages.INSTANCE.removeFormat());
      removeFormat.addSelectionHandler(new SelectionHandler<Item>() {
         @Override
         public void onSelection(SelectionEvent<Item> event) {
            removeFormatsSelectedItems();
         }
      });
      ctxMen.add(removeFormat);

      ctxMen.add(new SeparatorMenuItem());

      MenuItem moveColumnToTopEntry = new DwMenuItem(FilterMessages.INSTANCE.moveColumnsToTop());
      moveColumnToTopEntry.addSelectionHandler(new SelectionHandler<Item>() {
         @Override
         public void onSelection(SelectionEvent<Item> event) {
            List<ColumnDto> cols = grid.getSelectionModel().getSelectedItems();
            if (null != cols && !cols.isEmpty()) {
               int index = 0;
               for (ColumnDto col : cols) {
                  store.remove(col);
                  store.add(index, col);
                  index++;
               }
            }
         }
      });
      ctxMen.add(moveColumnToTopEntry);

      MenuItem moveColumnToBottomEntry = new DwMenuItem(FilterMessages.INSTANCE.moveColumnsToBottom());
      moveColumnToBottomEntry.addSelectionHandler(new SelectionHandler<Item>() {
         @Override
         public void onSelection(SelectionEvent<Item> event) {
            List<ColumnDto> cols = grid.getSelectionModel().getSelectedItems();
            if (null != cols && !cols.isEmpty()) {
               for (ColumnDto col : cols) {
                  store.remove(col);
                  store.add(col);
               }
            }
         }
      });
      ctxMen.add(moveColumnToBottomEntry);

      ctxMen.add(new SeparatorMenuItem());

      MenuItem removeColumn = new DwMenuItem(FilterMessages.INSTANCE.removeColumn());
      removeColumn.addSelectionHandler(new SelectionHandler<Item>() {
         @Override
         public void onSelection(SelectionEvent<Item> event) {
            removeSelectedItems();
         }
      });
      ctxMen.add(removeColumn);

      new GridDragSource<ColumnDto>(grid) {
         @Override
         protected void onDragDrop(DndDropEvent event) {
            DropTarget dt = event.getDropTarget();
            if (dt instanceof GridDropTarget && ((GridDropTarget<ColumnDto>) dt).getGrid() == grid)
               super.onDragDrop(event);
         }

      };
      final GridDropTarget<ColumnDto> target = new GridDropTarget<ColumnDto>(grid);
      target.setFeedback(Feedback.INSERT);
      target.setAllowSelfAsSource(true);
   }

   private void addNameColumn(List<ColumnConfig<ColumnDto, ?>> columns) {
      ColumnConfig<ColumnDto, String> ccName = new ColumnConfig<ColumnDto, String>(ColumnDtoPA.INSTANCE.name(), 170,
            FilterMessages.INSTANCE.column());
      ccName.setSortable(false);
      ccName.setMenuDisabled(true);
      columns.add(ccName);
   }

   private void addReccomendedAliasColumn(List<ColumnConfig<ColumnDto, ?>> columns) {
      ColumnConfig<ColumnDto, String> ccDefaultAlias = new ColumnConfig<ColumnDto, String>(
            ColumnDtoPA.INSTANCE.defaultAlias(), 170, FilterMessages.INSTANCE.defaultAlias());
      ccDefaultAlias.setSortable(false);
      ccDefaultAlias.setMenuDisabled(true);
      columns.add(ccDefaultAlias);
   }

   private ColumnConfig<ColumnDto, String> addAliasColumn(List<ColumnConfig<ColumnDto, ?>> columns) {
      ColumnConfig<ColumnDto, String> ccAlias = new ColumnConfig<ColumnDto, String>(ColumnDtoPA.INSTANCE.alias(), 170,
            FilterMessages.INSTANCE.alias());
      ccAlias.setMenuDisabled(true);
      ccAlias.setSortable(false);

      columns.add(ccAlias);

      return ccAlias;
   }

   private void configAliasColumnEditing(ColumnConfig<ColumnDto, String> ccAlias, GridEditing<ColumnDto> editing) {
      editing.addEditor(ccAlias, new TextField());
   }

   private void addConfigIndicatorColum(List<ColumnConfig<ColumnDto, ?>> columns) {
      ColumnConfig<ColumnDto, ColumnDto> ccConfigIndicator = new ColumnConfig<ColumnDto, ColumnDto>(
            new IdentityValueProvider<ColumnDto>(), 120, FilterMessages.INSTANCE.optionsLabel());
      ccConfigIndicator.setMenuDisabled(true);
      ccConfigIndicator.setSortable(false);
      ccConfigIndicator.setCell(new AbstractCell<ColumnDto>() {

         @Override
         public void render(com.google.gwt.cell.client.Cell.Context context, ColumnDto model, SafeHtmlBuilder sb) {
            if (model instanceof ColumnReferenceDto) {
               sb.append(BaseIcon.CALCULATOR.toSafeHtml());
            }

            if (((ColumnDtoDec) model).hasSomeFormat()) {
               sb.append(BaseIcon.FORMAT.toSafeHtml());
            }

            if (null != model.getAggregateFunction()) {
               sb.append(BaseIcon.AGGREGATION.toSafeHtml());
            }

         }
      });

      columns.add(ccConfigIndicator);
   }

   private ColumnConfig<ColumnDto, String> addDimensionColumn(List<ColumnConfig<ColumnDto, ?>> columns) {
      ColumnConfig<ColumnDto, String> ccDimension = new ColumnConfig<ColumnDto, String>(
            ColumnDtoPA.INSTANCE.dimension(), 170, FilterMessages.INSTANCE.dimension());
      ccDimension.setMenuDisabled(true);
      ccDimension.setSortable(false);

      columns.add(ccDimension);

      return ccDimension;
   }

   private void configDimensionColumnEditing(ColumnConfig<ColumnDto, String> ccDimension,
         GridEditing<ColumnDto> editing) {
      editing.addEditor(ccDimension, new TextField());
   }

   private void addTypeColumn(List<ColumnConfig<ColumnDto, ?>> columns) {
      ColumnConfig<ColumnDto, Integer> ccType = new ColumnConfig<ColumnDto, Integer>(ColumnDtoPA.INSTANCE.type(), 90,
            FilterMessages.INSTANCE.type());
      ccType.setSortable(false);
      ccType.setMenuDisabled(true);
      ccType.setCell(new AbstractCell<Integer>() {
         @Override
         public void render(com.google.gwt.cell.client.Cell.Context context, Integer value, SafeHtmlBuilder sb) {
            if (null != value)
               sb.appendEscaped(SqlTypes.getName(value));
         }
      });
      columns.add(ccType);
   }

   private void addDescriptionColumn(List<ColumnConfig<ColumnDto, ?>> columns) {
      ColumnConfig<ColumnDto, String> ccDescription = new ColumnConfig<ColumnDto, String>(
            ColumnDtoPA.INSTANCE.description(), 130, FilterMessages.INSTANCE.description());
      ccDescription.setMenuDisabled(true);
      ccDescription.setSortable(false);
      ccDescription.setCell(new AbstractCell<String>() {
         @Override
         public void render(com.google.gwt.cell.client.Cell.Context context, String value, SafeHtmlBuilder sb) {
            if (null != value)
               sb.appendEscaped(Format.ellipse(value, 60));
         }
      });
      columns.add(ccDescription);
   }

   private CCContainer<ColumnDto, AggregateFunctionDto> addAggregateColumn(List<ColumnConfig<ColumnDto, ?>> columns) {
      AggregateFunctionDto[] values = new AggregateFunctionDto[6];
      values[0] = AggregateFunctionDto.AVG;
      values[1] = AggregateFunctionDto.COUNT;
      values[2] = AggregateFunctionDto.COUNT_DISTINCT;
      values[3] = AggregateFunctionDto.MAX;
      values[4] = AggregateFunctionDto.MIN;
      values[5] = AggregateFunctionDto.SUM;

      CCContainer<ColumnDto, AggregateFunctionDto> cccAgg = gridHelperService.createComboBoxColumnConfig(values,
            ColumnDtoPA.INSTANCE.aggregateFunction(), true, SortDir.ASC, 120);

      ColumnConfig<ColumnDto, AggregateFunctionDto> aggreagateColumn = cccAgg.getConfig();
      aggreagateColumn.setHeader(FilterMessages.INSTANCE.aggregateHeading());

      columns.add(aggreagateColumn);

      return cccAgg;
   }

   private void configAggregateColumnEditing(CCContainer<ColumnDto, AggregateFunctionDto> cccAgg,
         GridEditing<ColumnDto> editing) {
      editing.addEditor(cccAgg.getConfig(), cccAgg.getConverter(), cccAgg.getCombo());

      editing.addStartEditHandler(new StartEditHandler<ColumnDto>() {

         @Override
         public void onStartEdit(StartEditEvent<ColumnDto> event) {
            ColumnDto col = store.get(event.getEditCell().getRow());
            if (null != col && sqlTypes.isLob(col.getType())) {
               event.getSource().cancelEditing();

               new DwAlertMessageBox(FilterMessages.INSTANCE.blobAggregateCancelTitle(),
                     FilterMessages.INSTANCE.blobAggregateCancelMsg()).show();
            }
         }
      });
   }

   protected void removeSelectedItems() {
      boolean oldValue = doNotProcessChangeEvents;
      doNotProcessChangeEvents = true;

      try {
         List<ColumnDto> columns = grid.getSelectionModel().getSelectedItems();
         for (ColumnDto column : columns) {
            store.remove(column);
         }
      } finally {
         doNotProcessChangeEvents = oldValue;
      }
   }

   protected void reloadColumnsFromReport() {
      boolean oldValue = doNotProcessChangeEvents;
      doNotProcessChangeEvents = true;

      try {
         report.setColumns(new ArrayList<ColumnDto>(store.getAll()));
      } finally {
         doNotProcessChangeEvents = oldValue;
      }
   }

   public void displayColumnFormatDialog() {
      ColumnDto column = grid.getSelectionModel().getSelectedItem();
      if (null != column)
         displayColumnFormatDialog(grid.getSelectionModel().getSelectedItems());
   }

   public void displayColumnFormatDialog(final ColumnDto column) {
      ColumnFormatWindow format = new ColumnFormatWindow(report, column) {
         @Override
         protected void onHide() {
            super.onHide();
            store.update(column);
            report.fireObjectChangedEvent();
         }
      };
      format.show();
   }

   protected void displayColumnFormatDialog(final List<ColumnDto> columns) {
      if (null == columns || columns.size() == 0)
         return;

      ColumnFormatWindow format = new ColumnFormatWindow(report, columns.get(0)) {
         @Override
         protected void onHide() {
            super.onHide();

            for (ColumnDto column : columns) {
               ColumnFormatDto clonedFormat = ColumnFormatDtoDec.clone(columns.get(0).getFormat());

               column.setFormat(clonedFormat);
               column.setNullReplacementFormat(columns.get(0).getNullReplacementFormat());
               column.setExportNullAsString(columns.get(0).isExportNullAsString());

               store.update(column);
            }

            report.fireObjectChangedEvent();
         }
      };
      format.show();
   }

   protected void removeFormatsSelectedItems() {
      List<ColumnDto> columns = grid.getSelectionModel().getSelectedItems();

      for (ColumnDto column : columns) {
         ((ColumnDtoDec) column).removeAllFormats();
         store.update(column);
      }
   }

   @Override
   public ImageResource getIcon() {
      return BaseIcon.CUBES.toImageResource();
   }
}
