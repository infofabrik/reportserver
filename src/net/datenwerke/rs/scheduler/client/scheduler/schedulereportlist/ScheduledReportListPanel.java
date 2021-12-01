package net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.assistedinject.Assisted;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.core.client.util.Format;
import com.sencha.gxt.core.client.util.IconHelper;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.SortDir;
import com.sencha.gxt.data.shared.SortInfo;
import com.sencha.gxt.data.shared.SortInfoBean;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.RowDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.RowDoubleClickEvent.RowDoubleClickHandler;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent.SelectionChangedHandler;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar;
import com.sencha.gxt.widget.core.client.toolbar.SeparatorToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gf.client.history.HistoryUiService;
import net.datenwerke.gf.client.history.HistoryUiService.JumpToObjectCallback;
import net.datenwerke.gf.client.history.HistoryUiService.JumpToObjectResultCallback;
import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwToggleButton;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwBorderContainer;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwNorthSouthContainer;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.wizard.WizardDialog;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.resources.BaseResources;
import net.datenwerke.gxtdto.client.servercommunication.callback.NotamCallback;
import net.datenwerke.gxtdto.client.ui.helper.grid.keyvalue.KeyValueGridHelper;
import net.datenwerke.gxtdto.client.ui.helper.grid.keyvalue.KeyValueProperty;
import net.datenwerke.gxtdto.client.ui.helper.grid.keyvalue.KeyValuePropertyPA;
import net.datenwerke.gxtdto.client.ui.helper.misc.FloatingWrapper;
import net.datenwerke.gxtdto.client.ui.helper.nav.WestPropertiesDialog;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwHookableToolbar;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwPagingToolBar;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.gxtdto.client.utils.modelkeyprovider.BasicObjectModelKeyProvider;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.i18tools.FormatUiHelper;
import net.datenwerke.rs.core.client.reportmanager.ReportManagerTreeLoaderDao;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.scheduler.client.scheduler.SchedulerDao;
import net.datenwerke.rs.scheduler.client.scheduler.SchedulerUIModule;
import net.datenwerke.rs.scheduler.client.scheduler.SchedulerUiService;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportServerJobFilterDto;
import net.datenwerke.rs.scheduler.client.scheduler.locale.SchedulerMessages;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereport.ScheduleDialog;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereport.ScheduleDialog.DialogCallback;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.dto.ReportScheduleJobInformation;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.dto.ReportScheduleJobListInformation;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.dto.ReportScheduleJobListInformationPA;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.hooks.ActionLogEntryDetailHook;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.hooks.ScheduledReportListDetailToolbarHook;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.hooks.ScheduledReportListFilter;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.hooks.ScheduledReportListToolbarHook;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.hooks.ScheduledReportToolbarListFilter;
import net.datenwerke.rs.scheduler.client.scheduler.security.SchedulingAdminViewGenericTargetIdentifier;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.scheduler.client.scheduler.dto.JobExecutionStatusDto;
import net.datenwerke.scheduler.client.scheduler.dto.OutcomeDto;
import net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterConfigurationDto;
import net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterCriteriaDto;
import net.datenwerke.scheduler.client.scheduler.dto.history.ActionEntryDto;
import net.datenwerke.scheduler.client.scheduler.dto.history.ExecutionLogEntryDto;
import net.datenwerke.scheduler.client.scheduler.dto.history.JobEntryDto;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.ExecuteDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class ScheduledReportListPanel extends DwBorderContainer {

	private static final int GRID_PAGE_SIZE = 25;

	private static final String MAIN_TOOLBAR_NAME = "schedulerlist:main:toolbar";
	
	private final HookHandlerService hookHandler;
	private final LoginService loginService;
	private final SchedulerDao schedulerDao;
	private final SchedulerUiService schedulerService;
	private final Provider<DwHookableToolbar> toolbarProvider;
	private final KeyValueGridHelper gridHelper;
	
	private final Provider<ScheduleDialog> scheduleDialogProvider;
	
	private final Collection<ScheduledReportToolbarListFilter> tbFilters;
	private final Collection<ScheduledReportListFilter> filters;
	
	private DwContentPanel listPanel;
	private DwNorthSouthContainer listPanelNsContainer;
	
	private DwContentPanel detailPanel;
	private DwNorthSouthContainer detailPanelNsContainer;
	
	private BorderLayoutData listPanelLayoutData;
	
	private JobFilterConfigurationDto jobFilterConfig = new ReportServerJobFilterDto();
	private List<JobFilterCriteriaDto> addCriterions = new ArrayList<JobFilterCriteriaDto>();
	
	private ListStore<ReportScheduleJobListInformation> listStore;
	private PagingLoader<PagingLoadConfig, PagingLoadResult<ReportScheduleJobListInformation>> loader;
	private Grid<ReportScheduleJobListInformation> grid;
	private DwHookableToolbar mainToolbar;
	private PagingToolBar pagingToolbar;

	private DwHookableToolbar detailToolbar;

	private DwToggleButton archiveBtn;

	private static ReportScheduleJobListInformationPA reportScheduleInfoPa = GWT.create(ReportScheduleJobListInformationPA.class);
	private static KeyValuePropertyPA keyvpPa = GWT.create(KeyValuePropertyPA.class);
	
	private ListStore<ExecutionLogEntryDto> detailScheduleInfoStore = new ListStore<ExecutionLogEntryDto>(new BasicObjectModelKeyProvider<ExecutionLogEntryDto>());
	
	private boolean initialRequestDone = false;
	
	private final boolean displayExecutorColumn;
	private final boolean displayJobColumn;
	private final boolean displayScheduledByColumn;

	private final HistoryUiService historyService;

	private final ReportManagerTreeLoaderDao reportTreeLoader;

	private final ToolbarService toolbarService;

	private final SecurityUIService securityService;

	private String name;

	private FormatUiHelper formatUiHelper;

	
	@Inject
	public ScheduledReportListPanel(
		HookHandlerService hookHandler,
		LoginService loginService,
		SchedulerDao schedulerDao,
		SchedulerUiService schedulerService,
		Provider<DwHookableToolbar> toolbarProvider,
		KeyValueGridHelper gridHelper,
		HistoryUiService historyService,
		ReportManagerTreeLoaderDao reportTreeLoader,
		ToolbarService toolbarService,
		SecurityUIService securityService,
		FormatUiHelper formatUiHelper,
		Provider<ScheduleDialog> scheduleDialogProvider,
		
		@Assisted String name,
		@Assisted("displayExecutorColumn") boolean displayExecutorColumn,
		@Assisted("displayScheduledByColumn") boolean displayScheduledByColumn
		){
		
		/* store objects */
		this.hookHandler = hookHandler;
		this.loginService = loginService;
		this.schedulerDao = schedulerDao;
		this.schedulerService = schedulerService;
		this.toolbarProvider = toolbarProvider;
		this.gridHelper = gridHelper;
		this.historyService = historyService;
		this.reportTreeLoader = reportTreeLoader;
		this.toolbarService = toolbarService;
		this.securityService = securityService;
		this.formatUiHelper = formatUiHelper;
		this.scheduleDialogProvider = scheduleDialogProvider;
		
		this.displayJobColumn = true;
		this.displayExecutorColumn = displayExecutorColumn;
		this.displayScheduledByColumn = displayScheduledByColumn;
		this.name = name;
		
		tbFilters = new ArrayList<ScheduledReportToolbarListFilter>();
		for(ScheduledReportToolbarListFilter filter : hookHandler.getHookers(ScheduledReportToolbarListFilter.class))
			if(filter.appliesTo(name))
				tbFilters.add(filter);
		
		filters = new ArrayList<ScheduledReportListFilter>();
		for(ScheduledReportListFilter filter : hookHandler.getHookers(ScheduledReportListFilter.class))
			if(filter.appliesTo(name))
				filters.add(filter);
		
		/* init */
		initializeUI();
	}

	private void initializeUI() {
		addClassName("rs-schedule-view");
		
		/* create store */
		createStore();
		
		/* create toolbar and add it */
		mainToolbar = toolbarProvider.get();
		mainToolbar.setContainerName(MAIN_TOOLBAR_NAME);
		initToolbar();

		/* create panels */
		createListPanel();
		createDetailPanel();
		
		/* paging */
		addPagingToolbar();
		
		/* layoutdata */
		listPanelLayoutData = new BorderLayoutData(0.6f);
		listPanelLayoutData.setMargins(new Margins(0, 1, 0, 0));
		listPanelLayoutData.setSplit(true);
		listPanelLayoutData.setMaxSize(Integer.MAX_VALUE);
		
		/* add panels to main panel */
		String userPropSplit = loginService.getCurrentUser().getUserPropertyValue(SchedulerUIModule.USER_PROPERTY_VIEW_VERTICAL_SPLIT);
		if(null == userPropSplit || "false".equals(userPropSplit))
			setWestWidget(listPanel, listPanelLayoutData);
		else
			setNorthWidget(listPanel, listPanelLayoutData);
		
		setCenterWidget(detailPanel);
	}
	
	
	
	public void load(){
		/* load */
		loader.load();
	}
	
	private void initToolbar() {
	   hookHandler.getHookers(ScheduledReportListToolbarHook.class)
	      .forEach(hooker -> hooker.statusBarToolbarHook_addLeft(mainToolbar, this));
		
		mainToolbar.addBaseHookersLeft();
		
		/* filters */
		if(! filters.isEmpty()){
			final DwContentPanel filterPanel = DwContentPanel.newInlineInstance();
			final VerticalLayoutContainer container = new VerticalLayoutContainer();
			filterPanel.setWidget(container);
			
			for (Iterator<ScheduledReportListFilter> iterator = filters.iterator(); iterator.hasNext();) {
				Iterable<Widget> widgets = iterator.next().getFilter(this);
				if(null != widgets)
				   widgets.forEach(container::add);
			}

			
			final DwTextButton filterBtn = toolbarService.createUnstyledToolbarItem(SchedulerMessages.INSTANCE.filter(), BaseIcon.FILTER);
			mainToolbar.add(filterBtn, new BoxLayoutData(new Margins(0, 5, 0, 0)));
			
			filterBtn.addSelectHandler(event -> {
				FloatingWrapper wrapper = new FloatingWrapper(filterPanel);
				wrapper.setBorders(true);
				wrapper.show(filterBtn);
			});
		}
		
		final DwTextButton newBtn = toolbarService.createUnstyledToolbarItem(SchedulerMessages.INSTANCE.newJob(), BaseIcon.REPORT);
      mainToolbar.add(newBtn, new BoxLayoutData(new Margins(0, 5, 0, 0)));
      
      newBtn.addSelectHandler( event -> {
         ScheduleDialog dialog = scheduleDialogProvider.get();
         
         dialog.displayDialog(Optional.empty(), Collections.emptyList(), new ArrayList<>(), new DialogCallback() {
            @Override
            public void finished(ReportScheduleDefinition configDto, final WizardDialog dialog) {
               dialog.mask(BaseMessages.INSTANCE.storingMsg());
               schedulerDao.schedule(configDto, new NotamCallback<Void>(SchedulerMessages.INSTANCE.scheduled()){
                  @Override
                  public void doOnSuccess(Void result) {
                     dialog.hide();
                  }
                  @Override
                  public void doOnFailure(Throwable caught) {
                     super.doOnFailure(caught);
                     dialog.unmask();
                  }
               });                  
            }
         });
      });

		
		mainToolbar.add(new FillToolItem());
		
		hookHandler.getHookers(ScheduledReportListToolbarHook.class)
		   .forEach(hooker -> hooker.statusBarToolbarHook_addRight(mainToolbar, this));
		
		mainToolbar.addBaseHookersRight();
		
		/* additional filters */
		for (Iterator<ScheduledReportToolbarListFilter> iterator = tbFilters.iterator(); iterator.hasNext();) {
			boolean addSep = iterator.next().addToToolbar(this, mainToolbar);
			if(iterator.hasNext() && addSep)
				mainToolbar.add(new SeparatorToolItem());
		}

		mainToolbar.add(new SeparatorToolItem());
		
		archiveBtn = new DwToggleButton(SchedulerMessages.INSTANCE.archive());
		archiveBtn.setIcon(BaseIcon.ARCHIVE);
		archiveBtn.setToolTip(SchedulerMessages.INSTANCE.displayArchived());
		mainToolbar.add(archiveBtn);
		
		archiveBtn.addSelectHandler(event -> reload());
	}
	
	public String getName() {
		return name;
	}
	
	private void createStore() {
		/* init filter */
		jobFilterConfig.setActive(true);
		
		/* create proxy */
		RpcProxy<PagingLoadConfig, PagingLoadResult<ReportScheduleJobListInformation>> proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<ReportScheduleJobListInformation>>() {   

			@Override
			public void load(
					PagingLoadConfig loadConfig,
					final AsyncCallback<PagingLoadResult<ReportScheduleJobListInformation>> callback) {
				if(loadConfig instanceof PagingLoadConfig){
					PagingLoadConfig plc = (PagingLoadConfig) loadConfig;
					schedulerService.transformLoadConfig(plc, jobFilterConfig);
					schedulerDao.getReportJobList(jobFilterConfig, addCriterions, new AsyncCallback<PagingLoadResult<ReportScheduleJobListInformation>>() {
						@Override
						public void onSuccess(
								PagingLoadResult<ReportScheduleJobListInformation> result) {
							initialRequestDone = true;
							callback.onSuccess(result);
						}
						public void onFailure(Throwable caught) {
							initialRequestDone = true;
							callback.onFailure(caught);
						};
					});
				}
			}   
		};   
		
		/* create loader and bind it */
		loader = new PagingLoader<PagingLoadConfig, PagingLoadResult<ReportScheduleJobListInformation>>(proxy);
		if(loader.getSortInfo().isEmpty()){
			SortInfo sortInfo = new SortInfoBean();
			sortInfo.setSortDir(SortDir.DESC);
			sortInfo.setSortField("jobId");
			loader.addSortInfo(sortInfo);
		}
		
		loader.setRemoteSort(true);
		resetConfig();
		
		listStore = new ListStore<ReportScheduleJobListInformation>(reportScheduleInfoPa.dtoId());
		loader.addLoadHandler(new LoadResultListStoreBinding<PagingLoadConfig, ReportScheduleJobListInformation, PagingLoadResult<ReportScheduleJobListInformation>>(listStore));
	}
	
	private void addPagingToolbar() {
		pagingToolbar = new DwPagingToolBar(GRID_PAGE_SIZE);
		
		pagingToolbar.bind(loader);
		
		listPanelNsContainer.setSouthWidget(pagingToolbar);
	}

	private void createDetailPanel() {
		detailPanel = new DwContentPanel();
		detailPanel.addClassName("rs-schedule-view-detail");
		detailPanel.setHeading(SchedulerMessages.INSTANCE.detailPanelTitle());
		
		detailPanelNsContainer = new DwNorthSouthContainer();
		detailPanel.setWidget(detailPanelNsContainer);
		
		detailToolbar = toolbarProvider.get();
		detailToolbar.setContainerName(MAIN_TOOLBAR_NAME);
		detailPanelNsContainer.setNorthWidget(detailToolbar);
		
		ToolBar dummyToolbar = new DwToolBar();
		DwTextButton dummyBtn = new DwTextButton(" ", IconHelper.getImageResource(BaseResources.INSTANCE.emptyImage().getSafeUri(),16,16));
		dummyToolbar.add(new FillToolItem());
		dummyToolbar.add(dummyBtn);
		detailPanelNsContainer.setSouthWidget(dummyToolbar);
	}

	private void createListPanel() {
		listPanel = new DwContentPanel();
		listPanel.addClassName("rs-schedule-view-list");
		listPanel.setHeading(SchedulerMessages.INSTANCE.listPanelHeading());
		
		listPanelNsContainer = new DwNorthSouthContainer();
		listPanel.setWidget(listPanelNsContainer);
		
		listPanelNsContainer.setNorthWidget(mainToolbar);
		
		/* create grid */
		grid = new Grid<ReportScheduleJobListInformation>(listStore, schedulerService.createReportScheduleListColumnModel(displayJobColumn, displayExecutorColumn, displayScheduledByColumn));
		grid.setLoader(loader);
		grid.setLoadMask(true);
		grid.getView().setAutoExpandColumn(grid.getColumnModel().findColumnConfig(reportScheduleInfoPa.reportName().getPath()));
		grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		grid.getSelectionModel().addSelectionChangedHandler(new SelectionChangedHandler<ReportScheduleJobListInformation>() {
			@Override
			public void onSelectionChanged(
					SelectionChangedEvent<ReportScheduleJobListInformation> event) {
				if(event.getSelection().isEmpty())
					return;
				
				final ReportScheduleJobListInformation selected = event.getSelection().get(0);
				if(null == selected)
					return;
				
				displayDetails(selected);
			}
		});
		listPanelNsContainer.setWidget(grid);
		
		/* context menu */
		Menu cMenu = new DwMenu();
		cMenu.add(historyService.getJumpToMenuItem(new JumpToObjectCallback(){
			private Long reportId;
			
			@Override
			public void getDtoTarget(final JumpToObjectResultCallback callback) {
				ReportScheduleJobListInformation data = grid.getSelectionModel().getSelectedItem();
				if(null == data)
					return;
				reportId = data.getReportId();
				if(null == reportId)
					return;
				
				reportTreeLoader.loadNodeById(reportId, new RsAsyncCallback<AbstractNodeDto>(){
					@Override
					public void onSuccess(AbstractNodeDto result) {
						if(null != result)
							callback.setResult(result);
					}
				});
			}

			@Override
			public boolean haveToUpdate() {
				ReportScheduleJobListInformation data = grid.getSelectionModel().getSelectedItem();
				if(null == data || null == reportId)
					return true;
				
				return ! reportId.equals(data.getReportId());
			}			
		}));

		grid.setContextMenu(cMenu);
	}
	
	protected void displayDetails(final ReportScheduleJobListInformation selected) {
		mask(BaseMessages.INSTANCE.loadingMsg());
		schedulerDao.loadFullScheduleInformation(selected, new RsAsyncCallback<ReportScheduleJobInformation>(){
			@Override
			public void onSuccess(ReportScheduleJobInformation result) {
				unmask();
				displayDetail(selected, result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				unmask();
				detailPanelNsContainer.clear();
				detailToolbar.clear();
			}

		});
	}

	public void reload() {
		if(! initialRequestDone)
			return;

		listStore.clear();
		
		resetConfig();
		
		detailPanel.clear();
		detailToolbar.clear();
		
		pagingToolbar.refresh();
	}

	private void resetConfig() {
		jobFilterConfig = new ReportServerJobFilterDto();
		addCriterions = new ArrayList<JobFilterCriteriaDto>();
		
		jobFilterConfig.setActive(null == archiveBtn || ! archiveBtn.getValue());
		jobFilterConfig.setInActive(null != archiveBtn && archiveBtn.getValue());
		
		for(ScheduledReportToolbarListFilter filter : tbFilters)
			filter.configure(this, jobFilterConfig, addCriterions);
		for(ScheduledReportListFilter filter : filters)
			filter.configure(this, jobFilterConfig, addCriterions);
	}

	private void displayDetail(ReportScheduleJobListInformation selected, ReportScheduleJobInformation result) {
		detailPanelNsContainer.clear();
		detailToolbar.clear();
		
		VerticalLayoutContainer wrapper = new VerticalLayoutContainer();
		wrapper.setScrollMode(ScrollMode.AUTOY);
		
		detailPanelNsContainer.setNorthWidget(detailToolbar);
		
		DwContentPanel propertiesPanel = new DwContentPanel();
		propertiesPanel.setLightHeader();
		propertiesPanel.setHeading(SchedulerMessages.INSTANCE.propertiesLabel());
		wrapper.add(propertiesPanel, new VerticalLayoutData(1,-1, new Margins(5)));
		
		Component grid = getDetailGrid(selected, result);
		propertiesPanel.add(grid);
		
		DwContentPanel nextSchedulePanel = new DwContentPanel();
		nextSchedulePanel.setLightHeader();
		nextSchedulePanel.setHeading(SchedulerMessages.INSTANCE.scheduleListLabel());
		wrapper.add(nextSchedulePanel, new VerticalLayoutData(1,-1, new Margins(5)));
		
		Component nextScheduleGrid = getCurrentScheduleGrid(selected, result);
		nextSchedulePanel.add(nextScheduleGrid);
		
		for(ScheduledReportListDetailToolbarHook hooker : hookHandler.getHookers(ScheduledReportListDetailToolbarHook.class))
			hooker.statusBarToolbarHook_addLeft(detailToolbar, selected, result, this);
		
		detailToolbar.addBaseHookersLeft();
		
		detailToolbar.add(new FillToolItem());
		
		for(ScheduledReportListDetailToolbarHook hooker : hookHandler.getHookers(ScheduledReportListDetailToolbarHook.class))
			hooker.statusBarToolbarHook_addRight(detailToolbar, selected, result, this);
		
		detailToolbar.addBaseHookersRight();
		
		detailPanelNsContainer.add(wrapper);
		
		detailPanel.setWidget(detailPanelNsContainer);
		
		detailPanel.forceLayout();
	}
	
	public Widget createLabel(String text) {
		Label label = new Label(text);
		label.addStyleName("schedule-detail-label");
		
		return label;
	}
	
	private String formatDate(Date date){
		try {
			return formatUiHelper.getShortDateTimeFormat().format(date);
		}catch(IllegalArgumentException e){
			return "?";
		}
	}

	private Grid<ExecutionLogEntryDto> getCurrentScheduleGrid(final ReportScheduleJobListInformation selected, ReportScheduleJobInformation result) {
		setDataInDetailStore(result);
		
		List<ColumnConfig<ExecutionLogEntryDto,?>> colConfig = new ArrayList<ColumnConfig<ExecutionLogEntryDto,?>>();
		
		ColumnConfig<ExecutionLogEntryDto,ExecutionLogEntryDto> iconColumn = new ColumnConfig<ExecutionLogEntryDto,ExecutionLogEntryDto>(new IdentityValueProvider<ExecutionLogEntryDto>("__icon"), 50);
		iconColumn.setMenuDisabled(true);
		iconColumn.setCell(new AbstractCell<ExecutionLogEntryDto>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					ExecutionLogEntryDto model, SafeHtmlBuilder sb) {
				if(model instanceof DatePropertyModel)
					sb.append(BaseIcon.HOURGLASS_O.toSafeHtml());
				if(model instanceof ExecutionLogEntryDto){
					ExecutionLogEntryDto entry = (ExecutionLogEntryDto) model;
					if(OutcomeDto.SUCCESS == entry.getOutcome())
						sb.append(BaseIcon.CHECK.toSafeHtml());
					else if(OutcomeDto.FAILURE == entry.getOutcome())
						sb.append(BaseIcon.ERROR.toSafeHtml());
					else if(OutcomeDto.VETO == entry.getOutcome() || OutcomeDto.ACTION_VETO == entry.getOutcome())
						sb.append(BaseIcon.FLAG.toSafeHtml());
					else if(OutcomeDto.EXECUTING== entry.getOutcome())
						sb.append(BaseIcon.ROTATE_RIGHT.toSafeHtml());
				}
			}
		});
		colConfig.add(iconColumn);
		
		ColumnConfig<ExecutionLogEntryDto, ExecutionLogEntryDto> dateColumn = new ColumnConfig<ExecutionLogEntryDto, ExecutionLogEntryDto>(new IdentityValueProvider<ExecutionLogEntryDto>("__date"), 120, SchedulerMessages.INSTANCE.gridSchedule());
		dateColumn.setMenuDisabled(true);
		dateColumn.setCell(new AbstractCell<ExecutionLogEntryDto>() {
			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					ExecutionLogEntryDto model, SafeHtmlBuilder sb) {
				if(model instanceof DatePropertyModel)
					sb.appendEscaped(formatDate(((DatePropertyModel)model).getDate()));
				if(model instanceof ExecutionLogEntryDto){
					ExecutionLogEntryDto entry = (ExecutionLogEntryDto) model;
					if(null != entry.getScheduledStart())
						sb.appendEscaped(formatDate(entry.getScheduledStart()));
				}
			}
		});
		colConfig.add(dateColumn);
		
		ColumnConfig<ExecutionLogEntryDto, ExecutionLogEntryDto> startColumn = new ColumnConfig<ExecutionLogEntryDto, ExecutionLogEntryDto>(new IdentityValueProvider<ExecutionLogEntryDto>("__start"), 120, SchedulerMessages.INSTANCE.gridStarted());
		startColumn.setMenuDisabled(true);
		startColumn.setCell(new AbstractCell<ExecutionLogEntryDto>() {
			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					ExecutionLogEntryDto model, SafeHtmlBuilder sb) {
				if(model instanceof DatePropertyModel)
					return;
				if(model instanceof ExecutionLogEntryDto){
					ExecutionLogEntryDto entry = (ExecutionLogEntryDto) model;
					if(null != entry.getStart())
						sb.appendEscaped(formatDate(entry.getStart()));
				}
			}
		});
		colConfig.add(startColumn);
		
		ColumnConfig<ExecutionLogEntryDto,ExecutionLogEntryDto> infoColumn = new ColumnConfig<ExecutionLogEntryDto,ExecutionLogEntryDto>(new IdentityValueProvider<ExecutionLogEntryDto>("__info"), 200, SchedulerMessages.INSTANCE.gridInfoLabel());
		infoColumn.setMenuDisabled(true);
		infoColumn.setCell(new AbstractCell<ExecutionLogEntryDto>() {
			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					ExecutionLogEntryDto value, SafeHtmlBuilder sb) {
				if(null == value.getOutcome())
					return;
				switch(value.getOutcome()){
				case FAILURE:
					sb.appendEscaped(null != value.getBadErrorDescription() ? value.getBadErrorDescription() : "");
					break;
				case VETO:
				case ACTION_VETO:
					sb.appendEscaped(null != value.getVetoExplanation() ? value.getVetoExplanation() : "");
					break;
				case EXECUTING:
					sb.appendEscaped(SchedulerMessages.INSTANCE.executing());
					break;
				case SUCCESS:
					break;
				}
			}
		});
		colConfig.add(infoColumn);
		
		final Grid<ExecutionLogEntryDto> grid = new Grid<ExecutionLogEntryDto>(detailScheduleInfoStore, new ColumnModel<ExecutionLogEntryDto>(colConfig));
		grid.getView().setAutoExpandColumn(infoColumn);

		grid.addRowDoubleClickHandler(new RowDoubleClickHandler() {
			@Override
			public void onRowDoubleClick(RowDoubleClickEvent event) {
				ExecutionLogEntryDto data = detailScheduleInfoStore.get(event.getRowIndex());
				if(data instanceof DatePropertyModel)
					return;
				
				schedulerDao.loadFullDetailsFor(selected.getJobId(), (ExecutionLogEntryDto)data, new RsAsyncCallback<ExecutionLogEntryDto>(){
					@Override
					public void onSuccess(ExecutionLogEntryDto result) {
						displayDetailsFor(selected, result);
					}
				});
			}
		});
		
		
		return grid;
	}

	protected void displayDetailsFor(ReportScheduleJobListInformation selected, ExecutionLogEntryDto data) {
		WestPropertiesDialog dialog = new WestPropertiesDialog();
		dialog.setHeading(SchedulerMessages.INSTANCE.scheduleEntryDetailHeader(selected.getReportName(), selected.getReportId(), selected.getJobId()));
		
		Component mainCard = getDetailCardFor(data);
		dialog.addCard(SchedulerMessages.INSTANCE.detailDialogMainCardHeader(), BaseIcon.EXCLAMATION, mainCard);
		
		for(ActionEntryDto action : data.getActionEntries()){
			for(ActionLogEntryDetailHook hooker : hookHandler.getHookers(ActionLogEntryDetailHook.class)){
				if(hooker.consumes(action)){
					Widget actionCard = hooker.getCard(action, this); 
						
					dialog.addCard(action.getActionName(), BaseIcon.FUTBOL_O, actionCard);
					break;
				}
			}
		}
	
		dialog.show();
	}

	protected Component getDetailCardFor(ExecutionLogEntryDto data) {
		VerticalLayoutContainer container = new VerticalLayoutContainer();
		
		DwContentPanel executionLogEntryPanel = new DwContentPanel();
		executionLogEntryPanel.setLightDarkStyle();
		executionLogEntryPanel.setHeading(SchedulerMessages.INSTANCE.executionLogEntryLabel());
		
		VerticalLayoutContainer executionLogEntryWrapper = new VerticalLayoutContainer();
		executionLogEntryPanel.add(executionLogEntryWrapper);

		container.add(executionLogEntryPanel, new VerticalLayoutData(1, -1, new Margins(5)));
		
		ListStore<KeyValueProperty> entryKeyValueStore = new ListStore<KeyValueProperty>(keyvpPa.id());
		entryKeyValueStore.add(new KeyValueProperty(SchedulerMessages.INSTANCE.scheduled(), 
				null != data.getScheduledStart() ?
						formatDate(data.getScheduledStart()) :
						""));
		entryKeyValueStore.add(new KeyValueProperty(SchedulerMessages.INSTANCE.started(), 
				null != data.getStart() ?
						formatDate(data.getStart()) :
						""));
		entryKeyValueStore.add(new KeyValueProperty(SchedulerMessages.INSTANCE.ended(), 
				null != data.getEnd() ?
						formatDate(data.getEnd()) :
						""));
		switch(data.getOutcome()){
		case SUCCESS:
			entryKeyValueStore.add(new KeyValueProperty(SchedulerMessages.INSTANCE.outcomeLabel(), SchedulerMessages.INSTANCE.success() ));
			break;
		case FAILURE:
			entryKeyValueStore.add(new KeyValueProperty(SchedulerMessages.INSTANCE.outcomeLabel(), SchedulerMessages.INSTANCE.failure() ));
			break;
		case VETO:
		case ACTION_VETO:
			entryKeyValueStore.add(new KeyValueProperty(SchedulerMessages.INSTANCE.outcomeLabel(), SchedulerMessages.INSTANCE.veto() ));
			entryKeyValueStore.add(new KeyValueProperty(SchedulerMessages.INSTANCE.vetoDescriptionLabel(), data.getVetoExplanation() ));
			break;
		case EXECUTING:
			entryKeyValueStore.add(new KeyValueProperty(SchedulerMessages.INSTANCE.outcomeLabel(), SchedulerMessages.INSTANCE.executing() ));
			break;
		}
		
		Grid<KeyValueProperty> entryGrid = gridHelper.create(entryKeyValueStore);
		executionLogEntryWrapper.add(entryGrid, new VerticalLayoutData(1,-1));
		if(null != data.getBadErrorDescription() && ! "".equals(data.getBadErrorDescription())){
			TextArea area = new TextArea();
			area.setWidth(400);
			area.setHeight(200);
			area.setValue(data.getBadErrorDescription());
			
			executionLogEntryWrapper.add(createLabel(SchedulerMessages.INSTANCE.errorDescriptionLabel()), new VerticalLayoutContainer.VerticalLayoutData(1,-1, new Margins(5,0,0,0)));
			executionLogEntryWrapper.add(area, new VerticalLayoutContainer.VerticalLayoutData(1,-1));
		}
		
		if (OutcomeDto.VETO == data.getOutcome()){
			TextArea area = new TextArea();
			area.setWidth(400);
			area.setHeight(200);
			area.setValue(data.getVetoExplanation());
			
			executionLogEntryWrapper.add(createLabel(SchedulerMessages.INSTANCE.vetoDescriptionLabel()), new VerticalLayoutContainer.VerticalLayoutData(1,-1, new Margins(5,0,0,0)));
			executionLogEntryWrapper.add(area, new VerticalLayoutContainer.VerticalLayoutData(1,-1));
		}
		
		
		JobEntryDto job = data.getJobEntry();
		if(null != job){
			ListStore<KeyValueProperty> jobKeyValueStore = new ListStore<KeyValueProperty>(keyvpPa.id());
			switch(job.getOutcome()){
			case SUCCESS:
				jobKeyValueStore.add(new KeyValueProperty(SchedulerMessages.INSTANCE.outcomeLabel(), SchedulerMessages.INSTANCE.success() ));
				break;
			case FAILURE:
				jobKeyValueStore.add(new KeyValueProperty(SchedulerMessages.INSTANCE.outcomeLabel(), SchedulerMessages.INSTANCE.failure() ));
				break;
			case VETO:
				jobKeyValueStore.add(new KeyValueProperty(SchedulerMessages.INSTANCE.outcomeLabel(), SchedulerMessages.INSTANCE.veto() ));
				break;
			case EXECUTING:
				jobKeyValueStore.add(new KeyValueProperty(SchedulerMessages.INSTANCE.outcomeLabel(), SchedulerMessages.INSTANCE.executing() ));
				break;
			case ACTION_VETO:
				break;
			}
			
			
			DwContentPanel jobDataPanel = new DwContentPanel();
			jobDataPanel.setLightDarkStyle();
			jobDataPanel.setHeading(SchedulerMessages.INSTANCE.jobDataLabel());
			VerticalLayoutContainer jobDataWrapper = new VerticalLayoutContainer();
			jobDataPanel.add(jobDataWrapper);
			
			Grid<KeyValueProperty> grid = gridHelper.create(jobKeyValueStore);
			container.add(jobDataPanel, new VerticalLayoutContainer.VerticalLayoutData(1,-1, new Margins(5)));
			jobDataWrapper.add(grid, new VerticalLayoutData(1,-1));
			
			if(OutcomeDto.FAILURE == job.getOutcome()){
				TextArea area = new TextArea();
				area.setWidth(400);
				area.setHeight(200);
				area.setValue(job.getErrorDescription());
				
				jobDataWrapper.add(createLabel(SchedulerMessages.INSTANCE.errorDescriptionLabel()));
				jobDataWrapper.add(area, new VerticalLayoutContainer.VerticalLayoutData(1,-1, new Margins(5)));
			} 
		}
		
		return container;
	}

	public void setDataInDetailStore(final ReportScheduleJobInformation result) {
		detailScheduleInfoStore.clear();
		
		result.getLastExecutedEntries()
		   .forEach(detailScheduleInfoStore::add);
		
		result.getNextPlannedEntries()
		   .forEach(date -> detailScheduleInfoStore.add(new DatePropertyModel(date)));
	}
	
	private Grid<KeyValueProperty> getDetailGrid( final ReportScheduleJobListInformation selected, final ReportScheduleJobInformation result) {
		ListStore<KeyValueProperty> store = new ListStore<KeyValueProperty>(keyvpPa.id());
		if(null != result.getScheduleDefinition().getReport()){
			ReportDto report = result.getScheduleDefinition().getReport();
			store.add(new KeyValueProperty(SchedulerMessages.INSTANCE.report(), report.getName()));
			store.add(new KeyValueProperty(SchedulerMessages.INSTANCE.gridIdLabel(), report.getId().toString()));
			store.add(new KeyValueProperty(SchedulerMessages.INSTANCE.jobId(), selected.getJobId().toString()));
			store.add(new KeyValueProperty(SchedulerMessages.INSTANCE.jobTitle(), selected.getJobTitle()));
			String description = null == selected.getJobDescription() ? SchedulerMessages.INSTANCE.noDescription() : Format.ellipse(selected.getJobDescription(), 270);
			store.add(new KeyValueProperty(SchedulerMessages.INSTANCE.jobDescription(), description));
			if (!selected.isExecutorDeleted()) 
				store.add(new KeyValueProperty(SchedulerMessages.INSTANCE.executor(), selected.getExecutorName()));
			else
				store.add(new KeyValueProperty(SchedulerMessages.INSTANCE.executor(), SchedulerMessages.INSTANCE.deletedExecutor()));
			if (!selected.isScheduledByDeleted()) 
				store.add(new KeyValueProperty(SchedulerMessages.INSTANCE.scheduledBy(), selected.getScheduledByName()));
			else
				store.add(new KeyValueProperty(SchedulerMessages.INSTANCE.scheduledBy(), SchedulerMessages.INSTANCE.deletedScheduledBy()));
			store.add(new KeyValueProperty(SchedulerMessages.INSTANCE.nrOfSuccessfulExecutions(), String.valueOf(result.getNrOfSuccessfulExecutions())));
			store.add(new KeyValueProperty(SchedulerMessages.INSTANCE.nrOfFailedExecutions(), String.valueOf(result.getNrOfFailedExecutions())));
			store.add(new KeyValueProperty(SchedulerMessages.INSTANCE.nrOfVetoedExecutions(), String.valueOf(result.getNrOfVetoedExecutions())));
			store.add(new KeyValueProperty(SchedulerMessages.INSTANCE.jobExecutionStatus(), String.valueOf(result.getExecutionStatus())){
				@Override
				public void onRowDoubleClick(RowDoubleClickEvent event) {
					UserDto user = loginService.getCurrentUser();
					if(! result.isOwner(user) && ! securityService.hasRight(SchedulingAdminViewGenericTargetIdentifier.class, ExecuteDto.class))
						return;
					
					if(! result.getExecutionStatus().equals(JobExecutionStatusDto.BAD_FAILURE))
						return;
					
						
					MenuItem item = new DwMenuItem(SchedulerMessages.INSTANCE.clearBadFailure(),BaseIcon.CHECK);
					item.addSelectionHandler(new SelectionHandler<Item>() {
						
						@Override
						public void onSelection(SelectionEvent<Item> event) {
							schedulerDao.clearErrorStatus(selected.getJobId(), new RsAsyncCallback<Void>(){
								@Override
								public void onSuccess(Void result) {
									displayDetails(selected);
								}
							});
						}
					});
					
					FloatingWrapper wrapper = new FloatingWrapper(item);
					wrapper.showAt(event.getEvent().getClientX(), event.getEvent().getClientY());
				}
			});
		}
		
		Grid<KeyValueProperty> grid = gridHelper.create(store);
		
		return grid;
	}
	
	public JobFilterConfigurationDto getJobFilterConfig() {
		return jobFilterConfig;
	}
	
	public boolean isDisplayExecutorColumn() {
		return displayExecutorColumn;
	}

	public boolean isDisplayJobColumn() {
		return displayJobColumn;
	}

	class DatePropertyModel extends ExecutionLogEntryDto{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private Date date;
		
		public DatePropertyModel(Date date){
			setDate(date);
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public Date getDate() {
			return date;
		}
		
		
	}


}
