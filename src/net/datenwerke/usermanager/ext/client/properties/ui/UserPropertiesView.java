package net.datenwerke.usermanager.ext.client.properties.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.SortDir;
import com.sencha.gxt.data.shared.Store;
import com.sencha.gxt.data.shared.Store.StoreSortInfo;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.container.NorthSouthContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.CompleteEditEvent;
import com.sencha.gxt.widget.core.client.event.CompleteEditEvent.CompleteEditHandler;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.DialogHideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.validator.AbstractValidator;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridSelectionModel;
import com.sencha.gxt.widget.core.client.grid.editing.GridEditing;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;
import com.sencha.gxt.widget.core.client.toolbar.SeparatorToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwSplitButton;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwConfirmMessageBox;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.gxtdto.client.utils.modelkeyprovider.DtoIdModelKeyProvider;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.client.usermanager.dto.UserPropertyDto;
import net.datenwerke.security.client.usermanager.dto.pa.UserPropertyDtoPA;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;
import net.datenwerke.usermanager.ext.client.properties.UserPropertiesDao;
import net.datenwerke.usermanager.ext.client.properties.locale.UserPropertiesMessages;

public class UserPropertiesView extends MainPanelView {

	public static final String VIEW_ID = "UserPropertiesView";
	
	private final UserPropertiesDao propertiesDao;
	
	private ListStore<UserPropertyDto> propertiesStore;
	private List<UserPropertyDto> removedProperties = new ArrayList<UserPropertyDto>();
	private List<UserPropertyDto> addedProperties = new ArrayList<UserPropertyDto>();


	private Grid<UserPropertyDto> grid;
	
	@Inject
	public UserPropertiesView(
			UserPropertiesDao propertiesDao
		){
		
		/* store objects */
		this.propertiesDao = propertiesDao;
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
		return BaseIcon.LIST_ALT.toImageResource();
	}
	
	@Override
	public String getComponentHeader() {
		return UserPropertiesMessages.INSTANCE.header();
	}

	@Override
	public Widget getViewComponent(AbstractNodeDto selectedNode) {
		NorthSouthContainer nsContainer = new NorthSouthContainer();
		
		/* prepare store */
		propertiesStore = new ListStore<UserPropertyDto>(new DtoIdModelKeyProvider());
		propertiesStore.addSortInfo(new StoreSortInfo<UserPropertyDto>(UserPropertyDtoPA.INSTANCE.key(), SortDir.ASC));
		updateStore(getUser());
		
		/* create grid */
		createGrid();
		nsContainer.setWidget(grid);
		
		/* create toolbar */
		ToolBar toolbar = createToolbar();
		nsContainer.setNorthWidget(toolbar);
		
		DwContentPanel main = new DwContentPanel();
		main.setLightHeader();
		main.setHeading(UserPropertiesMessages.INSTANCE.header());
		main.add(nsContainer);
		main.setInfoText(UserPropertiesMessages.INSTANCE.description());
		
		VerticalLayoutContainer wrapper = new VerticalLayoutContainer();
		wrapper.add(main, new VerticalLayoutData(1,-1, new Margins(10)));
		
		return wrapper;
	}
	
