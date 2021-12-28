package net.datenwerke.rs.grideditor.client.grideditor.vp;

import java.util.Date;

import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordEntryDto;

public class RowValueProviderDate extends RowValueProvider<Date> {

   public RowValueProviderDate(int index) {
      super(index);
   }

   @Override
   public Date getValue(GridEditorRecordDto object) {
      GridEditorRecordEntryDto entry = object.getData().get(index);
      if (entry.isEntryNull())
         return null;

      return entry.getDateValue();
   }

   @Override
   public void setValue(GridEditorRecordDto object, Date value) {
      GridEditorRecordEntryDto entry = object.getData().get(index);
      if (null == value)
         entry.setEntryNull(true);
      else {
         entry.setEntryNull(false);
         entry.setDateValue(value);
      }
   }

}