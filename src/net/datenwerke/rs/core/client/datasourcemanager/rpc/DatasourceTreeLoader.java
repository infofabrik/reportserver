package net.datenwerke.rs.core.client.datasourcemanager.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.treedb.client.treedb.rpc.RPCTreeLoader;

@RemoteServiceRelativePath("datasources_tree")
public interface DatasourceTreeLoader extends RemoteService, RPCTreeLoader {

}
