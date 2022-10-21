package net.datenwerke.rs.base.client.datasources.statementmanager.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface StatementManagerRpcServiceAsync {

   void cancelStatement(String statementId, AsyncCallback<Void> callback);

}
