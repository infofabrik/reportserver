package net.datenwerke.rs.grideditor.client.grideditor.dto.decorator;

import java.util.Map;
import java.util.Map.Entry;

import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.data.shared.Converter;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.StringLabelProvider;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.Field;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.editing.GridEditing;

import net.datenwerke.gxtdto.client.utils.modelkeyprovider.BasicObjectModelKeyProvider;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.TextSelectionListEditorDto;

/**
 * Dto Decorator for {@link TextSelectionListEditorDto}
 *
 */
public class TextSelectionListEditorDtoDec extends TextSelectionListEditorDto {

   private static final long serialVersionUID = 1L;

   public TextSelectionListEditorDtoDec() {
      super();
   }

   @Override
   public Field addEditor(ColumnConfig columnConfig, GridEditing<GridEditorRecordDto> editing) {
      ListStore<String> store = new ListStore<String>(new BasicObjectModelKeyProvider<String>());
      final Map<String, String> valueMap = getValueMap();
      if (null == valueMap)
         store.addAll(getValues());
      else
         store.addAll(valueMap.keySet());

      ComboBox<String> combo = new ComboBox<String>(store, new StringLabelProvider<String>());
      combo.setAllowBlank(true);
      combo.setForceSelection(isForceSelection());
      combo.setTriggerAction(TriggerAction.ALL);

      if (null == valueMap)
         editing.addEditor(columnConfig, combo);
      else {
         editing.addEditor(columnConfig, new Converter<String, String>() {

            @Override
            public String convertFieldValue(String object) {
               if (null == object)
                  return null;
               return valueMap.get(object);
            }

            @Override
            public String convertModelValue(String object) {
               if (null == object)
                  return null;
               for (Entry<String, String> e : valueMap.entrySet())
                  if (object.equals(e.getValue()))
                     return e.getKey();
               return null;
            }
         }, combo);
      }

      return combo;
   }
}
