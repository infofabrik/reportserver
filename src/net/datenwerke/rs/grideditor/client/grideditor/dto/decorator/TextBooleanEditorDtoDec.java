package net.datenwerke.rs.grideditor.client.grideditor.dto.decorator;

import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.TextBooleanEditorDto;

import com.sencha.gxt.data.shared.Converter;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.Field;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.editing.GridEditing;

/**
 * Dto Decorator for {@link TextBooleanEditorDto}
 *
 */
public class TextBooleanEditorDtoDec extends TextBooleanEditorDto {


	private static final long serialVersionUID = 1L;

	public TextBooleanEditorDtoDec() {
		super();
	}

	@Override
	public Field addEditor(ColumnConfig columnConfig,
			GridEditing<GridEditorRecordDto> editing) {
		CheckBox field = new CheckBox();
		editing.addEditor(columnConfig, new Converter<String, Boolean>(){
			@Override
			public String convertFieldValue(Boolean bool) {
				if(null == bool)
					return null;
				return bool ? getTrueText() : getFalseText();
			}

			@Override
			public Boolean convertModelValue(String strBool) {
				if(null == strBool)
					return null;
				return getTrueText().equals(strBool);
			}
			
		}, field);
		return field;
	}
}
