package net.datenwerke.rs.remoteserver.client.remoteservermanager.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.treedb.client.treedb.rpc.RPCTreeLoader;

@RemoteServiceRelativePath("remoteserver_tree")
public interface RemoteServerTreeLoader extends RemoteService, RPCTreeLoader {

}
