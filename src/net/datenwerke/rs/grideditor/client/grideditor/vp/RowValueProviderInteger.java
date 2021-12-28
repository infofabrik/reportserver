package net.datenwerke.rs.grideditor.client.grideditor.vp;

import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordEntryDto;

public class RowValueProviderInteger extends RowValueProvider<Integer> {

   public RowValueProviderInteger(int index) {
      super(index);
   }

   @Override
   public Integer getValue(GridEditorRecordDto object) {
      GridEditorRecordEntryDto entry = object.getData().get(index);
      if (entry.isEntryNull())
         return null;

      return entry.getIntValue();
   }

   @Override
   public void setValue(GridEditorRecordDto object, Integer value) {
      GridEditorRecordEntryDto entry = object.getData().get(index);
      if (null == value)
         entry.setEntryNull(true);
      else {
         entry.setEntryNull(false);
         entry.setIntValue(value);
      }
   }

}