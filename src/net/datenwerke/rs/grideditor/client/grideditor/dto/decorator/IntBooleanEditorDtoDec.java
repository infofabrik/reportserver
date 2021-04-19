package net.datenwerke.rs.grideditor.client.grideditor.dto.decorator;

import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.IntBooleanEditorDto;

import com.sencha.gxt.data.shared.Converter;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.Field;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.editing.GridEditing;

/**
 * Dto Decorator for {@link IntBooleanEditorDto}
 *
 */
public class IntBooleanEditorDtoDec extends IntBooleanEditorDto {


	private static final long serialVersionUID = 1L;

	public IntBooleanEditorDtoDec() {
		super();
	}

	@Override
	public Field addEditor(ColumnConfig columnConfig,
			GridEditing<GridEditorRecordDto> editing) {
		CheckBox field = new CheckBox();
		editing.addEditor(columnConfig, new Converter<Integer, Boolean>(){
			@Override
			public Integer convertFieldValue(Boolean bool) {
				if(null == bool)
					return null;
				return bool ? getTrueInt() : getFalseInt();
			}

			@Override
			public Boolean convertModelValue(Integer intBool) {
				if(null == intBool)
					return null;
				return intBool.equals(getTrueInt());
			}
			
		}, field);
		return field;
	}
}
