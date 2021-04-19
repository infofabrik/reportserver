package net.datenwerke.rs.core.client.datasourcemanager.rpc;

import net.datenwerke.treedb.client.treedb.rpc.RPCTreeManager;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("datasources_tree")
public interface DatasourceTreeManager extends RemoteService, RPCTreeManager {

}
