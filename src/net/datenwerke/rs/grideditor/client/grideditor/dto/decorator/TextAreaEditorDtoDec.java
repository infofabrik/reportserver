package net.datenwerke.rs.grideditor.client.grideditor.dto.decorator;

import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.TextAreaEditorDto;

import com.sencha.gxt.widget.core.client.form.Field;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.editing.GridEditing;

/**
 * Dto Decorator for {@link TextAreaEditorDto}
 *
 */
public class TextAreaEditorDtoDec extends TextAreaEditorDto {


	private static final long serialVersionUID = 1L;

	public TextAreaEditorDtoDec() {
		super();
	}

	@Override
	public Field addEditor(ColumnConfig columnConfig,
			GridEditing<GridEditorRecordDto> editing) {
		TextArea ta = new TextArea();
		editing.addEditor(columnConfig, ta);
		return ta;
	}
	
	@Override
	public boolean isRowEditable() {
		return false;
	}
}
