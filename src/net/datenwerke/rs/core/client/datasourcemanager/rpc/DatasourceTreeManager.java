package net.datenwerke.rs.core.client.datasourcemanager.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.treedb.client.treedb.rpc.RPCTreeManager;

@RemoteServiceRelativePath("datasources_tree")
public interface DatasourceTreeManager extends RemoteService, RPCTreeManager {

}
