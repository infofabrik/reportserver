package net.datenwerke.rs.core.client.datasinkmanager.rpc;

import net.datenwerke.treedb.client.treedb.rpc.RPCTreeLoader;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("datasinks_tree")
public interface DatasinkTreeLoader extends RemoteService, RPCTreeLoader {

}
