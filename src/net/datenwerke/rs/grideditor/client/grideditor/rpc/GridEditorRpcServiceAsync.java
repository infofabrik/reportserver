package net.datenwerke.rs.grideditor.client.grideditor.rpc;

import java.util.List;

import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordDto;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GridEditorRpcServiceAsync {

	void commitChanges(ReportDto report, String executeToken,
			List<GridEditorRecordDto> modified,
			List<GridEditorRecordDto> modifiedOriginals,
			List<GridEditorRecordDto> deletedRecords,
			List<GridEditorRecordDto> newRecords,
			AsyncCallback<Void> transformAndKeepCallback);

	


}
