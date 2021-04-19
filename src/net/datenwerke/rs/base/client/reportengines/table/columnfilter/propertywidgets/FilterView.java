package net.datenwerke.rs.base.client.reportengines.table.columnfilter.propertywidgets;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.text.shared.AbstractSafeHtmlRenderer;
import com.google.inject.Inject;
import com.sencha.gxt.cell.core.client.SimpleSafeHtmlCell;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.util.Format;
import com.sencha.gxt.data.shared.Converter;
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
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.dnd.core.client.DND.Feedback;
import com.sencha.gxt.dnd.core.client.DndDropEvent;
import com.sencha.gxt.dnd.core.client.DropTarget;
import com.sencha.gxt.dnd.core.client.GridDragSource;
import com.sencha.gxt.dnd.core.client.GridDropTarget;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.BeforeShowContextMenuEvent;
import com.sencha.gxt.widget.core.client.event.BeforeShowContextMenuEvent.BeforeShowContextMenuHandler;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent.CellDoubleClickHandler;
import com.sencha.gxt.widget.core.client.event.CompleteEditEvent;
import com.sencha.gxt.widget.core.client.event.CompleteEditEvent.CompleteEditHandler;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.DialogHideHandler;
import com.sencha.gxt.widget.core.client.event.ExpandEvent;
import com.sencha.gxt.widget.core.client.event.ExpandEvent.ExpandHandler;
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
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.menu.SeparatorMenuItem;
import com.sencha.gxt.widget.core.client.tips.QuickTip;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;

