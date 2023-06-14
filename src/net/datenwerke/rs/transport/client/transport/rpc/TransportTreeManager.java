package net.datenwerke.rs.transport.client.transport.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.treedb.client.treedb.rpc.RPCTreeManager;

@RemoteServiceRelativePath("transports_tree")
public interface TransportTreeManager extends RemoteService, RPCTreeManager {
}
