package net.datenwerke.rs.base.server.datasources;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.datenwerke.rs.base.client.datasources.statementmanager.rpc.StatementManagerRpcService;
import net.datenwerke.rs.base.service.datasources.statementmanager.StatementManagerService;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;

@Singleton
public class StatementManagerRpcServiceImpl extends SecuredRemoteServiceServlet implements StatementManagerRpcService {

	private static final long serialVersionUID = 7450755126306154348L;
	private StatementManagerService statementManagerService;
	
	@Inject
	public StatementManagerRpcServiceImpl(StatementManagerService statementManagerService) {
		this.statementManagerService = statementManagerService;
	}

	@Override
	public void cancelStatement(String statementId) {
		statementManagerService.cancelStatement(statementId);
	}

}
