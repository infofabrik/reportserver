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
public class IncludeRangesFilterAspect extends AbstractRangeFilterAspect {

   public IncludeRangesFilterAspect(TableReportDto report, ColumnDto column, String executeToken) {
      super(FilterMessages.INSTANCE.includeRangesTitle(), report, column, FilterType.Include, executeToken);
   }

   @Override
   protected List<FilterRangeDto> getValues(FilterDto filter) {
      return filter.getIncludeRanges();
   }

   @Override
   protected SelectionPanel<FilterRangeDto> createSelectionPanel(ColumnDto column) {
      return new RangeSelectionPanel(this, column);
   }

   @Override
   protected void setValues(FilterDto filter, List<FilterRangeDto> newValues) {
      filter.setIncludeRanges(newValues);
   }

}
