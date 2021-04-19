package net.datenwerke.rs.core.client.datasourcemanager.rpc;

import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DatasourceRpcServiceAsync {

	void getDefaultDatasource(AsyncCallback<DatasourceDefinitionDto> callback);

}
