package net.datenwerke.rs.remoteserver.client.remoteservermanager.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.treedb.client.treedb.rpc.RPCTreeManager;

@RemoteServiceRelativePath("remoteserver_tree")
public interface RemoteServerTreeManager extends RemoteService, RPCTreeManager {

}
