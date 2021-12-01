 package net.datenwerke.rs.base.client.reportengines.table.helpers.filter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import net.datenwerke.gxtdto.client.baseex.widget.btn.DwToggleButton;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwNorthSouthContainer;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.model.StringBaseModel;
import net.datenwerke.gxtdto.client.model.pa.StringBaseModelPa;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwPagingToolBar;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.gxtdto.client.utils.SqlTypes;
import net.datenwerke.gxtdto.client.utils.modelkeyprovider.BasicObjectModelKeyProvider;
import net.datenwerke.gxtdto.client.widgets.SeparatorTextLabel;
import net.datenwerke.rs.base.client.AvailableReportProperties;
import net.datenwerke.rs.base.client.datasources.statementmanager.StatementManagerDao;
import net.datenwerke.rs.base.client.reportengines.BaseReportEngineUiModule;
import net.datenwerke.rs.base.client.reportengines.table.TableReportUtilityDao;
import net.datenwerke.rs.base.client.reportengines.table.columnfilter.FilterService;
import net.datenwerke.rs.base.client.reportengines.table.columnfilter.locale.FilterMessages;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.BeforeLoadEvent;
import com.sencha.gxt.data.shared.loader.LoadEvent;
import com.sencha.gxt.data.shared.loader.LoadExceptionEvent;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.LoaderHandler;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.dnd.core.client.DndDropEvent;
import com.sencha.gxt.dnd.core.client.GridDragSource;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.event.RowDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.RowDoubleClickEvent.RowDoubleClickHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent.TriggerClickHandler;
import com.sencha.gxt.widget.core.client.form.PropertyEditor;
import com.sencha.gxt.widget.core.client.form.SimpleComboBox;
import com.sencha.gxt.widget.core.client.form.TriggerField;
import com.sencha.gxt.widget.core.client.form.Validator;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

/**
 * 
 *
 */
public class DistinctSelectorPanel<M> {

	private static final String RS_BTN_INFORM = "rs-btn-inform";

	private static StringBaseModelPa sbmPa = GWT.create(StringBaseModelPa.class);
	
	@Inject
	private static TableReportUtilityDao tableReportUtilityDao;
	
	@Inject
	private static StatementManagerDao statementManagerDao;
	
	@Inject
	private static FilterService filterService;
	
	private DwNorthSouthContainer nsContainer;
	private ToolBar toolbar;
	private Grid<StringBaseModel> grid;
	
	private Request request;
	
	private final FilterType type;
	private final TableReportDto report;
	private final ColumnDto column;
	private final SelectionPanel<M> selectionPanel;

	private PagingLoader<SelectorPanelLoadConfig<M>, PagingLoadResult<StringBaseModel>> loader;

	private PagingToolBar pagingToolBar;

	private DwToggleButton forceConsistent;

	private boolean beforeFirstSelection = true;

	private String executeToken;

	private boolean countFilterValues;


	public DistinctSelectorPanel(TableReportDto report, ColumnDto column, SelectionPanel<M> selectionPanel, FilterType type, String executeToken) {
		this.type = type;
		this.report = report;
		this.column = column;
		this.selectionPanel = selectionPanel;
		this.executeToken = executeToken;
		this.countFilterValues = "auto".equals(report.getEffectiveReportProperty(AvailableReportProperties.PROPERTY_DL_FILTER_COUNT_DEFAULT.getValue(), "auto"));
		
		toolbar = new DwToolBar();

		nsContainer = new DwNorthSouthContainer(); 
		nsContainer.setNorthWidget(toolbar);
		
		createGrid();
		createToolbar();
	}

	/**
	 * Close pending connections
	 */
	public void cleanup(){
		if(null != request){
			statementManagerDao.cancelStatement(executeToken);
			request.cancel();
			request = null;
		}
	}
	
