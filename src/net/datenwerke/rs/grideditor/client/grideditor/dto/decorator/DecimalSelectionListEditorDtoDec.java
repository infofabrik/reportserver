package net.datenwerke.rs.grideditor.client.grideditor.dto.decorator;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import net.datenwerke.gxtdto.client.utils.modelkeyprovider.BasicObjectModelKeyProvider;
import net.datenwerke.rs.grideditor.client.grideditor.dto.DecimalSelectionListEditorDto;
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
 * Dto Decorator for {@link DecimalSelectionListEditorDto}
 *
 */
public class DecimalSelectionListEditorDtoDec extends DecimalSelectionListEditorDto {


	private static final long serialVersionUID = 1L;

	public DecimalSelectionListEditorDtoDec() {
		super();
	}

	@Override
	public Field addEditor(ColumnConfig columnConfig,
			GridEditing<GridEditorRecordDto> editing) {
		ListStore<String> store = new ListStore<String>(new BasicObjectModelKeyProvider<String>());
		Map<String, BigDecimal> valueMap = getValueMap();
		if(null == valueMap){
			valueMap = new TreeMap<String, BigDecimal>();
			for(BigDecimal i : getValues())
				valueMap.put(""+i, i);
		}

		store.addAll(valueMap.keySet());
		
		ComboBox<String> combo = new ComboBox<String>(store, new StringLabelProvider<String>());
	    combo.setAllowBlank(true);
	    combo.setForceSelection(isForceSelection());
	    combo.setTriggerAction(TriggerAction.ALL);

	    final Map<String, BigDecimal> map = valueMap; 
    	editing.addEditor(columnConfig, new Converter<BigDecimal,String>() {

			@Override
			public BigDecimal convertFieldValue(String object) {
				if(null == object)
					return null;
				return map.get(object);
			}
			
			@Override
			public String convertModelValue(BigDecimal object) {
				if(null == object)
					return null;
				for(Entry<String, BigDecimal> e: map.entrySet())
					if(object.equals(e.getValue()))
						return e.getKey();
				return null;
			}
		}, combo);
		
		return combo;
	}
}
