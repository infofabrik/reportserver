package net.datenwerke.rs.uservariables.client.uservariables.mainpanel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.SortDir;
import com.sencha.gxt.data.shared.Store.StoreSortInfo;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent.CellDoubleClickHandler;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.DialogHideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridSelectionModel;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwSplitButton;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwNorthSouthContainer;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwConfirmMessageBox;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.selection.SelectionPopup;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.servercommunication.callback.NotamCallback;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.uservariables.client.uservariables.UserVariableConfigurator;
import net.datenwerke.rs.uservariables.client.uservariables.UserVariableDao;
import net.datenwerke.rs.uservariables.client.uservariables.UserVariablesUIService;
import net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableDefinitionDto;
import net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableInstanceDto;
import net.datenwerke.rs.uservariables.client.uservariables.dto.pa.UserVariableDefinitionDtoPA;
import net.datenwerke.rs.uservariables.client.uservariables.dto.pa.UserVariableInstanceDtoPA;
import net.datenwerke.rs.uservariables.client.uservariables.locale.UserVariablesMessages;
import net.datenwerke.security.client.usermanager.dto.AbstractUserManagerNodeDto;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class UserVariablesView extends MainPanelView {
	
	public static final String VIEW_ID = "UserVariablesView";
	
	private static UserVariableInstanceDtoPA uvInstPa = GWT.create(UserVariableInstanceDtoPA.class);
	private static UserVariableDefinitionDtoPA uvDefPa = GWT.create(UserVariableDefinitionDtoPA.class);
	
	private final ToolbarService toolbarService;
	private final UserVariablesUIService userVariablesService;
	private final UserVariableDao userVariableDao;
	
	private ListStore<UserVariableInstanceDto> hereVariableStore;
	private ListStore<UserVariableInstanceDto> inheritedVariableStore;
	
	private ListStore<UserVariableDefinitionDto> availableVariableDefinitions;
	
	private boolean hereVariablesLoaded = false;
	private boolean allVariableDefinitionsLoaded = false;
	
	private Component hereGridComponent;
	private Component inheritedGridComponent;
	
	@Inject
	public UserVariablesView(
		ToolbarService toolbarService,
		UserVariablesUIService userVariableService,
		UserVariableDao userVariableDao
		){
		this.toolbarService = toolbarService;
		this.userVariablesService = userVariableService;
		this.userVariableDao = userVariableDao;
	}
	
	@Override
	public String getViewId(){
		return VIEW_ID;
	}
	
	@Override
	public String getComponentHeader() {
		return UserVariablesMessages.INSTANCE.mainPanelView_header();
	}
	
	@Override
	public boolean isSticky() {
		return true;
	}
	
	@Override
	public ImageResource getIcon() {
		return BaseIcon.USER_VARIABLE.toImageResource();
	}

	@Override
	public Component getViewComponent(AbstractNodeDto selectedNode) {
		/* create stores */
		inheritedVariableStore = new ListStore<UserVariableInstanceDto>(uvInstPa.dtoId());
		createStoreSorter(inheritedVariableStore);
		
		hereVariableStore = new ListStore<UserVariableInstanceDto>(uvInstPa.dtoId());
		createStoreSorter(hereVariableStore);
		
		availableVariableDefinitions = new ListStore<UserVariableDefinitionDto>(uvDefPa.dtoId());
		
		/* create grids and disable them*/
		hereGridComponent = createHereGrid();
		hereGridComponent.disable();
		hereGridComponent.mask(BaseMessages.INSTANCE.loadingMsg()); //$NON-NLS-1$
		
		inheritedGridComponent = createInheritedGrid();
		inheritedGridComponent.disable();
		inheritedGridComponent.mask(BaseMessages.INSTANCE.loadingMsg()); //$NON-NLS-1$
		
		/* add here grid */
		DwContentPanel herePanel = new DwContentPanel();
		herePanel.setLightHeader();
		herePanel.setHeading(UserVariablesMessages.INSTANCE.mainPanelView_description());
		herePanel.add(hereGridComponent);
		
		/* add inherited grid */
		DwContentPanel inheritedPanel = new DwContentPanel();
		inheritedPanel.setLightHeader();
		inheritedPanel.setHeading(UserVariablesMessages.INSTANCE.mainPanelView_inheritedDescription());
		inheritedPanel.add(inheritedGridComponent);

		/* load data */
		loadData();
		
		VerticalLayoutContainer container = new VerticalLayoutContainer();
		container.setScrollMode(ScrollMode.AUTOY);
		container.add(herePanel, new VerticalLayoutData(1,-1,new Margins(10)));
		container.add(inheritedPanel, new VerticalLayoutData(1,-1,new Margins(10)));
		
		return container;
	}
	
	protected void createStoreSorter(ListStore<UserVariableInstanceDto> store) {
		store.addSortInfo(new StoreSortInfo<UserVariableInstanceDto>(new Comparator<UserVariableInstanceDto>(){

			@Override
			public int compare(UserVariableInstanceDto m1,
					UserVariableInstanceDto m2) {
				String n1 = m1.getDefinition().getName();
				String n2 = m2.getDefinition().getName();
				return n1.compareTo(n2);
			}
			
		}, SortDir.ASC));
	}

	private void loadData() {
		/* load here variables */
		userVariableDao.getDefinedUserVariableInstances((AbstractUserManagerNodeDto)getSelectedNode(), new RsAsyncCallback<List<UserVariableInstanceDto>>() {
			@Override
			public void onSuccess(List<UserVariableInstanceDto> result) {
				for(UserVariableInstanceDto instance : result)
					hereVariableStore.add(instance);
				hereVariablesLoaded = true;
				enableHereGridIfReady();
			}
		});
		
		/* load inherited variables */
		userVariableDao.getInheritedUserVariableInstances((AbstractUserManagerNodeDto)getSelectedNode(), new RsAsyncCallback<List<UserVariableInstanceDto>>() {
			@Override
			public void onSuccess(List<UserVariableInstanceDto> result) {
				for(UserVariableInstanceDto instance : result)
					inheritedVariableStore.add(instance);
				inheritedGridComponent.enable();
				inheritedGridComponent.unmask();
			}
		});
		
		/* load all definitions */
		userVariableDao.getDefinedUserVariableDefinitions(new RsAsyncCallback<ListLoadResult<UserVariableDefinitionDto>>() {
			@Override
			public void onSuccess(ListLoadResult<UserVariableDefinitionDto> result) {
				availableVariableDefinitions.addAll(result.getData());
				allVariableDefinitionsLoaded = true;
				enableHereGridIfReady();
			}
		});
	}

	protected void enableHereGridIfReady() {
		if(hereVariablesLoaded && allVariableDefinitionsLoaded){
			hereGridComponent.enable();
			hereGridComponent.unmask();
		}
	}

	private Component createInheritedGrid() {
		Grid<UserVariableInstanceDto> variableGrid = createGrid(inheritedVariableStore, true);
		
		return variableGrid;
	}

	private Grid<UserVariableInstanceDto> createGrid(ListStore<UserVariableInstanceDto> store, boolean inherited) {
		/* configure columns */
		List<ColumnConfig<UserVariableInstanceDto,?>> columns = new ArrayList<ColumnConfig<UserVariableInstanceDto,?>>();
		
		/* name column */
		ColumnConfig<UserVariableInstanceDto,UserVariableDefinitionDto> nameConfig = new ColumnConfig<UserVariableInstanceDto,UserVariableDefinitionDto>(uvInstPa.definition(), 200, BaseMessages.INSTANCE.name());
		nameConfig.setMenuDisabled(true);
		nameConfig.setCell(new AbstractCell<UserVariableDefinitionDto>() {
			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					UserVariableDefinitionDto value, SafeHtmlBuilder sb) {
				if(null != value && null != value.getName())
					sb.appendEscaped(value.getName());
			}
		});
		columns.add(nameConfig);
		
		ColumnConfig<UserVariableInstanceDto,UserVariableInstanceDto> valueConfig = new ColumnConfig<UserVariableInstanceDto,UserVariableInstanceDto>(new IdentityValueProvider<UserVariableInstanceDto>("__value"), 200, BaseMessages.INSTANCE.value()); 
		valueConfig.setMenuDisabled(true);
		valueConfig.setCell(new AbstractCell<UserVariableInstanceDto>() {
			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					UserVariableInstanceDto value, SafeHtmlBuilder sb) {
				UserVariableConfigurator configurator = userVariablesService.getConfigurator(value);
				sb.appendEscaped(String.valueOf(configurator.getDisplayValue(value, availableVariableDefinitions.findModel(value.getDefinition()))));
			}
		});
		columns.add(valueConfig);
		
		ColumnConfig<UserVariableInstanceDto,UserVariableDefinitionDto> descConfig = new ColumnConfig<UserVariableInstanceDto,UserVariableDefinitionDto>(uvInstPa.definition(), 200, BaseMessages.INSTANCE.description());
		descConfig.setMenuDisabled(true);
		descConfig.setCell(new AbstractCell<UserVariableDefinitionDto>() {
			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					UserVariableDefinitionDto value, SafeHtmlBuilder sb) {
				if(null != value.getDescription())
					sb.appendEscaped(value.getDescription());
			}
		});
		columns.add(descConfig);
		
		if(inherited){
			ColumnConfig<UserVariableInstanceDto,AbstractUserManagerNodeDto> inheritedConfig = new ColumnConfig<UserVariableInstanceDto,AbstractUserManagerNodeDto>(uvInstPa.folk(), 160, UserVariablesMessages.INSTANCE.definedAt());
			inheritedConfig.setMenuDisabled(true);
			inheritedConfig.setCell(new AbstractCell<AbstractUserManagerNodeDto>() {
				@Override
				public void render(com.google.gwt.cell.client.Cell.Context context,
						AbstractUserManagerNodeDto value, SafeHtmlBuilder sb) {
					if(null != value)
						sb.appendEscaped(value.toDisplayTitle());
				}
			});
			columns.add(inheritedConfig);
		}
		
		/* create grid */
		Grid<UserVariableInstanceDto> grid = new Grid<UserVariableInstanceDto>(store, new ColumnModel<UserVariableInstanceDto>(columns));
		grid.getView().setAutoExpandColumn(descConfig);
		grid.setSelectionModel(new GridSelectionModel<UserVariableInstanceDto>());
		grid.getView().setShowDirtyCells(false);
		
		return grid;
	}

	private Component createHereGrid() {
		/* create grid */
		final Grid<UserVariableInstanceDto> variableGrid = createGrid(hereVariableStore, false);
		
		/* listen to double click events */
		variableGrid.addCellDoubleClickHandler(new CellDoubleClickHandler() {
			@Override
			public void onCellClick(CellDoubleClickEvent event) {
				UserVariableInstanceDto instance = variableGrid.getStore().get(event.getRowIndex());
				if(null != instance)
					displayEditVariable(instance);
			}
		});
		
		/* build wrapper for toolbar */
		DwNorthSouthContainer wrapper = new DwNorthSouthContainer();
		wrapper.setWidget(variableGrid);
		
		/* create toolbar */
		ToolBar toolbar = new DwToolBar();
		wrapper.setNorthWidget(toolbar);
		
		/* add toolbar capabilities */
		addAddRemoveButtons(variableGrid, toolbar);
		
		return wrapper;
	}


	private void addAddRemoveButtons(final Grid<UserVariableInstanceDto> grid, ToolBar toolbar) {
		/* remove buttons */
		DwTextButton addBtn = toolbarService.createSmallButtonLeft(BaseMessages.INSTANCE.add(), BaseIcon.USER_VARIABLE);
		addBtn.addSelectHandler(new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
				/* create temporary store */
				ListStore<UserVariableDefinitionDto> tmpStore = new ListStore<UserVariableDefinitionDto>(uvDefPa.dtoId());
				for(UserVariableDefinitionDto definition : availableVariableDefinitions.getAll())
					tmpStore.add(definition);
				for(UserVariableInstanceDto instance : hereVariableStore.getAll())
					tmpStore.remove(instance.getDefinition());
				
				/* open selection popup */
				HashMap<ValueProvider<UserVariableDefinitionDto,String>, String> displayProperties = new LinkedHashMap<ValueProvider<UserVariableDefinitionDto, String>, String>();
				displayProperties.put(uvDefPa.name(), BaseMessages.INSTANCE.propertyName());
				displayProperties.put(uvDefPa.description(), BaseMessages.INSTANCE.propertyDescription());
				
				SelectionPopup<UserVariableDefinitionDto> popup = new SelectionPopup<UserVariableDefinitionDto>(tmpStore, displayProperties){
					@Override
					protected void itemsSelected(List<UserVariableDefinitionDto> selectedItems) {
						if(null != selectedItems && ! selectedItems.isEmpty())
							defineVariables(selectedItems);
					}
				};
				popup.loadData();
				popup.show();
			}
		});

		toolbar.add(addBtn);
		
		DwSplitButton removeButton = new DwSplitButton(BaseMessages.INSTANCE.remove());
		removeButton.setIcon(BaseIcon.DELETE);
		removeButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				List<UserVariableInstanceDto> selectedItems = grid.getSelectionModel().getSelectedItems();
				removeVariables(selectedItems);
			}
		});
		toolbar.add(removeButton);
		
		MenuItem removeAllButton = new DwMenuItem(BaseMessages.INSTANCE.removeAll(), BaseIcon.DELETE);
		removeAllButton.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				ConfirmMessageBox cmb = new DwConfirmMessageBox(UserVariablesMessages.INSTANCE.confirmRemoveAllHeader(), UserVariablesMessages.INSTANCE.confirmRemoveText());
				cmb.addDialogHideHandler(new DialogHideHandler() {
					@Override
					public void onDialogHide(DialogHideEvent event) {
						if (event.getHideButton() == PredefinedButton.YES) 
							removeVariables(grid.getStore().getAll());						
					}
				});
				cmb.show();
			}
		});

		Menu remMenu = new DwMenu();
		removeButton.setMenu(remMenu);
		remMenu.add(removeAllButton);
	}
	
	@SuppressWarnings("unchecked")
	protected void displayEditVariable(final UserVariableInstanceDto instance) {
		/* prepare dialog */
		final DwWindow window = new DwWindow();
		window.setOnEsc(false);
		window.setHeading(UserVariablesMessages.INSTANCE.editVariable() + (instance.getDefinition().getName() == null ? BaseMessages.INSTANCE.unknown() : instance.getDefinition().getName())); 
		window.setModal(true);
		
		/* ask config to configure window */
		final UserVariableConfigurator config = userVariablesService.getConfigurator(instance);
		Widget editComponent = config.getEditComponent(instance, availableVariableDefinitions.findModel(instance.getDefinition()));
		
		config.configureEditWindow(instance, availableVariableDefinitions.findModel(instance.getDefinition()), window);
		window.add(editComponent, new MarginData(10));
		
		final DwTextButton cnlButton = new DwTextButton(BaseMessages.INSTANCE.cancel());
		cnlButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				instance.clearModified();
				//TODO: GXT CHECK
				//window.hide(cnlButton);
				window.hide();
			}
		});
		window.addButton(cnlButton);
		
		final DwTextButton okButton = new DwTextButton(BaseMessages.INSTANCE.apply());
		okButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				//TODO: GXT CHECK
				//window.hide(okButton);
				window.hide();
				updateVariable(instance);
			}
		});
		window.addButton(okButton);

		window.show();
	}

	protected void defineVariables(List<UserVariableDefinitionDto> variable){
		userVariableDao.addUserVariableInstances(variable, (AbstractUserManagerNodeDto) getSelectedNode(), new NotamCallback<List<UserVariableInstanceDto>>(UserVariablesMessages.INSTANCE.variableCreated()) { 
			@Override
			public void doOnSuccess(List<UserVariableInstanceDto> result) {
				hereVariableStore.addAll(result);
			}
		});
	}

	protected void updateVariable(UserVariableInstanceDto instance) {
		userVariableDao.updateUserVariableInstance(instance, new NotamCallback<UserVariableInstanceDto>(BaseMessages.INSTANCE.changesApplied()) { //$NON-NLS-1$
			@Override
			public void doOnSuccess(UserVariableInstanceDto result) {
				hereVariableStore.update(result);
			}
		});
	}
	
	protected void removeVariables(final List<UserVariableInstanceDto> instances) {
		userVariableDao.removeUserVariableInstances(instances, new NotamCallback<Void>(UserVariablesMessages.INSTANCE.removedVariable()) { //$NON-NLS-1$
			@Override
			public void doOnSuccess(Void result) {
				for(UserVariableInstanceDto instance : instances)
					hereVariableStore.remove(instance);
			}
		});
	}

}