	private void createGrid(){
		RpcProxy<SelectorPanelLoadConfig<M>, PagingLoadResult<StringBaseModel>> proxy = new RpcProxy<SelectorPanelLoadConfig<M>, PagingLoadResult<StringBaseModel>>() {   
			@Override
			public void load(SelectorPanelLoadConfig<M> loadConfig,
					AsyncCallback<PagingLoadResult<StringBaseModel>> callback) {
				grid.mask(BaseMessages.INSTANCE.loadingMsg());
				
				if(null != request){
					request.cancel();
					request = null;
				}
				
				loadConfig.setCaseSensitive(null != column.getFilter() && null != column.getFilter().isCaseSensitive() ? column.getFilter().isCaseSensitive() : true);
				
				request = tableReportUtilityDao.getDistinctValuesPaged(loadConfig, report, column, type, Boolean.TRUE.equals(forceConsistent.getValue()), countFilterValues, executeToken, callback);
			}   
		};   

		loader = new PagingLoader<SelectorPanelLoadConfig<M>, PagingLoadResult<StringBaseModel>>(proxy);

		loader.setRemoteSort(true);   

		ListStore<StringBaseModel> store = new ListStore<StringBaseModel>(new BasicObjectModelKeyProvider<StringBaseModel>());
		loader.addLoadHandler(new LoadResultListStoreBinding<SelectorPanelLoadConfig<M>, StringBaseModel, PagingLoadResult<StringBaseModel>>(store));
		loader.addLoaderHandler(new LoaderHandler<SelectorPanelLoadConfig<M>, PagingLoadResult<StringBaseModel>>(){
			@Override
			public void onBeforeLoad(BeforeLoadEvent<SelectorPanelLoadConfig<M>> event) {
			}

			@Override
			public void onLoadException(LoadExceptionEvent<SelectorPanelLoadConfig<M>> event) {
				
			}

			@Override
			public void onLoad(LoadEvent<SelectorPanelLoadConfig<M>, PagingLoadResult<StringBaseModel>> event) {
				grid.unmask();
			}
		});
		
		pagingToolBar = new DwPagingToolBar(50);  
//		pagingToolBar.setHeight(25);
		
		//remove some items from toolbar
		// TODO: GXT3
		pagingToolBar.remove(12); // Text
		pagingToolBar.remove(11); // FillToolItem
		// Refresh
		pagingToolBar.remove(9); // Separator
		pagingToolBar.remove(8); // Last
		
		pagingToolBar.remove(6); // SeparatorToolItem
		
		pagingToolBar.remove(3); // 
		pagingToolBar.remove(2); // |

		pagingToolBar.remove(0); // first |<
		pagingToolBar.setEnableOverflow(false);
		
		// ptbMessages.setAfterPageText("&nbsp; / {0}"); //$NON-NLS-1$
		//pagingToolBar.setMessages(ptbMessages);
		
//		((TextBox)pagingToolBar.getWidget(1)).setHeight("27px");
		
		SimpleComboBox<Integer> pageSizeSelector = new SimpleComboBox<Integer>(new LabelProvider<Integer>() {
			@Override
			public String getLabel(Integer item) {
				return String.valueOf(item);
			}
		});
		pageSizeSelector.setTriggerAction(TriggerAction.ALL);
		pageSizeSelector.add(50);
		pageSizeSelector.add(100);
		pageSizeSelector.add(250);
		pageSizeSelector.add(500);
		pageSizeSelector.add(1000);
		
		pageSizeSelector.setWidth(60);
		pageSizeSelector.setValue(pageSizeSelector.getStore().get(0));
		pageSizeSelector.setTypeAhead(false);

		pageSizeSelector.addSelectionHandler(new SelectionHandler<Integer>() {
			@Override
			public void onSelection(SelectionEvent<Integer> event) {
				pagingToolBar.setPageSize(event.getSelectedItem());
				pagingToolBar.first();
			}
		});

		pagingToolBar.insert(new FillToolItem(), pagingToolBar.getWidgetCount()-1);
		pagingToolBar.insert(pageSizeSelector, pagingToolBar.getWidgetCount()-1);
		
		pagingToolBar.bind(loader);
		
		List<ColumnConfig<StringBaseModel, ?>> columns = new ArrayList<ColumnConfig<StringBaseModel, ?>>();   
		ColumnConfig<StringBaseModel, String> valueConfig = new ColumnConfig<StringBaseModel, String>(sbmPa.value(), 250, BaseMessages.INSTANCE.value());  
		valueConfig.setMenuDisabled(true);
		valueConfig.setSortable(true);
		columns.add(valueConfig);   
		valueConfig.setCell(new AbstractCell<String>() {
			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					String value, SafeHtmlBuilder sb) {
				sb.appendEscaped(filterService.getStringValue(value, 0));
			}
		});

