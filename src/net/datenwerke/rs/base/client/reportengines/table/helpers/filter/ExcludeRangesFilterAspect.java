package net.datenwerke.rs.base.client.reportengines.table.helpers.filter;

import java.util.List;

import net.datenwerke.rs.base.client.reportengines.table.columnfilter.locale.FilterMessages;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterRangeDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;

/**
 * 
 *
 */
public class ExcludeRangesFilterAspect extends AbstractRangeFilterAspect {

   public ExcludeRangesFilterAspect(TableReportDto report, ColumnDto column, String executeToken) {
      super(FilterMessages.INSTANCE.excludeRangeTitle(), report, column, FilterType.Exclude, executeToken);
   }

   @Override
   protected List<FilterRangeDto> getValues(FilterDto filter) {
      return filter.getExcludeRanges();
   }

   @Override
   protected SelectionPanel<FilterRangeDto> createSelectionPanel(ColumnDto column) {
      return new RangeSelectionPanel(this, column);
   }

   @Override
   protected void setValues(FilterDto filter, List<FilterRangeDto> newValues) {
      filter.setExcludeRanges(newValues);
   }

}
