package net.datenwerke.rs.computedcolumns.client.computedcolumns.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.computedcolumns.client.computedcolumns.dto.ComputedColumnDto;

@RemoteServiceRelativePath("computed_columns")
public interface ComputedColumnsRpcService extends RemoteService {

	Integer getColumnType(TableReportDto report, ComputedColumnDto oldColumn, ComputedColumnDto newColumn) throws ServerCallFailedException;
	
	Integer getColumnType(TableReportDto report, ComputedColumnDto column) throws ServerCallFailedException;
}
