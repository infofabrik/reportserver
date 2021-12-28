package net.datenwerke.rs.core.client.datasourcemanager.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;

public interface DatasourceRpcServiceAsync {

	void getDefaultDatasource(AsyncCallback<DatasourceDefinitionDto> callback);

}
