package net.datenwerke.rs.transport.client.transport.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.treedb.client.treedb.rpc.RPCTreeLoader;

@RemoteServiceRelativePath("transports_tree")
public interface TransportTreeLoader extends RemoteService, RPCTreeLoader {

}
