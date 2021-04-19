package net.datenwerke.rs.base.client.datasources.statementmanager.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("statementmanager")
public interface StatementManagerRpcService extends RemoteService {

	public void cancelStatement(String statementId);
}
