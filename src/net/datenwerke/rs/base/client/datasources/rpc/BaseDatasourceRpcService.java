package net.datenwerke.rs.base.client.datasources.rpc;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.gxtdto.client.model.ListStringBaseModel;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.base.client.dbhelper.dto.DatabaseHelperDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

@RemoteServiceRelativePath("base_datasource")
public interface BaseDatasourceRpcService extends RemoteService {

   public ArrayList<DatabaseHelperDto> getDBHelperList() throws ServerCallFailedException;

   public DatabaseHelperDto dummy(DatabaseHelperDto dto);

   List<String> loadColumnDefinition(DatasourceContainerDto container, String query) throws ServerCallFailedException;

   PagingLoadResult<ListStringBaseModel> loadData(DatasourceContainerDto container, PagingLoadConfig loadConfig,
         String query) throws ServerCallFailedException;

}
