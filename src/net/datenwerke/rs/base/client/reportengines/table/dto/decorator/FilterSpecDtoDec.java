package net.datenwerke.rs.base.client.reportengines.table.dto.decorator;

import net.datenwerke.gxtdto.client.dtomanager.IdedDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterSpecDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;

/**
 * Dto Decorator for {@link FilterSpecDto}
 *
 */
public abstract class FilterSpecDtoDec extends FilterSpecDto implements IdedDto {

   private static final long serialVersionUID = 1L;

   public FilterSpecDtoDec() {
      super();
   }

   public String toDisplayTitle() {
      return "Filter";
   }

   public boolean isStillValid(TableReportDto report) {
      return true;
   }

   public abstract FilterSpecDto cloneFilter();

}
