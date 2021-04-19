package net.datenwerke.rs.core.client.parameters.backend;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.GXT;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.dnd.core.client.DND.Feedback;
import com.sencha.gxt.dnd.core.client.DndDropEvent;
import com.sencha.gxt.dnd.core.client.GridDragSource;
import com.sencha.gxt.dnd.core.client.GridDropTarget;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent.CellDoubleClickHandler;
import com.sencha.gxt.widget.core.client.event.CompleteEditEvent;
import com.sencha.gxt.widget.core.client.event.CompleteEditEvent.CompleteEditHandler;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.DialogHideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
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
import com.sencha.gxt.widget.core.client.toolbar.SeparatorToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
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
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCBaseModel;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.servercommunication.callback.NotamCallback;
import net.datenwerke.gxtdto.client.ui.helper.grid.ExtendedKeyNav;
import net.datenwerke.gxtdto.client.ui.helper.nav.WestPropertiesDialog;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.gxtdto.client.utils.valueprovider.DisplayTitleValueProvider;
import net.datenwerke.rs.core.client.parameters.ParameterDao;
import net.datenwerke.rs.core.client.parameters.ParameterUIService;
import net.datenwerke.rs.core.client.parameters.config.ParameterConfigurator;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.client.parameters.dto.decorator.ParameterDefinitionDtoDec;
import net.datenwerke.rs.core.client.parameters.dto.pa.ParameterDefinitionDtoPA;
import net.datenwerke.rs.core.client.parameters.locale.ParametersMessages;
import net.datenwerke.rs.core.client.reportmanager.dto.ReportFolderDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.locale.ReportmanagerMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.theme.client.icon.CssIconImageResource;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

/**
 * 
 *
 */
public class ParameterView extends MainPanelView{

	private final ReportmanagerMessages reportManagerMessages = GWT.create(ReportmanagerMessages.class);
	
	private static ParameterDefinitionDtoPA defPa = GWT.create(ParameterDefinitionDtoPA.class);
	
	public static final String VIEW_ID = "ParameterView";
	
	private final ParameterUIService parameterService;
	private final ToolbarService toolbarService;
	private final ParameterDao parameterDao;
	
	private ListStore<ParameterDefinitionDto> parameterStore;
	private Grid<ParameterDefinitionDto> parameterGrid;
	private final ClipboardUiService clipboardService;
	
	private Widget editComponent;
	
	@Inject
	public ParameterView(
		ClipboardUiService clipboardService,
		ParameterUIService parameterService,
		ToolbarService toolbarService,
		ParameterDao parameterDao
		) {
		
		this.clipboardService = clipboardService;
		this.parameterService = parameterService;
		this.toolbarService = toolbarService;
		this.parameterDao = parameterDao;
	}

	@Override
	public String getViewId() {
		return VIEW_ID;
	}
	
	@Override
	public String getComponentHeader(){
		return reportManagerMessages.parameterManagement();
	}
	
	@Override
	public ImageResource getIcon() {
		return BaseIcon.COG.toImageResource();
	}
	
	@Override
	public Component getViewComponent(AbstractNodeDto selectedNode) {
		VerticalLayoutContainer container = new VerticalLayoutContainer();
		
		/* create store */
		createStore();
		
		/* create grid component */
		parameterGrid = createGrid();
		configureDnD(parameterGrid);
		
		/* create wrapper for grid */
		container.add(createToolbar(), new VerticalLayoutData(1, -1));
		container.add(parameterGrid, new VerticalLayoutData(1,1));
		
		/* prepare panel */
		DwContentPanel panel = new DwContentPanel();
		panel.setLightHeader();
		panel.setHeading(ParametersMessages.INSTANCE.mainPanelView_headline());
		panel.add(container);
	
		initClipboard();
		
		/* wrapper */
		VerticalLayoutContainer wrapper = new VerticalLayoutContainer();
		wrapper.add(panel, new VerticalLayoutData(1,1,new Margins(10)));
		
		return wrapper;
	}


