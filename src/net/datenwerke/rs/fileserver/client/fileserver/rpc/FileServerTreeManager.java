package net.datenwerke.rs.fileserver.client.fileserver.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.treedb.client.treedb.rpc.RPCTreeManager;

@RemoteServiceRelativePath("fileserver")
public interface FileServerTreeManager extends RemoteService, RPCTreeManager {

}
