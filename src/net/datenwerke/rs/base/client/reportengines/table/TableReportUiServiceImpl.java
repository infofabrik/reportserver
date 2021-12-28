package net.datenwerke.rs.base.client.reportengines.table;

import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.utils.SqlTypes;
import net.datenwerke.rs.base.client.reportengines.table.dto.AggregateFunctionDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;

public class TableReportUiServiceImpl implements TableReportUiService {

   final protected SqlTypes sqlTypes;

   @Inject
   public TableReportUiServiceImpl(SqlTypes sqlTypes) {
      super();
      this.sqlTypes = sqlTypes;
   }

   @Override
   public AggregateFunctionDto[] getAvailableAggregateFunctionsFor(ColumnDto col) {
      AggregateFunctionDto[] values = null;

      if (sqlTypes.isDateLikeType(col.getType()) || sqlTypes.isString(col.getType())) {
         values = new AggregateFunctionDto[] { AggregateFunctionDto.MAX, AggregateFunctionDto.MIN,
               AggregateFunctionDto.COUNT, AggregateFunctionDto.COUNT_DISTINCT };
      } else if (sqlTypes.isLob(col.getType())) {
         values = new AggregateFunctionDto[] {};
      } else {
         values = AggregateFunctionDto.values();
      }

      return values;
   }

   @Override
   public AggregateFunctionDto[] getAvailableNumericAggregateFunctionsFor(ColumnDto col) {
      AggregateFunctionDto[] values = null;

      if (sqlTypes.isDateLikeType(col.getType()) || sqlTypes.isString(col.getType())) {
         values = new AggregateFunctionDto[] { AggregateFunctionDto.COUNT, AggregateFunctionDto.COUNT_DISTINCT };
      } else if (sqlTypes.isLob(col.getType())) {
         values = new AggregateFunctionDto[] {};
      } else {
         values = AggregateFunctionDto.values();
      }

      return values;
   }
}