import net.datenwerke.gxtdto.client.baseex.widget.btn.DwSplitButton;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwAlertMessageBox;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwConfirmMessageBox;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.clipboard.ClipboardDtoItem;
import net.datenwerke.gxtdto.client.clipboard.ClipboardDtoListItem;
import net.datenwerke.gxtdto.client.clipboard.ClipboardItem;
import net.datenwerke.gxtdto.client.clipboard.ClipboardUiService;
import net.datenwerke.gxtdto.client.clipboard.processor.ClipboardCopyProcessor;
import net.datenwerke.gxtdto.client.clipboard.processor.ClipboardDtoPasteProcessor;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.forms.selection.SelectionMode;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.servercommunication.callback.ModalAsyncCallback;
import net.datenwerke.gxtdto.client.ui.helper.grid.ExtendedKeyNav;
import net.datenwerke.gxtdto.client.utilityservices.form.combo.EmptyOption;
import net.datenwerke.gxtdto.client.utilityservices.grid.GridHelperService;
import net.datenwerke.gxtdto.client.utilityservices.grid.GridHelperService.CCContainer;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.gxtdto.client.utils.SqlTypes;
import net.datenwerke.gxtdto.client.utils.modelkeyprovider.DtoIdModelKeyProvider;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.client.datasources.statementmanager.StatementManagerDao;
import net.datenwerke.rs.base.client.reportengines.table.TableReportUtilityDao;
import net.datenwerke.rs.base.client.reportengines.table.columnfilter.hooks.FilterViewEnhanceToolbarHook;
import net.datenwerke.rs.base.client.reportengines.table.columnfilter.locale.FilterMessages;
import net.datenwerke.rs.base.client.reportengines.table.dto.AdditionalColumnSpecDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.AggregateFunctionDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFilterDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnReferenceDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.NullHandlingDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.OrderDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.PreFilterDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnDtoDec;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFilterDtoDec;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatDtoDec;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterBlockDtoDec;
import net.datenwerke.rs.base.client.reportengines.table.dto.pa.ColumnDtoPA;
import net.datenwerke.rs.base.client.reportengines.table.helpers.ColumnFilterWindow;
import net.datenwerke.rs.base.client.reportengines.table.helpers.ColumnFormatWindow;
import net.datenwerke.rs.base.client.reportengines.table.helpers.ColumnSelector;
import net.datenwerke.rs.base.client.reportengines.table.helpers.EditSubtotalsWindow;
import net.datenwerke.rs.base.client.reportengines.table.prefilter.locale.PreFilterMessages;
import net.datenwerke.rs.core.client.contexthelp.ContextHelpUiService;
import net.datenwerke.rs.core.client.contexthelp.dto.ContextHelpInfo;
import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorUIService;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorMainPanelView;
import net.datenwerke.rs.core.client.reportexecutor.ui.aware.SelectionAwareView;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class FilterView extends ReportExecutorMainPanelView implements SelectionAwareView {

	public static final String VIEW_ID = "listconfig";
	
	private final ClipboardUiService clipboardService;
	private final TableReportUtilityDao tableReportUtilityDao;
	private final ToolbarService toolbarService;
	private final GridHelperService gridHelperService;
	private final HookHandlerService hookHandler;
	private final SqlTypes sqlTypes;
	private final ContextHelpUiService contextHelpService;
	private final StatementManagerDao statementManager;

	private TableReportDto report;
	private DwToolBar toolbar;
	private Grid<ColumnDto> grid;
	private ListStore<ColumnDto> store;
	
	private List<ColumnDto> availableColumnsInReport;
	
	private boolean doNotProcessChangeEvents = false;
	
	private boolean loadAvailableColumns = false;
	private List<ColumnDto> columnsToImport = new ArrayList<ColumnDto>();
	
	private ReportExecutorUIService reportExecutorUIService;
	
	@Inject
	public FilterView(
		ClipboardUiService clipboardService,
		TableReportUtilityDao tableReportUtilityDao,
		ToolbarService toolbarService,
		GridHelperService gridHelperService, 
		ContextHelpUiService contextHelpService,
		HookHandlerService hookHandler,
		StatementManagerDao statementManager,
		SqlTypes sqlTypes,
		ReportExecutorUIService reportExecutorUIService
		) {

		this.clipboardService = clipboardService;
		/* store objects */
		this.tableReportUtilityDao = tableReportUtilityDao;
		this.toolbarService = toolbarService;
		this.gridHelperService = gridHelperService;
		this.contextHelpService = contextHelpService;
		this.statementManager = statementManager;
		this.hookHandler = hookHandler;
		this.sqlTypes = sqlTypes;
		this.reportExecutorUIService = reportExecutorUIService;
	}
	
	@Override
	public String getViewId(){
		return VIEW_ID;
	}
	
	@Override
	public boolean wantsToBeDefault() {
		return true;
	}

	public void setReport(TableReportDto tableReport) {
		this.report = tableReport;
	}

	public void displayFilterDialog(final ColumnDto column) {
		String executeToken = getExecuteReportToken() + ":" + String.valueOf(Math.random());
		final ColumnFilterWindow filter = new ColumnFilterWindow(report, column, executeToken){
			@Override
			protected void onHide() {
				super.onHide();
				store.update((ColumnDtoDec) column);
			}
		};
		filter.setModal(true);
		
		filter.show();
	}

	public void displayColumnFormatDialog() {
		ColumnDto column = grid.getSelectionModel().getSelectedItem();
		if (null != column)
			displayColumnFormatDialog(grid.getSelectionModel().getSelectedItems());
	}
	

	public void displayColumnFormatDialog(final ColumnDto column) {
		ColumnFormatWindow format = new ColumnFormatWindow(report, column){
			@Override
			protected void onHide() {
				super.onHide();
				store.update(column);
				report.fireObjectChangedEvent();
			}
		};
		format.show();
	}
	

	private void displayColumnFormatDialog(final List<ColumnDto> columns) {
		if(null == columns || columns.size() == 0)
			return;
		
		ColumnFormatWindow format = new ColumnFormatWindow(report, columns.get(0)){
			@Override
			protected void onHide() {
				super.onHide();
				
				for(ColumnDto column : columns){
					ColumnFormatDto clonedFormat = ColumnFormatDtoDec.clone(columns.get(0).getFormat());
					
					column.setFormat(clonedFormat);
					column.setNullReplacementFormat(columns.get(0).getNullReplacementFormat());
					
					store.update(column);	
				}
			
				report.fireObjectChangedEvent();
			}
		};
		format.show();
	}
	
	protected void convertToPrefilter(ColumnDto col, boolean removeOldFilter){
		ColumnDto clone = ((ColumnDtoDec)col).cloneColumnForSelection();
		
		ColumnFilterDto filter =  new ColumnFilterDtoDec();
		filter.setColumn(clone);

		PreFilterDto preFilter = report.getPreFilter();
		FilterBlockDtoDec rootBlock = (FilterBlockDtoDec) preFilter.getRootBlock();
		
		rootBlock.addFilter(filter);
		
		if(removeOldFilter)
			((ColumnDtoDec)col).removeAllFilters();
	}
	
	public void displaySubtotalsDialog() {
		EditSubtotalsWindow format = new EditSubtotalsWindow(report){
			@Override
			protected void onHide() {
				super.onHide();
				store.commitChanges();
				report.fireObjectChangedEvent();
			}
		};
		format.show();
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
				if(! doNotProcessChangeEvents && TableReportDto.getColumnsPropertyAccessor().getPath().equals(event.getPropertyPath())){
					List<ColumnDto> cols = new ArrayList(report.getColumns());
					store.clear();
					store.addAll(cols);
				}
			}
		});
		
		/* add Columns */
		List<ColumnConfig<ColumnDto,?>> columns = new LinkedList<ColumnConfig<ColumnDto,?>>();
		
		/* add columns */
		addNameColumn(columns);
		addRecommendedAliasColumn(columns);
		ColumnConfig<ColumnDto, String> ccAlias = addAliasColumn(columns);
		addDescriptionColumn(columns);
		addConfigIndicatorColum(columns);
		CCContainer<ColumnDto, AggregateFunctionDto> cccAgg = addAggregateColumn(columns);
		CCContainer<ColumnDto, OrderDto> ccOrderContainer = addOrderColumn(columns);
		CCContainer<ColumnDto, Boolean> ccVisibilityContainer = addVisibilityColumn(columns);
		addRecommendedWidthColumn(columns);
		ColumnConfig<ColumnDto, Integer> ccWidth = addWidthColumn(columns);
		addTypeColumn(columns);
		
		/* create grid only after columns */
		this.grid = new Grid<ColumnDto>(store, new ColumnModel<ColumnDto>(columns));
		
		/* now create editing */
		final GridEditing<ColumnDto> editing = new GridInlineEditing<ColumnDto>(grid);
		configAliasColumnEditing(ccAlias, editing);
		configAggregateColumnEditing(columns, cccAgg, editing);
		configOrderColumnEditing(ccOrderContainer, editing);
		configVisibilityColumnEditing(ccVisibilityContainer, editing);
		configWidthColumnEditing(ccWidth, editing);
		
		new QuickTip(grid);
		
		
	

		grid.setSelectionModel(new GridSelectionModel<ColumnDto>());
		grid.getSelectionModel().setSelectionMode(com.sencha.gxt.core.client.Style.SelectionMode.MULTI);
		grid.getView().setShowDirtyCells(false);
		new ExtendedKeyNav(grid){
			protected void onSelectAll() {
				grid.getSelectionModel().selectAll();
			};
			@Override
			public void onDelete(NativeEvent evt) {
				if(! editing.isEditing()){
					final ConfirmMessageBox cmb = new DwConfirmMessageBox(FilterMessages.INSTANCE.deleteColumnOnKeyPressTitle(), FilterMessages.INSTANCE.deleteColumnOnKeyPressText());
					cmb.addDialogHideHandler(new DialogHideHandler() {
						@Override
						public void onDialogHide(DialogHideEvent event) {
							if (event.getHideButton() == PredefinedButton.YES)
								removeSelectedItems();
						}
					});
					cmb.show();
				}
			}
		};

		Menu ctxMen = new DwMenu();
		
		final MenuItem copyItem = new DwMenuItem(BaseMessages.INSTANCE.copy());
		copyItem.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				ClipboardItem clipboardItem = createClipboardItemFromSelected();
				clipboardService.setClipboardItem(clipboardItem);
			}
		});
		ctxMen.add(copyItem);
		
		final MenuItem pasteItem = new DwMenuItem(BaseMessages.INSTANCE.paste());
		pasteItem.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				ClipboardItem clipboardItem = clipboardService.getClipboardItem();
				
				if(null == clipboardItem || clipboardItem.getType() != ColumnDto.class)
					return;
				
				if(clipboardItem instanceof ClipboardDtoListItem) {
					ClipboardDtoListItem list = (ClipboardDtoListItem) clipboardItem;
					if(list.getList().isEmpty() || list.getList().get(0) instanceof AdditionalColumnSpecDto)
						return;
					
					handlePaste((ClipboardDtoListItem) clipboardItem);
				} else if(clipboardItem instanceof ClipboardDtoItem)
					handlePaste((ClipboardDtoItem) clipboardItem);
			}
		});
		ctxMen.add(pasteItem);
		
		ctxMen.add(new SeparatorMenuItem());
		
		final MenuItem editFilter = new DwMenuItem(FilterMessages.INSTANCE.editFilter());
		editFilter.addSelectionHandler(new SelectionHandler<Item>() {
			
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				displayFilterDialog();
			}
		});
		ctxMen.add(editFilter);
		
		MenuItem convertToPreFilter = new DwMenuItem(FilterMessages.INSTANCE.convertToPrefilterTitle());
		convertToPreFilter.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				final ConfirmMessageBox cmb = new DwConfirmMessageBox(FilterMessages.INSTANCE.convertToPrefilterTitle(), FilterMessages.INSTANCE.convertToPrefilterText());
				cmb.addDialogHideHandler(new DialogHideHandler() {
					
					@Override
					public void onDialogHide(DialogHideEvent event) {
						boolean convert = false;
						boolean remove = false;
						if (event.getHideButton() == PredefinedButton.YES){
							convert = true;
							remove = true;
						} else if (event.getHideButton() == PredefinedButton.NO){
							convert = true;
						}
							
						if(convert){
							List<ColumnDto> columns = grid.getSelectionModel().getSelectedItems();
							if (null != columns){
								for(ColumnDto column : columns){
									convertToPrefilter(column, remove);
									store.update(column);
								}
							}
							
							report.fireObjectChangedEvent();
							Info.display(PreFilterMessages.INSTANCE.infoTitleColumnToPrefilter(), PreFilterMessages.INSTANCE.infoTextColumnToPrefilter());
						}		
					}
				});

				DwTextButton cancelBtn = new DwTextButton(BaseMessages.INSTANCE.cancel());
				cancelBtn.addSelectHandler(new SelectHandler() {
					@Override
					public void onSelect(SelectEvent event) {
						cmb.hide();
					}
				});
				cmb.addButton(cancelBtn);
				
				cmb.show();
			}
		});
		ctxMen.add(convertToPreFilter);

		MenuItem removeFilters = new DwMenuItem(FilterMessages.INSTANCE.removeAllFilters());
		removeFilters.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				removeFiltersOnSelectedItems();
			}
		});
		ctxMen.add(removeFilters);

		ctxMen.add(new SeparatorMenuItem());
		
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
		
		
		Menu sortMenu = new DwMenu();
		MenuItem sortMenuItem = new DwMenuItem(FilterMessages.INSTANCE.orderHeading());
		sortMenuItem.setSubMenu(sortMenu);
		ctxMen.add(sortMenuItem);

		MenuItem sortAscending = new DwMenuItem(FilterMessages.INSTANCE.orderAsc(), BaseIcon.SORT_ALPHA_ASC);
		sortMenu.add(sortAscending);
		sortAscending.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				setOrderSelectedItems(OrderDto.ASC);
			}
		});

		MenuItem sortDescending = new DwMenuItem(FilterMessages.INSTANCE.orderDesc(), BaseIcon.SORT_ALPHA_DESC);
		sortMenu.add(sortDescending);
		sortDescending.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				setOrderSelectedItems(OrderDto.DESC);
			}
		});

		MenuItem sortNo = new DwMenuItem(FilterMessages.INSTANCE.orderNone());
		sortMenu.add(sortNo);
		sortNo.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				setOrderSelectedItems(null);
			}
		});

		ctxMen.add(new SeparatorMenuItem());
		
		
		MenuItem moveColumnToTopEntry = new DwMenuItem(FilterMessages.INSTANCE.moveColumnsToTop());
		moveColumnToTopEntry.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				List<ColumnDto> cols = grid.getSelectionModel().getSelectedItems();
				if(null != cols && ! cols.isEmpty()){
					int index = 0;
					for(ColumnDto col : cols){
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
				if(null != cols && ! cols.isEmpty()){
					for(ColumnDto col : cols){
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

		grid.setContextMenu(ctxMen);

		grid.addBeforeShowContextMenuHandler(new BeforeShowContextMenuHandler() {
			@Override
			public void onBeforeShowContextMenu(BeforeShowContextMenuEvent event) {
				if (grid.getSelectionModel().getSelection().size() == 0)
					event.setCancelled(true);
				if (grid.getSelectionModel().getSelection().size() > 1)
					editFilter.setEnabled(false);
				else
					editFilter.setEnabled(true);
				
				/* clipboard */
				pasteItem.setEnabled(true);
				ClipboardItem clipboardItem = clipboardService.getClipboardItem();
				if(null == clipboardItem || clipboardItem.getType() != ColumnDto.class)
					pasteItem.setEnabled(false);
				else if(clipboardItem instanceof ClipboardDtoListItem) {
					ClipboardDtoListItem list = (ClipboardDtoListItem) clipboardItem;
					if(list.getList().isEmpty() || list.getList().get(0) instanceof AdditionalColumnSpecDto)
						pasteItem.setEnabled(false);
				} 
					
				copyItem.setEnabled(true);
				List<ColumnDto> list = grid.getSelectionModel().getSelectedItems();
				ColumnDto selected = grid.getSelectionModel().getSelectedItem();
				if((null == list ||  list.isEmpty()) && null == selected)
					copyItem.setEnabled(false);
			}
		});

		grid.addCellDoubleClickHandler(new CellDoubleClickHandler() {
			@Override
			public void onCellClick(CellDoubleClickEvent event) {
				displayFilterDialog();
			}
		});

		
		new GridDragSource<ColumnDto>(grid){
			@Override
			protected void onDragDrop(DndDropEvent event) {
				DropTarget dt = event.getDropTarget();
				if(dt instanceof GridDropTarget && ((GridDropTarget<ColumnDto>)dt).getGrid() == grid)
					super.onDragDrop(event);
			}
			
		};
		final GridDropTarget<ColumnDto> target = new GridDropTarget<ColumnDto>(grid);
		target.setFeedback(Feedback.INSERT);
		target.setAllowSelfAsSource(true);
	}
	
	protected void setOrderSelectedItems(OrderDto order) {
		List<ColumnDto> columns = grid.getSelectionModel().getSelectedItems();

		for (ColumnDto column : columns) {
			((ColumnDtoDec) column).setOrder(order);
			store.update(column);
		}
	}

	protected void reloadColumnsFromReport() {
		boolean oldValue = doNotProcessChangeEvents;
		doNotProcessChangeEvents = true;
		
		try{
			report.setColumns(new ArrayList<ColumnDto>(store.getAll()));
		} finally {
			doNotProcessChangeEvents = oldValue;
		}
	}
	
	private void createToolbar() {
		toolbar = new DwToolBar();
		toolbar.setBorders(false);

		DwTextButton selectColumnsButton = toolbarService.createSmallButtonLeft(
				FilterMessages.INSTANCE.selectColumns(), BaseIcon.TABLE_ADD
		);

		toolbar.add(selectColumnsButton);
		
		selectColumnsButton.addSelectHandler(new SelectHandler() {
			ColumnSelector columnSelector;

			@Override
			public void onSelect(SelectEvent event) {

				if(null != columnSelector && !columnSelector.hasError()){
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
						try{
							store.clear();
						
							List<ColumnDto> newStoreList = new ArrayList<ColumnDto>();
							for(ColumnDto col : selectedItems)
								newStoreList.add(((ColumnDtoDec)col).cloneColumnForSelection());
							
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


		/* ask hookers */
		List<FilterViewEnhanceToolbarHook> toolbarEnhancers = hookHandler.getHookers(FilterViewEnhanceToolbarHook.class);
		
		for(FilterViewEnhanceToolbarHook te : toolbarEnhancers)
			te.initialize(this, report);
		
		for(FilterViewEnhanceToolbarHook te : toolbarEnhancers)
			te.enhanceToolbarLeft(toolbar);
		
		toolbar.add(new FillToolItem());
		
		for(FilterViewEnhanceToolbarHook te : toolbarEnhancers)
			te.enhanceToolbarRight(toolbar);
		
		/* info */
		if(contextHelpService.isEnabled()){
			ContextHelpInfo contextHelpInfo = new ContextHelpInfo("dynamiclist/listconfig");
			DwTextButton btn = contextHelpService.createDwTextButton(contextHelpInfo);
			toolbar.add(btn);
		}
	}
	
	
	public void displayFilterDialog() {
		ColumnDto column = grid.getSelectionModel().getSelectedItem();
		if(null == column)
			return;
		
		if(report.isEnableSubtotals()){
			if(null == column.getAggregateFunction() && ! column.isSubtotalGroup()){
				DwAlertMessageBox mbox = new DwAlertMessageBox(BaseMessages.INSTANCE.warning(), FilterMessages.INSTANCE.subTotalFilterWarningMsg());
				mbox.show();
				return;
			}
		}
		
		displayFilterDialog(column);
	}

	protected void removeSelectedItems() {
		boolean oldValue = doNotProcessChangeEvents;
		doNotProcessChangeEvents = true;
		
		try{
			List<ColumnDto> columns = grid.getSelectionModel().getSelectedItems();
			for (ColumnDto column : columns) {
				store.remove(column);
			}
		} finally {
			doNotProcessChangeEvents = oldValue;
		}
	}
	
	protected void removeFormatsSelectedItems() {
		List<ColumnDto> columns = grid.getSelectionModel().getSelectedItems();

		for (ColumnDto column : columns) {
			((ColumnDtoDec) column).removeAllFormats();
			store.update(column);
		}
	}

	protected void removeFiltersOnSelectedItems() {
		List<ColumnDto> columns = grid.getSelectionModel().getSelectedItems();

		for (ColumnDto column : columns) {
			((ColumnDtoDec) column).removeAllFilters();
			store.update(column);
		}
	}

	@Override
	public String getComponentHeader() {
		return FilterMessages.INSTANCE.filterHeading(); //$NON-NLS-1$
	}

	@Override
	public Component getViewComponent() {
		createToolbar();
		createGrid();

		VerticalLayoutContainer contentPanel = new VerticalLayoutContainer();
		contentPanel.add(toolbar, new VerticalLayoutData(1, -1));
		
//		contentPanel.setWidget(grid);
		contentPanel.add(grid, new VerticalLayoutData(1, 1));
		
		initClipboard();
		
		return contentPanel;
	}
	
	protected void initClipboard() {
		clipboardService.registerCopyHandler(grid, new ClipboardCopyProcessor() {
			@Override
			public ClipboardItem getItem() {
				return createClipboardItemFromSelected();
			}
		});
		
		clipboardService.registerPasteHandler(grid, new ClipboardDtoPasteProcessor(ColumnDto.class) {
			@Override
			protected void doPaste(ClipboardDtoItem dtoItem) {
				handlePaste(dtoItem);
			}
			@Override
			protected void doPaste(ClipboardDtoListItem listItem) {
				handlePaste(listItem);
			}
		});
	}
	
	protected void handlePaste(ClipboardDtoListItem listItem) {
		for(Dto col : listItem.getList())
			if(col instanceof ColumnDto && ! (col instanceof AdditionalColumnSpecDto))
				importColumn((ColumnDto) col);		
	}

	protected void handlePaste(ClipboardDtoItem dtoItem) {
		Dto dto = dtoItem.getDto();
		if(dto instanceof ColumnDto && ! (dto instanceof AdditionalColumnSpecDto)){
			ColumnDto col = (ColumnDto) dto;
			importColumn(col);
		}
	}

	protected ClipboardItem createClipboardItemFromSelected() {
		List<ColumnDto> list = grid.getSelectionModel().getSelectedItems();
		if(null != list &&  list.size() > 1)
			return new ClipboardDtoListItem(list, ColumnDto.class);
		
		ColumnDto col = grid.getSelectionModel().getSelectedItem();
		if(null != col)
			return new ClipboardDtoItem(col);
		return null;
	}

	protected void importColumn(ColumnDto col) {
		if(null == col.getName())
			return;
		
		if(null == availableColumnsInReport){
			columnsToImport.add(col);
			if(loadAvailableColumns)
				return;
			
			loadAvailableColumns = true;
			final String execToken = getExecuteReportToken();
			ModalAsyncCallback mac = new ModalAsyncCallback<ListLoadResult<ColumnDto>>(250) {
				
				@Override
				protected void doOnCancel() {
					statementManager.cancelStatement(execToken);
				};
				
				@Override
				public void doOnSuccess(com.sencha.gxt.data.shared.loader.ListLoadResult<ColumnDto> result) {
					availableColumnsInReport = result.getData();
					loadAvailableColumns = false;
					
					List<ColumnDto> cols = new ArrayList<ColumnDto>(columnsToImport);
					columnsToImport.clear();
					for(ColumnDto col : cols)
						importColumn(col);
				};
				
				@Override
				public void doOnFailure(Throwable caught) {
					loadAvailableColumns = false;
				}
			};
			
			tableReportUtilityDao.getReturnedColumns(report, execToken, mac);
		} else {
			boolean found = false;
			String name = col.getName();
			
			if(col instanceof ColumnReferenceDto){
				for(ColumnDto available : report.getAdditionalColumns()){
					if(name.equals(available.getName())){
						found = true;
						break;
					}
				}
			} else {
				for(ColumnDto available : availableColumnsInReport){
					if(name.equals(available.getName())){
						found = true;
						break;
					}
				}
			}
			
			if(found)
				store.add(((ColumnDtoDec)col).cloneColumnForSelection());
			else 
				new DwAlertMessageBox(FilterMessages.INSTANCE.columnCannotBeImportedTitle(), FilterMessages.INSTANCE.columnNotAllowedInReport(name)).show();
			
		}
	}

	@Override
	public ImageResource getIcon() {
		return BaseIcon.TABLE_EDIT.toImageResource();
	}

	private void addNameColumn(List<ColumnConfig<ColumnDto,?>> columns) {
		ColumnConfig<ColumnDto,String> ccName = new ColumnConfig<ColumnDto,String>(ColumnDtoPA.INSTANCE.name(), 170, FilterMessages.INSTANCE.column());  
		ccName.setSortable(false);
		ccName.setMenuDisabled(true);
		columns.add(ccName);
	}

	private void addTypeColumn(List<ColumnConfig<ColumnDto,?>> columns) {
		ColumnConfig<ColumnDto,Integer> ccType = new ColumnConfig<ColumnDto, Integer>(ColumnDtoPA.INSTANCE.type(), 90, FilterMessages.INSTANCE.type());
		ccType.setSortable(false);
		ccType.setMenuDisabled(true);
		ccType.setCell(new AbstractCell<Integer>() {
			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Integer value, SafeHtmlBuilder sb) {
				if(null != value)
					sb.appendEscaped(SqlTypes.getName(value));
			}
		});
		columns.add(ccType);
	}

	private void addRecommendedAliasColumn(List<ColumnConfig<ColumnDto,?>> columns) {
		ColumnConfig<ColumnDto,String> ccDefaultAlias = new ColumnConfig<ColumnDto,String>(ColumnDtoPA.INSTANCE.defaultAlias(), 170, FilterMessages.INSTANCE.defaultAlias());
		ccDefaultAlias.setSortable(false);
		ccDefaultAlias.setMenuDisabled(true);
		columns.add(ccDefaultAlias);
	}

	private ColumnConfig<ColumnDto, String> addAliasColumn(List<ColumnConfig<ColumnDto,?>> columns) {
		ColumnConfig<ColumnDto,String> ccAlias = new ColumnConfig<ColumnDto,String>(ColumnDtoPA.INSTANCE.alias(), 170, FilterMessages.INSTANCE.alias()); 
		ccAlias.setMenuDisabled(true);
		ccAlias.setSortable(false);
		
		columns.add(ccAlias);
		
		return ccAlias;
	}
	
	private void configAliasColumnEditing(ColumnConfig<ColumnDto,String> ccAlias, GridEditing<ColumnDto> editing) {
		editing.addEditor(ccAlias, new TextField());
	}

	private CCContainer<ColumnDto, AggregateFunctionDto> addAggregateColumn(final List<ColumnConfig<ColumnDto,?>> columns) {
		final CCContainer<ColumnDto, AggregateFunctionDto> cccAgg = gridHelperService.createComboBoxColumnConfig(AggregateFunctionDto.values(), ColumnDtoPA.INSTANCE.aggregateFunction(), true, SortDir.ASC, 120);
		
		cccAgg.getCombo().addExpandHandler(new ExpandHandler() {
			@Override
			public void onExpand(ExpandEvent event) {
				ColumnDto selectedItem = grid.getSelectionModel().getSelectedItem();
				if(null != selectedItem){
					cccAgg.getCombo().getStore().clear();
					AggregateFunctionDto[] values = null;
					
					if(sqlTypes.isDateLikeType(selectedItem.getType()) || sqlTypes.isString(selectedItem.getType())){
						values = new AggregateFunctionDto[]{
								AggregateFunctionDto.MAX,
								AggregateFunctionDto.MIN,
								AggregateFunctionDto.COUNT,
								AggregateFunctionDto.COUNT_DISTINCT
						};
					} else if(sqlTypes.isLob(selectedItem.getType())){
						values = new AggregateFunctionDto[]{};
					} else {
						values = AggregateFunctionDto.values();
					}
					
					cccAgg.getCombo().add(new EmptyOption());
					for(AggregateFunctionDto value : values){
						cccAgg.getCombo().add(value);
					}
				}
				
			}
		});
		
		final ColumnConfig<ColumnDto,AggregateFunctionDto> aggreagateColumn = cccAgg.getConfig();
		aggreagateColumn.setHeader(FilterMessages.INSTANCE.aggregateHeading());
		
		columns.add(aggreagateColumn);
		
		return cccAgg;
	}
	
	
	private void configAggregateColumnEditing(final List<ColumnConfig<ColumnDto,?>> columns, CCContainer<ColumnDto, AggregateFunctionDto> cccAgg, GridEditing<ColumnDto> editing) {
		final ColumnConfig<ColumnDto,AggregateFunctionDto> aggreagateColumn = cccAgg.getConfig();
		
		editing.addEditor(cccAgg.getConfig(), cccAgg.getConverter(), cccAgg.getCombo());
		
		editing.addCompleteEditHandler(new CompleteEditHandler<ColumnDto>() {

			@Override
			public void onCompleteEdit(CompleteEditEvent<ColumnDto> event) {
				ColumnDto col = store.get(event.getEditCell().getRow());
				ColumnConfig<ColumnDto, ?> gridColumn = columns.get(event.getEditCell().getCol());
				
				if(null != col && sqlTypes.isLob(col.getType()) && aggreagateColumn == gridColumn && col.getAggregateFunction() != null){
					col.setAggregateFunction(null);
					store.update(col);
					report.fireObjectChangedEvent();
					
					event.getSource().cancelEditing();
					
					new DwAlertMessageBox(FilterMessages.INSTANCE.blobAggregateCancelTitle(), FilterMessages.INSTANCE.blobAggregateCancelMsg()).show();
				}
			}
		});
		
		editing.addStartEditHandler(new StartEditHandler<ColumnDto>() {

			@Override
			public void onStartEdit(StartEditEvent<ColumnDto> event) {
				
			}
		});
	}

	private CCContainer<ColumnDto, OrderDto> addOrderColumn(List<ColumnConfig<ColumnDto,?>> columns) {
		CCContainer<ColumnDto, OrderDto> ccOrderContainer = gridHelperService.createComboBoxColumnConfig(OrderDto.values(), ColumnDtoPA.INSTANCE.order(), true, null,90);
				ColumnConfig<ColumnDto, OrderDto> orderColumn = ccOrderContainer.getConfig();
		orderColumn.setHeader(FilterMessages.INSTANCE.orderHeading());
		columns.add(orderColumn);
		
		return ccOrderContainer;
	}
	
	private void configOrderColumnEditing(CCContainer<ColumnDto, OrderDto> ccOrderContainer, GridEditing<ColumnDto> editing) {
		editing.addEditor(ccOrderContainer.getConfig(), ccOrderContainer.getConverter(), ccOrderContainer.getCombo());
	}

	private CCContainer<ColumnDto, Boolean> addVisibilityColumn(List<ColumnConfig<ColumnDto,?>> columns) {
		CCContainer<ColumnDto, Boolean> cccBoolean = gridHelperService.createBooleanComboBoxColumnConfig(ColumnDtoPA.INSTANCE.hidden(), false, false, 90, FilterMessages.INSTANCE.hidden(), FilterMessages.INSTANCE.visible());
		
		ColumnConfig<ColumnDto, Boolean> visibleColumn = cccBoolean.getConfig();
		visibleColumn.setHeader(FilterMessages.INSTANCE.visible() + "/" + FilterMessages.INSTANCE.hidden());
		
		visibleColumn.setWidth(90);
		visibleColumn.setSortable(false);
		visibleColumn.setMenuDisabled(true);
		
		visibleColumn.setCell(new SimpleSafeHtmlCell<Boolean>(new AbstractSafeHtmlRenderer<Boolean>() {
			@Override
			public SafeHtml render(Boolean object) {
				return SafeHtmlUtils.fromString(object ? FilterMessages.INSTANCE.hidden() : FilterMessages.INSTANCE.visible());
			}
		}));
		
		columns.add(visibleColumn);
		
		return cccBoolean;
	}
	
	private void configVisibilityColumnEditing(CCContainer<ColumnDto, Boolean> cccBoolean, GridEditing<ColumnDto> editing) {
		editing.addEditor(cccBoolean.getConfig(), cccBoolean.getConverter(), cccBoolean.getCombo());
	}

	private void addConfigIndicatorColum(List<ColumnConfig<ColumnDto,?>> columns) {
		ColumnConfig<ColumnDto,ColumnDto> ccConfigIndicator = new ColumnConfig<ColumnDto,ColumnDto>(new IdentityValueProvider<ColumnDto>(), 120, FilterMessages.INSTANCE.optionsLabel());
		ccConfigIndicator.setMenuDisabled(true);
		ccConfigIndicator.setSortable(false);
		ccConfigIndicator.setCell(new AbstractCell<ColumnDto>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					ColumnDto model, SafeHtmlBuilder sb) {
				if(model.isIndexColumn())
					sb.append(BaseIcon.INDEX.toSafeHtml());
				
				if (model instanceof ColumnReferenceDto) {
					sb.append(BaseIcon.CALCULATOR.toSafeHtml());
				}
				
				if (((ColumnDtoDec)model).hasSomeFormat()) {
					sb.append(BaseIcon.FORMAT.toSafeHtml());
				}
				
				if (((ColumnDtoDec)model).hasFilters()) {
					sb.append(BaseIcon.FILTER.toSafeHtml());
				}

				if (null != model.getNullHandling()) {
					if (model.getNullHandling().equals(NullHandlingDto.Exlude))
						sb.append(BaseIcon.NULL_EXCLUDE.toSafeHtml());
					else
						sb.append(BaseIcon.NULL_INCLUDE.toSafeHtml());
				}

				
				if (null != model.getAggregateFunction()) {
					sb.append(BaseIcon.AGGREGATION.toSafeHtml());
				}
				if (model.isSubtotalGroup()) {
					sb.append(BaseIcon.GROUP.toSafeHtml());
				}

				if (null != model.getOrder()) {
					if (model.getOrder().equals(OrderDto.ASC))
						sb.append(BaseIcon.SORT_ALPHA_ASC.toSafeHtml());
					else
						sb.append(BaseIcon.SORT_ALPHA_DESC.toSafeHtml());
				}

			}
		});

		columns.add(ccConfigIndicator);
	}

	private void addDescriptionColumn(List<ColumnConfig<ColumnDto,?>> columns) {
		ColumnConfig<ColumnDto, String> ccDescription = new ColumnConfig<ColumnDto, String>(ColumnDtoPA.INSTANCE.description(), 130, FilterMessages.INSTANCE.description());
		ccDescription.setMenuDisabled(true);
		ccDescription.setSortable(false);
		ccDescription.setCell(new AbstractCell<String>() {
			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,	String value, SafeHtmlBuilder sb) {
				if(null != value) 
					sb.appendEscaped(Format.ellipse(value, 60));
			}
		});
		columns.add(ccDescription);
	}
	
	private void addRecommendedWidthColumn(List<ColumnConfig<ColumnDto,?>> columns) {
		ColumnConfig<ColumnDto,String> ccDefaultWidth = new ColumnConfig<ColumnDto,String>(ColumnDtoPA.INSTANCE.defaultPreviewWidth(), 170, FilterMessages.INSTANCE.defaultWidth());
		ccDefaultWidth.setSortable(false);
		ccDefaultWidth.setMenuDisabled(true);
		columns.add(ccDefaultWidth);
	}
	
	private ColumnConfig<ColumnDto, Integer> addWidthColumn(List<ColumnConfig<ColumnDto,?>> columns) {
		ColumnConfig<ColumnDto,Integer> ccWidth = new ColumnConfig<ColumnDto,Integer>(ColumnDtoPA.INSTANCE.previewWidth(), 100, FilterMessages.INSTANCE.width());
		ccWidth.setSortable(false);
		ccWidth.setMenuDisabled(true);
		
		columns.add(ccWidth);
		
		return ccWidth;	
	}
	
	private void configWidthColumnEditing(ColumnConfig<ColumnDto,Integer> ccWidth, GridEditing<ColumnDto> editing) {				
		
    	editing.addEditor(ccWidth, new Converter<Integer,String>() {

    	Integer maxColumnWidth = reportExecutorUIService.getMaxColumnWidth();	
    		
			@Override
			public Integer convertFieldValue(String object) {
				if(null == object) 
					return null;
				else {
					int intValue;
					try {
						intValue = Integer.valueOf(object);
					} catch(NumberFormatException ex) {
					    return null;
					}
					if (intValue > maxColumnWidth) {
						new DwAlertMessageBox(FilterMessages.INSTANCE.failed(), FilterMessages.INSTANCE.previewWidthWarningMsg() + maxColumnWidth).show();
						return null;
					}	
					return intValue;
				}
			}
			
			@Override
			public String convertModelValue(Integer object) {
				if(null == object)
					return null;
				return String.valueOf(object);
			}
		},new TextField());
		
	}

	@Override
	public void makeAwareOfSelection() {
		grid.getView().refresh(true);
	}


}
