package net.datenwerke.rs.core.client.datasinkmanager.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.treedb.client.treedb.rpc.RPCTreeLoader;

@RemoteServiceRelativePath("datasinks_tree")
public interface DatasinkTreeLoader extends RemoteService, RPCTreeLoader {

}
