package net.datenwerke.rs.fileserver.client.fileserver.rpc;

import net.datenwerke.treedb.client.treedb.rpc.RPCTreeManager;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("fileserver")
public interface FileServerTreeManager extends RemoteService, RPCTreeManager {

}
