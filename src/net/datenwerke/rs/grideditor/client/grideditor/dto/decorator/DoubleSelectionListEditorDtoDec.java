package net.datenwerke.rs.grideditor.client.grideditor.dto.decorator;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import net.datenwerke.gxtdto.client.utils.modelkeyprovider.BasicObjectModelKeyProvider;
import net.datenwerke.rs.grideditor.client.grideditor.dto.DoubleSelectionListEditorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordDto;

import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.data.shared.Converter;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.StringLabelProvider;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.Field;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.editing.GridEditing;

/**
 * Dto Decorator for {@link DoubleSelectionListEditorDto}
 *
 */
public class DoubleSelectionListEditorDtoDec extends DoubleSelectionListEditorDto {


	private static final long serialVersionUID = 1L;

	public DoubleSelectionListEditorDtoDec() {
		super();
	}

	@Override
	public Field addEditor(ColumnConfig columnConfig,
			GridEditing<GridEditorRecordDto> editing) {
		ListStore<String> store = new ListStore<String>(new BasicObjectModelKeyProvider<String>());
		Map<String, Double> valueMap = getValueMap();
		if(null == valueMap){
			valueMap = new TreeMap<String, Double>();
			for(Double i : getValues())
				valueMap.put(""+i, i);
		}

		store.addAll(valueMap.keySet());
		
		ComboBox<String> combo = new ComboBox<String>(store, new StringLabelProvider<String>());
	    combo.setAllowBlank(true);
	    combo.setForceSelection(isForceSelection());
	    combo.setTriggerAction(TriggerAction.ALL);

	    final Map<String, Double> map = valueMap; 
    	editing.addEditor(columnConfig, new Converter<Double,String>() {

			@Override
			public Double convertFieldValue(String object) {
				if(null == object)
					return null;
				return map.get(object);
			}
			
			@Override
			public String convertModelValue(Double object) {
				if(null == object)
					return null;
				for(Entry<String, Double> e: map.entrySet())
					if(object.equals(e.getValue()))
						return e.getKey();
				return null;
			}
		}, combo);
		
		return combo;
	}
}