	protected void initClipboard() {
		clipboardService.registerCopyHandler(parameterGrid, new ClipboardCopyProcessor() {
			@Override
			public ClipboardItem getItem() {
				List<ParameterDefinitionDto> list = parameterGrid.getSelectionModel().getSelectedItems();
				if(null != list && list.size() > 1)
					return new ClipboardDtoListItem(list, ParameterDefinitionDto.class);
				
				ParameterDefinitionDto def = parameterGrid.getSelectionModel().getSelectedItem();
				if(null != def)
					return new ClipboardDtoItem(def);
				return null;
			}
		});
		
		clipboardService.registerPasteHandler(parameterGrid, new ClipboardDtoPasteProcessor(ParameterDefinitionDto.class) {
			@Override
			protected void doPaste(ClipboardDtoItem dtoItem) {
				duplicateParameter((ParameterDefinitionDto)dtoItem.getDto());
			}
			
			@Override
			protected void doPaste(ClipboardDtoListItem listItem) {
				duplicateParameters((List<ParameterDefinitionDto>)listItem.getList());
			}
		});
	}

	protected ToolBar createToolbar() {
		DwToolBar toolbar = new DwToolBar();
		
		/* add parameter */
		DwTextButton addParamBtn = toolbarService.createSmallButtonLeft(ParametersMessages.INSTANCE.parameter(), BaseIcon.COG_ADD);
		Menu addParamMenu = new DwMenu();
		addParamBtn.setMenu(addParamMenu);
		toolbar.add(addParamBtn);

		/* add separrator */
		DwTextButton addSeparatorBtn = toolbarService.createSmallButtonLeft(ParametersMessages.INSTANCE.divider(), BaseIcon.FONT);
		Menu addSeparatorMenu = new DwMenu();
		addSeparatorBtn.setMenu(addSeparatorMenu);
		toolbar.add(addSeparatorBtn);

		/* fill menus */
		for(final ParameterConfigurator configurator : parameterService.getAvailableParameterConfigurators()){
			MenuItem mi = new DwMenuItem(configurator.getName());
			if(null != configurator.getIcon())
				mi.setIcon(configurator.getIcon());
			
			if(! configurator.isAvailable())
				mi.disable();
			
			mi.addSelectionHandler(new SelectionHandler<Item>() {
				
				@Override
				public void onSelection(SelectionEvent<Item> event) {
					addParameter(configurator, getSelectedNode());
				}
			});
			
			/* add item to menu */
			switch(configurator.getType()){
			case Normal:
				addParamMenu.add(mi);
				break;
			case Separator:
				addSeparatorMenu.add(mi);
				break;
			case Container:
				break;
			}
		}
		
		toolbar.add(new SeparatorToolItem());
		
		DwTextButton editParameter = new DwTextButton(BaseMessages.INSTANCE.edit(), BaseIcon.COG_EDIT);
		toolbar.add(editParameter);
		editParameter.addSelectHandler(new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
				List<ParameterDefinitionDto> selectedItems = parameterGrid.getSelectionModel().getSelectedItems();
				if(null != selectedItems && ! selectedItems.isEmpty()){
					ParameterDefinitionDto pd = selectedItems.get(0);
					displayEditParameter(pd);
				}
			}
		});
		
		toolbar.add(new SeparatorToolItem());
		
		/* add update instances buttons */
		DwSplitButton updateInstaceButton = new DwSplitButton(ParametersMessages.INSTANCE.updateInstances());
		updateInstaceButton.setIcon(BaseIcon.WRENCH);
		updateInstaceButton.addSelectHandler(new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
				List<ParameterDefinitionDto> selectedItems = parameterGrid.getSelectionModel().getSelectedItems();
				updateParameterInstances(selectedItems);
			}
		});
		toolbar.add(updateInstaceButton);
		
		MenuItem updateAllInstancesButton = new DwMenuItem(ParametersMessages.INSTANCE.updateAllInstances(), BaseIcon.WRENCH);
		updateAllInstancesButton.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				updateParameterInstances(new ArrayList(parameterGrid.getStore().getAll()));
			}
		});
		
		Menu updateInsMenu = new DwMenu();
		updateInsMenu.add(updateAllInstancesButton);
		updateInstaceButton.setMenu(updateInsMenu);
		
		toolbar.add(new SeparatorToolItem());
		
		/* add remove buttons */
		DwSplitButton removeButton = new DwSplitButton(BaseMessages.INSTANCE.remove());
		removeButton.setIcon(BaseIcon.DELETE);
		removeButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				prepareRemove();
			}
		});
		toolbar.add(removeButton);
		
		MenuItem removeAllButton = new DwMenuItem(BaseMessages.INSTANCE.removeAll(), BaseIcon.DELETE);
		removeAllButton.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				ConfirmMessageBox cmb = new DwConfirmMessageBox(BaseMessages.INSTANCE.removeAll(), ParametersMessages.INSTANCE.reallyRemoveAllText());
				cmb.addDialogHideHandler(new DialogHideHandler() {
					@Override
					public void onDialogHide(DialogHideEvent event) {
						if (event.getHideButton() == PredefinedButton.YES)
							removeParameters(new ArrayList(parameterGrid.getStore().getAll()));
					}
				});
										
				cmb.show();
			}
		});

		Menu removeMenu = new DwMenu();
		removeMenu.add(removeAllButton);
		removeButton.setMenu(removeMenu);
		
		return toolbar;
	}


	protected void prepareRemove() {
		ConfirmMessageBox cmb = null;
		if(parameterGrid.getSelectionModel().getSelectedItems().size() == 1)
			cmb = new DwConfirmMessageBox(BaseMessages.INSTANCE.remove(), ParametersMessages.INSTANCE.reallyRemoveParameterText());
		else 
			cmb = new DwConfirmMessageBox(BaseMessages.INSTANCE.remove(), ParametersMessages.INSTANCE.reallyRemoveParametersText(parameterGrid.getSelectionModel().getSelectedItems().size()));
		cmb.addDialogHideHandler(new DialogHideHandler() {
			@Override
			public void onDialogHide(DialogHideEvent event) {
				if (event.getHideButton() == PredefinedButton.YES){
					List<ParameterDefinitionDto> selectedItems = parameterGrid.getSelectionModel().getSelectedItems();
					removeParameters(selectedItems);
				}
			}
		});
				
		cmb.show();
	}

	protected void createStore() {
		/* create store */
		parameterStore = new ListStore<ParameterDefinitionDto>(defPa.dtoId());
		parameterStore.setAutoCommit(true);
		
		/* load data */
		List<ParameterDefinitionDto> parameterDefinitions = ((ReportDto)getSelectedNode()).getParameterDefinitions();
		for(ParameterDefinitionDto pd : parameterDefinitions){
			if(pd instanceof ParameterDefinitionDtoDec)
				((ParameterDefinitionDtoDec) pd).setReport((ReportDto) getSelectedNode());
		}
		parameterStore.addAll(parameterDefinitions);
	}
	
	protected Grid<ParameterDefinitionDto> createGrid() {
		/* configure columns */ 
		List<ColumnConfig<ParameterDefinitionDto,?>> columns = new ArrayList<ColumnConfig<ParameterDefinitionDto,?>>();
		
		ColumnConfig<ParameterDefinitionDto,ParameterDefinitionDto> iconConfig = new ColumnConfig<ParameterDefinitionDto,ParameterDefinitionDto>(new IdentityValueProvider<ParameterDefinitionDto>("__parameter_icon"), 30); 
		iconConfig.setMenuDisabled(true);
		iconConfig.setCell(new AbstractCell<ParameterDefinitionDto>() {
			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					ParameterDefinitionDto value, SafeHtmlBuilder sb) {
				ParameterConfigurator configurator = parameterService.getConfigurator(value);
				ImageResource icon = configurator.getIcon();
				if(null != icon){
					if(icon instanceof CssIconImageResource)
						sb.append(((CssIconImageResource)icon).getCssIcon());
					else
						sb.append(AbstractImagePrototype.create(icon).getSafeHtml());
				}
			}
		});
		columns.add(iconConfig);
		
		/* key column */
		ColumnConfig<ParameterDefinitionDto,String> keyConfig = new ColumnConfig<ParameterDefinitionDto,String>(defPa.key(), 150, reportManagerMessages.key());
		keyConfig.setMenuDisabled(true);
		keyConfig.setSortable(false);
		columns.add(keyConfig);
		
		/* name column */
		ColumnConfig<ParameterDefinitionDto,String> nameConfig = new ColumnConfig<ParameterDefinitionDto,String>(defPa.name(), 200, BaseMessages.INSTANCE.name()); 
		nameConfig.setMenuDisabled(true);
		nameConfig.setSortable(false);
		columns.add(nameConfig);
		
		/* type column */
		ColumnConfig<ParameterDefinitionDto,String> ccType = new ColumnConfig<ParameterDefinitionDto,String>(new DisplayTitleValueProvider<ParameterDefinitionDto>(), 150, reportManagerMessages.type()); 
		ccType.setMenuDisabled(true);
		ccType.setSortable(false);
		columns.add(ccType);
		
		 /* config indicator column */
		ColumnConfig<ParameterDefinitionDto,ParameterDefinitionDto> ccConfigIndicator = new ColumnConfig<ParameterDefinitionDto,ParameterDefinitionDto>(new IdentityValueProvider<ParameterDefinitionDto>("__parameter_mods"), 120, ParametersMessages.INSTANCE.optionsHeader());
	    ccConfigIndicator.setMenuDisabled(true);
	    ccConfigIndicator.setSortable(false);
	    ccConfigIndicator.setCell(new AbstractCell<ParameterDefinitionDto>() {
			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					ParameterDefinitionDto model, SafeHtmlBuilder sb) {
				if(model.isHidden())
					sb.append(BaseIcon.FILE_O.toSafeHtml());
				if(!model.isEditable())
					sb.append(BaseIcon.EDIT_NOT.toSafeHtml());
				if(model.isMandatory())
					sb.append(BaseIcon.EXCLAMATION.toSafeHtml());
			}


	    });
	    columns.add(ccConfigIndicator);
		
		/* create grid */
		final Grid<ParameterDefinitionDto> grid = new Grid<ParameterDefinitionDto>(parameterStore, new ColumnModel<ParameterDefinitionDto>(columns));
		grid.setSelectionModel(new GridSelectionModel<ParameterDefinitionDto>());
		
		if(GXT.isIE())
			grid.getView().setForceFit(true);
		
		grid.getView().setShowDirtyCells(false);
		grid.getView().setSortingEnabled(false);
		
		/* menu */
		Menu menu = new Menu();
		grid.setContextMenu(menu);
		
		MenuItem edit = new DwMenuItem(BaseMessages.INSTANCE.edit(), BaseIcon.COG_EDIT);
		edit.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				List<ParameterDefinitionDto> selectedItems = parameterGrid.getSelectionModel().getSelectedItems();
				if(null != selectedItems && ! selectedItems.isEmpty()){
					ParameterDefinitionDto pd = selectedItems.get(0);
					displayEditParameter(pd);
				}
			}
		});
		menu.add(edit);
		menu.add(new SeparatorMenuItem());
		MenuItem remove = new DwMenuItem(BaseMessages.INSTANCE.remove(), BaseIcon.REMOVE);
		remove.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				prepareRemove();
			}
		});
		menu.add(remove);
		
		// edit //
		final GridEditing<ParameterDefinitionDto> editing = new GridInlineEditing<ParameterDefinitionDto>(grid);
		editing.addEditor(nameConfig, new TextField());
		editing.addEditor(keyConfig, new TextField());
		
		grid.addCellDoubleClickHandler(new CellDoubleClickHandler() {
			@Override
			public void onCellClick(CellDoubleClickEvent event) {
				ParameterDefinitionDto pd = grid.getStore().get(event.getRowIndex());
				if(null != pd)
					displayEditParameter(pd);
			}
		});
		
		/* listen to edit events */
		editing.addCompleteEditHandler(new CompleteEditHandler<ParameterDefinitionDto>() {
			@Override
			public void onCompleteEdit(
					CompleteEditEvent<ParameterDefinitionDto> event) {
				ParameterDefinitionDto pd = grid.getStore().get(event.getEditCell().getRow());
				if(null != pd)
					updateParameter(pd);
			}
		});
		
		/* keyboard */
		new ExtendedKeyNav(grid){
			protected void onSelectAll() {
				grid.getSelectionModel().selectAll();
			};
			@Override
			public void onDelete(NativeEvent evt) {
				if(! editing.isEditing())
					prepareRemove();
			}
		};
		
		return grid;
	}
	

	protected void configureDnD(Grid<ParameterDefinitionDto> grid) {
		/* make it draggable */
		final GridDragSource<ParameterDefinitionDto> gds = new GridDragSource<ParameterDefinitionDto>(grid);
		final GridDropTarget<ParameterDefinitionDto> target = new GridDropTarget<ParameterDefinitionDto>(grid){
			
			@Override
			protected void onDragDrop(DndDropEvent e) {
				  super.onDragDrop(e);
				  
				  List<?> list = (List<?>) e.getData();
				  if(list.size() > 0){
					  ParameterDefinitionDto parameter = (ParameterDefinitionDto) list.get(0);
					  int index = parameterStore.indexOf(parameter);
					  moveParameter(parameter, index);
				  }
			  }
		};
		target.setFeedback(Feedback.INSERT);
		target.setAllowSelfAsSource(true);
	}
	
	
	
	protected void displayEditParameter(final ParameterDefinitionDto definition) {
		WestPropertiesDialog editParameterWindow = new WestPropertiesDialog(840, 620, 210);
		editParameterWindow.setOnEsc(false);
		
		editParameterWindow.setHeading(ParametersMessages.INSTANCE.editParameter() + (definition.getKey() == null ? "key" : definition.getKey())); //$NON-NLS-1$
		editParameterWindow.setHeaderIcon(BaseIcon.COG);
		
		/* parameter main options */
		Widget mainOptionsComponent = createParameterMainOptionsPanel(definition);
		editParameterWindow.addCard(
				ParametersMessages.INSTANCE.parameterProperties(), 
				BaseIcon.INFO_CIRCLE, 
				mainOptionsComponent);
		
		/* parameter special options pane */
		Widget editComponent = createParameterSpecialOptionsPanel(definition);
		editParameterWindow.addCard(
				ParametersMessages.INSTANCE.specialProperties(), 
				BaseIcon.COG, 
				editComponent);
		
		/* buttons */
		addButtonsToEditWindow(editParameterWindow, definition);
		
		editParameterWindow.show();
	}
	
	private void addButtonsToEditWindow(final DwWindow window, final ParameterDefinitionDto definition) {
		/* cancel */
		DwTextButton cancelBtn = new  DwTextButton(BaseMessages.INSTANCE.cancel());
		cancelBtn.addSelectHandler(new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
				definition.clearModified();
				window.hide();
			}
		});
		window.addButton(cancelBtn);
		
		/* submit */
		final DwTextButton submitBtn = new DwTextButton(BaseMessages.INSTANCE.apply());
		submitBtn.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				if(null != editComponent){
					ParameterConfigurator configurator = parameterService.getConfigurator(definition);
					if(null == configurator)
						throw new IllegalStateException("We should have a configurator for " + definition.getClass().getName()); //$NON-NLS-1$
					
					/* update definition before close */
					configurator.updateDefinitionOnSubmit(definition, editComponent);
				}
				
				//TODO: GXT CHECK
				//window.hide(submitBtn);
				window.hide();
				updateParameter(definition);
			}
		});
		
		window.addButton(submitBtn);
	}

	protected Widget createParameterMainOptionsPanel(final ParameterDefinitionDto definition) {
		ParameterConfigurator configurator = parameterService.getConfigurator(definition);
		
		/* create form */
		SimpleForm form = SimpleForm.getInlineInstance();
		
		form.beginRow();
		form.addField(String.class, ParameterDefinitionDtoPA.INSTANCE.name(), BaseMessages.INSTANCE.propertyName()); //$NON-NLS-1$ //$NON-NLS-2$
		form.addField(String.class, ParameterDefinitionDtoPA.INSTANCE.key(), ParametersMessages.INSTANCE.key());
		form.endRow();
		
		form.addField(String.class, ParameterDefinitionDtoPA.INSTANCE.description(), BaseMessages.INSTANCE.propertyDescription(), new SFFCTextAreaImpl());
		
		if(configurator.canDependOnParameters()){
			form.addField(ParameterDefinitionDto.class, ParameterDefinitionDtoPA.INSTANCE.dependsOn(), ParametersMessages.INSTANCE.dependsOn(), new SFFCBaseModel<ParameterDefinitionDto>() { 
	
				@Override
				public ListStore<ParameterDefinitionDto> getAllItemsStore() {
					ListStore<ParameterDefinitionDto> tmpParameterStore = new ListStore<ParameterDefinitionDto>(defPa.dtoId());
					for(ParameterDefinitionDto def : parameterStore.getAll())
						if(!def.equals(definition))
							tmpParameterStore.add(def);
					return tmpParameterStore;
				}
	
				@Override
				public Map<ValueProvider<ParameterDefinitionDto, String>, String> getDisplayProperties() {
					Map<ValueProvider<ParameterDefinitionDto, String>, String> map = new HashMap<ValueProvider<ParameterDefinitionDto, String>, String>();
					map.put(defPa.key(), ParametersMessages.INSTANCE.key()); 
					return map;
				}
	
				@Override
				public boolean isMultiSelect() {
					return true;
				}
			});
		}
		
		form.beginRow();
		form.addField(Boolean.class, ParameterDefinitionDtoPA.INSTANCE.hidden(), ParametersMessages.INSTANCE.propertyHidden());
		form.addField(Boolean.class, ParameterDefinitionDtoPA.INSTANCE.editable(), ParametersMessages.INSTANCE.propertyEditable());
		form.addField(Boolean.class, ParameterDefinitionDtoPA.INSTANCE.mandatory(), ParametersMessages.INSTANCE.propertyMandatory());
		form.addField(Boolean.class, ParameterDefinitionDtoPA.INSTANCE.displayInline(), ParametersMessages.INSTANCE.propertyDisplayInline());
		form.endRow();
		
		form.setLabelAlign(LabelAlign.TOP);
		form.setFieldWidth(150);
		form.addField(Integer.class, ParameterDefinitionDtoPA.INSTANCE.labelWidth(), ParametersMessages.INSTANCE.propertyLabelWidth());
		
		form.bind(definition);
		
		DwContentPanel panel = new DwContentPanel();
		panel.setLightHeader();
		panel.setHeading(ParametersMessages.INSTANCE.parameterProperties() + " (" + ParametersMessages.INSTANCE.generalPoperties() + ")");
		panel.setWidget(form);
		
		return panel;
	}

	protected Widget createParameterSpecialOptionsPanel(ParameterDefinitionDto definition) {
		/* ask parameter to configure panel */
		ParameterConfigurator configurator = parameterService.getConfigurator(definition);
		if(null == configurator)
			throw new IllegalStateException("We should have a configurator for " + definition.getClass().getName()); //$NON-NLS-1$
		
		/* add edit component */
		editComponent = configurator.getEditComponentForDefinition(definition, (ReportDto) getSelectedNode());
		
		/* create wrapper */
		DwContentPanel wrapper = new DwContentPanel();
		wrapper.setLightHeader();
		wrapper.setHeading(ParametersMessages.INSTANCE.parameterProperties() + " (" + ParametersMessages.INSTANCE.specialProperties() + ")");
		wrapper.setWidget(editComponent);
		
		/* for some reason we need this extra wrapper to get scroll bars .. very strange .. */
		return wrapper;
	}

	public void addParameter(ParameterConfigurator configurator, AbstractNodeDto node) {
		if(! (node instanceof ReportDto) )
			throw new IllegalArgumentException("Parameters can only be added to reports. " +  node.getClass().getName() + " was passed."); //$NON-NLS-1$ //$NON-NLS-2$
		
		/* call server */
		parameterDao.addParameter((ReportDto)getSelectedNode(), configurator.getNewDto((ReportDto) node), node, new NotamCallback<ReportDto>(ParametersMessages.INSTANCE.parameterAdded()) { //$NON-NLS-1$
			@Override
			public void doOnSuccess(ReportDto adjustedReport) {
				updateStore(adjustedReport);
			}			
		});
	}
	
	protected void duplicateParameter(ParameterDefinitionDto param) {
		List<ParameterDefinitionDto> list = new ArrayList<ParameterDefinitionDto>();
		list.add(param);
		duplicateParameters(list);
	}
	
	protected void duplicateParameters(List<ParameterDefinitionDto> params) {
		parameterDao.duplicateParameters(params, getSelectedNode(), new NotamCallback<ReportDto>(ParametersMessages.INSTANCE.parameterDuplicated()) { //$NON-NLS-1$
			@Override
			public void doOnSuccess(ReportDto adjustedReport) {
				updateStore(adjustedReport);
			}			
		});
	}
	
	protected void updateParameter(final ParameterDefinitionDto pd) {
		/* call server */
		mask(BaseMessages.INSTANCE.storingMsg());
		parameterDao.updateParameter((ReportDto)getSelectedNode(), pd, new NotamCallback<ReportDto>(ParametersMessages.INSTANCE.parameterChanged()) {
			@Override
			public void doOnSuccess(ReportDto updatedNode) {
				updateStore(updatedNode);
				unmask();
			}
			@Override
			public void doOnFailure(Throwable caught) {
				reloadNode(new RsAsyncCallback<AbstractNodeDto>(){
					@Override
					public void onSuccess(AbstractNodeDto result) {
						updateStore(result);
						unmask();
					}
				});
			}
		});
	}
	
	protected void removeParameter(ParameterDefinitionDto parameter) {
		Set<ParameterDefinitionDto> toDeleteSet = new HashSet<ParameterDefinitionDto>();
		toDeleteSet.add(parameter);
		removeParameters(toDeleteSet);
	}

	protected void removeParameters(Collection<ParameterDefinitionDto> parameters) {
		/* call server */
		if(null != parameters && ! parameters.isEmpty()){
			parameterDao.removeParameters((ReportDto)getSelectedNode(), parameters, new NotamCallback<ReportDto>(ParametersMessages.INSTANCE.parameterRemoved()) {
				@Override
				public void doOnSuccess(ReportDto adjustedReport) {
					updateStore(adjustedReport);
				}
			});
		}
	}
	
	protected void moveParameter(ParameterDefinitionDto parameter, int to) {
		/* call server */
		parameterDao.movedParameter(parameter, to, new NotamCallback<ReportDto>(ParametersMessages.INSTANCE.parameterMoved()) {
			@Override
			public void doOnSuccess(ReportDto updatedNode) {
				updateStore(updatedNode);
			}
		});
	}
	
	protected void updateParameterInstances(final List<ParameterDefinitionDto> models) {
		if(null == models || models.isEmpty()){
			new DwAlertMessageBox(BaseMessages.INSTANCE.warning(), ParametersMessages.INSTANCE.noParametersSelected()).show();
			return;
		}
			
		ConfirmMessageBox cmb = new DwConfirmMessageBox(ParametersMessages.INSTANCE.updateInstancesMsgTitle(), ParametersMessages.INSTANCE.updateInstancesMsg());
		cmb.addDialogHideHandler(new DialogHideHandler() {
			@Override
			public void onDialogHide(DialogHideEvent event) {
				if (event.getHideButton() == PredefinedButton.YES) {
					parameterDao.updateParameterInstances((ReportDto)getSelectedNode(), models, new NotamCallback<ReportDto>(ParametersMessages.INSTANCE.parameterInstancesUpdated()) {
						@Override
						public void doOnSuccess(ReportDto updatedNode) {
							updateStore(updatedNode);
						}
					});
				}	
			}
		});
		cmb.show();
	}

	
	public void updateStore(AbstractNodeDto node) {
		if(! (node instanceof ReportDto) && ! (node instanceof ReportFolderDto))
			throw new IllegalArgumentException("Parameters can only be added to reports or folders. " +  node.getClass().getName() + " was passed."); //$NON-NLS-1$ //$NON-NLS-2$
		
		/* clear store */
		parameterStore.clear();
		
		List<ParameterDefinitionDto> parameters = new ArrayList<ParameterDefinitionDto>();
		
		if(node instanceof ReportDto)
			parameters = ((ReportDto)node).getParameterDefinitions();
		
		for(ParameterDefinitionDto pd : parameters){
			if(pd instanceof ParameterDefinitionDtoDec)
				((ParameterDefinitionDtoDec) pd).setReport((ReportDto) node);
			parameterStore.add(pd);
		}
	}
	
	
	
}
