package net.datenwerke.rs.grideditor.client.grideditor.vp;

import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordEntryDto;

public class RowValueProviderFloat extends RowValueProvider<Float> {

   public RowValueProviderFloat(int index) {
      super(index);
   }

   @Override
   public Float getValue(GridEditorRecordDto object) {
      GridEditorRecordEntryDto entry = object.getData().get(index);
      if (entry.isEntryNull())
         return null;

      return entry.getFloatValue();
   }

   @Override
   public void setValue(GridEditorRecordDto object, Float value) {
      GridEditorRecordEntryDto entry = object.getData().get(index);
      if (null == value)
         entry.setEntryNull(true);
      else {
         entry.setEntryNull(false);
         entry.setFloatValue(value);
      }
   }

}