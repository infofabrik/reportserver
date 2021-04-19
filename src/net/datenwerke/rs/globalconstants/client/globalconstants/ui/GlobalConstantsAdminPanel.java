package net.datenwerke.rs.globalconstants.client.globalconstants.ui;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwConfirmMessageBox;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.servercommunication.callback.NotamCallback;
import net.datenwerke.gxtdto.client.ui.helper.wrapper.ToolbarWrapperPanel;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.rs.globalconstants.client.globalconstants.GlobalConstantsDao;
import net.datenwerke.rs.globalconstants.client.globalconstants.dto.GlobalConstantDto;
import net.datenwerke.rs.globalconstants.client.globalconstants.dto.pa.GlobalConstantDtoPA;
import net.datenwerke.rs.globalconstants.client.globalconstants.locale.GlobalConstantsMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.event.StoreUpdateEvent;
import com.sencha.gxt.data.shared.event.StoreUpdateEvent.StoreUpdateHandler;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.CompleteEditEvent;
import com.sencha.gxt.widget.core.client.event.CompleteEditEvent.CompleteEditHandler;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.DialogHideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.editing.GridEditing;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;
import com.sencha.gxt.widget.core.client.menu.Item;

/**
 * 
 *
 */
@Singleton
public class GlobalConstantsAdminPanel extends DwContentPanel {

	private final GlobalConstantsDao constantsDao;
	private final ToolbarService toolbarService;
	
	private ListStore<GlobalConstantDto> globalConstantStore;
	
	private GlobalConstantDtoPA globalConstantProps;
	
	@Inject
	public GlobalConstantsAdminPanel(
		GlobalConstantsDao constantsDao,
		ToolbarService toolbarService
		){
		
		/* store objects */
		this.constantsDao = constantsDao;
		this.toolbarService = toolbarService;
		
		/* initialize ui */
		initializeUI();
	}

	private void initializeUI() {
		setHeading(GlobalConstantsMessages.INSTANCE.dialogTitle());
		
		/* create props */
		globalConstantProps = GWT.create(GlobalConstantDtoPA.class);
		
		/* create store */
		globalConstantStore = new ListStore<GlobalConstantDto>(globalConstantProps.dtoId());
		globalConstantStore.addStoreUpdateHandler(new StoreUpdateHandler<GlobalConstantDto>() {
			@Override
			public void onUpdate(StoreUpdateEvent<GlobalConstantDto> event) {
				for(GlobalConstantDto constant : event.getItems())
					updateConstant(constant);
			}
		});
		
		/* create grid */
		Component gridComponent = createGrid();
		
		DwContentPanel wrapper = new DwContentPanel();
		wrapper.setLightHeader();
		wrapper.setHeading(GlobalConstantsMessages.INSTANCE.dialogTitle());
		wrapper.setInfoText(GlobalConstantsMessages.INSTANCE.dialogDescription());
		wrapper.add(gridComponent);
		
		VerticalLayoutContainer outerWrapper = new VerticalLayoutContainer();
		outerWrapper.add(wrapper, new VerticalLayoutData(1,1, new Margins(10)));
		
		add(outerWrapper);
	}

	private void loadConstants() {
		constantsDao.loadGlobalConstants(new RsAsyncCallback<List<GlobalConstantDto>>(){
			@Override
			public void onSuccess(List<GlobalConstantDto> result) {
				/* clear store */
				globalConstantStore.clear();
				
				for(GlobalConstantDto constant : result)
					globalConstantStore.add(constant);
				
				enable();
				unmask();
			}
		});
	}

