package net.datenwerke.rs.adminutils.client.datasourcetester;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.adminutils.client.datasourcetester.rpc.DatasourceTesterRPCServiceAsync;
import net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceDto;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class DatasourceTesterDao extends Dao {

	
	private final DatasourceTesterRPCServiceAsync rpcService;

	@Inject
	public DatasourceTesterDao(DatasourceTesterRPCServiceAsync rpcService) {
		super();
		this.rpcService = rpcService;
	}
	
	
	public Request testConnection(DatabaseDatasourceDto databaseDto, AsyncCallback<Boolean> callback){
		return rpcService.testConnection(databaseDto, transformAndKeepCallback(callback));
	}
	
}
