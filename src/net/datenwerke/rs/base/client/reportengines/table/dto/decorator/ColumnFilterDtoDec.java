package net.datenwerke.rs.base.client.reportengines.table.dto.decorator;

import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFilterDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnReferenceDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterSpecDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;

/**
 * Dto Decorator for {@link ColumnFilterDto}
 *
 */
public class ColumnFilterDtoDec extends ColumnFilterDto {

   private static final long serialVersionUID = 1L;

   public ColumnFilterDtoDec() {
      super();
   }

   @Override
   public String toDisplayTitle() {
      return "Filter: " + (null != getColumn() ? getColumn().getName() : "");
   }

   @Override
   public boolean isStillValid(TableReportDto report) {
      ColumnDto a = getColumn();

      if (null == a)
         return false;

      if (!(a instanceof ColumnReferenceDto))
         return true;

      for (ColumnDto col : report.getAdditionalColumns())
         if (a.getName().equals(col.getName()))
            return true;

      return false;
   }

   @Override
   public FilterSpecDto cloneFilter() {
      ColumnFilterDtoDec clone = new ColumnFilterDtoDec();

      if (null != getColumn())
         clone.setColumn(((ColumnDtoDec) getColumn()).cloneColumnForSelection());

      return clone;
   }
}