		grid = new Grid<StringBaseModel>(store, new ColumnModel<StringBaseModel>(columns));
		grid.addRowDoubleClickHandler(new RowDoubleClickHandler() {
			@Override
			public void onRowDoubleClick(RowDoubleClickEvent event) {
				selectionPanel.insertElement(grid.getStore().get(event.getRowIndex()));
			}
		});

		grid.setLoadMask(true);   
		grid.getView().setAutoExpandColumn(valueConfig);

		new GridDragSource<StringBaseModel>(grid){
			@Override
			protected void onDragDrop(DndDropEvent event) {
				// do nothing
			}
		};
		
		nsContainer.setWidget(grid);
		nsContainer.setSouthWidget(pagingToolBar);
	}

	private void createToolbar(){
		forceConsistent = new DwToggleButton();
		forceConsistent.addStyleName(RS_BTN_INFORM);
		forceConsistent.setIcon(BaseIcon.LINK);
		forceConsistent.setToolTip(FilterMessages.INSTANCE.linkedDescription());
		forceConsistent.setValue(true);
		forceConsistent.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				pagingToolBar.setActivePage(1);
				pagingToolBar.refresh();
			}
		});
		
		toolbar.add(forceConsistent);
		final TriggerField<StringBaseModel> textFilter = new TriggerField<StringBaseModel>(new PropertyEditor<StringBaseModel>() {

			@Override
			public String render(StringBaseModel object) {
				return object.getValue();
			}

			@Override
			public StringBaseModel parse(CharSequence text) throws ParseException {
				return new StringBaseModel(String.valueOf(text));
			}
		}){
			
		};
//		textFilter.setTriggerStyle("x-form-clear-trigger");
		textFilter.setValidateOnBlur(false);
		textFilter.setAutoValidate(true);
		textFilter.setValidationDelay(500);
		textFilter.addValidator(new Validator<StringBaseModel>() {
			@Override
			public List<EditorError> validate(Editor<StringBaseModel> editor, StringBaseModel valueModel) {
				String value = null == valueModel ? "" : String.valueOf(valueModel.getValue());
				
				SelectorPanelLoadConfig<M> plc = new SelectorPanelLoadConfig<M>();
				
				/* go back to first page */
				plc.setLimit(pagingToolBar.getPageSize());
				plc.setOffset(0);
				
				/* set filter */
				plc.setFilter(value.replace("*", "%").replace("?", "_")+"%"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
				
				/* load */
				loader.load(plc);
				
				return null;
			}
		});

		/* listener */
		textFilter.addTriggerClickHandler(new TriggerClickHandler() {
			@Override
			public void onTriggerClick(TriggerClickEvent event) {
				textFilter.clear();
				textFilter.validate();
			}
		});
		
		if(SqlTypes.isDateLikeType(column.getType()))
			toolbar.add(SeparatorTextLabel.createSmallText(FilterMessages.INSTANCE.noSearchPossible()), new BoxLayoutData(new Margins(0,0,10,0)));
		else 
			toolbar.add(textFilter);
	}

	public Widget getComponent() {
		return nsContainer;
	}


	public void onSelection() {
		if(!beforeFirstSelection)
			return;
		beforeFirstSelection = false;
		
		SelectorPanelLoadConfig<M> config = new SelectorPanelLoadConfig<M>();   
		config.setOffset(0);   
		config.setLimit(50);   
		config.setFilter("");
		
		loader.load(config);  
	}

	public void setForceConsistency(boolean force) {
		forceConsistent.setValue(force);		
	}

	public void hideForceConsistency() {
		forceConsistent.hide();
	}


}
