package net.datenwerke.rs.adminutils.client.datasourcetester.rpc;

import net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceDto;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DatasourceTesterRPCServiceAsync {

	Request testConnection(DatabaseDatasourceDto databaseDto,
			AsyncCallback<Boolean> callback);

}
