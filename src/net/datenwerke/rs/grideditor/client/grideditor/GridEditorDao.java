package net.datenwerke.rs.grideditor.client.grideditor;

import java.util.List;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordDto;
import net.datenwerke.rs.grideditor.client.grideditor.rpc.GridEditorRpcServiceAsync;

import com.google.inject.Inject;

public class GridEditorDao extends Dao {

	private final GridEditorRpcServiceAsync rpcService;

	@Inject
	public GridEditorDao(GridEditorRpcServiceAsync rpcService) {
		super();
		this.rpcService = rpcService;
	}

	public void commitChanges(ReportDto report, String executeToken,
			List<GridEditorRecordDto> modified,
			List<GridEditorRecordDto> modifiedOriginals,
			List<GridEditorRecordDto> deletedRecords, List<GridEditorRecordDto> newRecords, RsAsyncCallback<Void> callback) {
		rpcService.commitChanges(report, executeToken, modified, modifiedOriginals, deletedRecords, newRecords, transformAndKeepCallback(callback)); 
	}

	
}
