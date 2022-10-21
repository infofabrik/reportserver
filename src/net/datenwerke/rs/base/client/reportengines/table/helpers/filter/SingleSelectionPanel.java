package net.datenwerke.rs.base.client.reportengines.table.helpers.filter;

import net.datenwerke.gxtdto.client.model.StringBaseModel;
import net.datenwerke.rs.base.client.reportengines.table.columnfilter.locale.FilterMessages;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;

/**
 * 
 *
 */
public class SingleSelectionPanel extends SelectionPanel<StringBaseModel> {

   public SingleSelectionPanel(AbstractFilterAspect<StringBaseModel> filterAspect, ColumnDto column) {
      super(filterAspect, column);
   }

   @Override
   protected GridView<StringBaseModel> initializeGridView() {
      return new SingleColumnGridView(store, column, this);
   }

   @Override
   protected TextView<StringBaseModel> initializeTextView() {
      return new SingleColumnTextView(store, column, this, tabPanel);
   }

   @Override
   public void insertElement(StringBaseModel value) {
      store.add(value);
   }

   @Override
   public void validate() {
      if (sqlTypes.isFloatingPoint(column.getType())) {
         tryParseText();
         for (StringBaseModel item : store.getAll()) {
            if (null != item && null != item.getValue() && !item.getValue().trim().startsWith("${")) {
               throw new IllegalArgumentException(FilterMessages.INSTANCE.noFloatEqualFilterWarning());
            }
         }
      }
   }

}
