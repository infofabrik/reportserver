package net.datenwerke.rs.base.client.reportengines.table;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.base.client.reportengines.table.dto.AggregateFunctionDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;

@ImplementedBy(TableReportUiServiceImpl.class)
public interface TableReportUiService {

	AggregateFunctionDto[] getAvailableAggregateFunctionsFor(ColumnDto col);

	AggregateFunctionDto[] getAvailableNumericAggregateFunctionsFor(ColumnDto col);

}