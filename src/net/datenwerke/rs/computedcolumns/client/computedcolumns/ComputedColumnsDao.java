package net.datenwerke.rs.computedcolumns.client.computedcolumns;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.computedcolumns.client.computedcolumns.dto.ComputedColumnDto;
import net.datenwerke.rs.computedcolumns.client.computedcolumns.rpc.ComputedColumnsRpcServiceAsync;

public class ComputedColumnsDao extends Dao {

	private final ComputedColumnsRpcServiceAsync rpcService;

	@Inject
	public ComputedColumnsDao(ComputedColumnsRpcServiceAsync rpcService) {
		super();
		this.rpcService = rpcService;
	}

	public void getColumnType(TableReportDto report, ComputedColumnDto oldColumn, ComputedColumnDto newColumn,
			AsyncCallback<Integer> callback){
		rpcService.getColumnType(report, oldColumn, newColumn, transformAndKeepCallback(callback));
	}
	
	public void getColumnType(TableReportDto report, ComputedColumnDto column,
			AsyncCallback<Integer> callback){
		rpcService.getColumnType(report, column, transformAndKeepCallback(callback));
	}
	
}
