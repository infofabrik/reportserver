package net.datenwerke.rs.dashboard.client.dashboard.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.cell.core.client.ButtonCell.ButtonArrowAlign;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.core.client.Style.HideMode;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.dnd.core.client.DND.Feedback;
import com.sencha.gxt.dnd.core.client.GridDragSource;
import com.sencha.gxt.dnd.core.client.GridDropTarget;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.container.CardLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.BeforeShowEvent;
import com.sencha.gxt.widget.core.client.event.BeforeShowEvent.BeforeShowHandler;
import com.sencha.gxt.widget.core.client.event.BlurEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.DialogHideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.menu.SeparatorMenuItem;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.selection.TreeSelectionPopup;
import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwConfirmMessageBox;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.selection.SelectionMode;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.theme.CssClassConstant;
import net.datenwerke.gxtdto.client.ui.helper.nav.NavigationPanelHelper;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.gxtdto.client.utils.modelkeyprovider.BasicObjectModelKeyProvider;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.dashboard.client.dashboard.DashboardDao;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardContainerDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardNodeDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardReferenceDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.DashboardDtoDec;
import net.datenwerke.rs.dashboard.client.dashboard.dto.pa.DashboardDtoPA;
import net.datenwerke.rs.dashboard.client.dashboard.hooks.DashboardToolbarHook;
import net.datenwerke.rs.dashboard.client.dashboard.locale.DashboardMessages;
import net.datenwerke.rs.dashboard.client.dashboard.provider.annotations.DashboardTreeDashboards;
import net.datenwerke.rs.dashboard.client.dashboard.security.DashboardViewGenericTargetIdentifier;
import net.datenwerke.rs.dashboard.client.dashboard.ui.DashboardView.EditSuccessCallback;
import net.datenwerke.rs.dashboard.client.dashboard.ui.helper.SFFCDashboardLayout;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.WriteDto;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class DashboardMainComponent extends DwContentPanel implements DashboardContainer{

	@CssClassConstant
	public static final String CSS_NAME = "rs-db";
	
	@CssClassConstant
	public static final String CSS_NAV_NAME = "rs-db-nav";

	@CssClassConstant
	public static final String CSS_MAIN_NAME = "rs-db-main";
	
	private final DashboardDao dao;
	private final HookHandlerService hookHandler;
	private final NavigationPanelHelper navPanelHelper;
	private final Provider<DashboardView> viewProvider;
	private final ToolbarService toolbarService;
	private final DadgetCatalogFactory catalogFactory;
	private final UITree dashboardTree;
	private final SecurityUIService securityService;
	
	private CardLayoutContainer cardContainer;
	
	private Map<DashboardDto, DashboardView> viewMap = new HashMap<DashboardDto, DashboardView>();
	
	private ListStore<DashboardDto> dbStore;
	
	private DashboardDto dashboard;
	
	private boolean loaded = false;
	private DwToolBar toolbar;
	private DwTextButton addDadgetBtn;
	private boolean isProtected;
	private VerticalLayoutContainer nsContainer;

	private ComboBox<DashboardDto> dashboardSelector;

	private DwMenuItem resetReferencedDashboardItem;
	
	private boolean editDashboardOnSelect;

	private boolean canEdit;

	private DwTextButton toolsBtn;
	
	private int reloadCounter = 0;
	
	private TreeSelectionPopup importDashboardPopup;
				
	@Inject
	public DashboardMainComponent(
		DashboardDao dao,
		HookHandlerService hookHandler,
		Provider<DashboardView> viewProvider,
		NavigationPanelHelper nagPanelHelper,
		ToolbarService toolbarService,
		DadgetCatalogFactory catalogFactory,
		@DashboardTreeDashboards UITree dashboardTree,
		SecurityUIService securityService
		){
		
		this.dao = dao;
		this.hookHandler = hookHandler;
		this.viewProvider = viewProvider;
		this.navPanelHelper = nagPanelHelper;
		this.toolbarService = toolbarService;
		this.catalogFactory = catalogFactory;
		this.dashboardTree = dashboardTree;
		this.securityService = securityService;
		
		canEdit = securityService.hasRight(DashboardViewGenericTargetIdentifier.class, WriteDto.class);
		
		init();
	}
	
	@Override
	public String getCssName() {
		return super.getCssName() + " " + CSS_NAME;
	}
	
	public String getCssMainName() {
		return CSS_MAIN_NAME;
	}
	
	public String getCssNavName() {
		return CSS_NAV_NAME;
	}

	protected void init() {
		setHeaderVisible(false);

		initMain();
		initToolbar();
		
		nsContainer = new VerticalLayoutContainer();
		add(nsContainer);
		nsContainer.add(toolbar, new VerticalLayoutData(1,-1));
		nsContainer.add(cardContainer, new VerticalLayoutData(1,1, new Margins(10,10,0,10)));
	}

	public void reload() {
		mask(BaseMessages.INSTANCE.loadingMsg());
		dao.getDashboardForUser(new RsAsyncCallback<DashboardContainerDto>(){
			public void onSuccess(DashboardContainerDto result) {
				unmask();
				init(result);
			};
			@Override
			public void onFailure(Throwable caught) {
				unmask();
			}
		});
	}
	
	public void reload(final DashboardDto dashboard) {
		mask(BaseMessages.INSTANCE.loadingMsg());
		dao.getDashboardForUser(new RsAsyncCallback<DashboardContainerDto>(){
			public void onSuccess(DashboardContainerDto result) {
				unmask();
				init(result, dashboard);
			};
			@Override
			public void onFailure(Throwable caught) {
				unmask();
			}
		});
	}

	private void initMain() {
		cardContainer = new CardLayoutContainer();
		cardContainer.setHideMode(HideMode.VISIBILITY);
		cardContainer.getElement().addClassName(getCssMainName());
	}
	
	protected void initToolbar() {
		toolbar = new DwToolBar();
		
		/* selector */
		dbStore = new ListStore<DashboardDto>(new BasicObjectModelKeyProvider<DashboardDto>());
		
		dashboardSelector = new ComboBox<DashboardDto>(dbStore, new LabelProvider<DashboardDto>() {
			@Override
			public String getLabel(DashboardDto item) {
				return item.getName();
			}
		} );
		dashboardSelector.setAllowBlank(false);
		dashboardSelector.setAllowTextSelection(false);
		dashboardSelector.setEditable(false);
		dashboardSelector.setForceSelection(true);
		dashboardSelector.setTypeAhead(false);
		dashboardSelector.setWidth(200);
		dashboardSelector.setTriggerAction(TriggerAction.ALL);
		dashboardSelector.addSelectionHandler(new SelectionHandler<DashboardDto>() {
			@Override
			public void onSelection(SelectionEvent<DashboardDto> event) {
				DashboardDto selectedItem = event.getSelectedItem();
				if(null != selectedItem)
					display(selectedItem);	
			}
		});
		dashboardSelector.addValueChangeHandler(new ValueChangeHandler<DashboardDto>() {
			@Override
			public void onValueChange(ValueChangeEvent<DashboardDto> event) {
				DashboardDto selectedItem = event.getValue();
				if(null != selectedItem)
					display(selectedItem);	
			}
		});
		toolbar.add(dashboardSelector);
				
		for(DashboardToolbarHook hooker : hookHandler.getHookers(DashboardToolbarHook.class))
			hooker.addLeft(toolbar, this);
		
		toolbar.add(new FillToolItem());

		for(DashboardToolbarHook hooker : hookHandler.getHookers(DashboardToolbarHook.class))
			hooker.addRight(toolbar, this);

		
		/* add dadget */
		addDadgetBtn = toolbarService.createSmallButtonLeft(DashboardMessages.INSTANCE.addDadget(), BaseIcon.DADGET);
		if(canEdit){
			toolbar.add(addDadgetBtn);
			
			addDadgetBtn.addSelectHandler( e -> showDadgetCatalog() );
		}
		
		/* add tools */
		toolsBtn = toolbarService.createSmallButtonLeft(DashboardMessages.INSTANCE.tools(), BaseIcon.COG);
		toolbar.add(toolsBtn);
		
		Menu toolsMenu = new DwMenu();
		
		toolsBtn.setMenu(toolsMenu);
		toolsBtn.setArrowAlign(ButtonArrowAlign.RIGHT);
		
		/* edit */
		final MenuItem editDashboardItem = new DwMenuItem(DashboardMessages.INSTANCE.editDashboard(), BaseIcon.COG);
		toolsMenu.add(editDashboardItem);
		if(! canEdit)
			editDashboardItem.disable();
		
		editDashboardItem.addSelectionHandler( e -> editDashboard() );
		
		final MenuItem editOrderDashboardItem = new DwMenuItem(DashboardMessages.INSTANCE.editDashboardOrder(), BaseIcon.ARROW_UP);
		toolsMenu.add(editOrderDashboardItem);
		
		editOrderDashboardItem.addSelectionHandler( e -> editDashboardOrder() );
		
		toolsMenu.add(new SeparatorMenuItem());
		
		/* add */
		MenuItem addDashboardItem = new DwMenuItem(DashboardMessages.INSTANCE.createDashboard(), BaseIcon.ITEMS_DETAIL);
		toolsMenu.add(addDashboardItem);
		if(! canEdit)
			addDashboardItem.disable();
		addDashboardItem.addSelectionHandler( e -> createDashboard(true) );
		
		/* import */
		MenuItem addReferencedDashboardItem = new DwMenuItem(DashboardMessages.INSTANCE.createReferenceDashboard(), BaseIcon.IMPORT);
		toolsMenu.add(addReferencedDashboardItem);
		addReferencedDashboardItem.addSelectionHandler( e -> createReferenceDashboard() );
		
		resetReferencedDashboardItem = new DwMenuItem(DashboardMessages.INSTANCE.resetReferenceDashboard(), BaseIcon.ROTATE_LEFT);
		resetReferencedDashboardItem.disable();
		toolsMenu.add(resetReferencedDashboardItem);
		resetReferencedDashboardItem.addSelectionHandler( e -> resetReferenceDashboard() );
		
		toolsMenu.add(new SeparatorMenuItem());
		/* remove */
		MenuItem removeDashboardItem = new DwMenuItem(DashboardMessages.INSTANCE.removeDashboard(), BaseIcon.DELETE);
		toolsMenu.add(removeDashboardItem);
		removeDashboardItem.addSelectionHandler(selectionHandlerEvent -> {
			ConfirmMessageBox cmb = new DwConfirmMessageBox(DashboardMessages.INSTANCE.removeDashboardConfirmTitle(),
					DashboardMessages.INSTANCE.removeDashboardConfirmMsg());
			cmb.addDialogHideHandler(dialogHideEvent -> {
				if (dialogHideEvent.getHideButton() == PredefinedButton.YES)
					removeDashboard();
			} );
			cmb.show();
		});
		
		toolsMenu.addBeforeShowHandler(new BeforeShowHandler() {
			
			@Override
			public void onBeforeShow(BeforeShowEvent event) {
				if(null == dashboard)
					editDashboardItem.disable();
				else if(canEdit)
					editDashboardItem.enable();
			}
		});
	}

	protected void resetReferenceDashboard() {
		if(! (dashboard instanceof DashboardReferenceDto))
			return;
		
		mask(BaseMessages.INSTANCE.storingMsg());
		viewMap.remove(dashboard);
		dao.resetReferencedDashboard((DashboardReferenceDto) dashboard, new RsAsyncCallback<DashboardDto>(){
			@Override
			public void onSuccess(DashboardDto result) {
				unmask();
				display(result);
			}
			@Override
			public void onFailure(Throwable caught) {
				super.onFailure(caught);
				unmask();
			}
		});
	}

	protected void editDashboardOrder() {
		final DwWindow window = new DwWindow();
		window.setHeading(DashboardMessages.INSTANCE.editDashboardOrder());
		window.setHeaderIcon(BaseIcon.ARROW_UP);
		
		window.setSize(300, 500);
		
		window.addCancelButton();

		final ListStore<DashboardDto> store = new ListStore<DashboardDto>(DashboardDtoPA.INSTANCE.dtoId());
		
		for(DashboardDto item : dbStore.getAll())
			store.add(item);
		
		List<ColumnConfig<DashboardDto, ?>> columns = new ArrayList<ColumnConfig<DashboardDto, ?>>();
		
		/* name column */
		ColumnConfig<DashboardDto, String> nameConfig = new ColumnConfig<DashboardDto, String>(DashboardDtoPA.INSTANCE.name(), 200, BaseMessages.INSTANCE.name()); 
		nameConfig.setMenuDisabled(true);
		columns.add(nameConfig);
		
		ColumnModel<DashboardDto> cm = new ColumnModel<DashboardDto>(columns);
		
		Grid<DashboardDto> grid = new Grid<DashboardDto>(store, cm);
		
		GridDragSource<DashboardDto> source = new GridDragSource<DashboardDto>(grid);
		GridDropTarget<DashboardDto> target = new GridDropTarget<DashboardDto>(grid);
		target.setAllowSelfAsSource(true);
		target.setFeedback(Feedback.BOTH);
		
		VerticalLayoutContainer container = new VerticalLayoutContainer();
		container.add(grid);
		container.setScrollMode(ScrollMode.AUTOY);
		window.add(container, new MarginData(10));
		
		DwTextButton submitBtn = new DwTextButton(BaseMessages.INSTANCE.submit());
		submitBtn.addSelectHandler(new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
				ArrayList<Long> ids = new ArrayList<Long>();
				ArrayList<DashboardDto> dashboards = new ArrayList<DashboardDto>(store.getAll());
				for(int i = 0; i < dashboards.size(); i++){
					dashboards.get(i).setN(i);
					ids.add(dashboards.get(i).getId());
				}

				cardContainer.mask(BaseMessages.INSTANCE.storingMsg());
				
				dao.changeDashboardOrder(ids, new RsAsyncCallback<Void>(){
					@Override
					public void onSuccess(Void result) {
						cardContainer.unmask();
						reload();
					}
					@Override
					public void onFailure(Throwable caught) {
						unmask();
					}
				});
				window.hide();
			}
		});
		window.addButton(submitBtn);
		
		window.show();
		
	}

	protected void createReferenceDashboard() {
		if (null != importDashboardPopup) {
			importDashboardPopup.show();
			return;
		}
			
		importDashboardPopup = new TreeSelectionPopup(dashboardTree, DashboardNodeDto.class){
			@Override
			protected void itemsSelected(List<AbstractNodeDto> selectedItems) {
				if(null != selectedItems && !selectedItems.isEmpty()) {
					dao.importReferencedDashboardForUser((DashboardNodeDto) selectedItems.get(0), new RsAsyncCallback<DashboardDto>(){
						@Override
						public void onSuccess(final DashboardDto dashboard) {
							if(null == dashboard)
								throw new IllegalArgumentException();
							
							unmask();
							dbStore.add(dashboard);
							Scheduler.get().scheduleDeferred(new ScheduledCommand() {
								@Override
								public void execute() {
									dashboardSelector.setValue(dashboard,true);
								}
							});
						}
					});
				}
			}
		};
		importDashboardPopup.setSelectionMode(SelectionMode.SINGLE);
		
		importDashboardPopup.show();
	}

	protected void showDadgetCatalog() {
		final DwWindow window = catalogFactory.create(dashboard, this);
		window.show();
	}

	private native String getCatalogViewTemplate() /*-{ 
	    return [
	    '<tpl for=".">', 
		    
	    '</tpl>', 
	    '<div class="x-clear"></div>'].join(""); 
    }-*/; 

	protected void editDashboard() {
		final DwWindow window = new DwWindow();
		window.setHeading(DashboardMessages.INSTANCE.editDashboard());
		window.setWidth(450);
		window.setHeight(420);
		
		final SimpleForm form = SimpleForm.getInlineInstance();
		form.setFieldWidth(400);
		form.addField(String.class,  DashboardDtoPA.INSTANCE.name(), DashboardMessages.INSTANCE.nameLabel());
		form.addField(String.class, DashboardDtoPA.INSTANCE.description(), DashboardMessages.INSTANCE.descriptionLabel(), new SFFCTextAreaImpl(){
			@Override
			public int getHeight() {
				return 75;
			}
		});
		form.addField(Boolean.class, DashboardDtoPA.INSTANCE.singlePage(), DashboardMessages.INSTANCE.singlePageLabel(), new SFFCDashboardLayout());
		form.addField(Long.class, DashboardDtoPA.INSTANCE.reloadInterval(), DashboardMessages.INSTANCE.reloadIntervalLabel());
		form.addField(List.class, DashboardDtoPA.INSTANCE.layout(), DashboardMessages.INSTANCE.layoutLabel(), new SFFCDashboardLayout());
		
		final DashboardDto copy = new DashboardDtoDec();
		copy.setName(dashboard.getName());
		copy.setDescription(dashboard.getDescription());
		copy.setReloadInterval(dashboard.getReloadInterval());
		copy.setLayout(dashboard.getLayout());
		copy.setSinglePage(dashboard.isSinglePage());

		/* bind and add dashboard */
		form.bind(copy);
		window.add(form, new MarginData(10));
		
		window.addCancelButton();

		DwTextButton submitBtn = new DwTextButton(BaseMessages.INSTANCE.submit());
		window.addButton(submitBtn);
		submitBtn.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				dashboard.setName(copy.getName());
				dashboard.setDescription(copy.getDescription());
				dashboard.setReloadInterval(copy.getReloadInterval());
				dashboard.setLayout(copy.getLayout());
				dashboard.setSinglePage(copy.isSinglePage());
				
				window.hide();
				
				mask(BaseMessages.INSTANCE.submit());
				dao.editDashboard(dashboard, new RsAsyncCallback<DashboardDto>(){
					@Override
					public void onSuccess(DashboardDto result) {
						unmask();
						updateDashboard(result);
						refreshDadgets();
					}
					
					@Override
					public void onFailure(Throwable caught) {
						unmask();
					}
				});
			}
		});
		
		window.show();
	}
	
	public void refreshDadgets() {		
						
		if(dashboard.getReloadInterval() > 0){
			reloadCounter++;
			final int myCounter = reloadCounter;
			Scheduler.get().scheduleFixedPeriod(new RepeatingCommand() {
				@Override
				public boolean execute() {
					if(reloadCounter != myCounter) { 
						return false;
					}
					
					for(DadgetPanel dp : viewMap.get(dashboard).getAllDagetPanels()) {
						dp.refresh();
					}
					
					return true;
				}
			}, (int) dashboard.getReloadInterval()*1000);
		} else {
			reloadCounter = 0;
		}
	}
	
	@Override
	public void edited(DashboardDto dashboard) {
		dao.editDashboard(dashboard, new RsAsyncCallback<DashboardDto>(){
			@Override
			public void onSuccess(DashboardDto result) {
			}
			
			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}
	

	protected void updateDashboard(DashboardDto dashboard) {
		this.dashboard = dashboard; 
		viewMap.put(dashboard,viewMap.remove(dashboard));
		
		for(DashboardDto db : dbStore.getAll()){
			if(db.equals(dashboard)){
				db.setName(dashboard.getName());
				dbStore.update(db);
				dashboardSelector.redraw();
			}
		}
		
		DashboardView view = viewMap.get(dashboard);
		
		view.dashboardChanged(dashboard);
		
		for(DashboardToolbarHook hooker : hookHandler.getHookers(DashboardToolbarHook.class))
			hooker.dashboardChanged(view.getDashboard(), view);

	}


	protected void removeDashboard() {
		mask(BaseMessages.INSTANCE.storingMsg());
		dao.removeDashboard(dashboard, new RsAsyncCallback<Void>(){
			@Override
			public void onSuccess(Void result) {
				reload();
			}
			@Override
			public void onFailure(Throwable caught) {
				unmask();
			}
		});
	}

	
	protected void init(DashboardContainerDto result) {
		init(result, null);
	}
	
	protected DashboardDto getPrimaryDashboard() {
		for (DashboardDto dashboard: dbStore.getAll()) {
			if (dashboard.isPrimary()) {
				return dashboard;
			}
		}
		return null;
	}

	protected void init(DashboardContainerDto result, DashboardDto dashboardToSelect) {
		dbStore.clear();
		cardContainer.clear();
		viewMap.clear();
		dashboard = null;
		dashboardSelector.disableEvents();
		dashboardSelector.clear();
		dashboardSelector.enableEvents();

		dbStore.addAll(result.getDashboards());

		if(null != dashboardToSelect)
			dashboardSelector.setValue(dashboardToSelect,true);
		else if(! dbStore.getAll().isEmpty()) {
			/* We select the primary dashboard if set. If not, we select the first dashboard in the list. */
			DashboardDto primaryDashboard = getPrimaryDashboard();
			if (null != primaryDashboard) 
				dashboardSelector.setValue(primaryDashboard, true);
			else
				dashboardSelector.setValue(dbStore.get(0),true);
		} else if(canEdit)
			createDashboard(false);
		
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				dashboardSelector.fireEvent(new BlurEvent());
				focus();
			}
		});
	}
	
	
	protected void createDashboard(final boolean editOnCreate) {
		mask(BaseMessages.INSTANCE.loadingMsg());
		dao.createDashboardForUser(new RsAsyncCallback<DashboardDto>(){
			@Override
			public void onSuccess(DashboardDto dashboard) {
				if(null == dashboard)
					throw new IllegalArgumentException();
				
				unmask();
				dbStore.add(dashboard);
				editDashboardOnSelect = editOnCreate;
				dashboardSelector.setValue(dashboard,true);
			}
			@Override
			public void onFailure(Throwable caught) {
				unmask();
			}
		});
	}

	protected void display(DashboardDto dashboard) {
		resetReferencedDashboardItem.disable();
		
		if(null != dashboard && this.dashboard != dashboard){
			if(viewMap.containsKey(dashboard)) {
				doDisplay(dashboard);
				refreshDadgets();
			}else {
				dao.loadDashboardForDisplay(dashboard, new RsAsyncCallback<DashboardDto>(){
					@Override
					public void onSuccess(DashboardDto result) {
						doDisplay(result);
						refreshDadgets();
					}
				});
			}
		}
	}

	protected void doDisplay(DashboardDto dashboard) {
		this.dashboard = dashboard;
		
		if(canEdit)
			addDadgetBtn.enable();
		
		if(dashboard instanceof DashboardReferenceDto)
			resetReferencedDashboardItem.enable();
		
		if(! viewMap.containsKey(dashboard)){
			DashboardView view = viewProvider.get();
			if(isProtected)
				view.init(this, dashboard, isProtected);
			else
				view.init(this, dashboard);
			
			viewMap.put(dashboard, view);
			cardContainer.add(view);
		}
		
		DashboardView view = viewMap.get(dashboard);
		
		for(DashboardToolbarHook hooker : hookHandler.getHookers(DashboardToolbarHook.class))
			hooker.dashboardDisplayed(view.getDashboard(), view);
		
		cardContainer.setActiveWidget(view);	
		view.makeAwareOfSelection();
		
		if(editDashboardOnSelect){
			editDashboardOnSelect = false;
			editDashboard();
		}
	}

	public void remove(DashboardDto dashboard, DadgetDto dadget) {
		dashboard.getDadgets().remove(dadget);
		dashboard.setDadgets(dashboard.getDadgets());
		
		dao.removeDadgetFromDashboard(dashboard, dadget, new RsAsyncCallback<DashboardDto>(){
			@Override
			public void onSuccess(DashboardDto result) {
				updateDashboard(result);
			}
		});		
	}
	
	@Override
	public void resizeDadget(DashboardDto dashboard, DadgetDto dadget,
			int offsetHeight) {
		dadget.setHeight(offsetHeight);
		dao.editDadgetToDashboard(dashboard, dadget, new RsAsyncCallback<DashboardDto>());
	}

	@Override
	public void addDadget(DashboardDto dashboard, DadgetDto dadget) {
		dao.addDadgetToDashboard(dashboard, dadget, new RsAsyncCallback<DashboardDto>(){
			@Override
			public void onSuccess(DashboardDto result) {
				updateDashboard(result);
			}
		});
	}
	
	public void dadgetConfigured(DashboardDto dashboard, final DadgetDto dadget, ConfigType type, final EditSuccessCallback callback){
		mask(BaseMessages.INSTANCE.save());
		dao.editDadgetToDashboard(dashboard, dadget, new RsAsyncCallback<DashboardDto>(){
			@Override
			public void onSuccess(DashboardDto result) {
				unmask();
				updateDashboard(result);
				
				DadgetDto updatedDadget = null;
				for(DadgetDto d : result.getDadgets()){
					if(d.getId().equals(dadget.getId())){
						updatedDadget = d;
						break;
					}
				}
				
				callback.onSuccess(result, updatedDadget);
			}
			@Override
			public void onFailure(Throwable caught) {
				unmask();
			}
		});
	}


	public void selected() {
		if(! loaded) {
			loaded = true;
			reload();
		} else if(null != dashboard){
			final DashboardView view = viewMap.get(dashboard);
			if(null != view){
				view.makeAwareOfSelection();

				Scheduler.get().scheduleDeferred(new ScheduledCommand() {
					
					@Override
					public void execute() {
						view.doForceLayout();
					}
				});
			}
			
			/* selector sometimes does not want to display value .. try and enforce it */
			dashboardSelector.setValue(dashboard,true);
		}
		onResize();
	}

	public void hideToolbar() {
		if(null != toolbar && toolbar == nsContainer.getWidget(0))
			toolbar.removeFromParent();
	}
	
	public void hideToolsButton() {
		toolsBtn.hide();
	}

	public void hideAddButton() {
		addDadgetBtn.hide();
	}

	public void setProtected(){
		this.isProtected = true;
	}


}
