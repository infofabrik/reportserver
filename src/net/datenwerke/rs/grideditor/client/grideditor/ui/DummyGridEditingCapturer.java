package net.datenwerke.rs.grideditor.client.grideditor.ui;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.sencha.gxt.data.shared.Converter;
import com.sencha.gxt.widget.core.client.form.Field;
import com.sencha.gxt.widget.core.client.form.IsField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.Grid.GridCell;
import com.sencha.gxt.widget.core.client.grid.editing.AbstractGridEditing;

import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordDto;

public class DummyGridEditingCapturer extends AbstractGridEditing<GridEditorRecordDto> {

   private ColumnConfig<GridEditorRecordDto, ?> columnConfig;
   private Converter<?, ?> converter;
   private IsField<?> field;

   @Override
   public <N, O> void addEditor(ColumnConfig<GridEditorRecordDto, N> columnConfig, Converter<N, O> converter,
         IsField<O> field) {
      this.columnConfig = columnConfig;
      this.converter = converter;
      this.field = field;
   }

   @Override
   public <N> void addEditor(ColumnConfig<GridEditorRecordDto, N> columnConfig, IsField<N> field) {
      this.columnConfig = columnConfig;
      this.field = field;
   }

   public ColumnConfig<GridEditorRecordDto, ?> getColumnConfig() {
      return columnConfig;
   }

   public Converter getConverter() {
      return converter;
   }

   public Field<?> getField() {
      // TODO: GXT CHECK
      if (field instanceof Field)
         return (Field<?>) field;
      throw new IllegalArgumentException("Expected field but got only isField");
   }

   @Override
   public void fireEvent(GwtEvent<?> event) {
   }

   @Override
   public void cancelEditing() {
      // TODO Auto-generated method stub

   }

   @Override
   public void completeEditing() {
      // TODO Auto-generated method stub

   }

   @Override
   public void startEditing(GridCell cell) {
      // TODO Auto-generated method stub

   }

   @Override
   protected SafeHtml getErrorHtml() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   protected boolean isValid() {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   protected void showTooltip(SafeHtml content) {
      // TODO Auto-generated method stub

   }

}
