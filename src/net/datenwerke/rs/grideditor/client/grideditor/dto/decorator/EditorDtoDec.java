package net.datenwerke.rs.grideditor.client.grideditor.dto.decorator;

import net.datenwerke.rs.grideditor.client.grideditor.dto.EditorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordDto;

import com.sencha.gxt.widget.core.client.form.Field;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.editing.GridEditing;

/**
 * Dto Decorator for {@link EditorDto}
 *
 */
abstract public class EditorDtoDec extends EditorDto {


	private static final long serialVersionUID = 1L;

	public EditorDtoDec() {
		super();
	}

	public abstract Field addEditor(ColumnConfig columnConfig, GridEditing<GridEditorRecordDto> editing);

	public boolean isRowEditable(){
		return true;
	}
}
