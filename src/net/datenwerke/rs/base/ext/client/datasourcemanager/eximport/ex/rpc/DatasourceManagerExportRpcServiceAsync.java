package net.datenwerke.rs.base.ext.client.datasourcemanager.eximport.ex.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.core.client.datasourcemanager.dto.AbstractDatasourceManagerNodeDto;

public interface DatasourceManagerExportRpcServiceAsync {

	void quickExport(AbstractDatasourceManagerNodeDto dto,
			AsyncCallback<Void> callback);

	void loadResult(AsyncCallback<String> callback);

}
