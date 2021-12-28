package net.datenwerke.rs.grideditor.client.grideditor.vp;

import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordEntryDto;

public class RowValueProviderBoolean extends RowValueProvider<Boolean> {

   public RowValueProviderBoolean(int index) {
      super(index);
   }

   @Override
   public Boolean getValue(GridEditorRecordDto object) {
      GridEditorRecordEntryDto entry = object.getData().get(index);
      if (entry.isEntryNull())
         return null;

      return entry.isBooleanValue();
   }

   @Override
   public void setValue(GridEditorRecordDto object, Boolean value) {
      GridEditorRecordEntryDto entry = object.getData().get(index);
      if (null == value)
         entry.setEntryNull(true);
      else {
         entry.setEntryNull(false);
         entry.setBooleanValue(value);
      }
   }

}