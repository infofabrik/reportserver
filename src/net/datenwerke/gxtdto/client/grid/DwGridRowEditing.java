package net.datenwerke.gxtdto.client.grid;

import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.editing.GridRowEditing;

import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;

/**
 *
 * @param <M>
 */
public class DwGridRowEditing<M> extends GridRowEditing<M> {

   public DwGridRowEditing(Grid<M> editableGrid) {
      super(editableGrid);
   }

   @Override
   protected GridRowEditing<M>.RowEditorComponent createRowEditor() {
      GridRowEditing<M>.RowEditorComponent rowEditor = super.createRowEditor();

      rowEditor.addStyleName("rs-row-editor");

      rowEditor.getCancelButton().addStyleName(DwTextButton.CSS_NAME);
      rowEditor.getSaveButton().addStyleName(DwTextButton.CSS_NAME);

      return rowEditor;
   }
}
