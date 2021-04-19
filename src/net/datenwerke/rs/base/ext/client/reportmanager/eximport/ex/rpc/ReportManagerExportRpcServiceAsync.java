package net.datenwerke.rs.base.ext.client.reportmanager.eximport.ex.rpc;

import net.datenwerke.rs.core.client.reportmanager.dto.reports.AbstractReportManagerNodeDto;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ReportManagerExportRpcServiceAsync {

	void quickExport(AbstractReportManagerNodeDto dto, boolean includeVariants,
			AsyncCallback<Void> callback);

	void loadResult(AsyncCallback<String> callback);

}
