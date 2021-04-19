package net.datenwerke.gxtdto.client.ui.helper.grid.keyvalue;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.gxtdto.client.locale.BaseMessages;

import com.google.gwt.core.client.GWT;
import com.google.inject.Singleton;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.event.RowDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.RowDoubleClickEvent.RowDoubleClickHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

@Singleton
public class KeyValueGridHelper {

	private static KeyValuePropertyPA keyvpPa = GWT.create(KeyValuePropertyPA.class);
	
	public Grid<KeyValueProperty> create(ListStore<KeyValueProperty> store){
		return create(store, BaseMessages.INSTANCE.key(), BaseMessages.INSTANCE.value(), 150, 200);
	}
	
	public Grid<KeyValueProperty> create(final ListStore<KeyValueProperty> store, String keyLabel, String valueLabel, int keySize, int valueSize){
		List<ColumnConfig<KeyValueProperty,?>> colConfig = new ArrayList<ColumnConfig<KeyValueProperty,?>>();
		
		ColumnConfig<KeyValueProperty,String> keyColumn = new ColumnConfig<KeyValueProperty,String>(keyvpPa.key(), keySize, keyLabel);
		keyColumn.setMenuDisabled(true);
		colConfig.add(keyColumn);
		
		ColumnConfig<KeyValueProperty,String> valueColumn = new ColumnConfig<KeyValueProperty,String>(keyvpPa.value(), valueSize, valueLabel);
		valueColumn.setMenuDisabled(true);
		colConfig.add(valueColumn);
		
		Grid<KeyValueProperty> grid = new Grid<KeyValueProperty>(store, new ColumnModel<KeyValueProperty>(colConfig));
		grid.getView().setAutoExpandColumn(valueColumn);
		
		grid.addRowDoubleClickHandler(new RowDoubleClickHandler() {
			@Override
			public void onRowDoubleClick(RowDoubleClickEvent event) {
				KeyValueProperty keyValueProperty = store.get(event.getRowIndex());
				if(null != keyValueProperty){
					keyValueProperty.onRowDoubleClick(event);
				}
			}
		});
		
		return grid;
	}
}
