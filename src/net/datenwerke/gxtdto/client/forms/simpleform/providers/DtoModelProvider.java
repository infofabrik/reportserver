package net.datenwerke.gxtdto.client.forms.simpleform.providers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.DialogHideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.Field;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwConfirmMessageBox;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.forms.binding.HasValueFieldBinding;
import net.datenwerke.gxtdto.client.forms.selection.SelectionMode;
import net.datenwerke.gxtdto.client.forms.selection.SelectionPopup;
import net.datenwerke.gxtdto.client.forms.selection.SingleSelectionField;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHookImpl;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCBaseModel;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.gxtdto.client.utils.handlers.GenericStoreHandler;
import net.datenwerke.gxtdto.client.utils.modelkeyprovider.DtoIdModelKeyProvider;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * 
 *
 */
public class DtoModelProvider extends FormFieldProviderHookImpl {

	private ListStore<Dto> store = new ListStore<Dto>(new DtoIdModelKeyProvider());
	private Grid<Dto> grid;
	
	@Override
	public boolean doConsumes(Class<?> type, SimpleFormFieldConfiguration... configs) {
		if(null == configs || configs.length == 0 || ! (configs[0] instanceof SFFCBaseModel))
			return false;
		
		while(type != null){
			if(type.equals(Dto.class))
				return true;
			type = type.getSuperclass();
		}
		return false;
	}

	@Override
	public Widget createFormField() {
		if(! (configs[0] instanceof SFFCBaseModel))
			throw new IllegalArgumentException("need base model configuration."); //$NON-NLS-1$
		
		SFFCBaseModel config = (SFFCBaseModel) configs[0];
		
		if(config.isMultiSelect())
			return createMulti(config);
		else 
			return createSingle(config);
	}
	
	private Widget createMulti(SFFCBaseModel config) {
		return createGridComponent(config);
	}
	
	private Component createGridComponent(final SFFCBaseModel config) {
		/* configure columns */ 
		List<ColumnConfig<Dto,?>> columns = new ArrayList<ColumnConfig<Dto,?>>();
		
		Map<ValueProvider<Dto, ?>,String> displayProperties = config.getDisplayProperties();
		for(ValueProvider<Dto, ?> vp :  displayProperties.keySet()){
			ColumnConfig cc  = new ColumnConfig(vp, 150, displayProperties.get(vp));
			columns.add(cc);
		}
		
		/* create grid */
		grid = new Grid<Dto>(store, new ColumnModel<Dto>(columns));
		grid.getView().setShowDirtyCells(false);

		/* wrapper */
		final VerticalLayoutContainer wrapper = new VerticalLayoutContainer();
		wrapper.setBorders(false);
		
		/* create toolbar */
		ToolBar toolbar = new DwToolBar();
		wrapper.add(toolbar, new VerticalLayoutData(1,-1));
		
		wrapper.add(grid, new VerticalLayoutData(1,-1));
		
		DwTextButton addButton = new DwTextButton(BaseMessages.INSTANCE.add(), BaseIcon.COG_ADD);
		addButton.addSelectHandler(new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
				SelectionPopup<Dto> popup = new SelectionPopup<Dto>(config.getAllItemsStore(), config.getDisplayProperties()){
					@Override
					protected void itemsSelected(List<Dto> selectedItems) {
						store.clear();
						store.addAll(new ArrayList<Dto>(selectedItems));
					}
				};
				popup.loadData();
				popup.setSelectionMode(SelectionMode.MULTI);
				popup.setSelectedItems(store.getAll());
				popup.show();
			}
		});
		toolbar.add(addButton);
		
		
		DwTextButton removeButton = new DwTextButton( BaseMessages.INSTANCE.remove(), BaseIcon.DELETE);
		removeButton.addSelectHandler(new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
				List<Dto> selectedItems = grid.getSelectionModel().getSelectedItems();
				for(Dto model : selectedItems)
					store.remove(model);
			}
		});
		toolbar.add(removeButton);
		
		DwTextButton removeAllButton = new DwTextButton( BaseMessages.INSTANCE.removeAll(), BaseIcon.DELETE);
		removeAllButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				ConfirmMessageBox cmb = new DwConfirmMessageBox( BaseMessages.INSTANCE.removeAll(), BaseMessages.INSTANCE.confirmDeleteMsg(""));
				cmb.addDialogHideHandler(new DialogHideHandler() {
					@Override
					public void onDialogHide(DialogHideEvent event) {
						if (event.getHideButton() == PredefinedButton.YES)
							store.clear();	
					}
				});
				cmb.show();
			}
		});
		toolbar.add(removeAllButton);
		
		return wrapper;
	}

	private Field createSingle(SFFCBaseModel config) {
		final SingleSelectionField field = new SingleSelectionField<Dto>(config.getAllItemsStore(), config.getDisplayProperties());
		
		field.addValueChangeHandler(new ValueChangeHandler() {
			@Override
			public void onValueChange(ValueChangeEvent event) {
				ValueChangeEvent.fire(DtoModelProvider.this, event.getValue());
			}
		});

		return field;
	}

	@Override
	public void addFieldBindings(Object model, ValueProvider vp, Widget field) {
		SFFCBaseModel<?> config = (SFFCBaseModel) configs[0];
		
		if(config.isMultiSelect())
			addFieldBindingMulti(config, model, field, vp);
		else
			addFieldBindingSingle(config, model, field, vp);
	}

	private void addFieldBindingSingle(SFFCBaseModel<?> config, Object model,
			Widget field, ValueProvider vp) {
		final SingleSelectionField ssf = (SingleSelectionField) field;
		
		fieldBinding = new HasValueFieldBinding(ssf, model, vp){
			@Override
			protected Object convertFieldValue(Object value) {
				return ssf.getValue();
			}
			
			@Override
			protected Object convertModelValue(Object value) {
				if(null == value)
					return null;
				else {
					ssf.setValue(value, true);
					return ssf.getValue();
				}
			}
		};
	}

	private void addFieldBindingMulti(SFFCBaseModel<?> config, final Object model, Widget field, final ValueProvider vp) {
		/* set values */
		Object listData = vp.getValue(model);
		if(listData instanceof List)
			store.addAll((List<Dto>) listData);
		
		/* change model on selection change */
		store.addStoreHandlers(new GenericStoreHandler<Dto>(){
			@Override
			protected void handleDataChangeEvent() {
				vp.setValue(model, new ArrayList(store.getAll()));
			}
		});
	}
	
	@Override
	public Object getValue(Widget field){
		if(! (configs[0] instanceof SFFCBaseModel))
			throw new IllegalArgumentException("need base model configuration."); //$NON-NLS-1$
		
		SFFCBaseModel<?> config = (SFFCBaseModel<?>) configs[0];
		
		if(config.isMultiSelect()){
			return new ArrayList(store.getAll());
		}else{
			
			return ((SingleSelectionField)field).getValue();
		}
	}
}
