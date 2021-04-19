package net.datenwerke.rs.computedcolumns.client.computedcolumns.rpc;

import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.computedcolumns.client.computedcolumns.dto.ComputedColumnDto;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ComputedColumnsRpcServiceAsync {

	void getColumnType(TableReportDto report, ComputedColumnDto oldColumn, ComputedColumnDto newColumn,
			AsyncCallback<Integer> callback);
	
	void getColumnType(TableReportDto report, ComputedColumnDto column,
			AsyncCallback<Integer> callback);
	
}
