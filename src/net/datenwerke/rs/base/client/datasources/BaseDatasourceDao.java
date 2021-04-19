package net.datenwerke.rs.base.client.datasources;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.gxtdto.client.model.ListStringBaseModel;
import net.datenwerke.rs.base.client.datasources.rpc.BaseDatasourceRpcServiceAsync;
import net.datenwerke.rs.base.client.dbhelper.dto.DatabaseHelperDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

public class BaseDatasourceDao extends Dao {

	private final BaseDatasourceRpcServiceAsync rpcService;
	
	@Inject
	public BaseDatasourceDao(BaseDatasourceRpcServiceAsync rpcService){
		this.rpcService = rpcService;
	}
	
	public void getDBHelperList(AsyncCallback<ArrayList<DatabaseHelperDto>> callback){
		rpcService.getDBHelperList(transformListCallback(callback));
	}
	
	public void loadColumnDefinition(DatasourceContainerDto container, String query,
			AsyncCallback<List<String>> callback) {
		rpcService.loadColumnDefinition(container, query, transformAndKeepCallback(callback));
	}

	public void loadData(DatasourceContainerDto container,
			PagingLoadConfig loadConfig, String query,
			AsyncCallback<PagingLoadResult<ListStringBaseModel>> callback) {
		rpcService.loadData(container, loadConfig, query, transformAndKeepCallback(callback));
	}

}
