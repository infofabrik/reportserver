package net.datenwerke.rs.base.client.datasources.statementmanager;

import javax.inject.Inject;

import net.datenwerke.gxtdto.client.servercommunication.callback.NotamCallback;
import net.datenwerke.rs.base.client.datasources.statementmanager.rpc.StatementManagerRpcServiceAsync;

public class StatementManagerDao {

	private final StatementManagerRpcServiceAsync rpcService;

	@Inject
	public StatementManagerDao(StatementManagerRpcServiceAsync rpcService) {
		this.rpcService = rpcService;
	}
	
	
	public void cancelStatement(String statementIdentifier){
		rpcService.cancelStatement(statementIdentifier, new NotamCallback<Void>(null));
	}
	
	
}
