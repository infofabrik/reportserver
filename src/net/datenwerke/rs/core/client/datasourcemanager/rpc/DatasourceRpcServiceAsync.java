package net.datenwerke.rs.core.client.datasourcemanager.rpc;

import java.util.Map;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;

public interface DatasourceRpcServiceAsync {

   void getDefaultDatasource(AsyncCallback<DatasourceDefinitionDto> callback);

   void getDatasourceInfoDetailsAsHtml(DatabaseDatasourceDto datasourceDto,
         AsyncCallback<Map<String, SafeHtml>> callback);

}
