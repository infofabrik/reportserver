package net.datenwerke.rs.uservariables.client.uservariables.mainpanel;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.cell.core.client.ButtonCell.ButtonArrowAlign;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.CompleteEditEvent;
import com.sencha.gxt.widget.core.client.event.CompleteEditEvent.CompleteEditHandler;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.DialogHideHandler;
import com.sencha.gxt.widget.core.client.event.RowDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.RowDoubleClickEvent.RowDoubleClickHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
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
import com.sencha.gxt.widget.core.client.toolbar.SeparatorToolItem;
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
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.servercommunication.callback.NotamCallback;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.gxtdto.client.utils.valueprovider.DisplayTitleValueProvider;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.uservariables.client.uservariables.UserVariableConfigurator;
import net.datenwerke.rs.uservariables.client.uservariables.UserVariableDao;
import net.datenwerke.rs.uservariables.client.uservariables.UserVariablesUIService;
import net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableDefinitionDto;
import net.datenwerke.rs.uservariables.client.uservariables.dto.pa.UserVariableDefinitionDtoPA;
import net.datenwerke.rs.uservariables.client.uservariables.locale.UserVariablesMessages;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class UserVariablesDefinitionPanel extends MainPanelView {

	public static final String VIEW_ID = "UserVariablesDefinitionView";
	
	private static UserVariableDefinitionDtoPA uvDefPa = GWT.create(UserVariableDefinitionDtoPA.class);
	
	private final UserVariablesUIService userVariablesService;
	private final ToolbarService toolbarService;
	private final UserVariableDao userVariableDao;
	
	private ListStore<UserVariableDefinitionDto>  variableStore;
	
	@Inject
	public UserVariablesDefinitionPanel(
		UserVariablesUIService userVariablesService,
		ToolbarService toolbarService,
		UserVariableDao userVariableDao
		){
		/* store objects */
		this.userVariablesService = userVariablesService;
		this.toolbarService = toolbarService;
		this.userVariableDao = userVariableDao;
	}
	
	@Override
	public String getViewId(){
		return VIEW_ID;
	}
	
	@Override
	public String getComponentHeader() {
		return UserVariablesMessages.INSTANCE.uservariableManagamentHeading();
	}
	
	@Override
	public ImageResource getIcon() {
		return BaseIcon.USER_VARIABLE.toImageResource();
	}

	@Override
	public Widget getViewComponent(AbstractNodeDto selectedNode) {
		DwContentPanel view = new DwContentPanel();
		view.setLightHeader();
		
		/* headline */
		view.setHeading(UserVariablesMessages.INSTANCE.viewHeadline());
		
		/* description */
		view.setInfoText(UserVariablesMessages.INSTANCE.viewDescription());
		
		final Component gridComponent = createGrid();
		gridComponent.mask(BaseMessages.INSTANCE.loadingMsg()); //$NON-NLS-1$
		gridComponent.disable();
		view.add(gridComponent);
		
		/* load data */
		userVariableDao.getDefinedUserVariableDefinitions(new RsAsyncCallback<ListLoadResult<UserVariableDefinitionDto>>() {
			@Override
			public void onSuccess(ListLoadResult<UserVariableDefinitionDto> result) {
				variableStore.addAll(result.getData());
				gridComponent.unmask();
				gridComponent.enable();
			}
		});
		
		VerticalLayoutContainer wrapper = new VerticalLayoutContainer();
		wrapper.add(view, new VerticalLayoutData(1,1, new Margins(10)));
		
		return wrapper;
	}
	
	private Component createGrid() {
		/* create store for aces */
		variableStore = new ListStore<UserVariableDefinitionDto>(uvDefPa.dtoId());
		variableStore.setAutoCommit(true);
		
		/* configure columns */
		List<ColumnConfig<UserVariableDefinitionDto,?>> columns = new ArrayList<ColumnConfig<UserVariableDefinitionDto,?>>();
		
		/* name column */
		ColumnConfig<UserVariableDefinitionDto,String> nameConfig = new ColumnConfig<UserVariableDefinitionDto,String>(uvDefPa.name(), 200, BaseMessages.INSTANCE.name()); 
		nameConfig.setMenuDisabled(true);
		columns.add(nameConfig);
		
		/* desc column */
		ColumnConfig<UserVariableDefinitionDto,String> descConfig = new ColumnConfig<UserVariableDefinitionDto,String>(uvDefPa.description(), 400, BaseMessages.INSTANCE.propertyDescription()); 
		descConfig.setMenuDisabled(true);
		columns.add(descConfig);
		
		/* type column */
		ColumnConfig<UserVariableDefinitionDto,String> ccType = new ColumnConfig<UserVariableDefinitionDto,String>(new DisplayTitleValueProvider<UserVariableDefinitionDto>(), 200, BaseMessages.INSTANCE.type());
		ccType.setMenuDisabled(true);
		ccType.setSortable(false);
		columns.add(ccType);
		
		/* create grid */
		final Grid<UserVariableDefinitionDto> variableGrid = new Grid<UserVariableDefinitionDto>(variableStore, new ColumnModel<UserVariableDefinitionDto>(columns));
		variableGrid.setSelectionModel(new GridSelectionModel<UserVariableDefinitionDto>());
		variableGrid.getView().setShowDirtyCells(false);
		variableGrid.getView().setAutoExpandColumn(descConfig);
		
		GridEditing<UserVariableDefinitionDto> editing = new GridInlineEditing<UserVariableDefinitionDto>(variableGrid);
		editing.addEditor(nameConfig, new TextField());
		editing.addEditor(descConfig, new TextField());
		
		/* listen to edit events */
		editing.addCompleteEditHandler(new CompleteEditHandler<UserVariableDefinitionDto>() {
			@Override
			public void onCompleteEdit(
					CompleteEditEvent<UserVariableDefinitionDto> event) {
				UserVariableDefinitionDto uvd = variableGrid.getStore().get(event.getEditCell().getRow());
				if(null != uvd)
					updateVariable(uvd);
			}
		});
		
		/* listen to double click events */
		variableGrid.addRowDoubleClickHandler(new RowDoubleClickHandler() {
			
			@Override
			public void onRowDoubleClick(RowDoubleClickEvent event) {
				UserVariableDefinitionDto uvd = variableGrid.getStore().get(event.getRowIndex());
				if(null != uvd)
					displayEditVariable(uvd);
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

	private void addAddRemoveButtons(final Grid<UserVariableDefinitionDto> grid, ToolBar toolbar) {
		DwTextButton addBtn = toolbarService.createSmallButtonLeft(BaseMessages.INSTANCE.add(), BaseIcon.USER_VARIABLE);
		addBtn.setArrowAlign(ButtonArrowAlign.RIGHT);
		Menu menu = new DwMenu();  
		for(final UserVariableConfigurator config : userVariablesService.getAllVariableConfigurators()){
			MenuItem addMenuItem = new DwMenuItem(config.getName(), config.getIcon());
			addMenuItem.addSelectionHandler(new SelectionHandler<Item>() {
				@Override
				public void onSelection(SelectionEvent<Item> event) {
					addVariable(config);
				}
			});
			menu.add(addMenuItem);	
		}
		addBtn.setMenu(menu);  
		
		toolbar.add(addBtn);
		
		
		DwTextButton editBtn = toolbarService.createSmallButtonLeft(BaseMessages.INSTANCE.edit(), BaseIcon.USER_VARIABLE);
		editBtn.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				UserVariableDefinitionDto var = grid.getSelectionModel().getSelectedItem();
				if(null != var)
					displayEditVariable(var);
			}
		});
		toolbar.add(editBtn);
		
		toolbar.add(new SeparatorToolItem());
		
		DwSplitButton removeButton = new DwSplitButton(BaseMessages.INSTANCE.remove());
		removeButton.setIcon(BaseIcon.DELETE);
		removeButton.addSelectHandler(new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
				List<UserVariableDefinitionDto> selectedItems = grid.getSelectionModel().getSelectedItems();
				removeVariables(selectedItems);
			}
		});
		toolbar.add(removeButton);
		
		MenuItem removeAllButton = new DwMenuItem(BaseMessages.INSTANCE.removeAll(), BaseIcon.DELETE);
		removeAllButton.addSelectionHandler(new SelectionHandler<Item>() {

			@Override
			public void onSelection(SelectionEvent<Item> event) {
				ConfirmMessageBox cmb = new DwConfirmMessageBox(UserVariablesMessages.INSTANCE.confirmRemoveAllHeader(), UserVariablesMessages.INSTANCE.confirmRemoveAllText());
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
		remMenu.add(removeAllButton);
		removeButton.setMenu(remMenu);
	}
	
	@SuppressWarnings("unchecked")
	protected void displayEditVariable(final UserVariableDefinitionDto definition) {
		/* ask config to configure window */
		final UserVariableConfigurator config = userVariablesService.getConfigurator(definition);
		final Widget editComponent = config.getEditComponent(definition);
		if(null == editComponent)
			return;
		
		/* prepare dialog */
		final DwWindow window = new DwWindow();
		window.setOnEsc(false);
		window.setHeading(UserVariablesMessages.INSTANCE.editVariable() + (definition.getName() == null ? BaseMessages.INSTANCE.unnamed() : definition.getName())); 
		window.setModal(true);
			
		window.add(editComponent, new MarginData(10));
		config.configureEditWindow(definition, window);

		final DwTextButton cnlButton = new DwTextButton(BaseMessages.INSTANCE.cancel());
		cnlButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				definition.clearModified();
				window.hide();
			}
		});
		window.addButton(cnlButton);
		
		final DwTextButton okButton = new DwTextButton(BaseMessages.INSTANCE.apply());
		okButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				window.hide();
				updateVariable(definition);
			}
		});
		window.addButton(okButton);
		
		window.show();
	}

	protected void addVariable(UserVariableConfigurator config) {
		userVariableDao.addUserVariableDefinition(config.createDTOInstance(), new NotamCallback<UserVariableDefinitionDto>(UserVariablesMessages.INSTANCE.variableCreated()) {
			@Override
			public void doOnSuccess(UserVariableDefinitionDto result) {
				variableStore.add(result);
				variableStore.commitChanges();
			}
		});
	}

	protected void updateVariable(UserVariableDefinitionDto uvd) {
		userVariableDao.updateUserVariableDefinition(uvd, new NotamCallback<UserVariableDefinitionDto>(BaseMessages.INSTANCE.changesApplied()) { 
			@Override
			public void doOnSuccess(UserVariableDefinitionDto result) {
				variableStore.update(result);
				variableStore.commitChanges();
			}
		});
	}
	
	protected void removeVariables(final List<UserVariableDefinitionDto> definitions) {
		final ArrayList<UserVariableDefinitionDto> copy = new ArrayList<UserVariableDefinitionDto>(definitions);
		final ArrayList<UserVariableDefinitionDto> copyToRemove = new ArrayList<UserVariableDefinitionDto>(definitions);
		userVariableDao.removeUserVariableAndAskForForce(copy, new NotamCallback<Void>(UserVariablesMessages.INSTANCE.variableDeleted()) { 
			@Override
			public void doOnSuccess(Void result) {
				for(UserVariableDefinitionDto def : copyToRemove)
					variableStore.remove(def);
				
				variableStore.commitChanges();
			}
		});
	}

}