	private Component createGrid() {
		/* configure columns */ 
		List<ColumnConfig<GlobalConstantDto, ?>> columns = new ArrayList<ColumnConfig<GlobalConstantDto, ?>>();
		
		/* name column */
		ColumnConfig<GlobalConstantDto, String> nameConfig = new ColumnConfig<GlobalConstantDto, String>(globalConstantProps.name(), 200, GlobalConstantsMessages.INSTANCE.propertyName()); 
		nameConfig.setMenuDisabled(true);
		columns.add(nameConfig);
		
		/* value column */
		ColumnConfig<GlobalConstantDto, String> valueConfig = new ColumnConfig<GlobalConstantDto, String>(globalConstantProps.value(), 200, GlobalConstantsMessages.INSTANCE.propertyValue()); 
		valueConfig.setMenuDisabled(true);
		columns.add(valueConfig);
		
		/* create grid */
		final Grid<GlobalConstantDto> grid = new Grid<GlobalConstantDto>(globalConstantStore, new ColumnModel<GlobalConstantDto>(columns));
		grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		grid.getView().setAutoExpandColumn(valueConfig);
		grid.getView().setShowDirtyCells(false);
		
		// edit //
		GridEditing<GlobalConstantDto> editing = new GridInlineEditing<GlobalConstantDto>(grid);
		editing.addEditor(nameConfig, new TextField());
		editing.addEditor(valueConfig, new TextField());
		editing.addCompleteEditHandler(new CompleteEditHandler<GlobalConstantDto>() {
			@Override
			public void onCompleteEdit(
					CompleteEditEvent<GlobalConstantDto> event) {
				globalConstantStore.commitChanges();
			}
		});
		
		ToolbarWrapperPanel wrapper = new ToolbarWrapperPanel(grid){
			
			@Override
			protected void removeAllButtonClicked(SelectionEvent<Item> event) {
				ConfirmMessageBox cmb = new DwConfirmMessageBox(GlobalConstantsMessages.INSTANCE.confirmRemoveAllTitle(), GlobalConstantsMessages.INSTANCE.confirmRemoveAllText());
				cmb.addDialogHideHandler(new DialogHideHandler() {
					
					@Override
					public void onDialogHide(DialogHideEvent event) {
						 if (event.getHideButton() == PredefinedButton.YES) 
							 removeAllConstants();						
					}
				});
				cmb.show();
			}

			@Override
			protected void removeButtonClicked(SelectEvent ce) {
				List<GlobalConstantDto> constantsToRemove = grid.getSelectionModel().getSelectedItems();
				if(null != constantsToRemove)
					removeSelectedConstant(constantsToRemove);
			}
		};
		
		DwTextButton addConstantBtn = toolbarService.createSmallButtonLeft(GlobalConstantsMessages.INSTANCE.addConstantText(), BaseIcon.EDIT);
		addConstantBtn.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				addNewConstant();
			}
		});
		
		wrapper.getToolbar().add(addConstantBtn);
		
		/* remove buttons */
		wrapper.addRemoveButtons();
		
		return wrapper;
	}

	protected void addNewConstant() {
		constantsDao.addNewConstant(new RsAsyncCallback<GlobalConstantDto>(){
			@Override
			public void onSuccess(GlobalConstantDto result) {
				globalConstantStore.add(result);
			}
		});
	}
	
	protected void removeAllConstants() {
		constantsDao.removeAllConstants(new RsAsyncCallback<Void>(){
			@Override
			public void onSuccess(Void result) {
				globalConstantStore.clear();
			}
		}, globalConstantStore.getAll());
	}
	
	protected void removeSelectedConstant(final	List<GlobalConstantDto> constantsToRemove) {
		constantsDao.removeConstants(constantsToRemove, new RsAsyncCallback<Void>(){
			@Override
			public void onSuccess(Void result) {
				for(GlobalConstantDto constant: constantsToRemove)
					globalConstantStore.remove(constant);
			}
		});
	}
	
	protected void updateConstant(GlobalConstantDto constant) {
		constantsDao.updateConstant(constant, new NotamCallback<GlobalConstantDto>(GlobalConstantsMessages.INSTANCE.constantSuccessfullyUpdated()));
	}

	public void notifyOfSelection() {
		disable();
		mask(BaseMessages.INSTANCE.loadingMsg());

		loadConstants();
	}

}
