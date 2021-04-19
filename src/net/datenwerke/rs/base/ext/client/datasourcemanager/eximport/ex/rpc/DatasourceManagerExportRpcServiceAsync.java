package net.datenwerke.rs.base.ext.client.datasourcemanager.eximport.ex.rpc;

import net.datenwerke.rs.core.client.datasourcemanager.dto.AbstractDatasourceManagerNodeDto;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DatasourceManagerExportRpcServiceAsync {

	void quickExport(AbstractDatasourceManagerNodeDto dto,
			AsyncCallback<Void> callback);

	void loadResult(AsyncCallback<String> callback);

}
