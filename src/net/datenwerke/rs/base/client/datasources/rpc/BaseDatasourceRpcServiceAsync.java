package net.datenwerke.rs.base.client.datasources.rpc;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

import net.datenwerke.gxtdto.client.model.ListStringBaseModel;
import net.datenwerke.rs.base.client.dbhelper.dto.DatabaseHelperDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;

public interface BaseDatasourceRpcServiceAsync {

   void getDBHelperList(AsyncCallback<ArrayList<DatabaseHelperDto>> callback);

   void dummy(DatabaseHelperDto dto, AsyncCallback<DatabaseHelperDto> callback);

   void loadColumnDefinition(DatasourceContainerDto container, String query,
         AsyncCallback<List<String>> transformAndKeepCallback);

   void loadData(DatasourceContainerDto container, PagingLoadConfig loadConfig, String query,
         AsyncCallback<PagingLoadResult<ListStringBaseModel>> transformAndKeepCallback);

}