	private ToolBar createToolbar() {
		ToolBar toolbar = new DwToolBar();
		
		DwSplitButton addBtn = new DwSplitButton(BaseMessages.INSTANCE.add());
		addBtn.setIcon(BaseIcon.COG_ADD);

		final Menu addBtnMenu = new DwMenu();
		addBtnMenu.setMaxHeight(300);
		addBtn.setMenu(addBtnMenu);
		propertiesDao.getPropertyKeys(new RsAsyncCallback<List<String>>(){
			@Override
			public void onSuccess(List<String> result) {
				for(final String entry : result){
					MenuItem item = new DwMenuItem(entry);
					addBtnMenu.add(item);
					item.addSelectionHandler(new SelectionHandler<Item>() {
						@Override
						public void onSelection(SelectionEvent<Item> event) {
							addProperty(entry);
						}
					});
				}
			}
		});
		
		addBtn.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				addProperty(null);
			}
		});
		toolbar.add(addBtn);
		
		toolbar.add(new SeparatorToolItem());
		
		DwSplitButton removeButton = new DwSplitButton(BaseMessages.INSTANCE.remove());
		removeButton.setIcon(BaseIcon.DELETE);
		removeButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				List<UserPropertyDto> selectedItems = grid.getSelectionModel().getSelectedItems();
				
				for(UserPropertyDto r : selectedItems){
					if(addedProperties.remove(r))
						propertiesStore.remove(r);
					else {
						removedProperties.add(r);
						propertiesStore.update(r);
					}
				}
			}
		});
		toolbar.add(removeButton);
		
		MenuItem removeAllButton = new DwMenuItem(BaseMessages.INSTANCE.removeAll(), BaseIcon.DELETE);
		removeAllButton.addSelectionHandler(new SelectionHandler<Item>() {

			@Override
			public void onSelection(SelectionEvent<Item> event) {
				ConfirmMessageBox cmb = new DwConfirmMessageBox(BaseMessages.INSTANCE.confirmDeleteTitle(), BaseMessages.INSTANCE.confirmDeleteMsg(""));
				cmb.addDialogHideHandler(new DialogHideHandler() {
					@Override
					public void onDialogHide(DialogHideEvent event) {
						if (event.getHideButton() == PredefinedButton.YES){ 
							for(UserPropertyDto r : grid.getStore().getAll()){
								if(addedProperties.remove(r))
									propertiesStore.remove(r);
								else {
									removedProperties.add(r);
									propertiesStore.update(r);
								}
							}
						}
					}
				});
				cmb.show();
			}
		});
		
		Menu remMenu = new DwMenu();
		remMenu.add(removeAllButton);
		removeButton.setMenu(remMenu);
		
		toolbar.add(new FillToolItem());
		
		DwTextButton resetBtn = new DwTextButton(BaseMessages.INSTANCE.revert(),BaseIcon.ROTATE_LEFT);
		resetBtn.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				propertiesStore.rejectChanges();
				for(UserPropertyDto p : addedProperties)
					propertiesStore.remove(p);
				addedProperties.clear();
				removedProperties.clear();
				
				for(UserPropertyDto r : grid.getStore().getAll())
					propertiesStore.update(r);
			}
		});
		toolbar.add(resetBtn);
		
		DwTextButton storeBtn = new DwTextButton(BaseMessages.INSTANCE.save(),BaseIcon.ACCEPT);
		toolbar.add(storeBtn);
		storeBtn.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				mask(BaseMessages.INSTANCE.storingMsg());
				
				List<UserPropertyDto> rP = new ArrayList<UserPropertyDto>(removedProperties);
				List<UserPropertyDto> aP = new ArrayList<UserPropertyDto>(addedProperties);
				
				addedProperties.clear();
				removedProperties.clear();
				
				for(UserPropertyDto p : rP)
					propertiesStore.remove(p);
				
				Collection<Store<UserPropertyDto>.Record> modifiedRecords = propertiesStore.getModifiedRecords();
				List<UserPropertyDto> mP = new ArrayList<UserPropertyDto>();
				for(Store<UserPropertyDto>.Record r : modifiedRecords){
					r.commit(true);
					mP.add(r.getModel());
				}
				
				propertiesDao.updateProperties(getUser(), aP, mP, rP, new RsAsyncCallback<UserDto>(){
					@Override
					public void onSuccess(UserDto result) {
						super.onSuccess(result);
						unmask();
						propertiesStore.clear();
						updateStore(result);
					}
					@Override
					public void onFailure(Throwable caught) {
						super.onFailure(caught);
						unmask();
					}
				});
			}
		});
		
		return toolbar;
	}

	protected void addProperty(String name) {
		UserPropertyDto property = new UserPropertyDto();
		
		name = getUniqueName(name);
		
		property.setKey(name);
		addedProperties.add(property);
		propertiesStore.add(property);
	}
	
	protected String getUniqueName(String name) {
		if(null == name)
			name = "unnamed";
		String c = name;
		int i = 0;
		while(hasPropertyWith(name))
			name = c + (++i);
		return name;
	}

	protected boolean hasPropertyWith(String name) {
		for(UserPropertyDto p : propertiesStore.getAll())
			if(name.equals(p.getKey()))
				return true;
		return false;
	}

	protected void updateStore(UserDto user) {
		for(UserPropertyDto prop : user.getProperties())
			propertiesStore.add(prop);
	}

	protected UserDto getUser(){
		AbstractNodeDto node = getSelectedNode();
		assert node instanceof UserDto;
		
		return (UserDto) node;
	}

	private void createGrid() {
		/* configure columns */
		List<ColumnConfig<UserPropertyDto,?>> columns = new ArrayList<ColumnConfig<UserPropertyDto,?>>();
	
		/* icon column */
		ColumnConfig<UserPropertyDto,UserPropertyDto> icConfig = new ColumnConfig<UserPropertyDto,UserPropertyDto>(new IdentityValueProvider<UserPropertyDto>(), 40); 
		icConfig.setMenuDisabled(true);
		icConfig.setCell(new AbstractCell<UserPropertyDto>(){
			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context, UserPropertyDto value,
					SafeHtmlBuilder sb) {
				if(removedProperties.contains(value))
					sb.append(BaseIcon.DELETE.toSafeHtml());
				else if(addedProperties.contains(value))
					sb.append(BaseIcon.ADD.toSafeHtml());
			}
		});
		columns.add(icConfig);
		
		/* name column */
		ColumnConfig<UserPropertyDto,String> nameConfig = new ColumnConfig<UserPropertyDto,String>(UserPropertyDtoPA.INSTANCE.key(), 200, UserPropertiesMessages.INSTANCE.gridNameHeader()); 
		nameConfig.setMenuDisabled(true);
		
		columns.add(nameConfig);
		
		/* value */
		ColumnConfig<UserPropertyDto,String> valueConfig = new ColumnConfig<UserPropertyDto,String>(UserPropertyDtoPA.INSTANCE.value(), 300, UserPropertiesMessages.INSTANCE.gridValueHeader()); 
		valueConfig.setMenuDisabled(true);
		
		columns.add(valueConfig);
		
		/* create grid */
		grid = new Grid<UserPropertyDto>(propertiesStore, new ColumnModel<UserPropertyDto>(columns));
		grid.getView().setAutoExpandColumn(valueConfig);
		grid.setSelectionModel(new GridSelectionModel<UserPropertyDto>());
		grid.getView().setShowDirtyCells(true);
		
		GridEditing<UserPropertyDto> editing = new GridInlineEditing<UserPropertyDto>(grid);
		
		TextField nameField = new TextField();
		nameField.addValidator(new AbstractValidator<String>() {
			@Override
			public List<EditorError> validate(Editor<String> editor, String value) {
				UserPropertyDto prop = grid.getSelectionModel().getSelectedItem();
				if(null == prop)
					return createError(editor, "Could not find property", value);
				
				for(UserPropertyDto p : propertiesStore.getAll())
					if(p != prop && null != value && value.equals(p.getKey()))
						return createError(editor, UserPropertiesMessages.INSTANCE.keyIsNotUnique(), value);
				return null;
			}
		});
		editing.addEditor(nameConfig, nameField);
		editing.addEditor(valueConfig, new TextField());
		
		editing.addCompleteEditHandler(new CompleteEditHandler<UserPropertyDto>() {
			@Override
			public void onCompleteEdit(	CompleteEditEvent<UserPropertyDto> event) {
				
				
			}
		});

	}

}
