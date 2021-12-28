package net.datenwerke.rs.grideditor.client.grideditor.dto.decorator;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.sencha.gxt.data.shared.Converter;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.editing.GridEditing;

import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.TextDateEditorDto;

/**
 * Dto Decorator for {@link TextDateEditorDto}
 *
 */
public class TextDateEditorDtoDec extends TextDateEditorDto {

   private static final long serialVersionUID = 1L;

   public TextDateEditorDtoDec() {
      super();
   }

   @Override
   public DateField addEditor(ColumnConfig columnConfig, GridEditing<GridEditorRecordDto> editing) {
      DateField field = new DateField();
      editing.addEditor(columnConfig, new Converter<String, Date>() {
         @Override
         public String convertFieldValue(Date date) {
            if (null == date)
               return null;
            try {
               String format = getDateFormat();
               return DateTimeFormat.getFormat(format).format(date);
            } catch (Exception e) {
               return DateTimeFormat.getFormat(PredefinedFormat.DATE_MEDIUM).format(date);
            }
         }

         @Override
         public Date convertModelValue(String strDate) {
            if (null == strDate)
               return null;
            try {
               String format = getDateFormat();
               return DateTimeFormat.getFormat(format).parse(strDate);
            } catch (Exception e) {
               return null;
            }
         }

      }, field);
      return field;
   }
}
