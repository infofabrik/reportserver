package net.datenwerke.rs.base.client.reportengines.table.helpers.filter;

import java.util.ArrayList;
import java.util.List;

import com.sencha.gxt.data.shared.ListStore;

import net.datenwerke.gxtdto.client.model.StringBaseModel;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;

/**
 * 
 *
 */
public abstract class AbstractSingleFilterAspect extends AbstractFilterAspect<StringBaseModel> {

   public AbstractSingleFilterAspect(String titleString, TableReportDto report, ColumnDto column, FilterType type,
         String executeToken) {
      super(titleString, report, column, type, executeToken);
   }

   @Override
   public void storeConfiguration() {
      ListStore<StringBaseModel> store = this.selectionPanel.getStore();

      List<String> newValues = new ArrayList<String>();
      for (StringBaseModel m : store.getAll())
         newValues.add(filterService.getStringValue(m.getValue(), column.getType()));

      setValues(column.getFilter(), newValues);
   }

   abstract protected void setValues(FilterDto filter, List<String> newValues);

   abstract protected List<String> getValues(FilterDto filter);

   @Override
   public void loadConfiguration(FilterDto filter) {
      /* stop updates */
      selectionPanel.setFireUpdates(false);

      /* insert data into selection panel */
      ListStore<StringBaseModel> store = this.selectionPanel.getStore();
      for (String v : getValues(filter)) {
         StringBaseModel value = new StringBaseModel();
         value.setValue(v);
         store.add(value);
      }

      /* resume updates */
      selectionPanel.setFireUpdates(true);
   }

}
