package net.datenwerke.rs.computedcolumns.client.computedcolumns.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.computedcolumns.client.computedcolumns.dto.ComputedColumnDto;

public interface ComputedColumnsRpcServiceAsync {

	void getColumnType(TableReportDto report, ComputedColumnDto oldColumn, ComputedColumnDto newColumn,
			AsyncCallback<Integer> callback);
	
	void getColumnType(TableReportDto report, ComputedColumnDto column,
			AsyncCallback<Integer> callback);
	
}
